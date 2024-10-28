package org.videolan.vlc.gui.browser;

import androidx.appcompat.view.ActionMode;
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
import kotlinx.coroutines.CoroutineScope;
import org.videolan.tools.MultiSelectHelper;
import org.videolan.vlc.gui.helpers.UiToolsKt;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H@"}, d2 = {"<anonymous>", "", "T", "Lorg/videolan/vlc/viewmodels/SortableModel;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.browser.MediaBrowserFragment$restoreMultiSelectHelper$1$1", f = "MediaBrowserFragment.kt", i = {}, l = {251}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaBrowserFragment.kt */
final class MediaBrowserFragment$restoreMultiSelectHelper$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MultiSelectHelper<T> $it;
    int label;
    final /* synthetic */ MediaBrowserFragment<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaBrowserFragment$restoreMultiSelectHelper$1$1(MediaBrowserFragment<T> mediaBrowserFragment, MultiSelectHelper<T> multiSelectHelper, Continuation<? super MediaBrowserFragment$restoreMultiSelectHelper$1$1> continuation) {
        super(2, continuation);
        this.this$0 = mediaBrowserFragment;
        this.$it = multiSelectHelper;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaBrowserFragment$restoreMultiSelectHelper$1$1(this.this$0, this.$it, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MediaBrowserFragment$restoreMultiSelectHelper$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FragmentActivity requireActivity = this.this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            ActionMode actionMode = this.this$0.getActionMode();
            Intrinsics.checkNotNull(actionMode);
            MultiSelectHelper<T> multiSelectHelper = this.$it;
            Intrinsics.checkNotNull(multiSelectHelper, "null cannot be cast to non-null type org.videolan.tools.MultiSelectHelper<org.videolan.medialibrary.media.MediaLibraryItem>");
            this.label = 1;
            if (UiToolsKt.fillActionMode(requireActivity, actionMode, multiSelectHelper, this) == coroutine_suspended) {
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
