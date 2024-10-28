package org.videolan.vlc.viewmodels;

import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import androidx.lifecycle.ViewModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0013B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u000bH@¢\u0006\u0002\u0010\fJ\u001e\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H@¢\u0006\u0002\u0010\u0012R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0014"}, d2 = {"Lorg/videolan/vlc/viewmodels/DisplaySettingsViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_settingChangeFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lorg/videolan/vlc/viewmodels/DisplaySettingsViewModel$SettingChange;", "settingChangeFlow", "Lkotlinx/coroutines/flow/StateFlow;", "getSettingChangeFlow", "()Lkotlinx/coroutines/flow/StateFlow;", "consume", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "send", "key", "", "value", "", "(Ljava/lang/String;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "SettingChange", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DisplaySettingsViewModel.kt */
public final class DisplaySettingsViewModel extends ViewModel {
    private final MutableStateFlow<SettingChange> _settingChangeFlow;
    private final StateFlow<SettingChange> settingChangeFlow;

    public DisplaySettingsViewModel() {
        MutableStateFlow<SettingChange> MutableStateFlow = StateFlowKt.MutableStateFlow(new SettingChange((String) null, (Object) null, 3, (DefaultConstructorMarker) null));
        this._settingChangeFlow = MutableStateFlow;
        this.settingChangeFlow = FlowKt.asStateFlow(MutableStateFlow);
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0001¢\u0006\u0002\u0010\u0005J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0001HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0001HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0013"}, d2 = {"Lorg/videolan/vlc/viewmodels/DisplaySettingsViewModel$SettingChange;", "", "key", "", "value", "(Ljava/lang/String;Ljava/lang/Object;)V", "getKey", "()Ljava/lang/String;", "getValue", "()Ljava/lang/Object;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: DisplaySettingsViewModel.kt */
    public static final class SettingChange {
        private final String key;
        private final Object value;

        public SettingChange() {
            this((String) null, (Object) null, 3, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ SettingChange copy$default(SettingChange settingChange, String str, Object obj, int i, Object obj2) {
            if ((i & 1) != 0) {
                str = settingChange.key;
            }
            if ((i & 2) != 0) {
                obj = settingChange.value;
            }
            return settingChange.copy(str, obj);
        }

        public final String component1() {
            return this.key;
        }

        public final Object component2() {
            return this.value;
        }

        public final SettingChange copy(String str, Object obj) {
            Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
            Intrinsics.checkNotNullParameter(obj, "value");
            return new SettingChange(str, obj);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SettingChange)) {
                return false;
            }
            SettingChange settingChange = (SettingChange) obj;
            return Intrinsics.areEqual((Object) this.key, (Object) settingChange.key) && Intrinsics.areEqual(this.value, settingChange.value);
        }

        public int hashCode() {
            return (this.key.hashCode() * 31) + this.value.hashCode();
        }

        public String toString() {
            return "SettingChange(key=" + this.key + ", value=" + this.value + ')';
        }

        public SettingChange(String str, Object obj) {
            Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
            Intrinsics.checkNotNullParameter(obj, "value");
            this.key = str;
            this.value = obj;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ SettingChange(String str, Object obj, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? "init" : str, (i & 2) != 0 ? 1 : obj);
        }

        public final String getKey() {
            return this.key;
        }

        public final Object getValue() {
            return this.value;
        }
    }

    public final StateFlow<SettingChange> getSettingChangeFlow() {
        return this.settingChangeFlow;
    }

    public final Object send(String str, Object obj, Continuation<? super Unit> continuation) {
        Object emit = this._settingChangeFlow.emit(new SettingChange(str, obj), continuation);
        return emit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? emit : Unit.INSTANCE;
    }

    public final Object consume(Continuation<? super Unit> continuation) {
        Object emit = this._settingChangeFlow.emit(new SettingChange((String) null, (Object) null, 3, (DefaultConstructorMarker) null), continuation);
        return emit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? emit : Unit.INSTANCE;
    }
}
