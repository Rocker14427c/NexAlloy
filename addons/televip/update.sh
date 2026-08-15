#!/usr/bin/env bash
#
# TeleVip (Telegram) addon auto-update.
#
# Pulls the latest TeleVip-Lsposed sources from upstream and refreshes the
# addon inside this repository, then regenerates the generated client lists.
#
# Run it manually, or let CI run it (see .github/workflows/android.yml).
# After this script, `git status` will show what changed; commit it.
#
set -euo pipefail

REPO_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/../.." && pwd)"
UPSTREAM="https://github.com/mustafa1dev/TeleVip-Lsposed.git"
REF="${TELEVIP_REF:-}"            # optional: pin a branch/tag, e.g. TELEVIP_REF=v3.6.2
TMP="$(mktemp -d)"
trap 'rm -rf "$TMP"' EXIT

echo "[tele vip] cloning upstream TeleVip-Lsposed (${REF:-default branch})..."
if [ -n "$REF" ]; then
  git clone --depth 1 --branch "$REF" "$UPSTREAM" "$TMP/televip"
else
  git clone --depth 1 "$UPSTREAM" "$TMP/televip"
fi

echo "[televip] syncing hook sources -> app/src/main/java/com/my/televip ..."
rsync -a --delete --exclude 'DexHolder.java' \
  "$TMP/televip/app/src/main/java/com/my/televip/" \
  "$REPO_ROOT/app/src/main/java/com/my/televip/"

echo "[televip] syncing translations -> app/src/main/assets/lang ..."
mkdir -p "$REPO_ROOT/app/src/main/assets/lang"
rsync -a --delete \
  "$TMP/televip/app/src/main/assets/lang/" \
  "$REPO_ROOT/app/src/main/assets/lang/"

echo "[televip] syncing settings adapter -> settingsadapter/src/main/java ..."
mkdir -p "$REPO_ROOT/settingsadapter/src/main/java/com/televip/SettingsAdapter"
rsync -a --delete \
  "$TMP/televip/settingsadapter/src/main/java/com/televip/SettingsAdapter/" \
  "$REPO_ROOT/settingsadapter/src/main/java/com/televip/SettingsAdapter/"

echo "[televip] recording upstream commit ..."
git -C "$TMP/televip" rev-parse HEAD > "$REPO_ROOT/addons/televip/UPSTREAM_COMMIT"
echo "[televip] synced from $(cat "$REPO_ROOT/addons/televip/UPSTREAM_COMMIT")"

echo "[televip] regenerating client lists (app list, LSPosed scope, <queries>) ..."
python3 "$REPO_ROOT/addons/televip/gen_clients.py"

echo "[televip] addon up to date."
