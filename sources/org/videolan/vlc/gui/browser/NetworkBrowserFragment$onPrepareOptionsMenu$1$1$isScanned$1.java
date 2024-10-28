package org.videolan.vlc.gui.browser;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.vlc.gui.helpers.MedialibraryUtils;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.browser.NetworkBrowserFragment$onPrepareOptionsMenu$1$1$isScanned$1", f = "NetworkBrowserFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: NetworkBrowserFragment.kt */
final class NetworkBrowserFragment$onPrepareOptionsMenu$1$1$isScanned$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ String $it;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NetworkBrowserFragment$onPrepareOptionsMenu$1$1$isScanned$1(String str, Continuation<? super NetworkBrowserFragment$onPrepareOptionsMenu$1$1$isScanned$1> continuation) {
        super(2, continuation);
        this.$it = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new NetworkBrowserFragment$onPrepareOptionsMenu$1$1$isScanned$1(this.$it, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((NetworkBrowserFragment$onPrepareOptionsMenu$1$1$isScanned$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return Boxing.boxBoolean(MedialibraryUtils.INSTANCE.isScanned(this.$it));
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
