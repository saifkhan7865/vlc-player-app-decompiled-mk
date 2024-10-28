package org.videolan.resources.util;

import android.content.Context;
import android.content.SharedPreferences;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.tools.Settings;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "Landroid/content/SharedPreferences;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.resources.util.ExtensionsKt$startMedialibrary$1$prefs$1", f = "Extensions.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: Extensions.kt */
final class ExtensionsKt$startMedialibrary$1$prefs$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super SharedPreferences>, Object> {
    final /* synthetic */ Context $this_startMedialibrary;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ExtensionsKt$startMedialibrary$1$prefs$1(Context context, Continuation<? super ExtensionsKt$startMedialibrary$1$prefs$1> continuation) {
        super(2, continuation);
        this.$this_startMedialibrary = context;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ExtensionsKt$startMedialibrary$1$prefs$1(this.$this_startMedialibrary, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super SharedPreferences> continuation) {
        return ((ExtensionsKt$startMedialibrary$1$prefs$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return Settings.INSTANCE.getInstance(this.$this_startMedialibrary);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
