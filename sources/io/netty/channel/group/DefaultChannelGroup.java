package io.netty.channel.group;

import io.ktor.http.ContentDisposition;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelId;
import io.netty.channel.ServerChannel;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class DefaultChannelGroup extends AbstractSet<Channel> implements ChannelGroup {
    private static final AtomicInteger nextId = new AtomicInteger();
    private volatile boolean closed;
    private final EventExecutor executor;
    private final String name;
    private final ConcurrentMap<ChannelId, Channel> nonServerChannels;
    private final ChannelFutureListener remover;
    private final ConcurrentMap<ChannelId, Channel> serverChannels;
    private final boolean stayClosed;
    private final VoidChannelGroupFuture voidFuture;

    public boolean equals(Object obj) {
        return this == obj;
    }

    public DefaultChannelGroup(EventExecutor eventExecutor) {
        this(eventExecutor, false);
    }

    public DefaultChannelGroup(String str, EventExecutor eventExecutor) {
        this(str, eventExecutor, false);
    }

    public DefaultChannelGroup(EventExecutor eventExecutor, boolean z) {
        this("group-0x" + Integer.toHexString(nextId.incrementAndGet()), eventExecutor, z);
    }

    public DefaultChannelGroup(String str, EventExecutor eventExecutor, boolean z) {
        this.serverChannels = PlatformDependent.newConcurrentHashMap();
        this.nonServerChannels = PlatformDependent.newConcurrentHashMap();
        this.remover = new ChannelFutureListener() {
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                DefaultChannelGroup.this.remove(channelFuture.channel());
            }
        };
        this.voidFuture = new VoidChannelGroupFuture(this);
        ObjectUtil.checkNotNull(str, ContentDisposition.Parameters.Name);
        this.name = str;
        this.executor = eventExecutor;
        this.stayClosed = z;
    }

    public String name() {
        return this.name;
    }

    public Channel find(ChannelId channelId) {
        Channel channel = (Channel) this.nonServerChannels.get(channelId);
        if (channel != null) {
            return channel;
        }
        return (Channel) this.serverChannels.get(channelId);
    }

    public boolean isEmpty() {
        return this.nonServerChannels.isEmpty() && this.serverChannels.isEmpty();
    }

    public int size() {
        return this.nonServerChannels.size() + this.serverChannels.size();
    }

    public boolean contains(Object obj) {
        if (obj instanceof ServerChannel) {
            return this.serverChannels.containsValue(obj);
        }
        if (obj instanceof Channel) {
            return this.nonServerChannels.containsValue(obj);
        }
        return false;
    }

    public boolean add(Channel channel) {
        boolean z = (channel instanceof ServerChannel ? this.serverChannels : this.nonServerChannels).putIfAbsent(channel.id(), channel) == null;
        if (z) {
            channel.closeFuture().addListener(this.remover);
        }
        if (this.stayClosed && this.closed) {
            channel.close();
        }
        return z;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: io.netty.channel.Channel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: io.netty.channel.Channel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v16, resolved type: io.netty.channel.Channel} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean remove(java.lang.Object r2) {
        /*
            r1 = this;
            boolean r0 = r2 instanceof io.netty.channel.ChannelId
            if (r0 == 0) goto L_0x0018
            java.util.concurrent.ConcurrentMap<io.netty.channel.ChannelId, io.netty.channel.Channel> r0 = r1.nonServerChannels
            java.lang.Object r0 = r0.remove(r2)
            io.netty.channel.Channel r0 = (io.netty.channel.Channel) r0
            if (r0 != 0) goto L_0x003f
            java.util.concurrent.ConcurrentMap<io.netty.channel.ChannelId, io.netty.channel.Channel> r0 = r1.serverChannels
            java.lang.Object r2 = r0.remove(r2)
            r0 = r2
            io.netty.channel.Channel r0 = (io.netty.channel.Channel) r0
            goto L_0x003f
        L_0x0018:
            boolean r0 = r2 instanceof io.netty.channel.Channel
            if (r0 == 0) goto L_0x003e
            io.netty.channel.Channel r2 = (io.netty.channel.Channel) r2
            boolean r0 = r2 instanceof io.netty.channel.ServerChannel
            if (r0 == 0) goto L_0x0030
            java.util.concurrent.ConcurrentMap<io.netty.channel.ChannelId, io.netty.channel.Channel> r0 = r1.serverChannels
            io.netty.channel.ChannelId r2 = r2.id()
            java.lang.Object r2 = r0.remove(r2)
            r0 = r2
            io.netty.channel.Channel r0 = (io.netty.channel.Channel) r0
            goto L_0x003f
        L_0x0030:
            java.util.concurrent.ConcurrentMap<io.netty.channel.ChannelId, io.netty.channel.Channel> r0 = r1.nonServerChannels
            io.netty.channel.ChannelId r2 = r2.id()
            java.lang.Object r2 = r0.remove(r2)
            r0 = r2
            io.netty.channel.Channel r0 = (io.netty.channel.Channel) r0
            goto L_0x003f
        L_0x003e:
            r0 = 0
        L_0x003f:
            if (r0 != 0) goto L_0x0043
            r2 = 0
            return r2
        L_0x0043:
            io.netty.channel.ChannelFuture r2 = r0.closeFuture()
            io.netty.channel.ChannelFutureListener r0 = r1.remover
            r2.removeListener(r0)
            r2 = 1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.channel.group.DefaultChannelGroup.remove(java.lang.Object):boolean");
    }

    public void clear() {
        this.nonServerChannels.clear();
        this.serverChannels.clear();
    }

    public Iterator<Channel> iterator() {
        return new CombinedIterator(this.serverChannels.values().iterator(), this.nonServerChannels.values().iterator());
    }

    public Object[] toArray() {
        ArrayList arrayList = new ArrayList(size());
        arrayList.addAll(this.serverChannels.values());
        arrayList.addAll(this.nonServerChannels.values());
        return arrayList.toArray();
    }

    public <T> T[] toArray(T[] tArr) {
        ArrayList arrayList = new ArrayList(size());
        arrayList.addAll(this.serverChannels.values());
        arrayList.addAll(this.nonServerChannels.values());
        return arrayList.toArray(tArr);
    }

    public ChannelGroupFuture close() {
        return close(ChannelMatchers.all());
    }

    public ChannelGroupFuture disconnect() {
        return disconnect(ChannelMatchers.all());
    }

    public ChannelGroupFuture deregister() {
        return deregister(ChannelMatchers.all());
    }

    public ChannelGroupFuture write(Object obj) {
        return write(obj, ChannelMatchers.all());
    }

    private static Object safeDuplicate(Object obj) {
        if (obj instanceof ByteBuf) {
            return ((ByteBuf) obj).retainedDuplicate();
        }
        if (obj instanceof ByteBufHolder) {
            return ((ByteBufHolder) obj).retainedDuplicate();
        }
        return ReferenceCountUtil.retain(obj);
    }

    public ChannelGroupFuture write(Object obj, ChannelMatcher channelMatcher) {
        return write(obj, channelMatcher, false);
    }

    public ChannelGroupFuture write(Object obj, ChannelMatcher channelMatcher, boolean z) {
        ChannelGroupFuture channelGroupFuture;
        ObjectUtil.checkNotNull(obj, "message");
        ObjectUtil.checkNotNull(channelMatcher, "matcher");
        if (z) {
            for (Channel channel : this.nonServerChannels.values()) {
                if (channelMatcher.matches(channel)) {
                    channel.write(safeDuplicate(obj), channel.voidPromise());
                }
            }
            channelGroupFuture = this.voidFuture;
        } else {
            LinkedHashMap linkedHashMap = new LinkedHashMap(this.nonServerChannels.size());
            for (Channel channel2 : this.nonServerChannels.values()) {
                if (channelMatcher.matches(channel2)) {
                    linkedHashMap.put(channel2, channel2.write(safeDuplicate(obj)));
                }
            }
            channelGroupFuture = new DefaultChannelGroupFuture((ChannelGroup) this, (Map<Channel, ChannelFuture>) linkedHashMap, this.executor);
        }
        ReferenceCountUtil.release(obj);
        return channelGroupFuture;
    }

    public ChannelGroup flush() {
        return flush(ChannelMatchers.all());
    }

    public ChannelGroupFuture flushAndWrite(Object obj) {
        return writeAndFlush(obj);
    }

    public ChannelGroupFuture writeAndFlush(Object obj) {
        return writeAndFlush(obj, ChannelMatchers.all());
    }

    public ChannelGroupFuture disconnect(ChannelMatcher channelMatcher) {
        ObjectUtil.checkNotNull(channelMatcher, "matcher");
        LinkedHashMap linkedHashMap = new LinkedHashMap(size());
        for (Channel channel : this.serverChannels.values()) {
            if (channelMatcher.matches(channel)) {
                linkedHashMap.put(channel, channel.disconnect());
            }
        }
        for (Channel channel2 : this.nonServerChannels.values()) {
            if (channelMatcher.matches(channel2)) {
                linkedHashMap.put(channel2, channel2.disconnect());
            }
        }
        return new DefaultChannelGroupFuture((ChannelGroup) this, (Map<Channel, ChannelFuture>) linkedHashMap, this.executor);
    }

    public ChannelGroupFuture close(ChannelMatcher channelMatcher) {
        ObjectUtil.checkNotNull(channelMatcher, "matcher");
        LinkedHashMap linkedHashMap = new LinkedHashMap(size());
        if (this.stayClosed) {
            this.closed = true;
        }
        for (Channel channel : this.serverChannels.values()) {
            if (channelMatcher.matches(channel)) {
                linkedHashMap.put(channel, channel.close());
            }
        }
        for (Channel channel2 : this.nonServerChannels.values()) {
            if (channelMatcher.matches(channel2)) {
                linkedHashMap.put(channel2, channel2.close());
            }
        }
        return new DefaultChannelGroupFuture((ChannelGroup) this, (Map<Channel, ChannelFuture>) linkedHashMap, this.executor);
    }

    public ChannelGroupFuture deregister(ChannelMatcher channelMatcher) {
        ObjectUtil.checkNotNull(channelMatcher, "matcher");
        LinkedHashMap linkedHashMap = new LinkedHashMap(size());
        for (Channel channel : this.serverChannels.values()) {
            if (channelMatcher.matches(channel)) {
                linkedHashMap.put(channel, channel.deregister());
            }
        }
        for (Channel channel2 : this.nonServerChannels.values()) {
            if (channelMatcher.matches(channel2)) {
                linkedHashMap.put(channel2, channel2.deregister());
            }
        }
        return new DefaultChannelGroupFuture((ChannelGroup) this, (Map<Channel, ChannelFuture>) linkedHashMap, this.executor);
    }

    public ChannelGroup flush(ChannelMatcher channelMatcher) {
        for (Channel channel : this.nonServerChannels.values()) {
            if (channelMatcher.matches(channel)) {
                channel.flush();
            }
        }
        return this;
    }

    public ChannelGroupFuture flushAndWrite(Object obj, ChannelMatcher channelMatcher) {
        return writeAndFlush(obj, channelMatcher);
    }

    public ChannelGroupFuture writeAndFlush(Object obj, ChannelMatcher channelMatcher) {
        return writeAndFlush(obj, channelMatcher, false);
    }

    public ChannelGroupFuture writeAndFlush(Object obj, ChannelMatcher channelMatcher, boolean z) {
        ChannelGroupFuture channelGroupFuture;
        ObjectUtil.checkNotNull(obj, "message");
        if (z) {
            for (Channel channel : this.nonServerChannels.values()) {
                if (channelMatcher.matches(channel)) {
                    channel.writeAndFlush(safeDuplicate(obj), channel.voidPromise());
                }
            }
            channelGroupFuture = this.voidFuture;
        } else {
            LinkedHashMap linkedHashMap = new LinkedHashMap(this.nonServerChannels.size());
            for (Channel channel2 : this.nonServerChannels.values()) {
                if (channelMatcher.matches(channel2)) {
                    linkedHashMap.put(channel2, channel2.writeAndFlush(safeDuplicate(obj)));
                }
            }
            channelGroupFuture = new DefaultChannelGroupFuture((ChannelGroup) this, (Map<Channel, ChannelFuture>) linkedHashMap, this.executor);
        }
        ReferenceCountUtil.release(obj);
        return channelGroupFuture;
    }

    public ChannelGroupFuture newCloseFuture() {
        return newCloseFuture(ChannelMatchers.all());
    }

    public ChannelGroupFuture newCloseFuture(ChannelMatcher channelMatcher) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(size());
        for (Channel channel : this.serverChannels.values()) {
            if (channelMatcher.matches(channel)) {
                linkedHashMap.put(channel, channel.closeFuture());
            }
        }
        for (Channel channel2 : this.nonServerChannels.values()) {
            if (channelMatcher.matches(channel2)) {
                linkedHashMap.put(channel2, channel2.closeFuture());
            }
        }
        return new DefaultChannelGroupFuture((ChannelGroup) this, (Map<Channel, ChannelFuture>) linkedHashMap, this.executor);
    }

    public int hashCode() {
        return System.identityHashCode(this);
    }

    public int compareTo(ChannelGroup channelGroup) {
        int compareTo = name().compareTo(channelGroup.name());
        if (compareTo != 0) {
            return compareTo;
        }
        return System.identityHashCode(this) - System.identityHashCode(channelGroup);
    }

    public String toString() {
        return StringUtil.simpleClassName((Object) this) + "(name: " + name() + ", size: " + size() + ')';
    }
}
