package org.videolan.vlc.gui.preferences;

import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.videolan.vlc.gui.preferences.PreferencesActivity;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.preferences.PreferencesActivity$Companion", f = "PreferencesActivity.kt", i = {0, 0}, l = {152}, m = "launchWithPref", n = {"activity", "prefKey"}, s = {"L$0", "L$1"})
/* compiled from: PreferencesActivity.kt */
final class PreferencesActivity$Companion$launchWithPref$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PreferencesActivity.Companion this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreferencesActivity$Companion$launchWithPref$1(PreferencesActivity.Companion companion, Continuation<? super PreferencesActivity$Companion$launchWithPref$1> continuation) {
        super(continuation);
        this.this$0 = companion;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.launchWithPref((FragmentActivity) null, (String) null, this);
    }
}
