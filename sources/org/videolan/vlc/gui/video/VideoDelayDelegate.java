package org.videolan.vlc.gui.video;

import android.animation.ValueAnimator;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import com.google.android.material.button.MaterialButton;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.Constants;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.helpers.OnRepeatListenerKey;
import org.videolan.vlc.gui.helpers.OnRepeatListenerTouch;
import org.videolan.vlc.interfaces.IPlaybackSettingsController;
import org.videolan.vlc.media.DelayValues;
import org.videolan.vlc.util.AccessibilityHelperKt;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J \u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00072\b\b\u0002\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0018J\u0018\u0010&\u001a\u00020!2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*H\u0007J\b\u0010+\u001a\u00020!H\u0016J\b\u0010,\u001a\u00020!H\u0002J\u0012\u0010-\u001a\u00020!2\b\u0010.\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010/\u001a\u00020!H\u0016J\b\u00100\u001a\u00020!H\u0007J\b\u00101\u001a\u00020!H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000fX.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0012X.¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u00020\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lorg/videolan/vlc/gui/video/VideoDelayDelegate;", "Landroid/view/View$OnClickListener;", "Lorg/videolan/vlc/interfaces/IPlaybackSettingsController;", "player", "Lorg/videolan/vlc/gui/video/VideoPlayerActivity;", "(Lorg/videolan/vlc/gui/video/VideoPlayerActivity;)V", "audioDelay", "", "btSaveListener", "close", "Landroid/widget/ImageView;", "delayApplyAll", "Lcom/google/android/material/button/MaterialButton;", "delayApplyBt", "delayContainer", "Landroid/view/View;", "delayFirstButton", "delayInfo", "Landroid/widget/TextView;", "delayInfoContainer", "delayResetButton", "delaySecondButton", "delayTitle", "playbackSetting", "Lorg/videolan/vlc/interfaces/IPlaybackSettingsController$DelayState;", "getPlaybackSetting", "()Lorg/videolan/vlc/interfaces/IPlaybackSettingsController$DelayState;", "setPlaybackSetting", "(Lorg/videolan/vlc/interfaces/IPlaybackSettingsController$DelayState;)V", "playbackSettingMinus", "playbackSettingPlus", "spuDelay", "delayAudioOrSpu", "", "delta", "fromCustom", "", "delayState", "delayChanged", "delayValues", "Lorg/videolan/vlc/media/DelayValues;", "service", "Lorg/videolan/vlc/PlaybackService;", "endPlaybackSetting", "initPlaybackSettingInfo", "onClick", "v", "showAudioDelaySetting", "showDelayControls", "showSubsDelaySetting", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoDelayDelegate.kt */
public final class VideoDelayDelegate implements View.OnClickListener, IPlaybackSettingsController {
    private long audioDelay;
    private final View.OnClickListener btSaveListener = new VideoDelayDelegate$$ExternalSyntheticLambda1(this);
    private ImageView close;
    private MaterialButton delayApplyAll;
    private MaterialButton delayApplyBt;
    private View delayContainer;
    private MaterialButton delayFirstButton;
    private TextView delayInfo;
    private View delayInfoContainer;
    private MaterialButton delayResetButton;
    private MaterialButton delaySecondButton;
    private TextView delayTitle;
    private IPlaybackSettingsController.DelayState playbackSetting = IPlaybackSettingsController.DelayState.OFF;
    private ImageView playbackSettingMinus;
    private ImageView playbackSettingPlus;
    /* access modifiers changed from: private */
    public final VideoPlayerActivity player;
    private long spuDelay;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VideoDelayDelegate.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                org.videolan.vlc.interfaces.IPlaybackSettingsController$DelayState[] r0 = org.videolan.vlc.interfaces.IPlaybackSettingsController.DelayState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                org.videolan.vlc.interfaces.IPlaybackSettingsController$DelayState r1 = org.videolan.vlc.interfaces.IPlaybackSettingsController.DelayState.AUDIO     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                org.videolan.vlc.interfaces.IPlaybackSettingsController$DelayState r1 = org.videolan.vlc.interfaces.IPlaybackSettingsController.DelayState.SUBS     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.video.VideoDelayDelegate.WhenMappings.<clinit>():void");
        }
    }

    public VideoDelayDelegate(VideoPlayerActivity videoPlayerActivity) {
        Intrinsics.checkNotNullParameter(videoPlayerActivity, "player");
        this.player = videoPlayerActivity;
    }

    public final IPlaybackSettingsController.DelayState getPlaybackSetting() {
        return this.playbackSetting;
    }

    public final void setPlaybackSetting(IPlaybackSettingsController.DelayState delayState) {
        Intrinsics.checkNotNullParameter(delayState, "<set-?>");
        this.playbackSetting = delayState;
    }

    public final void showDelayControls() {
        int i;
        VideoPlayerActivity videoPlayerActivity;
        int i2;
        VideoPlayerActivity videoPlayerActivity2;
        MaterialButton materialButton;
        MaterialButton materialButton2;
        this.player.getTouchDelegate().clearTouchAction();
        if (!this.player.getDisplayManager().isPrimary()) {
            this.player.getOverlayDelegate().showOverlayTimeout(-1);
        }
        KotlinExtensionsKt.setInvisible(this.player.getOverlayDelegate().getInfo());
        ViewStubCompat viewStubCompat = (ViewStubCompat) this.player.findViewById(R.id.player_overlay_settings_stub);
        if (viewStubCompat != null) {
            viewStubCompat.inflate();
            View findViewById = this.player.findViewById(R.id.player_delay_plus);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
            this.playbackSettingPlus = (ImageView) findViewById;
            View findViewById2 = this.player.findViewById(R.id.player_delay_minus);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
            this.playbackSettingMinus = (ImageView) findViewById2;
            View findViewById3 = this.player.findViewById(R.id.delay_first_button);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
            this.delayFirstButton = (MaterialButton) findViewById3;
            View findViewById4 = this.player.findViewById(R.id.delay_second_button);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
            this.delaySecondButton = (MaterialButton) findViewById4;
            View findViewById5 = this.player.findViewById(R.id.delay_reset_button);
            Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
            this.delayResetButton = (MaterialButton) findViewById5;
            View findViewById6 = this.player.findViewById(R.id.delay_info_container);
            Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
            this.delayInfoContainer = findViewById6;
            View findViewById7 = this.player.findViewById(R.id.delay_textinfo);
            Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
            this.delayInfo = (TextView) findViewById7;
            View findViewById8 = this.player.findViewById(R.id.delay_title);
            Intrinsics.checkNotNullExpressionValue(findViewById8, "findViewById(...)");
            this.delayTitle = (TextView) findViewById8;
            View findViewById9 = this.player.findViewById(R.id.delay_container);
            Intrinsics.checkNotNullExpressionValue(findViewById9, "findViewById(...)");
            this.delayContainer = findViewById9;
            View findViewById10 = this.player.findViewById(R.id.delay_apply_all);
            Intrinsics.checkNotNullExpressionValue(findViewById10, "findViewById(...)");
            this.delayApplyAll = (MaterialButton) findViewById10;
            View findViewById11 = this.player.findViewById(R.id.delay_apply_bt);
            Intrinsics.checkNotNullExpressionValue(findViewById11, "findViewById(...)");
            this.delayApplyBt = (MaterialButton) findViewById11;
            View findViewById12 = this.player.findViewById(R.id.close);
            Intrinsics.checkNotNullExpressionValue(findViewById12, "findViewById(...)");
            this.close = (ImageView) findViewById12;
        }
        MaterialButton materialButton3 = this.delayFirstButton;
        if (materialButton3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("delayFirstButton");
            materialButton3 = null;
        }
        if (this.playbackSetting == IPlaybackSettingsController.DelayState.AUDIO) {
            videoPlayerActivity = this.player;
            i = R.string.audio_delay_start;
        } else {
            videoPlayerActivity = this.player;
            i = R.string.subtitle_delay_first;
        }
        materialButton3.setText(videoPlayerActivity.getString(i));
        MaterialButton materialButton4 = this.delaySecondButton;
        if (materialButton4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("delaySecondButton");
            materialButton4 = null;
        }
        if (this.playbackSetting == IPlaybackSettingsController.DelayState.AUDIO) {
            videoPlayerActivity2 = this.player;
            i2 = R.string.audio_delay_end;
        } else {
            videoPlayerActivity2 = this.player;
            i2 = R.string.subtitle_delay_end;
        }
        materialButton4.setText(videoPlayerActivity2.getString(i2));
        ImageView imageView = this.playbackSettingMinus;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playbackSettingMinus");
            imageView = null;
        }
        View.OnClickListener onClickListener = this;
        imageView.setOnClickListener(onClickListener);
        ImageView imageView2 = this.playbackSettingPlus;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playbackSettingPlus");
            imageView2 = null;
        }
        imageView2.setOnClickListener(onClickListener);
        MaterialButton materialButton5 = this.delayFirstButton;
        if (materialButton5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("delayFirstButton");
            materialButton5 = null;
        }
        materialButton5.setOnClickListener(onClickListener);
        MaterialButton materialButton6 = this.delaySecondButton;
        if (materialButton6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("delaySecondButton");
            materialButton6 = null;
        }
        materialButton6.setOnClickListener(onClickListener);
        MaterialButton materialButton7 = this.delayResetButton;
        if (materialButton7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("delayResetButton");
            materialButton7 = null;
        }
        materialButton7.setOnClickListener(onClickListener);
        MaterialButton materialButton8 = this.delayApplyAll;
        if (materialButton8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("delayApplyAll");
            materialButton8 = null;
        }
        materialButton8.setOnClickListener(onClickListener);
        MaterialButton materialButton9 = this.delayApplyBt;
        if (materialButton9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("delayApplyBt");
            materialButton9 = null;
        }
        materialButton9.setOnClickListener(onClickListener);
        ImageView imageView3 = this.close;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("close");
            imageView3 = null;
        }
        imageView3.setOnClickListener(onClickListener);
        ImageView imageView4 = this.playbackSettingMinus;
        if (imageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playbackSettingMinus");
            imageView4 = null;
        }
        Lifecycle lifecycle = this.player.getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle, "<get-lifecycle>(...)");
        String str = "<get-lifecycle>(...)";
        imageView4.setOnTouchListener(new OnRepeatListenerTouch(0, 0, 0, onClickListener, lifecycle, 7, (DefaultConstructorMarker) null));
        ImageView imageView5 = this.playbackSettingPlus;
        if (imageView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playbackSettingPlus");
            imageView5 = null;
        }
        Lifecycle lifecycle2 = this.player.getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle2, str);
        imageView5.setOnTouchListener(new OnRepeatListenerTouch(0, 0, 0, onClickListener, lifecycle2, 7, (DefaultConstructorMarker) null));
        ImageView imageView6 = this.playbackSettingMinus;
        if (imageView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playbackSettingMinus");
            imageView6 = null;
        }
        Lifecycle lifecycle3 = this.player.getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle3, str);
        imageView6.setOnKeyListener(new OnRepeatListenerKey(0, 0, 0, onClickListener, lifecycle3, 7, (DefaultConstructorMarker) null));
        ImageView imageView7 = this.playbackSettingPlus;
        if (imageView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playbackSettingPlus");
            imageView7 = null;
        }
        Lifecycle lifecycle4 = this.player.getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle4, str);
        imageView7.setOnKeyListener(new OnRepeatListenerKey(0, 0, 0, onClickListener, lifecycle4, 7, (DefaultConstructorMarker) null));
        ImageView imageView8 = this.playbackSettingMinus;
        if (imageView8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playbackSettingMinus");
            imageView8 = null;
        }
        KotlinExtensionsKt.setVisible(imageView8);
        ImageView imageView9 = this.close;
        if (imageView9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("close");
            imageView9 = null;
        }
        KotlinExtensionsKt.setVisible(imageView9);
        ImageView imageView10 = this.playbackSettingPlus;
        if (imageView10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playbackSettingPlus");
            imageView10 = null;
        }
        KotlinExtensionsKt.setVisible(imageView10);
        MaterialButton materialButton10 = this.delayFirstButton;
        if (materialButton10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("delayFirstButton");
            materialButton10 = null;
        }
        KotlinExtensionsKt.setVisible(materialButton10);
        MaterialButton materialButton11 = this.delaySecondButton;
        if (materialButton11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("delaySecondButton");
            materialButton11 = null;
        }
        KotlinExtensionsKt.setVisible(materialButton11);
        if (this.playbackSetting != IPlaybackSettingsController.DelayState.AUDIO || (!this.player.getAudiomanager$vlc_android_release().isBluetoothA2dpOn() && !this.player.getAudiomanager$vlc_android_release().isBluetoothScoOn())) {
            MaterialButton materialButton12 = this.delayApplyBt;
            if (materialButton12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("delayApplyBt");
                materialButton12 = null;
            }
            KotlinExtensionsKt.setGone(materialButton12);
        } else {
            MaterialButton materialButton13 = this.delayApplyBt;
            if (materialButton13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("delayApplyBt");
                materialButton13 = null;
            }
            KotlinExtensionsKt.setVisible(materialButton13);
        }
        ImageView imageView11 = this.playbackSettingPlus;
        if (imageView11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playbackSettingPlus");
            imageView11 = null;
        }
        imageView11.requestFocus();
        initPlaybackSettingInfo();
        if (this.playbackSetting == IPlaybackSettingsController.DelayState.AUDIO) {
            MaterialButton materialButton14 = this.delayApplyAll;
            if (materialButton14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("delayApplyAll");
                materialButton2 = null;
            } else {
                materialButton2 = materialButton14;
            }
            KotlinExtensionsKt.setVisible(materialButton2);
        } else {
            MaterialButton materialButton15 = this.delayApplyAll;
            if (materialButton15 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("delayApplyAll");
                materialButton = null;
            } else {
                materialButton = materialButton15;
            }
            KotlinExtensionsKt.setGone(materialButton);
        }
        if (this.player.getDisplayManager().isPrimary()) {
            this.player.getOverlayDelegate().hideOverlay(true, true);
        }
    }

    private final void initPlaybackSettingInfo() {
        String str;
        String str2;
        this.player.getOverlayDelegate().initInfoOverlay();
        View view = this.delayContainer;
        TextView textView = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("delayContainer");
            view = null;
        }
        KotlinExtensionsKt.setVisible(view);
        int i = WhenMappings.$EnumSwitchMapping$0[this.playbackSetting.ordinal()];
        if (i == 1) {
            StringBuilder sb = new StringBuilder();
            PlaybackService service = this.player.getService();
            Intrinsics.checkNotNull(service);
            sb.append(service.getAudioDelay() / 1000);
            sb.append(" ms");
            str2 = sb.toString();
            str = this.player.getString(R.string.audio_delay);
        } else if (i != 2) {
            str2 = Constants.GROUP_VIDEOS_FOLDER;
            str = "";
        } else {
            StringBuilder sb2 = new StringBuilder();
            PlaybackService service2 = this.player.getService();
            Intrinsics.checkNotNull(service2);
            sb2.append(service2.getSpuDelay() / 1000);
            sb2.append(" ms");
            str2 = sb2.toString();
            str = this.player.getString(R.string.spu_delay);
        }
        Intrinsics.checkNotNull(str);
        TextView textView2 = this.delayTitle;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("delayTitle");
            textView2 = null;
        }
        textView2.setText(str);
        TextView textView3 = this.delayInfo;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("delayInfo");
        } else {
            textView = textView3;
        }
        textView.setText(str2);
    }

    /* JADX WARNING: type inference failed for: r12v12, types: [android.widget.TextView] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onClick(android.view.View r12) {
        /*
            r11 = this;
            r0 = 0
            if (r12 == 0) goto L_0x000c
            int r12 = r12.getId()
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            goto L_0x000d
        L_0x000c:
            r12 = r0
        L_0x000d:
            int r1 = org.videolan.vlc.R.id.player_delay_minus
            if (r12 != 0) goto L_0x0012
            goto L_0x0026
        L_0x0012:
            int r2 = r12.intValue()
            if (r2 != r1) goto L_0x0026
            org.videolan.vlc.interfaces.IPlaybackSettingsController$DelayState r7 = r11.playbackSetting
            r8 = 2
            r9 = 0
            r4 = -50000(0xffffffffffff3cb0, double:NaN)
            r6 = 0
            r3 = r11
            delayAudioOrSpu$default(r3, r4, r6, r7, r8, r9)
            goto L_0x0256
        L_0x0026:
            int r1 = org.videolan.vlc.R.id.player_delay_plus
            if (r12 != 0) goto L_0x002b
            goto L_0x003f
        L_0x002b:
            int r2 = r12.intValue()
            if (r2 != r1) goto L_0x003f
            org.videolan.vlc.interfaces.IPlaybackSettingsController$DelayState r7 = r11.playbackSetting
            r8 = 2
            r9 = 0
            r4 = 50000(0xc350, double:2.47033E-319)
            r6 = 0
            r3 = r11
            delayAudioOrSpu$default(r3, r4, r6, r7, r8, r9)
            goto L_0x0256
        L_0x003f:
            int r1 = org.videolan.vlc.R.id.delay_first_button
            r2 = 1
            r3 = -1
            if (r12 != 0) goto L_0x0048
            goto L_0x00d0
        L_0x0048:
            int r5 = r12.intValue()
            if (r5 != r1) goto L_0x00d0
            org.videolan.vlc.gui.video.VideoPlayerActivity r12 = r11.player
            org.videolan.vlc.PlaybackService r12 = r12.getService()
            if (r12 == 0) goto L_0x006f
            org.videolan.vlc.media.PlaylistManager r12 = r12.getPlaylistManager()
            if (r12 == 0) goto L_0x006f
            androidx.lifecycle.MutableLiveData r12 = r12.getDelayValue()
            if (r12 == 0) goto L_0x006f
            java.lang.Object r12 = r12.getValue()
            org.videolan.vlc.media.DelayValues r12 = (org.videolan.vlc.media.DelayValues) r12
            if (r12 == 0) goto L_0x006f
            long r5 = r12.getStart()
            goto L_0x0070
        L_0x006f:
            r5 = r3
        L_0x0070:
            int r12 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r12 != 0) goto L_0x00bd
            org.videolan.vlc.gui.video.VideoPlayerActivity r12 = r11.player
            org.videolan.vlc.PlaybackService r12 = r12.getService()
            if (r12 == 0) goto L_0x0089
            org.videolan.vlc.media.PlaylistManager r12 = r12.getPlaylistManager()
            if (r12 == 0) goto L_0x0089
            long r5 = java.lang.System.currentTimeMillis()
            r12.setDelayValue(r5, r2)
        L_0x0089:
            org.videolan.vlc.gui.video.VideoPlayerActivity r12 = r11.player
            org.videolan.vlc.PlaybackService r12 = r12.getService()
            if (r12 == 0) goto L_0x0256
            org.videolan.vlc.media.PlaylistManager r12 = r12.getPlaylistManager()
            if (r12 == 0) goto L_0x0256
            androidx.lifecycle.MutableLiveData r12 = r12.getDelayValue()
            if (r12 == 0) goto L_0x0256
            java.lang.Object r12 = r12.getValue()
            org.videolan.vlc.media.DelayValues r12 = (org.videolan.vlc.media.DelayValues) r12
            if (r12 == 0) goto L_0x0256
            long r1 = r12.getStop()
            int r12 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r12 != 0) goto L_0x0256
            com.google.android.material.button.MaterialButton r12 = r11.delaySecondButton
            if (r12 != 0) goto L_0x00b7
            java.lang.String r12 = "delaySecondButton"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12)
            goto L_0x00b8
        L_0x00b7:
            r0 = r12
        L_0x00b8:
            r0.requestFocus()
            goto L_0x0256
        L_0x00bd:
            org.videolan.vlc.gui.video.VideoPlayerActivity r12 = r11.player
            org.videolan.vlc.PlaybackService r12 = r12.getService()
            if (r12 == 0) goto L_0x0256
            org.videolan.vlc.media.PlaylistManager r12 = r12.getPlaylistManager()
            if (r12 == 0) goto L_0x0256
            r12.setDelayValue(r3, r2)
            goto L_0x0256
        L_0x00d0:
            int r1 = org.videolan.vlc.R.id.delay_second_button
            r5 = 0
            if (r12 != 0) goto L_0x00d7
            goto L_0x015f
        L_0x00d7:
            int r6 = r12.intValue()
            if (r6 != r1) goto L_0x015f
            org.videolan.vlc.gui.video.VideoPlayerActivity r12 = r11.player
            org.videolan.vlc.PlaybackService r12 = r12.getService()
            if (r12 == 0) goto L_0x00fe
            org.videolan.vlc.media.PlaylistManager r12 = r12.getPlaylistManager()
            if (r12 == 0) goto L_0x00fe
            androidx.lifecycle.MutableLiveData r12 = r12.getDelayValue()
            if (r12 == 0) goto L_0x00fe
            java.lang.Object r12 = r12.getValue()
            org.videolan.vlc.media.DelayValues r12 = (org.videolan.vlc.media.DelayValues) r12
            if (r12 == 0) goto L_0x00fe
            long r1 = r12.getStop()
            goto L_0x00ff
        L_0x00fe:
            r1 = r3
        L_0x00ff:
            int r12 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r12 != 0) goto L_0x014c
            org.videolan.vlc.gui.video.VideoPlayerActivity r12 = r11.player
            org.videolan.vlc.PlaybackService r12 = r12.getService()
            if (r12 == 0) goto L_0x0118
            org.videolan.vlc.media.PlaylistManager r12 = r12.getPlaylistManager()
            if (r12 == 0) goto L_0x0118
            long r1 = java.lang.System.currentTimeMillis()
            r12.setDelayValue(r1, r5)
        L_0x0118:
            org.videolan.vlc.gui.video.VideoPlayerActivity r12 = r11.player
            org.videolan.vlc.PlaybackService r12 = r12.getService()
            if (r12 == 0) goto L_0x0256
            org.videolan.vlc.media.PlaylistManager r12 = r12.getPlaylistManager()
            if (r12 == 0) goto L_0x0256
            androidx.lifecycle.MutableLiveData r12 = r12.getDelayValue()
            if (r12 == 0) goto L_0x0256
            java.lang.Object r12 = r12.getValue()
            org.videolan.vlc.media.DelayValues r12 = (org.videolan.vlc.media.DelayValues) r12
            if (r12 == 0) goto L_0x0256
            long r1 = r12.getStart()
            int r12 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r12 != 0) goto L_0x0256
            com.google.android.material.button.MaterialButton r12 = r11.delayFirstButton
            if (r12 != 0) goto L_0x0146
            java.lang.String r12 = "delayFirstButton"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12)
            goto L_0x0147
        L_0x0146:
            r0 = r12
        L_0x0147:
            r0.requestFocus()
            goto L_0x0256
        L_0x014c:
            org.videolan.vlc.gui.video.VideoPlayerActivity r12 = r11.player
            org.videolan.vlc.PlaybackService r12 = r12.getService()
            if (r12 == 0) goto L_0x0256
            org.videolan.vlc.media.PlaylistManager r12 = r12.getPlaylistManager()
            if (r12 == 0) goto L_0x0256
            r12.setDelayValue(r3, r5)
            goto L_0x0256
        L_0x015f:
            int r1 = org.videolan.vlc.R.id.delay_reset_button
            if (r12 != 0) goto L_0x0164
            goto L_0x01ae
        L_0x0164:
            int r3 = r12.intValue()
            if (r3 != r1) goto L_0x01ae
            org.videolan.vlc.interfaces.IPlaybackSettingsController$DelayState r12 = r11.playbackSetting
            org.videolan.vlc.interfaces.IPlaybackSettingsController$DelayState r1 = org.videolan.vlc.interfaces.IPlaybackSettingsController.DelayState.AUDIO
            r2 = 0
            if (r12 != r1) goto L_0x017e
            org.videolan.vlc.gui.video.VideoPlayerActivity r12 = r11.player
            org.videolan.vlc.PlaybackService r12 = r12.getService()
            if (r12 == 0) goto L_0x0189
            r12.setAudioDelay(r2)
            goto L_0x0189
        L_0x017e:
            org.videolan.vlc.gui.video.VideoPlayerActivity r12 = r11.player
            org.videolan.vlc.PlaybackService r12 = r12.getService()
            if (r12 == 0) goto L_0x0189
            r12.setSpuDelay(r2)
        L_0x0189:
            android.widget.TextView r12 = r11.delayInfo
            if (r12 != 0) goto L_0x0193
            java.lang.String r12 = "delayInfo"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12)
            goto L_0x0194
        L_0x0193:
            r0 = r12
        L_0x0194:
            java.lang.String r12 = "0 ms"
            java.lang.CharSequence r12 = (java.lang.CharSequence) r12
            r0.setText(r12)
            org.videolan.vlc.gui.video.VideoPlayerActivity r12 = r11.player
            org.videolan.vlc.PlaybackService r12 = r12.getService()
            if (r12 == 0) goto L_0x0256
            org.videolan.vlc.media.PlaylistManager r12 = r12.getPlaylistManager()
            if (r12 == 0) goto L_0x0256
            r12.resetDelayValues()
            goto L_0x0256
        L_0x01ae:
            int r0 = org.videolan.vlc.R.id.delay_apply_all
            java.lang.String r1 = "getString(...)"
            r3 = 1000(0x3e8, double:4.94E-321)
            if (r12 != 0) goto L_0x01b7
            goto L_0x01fd
        L_0x01b7:
            int r6 = r12.intValue()
            if (r6 != r0) goto L_0x01fd
            org.videolan.vlc.gui.video.VideoPlayerActivity r12 = r11.player
            org.videolan.vlc.PlaybackService r12 = r12.getService()
            if (r12 == 0) goto L_0x0256
            org.videolan.tools.Settings r0 = org.videolan.tools.Settings.INSTANCE
            org.videolan.vlc.gui.video.VideoPlayerActivity r6 = r11.player
            java.lang.Object r0 = r0.getInstance(r6)
            android.content.SharedPreferences r0 = (android.content.SharedPreferences) r0
            long r6 = r12.getAudioDelay()
            java.lang.Long r6 = java.lang.Long.valueOf(r6)
            java.lang.String r7 = "audio_delay_global"
            org.videolan.tools.SettingsKt.putSingle(r0, r7, r6)
            org.videolan.vlc.gui.helpers.UiTools r0 = org.videolan.vlc.gui.helpers.UiTools.INSTANCE
            org.videolan.vlc.gui.video.VideoPlayerActivity r6 = r11.player
            r7 = r6
            android.app.Activity r7 = (android.app.Activity) r7
            int r8 = org.videolan.vlc.R.string.audio_delay_global
            long r9 = r12.getAudioDelay()
            long r9 = r9 / r3
            java.lang.String r12 = java.lang.String.valueOf(r9)
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r5] = r12
            java.lang.String r12 = r6.getString(r8, r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r1)
            r0.snacker(r7, r12)
            goto L_0x0256
        L_0x01fd:
            int r0 = org.videolan.vlc.R.id.delay_apply_bt
            if (r12 != 0) goto L_0x0202
            goto L_0x0248
        L_0x0202:
            int r6 = r12.intValue()
            if (r6 != r0) goto L_0x0248
            org.videolan.vlc.gui.video.VideoPlayerActivity r12 = r11.player
            org.videolan.vlc.PlaybackService r12 = r12.getService()
            if (r12 == 0) goto L_0x0256
            org.videolan.tools.Settings r0 = org.videolan.tools.Settings.INSTANCE
            org.videolan.vlc.gui.video.VideoPlayerActivity r6 = r11.player
            java.lang.Object r0 = r0.getInstance(r6)
            android.content.SharedPreferences r0 = (android.content.SharedPreferences) r0
            long r6 = r12.getAudioDelay()
            java.lang.Long r6 = java.lang.Long.valueOf(r6)
            java.lang.String r7 = "key_bluetooth_delay"
            org.videolan.tools.SettingsKt.putSingle(r0, r7, r6)
            org.videolan.vlc.gui.helpers.UiTools r0 = org.videolan.vlc.gui.helpers.UiTools.INSTANCE
            org.videolan.vlc.gui.video.VideoPlayerActivity r6 = r11.player
            r7 = r6
            android.app.Activity r7 = (android.app.Activity) r7
            int r8 = org.videolan.vlc.R.string.audio_delay_bt
            long r9 = r12.getAudioDelay()
            long r9 = r9 / r3
            java.lang.String r12 = java.lang.String.valueOf(r9)
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r5] = r12
            java.lang.String r12 = r6.getString(r8, r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r1)
            r0.snacker(r7, r12)
            goto L_0x0256
        L_0x0248:
            int r0 = org.videolan.vlc.R.id.close
            if (r12 != 0) goto L_0x024d
            goto L_0x0256
        L_0x024d:
            int r12 = r12.intValue()
            if (r12 != r0) goto L_0x0256
            r11.endPlaybackSetting()
        L_0x0256:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.video.VideoDelayDelegate.onClick(android.view.View):void");
    }

    public static /* synthetic */ void delayAudioOrSpu$default(VideoDelayDelegate videoDelayDelegate, long j, boolean z, IPlaybackSettingsController.DelayState delayState, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        videoDelayDelegate.delayAudioOrSpu(j, z, delayState);
    }

    public final void delayAudioOrSpu(long j, boolean z, IPlaybackSettingsController.DelayState delayState) {
        PlaybackService service;
        Intrinsics.checkNotNullParameter(delayState, "delayState");
        if (delayState != IPlaybackSettingsController.DelayState.OFF && (service = this.player.getService()) != null) {
            long spuDelay2 = delayState == IPlaybackSettingsController.DelayState.SUBS ? service.getSpuDelay() : service.getAudioDelay();
            if (!z) {
                long j2 = spuDelay2 % j;
                if (j2 != 0) {
                    j -= j2;
                }
            }
            long j3 = spuDelay2 + j;
            this.player.getOverlayDelegate().initInfoOverlay();
            if (delayState == IPlaybackSettingsController.DelayState.SUBS) {
                service.setSpuDelay(j3);
            } else {
                service.setAudioDelay(j3);
            }
            TextView textView = this.delayTitle;
            TextView textView2 = null;
            if (textView != null) {
                if (textView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("delayTitle");
                    textView = null;
                }
                textView.setText(this.player.getString(delayState == IPlaybackSettingsController.DelayState.SUBS ? R.string.spu_delay : R.string.audio_delay));
            }
            TextView textView3 = this.delayInfo;
            if (textView3 != null) {
                if (textView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("delayInfo");
                } else {
                    textView2 = textView3;
                }
                textView2.setText((j3 / 1000) + " ms");
            }
            if (delayState == IPlaybackSettingsController.DelayState.SUBS) {
                this.spuDelay = j3;
            } else {
                this.audioDelay = j3;
            }
            if (!this.player.isPlaybackSettingActive$vlc_android_release()) {
                this.playbackSetting = delayState;
                showDelayControls();
            }
        }
    }

    public void showAudioDelaySetting() {
        this.playbackSetting = IPlaybackSettingsController.DelayState.AUDIO;
        showDelayControls();
    }

    public void showSubsDelaySetting() {
        this.playbackSetting = IPlaybackSettingsController.DelayState.SUBS;
        showDelayControls();
    }

    public void endPlaybackSetting() {
        if (this.playbackSetting != IPlaybackSettingsController.DelayState.OFF) {
            PlaybackService service = this.player.getService();
            if (service != null) {
                service.saveMediaMeta();
                this.playbackSetting = IPlaybackSettingsController.DelayState.OFF;
                ImageView imageView = this.playbackSettingMinus;
                if (imageView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("playbackSettingMinus");
                    imageView = null;
                }
                imageView.setOnClickListener((View.OnClickListener) null);
                ImageView imageView2 = this.playbackSettingPlus;
                if (imageView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("playbackSettingPlus");
                    imageView2 = null;
                }
                imageView2.setOnClickListener((View.OnClickListener) null);
                MaterialButton materialButton = this.delayFirstButton;
                if (materialButton == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("delayFirstButton");
                    materialButton = null;
                }
                materialButton.setOnClickListener((View.OnClickListener) null);
                MaterialButton materialButton2 = this.delaySecondButton;
                if (materialButton2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("delaySecondButton");
                    materialButton2 = null;
                }
                materialButton2.setOnClickListener((View.OnClickListener) null);
                ImageView imageView3 = this.close;
                if (imageView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("close");
                    imageView3 = null;
                }
                imageView3.setOnClickListener((View.OnClickListener) null);
                View view = this.delayContainer;
                if (view == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("delayContainer");
                    view = null;
                }
                KotlinExtensionsKt.setInvisible(view);
                KotlinExtensionsKt.setInvisible(this.player.getOverlayDelegate().getOverlayInfo());
                service.getPlaylistManager().getDelayValue().setValue(new DelayValues(0, 0, 3, (DefaultConstructorMarker) null));
                this.player.getOverlayDelegate().focusPlayPause();
            }
            if (AccessibilityHelperKt.isTalkbackIsEnabled(this.player)) {
                VideoPlayerOverlayDelegate.showOverlay$default(this.player.getOverlayDelegate(), false, 1, (Object) null);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:59:0x010c  */
    /* JADX WARNING: Removed duplicated region for block: B:62:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void delayChanged(org.videolan.vlc.media.DelayValues r14, org.videolan.vlc.PlaybackService r15) {
        /*
            r13 = this;
            java.lang.String r0 = "delayValues"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            java.lang.String r0 = "service"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r0)
            long r0 = r14.getStart()
            r2 = 1
            r3 = 0
            r4 = -1
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r6 == 0) goto L_0x0070
            long r0 = r14.getStop()
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r6 == 0) goto L_0x0070
            org.videolan.vlc.interfaces.IPlaybackSettingsController$DelayState r0 = r13.playbackSetting
            org.videolan.vlc.interfaces.IPlaybackSettingsController$DelayState r1 = org.videolan.vlc.interfaces.IPlaybackSettingsController.DelayState.SUBS
            if (r0 != r1) goto L_0x0029
            long r0 = r15.getSpuDelay()
            goto L_0x002d
        L_0x0029:
            long r0 = r15.getAudioDelay()
        L_0x002d:
            long r6 = r14.getStart()
            r8 = 1000(0x3e8, float:1.401E-42)
            long r8 = (long) r8
            long r6 = r6 * r8
            long r10 = r14.getStop()
            long r10 = r10 * r8
            long r6 = r6 - r10
            org.videolan.vlc.interfaces.IPlaybackSettingsController$DelayState r8 = r13.playbackSetting
            r13.delayAudioOrSpu(r6, r2, r8)
            org.videolan.vlc.interfaces.IPlaybackSettingsController$DelayState r6 = r13.playbackSetting
            org.videolan.vlc.interfaces.IPlaybackSettingsController$DelayState r7 = org.videolan.vlc.interfaces.IPlaybackSettingsController.DelayState.SUBS
            if (r6 != r7) goto L_0x004d
            long r6 = r15.getSpuDelay()
            goto L_0x0051
        L_0x004d:
            long r6 = r15.getAudioDelay()
        L_0x0051:
            int r8 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x0057
            r0 = 1
            goto L_0x0058
        L_0x0057:
            r0 = 0
        L_0x0058:
            org.videolan.vlc.media.PlaylistManager r15 = r15.getPlaylistManager()
            androidx.lifecycle.MutableLiveData r15 = r15.getDelayValue()
            org.videolan.vlc.media.DelayValues r1 = new org.videolan.vlc.media.DelayValues
            r11 = 3
            r12 = 0
            r7 = 0
            r9 = 0
            r6 = r1
            r6.<init>(r7, r9, r11, r12)
            r15.postValue(r1)
            goto L_0x0071
        L_0x0070:
            r0 = 0
        L_0x0071:
            com.google.android.material.button.MaterialButton r15 = r13.delayFirstButton
            if (r15 != 0) goto L_0x0076
            return
        L_0x0076:
            int r15 = android.os.Build.VERSION.SDK_INT
            r1 = 21
            if (r15 < r1) goto L_0x017b
            com.google.android.material.button.MaterialButton r15 = r13.delayFirstButton
            java.lang.String r1 = "delayFirstButton"
            r6 = 0
            if (r15 != 0) goto L_0x0087
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r15 = r6
        L_0x0087:
            long r7 = r14.getStart()
            int r9 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            org.videolan.vlc.gui.video.VideoPlayerActivity r7 = r13.player
            android.content.Context r7 = (android.content.Context) r7
            if (r9 != 0) goto L_0x0096
            int r8 = org.videolan.vlc.R.color.grey400transparent
            goto L_0x0098
        L_0x0096:
            int r8 = org.videolan.vlc.R.color.orange500
        L_0x0098:
            int r7 = androidx.core.content.ContextCompat.getColor(r7, r8)
            android.content.res.ColorStateList r7 = android.content.res.ColorStateList.valueOf(r7)
            r15.setIconTint(r7)
            com.google.android.material.button.MaterialButton r15 = r13.delaySecondButton
            java.lang.String r7 = "delaySecondButton"
            if (r15 != 0) goto L_0x00ad
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
            r15 = r6
        L_0x00ad:
            long r8 = r14.getStop()
            int r10 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            org.videolan.vlc.gui.video.VideoPlayerActivity r8 = r13.player
            android.content.Context r8 = (android.content.Context) r8
            if (r10 != 0) goto L_0x00bc
            int r9 = org.videolan.vlc.R.color.grey400transparent
            goto L_0x00be
        L_0x00bc:
            int r9 = org.videolan.vlc.R.color.orange500
        L_0x00be:
            int r8 = androidx.core.content.ContextCompat.getColor(r8, r9)
            android.content.res.ColorStateList r8 = android.content.res.ColorStateList.valueOf(r8)
            r15.setIconTint(r8)
            long r8 = r14.getStart()
            int r15 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r15 != 0) goto L_0x00e5
            long r8 = r14.getStop()
            int r15 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x00e5
            com.google.android.material.button.MaterialButton r14 = r13.delayFirstButton
            if (r14 != 0) goto L_0x00e1
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            goto L_0x00e2
        L_0x00e1:
            r6 = r14
        L_0x00e2:
            android.view.View r6 = (android.view.View) r6
            goto L_0x010a
        L_0x00e5:
            long r8 = r14.getStart()
            int r15 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x00fd
            long r14 = r14.getStop()
            int r1 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r1 != 0) goto L_0x00fd
            com.google.android.material.button.MaterialButton r14 = r13.delaySecondButton
            if (r14 != 0) goto L_0x00e1
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
            goto L_0x00e2
        L_0x00fd:
            if (r0 == 0) goto L_0x010a
            android.view.View r14 = r13.delayInfoContainer
            if (r14 != 0) goto L_0x0109
            java.lang.String r14 = "delayInfoContainer"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r14)
            goto L_0x010a
        L_0x0109:
            r6 = r14
        L_0x010a:
            if (r6 == 0) goto L_0x017b
            com.google.android.material.animation.ArgbEvaluatorCompat r14 = new com.google.android.material.animation.ArgbEvaluatorCompat
            r14.<init>()
            android.animation.TypeEvaluator r14 = (android.animation.TypeEvaluator) r14
            org.videolan.vlc.gui.video.VideoPlayerActivity r15 = r13.player
            android.content.Context r15 = (android.content.Context) r15
            int r0 = org.videolan.vlc.R.color.playerbackground
            int r15 = androidx.core.content.ContextCompat.getColor(r15, r0)
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)
            org.videolan.vlc.gui.video.VideoPlayerActivity r0 = r13.player
            android.content.Context r0 = (android.content.Context) r0
            int r1 = org.videolan.vlc.R.color.orange500focus
            int r0 = androidx.core.content.ContextCompat.getColor(r0, r1)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            org.videolan.vlc.gui.video.VideoPlayerActivity r1 = r13.player
            android.content.Context r1 = (android.content.Context) r1
            int r4 = org.videolan.vlc.R.color.playerbackground
            int r1 = androidx.core.content.ContextCompat.getColor(r1, r4)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r4 = 3
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r4[r3] = r15
            r4[r2] = r0
            r15 = 2
            r4[r15] = r1
            android.animation.ValueAnimator r14 = android.animation.ValueAnimator.ofObject(r14, r4)
            org.videolan.vlc.gui.video.VideoDelayDelegate$$ExternalSyntheticLambda2 r15 = new org.videolan.vlc.gui.video.VideoDelayDelegate$$ExternalSyntheticLambda2
            r15.<init>(r6, r6, r13)
            r14.addUpdateListener(r15)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r14)
            r15 = r14
            android.animation.Animator r15 = (android.animation.Animator) r15
            org.videolan.vlc.gui.video.VideoDelayDelegate$delayChanged$lambda$6$$inlined$doOnEnd$1 r0 = new org.videolan.vlc.gui.video.VideoDelayDelegate$delayChanged$lambda$6$$inlined$doOnEnd$1
            r0.<init>(r6, r13)
            android.animation.Animator$AnimatorListener r0 = (android.animation.Animator.AnimatorListener) r0
            r15.addListener(r0)
            r14.setRepeatCount(r2)
            android.view.animation.AccelerateDecelerateInterpolator r15 = new android.view.animation.AccelerateDecelerateInterpolator
            r15.<init>()
            android.animation.TimeInterpolator r15 = (android.animation.TimeInterpolator) r15
            r14.setInterpolator(r15)
            r0 = 500(0x1f4, double:2.47E-321)
            r14.setDuration(r0)
            r14.setStartDelay(r0)
            r14.start()
        L_0x017b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.video.VideoDelayDelegate.delayChanged(org.videolan.vlc.media.DelayValues, org.videolan.vlc.PlaybackService):void");
    }

    /* access modifiers changed from: private */
    public static final void delayChanged$lambda$6$lambda$4(View view, View view2, VideoDelayDelegate videoDelayDelegate, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(view, "$button");
        Intrinsics.checkNotNullParameter(videoDelayDelegate, "this$0");
        Intrinsics.checkNotNullParameter(valueAnimator, "it");
        Object animatedValue = valueAnimator.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        view.setBackgroundTintList(ColorStateList.valueOf(((Integer) animatedValue).intValue()));
        View view3 = videoDelayDelegate.delayInfoContainer;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("delayInfoContainer");
            view3 = null;
        }
        if (Intrinsics.areEqual((Object) view2, (Object) view3)) {
            view2.setBackground(ContextCompat.getDrawable(videoDelayDelegate.player, R.drawable.video_list_length_bg_opaque));
        }
    }

    /* access modifiers changed from: private */
    public static final void btSaveListener$lambda$8(VideoDelayDelegate videoDelayDelegate, View view) {
        Intrinsics.checkNotNullParameter(videoDelayDelegate, "this$0");
        PlaybackService service = videoDelayDelegate.player.getService();
        if (service != null) {
            SharedPreferences settings$vlc_android_release = service.getSettings$vlc_android_release();
            PlaybackService service2 = videoDelayDelegate.player.getService();
            SettingsKt.putSingle(settings$vlc_android_release, VideoPlayerActivity.KEY_BLUETOOTH_DELAY, Long.valueOf(service2 != null ? service2.getAudioDelay() : 0));
        }
    }
}
