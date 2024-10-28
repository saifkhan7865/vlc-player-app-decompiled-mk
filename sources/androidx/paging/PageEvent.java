package androidx.paging;

import androidx.paging.LoadState;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u001c\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002:\u0004\u000f\u0010\u0011\u0012B\u0007\b\u0004¢\u0006\u0002\u0010\u0003J;\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\"\u0010\u0005\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006H@ø\u0001\u0000¢\u0006\u0002\u0010\tJK\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0000\"\b\b\u0001\u0010\u000b*\u00020\u00022(\u0010\f\u001a$\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\r0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006H@ø\u0001\u0000¢\u0006\u0002\u0010\tJE\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0000\"\b\b\u0001\u0010\u000b*\u00020\u00022\"\u0010\f\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006H@ø\u0001\u0000¢\u0006\u0002\u0010\t\u0001\u0004\u0013\u0014\u0015\u0016\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"Landroidx/paging/PageEvent;", "T", "", "()V", "filter", "predicate", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "flatMap", "R", "transform", "", "map", "Drop", "Insert", "LoadStateUpdate", "StaticList", "Landroidx/paging/PageEvent$Drop;", "Landroidx/paging/PageEvent$Insert;", "Landroidx/paging/PageEvent$LoadStateUpdate;", "Landroidx/paging/PageEvent$StaticList;", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: PageEvent.kt */
public abstract class PageEvent<T> {
    public /* synthetic */ PageEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    static /* synthetic */ <T> Object filter$suspendImpl(PageEvent<T> pageEvent, Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function2, Continuation<? super PageEvent<T>> continuation) {
        return pageEvent;
    }

    public Object filter(Function2<? super T, ? super Continuation<? super Boolean>, ? extends Object> function2, Continuation<? super PageEvent<T>> continuation) {
        return filter$suspendImpl(this, function2, continuation);
    }

    public <R> Object flatMap(Function2<? super T, ? super Continuation<? super Iterable<? extends R>>, ? extends Object> function2, Continuation<? super PageEvent<R>> continuation) {
        return Intrinsics.checkNotNull(this, "null cannot be cast to non-null type androidx.paging.PageEvent<R of androidx.paging.PageEvent.flatMap>");
    }

    public <R> Object map(Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super PageEvent<R>> continuation) {
        return Intrinsics.checkNotNull(this, "null cannot be cast to non-null type androidx.paging.PageEvent<R of androidx.paging.PageEvent.map>");
    }

