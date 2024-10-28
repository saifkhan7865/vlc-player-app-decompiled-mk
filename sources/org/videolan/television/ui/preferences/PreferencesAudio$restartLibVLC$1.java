package org.videolan.television.ui.preferences;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.preferences.PreferencesAudio", f = "PreferencesAudio.kt", i = {}, l = {214, 215}, m = "restartLibVLC", n = {}, s = {})
/* compiled from: PreferencesAudio.kt */
final class PreferencesAudio$restartLibVLC$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PreferencesAudio this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreferencesAudio$restartLibVLC$1(PreferencesAudio preferencesAudio, Continuation<? super PreferencesAudio$restartLibVLC$1> continuation) {
        super(continuation);
        this.this$0 = preferencesAudio;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.restartLibVLC(this);
    }
}
