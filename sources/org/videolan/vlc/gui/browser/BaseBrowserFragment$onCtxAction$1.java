package org.videolan.vlc.gui.browser;

import android.content.Context;
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
import org.videolan.vlc.media.MediaUtils;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.browser.BaseBrowserFragment$onCtxAction$1", f = "BaseBrowserFragment.kt", i = {}, l = {796}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BaseBrowserFragment.kt */
final class BaseBrowserFragment$onCtxAction$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaWrapper $mw;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ BaseBrowserFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseBrowserFragment$onCtxAction$1(BaseBrowserFragment baseBrowserFragment, MediaWrapper mediaWrapper, Continuation<? super BaseBrowserFragment$onCtxAction$1> continuation) {
        super(2, continuation);
        this.this$0 = baseBrowserFragment;
        this.$mw = mediaWrapper;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BaseBrowserFragment$onCtxAction$1(this.this$0, this.$mw, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseBrowserFragment$onCtxAction$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        MediaUtils mediaUtils;
        Context context;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            mediaUtils = MediaUtils.INSTANCE;
            Context activity = this.this$0.getActivity();
            this.L$0 = mediaUtils;
            this.L$1 = activity;
            this.label = 1;
            Object access$getMediaWithMeta = this.this$0.getMediaWithMeta(this.$mw, this);
            if (access$getMediaWithMeta == coroutine_suspended) {
                return coroutine_suspended;
            }
            context = activity;
            obj = access$getMediaWithMeta;
        } else if (i == 1) {
            context = (Context) this.L$1;
            mediaUtils = (MediaUtils) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        mediaUtils.openMedia(context, (MediaWrapper) obj);
        return Unit.INSTANCE;
    }
}
