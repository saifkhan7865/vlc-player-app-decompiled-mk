package org.videolan.television.ui.browser;

import androidx.fragment.app.FragmentActivity;
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
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.television.ui.TvUtil;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.browser.MediaScrapingBrowserTvFragment$onClick$1$1", f = "MediaScrapingBrowserTvFragment.kt", i = {}, l = {147}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaScrapingBrowserTvFragment.kt */
final class MediaScrapingBrowserTvFragment$onClick$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $it;
    int label;
    final /* synthetic */ MediaScrapingBrowserTvFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaScrapingBrowserTvFragment$onClick$1$1(MediaScrapingBrowserTvFragment mediaScrapingBrowserTvFragment, long j, Continuation<? super MediaScrapingBrowserTvFragment$onClick$1$1> continuation) {
        super(2, continuation);
        this.this$0 = mediaScrapingBrowserTvFragment;
        this.$it = j;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaScrapingBrowserTvFragment$onClick$1$1(this.this$0, this.$it, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MediaScrapingBrowserTvFragment$onClick$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FragmentActivity requireActivity = this.this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            long j = this.$it;
            this.label = 1;
            obj = BuildersKt.withContext(Dispatchers.getIO(), new MediaScrapingBrowserTvFragment$onClick$1$1$invokeSuspend$$inlined$getFromMl$1(requireActivity, (Continuation) null, j), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        MediaWrapper mediaWrapper = (MediaWrapper) obj;
        TvUtil tvUtil = TvUtil.INSTANCE;
        FragmentActivity requireActivity2 = this.this$0.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
        Intrinsics.checkNotNull(mediaWrapper);
        TvUtil.showMediaDetail$default(tvUtil, requireActivity2, mediaWrapper, false, 4, (Object) null);
        return Unit.INSTANCE;
    }
}
