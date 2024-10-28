package org.videolan.vlc.gui.browser;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.viewmodels.browser.BrowserModel;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.browser.BaseBrowserFragment$onMediaPlayerEvent$1$1", f = "BaseBrowserFragment.kt", i = {}, l = {909}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BaseBrowserFragment.kt */
final class BaseBrowserFragment$onMediaPlayerEvent$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaWrapper $it;
    int label;
    final /* synthetic */ BaseBrowserFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseBrowserFragment$onMediaPlayerEvent$1$1(BaseBrowserFragment baseBrowserFragment, MediaWrapper mediaWrapper, Continuation<? super BaseBrowserFragment$onMediaPlayerEvent$1$1> continuation) {
        super(2, continuation);
        this.this$0 = baseBrowserFragment;
        this.$it = mediaWrapper;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BaseBrowserFragment$onMediaPlayerEvent$1$1(this.this$0, this.$it, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseBrowserFragment$onMediaPlayerEvent$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (((BrowserModel) this.this$0.getViewModel()).updateMediaPlayed(this.$it, this) == coroutine_suspended) {
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
