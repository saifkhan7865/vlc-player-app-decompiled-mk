package org.videolan.vlc.gui;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.channels.ActorKt;
import kotlinx.coroutines.channels.SendChannel;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00020\u00042\u00020\u0005:\u0001)B\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0014J\b\u0010\u001a\u001a\u00020\u001bH\u0014J\u0015\u0010\u001c\u001a\u00028\u00002\u0006\u0010\u001d\u001a\u00020\u001eH\u0016¢\u0006\u0002\u0010\u001fJ\b\u0010 \u001a\u00020\u001eH\u0016J\u001c\u0010!\u001a\u00020\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000\fH@¢\u0006\u0002\u0010$J\b\u0010%\u001a\u00020\u001bH\u0007J\b\u0010&\u001a\u00020\"H\u0014J\u001c\u0010'\u001a\b\u0012\u0004\u0012\u00028\u00000\f2\f\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000\fH\u0014J\u0016\u0010(\u001a\u00020\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000\fH\u0007R\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR*\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\f2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\f@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR!\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u00118BX\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013R \u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\f0\u0017X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0018\u0010\u0006¨\u0006*"}, d2 = {"Lorg/videolan/vlc/gui/DiffUtilAdapter;", "D", "VH", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lkotlinx/coroutines/CoroutineScope;", "()V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "<set-?>", "", "dataset", "getDataset", "()Ljava/util/List;", "diffCallback", "Lorg/videolan/vlc/gui/DiffUtilAdapter$DiffCallback;", "getDiffCallback", "()Lorg/videolan/vlc/gui/DiffUtilAdapter$DiffCallback;", "diffCallback$delegate", "Lkotlin/Lazy;", "updateActor", "Lkotlinx/coroutines/channels/SendChannel;", "getUpdateActor$annotations", "createCB", "detectMoves", "", "getItem", "position", "", "(I)Ljava/lang/Object;", "getItemCount", "internalUpdate", "", "list", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isEmpty", "onUpdateFinished", "prepareList", "update", "DiffCallback", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DiffUtilAdapter.kt */
public abstract class DiffUtilAdapter<D, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements CoroutineScope {
    private final CoroutineContext coroutineContext = Dispatchers.getMain().getImmediate().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null));
    private List<? extends D> dataset = CollectionsKt.emptyList();
    private final Lazy diffCallback$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new DiffUtilAdapter$diffCallback$2(this));
    private final SendChannel<List<? extends D>> updateActor = ActorKt.actor$default(this, (CoroutineContext) null, -1, (CoroutineStart) null, (Function1) null, new DiffUtilAdapter$updateActor$1(this, (Continuation<? super DiffUtilAdapter$updateActor$1>) null), 13, (Object) null);

    private static /* synthetic */ void getUpdateActor$annotations() {
    }

    /* access modifiers changed from: protected */
    public boolean detectMoves() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onUpdateFinished() {
    }

    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    public final List<D> getDataset() {
        return this.dataset;
    }

    /* access modifiers changed from: private */
    public final DiffCallback<D> getDiffCallback() {
        return (DiffCallback) this.diffCallback$delegate.getValue();
    }

    public final void update(List<? extends D> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.updateActor.m1139trySendJP2dKIU(list);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object internalUpdate(java.util.List<? extends D> r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof org.videolan.vlc.gui.DiffUtilAdapter$internalUpdate$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            org.videolan.vlc.gui.DiffUtilAdapter$internalUpdate$1 r0 = (org.videolan.vlc.gui.DiffUtilAdapter$internalUpdate$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.gui.DiffUtilAdapter$internalUpdate$1 r0 = new org.videolan.vlc.gui.DiffUtilAdapter$internalUpdate$1
            r0.<init>(r5, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r6 = r0.L$0
            org.videolan.vlc.gui.DiffUtilAdapter r6 = (org.videolan.vlc.gui.DiffUtilAdapter) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0053
        L_0x002e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r7)
            kotlinx.coroutines.CoroutineDispatcher r7 = kotlinx.coroutines.Dispatchers.getDefault()
            kotlin.coroutines.CoroutineContext r7 = (kotlin.coroutines.CoroutineContext) r7
            org.videolan.vlc.gui.DiffUtilAdapter$internalUpdate$2 r2 = new org.videolan.vlc.gui.DiffUtilAdapter$internalUpdate$2
            r4 = 0
            r2.<init>(r5, r6, r4)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r7 = kotlinx.coroutines.BuildersKt.withContext(r7, r2, r0)
            if (r7 != r1) goto L_0x0052
            return r1
        L_0x0052:
            r6 = r5
        L_0x0053:
            kotlin.Pair r7 = (kotlin.Pair) r7
            java.lang.Object r0 = r7.component1()
            java.util.List r0 = (java.util.List) r0
            java.lang.Object r7 = r7.component2()
            androidx.recyclerview.widget.DiffUtil$DiffResult r7 = (androidx.recyclerview.widget.DiffUtil.DiffResult) r7
            r6.dataset = r0
            r0 = r6
            androidx.recyclerview.widget.RecyclerView$Adapter r0 = (androidx.recyclerview.widget.RecyclerView.Adapter) r0
            r7.dispatchUpdatesTo((androidx.recyclerview.widget.RecyclerView.Adapter) r0)
            r6.onUpdateFinished()
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.DiffUtilAdapter.internalUpdate(java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public List<D> prepareList(List<? extends D> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        return CollectionsKt.toList(list);
    }

    public final boolean isEmpty() {
        return this.dataset.isEmpty();
    }

    public D getItem(int i) {
        return this.dataset.get(i);
    }

    public int getItemCount() {
        return this.dataset.size();
    }

    /* access modifiers changed from: protected */
    public DiffCallback<D> createCB() {
        return new DiffCallback<>();
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b\u0016\u0018\u0000*\u0004\b\u0002\u0010\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0018\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\b\u0010\u0013\u001a\u00020\u0010H\u0016J\b\u0010\u0014\u001a\u00020\u0010H\u0016J\"\u0010\u0015\u001a\u00020\u00162\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00020\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00020\u0005R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00020\u0005X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR \u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00020\u0005X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\t¨\u0006\u0017"}, d2 = {"Lorg/videolan/vlc/gui/DiffUtilAdapter$DiffCallback;", "D", "Landroidx/recyclerview/widget/DiffUtil$Callback;", "()V", "newList", "", "getNewList", "()Ljava/util/List;", "setNewList", "(Ljava/util/List;)V", "oldList", "getOldList", "setOldList", "areContentsTheSame", "", "oldItemPosition", "", "newItemPosition", "areItemsTheSame", "getNewListSize", "getOldListSize", "update", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: DiffUtilAdapter.kt */
    public static class DiffCallback<D> extends DiffUtil.Callback {
        public List<? extends D> newList;
        public List<? extends D> oldList;

        public boolean areContentsTheSame(int i, int i2) {
            return true;
        }

        public final List<D> getOldList() {
            List<? extends D> list = this.oldList;
            if (list != null) {
                return list;
            }
            Intrinsics.throwUninitializedPropertyAccessException("oldList");
            return null;
        }

        public final void setOldList(List<? extends D> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.oldList = list;
        }

        public final List<D> getNewList() {
            List<? extends D> list = this.newList;
            if (list != null) {
                return list;
            }
            Intrinsics.throwUninitializedPropertyAccessException("newList");
            return null;
        }

        public final void setNewList(List<? extends D> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.newList = list;
        }

        public final void update(List<? extends D> list, List<? extends D> list2) {
            Intrinsics.checkNotNullParameter(list, "oldList");
            Intrinsics.checkNotNullParameter(list2, "newList");
            setOldList(list);
            setNewList(list2);
        }

        public int getOldListSize() {
            return getOldList().size();
        }

        public int getNewListSize() {
            return getNewList().size();
        }

        public boolean areItemsTheSame(int i, int i2) {
            return Intrinsics.areEqual(getOldList().get(i), getNewList().get(i2));
        }
    }
}
