package io.ktor.util;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.util.NonceKt$nonceGeneratorJob$1", f = "Nonce.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {76}, m = "invokeSuspend", n = {"seedChannel", "previousRoundNonceList", "secureInstance", "weakRandom", "secureBytes", "weakBytes", "randomNonceList", "lastReseed", "index"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "J$0", "I$0"})
/* compiled from: Nonce.kt */
final class NonceKt$nonceGeneratorJob$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int I$0;
    int I$1;
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;

    NonceKt$nonceGeneratorJob$1(Continuation<? super NonceKt$nonceGeneratorJob$1> continuation) {
        super(2, continuation);
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new NonceKt$nonceGeneratorJob$1(continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((NonceKt$nonceGeneratorJob$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x007e A[Catch:{ all -> 0x0040, all -> 0x012e }, LOOP:0: B:13:0x007c->B:14:0x007e, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0093 A[Catch:{ all -> 0x0040, all -> 0x012e }] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x00a1 A[Catch:{ all -> 0x0040, all -> 0x012e }] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00db A[Catch:{ all -> 0x0040, all -> 0x012e }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00fe A[Catch:{ all -> 0x0040, all -> 0x012e }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0101 A[Catch:{ all -> 0x0040, all -> 0x012e }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0110 A[Catch:{ all -> 0x0040, all -> 0x012e }] */
    public final java.lang.Object invokeSuspend(java.lang.Object r24) {
        /*
            r23 = this;
            r1 = r23
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r1.label
            r3 = 1
            if (r2 == 0) goto L_0x004b
            if (r2 != r3) goto L_0x0043
            int r2 = r1.I$1
            int r4 = r1.I$0
            long r5 = r1.J$0
            java.lang.Object r7 = r1.L$6
            java.util.List r7 = (java.util.List) r7
            java.lang.Object r8 = r1.L$5
            byte[] r8 = (byte[]) r8
            java.lang.Object r9 = r1.L$4
            byte[] r9 = (byte[]) r9
            java.lang.Object r10 = r1.L$3
            java.security.SecureRandom r10 = (java.security.SecureRandom) r10
            java.lang.Object r11 = r1.L$2
            java.security.SecureRandom r11 = (java.security.SecureRandom) r11
            java.lang.Object r12 = r1.L$1
            java.util.ArrayList r12 = (java.util.ArrayList) r12
            java.lang.Object r13 = r1.L$0
            kotlinx.coroutines.channels.Channel r13 = (kotlinx.coroutines.channels.Channel) r13
            kotlin.ResultKt.throwOnFailure(r24)     // Catch:{ all -> 0x0040 }
            r20 = r8
            r8 = r7
            r7 = r10
            r10 = r20
            r21 = r5
            r6 = r11
            r5 = r12
            r11 = r21
            goto L_0x00ff
        L_0x0040:
            r0 = move-exception
            goto L_0x0122
        L_0x0043:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x004b:
            kotlin.ResultKt.throwOnFailure(r24)
            kotlinx.coroutines.channels.Channel r2 = io.ktor.util.NonceKt.getSeedChannel()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.security.SecureRandom r5 = io.ktor.util.NonceKt.lookupSecureRandom()
            java.lang.String r6 = "SHA1PRNG"
            java.security.SecureRandom r6 = java.security.SecureRandom.getInstance(r6)
            r7 = 128(0x80, float:1.794E-43)
            byte[] r8 = new byte[r7]
            r9 = 512(0x200, float:7.175E-43)
            byte[] r9 = new byte[r9]
            byte[] r7 = r5.generateSeed(r7)
            r6.setSeed(r7)
            r10 = 0
            r13 = r2
        L_0x0073:
            r5.nextBytes(r8)     // Catch:{ all -> 0x0040 }
            r6.nextBytes(r9)     // Catch:{ all -> 0x0040 }
            int r2 = r8.length     // Catch:{ all -> 0x0040 }
            r7 = 0
            r12 = 0
        L_0x007c:
            if (r12 >= r2) goto L_0x0087
            int r14 = r12 * 4
            byte r15 = r8[r12]     // Catch:{ all -> 0x0040 }
            r9[r14] = r15     // Catch:{ all -> 0x0040 }
            int r12 = r12 + 1
            goto L_0x007c
        L_0x0087:
            long r14 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0040 }
            long r16 = r14 - r10
            r18 = 30000(0x7530, double:1.4822E-319)
            int r2 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r2 <= 0) goto L_0x00a1
            long r10 = r10 - r14
            r6.setSeed(r10)     // Catch:{ all -> 0x0040 }
            int r2 = r8.length     // Catch:{ all -> 0x0040 }
            byte[] r2 = r5.generateSeed(r2)     // Catch:{ all -> 0x0040 }
            r6.setSeed(r2)     // Catch:{ all -> 0x0040 }
            r10 = r14
            goto L_0x00a4
        L_0x00a1:
            r6.setSeed(r8)     // Catch:{ all -> 0x0040 }
        L_0x00a4:
            java.lang.String r2 = io.ktor.util.CryptoKt.hex((byte[]) r9)     // Catch:{ all -> 0x0040 }
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2     // Catch:{ all -> 0x0040 }
            r12 = 16
            java.util.List r2 = kotlin.text.StringsKt.chunked(r2, r12)     // Catch:{ all -> 0x0040 }
            java.util.Collection r2 = (java.util.Collection) r2     // Catch:{ all -> 0x0040 }
            r12 = r4
            java.lang.Iterable r12 = (java.lang.Iterable) r12     // Catch:{ all -> 0x0040 }
            java.util.List r2 = kotlin.collections.CollectionsKt.plus(r2, r12)     // Catch:{ all -> 0x0040 }
            java.lang.Iterable r2 = (java.lang.Iterable) r2     // Catch:{ all -> 0x0040 }
            java.lang.String r12 = "weakRandom"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r12)     // Catch:{ all -> 0x0040 }
            r12 = r6
            java.util.Random r12 = (java.util.Random) r12     // Catch:{ all -> 0x0040 }
            java.util.List r2 = kotlin.collections.CollectionsKt.shuffled(r2, r12)     // Catch:{ all -> 0x0040 }
            int r12 = r2.size()     // Catch:{ all -> 0x0040 }
            int r12 = r12 / 2
            r7 = r6
            r6 = r5
            r5 = r4
            r4 = 0
            r20 = r8
            r8 = r2
            r2 = r12
            r11 = r10
            r10 = r9
            r9 = r20
        L_0x00d9:
            if (r4 >= r2) goto L_0x0101
            java.lang.Object r14 = r8.get(r4)     // Catch:{ all -> 0x0040 }
            r15 = r1
            kotlin.coroutines.Continuation r15 = (kotlin.coroutines.Continuation) r15     // Catch:{ all -> 0x0040 }
            r1.L$0 = r13     // Catch:{ all -> 0x0040 }
            r1.L$1 = r5     // Catch:{ all -> 0x0040 }
            r1.L$2 = r6     // Catch:{ all -> 0x0040 }
            r1.L$3 = r7     // Catch:{ all -> 0x0040 }
            r1.L$4 = r9     // Catch:{ all -> 0x0040 }
            r1.L$5 = r10     // Catch:{ all -> 0x0040 }
            r1.L$6 = r8     // Catch:{ all -> 0x0040 }
            r1.J$0 = r11     // Catch:{ all -> 0x0040 }
            r1.I$0 = r4     // Catch:{ all -> 0x0040 }
            r1.I$1 = r2     // Catch:{ all -> 0x0040 }
            r1.label = r3     // Catch:{ all -> 0x0040 }
            java.lang.Object r14 = r13.send(r14, r15)     // Catch:{ all -> 0x0040 }
            if (r14 != r0) goto L_0x00ff
            return r0
        L_0x00ff:
            int r4 = r4 + r3
            goto L_0x00d9
        L_0x0101:
            r5.clear()     // Catch:{ all -> 0x0040 }
            int r2 = r8.size()     // Catch:{ all -> 0x0040 }
            int r2 = r2 / 2
            int r4 = r8.size()     // Catch:{ all -> 0x0040 }
        L_0x010e:
            if (r2 >= r4) goto L_0x011a
            java.lang.Object r14 = r8.get(r2)     // Catch:{ all -> 0x0040 }
            r5.add(r14)     // Catch:{ all -> 0x0040 }
            int r2 = r2 + 1
            goto L_0x010e
        L_0x011a:
            r4 = r5
            r5 = r6
            r6 = r7
            r8 = r9
            r9 = r10
            r10 = r11
            goto L_0x0073
        L_0x0122:
            r2 = 0
            r13.close(r0)     // Catch:{ all -> 0x012e }
            kotlinx.coroutines.channels.SendChannel r13 = (kotlinx.coroutines.channels.SendChannel) r13
            kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r13, r2, r3, r2)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x012e:
            r0 = move-exception
            r4 = r0
            kotlinx.coroutines.channels.SendChannel r13 = (kotlinx.coroutines.channels.SendChannel) r13
            kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r13, r2, r3, r2)
            goto L_0x0137
        L_0x0136:
            throw r4
        L_0x0137:
            goto L_0x0136
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.NonceKt$nonceGeneratorJob$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
