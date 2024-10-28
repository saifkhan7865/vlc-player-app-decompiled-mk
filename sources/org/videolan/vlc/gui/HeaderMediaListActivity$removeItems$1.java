package org.videolan.vlc.gui;

import androidx.lifecycle.LifecycleOwnerKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.util.Permissions;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: HeaderMediaListActivity.kt */
final class HeaderMediaListActivity$removeItems$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ List<MediaWrapper> $items;
    final /* synthetic */ HeaderMediaListActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HeaderMediaListActivity$removeItems$1(HeaderMediaListActivity headerMediaListActivity, List<? extends MediaWrapper> list) {
        super(0);
        this.this$0 = headerMediaListActivity;
        this.$items = list;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.gui.HeaderMediaListActivity$removeItems$1$1", f = "HeaderMediaListActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.gui.HeaderMediaListActivity$removeItems$1$1  reason: invalid class name */
    /* compiled from: HeaderMediaListActivity.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(list, headerMediaListActivity, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                for (MediaWrapper next : list) {
                    Runnable headerMediaListActivity$removeItems$1$1$invokeSuspend$$inlined$Runnable$1 = new HeaderMediaListActivity$removeItems$1$1$invokeSuspend$$inlined$Runnable$1(headerMediaListActivity, next);
                    if (Permissions.INSTANCE.checkWritePermission(headerMediaListActivity, next, headerMediaListActivity$removeItems$1$1$invokeSuspend$$inlined$Runnable$1)) {
                        headerMediaListActivity$removeItems$1$1$invokeSuspend$$inlined$Runnable$1.run();
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final void invoke() {
        final List<MediaWrapper> list = this.$items;
        final HeaderMediaListActivity headerMediaListActivity = this.this$0;
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.this$0), (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1((Continuation<? super AnonymousClass1>) null), 3, (Object) null);
    }
}
