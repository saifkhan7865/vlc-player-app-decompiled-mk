package org.videolan.vlc.gui.video;

import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.core.widget.NestedScrollView;
import androidx.leanback.widget.BrowseFrameLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\u0014J\u0006\u0010\u0015\u001a\u00020\u0012J\b\u0010\u0016\u001a\u00020\u0014H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\f8BX\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X.¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lorg/videolan/vlc/gui/video/VideoPlayerOrientationDelegate;", "", "player", "Lorg/videolan/vlc/gui/video/VideoPlayerActivity;", "(Lorg/videolan/vlc/gui/video/VideoPlayerActivity;)V", "orientationAdapter", "Lorg/videolan/vlc/gui/video/OrientationAdapter;", "orientationList", "Landroidx/recyclerview/widget/RecyclerView;", "orientationMainView", "Landroid/view/View;", "overlayDelegate", "Lorg/videolan/vlc/gui/video/VideoPlayerOverlayDelegate;", "getOverlayDelegate", "()Lorg/videolan/vlc/gui/video/VideoPlayerOverlayDelegate;", "scrollView", "Landroidx/core/widget/NestedScrollView;", "displayOrientation", "", "hideOrientationOverlay", "", "isShowing", "showOrientationOverlay", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoPlayerOrientationDelegate.kt */
public final class VideoPlayerOrientationDelegate {
    private OrientationAdapter orientationAdapter;
    private RecyclerView orientationList;
    private View orientationMainView;
    /* access modifiers changed from: private */
    public final VideoPlayerActivity player;
    private NestedScrollView scrollView;

    public VideoPlayerOrientationDelegate(VideoPlayerActivity videoPlayerActivity) {
        Intrinsics.checkNotNullParameter(videoPlayerActivity, "player");
        this.player = videoPlayerActivity;
    }

    private final VideoPlayerOverlayDelegate getOverlayDelegate() {
        return this.player.getOverlayDelegate();
    }

    public final boolean isShowing() {
        View view = this.orientationMainView;
        if (view != null) {
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("orientationMainView");
                view = null;
            }
            if (view.getVisibility() == 0) {
                return true;
            }
        }
        return false;
    }

    private final void showOrientationOverlay() {
        ViewStubCompat viewStubCompat = (ViewStubCompat) this.player.findViewById(R.id.player_orientation_stub);
        if (viewStubCompat != null) {
            KotlinExtensionsKt.setVisible(viewStubCompat);
        }
        FrameLayout frameLayout = (FrameLayout) this.player.findViewById(R.id.orientation_background);
        if (frameLayout != null) {
            View view = frameLayout;
            this.orientationMainView = view;
            View view2 = null;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("orientationMainView");
                view = null;
            }
            ((BrowseFrameLayout) view.findViewById(R.id.orientation_background)).setOnFocusSearchListener(new VideoPlayerOrientationDelegate$$ExternalSyntheticLambda0(this));
            View view3 = this.orientationMainView;
            if (view3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("orientationMainView");
                view3 = null;
            }
            View findViewById = view3.findViewById(R.id.orientation_list);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
            this.orientationList = (RecyclerView) findViewById;
            View view4 = this.orientationMainView;
            if (view4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("orientationMainView");
                view4 = null;
            }
            View findViewById2 = view4.findViewById(R.id.orientation_scrollview);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
            this.scrollView = (NestedScrollView) findViewById2;
            View view5 = this.orientationMainView;
            if (view5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("orientationMainView");
                view5 = null;
            }
            view5.findViewById(R.id.close).setOnClickListener(new VideoPlayerOrientationDelegate$$ExternalSyntheticLambda1(this));
            RecyclerView recyclerView = this.orientationList;
            if (recyclerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("orientationList");
                recyclerView = null;
            }
            recyclerView.setLayoutManager(new LinearLayoutManager(this.player));
            OrientationAdapter orientationAdapter2 = new OrientationAdapter(this.player.getOrientationMode().getLocked() ? this.player.getOrientationMode().getOrientation() : -1);
            this.orientationAdapter = orientationAdapter2;
            orientationAdapter2.setOnSizeSelectedListener(new VideoPlayerOrientationDelegate$showOrientationOverlay$2$3(this));
            RecyclerView recyclerView2 = this.orientationList;
            if (recyclerView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("orientationList");
                recyclerView2 = null;
            }
            OrientationAdapter orientationAdapter3 = this.orientationAdapter;
            if (orientationAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("orientationAdapter");
                orientationAdapter3 = null;
            }
            recyclerView2.setAdapter(orientationAdapter3);
            View view6 = this.orientationMainView;
            if (view6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("orientationMainView");
            } else {
                view2 = view6;
            }
            view2.setOnClickListener(new VideoPlayerOrientationDelegate$$ExternalSyntheticLambda2(this));
        }
    }

    /* access modifiers changed from: private */
    public static final View showOrientationOverlay$lambda$4$lambda$1(VideoPlayerOrientationDelegate videoPlayerOrientationDelegate, View view, int i) {
        Intrinsics.checkNotNullParameter(videoPlayerOrientationDelegate, "this$0");
        RecyclerView recyclerView = videoPlayerOrientationDelegate.orientationList;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("orientationList");
            recyclerView = null;
        }
        if (recyclerView.hasFocus()) {
            return view;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static final void showOrientationOverlay$lambda$4$lambda$2(VideoPlayerOrientationDelegate videoPlayerOrientationDelegate, View view) {
        Intrinsics.checkNotNullParameter(videoPlayerOrientationDelegate, "this$0");
        videoPlayerOrientationDelegate.hideOrientationOverlay();
    }

    /* access modifiers changed from: private */
    public static final void showOrientationOverlay$lambda$4$lambda$3(VideoPlayerOrientationDelegate videoPlayerOrientationDelegate, View view) {
        Intrinsics.checkNotNullParameter(videoPlayerOrientationDelegate, "this$0");
        videoPlayerOrientationDelegate.hideOrientationOverlay();
    }

    public final void hideOrientationOverlay() {
        View view = this.orientationMainView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("orientationMainView");
            view = null;
        }
        KotlinExtensionsKt.setGone(view);
    }

    public final boolean displayOrientation() {
        PlaybackService service = this.player.getService();
        if (service != null && service.hasRenderer()) {
            return false;
        }
        showOrientationOverlay();
        View view = null;
        VideoPlayerOverlayDelegate.hideOverlay$default(getOverlayDelegate(), true, false, 2, (Object) null);
        View view2 = this.orientationMainView;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("orientationMainView");
        } else {
            view = view2;
        }
        KotlinExtensionsKt.setVisible(view);
        return true;
    }
}
