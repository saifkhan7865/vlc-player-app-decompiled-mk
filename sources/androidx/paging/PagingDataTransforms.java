package androidx.paging;

import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a>\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\b0\u0007H\u0007\u001aN\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\"\u0010\u0006\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\tH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001aN\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\r0\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\r*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0018\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\r0\u000f0\u0007H\u0007\u001a^\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\r0\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\r*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012(\u0010\u000e\u001a$\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\r0\u000f0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\tH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001a9\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u0002H\u0002H\u0007¢\u0006\u0002\u0010\u0014\u001a9\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u0002H\u0002H\u0007¢\u0006\u0002\u0010\u0014\u001a^\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\r0\u0001\"\b\b\u0000\u0010\r*\u00020\u0003\"\b\b\u0001\u0010\u0002*\u0002H\r*\b\u0012\u0004\u0012\u0002H\u00020\u00012\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00052\u001e\u0010\u0017\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u0001H\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\r0\tH\u0007\u001an\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\r0\u0001\"\b\b\u0000\u0010\u0002*\u0002H\r\"\b\b\u0001\u0010\r*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\b\b\u0002\u0010\u0011\u001a\u00020\u00122.\u0010\u0017\u001a*\b\u0001\u0012\u0006\u0012\u0004\u0018\u0001H\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\u0002\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001H\r0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0018H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001aH\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\r0\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\r*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\r0\u0007H\u0007\u001aX\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\r0\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\r*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\"\u0010\u000e\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\r0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\tH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001ag\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\r*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u000120\b\u0004\u0010\u000e\u001a*\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u001b\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\r0\u001b0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\tH\bø\u0001\u0000¢\u0006\u0002\u0010\u000b\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, d2 = {"filter", "Landroidx/paging/PagingData;", "T", "", "executor", "Ljava/util/concurrent/Executor;", "predicate", "Lkotlin/Function1;", "", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "(Landroidx/paging/PagingData;Lkotlin/jvm/functions/Function2;)Landroidx/paging/PagingData;", "flatMap", "R", "transform", "", "insertFooterItem", "terminalSeparatorType", "Landroidx/paging/TerminalSeparatorType;", "item", "(Landroidx/paging/PagingData;Landroidx/paging/TerminalSeparatorType;Ljava/lang/Object;)Landroidx/paging/PagingData;", "insertHeaderItem", "insertSeparators", "generator", "Lkotlin/Function3;", "(Landroidx/paging/PagingData;Landroidx/paging/TerminalSeparatorType;Lkotlin/jvm/functions/Function3;)Landroidx/paging/PagingData;", "map", "Landroidx/paging/PageEvent;", "paging-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: PagingDataTransforms.kt */
public final class PagingDataTransforms {
    public static final <T> PagingData<T> insertFooterItem(PagingData<T> pagingData, T t) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(t, "item");
        return insertFooterItem$default(pagingData, (TerminalSeparatorType) null, t, 1, (Object) null);
    }

    public static final <T> PagingData<T> insertHeaderItem(PagingData<T> pagingData, T t) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(t, "item");
        return insertHeaderItem$default(pagingData, (TerminalSeparatorType) null, t, 1, (Object) null);
    }

    public static final <R, T extends R> PagingData<R> insertSeparators(PagingData<T> pagingData, Executor executor, Function2<? super T, ? super T, ? extends R> function2) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(function2, "generator");
        return insertSeparators$default(pagingData, (TerminalSeparatorType) null, executor, function2, 1, (Object) null);
    }

    private static final <T, R> PagingData<R> transform(PagingData<T> pagingData, Function2<? super PageEvent<T>, ? super Continuation<? super PageEvent<R>>, ? extends Object> function2) {
        return new PagingData(new PagingDataTransforms$transform$$inlined$map$1(pagingData.getFlow$paging_common(), function2), pagingData.getUiReceiver$paging_common(), pagingData.getHintReceiver$paging_common(), (Function0) null, 8, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ PagingData insertSeparators$default(PagingData pagingData, TerminalSeparatorType terminalSeparatorType, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            terminalSeparatorType = TerminalSeparatorType.FULLY_COMPLETE;
        }
        return insertSeparators(pagingData, terminalSeparatorType, function3);
    }

    public static final /* synthetic */ PagingData insertSeparators(PagingData pagingData, TerminalSeparatorType terminalSeparatorType, Function3 function3) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(terminalSeparatorType, "terminalSeparatorType");
        Intrinsics.checkNotNullParameter(function3, "generator");
        return new PagingData(SeparatorsKt.insertEventSeparators(pagingData.getFlow$paging_common(), terminalSeparatorType, function3), pagingData.getUiReceiver$paging_common(), pagingData.getHintReceiver$paging_common(), (Function0) null, 8, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ PagingData insertSeparators$default(PagingData pagingData, TerminalSeparatorType terminalSeparatorType, Executor executor, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            terminalSeparatorType = TerminalSeparatorType.FULLY_COMPLETE;
        }
        return insertSeparators(pagingData, terminalSeparatorType, executor, function2);
    }

    public static final <R, T extends R> PagingData<R> insertSeparators(PagingData<T> pagingData, TerminalSeparatorType terminalSeparatorType, Executor executor, Function2<? super T, ? super T, ? extends R> function2) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(terminalSeparatorType, "terminalSeparatorType");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(function2, "generator");
        return insertSeparators((PagingData) pagingData, terminalSeparatorType, (Function3) new PagingDataTransforms$insertSeparators$1(executor, function2, (Continuation<? super PagingDataTransforms$insertSeparators$1>) null));
    }

    public static /* synthetic */ PagingData insertHeaderItem$default(PagingData pagingData, TerminalSeparatorType terminalSeparatorType, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            terminalSeparatorType = TerminalSeparatorType.FULLY_COMPLETE;
        }
        return insertHeaderItem(pagingData, terminalSeparatorType, obj);
    }

    public static final <T> PagingData<T> insertHeaderItem(PagingData<T> pagingData, TerminalSeparatorType terminalSeparatorType, T t) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(terminalSeparatorType, "terminalSeparatorType");
        Intrinsics.checkNotNullParameter(t, "item");
        return insertSeparators((PagingData) pagingData, terminalSeparatorType, (Function3) new PagingDataTransforms$insertHeaderItem$1(t, (Continuation<? super PagingDataTransforms$insertHeaderItem$1>) null));
    }

    public static /* synthetic */ PagingData insertFooterItem$default(PagingData pagingData, TerminalSeparatorType terminalSeparatorType, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            terminalSeparatorType = TerminalSeparatorType.FULLY_COMPLETE;
        }
        return insertFooterItem(pagingData, terminalSeparatorType, obj);
    }

    public static final <T> PagingData<T> insertFooterItem(PagingData<T> pagingData, TerminalSeparatorType terminalSeparatorType, T t) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(terminalSeparatorType, "terminalSeparatorType");
        Intrinsics.checkNotNullParameter(t, "item");
        return insertSeparators((PagingData) pagingData, terminalSeparatorType, (Function3) new PagingDataTransforms$insertFooterItem$1(t, (Continuation<? super PagingDataTransforms$insertFooterItem$1>) null));
    }

    public static final /* synthetic */ PagingData map(PagingData pagingData, Function2 function2) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(function2, "transform");
        return new PagingData(new PagingDataTransforms$map$$inlined$transform$1(pagingData.getFlow$paging_common(), function2), pagingData.getUiReceiver$paging_common(), pagingData.getHintReceiver$paging_common(), (Function0) null, 8, (DefaultConstructorMarker) null);
    }

    public static final <T, R> PagingData<R> map(PagingData<T> pagingData, Executor executor, Function1<? super T, ? extends R> function1) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(function1, "transform");
        return new PagingData(new PagingDataTransforms$map$$inlined$transform$2(pagingData.getFlow$paging_common(), executor, function1), pagingData.getUiReceiver$paging_common(), pagingData.getHintReceiver$paging_common(), (Function0) null, 8, (DefaultConstructorMarker) null);
    }

    public static final /* synthetic */ PagingData flatMap(PagingData pagingData, Function2 function2) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(function2, "transform");
        return new PagingData(new PagingDataTransforms$flatMap$$inlined$transform$1(pagingData.getFlow$paging_common(), function2), pagingData.getUiReceiver$paging_common(), pagingData.getHintReceiver$paging_common(), (Function0) null, 8, (DefaultConstructorMarker) null);
    }

    public static final <T, R> PagingData<R> flatMap(PagingData<T> pagingData, Executor executor, Function1<? super T, ? extends Iterable<? extends R>> function1) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(function1, "transform");
        return new PagingData(new PagingDataTransforms$flatMap$$inlined$transform$2(pagingData.getFlow$paging_common(), executor, function1), pagingData.getUiReceiver$paging_common(), pagingData.getHintReceiver$paging_common(), (Function0) null, 8, (DefaultConstructorMarker) null);
    }

    public static final /* synthetic */ PagingData filter(PagingData pagingData, Function2 function2) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(function2, "predicate");
        return new PagingData(new PagingDataTransforms$filter$$inlined$transform$1(pagingData.getFlow$paging_common(), function2), pagingData.getUiReceiver$paging_common(), pagingData.getHintReceiver$paging_common(), (Function0) null, 8, (DefaultConstructorMarker) null);
    }

    public static final <T> PagingData<T> filter(PagingData<T> pagingData, Executor executor, Function1<? super T, Boolean> function1) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(function1, "predicate");
        return new PagingData(new PagingDataTransforms$filter$$inlined$transform$2(pagingData.getFlow$paging_common(), executor, function1), pagingData.getUiReceiver$paging_common(), pagingData.getHintReceiver$paging_common(), (Function0) null, 8, (DefaultConstructorMarker) null);
    }
}
