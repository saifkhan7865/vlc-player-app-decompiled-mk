package org.videolan.vlc.util;

import android.media.AudioFocusRequest;
import android.media.AudioManager;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.PlaybackService;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0013\u001a\u00020\u0014H\u0003J\u0015\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\fH\u0000¢\u0006\u0002\b\u0018J\b\u0010\u0019\u001a\u00020\u0006H\u0002J\b\u0010\u001a\u001a\u00020\u0014H\u0003R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lorg/videolan/vlc/util/VLCAudioFocusHelper;", "", "service", "Lorg/videolan/vlc/PlaybackService;", "(Lorg/videolan/vlc/PlaybackService;)V", "audioFocusListener", "Landroid/media/AudioManager$OnAudioFocusChangeListener;", "audioFocusRequest", "Landroid/media/AudioFocusRequest;", "audioManager", "Landroid/media/AudioManager;", "hasAudioFocus", "", "lossTransient", "getLossTransient$vlc_android_release", "()Z", "setLossTransient$vlc_android_release", "(Z)V", "podcastPlaying", "abandonAudioFocus", "", "changeAudioFocus", "", "acquire", "changeAudioFocus$vlc_android_release", "createOnAudioFocusChangeListener", "requestAudioFocus", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VLCAudioFocusHelper.kt */
public final class VLCAudioFocusHelper {
    private final AudioManager.OnAudioFocusChangeListener audioFocusListener = createOnAudioFocusChangeListener();
    private AudioFocusRequest audioFocusRequest;
    private AudioManager audioManager;
    private boolean hasAudioFocus;
    private volatile boolean lossTransient;
    /* access modifiers changed from: private */
    public volatile boolean podcastPlaying;
    /* access modifiers changed from: private */
    public final PlaybackService service;

    public VLCAudioFocusHelper(PlaybackService playbackService) {
        Intrinsics.checkNotNullParameter(playbackService, NotificationCompat.CATEGORY_SERVICE);
        this.service = playbackService;
    }

    public final boolean getLossTransient$vlc_android_release() {
        return this.lossTransient;
    }

    public final void setLossTransient$vlc_android_release(boolean z) {
        this.lossTransient = z;
    }

    public final void changeAudioFocus$vlc_android_release(boolean z) {
        if (this.audioManager == null) {
            AudioManager audioManager2 = (AudioManager) ContextCompat.getSystemService(this.service, AudioManager.class);
            if (audioManager2 != null) {
                this.audioManager = audioManager2;
            } else {
                return;
            }
        }
        AudioManager audioManager3 = null;
        if (z && !this.service.hasRenderer()) {
            this.podcastPlaying = this.service.isPodcastPlaying();
            if ((!this.hasAudioFocus || this.podcastPlaying) && requestAudioFocus() == 1) {
                AudioManager audioManager4 = this.audioManager;
                if (audioManager4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("audioManager");
                } else {
                    audioManager3 = audioManager4;
                }
                audioManager3.setParameters("bgm_state=true");
                this.hasAudioFocus = true;
            }
        } else if (this.hasAudioFocus) {
            abandonAudioFocus();
            AudioManager audioManager5 = this.audioManager;
            if (audioManager5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("audioManager");
            } else {
                audioManager3 = audioManager5;
            }
            audioManager3.setParameters("bgm_state=false");
            this.hasAudioFocus = false;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: android.media.AudioManager} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: android.media.AudioFocusRequest} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v2 */
    /* JADX WARNING: type inference failed for: r2v4 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int abandonAudioFocus() {
        /*
            r3 = this;
            boolean r0 = org.videolan.libvlc.util.AndroidUtil.isOOrLater
            java.lang.String r1 = "audioManager"
            r2 = 0
            if (r0 == 0) goto L_0x001f
            android.media.AudioManager r0 = r3.audioManager
            if (r0 != 0) goto L_0x000f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r0 = r2
        L_0x000f:
            android.media.AudioFocusRequest r1 = r3.audioFocusRequest
            if (r1 != 0) goto L_0x0019
            java.lang.String r1 = "audioFocusRequest"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            goto L_0x001a
        L_0x0019:
            r2 = r1
        L_0x001a:
            int r0 = r0.abandonAudioFocusRequest(r2)
            goto L_0x002e
        L_0x001f:
            android.media.AudioManager r0 = r3.audioManager
            if (r0 != 0) goto L_0x0027
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            goto L_0x0028
        L_0x0027:
            r2 = r0
        L_0x0028:
            android.media.AudioManager$OnAudioFocusChangeListener r0 = r3.audioFocusListener
            int r0 = r2.abandonAudioFocus(r0)
        L_0x002e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.VLCAudioFocusHelper.abandonAudioFocus():int");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: android.media.AudioManager} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: android.media.AudioFocusRequest} */
    /* JADX WARNING: type inference failed for: r4v0 */
    /* JADX WARNING: type inference failed for: r4v2 */
    /* JADX WARNING: type inference failed for: r4v4 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int requestAudioFocus() {
        /*
            r6 = this;
            boolean r0 = org.videolan.libvlc.util.AndroidUtil.isOOrLater
            java.lang.String r1 = "audioManager"
            r2 = 1
            r3 = 3
            r4 = 0
            if (r0 == 0) goto L_0x0058
            android.media.AudioAttributes$Builder r0 = new android.media.AudioAttributes$Builder
            r0.<init>()
            org.videolan.vlc.PlaybackService r5 = r6.service
            boolean r5 = r5.isVideoPlaying()
            if (r5 == 0) goto L_0x0017
            goto L_0x0018
        L_0x0017:
            r3 = 2
        L_0x0018:
            android.media.AudioAttributes$Builder r0 = r0.setContentType(r3)
            android.media.AudioAttributes r0 = r0.build()
            android.media.AudioFocusRequest$Builder r3 = new android.media.AudioFocusRequest$Builder
            r3.<init>(r2)
            android.media.AudioManager$OnAudioFocusChangeListener r2 = r6.audioFocusListener
            android.media.AudioFocusRequest$Builder r2 = r3.setOnAudioFocusChangeListener(r2)
            boolean r3 = r6.podcastPlaying
            android.media.AudioFocusRequest$Builder r2 = r2.setWillPauseWhenDucked(r3)
            android.media.AudioFocusRequest$Builder r0 = r2.setAudioAttributes(r0)
            android.media.AudioFocusRequest r0 = r0.build()
            java.lang.String r2 = "build(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)
            r6.audioFocusRequest = r0
            android.media.AudioManager r0 = r6.audioManager
            if (r0 != 0) goto L_0x0048
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r0 = r4
        L_0x0048:
            android.media.AudioFocusRequest r1 = r6.audioFocusRequest
            if (r1 != 0) goto L_0x0052
            java.lang.String r1 = "audioFocusRequest"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            goto L_0x0053
        L_0x0052:
            r4 = r1
        L_0x0053:
            int r0 = r0.requestAudioFocus(r4)
            goto L_0x0067
        L_0x0058:
            android.media.AudioManager r0 = r6.audioManager
            if (r0 != 0) goto L_0x0060
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            goto L_0x0061
        L_0x0060:
            r4 = r0
        L_0x0061:
            android.media.AudioManager$OnAudioFocusChangeListener r0 = r6.audioFocusListener
            int r0 = r4.requestAudioFocus(r0, r3, r2)
        L_0x0067:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.VLCAudioFocusHelper.requestAudioFocus():int");
    }

    private final AudioManager.OnAudioFocusChangeListener createOnAudioFocusChangeListener() {
        return new VLCAudioFocusHelper$createOnAudioFocusChangeListener$1(this);
    }
}
