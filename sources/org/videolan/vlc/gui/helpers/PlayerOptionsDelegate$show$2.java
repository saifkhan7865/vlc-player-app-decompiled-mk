package org.videolan.vlc.gui.helpers;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.helpers.PlayerOptionsDelegate$show$2", f = "PlayerOptionsDelegate.kt", i = {}, l = {177}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PlayerOptionsDelegate.kt */
final class PlayerOptionsDelegate$show$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ PlayerOptionsDelegate this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlayerOptionsDelegate$show$2(PlayerOptionsDelegate playerOptionsDelegate, Continuation<? super PlayerOptionsDelegate$show$2> continuation) {
        super(2, continuation);
        this.this$0 = playerOptionsDelegate;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlayerOptionsDelegate$show$2(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlayerOptionsDelegate$show$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.gui.helpers.PlayerOptionsDelegate$show$2$1", f = "PlayerOptionsDelegate.kt", i = {}, l = {177}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.gui.helpers.PlayerOptionsDelegate$show$2$1  reason: invalid class name */
    /* compiled from: PlayerOptionsDelegate.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (DelayKt.delay(100, this) == coroutine_suspended) {
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

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        RecyclerView recyclerView = null;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass1((Continuation<? super AnonymousClass1>) null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        RecyclerView access$getRecyclerview$p = this.this$0.recyclerview;
        if (access$getRecyclerview$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerview");
            access$getRecyclerview$p = null;
        }
        RecyclerView.LayoutManager layoutManager = access$getRecyclerview$p.getLayoutManager();
        Intrinsics.checkNotNull(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
        int findFirstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        RecyclerView access$getRecyclerview$p2 = this.this$0.recyclerview;
        if (access$getRecyclerview$p2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerview");
        } else {
            recyclerView = access$getRecyclerview$p2;
        }
        RecyclerView.LayoutManager layoutManager2 = recyclerView.getLayoutManager();
        Intrinsics.checkNotNull(layoutManager2, "null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
        View findViewByPosition = ((LinearLayoutManager) layoutManager2).findViewByPosition(findFirstVisibleItemPosition);
        if (findViewByPosition != null) {
            Boxing.boxBoolean(findViewByPosition.requestFocus());
        }
        return Unit.INSTANCE;
    }
}
