package org.videolan.vlc.gui;

import com.google.android.material.textfield.TextInputEditText;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.tools.Settings;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "step", "Lorg/videolan/vlc/gui/PinStep;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: PinCodeActivity.kt */
final class PinCodeActivity$onCreate$3 extends Lambda implements Function1<PinStep, Unit> {
    final /* synthetic */ PinCodeActivity this$0;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PinCodeActivity.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(17:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|17) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                org.videolan.vlc.gui.PinStep[] r0 = org.videolan.vlc.gui.PinStep.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                org.videolan.vlc.gui.PinStep r1 = org.videolan.vlc.gui.PinStep.ENTER_EXISTING     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                org.videolan.vlc.gui.PinStep r1 = org.videolan.vlc.gui.PinStep.ENTER_NEW     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                org.videolan.vlc.gui.PinStep r1 = org.videolan.vlc.gui.PinStep.RE_ENTER     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                org.videolan.vlc.gui.PinStep r1 = org.videolan.vlc.gui.PinStep.NO_MATCH     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                org.videolan.vlc.gui.PinStep r1 = org.videolan.vlc.gui.PinStep.INVALID     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                org.videolan.vlc.gui.PinStep r1 = org.videolan.vlc.gui.PinStep.LOGIN_SUCCESS     // Catch:{ NoSuchFieldError -> 0x003d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                org.videolan.vlc.gui.PinStep r1 = org.videolan.vlc.gui.PinStep.EXIT     // Catch:{ NoSuchFieldError -> 0x0046 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0046 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0046 }
            L_0x0046:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.PinCodeActivity$onCreate$3.WhenMappings.<clinit>():void");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PinCodeActivity$onCreate$3(PinCodeActivity pinCodeActivity) {
        super(1);
        this.this$0 = pinCodeActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((PinStep) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(PinStep pinStep) {
        SafeModeModel access$getModel$p = this.this$0.model;
        SafeModeModel safeModeModel = null;
        if (access$getModel$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("model");
            access$getModel$p = null;
        }
        if (access$getModel$p.getStep().getValue() == PinStep.EXIT) {
            this.this$0.finish();
            return;
        }
        PinCodeReason access$getReason$p = this.this$0.reason;
        if (access$getReason$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("reason");
            access$getReason$p = null;
        }
        int i = -1;
        if (access$getReason$p == PinCodeReason.CHECK) {
            PinStep[] pinStepArr = {PinStep.INVALID, PinStep.ENTER_EXISTING};
            SafeModeModel access$getModel$p2 = this.this$0.model;
            if (access$getModel$p2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("model");
                access$getModel$p2 = null;
            }
            if (!ArraysKt.contains((T[]) pinStepArr, access$getModel$p2.getStep().getValue())) {
                this.this$0.setResult(-1);
                this.this$0.finish();
                return;
            }
        }
        PinCodeReason access$getReason$p2 = this.this$0.reason;
        if (access$getReason$p2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("reason");
            access$getReason$p2 = null;
        }
        if (access$getReason$p2 == PinCodeReason.UNLOCK) {
            PinStep[] pinStepArr2 = {PinStep.INVALID, PinStep.ENTER_EXISTING};
            SafeModeModel access$getModel$p3 = this.this$0.model;
            if (access$getModel$p3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("model");
                access$getModel$p3 = null;
            }
            if (!ArraysKt.contains((T[]) pinStepArr2, access$getModel$p3.getStep().getValue())) {
                this.this$0.setResult(-1);
                this.this$0.showTips();
                return;
            }
        }
        if (pinStep != null) {
            i = WhenMappings.$EnumSwitchMapping$0[pinStep.ordinal()];
        }
        switch (i) {
            case 1:
                this.this$0.getBinding$vlc_android_release().pinCodeTitle.setText(this.this$0.getString(R.string.safe_mode_pin));
                break;
            case 2:
                this.this$0.getBinding$vlc_android_release().pinCodeTitle.setText(this.this$0.getString(R.string.safe_mode_new_pin));
                break;
            case 3:
                this.this$0.getBinding$vlc_android_release().pinCodeTitle.setText(this.this$0.getString(R.string.safe_mode_re_pin));
                break;
            case 4:
                this.this$0.getBinding$vlc_android_release().pinCodeTitle.setText(this.this$0.getString(R.string.safe_mode_no_match));
                break;
            case 5:
                this.this$0.getBinding$vlc_android_release().pinCodeTitle.setText(this.this$0.getString(R.string.safe_mode_invalid_pin));
                break;
            case 6:
                this.this$0.getBinding$vlc_android_release().pinCodeTitle.setText(this.this$0.getString(R.string.safe_mode_invalid_pin));
                break;
        }
        SafeModeModel access$getModel$p4 = this.this$0.model;
        if (access$getModel$p4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("model");
        } else {
            safeModeModel = access$getModel$p4;
        }
        if (safeModeModel.isFinalStep()) {
            for (TextInputEditText imeOptions : this.this$0.getPinTexts()) {
                imeOptions.setImeOptions(6);
            }
            this.this$0.getBinding$vlc_android_release().nextButton.setText(this.this$0.getString(R.string.done));
        }
        if (!Settings.INSTANCE.getTvUI()) {
            this.this$0.updateFocus();
        }
    }
}
