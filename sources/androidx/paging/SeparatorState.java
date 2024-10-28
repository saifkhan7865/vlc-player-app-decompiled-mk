package androidx.paging;

import androidx.core.app.NotificationCompat;
import androidx.paging.LoadState;
import androidx.paging.PageEvent;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u0002H\u00012\u00020\u0002B^\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012L\u0010\u0006\u001aH\b\u0001\u0012\u0015\u0012\u0013\u0018\u00018\u0001¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u00018\u0001¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\f\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0007ø\u0001\u0000¢\u0006\u0002\u0010\rJ\u001a\u0010:\u001a\b\u0012\u0004\u0012\u00028\u00000;2\f\u0010<\u001a\b\u0012\u0004\u0012\u00028\u00010;J%\u0010=\u001a\b\u0012\u0004\u0012\u00028\u00000>2\f\u0010<\u001a\b\u0012\u0004\u0012\u00028\u00010>H@ø\u0001\u0000¢\u0006\u0002\u0010?J%\u0010@\u001a\b\u0012\u0004\u0012\u00028\u00000A2\f\u0010<\u001a\b\u0012\u0004\u0012\u00028\u00010AH@ø\u0001\u0000¢\u0006\u0002\u0010BJ%\u0010C\u001a\b\u0012\u0004\u0012\u00028\u00000>2\f\u0010<\u001a\b\u0012\u0004\u0012\u00028\u00010DH@ø\u0001\u0000¢\u0006\u0002\u0010EJ%\u0010F\u001a\b\u0012\u0004\u0012\u00028\u00000>2\f\u0010<\u001a\b\u0012\u0004\u0012\u00028\u00010GH@ø\u0001\u0000¢\u0006\u0002\u0010HJ&\u0010I\u001a\b\u0012\u0004\u0012\u0002H\u00030%\"\b\b\u0002\u0010\u0003*\u00020\u00022\f\u0010J\u001a\b\u0012\u0004\u0012\u0002H\u00030%H\u0002J\u0016\u0010K\u001a\b\u0012\u0004\u0012\u00028\u00000A*\b\u0012\u0004\u0012\u00028\u00010AJ\"\u0010L\u001a\u00020\u000f\"\b\b\u0002\u0010\u0003*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00030A2\u0006\u0010\u0004\u001a\u00020\u0005J\"\u0010M\u001a\u00020\u000f\"\b\b\u0002\u0010\u0003*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00030A2\u0006\u0010\u0004\u001a\u00020\u0005R\u001a\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R\\\u0010\u0006\u001aH\b\u0001\u0012\u0015\u0012\u0013\u0018\u00018\u0001¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u00018\u0001¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\f\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0007ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u001a\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0011\"\u0004\b\u001c\u0010\u0013R\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001d\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010%0$¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u001a\u0010(\u001a\u00020)X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010.\u001a\u00020)X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010+\"\u0004\b0\u0010-R\u0011\u00101\u001a\u000202¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u001a\u00105\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u0011\"\u0004\b7\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b8\u00109\u0002\u0004\n\u0002\b\u0019¨\u0006N"}, d2 = {"Landroidx/paging/SeparatorState;", "R", "", "T", "terminalSeparatorType", "Landroidx/paging/TerminalSeparatorType;", "generator", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "before", "after", "Lkotlin/coroutines/Continuation;", "(Landroidx/paging/TerminalSeparatorType;Lkotlin/jvm/functions/Function3;)V", "endTerminalSeparatorDeferred", "", "getEndTerminalSeparatorDeferred", "()Z", "setEndTerminalSeparatorDeferred", "(Z)V", "footerAdded", "getFooterAdded", "setFooterAdded", "getGenerator", "()Lkotlin/jvm/functions/Function3;", "Lkotlin/jvm/functions/Function3;", "headerAdded", "getHeaderAdded", "setHeaderAdded", "mediatorStates", "Landroidx/paging/LoadStates;", "getMediatorStates", "()Landroidx/paging/LoadStates;", "setMediatorStates", "(Landroidx/paging/LoadStates;)V", "pageStash", "", "Landroidx/paging/TransformablePage;", "getPageStash", "()Ljava/util/List;", "placeholdersAfter", "", "getPlaceholdersAfter", "()I", "setPlaceholdersAfter", "(I)V", "placeholdersBefore", "getPlaceholdersBefore", "setPlaceholdersBefore", "sourceStates", "Landroidx/paging/MutableLoadStateCollection;", "getSourceStates", "()Landroidx/paging/MutableLoadStateCollection;", "startTerminalSeparatorDeferred", "getStartTerminalSeparatorDeferred", "setStartTerminalSeparatorDeferred", "getTerminalSeparatorType", "()Landroidx/paging/TerminalSeparatorType;", "onDrop", "Landroidx/paging/PageEvent$Drop;", "event", "onEvent", "Landroidx/paging/PageEvent;", "(Landroidx/paging/PageEvent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onInsert", "Landroidx/paging/PageEvent$Insert;", "(Landroidx/paging/PageEvent$Insert;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onLoadStateUpdate", "Landroidx/paging/PageEvent$LoadStateUpdate;", "(Landroidx/paging/PageEvent$LoadStateUpdate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onStaticList", "Landroidx/paging/PageEvent$StaticList;", "(Landroidx/paging/PageEvent$StaticList;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "transformablePageToStash", "originalPage", "asRType", "terminatesEnd", "terminatesStart", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Separators.kt */
final class SeparatorState<R, T extends R> {
    private boolean endTerminalSeparatorDeferred;
    private boolean footerAdded;
    private final Function3<T, T, Continuation<? super R>, Object> generator;
    private boolean headerAdded;
    private LoadStates mediatorStates;
    private final List<TransformablePage<T>> pageStash = new ArrayList();
    private int placeholdersAfter;
    private int placeholdersBefore;
    private final MutableLoadStateCollection sourceStates = new MutableLoadStateCollection();
    private boolean startTerminalSeparatorDeferred;
    private final TerminalSeparatorType terminalSeparatorType;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Separators.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                androidx.paging.TerminalSeparatorType[] r0 = androidx.paging.TerminalSeparatorType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                androidx.paging.TerminalSeparatorType r1 = androidx.paging.TerminalSeparatorType.FULLY_COMPLETE     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                androidx.paging.TerminalSeparatorType r1 = androidx.paging.TerminalSeparatorType.SOURCE_COMPLETE     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.SeparatorState.WhenMappings.<clinit>():void");
        }
    }

    public final PageEvent.Insert<R> asRType(PageEvent.Insert<T> insert) {
        Intrinsics.checkNotNullParameter(insert, "<this>");
        return insert;
    }

    public SeparatorState(TerminalSeparatorType terminalSeparatorType2, Function3<? super T, ? super T, ? super Continuation<? super R>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(terminalSeparatorType2, "terminalSeparatorType");
        Intrinsics.checkNotNullParameter(function3, "generator");
        this.terminalSeparatorType = terminalSeparatorType2;
        this.generator = function3;
    }

    public final TerminalSeparatorType getTerminalSeparatorType() {
        return this.terminalSeparatorType;
    }

    public final Function3<T, T, Continuation<? super R>, Object> getGenerator() {
        return this.generator;
    }

    public final List<TransformablePage<T>> getPageStash() {
        return this.pageStash;
    }

    public final boolean getEndTerminalSeparatorDeferred() {
        return this.endTerminalSeparatorDeferred;
    }

    public final void setEndTerminalSeparatorDeferred(boolean z) {
        this.endTerminalSeparatorDeferred = z;
    }

    public final boolean getStartTerminalSeparatorDeferred() {
        return this.startTerminalSeparatorDeferred;
    }

    public final void setStartTerminalSeparatorDeferred(boolean z) {
        this.startTerminalSeparatorDeferred = z;
    }

    public final MutableLoadStateCollection getSourceStates() {
        return this.sourceStates;
    }

    public final LoadStates getMediatorStates() {
        return this.mediatorStates;
    }

    public final void setMediatorStates(LoadStates loadStates) {
        this.mediatorStates = loadStates;
    }

    public final int getPlaceholdersBefore() {
        return this.placeholdersBefore;
    }

    public final void setPlaceholdersBefore(int i) {
        this.placeholdersBefore = i;
    }

    public final int getPlaceholdersAfter() {
        return this.placeholdersAfter;
    }

    public final void setPlaceholdersAfter(int i) {
        this.placeholdersAfter = i;
    }

    public final boolean getFooterAdded() {
        return this.footerAdded;
    }

    public final void setFooterAdded(boolean z) {
        this.footerAdded = z;
    }

    public final boolean getHeaderAdded() {
        return this.headerAdded;
    }

    public final void setHeaderAdded(boolean z) {
        this.headerAdded = z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object onEvent(androidx.paging.PageEvent<T> r7, kotlin.coroutines.Continuation<? super androidx.paging.PageEvent<R>> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof androidx.paging.SeparatorState$onEvent$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            androidx.paging.SeparatorState$onEvent$1 r0 = (androidx.paging.SeparatorState$onEvent$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            androidx.paging.SeparatorState$onEvent$1 r0 = new androidx.paging.SeparatorState$onEvent$1
            r0.<init>(r6, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x004d
            if (r2 == r5) goto L_0x0045
            if (r2 == r4) goto L_0x003d
            if (r2 != r3) goto L_0x0035
            java.lang.Object r7 = r0.L$0
            androidx.paging.SeparatorState r7 = (androidx.paging.SeparatorState) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x009b
        L_0x0035:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x003d:
            java.lang.Object r7 = r0.L$0
            androidx.paging.SeparatorState r7 = (androidx.paging.SeparatorState) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0086
        L_0x0045:
            java.lang.Object r7 = r0.L$0
            androidx.paging.SeparatorState r7 = (androidx.paging.SeparatorState) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0062
        L_0x004d:
            kotlin.ResultKt.throwOnFailure(r8)
            boolean r8 = r7 instanceof androidx.paging.PageEvent.Insert
            if (r8 == 0) goto L_0x0065
            androidx.paging.PageEvent$Insert r7 = (androidx.paging.PageEvent.Insert) r7
            r0.L$0 = r6
            r0.label = r5
            java.lang.Object r8 = r6.onInsert(r7, r0)
            if (r8 != r1) goto L_0x0061
            return r1
        L_0x0061:
            r7 = r6
        L_0x0062:
            androidx.paging.PageEvent r8 = (androidx.paging.PageEvent) r8
            goto L_0x009d
        L_0x0065:
            boolean r8 = r7 instanceof androidx.paging.PageEvent.Drop
            if (r8 == 0) goto L_0x0074
            androidx.paging.PageEvent$Drop r7 = (androidx.paging.PageEvent.Drop) r7
            androidx.paging.PageEvent$Drop r7 = r6.onDrop(r7)
            r8 = r7
            androidx.paging.PageEvent r8 = (androidx.paging.PageEvent) r8
            r7 = r6
            goto L_0x009d
        L_0x0074:
            boolean r8 = r7 instanceof androidx.paging.PageEvent.LoadStateUpdate
            if (r8 == 0) goto L_0x0089
            androidx.paging.PageEvent$LoadStateUpdate r7 = (androidx.paging.PageEvent.LoadStateUpdate) r7
            r0.L$0 = r6
            r0.label = r4
            java.lang.Object r8 = r6.onLoadStateUpdate(r7, r0)
            if (r8 != r1) goto L_0x0085
            return r1
        L_0x0085:
            r7 = r6
        L_0x0086:
            androidx.paging.PageEvent r8 = (androidx.paging.PageEvent) r8
            goto L_0x009d
        L_0x0089:
            boolean r8 = r7 instanceof androidx.paging.PageEvent.StaticList
            if (r8 == 0) goto L_0x00d0
            androidx.paging.PageEvent$StaticList r7 = (androidx.paging.PageEvent.StaticList) r7
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r8 = r6.onStaticList(r7, r0)
            if (r8 != r1) goto L_0x009a
            return r1
        L_0x009a:
            r7 = r6
        L_0x009b:
            androidx.paging.PageEvent r8 = (androidx.paging.PageEvent) r8
        L_0x009d:
            boolean r0 = r7.endTerminalSeparatorDeferred
            if (r0 == 0) goto L_0x00b6
            java.util.List<androidx.paging.TransformablePage<T>> r0 = r7.pageStash
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x00aa
            goto L_0x00b6
        L_0x00aa:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "deferred endTerm, page stash should be empty"
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        L_0x00b6:
            boolean r0 = r7.startTerminalSeparatorDeferred
            if (r0 == 0) goto L_0x00cf
            java.util.List<androidx.paging.TransformablePage<T>> r7 = r7.pageStash
            boolean r7 = r7.isEmpty()
            if (r7 == 0) goto L_0x00c3
            goto L_0x00cf
        L_0x00c3:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "deferred startTerm, page stash should be empty"
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        L_0x00cf:
            return r8
        L_0x00d0:
            kotlin.NoWhenBranchMatchedException r7 = new kotlin.NoWhenBranchMatchedException
            r7.<init>()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.SeparatorState.onEvent(androidx.paging.PageEvent, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final <T> boolean terminatesStart(PageEvent.Insert<T> insert, TerminalSeparatorType terminalSeparatorType2) {
        LoadStates mediatorLoadStates;
        LoadState prepend;
        Intrinsics.checkNotNullParameter(insert, "<this>");
        Intrinsics.checkNotNullParameter(terminalSeparatorType2, "terminalSeparatorType");
        if (insert.getLoadType() == LoadType.APPEND) {
            return this.startTerminalSeparatorDeferred;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[terminalSeparatorType2.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return insert.getSourceLoadStates().getPrepend().getEndOfPaginationReached();
            }
            throw new NoWhenBranchMatchedException();
        } else if (!insert.getSourceLoadStates().getPrepend().getEndOfPaginationReached() || ((mediatorLoadStates = insert.getMediatorLoadStates()) != null && (prepend = mediatorLoadStates.getPrepend()) != null && !prepend.getEndOfPaginationReached())) {
            return false;
        } else {
            return true;
        }
    }

    public final <T> boolean terminatesEnd(PageEvent.Insert<T> insert, TerminalSeparatorType terminalSeparatorType2) {
        LoadStates mediatorLoadStates;
        LoadState append;
        Intrinsics.checkNotNullParameter(insert, "<this>");
        Intrinsics.checkNotNullParameter(terminalSeparatorType2, "terminalSeparatorType");
        if (insert.getLoadType() == LoadType.PREPEND) {
            return this.endTerminalSeparatorDeferred;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[terminalSeparatorType2.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return insert.getSourceLoadStates().getAppend().getEndOfPaginationReached();
            }
            throw new NoWhenBranchMatchedException();
        } else if (!insert.getSourceLoadStates().getAppend().getEndOfPaginationReached() || ((mediatorLoadStates = insert.getMediatorLoadStates()) != null && (append = mediatorLoadStates.getAppend()) != null && !append.getEndOfPaginationReached())) {
            return false;
        } else {
            return true;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v36, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v28, resolved type: androidx.paging.TransformablePage} */
    /* JADX WARNING: type inference failed for: r4v3 */
    /* JADX WARNING: type inference failed for: r4v4 */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x040f, code lost:
        r9 = r8.getHintOriginalPageOffset();
        r7 = r8.getHintOriginalIndices();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x0417, code lost:
        if (r7 == null) goto L_0x0425;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x0419, code lost:
        r10 = ((java.lang.Number) kotlin.collections.CollectionsKt.first(r7)).intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0425, code lost:
        r10 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0426, code lost:
        androidx.paging.SeparatorsKt.addSeparatorPage(r5, r6, (androidx.paging.TransformablePage) null, r8, r9, r10);
        r5 = r0;
        r6 = r1;
        r10 = r2;
        r9 = r11;
        r8 = r12;
        r7 = r13;
        r12 = r15;
        r13 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x0437, code lost:
        if (r6 != 0) goto L_0x07ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x0439, code lost:
        kotlin.jvm.internal.Intrinsics.checkNotNull(r8);
        r1 = r8.intValue();
        r0 = r5;
        r11 = r12;
        r12 = r13;
        r13 = r14;
        r14 = r19;
        r5 = 0;
        r22 = r10;
        r10 = r7;
        r7 = r22;
        r23 = r9;
        r9 = r8;
        r8 = r23;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x0452, code lost:
        if (r5 >= r1) goto L_0x049a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x0454, code lost:
        r15 = r14.generator;
        r3.L$0 = r14;
        r3.L$1 = r13;
        r3.L$2 = r12;
        r3.L$3 = r11;
        r3.L$4 = r10;
        r3.L$5 = r9;
        r3.L$6 = r8;
        r3.L$7 = r7;
        r3.L$8 = r12;
        r18 = r7;
        r3.L$9 = null;
        r3.Z$0 = r0;
        r3.I$0 = r6;
        r3.I$1 = r5;
        r3.I$2 = r1;
        r3.label = 3;
        r2 = androidx.paging.SeparatorsKt.insertInternalSeparators(r13.getPages().get(r5), r15, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x0486, code lost:
        if (r2 != r4) goto L_0x0489;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x0488, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0489, code lost:
        r15 = r14;
        r7 = r18;
        r14 = r12;
        r12 = r10;
        r10 = r8;
        r8 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x0490, code lost:
        r8.add(r2);
        r5 = r5 + 1;
        r8 = r10;
        r10 = r12;
        r12 = r14;
        r14 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x049a, code lost:
        r18 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x04a3, code lost:
        if (r13.getLoadType() != androidx.paging.LoadType.APPEND) goto L_0x052a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x04ae, code lost:
        if ((!r14.pageStash.isEmpty()) == false) goto L_0x052a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x04b0, code lost:
        r1 = (androidx.paging.TransformablePage) kotlin.collections.CollectionsKt.last(r14.pageStash);
        r2 = r14.generator;
        r5 = kotlin.collections.CollectionsKt.last(r1.getData());
        kotlin.jvm.internal.Intrinsics.checkNotNull(r10);
        r7 = kotlin.collections.CollectionsKt.first(r10.getData());
        r3.L$0 = r14;
        r3.L$1 = r13;
        r3.L$2 = r12;
        r3.L$3 = r11;
        r3.L$4 = r10;
        r3.L$5 = r9;
        r3.L$6 = r8;
        r15 = r18;
        r3.L$7 = r15;
        r3.L$8 = r1;
        r25 = r1;
        r3.L$9 = null;
        r3.Z$0 = r0;
        r3.I$0 = r6;
        r3.label = 4;
        r2 = r2.invoke(r5, r7, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x04f1, code lost:
        if (r2 != r4) goto L_0x04f4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x04f3, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x04f4, code lost:
        r7 = r25;
        r1 = r6;
        r18 = r13;
        r19 = r14;
        r6 = r2;
        r2 = r8;
        r13 = r11;
        r14 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x009b, code lost:
        r0 = r5;
        r8 = r6;
        r15 = r14;
        r6 = r2;
        r2 = r9;
        r14 = r13;
        r13 = r12;
        r12 = r11;
        r11 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x0501, code lost:
        r5 = r14;
        r9 = r12.getHintOriginalPageOffset();
        r8 = r12.getHintOriginalIndices();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x050c, code lost:
        if (r8 == null) goto L_0x051a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x050e, code lost:
        r10 = ((java.lang.Number) kotlin.collections.CollectionsKt.first(r8)).intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x051a, code lost:
        r10 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x051b, code lost:
        androidx.paging.SeparatorsKt.addSeparatorPage(r5, r6, r7, r12, r9, r10);
        r5 = r0;
        r8 = r2;
        r9 = r11;
        r10 = r13;
        r6 = r14;
        r7 = r15;
        r13 = r18;
        r14 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x052a, code lost:
        r5 = r0;
        r1 = r6;
        r6 = r12;
        r7 = r18;
        r12 = r10;
        r10 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0532, code lost:
        kotlin.jvm.internal.Intrinsics.checkNotNull(r12);
        r10.add(r14.transformablePageToStash(r12));
        r0 = r14.generator;
        r3.L$0 = r14;
        r3.L$1 = r13;
        r3.L$2 = r6;
        r3.L$3 = r10;
        r3.L$4 = r9;
        r3.L$5 = r8;
        r3.L$6 = r7;
        r3.L$7 = r6;
        r3.L$8 = null;
        r3.L$9 = null;
        r3.Z$0 = r5;
        r3.I$0 = r1;
        r3.label = 5;
        r2 = androidx.paging.SeparatorsKt.insertInternalSeparators(r12, r0, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x055e, code lost:
        if (r2 != r4) goto L_0x0561;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x0560, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x0561, code lost:
        r11 = r6;
        r12 = r13;
        r13 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x0564, code lost:
        r6.add(r2);
        r0 = r12.getPages();
        r2 = r9.intValue();
        kotlin.jvm.internal.Intrinsics.checkNotNull(r7);
        r0 = r0.subList(r2, r7.intValue() + 1).iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x0586, code lost:
        if (r0.hasNext() == false) goto L_0x07a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x0588, code lost:
        r2 = r0.next();
        r9 = r7;
        r14 = r13;
        r13 = r12;
        r12 = r11;
        r11 = r10;
        r10 = r8;
        r8 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x0597, code lost:
        if (r8.hasNext() == false) goto L_0x06ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x0599, code lost:
        r7 = r8.next();
        r6 = (androidx.paging.TransformablePage) r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x05af, code lost:
        if ((!r7.getData().isEmpty()) == false) goto L_0x0655;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x05b1, code lost:
        r0 = r14.generator;
        r2 = kotlin.collections.CollectionsKt.last(r6.getData());
        r15 = kotlin.collections.CollectionsKt.first(r7.getData());
        r3.L$0 = r14;
        r3.L$1 = r13;
        r3.L$2 = r12;
        r3.L$3 = r11;
        r3.L$4 = r10;
        r3.L$5 = r9;
        r3.L$6 = r8;
        r3.L$7 = r7;
        r3.L$8 = r6;
        r25 = r6;
        r3.L$9 = null;
        r3.Z$0 = r5;
        r3.I$0 = r1;
        r3.label = 6;
        r2 = r0.invoke(r2, r15, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x05e5, code lost:
        if (r2 != r4) goto L_0x05e8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x05e7, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x00d5, code lost:
        r0 = r8;
        r8 = r11;
        r11 = r14;
        r14 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x05e8, code lost:
        r6 = r2;
        r0 = r5;
        r15 = r11;
        r18 = r12;
        r19 = r13;
        r20 = r14;
        r2 = r25;
        r11 = r7;
        r12 = r8;
        r13 = r9;
        r14 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x05f7, code lost:
        r5 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x0601, code lost:
        if (r19.getLoadType() != androidx.paging.LoadType.PREPEND) goto L_0x0608;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x0603, code lost:
        r7 = r2.getHintOriginalPageOffset();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x0608, code lost:
        r7 = r11.getHintOriginalPageOffset();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x060c, code lost:
        r9 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x0613, code lost:
        if (r19.getLoadType() != androidx.paging.LoadType.PREPEND) goto L_0x062f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x0615, code lost:
        r7 = r2.getHintOriginalIndices();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x0619, code lost:
        if (r7 == null) goto L_0x0626;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x061b, code lost:
        r7 = ((java.lang.Number) kotlin.collections.CollectionsKt.last(r7)).intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x0626, code lost:
        r7 = kotlin.collections.CollectionsKt.getLastIndex(r2.getData());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x062f, code lost:
        r7 = r11.getHintOriginalIndices();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x0633, code lost:
        if (r7 == null) goto L_0x0641;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x0635, code lost:
        r7 = ((java.lang.Number) kotlin.collections.CollectionsKt.first(r7)).intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x063f, code lost:
        r10 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x0641, code lost:
        r10 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x0642, code lost:
        androidx.paging.SeparatorsKt.addSeparatorPage(r5, r6, r2, r11, r9, r10);
        r5 = r0;
        r9 = r2;
        r10 = r11;
        r11 = r12;
        r12 = r13;
        r13 = r14;
        r14 = r15;
        r6 = r18;
        r8 = r19;
        r7 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x0655, code lost:
        r25 = r6;
        r6 = r12;
        r12 = r9;
        r9 = r25;
        r22 = r10;
        r10 = r7;
        r7 = r14;
        r14 = r11;
        r11 = r8;
        r8 = r13;
        r13 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x0670, code lost:
        if ((!r10.getData().isEmpty()) == false) goto L_0x0679;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x0672, code lost:
        r14.add(r7.transformablePageToStash(r10));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x0679, code lost:
        r0 = r7.generator;
        r3.L$0 = r7;
        r3.L$1 = r8;
        r3.L$2 = r6;
        r3.L$3 = r14;
        r3.L$4 = r13;
        r3.L$5 = r12;
        r3.L$6 = r11;
        r3.L$7 = r10;
        r3.L$8 = r9;
        r3.L$9 = r6;
        r3.Z$0 = r5;
        r3.I$0 = r1;
        r3.label = 7;
        r2 = androidx.paging.SeparatorsKt.insertInternalSeparators(r10, r0, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x069a, code lost:
        if (r2 != r4) goto L_0x069d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x069c, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x069d, code lost:
        r15 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x06a0, code lost:
        r6.add(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x06af, code lost:
        if ((!r10.getData().isEmpty()) == false) goto L_0x06b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x06b1, code lost:
        r2 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x06b3, code lost:
        r2 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x06b4, code lost:
        r9 = r12;
        r10 = r13;
        r12 = r15;
        r13 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x06c0, code lost:
        if (r13.getLoadType() != androidx.paging.LoadType.PREPEND) goto L_0x073e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x06cc, code lost:
        if ((!r14.pageStash.isEmpty()) == false) goto L_0x073e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x06ce, code lost:
        r6 = (androidx.paging.TransformablePage) kotlin.collections.CollectionsKt.first(r14.pageStash);
        r0 = r14.generator;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r10);
        r2 = kotlin.collections.CollectionsKt.last(r10.getData());
        r7 = kotlin.collections.CollectionsKt.first(r6.getData());
        r3.L$0 = r14;
        r3.L$1 = r13;
        r3.L$2 = r12;
        r3.L$3 = r11;
        r3.L$4 = r10;
        r3.L$5 = r9;
        r3.L$6 = r6;
        r3.L$7 = null;
        r3.L$8 = null;
        r3.L$9 = null;
        r3.Z$0 = r5;
        r3.I$0 = r1;
        r3.label = 8;
        r2 = r0.invoke(r2, r7, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x070d, code lost:
        if (r2 != r4) goto L_0x009b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x070f, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x0710, code lost:
        r5 = r13;
        r9 = r11.getHintOriginalPageOffset();
        r7 = r11.getHintOriginalIndices();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x071b, code lost:
        if (r7 == null) goto L_0x0728;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x071d, code lost:
        r7 = ((java.lang.Number) kotlin.collections.CollectionsKt.last(r7)).intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0178, code lost:
        r11 = r9;
        r12 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:0x0728, code lost:
        r7 = kotlin.collections.CollectionsKt.getLastIndex(r11.getData());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x0730, code lost:
        androidx.paging.SeparatorsKt.addSeparatorPage(r5, r6, r11, r8, r9, r7);
        r5 = r0;
        r6 = r1;
        r9 = r2;
        r10 = r11;
        r11 = r12;
        r12 = r13;
        r13 = r14;
        r14 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x073e, code lost:
        r6 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:0x073f, code lost:
        r0 = r9.intValue() + 1;
        r1 = kotlin.collections.CollectionsKt.getLastIndex(r13.getPages());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:0x074d, code lost:
        if (r0 > r1) goto L_0x079d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x074f, code lost:
        r9 = r6;
        r15 = r13;
        r6 = r14;
        r13 = r11;
        r11 = r12;
        r12 = r10;
        r10 = r5;
        r5 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:206:0x0757, code lost:
        r2 = r6.generator;
        r3.L$0 = r6;
        r3.L$1 = r15;
        r3.L$2 = r11;
        r3.L$3 = r13;
        r3.L$4 = r12;
        r3.L$5 = r11;
        r3.L$6 = null;
        r3.L$7 = null;
        r3.L$8 = null;
        r3.L$9 = null;
        r3.Z$0 = r10;
        r3.I$0 = r9;
        r3.I$1 = r5;
        r3.I$2 = r1;
        r3.label = 9;
        r2 = androidx.paging.SeparatorsKt.insertInternalSeparators(r15.getPages().get(r5), r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:207:0x0788, code lost:
        if (r2 != r4) goto L_0x078b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x078a, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:209:0x078b, code lost:
        r14 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:210:0x078c, code lost:
        r11.add(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x078f, code lost:
        if (r5 == r1) goto L_0x0795;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x0791, code lost:
        r5 = r5 + 1;
        r11 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x0795, code lost:
        r0 = r3;
        r3 = r6;
        r6 = r9;
        r5 = r10;
        r9 = r12;
        r7 = r14;
        r8 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:214:0x079d, code lost:
        r0 = r3;
        r9 = r10;
        r7 = r12;
        r8 = r13;
        r3 = r14;
        r13 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:216:0x07ab, code lost:
        throw new java.lang.UnsupportedOperationException("Empty collection can't be reduced.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x07ac, code lost:
        r0 = r3;
        r7 = r13;
        r8 = r14;
        r3 = r19;
        r13 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:218:0x07b2, code lost:
        if (r5 == false) goto L_0x0821;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:0x07b6, code lost:
        if (r3.footerAdded != false) goto L_0x0821;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:221:0x07b8, code lost:
        r3.footerAdded = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:222:0x07bb, code lost:
        if (r6 == 0) goto L_0x07c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x07bd, code lost:
        r1 = (androidx.paging.TransformablePage) kotlin.collections.CollectionsKt.last(r3.pageStash);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:0x07c6, code lost:
        kotlin.jvm.internal.Intrinsics.checkNotNull(r9);
        r1 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x07ca, code lost:
        r2 = r7;
        r5 = r3.generator;
        r6 = kotlin.collections.CollectionsKt.last(r1.getData());
        r0.L$0 = r3;
        r0.L$1 = r8;
        r0.L$2 = r7;
        r0.L$3 = r13;
        r0.L$4 = r1;
        r0.L$5 = r2;
        r0.L$6 = null;
        r0.L$7 = null;
        r0.L$8 = null;
        r0.L$9 = null;
        r0.label = 10;
        r0 = r5.invoke(r6, null, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:226:0x07f4, code lost:
        if (r0 != r4) goto L_0x07f7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x07f6, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:0x07f7, code lost:
        r15 = r0;
        r16 = r1;
        r14 = r2;
        r5 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:229:0x07fc, code lost:
        r18 = r16.getHintOriginalPageOffset();
        r0 = r16.getHintOriginalIndices();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x0804, code lost:
        if (r0 == null) goto L_0x0811;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:231:0x0806, code lost:
        r0 = ((java.lang.Number) kotlin.collections.CollectionsKt.last(r0)).intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:232:0x0811, code lost:
        r0 = kotlin.collections.CollectionsKt.getLastIndex(r16.getData());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:233:0x0819, code lost:
        androidx.paging.SeparatorsKt.addSeparatorPage(r14, r15, r16, (androidx.paging.TransformablePage) null, r18, r0);
        r13 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:234:0x0821, code lost:
        r3.endTerminalSeparatorDeferred = false;
        r3.startTerminalSeparatorDeferred = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:235:0x082c, code lost:
        if (r8.getLoadType() != androidx.paging.LoadType.APPEND) goto L_0x0836;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:236:0x082e, code lost:
        r3.pageStash.addAll(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:237:0x0836, code lost:
        r3.pageStash.addAll(0, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:238:0x083d, code lost:
        r15 = r8.getLoadType();
        r8.getPages();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:239:0x0860, code lost:
        return new androidx.paging.PageEvent.Insert(r15, r7, r8.getPlaceholdersBefore(), r8.getPlaceholdersAfter(), r8.getSourceLoadStates(), r8.getMediatorLoadStates(), (kotlin.jvm.internal.DefaultConstructorMarker) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:246:?, code lost:
        return new androidx.paging.PageEvent.Insert(r3, kotlin.collections.CollectionsKt.listOf(androidx.paging.SeparatorsKt.separatorPage(r2, new int[]{r4}, r4, r4)), r1.getPlaceholdersBefore(), r1.getPlaceholdersAfter(), r1.getSourceLoadStates(), r1.getMediatorLoadStates(), (kotlin.jvm.internal.DefaultConstructorMarker) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:247:?, code lost:
        return r3.asRType(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x02e3, code lost:
        r3.endTerminalSeparatorDeferred = r4;
        r3.startTerminalSeparatorDeferred = r4;
        r3.headerAdded = r5;
        r3.footerAdded = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x02eb, code lost:
        if (r2 != null) goto L_0x02f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x02f2, code lost:
        r3 = r1.getLoadType();
        r1.getPages();
     */
    /* JADX WARNING: Incorrect type for immutable var: ssa=int, code=?, for r4v1, types: [boolean, int] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0119  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0142  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x017c  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x01b7  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x01f5  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0204  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object onInsert(androidx.paging.PageEvent.Insert<T> r25, kotlin.coroutines.Continuation<? super androidx.paging.PageEvent.Insert<R>> r26) {
        /*
            r24 = this;
            r0 = r24
            r1 = r25
            r2 = r26
            boolean r3 = r2 instanceof androidx.paging.SeparatorState$onInsert$1
            if (r3 == 0) goto L_0x001a
            r3 = r2
            androidx.paging.SeparatorState$onInsert$1 r3 = (androidx.paging.SeparatorState$onInsert$1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L_0x001a
            int r2 = r3.label
            int r2 = r2 - r5
            r3.label = r2
            goto L_0x001f
        L_0x001a:
            androidx.paging.SeparatorState$onInsert$1 r3 = new androidx.paging.SeparatorState$onInsert$1
            r3.<init>(r0, r2)
        L_0x001f:
            java.lang.Object r2 = r3.result
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r3.label
            switch(r5) {
                case 0: goto L_0x0204;
                case 1: goto L_0x01f5;
                case 2: goto L_0x01b7;
                case 3: goto L_0x017c;
                case 4: goto L_0x0142;
                case 5: goto L_0x0119;
                case 6: goto L_0x00db;
                case 7: goto L_0x00a6;
                case 8: goto L_0x0078;
                case 9: goto L_0x0053;
                case 10: goto L_0x0032;
                default: goto L_0x002a;
            }
        L_0x002a:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0032:
            java.lang.Object r1 = r3.L$5
            java.util.List r1 = (java.util.List) r1
            java.lang.Object r4 = r3.L$4
            androidx.paging.TransformablePage r4 = (androidx.paging.TransformablePage) r4
            java.lang.Object r5 = r3.L$3
            java.util.ArrayList r5 = (java.util.ArrayList) r5
            java.lang.Object r7 = r3.L$2
            java.util.ArrayList r7 = (java.util.ArrayList) r7
            java.lang.Object r8 = r3.L$1
            androidx.paging.PageEvent$Insert r8 = (androidx.paging.PageEvent.Insert) r8
            java.lang.Object r3 = r3.L$0
            androidx.paging.SeparatorState r3 = (androidx.paging.SeparatorState) r3
            kotlin.ResultKt.throwOnFailure(r2)
            r14 = r1
            r15 = r2
            r16 = r4
            goto L_0x07fc
        L_0x0053:
            int r1 = r3.I$2
            int r5 = r3.I$1
            int r9 = r3.I$0
            boolean r10 = r3.Z$0
            java.lang.Object r11 = r3.L$5
            java.util.ArrayList r11 = (java.util.ArrayList) r11
            java.lang.Object r12 = r3.L$4
            androidx.paging.TransformablePage r12 = (androidx.paging.TransformablePage) r12
            java.lang.Object r13 = r3.L$3
            java.util.ArrayList r13 = (java.util.ArrayList) r13
            java.lang.Object r14 = r3.L$2
            java.util.ArrayList r14 = (java.util.ArrayList) r14
            java.lang.Object r15 = r3.L$1
            androidx.paging.PageEvent$Insert r15 = (androidx.paging.PageEvent.Insert) r15
            java.lang.Object r6 = r3.L$0
            androidx.paging.SeparatorState r6 = (androidx.paging.SeparatorState) r6
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x078c
        L_0x0078:
            int r1 = r3.I$0
            boolean r5 = r3.Z$0
            java.lang.Object r6 = r3.L$6
            androidx.paging.TransformablePage r6 = (androidx.paging.TransformablePage) r6
            java.lang.Object r9 = r3.L$5
            java.lang.Integer r9 = (java.lang.Integer) r9
            java.lang.Object r10 = r3.L$4
            androidx.paging.TransformablePage r10 = (androidx.paging.TransformablePage) r10
            java.lang.Object r11 = r3.L$3
            java.util.ArrayList r11 = (java.util.ArrayList) r11
            java.lang.Object r12 = r3.L$2
            java.util.ArrayList r12 = (java.util.ArrayList) r12
            java.lang.Object r13 = r3.L$1
            androidx.paging.PageEvent$Insert r13 = (androidx.paging.PageEvent.Insert) r13
            java.lang.Object r14 = r3.L$0
            androidx.paging.SeparatorState r14 = (androidx.paging.SeparatorState) r14
            kotlin.ResultKt.throwOnFailure(r2)
        L_0x009b:
            r0 = r5
            r8 = r6
            r15 = r14
            r6 = r2
            r2 = r9
            r14 = r13
            r13 = r12
            r12 = r11
            r11 = r10
            goto L_0x0710
        L_0x00a6:
            int r1 = r3.I$0
            boolean r5 = r3.Z$0
            java.lang.Object r6 = r3.L$9
            java.util.ArrayList r6 = (java.util.ArrayList) r6
            java.lang.Object r9 = r3.L$8
            androidx.paging.TransformablePage r9 = (androidx.paging.TransformablePage) r9
            java.lang.Object r10 = r3.L$7
            androidx.paging.TransformablePage r10 = (androidx.paging.TransformablePage) r10
            java.lang.Object r11 = r3.L$6
            java.util.Iterator r11 = (java.util.Iterator) r11
            java.lang.Object r12 = r3.L$5
            java.lang.Integer r12 = (java.lang.Integer) r12
            java.lang.Object r13 = r3.L$4
            androidx.paging.TransformablePage r13 = (androidx.paging.TransformablePage) r13
            java.lang.Object r14 = r3.L$3
            java.util.ArrayList r14 = (java.util.ArrayList) r14
            java.lang.Object r15 = r3.L$2
            java.util.ArrayList r15 = (java.util.ArrayList) r15
            java.lang.Object r8 = r3.L$1
            androidx.paging.PageEvent$Insert r8 = (androidx.paging.PageEvent.Insert) r8
            java.lang.Object r7 = r3.L$0
            androidx.paging.SeparatorState r7 = (androidx.paging.SeparatorState) r7
            kotlin.ResultKt.throwOnFailure(r2)
        L_0x00d5:
            r0 = r8
            r8 = r11
            r11 = r14
            r14 = r7
            goto L_0x06a0
        L_0x00db:
            int r1 = r3.I$0
            boolean r5 = r3.Z$0
            java.lang.Object r6 = r3.L$8
            androidx.paging.TransformablePage r6 = (androidx.paging.TransformablePage) r6
            java.lang.Object r7 = r3.L$7
            androidx.paging.TransformablePage r7 = (androidx.paging.TransformablePage) r7
            java.lang.Object r8 = r3.L$6
            java.util.Iterator r8 = (java.util.Iterator) r8
            java.lang.Object r9 = r3.L$5
            java.lang.Integer r9 = (java.lang.Integer) r9
            java.lang.Object r10 = r3.L$4
            androidx.paging.TransformablePage r10 = (androidx.paging.TransformablePage) r10
            java.lang.Object r11 = r3.L$3
            java.util.ArrayList r11 = (java.util.ArrayList) r11
            java.lang.Object r12 = r3.L$2
            java.util.ArrayList r12 = (java.util.ArrayList) r12
            java.lang.Object r13 = r3.L$1
            androidx.paging.PageEvent$Insert r13 = (androidx.paging.PageEvent.Insert) r13
            java.lang.Object r14 = r3.L$0
            androidx.paging.SeparatorState r14 = (androidx.paging.SeparatorState) r14
            kotlin.ResultKt.throwOnFailure(r2)
            r0 = r5
            r15 = r11
            r18 = r12
            r19 = r13
            r20 = r14
            r11 = r7
            r12 = r8
            r13 = r9
            r14 = r10
            r22 = r6
            r6 = r2
            r2 = r22
            goto L_0x05f7
        L_0x0119:
            int r1 = r3.I$0
            boolean r5 = r3.Z$0
            java.lang.Object r6 = r3.L$7
            java.util.ArrayList r6 = (java.util.ArrayList) r6
            java.lang.Object r7 = r3.L$6
            java.lang.Integer r7 = (java.lang.Integer) r7
            java.lang.Object r8 = r3.L$5
            androidx.paging.TransformablePage r8 = (androidx.paging.TransformablePage) r8
            java.lang.Object r9 = r3.L$4
            java.lang.Integer r9 = (java.lang.Integer) r9
            java.lang.Object r10 = r3.L$3
            java.util.ArrayList r10 = (java.util.ArrayList) r10
            java.lang.Object r11 = r3.L$2
            java.util.ArrayList r11 = (java.util.ArrayList) r11
            java.lang.Object r12 = r3.L$1
            androidx.paging.PageEvent$Insert r12 = (androidx.paging.PageEvent.Insert) r12
            java.lang.Object r13 = r3.L$0
            androidx.paging.SeparatorState r13 = (androidx.paging.SeparatorState) r13
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x0564
        L_0x0142:
            int r1 = r3.I$0
            boolean r5 = r3.Z$0
            java.lang.Object r6 = r3.L$8
            androidx.paging.TransformablePage r6 = (androidx.paging.TransformablePage) r6
            java.lang.Object r7 = r3.L$7
            java.lang.Integer r7 = (java.lang.Integer) r7
            java.lang.Object r8 = r3.L$6
            androidx.paging.TransformablePage r8 = (androidx.paging.TransformablePage) r8
            java.lang.Object r9 = r3.L$5
            java.lang.Integer r9 = (java.lang.Integer) r9
            java.lang.Object r10 = r3.L$4
            androidx.paging.TransformablePage r10 = (androidx.paging.TransformablePage) r10
            java.lang.Object r11 = r3.L$3
            java.util.ArrayList r11 = (java.util.ArrayList) r11
            java.lang.Object r12 = r3.L$2
            java.util.ArrayList r12 = (java.util.ArrayList) r12
            java.lang.Object r13 = r3.L$1
            androidx.paging.PageEvent$Insert r13 = (androidx.paging.PageEvent.Insert) r13
            java.lang.Object r14 = r3.L$0
            androidx.paging.SeparatorState r14 = (androidx.paging.SeparatorState) r14
            kotlin.ResultKt.throwOnFailure(r2)
            r0 = r5
            r15 = r7
            r18 = r13
            r19 = r14
            r7 = r6
            r13 = r11
            r14 = r12
            r6 = r2
            r2 = r8
        L_0x0178:
            r11 = r9
            r12 = r10
            goto L_0x0501
        L_0x017c:
            int r1 = r3.I$2
            int r5 = r3.I$1
            int r6 = r3.I$0
            boolean r7 = r3.Z$0
            java.lang.Object r8 = r3.L$8
            java.util.ArrayList r8 = (java.util.ArrayList) r8
            java.lang.Object r9 = r3.L$7
            java.lang.Integer r9 = (java.lang.Integer) r9
            java.lang.Object r10 = r3.L$6
            androidx.paging.TransformablePage r10 = (androidx.paging.TransformablePage) r10
            java.lang.Object r11 = r3.L$5
            java.lang.Integer r11 = (java.lang.Integer) r11
            java.lang.Object r12 = r3.L$4
            androidx.paging.TransformablePage r12 = (androidx.paging.TransformablePage) r12
            java.lang.Object r13 = r3.L$3
            java.util.ArrayList r13 = (java.util.ArrayList) r13
            java.lang.Object r14 = r3.L$2
            java.util.ArrayList r14 = (java.util.ArrayList) r14
            java.lang.Object r15 = r3.L$1
            androidx.paging.PageEvent$Insert r15 = (androidx.paging.PageEvent.Insert) r15
            r25 = r1
            java.lang.Object r1 = r3.L$0
            androidx.paging.SeparatorState r1 = (androidx.paging.SeparatorState) r1
            kotlin.ResultKt.throwOnFailure(r2)
            r0 = r7
            r7 = r9
            r9 = r11
            r11 = r13
            r13 = r15
            r15 = r1
            r1 = r25
            goto L_0x0490
        L_0x01b7:
            int r1 = r3.I$0
            boolean r5 = r3.Z$0
            java.lang.Object r6 = r3.L$9
            java.util.List r6 = (java.util.List) r6
            java.lang.Object r7 = r3.L$8
            androidx.paging.TransformablePage r7 = (androidx.paging.TransformablePage) r7
            java.lang.Object r8 = r3.L$7
            java.lang.Integer r8 = (java.lang.Integer) r8
            java.lang.Object r9 = r3.L$6
            androidx.paging.TransformablePage r9 = (androidx.paging.TransformablePage) r9
            java.lang.Object r10 = r3.L$5
            java.lang.Integer r10 = (java.lang.Integer) r10
            java.lang.Object r11 = r3.L$4
            androidx.paging.TransformablePage r11 = (androidx.paging.TransformablePage) r11
            java.lang.Object r12 = r3.L$3
            java.util.ArrayList r12 = (java.util.ArrayList) r12
            java.lang.Object r13 = r3.L$2
            java.util.ArrayList r13 = (java.util.ArrayList) r13
            java.lang.Object r14 = r3.L$1
            androidx.paging.PageEvent$Insert r14 = (androidx.paging.PageEvent.Insert) r14
            java.lang.Object r15 = r3.L$0
            androidx.paging.SeparatorState r15 = (androidx.paging.SeparatorState) r15
            kotlin.ResultKt.throwOnFailure(r2)
            r0 = r5
            r5 = r6
            r18 = r13
            r19 = r15
            r6 = r2
            r2 = r8
            r13 = r11
            r15 = r12
            r8 = r7
            r11 = r9
            r12 = r10
            goto L_0x040f
        L_0x01f5:
            java.lang.Object r1 = r3.L$1
            androidx.paging.PageEvent$Insert r1 = (androidx.paging.PageEvent.Insert) r1
            java.lang.Object r3 = r3.L$0
            androidx.paging.SeparatorState r3 = (androidx.paging.SeparatorState) r3
            kotlin.ResultKt.throwOnFailure(r2)
            r4 = 0
            r5 = 1
            goto L_0x02e3
        L_0x0204:
            kotlin.ResultKt.throwOnFailure(r2)
            androidx.paging.TerminalSeparatorType r2 = r0.terminalSeparatorType
            boolean r2 = r0.terminatesStart(r1, r2)
            androidx.paging.TerminalSeparatorType r5 = r0.terminalSeparatorType
            boolean r5 = r0.terminatesEnd(r1, r5)
            java.util.List r6 = r25.getPages()
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            boolean r7 = r6 instanceof java.util.Collection
            if (r7 == 0) goto L_0x0228
            r7 = r6
            java.util.Collection r7 = (java.util.Collection) r7
            boolean r7 = r7.isEmpty()
            if (r7 == 0) goto L_0x0228
        L_0x0226:
            r6 = 1
            goto L_0x0243
        L_0x0228:
            java.util.Iterator r6 = r6.iterator()
        L_0x022c:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x0226
            java.lang.Object r7 = r6.next()
            androidx.paging.TransformablePage r7 = (androidx.paging.TransformablePage) r7
            java.util.List r7 = r7.getData()
            boolean r7 = r7.isEmpty()
            if (r7 != 0) goto L_0x022c
            r6 = 0
        L_0x0243:
            boolean r7 = r0.headerAdded
            if (r7 == 0) goto L_0x025e
            androidx.paging.LoadType r7 = r25.getLoadType()
            androidx.paging.LoadType r8 = androidx.paging.LoadType.PREPEND
            if (r7 != r8) goto L_0x025e
            if (r6 == 0) goto L_0x0252
            goto L_0x025e
        L_0x0252:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "Additional prepend event after prepend state is done"
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L_0x025e:
            boolean r7 = r0.footerAdded
            if (r7 == 0) goto L_0x0279
            androidx.paging.LoadType r7 = r25.getLoadType()
            androidx.paging.LoadType r8 = androidx.paging.LoadType.APPEND
            if (r7 != r8) goto L_0x0279
            if (r6 == 0) goto L_0x026d
            goto L_0x0279
        L_0x026d:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "Additional append event after append state is done"
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L_0x0279:
            androidx.paging.MutableLoadStateCollection r7 = r0.sourceStates
            androidx.paging.LoadStates r8 = r25.getSourceLoadStates()
            r7.set(r8)
            androidx.paging.LoadStates r7 = r25.getMediatorLoadStates()
            r0.mediatorStates = r7
            androidx.paging.LoadType r7 = r25.getLoadType()
            androidx.paging.LoadType r8 = androidx.paging.LoadType.APPEND
            if (r7 == r8) goto L_0x0296
            int r7 = r25.getPlaceholdersBefore()
            r0.placeholdersBefore = r7
        L_0x0296:
            androidx.paging.LoadType r7 = r25.getLoadType()
            androidx.paging.LoadType r8 = androidx.paging.LoadType.PREPEND
            if (r7 == r8) goto L_0x02a4
            int r7 = r25.getPlaceholdersAfter()
            r0.placeholdersAfter = r7
        L_0x02a4:
            if (r6 == 0) goto L_0x0335
            if (r2 != 0) goto L_0x02af
            if (r5 != 0) goto L_0x02af
            androidx.paging.PageEvent$Insert r1 = r24.asRType(r25)
            return r1
        L_0x02af:
            boolean r7 = r0.headerAdded
            if (r7 == 0) goto L_0x02bc
            boolean r7 = r0.footerAdded
            if (r7 == 0) goto L_0x02bc
            androidx.paging.PageEvent$Insert r1 = r24.asRType(r25)
            return r1
        L_0x02bc:
            java.util.List<androidx.paging.TransformablePage<T>> r7 = r0.pageStash
            boolean r7 = r7.isEmpty()
            if (r7 == 0) goto L_0x0335
            if (r2 == 0) goto L_0x031d
            if (r5 == 0) goto L_0x031d
            boolean r6 = r0.headerAdded
            if (r6 != 0) goto L_0x031d
            boolean r6 = r0.footerAdded
            if (r6 != 0) goto L_0x031d
            kotlin.jvm.functions.Function3<T, T, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r2 = r0.generator
            r3.L$0 = r0
            r3.L$1 = r1
            r5 = 1
            r3.label = r5
            r6 = 0
            java.lang.Object r2 = r2.invoke(r6, r6, r3)
            if (r2 != r4) goto L_0x02e1
            return r4
        L_0x02e1:
            r3 = r0
            r4 = 0
        L_0x02e3:
            r3.endTerminalSeparatorDeferred = r4
            r3.startTerminalSeparatorDeferred = r4
            r3.headerAdded = r5
            r3.footerAdded = r5
            if (r2 != 0) goto L_0x02f2
            androidx.paging.PageEvent$Insert r1 = r3.asRType(r1)
            goto L_0x031c
        L_0x02f2:
            androidx.paging.LoadType r3 = r1.getLoadType()
            r1.getPages()
            int[] r5 = new int[]{r4}
            androidx.paging.TransformablePage r2 = androidx.paging.SeparatorsKt.separatorPage(r2, r5, r4, r4)
            java.util.List r4 = kotlin.collections.CollectionsKt.listOf(r2)
            int r5 = r1.getPlaceholdersBefore()
            int r6 = r1.getPlaceholdersAfter()
            androidx.paging.LoadStates r7 = r1.getSourceLoadStates()
            androidx.paging.LoadStates r8 = r1.getMediatorLoadStates()
            androidx.paging.PageEvent$Insert r1 = new androidx.paging.PageEvent$Insert
            r9 = 0
            r2 = r1
            r2.<init>(r3, r4, r5, r6, r7, r8, r9)
        L_0x031c:
            return r1
        L_0x031d:
            if (r5 == 0) goto L_0x0327
            boolean r3 = r0.footerAdded
            if (r3 != 0) goto L_0x0327
            r3 = 1
            r0.endTerminalSeparatorDeferred = r3
            goto L_0x0328
        L_0x0327:
            r3 = 1
        L_0x0328:
            if (r2 == 0) goto L_0x0330
            boolean r2 = r0.headerAdded
            if (r2 != 0) goto L_0x0330
            r0.startTerminalSeparatorDeferred = r3
        L_0x0330:
            androidx.paging.PageEvent$Insert r1 = r24.asRType(r25)
            return r1
        L_0x0335:
            java.util.ArrayList r13 = new java.util.ArrayList
            java.util.List r7 = r25.getPages()
            int r7 = r7.size()
            r13.<init>(r7)
            java.util.ArrayList r12 = new java.util.ArrayList
            java.util.List r7 = r25.getPages()
            int r7 = r7.size()
            r12.<init>(r7)
            if (r6 != 0) goto L_0x03b1
            r7 = 0
        L_0x0352:
            java.util.List r8 = r25.getPages()
            int r8 = kotlin.collections.CollectionsKt.getLastIndex(r8)
            if (r7 >= r8) goto L_0x0373
            java.util.List r8 = r25.getPages()
            java.lang.Object r8 = r8.get(r7)
            androidx.paging.TransformablePage r8 = (androidx.paging.TransformablePage) r8
            java.util.List r8 = r8.getData()
            boolean r8 = r8.isEmpty()
            if (r8 == 0) goto L_0x0373
            int r7 = r7 + 1
            goto L_0x0352
        L_0x0373:
            java.lang.Integer r8 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r7)
            java.util.List r9 = r25.getPages()
            java.lang.Object r7 = r9.get(r7)
            androidx.paging.TransformablePage r7 = (androidx.paging.TransformablePage) r7
            java.util.List r9 = r25.getPages()
            int r9 = kotlin.collections.CollectionsKt.getLastIndex(r9)
        L_0x0389:
            if (r9 <= 0) goto L_0x03a2
            java.util.List r10 = r25.getPages()
            java.lang.Object r10 = r10.get(r9)
            androidx.paging.TransformablePage r10 = (androidx.paging.TransformablePage) r10
            java.util.List r10 = r10.getData()
            boolean r10 = r10.isEmpty()
            if (r10 == 0) goto L_0x03a2
            int r9 = r9 + -1
            goto L_0x0389
        L_0x03a2:
            java.lang.Integer r10 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r9)
            java.util.List r11 = r25.getPages()
            java.lang.Object r9 = r11.get(r9)
            androidx.paging.TransformablePage r9 = (androidx.paging.TransformablePage) r9
            goto L_0x03b5
        L_0x03b1:
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
        L_0x03b5:
            if (r2 == 0) goto L_0x0434
            boolean r2 = r0.headerAdded
            if (r2 != 0) goto L_0x0434
            r2 = 1
            r0.headerAdded = r2
            if (r6 == 0) goto L_0x03c9
            java.util.List<androidx.paging.TransformablePage<T>> r2 = r0.pageStash
            java.lang.Object r2 = kotlin.collections.CollectionsKt.first(r2)
            androidx.paging.TransformablePage r2 = (androidx.paging.TransformablePage) r2
            goto L_0x03cd
        L_0x03c9:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            r2 = r7
        L_0x03cd:
            r11 = r13
            java.util.List r11 = (java.util.List) r11
            kotlin.jvm.functions.Function3<T, T, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r14 = r0.generator
            java.util.List r15 = r2.getData()
            java.lang.Object r15 = kotlin.collections.CollectionsKt.first(r15)
            r3.L$0 = r0
            r3.L$1 = r1
            r3.L$2 = r13
            r3.L$3 = r12
            r3.L$4 = r7
            r3.L$5 = r8
            r3.L$6 = r9
            r3.L$7 = r10
            r3.L$8 = r2
            r3.L$9 = r11
            r3.Z$0 = r5
            r3.I$0 = r6
            r0 = 2
            r3.label = r0
            r0 = 0
            java.lang.Object r14 = r14.invoke(r0, r15, r3)
            if (r14 != r4) goto L_0x03fd
            return r4
        L_0x03fd:
            r19 = r24
            r0 = r5
            r5 = r11
            r15 = r12
            r18 = r13
            r13 = r7
            r12 = r8
            r11 = r9
            r8 = r2
            r2 = r10
            r22 = r14
            r14 = r1
            r1 = r6
            r6 = r22
        L_0x040f:
            int r9 = r8.getHintOriginalPageOffset()
            java.util.List r7 = r8.getHintOriginalIndices()
            if (r7 == 0) goto L_0x0425
            java.lang.Object r7 = kotlin.collections.CollectionsKt.first(r7)
            java.lang.Number r7 = (java.lang.Number) r7
            int r7 = r7.intValue()
            r10 = r7
            goto L_0x0426
        L_0x0425:
            r10 = 0
        L_0x0426:
            r7 = 0
            androidx.paging.SeparatorsKt.addSeparatorPage(r5, r6, r7, r8, r9, r10)
            r5 = r0
            r6 = r1
            r10 = r2
            r9 = r11
            r8 = r12
            r7 = r13
            r12 = r15
            r13 = r18
            goto L_0x0437
        L_0x0434:
            r19 = r24
            r14 = r1
        L_0x0437:
            if (r6 != 0) goto L_0x07ac
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)
            int r0 = r8.intValue()
            r1 = r0
            r0 = r5
            r11 = r12
            r12 = r13
            r13 = r14
            r14 = r19
            r5 = 0
            r22 = r10
            r10 = r7
            r7 = r22
            r23 = r9
            r9 = r8
            r8 = r23
        L_0x0452:
            if (r5 >= r1) goto L_0x049a
            java.util.List r2 = r13.getPages()
            java.lang.Object r2 = r2.get(r5)
            androidx.paging.TransformablePage r2 = (androidx.paging.TransformablePage) r2
            kotlin.jvm.functions.Function3<T, T, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r15 = r14.generator
            r3.L$0 = r14
            r3.L$1 = r13
            r3.L$2 = r12
            r3.L$3 = r11
            r3.L$4 = r10
            r3.L$5 = r9
            r3.L$6 = r8
            r3.L$7 = r7
            r3.L$8 = r12
            r18 = r7
            r7 = 0
            r3.L$9 = r7
            r3.Z$0 = r0
            r3.I$0 = r6
            r3.I$1 = r5
            r3.I$2 = r1
            r7 = 3
            r3.label = r7
            java.lang.Object r2 = androidx.paging.SeparatorsKt.insertInternalSeparators(r2, r15, r3)
            if (r2 != r4) goto L_0x0489
            return r4
        L_0x0489:
            r15 = r14
            r7 = r18
            r14 = r12
            r12 = r10
            r10 = r8
            r8 = r14
        L_0x0490:
            r8.add(r2)
            r2 = 1
            int r5 = r5 + r2
            r8 = r10
            r10 = r12
            r12 = r14
            r14 = r15
            goto L_0x0452
        L_0x049a:
            r18 = r7
            r2 = 1
            androidx.paging.LoadType r1 = r13.getLoadType()
            androidx.paging.LoadType r5 = androidx.paging.LoadType.APPEND
            if (r1 != r5) goto L_0x052a
            java.util.List<androidx.paging.TransformablePage<T>> r1 = r14.pageStash
            java.util.Collection r1 = (java.util.Collection) r1
            boolean r1 = r1.isEmpty()
            r1 = r1 ^ r2
            if (r1 == 0) goto L_0x052a
            java.util.List<androidx.paging.TransformablePage<T>> r1 = r14.pageStash
            java.lang.Object r1 = kotlin.collections.CollectionsKt.last(r1)
            androidx.paging.TransformablePage r1 = (androidx.paging.TransformablePage) r1
            kotlin.jvm.functions.Function3<T, T, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r2 = r14.generator
            java.util.List r5 = r1.getData()
            java.lang.Object r5 = kotlin.collections.CollectionsKt.last(r5)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10)
            java.util.List r7 = r10.getData()
            java.lang.Object r7 = kotlin.collections.CollectionsKt.first(r7)
            r3.L$0 = r14
            r3.L$1 = r13
            r3.L$2 = r12
            r3.L$3 = r11
            r3.L$4 = r10
            r3.L$5 = r9
            r3.L$6 = r8
            r15 = r18
            r3.L$7 = r15
            r3.L$8 = r1
            r25 = r1
            r1 = 0
            r3.L$9 = r1
            r3.Z$0 = r0
            r3.I$0 = r6
            r1 = 4
            r3.label = r1
            java.lang.Object r2 = r2.invoke(r5, r7, r3)
            if (r2 != r4) goto L_0x04f4
            return r4
        L_0x04f4:
            r7 = r25
            r1 = r6
            r18 = r13
            r19 = r14
            r6 = r2
            r2 = r8
            r13 = r11
            r14 = r12
            goto L_0x0178
        L_0x0501:
            r5 = r14
            java.util.List r5 = (java.util.List) r5
            int r9 = r12.getHintOriginalPageOffset()
            java.util.List r8 = r12.getHintOriginalIndices()
            if (r8 == 0) goto L_0x051a
            java.lang.Object r8 = kotlin.collections.CollectionsKt.first(r8)
            java.lang.Number r8 = (java.lang.Number) r8
            int r8 = r8.intValue()
            r10 = r8
            goto L_0x051b
        L_0x051a:
            r10 = 0
        L_0x051b:
            r8 = r12
            androidx.paging.SeparatorsKt.addSeparatorPage(r5, r6, r7, r8, r9, r10)
            r5 = r0
            r8 = r2
            r9 = r11
            r10 = r13
            r6 = r14
            r7 = r15
            r13 = r18
            r14 = r19
            goto L_0x0532
        L_0x052a:
            r15 = r18
            r5 = r0
            r1 = r6
            r6 = r12
            r7 = r15
            r12 = r10
            r10 = r11
        L_0x0532:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r12)
            androidx.paging.TransformablePage r0 = r14.transformablePageToStash(r12)
            r10.add(r0)
            kotlin.jvm.functions.Function3<T, T, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r0 = r14.generator
            r3.L$0 = r14
            r3.L$1 = r13
            r3.L$2 = r6
            r3.L$3 = r10
            r3.L$4 = r9
            r3.L$5 = r8
            r3.L$6 = r7
            r3.L$7 = r6
            r2 = 0
            r3.L$8 = r2
            r3.L$9 = r2
            r3.Z$0 = r5
            r3.I$0 = r1
            r2 = 5
            r3.label = r2
            java.lang.Object r2 = androidx.paging.SeparatorsKt.insertInternalSeparators(r12, r0, r3)
            if (r2 != r4) goto L_0x0561
            return r4
        L_0x0561:
            r11 = r6
            r12 = r13
            r13 = r14
        L_0x0564:
            r6.add(r2)
            java.util.List r0 = r12.getPages()
            int r2 = r9.intValue()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            int r6 = r7.intValue()
            r9 = 1
            int r6 = r6 + r9
            java.util.List r0 = r0.subList(r2, r6)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x07a4
            java.lang.Object r2 = r0.next()
            r9 = r7
            r14 = r13
            r13 = r12
            r12 = r11
            r11 = r10
            r10 = r8
            r8 = r0
        L_0x0593:
            boolean r0 = r8.hasNext()
            if (r0 == 0) goto L_0x06ba
            java.lang.Object r0 = r8.next()
            r7 = r0
            androidx.paging.TransformablePage r7 = (androidx.paging.TransformablePage) r7
            r6 = r2
            androidx.paging.TransformablePage r6 = (androidx.paging.TransformablePage) r6
            java.util.List r0 = r7.getData()
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            r2 = 1
            r0 = r0 ^ r2
            if (r0 == 0) goto L_0x0655
            kotlin.jvm.functions.Function3<T, T, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r0 = r14.generator
            java.util.List r2 = r6.getData()
            java.lang.Object r2 = kotlin.collections.CollectionsKt.last(r2)
            java.util.List r15 = r7.getData()
            java.lang.Object r15 = kotlin.collections.CollectionsKt.first(r15)
            r3.L$0 = r14
            r3.L$1 = r13
            r3.L$2 = r12
            r3.L$3 = r11
            r3.L$4 = r10
            r3.L$5 = r9
            r3.L$6 = r8
            r3.L$7 = r7
            r3.L$8 = r6
            r25 = r6
            r6 = 0
            r3.L$9 = r6
            r3.Z$0 = r5
            r3.I$0 = r1
            r6 = 6
            r3.label = r6
            java.lang.Object r2 = r0.invoke(r2, r15, r3)
            if (r2 != r4) goto L_0x05e8
            return r4
        L_0x05e8:
            r6 = r2
            r0 = r5
            r15 = r11
            r18 = r12
            r19 = r13
            r20 = r14
            r2 = r25
            r11 = r7
            r12 = r8
            r13 = r9
            r14 = r10
        L_0x05f7:
            r5 = r18
            java.util.List r5 = (java.util.List) r5
            androidx.paging.LoadType r7 = r19.getLoadType()
            androidx.paging.LoadType r8 = androidx.paging.LoadType.PREPEND
            if (r7 != r8) goto L_0x0608
            int r7 = r2.getHintOriginalPageOffset()
            goto L_0x060c
        L_0x0608:
            int r7 = r11.getHintOriginalPageOffset()
        L_0x060c:
            r9 = r7
            androidx.paging.LoadType r7 = r19.getLoadType()
            androidx.paging.LoadType r8 = androidx.paging.LoadType.PREPEND
            if (r7 != r8) goto L_0x062f
            java.util.List r7 = r2.getHintOriginalIndices()
            if (r7 == 0) goto L_0x0626
            java.lang.Object r7 = kotlin.collections.CollectionsKt.last(r7)
            java.lang.Number r7 = (java.lang.Number) r7
            int r7 = r7.intValue()
            goto L_0x063f
        L_0x0626:
            java.util.List r7 = r2.getData()
            int r7 = kotlin.collections.CollectionsKt.getLastIndex(r7)
            goto L_0x063f
        L_0x062f:
            java.util.List r7 = r11.getHintOriginalIndices()
            if (r7 == 0) goto L_0x0641
            java.lang.Object r7 = kotlin.collections.CollectionsKt.first(r7)
            java.lang.Number r7 = (java.lang.Number) r7
            int r7 = r7.intValue()
        L_0x063f:
            r10 = r7
            goto L_0x0642
        L_0x0641:
            r10 = 0
        L_0x0642:
            r7 = r2
            r8 = r11
            androidx.paging.SeparatorsKt.addSeparatorPage(r5, r6, r7, r8, r9, r10)
            r5 = r0
            r9 = r2
            r10 = r11
            r11 = r12
            r12 = r13
            r13 = r14
            r14 = r15
            r6 = r18
            r8 = r19
            r7 = r20
            goto L_0x0664
        L_0x0655:
            r25 = r6
            r6 = r12
            r12 = r9
            r9 = r25
            r22 = r10
            r10 = r7
            r7 = r14
            r14 = r11
            r11 = r8
            r8 = r13
            r13 = r22
        L_0x0664:
            java.util.List r0 = r10.getData()
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            r2 = 1
            r0 = r0 ^ r2
            if (r0 == 0) goto L_0x0679
            androidx.paging.TransformablePage r0 = r7.transformablePageToStash(r10)
            r14.add(r0)
        L_0x0679:
            kotlin.jvm.functions.Function3<T, T, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r0 = r7.generator
            r3.L$0 = r7
            r3.L$1 = r8
            r3.L$2 = r6
            r3.L$3 = r14
            r3.L$4 = r13
            r3.L$5 = r12
            r3.L$6 = r11
            r3.L$7 = r10
            r3.L$8 = r9
            r3.L$9 = r6
            r3.Z$0 = r5
            r3.I$0 = r1
            r2 = 7
            r3.label = r2
            java.lang.Object r2 = androidx.paging.SeparatorsKt.insertInternalSeparators(r10, r0, r3)
            if (r2 != r4) goto L_0x069d
            return r4
        L_0x069d:
            r15 = r6
            goto L_0x00d5
        L_0x06a0:
            r6.add(r2)
            java.util.List r2 = r10.getData()
            java.util.Collection r2 = (java.util.Collection) r2
            boolean r2 = r2.isEmpty()
            r6 = 1
            r2 = r2 ^ r6
            if (r2 == 0) goto L_0x06b3
            r2 = r10
            goto L_0x06b4
        L_0x06b3:
            r2 = r9
        L_0x06b4:
            r9 = r12
            r10 = r13
            r12 = r15
            r13 = r0
            goto L_0x0593
        L_0x06ba:
            androidx.paging.LoadType r0 = r13.getLoadType()
            androidx.paging.LoadType r2 = androidx.paging.LoadType.PREPEND
            if (r0 != r2) goto L_0x073e
            java.util.List<androidx.paging.TransformablePage<T>> r0 = r14.pageStash
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            r2 = 1
            r0 = r0 ^ r2
            if (r0 == 0) goto L_0x073e
            java.util.List<androidx.paging.TransformablePage<T>> r0 = r14.pageStash
            java.lang.Object r0 = kotlin.collections.CollectionsKt.first(r0)
            r6 = r0
            androidx.paging.TransformablePage r6 = (androidx.paging.TransformablePage) r6
            kotlin.jvm.functions.Function3<T, T, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r0 = r14.generator
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10)
            java.util.List r2 = r10.getData()
            java.lang.Object r2 = kotlin.collections.CollectionsKt.last(r2)
            java.util.List r7 = r6.getData()
            java.lang.Object r7 = kotlin.collections.CollectionsKt.first(r7)
            r3.L$0 = r14
            r3.L$1 = r13
            r3.L$2 = r12
            r3.L$3 = r11
            r3.L$4 = r10
            r3.L$5 = r9
            r3.L$6 = r6
            r8 = 0
            r3.L$7 = r8
            r3.L$8 = r8
            r3.L$9 = r8
            r3.Z$0 = r5
            r3.I$0 = r1
            r8 = 8
            r3.label = r8
            java.lang.Object r2 = r0.invoke(r2, r7, r3)
            if (r2 != r4) goto L_0x009b
            return r4
        L_0x0710:
            r5 = r13
            java.util.List r5 = (java.util.List) r5
            int r9 = r11.getHintOriginalPageOffset()
            java.util.List r7 = r11.getHintOriginalIndices()
            if (r7 == 0) goto L_0x0728
            java.lang.Object r7 = kotlin.collections.CollectionsKt.last(r7)
            java.lang.Number r7 = (java.lang.Number) r7
            int r7 = r7.intValue()
            goto L_0x0730
        L_0x0728:
            java.util.List r7 = r11.getData()
            int r7 = kotlin.collections.CollectionsKt.getLastIndex(r7)
        L_0x0730:
            r10 = r7
            r7 = r11
            androidx.paging.SeparatorsKt.addSeparatorPage(r5, r6, r7, r8, r9, r10)
            r5 = r0
            r6 = r1
            r9 = r2
            r10 = r11
            r11 = r12
            r12 = r13
            r13 = r14
            r14 = r15
            goto L_0x073f
        L_0x073e:
            r6 = r1
        L_0x073f:
            int r0 = r9.intValue()
            r1 = 1
            int r0 = r0 + r1
            java.util.List r1 = r13.getPages()
            int r1 = kotlin.collections.CollectionsKt.getLastIndex(r1)
            if (r0 > r1) goto L_0x079d
            r9 = r6
            r15 = r13
            r6 = r14
            r13 = r11
            r11 = r12
            r12 = r10
            r10 = r5
            r5 = r0
        L_0x0757:
            java.util.List r0 = r15.getPages()
            java.lang.Object r0 = r0.get(r5)
            androidx.paging.TransformablePage r0 = (androidx.paging.TransformablePage) r0
            kotlin.jvm.functions.Function3<T, T, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r2 = r6.generator
            r3.L$0 = r6
            r3.L$1 = r15
            r3.L$2 = r11
            r3.L$3 = r13
            r3.L$4 = r12
            r3.L$5 = r11
            r7 = 0
            r3.L$6 = r7
            r3.L$7 = r7
            r3.L$8 = r7
            r3.L$9 = r7
            r3.Z$0 = r10
            r3.I$0 = r9
            r3.I$1 = r5
            r3.I$2 = r1
            r7 = 9
            r3.label = r7
            java.lang.Object r2 = androidx.paging.SeparatorsKt.insertInternalSeparators(r0, r2, r3)
            if (r2 != r4) goto L_0x078b
            return r4
        L_0x078b:
            r14 = r11
        L_0x078c:
            r11.add(r2)
            if (r5 == r1) goto L_0x0795
            int r5 = r5 + 1
            r11 = r14
            goto L_0x0757
        L_0x0795:
            r0 = r3
            r3 = r6
            r6 = r9
            r5 = r10
            r9 = r12
            r7 = r14
            r8 = r15
            goto L_0x07b2
        L_0x079d:
            r0 = r3
            r9 = r10
            r7 = r12
            r8 = r13
            r3 = r14
            r13 = r11
            goto L_0x07b2
        L_0x07a4:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            java.lang.String r1 = "Empty collection can't be reduced."
            r0.<init>(r1)
            throw r0
        L_0x07ac:
            r0 = r3
            r7 = r13
            r8 = r14
            r3 = r19
            r13 = r12
        L_0x07b2:
            if (r5 == 0) goto L_0x0821
            boolean r1 = r3.footerAdded
            if (r1 != 0) goto L_0x0821
            r1 = 1
            r3.footerAdded = r1
            if (r6 == 0) goto L_0x07c6
            java.util.List<androidx.paging.TransformablePage<T>> r1 = r3.pageStash
            java.lang.Object r1 = kotlin.collections.CollectionsKt.last(r1)
            androidx.paging.TransformablePage r1 = (androidx.paging.TransformablePage) r1
            goto L_0x07ca
        L_0x07c6:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)
            r1 = r9
        L_0x07ca:
            r2 = r7
            java.util.List r2 = (java.util.List) r2
            kotlin.jvm.functions.Function3<T, T, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r5 = r3.generator
            java.util.List r6 = r1.getData()
            java.lang.Object r6 = kotlin.collections.CollectionsKt.last(r6)
            r0.L$0 = r3
            r0.L$1 = r8
            r0.L$2 = r7
            r0.L$3 = r13
            r0.L$4 = r1
            r0.L$5 = r2
            r9 = 0
            r0.L$6 = r9
            r0.L$7 = r9
            r0.L$8 = r9
            r0.L$9 = r9
            r10 = 10
            r0.label = r10
            java.lang.Object r0 = r5.invoke(r6, r9, r0)
            if (r0 != r4) goto L_0x07f7
            return r4
        L_0x07f7:
            r15 = r0
            r16 = r1
            r14 = r2
            r5 = r13
        L_0x07fc:
            int r18 = r16.getHintOriginalPageOffset()
            java.util.List r0 = r16.getHintOriginalIndices()
            if (r0 == 0) goto L_0x0811
            java.lang.Object r0 = kotlin.collections.CollectionsKt.last(r0)
            java.lang.Number r0 = (java.lang.Number) r0
            int r0 = r0.intValue()
            goto L_0x0819
        L_0x0811:
            java.util.List r0 = r16.getData()
            int r0 = kotlin.collections.CollectionsKt.getLastIndex(r0)
        L_0x0819:
            r19 = r0
            r17 = 0
            androidx.paging.SeparatorsKt.addSeparatorPage(r14, r15, r16, r17, r18, r19)
            r13 = r5
        L_0x0821:
            r0 = 0
            r3.endTerminalSeparatorDeferred = r0
            r3.startTerminalSeparatorDeferred = r0
            androidx.paging.LoadType r1 = r8.getLoadType()
            androidx.paging.LoadType r2 = androidx.paging.LoadType.APPEND
            if (r1 != r2) goto L_0x0836
            java.util.List<androidx.paging.TransformablePage<T>> r0 = r3.pageStash
            java.util.Collection r13 = (java.util.Collection) r13
            r0.addAll(r13)
            goto L_0x083d
        L_0x0836:
            java.util.List<androidx.paging.TransformablePage<T>> r1 = r3.pageStash
            java.util.Collection r13 = (java.util.Collection) r13
            r1.addAll(r0, r13)
        L_0x083d:
            androidx.paging.LoadType r15 = r8.getLoadType()
            r8.getPages()
            r16 = r7
            java.util.List r16 = (java.util.List) r16
            int r17 = r8.getPlaceholdersBefore()
            int r18 = r8.getPlaceholdersAfter()
            androidx.paging.LoadStates r19 = r8.getSourceLoadStates()
            androidx.paging.LoadStates r20 = r8.getMediatorLoadStates()
            androidx.paging.PageEvent$Insert r0 = new androidx.paging.PageEvent$Insert
            r21 = 0
            r14 = r0
            r14.<init>(r15, r16, r17, r18, r19, r20, r21)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.SeparatorState.onInsert(androidx.paging.PageEvent$Insert, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final PageEvent.Drop<R> onDrop(PageEvent.Drop<T> drop) {
        Intrinsics.checkNotNullParameter(drop, NotificationCompat.CATEGORY_EVENT);
        this.sourceStates.set(drop.getLoadType(), LoadState.NotLoading.Companion.getIncomplete$paging_common());
        if (drop.getLoadType() == LoadType.PREPEND) {
            this.placeholdersBefore = drop.getPlaceholdersRemaining();
            this.headerAdded = false;
        } else if (drop.getLoadType() == LoadType.APPEND) {
            this.placeholdersAfter = drop.getPlaceholdersRemaining();
            this.footerAdded = false;
        }
        if (this.pageStash.isEmpty()) {
            if (drop.getLoadType() == LoadType.PREPEND) {
                this.startTerminalSeparatorDeferred = false;
            } else {
                this.endTerminalSeparatorDeferred = false;
            }
        }
        CollectionsKt.removeAll(this.pageStash, new SeparatorState$onDrop$1(new IntRange(drop.getMinPageOffset(), drop.getMaxPageOffset())));
        return drop;
    }

    public final Object onLoadStateUpdate(PageEvent.LoadStateUpdate<T> loadStateUpdate, Continuation<? super PageEvent<R>> continuation) {
        LoadStates loadStates = this.mediatorStates;
        if (!Intrinsics.areEqual((Object) this.sourceStates.snapshot(), (Object) loadStateUpdate.getSource()) || !Intrinsics.areEqual((Object) loadStates, (Object) loadStateUpdate.getMediator())) {
            this.sourceStates.set(loadStateUpdate.getSource());
            this.mediatorStates = loadStateUpdate.getMediator();
            LoadState loadState = null;
            if (loadStateUpdate.getMediator() != null && loadStateUpdate.getMediator().getPrepend().getEndOfPaginationReached()) {
                if (!Intrinsics.areEqual((Object) loadStates != null ? loadStates.getPrepend() : null, (Object) loadStateUpdate.getMediator().getPrepend())) {
                    return onInsert(PageEvent.Insert.Companion.Prepend(CollectionsKt.emptyList(), this.placeholdersBefore, loadStateUpdate.getSource(), loadStateUpdate.getMediator()), continuation);
                }
            }
            if (loadStateUpdate.getMediator() != null && loadStateUpdate.getMediator().getAppend().getEndOfPaginationReached()) {
                if (loadStates != null) {
                    loadState = loadStates.getAppend();
                }
                if (!Intrinsics.areEqual((Object) loadState, (Object) loadStateUpdate.getMediator().getAppend())) {
                    return onInsert(PageEvent.Insert.Companion.Append(CollectionsKt.emptyList(), this.placeholdersAfter, loadStateUpdate.getSource(), loadStateUpdate.getMediator()), continuation);
                }
            }
            Intrinsics.checkNotNull(loadStateUpdate, "null cannot be cast to non-null type androidx.paging.PageEvent<R of androidx.paging.SeparatorState>");
            return loadStateUpdate;
        }
        Intrinsics.checkNotNull(loadStateUpdate, "null cannot be cast to non-null type androidx.paging.PageEvent<R of androidx.paging.SeparatorState>");
        return loadStateUpdate;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object onStaticList(androidx.paging.PageEvent.StaticList<T> r11, kotlin.coroutines.Continuation<? super androidx.paging.PageEvent<R>> r12) {
        /*
            r10 = this;
            boolean r0 = r12 instanceof androidx.paging.SeparatorState$onStaticList$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            androidx.paging.SeparatorState$onStaticList$1 r0 = (androidx.paging.SeparatorState$onStaticList$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            androidx.paging.SeparatorState$onStaticList$1 r0 = new androidx.paging.SeparatorState$onStaticList$1
            r0.<init>(r10, r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0048
            if (r2 != r3) goto L_0x0040
            int r11 = r0.I$1
            int r2 = r0.I$0
            java.lang.Object r4 = r0.L$3
            java.lang.Object r5 = r0.L$2
            java.util.List r5 = (java.util.List) r5
            java.lang.Object r6 = r0.L$1
            androidx.paging.PageEvent$StaticList r6 = (androidx.paging.PageEvent.StaticList) r6
            java.lang.Object r7 = r0.L$0
            androidx.paging.SeparatorState r7 = (androidx.paging.SeparatorState) r7
            kotlin.ResultKt.throwOnFailure(r12)
            r9 = r4
            r4 = r11
            r11 = r6
            r6 = r9
            goto L_0x008b
        L_0x0040:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0048:
            kotlin.ResultKt.throwOnFailure(r12)
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            java.util.List r12 = (java.util.List) r12
            java.util.List r2 = r11.getData()
            int r2 = r2.size()
            if (r2 < 0) goto L_0x009d
            r4 = 0
            r7 = r10
            r5 = r12
        L_0x005f:
            java.util.List r12 = r11.getData()
            int r6 = r4 + -1
            java.lang.Object r12 = kotlin.collections.CollectionsKt.getOrNull(r12, r6)
            java.util.List r6 = r11.getData()
            java.lang.Object r6 = kotlin.collections.CollectionsKt.getOrNull(r6, r4)
            kotlin.jvm.functions.Function3<T, T, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r8 = r7.generator
            r0.L$0 = r7
            r0.L$1 = r11
            r0.L$2 = r5
            r0.L$3 = r6
            r0.I$0 = r4
            r0.I$1 = r2
            r0.label = r3
            java.lang.Object r12 = r8.invoke(r12, r6, r0)
            if (r12 != r1) goto L_0x0088
            return r1
        L_0x0088:
            r9 = r4
            r4 = r2
            r2 = r9
        L_0x008b:
            if (r12 == 0) goto L_0x0090
            r5.add(r12)
        L_0x0090:
            if (r6 == 0) goto L_0x0095
            r5.add(r6)
        L_0x0095:
            if (r2 == r4) goto L_0x009c
            int r12 = r2 + 1
            r2 = r4
            r4 = r12
            goto L_0x005f
        L_0x009c:
            r12 = r5
        L_0x009d:
            androidx.paging.PageEvent$StaticList r0 = new androidx.paging.PageEvent$StaticList
            androidx.paging.LoadStates r1 = r11.getSourceLoadStates()
            androidx.paging.LoadStates r11 = r11.getMediatorLoadStates()
            r0.<init>(r12, r1, r11)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.SeparatorState.onStaticList(androidx.paging.PageEvent$StaticList, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final <T> TransformablePage<T> transformablePageToStash(TransformablePage<T> transformablePage) {
        int[] originalPageOffsets = transformablePage.getOriginalPageOffsets();
        List listOf = CollectionsKt.listOf(CollectionsKt.first(transformablePage.getData()), CollectionsKt.last(transformablePage.getData()));
        int hintOriginalPageOffset = transformablePage.getHintOriginalPageOffset();
        List<Integer> hintOriginalIndices = transformablePage.getHintOriginalIndices();
        Integer valueOf = Integer.valueOf(hintOriginalIndices != null ? ((Number) CollectionsKt.first(hintOriginalIndices)).intValue() : 0);
        List<Integer> hintOriginalIndices2 = transformablePage.getHintOriginalIndices();
        return new TransformablePage<>(originalPageOffsets, listOf, hintOriginalPageOffset, CollectionsKt.listOf(valueOf, Integer.valueOf(hintOriginalIndices2 != null ? ((Number) CollectionsKt.last(hintOriginalIndices2)).intValue() : CollectionsKt.getLastIndex(transformablePage.getData()))));
    }
}
