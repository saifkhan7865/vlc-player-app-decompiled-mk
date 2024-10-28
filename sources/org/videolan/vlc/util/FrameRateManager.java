package org.videolan.vlc.util;

import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.Handler;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import java.math.BigDecimal;
import java.math.RoundingMode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.VersionDependantKt;
import org.videolan.vlc.gui.dialogs.adapters.VlcTrack;

@Metadata(d1 = {"\u0000I\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\n\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0018\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\u0018\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001bH\u0007J\u0018\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001bH\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u001d"}, d2 = {"Lorg/videolan/vlc/util/FrameRateManager;", "", "activity", "Landroidx/fragment/app/FragmentActivity;", "service", "Lorg/videolan/vlc/PlaybackService;", "(Landroidx/fragment/app/FragmentActivity;Lorg/videolan/vlc/PlaybackService;)V", "getActivity", "()Landroidx/fragment/app/FragmentActivity;", "displayListener", "org/videolan/vlc/util/FrameRateManager$displayListener$1", "Lorg/videolan/vlc/util/FrameRateManager$displayListener$1;", "getService", "()Lorg/videolan/vlc/PlaybackService;", "getDisplayManager", "Landroid/hardware/display/DisplayManager;", "matchFrameRate", "", "surfaceView", "Landroid/view/SurfaceView;", "window", "Landroid/view/Window;", "setFrameRateM", "videoFrameRate", "", "setFrameRateR", "surface", "Landroid/view/Surface;", "setFrameRateS", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FrameRateManager.kt */
public final class FrameRateManager {
    private final FragmentActivity activity;
    private final FrameRateManager$displayListener$1 displayListener = new FrameRateManager$displayListener$1(this);
    private final PlaybackService service;

    public FrameRateManager(FragmentActivity fragmentActivity, PlaybackService playbackService) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        Intrinsics.checkNotNullParameter(playbackService, NotificationCompat.CATEGORY_SERVICE);
        this.activity = fragmentActivity;
        this.service = playbackService;
    }

    public final FragmentActivity getActivity() {
        return this.activity;
    }

    public final PlaybackService getService() {
        return this.service;
    }

    /* access modifiers changed from: private */
    public final DisplayManager getDisplayManager() {
        Object systemService = this.activity.getSystemService("display");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.hardware.display.DisplayManager");
        return (DisplayManager) systemService;
    }

    public final void matchFrameRate(SurfaceView surfaceView, Window window) {
        Intrinsics.checkNotNullParameter(surfaceView, "surfaceView");
        Intrinsics.checkNotNullParameter(window, "window");
        VlcTrack selectedVideoTrack = VersionDependantKt.getSelectedVideoTrack(this.service.getMediaplayer());
        if (selectedVideoTrack != null && selectedVideoTrack.getFrameRateDen() != 0) {
            float frameRateNum = ((float) selectedVideoTrack.getFrameRateNum()) / ((float) selectedVideoTrack.getFrameRateDen());
            Surface surface = surfaceView.getHolder().getSurface();
            if (Build.VERSION.SDK_INT >= 31) {
                Intrinsics.checkNotNull(surface);
                setFrameRateS(frameRateNum, surface);
            } else if (Build.VERSION.SDK_INT == 30) {
                Intrinsics.checkNotNull(surface);
                setFrameRateR(frameRateNum, surface);
            } else if (Build.VERSION.SDK_INT >= 23) {
                setFrameRateM(frameRateNum, window);
            }
        }
    }

    public final void setFrameRateR(float f, Surface surface) {
        Intrinsics.checkNotNullParameter(surface, "surface");
        surface.setFrameRate(f, 1);
        getDisplayManager().registerDisplayListener(this.displayListener, (Handler) null);
    }

    public final void setFrameRateS(float f, Surface surface) {
        Display.Mode m;
        float[] m2;
        Intrinsics.checkNotNullParameter(surface, "surface");
        boolean z = false;
        if (this.service.getMediaplayer().getLength() < 300000) {
            surface.setFrameRate(f, 1, 0);
            return;
        }
        Display m3 = this.activity.getDisplay();
        if (m3 != null && (m = AppUtils$$ExternalSyntheticApiModelOutline0.m(m3)) != null && (m2 = AppUtils$$ExternalSyntheticApiModelOutline0.m(m)) != null) {
            int length = m2.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                float f2 = m2[i];
                if (StringsKt.startsWith$default(String.valueOf(f), String.valueOf(f2), false, 2, (Object) null) || StringsKt.startsWith$default(String.valueOf(f2), String.valueOf(f), false, 2, (Object) null) || f2 % f == 0.0f) {
                    z = true;
                } else {
                    i++;
                }
            }
            z = true;
        }
        if (z) {
            surface.setFrameRate(f, 1, 1);
            getDisplayManager().registerDisplayListener(this.displayListener, (Handler) null);
        } else if (!z && getDisplayManager().getMatchContentFrameRateUserPreference() == 2) {
            surface.setFrameRate(f, 1, 1);
            getDisplayManager().registerDisplayListener(this.displayListener, (Handler) null);
        }
    }

    public final void setFrameRateM(float f, Window window) {
        Display.Mode[] m;
        Display.Mode mode;
        Intrinsics.checkNotNullParameter(window, "window");
        Object systemService = ContextCompat.getSystemService(this.activity, WindowManager.class);
        Intrinsics.checkNotNull(systemService);
        Display defaultDisplay = ((WindowManager) systemService).getDefaultDisplay();
        if (this.service.getMediaplayer().getLength() > 300000 && (m = AppUtils$$ExternalSyntheticApiModelOutline0.m(defaultDisplay)) != null) {
            Display.Mode m2 = AppUtils$$ExternalSyntheticApiModelOutline0.m(defaultDisplay);
            int length = m.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    mode = m2;
                    break;
                }
                mode = m[i];
                if (mode.getPhysicalHeight() == m2.getPhysicalHeight() && mode.getPhysicalWidth() == m2.getPhysicalWidth() && (Intrinsics.areEqual((Object) new BigDecimal(String.valueOf(f)).setScale(1, RoundingMode.FLOOR), (Object) new BigDecimal(String.valueOf(AppUtils$$ExternalSyntheticApiModelOutline0.m(mode))).setScale(1, RoundingMode.FLOOR)) || AppUtils$$ExternalSyntheticApiModelOutline0.m(mode) % f == 0.0f)) {
                    break;
                }
                i++;
            }
            if (!Intrinsics.areEqual((Object) mode, (Object) m2)) {
                window.getAttributes().preferredDisplayModeId = AppUtils$$ExternalSyntheticApiModelOutline0.m(mode);
                getDisplayManager().registerDisplayListener(this.displayListener, (Handler) null);
            }
        }
    }
}
