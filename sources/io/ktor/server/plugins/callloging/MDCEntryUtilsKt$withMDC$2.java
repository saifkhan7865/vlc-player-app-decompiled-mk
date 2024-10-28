package io.ktor.server.plugins.callloging;

import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 176)
@DebugMetadata(c = "io.ktor.server.plugins.callloging.MDCEntryUtilsKt$withMDC$2", f = "MDCEntryUtils.kt", i = {}, l = {21}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MDCEntryUtils.kt */
public final class MDCEntryUtilsKt$withMDC$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<Continuation<? super Unit>, Object> $block;
    final /* synthetic */ List<MDCEntry> $mdcEntries;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MDCEntryUtilsKt$withMDC$2(Function1<? super Continuation<? super Unit>, ? extends Object> function1, List<MDCEntry> list, Continuation<? super MDCEntryUtilsKt$withMDC$2> continuation) {
        super(2, continuation);
        this.$block = function1;
        this.$mdcEntries = list;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MDCEntryUtilsKt$withMDC$2(this.$block, this.$mdcEntries, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MDCEntryUtilsKt$withMDC$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Function1<Continuation<? super Unit>, Object> function1 = this.$block;
            this.label = 1;
            if (function1.invoke(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th) {
                MDCEntryUtilsKt.cleanup(this.$mdcEntries);
                throw th;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        MDCEntryUtilsKt.cleanup(this.$mdcEntries);
        return Unit.INSTANCE;
    }

    /* JADX INFO: finally extract failed */
    public final Object invokeSuspend$$forInline(Object obj) {
        try {
            this.$block.invoke(this);
            MDCEntryUtilsKt.cleanup(this.$mdcEntries);
            return Unit.INSTANCE;
        } catch (Throwable th) {
            MDCEntryUtilsKt.cleanup(this.$mdcEntries);
            throw th;
        }
    }
}
