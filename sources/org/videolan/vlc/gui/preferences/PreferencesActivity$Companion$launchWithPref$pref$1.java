package org.videolan.vlc.gui.preferences;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.vlc.gui.preferences.search.PreferenceItem;
import org.videolan.vlc.gui.preferences.search.PreferenceParser;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003*\u00020\u0004H@"}, d2 = {"<anonymous>", "Ljava/util/ArrayList;", "Lorg/videolan/vlc/gui/preferences/search/PreferenceItem;", "Lkotlin/collections/ArrayList;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.preferences.PreferencesActivity$Companion$launchWithPref$pref$1", f = "PreferencesActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PreferencesActivity.kt */
final class PreferencesActivity$Companion$launchWithPref$pref$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<PreferenceItem>>, Object> {
    final /* synthetic */ FragmentActivity $activity;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreferencesActivity$Companion$launchWithPref$pref$1(FragmentActivity fragmentActivity, Continuation<? super PreferencesActivity$Companion$launchWithPref$pref$1> continuation) {
        super(2, continuation);
        this.$activity = fragmentActivity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PreferencesActivity$Companion$launchWithPref$pref$1(this.$activity, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ArrayList<PreferenceItem>> continuation) {
        return ((PreferencesActivity$Companion$launchWithPref$pref$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return PreferenceParser.INSTANCE.parsePreferences((Context) this.$activity, true);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
