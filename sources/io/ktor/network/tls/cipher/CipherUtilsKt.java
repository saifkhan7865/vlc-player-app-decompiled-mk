package io.ktor.network.tls.cipher;

import io.ktor.network.util.PoolsKt;
import io.ktor.utils.io.core.ByteBuffersKt;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.Output;
import io.ktor.utils.io.core.OutputArraysJVMKt;
import io.ktor.utils.io.core.OutputKt;
import io.ktor.utils.io.pool.ByteBufferPool;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import javax.crypto.Cipher;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a/\u0010\u0005\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0019\b\u0002\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\rH\u0000\"\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u000e"}, d2 = {"CryptoBufferPool", "Lio/ktor/utils/io/pool/ObjectPool;", "Ljava/nio/ByteBuffer;", "getCryptoBufferPool", "()Lio/ktor/utils/io/pool/ObjectPool;", "cipherLoop", "Lio/ktor/utils/io/core/ByteReadPacket;", "cipher", "Ljavax/crypto/Cipher;", "header", "Lkotlin/Function1;", "Lio/ktor/utils/io/core/BytePacketBuilder;", "", "Lkotlin/ExtensionFunctionType;", "ktor-network-tls"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: CipherUtils.kt */
public final class CipherUtilsKt {
    private static final ObjectPool<ByteBuffer> CryptoBufferPool = new ByteBufferPool(128, 65536);

    public static final ObjectPool<ByteBuffer> getCryptoBufferPool() {
        return CryptoBufferPool;
    }

    public static /* synthetic */ ByteReadPacket cipherLoop$default(ByteReadPacket byteReadPacket, Cipher cipher, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = CipherUtilsKt$cipherLoop$1.INSTANCE;
        }
        return cipherLoop(byteReadPacket, cipher, function1);
    }

    public static final ByteReadPacket cipherLoop(ByteReadPacket byteReadPacket, Cipher cipher, Function1<? super BytePacketBuilder, Unit> function1) {
        BytePacketBuilder bytePacketBuilder;
        Intrinsics.checkNotNullParameter(byteReadPacket, "<this>");
        Intrinsics.checkNotNullParameter(cipher, "cipher");
        Intrinsics.checkNotNullParameter(function1, "header");
        ByteBuffer borrow = PoolsKt.getDefaultByteBufferPool().borrow();
        ByteBuffer borrow2 = CryptoBufferPool.borrow();
        boolean z = true;
        try {
            bytePacketBuilder = new BytePacketBuilder((ObjectPool) null, 1, (DefaultConstructorMarker) null);
            borrow.clear();
            function1.invoke(bytePacketBuilder);
            while (true) {
                int readAvailable = borrow.hasRemaining() ? ByteBuffersKt.readAvailable(byteReadPacket, borrow) : 0;
                borrow.flip();
                if (borrow.hasRemaining() || (readAvailable != -1 && !byteReadPacket.getEndOfInput())) {
                    borrow2.clear();
                    if (cipher.getOutputSize(borrow.remaining()) > borrow2.remaining()) {
                        if (z) {
                            CryptoBufferPool.recycle(borrow2);
                        }
                        ByteBuffer allocate = ByteBuffer.allocate(cipher.getOutputSize(borrow.remaining()));
                        Intrinsics.checkNotNullExpressionValue(allocate, "allocate(cipher.getOutpu…e(srcBuffer.remaining()))");
                        borrow2 = allocate;
                        z = false;
                    }
                    cipher.update(borrow, borrow2);
                    borrow2.flip();
                    OutputArraysJVMKt.writeFully(bytePacketBuilder, borrow2);
                    borrow.compact();
                }
            }
            borrow.hasRemaining();
            borrow2.hasRemaining();
            int outputSize = cipher.getOutputSize(0);
            if (outputSize != 0) {
                if (outputSize > borrow2.capacity()) {
                    byte[] doFinal = cipher.doFinal();
                    Intrinsics.checkNotNullExpressionValue(doFinal, "cipher.doFinal()");
                    OutputKt.writeFully$default((Output) bytePacketBuilder, doFinal, 0, 0, 6, (Object) null);
                } else {
                    borrow2.clear();
                    cipher.doFinal(CipherKt.getEmptyByteBuffer(), borrow2);
                    borrow2.flip();
                    if (!borrow2.hasRemaining()) {
                        byte[] doFinal2 = cipher.doFinal();
                        Intrinsics.checkNotNullExpressionValue(doFinal2, "cipher.doFinal()");
                        OutputKt.writeFully$default((Output) bytePacketBuilder, doFinal2, 0, 0, 6, (Object) null);
                    } else {
                        OutputArraysJVMKt.writeFully(bytePacketBuilder, borrow2);
                    }
                }
            }
            ByteReadPacket build = bytePacketBuilder.build();
            PoolsKt.getDefaultByteBufferPool().recycle(borrow);
            if (z) {
                CryptoBufferPool.recycle(borrow2);
            }
            return build;
        } catch (Throwable th) {
            PoolsKt.getDefaultByteBufferPool().recycle(borrow);
            if (z) {
                CryptoBufferPool.recycle(borrow2);
            }
            throw th;
        }
    }
}
