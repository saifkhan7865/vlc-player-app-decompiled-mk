package io.ktor.network.tls;

import io.ktor.utils.io.ByteReadChannel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProducerScope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "Lio/ktor/network/tls/TLSRecord;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.tls.TLSClientHandshake$input$1", f = "TLSClientHandshake.kt", i = {0, 0, 1, 1}, l = {60, 90}, m = "invokeSuspend", n = {"$this$produce", "useCipher", "$this$produce", "useCipher"}, s = {"L$0", "I$0", "L$0", "I$0"})
/* compiled from: TLSClientHandshake.kt */
final class TLSClientHandshake$input$1 extends SuspendLambda implements Function2<ProducerScope<? super TLSRecord>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ByteReadChannel $rawInput;
    int I$0;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ TLSClientHandshake this$0;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: TLSClientHandshake.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                io.ktor.network.tls.TLSRecordType[] r0 = io.ktor.network.tls.TLSRecordType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                io.ktor.network.tls.TLSRecordType r1 = io.ktor.network.tls.TLSRecordType.Alert     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                io.ktor.network.tls.TLSRecordType r1 = io.ktor.network.tls.TLSRecordType.ChangeCipherSpec     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.tls.TLSClientHandshake$input$1.WhenMappings.<clinit>():void");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TLSClientHandshake$input$1(ByteReadChannel byteReadChannel, TLSClientHandshake tLSClientHandshake, Continuation<? super TLSClientHandshake$input$1> continuation) {
        super(2, continuation);
        this.$rawInput = byteReadChannel;
        this.this$0 = tLSClientHandshake;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TLSClientHandshake$input$1 tLSClientHandshake$input$1 = new TLSClientHandshake$input$1(this.$rawInput, this.this$0, continuation);
        tLSClientHandshake$input$1.L$0 = obj;
        return tLSClientHandshake$input$1;
    }

    public final Object invoke(ProducerScope<? super TLSRecord> producerScope, Continuation<? super Unit> continuation) {
        return ((TLSClientHandshake$input$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x004d A[Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b, all -> 0x0127 }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0069 A[Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b, all -> 0x0127 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00be A[Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b, all -> 0x0127 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00d6 A[Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b, all -> 0x0127 }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00e2 A[SYNTHETIC, Splitter:B:40:0x00e2] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            r14 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r14.label
            r2 = 2
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L_0x002e
            if (r1 == r3) goto L_0x0021
            if (r1 != r2) goto L_0x0019
            int r1 = r14.I$0
            java.lang.Object r5 = r14.L$0
            kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            goto L_0x0037
        L_0x0019:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r15.<init>(r0)
            throw r15
        L_0x0021:
            int r1 = r14.I$0
            java.lang.Object r5 = r14.L$0
            kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            goto L_0x0049
        L_0x002b:
            r15 = move-exception
            goto L_0x010c
        L_0x002e:
            kotlin.ResultKt.throwOnFailure(r15)
            java.lang.Object r15 = r14.L$0
            kotlinx.coroutines.channels.ProducerScope r15 = (kotlinx.coroutines.channels.ProducerScope) r15
            r1 = 0
            r5 = r15
        L_0x0037:
            io.ktor.utils.io.ByteReadChannel r15 = r14.$rawInput     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            r6 = r14
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            r14.L$0 = r5     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            r14.I$0 = r1     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            r14.label = r3     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            java.lang.Object r15 = io.ktor.network.tls.ParserKt.readTLSRecord(r15, r6)     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            if (r15 != r0) goto L_0x0049
            return r0
        L_0x0049:
            io.ktor.network.tls.TLSRecord r15 = (io.ktor.network.tls.TLSRecord) r15     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            if (r1 == 0) goto L_0x0057
            io.ktor.network.tls.TLSClientHandshake r6 = r14.this$0     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            io.ktor.network.tls.cipher.TLSCipher r6 = r6.getCipher()     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            io.ktor.network.tls.TLSRecord r15 = r6.decrypt(r15)     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
        L_0x0057:
            io.ktor.utils.io.core.ByteReadPacket r9 = r15.getPacket()     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            io.ktor.network.tls.TLSRecordType r6 = r15.getType()     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            int[] r7 = io.ktor.network.tls.TLSClientHandshake$input$1.WhenMappings.$EnumSwitchMapping$0     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            int r6 = r6.ordinal()     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            r6 = r7[r6]     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            if (r6 == r3) goto L_0x00be
            if (r6 == r2) goto L_0x008c
            kotlinx.coroutines.channels.SendChannel r12 = r5.getChannel()     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            io.ktor.network.tls.TLSRecord r13 = new io.ktor.network.tls.TLSRecord     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            io.ktor.network.tls.TLSRecordType r7 = r15.getType()     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            r10 = 2
            r11 = 0
            r8 = 0
            r6 = r13
            r6.<init>(r7, r8, r9, r10, r11)     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            r15 = r14
            kotlin.coroutines.Continuation r15 = (kotlin.coroutines.Continuation) r15     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            r14.L$0 = r5     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            r14.I$0 = r1     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            r14.label = r2     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            java.lang.Object r15 = r12.send(r13, r15)     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            if (r15 != r0) goto L_0x0037
            return r0
        L_0x008c:
            if (r1 != 0) goto L_0x00b2
            byte r15 = r9.readByte()     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            if (r15 != r3) goto L_0x0096
            r1 = 1
            goto L_0x0037
        L_0x0096:
            io.ktor.network.tls.TLSException r0 = new io.ktor.network.tls.TLSException     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            r1.<init>()     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            java.lang.String r6 = "Expected flag: 1, received "
            r1.append(r6)     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            r1.append(r15)     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            java.lang.String r15 = " in ChangeCipherSpec"
            r1.append(r15)     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            java.lang.String r15 = r1.toString()     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            r0.<init>(r15, r4, r2, r4)     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            throw r0     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
        L_0x00b2:
            java.lang.String r15 = "Check failed."
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            java.lang.String r15 = r15.toString()     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            r0.<init>(r15)     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            throw r0     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
        L_0x00be:
            io.ktor.network.tls.TLSAlertLevel$Companion r15 = io.ktor.network.tls.TLSAlertLevel.Companion     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            byte r0 = r9.readByte()     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            io.ktor.network.tls.TLSAlertLevel r15 = r15.byCode(r0)     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            io.ktor.network.tls.TLSAlertType$Companion r0 = io.ktor.network.tls.TLSAlertType.Companion     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            byte r1 = r9.readByte()     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            io.ktor.network.tls.TLSAlertType r0 = r0.byCode(r1)     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            io.ktor.network.tls.TLSAlertType r1 = io.ktor.network.tls.TLSAlertType.CloseNotify     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            if (r0 != r1) goto L_0x00e2
            kotlin.Unit r15 = kotlin.Unit.INSTANCE     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
        L_0x00d8:
            io.ktor.network.tls.TLSClientHandshake r0 = r14.this$0
            kotlinx.coroutines.channels.SendChannel r0 = r0.getOutput()
            kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r0, r4, r3, r4)
            return r15
        L_0x00e2:
            io.ktor.network.tls.TLSException r1 = new io.ktor.network.tls.TLSException     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            r6.<init>()     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            java.lang.String r7 = "Received alert during handshake. Level: "
            r6.append(r7)     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            r6.append(r15)     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            java.lang.String r15 = ", code: "
            r6.append(r15)     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            r6.append(r0)     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            java.lang.String r15 = r6.toString()     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            r1.<init>(r15, r4, r2, r4)     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            kotlinx.coroutines.channels.SendChannel r15 = r5.getChannel()     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            java.lang.Throwable r1 = (java.lang.Throwable) r1     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            r15.close(r1)     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            kotlin.Unit r15 = kotlin.Unit.INSTANCE     // Catch:{ ClosedReceiveChannelException -> 0x0114, all -> 0x002b }
            goto L_0x00d8
        L_0x010c:
            kotlinx.coroutines.channels.SendChannel r0 = r5.getChannel()     // Catch:{ all -> 0x0127 }
            r0.close(r15)     // Catch:{ all -> 0x0127 }
            goto L_0x011b
        L_0x0114:
            kotlinx.coroutines.channels.SendChannel r15 = r5.getChannel()     // Catch:{ all -> 0x0127 }
            kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r15, r4, r3, r4)     // Catch:{ all -> 0x0127 }
        L_0x011b:
            io.ktor.network.tls.TLSClientHandshake r15 = r14.this$0
            kotlinx.coroutines.channels.SendChannel r15 = r15.getOutput()
            kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r15, r4, r3, r4)
            kotlin.Unit r15 = kotlin.Unit.INSTANCE
            return r15
        L_0x0127:
            r15 = move-exception
            io.ktor.network.tls.TLSClientHandshake r0 = r14.this$0
            kotlinx.coroutines.channels.SendChannel r0 = r0.getOutput()
            kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r0, r4, r3, r4)
            goto L_0x0133
        L_0x0132:
            throw r15
        L_0x0133:
            goto L_0x0132
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.tls.TLSClientHandshake$input$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
