package org.videolan.vlc.gui.video;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.CoroutineLiveDataKt;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.helpers.BitmapUtil;
import org.videolan.vlc.util.KextensionsKt;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u000e\u001a\u00020\u000fJ\b\u0010\u0010\u001a\u00020\u000fH\u0002J.\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX.¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lorg/videolan/vlc/gui/video/VideoPlayerScreenshotDelegate;", "", "player", "Lorg/videolan/vlc/gui/video/VideoPlayerActivity;", "(Lorg/videolan/vlc/gui/video/VideoPlayerActivity;)V", "container", "Landroid/widget/FrameLayout;", "flash", "Landroid/view/View;", "screenshotActions", "screenshotImageBackground", "screenshotImageView", "Landroid/widget/ImageView;", "screenshotShare", "hide", "", "initScreenshot", "takeScreenshot", "dst", "Ljava/io/File;", "bitmap", "Landroid/graphics/Bitmap;", "surfaceBounds", "", "width", "", "height", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoPlayerScreenshotDelegate.kt */
public final class VideoPlayerScreenshotDelegate {
    private FrameLayout container;
    private View flash;
    private final VideoPlayerActivity player;
    private FrameLayout screenshotActions;
    private View screenshotImageBackground;
    private ImageView screenshotImageView;
    private View screenshotShare;

    public VideoPlayerScreenshotDelegate(VideoPlayerActivity videoPlayerActivity) {
        Intrinsics.checkNotNullParameter(videoPlayerActivity, "player");
        this.player = videoPlayerActivity;
    }

    private final void initScreenshot() {
        ViewStubCompat viewStubCompat = (ViewStubCompat) this.player.findViewById(R.id.player_screenshot_stub);
        if (viewStubCompat != null) {
            KotlinExtensionsKt.setVisible(viewStubCompat);
            View findViewById = this.player.findViewById(R.id.screenshot_bitmap);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
            this.screenshotImageView = (ImageView) findViewById;
            View findViewById2 = this.player.findViewById(R.id.screenshot_bitmap_background);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
            this.screenshotImageBackground = findViewById2;
            View findViewById3 = this.player.findViewById(R.id.screenshot_share);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
            this.screenshotShare = findViewById3;
            View findViewById4 = this.player.findViewById(R.id.screenshot_flash);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
            this.flash = findViewById4;
            View findViewById5 = this.player.findViewById(R.id.screenshotContainer);
            Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
            this.container = (FrameLayout) findViewById5;
            View findViewById6 = this.player.findViewById(R.id.screenshot_actions);
            Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
            this.screenshotActions = (FrameLayout) findViewById6;
        }
    }

