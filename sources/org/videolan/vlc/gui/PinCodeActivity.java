package org.videolan.vlc.gui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.activity.ComponentActivity;
import androidx.core.view.ViewGroupKt;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.gridlayout.widget.GridLayout;
import androidx.lifecycle.ViewModelLazy;
import com.google.android.material.textfield.TextInputEditText;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import org.videolan.resources.AndroidDevices;
import org.videolan.resources.Constants;
import org.videolan.resources.util.HelpersKt;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.Settings;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.PinCodeActivityBinding;
import org.videolan.vlc.gui.helpers.UiTools;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 .2\u00020\u0001:\u0001.B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0018\u001a\u0004\u0018\u00010\u0011H\u0002J\n\u0010\u0019\u001a\u0004\u0018\u00010\u0011H\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\nH\u0016J\b\u0010\u001f\u001a\u00020 H\u0002J\u0012\u0010!\u001a\u00020 2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0015J\u0018\u0010$\u001a\u00020\n2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0016J\u0010\u0010)\u001a\u00020\n2\u0006\u0010*\u001a\u00020+H\u0016J\b\u0010,\u001a\u00020 H\u0002J\b\u0010-\u001a\u00020 H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\nXD¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX.¢\u0006\u0002\n\u0000R!\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108BX\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0016\u001a\u00020\u0017X.¢\u0006\u0002\n\u0000¨\u0006/²\u0006\n\u00100\u001a\u00020\u000eX\u0002"}, d2 = {"Lorg/videolan/vlc/gui/PinCodeActivity;", "Lorg/videolan/vlc/gui/BaseActivity;", "()V", "binding", "Lorg/videolan/vlc/databinding/PinCodeActivityBinding;", "getBinding$vlc_android_release", "()Lorg/videolan/vlc/databinding/PinCodeActivityBinding;", "setBinding$vlc_android_release", "(Lorg/videolan/vlc/databinding/PinCodeActivityBinding;)V", "displayTitle", "", "getDisplayTitle", "()Z", "model", "Lorg/videolan/vlc/gui/SafeModeModel;", "pinTexts", "", "Lcom/google/android/material/textfield/TextInputEditText;", "getPinTexts", "()[Lcom/google/android/material/textfield/TextInputEditText;", "pinTexts$delegate", "Lkotlin/Lazy;", "reason", "Lorg/videolan/vlc/gui/PinCodeReason;", "getCurrentInput", "getLastSetET", "getPinCode", "", "getSnackAnchorView", "Landroid/view/View;", "overAudioPlayer", "next", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onKeyDown", "keyCode", "", "event", "Landroid/view/KeyEvent;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "showTips", "updateFocus", "Companion", "vlc-android_release", "getModel"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PinCodeActivity.kt */
public final class PinCodeActivity extends BaseActivity {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public PinCodeActivityBinding binding;
    private final boolean displayTitle = true;
    /* access modifiers changed from: private */
    public SafeModeModel model;
    private final Lazy pinTexts$delegate = LazyKt.lazy(new PinCodeActivity$pinTexts$2(this));
    /* access modifiers changed from: private */
    public PinCodeReason reason;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PinCodeActivity.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                org.videolan.vlc.gui.PinCodeReason[] r0 = org.videolan.vlc.gui.PinCodeReason.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                org.videolan.vlc.gui.PinCodeReason r1 = org.videolan.vlc.gui.PinCodeReason.FIRST_CREATION     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                org.videolan.vlc.gui.PinCodeReason r1 = org.videolan.vlc.gui.PinCodeReason.MODIFY     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.PinCodeActivity.WhenMappings.<clinit>():void");
        }
    }

    public final PinCodeActivityBinding getBinding$vlc_android_release() {
        PinCodeActivityBinding pinCodeActivityBinding = this.binding;
        if (pinCodeActivityBinding != null) {
            return pinCodeActivityBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final void setBinding$vlc_android_release(PinCodeActivityBinding pinCodeActivityBinding) {
        Intrinsics.checkNotNullParameter(pinCodeActivityBinding, "<set-?>");
        this.binding = pinCodeActivityBinding;
    }

    public View getSnackAnchorView(boolean z) {
        View root = getBinding$vlc_android_release().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    public boolean getDisplayTitle() {
        return this.displayTitle;
    }

    /* access modifiers changed from: private */
    public final TextInputEditText[] getPinTexts() {
        return (TextInputEditText[]) this.pinTexts$delegate.getValue();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        int i;
        super.onCreate(bundle);
        if (getIntent().hasExtra("pin_code_reason")) {
            this.reason = PinCodeReason.values()[getIntent().getIntExtra("pin_code_reason", 0)];
            Activity activity = this;
            ViewDataBinding contentView = DataBindingUtil.setContentView(activity, R.layout.pin_code_activity);
            Intrinsics.checkNotNullExpressionValue(contentView, "setContentView(...)");
            setBinding$vlc_android_release((PinCodeActivityBinding) contentView);
            if (!Settings.INSTANCE.getTvUI()) {
                updateFocus();
                UiTools.INSTANCE.setKeyboardVisibility(getBinding$vlc_android_release().pinCode1, true);
                KotlinExtensionsKt.setGone(getBinding$vlc_android_release().keyboardGrid);
            } else {
                KotlinExtensionsKt.setVisible(getBinding$vlc_android_release().keyboardGrid);
                getBinding$vlc_android_release().keyboardButton1.requestFocus();
                for (TextInputEditText focusable : getPinTexts()) {
                    focusable.setFocusable(false);
                }
            }
            TextView textView = getBinding$vlc_android_release().pinCodeReason;
            PinCodeReason pinCodeReason = this.reason;
            SafeModeModel safeModeModel = null;
            if (pinCodeReason == null) {
                Intrinsics.throwUninitializedPropertyAccessException("reason");
                pinCodeReason = null;
            }
            int i2 = WhenMappings.$EnumSwitchMapping$0[pinCodeReason.ordinal()];
            if (i2 == 1) {
                i = R.string.pin_code_reason_create;
            } else if (i2 != 2) {
                i = R.string.pin_code_reason_check;
            } else {
                i = R.string.pin_code_reason_modify;
            }
            textView.setText(getString(i));
            for (TextInputEditText textInputEditText : getPinTexts()) {
                Intrinsics.checkNotNull(textInputEditText);
                textInputEditText.addTextChangedListener(new PinCodeActivity$onCreate$lambda$8$$inlined$doOnTextChanged$1(this, textInputEditText));
                textInputEditText.setOnKeyListener(new PinCodeActivity$$ExternalSyntheticLambda0(textInputEditText, this));
                textInputEditText.setOnFocusChangeListener(new PinCodeActivity$$ExternalSyntheticLambda1(this, textInputEditText));
            }
            ComponentActivity componentActivity = this;
            SafeModeModel onCreate$lambda$9 = onCreate$lambda$9(new ViewModelLazy(Reflection.getOrCreateKotlinClass(SafeModeModel.class), new PinCodeActivity$onCreate$$inlined$viewModels$default$2(componentActivity), new PinCodeActivity$onCreate$getModel$2(this), new PinCodeActivity$onCreate$$inlined$viewModels$default$3((Function0) null, componentActivity)));
            this.model = onCreate$lambda$9;
            if (onCreate$lambda$9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("model");
            } else {
                safeModeModel = onCreate$lambda$9;
            }
            safeModeModel.getStep().observe(this, new PinCodeActivityKt$sam$androidx_lifecycle_Observer$0(new PinCodeActivity$onCreate$3(this)));
            for (TextInputEditText onEditorActionListener : getPinTexts()) {
                onEditorActionListener.setOnEditorActionListener(new PinCodeActivity$$ExternalSyntheticLambda2(this));
            }
            getBinding$vlc_android_release().nextButton.setOnClickListener(new PinCodeActivity$$ExternalSyntheticLambda3(this));
            getBinding$vlc_android_release().cancelButton.setOnClickListener(new PinCodeActivity$$ExternalSyntheticLambda4(this));
            setResult(0);
            if (AndroidDevices.INSTANCE.isTv()) {
                HelpersKt.applyOverscanMargin(activity);
            }
            GridLayout gridLayout = getBinding$vlc_android_release().keyboardGrid;
            Intrinsics.checkNotNullExpressionValue(gridLayout, "keyboardGrid");
            for (View onClickListener : ViewGroupKt.getChildren(gridLayout)) {
                onClickListener.setOnClickListener(new PinCodeActivity$$ExternalSyntheticLambda5(this));
            }
            return;
        }
        throw new IllegalStateException("No reason given");
    }

    /* access modifiers changed from: private */
    public static final boolean onCreate$lambda$8$lambda$5(TextInputEditText textInputEditText, PinCodeActivity pinCodeActivity, View view, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(textInputEditText, "$editText");
        Intrinsics.checkNotNullParameter(pinCodeActivity, "this$0");
        if (i != 67 || keyEvent.getAction() != 0) {
            return false;
        }
        Editable text = textInputEditText.getText();
        if (text != null && text.length() > 0) {
            return false;
        }
        TextInputEditText lastSetET = pinCodeActivity.getLastSetET();
        if (lastSetET != null) {
            PinCodeActivityKt.clearText(lastSetET);
        }
        pinCodeActivity.updateFocus();
        return true;
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$8$lambda$7(PinCodeActivity pinCodeActivity, TextInputEditText textInputEditText, View view, boolean z) {
        Intrinsics.checkNotNullParameter(pinCodeActivity, "this$0");
        Intrinsics.checkNotNullParameter(textInputEditText, "$editText");
        if (!Settings.INSTANCE.getTvUI()) {
            if (z) {
                for (TextInputEditText textInputEditText2 : pinCodeActivity.getPinTexts()) {
                    if (!Intrinsics.areEqual((Object) view, (Object) textInputEditText2)) {
                        textInputEditText2.clearFocus();
                    }
                }
            }
            textInputEditText.setTransformationMethod(new PasswordTransformationMethod());
        }
    }

    private static final SafeModeModel onCreate$lambda$9(Lazy<SafeModeModel> lazy) {
        return lazy.getValue();
    }

    /* access modifiers changed from: private */
    public static final boolean onCreate$lambda$11$lambda$10(PinCodeActivity pinCodeActivity, TextView textView, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(pinCodeActivity, "this$0");
        if (i != 5 && i != 6) {
            return false;
        }
        pinCodeActivity.next();
        return false;
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$12(PinCodeActivity pinCodeActivity, View view) {
        Intrinsics.checkNotNullParameter(pinCodeActivity, "this$0");
        pinCodeActivity.next();
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$13(PinCodeActivity pinCodeActivity, View view) {
        Intrinsics.checkNotNullParameter(pinCodeActivity, "this$0");
        pinCodeActivity.finish();
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$15$lambda$14(PinCodeActivity pinCodeActivity, View view) {
        Intrinsics.checkNotNullParameter(pinCodeActivity, "this$0");
        if (Intrinsics.areEqual(view.getTag(), (Object) Constants.GROUP_VIDEOS_NONE)) {
            TextInputEditText lastSetET = pinCodeActivity.getLastSetET();
            if (lastSetET != null) {
                PinCodeActivityKt.clearText(lastSetET);
                return;
            }
            return;
        }
        TextInputEditText currentInput = pinCodeActivity.getCurrentInput();
        if (currentInput != null) {
            Object tag = view.getTag();
            Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type kotlin.String");
            currentInput.setText((String) tag);
        }
    }

    /* access modifiers changed from: private */
    public final void showTips() {
        UiTools.INSTANCE.setKeyboardVisibility(getBinding$vlc_android_release().pinCode1, false);
        KotlinExtensionsKt.setGone(getBinding$vlc_android_release().pinGroup);
        KotlinExtensionsKt.setVisible(getBinding$vlc_android_release().sucessGroup);
        getBinding$vlc_android_release().nextButton.setText(getString(R.string.done));
        getBinding$vlc_android_release().nextButton.setEnabled(true);
        KotlinExtensionsKt.setGone(getBinding$vlc_android_release().keyboardGrid);
        getBinding$vlc_android_release().nextButton.requestFocus();
        KotlinExtensionsKt.setGone(getBinding$vlc_android_release().cancelButton);
    }

    private final TextInputEditText getLastSetET() {
        TextInputEditText textInputEditText;
        Object[] reversedArray = ArraysKt.reversedArray((T[]) getPinTexts());
        int length = reversedArray.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                textInputEditText = null;
                break;
            }
            textInputEditText = reversedArray[i];
            Editable text = ((TextInputEditText) textInputEditText).getText();
            if (text != null) {
                Intrinsics.checkNotNull(text);
                if (!StringsKt.isBlank(text)) {
                    break;
                }
            }
            i++;
        }
        return textInputEditText;
    }

    /* access modifiers changed from: private */
    public final void updateFocus() {
        if (!Settings.INSTANCE.getTvUI()) {
            TextInputEditText currentInput = getCurrentInput();
            if (currentInput == null) {
                currentInput = getBinding$vlc_android_release().pinCode4;
            }
            currentInput.requestFocus();
        }
    }

    private final TextInputEditText getCurrentInput() {
        TextInputEditText[] pinTexts = getPinTexts();
        int length = pinTexts.length;
        for (int i = 0; i < length; i++) {
            TextInputEditText textInputEditText = pinTexts[i];
            CharSequence text = textInputEditText.getText();
            if (text == null || StringsKt.isBlank(text)) {
                return textInputEditText;
            }
        }
        return null;
    }

    private final String getPinCode() {
        StringBuilder sb = new StringBuilder();
        for (TextInputEditText text : getPinTexts()) {
            sb.append(String.valueOf(text.getText()));
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0022, code lost:
        if (r3 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0024, code lost:
        r3.setText("9");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002d, code lost:
        r3 = getCurrentInput();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0031, code lost:
        if (r3 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0033, code lost:
        r3.setText("8");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003c, code lost:
        r3 = getCurrentInput();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0040, code lost:
        if (r3 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0042, code lost:
        r3.setText("7");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004b, code lost:
        r3 = getCurrentInput();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004f, code lost:
        if (r3 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0051, code lost:
        r3.setText(org.videolan.resources.Constants.GROUP_VIDEOS_NAME);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0059, code lost:
        r3 = getCurrentInput();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005d, code lost:
        if (r3 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005f, code lost:
        r3.setText("5");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0067, code lost:
        r3 = getCurrentInput();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x006b, code lost:
        if (r3 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006d, code lost:
        r3.setText("4");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0075, code lost:
        r3 = getCurrentInput();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0079, code lost:
        if (r3 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x007b, code lost:
        r3.setText("3");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0083, code lost:
        r3 = getCurrentInput();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0087, code lost:
        if (r3 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0089, code lost:
        r3.setText("2");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0091, code lost:
        r3 = getCurrentInput();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0095, code lost:
        if (r3 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0097, code lost:
        r3.setText(okhttp3.internal.cache.DiskLruCache.VERSION_1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x009f, code lost:
        r3 = getCurrentInput();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a3, code lost:
        if (r3 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00a5, code lost:
        r3.setText(org.videolan.resources.Constants.GROUP_VIDEOS_FOLDER);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001e, code lost:
        r3 = getCurrentInput();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onKeyDown(int r3, android.view.KeyEvent r4) {
        /*
            r2 = this;
            java.lang.String r0 = "event"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            r0 = 66
            r1 = 1
            if (r3 == r0) goto L_0x00b7
            r0 = 67
            if (r3 == r0) goto L_0x00ad
            r0 = 160(0xa0, float:2.24E-43)
            if (r3 == r0) goto L_0x00b7
            switch(r3) {
                case 7: goto L_0x009f;
                case 8: goto L_0x0091;
                case 9: goto L_0x0083;
                case 10: goto L_0x0075;
                case 11: goto L_0x0067;
                case 12: goto L_0x0059;
                case 13: goto L_0x004b;
                case 14: goto L_0x003c;
                case 15: goto L_0x002d;
                case 16: goto L_0x001e;
                default: goto L_0x0015;
            }
        L_0x0015:
            switch(r3) {
                case 144: goto L_0x009f;
                case 145: goto L_0x0091;
                case 146: goto L_0x0083;
                case 147: goto L_0x0075;
                case 148: goto L_0x0067;
                case 149: goto L_0x0059;
                case 150: goto L_0x004b;
                case 151: goto L_0x003c;
                case 152: goto L_0x002d;
                case 153: goto L_0x001e;
                default: goto L_0x0018;
            }
        L_0x0018:
            boolean r1 = super.onKeyDown(r3, r4)
            goto L_0x00ba
        L_0x001e:
            com.google.android.material.textfield.TextInputEditText r3 = r2.getCurrentInput()
            if (r3 == 0) goto L_0x00ba
            java.lang.String r4 = "9"
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            r3.setText(r4)
            goto L_0x00ba
        L_0x002d:
            com.google.android.material.textfield.TextInputEditText r3 = r2.getCurrentInput()
            if (r3 == 0) goto L_0x00ba
            java.lang.String r4 = "8"
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            r3.setText(r4)
            goto L_0x00ba
        L_0x003c:
            com.google.android.material.textfield.TextInputEditText r3 = r2.getCurrentInput()
            if (r3 == 0) goto L_0x00ba
            java.lang.String r4 = "7"
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            r3.setText(r4)
            goto L_0x00ba
        L_0x004b:
            com.google.android.material.textfield.TextInputEditText r3 = r2.getCurrentInput()
            if (r3 == 0) goto L_0x00ba
            java.lang.String r4 = "6"
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            r3.setText(r4)
            goto L_0x00ba
        L_0x0059:
            com.google.android.material.textfield.TextInputEditText r3 = r2.getCurrentInput()
            if (r3 == 0) goto L_0x00ba
            java.lang.String r4 = "5"
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            r3.setText(r4)
            goto L_0x00ba
        L_0x0067:
            com.google.android.material.textfield.TextInputEditText r3 = r2.getCurrentInput()
            if (r3 == 0) goto L_0x00ba
            java.lang.String r4 = "4"
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            r3.setText(r4)
            goto L_0x00ba
        L_0x0075:
            com.google.android.material.textfield.TextInputEditText r3 = r2.getCurrentInput()
            if (r3 == 0) goto L_0x00ba
            java.lang.String r4 = "3"
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            r3.setText(r4)
            goto L_0x00ba
        L_0x0083:
            com.google.android.material.textfield.TextInputEditText r3 = r2.getCurrentInput()
            if (r3 == 0) goto L_0x00ba
            java.lang.String r4 = "2"
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            r3.setText(r4)
            goto L_0x00ba
        L_0x0091:
            com.google.android.material.textfield.TextInputEditText r3 = r2.getCurrentInput()
            if (r3 == 0) goto L_0x00ba
            java.lang.String r4 = "1"
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            r3.setText(r4)
            goto L_0x00ba
        L_0x009f:
            com.google.android.material.textfield.TextInputEditText r3 = r2.getCurrentInput()
            if (r3 == 0) goto L_0x00ba
            java.lang.String r4 = "0"
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            r3.setText(r4)
            goto L_0x00ba
        L_0x00ad:
            com.google.android.material.textfield.TextInputEditText r3 = r2.getLastSetET()
            if (r3 == 0) goto L_0x00ba
            org.videolan.vlc.gui.PinCodeActivityKt.clearText(r3)
            goto L_0x00ba
        L_0x00b7:
            r2.next()
        L_0x00ba:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.PinCodeActivity.onKeyDown(int, android.view.KeyEvent):boolean");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002a, code lost:
        if (r0.getStep().getValue() == org.videolan.vlc.gui.PinStep.NO_MATCH) goto L_0x002c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void next() {
        /*
            r5 = this;
            org.videolan.vlc.gui.SafeModeModel r0 = r5.model
            r1 = 0
            java.lang.String r2 = "model"
            if (r0 != 0) goto L_0x000b
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r0 = r1
        L_0x000b:
            androidx.lifecycle.MutableLiveData r0 = r0.getStep()
            java.lang.Object r0 = r0.getValue()
            org.videolan.vlc.gui.PinStep r3 = org.videolan.vlc.gui.PinStep.RE_ENTER
            r4 = 0
            if (r0 == r3) goto L_0x002c
            org.videolan.vlc.gui.SafeModeModel r0 = r5.model
            if (r0 != 0) goto L_0x0020
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r0 = r1
        L_0x0020:
            androidx.lifecycle.MutableLiveData r0 = r0.getStep()
            java.lang.Object r0 = r0.getValue()
            org.videolan.vlc.gui.PinStep r3 = org.videolan.vlc.gui.PinStep.NO_MATCH
            if (r0 != r3) goto L_0x0054
        L_0x002c:
            org.videolan.vlc.gui.SafeModeModel r0 = r5.model
            if (r0 != 0) goto L_0x0034
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r0 = r1
        L_0x0034:
            java.lang.String r3 = r5.getPinCode()
            boolean r0 = r0.checkMatch(r3)
            if (r0 == 0) goto L_0x0077
            org.videolan.vlc.gui.SafeModeModel r0 = r5.model
            if (r0 != 0) goto L_0x0046
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r0 = r1
        L_0x0046:
            java.lang.String r3 = r5.getPinCode()
            r0.savePin(r3)
            r0 = -1
            r5.setResult(r0)
            r5.finish()
        L_0x0054:
            org.videolan.vlc.gui.SafeModeModel r0 = r5.model
            if (r0 != 0) goto L_0x005c
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            goto L_0x005d
        L_0x005c:
            r1 = r0
        L_0x005d:
            java.lang.String r0 = r5.getPinCode()
            r1.nextStep(r0)
            com.google.android.material.textfield.TextInputEditText[] r0 = r5.getPinTexts()
            int r1 = r0.length
        L_0x0069:
            if (r4 >= r1) goto L_0x0076
            r2 = r0[r4]
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            org.videolan.vlc.gui.PinCodeActivityKt.clearText(r2)
            int r4 = r4 + 1
            goto L_0x0069
        L_0x0076:
            return
        L_0x0077:
            com.google.android.material.textfield.TextInputEditText[] r0 = r5.getPinTexts()
            int r1 = r0.length
        L_0x007c:
            if (r4 >= r1) goto L_0x0089
            r2 = r0[r4]
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            org.videolan.vlc.gui.PinCodeActivityKt.clearText(r2)
            int r4 = r4 + 1
            goto L_0x007c
        L_0x0089:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.PinCodeActivity.next():void");
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        if (menuItem.getItemId() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lorg/videolan/vlc/gui/PinCodeActivity$Companion;", "", "()V", "getIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "reason", "Lorg/videolan/vlc/gui/PinCodeReason;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PinCodeActivity.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Intent getIntent(Context context, PinCodeReason pinCodeReason) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(pinCodeReason, "reason");
            Intent intent = new Intent(context, PinCodeActivity.class);
            intent.putExtra("pin_code_reason", pinCodeReason.ordinal());
            return intent;
        }
    }
}
