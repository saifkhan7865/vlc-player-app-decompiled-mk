package io.netty.handler.codec.spdy;

import io.netty.channel.ChannelPromise;
import io.netty.util.internal.PlatformDependent;
import java.util.Comparator;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

final class SpdySession {
    private final AtomicInteger activeLocalStreams = new AtomicInteger();
    private final AtomicInteger activeRemoteStreams = new AtomicInteger();
    /* access modifiers changed from: private */
    public final Map<Integer, StreamState> activeStreams = PlatformDependent.newConcurrentHashMap();
    private final AtomicInteger receiveWindowSize;
    private final AtomicInteger sendWindowSize;
    private final StreamComparator streamComparator = new StreamComparator();

    SpdySession(int i, int i2) {
        this.sendWindowSize = new AtomicInteger(i);
        this.receiveWindowSize = new AtomicInteger(i2);
    }

    /* access modifiers changed from: package-private */
    public int numActiveStreams(boolean z) {
        if (z) {
            return this.activeRemoteStreams.get();
        }
        return this.activeLocalStreams.get();
    }

    /* access modifiers changed from: package-private */
    public boolean noActiveStreams() {
        return this.activeStreams.isEmpty();
    }

    /* access modifiers changed from: package-private */
    public boolean isActiveStream(int i) {
        return this.activeStreams.containsKey(Integer.valueOf(i));
    }

    /* access modifiers changed from: package-private */
    public Map<Integer, StreamState> activeStreams() {
        TreeMap treeMap = new TreeMap(this.streamComparator);
        treeMap.putAll(this.activeStreams);
        return treeMap;
    }

    /* access modifiers changed from: package-private */
    public void acceptStream(int i, byte b, boolean z, boolean z2, int i2, int i3, boolean z3) {
        if ((z && z2) || this.activeStreams.put(Integer.valueOf(i), new StreamState(b, z, z2, i2, i3)) != null) {
            return;
        }
        if (z3) {
            this.activeRemoteStreams.incrementAndGet();
        } else {
            this.activeLocalStreams.incrementAndGet();
        }
    }

    private StreamState removeActiveStream(int i, boolean z) {
        StreamState remove = this.activeStreams.remove(Integer.valueOf(i));
        if (remove != null) {
            if (z) {
                this.activeRemoteStreams.decrementAndGet();
            } else {
                this.activeLocalStreams.decrementAndGet();
            }
        }
        return remove;
    }

