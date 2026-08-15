<div align="center">
  <h1>NexAlloy</h1>
  <a href="https://discord.gg/QWUrAA2mKq"><img alt="Discord Server" src="https://img.shields.io/badge/Discord%20Server-5865F2.svg?logo=discord&logoColor=white"></a>
  <a href="https://t.me/revancedxposed"><img alt="Telegram Channel" src="https://img.shields.io/badge/Telegram_Channel-blue.svg?logo=telegram&logoColor=white"></a>
  <a href="https://github.com/NexAlloy/NexAlloy/releases/latest"><img alt="GitHub Downloads" src="https://img.shields.io/endpoint?url=https%3A%2F%2Fshields.chsbuffer.workers.dev%2F%3Frepos%3DNexAlloy%2FNexAlloy%26cacheSeconds%3D3600"></a>
  <a href="https://github.com/NexAlloy/NexAlloy"><img alt="GitHub Stars" src="https://img.shields.io/github/stars/NexAlloy/NexAlloy"></a>  
  <br>
</div>

**ChsBuffer's LSPosed module, powered by Morphe, ReVanced, and beyond.**  
> [!CAUTION]
> **Migration Notice:** This project has evolved from **ReVancedXposed** to **NexAlloy**. 
> 
> **Upgrading:** We’ve kept the original Package ID for your convenience. You can install this as an update, but **you must manually export your settings from the old version and import them into the new one** to keep your configuration.

>[!IMPORTANT]  
> - This is **NOT an official Morphe or ReVanced project**, do not ask their developers for help.  
> - **Root access** is strictly **required** to use this module!
> - **Having issues?** Check the **[FAQ](https://github.com/NexAlloy/NexAlloy/wiki/Frequently-Asked-Questions)** before reporting.

## Downloads
- **Release build**: [Download](https://github.com/NexAlloy/NexAlloy/releases/latest)
- **Nightly build**: [Download](https://nightly.link/NexAlloy/NexAlloy/workflows/android/main)

<sub>If you've joined the YouTube beta program, please try the nightly build before reporting an issue.</sub>

## Auto-update & building

This fork updates itself and builds its own APK automatically:

- **TeleVip addon** lives in `addons/televip/` and is synced from upstream
  [mustafa1dev/TeleVip-Lsposed](https://github.com/mustafa1dev/TeleVip-Lsposed)
  by `addons/televip/update.sh` — run it any time (or let CI do it). The
  Telegram client list, LSPosed scope and `<queries>` are regenerated from
  upstream's `ClientChecker.java`, so new clients appear automatically.
- **Morphe patches** are git submodules tracking the NexAlloy `nexalloy`
  branch and are bumped by `git submodule update --remote`.
- **GitHub Actions** (`.github/workflows/android.yml`) runs daily and on every
  push: it syncs both, builds signed release + debug APKs, uploads them as
  artifacts, and publishes a `autobuild` pre-release.

To build locally:

```sh
git submodule update --init --recursive
bash addons/televip/update.sh
./gradlew assembleRelease
```

(Set the `sdk.dir` in `local.properties` and add a `signing.properties` if you
want your own signature; without one the release APK is unsigned.)

## Patches

### YouTube
- Remove ads
- SponsorBlock
- Remove background playback restrictions
- Remove share links tracking query parameter
- Hide and change navigation buttons
- Swipe controls
- Remember video quality changes
- Show video quality button
- Show advanced video quality menu
- Copy video url video player button
- Open external downloader app
- Custom playback speed
- Remember playback speed
- Playback speed dialog button
- Hide layout components
- Hide video action buttons
- Disable Shorts resuming on startup
- Disable video codecs
- Disable auto captions
- Alternative thumbnails
- Bypass image region restrictions

### YouTube Music
- Remove music video ads
- Remove background playback restrictions
- Hide upgrade button
- Hide 'Get Music Premium' label
- Enable exclusive audio playback

### Reddit
- Hide ads
- Sanitize sharing links

### Google Photos
- Spoof Pixel XL

### Photomath
- Unlock plus

### Instagram
- Hide ads

### Threads
- Hide ads

### Strava
- Unlock subscription features
- Disable subscription suggestions

### AllTrails
- Enable Peak membership

### Telegram (TeleVip)
Telegram and its supported clients are patched by the bundled **TeleVip** engine ([mustafa1dev/TeleVip-Lsposed](https://github.com/mustafa1dev/TeleVip-Lsposed)). Settings are configured **inside Telegram** → Settings → TeleVip. Includes: hide "seen"/typing/online status, show deleted messages, save protected stories & voice messages, remove content-saving restrictions, disable stories, local premium, download speed boost, and more. Supported clients: Telegram, Telegram Beta/Web, Plus Messenger, Nagram/NagramX, Nekogram, Cherrygram, Nicegram, iMe, X Plus, Forkgram, Turrit, Telegraph, Telega, Momogram and others.

## Supports
[![Discord Server](https://img.shields.io/badge/Join-Discord-5865F2.svg?logo=discord)](https://discord.gg/QWUrAA2mKq)  
[![FAQ](https://img.shields.io/badge/Read-FAQ-orange.svg?logo=github)](https://github.com/NexAlloy/NexAlloy/wiki/Frequently-Asked-Questions)  
or [Create an issue](https://github.com/NexAlloy/NexAlloy/issues/new/choose)

## ⭐ Credits

[DexKit](https://luckypray.org/DexKit/en/): a high-performance dex runtime parsing library.  
[Morphe](https://morphe.software): Transform Your Android Apps  
[ReVanced](https://revanced.app): Continuing the legacy of Vanced at [revanced.app](https://revanced.app)  
[TeleVip](https://github.com/mustafa1dev/TeleVip-Lsposed): Telegram patches (GPL-3.0)
