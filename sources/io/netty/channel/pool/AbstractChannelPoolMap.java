package io.netty.channel.pool;

import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import io.netty.channel.pool.ChannelPool;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.GlobalEventExecutor;
import io.netty.util.concurrent.Promise;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.ReadOnlyIterator;
import java.io.Closeable;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public abstract class AbstractChannelPoolMap<K, P extends ChannelPool> implements ChannelPoolMap<K, P>, Iterable<Map.Entry<K, P>>, Closeable {
    private final ConcurrentMap<K, P> map = PlatformDependent.newConcurrentHashMap();

    /* access modifiers changed from: protected */
    public abstract P newPool(K k);

    public final P get(K k) {
        P p = (ChannelPool) this.map.get(ObjectUtil.checkNotNull(k, LeanbackPreferenceDialogFragment.ARG_KEY));
        if (p != null) {
            return p;
        }
        P newPool = newPool(k);
        P p2 = (ChannelPool) this.map.putIfAbsent(k, newPool);
        if (p2 == null) {
            return newPool;
        }
        poolCloseAsyncIfSupported(newPool);
        return p2;
    }

    public final boolean remove(K k) {
        ChannelPool channelPool = (ChannelPool) this.map.remove(ObjectUtil.checkNotNull(k, LeanbackPreferenceDialogFragment.ARG_KEY));
        if (channelPool == null) {
            return false;
        }
        poolCloseAsyncIfSupported(channelPool);
        return true;
    }

    private Future<Boolean> removeAsyncIfSupported(K k) {
        ChannelPool channelPool = (ChannelPool) this.map.remove(ObjectUtil.checkNotNull(k, LeanbackPreferenceDialogFragment.ARG_KEY));
        if (channelPool == null) {
            return GlobalEventExecutor.INSTANCE.newSucceededFuture(Boolean.FALSE);
        }
        final Promise newPromise = GlobalEventExecutor.INSTANCE.newPromise();
        poolCloseAsyncIfSupported(channelPool).addListener(new GenericFutureListener<Future<? super Void>>() {
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    newPromise.setSuccess(Boolean.TRUE);
                } else {
                    newPromise.setFailure(future.cause());
                }
            }
        });
        return newPromise;
    }

    private static Future<Void> poolCloseAsyncIfSupported(ChannelPool channelPool) {
        if (channelPool instanceof SimpleChannelPool) {
            return ((SimpleChannelPool) channelPool).closeAsync();
        }
        try {
            channelPool.close();
            return GlobalEventExecutor.INSTANCE.newSucceededFuture(null);
        } catch (Exception e) {
            return GlobalEventExecutor.INSTANCE.newFailedFuture(e);
        }
    }

    public final Iterator<Map.Entry<K, P>> iterator() {
        return new ReadOnlyIterator(this.map.entrySet().iterator());
    }

    public final int size() {
        return this.map.size();
    }

    public final boolean isEmpty() {
        return this.map.isEmpty();
    }

    public final boolean contains(K k) {
        return this.map.containsKey(ObjectUtil.checkNotNull(k, LeanbackPreferenceDialogFragment.ARG_KEY));
    }

    public final void close() {
        for (Object removeAsyncIfSupported : this.map.keySet()) {
            removeAsyncIfSupported(removeAsyncIfSupported).syncUninterruptibly();
        }
    }
}
