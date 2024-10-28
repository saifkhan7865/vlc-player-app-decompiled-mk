package org.videolan.vlc.gui.network;

import androidx.fragment.app.Fragment;
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
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.gui.SecondaryActivity;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.viewmodels.StreamsModel;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.network.StreamsFragmentDelegate$onCtxAction$3", f = "StreamsFragmentDelegate.kt", i = {}, l = {88}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: StreamsFragmentDelegate.kt */
final class StreamsFragmentDelegate$onCtxAction$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $position;
    int label;
    final /* synthetic */ StreamsFragmentDelegate this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StreamsFragmentDelegate$onCtxAction$3(StreamsFragmentDelegate streamsFragmentDelegate, int i, Continuation<? super StreamsFragmentDelegate$onCtxAction$3> continuation) {
        super(2, continuation);
        this.this$0 = streamsFragmentDelegate;
        this.$position = i;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new StreamsFragmentDelegate$onCtxAction$3(this.this$0, this.$position, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((StreamsFragmentDelegate$onCtxAction$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            UiTools uiTools = UiTools.INSTANCE;
            Fragment access$getFragment$p = this.this$0.fragment;
            StreamsModel streamsModel = null;
            if (access$getFragment$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException(SecondaryActivity.KEY_FRAGMENT);
                access$getFragment$p = null;
            }
            FragmentActivity requireActivity = access$getFragment$p.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            StreamsModel access$getViewModel$p = this.this$0.viewModel;
            if (access$getViewModel$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                streamsModel = access$getViewModel$p;
            }
            this.label = 1;
            if (uiTools.createShortcut(requireActivity, (MediaLibraryItem) streamsModel.getDataset().get(this.$position), this) == coroutine_suspended) {
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
