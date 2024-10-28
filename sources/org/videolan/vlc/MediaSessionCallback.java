package org.videolan.vlc;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import android.view.KeyEvent;
import androidx.lifecycle.LifecycleOwnerKt;
import java.security.SecureRandom;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.resources.AndroidDevices;
import org.videolan.resources.Constants;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;
import org.videolan.tools.Settings;
import org.videolan.vlc.media.PlayerController;
import org.videolan.vlc.util.FlagSet;
import org.videolan.vlc.util.PlaybackAction;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006H\u0002J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0003J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0002J,\u0010\u000f\u001a\u00020\b2\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0006H\u0002J\u001c\u0010\u0016\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010\u001b\u001a\u00020\bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020\bH\u0016J\b\u0010 \u001a\u00020\bH\u0016J\u001a\u0010!\u001a\u00020\b2\u0006\u0010\"\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u001c\u0010#\u001a\u00020\b2\b\u0010$\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u001c\u0010%\u001a\u00020\b2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010(\u001a\u00020\bH\u0016J\u0010\u0010)\u001a\u00020\b2\u0006\u0010*\u001a\u00020+H\u0016J\u0010\u0010,\u001a\u00020\b2\u0006\u0010-\u001a\u00020.H\u0016J\u0010\u0010/\u001a\u00020\b2\u0006\u00100\u001a\u00020\u0014H\u0016J\u0010\u00101\u001a\u00020\b2\u0006\u00102\u001a\u00020\u0014H\u0016J\b\u00103\u001a\u00020\bH\u0016J\b\u00104\u001a\u00020\bH\u0016J\u0010\u00105\u001a\u00020\b2\u0006\u00106\u001a\u00020+H\u0016J\b\u00107\u001a\u00020\bH\u0016J\u0010\u00108\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020+H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lorg/videolan/vlc/MediaSessionCallback;", "Landroid/support/v4/media/session/MediaSessionCompat$Callback;", "playbackService", "Lorg/videolan/vlc/PlaybackService;", "(Lorg/videolan/vlc/PlaybackService;)V", "prevActionSeek", "", "checkForSeekFailure", "", "forward", "isAndroidAutoHardKey", "keyEvent", "Landroid/view/KeyEvent;", "isBluetoothHeadsetHardKey", "isWiredHeadsetHardKey", "loadMedia", "mediaList", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "position", "", "allowRandom", "onCustomAction", "actionId", "", "extras", "Landroid/os/Bundle;", "onFastForward", "onMediaButtonEvent", "mediaButtonEvent", "Landroid/content/Intent;", "onPause", "onPlay", "onPlayFromMediaId", "mediaId", "onPlayFromSearch", "query", "onPlayFromUri", "uri", "Landroid/net/Uri;", "onRewind", "onSeekTo", "pos", "", "onSetPlaybackSpeed", "speed", "", "onSetRepeatMode", "repeatMode", "onSetShuffleMode", "shuffleMode", "onSkipToNext", "onSkipToPrevious", "onSkipToQueueItem", "id", "onStop", "seek", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaSessionCallback.kt */
public final class MediaSessionCallback extends MediaSessionCompat.Callback {
    /* access modifiers changed from: private */
    public final PlaybackService playbackService;
    private boolean prevActionSeek;

    public MediaSessionCallback(PlaybackService playbackService2) {
        Intrinsics.checkNotNullParameter(playbackService2, "playbackService");
        this.playbackService = playbackService2;
    }

    public void onPlay() {
        if (this.playbackService.hasMedia()) {
            this.playbackService.play();
        } else if (!AndroidDevices.INSTANCE.isAndroidTv()) {
            PlaybackService.Companion.loadLastAudio(this.playbackService);
        }
    }

    private final boolean isWiredHeadsetHardKey(KeyEvent keyEvent) {
        return (keyEvent.getDeviceId() == -1 && keyEvent.getFlags() == 0) ? false : true;
    }

    private final boolean isBluetoothHeadsetHardKey(KeyEvent keyEvent) {
        return keyEvent.getKeyCode() != 85 && keyEvent.getDeviceId() == -1 && keyEvent.getFlags() == 0;
    }

    private final boolean isAndroidAutoHardKey(KeyEvent keyEvent) {
        boolean isCarMode = this.playbackService.isCarMode();
        if (isCarMode) {
            Log.i("VLC/MediaSessionCallback", "Android Auto Key Press: " + keyEvent);
        }
        return isCarMode && keyEvent.getDeviceId() == 0 && (keyEvent.getFlags() & 4) != 0;
    }

