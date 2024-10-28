package io.netty.channel;

import androidx.tvprovider.media.tv.TvContractCompat;
import io.netty.util.concurrent.CompleteFuture;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.internal.ObjectUtil;

abstract class CompleteChannelFuture extends CompleteFuture<Void> implements ChannelFuture {
    private final Channel channel;

    public ChannelFuture await() throws InterruptedException {
        return this;
    }

    public ChannelFuture awaitUninterruptibly() {
        return this;
    }

    public Void getNow() {
        return null;
    }

    public boolean isVoid() {
        return false;
    }

    public ChannelFuture sync() throws InterruptedException {
        return this;
    }

    public ChannelFuture syncUninterruptibly() {
        return this;
    }

    protected CompleteChannelFuture(Channel channel2, EventExecutor eventExecutor) {
        super(eventExecutor);
        this.channel = (Channel) ObjectUtil.checkNotNull(channel2, TvContractCompat.PARAM_CHANNEL);
    }

    /* access modifiers changed from: protected */
    public EventExecutor executor() {
        EventExecutor executor = super.executor();
        return executor == null ? channel().eventLoop() : executor;
    }

    public ChannelFuture addListener(GenericFutureListener<? extends Future<? super Void>> genericFutureListener) {
        super.addListener(genericFutureListener);
        return this;
    }

    public ChannelFuture addListeners(GenericFutureListener<? extends Future<? super Void>>... genericFutureListenerArr) {
        super.addListeners(genericFutureListenerArr);
        return this;
    }

    public ChannelFuture removeListener(GenericFutureListener<? extends Future<? super Void>> genericFutureListener) {
        super.removeListener(genericFutureListener);
        return this;
    }

    public ChannelFuture removeListeners(GenericFutureListener<? extends Future<? super Void>>... genericFutureListenerArr) {
        super.removeListeners(genericFutureListenerArr);
        return this;
    }

    public Channel channel() {
        return this.channel;
    }
}
