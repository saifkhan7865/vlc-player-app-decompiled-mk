package io.netty.channel;

import androidx.tvprovider.media.tv.TvContractCompat;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.internal.ObjectUtil;

public final class CoalescingBufferQueue extends AbstractCoalescingBufferQueue {
    private final Channel channel;

    public CoalescingBufferQueue(Channel channel2) {
        this(channel2, 4);
    }

    public CoalescingBufferQueue(Channel channel2, int i) {
        this(channel2, i, false);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CoalescingBufferQueue(Channel channel2, int i, boolean z) {
        super(z ? channel2 : null, i);
        this.channel = (Channel) ObjectUtil.checkNotNull(channel2, TvContractCompat.PARAM_CHANNEL);
    }

    public ByteBuf remove(int i, ChannelPromise channelPromise) {
        return remove(this.channel.alloc(), i, channelPromise);
    }

    public void releaseAndFailAll(Throwable th) {
        releaseAndFailAll(this.channel, th);
    }

    /* access modifiers changed from: protected */
    public ByteBuf compose(ByteBufAllocator byteBufAllocator, ByteBuf byteBuf, ByteBuf byteBuf2) {
        if (!(byteBuf instanceof CompositeByteBuf)) {
            return composeIntoComposite(byteBufAllocator, byteBuf, byteBuf2);
        }
        CompositeByteBuf compositeByteBuf = (CompositeByteBuf) byteBuf;
        compositeByteBuf.addComponent(true, byteBuf2);
        return compositeByteBuf;
    }

    /* access modifiers changed from: protected */
    public ByteBuf removeEmptyValue() {
        return Unpooled.EMPTY_BUFFER;
    }
}
