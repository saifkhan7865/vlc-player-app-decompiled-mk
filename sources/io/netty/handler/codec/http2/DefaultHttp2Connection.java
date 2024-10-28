package io.netty.handler.codec.http2;

import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http2.Http2Connection;
import io.netty.handler.codec.http2.Http2Exception;
import io.netty.handler.codec.http2.Http2Stream;
import io.netty.util.collection.IntObjectHashMap;
import io.netty.util.collection.IntObjectMap;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.Promise;
import io.netty.util.concurrent.PromiseNotifier;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class DefaultHttp2Connection implements Http2Connection {
    /* access modifiers changed from: private */
    public static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) DefaultHttp2Connection.class);
    final ActiveStreams activeStreams;
    Promise<Void> closePromise;
    final ConnectionStream connectionStream;
    final List<Http2Connection.Listener> listeners;
    final DefaultEndpoint<Http2LocalFlowController> localEndpoint;
    final PropertyKeyRegistry propertyKeyRegistry;
    final DefaultEndpoint<Http2RemoteFlowController> remoteEndpoint;
    final IntObjectMap<Http2Stream> streamMap;

    interface Event {
        void process();
    }

    public DefaultHttp2Connection(boolean z) {
        this(z, 100);
    }

    public DefaultHttp2Connection(boolean z, int i) {
        IntObjectHashMap intObjectHashMap = new IntObjectHashMap();
        this.streamMap = intObjectHashMap;
        this.propertyKeyRegistry = new PropertyKeyRegistry();
        ConnectionStream connectionStream2 = new ConnectionStream();
        this.connectionStream = connectionStream2;
        ArrayList arrayList = new ArrayList(4);
        this.listeners = arrayList;
        this.activeStreams = new ActiveStreams(arrayList);
        this.localEndpoint = new DefaultEndpoint<>(z, z ? Integer.MAX_VALUE : i);
        this.remoteEndpoint = new DefaultEndpoint<>(!z, i);
        intObjectHashMap.put(connectionStream2.id(), connectionStream2);
    }

    /* access modifiers changed from: package-private */
    public final boolean isClosed() {
        return this.closePromise != null;
    }

    public Future<Void> close(Promise<Void> promise) {
        ObjectUtil.checkNotNull(promise, "promise");
        Promise<Void> promise2 = this.closePromise;
        if (promise2 == null) {
            this.closePromise = promise;
        } else if (promise2 != promise) {
            if (!(promise instanceof ChannelPromise) || !((ChannelFuture) promise2).isVoid()) {
                PromiseNotifier.cascade(this.closePromise, promise);
            } else {
                this.closePromise = promise;
            }
        }
        if (isStreamMapEmpty()) {
            promise.trySuccess(null);
            return promise;
        }
        Iterator<IntObjectMap.PrimitiveEntry<Http2Stream>> it = this.streamMap.entries().iterator();
        if (this.activeStreams.allowModifications()) {
            this.activeStreams.incrementPendingIterations();
            while (it.hasNext()) {
                try {
                    DefaultStream defaultStream = (DefaultStream) it.next().value();
                    if (defaultStream.id() != 0) {
                        defaultStream.close(it);
                    }
                } finally {
                    this.activeStreams.decrementPendingIterations();
                }
            }
        } else {
            while (it.hasNext()) {
                Http2Stream http2Stream = (Http2Stream) it.next().value();
                if (http2Stream.id() != 0) {
                    http2Stream.close();
                }
            }
        }
        return this.closePromise;
    }

    public void addListener(Http2Connection.Listener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(Http2Connection.Listener listener) {
        this.listeners.remove(listener);
    }

    public boolean isServer() {
        return this.localEndpoint.isServer();
    }

    public Http2Stream connectionStream() {
        return this.connectionStream;
    }

    public Http2Stream stream(int i) {
        return this.streamMap.get(i);
    }

    public boolean streamMayHaveExisted(int i) {
        return this.remoteEndpoint.mayHaveCreatedStream(i) || this.localEndpoint.mayHaveCreatedStream(i);
    }

    public int numActiveStreams() {
        return this.activeStreams.size();
    }

    public Http2Stream forEachActiveStream(Http2StreamVisitor http2StreamVisitor) throws Http2Exception {
        return this.activeStreams.forEachActiveStream(http2StreamVisitor);
    }

    public Http2Connection.Endpoint<Http2LocalFlowController> local() {
        return this.localEndpoint;
    }

    public Http2Connection.Endpoint<Http2RemoteFlowController> remote() {
        return this.remoteEndpoint;
    }

    public boolean goAwayReceived() {
        return this.localEndpoint.lastStreamKnownByPeer >= 0;
    }

    public void goAwayReceived(int i, long j, ByteBuf byteBuf) throws Http2Exception {
        if (this.localEndpoint.lastStreamKnownByPeer() < 0 || this.localEndpoint.lastStreamKnownByPeer() >= i) {
            this.localEndpoint.lastStreamKnownByPeer(i);
            for (int i2 = 0; i2 < this.listeners.size(); i2++) {
                try {
                    this.listeners.get(i2).onGoAwayReceived(i, j, byteBuf);
                } catch (Throwable th) {
                    logger.error("Caught Throwable from listener onGoAwayReceived.", th);
                }
            }
            closeStreamsGreaterThanLastKnownStreamId(i, this.localEndpoint);
            return;
        }
        throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "lastStreamId MUST NOT increase. Current value: %d new value: %d", Integer.valueOf(this.localEndpoint.lastStreamKnownByPeer()), Integer.valueOf(i));
    }

    public boolean goAwaySent() {
        return this.remoteEndpoint.lastStreamKnownByPeer >= 0;
    }

    public boolean goAwaySent(int i, long j, ByteBuf byteBuf) throws Http2Exception {
        if (this.remoteEndpoint.lastStreamKnownByPeer() >= 0) {
            if (i == this.remoteEndpoint.lastStreamKnownByPeer()) {
                return false;
            }
            if (i > this.remoteEndpoint.lastStreamKnownByPeer()) {
                throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Last stream identifier must not increase between sending multiple GOAWAY frames (was '%d', is '%d').", Integer.valueOf(this.remoteEndpoint.lastStreamKnownByPeer()), Integer.valueOf(i));
            }
        }
        this.remoteEndpoint.lastStreamKnownByPeer(i);
        for (int i2 = 0; i2 < this.listeners.size(); i2++) {
            try {
                this.listeners.get(i2).onGoAwaySent(i, j, byteBuf);
            } catch (Throwable th) {
                logger.error("Caught Throwable from listener onGoAwaySent.", th);
            }
        }
        closeStreamsGreaterThanLastKnownStreamId(i, this.remoteEndpoint);
        return true;
    }

    private void closeStreamsGreaterThanLastKnownStreamId(final int i, final DefaultEndpoint<?> defaultEndpoint) throws Http2Exception {
        forEachActiveStream(new Http2StreamVisitor() {
            public boolean visit(Http2Stream http2Stream) {
                if (http2Stream.id() <= i || !defaultEndpoint.isValidStreamId(http2Stream.id())) {
                    return true;
                }
                http2Stream.close();
                return true;
            }
        });
    }

    private boolean isStreamMapEmpty() {
        return this.streamMap.size() == 1;
    }

    /* access modifiers changed from: package-private */
    public void removeStream(DefaultStream defaultStream, Iterator<?> it) {
        if (it != null) {
            it.remove();
        } else if (this.streamMap.remove(defaultStream.id()) == null) {
            return;
        }
        for (int i = 0; i < this.listeners.size(); i++) {
            try {
                this.listeners.get(i).onStreamRemoved(defaultStream);
            } catch (Throwable th) {
                logger.error("Caught Throwable from listener onStreamRemoved.", th);
            }
        }
        if (this.closePromise != null && isStreamMapEmpty()) {
            this.closePromise.trySuccess(null);
        }
    }

    /* renamed from: io.netty.handler.codec.http2.DefaultHttp2Connection$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                io.netty.handler.codec.http2.Http2Stream$State[] r0 = io.netty.handler.codec.http2.Http2Stream.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State = r0
                io.netty.handler.codec.http2.Http2Stream$State r1 = io.netty.handler.codec.http2.Http2Stream.State.IDLE     // Catch:{ NoSuchFieldError -> 0x0012 }
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
                int[] r0 = $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State     // Catch:{ NoSuchFieldError -> 0x0028 }
                io.netty.handler.codec.http2.Http2Stream$State r1 = io.netty.handler.codec.http2.Http2Stream.State.RESERVED_REMOTE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State     // Catch:{ NoSuchFieldError -> 0x0033 }
                io.netty.handler.codec.http2.Http2Stream$State r1 = io.netty.handler.codec.http2.Http2Stream.State.OPEN     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State     // Catch:{ NoSuchFieldError -> 0x003e }
                io.netty.handler.codec.http2.Http2Stream$State r1 = io.netty.handler.codec.http2.Http2Stream.State.HALF_CLOSED_LOCAL     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State     // Catch:{ NoSuchFieldError -> 0x0049 }
                io.netty.handler.codec.http2.Http2Stream$State r1 = io.netty.handler.codec.http2.Http2Stream.State.HALF_CLOSED_REMOTE     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http2.DefaultHttp2Connection.AnonymousClass2.<clinit>():void");
        }
    }

    static Http2Stream.State activeState(int i, Http2Stream.State state, boolean z, boolean z2) throws Http2Exception {
        int i2 = AnonymousClass2.$SwitchMap$io$netty$handler$codec$http2$Http2Stream$State[state.ordinal()];
        if (i2 != 1) {
            if (i2 == 2) {
                return Http2Stream.State.HALF_CLOSED_REMOTE;
            }
            if (i2 == 3) {
                return Http2Stream.State.HALF_CLOSED_LOCAL;
            }
            Http2Error http2Error = Http2Error.PROTOCOL_ERROR;
            throw Http2Exception.streamError(i, http2Error, "Attempting to open a stream in an invalid state: " + state, new Object[0]);
        } else if (z2) {
            return z ? Http2Stream.State.HALF_CLOSED_LOCAL : Http2Stream.State.HALF_CLOSED_REMOTE;
        } else {
            return Http2Stream.State.OPEN;
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyHalfClosed(Http2Stream http2Stream) {
        for (int i = 0; i < this.listeners.size(); i++) {
            try {
                this.listeners.get(i).onStreamHalfClosed(http2Stream);
            } catch (Throwable th) {
                logger.error("Caught Throwable from listener onStreamHalfClosed.", th);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyClosed(Http2Stream http2Stream) {
        for (int i = 0; i < this.listeners.size(); i++) {
            try {
                this.listeners.get(i).onStreamClosed(http2Stream);
            } catch (Throwable th) {
                logger.error("Caught Throwable from listener onStreamClosed.", th);
            }
        }
    }

    public Http2Connection.PropertyKey newKey() {
        return this.propertyKeyRegistry.newKey();
    }

    /* access modifiers changed from: package-private */
    public final DefaultPropertyKey verifyKey(Http2Connection.PropertyKey propertyKey) {
        return ((DefaultPropertyKey) ObjectUtil.checkNotNull((DefaultPropertyKey) propertyKey, LeanbackPreferenceDialogFragment.ARG_KEY)).verifyConnection(this);
    }

    private class DefaultStream implements Http2Stream {
        private static final byte META_STATE_RECV_HEADERS = 16;
        private static final byte META_STATE_RECV_TRAILERS = 32;
        private static final byte META_STATE_SENT_HEADERS = 2;
        private static final byte META_STATE_SENT_PUSHPROMISE = 8;
        private static final byte META_STATE_SENT_RST = 1;
        private static final byte META_STATE_SENT_TRAILERS = 4;
        private final int id;
        private byte metaState;
        private final PropertyMap properties = new PropertyMap();
        private Http2Stream.State state;

        DefaultStream(int i, Http2Stream.State state2) {
            this.id = i;
            this.state = state2;
        }

        public final int id() {
            return this.id;
        }

        public final Http2Stream.State state() {
            return this.state;
        }

        public boolean isResetSent() {
            return (this.metaState & 1) != 0;
        }

        public Http2Stream resetSent() {
            this.metaState = (byte) (this.metaState | 1);
            return this;
        }

        public Http2Stream headersSent(boolean z) {
            if (!z) {
                this.metaState = (byte) (this.metaState | (isHeadersSent() ? (byte) 4 : 2));
            }
            return this;
        }

        public boolean isHeadersSent() {
            return (this.metaState & 2) != 0;
        }

        public boolean isTrailersSent() {
            return (this.metaState & 4) != 0;
        }

        public Http2Stream headersReceived(boolean z) {
            if (!z) {
                this.metaState = (byte) (this.metaState | (isHeadersReceived() ? (byte) 32 : 16));
            }
            return this;
        }

        public boolean isHeadersReceived() {
            return (this.metaState & 16) != 0;
        }

        public boolean isTrailersReceived() {
            return (this.metaState & 32) != 0;
        }

        public Http2Stream pushPromiseSent() {
            this.metaState = (byte) (this.metaState | 8);
            return this;
        }

        public boolean isPushPromiseSent() {
            return (this.metaState & 8) != 0;
        }

        public final <V> V setProperty(Http2Connection.PropertyKey propertyKey, V v) {
            return this.properties.add(DefaultHttp2Connection.this.verifyKey(propertyKey), v);
        }

        public final <V> V getProperty(Http2Connection.PropertyKey propertyKey) {
            return this.properties.get(DefaultHttp2Connection.this.verifyKey(propertyKey));
        }

        public final <V> V removeProperty(Http2Connection.PropertyKey propertyKey) {
            return this.properties.remove(DefaultHttp2Connection.this.verifyKey(propertyKey));
        }

        public Http2Stream open(boolean z) throws Http2Exception {
            this.state = DefaultHttp2Connection.activeState(this.id, this.state, isLocal(), z);
            DefaultEndpoint<? extends Http2FlowController> createdBy = createdBy();
            if (createdBy.canOpenStream()) {
                activate();
                return this;
            }
            Http2Error http2Error = Http2Error.PROTOCOL_ERROR;
            throw Http2Exception.connectionError(http2Error, "Maximum active streams violated for this endpoint: " + createdBy.maxActiveStreams(), new Object[0]);
        }

        /* access modifiers changed from: package-private */
        public void activate() {
            if (this.state == Http2Stream.State.HALF_CLOSED_LOCAL) {
                headersSent(false);
            } else if (this.state == Http2Stream.State.HALF_CLOSED_REMOTE) {
                headersReceived(false);
            }
            DefaultHttp2Connection.this.activeStreams.activate(this);
        }

        /* access modifiers changed from: package-private */
        public Http2Stream close(Iterator<?> it) {
            if (this.state == Http2Stream.State.CLOSED) {
                return this;
            }
            this.state = Http2Stream.State.CLOSED;
            DefaultEndpoint<? extends Http2FlowController> createdBy = createdBy();
            createdBy.numStreams--;
            DefaultHttp2Connection.this.activeStreams.deactivate(this, it);
            return this;
        }

        public Http2Stream close() {
            return close((Iterator<?>) null);
        }

        public Http2Stream closeLocalSide() {
            int i = AnonymousClass2.$SwitchMap$io$netty$handler$codec$http2$Http2Stream$State[this.state.ordinal()];
            if (i == 4) {
                this.state = Http2Stream.State.HALF_CLOSED_LOCAL;
                DefaultHttp2Connection.this.notifyHalfClosed(this);
            } else if (i != 5) {
                close();
            }
            return this;
        }

        public Http2Stream closeRemoteSide() {
            int i = AnonymousClass2.$SwitchMap$io$netty$handler$codec$http2$Http2Stream$State[this.state.ordinal()];
            if (i == 4) {
                this.state = Http2Stream.State.HALF_CLOSED_REMOTE;
                DefaultHttp2Connection.this.notifyHalfClosed(this);
            } else if (i != 6) {
                close();
            }
            return this;
        }

        /* access modifiers changed from: package-private */
        public DefaultEndpoint<? extends Http2FlowController> createdBy() {
            return DefaultHttp2Connection.this.localEndpoint.isValidStreamId(this.id) ? DefaultHttp2Connection.this.localEndpoint : DefaultHttp2Connection.this.remoteEndpoint;
        }

        /* access modifiers changed from: package-private */
        public final boolean isLocal() {
            return DefaultHttp2Connection.this.localEndpoint.isValidStreamId(this.id);
        }

        private class PropertyMap {
            Object[] values;

            private PropertyMap() {
                this.values = EmptyArrays.EMPTY_OBJECTS;
            }

            /* access modifiers changed from: package-private */
            public <V> V add(DefaultPropertyKey defaultPropertyKey, V v) {
                resizeIfNecessary(defaultPropertyKey.index);
                V v2 = this.values[defaultPropertyKey.index];
                this.values[defaultPropertyKey.index] = v;
                return v2;
            }

            /* access modifiers changed from: package-private */
            public <V> V get(DefaultPropertyKey defaultPropertyKey) {
                int i = defaultPropertyKey.index;
                V[] vArr = this.values;
                if (i >= vArr.length) {
                    return null;
                }
                return vArr[defaultPropertyKey.index];
            }

            /* access modifiers changed from: package-private */
            public <V> V remove(DefaultPropertyKey defaultPropertyKey) {
                int i = defaultPropertyKey.index;
                V[] vArr = this.values;
                if (i >= vArr.length) {
                    return null;
                }
                V v = vArr[defaultPropertyKey.index];
                this.values[defaultPropertyKey.index] = null;
                return v;
            }

            /* access modifiers changed from: package-private */
            public void resizeIfNecessary(int i) {
                Object[] objArr = this.values;
                if (i >= objArr.length) {
                    this.values = Arrays.copyOf(objArr, DefaultHttp2Connection.this.propertyKeyRegistry.size());
                }
            }
        }
    }

    private final class ConnectionStream extends DefaultStream {
        /* access modifiers changed from: package-private */
        public DefaultEndpoint<? extends Http2FlowController> createdBy() {
            return null;
        }

        public boolean isResetSent() {
            return false;
        }

        ConnectionStream() {
            super(0, Http2Stream.State.IDLE);
        }

        public Http2Stream resetSent() {
            throw new UnsupportedOperationException();
        }

        public Http2Stream open(boolean z) {
            throw new UnsupportedOperationException();
        }

        public Http2Stream close() {
            throw new UnsupportedOperationException();
        }

        public Http2Stream closeLocalSide() {
            throw new UnsupportedOperationException();
        }

        public Http2Stream closeRemoteSide() {
            throw new UnsupportedOperationException();
        }

        public Http2Stream headersSent(boolean z) {
            throw new UnsupportedOperationException();
        }

        public boolean isHeadersSent() {
            throw new UnsupportedOperationException();
        }

        public Http2Stream pushPromiseSent() {
            throw new UnsupportedOperationException();
        }

        public boolean isPushPromiseSent() {
            throw new UnsupportedOperationException();
        }
    }

    private final class DefaultEndpoint<F extends Http2FlowController> implements Http2Connection.Endpoint<F> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private F flowController;
        /* access modifiers changed from: private */
        public int lastStreamKnownByPeer = -1;
        private int maxActiveStreams;
        private final int maxReservedStreams;
        private int maxStreams;
        private int nextReservationStreamId;
        private int nextStreamIdToCreate;
        int numActiveStreams;
        int numStreams;
        private boolean pushToAllowed;
        private final boolean server;

        static {
            Class<DefaultHttp2Connection> cls = DefaultHttp2Connection.class;
        }

        DefaultEndpoint(boolean z, int i) {
            this.server = z;
            if (z) {
                this.nextStreamIdToCreate = 2;
                this.nextReservationStreamId = 0;
            } else {
                this.nextStreamIdToCreate = 1;
                this.nextReservationStreamId = 1;
            }
            this.pushToAllowed = true ^ z;
            this.maxActiveStreams = Integer.MAX_VALUE;
            this.maxReservedStreams = ObjectUtil.checkPositiveOrZero(i, "maxReservedStreams");
            updateMaxStreams();
        }

        public int incrementAndGetNextStreamId() {
            int i = this.nextReservationStreamId;
            if (i < 0) {
                return i;
            }
            int i2 = i + 2;
            this.nextReservationStreamId = i2;
            return i2;
        }

        private void incrementExpectedStreamId(int i) {
            int i2 = this.nextReservationStreamId;
            if (i > i2 && i2 >= 0) {
                this.nextReservationStreamId = i;
            }
            this.nextStreamIdToCreate = i + 2;
            this.numStreams++;
        }

        public boolean isValidStreamId(int i) {
            if (i <= 0) {
                return false;
            }
            return this.server == ((i & 1) == 0);
        }

        public boolean mayHaveCreatedStream(int i) {
            return isValidStreamId(i) && i <= lastStreamCreated();
        }

        public boolean canOpenStream() {
            return this.numActiveStreams < this.maxActiveStreams;
        }

        public DefaultStream createStream(int i, boolean z) throws Http2Exception {
            Http2Stream.State activeState = DefaultHttp2Connection.activeState(i, Http2Stream.State.IDLE, isLocal(), z);
            checkNewStreamAllowed(i, activeState);
            DefaultStream defaultStream = new DefaultStream(i, activeState);
            incrementExpectedStreamId(i);
            addStream(defaultStream);
            defaultStream.activate();
            return defaultStream;
        }

        public boolean created(Http2Stream http2Stream) {
            return (http2Stream instanceof DefaultStream) && ((DefaultStream) http2Stream).createdBy() == this;
        }

        public boolean isServer() {
            return this.server;
        }

        public DefaultStream reservePushStream(int i, Http2Stream http2Stream) throws Http2Exception {
            if (http2Stream == null) {
                throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Parent stream missing", new Object[0]);
            } else if (!isLocal() ? !http2Stream.state().remoteSideOpen() : !http2Stream.state().localSideOpen()) {
                throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Stream %d is not open for sending push promise", Integer.valueOf(http2Stream.id()));
            } else if (opposite().allowPushTo()) {
                Http2Stream.State state = isLocal() ? Http2Stream.State.RESERVED_LOCAL : Http2Stream.State.RESERVED_REMOTE;
                checkNewStreamAllowed(i, state);
                DefaultStream defaultStream = new DefaultStream(i, state);
                incrementExpectedStreamId(i);
                addStream(defaultStream);
                return defaultStream;
            } else {
                throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Server push not allowed to opposite endpoint", new Object[0]);
            }
        }

        private void addStream(DefaultStream defaultStream) {
            DefaultHttp2Connection.this.streamMap.put(defaultStream.id(), defaultStream);
            for (int i = 0; i < DefaultHttp2Connection.this.listeners.size(); i++) {
                try {
                    DefaultHttp2Connection.this.listeners.get(i).onStreamAdded(defaultStream);
                } catch (Throwable th) {
                    DefaultHttp2Connection.logger.error("Caught Throwable from listener onStreamAdded.", th);
                }
            }
        }

        public void allowPushTo(boolean z) {
            if (!z || !this.server) {
                this.pushToAllowed = z;
                return;
            }
            throw new IllegalArgumentException("Servers do not allow push");
        }

        public boolean allowPushTo() {
            return this.pushToAllowed;
        }

        public int numActiveStreams() {
            return this.numActiveStreams;
        }

        public int maxActiveStreams() {
            return this.maxActiveStreams;
        }

        public void maxActiveStreams(int i) {
            this.maxActiveStreams = i;
            updateMaxStreams();
        }

        public int lastStreamCreated() {
            int i = this.nextStreamIdToCreate;
            if (i > 1) {
                return i - 2;
            }
            return 0;
        }

        public int lastStreamKnownByPeer() {
            return this.lastStreamKnownByPeer;
        }

        /* access modifiers changed from: private */
        public void lastStreamKnownByPeer(int i) {
            this.lastStreamKnownByPeer = i;
        }

        public F flowController() {
            return this.flowController;
        }

        public void flowController(F f) {
            this.flowController = (Http2FlowController) ObjectUtil.checkNotNull(f, "flowController");
        }

        public Http2Connection.Endpoint<? extends Http2FlowController> opposite() {
            return isLocal() ? DefaultHttp2Connection.this.remoteEndpoint : DefaultHttp2Connection.this.localEndpoint;
        }

        private void updateMaxStreams() {
            this.maxStreams = (int) Math.min(2147483647L, ((long) this.maxActiveStreams) + ((long) this.maxReservedStreams));
        }

        private void checkNewStreamAllowed(int i, Http2Stream.State state) throws Http2Exception {
            int i2 = this.lastStreamKnownByPeer;
            if (i2 >= 0 && i > i2) {
                throw Http2Exception.streamError(i, Http2Error.REFUSED_STREAM, "Cannot create stream %d greater than Last-Stream-ID %d from GOAWAY.", Integer.valueOf(i), Integer.valueOf(this.lastStreamKnownByPeer));
            } else if (isValidStreamId(i)) {
                int i3 = this.nextStreamIdToCreate;
                if (i < i3) {
                    throw Http2Exception.closedStreamError(Http2Error.PROTOCOL_ERROR, "Request stream %d is behind the next expected stream %d", Integer.valueOf(i), Integer.valueOf(this.nextStreamIdToCreate));
                } else if (i3 > 0) {
                    boolean z = state == Http2Stream.State.RESERVED_LOCAL || state == Http2Stream.State.RESERVED_REMOTE;
                    if ((!z && !canOpenStream()) || (z && this.numStreams >= this.maxStreams)) {
                        Http2Error http2Error = Http2Error.REFUSED_STREAM;
                        StringBuilder sb = new StringBuilder("Maximum active streams violated for this endpoint: ");
                        sb.append(z ? this.maxStreams : this.maxActiveStreams);
                        throw Http2Exception.streamError(i, http2Error, sb.toString(), new Object[0]);
                    } else if (DefaultHttp2Connection.this.isClosed()) {
                        throw Http2Exception.connectionError(Http2Error.INTERNAL_ERROR, "Attempted to create stream id %d after connection was closed", Integer.valueOf(i));
                    }
                } else {
                    throw new Http2Exception(Http2Error.REFUSED_STREAM, "Stream IDs are exhausted for this endpoint.", Http2Exception.ShutdownHint.GRACEFUL_SHUTDOWN);
                }
            } else if (i >= 0) {
                throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Request stream %d is not correct for %s connection", Integer.valueOf(i), this.server ? "server" : "client");
            } else {
                throw new Http2NoMoreStreamIdsException();
            }
        }

        private boolean isLocal() {
            return this == DefaultHttp2Connection.this.localEndpoint;
        }
    }

    private final class ActiveStreams {
        private final List<Http2Connection.Listener> listeners;
        private final Queue<Event> pendingEvents = new ArrayDeque(4);
        private int pendingIterations;
        private final Set<Http2Stream> streams = new LinkedHashSet();

        ActiveStreams(List<Http2Connection.Listener> list) {
            this.listeners = list;
        }

        public int size() {
            return this.streams.size();
        }

        public void activate(final DefaultStream defaultStream) {
            if (allowModifications()) {
                addToActiveStreams(defaultStream);
            } else {
                this.pendingEvents.add(new Event() {
                    public void process() {
                        ActiveStreams.this.addToActiveStreams(defaultStream);
                    }
                });
            }
        }

        public void deactivate(final DefaultStream defaultStream, final Iterator<?> it) {
            if (allowModifications() || it != null) {
                removeFromActiveStreams(defaultStream, it);
            } else {
                this.pendingEvents.add(new Event() {
                    public void process() {
                        ActiveStreams.this.removeFromActiveStreams(defaultStream, it);
                    }
                });
            }
        }

        public Http2Stream forEachActiveStream(Http2StreamVisitor http2StreamVisitor) throws Http2Exception {
            incrementPendingIterations();
            try {
                for (Http2Stream next : this.streams) {
                    if (!http2StreamVisitor.visit(next)) {
                        return next;
                    }
                }
                decrementPendingIterations();
                return null;
            } finally {
                decrementPendingIterations();
            }
        }

        /* access modifiers changed from: package-private */
        public void addToActiveStreams(DefaultStream defaultStream) {
            if (this.streams.add(defaultStream)) {
                defaultStream.createdBy().numActiveStreams++;
                for (int i = 0; i < this.listeners.size(); i++) {
                    try {
                        this.listeners.get(i).onStreamActive(defaultStream);
                    } catch (Throwable th) {
                        DefaultHttp2Connection.logger.error("Caught Throwable from listener onStreamActive.", th);
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void removeFromActiveStreams(DefaultStream defaultStream, Iterator<?> it) {
            if (this.streams.remove(defaultStream)) {
                DefaultEndpoint<? extends Http2FlowController> createdBy = defaultStream.createdBy();
                createdBy.numActiveStreams--;
                DefaultHttp2Connection.this.notifyClosed(defaultStream);
            }
            DefaultHttp2Connection.this.removeStream(defaultStream, it);
        }

        /* access modifiers changed from: package-private */
        public boolean allowModifications() {
            return this.pendingIterations == 0;
        }

        /* access modifiers changed from: package-private */
        public void incrementPendingIterations() {
            this.pendingIterations++;
        }

        /* access modifiers changed from: package-private */
        public void decrementPendingIterations() {
            this.pendingIterations--;
            if (allowModifications()) {
                while (true) {
                    Event poll = this.pendingEvents.poll();
                    if (poll != null) {
                        try {
                            poll.process();
                        } catch (Throwable th) {
                            DefaultHttp2Connection.logger.error("Caught Throwable while processing pending ActiveStreams$Event.", th);
                        }
                    } else {
                        return;
                    }
                }
            }
        }
    }

    final class DefaultPropertyKey implements Http2Connection.PropertyKey {
        final int index;

        DefaultPropertyKey(int i) {
            this.index = i;
        }

        /* access modifiers changed from: package-private */
        public DefaultPropertyKey verifyConnection(Http2Connection http2Connection) {
            if (http2Connection == DefaultHttp2Connection.this) {
                return this;
            }
            throw new IllegalArgumentException("Using a key that was not created by this connection");
        }
    }

    private final class PropertyKeyRegistry {
        final List<DefaultPropertyKey> keys;

        private PropertyKeyRegistry() {
            this.keys = new ArrayList(4);
        }

        /* access modifiers changed from: package-private */
        public DefaultPropertyKey newKey() {
            DefaultPropertyKey defaultPropertyKey = new DefaultPropertyKey(this.keys.size());
            this.keys.add(defaultPropertyKey);
            return defaultPropertyKey;
        }

        /* access modifiers changed from: package-private */
        public int size() {
            return this.keys.size();
        }
    }
}
