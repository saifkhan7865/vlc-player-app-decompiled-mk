package org.videolan.television.ui.preferences;

import android.app.Activity;
import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate;
import org.videolan.vlc.gui.preferences.search.PreferenceParser;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.preferences.PreferencesAdvanced$onPreferenceTreeClick$9", f = "PreferencesAdvanced.kt", i = {}, l = {293, 294}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PreferencesAdvanced.kt */
final class PreferencesAdvanced$onPreferenceTreeClick$9 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ File $dst;
    int label;
    final /* synthetic */ PreferencesAdvanced this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreferencesAdvanced$onPreferenceTreeClick$9(PreferencesAdvanced preferencesAdvanced, File file, Continuation<? super PreferencesAdvanced$onPreferenceTreeClick$9> continuation) {
        super(2, continuation);
        this.this$0 = preferencesAdvanced;
        this.$dst = file;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PreferencesAdvanced$onPreferenceTreeClick$9(this.this$0, this.$dst, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PreferencesAdvanced$onPreferenceTreeClick$9) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            StoragePermissionsDelegate.Companion companion = StoragePermissionsDelegate.Companion;
            Activity activity = this.this$0.getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
            Uri fromFile = Uri.fromFile(this.$dst);
            Intrinsics.checkNotNullExpressionValue(fromFile, "fromFile(...)");
            this.label = 1;
            obj = companion.getWritePermission((FragmentActivity) activity, fromFile, (Continuation<? super Boolean>) this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (((Boolean) obj).booleanValue()) {
            PreferenceParser preferenceParser = PreferenceParser.INSTANCE;
            Activity activity2 = this.this$0.getActivity();
            Intrinsics.checkNotNull(activity2);
            this.label = 2;
            if (preferenceParser.exportPreferences(activity2, this.$dst, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}
