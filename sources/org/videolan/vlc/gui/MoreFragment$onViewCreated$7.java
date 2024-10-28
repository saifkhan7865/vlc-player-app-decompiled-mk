package org.videolan.vlc.gui;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleCoroutineScope;
import androidx.lifecycle.LifecycleOwnerKt;
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
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import org.videolan.vlc.gui.view.EmptyLoadingState;
import org.videolan.vlc.gui.view.TitleListView;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "invoke", "(Ljava/lang/Boolean;)V"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MoreFragment.kt */
final class MoreFragment$onViewCreated$7 extends Lambda implements Function1<Boolean, Unit> {
    final /* synthetic */ MoreFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MoreFragment$onViewCreated$7(MoreFragment moreFragment) {
        super(1);
        this.this$0 = moreFragment;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.gui.MoreFragment$onViewCreated$7$1", f = "MoreFragment.kt", i = {}, l = {146}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.gui.MoreFragment$onViewCreated$7$1  reason: invalid class name */
    /* compiled from: MoreFragment.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(bool, moreFragment, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Boolean bool = bool;
                Intrinsics.checkNotNullExpressionValue(bool, "$it");
                if (bool.booleanValue()) {
                    this.label = 1;
                    if (DelayKt.delay(300, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            FragmentActivity activity = moreFragment.getActivity();
            TitleListView titleListView = null;
            MainActivity mainActivity = activity instanceof MainActivity ? (MainActivity) activity : null;
            if (mainActivity != null) {
                Boolean bool2 = bool;
                Intrinsics.checkNotNullExpressionValue(bool2, "$it");
                mainActivity.setRefreshing(bool2.booleanValue());
            }
            Boolean bool3 = bool;
            Intrinsics.checkNotNullExpressionValue(bool3, "$it");
            if (bool3.booleanValue()) {
                TitleListView access$getStreamsEntry$p = moreFragment.streamsEntry;
                if (access$getStreamsEntry$p == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("streamsEntry");
                } else {
                    titleListView = access$getStreamsEntry$p;
                }
                titleListView.getLoading().setState(EmptyLoadingState.LOADING);
            }
            return Unit.INSTANCE;
        }
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Boolean) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(final Boolean bool) {
        LifecycleCoroutineScope lifecycleScope = LifecycleOwnerKt.getLifecycleScope(this.this$0);
        final MoreFragment moreFragment = this.this$0;
        lifecycleScope.launchWhenStarted(new AnonymousClass1((Continuation<? super AnonymousClass1>) null));
    }
}
