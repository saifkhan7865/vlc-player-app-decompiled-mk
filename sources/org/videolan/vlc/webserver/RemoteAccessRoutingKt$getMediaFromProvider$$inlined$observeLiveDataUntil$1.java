package org.videolan.vlc.webserver;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutKt;
import org.videolan.tools.livedata.LiveDataset;

@Metadata(d1 = {"\u0000\n\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H@¨\u0006\u0003"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;", "org/videolan/resources/util/ExtensionsKt$observeLiveDataUntil$2"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessRoutingKt$getMediaFromProvider$$inlined$observeLiveDataUntil$1", f = "RemoteAccessRouting.kt", i = {0}, l = {338}, m = "invokeSuspend", n = {"finalValue$iv"}, s = {"L$0"})
/* compiled from: Extensions.kt */
public final class RemoteAccessRoutingKt$getMediaFromProvider$$inlined$observeLiveDataUntil$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Pair<? extends Integer, ? extends String>>, Object> {
    final /* synthetic */ LiveData $data;
    final /* synthetic */ LiveDataset $dataset$inlined;
    final /* synthetic */ ArrayList $descriptions$inlined;
    final /* synthetic */ long $timeout;
    Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RemoteAccessRoutingKt$getMediaFromProvider$$inlined$observeLiveDataUntil$1(long j, LiveData liveData, Continuation continuation, ArrayList arrayList, LiveDataset liveDataset) {
        super(2, continuation);
        this.$timeout = j;
        this.$data = liveData;
        this.$descriptions$inlined = arrayList;
        this.$dataset$inlined = liveDataset;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RemoteAccessRoutingKt$getMediaFromProvider$$inlined$observeLiveDataUntil$1(this.$timeout, this.$data, continuation, this.$descriptions$inlined, this.$dataset$inlined);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Pair<? extends Integer, ? extends String>> continuation) {
        return ((RemoteAccessRoutingKt$getMediaFromProvider$$inlined$observeLiveDataUntil$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H@¨\u0006\u0005"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;", "org/videolan/resources/util/ExtensionsKt$suspendCoroutineWithTimeout$2", "org/videolan/resources/util/ExtensionsKt$observeLiveDataUntil$2$invokeSuspend$$inlined$suspendCoroutineWithTimeout$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessRoutingKt$getMediaFromProvider$$inlined$observeLiveDataUntil$1$1", f = "RemoteAccessRouting.kt", i = {}, l = {337}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.webserver.RemoteAccessRoutingKt$getMediaFromProvider$$inlined$observeLiveDataUntil$1$1  reason: invalid class name */
    /* compiled from: Extensions.kt */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(objectRef3, continuation, liveData, booleanRef, arrayList, liveDataset);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(T t) {
            Ref.ObjectRef objectRef;
            T coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(t);
                Ref.ObjectRef objectRef2 = objectRef3;
                this.L$0 = objectRef2;
                this.label = 1;
                Continuation continuation = this;
                CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
                cancellableContinuationImpl.initCancellability();
                CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
                final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                final Object value = liveData.getValue();
                final Ref.BooleanRef booleanRef = booleanRef;
                final LiveData liveData = liveData;
                final ArrayList arrayList = arrayList;
                final LiveDataset liveDataset = liveDataset;
                final CancellableContinuation cancellableContinuation2 = cancellableContinuation;
                final Ref.ObjectRef objectRef4 = objectRef3;
                objectRef3.element = new Observer() {
                    public final void onChanged(T t) {
                        if (!Intrinsics.areEqual(value, (Object) t) || !booleanRef.element) {
                            booleanRef.element = true;
                            arrayList.add((Pair) t);
                            if (arrayList.size() >= liveDataset.getList().size() && !cancellableContinuation2.isCancelled()) {
                                Result.Companion companion = Result.Companion;
                                cancellableContinuation2.resumeWith(Result.m1774constructorimpl(t));
                                Observer observer = (Observer) objectRef4.element;
                                if (observer != null) {
                                    liveData.removeObserver(observer);
                                }
                            }
                        }
                    }
                };
                liveData.observeForever((Observer) objectRef3.element);
                final LiveData liveData2 = liveData;
                cancellableContinuation.invokeOnCancellation(new Function1<Throwable, Unit>() {
                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        invoke((Throwable) obj);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Throwable th) {
                        liveData2.removeObserver((Observer) objectRef3.element);
                    }
                });
                T result = cancellableContinuationImpl.getResult();
                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(continuation);
                }
                if (result == coroutine_suspended) {
                    return coroutine_suspended;
                }
                objectRef = objectRef2;
                t = result;
            } else if (i == 1) {
                objectRef = (Ref.ObjectRef) this.L$0;
                ResultKt.throwOnFailure(t);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            objectRef.element = t;
            return Unit.INSTANCE;
        }
    }

    public final Object invokeSuspend(Object obj) {
        Ref.ObjectRef objectRef;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            long j = this.$timeout;
            final LiveData liveData = this.$data;
            Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            final ArrayList arrayList = this.$descriptions$inlined;
            final LiveDataset liveDataset = this.$dataset$inlined;
            final Ref.ObjectRef objectRef3 = objectRef2;
            this.L$0 = objectRef2;
            this.label = 1;
            if (TimeoutKt.withTimeoutOrNull(j, new AnonymousClass1((Continuation) null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            objectRef = objectRef2;
        } else if (i == 1) {
            objectRef = (Ref.ObjectRef) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return objectRef.element;
    }
}
