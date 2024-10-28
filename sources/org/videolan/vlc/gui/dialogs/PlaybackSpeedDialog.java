package org.videolan.vlc.gui.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwnerKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt;
import org.videolan.tools.Strings;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.DialogPlaybackSpeedBinding;
import org.videolan.vlc.gui.helpers.OnRepeatListenerKey;
import org.videolan.vlc.gui.helpers.OnRepeatListenerTouch;
import org.videolan.vlc.util.BrowserutilsKt;
import org.videolan.vlc.util.KextensionsKt;

@Metadata(d1 = {"\u0000]\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b*\u0001\u000e\u0018\u0000 *2\u00020\u0001:\u0001*B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0006H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u0014H\u0016J$\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0017J\u0012\u0010$\u001a\u00020\u00162\b\u0010%\u001a\u0004\u0018\u00010\nH\u0002J\u001a\u0010&\u001a\u00020\u00162\u0006\u0010'\u001a\u00020\u001b2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010(\u001a\u00020\u0016H\u0002J\b\u0010)\u001a\u00020\u0016H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068BX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/PlaybackSpeedDialog;", "Lorg/videolan/vlc/gui/dialogs/VLCBottomSheetDialogFragment;", "()V", "binding", "Lorg/videolan/vlc/databinding/DialogPlaybackSpeedBinding;", "orangeColor", "", "getOrangeColor", "()I", "playbackService", "Lorg/videolan/vlc/PlaybackService;", "resetListener", "Landroid/view/View$OnClickListener;", "seekBarListener", "org/videolan/vlc/gui/dialogs/PlaybackSpeedDialog$seekBarListener$1", "Lorg/videolan/vlc/gui/dialogs/PlaybackSpeedDialog$seekBarListener$1;", "speedDownListener", "speedUpListener", "textColor", "allowRemote", "", "changeSpeedTo", "", "newValue", "", "getDefaultState", "initialFocusedView", "Landroid/view/View;", "needToManageOrientation", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onServiceChanged", "service", "onViewCreated", "view", "setRateProgress", "updateInterface", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaybackSpeedDialog.kt */
public final class PlaybackSpeedDialog extends VLCBottomSheetDialogFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TAG = "VLC/PlaybackSpeedDialog";
    private DialogPlaybackSpeedBinding binding;
    /* access modifiers changed from: private */
    public PlaybackService playbackService;
    private final View.OnClickListener resetListener = new PlaybackSpeedDialog$$ExternalSyntheticLambda0(this);
    private final PlaybackSpeedDialog$seekBarListener$1 seekBarListener = new PlaybackSpeedDialog$seekBarListener$1(this);
    private final View.OnClickListener speedDownListener = new PlaybackSpeedDialog$$ExternalSyntheticLambda2(this);
    private final View.OnClickListener speedUpListener = new PlaybackSpeedDialog$$ExternalSyntheticLambda1(this);
    private int textColor;

    public boolean allowRemote() {
        return true;
    }

    public int getDefaultState() {
        return 3;
    }

    public boolean needToManageOrientation() {
        return true;
    }

    private final int getOrangeColor() {
        TypedValue typedValue = new TypedValue();
        requireActivity().getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }

    /* access modifiers changed from: private */
    public static final void resetListener$lambda$0(PlaybackSpeedDialog playbackSpeedDialog, View view) {
        Intrinsics.checkNotNullParameter(playbackSpeedDialog, "this$0");
        PlaybackService playbackService2 = playbackSpeedDialog.playbackService;
        if (playbackService2 != null) {
            Intrinsics.checkNotNull(playbackService2);
            if (((double) playbackService2.getRate()) != 1.0d) {
                PlaybackService playbackService3 = playbackSpeedDialog.playbackService;
                Intrinsics.checkNotNull(playbackService3);
                if (playbackService3.getCurrentMediaWrapper() != null) {
                    PlaybackService playbackService4 = playbackSpeedDialog.playbackService;
                    Intrinsics.checkNotNull(playbackService4);
                    playbackService4.setRate(1.0f, true);
                    playbackSpeedDialog.setRateProgress();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void speedUpListener$lambda$1(PlaybackSpeedDialog playbackSpeedDialog, View view) {
        Intrinsics.checkNotNullParameter(playbackSpeedDialog, "this$0");
        PlaybackService playbackService2 = playbackSpeedDialog.playbackService;
        if (playbackService2 != null) {
            Intrinsics.checkNotNull(playbackService2);
            playbackSpeedDialog.changeSpeedTo(playbackService2.getRate() + 0.01f);
        }
    }

    /* access modifiers changed from: private */
    public static final void speedDownListener$lambda$2(PlaybackSpeedDialog playbackSpeedDialog, View view) {
        Intrinsics.checkNotNullParameter(playbackSpeedDialog, "this$0");
        PlaybackService playbackService2 = playbackSpeedDialog.playbackService;
        if (playbackService2 != null) {
            Intrinsics.checkNotNull(playbackService2);
            playbackSpeedDialog.changeSpeedTo(playbackService2.getRate() - 0.01f);
        }
    }

    public View initialFocusedView() {
        DialogPlaybackSpeedBinding dialogPlaybackSpeedBinding = this.binding;
        if (dialogPlaybackSpeedBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPlaybackSpeedBinding = null;
        }
        TextView textView = dialogPlaybackSpeedBinding.playbackSpeedValue;
        Intrinsics.checkNotNullExpressionValue(textView, "playbackSpeedValue");
        return textView;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        DialogPlaybackSpeedBinding inflate = DialogPlaybackSpeedBinding.inflate(layoutInflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        DialogPlaybackSpeedBinding dialogPlaybackSpeedBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        inflate.playbackSpeedSeek.setOnSeekBarChangeListener(this.seekBarListener);
        DialogPlaybackSpeedBinding dialogPlaybackSpeedBinding2 = this.binding;
        if (dialogPlaybackSpeedBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPlaybackSpeedBinding2 = null;
        }
        dialogPlaybackSpeedBinding2.playbackSpeedValue.setOnClickListener(this.resetListener);
        DialogPlaybackSpeedBinding dialogPlaybackSpeedBinding3 = this.binding;
        if (dialogPlaybackSpeedBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPlaybackSpeedBinding3 = null;
        }
        AppCompatImageButton appCompatImageButton = dialogPlaybackSpeedBinding3.buttonSpeedMinus;
        View.OnClickListener onClickListener = this.speedDownListener;
        Lifecycle lifecycle = getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle, "<get-lifecycle>(...)");
        appCompatImageButton.setOnTouchListener(new OnRepeatListenerTouch(0, 0, 0, onClickListener, lifecycle, 7, (DefaultConstructorMarker) null));
        DialogPlaybackSpeedBinding dialogPlaybackSpeedBinding4 = this.binding;
        if (dialogPlaybackSpeedBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPlaybackSpeedBinding4 = null;
        }
        AppCompatImageButton appCompatImageButton2 = dialogPlaybackSpeedBinding4.buttonSpeedPlus;
        View.OnClickListener onClickListener2 = this.speedUpListener;
        Lifecycle lifecycle2 = getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle2, "<get-lifecycle>(...)");
        appCompatImageButton2.setOnTouchListener(new OnRepeatListenerTouch(0, 0, 0, onClickListener2, lifecycle2, 7, (DefaultConstructorMarker) null));
        DialogPlaybackSpeedBinding dialogPlaybackSpeedBinding5 = this.binding;
        if (dialogPlaybackSpeedBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPlaybackSpeedBinding5 = null;
        }
        AppCompatImageButton appCompatImageButton3 = dialogPlaybackSpeedBinding5.buttonSpeedMinus;
        View.OnClickListener onClickListener3 = this.speedDownListener;
        Lifecycle lifecycle3 = getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle3, "<get-lifecycle>(...)");
        appCompatImageButton3.setOnKeyListener(new OnRepeatListenerKey(0, 0, 0, onClickListener3, lifecycle3, 7, (DefaultConstructorMarker) null));
        DialogPlaybackSpeedBinding dialogPlaybackSpeedBinding6 = this.binding;
        if (dialogPlaybackSpeedBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPlaybackSpeedBinding6 = null;
        }
        AppCompatImageButton appCompatImageButton4 = dialogPlaybackSpeedBinding6.buttonSpeedPlus;
        View.OnClickListener onClickListener4 = this.speedUpListener;
        Lifecycle lifecycle4 = getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle4, "<get-lifecycle>(...)");
        appCompatImageButton4.setOnKeyListener(new OnRepeatListenerKey(0, 0, 0, onClickListener4, lifecycle4, 7, (DefaultConstructorMarker) null));
        DialogPlaybackSpeedBinding dialogPlaybackSpeedBinding7 = this.binding;
        if (dialogPlaybackSpeedBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPlaybackSpeedBinding7 = null;
        }
        dialogPlaybackSpeedBinding7.buttonSpeed1.setOnClickListener(new PlaybackSpeedDialog$$ExternalSyntheticLambda3(this));
        DialogPlaybackSpeedBinding dialogPlaybackSpeedBinding8 = this.binding;
        if (dialogPlaybackSpeedBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPlaybackSpeedBinding8 = null;
        }
        dialogPlaybackSpeedBinding8.buttonSpeed08.setOnClickListener(new PlaybackSpeedDialog$$ExternalSyntheticLambda4(this));
        DialogPlaybackSpeedBinding dialogPlaybackSpeedBinding9 = this.binding;
        if (dialogPlaybackSpeedBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPlaybackSpeedBinding9 = null;
        }
        dialogPlaybackSpeedBinding9.buttonSpeed125.setOnClickListener(new PlaybackSpeedDialog$$ExternalSyntheticLambda5(this));
        DialogPlaybackSpeedBinding dialogPlaybackSpeedBinding10 = this.binding;
        if (dialogPlaybackSpeedBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPlaybackSpeedBinding10 = null;
        }
        dialogPlaybackSpeedBinding10.buttonSpeed15.setOnClickListener(new PlaybackSpeedDialog$$ExternalSyntheticLambda6(this));
        DialogPlaybackSpeedBinding dialogPlaybackSpeedBinding11 = this.binding;
        if (dialogPlaybackSpeedBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPlaybackSpeedBinding11 = null;
        }
        dialogPlaybackSpeedBinding11.buttonSpeed2.setOnClickListener(new PlaybackSpeedDialog$$ExternalSyntheticLambda7(this));
        DialogPlaybackSpeedBinding dialogPlaybackSpeedBinding12 = this.binding;
        if (dialogPlaybackSpeedBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPlaybackSpeedBinding12 = null;
        }
        dialogPlaybackSpeedBinding12.buttonSpeedMinus.setOnClickListener(new PlaybackSpeedDialog$$ExternalSyntheticLambda8(this));
        DialogPlaybackSpeedBinding dialogPlaybackSpeedBinding13 = this.binding;
        if (dialogPlaybackSpeedBinding13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPlaybackSpeedBinding13 = null;
        }
        dialogPlaybackSpeedBinding13.buttonSpeedPlus.setOnClickListener(new PlaybackSpeedDialog$$ExternalSyntheticLambda9(this));
        DialogPlaybackSpeedBinding dialogPlaybackSpeedBinding14 = this.binding;
        if (dialogPlaybackSpeedBinding14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPlaybackSpeedBinding14 = null;
        }
        this.textColor = dialogPlaybackSpeedBinding14.playbackSpeedValue.getCurrentTextColor();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.setCancelable(true);
        }
        Dialog dialog2 = getDialog();
        if (dialog2 != null) {
            dialog2.setCanceledOnTouchOutside(true);
        }
        DialogPlaybackSpeedBinding dialogPlaybackSpeedBinding15 = this.binding;
        if (dialogPlaybackSpeedBinding15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogPlaybackSpeedBinding = dialogPlaybackSpeedBinding15;
        }
        View root = dialogPlaybackSpeedBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    /* access modifiers changed from: private */
    public static final void onCreateView$lambda$3(PlaybackSpeedDialog playbackSpeedDialog, View view) {
        Intrinsics.checkNotNullParameter(playbackSpeedDialog, "this$0");
        playbackSpeedDialog.changeSpeedTo(1.0f);
    }

    /* access modifiers changed from: private */
    public static final void onCreateView$lambda$4(PlaybackSpeedDialog playbackSpeedDialog, View view) {
        Intrinsics.checkNotNullParameter(playbackSpeedDialog, "this$0");
        playbackSpeedDialog.changeSpeedTo(0.8f);
    }

    /* access modifiers changed from: private */
    public static final void onCreateView$lambda$5(PlaybackSpeedDialog playbackSpeedDialog, View view) {
        Intrinsics.checkNotNullParameter(playbackSpeedDialog, "this$0");
        playbackSpeedDialog.changeSpeedTo(1.25f);
    }

    /* access modifiers changed from: private */
    public static final void onCreateView$lambda$6(PlaybackSpeedDialog playbackSpeedDialog, View view) {
        Intrinsics.checkNotNullParameter(playbackSpeedDialog, "this$0");
        playbackSpeedDialog.changeSpeedTo(1.5f);
    }

    /* access modifiers changed from: private */
    public static final void onCreateView$lambda$7(PlaybackSpeedDialog playbackSpeedDialog, View view) {
        Intrinsics.checkNotNullParameter(playbackSpeedDialog, "this$0");
        playbackSpeedDialog.changeSpeedTo(2.0f);
    }

    /* access modifiers changed from: private */
    public static final void onCreateView$lambda$8(PlaybackSpeedDialog playbackSpeedDialog, View view) {
        Intrinsics.checkNotNullParameter(playbackSpeedDialog, "this$0");
        PlaybackService playbackService2 = playbackSpeedDialog.playbackService;
        Intrinsics.checkNotNull(playbackService2);
        playbackSpeedDialog.changeSpeedTo(playbackService2.getRate() - 0.01f);
    }

    /* access modifiers changed from: private */
    public static final void onCreateView$lambda$9(PlaybackSpeedDialog playbackSpeedDialog, View view) {
        Intrinsics.checkNotNullParameter(playbackSpeedDialog, "this$0");
        PlaybackService playbackService2 = playbackSpeedDialog.playbackService;
        Intrinsics.checkNotNull(playbackService2);
        playbackSpeedDialog.changeSpeedTo(playbackService2.getRate() + 0.01f);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        KextensionsKt.launchWhenStarted(FlowKt.onEach(PlaybackService.Companion.getServiceFlow(), new PlaybackSpeedDialog$onViewCreated$1(this, (Continuation<? super PlaybackSpeedDialog$onViewCreated$1>) null)), LifecycleOwnerKt.getLifecycleScope(this));
    }

    private final void setRateProgress() {
        PlaybackService playbackService2 = this.playbackService;
        Intrinsics.checkNotNull(playbackService2);
        double d = (double) 100;
        double d2 = (double) 1;
        Double.isNaN(d2);
        Double.isNaN(d);
        double log = d * (d2 + (Math.log((double) playbackService2.getRate()) / Math.log(8.0d)));
        DialogPlaybackSpeedBinding dialogPlaybackSpeedBinding = this.binding;
        if (dialogPlaybackSpeedBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPlaybackSpeedBinding = null;
        }
        dialogPlaybackSpeedBinding.playbackSpeedSeek.setProgress((int) log);
        updateInterface();
    }

    private final void changeSpeedTo(float f) {
        PlaybackService playbackService2 = this.playbackService;
        if (playbackService2 != null && f <= 8.0f && f >= 0.25f) {
            Intrinsics.checkNotNull(playbackService2);
            playbackService2.setRate(f, true);
            setRateProgress();
        }
    }

    /* access modifiers changed from: private */
    public final void updateInterface() {
        PlaybackService playbackService2 = this.playbackService;
        Intrinsics.checkNotNull(playbackService2);
        float rate = playbackService2.getRate();
        DialogPlaybackSpeedBinding dialogPlaybackSpeedBinding = this.binding;
        String str = null;
        if (dialogPlaybackSpeedBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPlaybackSpeedBinding = null;
        }
        dialogPlaybackSpeedBinding.playbackSpeedValue.setText(Strings.formatRateString(rate));
        if (rate == 1.0f) {
            DialogPlaybackSpeedBinding dialogPlaybackSpeedBinding2 = this.binding;
            if (dialogPlaybackSpeedBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                dialogPlaybackSpeedBinding2 = null;
            }
            dialogPlaybackSpeedBinding2.playbackSpeedValue.setTextColor(this.textColor);
        } else {
            DialogPlaybackSpeedBinding dialogPlaybackSpeedBinding3 = this.binding;
            if (dialogPlaybackSpeedBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                dialogPlaybackSpeedBinding3 = null;
            }
            dialogPlaybackSpeedBinding3.playbackSpeedValue.setTextColor(getOrangeColor());
        }
        DialogPlaybackSpeedBinding dialogPlaybackSpeedBinding4 = this.binding;
        if (dialogPlaybackSpeedBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPlaybackSpeedBinding4 = null;
        }
        TextView textView = dialogPlaybackSpeedBinding4.streamWarning;
        PlaybackService playbackService3 = this.playbackService;
        if (playbackService3 != null) {
            str = playbackService3.getCurrentMediaLocation();
        }
        textView.setVisibility((!BrowserutilsKt.isSchemeStreaming(str) || rate <= 1.0f) ? 4 : 0);
    }

    /* access modifiers changed from: private */
    public final void onServiceChanged(PlaybackService playbackService2) {
        if (playbackService2 != null) {
            this.playbackService = playbackService2;
            setRateProgress();
            return;
        }
        this.playbackService = null;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/PlaybackSpeedDialog$Companion;", "", "()V", "TAG", "", "newInstance", "Lorg/videolan/vlc/gui/dialogs/PlaybackSpeedDialog;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PlaybackSpeedDialog.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final PlaybackSpeedDialog newInstance() {
            return new PlaybackSpeedDialog();
        }
    }
}
