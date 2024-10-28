package io.netty.channel;

import io.netty.channel.MessageSizeEstimator;
import io.netty.util.internal.ObjectUtil;

abstract class PendingBytesTracker implements MessageSizeEstimator.Handle {
    private final MessageSizeEstimator.Handle estimatorHandle;

    public abstract void decrementPendingOutboundBytes(long j);

    public abstract void incrementPendingOutboundBytes(long j);

    private PendingBytesTracker(MessageSizeEstimator.Handle handle) {
        this.estimatorHandle = (MessageSizeEstimator.Handle) ObjectUtil.checkNotNull(handle, "estimatorHandle");
    }

    public final int size(Object obj) {
        return this.estimatorHandle.size(obj);
    }

    static PendingBytesTracker newTracker(Channel channel) {
        if (channel.pipeline() instanceof DefaultChannelPipeline) {
            return new DefaultChannelPipelinePendingBytesTracker((DefaultChannelPipeline) channel.pipeline());
        }
        ChannelOutboundBuffer outboundBuffer = channel.unsafe().outboundBuffer();
        MessageSizeEstimator.Handle newHandle = channel.config().getMessageSizeEstimator().newHandle();
        return outboundBuffer == null ? new NoopPendingBytesTracker(newHandle) : new ChannelOutboundBufferPendingBytesTracker(outboundBuffer, newHandle);
    }

    private static final class DefaultChannelPipelinePendingBytesTracker extends PendingBytesTracker {
        private final DefaultChannelPipeline pipeline;

        DefaultChannelPipelinePendingBytesTracker(DefaultChannelPipeline defaultChannelPipeline) {
            super(defaultChannelPipeline.estimatorHandle());
            this.pipeline = defaultChannelPipeline;
        }

        public void incrementPendingOutboundBytes(long j) {
            this.pipeline.incrementPendingOutboundBytes(j);
        }

        public void decrementPendingOutboundBytes(long j) {
            this.pipeline.decrementPendingOutboundBytes(j);
        }
    }

    private static final class ChannelOutboundBufferPendingBytesTracker extends PendingBytesTracker {
        private final ChannelOutboundBuffer buffer;

        ChannelOutboundBufferPendingBytesTracker(ChannelOutboundBuffer channelOutboundBuffer, MessageSizeEstimator.Handle handle) {
            super(handle);
            this.buffer = channelOutboundBuffer;
        }

        public void incrementPendingOutboundBytes(long j) {
            this.buffer.incrementPendingOutboundBytes(j);
        }

        public void decrementPendingOutboundBytes(long j) {
            this.buffer.decrementPendingOutboundBytes(j);
        }
    }

    private static final class NoopPendingBytesTracker extends PendingBytesTracker {
        public void decrementPendingOutboundBytes(long j) {
        }

        public void incrementPendingOutboundBytes(long j) {
        }

        NoopPendingBytesTracker(MessageSizeEstimator.Handle handle) {
            super(handle);
        }
    }
}
