package org.videolan.vlc.gui.video;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.core.widget.NestedScrollView;
import androidx.leanback.widget.BrowseFrameLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.tools.AppScope;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0017J\u0006\u0010\u0018\u001a\u00020\u0015J\r\u0010\u0019\u001a\u0004\u0018\u00010\u0015¢\u0006\u0002\u0010\u001aJ\u0015\u0010\u001b\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001c\u001a\u00020\u001d¢\u0006\u0002\u0010\u001eJ\u0006\u0010\u001f\u001a\u00020\u0017R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\t8BX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X.¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lorg/videolan/vlc/gui/video/VideoPlayerResizeDelegate;", "", "player", "Lorg/videolan/vlc/gui/video/VideoPlayerActivity;", "(Lorg/videolan/vlc/gui/video/VideoPlayerActivity;)V", "foldCheckbox", "Landroid/widget/CheckBox;", "notchCheckbox", "overlayDelegate", "Lorg/videolan/vlc/gui/video/VideoPlayerOverlayDelegate;", "getOverlayDelegate", "()Lorg/videolan/vlc/gui/video/VideoPlayerOverlayDelegate;", "resizeMainView", "Landroid/view/View;", "scrollView", "Landroidx/core/widget/NestedScrollView;", "sizeAdapter", "Lorg/videolan/vlc/gui/video/SizeAdapter;", "sizeList", "Landroidx/recyclerview/widget/RecyclerView;", "displayResize", "", "hideResizeOverlay", "", "isShowing", "resizeVideo", "()Ljava/lang/Boolean;", "setVideoScale", "scale", "Lorg/videolan/libvlc/MediaPlayer$ScaleType;", "(Lorg/videolan/libvlc/MediaPlayer$ScaleType;)Lkotlin/Unit;", "showResizeOverlay", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoPlayerResizeDelegate.kt */
public final class VideoPlayerResizeDelegate {
    private CheckBox foldCheckbox;
    private CheckBox notchCheckbox;
    private final VideoPlayerActivity player;
    private View resizeMainView;
    private NestedScrollView scrollView;
    private SizeAdapter sizeAdapter;
    /* access modifiers changed from: private */
    public RecyclerView sizeList;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VideoPlayerResizeDelegate.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(25:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|25) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0046 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0050 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x005a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0064 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                org.videolan.libvlc.MediaPlayer$ScaleType[] r0 = org.videolan.libvlc.MediaPlayer.ScaleType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                org.videolan.libvlc.MediaPlayer$ScaleType r1 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_BEST_FIT     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                org.videolan.libvlc.MediaPlayer$ScaleType r1 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_FIT_SCREEN     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                org.videolan.libvlc.MediaPlayer$ScaleType r1 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_FILL     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                org.videolan.libvlc.MediaPlayer$ScaleType r1 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_16_9     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                org.videolan.libvlc.MediaPlayer$ScaleType r1 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_4_3     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                org.videolan.libvlc.MediaPlayer$ScaleType r1 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_16_10     // Catch:{ NoSuchFieldError -> 0x003d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                org.videolan.libvlc.MediaPlayer$ScaleType r1 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_221_1     // Catch:{ NoSuchFieldError -> 0x0046 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0046 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0046 }
            L_0x0046:
                org.videolan.libvlc.MediaPlayer$ScaleType r1 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_235_1     // Catch:{ NoSuchFieldError -> 0x0050 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0050 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0050 }
            L_0x0050:
                org.videolan.libvlc.MediaPlayer$ScaleType r1 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_239_1     // Catch:{ NoSuchFieldError -> 0x005a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005a }
            L_0x005a:
                org.videolan.libvlc.MediaPlayer$ScaleType r1 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_5_4     // Catch:{ NoSuchFieldError -> 0x0064 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0064 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0064 }
            L_0x0064:
                org.videolan.libvlc.MediaPlayer$ScaleType r1 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_ORIGINAL     // Catch:{ NoSuchFieldError -> 0x006e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.video.VideoPlayerResizeDelegate.WhenMappings.<clinit>():void");
        }
    }

    public VideoPlayerResizeDelegate(VideoPlayerActivity videoPlayerActivity) {
        Intrinsics.checkNotNullParameter(videoPlayerActivity, "player");
        this.player = videoPlayerActivity;
    }

    private final VideoPlayerOverlayDelegate getOverlayDelegate() {
        return this.player.getOverlayDelegate();
    }

    public final boolean isShowing() {
        View view = this.resizeMainView;
        if (view != null) {
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resizeMainView");
                view = null;
            }
            if (view.getVisibility() == 0) {
                return true;
            }
        }
        return false;
    }

    public final void showResizeOverlay() {
        MediaPlayer.ScaleType scaleType;
        MediaPlayer mediaplayer;
        ViewStubCompat viewStubCompat = (ViewStubCompat) this.player.findViewById(R.id.player_resize_stub);
        if (viewStubCompat != null) {
            KotlinExtensionsKt.setVisible(viewStubCompat);
        }
        FrameLayout frameLayout = (FrameLayout) this.player.findViewById(R.id.resize_background);
        if (frameLayout != null) {
            View view = frameLayout;
            this.resizeMainView = view;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resizeMainView");
                view = null;
            }
            ((BrowseFrameLayout) view.findViewById(R.id.resize_background)).setOnFocusSearchListener(new VideoPlayerResizeDelegate$$ExternalSyntheticLambda0(this));
            View view2 = this.resizeMainView;
            if (view2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resizeMainView");
                view2 = null;
            }
            View findViewById = view2.findViewById(R.id.notch);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
            this.notchCheckbox = (CheckBox) findViewById;
            View view3 = this.resizeMainView;
            if (view3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resizeMainView");
                view3 = null;
            }
            View findViewById2 = view3.findViewById(R.id.notch_title);
            View view4 = this.resizeMainView;
            if (view4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resizeMainView");
                view4 = null;
            }
            View findViewById3 = view4.findViewById(R.id.size_list);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
            this.sizeList = (RecyclerView) findViewById3;
            View view5 = this.resizeMainView;
            if (view5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resizeMainView");
                view5 = null;
            }
            View findViewById4 = view5.findViewById(R.id.resize_scrollview);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
            this.scrollView = (NestedScrollView) findViewById4;
            View view6 = this.resizeMainView;
            if (view6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resizeMainView");
                view6 = null;
            }
            View findViewById5 = view6.findViewById(R.id.foldable);
            Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
            this.foldCheckbox = (CheckBox) findViewById5;
            View view7 = this.resizeMainView;
            if (view7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resizeMainView");
                view7 = null;
            }
            View findViewById6 = view7.findViewById(R.id.foldable_title);
            View view8 = this.resizeMainView;
            if (view8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resizeMainView");
                view8 = null;
            }
            view8.findViewById(R.id.close).setOnClickListener(new VideoPlayerResizeDelegate$$ExternalSyntheticLambda1(this));
            RecyclerView recyclerView = this.sizeList;
            if (recyclerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sizeList");
                recyclerView = null;
            }
            recyclerView.setLayoutManager(new LinearLayoutManager(this.player));
            SizeAdapter sizeAdapter2 = new SizeAdapter();
            this.sizeAdapter = sizeAdapter2;
            sizeAdapter2.setOnSizeSelectedListener(new VideoPlayerResizeDelegate$showResizeOverlay$2$3(this));
            RecyclerView recyclerView2 = this.sizeList;
            if (recyclerView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sizeList");
                recyclerView2 = null;
            }
            SizeAdapter sizeAdapter3 = this.sizeAdapter;
            if (sizeAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sizeAdapter");
                sizeAdapter3 = null;
            }
            recyclerView2.setAdapter(sizeAdapter3);
            SharedPreferences sharedPreferences = (SharedPreferences) Settings.INSTANCE.getInstance(this.player);
            boolean z = true;
            if (this.player.getOverlayDelegate().getFoldingFeature() != null) {
                CheckBox checkBox = this.foldCheckbox;
                if (checkBox == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("foldCheckbox");
                    checkBox = null;
                }
                KotlinExtensionsKt.setVisible(checkBox);
                KotlinExtensionsKt.setVisible(findViewById6);
                CheckBox checkBox2 = this.foldCheckbox;
                if (checkBox2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("foldCheckbox");
                    checkBox2 = null;
                }
                checkBox2.setChecked(sharedPreferences.getBoolean(SettingsKt.ALLOW_FOLD_AUTO_LAYOUT, true));
                CheckBox checkBox3 = this.foldCheckbox;
                if (checkBox3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("foldCheckbox");
                    checkBox3 = null;
                }
                checkBox3.setOnCheckedChangeListener(new VideoPlayerResizeDelegate$$ExternalSyntheticLambda2(sharedPreferences, this));
            } else {
                CheckBox checkBox4 = this.foldCheckbox;
                if (checkBox4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("foldCheckbox");
                    checkBox4 = null;
                }
                KotlinExtensionsKt.setGone(checkBox4);
                KotlinExtensionsKt.setGone(findViewById6);
            }
            if (this.player.getHasPhysicalNotch()) {
                CheckBox checkBox5 = this.notchCheckbox;
                if (checkBox5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("notchCheckbox");
                    checkBox5 = null;
                }
                KotlinExtensionsKt.setVisible(checkBox5);
                KotlinExtensionsKt.setVisible(findViewById2);
                CheckBox checkBox6 = this.notchCheckbox;
                if (checkBox6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("notchCheckbox");
                    checkBox6 = null;
                }
                if (sharedPreferences.getInt(SettingsKt.DISPLAY_UNDER_NOTCH, 1) != 1) {
                    z = false;
                }
                checkBox6.setChecked(z);
                CheckBox checkBox7 = this.notchCheckbox;
                if (checkBox7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("notchCheckbox");
                    checkBox7 = null;
                }
                checkBox7.setOnCheckedChangeListener(new VideoPlayerResizeDelegate$$ExternalSyntheticLambda3(this, sharedPreferences));
            } else {
                KotlinExtensionsKt.setGone(findViewById2);
                CheckBox checkBox8 = this.notchCheckbox;
                if (checkBox8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("notchCheckbox");
                    checkBox8 = null;
                }
                KotlinExtensionsKt.setGone(checkBox8);
            }
            View view9 = this.resizeMainView;
            if (view9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resizeMainView");
                view9 = null;
            }
            view9.setOnClickListener(new VideoPlayerResizeDelegate$$ExternalSyntheticLambda4(this));
            SizeAdapter sizeAdapter4 = this.sizeAdapter;
            if (sizeAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sizeAdapter");
                sizeAdapter4 = null;
            }
            MediaPlayer.ScaleType[] values = MediaPlayer.ScaleType.values();
            PlaybackService service = this.player.getService();
            if (service == null || (mediaplayer = service.getMediaplayer()) == null || (scaleType = mediaplayer.getVideoScale()) == null) {
                scaleType = MediaPlayer.ScaleType.SURFACE_BEST_FIT;
            }
            Intrinsics.checkNotNull(scaleType);
            sizeAdapter4.setSelectedSize(ArraysKt.indexOf((T[]) values, scaleType));
            NestedScrollView nestedScrollView = this.scrollView;
            if (nestedScrollView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("scrollView");
                nestedScrollView = null;
            }
            nestedScrollView.scrollTo(0, 0);
            View view10 = this.resizeMainView;
            if (view10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resizeMainView");
                view10 = null;
            }
            view10.setVisibility(0);
            if (Settings.INSTANCE.getShowTvUi()) {
                Job unused = BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, (CoroutineContext) null, (CoroutineStart) null, new VideoPlayerResizeDelegate$showResizeOverlay$2$7(this, (Continuation<? super VideoPlayerResizeDelegate$showResizeOverlay$2$7>) null), 3, (Object) null);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final View showResizeOverlay$lambda$8$lambda$1(VideoPlayerResizeDelegate videoPlayerResizeDelegate, View view, int i) {
        Intrinsics.checkNotNullParameter(videoPlayerResizeDelegate, "this$0");
        RecyclerView recyclerView = videoPlayerResizeDelegate.sizeList;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sizeList");
            recyclerView = null;
        }
        if (recyclerView.hasFocus()) {
            return view;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static final void showResizeOverlay$lambda$8$lambda$2(VideoPlayerResizeDelegate videoPlayerResizeDelegate, View view) {
        Intrinsics.checkNotNullParameter(videoPlayerResizeDelegate, "this$0");
        videoPlayerResizeDelegate.hideResizeOverlay();
    }

    /* access modifiers changed from: private */
    public static final void showResizeOverlay$lambda$8$lambda$6(VideoPlayerResizeDelegate videoPlayerResizeDelegate, SharedPreferences sharedPreferences, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(videoPlayerResizeDelegate, "this$0");
        Intrinsics.checkNotNullParameter(sharedPreferences, "$settings");
        int i = z ? 1 : 2;
        videoPlayerResizeDelegate.player.getWindow().getAttributes().layoutInDisplayCutoutMode = i;
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(SettingsKt.DISPLAY_UNDER_NOTCH, i);
        edit.apply();
        videoPlayerResizeDelegate.getOverlayDelegate().showOverlay(true);
        VideoPlayerOverlayDelegate.hideOverlay$default(videoPlayerResizeDelegate.getOverlayDelegate(), true, false, 2, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final void showResizeOverlay$lambda$8$lambda$7(VideoPlayerResizeDelegate videoPlayerResizeDelegate, View view) {
        Intrinsics.checkNotNullParameter(videoPlayerResizeDelegate, "this$0");
        videoPlayerResizeDelegate.hideResizeOverlay();
    }

    public final void hideResizeOverlay() {
        View view = this.resizeMainView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resizeMainView");
            view = null;
        }
        KotlinExtensionsKt.setGone(view);
    }

    public final Boolean resizeVideo() {
        PlaybackService service = this.player.getService();
        if (service == null) {
            return null;
        }
        MediaPlayer.ScaleType[] mainScaleTypes = MediaPlayer.ScaleType.getMainScaleTypes();
        Intrinsics.checkNotNullExpressionValue(mainScaleTypes, "getMainScaleTypes(...)");
        MediaPlayer.ScaleType scaleType = MediaPlayer.ScaleType.getMainScaleTypes()[RangesKt.coerceAtLeast(ArraysKt.indexOf((T[]) (Object[]) mainScaleTypes, service.getMediaplayer().getVideoScale()) + 1, 0) % MediaPlayer.ScaleType.getMainScaleTypes().length];
        Intrinsics.checkNotNull(scaleType);
        setVideoScale(scaleType);
        return Boolean.valueOf(this.player.getHandler().sendEmptyMessage(8));
    }

    public final Unit setVideoScale(MediaPlayer.ScaleType scaleType) {
        Intrinsics.checkNotNullParameter(scaleType, "scale");
        PlaybackService service = this.player.getService();
        if (service == null) {
            return null;
        }
        service.getMediaplayer().setVideoScale(scaleType);
        switch (WhenMappings.$EnumSwitchMapping$0[scaleType.ordinal()]) {
            case 1:
                getOverlayDelegate().showInfo(R.string.surface_best_fit, 1000, R.string.resize_tip);
                break;
            case 2:
                getOverlayDelegate().showInfo(R.string.surface_fit_screen, 1000, R.string.resize_tip);
                break;
            case 3:
                getOverlayDelegate().showInfo(R.string.surface_fill, 1000, R.string.resize_tip);
                break;
            case 4:
                VideoPlayerOverlayDelegate overlayDelegate = getOverlayDelegate();
                String string = this.player.getString(R.string.resize_tip);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                overlayDelegate.showInfo("16:9", 1000, string);
                break;
            case 5:
                VideoPlayerOverlayDelegate overlayDelegate2 = getOverlayDelegate();
                String string2 = this.player.getString(R.string.resize_tip);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                overlayDelegate2.showInfo("4:3", 1000, string2);
                break;
            case 6:
                VideoPlayerOverlayDelegate overlayDelegate3 = getOverlayDelegate();
                String string3 = this.player.getString(R.string.resize_tip);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                overlayDelegate3.showInfo("16:10", 1000, string3);
                break;
            case 7:
                VideoPlayerOverlayDelegate overlayDelegate4 = getOverlayDelegate();
                String string4 = this.player.getString(R.string.resize_tip);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                overlayDelegate4.showInfo("2.21:1", 1000, string4);
                break;
            case 8:
                VideoPlayerOverlayDelegate overlayDelegate5 = getOverlayDelegate();
                String string5 = this.player.getString(R.string.resize_tip);
                Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                overlayDelegate5.showInfo("2.35:1", 1000, string5);
                break;
            case 9:
                VideoPlayerOverlayDelegate overlayDelegate6 = getOverlayDelegate();
                String string6 = this.player.getString(R.string.resize_tip);
                Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                overlayDelegate6.showInfo("2.39:1", 1000, string6);
                break;
            case 10:
                VideoPlayerOverlayDelegate overlayDelegate7 = getOverlayDelegate();
                String string7 = this.player.getString(R.string.resize_tip);
                Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
                overlayDelegate7.showInfo("5:4", 1000, string7);
                break;
            case 11:
                getOverlayDelegate().showInfo(R.string.surface_original, 1000, R.string.resize_tip);
                break;
        }
        SettingsKt.putSingle(service.getSettings$vlc_android_release(), SettingsKt.VIDEO_RATIO, Integer.valueOf(scaleType.ordinal()));
        return Unit.INSTANCE;
    }

    public final boolean displayResize() {
        PlaybackService service = this.player.getService();
        if (service != null && service.hasRenderer()) {
            return false;
        }
        showResizeOverlay();
        VideoPlayerOverlayDelegate.hideOverlay$default(getOverlayDelegate(), true, false, 2, (Object) null);
        return true;
    }

    /* access modifiers changed from: private */
    public static final void showResizeOverlay$lambda$8$lambda$4(SharedPreferences sharedPreferences, VideoPlayerResizeDelegate videoPlayerResizeDelegate, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "$settings");
        Intrinsics.checkNotNullParameter(videoPlayerResizeDelegate, "this$0");
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(SettingsKt.ALLOW_FOLD_AUTO_LAYOUT, z);
        edit.apply();
        videoPlayerResizeDelegate.player.getOverlayDelegate().manageHinge();
    }
}
