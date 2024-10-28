package androidx.paging;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0001\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0005\"\u0004\b\u0001\u0010\u0006\"\u0004\b\u0002\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00050\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00032?\b\u0004\u0010\b\u001a9\b\u0001\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u0002H\u0006\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00010\tHHø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001ah\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0011\"\u0004\b\u0001\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00110\u000329\b\u0004\u0010\b\u001a3\b\u0001\u0012\u0013\u0012\u0011H\u0011¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0013\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u00030\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0012H\bø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001ab\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0011\"\u0004\b\u0001\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00110\u000323\b\u0004\u0010\b\u001a-\b\u0001\u0012\u0013\u0012\u0011H\u0011¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0012H\bø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001an\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0003\"\u0004\b\u0000\u0010\u0011*\b\u0012\u0004\u0012\u0002H\u00110\u00032F\u0010\u0017\u001aB\b\u0001\u0012\u0013\u0012\u0011H\u0011¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u0011¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0018H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u001a\u001a|\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0011\"\u0004\b\u0001\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00110\u00032\u0006\u0010\u001c\u001a\u0002H\u00042F\u0010\u0017\u001aB\b\u0001\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u0011¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0018H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u001d\u001ap\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0011\"\u0004\b\u0001\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00110\u00032B\u0010\b\u001a>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u001f\u0012\u0013\u0012\u0011H\u0011¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0018¢\u0006\u0002\b!H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u001a\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, d2 = {"NULL", "", "combineWithoutBatching", "Lkotlinx/coroutines/flow/Flow;", "R", "T1", "T2", "otherFlow", "transform", "Lkotlin/Function4;", "Landroidx/paging/CombineSource;", "Lkotlin/ParameterName;", "name", "updateFrom", "Lkotlin/coroutines/Continuation;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function4;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "simpleFlatMapLatest", "T", "Lkotlin/Function2;", "value", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "simpleMapLatest", "simpleRunningReduce", "operation", "Lkotlin/Function3;", "accumulator", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "simpleScan", "initial", "(Lkotlinx/coroutines/flow/Flow;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "simpleTransformLatest", "Lkotlinx/coroutines/flow/FlowCollector;", "", "Lkotlin/ExtensionFunctionType;", "paging-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: FlowExt.kt */
public final class FlowExtKt {
    /* access modifiers changed from: private */
    public static final Object NULL = new Object();

    public static final <T, R> Flow<R> simpleScan(Flow<? extends T> flow, R r, Function3<? super R, ? super T, ? super Continuation<? super R>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(flow, "<this>");
        Intrinsics.checkNotNullParameter(function3, "operation");
        return FlowKt.flow(new FlowExtKt$simpleScan$1(r, flow, function3, (Continuation<? super FlowExtKt$simpleScan$1>) null));
    }

    public static final <T> Flow<T> simpleRunningReduce(Flow<? extends T> flow, Function3<? super T, ? super T, ? super Continuation<? super T>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(flow, "<this>");
        Intrinsics.checkNotNullParameter(function3, "operation");
        return FlowKt.flow(new FlowExtKt$simpleRunningReduce$1(flow, function3, (Continuation<? super FlowExtKt$simpleRunningReduce$1>) null));
    }

    public static final <T, R> Flow<R> simpleTransformLatest(Flow<? extends T> flow, Function3<? super FlowCollector<? super R>, ? super T, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(flow, "<this>");
        Intrinsics.checkNotNullParameter(function3, "transform");
        return SimpleChannelFlowKt.simpleChannelFlow(new FlowExtKt$simpleTransformLatest$1(flow, function3, (Continuation<? super FlowExtKt$simpleTransformLatest$1>) null));
    }

    public static final <T, R> Flow<R> simpleFlatMapLatest(Flow<? extends T> flow, Function2<? super T, ? super Continuation<? super Flow<? extends R>>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter(flow, "<this>");
        Intrinsics.checkNotNullParameter(function2, "transform");
        return simpleTransformLatest(flow, new FlowExtKt$simpleFlatMapLatest$1(function2, (Continuation<? super FlowExtKt$simpleFlatMapLatest$1>) null));
    }

    public static final <T, R> Flow<R> simpleMapLatest(Flow<? extends T> flow, Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter(flow, "<this>");
        Intrinsics.checkNotNullParameter(function2, "transform");
        return simpleTransformLatest(flow, new FlowExtKt$simpleMapLatest$1(function2, (Continuation<? super FlowExtKt$simpleMapLatest$1>) null));
    }

    public static final <T1, T2, R> Object combineWithoutBatching(Flow<? extends T1> flow, Flow<? extends T2> flow2, Function4<? super T1, ? super T2, ? super CombineSource, ? super Continuation<? super R>, ? extends Object> function4, Continuation<? super Flow<? extends R>> continuation) {
        return SimpleChannelFlowKt.simpleChannelFlow(new FlowExtKt$combineWithoutBatching$2(flow, flow2, function4, (Continuation<? super FlowExtKt$combineWithoutBatching$2>) null));
    }

    private static final <T1, T2, R> Object combineWithoutBatching$$forInline(Flow<? extends T1> flow, Flow<? extends T2> flow2, Function4<? super T1, ? super T2, ? super CombineSource, ? super Continuation<? super R>, ? extends Object> function4, Continuation<? super Flow<? extends R>> continuation) {
        return SimpleChannelFlowKt.simpleChannelFlow(new FlowExtKt$combineWithoutBatching$2(flow, flow2, function4, (Continuation<? super FlowExtKt$combineWithoutBatching$2>) null));
    }
}
