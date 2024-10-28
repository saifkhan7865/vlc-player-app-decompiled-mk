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
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.media.MediaUtils;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.browser.BaseBrowserFragment$onClick$1", f = "BaseBrowserFragment.kt", i = {}, l = {713}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BaseBrowserFragment.kt */
final class BaseBrowserFragment$onClick$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaLibraryItem $item;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ BaseBrowserFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseBrowserFragment$onClick$1(BaseBrowserFragment baseBrowserFragment, MediaLibraryItem mediaLibraryItem, Continuation<? super BaseBrowserFragment$onClick$1> continuation) {
        super(2, continuation);
        this.this$0 = baseBrowserFragment;
        this.$item = mediaLibraryItem;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BaseBrowserFragment$onClick$1(this.this$0, this.$item, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseBrowserFragment$onClick$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        MediaUtils mediaUtils;
        Context context;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            mediaUtils = MediaUtils.INSTANCE;
            Context requireContext = this.this$0.requireContext();
            this.L$0 = mediaUtils;
            this.L$1 = requireContext;
            this.label = 1;
            Object access$getMediaWithMeta = this.this$0.getMediaWithMeta((MediaWrapper) this.$item, this);
            if (access$getMediaWithMeta == coroutine_suspended) {
                return coroutine_suspended;
            }
            context = requireContext;
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