    /* access modifiers changed from: package-private */
    public void removeStream(int i, Throwable th, boolean z) {
        StreamState removeActiveStream = removeActiveStream(i, z);
        if (removeActiveStream != null) {
            removeActiveStream.clearPendingWrites(th);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isRemoteSideClosed(int i) {
        StreamState streamState = this.activeStreams.get(Integer.valueOf(i));
        return streamState == null || streamState.isRemoteSideClosed();
    }

    /* access modifiers changed from: package-private */
    public void closeRemoteSide(int i, boolean z) {
        StreamState streamState = this.activeStreams.get(Integer.valueOf(i));
        if (streamState != null) {
            streamState.closeRemoteSide();
            if (streamState.isLocalSideClosed()) {
                removeActiveStream(i, z);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isLocalSideClosed(int i) {
        StreamState streamState = this.activeStreams.get(Integer.valueOf(i));
        return streamState == null || streamState.isLocalSideClosed();
    }

    /* access modifiers changed from: package-private */
    public void closeLocalSide(int i, boolean z) {
        StreamState streamState = this.activeStreams.get(Integer.valueOf(i));
        if (streamState != null) {
            streamState.closeLocalSide();
            if (streamState.isRemoteSideClosed()) {
                removeActiveStream(i, z);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean hasReceivedReply(int i) {
        StreamState streamState = this.activeStreams.get(Integer.valueOf(i));
        return streamState != null && streamState.hasReceivedReply();
    }

    /* access modifiers changed from: package-private */
    public void receivedReply(int i) {
        StreamState streamState = this.activeStreams.get(Integer.valueOf(i));
        if (streamState != null) {
            streamState.receivedReply();
        }
    }

    /* access modifiers changed from: package-private */
    public int getSendWindowSize(int i) {
        if (i == 0) {
            return this.sendWindowSize.get();
        }
        StreamState streamState = this.activeStreams.get(Integer.valueOf(i));
        if (streamState != null) {
            return streamState.getSendWindowSize();
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public int updateSendWindowSize(int i, int i2) {
        if (i == 0) {
            return this.sendWindowSize.addAndGet(i2);
        }
        StreamState streamState = this.activeStreams.get(Integer.valueOf(i));
        if (streamState != null) {
            return streamState.updateSendWindowSize(i2);
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public int updateReceiveWindowSize(int i, int i2) {
        if (i == 0) {
            return this.receiveWindowSize.addAndGet(i2);
        }
        StreamState streamState = this.activeStreams.get(Integer.valueOf(i));
        if (streamState == null) {
            return -1;
        }
        if (i2 > 0) {
            streamState.setReceiveWindowSizeLowerBound(0);
        }
        return streamState.updateReceiveWindowSize(i2);
    }

    /* access modifiers changed from: package-private */
    public int getReceiveWindowSizeLowerBound(int i) {
        StreamState streamState;
        if (i == 0 || (streamState = this.activeStreams.get(Integer.valueOf(i))) == null) {
            return 0;
        }
        return streamState.getReceiveWindowSizeLowerBound();
    }

    /* access modifiers changed from: package-private */
    public void updateAllSendWindowSizes(int i) {
        for (StreamState updateSendWindowSize : this.activeStreams.values()) {
            updateSendWindowSize.updateSendWindowSize(i);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateAllReceiveWindowSizes(int i) {
        for (StreamState next : this.activeStreams.values()) {
            next.updateReceiveWindowSize(i);
            if (i < 0) {
                next.setReceiveWindowSizeLowerBound(i);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean putPendingWrite(int i, PendingWrite pendingWrite) {
        StreamState streamState = this.activeStreams.get(Integer.valueOf(i));
        return streamState != null && streamState.putPendingWrite(pendingWrite);
    }

    /* access modifiers changed from: package-private */
    public PendingWrite getPendingWrite(int i) {
        PendingWrite pendingWrite;
        if (i == 0) {
            for (Map.Entry<Integer, StreamState> value : activeStreams().entrySet()) {
                StreamState streamState = (StreamState) value.getValue();
                if (streamState.getSendWindowSize() > 0 && (pendingWrite = streamState.getPendingWrite()) != null) {
                    return pendingWrite;
                }
            }
            return null;
        }
        StreamState streamState2 = this.activeStreams.get(Integer.valueOf(i));
        if (streamState2 != null) {
            return streamState2.getPendingWrite();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public PendingWrite removePendingWrite(int i) {
        StreamState streamState = this.activeStreams.get(Integer.valueOf(i));
        if (streamState != null) {
            return streamState.removePendingWrite();
        }
        return null;
    }

    private static final class StreamState {
        private boolean localSideClosed;
        private final Queue<PendingWrite> pendingWriteQueue = new ConcurrentLinkedQueue();
        private final byte priority;
        private final AtomicInteger receiveWindowSize;
        private int receiveWindowSizeLowerBound;
        private boolean receivedReply;
        private boolean remoteSideClosed;
        private final AtomicInteger sendWindowSize;

        StreamState(byte b, boolean z, boolean z2, int i, int i2) {
            this.priority = b;
            this.remoteSideClosed = z;
            this.localSideClosed = z2;
            this.sendWindowSize = new AtomicInteger(i);
            this.receiveWindowSize = new AtomicInteger(i2);
        }

        /* access modifiers changed from: package-private */
        public byte getPriority() {
            return this.priority;
        }

        /* access modifiers changed from: package-private */
        public boolean isRemoteSideClosed() {
            return this.remoteSideClosed;
        }

        /* access modifiers changed from: package-private */
        public void closeRemoteSide() {
            this.remoteSideClosed = true;
        }

        /* access modifiers changed from: package-private */
        public boolean isLocalSideClosed() {
            return this.localSideClosed;
        }

        /* access modifiers changed from: package-private */
        public void closeLocalSide() {
            this.localSideClosed = true;
        }

        /* access modifiers changed from: package-private */
        public boolean hasReceivedReply() {
            return this.receivedReply;
        }

        /* access modifiers changed from: package-private */
        public void receivedReply() {
            this.receivedReply = true;
        }

        /* access modifiers changed from: package-private */
        public int getSendWindowSize() {
            return this.sendWindowSize.get();
        }

        /* access modifiers changed from: package-private */
        public int updateSendWindowSize(int i) {
            return this.sendWindowSize.addAndGet(i);
        }

        /* access modifiers changed from: package-private */
        public int updateReceiveWindowSize(int i) {
            return this.receiveWindowSize.addAndGet(i);
        }

        /* access modifiers changed from: package-private */
        public int getReceiveWindowSizeLowerBound() {
            return this.receiveWindowSizeLowerBound;
        }

        /* access modifiers changed from: package-private */
        public void setReceiveWindowSizeLowerBound(int i) {
            this.receiveWindowSizeLowerBound = i;
        }

        /* access modifiers changed from: package-private */
        public boolean putPendingWrite(PendingWrite pendingWrite) {
            return this.pendingWriteQueue.offer(pendingWrite);
        }

        /* access modifiers changed from: package-private */
        public PendingWrite getPendingWrite() {
            return this.pendingWriteQueue.peek();
        }

        /* access modifiers changed from: package-private */
        public PendingWrite removePendingWrite() {
            return this.pendingWriteQueue.poll();
        }

        /* access modifiers changed from: package-private */
        public void clearPendingWrites(Throwable th) {
            while (true) {
                PendingWrite poll = this.pendingWriteQueue.poll();
                if (poll != null) {
                    poll.fail(th);
                } else {
                    return;
                }
            }
        }
    }

    private final class StreamComparator implements Comparator<Integer> {
        StreamComparator() {
        }

        public int compare(Integer num, Integer num2) {
            int priority = ((StreamState) SpdySession.this.activeStreams.get(num)).getPriority() - ((StreamState) SpdySession.this.activeStreams.get(num2)).getPriority();
            if (priority != 0) {
                return priority;
            }
            return num.intValue() - num2.intValue();
        }
    }

    public static final class PendingWrite {
        final ChannelPromise promise;
        final SpdyDataFrame spdyDataFrame;

        PendingWrite(SpdyDataFrame spdyDataFrame2, ChannelPromise channelPromise) {
            this.spdyDataFrame = spdyDataFrame2;
            this.promise = channelPromise;
        }

        /* access modifiers changed from: package-private */
        public void fail(Throwable th) {
            this.spdyDataFrame.release();
            this.promise.setFailure(th);
        }
    }
}
