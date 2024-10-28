package org.videolan.vlc.gui.browser;

import android.net.Uri;
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
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.repository.BrowserFavRepository;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.browser.MainBrowserFragment$onCtxAction$1", f = "MainBrowserFragment.kt", i = {}, l = {409}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MainBrowserFragment.kt */
final class MainBrowserFragment$onCtxAction$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaWrapper $mw;
    int label;
    final /* synthetic */ MainBrowserFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MainBrowserFragment$onCtxAction$1(MainBrowserFragment mainBrowserFragment, MediaWrapper mediaWrapper, Continuation<? super MainBrowserFragment$onCtxAction$1> continuation) {
        super(2, continuation);
        this.this$0 = mainBrowserFragment;
        this.$mw = mediaWrapper;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainBrowserFragment$onCtxAction$1(this.this$0, this.$mw, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainBrowserFragment$onCtxAction$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            BrowserFavRepository access$getBrowserFavRepository$p = this.this$0.browserFavRepository;
            if (access$getBrowserFavRepository$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("browserFavRepository");
                access$getBrowserFavRepository$p = null;
            }
            Uri uri = this.$mw.getUri();
            Intrinsics.checkNotNullExpressionValue(uri, "getUri(...)");
            this.label = 1;
            if (access$getBrowserFavRepository$p.deleteBrowserFav(uri, this) == coroutine_suspended) {
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
