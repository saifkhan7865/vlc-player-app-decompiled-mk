package org.videolan.vlc.util;

import java.util.Date;
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

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.AutoUpdate$checkUpdate$2$1$1$1", f = "AutoUpdate.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: AutoUpdate.kt */
final class AutoUpdate$checkUpdate$2$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $abi;
    final /* synthetic */ String $decodedUrl;
    final /* synthetic */ Function2<String, Date, Unit> $listener;
    final /* synthetic */ Date $nightlyDate;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AutoUpdate$checkUpdate$2$1$1$1(Function2<? super String, ? super Date, Unit> function2, String str, String str2, Date date, Continuation<? super AutoUpdate$checkUpdate$2$1$1$1> continuation) {
        super(2, continuation);
        this.$listener = function2;
        this.$abi = str;
        this.$decodedUrl = str2;
        this.$nightlyDate = date;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AutoUpdate$checkUpdate$2$1$1$1(this.$listener, this.$abi, this.$decodedUrl, this.$nightlyDate, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AutoUpdate$checkUpdate$2$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Date date = this.$nightlyDate;
            Intrinsics.checkNotNullExpressionValue(date, "$nightlyDate");
            this.$listener.invoke("http://artifacts.videolan.org/vlc-android/nightly-" + this.$abi + '/' + this.$decodedUrl, date);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
