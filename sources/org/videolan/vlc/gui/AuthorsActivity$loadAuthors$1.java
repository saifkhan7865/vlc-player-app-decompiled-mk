package org.videolan.vlc.gui;

import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.AuthorsActivity$loadAuthors$1", f = "AuthorsActivity.kt", i = {}, l = {63}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: AuthorsActivity.kt */
final class AuthorsActivity$loadAuthors$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ AuthorsActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AuthorsActivity$loadAuthors$1(AuthorsActivity authorsActivity, Continuation<? super AuthorsActivity$loadAuthors$1> continuation) {
        super(2, continuation);
        this.this$0 = authorsActivity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AuthorsActivity$loadAuthors$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AuthorsActivity$loadAuthors$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = BuildersKt.withContext(Dispatchers.getIO(), new AuthorsActivity$loadAuthors$1$authors$1((Continuation<? super AuthorsActivity$loadAuthors$1$authors$1>) null), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Intrinsics.checkNotNullExpressionValue(obj, "withContext(...)");
        this.this$0.getBinding$vlc_android_release().authorsList.setAdapter(new AuthorsAdapter((List) obj));
        return Unit.INSTANCE;
    }
}
