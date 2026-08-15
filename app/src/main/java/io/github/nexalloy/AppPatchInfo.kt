package io.github.nexalloy

import io.github.nexalloy.hoodles.morphe.alltrails.AllTrailsPatches
import io.github.nexalloy.morphe.music.YTMusicPatches
import io.github.nexalloy.morphe.reddit.RedditPatches
import io.github.nexalloy.morphe.youtube.YouTubePatches
import io.github.nexalloy.revanced.googlephotos.GooglePhotosPatches
import io.github.nexalloy.revanced.meta.MetaPatches
import io.github.nexalloy.revanced.photomath.PhotomathPatches
import io.github.nexalloy.revanced.strava.StravaPatches

class AppPatchInfo(val appName: String, val packageName: String, val patches: Array<Patch>)

val appPatchConfigurations = listOf(
    AppPatchInfo("YouTube", "com.google.android.youtube", YouTubePatches),
    AppPatchInfo("YT Music", "com.google.android.apps.youtube.music", YTMusicPatches),
    AppPatchInfo("Reddit", "com.reddit.frontpage", RedditPatches),
    AppPatchInfo("Google Photos", "com.google.android.apps.photos", GooglePhotosPatches),
    AppPatchInfo("Photomath", "com.microblink.photomath", PhotomathPatches),
    AppPatchInfo("Instagram", "com.instagram.android", MetaPatches),
    AppPatchInfo("Threads", "com.instagram.barcelona", MetaPatches),
    AppPatchInfo("Strava", "com.strava", StravaPatches),
    AppPatchInfo("AllTrails", "com.alltrails.alltrails", AllTrailsPatches),
)

val patchesByPackage = appPatchConfigurations.associate { it.packageName to it.patches }

// Apps shown in the app list but not handled by NexAlloy's DexKit patch engine.
// Telegram and its clients are hooked by the bundled TeleVip module instead
// (com.my.televip.MainHook), with settings injected inside the Telegram app.
// The list below is REGENERATED from upstream ClientChecker.java by
// addons/televip/gen_clients.py — do not edit by hand.
// BEGIN_TELEVIP_CLIENTS
val uiOnlyAppPatchConfigurations = listOf(
    AppPatchInfo("Telegram", "org.telegram.messenger", emptyArray()),
    AppPatchInfo("Telegram Web", "org.telegram.messenger.web", emptyArray()),
    AppPatchInfo("Plus Messenger", "org.telegram.plus", emptyArray()),
    AppPatchInfo("TG Connect", "com.tgconnect.android", emptyArray()),
    AppPatchInfo("Nagram", "xyz.nextalone.nagram", emptyArray()),
    AppPatchInfo("Nicegram", "app.nicegram", emptyArray()),
    AppPatchInfo("Telegram Beta", "org.telegram.messenger.beta", emptyArray()),
    AppPatchInfo("NagramX", "nu.gpu.nagram", emptyArray()),
    AppPatchInfo("X Plus", "com.xplus.messenger", emptyArray()),
    AppPatchInfo("iMe", "com.iMe.android", emptyArray()),
    AppPatchInfo("iMe Direct", "com.iMe.android.web", emptyArray()),
    AppPatchInfo("Forkgram", "org.forkgram.messenger", emptyArray()),
    AppPatchInfo("ForkClient Beta", "org.forkclient.messenger.beta", emptyArray()),
    AppPatchInfo("Telegraph", "ir.ilmili.telegraph", emptyArray()),
    AppPatchInfo("Telega", "ru.dahl.messenger", emptyArray()),
    AppPatchInfo("Momogram", "nekox.messenger.broken", emptyArray()),
    AppPatchInfo("Momogram (momo.gram)", "momo.gram", emptyArray()),
    AppPatchInfo("Nekogram", "tw.nekomimi.nekogram", emptyArray()),
    AppPatchInfo("Cherrygram", "uz.unnarsx.cherrygram", emptyArray()),
    AppPatchInfo("ForkgramClassic", "org.forkgram.classic", emptyArray()),
    AppPatchInfo("Turrit", "org.telegram.group", emptyArray()),
    AppPatchInfo("Nagram XF", "fork.risin42.nagramx", emptyArray()),
)
// END_TELEVIP_CLIENTS
