package io.ktor.network.tls;

import io.ktor.utils.io.ByteWriteChannel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ActorScope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ActorScope;", "Lio/ktor/network/tls/TLSRecord;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.tls.TLSClientHandshake$output$1", f = "TLSClientHandshake.kt", i = {0, 0, 1, 1}, l = {107, 112, 118, 118}, m = "invokeSuspend", n = {"$this$actor", "useCipher", "$this$actor", "useCipher"}, s = {"L$0", "I$0", "L$0", "I$0"})
/* compiled from: TLSClientHandshake.kt */
final class TLSClientHandshake$output$1 extends SuspendLambda implements Function2<ActorScope<TLSRecord>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ByteWriteChannel $rawOutput;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ TLSClientHandshake this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TLSClientHandshake$output$1(TLSClientHandshake tLSClientHandshake, ByteWriteChannel byteWriteChannel, Continuation<? super TLSClientHandshake$output$1> continuation) {
        super(2, continuation);
        this.this$0 = tLSClientHandshake;
        this.$rawOutput = byteWriteChannel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TLSClientHandshake$output$1 tLSClientHandshake$output$1 = new TLSClientHandshake$output$1(this.this$0, this.$rawOutput, continuation);
        tLSClientHandshake$output$1.L$0 = obj;
        return tLSClientHandshake$output$1;
    }

    public final Object invoke(ActorScope<TLSRecord> actorScope, Continuation<? super Unit> continuation) {
        return ((TLSClientHandshake$output$1) create(actorScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v24, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v13, resolved type: kotlinx.coroutines.channels.ChannelIterator} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v25, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v15, resolved type: kotlinx.coroutines.channels.ActorScope} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x007e A[Catch:{ all -> 0x0115, all -> 0x0156 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x007f A[Catch:{ all -> 0x0115, all -> 0x0156 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x008b A[Catch:{ all -> 0x0115, all -> 0x0156 }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0107 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r17) {
        /*
            r16 = this;
            r1 = r16
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r1.label
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            r7 = 0
            if (r0 == 0) goto L_0x005d
            if (r0 == r6) goto L_0x004a
            if (r0 == r5) goto L_0x002d
            if (r0 == r4) goto L_0x0028
            if (r0 == r3) goto L_0x001f
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x001f:
            java.lang.Object r0 = r1.L$0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            kotlin.ResultKt.throwOnFailure(r17)
            goto L_0x0150
        L_0x0028:
            kotlin.ResultKt.throwOnFailure(r17)
            goto L_0x0108
        L_0x002d:
            int r8 = r1.I$0
            java.lang.Object r0 = r1.L$1
            r9 = r0
            kotlinx.coroutines.channels.ChannelIterator r9 = (kotlinx.coroutines.channels.ChannelIterator) r9
            java.lang.Object r0 = r1.L$0
            r10 = r0
            kotlinx.coroutines.channels.ActorScope r10 = (kotlinx.coroutines.channels.ActorScope) r10
            kotlin.ResultKt.throwOnFailure(r17)     // Catch:{ all -> 0x0041 }
            r0 = r10
        L_0x003d:
            r14 = r9
            r9 = r8
            r8 = r14
            goto L_0x006d
        L_0x0041:
            r0 = move-exception
            r14 = r10
            r10 = r0
            r0 = r14
            r15 = r9
            r9 = r8
            r8 = r15
            goto L_0x00c6
        L_0x004a:
            int r0 = r1.I$0
            java.lang.Object r8 = r1.L$1
            kotlinx.coroutines.channels.ChannelIterator r8 = (kotlinx.coroutines.channels.ChannelIterator) r8
            java.lang.Object r9 = r1.L$0
            kotlinx.coroutines.channels.ActorScope r9 = (kotlinx.coroutines.channels.ActorScope) r9
            kotlin.ResultKt.throwOnFailure(r17)     // Catch:{ all -> 0x0115 }
            r10 = r17
            r11 = r9
            r9 = r8
            r8 = r0
            goto L_0x0083
        L_0x005d:
            kotlin.ResultKt.throwOnFailure(r17)
            java.lang.Object r0 = r1.L$0
            kotlinx.coroutines.channels.ActorScope r0 = (kotlinx.coroutines.channels.ActorScope) r0
            kotlinx.coroutines.channels.Channel r8 = r0.getChannel()     // Catch:{ all -> 0x0115 }
            kotlinx.coroutines.channels.ChannelIterator r8 = r8.iterator()     // Catch:{ all -> 0x0115 }
            r9 = 0
        L_0x006d:
            r10 = r1
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10     // Catch:{ all -> 0x0115 }
            r1.L$0 = r0     // Catch:{ all -> 0x0115 }
            r1.L$1 = r8     // Catch:{ all -> 0x0115 }
            r1.I$0 = r9     // Catch:{ all -> 0x0115 }
            r1.label = r6     // Catch:{ all -> 0x0115 }
            java.lang.Object r10 = r8.hasNext(r10)     // Catch:{ all -> 0x0115 }
            if (r10 != r2) goto L_0x007f
            return r2
        L_0x007f:
            r11 = r0
            r14 = r9
            r9 = r8
            r8 = r14
        L_0x0083:
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch:{ all -> 0x0115 }
            boolean r0 = r10.booleanValue()     // Catch:{ all -> 0x0115 }
            if (r0 == 0) goto L_0x00ce
            java.lang.Object r0 = r9.next()     // Catch:{ all -> 0x0115 }
            io.ktor.network.tls.TLSRecord r0 = (io.ktor.network.tls.TLSRecord) r0     // Catch:{ all -> 0x0115 }
            if (r8 == 0) goto L_0x00a5
            io.ktor.network.tls.TLSClientHandshake r10 = r1.this$0     // Catch:{ all -> 0x009e }
            io.ktor.network.tls.cipher.TLSCipher r10 = r10.getCipher()     // Catch:{ all -> 0x009e }
            io.ktor.network.tls.TLSRecord r10 = r10.encrypt(r0)     // Catch:{ all -> 0x009e }
            goto L_0x00a6
        L_0x009e:
            r0 = move-exception
            r10 = r0
            r0 = r11
            r14 = r9
            r9 = r8
            r8 = r14
            goto L_0x00c6
        L_0x00a5:
            r10 = r0
        L_0x00a6:
            io.ktor.network.tls.TLSRecordType r0 = r0.getType()     // Catch:{ all -> 0x009e }
            io.ktor.network.tls.TLSRecordType r12 = io.ktor.network.tls.TLSRecordType.ChangeCipherSpec     // Catch:{ all -> 0x009e }
            if (r0 != r12) goto L_0x00af
            r8 = 1
        L_0x00af:
            io.ktor.utils.io.ByteWriteChannel r0 = r1.$rawOutput     // Catch:{ all -> 0x009e }
            r12 = r1
            kotlin.coroutines.Continuation r12 = (kotlin.coroutines.Continuation) r12     // Catch:{ all -> 0x009e }
            r1.L$0 = r11     // Catch:{ all -> 0x009e }
            r1.L$1 = r9     // Catch:{ all -> 0x009e }
            r1.I$0 = r8     // Catch:{ all -> 0x009e }
            r1.label = r5     // Catch:{ all -> 0x009e }
            java.lang.Object r0 = io.ktor.network.tls.RenderKt.writeRecord(r0, r10, r12)     // Catch:{ all -> 0x009e }
            if (r0 != r2) goto L_0x00c3
            return r2
        L_0x00c3:
            r0 = r11
            goto L_0x003d
        L_0x00c6:
            kotlinx.coroutines.channels.Channel r11 = r0.getChannel()     // Catch:{ all -> 0x0115 }
            r11.close(r10)     // Catch:{ all -> 0x0115 }
            goto L_0x006d
        L_0x00ce:
            io.ktor.utils.io.ByteWriteChannel r0 = r1.$rawOutput
            io.ktor.network.tls.TLSRecordType r9 = io.ktor.network.tls.TLSRecordType.Alert
            io.ktor.utils.io.core.BytePacketBuilder r3 = new io.ktor.utils.io.core.BytePacketBuilder
            r3.<init>(r7, r6, r7)
            io.ktor.network.tls.TLSAlertLevel r5 = io.ktor.network.tls.TLSAlertLevel.WARNING     // Catch:{ all -> 0x0110 }
            int r5 = r5.getCode()     // Catch:{ all -> 0x0110 }
            byte r5 = (byte) r5     // Catch:{ all -> 0x0110 }
            r3.writeByte(r5)     // Catch:{ all -> 0x0110 }
            io.ktor.network.tls.TLSAlertType r5 = io.ktor.network.tls.TLSAlertType.CloseNotify     // Catch:{ all -> 0x0110 }
            int r5 = r5.getCode()     // Catch:{ all -> 0x0110 }
            byte r5 = (byte) r5     // Catch:{ all -> 0x0110 }
            r3.writeByte(r5)     // Catch:{ all -> 0x0110 }
            io.ktor.utils.io.core.ByteReadPacket r11 = r3.build()     // Catch:{ all -> 0x0110 }
            io.ktor.network.tls.TLSRecord r3 = new io.ktor.network.tls.TLSRecord
            r10 = 0
            r12 = 2
            r13 = 0
            r8 = r3
            r8.<init>(r9, r10, r11, r12, r13)
            r5 = r1
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r1.L$0 = r7
            r1.L$1 = r7
            r1.label = r4
            java.lang.Object r0 = io.ktor.network.tls.RenderKt.writeRecord(r0, r3, r5)
            if (r0 != r2) goto L_0x0108
            return r2
        L_0x0108:
            io.ktor.utils.io.ByteWriteChannel r0 = r1.$rawOutput
            io.ktor.utils.io.ByteWriteChannelKt.close(r0)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0110:
            r0 = move-exception
            r3.release()
            throw r0
        L_0x0115:
            r0 = move-exception
            io.ktor.utils.io.ByteWriteChannel r4 = r1.$rawOutput
            io.ktor.network.tls.TLSRecordType r9 = io.ktor.network.tls.TLSRecordType.Alert
            io.ktor.utils.io.core.BytePacketBuilder r5 = new io.ktor.utils.io.core.BytePacketBuilder
            r5.<init>(r7, r6, r7)
            io.ktor.network.tls.TLSAlertLevel r6 = io.ktor.network.tls.TLSAlertLevel.WARNING     // Catch:{ all -> 0x0156 }
            int r6 = r6.getCode()     // Catch:{ all -> 0x0156 }
            byte r6 = (byte) r6     // Catch:{ all -> 0x0156 }
            r5.writeByte(r6)     // Catch:{ all -> 0x0156 }
            io.ktor.network.tls.TLSAlertType r6 = io.ktor.network.tls.TLSAlertType.CloseNotify     // Catch:{ all -> 0x0156 }
            int r6 = r6.getCode()     // Catch:{ all -> 0x0156 }
            byte r6 = (byte) r6     // Catch:{ all -> 0x0156 }
            r5.writeByte(r6)     // Catch:{ all -> 0x0156 }
            io.ktor.utils.io.core.ByteReadPacket r11 = r5.build()     // Catch:{ all -> 0x0156 }
            io.ktor.network.tls.TLSRecord r5 = new io.ktor.network.tls.TLSRecord
            r10 = 0
            r12 = 2
            r13 = 0
            r8 = r5
            r8.<init>(r9, r10, r11, r12, r13)
            r6 = r1
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r1.L$0 = r0
            r1.L$1 = r7
            r1.label = r3
            java.lang.Object r3 = io.ktor.network.tls.RenderKt.writeRecord(r4, r5, r6)
            if (r3 != r2) goto L_0x0150
            return r2
        L_0x0150:
            io.ktor.utils.io.ByteWriteChannel r2 = r1.$rawOutput
            io.ktor.utils.io.ByteWriteChannelKt.close(r2)
            throw r0
        L_0x0156:
            r0 = move-exception
            r5.release()
            goto L_0x015c
        L_0x015b:
            throw r0
        L_0x015c:
            goto L_0x015b
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.tls.TLSClientHandshake$output$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
