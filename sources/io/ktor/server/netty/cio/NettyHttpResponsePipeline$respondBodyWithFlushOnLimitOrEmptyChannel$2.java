package io.ktor.server.netty.cio;

import androidx.tvprovider.media.tv.TvContractCompat;
import io.ktor.utils.io.ByteReadChannel;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "channel", "Lio/ktor/utils/io/ByteReadChannel;", "unflushedBytes", "", "invoke", "(Lio/ktor/utils/io/ByteReadChannel;I)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyHttpResponsePipeline.kt */
final class NettyHttpResponsePipeline$respondBodyWithFlushOnLimitOrEmptyChannel$2 extends Lambda implements Function2<ByteReadChannel, Integer, Boolean> {
    public static final NettyHttpResponsePipeline$respondBodyWithFlushOnLimitOrEmptyChannel$2 INSTANCE = new NettyHttpResponsePipeline$respondBodyWithFlushOnLimitOrEmptyChannel$2();

    NettyHttpResponsePipeline$respondBodyWithFlushOnLimitOrEmptyChannel$2() {
        super(2);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke((ByteReadChannel) obj, ((Number) obj2).intValue());
    }

    public final Boolean invoke(ByteReadChannel byteReadChannel, int i) {
        Intrinsics.checkNotNullParameter(byteReadChannel, TvContractCompat.PARAM_CHANNEL);
        return Boolean.valueOf(i >= 65536 || byteReadChannel.getAvailableForRead() == 0);
    }
}