    private PageEvent() {
    }

    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001c\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\b\b\u0001\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B+\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\tJ\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0007HÆ\u0003J7\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0002HÖ\u0003J;\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00010\u00032\"\u0010\u0017\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0018H@ø\u0001\u0000¢\u0006\u0002\u0010\u001aJK\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u001c0\u0003\"\b\b\u0002\u0010\u001c*\u00020\u00022(\u0010\u001d\u001a$\b\u0001\u0012\u0004\u0012\u00028\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001c0\u001e0\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0018H@ø\u0001\u0000¢\u0006\u0002\u0010\u001aJ\t\u0010\u001f\u001a\u00020 HÖ\u0001JE\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u001c0\u0003\"\b\b\u0002\u0010\u001c*\u00020\u00022\"\u0010\u001d\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001c0\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0018H@ø\u0001\u0000¢\u0006\u0002\u0010\u001aJ\b\u0010\"\u001a\u00020#H\u0016R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r\u0002\u0004\n\u0002\b\u0019¨\u0006$"}, d2 = {"Landroidx/paging/PageEvent$StaticList;", "T", "", "Landroidx/paging/PageEvent;", "data", "", "sourceLoadStates", "Landroidx/paging/LoadStates;", "mediatorLoadStates", "(Ljava/util/List;Landroidx/paging/LoadStates;Landroidx/paging/LoadStates;)V", "getData", "()Ljava/util/List;", "getMediatorLoadStates", "()Landroidx/paging/LoadStates;", "getSourceLoadStates", "component1", "component2", "component3", "copy", "equals", "", "other", "filter", "predicate", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "flatMap", "R", "transform", "", "hashCode", "", "map", "toString", "", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: PageEvent.kt */
    public static final class StaticList<T> extends PageEvent<T> {
        private final List<T> data;
        private final LoadStates mediatorLoadStates;
        private final LoadStates sourceLoadStates;

        public static /* synthetic */ StaticList copy$default(StaticList staticList, List<T> list, LoadStates loadStates, LoadStates loadStates2, int i, Object obj) {
            if ((i & 1) != 0) {
                list = staticList.data;
            }
            if ((i & 2) != 0) {
                loadStates = staticList.sourceLoadStates;
            }
            if ((i & 4) != 0) {
                loadStates2 = staticList.mediatorLoadStates;
            }
            return staticList.copy(list, loadStates, loadStates2);
        }

        public final List<T> component1() {
            return this.data;
        }

        public final LoadStates component2() {
            return this.sourceLoadStates;
        }

        public final LoadStates component3() {
            return this.mediatorLoadStates;
        }

        public final StaticList<T> copy(List<? extends T> list, LoadStates loadStates, LoadStates loadStates2) {
            Intrinsics.checkNotNullParameter(list, "data");
            return new StaticList<>(list, loadStates, loadStates2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof StaticList)) {
                return false;
            }
            StaticList staticList = (StaticList) obj;
            return Intrinsics.areEqual((Object) this.data, (Object) staticList.data) && Intrinsics.areEqual((Object) this.sourceLoadStates, (Object) staticList.sourceLoadStates) && Intrinsics.areEqual((Object) this.mediatorLoadStates, (Object) staticList.mediatorLoadStates);
        }

        public int hashCode() {
            int hashCode = this.data.hashCode() * 31;
            LoadStates loadStates = this.sourceLoadStates;
            int i = 0;
            int hashCode2 = (hashCode + (loadStates == null ? 0 : loadStates.hashCode())) * 31;
            LoadStates loadStates2 = this.mediatorLoadStates;
            if (loadStates2 != null) {
                i = loadStates2.hashCode();
            }
            return hashCode2 + i;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ StaticList(List list, LoadStates loadStates, LoadStates loadStates2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(list, (i & 2) != 0 ? null : loadStates, (i & 4) != 0 ? null : loadStates2);
        }

        public final List<T> getData() {
            return this.data;
        }

        public final LoadStates getSourceLoadStates() {
            return this.sourceLoadStates;
        }

        public final LoadStates getMediatorLoadStates() {
            return this.mediatorLoadStates;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public StaticList(List<? extends T> list, LoadStates loadStates, LoadStates loadStates2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(list, "data");
            this.data = list;
            this.sourceLoadStates = loadStates;
            this.mediatorLoadStates = loadStates2;
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x0046  */
        /* JADX WARNING: Removed duplicated region for block: B:15:0x0069  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public <R> java.lang.Object map(kotlin.jvm.functions.Function2<? super T, ? super kotlin.coroutines.Continuation<? super R>, ? extends java.lang.Object> r9, kotlin.coroutines.Continuation<? super androidx.paging.PageEvent<R>> r10) {
            /*
                r8 = this;
                boolean r0 = r10 instanceof androidx.paging.PageEvent$StaticList$map$1
                if (r0 == 0) goto L_0x0014
                r0 = r10
                androidx.paging.PageEvent$StaticList$map$1 r0 = (androidx.paging.PageEvent$StaticList$map$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L_0x0014
                int r10 = r0.label
                int r10 = r10 - r2
                r0.label = r10
                goto L_0x0019
            L_0x0014:
                androidx.paging.PageEvent$StaticList$map$1 r0 = new androidx.paging.PageEvent$StaticList$map$1
                r0.<init>(r8, r10)
            L_0x0019:
                java.lang.Object r10 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L_0x0046
                if (r2 != r3) goto L_0x003e
                java.lang.Object r9 = r0.L$4
                java.util.Collection r9 = (java.util.Collection) r9
                java.lang.Object r2 = r0.L$3
                java.util.Iterator r2 = (java.util.Iterator) r2
                java.lang.Object r4 = r0.L$2
                java.util.Collection r4 = (java.util.Collection) r4
                java.lang.Object r5 = r0.L$1
                kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
                java.lang.Object r6 = r0.L$0
                androidx.paging.PageEvent$StaticList r6 = (androidx.paging.PageEvent.StaticList) r6
                kotlin.ResultKt.throwOnFailure(r10)
                goto L_0x0083
            L_0x003e:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r10)
                throw r9
            L_0x0046:
                kotlin.ResultKt.throwOnFailure(r10)
                java.util.List<T> r10 = r8.data
                java.lang.Iterable r10 = (java.lang.Iterable) r10
                java.util.ArrayList r2 = new java.util.ArrayList
                r4 = 10
                int r4 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r10, r4)
                r2.<init>(r4)
                java.util.Collection r2 = (java.util.Collection) r2
                java.util.Iterator r10 = r10.iterator()
                r6 = r8
                r7 = r10
                r10 = r9
                r9 = r2
                r2 = r7
            L_0x0063:
                boolean r4 = r2.hasNext()
                if (r4 == 0) goto L_0x0089
                java.lang.Object r4 = r2.next()
                r0.L$0 = r6
                r0.L$1 = r10
                r0.L$2 = r9
                r0.L$3 = r2
                r0.L$4 = r9
                r0.label = r3
                java.lang.Object r4 = r10.invoke(r4, r0)
                if (r4 != r1) goto L_0x0080
                return r1
            L_0x0080:
                r5 = r10
                r10 = r4
                r4 = r9
            L_0x0083:
                r9.add(r10)
                r9 = r4
                r10 = r5
                goto L_0x0063
            L_0x0089:
                java.util.List r9 = (java.util.List) r9
                androidx.paging.LoadStates r10 = r6.sourceLoadStates
                androidx.paging.LoadStates r0 = r6.mediatorLoadStates
                androidx.paging.PageEvent$StaticList r1 = new androidx.paging.PageEvent$StaticList
                r1.<init>(r9, r10, r0)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageEvent.StaticList.map(kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x0042  */
        /* JADX WARNING: Removed duplicated region for block: B:15:0x005e  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public <R> java.lang.Object flatMap(kotlin.jvm.functions.Function2<? super T, ? super kotlin.coroutines.Continuation<? super java.lang.Iterable<? extends R>>, ? extends java.lang.Object> r8, kotlin.coroutines.Continuation<? super androidx.paging.PageEvent<R>> r9) {
            /*
                r7 = this;
                boolean r0 = r9 instanceof androidx.paging.PageEvent$StaticList$flatMap$1
                if (r0 == 0) goto L_0x0014
                r0 = r9
                androidx.paging.PageEvent$StaticList$flatMap$1 r0 = (androidx.paging.PageEvent$StaticList$flatMap$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L_0x0014
                int r9 = r0.label
                int r9 = r9 - r2
                r0.label = r9
                goto L_0x0019
            L_0x0014:
                androidx.paging.PageEvent$StaticList$flatMap$1 r0 = new androidx.paging.PageEvent$StaticList$flatMap$1
                r0.<init>(r7, r9)
            L_0x0019:
                java.lang.Object r9 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L_0x0042
                if (r2 != r3) goto L_0x003a
                java.lang.Object r8 = r0.L$3
                java.util.Iterator r8 = (java.util.Iterator) r8
                java.lang.Object r2 = r0.L$2
                java.util.Collection r2 = (java.util.Collection) r2
                java.lang.Object r4 = r0.L$1
                kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
                java.lang.Object r5 = r0.L$0
                androidx.paging.PageEvent$StaticList r5 = (androidx.paging.PageEvent.StaticList) r5
                kotlin.ResultKt.throwOnFailure(r9)
                goto L_0x0076
            L_0x003a:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r9)
                throw r8
            L_0x0042:
                kotlin.ResultKt.throwOnFailure(r9)
                java.util.List<T> r9 = r7.data
                java.lang.Iterable r9 = (java.lang.Iterable) r9
                java.util.ArrayList r2 = new java.util.ArrayList
                r2.<init>()
                java.util.Collection r2 = (java.util.Collection) r2
                java.util.Iterator r9 = r9.iterator()
                r5 = r7
                r6 = r9
                r9 = r8
                r8 = r6
            L_0x0058:
                boolean r4 = r8.hasNext()
                if (r4 == 0) goto L_0x007d
                java.lang.Object r4 = r8.next()
                r0.L$0 = r5
                r0.L$1 = r9
                r0.L$2 = r2
                r0.L$3 = r8
                r0.label = r3
                java.lang.Object r4 = r9.invoke(r4, r0)
                if (r4 != r1) goto L_0x0073
                return r1
            L_0x0073:
                r6 = r4
                r4 = r9
                r9 = r6
            L_0x0076:
                java.lang.Iterable r9 = (java.lang.Iterable) r9
                kotlin.collections.CollectionsKt.addAll(r2, r9)
                r9 = r4
                goto L_0x0058
            L_0x007d:
                java.util.List r2 = (java.util.List) r2
                androidx.paging.LoadStates r8 = r5.sourceLoadStates
                androidx.paging.LoadStates r9 = r5.mediatorLoadStates
                androidx.paging.PageEvent$StaticList r0 = new androidx.paging.PageEvent$StaticList
                r0.<init>(r2, r8, r9)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageEvent.StaticList.flatMap(kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x0044  */
        /* JADX WARNING: Removed duplicated region for block: B:15:0x005f  */
        /* JADX WARNING: Removed duplicated region for block: B:21:0x0082  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object filter(kotlin.jvm.functions.Function2<? super T, ? super kotlin.coroutines.Continuation<? super java.lang.Boolean>, ? extends java.lang.Object> r9, kotlin.coroutines.Continuation<? super androidx.paging.PageEvent<T>> r10) {
            /*
                r8 = this;
                boolean r0 = r10 instanceof androidx.paging.PageEvent$StaticList$filter$1
                if (r0 == 0) goto L_0x0014
                r0 = r10
                androidx.paging.PageEvent$StaticList$filter$1 r0 = (androidx.paging.PageEvent$StaticList$filter$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L_0x0014
                int r10 = r0.label
                int r10 = r10 - r2
                r0.label = r10
                goto L_0x0019
            L_0x0014:
                androidx.paging.PageEvent$StaticList$filter$1 r0 = new androidx.paging.PageEvent$StaticList$filter$1
                r0.<init>(r8, r10)
            L_0x0019:
                java.lang.Object r10 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L_0x0044
                if (r2 != r3) goto L_0x003c
                java.lang.Object r9 = r0.L$4
                java.lang.Object r2 = r0.L$3
                java.util.Iterator r2 = (java.util.Iterator) r2
                java.lang.Object r4 = r0.L$2
                java.util.Collection r4 = (java.util.Collection) r4
                java.lang.Object r5 = r0.L$1
                kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
                java.lang.Object r6 = r0.L$0
                androidx.paging.PageEvent$StaticList r6 = (androidx.paging.PageEvent.StaticList) r6
                kotlin.ResultKt.throwOnFailure(r10)
                goto L_0x007a
            L_0x003c:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r10)
                throw r9
            L_0x0044:
                kotlin.ResultKt.throwOnFailure(r10)
                java.util.List<T> r10 = r8.data
                java.lang.Iterable r10 = (java.lang.Iterable) r10
                java.util.ArrayList r2 = new java.util.ArrayList
                r2.<init>()
                java.util.Collection r2 = (java.util.Collection) r2
                java.util.Iterator r10 = r10.iterator()
                r6 = r8
                r4 = r2
                r2 = r10
            L_0x0059:
                boolean r10 = r2.hasNext()
                if (r10 == 0) goto L_0x0087
                java.lang.Object r10 = r2.next()
                r0.L$0 = r6
                r0.L$1 = r9
                r0.L$2 = r4
                r0.L$3 = r2
                r0.L$4 = r10
                r0.label = r3
                java.lang.Object r5 = r9.invoke(r10, r0)
                if (r5 != r1) goto L_0x0076
                return r1
            L_0x0076:
                r7 = r5
                r5 = r9
                r9 = r10
                r10 = r7
            L_0x007a:
                java.lang.Boolean r10 = (java.lang.Boolean) r10
                boolean r10 = r10.booleanValue()
                if (r10 == 0) goto L_0x0085
                r4.add(r9)
            L_0x0085:
                r9 = r5
                goto L_0x0059
            L_0x0087:
                java.util.List r4 = (java.util.List) r4
                androidx.paging.LoadStates r9 = r6.sourceLoadStates
                androidx.paging.LoadStates r10 = r6.mediatorLoadStates
                androidx.paging.PageEvent$StaticList r0 = new androidx.paging.PageEvent$StaticList
                r0.<init>(r4, r9, r10)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageEvent.StaticList.filter(kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
        }

        public String toString() {
            LoadStates loadStates = this.mediatorLoadStates;
            String str = "PageEvent.StaticList with " + this.data.size() + " items (\n                    |   first item: " + CollectionsKt.firstOrNull(this.data) + "\n                    |   last item: " + CollectionsKt.lastOrNull(this.data) + "\n                    |   sourceLoadStates: " + this.sourceLoadStates + "\n                    ";
            if (loadStates != null) {
                str = str + "|   mediatorLoadStates: " + loadStates + 10;
            }
            return StringsKt.trimMargin$default(str + "|)", (String) null, 1, (Object) null);
        }
    }

    @Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001c\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\b\u0018\u0000 5*\b\b\u0001\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u00015BG\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000fJ\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\u0015\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\b0\u0007HÆ\u0003J\t\u0010\u001c\u001a\u00020\nHÆ\u0003J\t\u0010\u001d\u001a\u00020\nHÆ\u0003J\t\u0010\u001e\u001a\u00020\rHÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\rHÆ\u0003JY\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\rHÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0002HÖ\u0003J;\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00010\u00032\"\u0010%\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0'\u0012\u0006\u0012\u0004\u0018\u00010\u00020&H@ø\u0001\u0000¢\u0006\u0002\u0010(JK\u0010)\u001a\b\u0012\u0004\u0012\u0002H*0\u0003\"\b\b\u0002\u0010**\u00020\u00022(\u0010+\u001a$\b\u0001\u0012\u0004\u0012\u00028\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H*0,0'\u0012\u0006\u0012\u0004\u0018\u00010\u00020&H@ø\u0001\u0000¢\u0006\u0002\u0010(J\t\u0010-\u001a\u00020\nHÖ\u0001JE\u0010.\u001a\b\u0012\u0004\u0012\u0002H*0\u0003\"\b\b\u0002\u0010**\u00020\u00022\"\u0010+\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H*0'\u0012\u0006\u0012\u0004\u0018\u00010\u00020&H@ø\u0001\u0000¢\u0006\u0002\u0010(J9\u0010/\u001a\b\u0012\u0004\u0012\u0002H*0\u0000\"\b\b\u0002\u0010**\u00020\u00022\u001e\u0010+\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H*0\b00H\bJ\b\u00101\u001a\u000202H\u0016JM\u00103\u001a\b\u0012\u0004\u0012\u0002H*0\u0000\"\b\b\u0002\u0010**\u00020\u00022*\u0010+\u001a&\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\b0\u0007\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H*0\b0\u000700H\bø\u0001\u0001¢\u0006\u0002\b4R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001d\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013\u0002\u000b\n\u0002\b\u0019\n\u0005\b20\u0001¨\u00066"}, d2 = {"Landroidx/paging/PageEvent$Insert;", "T", "", "Landroidx/paging/PageEvent;", "loadType", "Landroidx/paging/LoadType;", "pages", "", "Landroidx/paging/TransformablePage;", "placeholdersBefore", "", "placeholdersAfter", "sourceLoadStates", "Landroidx/paging/LoadStates;", "mediatorLoadStates", "(Landroidx/paging/LoadType;Ljava/util/List;IILandroidx/paging/LoadStates;Landroidx/paging/LoadStates;)V", "getLoadType", "()Landroidx/paging/LoadType;", "getMediatorLoadStates", "()Landroidx/paging/LoadStates;", "getPages", "()Ljava/util/List;", "getPlaceholdersAfter", "()I", "getPlaceholdersBefore", "getSourceLoadStates", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "filter", "predicate", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "flatMap", "R", "transform", "", "hashCode", "map", "mapPages", "Lkotlin/Function1;", "toString", "", "transformPages", "transformPages$paging_common", "Companion", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: PageEvent.kt */
    public static final class Insert<T> extends PageEvent<T> {
        public static final Companion Companion;
        /* access modifiers changed from: private */
        public static final Insert<Object> EMPTY_REFRESH_LOCAL;
        private final LoadType loadType;
        private final LoadStates mediatorLoadStates;
        private final List<TransformablePage<T>> pages;
        private final int placeholdersAfter;
        private final int placeholdersBefore;
        private final LoadStates sourceLoadStates;

        public /* synthetic */ Insert(LoadType loadType2, List list, int i, int i2, LoadStates loadStates, LoadStates loadStates2, DefaultConstructorMarker defaultConstructorMarker) {
            this(loadType2, list, i, i2, loadStates, loadStates2);
        }

        public static /* synthetic */ Insert copy$default(Insert insert, LoadType loadType2, List<TransformablePage<T>> list, int i, int i2, LoadStates loadStates, LoadStates loadStates2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                loadType2 = insert.loadType;
            }
            if ((i3 & 2) != 0) {
                list = insert.pages;
            }
            List<TransformablePage<T>> list2 = list;
            if ((i3 & 4) != 0) {
                i = insert.placeholdersBefore;
            }
            int i4 = i;
            if ((i3 & 8) != 0) {
                i2 = insert.placeholdersAfter;
            }
            int i5 = i2;
            if ((i3 & 16) != 0) {
                loadStates = insert.sourceLoadStates;
            }
            LoadStates loadStates3 = loadStates;
            if ((i3 & 32) != 0) {
                loadStates2 = insert.mediatorLoadStates;
            }
            return insert.copy(loadType2, list2, i4, i5, loadStates3, loadStates2);
        }

        public final LoadType component1() {
            return this.loadType;
        }

        public final List<TransformablePage<T>> component2() {
            return this.pages;
        }

        public final int component3() {
            return this.placeholdersBefore;
        }

        public final int component4() {
            return this.placeholdersAfter;
        }

        public final LoadStates component5() {
            return this.sourceLoadStates;
        }

        public final LoadStates component6() {
            return this.mediatorLoadStates;
        }

        public final Insert<T> copy(LoadType loadType2, List<TransformablePage<T>> list, int i, int i2, LoadStates loadStates, LoadStates loadStates2) {
            Intrinsics.checkNotNullParameter(loadType2, "loadType");
            Intrinsics.checkNotNullParameter(list, "pages");
            Intrinsics.checkNotNullParameter(loadStates, "sourceLoadStates");
            return new Insert(loadType2, list, i, i2, loadStates, loadStates2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Insert)) {
                return false;
            }
            Insert insert = (Insert) obj;
            return this.loadType == insert.loadType && Intrinsics.areEqual((Object) this.pages, (Object) insert.pages) && this.placeholdersBefore == insert.placeholdersBefore && this.placeholdersAfter == insert.placeholdersAfter && Intrinsics.areEqual((Object) this.sourceLoadStates, (Object) insert.sourceLoadStates) && Intrinsics.areEqual((Object) this.mediatorLoadStates, (Object) insert.mediatorLoadStates);
        }

        public int hashCode() {
            int hashCode = ((((((((this.loadType.hashCode() * 31) + this.pages.hashCode()) * 31) + this.placeholdersBefore) * 31) + this.placeholdersAfter) * 31) + this.sourceLoadStates.hashCode()) * 31;
            LoadStates loadStates = this.mediatorLoadStates;
            return hashCode + (loadStates == null ? 0 : loadStates.hashCode());
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ Insert(LoadType loadType2, List list, int i, int i2, LoadStates loadStates, LoadStates loadStates2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(loadType2, list, i, i2, loadStates, (i3 & 32) != 0 ? null : loadStates2);
        }

        public final LoadType getLoadType() {
            return this.loadType;
        }

        public final List<TransformablePage<T>> getPages() {
            return this.pages;
        }

        public final int getPlaceholdersBefore() {
            return this.placeholdersBefore;
        }

        public final int getPlaceholdersAfter() {
            return this.placeholdersAfter;
        }

        public final LoadStates getSourceLoadStates() {
            return this.sourceLoadStates;
        }

        public final LoadStates getMediatorLoadStates() {
            return this.mediatorLoadStates;
        }

        private Insert(LoadType loadType2, List<TransformablePage<T>> list, int i, int i2, LoadStates loadStates, LoadStates loadStates2) {
            super((DefaultConstructorMarker) null);
            this.loadType = loadType2;
            this.pages = list;
            this.placeholdersBefore = i;
            this.placeholdersAfter = i2;
            this.sourceLoadStates = loadStates;
            this.mediatorLoadStates = loadStates2;
            if (loadType2 != LoadType.APPEND && i < 0) {
                throw new IllegalArgumentException(("Prepend insert defining placeholdersBefore must be > 0, but was " + i).toString());
            } else if (loadType2 != LoadType.PREPEND && i2 < 0) {
                throw new IllegalArgumentException(("Append insert defining placeholdersAfter must be > 0, but was " + i2).toString());
            } else if (loadType2 == LoadType.REFRESH && !(!list.isEmpty())) {
                throw new IllegalArgumentException("Cannot create a REFRESH Insert event with no TransformablePages as this could permanently stall pagination. Note that this check does not prevent empty LoadResults and is instead usually an indication of an internal error in Paging itself.".toString());
            }
        }

        public final <R> Insert<R> transformPages$paging_common(Function1<? super List<TransformablePage<T>>, ? extends List<TransformablePage<R>>> function1) {
            Intrinsics.checkNotNullParameter(function1, "transform");
            return new Insert(getLoadType(), (List) function1.invoke(getPages()), getPlaceholdersBefore(), getPlaceholdersAfter(), getSourceLoadStates(), getMediatorLoadStates(), (DefaultConstructorMarker) null);
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x006e  */
        /* JADX WARNING: Removed duplicated region for block: B:15:0x0094  */
        /* JADX WARNING: Removed duplicated region for block: B:18:0x00bf  */
        /* JADX WARNING: Removed duplicated region for block: B:21:0x00e3  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x002c  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public <R> java.lang.Object map(kotlin.jvm.functions.Function2<? super T, ? super kotlin.coroutines.Continuation<? super R>, ? extends java.lang.Object> r18, kotlin.coroutines.Continuation<? super androidx.paging.PageEvent<R>> r19) {
            /*
                r17 = this;
                r0 = r19
                boolean r1 = r0 instanceof androidx.paging.PageEvent$Insert$map$1
                if (r1 == 0) goto L_0x0018
                r1 = r0
                androidx.paging.PageEvent$Insert$map$1 r1 = (androidx.paging.PageEvent$Insert$map$1) r1
                int r2 = r1.label
                r3 = -2147483648(0xffffffff80000000, float:-0.0)
                r2 = r2 & r3
                if (r2 == 0) goto L_0x0018
                int r0 = r1.label
                int r0 = r0 - r3
                r1.label = r0
                r2 = r17
                goto L_0x001f
            L_0x0018:
                androidx.paging.PageEvent$Insert$map$1 r1 = new androidx.paging.PageEvent$Insert$map$1
                r2 = r17
                r1.<init>(r2, r0)
            L_0x001f:
                java.lang.Object r0 = r1.result
                java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r4 = r1.label
                r5 = 10
                r6 = 1
                if (r4 == 0) goto L_0x006e
                if (r4 != r6) goto L_0x0066
                java.lang.Object r4 = r1.L$10
                java.util.Collection r4 = (java.util.Collection) r4
                java.lang.Object r7 = r1.L$9
                java.util.Collection r7 = (java.util.Collection) r7
                java.lang.Object r8 = r1.L$8
                java.util.Iterator r8 = (java.util.Iterator) r8
                java.lang.Object r9 = r1.L$7
                java.util.Collection r9 = (java.util.Collection) r9
                java.lang.Object r10 = r1.L$6
                int[] r10 = (int[]) r10
                java.lang.Object r11 = r1.L$5
                androidx.paging.TransformablePage r11 = (androidx.paging.TransformablePage) r11
                java.lang.Object r12 = r1.L$4
                java.util.Iterator r12 = (java.util.Iterator) r12
                java.lang.Object r13 = r1.L$3
                java.util.Collection r13 = (java.util.Collection) r13
                java.lang.Object r14 = r1.L$2
                androidx.paging.LoadType r14 = (androidx.paging.LoadType) r14
                java.lang.Object r15 = r1.L$1
                androidx.paging.PageEvent$Insert r15 = (androidx.paging.PageEvent.Insert) r15
                java.lang.Object r6 = r1.L$0
                kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
                kotlin.ResultKt.throwOnFailure(r0)
                r16 = r11
                r11 = r8
                r8 = r14
                r14 = r9
                r9 = r15
                r15 = 1
                goto L_0x00eb
            L_0x0066:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
                r0.<init>(r1)
                throw r0
            L_0x006e:
                kotlin.ResultKt.throwOnFailure(r0)
                androidx.paging.LoadType r0 = r17.getLoadType()
                java.util.List r4 = r17.getPages()
                java.lang.Iterable r4 = (java.lang.Iterable) r4
                java.util.ArrayList r6 = new java.util.ArrayList
                int r7 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r4, r5)
                r6.<init>(r7)
                java.util.Collection r6 = (java.util.Collection) r6
                java.util.Iterator r4 = r4.iterator()
                r7 = r0
                r8 = r2
                r0 = r18
            L_0x008e:
                boolean r9 = r4.hasNext()
                if (r9 == 0) goto L_0x010d
                java.lang.Object r9 = r4.next()
                androidx.paging.TransformablePage r9 = (androidx.paging.TransformablePage) r9
                int[] r10 = r9.getOriginalPageOffsets()
                java.util.List r11 = r9.getData()
                java.lang.Iterable r11 = (java.lang.Iterable) r11
                java.util.ArrayList r12 = new java.util.ArrayList
                int r13 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r11, r5)
                r12.<init>(r13)
                java.util.Collection r12 = (java.util.Collection) r12
                java.util.Iterator r11 = r11.iterator()
                r13 = r9
                r9 = r8
                r8 = r7
                r7 = r6
                r6 = r4
                r4 = r7
            L_0x00b9:
                boolean r14 = r11.hasNext()
                if (r14 == 0) goto L_0x00f5
                java.lang.Object r14 = r11.next()
                r1.L$0 = r0
                r1.L$1 = r9
                r1.L$2 = r8
                r1.L$3 = r7
                r1.L$4 = r6
                r1.L$5 = r13
                r1.L$6 = r10
                r1.L$7 = r12
                r1.L$8 = r11
                r1.L$9 = r12
                r1.L$10 = r4
                r15 = 1
                r1.label = r15
                java.lang.Object r14 = r0.invoke(r14, r1)
                if (r14 != r3) goto L_0x00e3
                return r3
            L_0x00e3:
                r16 = r13
                r13 = r7
                r7 = r12
                r12 = r6
                r6 = r0
                r0 = r14
                r14 = r7
            L_0x00eb:
                r7.add(r0)
                r0 = r6
                r6 = r12
                r7 = r13
                r12 = r14
                r13 = r16
                goto L_0x00b9
            L_0x00f5:
                r15 = 1
                java.util.List r12 = (java.util.List) r12
                int r11 = r13.getHintOriginalPageOffset()
                java.util.List r13 = r13.getHintOriginalIndices()
                androidx.paging.TransformablePage r14 = new androidx.paging.TransformablePage
                r14.<init>(r10, r12, r11, r13)
                r4.add(r14)
                r4 = r6
                r6 = r7
                r7 = r8
                r8 = r9
                goto L_0x008e
            L_0x010d:
                r0 = r6
                java.util.List r0 = (java.util.List) r0
                int r9 = r8.getPlaceholdersBefore()
                int r10 = r8.getPlaceholdersAfter()
                androidx.paging.LoadStates r11 = r8.getSourceLoadStates()
                androidx.paging.LoadStates r12 = r8.getMediatorLoadStates()
                androidx.paging.PageEvent$Insert r1 = new androidx.paging.PageEvent$Insert
                r13 = 0
                r6 = r1
                r8 = r0
                r6.<init>(r7, r8, r9, r10, r11, r12, r13)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageEvent.Insert.map(kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x0078  */
        /* JADX WARNING: Removed duplicated region for block: B:15:0x00a1  */
        /* JADX WARNING: Removed duplicated region for block: B:18:0x00cd  */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x00d5  */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x0101  */
        /* JADX WARNING: Removed duplicated region for block: B:27:0x0119  */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x012d A[LOOP:0: B:28:0x0123->B:30:0x012d, LOOP_END] */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public <R> java.lang.Object flatMap(kotlin.jvm.functions.Function2<? super T, ? super kotlin.coroutines.Continuation<? super java.lang.Iterable<? extends R>>, ? extends java.lang.Object> r19, kotlin.coroutines.Continuation<? super androidx.paging.PageEvent<R>> r20) {
            /*
                r18 = this;
                r0 = r20
                boolean r1 = r0 instanceof androidx.paging.PageEvent$Insert$flatMap$1
                if (r1 == 0) goto L_0x0018
                r1 = r0
                androidx.paging.PageEvent$Insert$flatMap$1 r1 = (androidx.paging.PageEvent$Insert$flatMap$1) r1
                int r2 = r1.label
                r3 = -2147483648(0xffffffff80000000, float:-0.0)
                r2 = r2 & r3
                if (r2 == 0) goto L_0x0018
                int r0 = r1.label
                int r0 = r0 - r3
                r1.label = r0
                r2 = r18
                goto L_0x001f
            L_0x0018:
                androidx.paging.PageEvent$Insert$flatMap$1 r1 = new androidx.paging.PageEvent$Insert$flatMap$1
                r2 = r18
                r1.<init>(r2, r0)
            L_0x001f:
                java.lang.Object r0 = r1.result
                java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r4 = r1.label
                r5 = 1
                if (r4 == 0) goto L_0x0078
                if (r4 != r5) goto L_0x0070
                int r4 = r1.I$1
                int r6 = r1.I$0
                java.lang.Object r7 = r1.L$10
                java.util.Collection r7 = (java.util.Collection) r7
                java.lang.Object r8 = r1.L$9
                java.util.Collection r8 = (java.util.Collection) r8
                java.lang.Object r9 = r1.L$8
                java.util.Iterator r9 = (java.util.Iterator) r9
                java.lang.Object r10 = r1.L$7
                java.util.List r10 = (java.util.List) r10
                java.lang.Object r11 = r1.L$6
                java.util.List r11 = (java.util.List) r11
                java.lang.Object r12 = r1.L$5
                androidx.paging.TransformablePage r12 = (androidx.paging.TransformablePage) r12
                java.lang.Object r13 = r1.L$4
                java.util.Iterator r13 = (java.util.Iterator) r13
                java.lang.Object r14 = r1.L$3
                java.util.Collection r14 = (java.util.Collection) r14
                java.lang.Object r15 = r1.L$2
                androidx.paging.LoadType r15 = (androidx.paging.LoadType) r15
                java.lang.Object r5 = r1.L$1
                androidx.paging.PageEvent$Insert r5 = (androidx.paging.PageEvent.Insert) r5
                java.lang.Object r2 = r1.L$0
                kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
                kotlin.ResultKt.throwOnFailure(r0)
                r16 = r12
                r12 = r10
                r10 = r9
                r9 = r11
                r11 = r4
                r4 = r1
                r1 = r2
                r2 = 1
                r17 = r6
                r6 = r5
                r5 = r15
                r15 = r17
                goto L_0x010e
            L_0x0070:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
                r0.<init>(r1)
                throw r0
            L_0x0078:
                kotlin.ResultKt.throwOnFailure(r0)
                androidx.paging.LoadType r0 = r18.getLoadType()
                java.util.List r2 = r18.getPages()
                java.lang.Iterable r2 = (java.lang.Iterable) r2
                java.util.ArrayList r4 = new java.util.ArrayList
                r5 = 10
                int r5 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r2, r5)
                r4.<init>(r5)
                java.util.Collection r4 = (java.util.Collection) r4
                java.util.Iterator r2 = r2.iterator()
                r6 = r18
                r5 = r0
                r0 = r19
            L_0x009b:
                boolean r7 = r2.hasNext()
                if (r7 == 0) goto L_0x0157
                java.lang.Object r7 = r2.next()
                androidx.paging.TransformablePage r7 = (androidx.paging.TransformablePage) r7
                java.util.ArrayList r8 = new java.util.ArrayList
                r8.<init>()
                java.util.List r8 = (java.util.List) r8
                java.util.ArrayList r9 = new java.util.ArrayList
                r9.<init>()
                java.util.List r9 = (java.util.List) r9
                java.util.List r10 = r7.getData()
                java.lang.Iterable r10 = (java.lang.Iterable) r10
                java.util.Iterator r10 = r10.iterator()
                r11 = 0
                r12 = r7
                r7 = r4
                r17 = r10
                r10 = r9
                r9 = r17
            L_0x00c7:
                boolean r13 = r9.hasNext()
                if (r13 == 0) goto L_0x0140
                java.lang.Object r13 = r9.next()
                int r14 = r11 + 1
                if (r11 >= 0) goto L_0x00d8
                kotlin.collections.CollectionsKt.throwIndexOverflow()
            L_0x00d8:
                r15 = r8
                java.util.Collection r15 = (java.util.Collection) r15
                r1.L$0 = r0
                r1.L$1 = r6
                r1.L$2 = r5
                r1.L$3 = r4
                r1.L$4 = r2
                r1.L$5 = r12
                r1.L$6 = r8
                r1.L$7 = r10
                r1.L$8 = r9
                r1.L$9 = r15
                r1.L$10 = r7
                r1.I$0 = r14
                r1.I$1 = r11
                r19 = r2
                r2 = 1
                r1.label = r2
                java.lang.Object r13 = r0.invoke(r13, r1)
                if (r13 != r3) goto L_0x0101
                return r3
            L_0x0101:
                r16 = r12
                r12 = r10
                r10 = r9
                r9 = r8
                r8 = r15
                r15 = r14
                r14 = r4
                r4 = r1
                r1 = r0
                r0 = r13
                r13 = r19
            L_0x010e:
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                kotlin.collections.CollectionsKt.addAll(r8, r0)
                java.util.List r0 = r16.getHintOriginalIndices()
                if (r0 == 0) goto L_0x0123
                java.lang.Object r0 = r0.get(r11)
                java.lang.Number r0 = (java.lang.Number) r0
                int r11 = r0.intValue()
            L_0x0123:
                int r0 = r12.size()
                int r8 = r9.size()
                if (r0 >= r8) goto L_0x0135
                java.lang.Integer r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r11)
                r12.add(r0)
                goto L_0x0123
            L_0x0135:
                r0 = r1
                r1 = r4
                r8 = r9
                r9 = r10
                r10 = r12
                r2 = r13
                r4 = r14
                r11 = r15
                r12 = r16
                goto L_0x00c7
            L_0x0140:
                r19 = r2
                r2 = 1
                androidx.paging.TransformablePage r9 = new androidx.paging.TransformablePage
                int[] r11 = r12.getOriginalPageOffsets()
                int r12 = r12.getHintOriginalPageOffset()
                r9.<init>(r11, r8, r12, r10)
                r7.add(r9)
                r2 = r19
                goto L_0x009b
            L_0x0157:
                r0 = r4
                java.util.List r0 = (java.util.List) r0
                int r7 = r6.getPlaceholdersBefore()
                int r8 = r6.getPlaceholdersAfter()
                androidx.paging.LoadStates r9 = r6.getSourceLoadStates()
                androidx.paging.LoadStates r10 = r6.getMediatorLoadStates()
                androidx.paging.PageEvent$Insert r1 = new androidx.paging.PageEvent$Insert
                r11 = 0
                r4 = r1
                r6 = r0
                r4.<init>(r5, r6, r7, r8, r9, r10, r11)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageEvent.Insert.flatMap(kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x0075  */
        /* JADX WARNING: Removed duplicated region for block: B:15:0x009e  */
        /* JADX WARNING: Removed duplicated region for block: B:18:0x00ca  */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x00d2  */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x00f9  */
        /* JADX WARNING: Removed duplicated region for block: B:27:0x010e  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object filter(kotlin.jvm.functions.Function2<? super T, ? super kotlin.coroutines.Continuation<? super java.lang.Boolean>, ? extends java.lang.Object> r19, kotlin.coroutines.Continuation<? super androidx.paging.PageEvent<T>> r20) {
            /*
                r18 = this;
                r0 = r20
                boolean r1 = r0 instanceof androidx.paging.PageEvent$Insert$filter$1
                if (r1 == 0) goto L_0x0018
                r1 = r0
                androidx.paging.PageEvent$Insert$filter$1 r1 = (androidx.paging.PageEvent$Insert$filter$1) r1
                int r2 = r1.label
                r3 = -2147483648(0xffffffff80000000, float:-0.0)
                r2 = r2 & r3
                if (r2 == 0) goto L_0x0018
                int r0 = r1.label
                int r0 = r0 - r3
                r1.label = r0
                r2 = r18
                goto L_0x001f
            L_0x0018:
                androidx.paging.PageEvent$Insert$filter$1 r1 = new androidx.paging.PageEvent$Insert$filter$1
                r2 = r18
                r1.<init>(r2, r0)
            L_0x001f:
                java.lang.Object r0 = r1.result
                java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r4 = r1.label
                r5 = 1
                if (r4 == 0) goto L_0x0075
                if (r4 != r5) goto L_0x006d
                int r4 = r1.I$1
                int r6 = r1.I$0
                java.lang.Object r7 = r1.L$10
                java.util.Collection r7 = (java.util.Collection) r7
                java.lang.Object r8 = r1.L$9
                java.lang.Object r9 = r1.L$8
                java.util.Iterator r9 = (java.util.Iterator) r9
                java.lang.Object r10 = r1.L$7
                java.util.List r10 = (java.util.List) r10
                java.lang.Object r11 = r1.L$6
                java.util.List r11 = (java.util.List) r11
                java.lang.Object r12 = r1.L$5
                androidx.paging.TransformablePage r12 = (androidx.paging.TransformablePage) r12
                java.lang.Object r13 = r1.L$4
                java.util.Iterator r13 = (java.util.Iterator) r13
                java.lang.Object r14 = r1.L$3
                java.util.Collection r14 = (java.util.Collection) r14
                java.lang.Object r15 = r1.L$2
                androidx.paging.LoadType r15 = (androidx.paging.LoadType) r15
                java.lang.Object r5 = r1.L$1
                androidx.paging.PageEvent$Insert r5 = (androidx.paging.PageEvent.Insert) r5
                java.lang.Object r2 = r1.L$0
                kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
                kotlin.ResultKt.throwOnFailure(r0)
                r16 = r6
                r6 = r5
                r5 = r15
                r15 = r12
                r12 = r16
                r17 = r9
                r9 = r8
                r8 = r11
                r11 = r10
                r10 = r17
                goto L_0x0106
            L_0x006d:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
                r0.<init>(r1)
                throw r0
            L_0x0075:
                kotlin.ResultKt.throwOnFailure(r0)
                androidx.paging.LoadType r0 = r18.getLoadType()
                java.util.List r2 = r18.getPages()
                java.lang.Iterable r2 = (java.lang.Iterable) r2
                java.util.ArrayList r4 = new java.util.ArrayList
                r5 = 10
                int r5 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r2, r5)
                r4.<init>(r5)
                java.util.Collection r4 = (java.util.Collection) r4
                java.util.Iterator r2 = r2.iterator()
                r6 = r18
                r5 = r0
                r0 = r19
            L_0x0098:
                boolean r7 = r2.hasNext()
                if (r7 == 0) goto L_0x0142
                java.lang.Object r7 = r2.next()
                androidx.paging.TransformablePage r7 = (androidx.paging.TransformablePage) r7
                java.util.ArrayList r8 = new java.util.ArrayList
                r8.<init>()
                java.util.List r8 = (java.util.List) r8
                java.util.ArrayList r9 = new java.util.ArrayList
                r9.<init>()
                java.util.List r9 = (java.util.List) r9
                java.util.List r10 = r7.getData()
                java.lang.Iterable r10 = (java.lang.Iterable) r10
                java.util.Iterator r10 = r10.iterator()
                r11 = 0
                r12 = r7
                r7 = r4
                r16 = r10
                r10 = r9
                r9 = r16
            L_0x00c4:
                boolean r13 = r9.hasNext()
                if (r13 == 0) goto L_0x0130
                java.lang.Object r13 = r9.next()
                int r14 = r11 + 1
                if (r11 >= 0) goto L_0x00d5
                kotlin.collections.CollectionsKt.throwIndexOverflow()
            L_0x00d5:
                r1.L$0 = r0
                r1.L$1 = r6
                r1.L$2 = r5
                r1.L$3 = r4
                r1.L$4 = r2
                r1.L$5 = r12
                r1.L$6 = r8
                r1.L$7 = r10
                r1.L$8 = r9
                r1.L$9 = r13
                r1.L$10 = r7
                r1.I$0 = r14
                r1.I$1 = r11
                r15 = 1
                r1.label = r15
                java.lang.Object r15 = r0.invoke(r13, r1)
                if (r15 != r3) goto L_0x00f9
                return r3
            L_0x00f9:
                r16 = r2
                r2 = r0
                r0 = r15
                r15 = r12
                r12 = r14
                r14 = r4
                r4 = r11
                r11 = r10
                r10 = r9
                r9 = r13
                r13 = r16
            L_0x0106:
                java.lang.Boolean r0 = (java.lang.Boolean) r0
                boolean r0 = r0.booleanValue()
                if (r0 == 0) goto L_0x0128
                r8.add(r9)
                java.util.List r0 = r15.getHintOriginalIndices()
                if (r0 == 0) goto L_0x0121
                java.lang.Object r0 = r0.get(r4)
                java.lang.Number r0 = (java.lang.Number) r0
                int r4 = r0.intValue()
            L_0x0121:
                java.lang.Integer r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r4)
                r11.add(r0)
            L_0x0128:
                r0 = r2
                r9 = r10
                r10 = r11
                r11 = r12
                r2 = r13
                r4 = r14
                r12 = r15
                goto L_0x00c4
            L_0x0130:
                androidx.paging.TransformablePage r9 = new androidx.paging.TransformablePage
                int[] r11 = r12.getOriginalPageOffsets()
                int r12 = r12.getHintOriginalPageOffset()
                r9.<init>(r11, r8, r12, r10)
                r7.add(r9)
                goto L_0x0098
            L_0x0142:
                r0 = r4
                java.util.List r0 = (java.util.List) r0
                int r7 = r6.getPlaceholdersBefore()
                int r8 = r6.getPlaceholdersAfter()
                androidx.paging.LoadStates r9 = r6.getSourceLoadStates()
                androidx.paging.LoadStates r10 = r6.getMediatorLoadStates()
                androidx.paging.PageEvent$Insert r1 = new androidx.paging.PageEvent$Insert
                r11 = 0
                r4 = r1
                r6 = r0
                r4.<init>(r5, r6, r7, r8, r9, r10, r11)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageEvent.Insert.filter(kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
        }

        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JF\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\b0\u0004\"\b\b\u0002\u0010\b*\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000fJF\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\b0\u0004\"\b\b\u0002\u0010\b*\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u000b0\n2\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000fJN\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\b0\u0004\"\b\b\u0002\u0010\b*\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u000b0\n2\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000fR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0014"}, d2 = {"Landroidx/paging/PageEvent$Insert$Companion;", "", "()V", "EMPTY_REFRESH_LOCAL", "Landroidx/paging/PageEvent$Insert;", "getEMPTY_REFRESH_LOCAL", "()Landroidx/paging/PageEvent$Insert;", "Append", "T", "pages", "", "Landroidx/paging/TransformablePage;", "placeholdersAfter", "", "sourceLoadStates", "Landroidx/paging/LoadStates;", "mediatorLoadStates", "Prepend", "placeholdersBefore", "Refresh", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* compiled from: PageEvent.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public static /* synthetic */ Insert Refresh$default(Companion companion, List list, int i, int i2, LoadStates loadStates, LoadStates loadStates2, int i3, Object obj) {
                if ((i3 & 16) != 0) {
                    loadStates2 = null;
                }
                return companion.Refresh(list, i, i2, loadStates, loadStates2);
            }

            public final <T> Insert<T> Refresh(List<TransformablePage<T>> list, int i, int i2, LoadStates loadStates, LoadStates loadStates2) {
                Intrinsics.checkNotNullParameter(list, "pages");
                Intrinsics.checkNotNullParameter(loadStates, "sourceLoadStates");
                return new Insert(LoadType.REFRESH, list, i, i2, loadStates, loadStates2, (DefaultConstructorMarker) null);
            }

            public static /* synthetic */ Insert Prepend$default(Companion companion, List list, int i, LoadStates loadStates, LoadStates loadStates2, int i2, Object obj) {
                if ((i2 & 8) != 0) {
                    loadStates2 = null;
                }
                return companion.Prepend(list, i, loadStates, loadStates2);
            }

            public final <T> Insert<T> Prepend(List<TransformablePage<T>> list, int i, LoadStates loadStates, LoadStates loadStates2) {
                Intrinsics.checkNotNullParameter(list, "pages");
                Intrinsics.checkNotNullParameter(loadStates, "sourceLoadStates");
                return new Insert(LoadType.PREPEND, list, i, -1, loadStates, loadStates2, (DefaultConstructorMarker) null);
            }

            public static /* synthetic */ Insert Append$default(Companion companion, List list, int i, LoadStates loadStates, LoadStates loadStates2, int i2, Object obj) {
                if ((i2 & 8) != 0) {
                    loadStates2 = null;
                }
                return companion.Append(list, i, loadStates, loadStates2);
            }

            public final <T> Insert<T> Append(List<TransformablePage<T>> list, int i, LoadStates loadStates, LoadStates loadStates2) {
                Intrinsics.checkNotNullParameter(list, "pages");
                Intrinsics.checkNotNullParameter(loadStates, "sourceLoadStates");
                return new Insert(LoadType.APPEND, list, -1, i, loadStates, loadStates2, (DefaultConstructorMarker) null);
            }

            public final Insert<Object> getEMPTY_REFRESH_LOCAL() {
                return Insert.EMPTY_REFRESH_LOCAL;
            }
        }

        static {
            Companion companion = new Companion((DefaultConstructorMarker) null);
            Companion = companion;
            EMPTY_REFRESH_LOCAL = Companion.Refresh$default(companion, CollectionsKt.listOf(TransformablePage.Companion.getEMPTY_INITIAL_PAGE()), 0, 0, new LoadStates(LoadState.NotLoading.Companion.getIncomplete$paging_common(), LoadState.NotLoading.Companion.getComplete$paging_common(), LoadState.NotLoading.Companion.getComplete$paging_common()), (LoadStates) null, 16, (Object) null);
        }

        public String toString() {
            List data;
            List data2;
            int i = 0;
            for (TransformablePage data3 : this.pages) {
                i += data3.getData().size();
            }
            int i2 = this.placeholdersBefore;
            String str = "none";
            String valueOf = i2 != -1 ? String.valueOf(i2) : str;
            int i3 = this.placeholdersAfter;
            if (i3 != -1) {
                str = String.valueOf(i3);
            }
            LoadStates loadStates = this.mediatorLoadStates;
            StringBuilder sb = new StringBuilder("PageEvent.Insert for ");
            sb.append(this.loadType);
            sb.append(", with ");
            sb.append(i);
            sb.append(" items (\n                    |   first item: ");
            TransformablePage transformablePage = (TransformablePage) CollectionsKt.firstOrNull(this.pages);
            sb.append((transformablePage == null || (data2 = transformablePage.getData()) == null) ? null : CollectionsKt.firstOrNull(data2));
            sb.append("\n                    |   last item: ");
            TransformablePage transformablePage2 = (TransformablePage) CollectionsKt.lastOrNull(this.pages);
            sb.append((transformablePage2 == null || (data = transformablePage2.getData()) == null) ? null : CollectionsKt.lastOrNull(data));
            sb.append("\n                    |   placeholdersBefore: ");
            sb.append(valueOf);
            sb.append("\n                    |   placeholdersAfter: ");
            sb.append(str);
            sb.append("\n                    |   sourceLoadStates: ");
            sb.append(this.sourceLoadStates);
            sb.append("\n                    ");
            String sb2 = sb.toString();
            if (loadStates != null) {
                sb2 = sb2 + "|   mediatorLoadStates: " + loadStates + 10;
            }
            return StringsKt.trimMargin$default(sb2 + "|)", (String) null, 1, (Object) null);
        }

        private final <R> Insert<R> mapPages(Function1<? super TransformablePage<T>, TransformablePage<R>> function1) {
            LoadType loadType2 = getLoadType();
            Iterable<Object> pages2 = getPages();
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(pages2, 10));
            for (Object invoke : pages2) {
                arrayList.add(function1.invoke(invoke));
            }
            return new Insert(loadType2, (List) arrayList, getPlaceholdersBefore(), getPlaceholdersAfter(), getSourceLoadStates(), getMediatorLoadStates(), (DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\b\b\u0001\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B%\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J7\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0007HÖ\u0001J\b\u0010\u001c\u001a\u00020\u001dH\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0010\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000e¨\u0006\u001e"}, d2 = {"Landroidx/paging/PageEvent$Drop;", "T", "", "Landroidx/paging/PageEvent;", "loadType", "Landroidx/paging/LoadType;", "minPageOffset", "", "maxPageOffset", "placeholdersRemaining", "(Landroidx/paging/LoadType;III)V", "getLoadType", "()Landroidx/paging/LoadType;", "getMaxPageOffset", "()I", "getMinPageOffset", "pageCount", "getPageCount", "getPlaceholdersRemaining", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: PageEvent.kt */
    public static final class Drop<T> extends PageEvent<T> {
        private final LoadType loadType;
        private final int maxPageOffset;
        private final int minPageOffset;
        private final int placeholdersRemaining;

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* compiled from: PageEvent.kt */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
            static {
                /*
                    androidx.paging.LoadType[] r0 = androidx.paging.LoadType.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    androidx.paging.LoadType r1 = androidx.paging.LoadType.APPEND     // Catch:{ NoSuchFieldError -> 0x0010 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                    r2 = 1
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
                L_0x0010:
                    androidx.paging.LoadType r1 = androidx.paging.LoadType.PREPEND     // Catch:{ NoSuchFieldError -> 0x0019 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                    r2 = 2
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                L_0x0019:
                    $EnumSwitchMapping$0 = r0
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageEvent.Drop.WhenMappings.<clinit>():void");
            }
        }

        public static /* synthetic */ Drop copy$default(Drop drop, LoadType loadType2, int i, int i2, int i3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                loadType2 = drop.loadType;
            }
            if ((i4 & 2) != 0) {
                i = drop.minPageOffset;
            }
            if ((i4 & 4) != 0) {
                i2 = drop.maxPageOffset;
            }
            if ((i4 & 8) != 0) {
                i3 = drop.placeholdersRemaining;
            }
            return drop.copy(loadType2, i, i2, i3);
        }

        public final LoadType component1() {
            return this.loadType;
        }

        public final int component2() {
            return this.minPageOffset;
        }

        public final int component3() {
            return this.maxPageOffset;
        }

        public final int component4() {
            return this.placeholdersRemaining;
        }

        public final Drop<T> copy(LoadType loadType2, int i, int i2, int i3) {
            Intrinsics.checkNotNullParameter(loadType2, "loadType");
            return new Drop<>(loadType2, i, i2, i3);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Drop)) {
                return false;
            }
            Drop drop = (Drop) obj;
            return this.loadType == drop.loadType && this.minPageOffset == drop.minPageOffset && this.maxPageOffset == drop.maxPageOffset && this.placeholdersRemaining == drop.placeholdersRemaining;
        }

        public int hashCode() {
            return (((((this.loadType.hashCode() * 31) + this.minPageOffset) * 31) + this.maxPageOffset) * 31) + this.placeholdersRemaining;
        }

        public final LoadType getLoadType() {
            return this.loadType;
        }

        public final int getMinPageOffset() {
            return this.minPageOffset;
        }

        public final int getMaxPageOffset() {
            return this.maxPageOffset;
        }

        public final int getPlaceholdersRemaining() {
            return this.placeholdersRemaining;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Drop(LoadType loadType2, int i, int i2, int i3) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(loadType2, "loadType");
            this.loadType = loadType2;
            this.minPageOffset = i;
            this.maxPageOffset = i2;
            this.placeholdersRemaining = i3;
            if (loadType2 == LoadType.REFRESH) {
                throw new IllegalArgumentException("Drop load type must be PREPEND or APPEND".toString());
            } else if (getPageCount() <= 0) {
                throw new IllegalArgumentException(("Drop count must be > 0, but was " + getPageCount()).toString());
            } else if (i3 < 0) {
                throw new IllegalArgumentException(("Invalid placeholdersRemaining " + i3).toString());
            }
        }

        public final int getPageCount() {
            return (this.maxPageOffset - this.minPageOffset) + 1;
        }

        public String toString() {
            String str;
            int i = WhenMappings.$EnumSwitchMapping$0[this.loadType.ordinal()];
            if (i == 1) {
                str = "end";
            } else if (i == 2) {
                str = "front";
            } else {
                throw new IllegalArgumentException("Drop load type must be PREPEND or APPEND");
            }
            return StringsKt.trimMargin$default("PageEvent.Drop from the " + str + " (\n                    |   minPageOffset: " + this.minPageOffset + "\n                    |   maxPageOffset: " + this.maxPageOffset + "\n                    |   placeholdersRemaining: " + this.placeholdersRemaining + "\n                    |)", (String) null, 1, (Object) null);
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\b\b\u0001\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0019\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\u000b\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J%\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u0015"}, d2 = {"Landroidx/paging/PageEvent$LoadStateUpdate;", "T", "", "Landroidx/paging/PageEvent;", "source", "Landroidx/paging/LoadStates;", "mediator", "(Landroidx/paging/LoadStates;Landroidx/paging/LoadStates;)V", "getMediator", "()Landroidx/paging/LoadStates;", "getSource", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: PageEvent.kt */
    public static final class LoadStateUpdate<T> extends PageEvent<T> {
        private final LoadStates mediator;
        private final LoadStates source;

        public static /* synthetic */ LoadStateUpdate copy$default(LoadStateUpdate loadStateUpdate, LoadStates loadStates, LoadStates loadStates2, int i, Object obj) {
            if ((i & 1) != 0) {
                loadStates = loadStateUpdate.source;
            }
            if ((i & 2) != 0) {
                loadStates2 = loadStateUpdate.mediator;
            }
            return loadStateUpdate.copy(loadStates, loadStates2);
        }

        public final LoadStates component1() {
            return this.source;
        }

        public final LoadStates component2() {
            return this.mediator;
        }

        public final LoadStateUpdate<T> copy(LoadStates loadStates, LoadStates loadStates2) {
            Intrinsics.checkNotNullParameter(loadStates, "source");
            return new LoadStateUpdate<>(loadStates, loadStates2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof LoadStateUpdate)) {
                return false;
            }
            LoadStateUpdate loadStateUpdate = (LoadStateUpdate) obj;
            return Intrinsics.areEqual((Object) this.source, (Object) loadStateUpdate.source) && Intrinsics.areEqual((Object) this.mediator, (Object) loadStateUpdate.mediator);
        }

        public int hashCode() {
            int hashCode = this.source.hashCode() * 31;
            LoadStates loadStates = this.mediator;
            return hashCode + (loadStates == null ? 0 : loadStates.hashCode());
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ LoadStateUpdate(LoadStates loadStates, LoadStates loadStates2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(loadStates, (i & 2) != 0 ? null : loadStates2);
        }

        public final LoadStates getSource() {
            return this.source;
        }

        public final LoadStates getMediator() {
            return this.mediator;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LoadStateUpdate(LoadStates loadStates, LoadStates loadStates2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(loadStates, "source");
            this.source = loadStates;
            this.mediator = loadStates2;
        }

        public String toString() {
            LoadStates loadStates = this.mediator;
            String str = "PageEvent.LoadStateUpdate (\n                    |   sourceLoadStates: " + this.source + "\n                    ";
            if (loadStates != null) {
                str = str + "|   mediatorLoadStates: " + loadStates + 10;
            }
            return StringsKt.trimMargin$default(str + "|)", (String) null, 1, (Object) null);
        }
    }
}
