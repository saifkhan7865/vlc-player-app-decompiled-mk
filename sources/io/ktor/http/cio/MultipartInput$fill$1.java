package io.ktor.http.cio;

import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.pool.ByteArrayPoolKt;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.http.cio.MultipartInput$fill$1", f = "CIOMultipartDataBase.kt", i = {0}, l = {208}, m = "invokeSuspend", n = {"buffer"}, s = {"L$0"})
/* compiled from: CIOMultipartDataBase.kt */
final class MultipartInput$fill$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final /* synthetic */ ByteBuffer $destination;
    final /* synthetic */ int $length;
    final /* synthetic */ int $offset;
    Object L$0;
    int label;
    final /* synthetic */ MultipartInput this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MultipartInput$fill$1(MultipartInput multipartInput, int i, ByteBuffer byteBuffer, int i2, Continuation<? super MultipartInput$fill$1> continuation) {
        super(2, continuation);
        this.this$0 = multipartInput;
        this.$length = i;
        this.$destination = byteBuffer;
        this.$offset = i2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MultipartInput$fill$1(this.this$0, this.$length, this.$destination, this.$offset, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
        return ((MultipartInput$fill$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Throwable th;
        byte[] bArr;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            byte[] borrow = ByteArrayPoolKt.getByteArrayPool().borrow();
            try {
                this.L$0 = borrow;
                this.label = 1;
                Object readAvailable = this.this$0.tail.readAvailable(borrow, 0, Math.min(this.$length, borrow.length), this);
                if (readAvailable == coroutine_suspended) {
                    return coroutine_suspended;
                }
                bArr = borrow;
                obj = readAvailable;
            } catch (Throwable th2) {
                Throwable th3 = th2;
                bArr = borrow;
                th = th3;
                ByteArrayPoolKt.getByteArrayPool().recycle(bArr);
                throw th;
            }
        } else if (i == 1) {
            bArr = (byte[]) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th4) {
                th = th4;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        int coerceAtLeast = RangesKt.coerceAtLeast(((Number) obj).intValue(), 0);
        ByteBuffer byteBuffer = this.$destination;
        int i2 = this.$offset;
        ByteBuffer order = ByteBuffer.wrap(bArr, 0, coerceAtLeast).slice().order(ByteOrder.BIG_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(order, "wrap(this, offset, lengt…der(ByteOrder.BIG_ENDIAN)");
        Memory.m1510copyToJT6ljtQ(Memory.m1509constructorimpl(order), byteBuffer, 0, coerceAtLeast, i2);
        ByteArrayPoolKt.getByteArrayPool().recycle(bArr);
        return Boxing.boxInt(coerceAtLeast);
    }
}
