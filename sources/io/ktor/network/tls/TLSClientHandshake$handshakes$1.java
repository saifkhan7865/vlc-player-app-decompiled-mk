package io.ktor.network.tls;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProducerScope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "Lio/ktor/network/tls/TLSHandshake;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.tls.TLSClientHandshake$handshakes$1", f = "TLSClientHandshake.kt", i = {0, 1, 1, 1}, l = {135, 150}, m = "invokeSuspend", n = {"$this$produce", "$this$produce", "packet", "handshake"}, s = {"L$0", "L$0", "L$1", "L$2"})
/* compiled from: TLSClientHandshake.kt */
final class TLSClientHandshake$handshakes$1 extends SuspendLambda implements Function2<ProducerScope<? super TLSHandshake>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ TLSClientHandshake this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TLSClientHandshake$handshakes$1(TLSClientHandshake tLSClientHandshake, Continuation<? super TLSClientHandshake$handshakes$1> continuation) {
        super(2, continuation);
        this.this$0 = tLSClientHandshake;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TLSClientHandshake$handshakes$1 tLSClientHandshake$handshakes$1 = new TLSClientHandshake$handshakes$1(this.this$0, continuation);
        tLSClientHandshake$handshakes$1.L$0 = obj;
        return tLSClientHandshake$handshakes$1;
    }

    public final Object invoke(ProducerScope<? super TLSHandshake> producerScope, Continuation<? super Unit> continuation) {
        return ((TLSClientHandshake$handshakes$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0078, code lost:
        if (r8.getType() == io.ktor.network.tls.TLSHandshakeType.Finished) goto L_0x0083;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x007a, code lost:
        io.ktor.network.tls.UtilsKt.m1472plusAssignHh8V18w(io.ktor.network.tls.TLSClientHandshake.access$getDigest$p(r7.this$0), r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0083, code lost:
        r7.L$0 = r1;
        r7.L$1 = r4;
        r7.L$2 = r8;
        r7.label = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0096, code lost:
        if (r1.getChannel().send(r8, r7) != r0) goto L_0x0099;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0098, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0099, code lost:
        r5 = r1;
        r1 = r8;
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0072 A[EDGE_INSN: B:33:0x0072->B:19:0x0072 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x002f
            if (r1 == r3) goto L_0x0027
            if (r1 != r2) goto L_0x001f
            java.lang.Object r1 = r7.L$2
            io.ktor.network.tls.TLSHandshake r1 = (io.ktor.network.tls.TLSHandshake) r1
            java.lang.Object r4 = r7.L$1
            io.ktor.utils.io.core.ByteReadPacket r4 = (io.ktor.utils.io.core.ByteReadPacket) r4
            java.lang.Object r5 = r7.L$0
            kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x009b
        L_0x001f:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x0027:
            java.lang.Object r1 = r7.L$0
            kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0050
        L_0x002f:
            kotlin.ResultKt.throwOnFailure(r8)
            java.lang.Object r8 = r7.L$0
            kotlinx.coroutines.channels.ProducerScope r8 = (kotlinx.coroutines.channels.ProducerScope) r8
            r1 = r8
        L_0x0037:
            io.ktor.network.tls.TLSClientHandshake r8 = r7.this$0
            kotlinx.coroutines.channels.ReceiveChannel r8 = r8.getInput()
            r4 = r7
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r7.L$0 = r1
            r5 = 0
            r7.L$1 = r5
            r7.L$2 = r5
            r7.label = r3
            java.lang.Object r8 = r8.receive(r4)
            if (r8 != r0) goto L_0x0050
            return r0
        L_0x0050:
            io.ktor.network.tls.TLSRecord r8 = (io.ktor.network.tls.TLSRecord) r8
            io.ktor.network.tls.TLSRecordType r4 = r8.getType()
            io.ktor.network.tls.TLSRecordType r5 = io.ktor.network.tls.TLSRecordType.Handshake
            if (r4 != r5) goto L_0x00ab
            io.ktor.utils.io.core.ByteReadPacket r8 = r8.getPacket()
            r4 = r8
        L_0x005f:
            boolean r8 = r4.getEndOfInput()
            r8 = r8 ^ r3
            if (r8 == 0) goto L_0x0037
            io.ktor.network.tls.TLSHandshake r8 = io.ktor.network.tls.ParserKt.readTLSHandshake(r4)
            io.ktor.network.tls.TLSHandshakeType r5 = r8.getType()
            io.ktor.network.tls.TLSHandshakeType r6 = io.ktor.network.tls.TLSHandshakeType.HelloRequest
            if (r5 == r6) goto L_0x005f
            io.ktor.network.tls.TLSHandshakeType r5 = r8.getType()
            io.ktor.network.tls.TLSHandshakeType r6 = io.ktor.network.tls.TLSHandshakeType.Finished
            if (r5 == r6) goto L_0x0083
            io.ktor.network.tls.TLSClientHandshake r5 = r7.this$0
            io.ktor.utils.io.core.BytePacketBuilder r5 = r5.digest
            io.ktor.network.tls.UtilsKt.m1472plusAssignHh8V18w(r5, r8)
        L_0x0083:
            kotlinx.coroutines.channels.SendChannel r5 = r1.getChannel()
            r6 = r7
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r7.L$0 = r1
            r7.L$1 = r4
            r7.L$2 = r8
            r7.label = r2
            java.lang.Object r5 = r5.send(r8, r6)
            if (r5 != r0) goto L_0x0099
            return r0
        L_0x0099:
            r5 = r1
            r1 = r8
        L_0x009b:
            io.ktor.network.tls.TLSHandshakeType r8 = r1.getType()
            io.ktor.network.tls.TLSHandshakeType r1 = io.ktor.network.tls.TLSHandshakeType.Finished
            if (r8 != r1) goto L_0x00a9
            r4.release()
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x00a9:
            r1 = r5
            goto L_0x005f
        L_0x00ab:
            io.ktor.utils.io.core.ByteReadPacket r0 = r8.getPacket()
            r0.release()
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "TLS handshake expected, got "
            r1.<init>(r2)
            io.ktor.network.tls.TLSRecordType r8 = r8.getType()
            r1.append(r8)
            java.lang.String r8 = r1.toString()
            java.lang.String r8 = r8.toString()
            r0.<init>(r8)
            goto L_0x00cf
        L_0x00ce:
            throw r0
        L_0x00cf:
            goto L_0x00ce
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.tls.TLSClientHandshake$handshakes$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