    public final void takeScreenshot(File file, Bitmap bitmap, int[] iArr, int i, int i2) {
        View view;
        File file2 = file;
        Bitmap bitmap2 = bitmap;
        int[] iArr2 = iArr;
        int i3 = i;
        int i4 = i2;
        Intrinsics.checkNotNullParameter(file2, "dst");
        Intrinsics.checkNotNullParameter(bitmap2, "bitmap");
        Intrinsics.checkNotNullParameter(iArr2, "surfaceBounds");
        initScreenshot();
        View view2 = this.screenshotShare;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotShare");
            view2 = null;
        }
        view2.setOnClickListener(new VideoPlayerScreenshotDelegate$$ExternalSyntheticLambda4(this, file2));
        ImageView imageView = this.screenshotImageView;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotImageView");
            imageView = null;
        }
        imageView.setImageBitmap(bitmap2);
        ImageView imageView2 = this.screenshotImageView;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotImageView");
            imageView2 = null;
        }
        imageView2.setAlpha(1.0f);
        ImageView imageView3 = this.screenshotImageView;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotImageView");
            imageView3 = null;
        }
        imageView3.setTranslationX((float) iArr2[0]);
        ImageView imageView4 = this.screenshotImageView;
        if (imageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotImageView");
            imageView4 = null;
        }
        imageView4.setTranslationY((float) iArr2[1]);
        ImageView imageView5 = this.screenshotImageView;
        if (imageView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotImageView");
            imageView5 = null;
        }
        imageView5.getLayoutParams().width = i3;
        ImageView imageView6 = this.screenshotImageView;
        if (imageView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotImageView");
            imageView6 = null;
        }
        imageView6.getLayoutParams().height = i4;
        ImageView imageView7 = this.screenshotImageView;
        if (imageView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotImageView");
            imageView7 = null;
        }
        imageView7.setScaleX(1.0f);
        ImageView imageView8 = this.screenshotImageView;
        if (imageView8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotImageView");
            imageView8 = null;
        }
        imageView8.setScaleY(1.0f);
        View view3 = this.screenshotImageBackground;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotImageBackground");
            view3 = null;
        }
        KotlinExtensionsKt.setVisible(view3);
        View view4 = this.screenshotImageBackground;
        if (view4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotImageBackground");
            view4 = null;
        }
        view4.setPivotX(0.0f);
        View view5 = this.screenshotImageBackground;
        if (view5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotImageBackground");
            view5 = null;
        }
        view5.setPivotY(0.0f);
        ImageView imageView9 = this.screenshotImageView;
        if (imageView9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotImageView");
            imageView9 = null;
        }
        KotlinExtensionsKt.setVisible(imageView9);
        ImageView imageView10 = this.screenshotImageView;
        if (imageView10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotImageView");
            imageView10 = null;
        }
        imageView10.setPivotX(0.0f);
        ImageView imageView11 = this.screenshotImageView;
        if (imageView11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotImageView");
            imageView11 = null;
        }
        imageView11.setPivotY(0.0f);
        float f = (float) i3;
        float dp = ((float) KotlinExtensionsKt.getDp(150)) / f;
        float f2 = ((float) i4) * dp;
        float screenHeight = (((float) KextensionsKt.getScreenHeight(this.player)) - f2) - ((float) KotlinExtensionsKt.getDp(48));
        FrameLayout frameLayout = this.screenshotActions;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotActions");
            frameLayout = null;
        }
        frameLayout.setAlpha(0.0f);
        ImageView imageView12 = this.screenshotImageView;
        if (imageView12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotImageView");
            imageView12 = null;
        }
        ViewPropertyAnimator scaleY = imageView12.animate().translationY(screenHeight).translationX((float) KotlinExtensionsKt.getDp(16)).scaleX(dp).scaleY(dp);
        VideoPlayerScreenshotDelegate$$ExternalSyntheticLambda5 videoPlayerScreenshotDelegate$$ExternalSyntheticLambda5 = r0;
        VideoPlayerScreenshotDelegate$$ExternalSyntheticLambda5 videoPlayerScreenshotDelegate$$ExternalSyntheticLambda52 = new VideoPlayerScreenshotDelegate$$ExternalSyntheticLambda5(this, bitmap, i, dp, i2);
        scaleY.withEndAction(videoPlayerScreenshotDelegate$$ExternalSyntheticLambda5);
        int dp2 = KotlinExtensionsKt.getDp(6);
        View view6 = this.screenshotImageBackground;
        if (view6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotImageBackground");
            view6 = null;
        }
        view6.setTranslationY(screenHeight - ((float) dp2));
        View view7 = this.screenshotImageBackground;
        if (view7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotImageBackground");
            view7 = null;
        }
        view7.setTranslationX((float) (KotlinExtensionsKt.getDp(16) - dp2));
        View view8 = this.screenshotImageBackground;
        if (view8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotImageBackground");
            view8 = null;
        }
        float f3 = (float) (dp2 * 2);
        view8.getLayoutParams().width = (int) ((f * dp) + f3);
        View view9 = this.screenshotImageBackground;
        if (view9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotImageBackground");
            view9 = null;
        }
        view9.getLayoutParams().height = (int) (f2 + f3);
        View view10 = this.flash;
        if (view10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("flash");
            view10 = null;
        }
        KotlinExtensionsKt.setVisible(view10);
        View view11 = this.flash;
        if (view11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("flash");
            view = null;
        } else {
            view = view11;
        }
        view.animate().alpha(1.0f).withEndAction(new VideoPlayerScreenshotDelegate$$ExternalSyntheticLambda6(this));
        this.player.getHandler().removeMessages(14);
        this.player.getHandler().sendEmptyMessageDelayed(14, CoroutineLiveDataKt.DEFAULT_TIMEOUT);
    }

    /* access modifiers changed from: private */
    public static final void takeScreenshot$lambda$0(VideoPlayerScreenshotDelegate videoPlayerScreenshotDelegate, File file, View view) {
        Intrinsics.checkNotNullParameter(videoPlayerScreenshotDelegate, "this$0");
        Intrinsics.checkNotNullParameter(file, "$dst");
        KextensionsKt.share((FragmentActivity) videoPlayerScreenshotDelegate.player, file);
    }

    /* access modifiers changed from: private */
    public static final void takeScreenshot$lambda$1(VideoPlayerScreenshotDelegate videoPlayerScreenshotDelegate, Bitmap bitmap, int i, float f, int i2) {
        VideoPlayerScreenshotDelegate videoPlayerScreenshotDelegate2 = videoPlayerScreenshotDelegate;
        int i3 = i;
        float f2 = f;
        int i4 = i2;
        Intrinsics.checkNotNullParameter(videoPlayerScreenshotDelegate2, "this$0");
        Intrinsics.checkNotNullParameter(bitmap, "$bitmap");
        FrameLayout frameLayout = videoPlayerScreenshotDelegate2.screenshotActions;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotActions");
            frameLayout = null;
        }
        frameLayout.setTranslationY((((float) KextensionsKt.getScreenHeight(videoPlayerScreenshotDelegate2.player)) - ((float) KotlinExtensionsKt.getDp(48))) - ((float) KotlinExtensionsKt.getDp(48)));
        FrameLayout frameLayout2 = videoPlayerScreenshotDelegate2.screenshotActions;
        if (frameLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotActions");
            frameLayout2 = null;
        }
        frameLayout2.getLayoutParams().width = KotlinExtensionsKt.getDp(250);
        FrameLayout frameLayout3 = videoPlayerScreenshotDelegate2.screenshotActions;
        if (frameLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotActions");
            frameLayout3 = null;
        }
        KotlinExtensionsKt.setVisible(frameLayout3);
        FrameLayout frameLayout4 = videoPlayerScreenshotDelegate2.screenshotActions;
        if (frameLayout4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotActions");
            frameLayout4 = null;
        }
        frameLayout4.animate().alpha(1.0f);
        View view = videoPlayerScreenshotDelegate2.screenshotImageBackground;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotImageBackground");
            view = null;
        }
        view.animate().alpha(1.0f);
        BitmapUtil bitmapUtil = BitmapUtil.INSTANCE;
        double d = (double) i3;
        double d2 = (double) f2;
        Double.isNaN(d);
        Double.isNaN(d2);
        double d3 = (double) i4;
        Double.isNaN(d3);
        Double.isNaN(d2);
        Bitmap roundedRectangleBitmap$default = BitmapUtil.roundedRectangleBitmap$default(bitmapUtil, bitmap, (int) (d * d2), (int) (d3 * d2), (float) KotlinExtensionsKt.getDp(4), false, false, false, false, 240, (Object) null);
        ImageView imageView = videoPlayerScreenshotDelegate2.screenshotImageView;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotImageView");
            imageView = null;
        }
        imageView.setImageBitmap(roundedRectangleBitmap$default);
        ImageView imageView2 = videoPlayerScreenshotDelegate2.screenshotImageView;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotImageView");
            imageView2 = null;
        }
        imageView2.getLayoutParams().width = (int) (((float) i3) * f2);
        ImageView imageView3 = videoPlayerScreenshotDelegate2.screenshotImageView;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotImageView");
            imageView3 = null;
        }
        imageView3.getLayoutParams().height = (int) (((float) i4) * f2);
        ImageView imageView4 = videoPlayerScreenshotDelegate2.screenshotImageView;
        if (imageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotImageView");
            imageView4 = null;
        }
        imageView4.setScaleX(1.0f);
        ImageView imageView5 = videoPlayerScreenshotDelegate2.screenshotImageView;
        if (imageView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotImageView");
            imageView5 = null;
        }
        imageView5.setScaleY(1.0f);
    }

    /* access modifiers changed from: private */
    public static final void takeScreenshot$lambda$3(VideoPlayerScreenshotDelegate videoPlayerScreenshotDelegate) {
        Intrinsics.checkNotNullParameter(videoPlayerScreenshotDelegate, "this$0");
        View view = videoPlayerScreenshotDelegate.flash;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("flash");
            view = null;
        }
        view.animate().alpha(0.0f).withEndAction(new VideoPlayerScreenshotDelegate$$ExternalSyntheticLambda0(videoPlayerScreenshotDelegate));
    }

    /* access modifiers changed from: private */
    public static final void takeScreenshot$lambda$3$lambda$2(VideoPlayerScreenshotDelegate videoPlayerScreenshotDelegate) {
        Intrinsics.checkNotNullParameter(videoPlayerScreenshotDelegate, "this$0");
        View view = videoPlayerScreenshotDelegate.flash;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("flash");
            view = null;
        }
        KotlinExtensionsKt.setGone(view);
    }

    public final void hide() {
        ImageView imageView = this.screenshotImageView;
        View view = null;
        if (imageView != null) {
            if (imageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("screenshotImageView");
                imageView = null;
            }
            imageView.animate().translationYBy((float) KotlinExtensionsKt.getDp(200)).alpha(0.0f).withEndAction(new VideoPlayerScreenshotDelegate$$ExternalSyntheticLambda1(this));
        }
        FrameLayout frameLayout = this.screenshotActions;
        if (frameLayout != null) {
            if (frameLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("screenshotActions");
                frameLayout = null;
            }
            frameLayout.animate().translationYBy((float) KotlinExtensionsKt.getDp(200)).alpha(0.0f).withEndAction(new VideoPlayerScreenshotDelegate$$ExternalSyntheticLambda2(this));
        }
        View view2 = this.screenshotImageBackground;
        if (view2 != null) {
            if (view2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("screenshotImageBackground");
            } else {
                view = view2;
            }
            view.animate().translationYBy((float) KotlinExtensionsKt.getDp(200)).alpha(0.0f).withEndAction(new VideoPlayerScreenshotDelegate$$ExternalSyntheticLambda3(this));
        }
    }

    /* access modifiers changed from: private */
    public static final void hide$lambda$4(VideoPlayerScreenshotDelegate videoPlayerScreenshotDelegate) {
        Intrinsics.checkNotNullParameter(videoPlayerScreenshotDelegate, "this$0");
        ImageView imageView = videoPlayerScreenshotDelegate.screenshotImageView;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotImageView");
            imageView = null;
        }
        KotlinExtensionsKt.setGone(imageView);
    }

    /* access modifiers changed from: private */
    public static final void hide$lambda$5(VideoPlayerScreenshotDelegate videoPlayerScreenshotDelegate) {
        Intrinsics.checkNotNullParameter(videoPlayerScreenshotDelegate, "this$0");
        FrameLayout frameLayout = videoPlayerScreenshotDelegate.screenshotActions;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotActions");
            frameLayout = null;
        }
        KotlinExtensionsKt.setGone(frameLayout);
    }

    /* access modifiers changed from: private */
    public static final void hide$lambda$6(VideoPlayerScreenshotDelegate videoPlayerScreenshotDelegate) {
        Intrinsics.checkNotNullParameter(videoPlayerScreenshotDelegate, "this$0");
        View view = videoPlayerScreenshotDelegate.screenshotImageBackground;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenshotImageBackground");
            view = null;
        }
        KotlinExtensionsKt.setGone(view);
    }
}
