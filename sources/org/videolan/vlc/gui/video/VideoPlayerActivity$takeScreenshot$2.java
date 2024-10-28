package org.videolan.vlc.gui.video;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.PixelCopy;
import android.view.SurfaceView;
import android.widget.FrameLayout;
import androidx.core.net.UriKt;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.libvlc.util.VLCVideoLayout;
import org.videolan.resources.AndroidDevices;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.dialogs.adapters.VlcTrack;
import org.videolan.vlc.gui.helpers.BitmapUtil;
import org.videolan.vlc.gui.helpers.UiTools;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.video.VideoPlayerActivity$takeScreenshot$2", f = "VideoPlayerActivity.kt", i = {}, l = {1112}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: VideoPlayerActivity.kt */
final class VideoPlayerActivity$takeScreenshot$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ VideoPlayerActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoPlayerActivity$takeScreenshot$2(VideoPlayerActivity videoPlayerActivity, Continuation<? super VideoPlayerActivity$takeScreenshot$2> continuation) {
        super(2, continuation);
        this.this$0 = videoPlayerActivity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideoPlayerActivity$takeScreenshot$2(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoPlayerActivity$takeScreenshot$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.gui.video.VideoPlayerActivity$takeScreenshot$2$1", f = "VideoPlayerActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.gui.video.VideoPlayerActivity$takeScreenshot$2$1  reason: invalid class name */
    /* compiled from: VideoPlayerActivity.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(videoPlayerActivity, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            FrameLayout frameLayout;
            int i;
            int i2;
            VlcTrack currentVideoTrack;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                VLCVideoLayout videoLayout = videoPlayerActivity.getVideoLayout();
                if (!(videoLayout == null || (frameLayout = (FrameLayout) videoLayout.findViewById(R.id.player_surface_frame)) == null)) {
                    VideoPlayerActivity videoPlayerActivity = videoPlayerActivity;
                    SurfaceView surfaceView = (SurfaceView) frameLayout.findViewById(R.id.surface_video);
                    if (surfaceView != null) {
                        Intrinsics.checkNotNull(surfaceView);
                        PlaybackService service = videoPlayerActivity.getService();
                        if (service == null || (currentVideoTrack = service.getCurrentVideoTrack()) == null) {
                            i = 0;
                            i2 = 0;
                        } else {
                            i = currentVideoTrack.getWidth();
                            i2 = currentVideoTrack.getHeight();
                        }
                        if (i == 0) {
                            i = surfaceView.getWidth();
                        }
                        if (i2 == 0) {
                            i2 = surfaceView.getHeight();
                        }
                        try {
                            Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
                            Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
                            UriKt.toFile(AndroidDevices.MediaFolders.INSTANCE.getEXTERNAL_PUBLIC_SCREENSHOTS_URI_DIRECTORY()).mkdirs();
                            PixelCopy.request(surfaceView, createBitmap, new VideoPlayerActivity$takeScreenshot$2$1$$ExternalSyntheticLambda1(videoPlayerActivity, surfaceView, createBitmap, new File(AndroidDevices.MediaFolders.INSTANCE.getEXTERNAL_PUBLIC_SCREENSHOTS_URI_DIRECTORY().getPath() + "/vlc_" + simpleDateFormat.format(new Date()) + ".jpg"), surfaceView), new Handler(Looper.getMainLooper()));
                        } catch (Exception e) {
                            Log.e("VLC/VideoPlayerActivity", e.getMessage(), e);
                            UiTools.snacker$default(UiTools.INSTANCE, videoPlayerActivity, R.string.screenshot_error, false, 4, (Object) null);
                        }
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }

        /* access modifiers changed from: private */
        public static final void invokeSuspend$lambda$3$lambda$2$lambda$1(VideoPlayerActivity videoPlayerActivity, SurfaceView surfaceView, Bitmap bitmap, File file, SurfaceView surfaceView2, int i) {
            if (i != 0) {
                UiTools.snacker$default(UiTools.INSTANCE, videoPlayerActivity, R.string.screenshot_error, false, 4, (Object) null);
                return;
            }
            int[] iArr = new int[2];
            surfaceView.getLocationOnScreen(iArr);
            BitmapUtil bitmapUtil = BitmapUtil.INSTANCE;
            String absolutePath = file.getAbsolutePath();
            Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
            if (bitmapUtil.saveOnDisk(bitmap, absolutePath)) {
                videoPlayerActivity.getScreenshotDelegate().takeScreenshot(file, bitmap, iArr, surfaceView2.getWidth(), surfaceView2.getHeight());
                return;
            }
            UiTools.snacker$default(UiTools.INSTANCE, videoPlayerActivity, R.string.screenshot_error, false, 4, (Object) null);
        }
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final VideoPlayerActivity videoPlayerActivity = this.this$0;
            this.label = 1;
            if (BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass1((Continuation<? super AnonymousClass1>) null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
