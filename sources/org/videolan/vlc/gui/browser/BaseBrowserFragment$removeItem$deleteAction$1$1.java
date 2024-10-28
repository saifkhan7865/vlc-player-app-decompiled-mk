package org.videolan.vlc.gui.browser;

import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.viewmodels.browser.BrowserModel;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.browser.BaseBrowserFragment$removeItem$deleteAction$1$1", f = "BaseBrowserFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BaseBrowserFragment.kt */
final class BaseBrowserFragment$removeItem$deleteAction$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaWrapper $mw;
    int label;
    final /* synthetic */ BaseBrowserFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseBrowserFragment$removeItem$deleteAction$1$1(BaseBrowserFragment baseBrowserFragment, MediaWrapper mediaWrapper, Continuation<? super BaseBrowserFragment$removeItem$deleteAction$1$1> continuation) {
        super(2, continuation);
        this.this$0 = baseBrowserFragment;
        this.$mw = mediaWrapper;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BaseBrowserFragment$removeItem$deleteAction$1$1(this.this$0, this.$mw, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseBrowserFragment$removeItem$deleteAction$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            MediaUtils mediaUtils = MediaUtils.INSTANCE;
            FragmentActivity requireActivity = this.this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            final BaseBrowserFragment baseBrowserFragment = this.this$0;
            mediaUtils.deleteItem(requireActivity, this.$mw, new Function1<MediaLibraryItem, Unit>() {
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((MediaLibraryItem) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(MediaLibraryItem mediaLibraryItem) {
                    Intrinsics.checkNotNullParameter(mediaLibraryItem, "it");
                    ((BrowserModel) baseBrowserFragment.getViewModel()).refresh();
                }
            });
            ((BrowserModel) this.this$0.getViewModel()).remove(this.$mw);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
