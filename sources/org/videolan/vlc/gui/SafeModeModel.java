package org.videolan.vlc.gui;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import java.security.MessageDigest;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.videolan.resources.util.ExtensionsKt;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u001dB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\nJ\u0010\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\nH\u0002J\u0010\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\nH\u0002J\u0006\u0010\u0019\u001a\u00020\u0014J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0015\u001a\u00020\nJ\u000e\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0015\u001a\u00020\nR*\u0010\u0007\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bj\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n`\u000bX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001f\u0010\u000e\u001a\u0010\u0012\f\u0012\n \u0010*\u0004\u0018\u00010\t0\t0\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"Lorg/videolan/vlc/gui/SafeModeModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "reason", "Lorg/videolan/vlc/gui/PinCodeReason;", "(Landroid/app/Application;Lorg/videolan/vlc/gui/PinCodeReason;)V", "pins", "Ljava/util/HashMap;", "Lorg/videolan/vlc/gui/PinStep;", "", "Lkotlin/collections/HashMap;", "getReason", "()Lorg/videolan/vlc/gui/PinCodeReason;", "step", "Landroidx/lifecycle/MutableLiveData;", "kotlin.jvm.PlatformType", "getStep", "()Landroidx/lifecycle/MutableLiveData;", "checkMatch", "", "pin", "checkValid", "getSha256", "input", "isFinalStep", "nextStep", "", "savePin", "Factory", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PinCodeActivity.kt */
public final class SafeModeModel extends AndroidViewModel {
    private final HashMap<PinStep, String> pins = new HashMap<>();
    private final PinCodeReason reason;
    private final MutableLiveData<PinStep> step;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PinCodeActivity.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
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
                org.videolan.vlc.gui.PinStep r1 = org.videolan.vlc.gui.PinStep.INVALID     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.SafeModeModel.WhenMappings.<clinit>():void");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SafeModeModel(Application application, PinCodeReason pinCodeReason) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(pinCodeReason, "reason");
        this.reason = pinCodeReason;
        MutableLiveData<PinStep> mutableLiveData = new MutableLiveData<>(PinStep.ENTER_EXISTING);
        this.step = mutableLiveData;
        CharSequence string = ((SharedPreferences) Settings.INSTANCE.getInstance(application)).getString(SettingsKt.KEY_SAFE_MODE_PIN, "");
        if (string == null || StringsKt.isBlank(string)) {
            mutableLiveData.postValue(PinStep.ENTER_NEW);
        }
    }

    public final PinCodeReason getReason() {
        return this.reason;
    }

    public final void nextStep(String str) {
        Intrinsics.checkNotNullParameter(str, "pin");
        if (ArraysKt.contains((T[]) new PinStep[]{PinStep.ENTER_EXISTING, PinStep.INVALID}, this.step.getValue()) && !checkValid(str)) {
            this.step.postValue(PinStep.INVALID);
        } else if (this.reason == PinCodeReason.CHECK || this.reason == PinCodeReason.UNLOCK) {
            MutableLiveData<PinStep> mutableLiveData = this.step;
            mutableLiveData.postValue(mutableLiveData.getValue() == PinStep.LOGIN_SUCCESS ? PinStep.EXIT : PinStep.LOGIN_SUCCESS);
        } else {
            PinStep value = this.step.getValue();
            Intrinsics.checkNotNull(value);
            this.pins.put(value, str);
            PinStep value2 = this.step.getValue();
            Intrinsics.checkNotNull(value2);
            int i = WhenMappings.$EnumSwitchMapping$0[value2.ordinal()];
            if (i == 1 || i == 2) {
                this.step.postValue(PinStep.ENTER_NEW);
            } else {
                this.step.postValue(PinStep.RE_ENTER);
            }
        }
    }

    public final boolean isFinalStep() {
        return this.step.getValue() == PinStep.RE_ENTER;
    }

    public final boolean checkMatch(String str) {
        Intrinsics.checkNotNullParameter(str, "pin");
        boolean areEqual = Intrinsics.areEqual((Object) this.pins.get(PinStep.ENTER_NEW), (Object) str);
        if (!areEqual) {
            this.step.postValue(PinStep.NO_MATCH);
        }
        return areEqual;
    }

    private final boolean checkValid(String str) {
        return Intrinsics.areEqual((Object) getSha256(str), (Object) ((SharedPreferences) Settings.INSTANCE.getInstance(getApplication())).getString(SettingsKt.KEY_SAFE_MODE_PIN, ""));
    }

    public final void savePin(String str) {
        Intrinsics.checkNotNullParameter(str, "pin");
        SettingsKt.putSingle((SharedPreferences) Settings.INSTANCE.getInstance(getApplication()), SettingsKt.KEY_SAFE_MODE_PIN, getSha256(str));
    }

    private final String getSha256(String str) {
        MessageDigest instance = MessageDigest.getInstance("SHA-256");
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        instance.update(bytes);
        byte[] digest = instance.digest();
        Intrinsics.checkNotNullExpressionValue(digest, "digest(...)");
        return ArraysKt.joinToString$default(digest, (CharSequence) ":", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) SafeModeModel$getSha256$1.INSTANCE, 30, (Object) null);
    }

    public final MutableLiveData<PinStep> getStep() {
        return this.step;
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J%\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\u000bH\u0016¢\u0006\u0002\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lorg/videolan/vlc/gui/SafeModeModel$Factory;", "Landroidx/lifecycle/ViewModelProvider$NewInstanceFactory;", "context", "Landroid/content/Context;", "reason", "Lorg/videolan/vlc/gui/PinCodeReason;", "(Landroid/content/Context;Lorg/videolan/vlc/gui/PinCodeReason;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PinCodeActivity.kt */
    public static final class Factory extends ViewModelProvider.NewInstanceFactory {
        private final Context context;
        private final PinCodeReason reason;

        public Factory(Context context2, PinCodeReason pinCodeReason) {
            Intrinsics.checkNotNullParameter(context2, "context");
            Intrinsics.checkNotNullParameter(pinCodeReason, "reason");
            this.context = context2;
            this.reason = pinCodeReason;
        }

        public <T extends ViewModel> T create(Class<T> cls) {
            Intrinsics.checkNotNullParameter(cls, "modelClass");
            Context applicationContext = ExtensionsKt.retrieveApplication(this.context).getApplicationContext();
            Intrinsics.checkNotNull(applicationContext, "null cannot be cast to non-null type android.app.Application");
            return (ViewModel) new SafeModeModel((Application) applicationContext, this.reason);
        }
    }
}
