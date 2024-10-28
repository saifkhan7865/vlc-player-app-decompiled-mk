package org.videolan.television.ui.preferences;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.preferences.PreferencesAdvanced", f = "PreferencesAdvanced.kt", i = {}, l = {386, 387}, m = "restartLibVLC", n = {}, s = {})
/* compiled from: PreferencesAdvanced.kt */
final class PreferencesAdvanced$restartLibVLC$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PreferencesAdvanced this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreferencesAdvanced$restartLibVLC$1(PreferencesAdvanced preferencesAdvanced, Continuation<? super PreferencesAdvanced$restartLibVLC$1> continuation) {
        super(continuation);
        this.this$0 = preferencesAdvanced;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.restartLibVLC(this);
    }
}
