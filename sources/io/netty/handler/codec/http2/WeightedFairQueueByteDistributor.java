package io.netty.handler.codec.http2;

import io.netty.handler.codec.http2.Http2Connection;
import io.netty.handler.codec.http2.StreamByteDistributor;
import io.netty.util.collection.IntCollections;
import io.netty.util.collection.IntObjectHashMap;
import io.netty.util.collection.IntObjectMap;
import io.netty.util.internal.DefaultPriorityQueue;
import io.netty.util.internal.EmptyPriorityQueue;
import io.netty.util.internal.MathUtil;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PriorityQueue;
import io.netty.util.internal.PriorityQueueNode;
import io.netty.util.internal.SystemPropertyUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public final class WeightedFairQueueByteDistributor implements StreamByteDistributor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int DEFAULT_MAX_STATE_ONLY_SIZE = 5;
    static final int INITIAL_CHILDREN_MAP_SIZE = Math.max(1, SystemPropertyUtil.getInt("io.netty.http2.childrenMapSize", 2));
    private int allocationQuantum;
    private final Http2Connection connection;
    /* access modifiers changed from: private */
    public final State connectionState;
    /* access modifiers changed from: private */
    public final int maxStateOnlySize;
    /* access modifiers changed from: private */
    public final Http2Connection.PropertyKey stateKey;
    /* access modifiers changed from: private */
    public final IntObjectMap<State> stateOnlyMap;
    /* access modifiers changed from: private */
    public final PriorityQueue<State> stateOnlyRemovalQueue;

    public WeightedFairQueueByteDistributor(Http2Connection http2Connection) {
        this(http2Connection, 5);
    }

    public WeightedFairQueueByteDistributor(Http2Connection http2Connection, int i) {
        this.allocationQuantum = 1024;
        ObjectUtil.checkPositiveOrZero(i, "maxStateOnlySize");
        if (i == 0) {
            this.stateOnlyMap = IntCollections.emptyMap();
            this.stateOnlyRemovalQueue = EmptyPriorityQueue.instance();
        } else {
            this.stateOnlyMap = new IntObjectHashMap(i);
            this.stateOnlyRemovalQueue = new DefaultPriorityQueue(StateOnlyComparator.INSTANCE, i + 2);
        }
        this.maxStateOnlySize = i;
        this.connection = http2Connection;
        Http2Connection.PropertyKey newKey = http2Connection.newKey();
        this.stateKey = newKey;
        Http2Stream connectionStream = http2Connection.connectionStream();
        State state = new State(this, connectionStream, 16);
        this.connectionState = state;
        connectionStream.setProperty(newKey, state);
        http2Connection.addListener(new Http2ConnectionAdapter() {
            public void onStreamAdded(Http2Stream http2Stream) {
                State state = (State) WeightedFairQueueByteDistributor.this.stateOnlyMap.remove(http2Stream.id());
                if (state == null) {
                    state = new State(WeightedFairQueueByteDistributor.this, http2Stream);
                    ArrayList arrayList = new ArrayList(1);
                    WeightedFairQueueByteDistributor.this.connectionState.takeChild(state, false, arrayList);
                    WeightedFairQueueByteDistributor.this.notifyParentChanged(arrayList);
                } else {
                    WeightedFairQueueByteDistributor.this.stateOnlyRemovalQueue.removeTyped(state);
                    state.stream = http2Stream;
                }
                int i = AnonymousClass2.$SwitchMap$io$netty$handler$codec$http2$Http2Stream$State[http2Stream.state().ordinal()];
                if (i == 1 || i == 2) {
                    state.setStreamReservedOrActivated();
                }
                http2Stream.setProperty(WeightedFairQueueByteDistributor.this.stateKey, state);
            }

            public void onStreamActive(Http2Stream http2Stream) {
                WeightedFairQueueByteDistributor.this.state(http2Stream).setStreamReservedOrActivated();
            }

            public void onStreamClosed(Http2Stream http2Stream) {
                WeightedFairQueueByteDistributor.this.state(http2Stream).close();
            }

            public void onStreamRemoved(Http2Stream http2Stream) {
                State access$400 = WeightedFairQueueByteDistributor.this.state(http2Stream);
                access$400.stream = null;
                if (WeightedFairQueueByteDistributor.this.maxStateOnlySize == 0) {
                    access$400.parent.removeChild(access$400);
                    return;
                }
                if (WeightedFairQueueByteDistributor.this.stateOnlyRemovalQueue.size() == WeightedFairQueueByteDistributor.this.maxStateOnlySize) {
                    State state = (State) WeightedFairQueueByteDistributor.this.stateOnlyRemovalQueue.peek();
                    if (StateOnlyComparator.INSTANCE.compare(state, access$400) >= 0) {
                        access$400.parent.removeChild(access$400);
                        return;
                    }
                    WeightedFairQueueByteDistributor.this.stateOnlyRemovalQueue.poll();
                    state.parent.removeChild(state);
                    WeightedFairQueueByteDistributor.this.stateOnlyMap.remove(state.streamId);
                }
                WeightedFairQueueByteDistributor.this.stateOnlyRemovalQueue.add(access$400);
                WeightedFairQueueByteDistributor.this.stateOnlyMap.put(access$400.streamId, access$400);
            }
        });
    }

    /* renamed from: io.netty.handler.codec.http2.WeightedFairQueueByteDistributor$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                io.netty.handler.codec.http2.Http2Stream$State[] r0 = io.netty.handler.codec.http2.Http2Stream.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State = r0
                io.netty.handler.codec.http2.Http2Stream$State r1 = io.netty.handler.codec.http2.Http2Stream.State.RESERVED_REMOTE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State     // Catch:{ NoSuchFieldError -> 0x001d }
                io.netty.handler.codec.http2.Http2Stream$State r1 = io.netty.handler.codec.http2.Http2Stream.State.RESERVED_LOCAL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http2.WeightedFairQueueByteDistributor.AnonymousClass2.<clinit>():void");
        }
    }

    public void updateStreamableBytes(StreamByteDistributor.StreamState streamState) {
        state(streamState.stream()).updateStreamableBytes(Http2CodecUtil.streamableBytes(streamState), streamState.hasFrame() && streamState.windowSize() >= 0);
    }

    public void updateDependencyTree(int i, int i2, short s, boolean z) {
        ArrayList arrayList;
        State state = state(i);
        if (state == null) {
            if (this.maxStateOnlySize != 0) {
                state = new State(this, i);
                this.stateOnlyRemovalQueue.add(state);
                this.stateOnlyMap.put(i, state);
            } else {
                return;
            }
        }
        State state2 = state(i2);
        int i3 = 0;
        if (state2 == null) {
            if (this.maxStateOnlySize != 0) {
                state2 = new State(this, i2);
                this.stateOnlyRemovalQueue.add(state2);
                this.stateOnlyMap.put(i2, state2);
                ArrayList arrayList2 = new ArrayList(1);
                this.connectionState.takeChild(state2, false, arrayList2);
                notifyParentChanged(arrayList2);
            } else {
                return;
            }
        }
        if (!(state.activeCountForTree == 0 || state.parent == null)) {
            state.parent.totalQueuedWeights += (long) (s - state.weight);
        }
        state.weight = s;
        if (state2 != state.parent || (z && state2.children.size() != 1)) {
            if (state2.isDescendantOf(state)) {
                arrayList = new ArrayList((z ? state2.children.size() : 0) + 2);
                state.parent.takeChild(state2, false, arrayList);
            } else {
                if (z) {
                    i3 = state2.children.size();
                }
                arrayList = new ArrayList(i3 + 1);
            }
            state2.takeChild(state, z, arrayList);
            notifyParentChanged(arrayList);
        }
        while (this.stateOnlyRemovalQueue.size() > this.maxStateOnlySize) {
            State state3 = (State) this.stateOnlyRemovalQueue.poll();
            state3.parent.removeChild(state3);
            this.stateOnlyMap.remove(state3.streamId);
        }
    }

    public boolean distribute(int i, StreamByteDistributor.Writer writer) throws Http2Exception {
        if (this.connectionState.activeCountForTree == 0) {
            return false;
        }
        while (true) {
            int i2 = this.connectionState.activeCountForTree;
            i -= distributeToChildren(i, writer, this.connectionState);
            if (this.connectionState.activeCountForTree == 0 || (i <= 0 && i2 == this.connectionState.activeCountForTree)) {
            }
        }
        if (this.connectionState.activeCountForTree != 0) {
            return true;
        }
        return false;
    }

    public void allocationQuantum(int i) {
        ObjectUtil.checkPositive(i, "allocationQuantum");
        this.allocationQuantum = i;
    }

    private int distribute(int i, StreamByteDistributor.Writer writer, State state) throws Http2Exception {
        if (!state.isActive()) {
            return distributeToChildren(i, writer, state);
        }
        int min = Math.min(i, state.streamableBytes);
        state.write(min, writer);
        if (min == 0 && i != 0) {
            state.updateStreamableBytes(state.streamableBytes, false);
        }
        return min;
    }

    private int distributeToChildren(int i, StreamByteDistributor.Writer writer, State state) throws Http2Exception {
        long j = state.totalQueuedWeights;
        State pollPseudoTimeQueue = state.pollPseudoTimeQueue();
        State peekPseudoTimeQueue = state.peekPseudoTimeQueue();
        pollPseudoTimeQueue.setDistributing();
        if (peekPseudoTimeQueue != null) {
            try {
                i = Math.min(i, (int) Math.min((((peekPseudoTimeQueue.pseudoTimeToWrite - pollPseudoTimeQueue.pseudoTimeToWrite) * ((long) pollPseudoTimeQueue.weight)) / j) + ((long) this.allocationQuantum), 2147483647L));
            } catch (Throwable th) {
                pollPseudoTimeQueue.unsetDistributing();
                if (pollPseudoTimeQueue.activeCountForTree != 0) {
                    state.offerPseudoTimeQueue(pollPseudoTimeQueue);
                }
                throw th;
            }
        }
        int distribute = distribute(i, writer, pollPseudoTimeQueue);
        state.pseudoTime += (long) distribute;
        pollPseudoTimeQueue.updatePseudoTime(state, distribute, j);
        pollPseudoTimeQueue.unsetDistributing();
        if (pollPseudoTimeQueue.activeCountForTree != 0) {
            state.offerPseudoTimeQueue(pollPseudoTimeQueue);
        }
        return distribute;
    }

    /* access modifiers changed from: private */
    public State state(Http2Stream http2Stream) {
        return (State) http2Stream.getProperty(this.stateKey);
    }

    private State state(int i) {
        Http2Stream stream = this.connection.stream(i);
        return stream != null ? state(stream) : this.stateOnlyMap.get(i);
    }

    /* access modifiers changed from: package-private */
    public boolean isChild(int i, int i2, short s) {
        State state = state(i2);
        if (state.children.containsKey(i)) {
            State state2 = state(i);
            return state2.parent == state && state2.weight == s;
        }
    }

    /* access modifiers changed from: package-private */
    public int numChildren(int i) {
        State state = state(i);
        if (state == null) {
            return 0;
        }
        return state.children.size();
    }

    /* access modifiers changed from: package-private */
    public void notifyParentChanged(List<ParentChangedEvent> list) {
        for (int i = 0; i < list.size(); i++) {
            ParentChangedEvent parentChangedEvent = list.get(i);
            this.stateOnlyRemovalQueue.priorityChanged(parentChangedEvent.state);
            if (!(parentChangedEvent.state.parent == null || parentChangedEvent.state.activeCountForTree == 0)) {
                parentChangedEvent.state.parent.offerAndInitializePseudoTime(parentChangedEvent.state);
                parentChangedEvent.state.parent.activeCountChangeForTree(parentChangedEvent.state.activeCountForTree);
            }
        }
    }

    private static final class StateOnlyComparator implements Comparator<State>, Serializable {
        static final StateOnlyComparator INSTANCE = new StateOnlyComparator();
        private static final long serialVersionUID = -4806936913002105966L;

        private StateOnlyComparator() {
        }

        public int compare(State state, State state2) {
            boolean wasStreamReservedOrActivated = state.wasStreamReservedOrActivated();
            if (wasStreamReservedOrActivated != state2.wasStreamReservedOrActivated()) {
                return wasStreamReservedOrActivated ? -1 : 1;
            }
            int i = state2.dependencyTreeDepth - state.dependencyTreeDepth;
            return i != 0 ? i : state.streamId - state2.streamId;
        }
    }

    private static final class StatePseudoTimeComparator implements Comparator<State>, Serializable {
        static final StatePseudoTimeComparator INSTANCE = new StatePseudoTimeComparator();
        private static final long serialVersionUID = -1437548640227161828L;

        private StatePseudoTimeComparator() {
        }

        public int compare(State state, State state2) {
            return MathUtil.compare(state.pseudoTimeToWrite, state2.pseudoTimeToWrite);
        }
    }

    private final class State implements PriorityQueueNode {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final byte STATE_IS_ACTIVE = 1;
        private static final byte STATE_IS_DISTRIBUTING = 2;
        private static final byte STATE_STREAM_ACTIVATED = 4;
        int activeCountForTree;
        IntObjectMap<State> children;
        int dependencyTreeDepth;
        private byte flags;
        State parent;
        long pseudoTime;
        private final PriorityQueue<State> pseudoTimeQueue;
        private int pseudoTimeQueueIndex;
        long pseudoTimeToWrite;
        private int stateOnlyQueueIndex;
        Http2Stream stream;
        final int streamId;
        int streamableBytes;
        long totalQueuedWeights;
        short weight;

        static {
            Class<WeightedFairQueueByteDistributor> cls = WeightedFairQueueByteDistributor.class;
        }

        State(WeightedFairQueueByteDistributor weightedFairQueueByteDistributor, int i) {
            this(i, (Http2Stream) null, 0);
        }

        State(WeightedFairQueueByteDistributor weightedFairQueueByteDistributor, Http2Stream http2Stream) {
            this(weightedFairQueueByteDistributor, http2Stream, 0);
        }

        State(WeightedFairQueueByteDistributor weightedFairQueueByteDistributor, Http2Stream http2Stream, int i) {
            this(http2Stream.id(), http2Stream, i);
        }

        State(int i, Http2Stream http2Stream, int i2) {
            this.children = IntCollections.emptyMap();
            this.pseudoTimeQueueIndex = -1;
            this.stateOnlyQueueIndex = -1;
            this.weight = 16;
            this.stream = http2Stream;
            this.streamId = i;
            this.pseudoTimeQueue = new DefaultPriorityQueue(StatePseudoTimeComparator.INSTANCE, i2);
        }

        /* access modifiers changed from: package-private */
        public boolean isDescendantOf(State state) {
            for (State state2 = this.parent; state2 != null; state2 = state2.parent) {
                if (state2 == state) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public void takeChild(State state, boolean z, List<ParentChangedEvent> list) {
            takeChild((Iterator<IntObjectMap.PrimitiveEntry<State>>) null, state, z, list);
        }

        /* access modifiers changed from: package-private */
        public void takeChild(Iterator<IntObjectMap.PrimitiveEntry<State>> it, State state, boolean z, List<ParentChangedEvent> list) {
            State state2 = state.parent;
            if (state2 != this) {
                list.add(new ParentChangedEvent(state, state2));
                state.setParent(this);
                if (it != null) {
                    it.remove();
                } else if (state2 != null) {
                    state2.children.remove(state.streamId);
                }
                initChildrenIfEmpty();
                State put = this.children.put(state.streamId, state);
            }
            if (z && !this.children.isEmpty()) {
                Iterator<IntObjectMap.PrimitiveEntry<State>> it2 = removeAllChildrenExcept(state).entries().iterator();
                while (it2.hasNext()) {
                    state.takeChild(it2, (State) it2.next().value(), false, list);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void removeChild(State state) {
            if (this.children.remove(state.streamId) != null) {
                ArrayList arrayList = new ArrayList(state.children.size() + 1);
                arrayList.add(new ParentChangedEvent(state, state.parent));
                state.setParent((State) null);
                if (!state.children.isEmpty()) {
                    Iterator<IntObjectMap.PrimitiveEntry<State>> it = state.children.entries().iterator();
                    long totalWeight = state.getTotalWeight();
                    do {
                        State state2 = (State) it.next().value();
                        state2.weight = (short) ((int) Math.max(1, ((long) (state2.weight * state.weight)) / totalWeight));
                        takeChild(it, state2, false, arrayList);
                    } while (it.hasNext());
                }
                WeightedFairQueueByteDistributor.this.notifyParentChanged(arrayList);
            }
        }

        private long getTotalWeight() {
            long j = 0;
            for (State state : this.children.values()) {
                j += (long) state.weight;
            }
            return j;
        }

        private IntObjectMap<State> removeAllChildrenExcept(State state) {
            State remove = this.children.remove(state.streamId);
            IntObjectMap<State> intObjectMap = this.children;
            initChildren();
            if (remove != null) {
                this.children.put(remove.streamId, remove);
            }
            return intObjectMap;
        }

        private void setParent(State state) {
            int i;
            State state2;
            if (!(this.activeCountForTree == 0 || (state2 = this.parent) == null)) {
                state2.removePseudoTimeQueue(this);
                this.parent.activeCountChangeForTree(-this.activeCountForTree);
            }
            this.parent = state;
            if (state == null) {
                i = Integer.MAX_VALUE;
            } else {
                i = state.dependencyTreeDepth + 1;
            }
            this.dependencyTreeDepth = i;
        }

        private void initChildrenIfEmpty() {
            if (this.children == IntCollections.emptyMap()) {
                initChildren();
            }
        }

        private void initChildren() {
            this.children = new IntObjectHashMap(WeightedFairQueueByteDistributor.INITIAL_CHILDREN_MAP_SIZE);
        }

        /* access modifiers changed from: package-private */
        public void write(int i, StreamByteDistributor.Writer writer) throws Http2Exception {
            try {
                writer.write(this.stream, i);
            } catch (Throwable th) {
                throw Http2Exception.connectionError(Http2Error.INTERNAL_ERROR, th, "byte distribution write error", new Object[0]);
            }
        }

        /* access modifiers changed from: package-private */
        public void activeCountChangeForTree(int i) {
            int i2 = this.activeCountForTree + i;
            this.activeCountForTree = i2;
            State state = this.parent;
            if (state != null) {
                if (i2 == 0) {
                    state.removePseudoTimeQueue(this);
                } else if (i2 == i && !isDistributing()) {
                    this.parent.offerAndInitializePseudoTime(this);
                }
                this.parent.activeCountChangeForTree(i);
            }
        }

        /* access modifiers changed from: package-private */
        public void updateStreamableBytes(int i, boolean z) {
            if (isActive() != z) {
                if (z) {
                    activeCountChangeForTree(1);
                    setActive();
                } else {
                    activeCountChangeForTree(-1);
                    unsetActive();
                }
            }
            this.streamableBytes = i;
        }

        /* access modifiers changed from: package-private */
        public void updatePseudoTime(State state, int i, long j) {
            this.pseudoTimeToWrite = Math.min(this.pseudoTimeToWrite, state.pseudoTime) + ((((long) i) * j) / ((long) this.weight));
        }

        /* access modifiers changed from: package-private */
        public void offerAndInitializePseudoTime(State state) {
            state.pseudoTimeToWrite = this.pseudoTime;
            offerPseudoTimeQueue(state);
        }

        /* access modifiers changed from: package-private */
        public void offerPseudoTimeQueue(State state) {
            this.pseudoTimeQueue.offer(state);
            this.totalQueuedWeights += (long) state.weight;
        }

        /* access modifiers changed from: package-private */
        public State pollPseudoTimeQueue() {
            State state = (State) this.pseudoTimeQueue.poll();
            this.totalQueuedWeights -= (long) state.weight;
            return state;
        }

        /* access modifiers changed from: package-private */
        public void removePseudoTimeQueue(State state) {
            if (this.pseudoTimeQueue.removeTyped(state)) {
                this.totalQueuedWeights -= (long) state.weight;
            }
        }

        /* access modifiers changed from: package-private */
        public State peekPseudoTimeQueue() {
            return (State) this.pseudoTimeQueue.peek();
        }

        /* access modifiers changed from: package-private */
        public void close() {
            updateStreamableBytes(0, false);
            this.stream = null;
        }

        /* access modifiers changed from: package-private */
        public boolean wasStreamReservedOrActivated() {
            return (this.flags & 4) != 0;
        }

        /* access modifiers changed from: package-private */
        public void setStreamReservedOrActivated() {
            this.flags = (byte) (this.flags | 4);
        }

        /* access modifiers changed from: package-private */
        public boolean isActive() {
            return (this.flags & 1) != 0;
        }

        private void setActive() {
            this.flags = (byte) (this.flags | 1);
        }

        private void unsetActive() {
            this.flags = (byte) (this.flags & -2);
        }

        /* access modifiers changed from: package-private */
        public boolean isDistributing() {
            return (this.flags & 2) != 0;
        }

        /* access modifiers changed from: package-private */
        public void setDistributing() {
            this.flags = (byte) (this.flags | 2);
        }

        /* access modifiers changed from: package-private */
        public void unsetDistributing() {
            this.flags = (byte) (this.flags & -3);
        }

        public int priorityQueueIndex(DefaultPriorityQueue<?> defaultPriorityQueue) {
            return defaultPriorityQueue == WeightedFairQueueByteDistributor.this.stateOnlyRemovalQueue ? this.stateOnlyQueueIndex : this.pseudoTimeQueueIndex;
        }

        public void priorityQueueIndex(DefaultPriorityQueue<?> defaultPriorityQueue, int i) {
            if (defaultPriorityQueue == WeightedFairQueueByteDistributor.this.stateOnlyRemovalQueue) {
                this.stateOnlyQueueIndex = i;
            } else {
                this.pseudoTimeQueueIndex = i;
            }
        }

        public String toString() {
            int i = this.activeCountForTree;
            if (i <= 0) {
                i = 1;
            }
            StringBuilder sb = new StringBuilder(i * 256);
            toString(sb);
            return sb.toString();
        }

        private void toString(StringBuilder sb) {
            sb.append("{streamId ");
            sb.append(this.streamId);
            sb.append(" streamableBytes ");
            sb.append(this.streamableBytes);
            sb.append(" activeCountForTree ");
            sb.append(this.activeCountForTree);
            sb.append(" pseudoTimeQueueIndex ");
            sb.append(this.pseudoTimeQueueIndex);
            sb.append(" pseudoTimeToWrite ");
            sb.append(this.pseudoTimeToWrite);
            sb.append(" pseudoTime ");
            sb.append(this.pseudoTime);
            sb.append(" flags ");
            sb.append(this.flags);
            sb.append(" pseudoTimeQueue.size() ");
            sb.append(this.pseudoTimeQueue.size());
            sb.append(" stateOnlyQueueIndex ");
            sb.append(this.stateOnlyQueueIndex);
            sb.append(" parent.streamId ");
            State state = this.parent;
            sb.append(state == null ? -1 : state.streamId);
            sb.append("} [");
            if (!this.pseudoTimeQueue.isEmpty()) {
                for (State state2 : this.pseudoTimeQueue) {
                    state2.toString(sb);
                    sb.append(", ");
                }
                sb.setLength(sb.length() - 2);
            }
            sb.append(AbstractJsonLexerKt.END_LIST);
        }
    }

    private static final class ParentChangedEvent {
        final State oldParent;
        final State state;

        ParentChangedEvent(State state2, State state3) {
            this.state = state2;
            this.oldParent = state3;
        }
    }
}
