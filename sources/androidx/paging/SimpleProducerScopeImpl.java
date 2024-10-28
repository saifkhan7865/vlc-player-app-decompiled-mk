package androidx.paging;

import androidx.tvprovider.media.tv.TvContractCompat;
import io.ktor.server.auth.OAuth2RequestParameters;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.selects.SelectClause2;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004B\u001b\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0007J\u001f\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u0018H@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J\u0015\u0010\u001a\u001a\u00020\u000f2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0001J.\u0010\u001d\u001a\u00020\u00162#\u0010\u001e\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u001c¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u00160\u001fH\u0001J\u0016\u0010\"\u001a\u00020\u000f2\u0006\u0010#\u001a\u00028\u0000H\u0001¢\u0006\u0002\u0010$J\u0019\u0010%\u001a\u00020\u00162\u0006\u0010#\u001a\u00028\u0000HAø\u0001\u0000¢\u0006\u0002\u0010&J'\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00160(2\u0006\u0010#\u001a\u00028\u0000H\u0001ø\u0001\u0001ø\u0001\u0002ø\u0001\u0000¢\u0006\u0004\b)\u0010*R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX\u0005¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000f8\u0016X\u0005¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0010R$\u0010\u0011\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00040\u0012X\u0005¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006+"}, d2 = {"Landroidx/paging/SimpleProducerScopeImpl;", "T", "Landroidx/paging/SimpleProducerScope;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlinx/coroutines/channels/SendChannel;", "scope", "channel", "(Lkotlinx/coroutines/CoroutineScope;Lkotlinx/coroutines/channels/SendChannel;)V", "getChannel", "()Lkotlinx/coroutines/channels/SendChannel;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "isClosedForSend", "", "()Z", "onSend", "Lkotlinx/coroutines/selects/SelectClause2;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "awaitClose", "", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "close", "cause", "", "invokeOnClose", "handler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "offer", "element", "(Ljava/lang/Object;)Z", "send", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "trySend", "Lkotlinx/coroutines/channels/ChannelResult;", "trySend-JP2dKIU", "(Ljava/lang/Object;)Ljava/lang/Object;", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SimpleChannelFlow.kt */
public final class SimpleProducerScopeImpl<T> implements SimpleProducerScope<T>, CoroutineScope, SendChannel<T> {
    private final /* synthetic */ CoroutineScope $$delegate_0;
    private final SendChannel<T> channel;

    public boolean close(Throwable th) {
        return this.channel.close(th);
    }

    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

    public SelectClause2<T, SendChannel<T>> getOnSend() {
        return this.channel.getOnSend();
    }

    public void invokeOnClose(Function1<? super Throwable, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "handler");
        this.channel.invokeOnClose(function1);
    }

    public boolean isClosedForSend() {
        return this.channel.isClosedForSend();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of 'trySend' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = {}))
    public boolean offer(T t) {
        return this.channel.offer(t);
    }

    public Object send(T t, Continuation<? super Unit> continuation) {
        return this.channel.send(t, continuation);
    }

    /* renamed from: trySend-JP2dKIU  reason: not valid java name */
    public Object m123trySendJP2dKIU(T t) {
        return this.channel.m1139trySendJP2dKIU(t);
    }

    public SimpleProducerScopeImpl(CoroutineScope coroutineScope, SendChannel<? super T> sendChannel) {
        Intrinsics.checkNotNullParameter(coroutineScope, OAuth2RequestParameters.Scope);
        Intrinsics.checkNotNullParameter(sendChannel, TvContractCompat.PARAM_CHANNEL);
        this.channel = sendChannel;
        this.$$delegate_0 = coroutineScope;
    }

    public SendChannel<T> getChannel() {
        return this.channel;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object awaitClose(kotlin.jvm.functions.Function0<kotlin.Unit> r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof androidx.paging.SimpleProducerScopeImpl$awaitClose$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            androidx.paging.SimpleProducerScopeImpl$awaitClose$1 r0 = (androidx.paging.SimpleProducerScopeImpl$awaitClose$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            androidx.paging.SimpleProducerScopeImpl$awaitClose$1 r0 = new androidx.paging.SimpleProducerScopeImpl$awaitClose$1
            r0.<init>(r5, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.Job r6 = (kotlinx.coroutines.Job) r6
            java.lang.Object r6 = r0.L$0
            kotlin.jvm.functions.Function0 r6 = (kotlin.jvm.functions.Function0) r6
            kotlin.ResultKt.throwOnFailure(r7)     // Catch:{ all -> 0x008e }
            goto L_0x007c
        L_0x0032:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r7)
            kotlin.coroutines.CoroutineContext r7 = r5.getCoroutineContext()     // Catch:{ all -> 0x008e }
            kotlinx.coroutines.Job$Key r2 = kotlinx.coroutines.Job.Key     // Catch:{ all -> 0x008e }
            kotlin.coroutines.CoroutineContext$Key r2 = (kotlin.coroutines.CoroutineContext.Key) r2     // Catch:{ all -> 0x008e }
            kotlin.coroutines.CoroutineContext$Element r7 = r7.get(r2)     // Catch:{ all -> 0x008e }
            if (r7 == 0) goto L_0x0082
            kotlinx.coroutines.Job r7 = (kotlinx.coroutines.Job) r7     // Catch:{ all -> 0x008e }
            r0.L$0 = r6     // Catch:{ all -> 0x008e }
            r0.L$1 = r7     // Catch:{ all -> 0x008e }
            r0.label = r3     // Catch:{ all -> 0x008e }
            kotlinx.coroutines.CancellableContinuationImpl r2 = new kotlinx.coroutines.CancellableContinuationImpl     // Catch:{ all -> 0x008e }
            kotlin.coroutines.Continuation r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r0)     // Catch:{ all -> 0x008e }
            r2.<init>(r4, r3)     // Catch:{ all -> 0x008e }
            r2.initCancellability()     // Catch:{ all -> 0x008e }
            r3 = r2
            kotlinx.coroutines.CancellableContinuation r3 = (kotlinx.coroutines.CancellableContinuation) r3     // Catch:{ all -> 0x008e }
            androidx.paging.SimpleProducerScopeImpl$awaitClose$2$1 r4 = new androidx.paging.SimpleProducerScopeImpl$awaitClose$2$1     // Catch:{ all -> 0x008e }
            r4.<init>(r3)     // Catch:{ all -> 0x008e }
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4     // Catch:{ all -> 0x008e }
            r7.invokeOnCompletion(r4)     // Catch:{ all -> 0x008e }
            java.lang.Object r7 = r2.getResult()     // Catch:{ all -> 0x008e }
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()     // Catch:{ all -> 0x008e }
            if (r7 != r2) goto L_0x0079
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r0)     // Catch:{ all -> 0x008e }
        L_0x0079:
            if (r7 != r1) goto L_0x007c
            return r1
        L_0x007c:
            r6.invoke()
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L_0x0082:
            java.lang.String r7 = "Internal error, context should have a job."
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x008e }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x008e }
            r0.<init>(r7)     // Catch:{ all -> 0x008e }
            throw r0     // Catch:{ all -> 0x008e }
        L_0x008e:
            r7 = move-exception
            r6.invoke()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.SimpleProducerScopeImpl.awaitClose(kotlin.jvm.functions.Function0, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
