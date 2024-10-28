package io.ktor.network.sockets;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import androidx.tvprovider.media.tv.TvContractCompat;
import io.ktor.network.selector.SelectableBase;
import io.ktor.network.selector.SelectorManager;
import io.ktor.network.sockets.SocketOptions;
import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.utils.io.ByteWriteChannelKt;
import io.ktor.utils.io.ReaderJob;
import io.ktor.utils.io.WriterJob;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectableChannel;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\b \u0018\u0000*\u000e\b\u0000\u0010\u0001 \u0001*\u00020\u0002*\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006B1\u0012\u0006\u0010\u0007\u001a\u00028\u0000\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010\u000fJ\n\u00104\u001a\u0004\u0018\u00010/H\u0002JE\u00105\u001a\u0002H6\"\b\b\u0001\u00106*\u00020+2\u0006\u00107\u001a\u0002082\u0006\u0010\u0007\u001a\u0002092\u000e\u0010:\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H60\u001c2\f\u0010;\u001a\b\u0012\u0004\u0012\u0002H60<H\u0002¢\u0006\u0002\u0010=J\u000e\u0010>\u001a\u00020'2\u0006\u0010\u0007\u001a\u000209J\u000e\u0010?\u001a\u00020\u001d2\u0006\u0010\u0007\u001a\u000209J\b\u0010@\u001a\u00020AH\u0002J\b\u0010B\u001a\u00020AH\u0016J\u001e\u0010C\u001a\u0004\u0018\u00010/2\b\u0010D\u001a\u0004\u0018\u00010/2\b\u0010E\u001a\u0004\u0018\u00010/H\u0002J\b\u0010F\u001a\u00020AH\u0016R\u0016\u0010\u0007\u001a\u00028\u0000X\u0004¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\u00020\u00168VX\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0019\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001d0\u001cX\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u001e\u0010\u001fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020#X\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0004¢\u0006\u0002\n\u0000R\u001c\u0010&\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010'0\u001cX\u0004¢\u0006\b\n\u0000\u0012\u0004\b(\u0010\u001fR\"\u0010)\u001a\u00020**\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010+0\u001c8BX\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-R*\u0010.\u001a\u0004\u0018\u00010/*\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010+0\u001c8BX\u0004¢\u0006\f\u0012\u0004\b0\u00101\u001a\u0004\b2\u00103¨\u0006G"}, d2 = {"Lio/ktor/network/sockets/NIOSocketImpl;", "S", "Ljava/nio/channels/ByteChannel;", "Ljava/nio/channels/SelectableChannel;", "Lio/ktor/network/sockets/ReadWriteSocket;", "Lio/ktor/network/selector/SelectableBase;", "Lkotlinx/coroutines/CoroutineScope;", "channel", "selector", "Lio/ktor/network/selector/SelectorManager;", "pool", "Lio/ktor/utils/io/pool/ObjectPool;", "Ljava/nio/ByteBuffer;", "socketOptions", "Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;", "(Ljava/nio/channels/SelectableChannel;Lio/ktor/network/selector/SelectorManager;Lio/ktor/utils/io/pool/ObjectPool;Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;)V", "getChannel", "()Ljava/nio/channels/SelectableChannel;", "Ljava/nio/channels/SelectableChannel;", "closeFlag", "Ljava/util/concurrent/atomic/AtomicBoolean;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "getPool", "()Lio/ktor/utils/io/pool/ObjectPool;", "readerJob", "Ljava/util/concurrent/atomic/AtomicReference;", "Lio/ktor/utils/io/ReaderJob;", "getReaderJob$annotations", "()V", "getSelector", "()Lio/ktor/network/selector/SelectorManager;", "socketContext", "Lkotlinx/coroutines/CompletableJob;", "getSocketContext", "()Lkotlinx/coroutines/CompletableJob;", "writerJob", "Lio/ktor/utils/io/WriterJob;", "getWriterJob$annotations", "completedOrNotStarted", "", "Lkotlinx/coroutines/Job;", "getCompletedOrNotStarted", "(Ljava/util/concurrent/atomic/AtomicReference;)Z", "exception", "", "getException$annotations", "(Ljava/util/concurrent/atomic/AtomicReference;)V", "getException", "(Ljava/util/concurrent/atomic/AtomicReference;)Ljava/lang/Throwable;", "actualClose", "attachFor", "J", "name", "", "Lio/ktor/utils/io/ByteChannel;", "ref", "producer", "Lkotlin/Function0;", "(Ljava/lang/String;Lio/ktor/utils/io/ByteChannel;Ljava/util/concurrent/atomic/AtomicReference;Lkotlin/jvm/functions/Function0;)Lkotlinx/coroutines/Job;", "attachForReading", "attachForWriting", "checkChannels", "", "close", "combine", "e1", "e2", "dispose", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NIOSocketImpl.kt */
public abstract class NIOSocketImpl<S extends SelectableChannel & ByteChannel> extends SelectableBase implements ReadWriteSocket, CoroutineScope {
    private final S channel;
    private final AtomicBoolean closeFlag;
    private final ObjectPool<ByteBuffer> pool;
    private final AtomicReference<ReaderJob> readerJob;
    private final SelectorManager selector;
    private final CompletableJob socketContext;
    /* access modifiers changed from: private */
    public final SocketOptions.TCPClientSocketOptions socketOptions;
    private final AtomicReference<WriterJob> writerJob;

    private static /* synthetic */ void getException$annotations(AtomicReference atomicReference) {
    }

    private static /* synthetic */ void getReaderJob$annotations() {
    }

    private static /* synthetic */ void getWriterJob$annotations() {
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ NIOSocketImpl(SelectableChannel selectableChannel, SelectorManager selectorManager, ObjectPool objectPool, SocketOptions.TCPClientSocketOptions tCPClientSocketOptions, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(selectableChannel, selectorManager, objectPool, (i & 8) != 0 ? null : tCPClientSocketOptions);
    }

    public S getChannel() {
        return this.channel;
    }

    public final SelectorManager getSelector() {
        return this.selector;
    }

    public final ObjectPool<ByteBuffer> getPool() {
        return this.pool;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NIOSocketImpl(S s, SelectorManager selectorManager, ObjectPool<ByteBuffer> objectPool, SocketOptions.TCPClientSocketOptions tCPClientSocketOptions) {
        super(s);
        Intrinsics.checkNotNullParameter(s, TvContractCompat.PARAM_CHANNEL);
        Intrinsics.checkNotNullParameter(selectorManager, "selector");
        this.channel = s;
        this.selector = selectorManager;
        this.pool = objectPool;
        this.socketOptions = tCPClientSocketOptions;
        this.closeFlag = new AtomicBoolean();
        this.readerJob = new AtomicReference<>();
        this.writerJob = new AtomicReference<>();
        this.socketContext = JobKt.Job$default((Job) null, 1, (Object) null);
    }

    public CompletableJob getSocketContext() {
        return this.socketContext;
    }

    public CoroutineContext getCoroutineContext() {
        return getSocketContext();
    }

    public final WriterJob attachForReading(io.ktor.utils.io.ByteChannel byteChannel) {
        Intrinsics.checkNotNullParameter(byteChannel, TvContractCompat.PARAM_CHANNEL);
        return (WriterJob) attachFor("reading", byteChannel, this.writerJob, new NIOSocketImpl$attachForReading$1(this, byteChannel));
    }

    public final ReaderJob attachForWriting(io.ktor.utils.io.ByteChannel byteChannel) {
        Intrinsics.checkNotNullParameter(byteChannel, TvContractCompat.PARAM_CHANNEL);
        return (ReaderJob) attachFor("writing", byteChannel, this.readerJob, new NIOSocketImpl$attachForWriting$1(this, byteChannel));
    }

    public void dispose() {
        close();
    }

    public void close() {
        ByteWriteChannel channel2;
        if (this.closeFlag.compareAndSet(false, true)) {
            ReaderJob readerJob2 = this.readerJob.get();
            if (!(readerJob2 == null || (channel2 = readerJob2.getChannel()) == null)) {
                ByteWriteChannelKt.close(channel2);
            }
            WriterJob writerJob2 = this.writerJob.get();
            if (writerJob2 != null) {
                Job.DefaultImpls.cancel$default((Job) writerJob2, (CancellationException) null, 1, (Object) null);
            }
            checkChannels();
        }
    }

    private final <J extends Job> J attachFor(String str, io.ktor.utils.io.ByteChannel byteChannel, AtomicReference<J> atomicReference, Function0<? extends J> function0) {
        if (!this.closeFlag.get()) {
            J j = (Job) function0.invoke();
            if (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(atomicReference, (Object) null, j)) {
                IllegalStateException illegalStateException = new IllegalStateException(str + " channel has already been set");
                Job.DefaultImpls.cancel$default((Job) j, (CancellationException) null, 1, (Object) null);
                throw illegalStateException;
            } else if (!this.closeFlag.get()) {
                byteChannel.attachJob(j);
                j.invokeOnCompletion(new NIOSocketImpl$attachFor$1(this));
                return j;
            } else {
                ClosedChannelException closedChannelException = new ClosedChannelException();
                Job.DefaultImpls.cancel$default((Job) j, (CancellationException) null, 1, (Object) null);
                byteChannel.close(closedChannelException);
                throw closedChannelException;
            }
        } else {
            ClosedChannelException closedChannelException2 = new ClosedChannelException();
            byteChannel.close(closedChannelException2);
            throw closedChannelException2;
        }
    }

    private final Throwable actualClose() {
        try {
            ((ByteChannel) getChannel()).close();
            super.close();
            this.selector.notifyClosed(this);
            return null;
        } catch (Throwable th) {
            this.selector.notifyClosed(this);
            return th;
        }
    }

    /* access modifiers changed from: private */
    public final void checkChannels() {
        if (this.closeFlag.get() && getCompletedOrNotStarted(this.readerJob) && getCompletedOrNotStarted(this.writerJob)) {
            Throwable exception = getException(this.readerJob);
            Throwable exception2 = getException(this.writerJob);
            Throwable combine = combine(combine(exception, exception2), actualClose());
            if (combine == null) {
                getSocketContext().complete();
            } else {
                getSocketContext().completeExceptionally(combine);
            }
        }
    }

    private final Throwable combine(Throwable th, Throwable th2) {
        if (th == null) {
            return th2;
        }
        if (th2 == null || th == th2) {
            return th;
        }
        ExceptionsKt.addSuppressed(th, th2);
        return th;
    }

    private final boolean getCompletedOrNotStarted(AtomicReference<? extends Job> atomicReference) {
        Job job = (Job) atomicReference.get();
        return job == null || job.isCompleted();
    }

    private final Throwable getException(AtomicReference<? extends Job> atomicReference) {
        CancellationException cancellationException;
        Job job = (Job) atomicReference.get();
        if (job == null) {
            return null;
        }
        if (!job.isCancelled()) {
            job = null;
        }
        if (job == null || (cancellationException = job.getCancellationException()) == null) {
            return null;
        }
        return cancellationException.getCause();
    }
}
