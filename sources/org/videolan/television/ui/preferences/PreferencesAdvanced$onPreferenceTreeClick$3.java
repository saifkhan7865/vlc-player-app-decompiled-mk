package org.videolan.television.ui.preferences;

import android.app.ActivityManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferencesAdvanced.kt */
final class PreferencesAdvanced$onPreferenceTreeClick$3 extends Lambda implements Function0<Unit> {
    final /* synthetic */ PreferencesAdvanced this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreferencesAdvanced$onPreferenceTreeClick$3(PreferencesAdvanced preferencesAdvanced) {
        super(0);
        this.this$0 = preferencesAdvanced;
    }

    public final void invoke() {
        Object systemService = this.this$0.getActivity().getSystemService("activity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        boolean unused = ((ActivityManager) systemService).clearApplicationUserData();
    }
}