    public void onCustomAction(String str, Bundle bundle) {
        Object obj = null;
        int i = 2;
        if (Intrinsics.areEqual((Object) str, (Object) Constants.CUSTOM_ACTION_SPEED)) {
            List listOf = CollectionsKt.listOf(Float.valueOf(0.5f), Float.valueOf(0.8f), Float.valueOf(1.0f), Float.valueOf(1.1f), Float.valueOf(1.2f), Float.valueOf(1.5f), Float.valueOf(2.0f));
            Iterator it = listOf.iterator();
            if (it.hasNext()) {
                obj = it.next();
                if (it.hasNext()) {
                    float abs = Math.abs(this.playbackService.getRate() - ((Number) obj).floatValue());
                    do {
                        Object next = it.next();
                        float abs2 = Math.abs(this.playbackService.getRate() - ((Number) next).floatValue());
                        if (Float.compare(abs, abs2) > 0) {
                            obj = next;
                            abs = abs2;
                        }
                    } while (it.hasNext());
                }
            }
            this.playbackService.setRate(((Number) listOf.get((CollectionsKt.indexOf(listOf, obj) + 1) % listOf.size())).floatValue(), true);
        } else if (Intrinsics.areEqual((Object) str, (Object) Constants.CUSTOM_ACTION_BOOKMARK)) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.playbackService), (CoroutineContext) null, (CoroutineStart) null, new MediaSessionCallback$onCustomAction$1(this, (Continuation<? super MediaSessionCallback$onCustomAction$1>) null), 3, (Object) null);
        } else if (Intrinsics.areEqual((Object) str, (Object) Constants.CUSTOM_ACTION_REWIND)) {
            onRewind();
        } else if (Intrinsics.areEqual((Object) str, (Object) Constants.CUSTOM_ACTION_FAST_FORWARD)) {
            onFastForward();
        } else if (Intrinsics.areEqual((Object) str, (Object) Constants.CUSTOM_ACTION_SHUFFLE)) {
            if (this.playbackService.canShuffle()) {
                this.playbackService.shuffle();
            }
        } else if (Intrinsics.areEqual((Object) str, (Object) Constants.CUSTOM_ACTION_REPEAT)) {
            PlaybackService playbackService2 = this.playbackService;
            int repeatType = playbackService2.getRepeatType();
            if (repeatType != 0) {
                i = (repeatType == 1 || repeatType != 2) ? 0 : 1;
            }
            playbackService2.setRepeatType(i);
        }
    }

    public void onPlayFromMediaId(String str, Bundle bundle) {
        Intrinsics.checkNotNullParameter(str, "mediaId");
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.playbackService), (CoroutineContext) null, (CoroutineStart) null, new MediaSessionCallback$onPlayFromMediaId$1(this, bundle, str, (Continuation<? super MediaSessionCallback$onPlayFromMediaId$1>) null), 3, (Object) null);
    }

    static /* synthetic */ void loadMedia$default(MediaSessionCallback mediaSessionCallback, List list, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        mediaSessionCallback.loadMedia(list, i, z);
    }

    private final void loadMedia(List<? extends MediaWrapper> list, int i, boolean z) {
        if (list != null) {
            if (this.playbackService.isCarMode()) {
                for (MediaWrapper addFlags : list) {
                    addFlags.addFlags(8);
                }
            }
            PlaybackService playbackService2 = this.playbackService;
            if (z && playbackService2.isShuffling()) {
                i = new SecureRandom().nextInt(Math.min(list.size(), 500));
            }
            playbackService2.load(list, i);
        }
    }

    private final void seek(long j) {
        PlaybackService.seek$default(this.playbackService, j, 0.0d, true, false, 10, (Object) null);
        PlayerController.updateProgress$default(this.playbackService.getPlaylistManager().getPlayer(), j, 0, 2, (Object) null);
    }

    private final void checkForSeekFailure(boolean z) {
        if (this.playbackService.getPlaylistManager().getPlayer().getLastPosition() != 0.0f) {
            return;
        }
        if (z || this.playbackService.getTime() > 0) {
            this.playbackService.displayPlaybackMessage(R.string.unseekable_stream, new String[0]);
        }
    }

    public void onPlayFromUri(Uri uri, Bundle bundle) {
        this.playbackService.loadUri(uri);
    }

    public void onPlayFromSearch(String str, Bundle bundle) {
        this.playbackService.getMediaSession$vlc_android_release().setPlaybackState(new PlaybackStateCompat.Builder().setActions(this.playbackService.getEnabledActions$vlc_android_release().getCapabilities()).setState(8, this.playbackService.getTime(), this.playbackService.getSpeed()).build());
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.playbackService), Dispatchers.getIO(), (CoroutineStart) null, new MediaSessionCallback$onPlayFromSearch$1(this, str, bundle, (Continuation<? super MediaSessionCallback$onPlayFromSearch$1>) null), 2, (Object) null);
    }

    public void onSetShuffleMode(int i) {
        this.playbackService.setShuffleType(i);
    }

    public void onSetRepeatMode(int i) {
        this.playbackService.setRepeatType(i);
    }

    public void onPause() {
        this.playbackService.pause();
    }

    public void onStop() {
        PlaybackService.stop$default(this.playbackService, false, false, 3, (Object) null);
    }

    public void onSkipToNext() {
        PlaybackService.next$default(this.playbackService, false, 1, (Object) null);
    }

    public void onSkipToPrevious() {
        this.playbackService.previous(false);
    }

    public void onSeekTo(long j) {
        if (j < 0) {
            j += this.playbackService.getTime();
        }
        seek(j);
    }

    public void onFastForward() {
        seek(RangesKt.coerceAtMost(this.playbackService.getTime() + (((long) Settings.INSTANCE.getAudioJumpDelay()) * 1000), this.playbackService.getLength()));
        checkForSeekFailure(true);
    }

    public void onRewind() {
        seek(RangesKt.coerceAtLeast(this.playbackService.getTime() - (((long) Settings.INSTANCE.getAudioJumpDelay()) * 1000), 0));
        checkForSeekFailure(false);
    }

    public void onSkipToQueueItem(long j) {
        this.playbackService.playIndexOrLoadLastPlaylist((int) j);
    }

    public void onSetPlaybackSpeed(float f) {
        this.playbackService.setRate(RangesKt.coerceIn(f, 0.5f, 2.0f), false);
    }

    public boolean onMediaButtonEvent(Intent intent) {
        Parcelable parcelable;
        Intrinsics.checkNotNullParameter(intent, "mediaButtonEvent");
        if (Build.VERSION.SDK_INT >= 33) {
            parcelable = (Parcelable) AppUtils$$ExternalSyntheticApiModelOutline0.m(intent, "android.intent.extra.KEY_EVENT", KeyEvent.class);
        } else {
            Parcelable parcelableExtra = intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
            if (!(parcelableExtra instanceof KeyEvent)) {
                parcelableExtra = null;
            }
            parcelable = (KeyEvent) parcelableExtra;
        }
        KeyEvent keyEvent = (KeyEvent) parcelable;
        if (keyEvent == null) {
            return false;
        }
        if (this.playbackService.getDetectHeadset() && this.playbackService.getSettings$vlc_android_release().getBoolean("ignore_headset_media_button_presses", false)) {
            if (this.playbackService.getHeadsetInserted() && isWiredHeadsetHardKey(keyEvent)) {
                return true;
            }
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter != null && 2 == defaultAdapter.getProfileConnectionState(1) && isBluetoothHeadsetHardKey(keyEvent)) {
                return true;
            }
        }
        if (this.playbackService.hasMedia() || !(keyEvent.getKeyCode() == 126 || keyEvent.getKeyCode() == 85)) {
            if (!isAndroidAutoHardKey(keyEvent) || (keyEvent.getKeyCode() != 88 && keyEvent.getKeyCode() != 87)) {
                return super.onMediaButtonEvent(intent);
            }
            int action = keyEvent.getAction();
            if (action != 0) {
                if (action == 1) {
                    if (!this.prevActionSeek) {
                        FlagSet<PlaybackAction> enabledActions$vlc_android_release = this.playbackService.getEnabledActions$vlc_android_release();
                        int keyCode = keyEvent.getKeyCode();
                        if (keyCode != 87) {
                            if (keyCode == 88 && enabledActions$vlc_android_release.contains(PlaybackAction.ACTION_SKIP_TO_PREVIOUS)) {
                                onSkipToPrevious();
                            }
                        } else if (enabledActions$vlc_android_release.contains(PlaybackAction.ACTION_SKIP_TO_NEXT)) {
                            onSkipToNext();
                        }
                    }
                    this.prevActionSeek = false;
                }
            } else if (this.playbackService.isSeekable() && keyEvent.isLongPress()) {
                int keyCode2 = keyEvent.getKeyCode();
                if (keyCode2 == 87) {
                    onFastForward();
                } else if (keyCode2 == 88) {
                    onRewind();
                }
                this.prevActionSeek = true;
            }
            return true;
        } else if (keyEvent.getAction() != 0) {
            return false;
        } else {
            PlaybackService.Companion.loadLastAudio(this.playbackService);
            return true;
        }
    }
}
