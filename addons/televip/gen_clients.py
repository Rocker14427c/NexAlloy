#!/usr/bin/env python3
"""
Regenerates the Telegram/TeleVip client lists from the synced upstream source
(com.my.televip.ClientChecker.ClientType), so that new Telegram clients picked
up by upstream appear automatically — in the NexAlloy app list, in the LSPosed
module scope, and in the AndroidManifest <queries> block.

Run via `addons/televip/update.sh` or directly:
    python3 addons/televip/gen_clients.py
"""

import re
import sys
from pathlib import Path

REPO = Path(__file__).resolve().parent.parent.parent

CLIENT_CHECKER = REPO / "app/src/main/java/com/my/televip/ClientChecker.java"
APP_PATCH_INFO = REPO / "app/src/main/java/io/github/nexalloy/AppPatchInfo.kt"
SCOPE_ARRAY = REPO / "app/src/main/res/values/arrays.xml"
MANIFEST = REPO / "app/src/main/AndroidManifest.xml"

# Nicer display names for the app list (fall back to the enum constant).
NAME_MAP = {
    "TelegramWeb": "Telegram Web",
    "TelegramPlus": "Plus Messenger",
    "TGConnect": "TG Connect",
    "TelegramBeta": "Telegram Beta",
    "XPlus": "X Plus",
    "iMeWeb": "iMe Direct",
    "forkgram": "Forkgram",
    "forkgramBeta": "ForkClient Beta",
    "NagramXF": "Nagram XF",
}

FIRST_ARG = re.compile(
    r'^\s*(\w+)\s*\(\s*("(?:[^"\\]|\\.)*"|new String\[\]\s*\{[^}]*\})\s*,'
)


def parse_clients() -> list[tuple[str, list[str]]]:
    text = CLIENT_CHECKER.read_text(encoding="utf-8")
    m = re.search(r"enum ClientType\s*\{(.*?);", text, re.S)
    if not m:
        sys.exit(f"[gen_clients] enum ClientType not found in {CLIENT_CHECKER}")

    clients: list[tuple[str, list[str]]] = []
    for raw in m.group(1).splitlines():
        mm = FIRST_ARG.match(raw)
        if not mm:
            continue
        name, first = mm.group(1), mm.group(2)
        if first.startswith('"'):
            pkgs = [first.strip('"')]
        else:  # new String[]{...}
            pkgs = re.findall(r'"([^"]*)"', first)
        if pkgs:
            clients.append((name, pkgs))
    return clients


def replace_between(text: str, begin: str, end: str, body: str) -> str:
    pattern = re.compile(re.escape(begin) + r".*?" + re.escape(end), re.S)
    if not pattern.search(text):
        sys.exit(f"[gen_clients] markers {begin!r} ... {end!r} not found")
    return pattern.sub(begin + "\n" + body + "\n" + end, text, count=1)


def main() -> None:
    clients = parse_clients()
    if not clients:
        sys.exit("[gen_clients] no clients parsed")
    all_pkgs = [pkg for _, pkgs in clients for pkg in pkgs]
    print(f"[gen_clients] {len(clients)} clients / {len(all_pkgs)} packages")

    # 1) NexAlloy app list (ui-only rows); disambiguate extra packages
    items = []
    for name, pkgs in clients:
        for i, pkg in enumerate(pkgs):
            display = NAME_MAP.get(name, name)
            if i > 0:
                display = f"{display} ({pkg})"
            items.append(f'    AppPatchInfo("{display}", "{pkg}", emptyArray()),')
    block = "val uiOnlyAppPatchConfigurations = listOf(\n" + "\n".join(items) + "\n)"
    t = APP_PATCH_INFO.read_text(encoding="utf-8")
    t = replace_between(t, "// BEGIN_TELEVIP_CLIENTS", "// END_TELEVIP_CLIENTS", block)
    APP_PATCH_INFO.write_text(t, encoding="utf-8")

    # 2) LSPosed scope array
    scope = "\n".join(f"        <item>{pkg}</item>" for pkg in all_pkgs)
    t = SCOPE_ARRAY.read_text(encoding="utf-8")
    t = replace_between(t, "<!-- TELEVIP_SCOPE_BEGIN -->", "<!-- TELEVIP_SCOPE_END -->", scope)
    SCOPE_ARRAY.write_text(t, encoding="utf-8")

    # 3) AndroidManifest <queries>
    queries = "\n".join(f'        <package android:name="{pkg}" />' for pkg in all_pkgs)
    t = MANIFEST.read_text(encoding="utf-8")
    t = replace_between(t, "<!-- TELEVIP_QUERIES_BEGIN -->", "<!-- TELEVIP_QUERIES_END -->", queries)
    MANIFEST.write_text(t, encoding="utf-8")

    print("[gen_clients] done.")


if __name__ == "__main__":
    main()
