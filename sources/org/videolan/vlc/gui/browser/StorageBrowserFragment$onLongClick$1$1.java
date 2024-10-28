package org.videolan.vlc.gui.browser;

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
import org.videolan.vlc.viewmodels.browser.BrowserModel;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.browser.StorageBrowserFragment$onLongClick$1$1", f = "StorageBrowserFragment.kt", i = {}, l = {155}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: StorageBrowserFragment.kt */
final class StorageBrowserFragment$onLongClick$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $path;
    int label;
    final /* synthetic */ StorageBrowserFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StorageBrowserFragment$onLongClick$1$1(StorageBrowserFragment storageBrowserFragment, String str, Continuation<? super StorageBrowserFragment$onLongClick$1$1> continuation) {
        super(2, continuation);
        this.this$0 = storageBrowserFragment;
        this.$path = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new StorageBrowserFragment$onLongClick$1$1(this.this$0, this.$path, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((StorageBrowserFragment$onLongClick$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            String str = this.$path;
            Intrinsics.checkNotNullExpressionValue(str, "$path");
            this.label = 1;
            if (((BrowserModel) this.this$0.getViewModel()).toggleBanState(str, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
