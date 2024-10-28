package io.ktor.utils.io;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.Buffer;
import io.ktor.utils.io.core.BufferUtilsJvmKt;
import io.ktor.utils.io.core.ByteBuffersKt;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.OutputArraysJVMKt;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.internal.CancellableReusableContinuation;
import io.ktor.utils.io.internal.ClosedElement;
import io.ktor.utils.io.internal.FailedLookAhead;
import io.ktor.utils.io.internal.JoiningState;
import io.ktor.utils.io.internal.ObjectPoolKt;
import io.ktor.utils.io.internal.ReadSessionImpl;
import io.ktor.utils.io.internal.ReadWriteBufferState;
import io.ktor.utils.io.internal.ReadWriteBufferStateKt;
import io.ktor.utils.io.internal.RingBufferCapacity;
import io.ktor.utils.io.internal.TerminatedLookAhead;
import io.ktor.utils.io.internal.WriteSessionImpl;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.ClosedReceiveChannelException;

@Metadata(d1 = {"\u0000Ò\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0004\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\bB\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0010\u0018\u0000 ó\u00022\u00030õ\u00022\u00030ö\u00022\u00030÷\u00022\u00020k2\u00030ø\u00022\u00030ù\u0002:\u0002ó\u0002B\u0011\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004B)\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u0003\u0010\fJ\u001f\u0010\u0010\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0012H\u0017¢\u0006\u0004\b\u0015\u0010\u0016J\u001b\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019J\u001b\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0019J\u0013\u0010\u001b\u001a\u00020\u0014H@ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\u0014H@ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001cJ\u0013\u0010\u001e\u001a\u00020\u0014H@ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001cJ/\u0010\"\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\n2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00140 H@ø\u0001\u0000¢\u0006\u0004\b\"\u0010#J\u0011\u0010%\u001a\u0004\u0018\u00010$H\u0016¢\u0006\u0004\b%\u0010&J'\u0010*\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020\nH\u0000¢\u0006\u0004\b(\u0010)J\u0019\u0010-\u001a\u00020\u00052\b\u0010,\u001a\u0004\u0018\u00010+H\u0016¢\u0006\u0004\b-\u0010.J\u0019\u0010/\u001a\u00020\u00052\b\u0010,\u001a\u0004\u0018\u00010+H\u0016¢\u0006\u0004\b/\u0010.JP\u00105\u001a\u00020\u00052\u0006\u00100\u001a\u00020\u000526\u00104\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(0\u0012\u0004\u0012\u00020\u000501H\b¢\u0006\u0004\b5\u00106J\u0017\u00107\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\nH\u0016¢\u0006\u0004\b7\u00108J-\u0010@\u001a\u00020:2\u0006\u00109\u001a\u00020\u00002\u0006\u0010;\u001a\u00020:2\b\u0010=\u001a\u0004\u0018\u00010<H@ø\u0001\u0000¢\u0006\u0004\b>\u0010?J\u000f\u0010D\u001a\u00020AH\u0000¢\u0006\u0004\bB\u0010CJ,\u0010G\u001a\u00020\u00142\u0017\u0010F\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00140 ¢\u0006\u0002\bEHHø\u0001\u0000¢\u0006\u0004\bG\u0010HJ4\u0010I\u001a\u00020\u00142\u0006\u0010=\u001a\u00020<2\u0017\u0010!\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00140 ¢\u0006\u0002\bEHHø\u0001\u0000¢\u0006\u0004\bI\u0010JJ\u001b\u0010L\u001a\u00020:2\u0006\u0010K\u001a\u00020:H@ø\u0001\u0000¢\u0006\u0004\bL\u0010MJ#\u0010O\u001a\u00020:2\u0006\u0010N\u001a\u00020:2\u0006\u0010K\u001a\u00020:H@ø\u0001\u0000¢\u0006\u0004\bO\u0010PJA\u0010S\u001a\u00020\u00142\u0006\u0010Q\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u000e2\u0017\u0010R\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00140 ¢\u0006\u0002\bEH\b¢\u0006\u0004\bS\u0010TJ\u000f\u0010U\u001a\u00020\u0014H\u0016¢\u0006\u0004\bU\u0010VJ\u0017\u0010X\u001a\u00020\u00142\u0006\u0010W\u001a\u00020\nH\u0016¢\u0006\u0004\bX\u00108J\u0017\u0010Y\u001a\u00020\u00142\u0006\u0010=\u001a\u00020<H\u0002¢\u0006\u0004\bY\u0010ZJ\u000f\u0010[\u001a\u00020\u0014H\u0016¢\u0006\u0004\b[\u0010VJ\u0017\u0010]\u001a\u00020\u00142\u0006\u0010\\\u001a\u00020\nH\u0002¢\u0006\u0004\b]\u00108J\u0011\u0010`\u001a\u0004\u0018\u00010<H\u0000¢\u0006\u0004\b^\u0010_J#\u0010d\u001a\u00020\u00142\u0006\u00109\u001a\u00020\u00002\u0006\u0010a\u001a\u00020\u0005H@ø\u0001\u0000¢\u0006\u0004\bb\u0010cJ+\u0010e\u001a\u00020\u00142\u0006\u00109\u001a\u00020\u00002\u0006\u0010a\u001a\u00020\u00052\u0006\u0010=\u001a\u00020<H@ø\u0001\u0000¢\u0006\u0004\be\u0010fJ.\u0010i\u001a\u00028\u0000\"\u0004\b\u0000\u0010g2\u0017\u00104\u001a\u0013\u0012\u0004\u0012\u00020h\u0012\u0004\u0012\u00028\u00000 ¢\u0006\u0002\bEH\u0017¢\u0006\u0004\bi\u0010jJB\u0010n\u001a\u00028\u0000\"\u0004\b\u0000\u0010g2'\u00104\u001a#\b\u0001\u0012\u0004\u0012\u00020k\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000l\u0012\u0006\u0012\u0004\u0018\u00010m01¢\u0006\u0002\bEH@ø\u0001\u0000¢\u0006\u0004\bn\u0010oJ\u000f\u0010p\u001a\u00020\bH\u0002¢\u0006\u0004\bp\u0010qJA\u0010x\u001a\u00020:2\u0006\u0010s\u001a\u00020r2\u0006\u0010t\u001a\u00020:2\u0006\u0010u\u001a\u00020:2\u0006\u0010\u001f\u001a\u00020:2\u0006\u0010K\u001a\u00020:H@ø\u0001\u0001ø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\bv\u0010wJ\u001f\u0010|\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u00012\u0006\u0010y\u001a\u00020\nH\u0000¢\u0006\u0004\bz\u0010{J/\u0010~\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\n2\u0012\u0010}\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00140 H@ø\u0001\u0000¢\u0006\u0004\b~\u0010#J/\u0010\u0001\u001a\u00020\n2\u0007\u0010\u0001\u001a\u000202\b\b\u0002\u00107\u001a\u00020\n2\b\b\u0002\u0010K\u001a\u00020\nH\u0002¢\u0006\u0006\b\u0001\u0010\u0001J\u001b\u0010\u0001\u001a\u00020\n2\u0007\u0010\u0001\u001a\u00020\u0001H\u0002¢\u0006\u0006\b\u0001\u0010\u0001J-\u0010\u0001\u001a\u00020\n2\b\u0010\u0001\u001a\u00030\u00012\u0006\u0010u\u001a\u00020\n2\u0007\u0010\u0001\u001a\u00020\nH\u0002¢\u0006\u0006\b\u0001\u0010\u0001J \u0010\u0001\u001a\u00020\n2\b\u0010\u0001\u001a\u00030\u0001H@ø\u0001\u0000¢\u0006\u0006\b\u0001\u0010\u0001J\u001f\u0010\u0001\u001a\u00020\n2\u0007\u0010\u0001\u001a\u00020\u0001H@ø\u0001\u0000¢\u0006\u0006\b\u0001\u0010\u0001J1\u0010\u0001\u001a\u00020\n2\b\u0010\u0001\u001a\u00030\u00012\u0006\u0010u\u001a\u00020\n2\u0007\u0010\u0001\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0006\b\u0001\u0010\u0001J.\u0010\u0001\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\n2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00140 H\u0016¢\u0006\u0006\b\u0001\u0010\u0001J \u0010\u0001\u001a\u00020\n2\b\u0010\u0001\u001a\u00030\u0001H@ø\u0001\u0000¢\u0006\u0006\b\u0001\u0010\u0001J\u001f\u0010\u0001\u001a\u00020\n2\u0007\u0010\u0001\u001a\u00020\u0001H@ø\u0001\u0000¢\u0006\u0006\b\u0001\u0010\u0001J1\u0010\u0001\u001a\u00020\n2\b\u0010\u0001\u001a\u00030\u00012\u0006\u0010u\u001a\u00020\n2\u0007\u0010\u0001\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0006\b\u0001\u0010\u0001J1\u0010\u0001\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\n2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00140 H@ø\u0001\u0000¢\u0006\u0005\b\u0001\u0010#J\u0015\u0010\u0001\u001a\u00020\u0005H@ø\u0001\u0000¢\u0006\u0005\b\u0001\u0010\u001cJ\u0016\u0010\u0001\u001a\u00030\u0001H@ø\u0001\u0000¢\u0006\u0005\b\u0001\u0010\u001cJ\u0016\u0010\u0001\u001a\u00030\u0001H@ø\u0001\u0000¢\u0006\u0005\b\u0001\u0010\u001cJ\u0016\u0010\u0001\u001a\u00030\u0001H@ø\u0001\u0000¢\u0006\u0005\b\u0001\u0010\u001cJ(\u0010\u0001\u001a\u00020\u00142\b\u0010\u0001\u001a\u00030\u00012\u0006\u0010\u0017\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0006\b\u0001\u0010\u0001J\u001f\u0010\u0001\u001a\u00020\n2\u0007\u0010\u0001\u001a\u00020\u0001H@ø\u0001\u0000¢\u0006\u0006\b\u0001\u0010\u0001J1\u0010\u0001\u001a\u00020\u00142\b\u0010\u0001\u001a\u00030\u00012\u0006\u0010u\u001a\u00020\n2\u0007\u0010\u0001\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0006\b\u0001\u0010\u0001J(\u0010\u0001\u001a\u00020\u00142\b\u0010\u0001\u001a\u00030\u00012\u0006\u0010\u0017\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0006\b\u0001\u0010\u0001J(\u0010\u0001\u001a\u00020\n2\u0007\u0010\u0001\u001a\u00020\u00012\u0007\u0010\u0001\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0006\b\u0001\u0010\u0001J1\u0010\u0001\u001a\u00020\u00142\b\u0010\u0001\u001a\u00030\u00012\u0006\u0010u\u001a\u00020\n2\u0007\u0010\u0001\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0006\b\u0001\u0010\u0001J\u0015\u0010\u0001\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0005\b\u0001\u0010\u001cJ\u0015\u0010\u0001\u001a\u00020:H@ø\u0001\u0000¢\u0006\u0005\b\u0001\u0010\u001cJ\u001e\u0010\u0001\u001a\u00030\u00012\u0006\u0010Q\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0005\b\u0001\u0010\u0019J1\u0010¡\u0001\u001a\u00030\u00012\u0006\u0010Q\u001a\u00020\n2\b\u0010 \u0001\u001a\u00030\u00012\u0006\u0010\r\u001a\u00020\u0001H@ø\u0001\u0000¢\u0006\u0006\b¡\u0001\u0010¢\u0001JC\u0010¦\u0001\u001a\u00028\u0000\"\n\b\u0000\u0010¤\u0001*\u00030£\u00012\u0006\u0010Q\u001a\u00020\n2\u0018\u0010¥\u0001\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00028\u00000 ¢\u0006\u0002\bEHHø\u0001\u0000¢\u0006\u0005\b¦\u0001\u0010#J\u001e\u0010§\u0001\u001a\u00030\u00012\u0006\u0010;\u001a\u00020:H@ø\u0001\u0000¢\u0006\u0005\b§\u0001\u0010MJ\u001e\u0010¨\u0001\u001a\u00030\u00012\u0006\u0010;\u001a\u00020:H@ø\u0001\u0000¢\u0006\u0005\b¨\u0001\u0010MJ,\u0010ª\u0001\u001a\u00020\u00142\u0018\u0010}\u001a\u0014\u0012\u0005\u0012\u00030©\u0001\u0012\u0004\u0012\u00020\u00140 ¢\u0006\u0002\bEH\u0017¢\u0006\u0006\bª\u0001\u0010«\u0001J\u0016\u0010­\u0001\u001a\u00030¬\u0001H@ø\u0001\u0000¢\u0006\u0005\b­\u0001\u0010\u001cJ\u001d\u0010®\u0001\u001a\u00020\u00052\u0006\u0010Q\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0005\b®\u0001\u0010\u0019J\u001d\u0010¯\u0001\u001a\u00020\u00052\u0006\u0010Q\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0005\b¯\u0001\u0010\u0019J\u001d\u0010°\u0001\u001a\u00020\u00052\u0006\u0010Q\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0005\b°\u0001\u0010\u0019J\u001b\u0010±\u0001\u001a\u00020\u00052\u0006\u0010Q\u001a\u00020\nH\b¢\u0006\u0006\b±\u0001\u0010²\u0001J?\u0010´\u0001\u001a\u00020\u00142(\u0010}\u001a$\b\u0001\u0012\u0005\u0012\u00030³\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140l\u0012\u0006\u0012\u0004\u0018\u00010m01¢\u0006\u0002\bEH@ø\u0001\u0000¢\u0006\u0005\b´\u0001\u0010oJ \u0010¶\u0001\u001a\u0005\u0018\u00010µ\u00012\u0006\u0010;\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0005\b¶\u0001\u0010\u0019J8\u0010»\u0001\u001a\u00020\u0005\"\u000f\b\u0000\u0010¹\u0001*\b0·\u0001j\u0003`¸\u00012\u0007\u0010º\u0001\u001a\u00028\u00002\u0006\u0010;\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0006\b»\u0001\u0010¼\u0001J-\u0010½\u0001\u001a\u00020\u00052\r\u0010º\u0001\u001a\b0·\u0001j\u0003`¸\u00012\u0006\u0010;\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0006\b½\u0001\u0010¼\u0001J-\u0010¾\u0001\u001a\u00020\u00052\r\u0010º\u0001\u001a\b0·\u0001j\u0003`¸\u00012\u0006\u0010;\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0006\b¾\u0001\u0010¼\u0001J2\u0010¿\u0001\u001a\u00020\u00052\u001d\u0010!\u001a\u0019\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000501¢\u0006\u0002\bEH\b¢\u0006\u0006\b¿\u0001\u0010À\u0001J\u001a\u0010Á\u0001\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\bH\u0002¢\u0006\u0006\bÁ\u0001\u0010Â\u0001J\u001b\u0010Ã\u0001\u001a\u00030\u00012\u0006\u0010;\u001a\u00020:H\u0002¢\u0006\u0006\bÃ\u0001\u0010Ä\u0001J&\u0010Ç\u0001\u001a\u0004\u0018\u00010\u00012\u0007\u0010Å\u0001\u001a\u00020\n2\u0007\u0010Æ\u0001\u001a\u00020\nH\u0016¢\u0006\u0006\bÇ\u0001\u0010È\u0001J\u0012\u0010Ë\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0006\bÉ\u0001\u0010Ê\u0001J&\u0010Î\u0001\u001a\u0004\u0018\u00010\u00002\u0007\u0010Ì\u0001\u001a\u00020\u00002\u0007\u0010Í\u0001\u001a\u00020<H\u0002¢\u0006\u0006\bÎ\u0001\u0010Ï\u0001J\u0011\u0010Ð\u0001\u001a\u00020\u0014H\u0002¢\u0006\u0005\bÐ\u0001\u0010VJ\u0011\u0010Ò\u0001\u001a\u00020\u0014H\u0000¢\u0006\u0005\bÑ\u0001\u0010VJ\u001c\u0010Ó\u0001\u001a\u00020\u00142\b\u0010,\u001a\u0004\u0018\u00010+H\u0002¢\u0006\u0006\bÓ\u0001\u0010Ô\u0001J\u0011\u0010Õ\u0001\u001a\u00020\u0014H\u0002¢\u0006\u0005\bÕ\u0001\u0010VJ#\u0010Õ\u0001\u001a\u00020\u00142\u000e\u0010×\u0001\u001a\t\u0012\u0004\u0012\u00020+0Ö\u0001H\b¢\u0006\u0006\bÕ\u0001\u0010Ø\u0001J\u0011\u0010Ù\u0001\u001a\u00020\u0014H\u0002¢\u0006\u0005\bÙ\u0001\u0010VJ#\u0010Û\u0001\u001a\u00020<2\u0007\u0010Ú\u0001\u001a\u00020\u00002\u0006\u0010a\u001a\u00020\u0005H\u0002¢\u0006\u0006\bÛ\u0001\u0010Ü\u0001J\u0014\u0010Ý\u0001\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0006\bÝ\u0001\u0010Þ\u0001J\u0014\u0010à\u0001\u001a\u0004\u0018\u00010\u0001H\u0000¢\u0006\u0006\bß\u0001\u0010Þ\u0001J\u0012\u0010á\u0001\u001a\u00020\u0005H\u0002¢\u0006\u0006\bá\u0001\u0010â\u0001J\u0013\u0010ã\u0001\u001a\u00030³\u0001H\u0016¢\u0006\u0006\bã\u0001\u0010ä\u0001J(\u0010æ\u0001\u001a\u00020m2\u0006\u0010Q\u001a\u00020\n2\r\u0010å\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050lH\u0002¢\u0006\u0005\bæ\u0001\u0010\u0019J\u0013\u0010ç\u0001\u001a\u00030µ\u0001H\u0016¢\u0006\u0006\bç\u0001\u0010è\u0001J\u001a\u0010é\u0001\u001a\u00020\u00052\u0006\u0010=\u001a\u00020<H\u0002¢\u0006\u0006\bé\u0001\u0010ê\u0001J\u001b\u0010ì\u0001\u001a\u00020\u00052\u0007\u0010ë\u0001\u001a\u00020\u0005H\u0002¢\u0006\u0006\bì\u0001\u0010í\u0001J\u0012\u0010ï\u0001\u001a\u00020\u0005H\u0000¢\u0006\u0006\bî\u0001\u0010â\u0001J\u001c\u0010ñ\u0001\u001a\u00020\n2\b\u0010ð\u0001\u001a\u00030\u0001H\u0002¢\u0006\u0006\bñ\u0001\u0010ò\u0001J\u001d\u0010ô\u0001\u001a\u00020\u00142\u0006\u0010Q\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0005\bó\u0001\u0010\u0019J1\u0010õ\u0001\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\n2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00140 H@ø\u0001\u0000¢\u0006\u0005\bõ\u0001\u0010#J\u001a\u0010ö\u0001\u001a\u00020\n2\u0006\u00109\u001a\u00020H\u0002¢\u0006\u0006\bö\u0001\u0010÷\u0001J\u001a\u0010ö\u0001\u001a\u00020\n2\u0006\u00109\u001a\u00020\u0001H\u0002¢\u0006\u0006\bö\u0001\u0010\u0001J,\u0010ö\u0001\u001a\u00020\n2\u0007\u00109\u001a\u00030\u00012\u0006\u0010u\u001a\u00020\n2\u0007\u0010\u0001\u001a\u00020\nH\u0002¢\u0006\u0006\bö\u0001\u0010\u0001J\u001f\u0010ø\u0001\u001a\u00020\n2\u0007\u00109\u001a\u00030\u0001H@ø\u0001\u0000¢\u0006\u0006\bø\u0001\u0010\u0001J\u001e\u0010ø\u0001\u001a\u00020\n2\u0006\u00109\u001a\u00020\u0001H@ø\u0001\u0000¢\u0006\u0006\bø\u0001\u0010\u0001J0\u0010ø\u0001\u001a\u00020\n2\u0007\u00109\u001a\u00030\u00012\u0006\u0010u\u001a\u00020\n2\u0007\u0010\u0001\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0006\bø\u0001\u0010\u0001J.\u0010ø\u0001\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\n2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00140 H\u0016¢\u0006\u0006\bø\u0001\u0010\u0001J\u001f\u0010ù\u0001\u001a\u00020\n2\u0007\u00109\u001a\u00030\u0001H@ø\u0001\u0000¢\u0006\u0006\bù\u0001\u0010\u0001J\u001e\u0010ù\u0001\u001a\u00020\n2\u0006\u00109\u001a\u00020\u0001H@ø\u0001\u0000¢\u0006\u0006\bù\u0001\u0010\u0001J \u0010û\u0001\u001a\u00020\u00142\b\u0010ú\u0001\u001a\u00030\u0001H@ø\u0001\u0000¢\u0006\u0006\bû\u0001\u0010ü\u0001J \u0010þ\u0001\u001a\u00020\u00142\b\u0010ý\u0001\u001a\u00030\u0001H@ø\u0001\u0000¢\u0006\u0006\bþ\u0001\u0010ÿ\u0001J \u0010\u0002\u001a\u00020\u00142\b\u0010\u0002\u001a\u00030\u0001H@ø\u0001\u0000¢\u0006\u0006\b\u0002\u0010\u0002J7\u0010\u0002\u001a\u00020\u00142\u0007\u0010\u0002\u001a\u00020r2\u0007\u0010\u0002\u001a\u00020\n2\u0007\u0010\u0002\u001a\u00020\nH@ø\u0001\u0001ø\u0001\u0000ø\u0001\u0000¢\u0006\u0006\b\u0002\u0010\u0002J\u001e\u0010\u0002\u001a\u00020\u00142\u0006\u00109\u001a\u00020H@ø\u0001\u0000¢\u0006\u0006\b\u0002\u0010\u0002J\u001e\u0010\u0002\u001a\u00020\u00142\u0006\u00109\u001a\u00020\u0001H@ø\u0001\u0000¢\u0006\u0006\b\u0002\u0010\u0001J0\u0010\u0002\u001a\u00020\u00142\u0007\u00109\u001a\u00030\u00012\u0006\u0010u\u001a\u00020\n2\u0007\u0010\u0001\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0006\b\u0002\u0010\u0001J\u001e\u0010\u0002\u001a\u00020\u00142\u0006\u00109\u001a\u00020H@ø\u0001\u0000¢\u0006\u0006\b\u0002\u0010\u0002J\u001e\u0010\u0002\u001a\u00020\u00142\u0006\u00109\u001a\u00020\u0001H@ø\u0001\u0000¢\u0006\u0006\b\u0002\u0010\u0001J0\u0010\u0002\u001a\u00020\u00142\u0007\u00109\u001a\u00030\u00012\u0006\u0010u\u001a\u00020\n2\u0007\u0010\u0001\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0006\b\u0002\u0010\u0001J\u001e\u0010\u0002\u001a\u00020\u00142\u0007\u0010\u0002\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0005\b\u0002\u0010\u0019J\u001e\u0010\u0002\u001a\u00020\u00142\u0007\u0010\u0002\u001a\u00020:H@ø\u0001\u0000¢\u0006\u0005\b\u0002\u0010MJ \u0010\u0002\u001a\u00020\u00142\b\u0010ð\u0001\u001a\u00030\u0001H@ø\u0001\u0000¢\u0006\u0006\b\u0002\u0010\u0002J \u0010\u0002\u001a\u00020\u00142\b\u0010ð\u0001\u001a\u00030\u0001H@ø\u0001\u0000¢\u0006\u0006\b\u0002\u0010\u0002JQ\u0010\u0002\u001a\u00020\u00142\u0006\u0010Q\u001a\u00020\n2\u0017\u0010F\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00140 ¢\u0006\u0002\bE2\u0018\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00140 ¢\u0006\u0002\bEHHø\u0001\u0000¢\u0006\u0006\b\u0002\u0010\u0002J \u0010\u0002\u001a\u00020\u00142\b\u0010\u0002\u001a\u00030¬\u0001H@ø\u0001\u0000¢\u0006\u0006\b\u0002\u0010\u0002J0\u0010\u0002\u001a\u00020\n2\u0007\u00109\u001a\u00030\u00012\u0006\u0010u\u001a\u00020\n2\u0007\u0010\u0001\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0006\b\u0002\u0010\u0001J\u001d\u0010\u0002\u001a\u00020\u00142\u0006\u0010Q\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0005\b\u0002\u0010\u0019J*\u0010\u0002\u001a\u00020\u00142\u0006\u0010Q\u001a\u00020\n2\u000e\u0010\u0002\u001a\t\u0012\u0004\u0012\u00020\u00140\u0002H\u0002¢\u0006\u0006\b\u0002\u0010\u0002J\u001a\u0010\u0002\u001a\u00020\u00052\u0006\u0010Q\u001a\u00020\nH\u0002¢\u0006\u0006\b\u0002\u0010²\u0001J>\u0010\u0002\u001a\u00020\u00142'\u00104\u001a#\b\u0001\u0012\u0004\u0012\u00020$\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140l\u0012\u0006\u0012\u0004\u0018\u00010m01¢\u0006\u0002\bEH@ø\u0001\u0000¢\u0006\u0005\b\u0002\u0010oJ)\u0010\u0002\u001a\u00020\u00142\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050 H@ø\u0001\u0000¢\u0006\u0005\b\u0002\u0010HJ7\u0010 \u0002\u001a\u00020\u00052\u0007\u0010\u0001\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u000e2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050 H\u0002¢\u0006\u0006\b \u0002\u0010¡\u0002J&\u0010¢\u0002\u001a\u00020\u00052\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050 H\u0002¢\u0006\u0006\b¢\u0002\u0010£\u0002J)\u0010¤\u0002\u001a\u00020\u00142\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050 H@ø\u0001\u0000¢\u0006\u0005\b¤\u0002\u0010HJ9\u0010¦\u0002\u001a\u00020\u00142$\u0010!\u001a \u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00140¥\u0002¢\u0006\u0002\bEH\b¢\u0006\u0006\b¦\u0002\u0010§\u0002J%\u0010¨\u0002\u001a\u00020\u0014*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020\nH\u0002¢\u0006\u0005\b¨\u0002\u0010)J%\u0010©\u0002\u001a\u00020\u0014*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020\nH\u0002¢\u0006\u0005\b©\u0002\u0010)J\u0015\u0010ª\u0002\u001a\u00020\u0014*\u00020\u0001H\u0002¢\u0006\u0005\bª\u0002\u0010\u0004J\u001f\u0010¬\u0002\u001a\u00020\n*\u00020\u00012\u0007\u0010«\u0002\u001a\u00020\nH\u0002¢\u0006\u0006\b¬\u0002\u0010­\u0002J(\u0010°\u0002\u001a\u00020\u0014*\u00020\u00012\u0007\u0010®\u0002\u001a\u00020\n2\u0007\u0010¯\u0002\u001a\u00020\nH\u0002¢\u0006\u0006\b°\u0002\u0010±\u0002J\u001d\u0010²\u0002\u001a\u00020\u0014*\u00020\u00012\u0006\u0010\u0017\u001a\u00020\nH\u0002¢\u0006\u0005\b²\u0002\u0010{J@\u0010³\u0002\u001a\u00020\u0005*\u00020\u00012\u0006\u0010Q\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u000e2\u0017\u0010R\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00140 ¢\u0006\u0002\bEH\b¢\u0006\u0006\b³\u0002\u0010´\u0002J]\u0010µ\u0002\u001a\u00020\u0014*\u00020\u00012\u0006\u0010Q\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u000e2\u0017\u0010F\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00140 ¢\u0006\u0002\bE2\u0018\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00140 ¢\u0006\u0002\bEHHø\u0001\u0000¢\u0006\u0006\bµ\u0002\u0010¶\u0002R\u001b\u0010·\u0002\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u000e¢\u0006\b\n\u0006\b·\u0002\u0010¸\u0002R\u001d\u0010\u0006\u001a\u00020\u00058\u0016X\u0004¢\u0006\u000f\n\u0005\b\u0006\u0010¹\u0002\u001a\u0006\bº\u0002\u0010â\u0001R\u0017\u0010½\u0002\u001a\u00020\n8VX\u0004¢\u0006\b\u001a\u0006\b»\u0002\u0010¼\u0002R\u0017\u0010¿\u0002\u001a\u00020\n8VX\u0004¢\u0006\b\u001a\u0006\b¾\u0002\u0010¼\u0002R0\u0010Æ\u0002\u001a\u0005\u0018\u00010À\u00022\n\u0010Á\u0002\u001a\u0005\u0018\u00010À\u00028B@BX\u000e¢\u0006\u0010\u001a\u0006\bÂ\u0002\u0010Ã\u0002\"\u0006\bÄ\u0002\u0010Å\u0002R\u0019\u0010É\u0002\u001a\u0004\u0018\u00010+8VX\u0004¢\u0006\b\u001a\u0006\bÇ\u0002\u0010È\u0002R\u0017\u0010Ê\u0002\u001a\u00020\u00058VX\u0004¢\u0006\b\u001a\u0006\bÊ\u0002\u0010â\u0001R\u0017\u0010Ë\u0002\u001a\u00020\u00058VX\u0004¢\u0006\b\u001a\u0006\bË\u0002\u0010â\u0001R\u001b\u0010Í\u0001\u001a\u0004\u0018\u00010<8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bÍ\u0001\u0010Ì\u0002R\u001b\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0002X\u0004¢\u0006\u0007\n\u0005\b\t\u0010Í\u0002R:\u0010Ò\u0002\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010l2\u000f\u0010Á\u0002\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010l8B@BX\u000e¢\u0006\u0010\u001a\u0006\bÎ\u0002\u0010Ï\u0002\"\u0006\bÐ\u0002\u0010Ñ\u0002R\u0019\u0010Ó\u0002\u001a\u00020\n8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bÓ\u0002\u0010Ô\u0002R\u001f\u0010ª\u0001\u001a\u00030Õ\u00028\u0002X\u0004¢\u0006\u000f\n\u0006\bª\u0001\u0010Ö\u0002\u0012\u0005\b×\u0002\u0010VR\u001e\u0010Ù\u0002\u001a\t\u0012\u0004\u0012\u00020\u00050Ø\u00028\u0002X\u0004¢\u0006\b\n\u0006\bÙ\u0002\u0010Ú\u0002R\u001d\u0010\u000b\u001a\u00020\n8\u0000X\u0004¢\u0006\u000f\n\u0005\b\u000b\u0010Ô\u0002\u001a\u0006\bÛ\u0002\u0010¼\u0002R\u0016\u0010Ý\u0002\u001a\u00020A8BX\u0004¢\u0006\u0007\u001a\u0005\bÜ\u0002\u0010CR2\u0010ß\u0002\u001a\u00020:2\u0007\u0010Þ\u0002\u001a\u00020:8\u0016@PX\u000e¢\u0006\u0018\n\u0006\bß\u0002\u0010à\u0002\u001a\u0006\bá\u0002\u0010â\u0002\"\u0006\bã\u0002\u0010ä\u0002R2\u0010å\u0002\u001a\u00020:2\u0007\u0010Þ\u0002\u001a\u00020:8\u0016@PX\u000e¢\u0006\u0018\n\u0006\bå\u0002\u0010à\u0002\u001a\u0006\bæ\u0002\u0010â\u0002\"\u0006\bç\u0002\u0010ä\u0002R:\u0010ê\u0002\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010l2\u000f\u0010Á\u0002\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010l8B@BX\u000e¢\u0006\u0010\u001a\u0006\bè\u0002\u0010Ï\u0002\"\u0006\bé\u0002\u0010Ñ\u0002R\u0019\u0010ë\u0002\u001a\u00020\n8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bë\u0002\u0010Ô\u0002R\u0018\u0010í\u0002\u001a\u00030ì\u00028\u0002X\u0004¢\u0006\b\n\u0006\bí\u0002\u0010î\u0002R\u001e\u0010ï\u0002\u001a\t\u0012\u0004\u0012\u00020\u00140Ø\u00028\u0002X\u0004¢\u0006\b\n\u0006\bï\u0002\u0010Ú\u0002R)\u0010ð\u0002\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140l\u0012\u0004\u0012\u00020m0 8\u0002X\u0004¢\u0006\b\n\u0006\bð\u0002\u0010ñ\u0002R\u0019\u0010ò\u0002\u001a\u00020\n8\u0002@\u0002X\u000e¢\u0006\b\n\u0006\bò\u0002\u0010Ô\u0002\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006ô\u0002"}, d2 = {"Lio/ktor/utils/io/ByteBufferChannel;", "Ljava/nio/ByteBuffer;", "content", "<init>", "(Ljava/nio/ByteBuffer;)V", "", "autoFlush", "Lio/ktor/utils/io/pool/ObjectPool;", "Lio/ktor/utils/io/internal/ReadWriteBufferState$Initial;", "pool", "", "reservedSize", "(ZLio/ktor/utils/io/pool/ObjectPool;I)V", "buffer", "Lio/ktor/utils/io/internal/RingBufferCapacity;", "capacity", "afterBufferVisited", "(Ljava/nio/ByteBuffer;Lio/ktor/utils/io/internal/RingBufferCapacity;)I", "Lkotlinx/coroutines/Job;", "job", "", "attachJob", "(Lkotlinx/coroutines/Job;)V", "n", "awaitAtLeast", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitAtLeastSuspend", "awaitClose", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitContent", "awaitFreeSpace", "min", "Lkotlin/Function1;", "block", "awaitFreeSpaceOrDelegate", "(ILkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lio/ktor/utils/io/WriterSuspendSession;", "beginWriteSession", "()Lio/ktor/utils/io/WriterSuspendSession;", "count", "bytesWrittenFromSession$ktor_io", "(Ljava/nio/ByteBuffer;Lio/ktor/utils/io/internal/RingBufferCapacity;I)V", "bytesWrittenFromSession", "", "cause", "cancel", "(Ljava/lang/Throwable;)Z", "close", "last", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "visitor", "consumeEachBufferRangeFast", "(ZLkotlin/jvm/functions/Function2;)Z", "consumed", "(I)V", "src", "", "limit", "Lio/ktor/utils/io/internal/JoiningState;", "joined", "copyDirect$ktor_io", "(Lio/ktor/utils/io/ByteBufferChannel;JLio/ktor/utils/io/internal/JoiningState;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "copyDirect", "Lio/ktor/utils/io/internal/ReadWriteBufferState;", "currentState$ktor_io", "()Lio/ktor/utils/io/internal/ReadWriteBufferState;", "currentState", "Lkotlin/ExtensionFunctionType;", "channelWriter", "delegatePrimitive", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delegateSuspend", "(Lio/ktor/utils/io/internal/JoiningState;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "max", "discard", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "discarded0", "discardSuspend", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "size", "writer", "doWritePrimitive", "(ILjava/nio/ByteBuffer;Lio/ktor/utils/io/internal/RingBufferCapacity;Lkotlin/jvm/functions/Function1;)V", "endReadSession", "()V", "written", "endWriteSession", "ensureClosedJoined", "(Lio/ktor/utils/io/internal/JoiningState;)V", "flush", "minWriteSize", "flushImpl", "getJoining$ktor_io", "()Lio/ktor/utils/io/internal/JoiningState;", "getJoining", "delegateClose", "joinFrom$ktor_io", "(Lio/ktor/utils/io/ByteBufferChannel;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "joinFrom", "joinFromSuspend", "(Lio/ktor/utils/io/ByteBufferChannel;ZLio/ktor/utils/io/internal/JoiningState;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "R", "Lio/ktor/utils/io/LookAheadSession;", "lookAhead", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "Lio/ktor/utils/io/LookAheadSuspendSession;", "Lkotlin/coroutines/Continuation;", "", "lookAheadSuspend", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "newBuffer", "()Lio/ktor/utils/io/internal/ReadWriteBufferState$Initial;", "Lio/ktor/utils/io/bits/Memory;", "destination", "destinationOffset", "offset", "peekTo-lBXzO7A", "(Ljava/nio/ByteBuffer;JJJJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "peekTo", "lockedSpace", "prepareWriteBuffer$ktor_io", "(Ljava/nio/ByteBuffer;I)V", "prepareWriteBuffer", "consumer", "read", "Lio/ktor/utils/io/core/Buffer;", "dst", "readAsMuchAsPossible", "(Lio/ktor/utils/io/core/Buffer;II)I", "(Ljava/nio/ByteBuffer;)I", "", "length", "([BII)I", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "readAvailable", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Ljava/nio/ByteBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "([BIILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(ILkotlin/jvm/functions/Function1;)I", "readAvailableSuspend", "readBlockSuspend", "readBoolean", "", "readByte", "", "readDouble", "", "readFloat", "readFully", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readFullySuspend", "rc0", "(Ljava/nio/ByteBuffer;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readInt", "readLong", "Lio/ktor/utils/io/core/ByteReadPacket;", "readPacket", "Lio/ktor/utils/io/core/BytePacketBuilder;", "builder", "readPacketSuspend", "(ILio/ktor/utils/io/core/BytePacketBuilder;Ljava/nio/ByteBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "T", "getter", "readPrimitive", "readRemaining", "readRemainingSuspend", "Lio/ktor/utils/io/ReadSession;", "readSession", "(Lkotlin/jvm/functions/Function1;)V", "", "readShort", "readSuspend", "readSuspendImpl", "readSuspendLoop", "readSuspendPredicate", "(I)Z", "Lio/ktor/utils/io/SuspendableReadSession;", "readSuspendableSession", "", "readUTF8Line", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "A", "out", "readUTF8LineTo", "(Ljava/lang/Appendable;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readUTF8LineToAscii", "readUTF8LineToUtf8Suspend", "reading", "(Lkotlin/jvm/functions/Function2;)Z", "releaseBuffer", "(Lio/ktor/utils/io/internal/ReadWriteBufferState$Initial;)V", "remainingPacket", "(J)Lio/ktor/utils/io/core/ByteReadPacket;", "skip", "atLeast", "request", "(II)Ljava/nio/ByteBuffer;", "resolveChannelInstance$ktor_io", "()Lio/ktor/utils/io/ByteBufferChannel;", "resolveChannelInstance", "current", "joining", "resolveDelegation", "(Lio/ktor/utils/io/ByteBufferChannel;Lio/ktor/utils/io/internal/JoiningState;)Lio/ktor/utils/io/ByteBufferChannel;", "restoreStateAfterRead", "restoreStateAfterWrite$ktor_io", "restoreStateAfterWrite", "resumeClosed", "(Ljava/lang/Throwable;)V", "resumeReadOp", "Lkotlin/Function0;", "exception", "(Lkotlin/jvm/functions/Function0;)V", "resumeWriteOp", "delegate", "setupDelegateTo", "(Lio/ktor/utils/io/ByteBufferChannel;Z)Lio/ktor/utils/io/internal/JoiningState;", "setupStateForRead", "()Ljava/nio/ByteBuffer;", "setupStateForWrite$ktor_io", "setupStateForWrite", "shouldResumeReadOp", "()Z", "startReadSession", "()Lio/ktor/utils/io/SuspendableReadSession;", "continuation", "suspensionForSize", "toString", "()Ljava/lang/String;", "tryCompleteJoining", "(Lio/ktor/utils/io/internal/JoiningState;)Z", "forceTermination", "tryReleaseBuffer", "(Z)Z", "tryTerminate$ktor_io", "tryTerminate", "packet", "tryWritePacketPart", "(Lio/ktor/utils/io/core/ByteReadPacket;)I", "tryWriteSuspend$ktor_io", "tryWriteSuspend", "write", "writeAsMuchAsPossible", "(Lio/ktor/utils/io/core/Buffer;)I", "writeAvailable", "writeAvailableSuspend", "b", "writeByte", "(BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "d", "writeDouble", "(DLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "f", "writeFloat", "(FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "memory", "startIndex", "endIndex", "writeFully-JT6ljtQ", "(Ljava/nio/ByteBuffer;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeFully", "(Lio/ktor/utils/io/core/Buffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeFullySuspend", "i", "writeInt", "l", "writeLong", "writePacket", "(Lio/ktor/utils/io/core/ByteReadPacket;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writePacketSuspend", "bufferWriter", "writePrimitive", "(ILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "s", "writeShort", "(SLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeSuspend", "Lkotlinx/coroutines/CancellableContinuation;", "c", "writeSuspendBlock", "(ILkotlinx/coroutines/CancellableContinuation;)V", "writeSuspendPredicate", "writeSuspendSession", "writeWhile", "writeWhileLoop", "(Ljava/nio/ByteBuffer;Lio/ktor/utils/io/internal/RingBufferCapacity;Lkotlin/jvm/functions/Function1;)Z", "writeWhileNoSuspend", "(Lkotlin/jvm/functions/Function1;)Z", "writeWhileSuspend", "Lkotlin/Function3;", "writing", "(Lkotlin/jvm/functions/Function3;)V", "bytesRead", "bytesWritten", "carry", "idx", "carryIndex", "(Ljava/nio/ByteBuffer;I)I", "position", "available", "prepareBuffer", "(Ljava/nio/ByteBuffer;II)V", "rollBytes", "tryWritePrimitive", "(Ljava/nio/ByteBuffer;ILio/ktor/utils/io/internal/RingBufferCapacity;Lkotlin/jvm/functions/Function1;)Z", "writeSuspendPrimitive", "(Ljava/nio/ByteBuffer;ILio/ktor/utils/io/internal/RingBufferCapacity;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "attachedJob", "Lkotlinx/coroutines/Job;", "Z", "getAutoFlush", "getAvailableForRead", "()I", "availableForRead", "getAvailableForWrite", "availableForWrite", "Lio/ktor/utils/io/internal/ClosedElement;", "value", "getClosed", "()Lio/ktor/utils/io/internal/ClosedElement;", "setClosed", "(Lio/ktor/utils/io/internal/ClosedElement;)V", "closed", "getClosedCause", "()Ljava/lang/Throwable;", "closedCause", "isClosedForRead", "isClosedForWrite", "Lio/ktor/utils/io/internal/JoiningState;", "Lio/ktor/utils/io/pool/ObjectPool;", "getReadOp", "()Lkotlin/coroutines/Continuation;", "setReadOp", "(Lkotlin/coroutines/Continuation;)V", "readOp", "readPosition", "I", "Lio/ktor/utils/io/internal/ReadSessionImpl;", "Lio/ktor/utils/io/internal/ReadSessionImpl;", "getReadSession$annotations", "Lio/ktor/utils/io/internal/CancellableReusableContinuation;", "readSuspendContinuationCache", "Lio/ktor/utils/io/internal/CancellableReusableContinuation;", "getReservedSize$ktor_io", "getState", "state", "<set-?>", "totalBytesRead", "J", "getTotalBytesRead", "()J", "setTotalBytesRead$ktor_io", "(J)V", "totalBytesWritten", "getTotalBytesWritten", "setTotalBytesWritten$ktor_io", "getWriteOp", "setWriteOp", "writeOp", "writePosition", "Lio/ktor/utils/io/internal/WriteSessionImpl;", "writeSession", "Lio/ktor/utils/io/internal/WriteSessionImpl;", "writeSuspendContinuationCache", "writeSuspension", "Lkotlin/jvm/functions/Function1;", "writeSuspensionSize", "Companion", "ktor-io", "Lio/ktor/utils/io/ByteChannel;", "Lio/ktor/utils/io/ByteReadChannel;", "Lio/ktor/utils/io/ByteWriteChannel;", "Lio/ktor/utils/io/HasReadSession;", "Lio/ktor/utils/io/HasWriteSession;"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ByteBufferChannel.kt */
public class ByteBufferChannel implements ByteChannel, ByteReadChannel, ByteWriteChannel, LookAheadSuspendSession, HasReadSession, HasWriteSession {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int ReservedLongIndex = -8;
    private static final /* synthetic */ AtomicReferenceFieldUpdater _closed$FU;
    private static final /* synthetic */ AtomicReferenceFieldUpdater _readOp$FU;
    private static final /* synthetic */ AtomicReferenceFieldUpdater _state$FU;
    static final /* synthetic */ AtomicReferenceFieldUpdater _writeOp$FU;
    private volatile /* synthetic */ Object _closed;
    private volatile /* synthetic */ Object _readOp;
    private volatile /* synthetic */ Object _state;
    volatile /* synthetic */ Object _writeOp;
    /* access modifiers changed from: private */
    public volatile Job attachedJob;
    private final boolean autoFlush;
    private volatile JoiningState joining;
    private final ObjectPool<ReadWriteBufferState.Initial> pool;
    private int readPosition;
    /* access modifiers changed from: private */
    public final ReadSessionImpl readSession;
    private final CancellableReusableContinuation<Boolean> readSuspendContinuationCache;
    private final int reservedSize;
    private volatile long totalBytesRead;
    private volatile long totalBytesWritten;
    private int writePosition;
    private final WriteSessionImpl writeSession;
    private final CancellableReusableContinuation<Unit> writeSuspendContinuationCache;
    private final Function1<Continuation<? super Unit>, Object> writeSuspension;
    /* access modifiers changed from: private */
    public volatile int writeSuspensionSize;

    static {
        Class<ByteBufferChannel> cls = ByteBufferChannel.class;
        _state$FU = AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "_state");
        _closed$FU = AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "_closed");
        _readOp$FU = AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "_readOp");
        _writeOp$FU = AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "_writeOp");
    }

    private static /* synthetic */ void getReadSession$annotations() {
    }

    public Object awaitContent(Continuation<? super Unit> continuation) {
        return awaitContent$suspendImpl(this, continuation);
    }

    public Object awaitFreeSpace(Continuation<? super Unit> continuation) {
        return awaitFreeSpace$suspendImpl(this, continuation);
    }

    public Object discard(long j, Continuation<? super Long> continuation) {
        return discard$suspendImpl(this, j, continuation);
    }

    @Deprecated(message = "Use read { } instead.")
    public <R> Object lookAheadSuspend(Function2<? super LookAheadSuspendSession, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super R> continuation) {
        return lookAheadSuspend$suspendImpl(this, function2, continuation);
    }

    /* renamed from: peekTo-lBXzO7A  reason: not valid java name */
    public Object m148peekTolBXzO7A(ByteBuffer byteBuffer, long j, long j2, long j3, long j4, Continuation<? super Long> continuation) {
        return m146peekTolBXzO7A$suspendImpl(this, byteBuffer, j, j2, j3, j4, continuation);
    }

    public Object read(int i, Function1<? super ByteBuffer, Unit> function1, Continuation<? super Unit> continuation) {
        return read$suspendImpl(this, i, function1, continuation);
    }

    public Object readAvailable(ChunkBuffer chunkBuffer, Continuation<? super Integer> continuation) {
        return readAvailable$suspendImpl(this, chunkBuffer, continuation);
    }

    public Object readAvailable(ByteBuffer byteBuffer, Continuation<? super Integer> continuation) {
        return readAvailable$suspendImpl(this, byteBuffer, continuation);
    }

    public Object readAvailable(byte[] bArr, int i, int i2, Continuation<? super Integer> continuation) {
        return readAvailable$suspendImpl(this, bArr, i, i2, continuation);
    }

    public Object readFully(ChunkBuffer chunkBuffer, int i, Continuation<? super Unit> continuation) {
        return readFully$suspendImpl(this, chunkBuffer, i, continuation);
    }

    public Object readPacket(int i, Continuation<? super ByteReadPacket> continuation) {
        return readPacket$suspendImpl(this, i, continuation);
    }

    public Object readRemaining(long j, Continuation<? super ByteReadPacket> continuation) {
        return readRemaining$suspendImpl(this, j, continuation);
    }

    @Deprecated(message = "Use read { } instead.")
    public Object readSuspendableSession(Function2<? super SuspendableReadSession, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        return readSuspendableSession$suspendImpl(this, function2, continuation);
    }

    public Object readUTF8Line(int i, Continuation<? super String> continuation) {
        return readUTF8Line$suspendImpl(this, i, continuation);
    }

    public <A extends Appendable> Object readUTF8LineTo(A a, int i, Continuation<? super Boolean> continuation) {
        return readUTF8LineToAscii(a, i, continuation);
    }

    public Object write(int i, Function1<? super ByteBuffer, Unit> function1, Continuation<? super Unit> continuation) {
        return write$suspendImpl(this, i, function1, continuation);
    }

    public Object writeAvailable(ChunkBuffer chunkBuffer, Continuation<? super Integer> continuation) {
        return writeAvailable$suspendImpl(this, chunkBuffer, continuation);
    }

    public Object writeAvailable(ByteBuffer byteBuffer, Continuation<? super Integer> continuation) {
        return writeAvailable$suspendImpl(this, byteBuffer, continuation);
    }

    public Object writeAvailable(byte[] bArr, int i, int i2, Continuation<? super Integer> continuation) {
        return writeAvailable$suspendImpl(this, bArr, i, i2, continuation);
    }

    public Object writeByte(byte b, Continuation<? super Unit> continuation) {
        return writeByte$suspendImpl(this, b, continuation);
    }

    public Object writeDouble(double d, Continuation<? super Unit> continuation) {
        return writeDouble$suspendImpl(this, d, continuation);
    }

    public Object writeFloat(float f, Continuation<? super Unit> continuation) {
        return writeFloat$suspendImpl(this, f, continuation);
    }

    public Object writeFully(Buffer buffer, Continuation<? super Unit> continuation) {
        return writeFully$suspendImpl(this, buffer, continuation);
    }

    public Object writeFully(ByteBuffer byteBuffer, Continuation<? super Unit> continuation) {
        return writeFully$suspendImpl(this, byteBuffer, continuation);
    }

    public Object writeFully(byte[] bArr, int i, int i2, Continuation<? super Unit> continuation) {
        return writeFully$suspendImpl(this, bArr, i, i2, continuation);
    }

    /* renamed from: writeFully-JT6ljtQ  reason: not valid java name */
    public Object m149writeFullyJT6ljtQ(ByteBuffer byteBuffer, int i, int i2, Continuation<? super Unit> continuation) {
        return m147writeFullyJT6ljtQ$suspendImpl(this, byteBuffer, i, i2, continuation);
    }

    public Object writeInt(int i, Continuation<? super Unit> continuation) {
        return writeInt$suspendImpl(this, i, continuation);
    }

    public Object writeLong(long j, Continuation<? super Unit> continuation) {
        return writeLong$suspendImpl(this, j, continuation);
    }

    public Object writePacket(ByteReadPacket byteReadPacket, Continuation<? super Unit> continuation) {
        return writePacket$suspendImpl(this, byteReadPacket, continuation);
    }

    public Object writeShort(short s, Continuation<? super Unit> continuation) {
        return writeShort$suspendImpl(this, s, continuation);
    }

    @Deprecated(message = "Use write { } instead.")
    public Object writeSuspendSession(Function2<? super WriterSuspendSession, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        return writeSuspendSession$suspendImpl(this, function2, continuation);
    }

    public Object writeWhile(Function1<? super ByteBuffer, Boolean> function1, Continuation<? super Unit> continuation) {
        return writeWhile$suspendImpl(this, function1, continuation);
    }

    public ByteBufferChannel(boolean z, ObjectPool<ReadWriteBufferState.Initial> objectPool, int i) {
        Intrinsics.checkNotNullParameter(objectPool, "pool");
        this.autoFlush = z;
        this.pool = objectPool;
        this.reservedSize = i;
        this._state = ReadWriteBufferState.IdleEmpty.INSTANCE;
        this._closed = null;
        this._readOp = null;
        this._writeOp = null;
        this.readSession = new ReadSessionImpl(this);
        this.writeSession = new WriteSessionImpl(this);
        this.readSuspendContinuationCache = new CancellableReusableContinuation<>();
        this.writeSuspendContinuationCache = new CancellableReusableContinuation<>();
        this.writeSuspension = new ByteBufferChannel$writeSuspension$1(this);
    }

    public boolean getAutoFlush() {
        return this.autoFlush;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ByteBufferChannel(boolean z, ObjectPool<ReadWriteBufferState.Initial> objectPool, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, (i2 & 2) != 0 ? ObjectPoolKt.getBufferObjectPool() : objectPool, (i2 & 4) != 0 ? 8 : i);
    }

    public final int getReservedSize$ktor_io() {
        return this.reservedSize;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ByteBufferChannel(ByteBuffer byteBuffer) {
        this(false, ObjectPoolKt.getBufferObjectNoPool(), 0);
        Intrinsics.checkNotNullParameter(byteBuffer, "content");
        ByteBuffer slice = byteBuffer.slice();
        Intrinsics.checkNotNullExpressionValue(slice, "content.slice()");
        ReadWriteBufferState.Initial initial = new ReadWriteBufferState.Initial(slice, 0);
        initial.capacity.resetForRead();
        this._state = initial.startWriting$ktor_io();
        restoreStateAfterWrite$ktor_io();
        ByteWriteChannelKt.close(this);
        tryTerminate$ktor_io();
    }

    private final ReadWriteBufferState getState() {
        return (ReadWriteBufferState) this._state;
    }

    /* access modifiers changed from: private */
    public final ClosedElement getClosed() {
        return (ClosedElement) this._closed;
    }

    private final void setClosed(ClosedElement closedElement) {
        this._closed = closedElement;
    }

    private final Continuation<Boolean> getReadOp() {
        return (Continuation) this._readOp;
    }

    private final void setReadOp(Continuation<? super Boolean> continuation) {
        this._readOp = continuation;
    }

    /* access modifiers changed from: private */
    public final Continuation<Unit> getWriteOp() {
        return (Continuation) this._writeOp;
    }

    private final void setWriteOp(Continuation<? super Unit> continuation) {
        this._writeOp = continuation;
    }

    public final ReadWriteBufferState currentState$ktor_io() {
        return getState();
    }

    public final JoiningState getJoining$ktor_io() {
        return this.joining;
    }

    @Deprecated(message = "\n    We're migrating to the new kotlinx-io library.\n    This declaration is deprecated and will be removed in Ktor 4.0.0\n    If you have any problems with migration, please contact us in \n    https://youtrack.jetbrains.com/issue/KTOR-6030/Migrate-to-new-kotlinx.io-library\n    ")
    public void attachJob(Job job) {
        Intrinsics.checkNotNullParameter(job, "job");
        Job job2 = this.attachedJob;
        if (job2 != null) {
            Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
        }
        this.attachedJob = job;
        Job.DefaultImpls.invokeOnCompletion$default(job, true, false, new ByteBufferChannel$attachJob$1(this), 2, (Object) null);
    }

    public int getAvailableForRead() {
        return getState().capacity._availableForRead$internal;
    }

    public int getAvailableForWrite() {
        return getState().capacity._availableForWrite$internal;
    }

    public boolean isClosedForRead() {
        return getState() == ReadWriteBufferState.Terminated.INSTANCE && getClosed() != null;
    }

    public boolean isClosedForWrite() {
        return getClosed() != null;
    }

    public long getTotalBytesRead() {
        return this.totalBytesRead;
    }

    public void setTotalBytesRead$ktor_io(long j) {
        this.totalBytesRead = j;
    }

    public long getTotalBytesWritten() {
        return this.totalBytesWritten;
    }

    public void setTotalBytesWritten$ktor_io(long j) {
        this.totalBytesWritten = j;
    }

    public Throwable getClosedCause() {
        ClosedElement closed = getClosed();
        if (closed != null) {
            return closed.getCause();
        }
        return null;
    }

    public boolean close(Throwable th) {
        ClosedElement closedElement;
        JoiningState joiningState;
        if (getClosed() != null) {
            return false;
        }
        if (th == null) {
            closedElement = ClosedElement.Companion.getEmptyCause();
        } else {
            closedElement = new ClosedElement(th);
        }
        getState().capacity.flush();
        if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_closed$FU, this, (Object) null, closedElement)) {
            return false;
        }
        getState().capacity.flush();
        if (getState().capacity.isEmpty() || th != null) {
            tryTerminate$ktor_io();
        }
        resumeClosed(th);
        if (getState() == ReadWriteBufferState.Terminated.INSTANCE && (joiningState = this.joining) != null) {
            ensureClosedJoined(joiningState);
        }
        if (th != null) {
            Job job = this.attachedJob;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            this.readSuspendContinuationCache.close(th);
            this.writeSuspendContinuationCache.close(th);
            return true;
        }
        this.writeSuspendContinuationCache.close((Throwable) new ClosedWriteChannelException(ByteBufferChannelKt.DEFAULT_CLOSE_MESSAGE));
        this.readSuspendContinuationCache.close(Boolean.valueOf(getState().capacity.flush()));
        return true;
    }

    public boolean cancel(Throwable th) {
        if (th == null) {
            th = new CancellationException("Channel has been cancelled");
        }
        return close(th);
    }

    /* access modifiers changed from: private */
    public final void flushImpl(int i) {
        ReadWriteBufferState state;
        ByteBufferChannel delegatedTo;
        JoiningState joiningState = this.joining;
        if (!(joiningState == null || (delegatedTo = joiningState.getDelegatedTo()) == null)) {
            delegatedTo.flush();
        }
        do {
            state = getState();
            if (state != ReadWriteBufferState.Terminated.INSTANCE) {
                state.capacity.flush();
            } else {
                return;
            }
        } while (state != getState());
        int i2 = state.capacity._availableForWrite$internal;
        if (state.capacity._availableForRead$internal >= 1) {
            resumeReadOp();
        }
        JoiningState joiningState2 = this.joining;
        if (i2 < i) {
            return;
        }
        if (joiningState2 == null || getState() == ReadWriteBufferState.Terminated.INSTANCE) {
            resumeWriteOp();
        }
    }

    public void flush() {
        flushImpl(1);
    }

    public final void prepareWriteBuffer$ktor_io(ByteBuffer byteBuffer, int i) {
        Intrinsics.checkNotNullParameter(byteBuffer, "buffer");
        prepareBuffer(byteBuffer, this.writePosition, i);
    }

    private final void prepareBuffer(ByteBuffer byteBuffer, int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        } else if (i2 >= 0) {
            byteBuffer.limit(RangesKt.coerceAtMost(i2 + i, byteBuffer.capacity() - this.reservedSize));
            byteBuffer.position(i);
        } else {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public final ByteBuffer setupStateForWrite$ktor_io() {
        Object obj;
        ReadWriteBufferState readWriteBufferState;
        ReadWriteBufferState readWriteBufferState2;
        Continuation<Unit> writeOp = getWriteOp();
        if (writeOp == null) {
            ReadWriteBufferState readWriteBufferState3 = null;
            ReadWriteBufferState.Initial initial = null;
            do {
                obj = this._state;
                readWriteBufferState = (ReadWriteBufferState) obj;
                if (this.joining != null) {
                    if (initial != null) {
                        releaseBuffer(initial);
                    }
                    return null;
                } else if (getClosed() != null) {
                    if (initial != null) {
                        releaseBuffer(initial);
                    }
                    ClosedElement closed = getClosed();
                    Intrinsics.checkNotNull(closed);
                    Void unused = ByteBufferChannelKt.rethrowClosed(closed.getSendException());
                    throw new KotlinNothingValueException();
                } else if (readWriteBufferState == ReadWriteBufferState.IdleEmpty.INSTANCE) {
                    if (initial == null) {
                        initial = newBuffer();
                    }
                    readWriteBufferState2 = initial.startWriting$ktor_io();
                } else if (readWriteBufferState == ReadWriteBufferState.Terminated.INSTANCE) {
                    if (initial != null) {
                        releaseBuffer(initial);
                    }
                    if (this.joining != null) {
                        return null;
                    }
                    ClosedElement closed2 = getClosed();
                    Intrinsics.checkNotNull(closed2);
                    Void unused2 = ByteBufferChannelKt.rethrowClosed(closed2.getSendException());
                    throw new KotlinNothingValueException();
                } else {
                    readWriteBufferState2 = readWriteBufferState.startWriting$ktor_io();
                }
            } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, obj, readWriteBufferState2));
            if (getClosed() == null) {
                ByteBuffer writeBuffer = readWriteBufferState2.getWriteBuffer();
                if (initial != null) {
                    if (readWriteBufferState == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("old");
                    } else {
                        readWriteBufferState3 = readWriteBufferState;
                    }
                    if (readWriteBufferState3 != ReadWriteBufferState.IdleEmpty.INSTANCE) {
                        releaseBuffer(initial);
                    }
                }
                prepareBuffer(writeBuffer, this.writePosition, readWriteBufferState2.capacity._availableForWrite$internal);
                return writeBuffer;
            }
            restoreStateAfterWrite$ktor_io();
            tryTerminate$ktor_io();
            ClosedElement closed3 = getClosed();
            Intrinsics.checkNotNull(closed3);
            Void unused3 = ByteBufferChannelKt.rethrowClosed(closed3.getSendException());
            throw new KotlinNothingValueException();
        }
        throw new IllegalStateException("Write operation is already in progress: " + writeOp);
    }

    private final JoiningState setupDelegateTo(ByteBufferChannel byteBufferChannel, boolean z) {
        if (this != byteBufferChannel) {
            JoiningState joiningState = new JoiningState(byteBufferChannel, z);
            this.joining = joiningState;
            ClosedElement closed = getClosed();
            if (closed == null) {
                flush();
                return joiningState;
            }
            if (closed.getCause() != null) {
                byteBufferChannel.close(closed.getCause());
            } else if (!z || getState() != ReadWriteBufferState.Terminated.INSTANCE) {
                byteBufferChannel.flush();
            } else {
                ByteWriteChannelKt.close(byteBufferChannel);
            }
            return joiningState;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    private final boolean tryCompleteJoining(JoiningState joiningState) {
        if (!tryReleaseBuffer(true)) {
            return false;
        }
        ensureClosedJoined(joiningState);
        Continuation continuation = (Continuation) _readOp$FU.getAndSet(this, (Object) null);
        if (continuation != null) {
            Result.Companion companion = Result.Companion;
            continuation.resumeWith(Result.m1774constructorimpl(ResultKt.createFailure(new IllegalStateException("Joining is in progress"))));
        }
        resumeWriteOp();
        return true;
    }

    public final boolean tryTerminate$ktor_io() {
        if (getClosed() == null || !tryReleaseBuffer(false)) {
            return false;
        }
        JoiningState joiningState = this.joining;
        if (joiningState != null) {
            ensureClosedJoined(joiningState);
        }
        resumeReadOp();
        resumeWriteOp();
        return true;
    }

    private final int carryIndex(ByteBuffer byteBuffer, int i) {
        return i >= byteBuffer.capacity() - this.reservedSize ? i - (byteBuffer.capacity() - this.reservedSize) : i;
    }

    private final void writing(Function3<? super ByteBufferChannel, ? super ByteBuffer, ? super RingBufferCapacity, Unit> function3) {
        ByteBufferChannel byteBufferChannel;
        JoiningState joiningState = this.joining;
        if (joiningState == null || (byteBufferChannel = resolveDelegation(this, joiningState)) == null) {
            byteBufferChannel = this;
        }
        ByteBuffer byteBuffer = byteBufferChannel.setupStateForWrite$ktor_io();
        if (byteBuffer != null) {
            RingBufferCapacity ringBufferCapacity = byteBufferChannel.getState().capacity;
            long totalBytesWritten2 = byteBufferChannel.getTotalBytesWritten();
            try {
                ClosedElement closed = byteBufferChannel.getClosed();
                if (closed == null) {
                    function3.invoke(byteBufferChannel, byteBuffer, ringBufferCapacity);
                } else {
                    Void unused = ByteBufferChannelKt.rethrowClosed(closed.getSendException());
                    throw new KotlinNothingValueException();
                }
            } finally {
                InlineMarker.finallyStart(1);
                if (ringBufferCapacity.isFull() || byteBufferChannel.getAutoFlush()) {
                    byteBufferChannel.flush();
                }
                if (byteBufferChannel != this) {
                    setTotalBytesWritten$ktor_io(getTotalBytesWritten() + (byteBufferChannel.getTotalBytesWritten() - totalBytesWritten2));
                }
                byteBufferChannel.restoreStateAfterWrite$ktor_io();
                byteBufferChannel.tryTerminate$ktor_io();
                InlineMarker.finallyEnd(1);
            }
        }
    }

    private final boolean reading(Function2<? super ByteBuffer, ? super RingBufferCapacity, Boolean> function2) {
        ByteBuffer byteBuffer = setupStateForRead();
        if (byteBuffer == null) {
            return false;
        }
        RingBufferCapacity ringBufferCapacity = getState().capacity;
        try {
            if (ringBufferCapacity._availableForRead$internal == 0) {
                return false;
            }
            boolean booleanValue = function2.invoke(byteBuffer, ringBufferCapacity).booleanValue();
            InlineMarker.finallyStart(1);
            restoreStateAfterRead();
            tryTerminate$ktor_io();
            InlineMarker.finallyEnd(1);
            return booleanValue;
        } finally {
            InlineMarker.finallyStart(1);
            restoreStateAfterRead();
            tryTerminate$ktor_io();
            InlineMarker.finallyEnd(1);
        }
    }

    public final Object readFully(byte[] bArr, int i, int i2, Continuation<? super Unit> continuation) {
        int readAsMuchAsPossible = readAsMuchAsPossible(bArr, i, i2);
        if (readAsMuchAsPossible >= i2) {
            return Unit.INSTANCE;
        }
        Object readFullySuspend = readFullySuspend(bArr, i + readAsMuchAsPossible, i2 - readAsMuchAsPossible, continuation);
        return readFullySuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? readFullySuspend : Unit.INSTANCE;
    }

    public final Object readFully(ByteBuffer byteBuffer, Continuation<? super Integer> continuation) {
        int readAsMuchAsPossible = readAsMuchAsPossible(byteBuffer);
        if (!byteBuffer.hasRemaining()) {
            return Boxing.boxInt(readAsMuchAsPossible);
        }
        return readFullySuspend(byteBuffer, readAsMuchAsPossible, continuation);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object readFullySuspend(java.nio.ByteBuffer r6, int r7, kotlin.coroutines.Continuation<? super java.lang.Integer> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof io.ktor.utils.io.ByteBufferChannel$readFullySuspend$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ktor.utils.io.ByteBufferChannel$readFullySuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel$readFullySuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$readFullySuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readFullySuspend$1
            r0.<init>(r5, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003c
            if (r2 != r3) goto L_0x0034
            int r6 = r0.I$0
            java.lang.Object r7 = r0.L$1
            java.nio.ByteBuffer r7 = (java.nio.ByteBuffer) r7
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0058
        L_0x0034:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x003c:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r5
        L_0x0040:
            boolean r8 = r6.hasRemaining()
            if (r8 == 0) goto L_0x0086
            r0.L$0 = r2
            r0.L$1 = r6
            r0.I$0 = r7
            r0.label = r3
            java.lang.Object r8 = r2.readSuspend(r3, r0)
            if (r8 != r1) goto L_0x0055
            return r1
        L_0x0055:
            r4 = r7
            r7 = r6
            r6 = r4
        L_0x0058:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L_0x0069
            int r8 = r2.readAsMuchAsPossible(r7)
            int r6 = r6 + r8
            r4 = r7
            r7 = r6
            r6 = r4
            goto L_0x0040
        L_0x0069:
            kotlinx.coroutines.channels.ClosedReceiveChannelException r6 = new kotlinx.coroutines.channels.ClosedReceiveChannelException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r0 = "Unexpected EOF: expected "
            r8.<init>(r0)
            int r7 = r7.remaining()
            r8.append(r7)
            java.lang.String r7 = " more bytes"
            r8.append(r7)
            java.lang.String r7 = r8.toString()
            r6.<init>(r7)
            throw r6
        L_0x0086:
            java.lang.Integer r6 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r7)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readFullySuspend(java.nio.ByteBuffer, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object readFully$suspendImpl(ByteBufferChannel byteBufferChannel, ChunkBuffer chunkBuffer, int i, Continuation<? super Unit> continuation) {
        int readAsMuchAsPossible$default = readAsMuchAsPossible$default(byteBufferChannel, chunkBuffer, 0, i, 2, (Object) null);
        if (readAsMuchAsPossible$default == i) {
            return Unit.INSTANCE;
        }
        Object readFullySuspend = byteBufferChannel.readFullySuspend(chunkBuffer, i - readAsMuchAsPossible$default, continuation);
        return readFullySuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? readFullySuspend : Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object readFullySuspend(io.ktor.utils.io.core.internal.ChunkBuffer r13, int r14, kotlin.coroutines.Continuation<? super kotlin.Unit> r15) {
        /*
            r12 = this;
            boolean r0 = r15 instanceof io.ktor.utils.io.ByteBufferChannel$readFullySuspend$2
            if (r0 == 0) goto L_0x0014
            r0 = r15
            io.ktor.utils.io.ByteBufferChannel$readFullySuspend$2 r0 = (io.ktor.utils.io.ByteBufferChannel$readFullySuspend$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$readFullySuspend$2 r0 = new io.ktor.utils.io.ByteBufferChannel$readFullySuspend$2
            r0.<init>(r12, r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003f
            if (r2 != r3) goto L_0x0037
            int r13 = r0.I$1
            int r14 = r0.I$0
            java.lang.Object r2 = r0.L$1
            io.ktor.utils.io.core.internal.ChunkBuffer r2 = (io.ktor.utils.io.core.internal.ChunkBuffer) r2
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r4 = (io.ktor.utils.io.ByteBufferChannel) r4
            kotlin.ResultKt.throwOnFailure(r15)
            r10 = r4
            goto L_0x0069
        L_0x0037:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L_0x003f:
            kotlin.ResultKt.throwOnFailure(r15)
            r15 = 0
            r4 = r12
        L_0x0044:
            r2 = r13
            io.ktor.utils.io.core.Buffer r2 = (io.ktor.utils.io.core.Buffer) r2
            int r5 = r2.getLimit()
            int r2 = r2.getWritePosition()
            if (r5 <= r2) goto L_0x009b
            if (r15 >= r14) goto L_0x009b
            r0.L$0 = r4
            r0.L$1 = r13
            r0.I$0 = r14
            r0.I$1 = r15
            r0.label = r3
            java.lang.Object r2 = r4.readSuspend(r3, r0)
            if (r2 != r1) goto L_0x0064
            return r1
        L_0x0064:
            r10 = r4
            r11 = r2
            r2 = r13
            r13 = r15
            r15 = r11
        L_0x0069:
            java.lang.Boolean r15 = (java.lang.Boolean) r15
            boolean r15 = r15.booleanValue()
            if (r15 == 0) goto L_0x0081
            r5 = r2
            io.ktor.utils.io.core.Buffer r5 = (io.ktor.utils.io.core.Buffer) r5
            int r7 = r14 - r13
            r8 = 2
            r9 = 0
            r6 = 0
            r4 = r10
            int r15 = readAsMuchAsPossible$default(r4, r5, r6, r7, r8, r9)
            int r15 = r15 + r13
            r13 = r2
            goto L_0x0044
        L_0x0081:
            kotlinx.coroutines.channels.ClosedReceiveChannelException r15 = new kotlinx.coroutines.channels.ClosedReceiveChannelException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Unexpected EOF: expected "
            r0.<init>(r1)
            int r14 = r14 - r13
            r0.append(r14)
            java.lang.String r13 = " more bytes"
            r0.append(r13)
            java.lang.String r13 = r0.toString()
            r15.<init>(r13)
            throw r15
        L_0x009b:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readFullySuspend(io.ktor.utils.io.core.internal.ChunkBuffer, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object readFullySuspend(byte[] r8, int r9, int r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r7 = this;
            boolean r0 = r11 instanceof io.ktor.utils.io.ByteBufferChannel$readFullySuspend$3
            if (r0 == 0) goto L_0x0014
            r0 = r11
            io.ktor.utils.io.ByteBufferChannel$readFullySuspend$3 r0 = (io.ktor.utils.io.ByteBufferChannel$readFullySuspend$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$readFullySuspend$3 r0 = new io.ktor.utils.io.ByteBufferChannel$readFullySuspend$3
            r0.<init>(r7, r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0040
            if (r2 != r3) goto L_0x0038
            int r8 = r0.I$2
            int r9 = r0.I$1
            int r10 = r0.I$0
            java.lang.Object r2 = r0.L$1
            byte[] r2 = (byte[]) r2
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r4 = (io.ktor.utils.io.ByteBufferChannel) r4
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x005f
        L_0x0038:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0040:
            kotlin.ResultKt.throwOnFailure(r11)
            r11 = 0
            r4 = r7
        L_0x0045:
            r0.L$0 = r4
            r0.L$1 = r8
            r0.I$0 = r9
            r0.I$1 = r10
            r0.I$2 = r11
            r0.label = r3
            java.lang.Object r2 = r4.readSuspend(r3, r0)
            if (r2 != r1) goto L_0x0058
            return r1
        L_0x0058:
            r5 = r2
            r2 = r8
            r8 = r11
            r11 = r5
            r6 = r10
            r10 = r9
            r9 = r6
        L_0x005f:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L_0x0077
            int r10 = r10 + r8
            int r8 = r9 - r8
            int r11 = r4.readAsMuchAsPossible((byte[]) r2, (int) r10, (int) r8)
            if (r11 < r8) goto L_0x0073
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x0073:
            r9 = r10
            r10 = r8
            r8 = r2
            goto L_0x0045
        L_0x0077:
            kotlinx.coroutines.channels.ClosedReceiveChannelException r8 = new kotlinx.coroutines.channels.ClosedReceiveChannelException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = "Unexpected EOF: expected "
            r10.<init>(r11)
            r10.append(r9)
            java.lang.String r9 = " more bytes"
            r10.append(r9)
            java.lang.String r9 = r10.toString()
            r8.<init>(r9)
            goto L_0x0091
        L_0x0090:
            throw r8
        L_0x0091:
            goto L_0x0090
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readFullySuspend(byte[], int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object readAvailable$suspendImpl(ByteBufferChannel byteBufferChannel, byte[] bArr, int i, int i2, Continuation<? super Integer> continuation) {
        int readAsMuchAsPossible = byteBufferChannel.readAsMuchAsPossible(bArr, i, i2);
        if (readAsMuchAsPossible == 0 && byteBufferChannel.getClosed() != null) {
            readAsMuchAsPossible = byteBufferChannel.getState().capacity.flush() ? byteBufferChannel.readAsMuchAsPossible(bArr, i, i2) : -1;
        } else if (readAsMuchAsPossible <= 0 && i2 != 0) {
            return byteBufferChannel.readAvailableSuspend(bArr, i, i2, continuation);
        }
        return Boxing.boxInt(readAsMuchAsPossible);
    }

    static /* synthetic */ Object readAvailable$suspendImpl(ByteBufferChannel byteBufferChannel, ByteBuffer byteBuffer, Continuation<? super Integer> continuation) {
        int readAsMuchAsPossible = byteBufferChannel.readAsMuchAsPossible(byteBuffer);
        if (readAsMuchAsPossible == 0 && byteBufferChannel.getClosed() != null) {
            readAsMuchAsPossible = byteBufferChannel.getState().capacity.flush() ? byteBufferChannel.readAsMuchAsPossible(byteBuffer) : -1;
        } else if (readAsMuchAsPossible <= 0 && byteBuffer.hasRemaining()) {
            return byteBufferChannel.readAvailableSuspend(byteBuffer, continuation);
        }
        return Boxing.boxInt(readAsMuchAsPossible);
    }

    static /* synthetic */ Object readAvailable$suspendImpl(ByteBufferChannel byteBufferChannel, ChunkBuffer chunkBuffer, Continuation<? super Integer> continuation) {
        Buffer buffer = chunkBuffer;
        int readAsMuchAsPossible$default = readAsMuchAsPossible$default(byteBufferChannel, buffer, 0, 0, 6, (Object) null);
        if (readAsMuchAsPossible$default == 0 && byteBufferChannel.getClosed() != null) {
            readAsMuchAsPossible$default = byteBufferChannel.getState().capacity.flush() ? readAsMuchAsPossible$default(byteBufferChannel, buffer, 0, 0, 6, (Object) null) : -1;
        } else if (readAsMuchAsPossible$default <= 0 && buffer.getLimit() > buffer.getWritePosition()) {
            return byteBufferChannel.readAvailableSuspend(chunkBuffer, continuation);
        }
        return Boxing.boxInt(readAsMuchAsPossible$default);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object readAvailableSuspend(byte[] r6, int r7, int r8, kotlin.coroutines.Continuation<? super java.lang.Integer> r9) {
        /*
            r5 = this;
            boolean r0 = r9 instanceof io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$1
            r0.<init>(r5, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0045
            if (r2 == r4) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0076
        L_0x002d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0035:
            int r8 = r0.I$1
            int r7 = r0.I$0
            java.lang.Object r6 = r0.L$1
            byte[] r6 = (byte[]) r6
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x005a
        L_0x0045:
            kotlin.ResultKt.throwOnFailure(r9)
            r0.L$0 = r5
            r0.L$1 = r6
            r0.I$0 = r7
            r0.I$1 = r8
            r0.label = r4
            java.lang.Object r9 = r5.readSuspend(r4, r0)
            if (r9 != r1) goto L_0x0059
            return r1
        L_0x0059:
            r2 = r5
        L_0x005a:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 != 0) goto L_0x0068
            r6 = -1
            java.lang.Integer r6 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r6)
            return r6
        L_0x0068:
            r9 = 0
            r0.L$0 = r9
            r0.L$1 = r9
            r0.label = r3
            java.lang.Object r9 = r2.readAvailable(r6, r7, r8, r0)
            if (r9 != r1) goto L_0x0076
            return r1
        L_0x0076:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readAvailableSuspend(byte[], int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object readAvailableSuspend(java.nio.ByteBuffer r6, kotlin.coroutines.Continuation<? super java.lang.Integer> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$2
            if (r0 == 0) goto L_0x0014
            r0 = r7
            io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$2 r0 = (io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$2 r0 = new io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$2
            r0.<init>(r5, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0041
            if (r2 == r4) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x006e
        L_0x002d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0035:
            java.lang.Object r6 = r0.L$1
            java.nio.ByteBuffer r6 = (java.nio.ByteBuffer) r6
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0052
        L_0x0041:
            kotlin.ResultKt.throwOnFailure(r7)
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r4
            java.lang.Object r7 = r5.readSuspend(r4, r0)
            if (r7 != r1) goto L_0x0051
            return r1
        L_0x0051:
            r2 = r5
        L_0x0052:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 != 0) goto L_0x0060
            r6 = -1
            java.lang.Integer r6 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r6)
            return r6
        L_0x0060:
            r7 = 0
            r0.L$0 = r7
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r7 = r2.readAvailable((java.nio.ByteBuffer) r6, (kotlin.coroutines.Continuation<? super java.lang.Integer>) r0)
            if (r7 != r1) goto L_0x006e
            return r1
        L_0x006e:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readAvailableSuspend(java.nio.ByteBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object readAvailableSuspend(io.ktor.utils.io.core.internal.ChunkBuffer r6, kotlin.coroutines.Continuation<? super java.lang.Integer> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$3
            if (r0 == 0) goto L_0x0014
            r0 = r7
            io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$3 r0 = (io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$3 r0 = new io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$3
            r0.<init>(r5, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0041
            if (r2 == r4) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x006e
        L_0x002d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0035:
            java.lang.Object r6 = r0.L$1
            io.ktor.utils.io.core.internal.ChunkBuffer r6 = (io.ktor.utils.io.core.internal.ChunkBuffer) r6
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0052
        L_0x0041:
            kotlin.ResultKt.throwOnFailure(r7)
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r4
            java.lang.Object r7 = r5.readSuspend(r4, r0)
            if (r7 != r1) goto L_0x0051
            return r1
        L_0x0051:
            r2 = r5
        L_0x0052:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 != 0) goto L_0x0060
            r6 = -1
            java.lang.Integer r6 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r6)
            return r6
        L_0x0060:
            r7 = 0
            r0.L$0 = r7
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r7 = r2.readAvailable((io.ktor.utils.io.core.internal.ChunkBuffer) r6, (kotlin.coroutines.Continuation<? super java.lang.Integer>) r0)
            if (r7 != r1) goto L_0x006e
            return r1
        L_0x006e:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readAvailableSuspend(io.ktor.utils.io.core.internal.ChunkBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object readPacket$suspendImpl(ByteBufferChannel byteBufferChannel, int i, Continuation<? super ByteReadPacket> continuation) {
        Throwable cause;
        ClosedElement closed = byteBufferChannel.getClosed();
        if (closed != null && (cause = closed.getCause()) != null) {
            Void unused = ByteBufferChannelKt.rethrowClosed(cause);
            throw new KotlinNothingValueException();
        } else if (i == 0) {
            return ByteReadPacket.Companion.getEmpty();
        } else {
            BytePacketBuilder bytePacketBuilder = new BytePacketBuilder((ObjectPool) null, 1, (DefaultConstructorMarker) null);
            ByteBuffer borrow = ObjectPoolKt.getBufferPool().borrow();
            while (i > 0) {
                try {
                    borrow.clear();
                    if (borrow.remaining() > i) {
                        borrow.limit(i);
                    }
                    int readAsMuchAsPossible = byteBufferChannel.readAsMuchAsPossible(borrow);
                    if (readAsMuchAsPossible == 0) {
                        break;
                    }
                    borrow.flip();
                    OutputArraysJVMKt.writeFully(bytePacketBuilder, borrow);
                    i -= readAsMuchAsPossible;
                } catch (Throwable th) {
                    ObjectPoolKt.getBufferPool().recycle(borrow);
                    bytePacketBuilder.release();
                    throw th;
                }
            }
            if (i != 0) {
                return byteBufferChannel.readPacketSuspend(i, bytePacketBuilder, borrow, continuation);
            }
            ObjectPoolKt.getBufferPool().recycle(borrow);
            return bytePacketBuilder.build();
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x004b A[SYNTHETIC, Splitter:B:18:0x004b] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object readPacketSuspend(int r7, io.ktor.utils.io.core.BytePacketBuilder r8, java.nio.ByteBuffer r9, kotlin.coroutines.Continuation<? super io.ktor.utils.io.core.ByteReadPacket> r10) {
        /*
            r6 = this;
            boolean r0 = r10 instanceof io.ktor.utils.io.ByteBufferChannel$readPacketSuspend$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            io.ktor.utils.io.ByteBufferChannel$readPacketSuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel$readPacketSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$readPacketSuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readPacketSuspend$1
            r0.<init>(r6, r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0045
            if (r2 != r3) goto L_0x003d
            int r7 = r0.I$0
            java.lang.Object r8 = r0.L$2
            java.nio.ByteBuffer r8 = (java.nio.ByteBuffer) r8
            java.lang.Object r9 = r0.L$1
            io.ktor.utils.io.core.BytePacketBuilder r9 = (io.ktor.utils.io.core.BytePacketBuilder) r9
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x003b }
            r5 = r9
            r9 = r8
            r8 = r5
            goto L_0x0068
        L_0x003b:
            r7 = move-exception
            goto L_0x0089
        L_0x003d:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0045:
            kotlin.ResultKt.throwOnFailure(r10)
            r2 = r6
        L_0x0049:
            if (r7 <= 0) goto L_0x0079
            r9.clear()     // Catch:{ all -> 0x0085 }
            int r10 = r9.remaining()     // Catch:{ all -> 0x0085 }
            if (r10 <= r7) goto L_0x0057
            r9.limit(r7)     // Catch:{ all -> 0x0085 }
        L_0x0057:
            r0.L$0 = r2     // Catch:{ all -> 0x0085 }
            r0.L$1 = r8     // Catch:{ all -> 0x0085 }
            r0.L$2 = r9     // Catch:{ all -> 0x0085 }
            r0.I$0 = r7     // Catch:{ all -> 0x0085 }
            r0.label = r3     // Catch:{ all -> 0x0085 }
            java.lang.Object r10 = r2.readFully(r9, r0)     // Catch:{ all -> 0x0085 }
            if (r10 != r1) goto L_0x0068
            return r1
        L_0x0068:
            java.lang.Number r10 = (java.lang.Number) r10     // Catch:{ all -> 0x0085 }
            int r10 = r10.intValue()     // Catch:{ all -> 0x0085 }
            r9.flip()     // Catch:{ all -> 0x0085 }
            r4 = r8
            io.ktor.utils.io.core.Output r4 = (io.ktor.utils.io.core.Output) r4     // Catch:{ all -> 0x0085 }
            io.ktor.utils.io.core.OutputArraysJVMKt.writeFully(r4, r9)     // Catch:{ all -> 0x0085 }
            int r7 = r7 - r10
            goto L_0x0049
        L_0x0079:
            io.ktor.utils.io.core.ByteReadPacket r7 = r8.build()     // Catch:{ all -> 0x0085 }
            io.ktor.utils.io.pool.ObjectPool r8 = io.ktor.utils.io.internal.ObjectPoolKt.getBufferPool()
            r8.recycle(r9)
            return r7
        L_0x0085:
            r7 = move-exception
            r5 = r9
            r9 = r8
            r8 = r5
        L_0x0089:
            r9.release()     // Catch:{ all -> 0x008d }
            throw r7     // Catch:{ all -> 0x008d }
        L_0x008d:
            r7 = move-exception
            io.ktor.utils.io.pool.ObjectPool r9 = io.ktor.utils.io.internal.ObjectPoolKt.getBufferPool()
            r9.recycle(r8)
            goto L_0x0097
        L_0x0096:
            throw r7
        L_0x0097:
            goto L_0x0096
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readPacketSuspend(int, io.ktor.utils.io.core.BytePacketBuilder, java.nio.ByteBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object readBoolean(kotlin.coroutines.Continuation<? super java.lang.Boolean> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof io.ktor.utils.io.ByteBufferChannel$readBoolean$1
            if (r0 == 0) goto L_0x0014
            r0 = r5
            io.ktor.utils.io.ByteBufferChannel$readBoolean$1 r0 = (io.ktor.utils.io.ByteBufferChannel$readBoolean$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$readBoolean$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readBoolean$1
            r0.<init>(r4, r5)
        L_0x0019:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            kotlin.ResultKt.throwOnFailure(r5)
            goto L_0x003e
        L_0x002a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r5)
            r0.label = r3
            java.lang.Object r5 = r4.readByte(r0)
            if (r5 != r1) goto L_0x003e
            return r1
        L_0x003e:
            java.lang.Number r5 = (java.lang.Number) r5
            byte r5 = r5.byteValue()
            if (r5 == 0) goto L_0x0047
            goto L_0x0048
        L_0x0047:
            r3 = 0
        L_0x0048:
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readBoolean(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x009e A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00a8  */
    public final java.lang.Object readByte(kotlin.coroutines.Continuation<? super java.lang.Byte> r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof io.ktor.utils.io.ByteBufferChannel$readByte$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            io.ktor.utils.io.ByteBufferChannel$readByte$1 r0 = (io.ktor.utils.io.ByteBufferChannel$readByte$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$readByte$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readByte$1
            r0.<init>(r8, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            int r2 = r0.I$0
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r4 = (io.ktor.utils.io.ByteBufferChannel) r4
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x009f
        L_0x0031:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L_0x0039:
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = 1
            r4 = r8
        L_0x003e:
            kotlin.jvm.internal.Ref$ObjectRef r9 = new kotlin.jvm.internal.Ref$ObjectRef
            r9.<init>()
            java.nio.ByteBuffer r5 = r4.setupStateForRead()
            if (r5 != 0) goto L_0x004a
            goto L_0x0092
        L_0x004a:
            io.ktor.utils.io.internal.ReadWriteBufferState r6 = r4.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r6 = r6.capacity
            int r7 = r6._availableForRead$internal     // Catch:{ all -> 0x00c1 }
            if (r7 != 0) goto L_0x005b
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
            goto L_0x0092
        L_0x005b:
            boolean r7 = r6.tryReadExact(r2)     // Catch:{ all -> 0x00c1 }
            if (r7 != 0) goto L_0x0063
            r5 = 0
            goto L_0x007a
        L_0x0063:
            int r7 = r5.remaining()     // Catch:{ all -> 0x00c1 }
            if (r7 >= r2) goto L_0x006c
            r4.rollBytes(r5, r2)     // Catch:{ all -> 0x00c1 }
        L_0x006c:
            byte r7 = r5.get()     // Catch:{ all -> 0x00c1 }
            java.lang.Byte r7 = kotlin.coroutines.jvm.internal.Boxing.boxByte(r7)     // Catch:{ all -> 0x00c1 }
            r9.element = r7     // Catch:{ all -> 0x00c1 }
            r4.bytesRead(r5, r6, r2)     // Catch:{ all -> 0x00c1 }
            r5 = 1
        L_0x007a:
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
            if (r5 == 0) goto L_0x0092
            T r0 = r9.element
            if (r0 != 0) goto L_0x008d
            java.lang.String r9 = "result"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r9)
            r9 = 0
            goto L_0x0091
        L_0x008d:
            T r9 = r9.element
            java.lang.Number r9 = (java.lang.Number) r9
        L_0x0091:
            return r9
        L_0x0092:
            r0.L$0 = r4
            r0.I$0 = r2
            r0.label = r3
            java.lang.Object r9 = r4.readSuspend(r2, r0)
            if (r9 != r1) goto L_0x009f
            return r1
        L_0x009f:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto L_0x00a8
            goto L_0x003e
        L_0x00a8:
            kotlinx.coroutines.channels.ClosedReceiveChannelException r9 = new kotlinx.coroutines.channels.ClosedReceiveChannelException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "EOF while "
            r0.<init>(r1)
            r0.append(r2)
            java.lang.String r1 = " bytes expected"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r9.<init>(r0)
            throw r9
        L_0x00c1:
            r9 = move-exception
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
            goto L_0x00ca
        L_0x00c9:
            throw r9
        L_0x00ca:
            goto L_0x00c9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readByte(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x009f A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00a9  */
    public final java.lang.Object readShort(kotlin.coroutines.Continuation<? super java.lang.Short> r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof io.ktor.utils.io.ByteBufferChannel$readShort$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            io.ktor.utils.io.ByteBufferChannel$readShort$1 r0 = (io.ktor.utils.io.ByteBufferChannel$readShort$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$readShort$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readShort$1
            r0.<init>(r8, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            int r2 = r0.I$0
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r4 = (io.ktor.utils.io.ByteBufferChannel) r4
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x00a0
        L_0x0031:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L_0x0039:
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = 2
            r2 = 2
            r4 = r8
        L_0x003f:
            kotlin.jvm.internal.Ref$ObjectRef r9 = new kotlin.jvm.internal.Ref$ObjectRef
            r9.<init>()
            java.nio.ByteBuffer r5 = r4.setupStateForRead()
            if (r5 != 0) goto L_0x004b
            goto L_0x0093
        L_0x004b:
            io.ktor.utils.io.internal.ReadWriteBufferState r6 = r4.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r6 = r6.capacity
            int r7 = r6._availableForRead$internal     // Catch:{ all -> 0x00c2 }
            if (r7 != 0) goto L_0x005c
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
            goto L_0x0093
        L_0x005c:
            boolean r7 = r6.tryReadExact(r2)     // Catch:{ all -> 0x00c2 }
            if (r7 != 0) goto L_0x0064
            r5 = 0
            goto L_0x007b
        L_0x0064:
            int r7 = r5.remaining()     // Catch:{ all -> 0x00c2 }
            if (r7 >= r2) goto L_0x006d
            r4.rollBytes(r5, r2)     // Catch:{ all -> 0x00c2 }
        L_0x006d:
            short r7 = r5.getShort()     // Catch:{ all -> 0x00c2 }
            java.lang.Short r7 = kotlin.coroutines.jvm.internal.Boxing.boxShort(r7)     // Catch:{ all -> 0x00c2 }
            r9.element = r7     // Catch:{ all -> 0x00c2 }
            r4.bytesRead(r5, r6, r2)     // Catch:{ all -> 0x00c2 }
            r5 = 1
        L_0x007b:
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
            if (r5 == 0) goto L_0x0093
            T r0 = r9.element
            if (r0 != 0) goto L_0x008e
            java.lang.String r9 = "result"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r9)
            r9 = 0
            goto L_0x0092
        L_0x008e:
            T r9 = r9.element
            java.lang.Number r9 = (java.lang.Number) r9
        L_0x0092:
            return r9
        L_0x0093:
            r0.L$0 = r4
            r0.I$0 = r2
            r0.label = r3
            java.lang.Object r9 = r4.readSuspend(r2, r0)
            if (r9 != r1) goto L_0x00a0
            return r1
        L_0x00a0:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto L_0x00a9
            goto L_0x003f
        L_0x00a9:
            kotlinx.coroutines.channels.ClosedReceiveChannelException r9 = new kotlinx.coroutines.channels.ClosedReceiveChannelException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "EOF while "
            r0.<init>(r1)
            r0.append(r2)
            java.lang.String r1 = " bytes expected"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r9.<init>(r0)
            throw r9
        L_0x00c2:
            r9 = move-exception
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
            goto L_0x00cb
        L_0x00ca:
            throw r9
        L_0x00cb:
            goto L_0x00ca
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readShort(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x009f A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00a9  */
    public final java.lang.Object readInt(kotlin.coroutines.Continuation<? super java.lang.Integer> r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof io.ktor.utils.io.ByteBufferChannel$readInt$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            io.ktor.utils.io.ByteBufferChannel$readInt$1 r0 = (io.ktor.utils.io.ByteBufferChannel$readInt$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$readInt$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readInt$1
            r0.<init>(r8, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            int r2 = r0.I$0
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r4 = (io.ktor.utils.io.ByteBufferChannel) r4
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x00a0
        L_0x0031:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L_0x0039:
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = 4
            r2 = 4
            r4 = r8
        L_0x003f:
            kotlin.jvm.internal.Ref$ObjectRef r9 = new kotlin.jvm.internal.Ref$ObjectRef
            r9.<init>()
            java.nio.ByteBuffer r5 = r4.setupStateForRead()
            if (r5 != 0) goto L_0x004b
            goto L_0x0093
        L_0x004b:
            io.ktor.utils.io.internal.ReadWriteBufferState r6 = r4.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r6 = r6.capacity
            int r7 = r6._availableForRead$internal     // Catch:{ all -> 0x00c2 }
            if (r7 != 0) goto L_0x005c
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
            goto L_0x0093
        L_0x005c:
            boolean r7 = r6.tryReadExact(r2)     // Catch:{ all -> 0x00c2 }
            if (r7 != 0) goto L_0x0064
            r5 = 0
            goto L_0x007b
        L_0x0064:
            int r7 = r5.remaining()     // Catch:{ all -> 0x00c2 }
            if (r7 >= r2) goto L_0x006d
            r4.rollBytes(r5, r2)     // Catch:{ all -> 0x00c2 }
        L_0x006d:
            int r7 = r5.getInt()     // Catch:{ all -> 0x00c2 }
            java.lang.Integer r7 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r7)     // Catch:{ all -> 0x00c2 }
            r9.element = r7     // Catch:{ all -> 0x00c2 }
            r4.bytesRead(r5, r6, r2)     // Catch:{ all -> 0x00c2 }
            r5 = 1
        L_0x007b:
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
            if (r5 == 0) goto L_0x0093
            T r0 = r9.element
            if (r0 != 0) goto L_0x008e
            java.lang.String r9 = "result"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r9)
            r9 = 0
            goto L_0x0092
        L_0x008e:
            T r9 = r9.element
            java.lang.Number r9 = (java.lang.Number) r9
        L_0x0092:
            return r9
        L_0x0093:
            r0.L$0 = r4
            r0.I$0 = r2
            r0.label = r3
            java.lang.Object r9 = r4.readSuspend(r2, r0)
            if (r9 != r1) goto L_0x00a0
            return r1
        L_0x00a0:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto L_0x00a9
            goto L_0x003f
        L_0x00a9:
            kotlinx.coroutines.channels.ClosedReceiveChannelException r9 = new kotlinx.coroutines.channels.ClosedReceiveChannelException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "EOF while "
            r0.<init>(r1)
            r0.append(r2)
            java.lang.String r1 = " bytes expected"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r9.<init>(r0)
            throw r9
        L_0x00c2:
            r9 = move-exception
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
            goto L_0x00cb
        L_0x00ca:
            throw r9
        L_0x00cb:
            goto L_0x00ca
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readInt(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00a1 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00ab  */
    public final java.lang.Object readLong(kotlin.coroutines.Continuation<? super java.lang.Long> r10) {
        /*
            r9 = this;
            boolean r0 = r10 instanceof io.ktor.utils.io.ByteBufferChannel$readLong$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            io.ktor.utils.io.ByteBufferChannel$readLong$1 r0 = (io.ktor.utils.io.ByteBufferChannel$readLong$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$readLong$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readLong$1
            r0.<init>(r9, r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            int r2 = r0.I$0
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r4 = (io.ktor.utils.io.ByteBufferChannel) r4
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x00a2
        L_0x0031:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L_0x0039:
            kotlin.ResultKt.throwOnFailure(r10)
            r10 = 8
            r2 = 8
            r4 = r9
        L_0x0041:
            kotlin.jvm.internal.Ref$ObjectRef r10 = new kotlin.jvm.internal.Ref$ObjectRef
            r10.<init>()
            java.nio.ByteBuffer r5 = r4.setupStateForRead()
            if (r5 != 0) goto L_0x004d
            goto L_0x0095
        L_0x004d:
            io.ktor.utils.io.internal.ReadWriteBufferState r6 = r4.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r6 = r6.capacity
            int r7 = r6._availableForRead$internal     // Catch:{ all -> 0x00c4 }
            if (r7 != 0) goto L_0x005e
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
            goto L_0x0095
        L_0x005e:
            boolean r7 = r6.tryReadExact(r2)     // Catch:{ all -> 0x00c4 }
            if (r7 != 0) goto L_0x0066
            r5 = 0
            goto L_0x007d
        L_0x0066:
            int r7 = r5.remaining()     // Catch:{ all -> 0x00c4 }
            if (r7 >= r2) goto L_0x006f
            r4.rollBytes(r5, r2)     // Catch:{ all -> 0x00c4 }
        L_0x006f:
            long r7 = r5.getLong()     // Catch:{ all -> 0x00c4 }
            java.lang.Long r7 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r7)     // Catch:{ all -> 0x00c4 }
            r10.element = r7     // Catch:{ all -> 0x00c4 }
            r4.bytesRead(r5, r6, r2)     // Catch:{ all -> 0x00c4 }
            r5 = 1
        L_0x007d:
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
            if (r5 == 0) goto L_0x0095
            T r0 = r10.element
            if (r0 != 0) goto L_0x0090
            java.lang.String r10 = "result"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r10)
            r10 = 0
            goto L_0x0094
        L_0x0090:
            T r10 = r10.element
            java.lang.Number r10 = (java.lang.Number) r10
        L_0x0094:
            return r10
        L_0x0095:
            r0.L$0 = r4
            r0.I$0 = r2
            r0.label = r3
            java.lang.Object r10 = r4.readSuspend(r2, r0)
            if (r10 != r1) goto L_0x00a2
            return r1
        L_0x00a2:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L_0x00ab
            goto L_0x0041
        L_0x00ab:
            kotlinx.coroutines.channels.ClosedReceiveChannelException r10 = new kotlinx.coroutines.channels.ClosedReceiveChannelException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "EOF while "
            r0.<init>(r1)
            r0.append(r2)
            java.lang.String r1 = " bytes expected"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r10.<init>(r0)
            throw r10
        L_0x00c4:
            r10 = move-exception
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
            goto L_0x00cd
        L_0x00cc:
            throw r10
        L_0x00cd:
            goto L_0x00cc
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readLong(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ab A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b5  */
    public final java.lang.Object readFloat(kotlin.coroutines.Continuation<? super java.lang.Float> r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof io.ktor.utils.io.ByteBufferChannel$readFloat$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            io.ktor.utils.io.ByteBufferChannel$readFloat$1 r0 = (io.ktor.utils.io.ByteBufferChannel$readFloat$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$readFloat$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readFloat$1
            r0.<init>(r8, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            int r2 = r0.I$0
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r4 = (io.ktor.utils.io.ByteBufferChannel) r4
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x00ac
        L_0x0031:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L_0x0039:
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = 4
            r2 = 4
            r4 = r8
        L_0x003f:
            kotlin.jvm.internal.Ref$ObjectRef r9 = new kotlin.jvm.internal.Ref$ObjectRef
            r9.<init>()
            java.nio.ByteBuffer r5 = r4.setupStateForRead()
            if (r5 != 0) goto L_0x004b
            goto L_0x009f
        L_0x004b:
            io.ktor.utils.io.internal.ReadWriteBufferState r6 = r4.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r6 = r6.capacity
            int r7 = r6._availableForRead$internal     // Catch:{ all -> 0x00ce }
            if (r7 != 0) goto L_0x005c
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
            goto L_0x009f
        L_0x005c:
            boolean r7 = r6.tryReadExact(r2)     // Catch:{ all -> 0x00ce }
            if (r7 != 0) goto L_0x0064
            r5 = 0
            goto L_0x007b
        L_0x0064:
            int r7 = r5.remaining()     // Catch:{ all -> 0x00ce }
            if (r7 >= r2) goto L_0x006d
            r4.rollBytes(r5, r2)     // Catch:{ all -> 0x00ce }
        L_0x006d:
            int r7 = r5.getInt()     // Catch:{ all -> 0x00ce }
            java.lang.Integer r7 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r7)     // Catch:{ all -> 0x00ce }
            r9.element = r7     // Catch:{ all -> 0x00ce }
            r4.bytesRead(r5, r6, r2)     // Catch:{ all -> 0x00ce }
            r5 = 1
        L_0x007b:
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
            if (r5 == 0) goto L_0x009f
            T r0 = r9.element
            if (r0 != 0) goto L_0x008e
            java.lang.String r9 = "result"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r9)
            r9 = 0
            goto L_0x0092
        L_0x008e:
            T r9 = r9.element
            java.lang.Number r9 = (java.lang.Number) r9
        L_0x0092:
            int r9 = r9.intValue()
            float r9 = java.lang.Float.intBitsToFloat(r9)
            java.lang.Float r9 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r9)
            return r9
        L_0x009f:
            r0.L$0 = r4
            r0.I$0 = r2
            r0.label = r3
            java.lang.Object r9 = r4.readSuspend(r2, r0)
            if (r9 != r1) goto L_0x00ac
            return r1
        L_0x00ac:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto L_0x00b5
            goto L_0x003f
        L_0x00b5:
            kotlinx.coroutines.channels.ClosedReceiveChannelException r9 = new kotlinx.coroutines.channels.ClosedReceiveChannelException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "EOF while "
            r0.<init>(r1)
            r0.append(r2)
            java.lang.String r1 = " bytes expected"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r9.<init>(r0)
            throw r9
        L_0x00ce:
            r9 = move-exception
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
            goto L_0x00d7
        L_0x00d6:
            throw r9
        L_0x00d7:
            goto L_0x00d6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readFloat(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ad A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b7  */
    public final java.lang.Object readDouble(kotlin.coroutines.Continuation<? super java.lang.Double> r10) {
        /*
            r9 = this;
            boolean r0 = r10 instanceof io.ktor.utils.io.ByteBufferChannel$readDouble$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            io.ktor.utils.io.ByteBufferChannel$readDouble$1 r0 = (io.ktor.utils.io.ByteBufferChannel$readDouble$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$readDouble$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readDouble$1
            r0.<init>(r9, r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            int r2 = r0.I$0
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r4 = (io.ktor.utils.io.ByteBufferChannel) r4
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x00ae
        L_0x0031:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L_0x0039:
            kotlin.ResultKt.throwOnFailure(r10)
            r10 = 8
            r2 = 8
            r4 = r9
        L_0x0041:
            kotlin.jvm.internal.Ref$ObjectRef r10 = new kotlin.jvm.internal.Ref$ObjectRef
            r10.<init>()
            java.nio.ByteBuffer r5 = r4.setupStateForRead()
            if (r5 != 0) goto L_0x004d
            goto L_0x00a1
        L_0x004d:
            io.ktor.utils.io.internal.ReadWriteBufferState r6 = r4.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r6 = r6.capacity
            int r7 = r6._availableForRead$internal     // Catch:{ all -> 0x00d0 }
            if (r7 != 0) goto L_0x005e
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
            goto L_0x00a1
        L_0x005e:
            boolean r7 = r6.tryReadExact(r2)     // Catch:{ all -> 0x00d0 }
            if (r7 != 0) goto L_0x0066
            r5 = 0
            goto L_0x007d
        L_0x0066:
            int r7 = r5.remaining()     // Catch:{ all -> 0x00d0 }
            if (r7 >= r2) goto L_0x006f
            r4.rollBytes(r5, r2)     // Catch:{ all -> 0x00d0 }
        L_0x006f:
            long r7 = r5.getLong()     // Catch:{ all -> 0x00d0 }
            java.lang.Long r7 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r7)     // Catch:{ all -> 0x00d0 }
            r10.element = r7     // Catch:{ all -> 0x00d0 }
            r4.bytesRead(r5, r6, r2)     // Catch:{ all -> 0x00d0 }
            r5 = 1
        L_0x007d:
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
            if (r5 == 0) goto L_0x00a1
            T r0 = r10.element
            if (r0 != 0) goto L_0x0090
            java.lang.String r10 = "result"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r10)
            r10 = 0
            goto L_0x0094
        L_0x0090:
            T r10 = r10.element
            java.lang.Number r10 = (java.lang.Number) r10
        L_0x0094:
            long r0 = r10.longValue()
            double r0 = java.lang.Double.longBitsToDouble(r0)
            java.lang.Double r10 = kotlin.coroutines.jvm.internal.Boxing.boxDouble(r0)
            return r10
        L_0x00a1:
            r0.L$0 = r4
            r0.I$0 = r2
            r0.label = r3
            java.lang.Object r10 = r4.readSuspend(r2, r0)
            if (r10 != r1) goto L_0x00ae
            return r1
        L_0x00ae:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L_0x00b7
            goto L_0x0041
        L_0x00b7:
            kotlinx.coroutines.channels.ClosedReceiveChannelException r10 = new kotlinx.coroutines.channels.ClosedReceiveChannelException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "EOF while "
            r0.<init>(r1)
            r0.append(r2)
            java.lang.String r1 = " bytes expected"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r10.<init>(r0)
            throw r10
        L_0x00d0:
            r10 = move-exception
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
            goto L_0x00d9
        L_0x00d8:
            throw r10
        L_0x00d9:
            goto L_0x00d8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readDouble(kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final <T extends Number> Object readPrimitive(int i, Function1<? super ByteBuffer, ? extends T> function1, Continuation<? super T> continuation) {
        Object readSuspend;
        boolean z;
        do {
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            ByteBuffer byteBuffer = setupStateForRead();
            if (byteBuffer != null) {
                RingBufferCapacity ringBufferCapacity = getState().capacity;
                try {
                    if (ringBufferCapacity._availableForRead$internal != 0) {
                        RingBufferCapacity ringBufferCapacity2 = ringBufferCapacity;
                        ByteBuffer byteBuffer2 = byteBuffer;
                        if (!ringBufferCapacity.tryReadExact(i)) {
                            z = false;
                        } else {
                            if (byteBuffer.remaining() < i) {
                                rollBytes(byteBuffer, i);
                            }
                            objectRef.element = function1.invoke(byteBuffer);
                            bytesRead(byteBuffer, ringBufferCapacity, i);
                            z = true;
                        }
                        Boolean valueOf = Boolean.valueOf(z);
                        Boolean bool = valueOf;
                        boolean booleanValue = valueOf.booleanValue();
                        InlineMarker.finallyStart(1);
                        restoreStateAfterRead();
                        tryTerminate$ktor_io();
                        InlineMarker.finallyEnd(1);
                        if (booleanValue) {
                            if (objectRef.element != null) {
                                return (Number) objectRef.element;
                            }
                            Intrinsics.throwUninitializedPropertyAccessException("result");
                            return null;
                        }
                    }
                } finally {
                    InlineMarker.finallyStart(1);
                    restoreStateAfterRead();
                    tryTerminate$ktor_io();
                    InlineMarker.finallyEnd(1);
                }
            }
            InlineMarker.mark(0);
            readSuspend = readSuspend(i, continuation);
            InlineMarker.mark(1);
        } while (((Boolean) readSuspend).booleanValue());
        throw new ClosedReceiveChannelException("EOF while " + i + " bytes expected");
    }

    private final void rollBytes(ByteBuffer byteBuffer, int i) {
        int remaining = byteBuffer.remaining();
        byteBuffer.limit(byteBuffer.position() + i);
        int i2 = i - remaining;
        for (int i3 = 0; i3 < i2; i3++) {
            byteBuffer.put(byteBuffer.capacity() + ReservedLongIndex + i3, byteBuffer.get(i3));
        }
    }

    private final void carry(ByteBuffer byteBuffer) {
        int capacity = byteBuffer.capacity() - this.reservedSize;
        int position = byteBuffer.position();
        for (int i = capacity; i < position; i++) {
            byteBuffer.put(i - capacity, byteBuffer.get(i));
        }
    }

    public final void bytesWrittenFromSession$ktor_io(ByteBuffer byteBuffer, RingBufferCapacity ringBufferCapacity, int i) {
        Intrinsics.checkNotNullParameter(byteBuffer, "buffer");
        Intrinsics.checkNotNullParameter(ringBufferCapacity, "capacity");
        bytesWritten(byteBuffer, ringBufferCapacity, i);
    }

    private final void bytesWritten(ByteBuffer byteBuffer, RingBufferCapacity ringBufferCapacity, int i) {
        if (i >= 0) {
            this.writePosition = carryIndex(byteBuffer, this.writePosition + i);
            ringBufferCapacity.completeWrite(i);
            setTotalBytesWritten$ktor_io(getTotalBytesWritten() + ((long) i));
            return;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    private final void bytesRead(ByteBuffer byteBuffer, RingBufferCapacity ringBufferCapacity, int i) {
        if (i >= 0) {
            this.readPosition = carryIndex(byteBuffer, this.readPosition + i);
            ringBufferCapacity.completeRead(i);
            setTotalBytesRead$ktor_io(getTotalBytesRead() + ((long) i));
            resumeWriteOp();
            return;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = resolveDelegation(r1, r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final io.ktor.utils.io.ByteBufferChannel resolveChannelInstance$ktor_io() {
        /*
            r1 = this;
            io.ktor.utils.io.internal.JoiningState r0 = r1.joining
            if (r0 == 0) goto L_0x000a
            io.ktor.utils.io.ByteBufferChannel r0 = r1.resolveDelegation(r1, r0)
            if (r0 != 0) goto L_0x000b
        L_0x000a:
            r0 = r1
        L_0x000b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.resolveChannelInstance$ktor_io():io.ktor.utils.io.ByteBufferChannel");
    }

    private final ByteBufferChannel resolveDelegation(ByteBufferChannel byteBufferChannel, JoiningState joiningState) {
        while (byteBufferChannel.getState() == ReadWriteBufferState.Terminated.INSTANCE) {
            byteBufferChannel = joiningState.getDelegatedTo();
            joiningState = byteBufferChannel.joining;
            if (joiningState == null) {
                return byteBufferChannel;
            }
        }
        return null;
    }

    private final Object delegateSuspend(JoiningState joiningState, Function1<? super ByteBufferChannel, Unit> function1, Continuation<? super Unit> continuation) {
        while (getState() != ReadWriteBufferState.Terminated.INSTANCE) {
            InlineMarker.mark(0);
            writeSuspend(1, continuation);
            InlineMarker.mark(1);
        }
        function1.invoke(joiningState.getDelegatedTo());
        return Unit.INSTANCE;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00ae, code lost:
        if (r8.getState() != io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE) goto L_0x00c3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00b0, code lost:
        r0.L$0 = null;
        r0.L$1 = null;
        r0.label = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00c0, code lost:
        if (r2.getDelegatedTo().writeByte((byte) r9, r0) != r1) goto L_0x01cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00c2, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00c3, code lost:
        r0.L$0 = r2;
        r0.L$1 = r8;
        r0.B$0 = r9;
        r0.label = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00d0, code lost:
        if (r8.writeSuspend(1, r0) != r1) goto L_0x00a8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00d2, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        r0.L$0 = r6;
        r0.L$1 = r8;
        r0.L$2 = r2;
        r0.B$0 = r10;
        r0.I$0 = r9;
        r0.label = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00f4, code lost:
        if (r8.writeSuspend(r9, r0) != r1) goto L_0x00f7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00f6, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00f9, code lost:
        if (r8.joining == null) goto L_0x0153;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00fb, code lost:
        r8.restoreStateAfterWrite$ktor_io();
        r9 = r8.joining;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0109, code lost:
        if (r8.getState() != io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE) goto L_0x0120;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x010b, code lost:
        r0.L$0 = null;
        r0.L$1 = null;
        r0.L$2 = null;
        r0.label = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x011d, code lost:
        if (r9.getDelegatedTo().writeByte((byte) r10, r0) != r1) goto L_0x01cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x011f, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0120, code lost:
        r2 = r9;
        r9 = r8;
        r8 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0129, code lost:
        if (r9.getState() != io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE) goto L_0x0140;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x012b, code lost:
        r0.L$0 = null;
        r0.L$1 = null;
        r0.L$2 = null;
        r0.label = 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x013d, code lost:
        if (r2.getDelegatedTo().writeByte((byte) r8, r0) != r1) goto L_0x01cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x013f, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0140, code lost:
        r0.L$0 = r2;
        r0.L$1 = r9;
        r0.L$2 = null;
        r0.B$0 = r8;
        r0.label = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0150, code lost:
        if (r9.writeSuspend(1, r0) != r1) goto L_0x0123;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0152, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0157, code lost:
        if (r6.tryWriteExact(r9) != false) goto L_0x015a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x015a, code lost:
        r8.prepareWriteBuffer$ktor_io(r2, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0161, code lost:
        if (r2.remaining() >= r9) goto L_0x0172;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0163, code lost:
        r2.limit(r2.capacity());
        r2.put((byte) r10);
        r8.carry(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0172, code lost:
        r2.put((byte) r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0176, code lost:
        r8.bytesWritten(r2, r6, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x017d, code lost:
        if (r6.isFull() != false) goto L_0x0185;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0183, code lost:
        if (r8.getAutoFlush() == false) goto L_0x0188;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0185, code lost:
        r8.flush();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0188, code lost:
        r8.restoreStateAfterWrite$ktor_io();
        r8.tryTerminate$ktor_io();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x018f, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0190, code lost:
        r5 = r8;
        r8 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01cf, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ java.lang.Object writeByte$suspendImpl(io.ktor.utils.io.ByteBufferChannel r8, byte r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            boolean r0 = r10 instanceof io.ktor.utils.io.ByteBufferChannel$writeByte$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            io.ktor.utils.io.ByteBufferChannel$writeByte$1 r0 = (io.ktor.utils.io.ByteBufferChannel$writeByte$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$writeByte$1 r0 = new io.ktor.utils.io.ByteBufferChannel$writeByte$1
            r0.<init>(r8, r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            switch(r2) {
                case 0: goto L_0x006e;
                case 1: goto L_0x0069;
                case 2: goto L_0x0069;
                case 3: goto L_0x0069;
                case 4: goto L_0x0058;
                case 5: goto L_0x003d;
                case 6: goto L_0x0069;
                case 7: goto L_0x0069;
                case 8: goto L_0x002e;
                default: goto L_0x0026;
            }
        L_0x0026:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x002e:
            byte r8 = r0.B$0
            java.lang.Object r9 = r0.L$1
            io.ktor.utils.io.ByteBufferChannel r9 = (io.ktor.utils.io.ByteBufferChannel) r9
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.internal.JoiningState r2 = (io.ktor.utils.io.internal.JoiningState) r2
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0123
        L_0x003d:
            int r8 = r0.I$0
            byte r9 = r0.B$0
            java.lang.Object r2 = r0.L$2
            java.nio.ByteBuffer r2 = (java.nio.ByteBuffer) r2
            java.lang.Object r5 = r0.L$1
            io.ktor.utils.io.ByteBufferChannel r5 = (io.ktor.utils.io.ByteBufferChannel) r5
            java.lang.Object r6 = r0.L$0
            io.ktor.utils.io.internal.RingBufferCapacity r6 = (io.ktor.utils.io.internal.RingBufferCapacity) r6
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x0055 }
            r10 = r9
            r9 = r8
            r8 = r5
            goto L_0x00f7
        L_0x0055:
            r8 = move-exception
            goto L_0x0192
        L_0x0058:
            byte r8 = r0.B$0
            java.lang.Object r9 = r0.L$1
            io.ktor.utils.io.ByteBufferChannel r9 = (io.ktor.utils.io.ByteBufferChannel) r9
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.internal.JoiningState r2 = (io.ktor.utils.io.internal.JoiningState) r2
            kotlin.ResultKt.throwOnFailure(r10)
            r7 = r9
            r9 = r8
            r8 = r7
            goto L_0x00a8
        L_0x0069:
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x01cd
        L_0x006e:
            kotlin.ResultKt.throwOnFailure(r10)
            io.ktor.utils.io.internal.JoiningState r10 = r8.joining
            if (r10 == 0) goto L_0x0085
            io.ktor.utils.io.ByteBufferChannel r10 = r8.resolveDelegation(r8, r10)
            if (r10 == 0) goto L_0x0085
            byte r8 = (byte) r9
            r0.label = r3
            java.lang.Object r8 = r10.writeByte(r8, r0)
            if (r8 != r1) goto L_0x01cd
            return r1
        L_0x0085:
            java.nio.ByteBuffer r10 = r8.setupStateForWrite$ktor_io()
            if (r10 != 0) goto L_0x00d3
            io.ktor.utils.io.internal.JoiningState r10 = r8.joining
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10)
            io.ktor.utils.io.internal.ReadWriteBufferState r2 = r8.getState()
            io.ktor.utils.io.internal.ReadWriteBufferState$Terminated r5 = io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE
            if (r2 != r5) goto L_0x00a7
            io.ktor.utils.io.ByteBufferChannel r8 = r10.getDelegatedTo()
            byte r9 = (byte) r9
            r10 = 2
            r0.label = r10
            java.lang.Object r8 = r8.writeByte(r9, r0)
            if (r8 != r1) goto L_0x01cd
            return r1
        L_0x00a7:
            r2 = r10
        L_0x00a8:
            io.ktor.utils.io.internal.ReadWriteBufferState r10 = r8.getState()
            io.ktor.utils.io.internal.ReadWriteBufferState$Terminated r5 = io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE
            if (r10 != r5) goto L_0x00c3
            io.ktor.utils.io.ByteBufferChannel r8 = r2.getDelegatedTo()
            byte r9 = (byte) r9
            r0.L$0 = r4
            r0.L$1 = r4
            r10 = 3
            r0.label = r10
            java.lang.Object r8 = r8.writeByte(r9, r0)
            if (r8 != r1) goto L_0x01cd
            return r1
        L_0x00c3:
            r0.L$0 = r2
            r0.L$1 = r8
            r0.B$0 = r9
            r10 = 4
            r0.label = r10
            java.lang.Object r10 = r8.writeSuspend(r3, r0)
            if (r10 != r1) goto L_0x00a8
            return r1
        L_0x00d3:
            io.ktor.utils.io.internal.ReadWriteBufferState r2 = r8.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r2 = r2.capacity
            boolean r5 = r2.tryWriteExact(r3)
            if (r5 != 0) goto L_0x0199
            r6 = r2
            r2 = r10
            r10 = r9
            r9 = 1
        L_0x00e3:
            r0.L$0 = r6     // Catch:{ all -> 0x018f }
            r0.L$1 = r8     // Catch:{ all -> 0x018f }
            r0.L$2 = r2     // Catch:{ all -> 0x018f }
            r0.B$0 = r10     // Catch:{ all -> 0x018f }
            r0.I$0 = r9     // Catch:{ all -> 0x018f }
            r5 = 5
            r0.label = r5     // Catch:{ all -> 0x018f }
            java.lang.Object r5 = r8.writeSuspend(r9, r0)     // Catch:{ all -> 0x018f }
            if (r5 != r1) goto L_0x00f7
            return r1
        L_0x00f7:
            io.ktor.utils.io.internal.JoiningState r5 = r8.joining
            if (r5 == 0) goto L_0x0153
            r8.restoreStateAfterWrite$ktor_io()
            io.ktor.utils.io.internal.JoiningState r9 = r8.joining
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)
            io.ktor.utils.io.internal.ReadWriteBufferState r2 = r8.getState()
            io.ktor.utils.io.internal.ReadWriteBufferState$Terminated r5 = io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE
            if (r2 != r5) goto L_0x0120
            io.ktor.utils.io.ByteBufferChannel r8 = r9.getDelegatedTo()
            byte r9 = (byte) r10
            r0.L$0 = r4
            r0.L$1 = r4
            r0.L$2 = r4
            r10 = 6
            r0.label = r10
            java.lang.Object r8 = r8.writeByte(r9, r0)
            if (r8 != r1) goto L_0x01cd
            return r1
        L_0x0120:
            r2 = r9
            r9 = r8
            r8 = r10
        L_0x0123:
            io.ktor.utils.io.internal.ReadWriteBufferState r10 = r9.getState()
            io.ktor.utils.io.internal.ReadWriteBufferState$Terminated r5 = io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE
            if (r10 != r5) goto L_0x0140
            io.ktor.utils.io.ByteBufferChannel r9 = r2.getDelegatedTo()
            byte r8 = (byte) r8
            r0.L$0 = r4
            r0.L$1 = r4
            r0.L$2 = r4
            r10 = 7
            r0.label = r10
            java.lang.Object r8 = r9.writeByte(r8, r0)
            if (r8 != r1) goto L_0x01cd
            return r1
        L_0x0140:
            r0.L$0 = r2
            r0.L$1 = r9
            r0.L$2 = r4
            r0.B$0 = r8
            r10 = 8
            r0.label = r10
            java.lang.Object r10 = r9.writeSuspend(r3, r0)
            if (r10 != r1) goto L_0x0123
            return r1
        L_0x0153:
            boolean r5 = r6.tryWriteExact(r9)
            if (r5 != 0) goto L_0x015a
            goto L_0x00e3
        L_0x015a:
            r8.prepareWriteBuffer$ktor_io(r2, r9)
            int r0 = r2.remaining()
            if (r0 >= r9) goto L_0x0172
            int r0 = r2.capacity()
            r2.limit(r0)
            byte r10 = (byte) r10
            r2.put(r10)
            r8.carry(r2)
            goto L_0x0176
        L_0x0172:
            byte r10 = (byte) r10
            r2.put(r10)
        L_0x0176:
            r8.bytesWritten(r2, r6, r9)
            boolean r9 = r6.isFull()
            if (r9 != 0) goto L_0x0185
            boolean r9 = r8.getAutoFlush()
            if (r9 == 0) goto L_0x0188
        L_0x0185:
            r8.flush()
        L_0x0188:
            r8.restoreStateAfterWrite$ktor_io()
            r8.tryTerminate$ktor_io()
            goto L_0x01cd
        L_0x018f:
            r9 = move-exception
            r5 = r8
            r8 = r9
        L_0x0192:
            r5.restoreStateAfterWrite$ktor_io()
            r5.tryTerminate$ktor_io()
            throw r8
        L_0x0199:
            r8.prepareWriteBuffer$ktor_io(r10, r3)
            int r0 = r10.remaining()
            if (r0 >= r3) goto L_0x01b1
            int r0 = r10.capacity()
            r10.limit(r0)
            byte r9 = (byte) r9
            r10.put(r9)
            r8.carry(r10)
            goto L_0x01b5
        L_0x01b1:
            byte r9 = (byte) r9
            r10.put(r9)
        L_0x01b5:
            r8.bytesWritten(r10, r2, r3)
            boolean r9 = r2.isFull()
            if (r9 != 0) goto L_0x01c4
            boolean r9 = r8.getAutoFlush()
            if (r9 == 0) goto L_0x01c7
        L_0x01c4:
            r8.flush()
        L_0x01c7:
            r8.restoreStateAfterWrite$ktor_io()
            r8.tryTerminate$ktor_io()
        L_0x01cd:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeByte$suspendImpl(io.ktor.utils.io.ByteBufferChannel, byte, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00ae, code lost:
        if (r8.getState() != io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE) goto L_0x00c3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00b0, code lost:
        r0.L$0 = null;
        r0.L$1 = null;
        r0.label = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00c0, code lost:
        if (r2.getDelegatedTo().writeShort((short) r9, r0) != r1) goto L_0x01cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00c2, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00c3, code lost:
        r0.L$0 = r2;
        r0.L$1 = r8;
        r0.S$0 = r9;
        r0.label = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00d0, code lost:
        if (r8.writeSuspend(1, r0) != r1) goto L_0x00a8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00d2, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        r0.L$0 = r6;
        r0.L$1 = r8;
        r0.L$2 = r10;
        r0.S$0 = r9;
        r0.I$0 = r2;
        r0.label = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00f1, code lost:
        if (r8.writeSuspend(r2, r0) != r1) goto L_0x00f4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00f3, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00f6, code lost:
        if (r8.joining == null) goto L_0x0151;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00f8, code lost:
        r8.restoreStateAfterWrite$ktor_io();
        r10 = r8.joining;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0106, code lost:
        if (r8.getState() != io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE) goto L_0x011d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0108, code lost:
        r0.L$0 = null;
        r0.L$1 = null;
        r0.L$2 = null;
        r0.label = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x011a, code lost:
        if (r10.getDelegatedTo().writeShort((short) r9, r0) != r1) goto L_0x01cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x011c, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x011d, code lost:
        r2 = r10;
        r7 = r9;
        r9 = r8;
        r8 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0127, code lost:
        if (r9.getState() != io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE) goto L_0x013e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0129, code lost:
        r0.L$0 = null;
        r0.L$1 = null;
        r0.L$2 = null;
        r0.label = 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x013b, code lost:
        if (r2.getDelegatedTo().writeShort((short) r8, r0) != r1) goto L_0x01cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x013d, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x013e, code lost:
        r0.L$0 = r2;
        r0.L$1 = r9;
        r0.L$2 = null;
        r0.S$0 = r8;
        r0.label = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x014e, code lost:
        if (r9.writeSuspend(1, r0) != r1) goto L_0x0121;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0150, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0155, code lost:
        if (r6.tryWriteExact(r2) != false) goto L_0x0158;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0158, code lost:
        r8.prepareWriteBuffer$ktor_io(r10, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x015f, code lost:
        if (r10.remaining() >= r2) goto L_0x0170;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0161, code lost:
        r10.limit(r10.capacity());
        r10.putShort((short) r9);
        r8.carry(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0170, code lost:
        r10.putShort((short) r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0174, code lost:
        r8.bytesWritten(r10, r6, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x017b, code lost:
        if (r6.isFull() != false) goto L_0x0183;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0181, code lost:
        if (r8.getAutoFlush() == false) goto L_0x0186;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0183, code lost:
        r8.flush();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0186, code lost:
        r8.restoreStateAfterWrite$ktor_io();
        r8.tryTerminate$ktor_io();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x018d, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x018e, code lost:
        r5 = r8;
        r8 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01cd, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ java.lang.Object writeShort$suspendImpl(io.ktor.utils.io.ByteBufferChannel r8, short r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            boolean r0 = r10 instanceof io.ktor.utils.io.ByteBufferChannel$writeShort$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            io.ktor.utils.io.ByteBufferChannel$writeShort$1 r0 = (io.ktor.utils.io.ByteBufferChannel$writeShort$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$writeShort$1 r0 = new io.ktor.utils.io.ByteBufferChannel$writeShort$1
            r0.<init>(r8, r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            switch(r2) {
                case 0: goto L_0x006e;
                case 1: goto L_0x0069;
                case 2: goto L_0x0069;
                case 3: goto L_0x0069;
                case 4: goto L_0x0058;
                case 5: goto L_0x003d;
                case 6: goto L_0x0069;
                case 7: goto L_0x0069;
                case 8: goto L_0x002e;
                default: goto L_0x0026;
            }
        L_0x0026:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x002e:
            short r8 = r0.S$0
            java.lang.Object r9 = r0.L$1
            io.ktor.utils.io.ByteBufferChannel r9 = (io.ktor.utils.io.ByteBufferChannel) r9
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.internal.JoiningState r2 = (io.ktor.utils.io.internal.JoiningState) r2
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0121
        L_0x003d:
            int r8 = r0.I$0
            short r9 = r0.S$0
            java.lang.Object r2 = r0.L$2
            java.nio.ByteBuffer r2 = (java.nio.ByteBuffer) r2
            java.lang.Object r5 = r0.L$1
            io.ktor.utils.io.ByteBufferChannel r5 = (io.ktor.utils.io.ByteBufferChannel) r5
            java.lang.Object r6 = r0.L$0
            io.ktor.utils.io.internal.RingBufferCapacity r6 = (io.ktor.utils.io.internal.RingBufferCapacity) r6
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x0055 }
            r10 = r2
            r2 = r8
            r8 = r5
            goto L_0x00f4
        L_0x0055:
            r8 = move-exception
            goto L_0x0190
        L_0x0058:
            short r8 = r0.S$0
            java.lang.Object r9 = r0.L$1
            io.ktor.utils.io.ByteBufferChannel r9 = (io.ktor.utils.io.ByteBufferChannel) r9
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.internal.JoiningState r2 = (io.ktor.utils.io.internal.JoiningState) r2
            kotlin.ResultKt.throwOnFailure(r10)
            r7 = r9
            r9 = r8
            r8 = r7
            goto L_0x00a8
        L_0x0069:
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x01cb
        L_0x006e:
            kotlin.ResultKt.throwOnFailure(r10)
            io.ktor.utils.io.internal.JoiningState r10 = r8.joining
            if (r10 == 0) goto L_0x0085
            io.ktor.utils.io.ByteBufferChannel r10 = r8.resolveDelegation(r8, r10)
            if (r10 == 0) goto L_0x0085
            short r8 = (short) r9
            r0.label = r3
            java.lang.Object r8 = r10.writeShort(r8, r0)
            if (r8 != r1) goto L_0x01cb
            return r1
        L_0x0085:
            java.nio.ByteBuffer r10 = r8.setupStateForWrite$ktor_io()
            r2 = 2
            if (r10 != 0) goto L_0x00d3
            io.ktor.utils.io.internal.JoiningState r10 = r8.joining
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10)
            io.ktor.utils.io.internal.ReadWriteBufferState r5 = r8.getState()
            io.ktor.utils.io.internal.ReadWriteBufferState$Terminated r6 = io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE
            if (r5 != r6) goto L_0x00a7
            io.ktor.utils.io.ByteBufferChannel r8 = r10.getDelegatedTo()
            short r9 = (short) r9
            r0.label = r2
            java.lang.Object r8 = r8.writeShort(r9, r0)
            if (r8 != r1) goto L_0x01cb
            return r1
        L_0x00a7:
            r2 = r10
        L_0x00a8:
            io.ktor.utils.io.internal.ReadWriteBufferState r10 = r8.getState()
            io.ktor.utils.io.internal.ReadWriteBufferState$Terminated r5 = io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE
            if (r10 != r5) goto L_0x00c3
            io.ktor.utils.io.ByteBufferChannel r8 = r2.getDelegatedTo()
            short r9 = (short) r9
            r0.L$0 = r4
            r0.L$1 = r4
            r10 = 3
            r0.label = r10
            java.lang.Object r8 = r8.writeShort(r9, r0)
            if (r8 != r1) goto L_0x01cb
            return r1
        L_0x00c3:
            r0.L$0 = r2
            r0.L$1 = r8
            r0.S$0 = r9
            r10 = 4
            r0.label = r10
            java.lang.Object r10 = r8.writeSuspend(r3, r0)
            if (r10 != r1) goto L_0x00a8
            return r1
        L_0x00d3:
            io.ktor.utils.io.internal.ReadWriteBufferState r5 = r8.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r5 = r5.capacity
            boolean r6 = r5.tryWriteExact(r2)
            if (r6 != 0) goto L_0x0197
            r6 = r5
        L_0x00e0:
            r0.L$0 = r6     // Catch:{ all -> 0x018d }
            r0.L$1 = r8     // Catch:{ all -> 0x018d }
            r0.L$2 = r10     // Catch:{ all -> 0x018d }
            r0.S$0 = r9     // Catch:{ all -> 0x018d }
            r0.I$0 = r2     // Catch:{ all -> 0x018d }
            r5 = 5
            r0.label = r5     // Catch:{ all -> 0x018d }
            java.lang.Object r5 = r8.writeSuspend(r2, r0)     // Catch:{ all -> 0x018d }
            if (r5 != r1) goto L_0x00f4
            return r1
        L_0x00f4:
            io.ktor.utils.io.internal.JoiningState r5 = r8.joining
            if (r5 == 0) goto L_0x0151
            r8.restoreStateAfterWrite$ktor_io()
            io.ktor.utils.io.internal.JoiningState r10 = r8.joining
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10)
            io.ktor.utils.io.internal.ReadWriteBufferState r2 = r8.getState()
            io.ktor.utils.io.internal.ReadWriteBufferState$Terminated r5 = io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE
            if (r2 != r5) goto L_0x011d
            io.ktor.utils.io.ByteBufferChannel r8 = r10.getDelegatedTo()
            short r9 = (short) r9
            r0.L$0 = r4
            r0.L$1 = r4
            r0.L$2 = r4
            r10 = 6
            r0.label = r10
            java.lang.Object r8 = r8.writeShort(r9, r0)
            if (r8 != r1) goto L_0x01cb
            return r1
        L_0x011d:
            r2 = r10
            r7 = r9
            r9 = r8
            r8 = r7
        L_0x0121:
            io.ktor.utils.io.internal.ReadWriteBufferState r10 = r9.getState()
            io.ktor.utils.io.internal.ReadWriteBufferState$Terminated r5 = io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE
            if (r10 != r5) goto L_0x013e
            io.ktor.utils.io.ByteBufferChannel r9 = r2.getDelegatedTo()
            short r8 = (short) r8
            r0.L$0 = r4
            r0.L$1 = r4
            r0.L$2 = r4
            r10 = 7
            r0.label = r10
            java.lang.Object r8 = r9.writeShort(r8, r0)
            if (r8 != r1) goto L_0x01cb
            return r1
        L_0x013e:
            r0.L$0 = r2
            r0.L$1 = r9
            r0.L$2 = r4
            r0.S$0 = r8
            r10 = 8
            r0.label = r10
            java.lang.Object r10 = r9.writeSuspend(r3, r0)
            if (r10 != r1) goto L_0x0121
            return r1
        L_0x0151:
            boolean r5 = r6.tryWriteExact(r2)
            if (r5 != 0) goto L_0x0158
            goto L_0x00e0
        L_0x0158:
            r8.prepareWriteBuffer$ktor_io(r10, r2)
            int r0 = r10.remaining()
            if (r0 >= r2) goto L_0x0170
            int r0 = r10.capacity()
            r10.limit(r0)
            short r9 = (short) r9
            r10.putShort(r9)
            r8.carry(r10)
            goto L_0x0174
        L_0x0170:
            short r9 = (short) r9
            r10.putShort(r9)
        L_0x0174:
            r8.bytesWritten(r10, r6, r2)
            boolean r9 = r6.isFull()
            if (r9 != 0) goto L_0x0183
            boolean r9 = r8.getAutoFlush()
            if (r9 == 0) goto L_0x0186
        L_0x0183:
            r8.flush()
        L_0x0186:
            r8.restoreStateAfterWrite$ktor_io()
            r8.tryTerminate$ktor_io()
            goto L_0x01cb
        L_0x018d:
            r9 = move-exception
            r5 = r8
            r8 = r9
        L_0x0190:
            r5.restoreStateAfterWrite$ktor_io()
            r5.tryTerminate$ktor_io()
            throw r8
        L_0x0197:
            r8.prepareWriteBuffer$ktor_io(r10, r2)
            int r0 = r10.remaining()
            if (r0 >= r2) goto L_0x01af
            int r0 = r10.capacity()
            r10.limit(r0)
            short r9 = (short) r9
            r10.putShort(r9)
            r8.carry(r10)
            goto L_0x01b3
        L_0x01af:
            short r9 = (short) r9
            r10.putShort(r9)
        L_0x01b3:
            r8.bytesWritten(r10, r5, r2)
            boolean r9 = r5.isFull()
            if (r9 != 0) goto L_0x01c2
            boolean r9 = r8.getAutoFlush()
            if (r9 == 0) goto L_0x01c5
        L_0x01c2:
            r8.flush()
        L_0x01c5:
            r8.restoreStateAfterWrite$ktor_io()
            r8.tryTerminate$ktor_io()
        L_0x01cb:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeShort$suspendImpl(io.ktor.utils.io.ByteBufferChannel, short, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00ad, code lost:
        if (r8.getState() != io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE) goto L_0x00c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00af, code lost:
        r8 = r2.getDelegatedTo();
        r0.L$0 = null;
        r0.L$1 = null;
        r0.label = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00be, code lost:
        if (r8.writeInt(r9, r0) != r1) goto L_0x01c3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00c0, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00c1, code lost:
        r0.L$0 = r2;
        r0.L$1 = r8;
        r0.I$0 = r9;
        r0.label = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00cd, code lost:
        if (r8.writeSuspend(1, r0) != r1) goto L_0x00a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00cf, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        r0.L$0 = r6;
        r0.L$1 = r8;
        r0.L$2 = r2;
        r0.I$0 = r9;
        r0.I$1 = r3;
        r0.label = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00ef, code lost:
        if (r8.writeSuspend(r3, r0) != r1) goto L_0x00f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00f1, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00f4, code lost:
        if (r8.joining == null) goto L_0x014d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00f6, code lost:
        r8.restoreStateAfterWrite$ktor_io();
        r10 = r8.joining;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0104, code lost:
        if (r8.getState() != io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE) goto L_0x011a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0106, code lost:
        r8 = r10.getDelegatedTo();
        r0.L$0 = null;
        r0.L$1 = null;
        r0.L$2 = null;
        r0.label = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0117, code lost:
        if (r8.writeInt(r9, r0) != r1) goto L_0x01c3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0119, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x011a, code lost:
        r2 = r10;
        r7 = r9;
        r9 = r8;
        r8 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0124, code lost:
        if (r9.getState() != io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE) goto L_0x013a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0126, code lost:
        r9 = r2.getDelegatedTo();
        r0.L$0 = null;
        r0.L$1 = null;
        r0.L$2 = null;
        r0.label = 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0137, code lost:
        if (r9.writeInt(r8, r0) != r1) goto L_0x01c3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0139, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x013a, code lost:
        r0.L$0 = r2;
        r0.L$1 = r9;
        r0.L$2 = null;
        r0.I$0 = r8;
        r0.label = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x014a, code lost:
        if (r9.writeSuspend(1, r0) != r1) goto L_0x011e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x014c, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0151, code lost:
        if (r6.tryWriteExact(r3) != false) goto L_0x0154;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0154, code lost:
        r8.prepareWriteBuffer$ktor_io(r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x015b, code lost:
        if (r2.remaining() >= r3) goto L_0x016b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x015d, code lost:
        r2.limit(r2.capacity());
        r2.putInt(r9);
        r8.carry(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x016b, code lost:
        r2.putInt(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x016e, code lost:
        r8.bytesWritten(r2, r6, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0175, code lost:
        if (r6.isFull() != false) goto L_0x017d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x017b, code lost:
        if (r8.getAutoFlush() == false) goto L_0x0180;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x017d, code lost:
        r8.flush();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0180, code lost:
        r8.restoreStateAfterWrite$ktor_io();
        r8.tryTerminate$ktor_io();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0187, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0188, code lost:
        r3 = r8;
        r8 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01c5, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ java.lang.Object writeInt$suspendImpl(io.ktor.utils.io.ByteBufferChannel r8, int r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            boolean r0 = r10 instanceof io.ktor.utils.io.ByteBufferChannel$writeInt$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            io.ktor.utils.io.ByteBufferChannel$writeInt$1 r0 = (io.ktor.utils.io.ByteBufferChannel$writeInt$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$writeInt$1 r0 = new io.ktor.utils.io.ByteBufferChannel$writeInt$1
            r0.<init>(r8, r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 4
            r4 = 1
            r5 = 0
            switch(r2) {
                case 0: goto L_0x006f;
                case 1: goto L_0x006a;
                case 2: goto L_0x006a;
                case 3: goto L_0x006a;
                case 4: goto L_0x0059;
                case 5: goto L_0x003e;
                case 6: goto L_0x006a;
                case 7: goto L_0x006a;
                case 8: goto L_0x002f;
                default: goto L_0x0027;
            }
        L_0x0027:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x002f:
            int r8 = r0.I$0
            java.lang.Object r9 = r0.L$1
            io.ktor.utils.io.ByteBufferChannel r9 = (io.ktor.utils.io.ByteBufferChannel) r9
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.internal.JoiningState r2 = (io.ktor.utils.io.internal.JoiningState) r2
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x011e
        L_0x003e:
            int r8 = r0.I$1
            int r9 = r0.I$0
            java.lang.Object r2 = r0.L$2
            java.nio.ByteBuffer r2 = (java.nio.ByteBuffer) r2
            java.lang.Object r3 = r0.L$1
            io.ktor.utils.io.ByteBufferChannel r3 = (io.ktor.utils.io.ByteBufferChannel) r3
            java.lang.Object r6 = r0.L$0
            io.ktor.utils.io.internal.RingBufferCapacity r6 = (io.ktor.utils.io.internal.RingBufferCapacity) r6
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x0056 }
            r7 = r3
            r3 = r8
            r8 = r7
            goto L_0x00f2
        L_0x0056:
            r8 = move-exception
            goto L_0x018a
        L_0x0059:
            int r8 = r0.I$0
            java.lang.Object r9 = r0.L$1
            io.ktor.utils.io.ByteBufferChannel r9 = (io.ktor.utils.io.ByteBufferChannel) r9
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.internal.JoiningState r2 = (io.ktor.utils.io.internal.JoiningState) r2
            kotlin.ResultKt.throwOnFailure(r10)
            r7 = r9
            r9 = r8
            r8 = r7
            goto L_0x00a7
        L_0x006a:
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x01c3
        L_0x006f:
            kotlin.ResultKt.throwOnFailure(r10)
            io.ktor.utils.io.internal.JoiningState r10 = r8.joining
            if (r10 == 0) goto L_0x0085
            io.ktor.utils.io.ByteBufferChannel r10 = r8.resolveDelegation(r8, r10)
            if (r10 == 0) goto L_0x0085
            r0.label = r4
            java.lang.Object r8 = r10.writeInt(r9, r0)
            if (r8 != r1) goto L_0x01c3
            return r1
        L_0x0085:
            java.nio.ByteBuffer r10 = r8.setupStateForWrite$ktor_io()
            if (r10 != 0) goto L_0x00d0
            io.ktor.utils.io.internal.JoiningState r10 = r8.joining
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10)
            io.ktor.utils.io.internal.ReadWriteBufferState r2 = r8.getState()
            io.ktor.utils.io.internal.ReadWriteBufferState$Terminated r6 = io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE
            if (r2 != r6) goto L_0x00a6
            io.ktor.utils.io.ByteBufferChannel r8 = r10.getDelegatedTo()
            r10 = 2
            r0.label = r10
            java.lang.Object r8 = r8.writeInt(r9, r0)
            if (r8 != r1) goto L_0x01c3
            return r1
        L_0x00a6:
            r2 = r10
        L_0x00a7:
            io.ktor.utils.io.internal.ReadWriteBufferState r10 = r8.getState()
            io.ktor.utils.io.internal.ReadWriteBufferState$Terminated r6 = io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE
            if (r10 != r6) goto L_0x00c1
            io.ktor.utils.io.ByteBufferChannel r8 = r2.getDelegatedTo()
            r0.L$0 = r5
            r0.L$1 = r5
            r10 = 3
            r0.label = r10
            java.lang.Object r8 = r8.writeInt(r9, r0)
            if (r8 != r1) goto L_0x01c3
            return r1
        L_0x00c1:
            r0.L$0 = r2
            r0.L$1 = r8
            r0.I$0 = r9
            r0.label = r3
            java.lang.Object r10 = r8.writeSuspend(r4, r0)
            if (r10 != r1) goto L_0x00a7
            return r1
        L_0x00d0:
            io.ktor.utils.io.internal.ReadWriteBufferState r2 = r8.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r2 = r2.capacity
            boolean r6 = r2.tryWriteExact(r3)
            if (r6 != 0) goto L_0x0191
            r6 = r2
            r2 = r10
        L_0x00de:
            r0.L$0 = r6     // Catch:{ all -> 0x0187 }
            r0.L$1 = r8     // Catch:{ all -> 0x0187 }
            r0.L$2 = r2     // Catch:{ all -> 0x0187 }
            r0.I$0 = r9     // Catch:{ all -> 0x0187 }
            r0.I$1 = r3     // Catch:{ all -> 0x0187 }
            r10 = 5
            r0.label = r10     // Catch:{ all -> 0x0187 }
            java.lang.Object r10 = r8.writeSuspend(r3, r0)     // Catch:{ all -> 0x0187 }
            if (r10 != r1) goto L_0x00f2
            return r1
        L_0x00f2:
            io.ktor.utils.io.internal.JoiningState r10 = r8.joining
            if (r10 == 0) goto L_0x014d
            r8.restoreStateAfterWrite$ktor_io()
            io.ktor.utils.io.internal.JoiningState r10 = r8.joining
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10)
            io.ktor.utils.io.internal.ReadWriteBufferState r2 = r8.getState()
            io.ktor.utils.io.internal.ReadWriteBufferState$Terminated r3 = io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE
            if (r2 != r3) goto L_0x011a
            io.ktor.utils.io.ByteBufferChannel r8 = r10.getDelegatedTo()
            r0.L$0 = r5
            r0.L$1 = r5
            r0.L$2 = r5
            r10 = 6
            r0.label = r10
            java.lang.Object r8 = r8.writeInt(r9, r0)
            if (r8 != r1) goto L_0x01c3
            return r1
        L_0x011a:
            r2 = r10
            r7 = r9
            r9 = r8
            r8 = r7
        L_0x011e:
            io.ktor.utils.io.internal.ReadWriteBufferState r10 = r9.getState()
            io.ktor.utils.io.internal.ReadWriteBufferState$Terminated r3 = io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE
            if (r10 != r3) goto L_0x013a
            io.ktor.utils.io.ByteBufferChannel r9 = r2.getDelegatedTo()
            r0.L$0 = r5
            r0.L$1 = r5
            r0.L$2 = r5
            r10 = 7
            r0.label = r10
            java.lang.Object r8 = r9.writeInt(r8, r0)
            if (r8 != r1) goto L_0x01c3
            return r1
        L_0x013a:
            r0.L$0 = r2
            r0.L$1 = r9
            r0.L$2 = r5
            r0.I$0 = r8
            r10 = 8
            r0.label = r10
            java.lang.Object r10 = r9.writeSuspend(r4, r0)
            if (r10 != r1) goto L_0x011e
            return r1
        L_0x014d:
            boolean r10 = r6.tryWriteExact(r3)
            if (r10 != 0) goto L_0x0154
            goto L_0x00de
        L_0x0154:
            r8.prepareWriteBuffer$ktor_io(r2, r3)
            int r10 = r2.remaining()
            if (r10 >= r3) goto L_0x016b
            int r10 = r2.capacity()
            r2.limit(r10)
            r2.putInt(r9)
            r8.carry(r2)
            goto L_0x016e
        L_0x016b:
            r2.putInt(r9)
        L_0x016e:
            r8.bytesWritten(r2, r6, r3)
            boolean r9 = r6.isFull()
            if (r9 != 0) goto L_0x017d
            boolean r9 = r8.getAutoFlush()
            if (r9 == 0) goto L_0x0180
        L_0x017d:
            r8.flush()
        L_0x0180:
            r8.restoreStateAfterWrite$ktor_io()
            r8.tryTerminate$ktor_io()
            goto L_0x01c3
        L_0x0187:
            r9 = move-exception
            r3 = r8
            r8 = r9
        L_0x018a:
            r3.restoreStateAfterWrite$ktor_io()
            r3.tryTerminate$ktor_io()
            throw r8
        L_0x0191:
            r8.prepareWriteBuffer$ktor_io(r10, r3)
            int r0 = r10.remaining()
            if (r0 >= r3) goto L_0x01a8
            int r0 = r10.capacity()
            r10.limit(r0)
            r10.putInt(r9)
            r8.carry(r10)
            goto L_0x01ab
        L_0x01a8:
            r10.putInt(r9)
        L_0x01ab:
            r8.bytesWritten(r10, r2, r3)
            boolean r9 = r2.isFull()
            if (r9 != 0) goto L_0x01ba
            boolean r9 = r8.getAutoFlush()
            if (r9 == 0) goto L_0x01bd
        L_0x01ba:
            r8.flush()
        L_0x01bd:
            r8.restoreStateAfterWrite$ktor_io()
            r8.tryTerminate$ktor_io()
        L_0x01c3:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeInt$suspendImpl(io.ktor.utils.io.ByteBufferChannel, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00ae, code lost:
        if (r10.getState() != io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE) goto L_0x00c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00b0, code lost:
        r10 = r2.getDelegatedTo();
        r0.L$0 = null;
        r0.L$1 = null;
        r0.label = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00bf, code lost:
        if (r10.writeLong(r11, r0) != r1) goto L_0x01c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00c1, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00c2, code lost:
        r0.L$0 = r2;
        r0.L$1 = r10;
        r0.J$0 = r11;
        r0.label = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00cf, code lost:
        if (r10.writeSuspend(1, r0) != r1) goto L_0x00a8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00d1, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        r0.L$0 = r7;
        r0.L$1 = r10;
        r0.L$2 = r2;
        r0.J$0 = r12;
        r0.I$0 = r11;
        r0.label = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00f4, code lost:
        if (r10.writeSuspend(r11, r0) != r1) goto L_0x00f7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00f6, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00f9, code lost:
        if (r10.joining == null) goto L_0x0150;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00fb, code lost:
        r10.restoreStateAfterWrite$ktor_io();
        r11 = r10.joining;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0109, code lost:
        if (r10.getState() != io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE) goto L_0x011f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x010b, code lost:
        r10 = r11.getDelegatedTo();
        r0.L$0 = null;
        r0.L$1 = null;
        r0.L$2 = null;
        r0.label = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x011c, code lost:
        if (r10.writeLong(r12, r0) != r1) goto L_0x01c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x011e, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x011f, code lost:
        r2 = r11;
        r8 = r12;
        r12 = r10;
        r10 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0129, code lost:
        if (r12.getState() != io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE) goto L_0x013f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x012b, code lost:
        r12 = r2.getDelegatedTo();
        r0.L$0 = null;
        r0.L$1 = null;
        r0.L$2 = null;
        r0.label = 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x013c, code lost:
        if (r12.writeLong(r10, r0) != r1) goto L_0x01c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x013e, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x013f, code lost:
        r0.L$0 = r2;
        r0.L$1 = r12;
        r0.L$2 = null;
        r0.J$0 = r10;
        r0.label = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x014d, code lost:
        if (r12.writeSuspend(1, r0) != r1) goto L_0x0123;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x014f, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0154, code lost:
        if (r7.tryWriteExact(r11) != false) goto L_0x0157;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0157, code lost:
        r10.prepareWriteBuffer$ktor_io(r2, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x015e, code lost:
        if (r2.remaining() >= r11) goto L_0x016e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0160, code lost:
        r2.limit(r2.capacity());
        r2.putLong(r12);
        r10.carry(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x016e, code lost:
        r2.putLong(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0171, code lost:
        r10.bytesWritten(r2, r7, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0178, code lost:
        if (r7.isFull() != false) goto L_0x0180;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x017e, code lost:
        if (r10.getAutoFlush() == false) goto L_0x0183;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0180, code lost:
        r10.flush();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0183, code lost:
        r10.restoreStateAfterWrite$ktor_io();
        r10.tryTerminate$ktor_io();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x018a, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x018b, code lost:
        r6 = r10;
        r10 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01c8, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0030  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ java.lang.Object writeLong$suspendImpl(io.ktor.utils.io.ByteBufferChannel r10, long r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            boolean r0 = r13 instanceof io.ktor.utils.io.ByteBufferChannel$writeLong$1
            if (r0 == 0) goto L_0x0014
            r0 = r13
            io.ktor.utils.io.ByteBufferChannel$writeLong$1 r0 = (io.ktor.utils.io.ByteBufferChannel$writeLong$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$writeLong$1 r0 = new io.ktor.utils.io.ByteBufferChannel$writeLong$1
            r0.<init>(r10, r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 8
            r4 = 1
            r5 = 0
            switch(r2) {
                case 0: goto L_0x0070;
                case 1: goto L_0x006b;
                case 2: goto L_0x006b;
                case 3: goto L_0x006b;
                case 4: goto L_0x005a;
                case 5: goto L_0x003f;
                case 6: goto L_0x006b;
                case 7: goto L_0x006b;
                case 8: goto L_0x0030;
                default: goto L_0x0028;
            }
        L_0x0028:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0030:
            long r10 = r0.J$0
            java.lang.Object r12 = r0.L$1
            io.ktor.utils.io.ByteBufferChannel r12 = (io.ktor.utils.io.ByteBufferChannel) r12
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.internal.JoiningState r2 = (io.ktor.utils.io.internal.JoiningState) r2
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x0123
        L_0x003f:
            int r10 = r0.I$0
            long r11 = r0.J$0
            java.lang.Object r2 = r0.L$2
            java.nio.ByteBuffer r2 = (java.nio.ByteBuffer) r2
            java.lang.Object r6 = r0.L$1
            io.ktor.utils.io.ByteBufferChannel r6 = (io.ktor.utils.io.ByteBufferChannel) r6
            java.lang.Object r7 = r0.L$0
            io.ktor.utils.io.internal.RingBufferCapacity r7 = (io.ktor.utils.io.internal.RingBufferCapacity) r7
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ all -> 0x0057 }
            r12 = r11
            r11 = r10
            r10 = r6
            goto L_0x00f7
        L_0x0057:
            r10 = move-exception
            goto L_0x018d
        L_0x005a:
            long r10 = r0.J$0
            java.lang.Object r12 = r0.L$1
            io.ktor.utils.io.ByteBufferChannel r12 = (io.ktor.utils.io.ByteBufferChannel) r12
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.internal.JoiningState r2 = (io.ktor.utils.io.internal.JoiningState) r2
            kotlin.ResultKt.throwOnFailure(r13)
            r8 = r10
            r10 = r12
            r11 = r8
            goto L_0x00a8
        L_0x006b:
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x01c6
        L_0x0070:
            kotlin.ResultKt.throwOnFailure(r13)
            io.ktor.utils.io.internal.JoiningState r13 = r10.joining
            if (r13 == 0) goto L_0x0086
            io.ktor.utils.io.ByteBufferChannel r13 = r10.resolveDelegation(r10, r13)
            if (r13 == 0) goto L_0x0086
            r0.label = r4
            java.lang.Object r10 = r13.writeLong(r11, r0)
            if (r10 != r1) goto L_0x01c6
            return r1
        L_0x0086:
            java.nio.ByteBuffer r13 = r10.setupStateForWrite$ktor_io()
            if (r13 != 0) goto L_0x00d2
            io.ktor.utils.io.internal.JoiningState r13 = r10.joining
            kotlin.jvm.internal.Intrinsics.checkNotNull(r13)
            io.ktor.utils.io.internal.ReadWriteBufferState r2 = r10.getState()
            io.ktor.utils.io.internal.ReadWriteBufferState$Terminated r3 = io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE
            if (r2 != r3) goto L_0x00a7
            io.ktor.utils.io.ByteBufferChannel r10 = r13.getDelegatedTo()
            r13 = 2
            r0.label = r13
            java.lang.Object r10 = r10.writeLong(r11, r0)
            if (r10 != r1) goto L_0x01c6
            return r1
        L_0x00a7:
            r2 = r13
        L_0x00a8:
            io.ktor.utils.io.internal.ReadWriteBufferState r13 = r10.getState()
            io.ktor.utils.io.internal.ReadWriteBufferState$Terminated r3 = io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE
            if (r13 != r3) goto L_0x00c2
            io.ktor.utils.io.ByteBufferChannel r10 = r2.getDelegatedTo()
            r0.L$0 = r5
            r0.L$1 = r5
            r13 = 3
            r0.label = r13
            java.lang.Object r10 = r10.writeLong(r11, r0)
            if (r10 != r1) goto L_0x01c6
            return r1
        L_0x00c2:
            r0.L$0 = r2
            r0.L$1 = r10
            r0.J$0 = r11
            r13 = 4
            r0.label = r13
            java.lang.Object r13 = r10.writeSuspend(r4, r0)
            if (r13 != r1) goto L_0x00a8
            return r1
        L_0x00d2:
            io.ktor.utils.io.internal.ReadWriteBufferState r2 = r10.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r2 = r2.capacity
            boolean r6 = r2.tryWriteExact(r3)
            if (r6 != 0) goto L_0x0194
            r7 = r2
            r2 = r13
            r12 = r11
            r11 = 8
        L_0x00e3:
            r0.L$0 = r7     // Catch:{ all -> 0x018a }
            r0.L$1 = r10     // Catch:{ all -> 0x018a }
            r0.L$2 = r2     // Catch:{ all -> 0x018a }
            r0.J$0 = r12     // Catch:{ all -> 0x018a }
            r0.I$0 = r11     // Catch:{ all -> 0x018a }
            r6 = 5
            r0.label = r6     // Catch:{ all -> 0x018a }
            java.lang.Object r6 = r10.writeSuspend(r11, r0)     // Catch:{ all -> 0x018a }
            if (r6 != r1) goto L_0x00f7
            return r1
        L_0x00f7:
            io.ktor.utils.io.internal.JoiningState r6 = r10.joining
            if (r6 == 0) goto L_0x0150
            r10.restoreStateAfterWrite$ktor_io()
            io.ktor.utils.io.internal.JoiningState r11 = r10.joining
            kotlin.jvm.internal.Intrinsics.checkNotNull(r11)
            io.ktor.utils.io.internal.ReadWriteBufferState r2 = r10.getState()
            io.ktor.utils.io.internal.ReadWriteBufferState$Terminated r6 = io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE
            if (r2 != r6) goto L_0x011f
            io.ktor.utils.io.ByteBufferChannel r10 = r11.getDelegatedTo()
            r0.L$0 = r5
            r0.L$1 = r5
            r0.L$2 = r5
            r11 = 6
            r0.label = r11
            java.lang.Object r10 = r10.writeLong(r12, r0)
            if (r10 != r1) goto L_0x01c6
            return r1
        L_0x011f:
            r2 = r11
            r8 = r12
            r12 = r10
            r10 = r8
        L_0x0123:
            io.ktor.utils.io.internal.ReadWriteBufferState r13 = r12.getState()
            io.ktor.utils.io.internal.ReadWriteBufferState$Terminated r6 = io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE
            if (r13 != r6) goto L_0x013f
            io.ktor.utils.io.ByteBufferChannel r12 = r2.getDelegatedTo()
            r0.L$0 = r5
            r0.L$1 = r5
            r0.L$2 = r5
            r13 = 7
            r0.label = r13
            java.lang.Object r10 = r12.writeLong(r10, r0)
            if (r10 != r1) goto L_0x01c6
            return r1
        L_0x013f:
            r0.L$0 = r2
            r0.L$1 = r12
            r0.L$2 = r5
            r0.J$0 = r10
            r0.label = r3
            java.lang.Object r13 = r12.writeSuspend(r4, r0)
            if (r13 != r1) goto L_0x0123
            return r1
        L_0x0150:
            boolean r6 = r7.tryWriteExact(r11)
            if (r6 != 0) goto L_0x0157
            goto L_0x00e3
        L_0x0157:
            r10.prepareWriteBuffer$ktor_io(r2, r11)
            int r0 = r2.remaining()
            if (r0 >= r11) goto L_0x016e
            int r0 = r2.capacity()
            r2.limit(r0)
            r2.putLong(r12)
            r10.carry(r2)
            goto L_0x0171
        L_0x016e:
            r2.putLong(r12)
        L_0x0171:
            r10.bytesWritten(r2, r7, r11)
            boolean r11 = r7.isFull()
            if (r11 != 0) goto L_0x0180
            boolean r11 = r10.getAutoFlush()
            if (r11 == 0) goto L_0x0183
        L_0x0180:
            r10.flush()
        L_0x0183:
            r10.restoreStateAfterWrite$ktor_io()
            r10.tryTerminate$ktor_io()
            goto L_0x01c6
        L_0x018a:
            r11 = move-exception
            r6 = r10
            r10 = r11
        L_0x018d:
            r6.restoreStateAfterWrite$ktor_io()
            r6.tryTerminate$ktor_io()
            throw r10
        L_0x0194:
            r10.prepareWriteBuffer$ktor_io(r13, r3)
            int r0 = r13.remaining()
            if (r0 >= r3) goto L_0x01ab
            int r0 = r13.capacity()
            r13.limit(r0)
            r13.putLong(r11)
            r10.carry(r13)
            goto L_0x01ae
        L_0x01ab:
            r13.putLong(r11)
        L_0x01ae:
            r10.bytesWritten(r13, r2, r3)
            boolean r11 = r2.isFull()
            if (r11 != 0) goto L_0x01bd
            boolean r11 = r10.getAutoFlush()
            if (r11 == 0) goto L_0x01c0
        L_0x01bd:
            r10.flush()
        L_0x01c0:
            r10.restoreStateAfterWrite$ktor_io()
            r10.tryTerminate$ktor_io()
        L_0x01c6:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeLong$suspendImpl(io.ktor.utils.io.ByteBufferChannel, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object writeDouble$suspendImpl(ByteBufferChannel byteBufferChannel, double d, Continuation<? super Unit> continuation) {
        Object writeLong = byteBufferChannel.writeLong(Double.doubleToRawLongBits(d), continuation);
        return writeLong == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeLong : Unit.INSTANCE;
    }

    static /* synthetic */ Object writeFloat$suspendImpl(ByteBufferChannel byteBufferChannel, float f, Continuation<? super Unit> continuation) {
        Object writeInt = byteBufferChannel.writeInt(Float.floatToRawIntBits(f), continuation);
        return writeInt == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeInt : Unit.INSTANCE;
    }

    /* JADX INFO: finally extract failed */
    private final Object writePrimitive(int i, Function1<? super ByteBufferChannel, Unit> function1, Function1<? super ByteBuffer, Unit> function12, Continuation<? super Unit> continuation) {
        JoiningState joiningState = this.joining;
        if (joiningState != null) {
            JoiningState joiningState2 = joiningState;
            ByteBufferChannel resolveDelegation = resolveDelegation(this, joiningState);
            if (resolveDelegation != null) {
                ByteBufferChannel byteBufferChannel = resolveDelegation;
                function1.invoke(resolveDelegation);
                return Unit.INSTANCE;
            }
            Void voidR = null;
        }
        ByteBuffer byteBuffer = setupStateForWrite$ktor_io();
        if (byteBuffer != null) {
            RingBufferCapacity ringBufferCapacity = getState().capacity;
            if (!ringBufferCapacity.tryWriteExact(i)) {
                while (true) {
                    try {
                        InlineMarker.mark(0);
                        writeSuspend(i, continuation);
                        InlineMarker.mark(1);
                        if (this.joining != null) {
                            restoreStateAfterWrite$ktor_io();
                            JoiningState joiningState3 = this.joining;
                            Intrinsics.checkNotNull(joiningState3);
                            if (getState() == ReadWriteBufferState.Terminated.INSTANCE) {
                                function1.invoke(joiningState3.getDelegatedTo());
                                Unit unit = Unit.INSTANCE;
                            } else {
                                while (getState() != ReadWriteBufferState.Terminated.INSTANCE) {
                                    InlineMarker.mark(0);
                                    writeSuspend(1, continuation);
                                    InlineMarker.mark(1);
                                }
                                ByteBufferChannel delegatedTo = joiningState3.getDelegatedTo();
                                ByteBufferChannel byteBufferChannel2 = delegatedTo;
                                function1.invoke(delegatedTo);
                                Unit unit2 = Unit.INSTANCE;
                                Unit unit3 = Unit.INSTANCE;
                                Unit unit4 = Unit.INSTANCE;
                            }
                            Unit unit5 = Unit.INSTANCE;
                        } else if (ringBufferCapacity.tryWriteExact(i)) {
                            prepareWriteBuffer$ktor_io(byteBuffer, i);
                            ByteBuffer byteBuffer2 = byteBuffer;
                            if (byteBuffer.remaining() < i) {
                                byteBuffer.limit(byteBuffer.capacity());
                                function12.invoke(byteBuffer);
                                carry(byteBuffer);
                            } else {
                                function12.invoke(byteBuffer);
                            }
                            bytesWritten(byteBuffer, ringBufferCapacity, i);
                            Unit unit6 = Unit.INSTANCE;
                            if (ringBufferCapacity.isFull() || getAutoFlush()) {
                                flush();
                            }
                            restoreStateAfterWrite$ktor_io();
                            tryTerminate$ktor_io();
                            Unit unit7 = Unit.INSTANCE;
                        }
                    } catch (Throwable th) {
                        restoreStateAfterWrite$ktor_io();
                        tryTerminate$ktor_io();
                        throw th;
                    }
                }
                return Unit.INSTANCE;
            }
            prepareWriteBuffer$ktor_io(byteBuffer, i);
            ByteBuffer byteBuffer3 = byteBuffer;
            if (byteBuffer.remaining() < i) {
                byteBuffer.limit(byteBuffer.capacity());
                function12.invoke(byteBuffer);
                carry(byteBuffer);
            } else {
                function12.invoke(byteBuffer);
            }
            bytesWritten(byteBuffer, ringBufferCapacity, i);
            Unit unit8 = Unit.INSTANCE;
            if (ringBufferCapacity.isFull() || getAutoFlush()) {
                flush();
            }
            restoreStateAfterWrite$ktor_io();
            tryTerminate$ktor_io();
            return Unit.INSTANCE;
        }
        JoiningState joiningState4 = this.joining;
        Intrinsics.checkNotNull(joiningState4);
        if (getState() == ReadWriteBufferState.Terminated.INSTANCE) {
            function1.invoke(joiningState4.getDelegatedTo());
            Unit unit9 = Unit.INSTANCE;
        } else {
            while (getState() != ReadWriteBufferState.Terminated.INSTANCE) {
                InlineMarker.mark(0);
                writeSuspend(1, continuation);
                InlineMarker.mark(1);
            }
            ByteBufferChannel delegatedTo2 = joiningState4.getDelegatedTo();
            ByteBufferChannel byteBufferChannel3 = delegatedTo2;
            function1.invoke(delegatedTo2);
            Unit unit10 = Unit.INSTANCE;
            Unit unit11 = Unit.INSTANCE;
            Unit unit12 = Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    private final boolean tryWritePrimitive(ByteBuffer byteBuffer, int i, RingBufferCapacity ringBufferCapacity, Function1<? super ByteBuffer, Unit> function1) {
        if (!ringBufferCapacity.tryWriteExact(i)) {
            return false;
        }
        prepareWriteBuffer$ktor_io(byteBuffer, i);
        if (byteBuffer.remaining() < i) {
            byteBuffer.limit(byteBuffer.capacity());
            function1.invoke(byteBuffer);
            carry(byteBuffer);
        } else {
            function1.invoke(byteBuffer);
        }
        bytesWritten(byteBuffer, ringBufferCapacity, i);
        if (ringBufferCapacity.isFull() || getAutoFlush()) {
            flush();
        }
        restoreStateAfterWrite$ktor_io();
        tryTerminate$ktor_io();
        return true;
    }

    private final void doWritePrimitive(int i, ByteBuffer byteBuffer, RingBufferCapacity ringBufferCapacity, Function1<? super ByteBuffer, Unit> function1) {
        if (byteBuffer.remaining() < i) {
            byteBuffer.limit(byteBuffer.capacity());
            function1.invoke(byteBuffer);
            carry(byteBuffer);
        } else {
            function1.invoke(byteBuffer);
        }
        bytesWritten(byteBuffer, ringBufferCapacity, i);
        if (ringBufferCapacity.isFull() || getAutoFlush()) {
            flush();
        }
        restoreStateAfterWrite$ktor_io();
        tryTerminate$ktor_io();
    }

    /* JADX INFO: finally extract failed */
    private final Object writeSuspendPrimitive(ByteBuffer byteBuffer, int i, RingBufferCapacity ringBufferCapacity, Function1<? super ByteBufferChannel, Unit> function1, Function1<? super ByteBuffer, Unit> function12, Continuation<? super Unit> continuation) {
        do {
            try {
                InlineMarker.mark(0);
                writeSuspend(i, continuation);
                InlineMarker.mark(1);
                if (this.joining != null) {
                    restoreStateAfterWrite$ktor_io();
                    JoiningState joiningState = this.joining;
                    Intrinsics.checkNotNull(joiningState);
                    if (getState() == ReadWriteBufferState.Terminated.INSTANCE) {
                        function1.invoke(joiningState.getDelegatedTo());
                        Unit unit = Unit.INSTANCE;
                    } else {
                        while (getState() != ReadWriteBufferState.Terminated.INSTANCE) {
                            InlineMarker.mark(0);
                            writeSuspend(1, continuation);
                            InlineMarker.mark(1);
                        }
                        ByteBufferChannel delegatedTo = joiningState.getDelegatedTo();
                        ByteBufferChannel byteBufferChannel = delegatedTo;
                        function1.invoke(delegatedTo);
                        Unit unit2 = Unit.INSTANCE;
                        Unit unit3 = Unit.INSTANCE;
                        Unit unit4 = Unit.INSTANCE;
                    }
                    return Unit.INSTANCE;
                }
            } catch (Throwable th) {
                restoreStateAfterWrite$ktor_io();
                tryTerminate$ktor_io();
                throw th;
            }
        } while (!ringBufferCapacity.tryWriteExact(i));
        prepareWriteBuffer$ktor_io(byteBuffer, i);
        ByteBuffer byteBuffer2 = byteBuffer;
        if (byteBuffer.remaining() < i) {
            byteBuffer.limit(byteBuffer.capacity());
            function12.invoke(byteBuffer);
            carry(byteBuffer);
        } else {
            function12.invoke(byteBuffer);
        }
        bytesWritten(byteBuffer, ringBufferCapacity, i);
        Unit unit5 = Unit.INSTANCE;
        if (ringBufferCapacity.isFull() || getAutoFlush()) {
            flush();
        }
        restoreStateAfterWrite$ktor_io();
        tryTerminate$ktor_io();
        return Unit.INSTANCE;
    }

    private final Object delegatePrimitive(Function1<? super ByteBufferChannel, Unit> function1, Continuation<? super Unit> continuation) {
        JoiningState joiningState = this.joining;
        Intrinsics.checkNotNull(joiningState);
        if (getState() == ReadWriteBufferState.Terminated.INSTANCE) {
            function1.invoke(joiningState.getDelegatedTo());
            return Unit.INSTANCE;
        }
        while (getState() != ReadWriteBufferState.Terminated.INSTANCE) {
            InlineMarker.mark(0);
            writeSuspend(1, continuation);
            InlineMarker.mark(1);
        }
        ByteBufferChannel delegatedTo = joiningState.getDelegatedTo();
        ByteBufferChannel byteBufferChannel = delegatedTo;
        function1.invoke(delegatedTo);
        Unit unit = Unit.INSTANCE;
        Unit unit2 = Unit.INSTANCE;
        return Unit.INSTANCE;
    }

    static /* synthetic */ Object awaitFreeSpace$suspendImpl(ByteBufferChannel byteBufferChannel, Continuation<? super Unit> continuation) {
        Object writeSuspend = byteBufferChannel.writeSuspend(1, continuation);
        return writeSuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeSuspend : Unit.INSTANCE;
    }

    static /* synthetic */ Object writeAvailable$suspendImpl(ByteBufferChannel byteBufferChannel, ByteBuffer byteBuffer, Continuation<? super Integer> continuation) {
        ByteBufferChannel resolveDelegation;
        ByteBufferChannel resolveDelegation2;
        JoiningState joiningState = byteBufferChannel.joining;
        if (joiningState != null && (resolveDelegation2 = byteBufferChannel.resolveDelegation(byteBufferChannel, joiningState)) != null) {
            return resolveDelegation2.writeAvailable(byteBuffer, continuation);
        }
        int writeAsMuchAsPossible = byteBufferChannel.writeAsMuchAsPossible(byteBuffer);
        if (writeAsMuchAsPossible > 0) {
            return Boxing.boxInt(writeAsMuchAsPossible);
        }
        JoiningState joiningState2 = byteBufferChannel.joining;
        if (joiningState2 == null || (resolveDelegation = byteBufferChannel.resolveDelegation(byteBufferChannel, joiningState2)) == null) {
            return byteBufferChannel.writeAvailableSuspend(byteBuffer, continuation);
        }
        return resolveDelegation.writeAvailableSuspend(byteBuffer, continuation);
    }

    static /* synthetic */ Object writeAvailable$suspendImpl(ByteBufferChannel byteBufferChannel, ChunkBuffer chunkBuffer, Continuation<? super Integer> continuation) {
        ByteBufferChannel resolveDelegation;
        ByteBufferChannel resolveDelegation2;
        JoiningState joiningState = byteBufferChannel.joining;
        if (joiningState != null && (resolveDelegation2 = byteBufferChannel.resolveDelegation(byteBufferChannel, joiningState)) != null) {
            return resolveDelegation2.writeAvailable(chunkBuffer, continuation);
        }
        int writeAsMuchAsPossible = byteBufferChannel.writeAsMuchAsPossible((Buffer) chunkBuffer);
        if (writeAsMuchAsPossible > 0) {
            return Boxing.boxInt(writeAsMuchAsPossible);
        }
        JoiningState joiningState2 = byteBufferChannel.joining;
        if (joiningState2 == null || (resolveDelegation = byteBufferChannel.resolveDelegation(byteBufferChannel, joiningState2)) == null) {
            return byteBufferChannel.writeAvailableSuspend(chunkBuffer, continuation);
        }
        return resolveDelegation.writeAvailableSuspend(chunkBuffer, continuation);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object writeAvailableSuspend(java.nio.ByteBuffer r7, kotlin.coroutines.Continuation<? super java.lang.Integer> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof io.ktor.utils.io.ByteBufferChannel$writeAvailableSuspend$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ktor.utils.io.ByteBufferChannel$writeAvailableSuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel$writeAvailableSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$writeAvailableSuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$writeAvailableSuspend$1
            r0.<init>(r6, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0048
            if (r2 == r5) goto L_0x003c
            if (r2 == r4) goto L_0x0038
            if (r2 != r3) goto L_0x0030
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x007f
        L_0x0030:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0038:
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0071
        L_0x003c:
            java.lang.Object r7 = r0.L$1
            java.nio.ByteBuffer r7 = (java.nio.ByteBuffer) r7
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0059
        L_0x0048:
            kotlin.ResultKt.throwOnFailure(r8)
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r5
            java.lang.Object r8 = r6.writeSuspend(r5, r0)
            if (r8 != r1) goto L_0x0058
            return r1
        L_0x0058:
            r2 = r6
        L_0x0059:
            io.ktor.utils.io.internal.JoiningState r8 = r2.joining
            r5 = 0
            if (r8 == 0) goto L_0x0072
            io.ktor.utils.io.ByteBufferChannel r8 = r2.resolveDelegation(r2, r8)
            if (r8 == 0) goto L_0x0072
            r0.L$0 = r5
            r0.L$1 = r5
            r0.label = r4
            java.lang.Object r8 = r8.writeAvailableSuspend((java.nio.ByteBuffer) r7, (kotlin.coroutines.Continuation<? super java.lang.Integer>) r0)
            if (r8 != r1) goto L_0x0071
            return r1
        L_0x0071:
            return r8
        L_0x0072:
            r0.L$0 = r5
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r8 = r2.writeAvailable((java.nio.ByteBuffer) r7, (kotlin.coroutines.Continuation<? super java.lang.Integer>) r0)
            if (r8 != r1) goto L_0x007f
            return r1
        L_0x007f:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeAvailableSuspend(java.nio.ByteBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object writeAvailableSuspend(io.ktor.utils.io.core.internal.ChunkBuffer r7, kotlin.coroutines.Continuation<? super java.lang.Integer> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof io.ktor.utils.io.ByteBufferChannel$writeAvailableSuspend$3
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ktor.utils.io.ByteBufferChannel$writeAvailableSuspend$3 r0 = (io.ktor.utils.io.ByteBufferChannel$writeAvailableSuspend$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$writeAvailableSuspend$3 r0 = new io.ktor.utils.io.ByteBufferChannel$writeAvailableSuspend$3
            r0.<init>(r6, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0048
            if (r2 == r5) goto L_0x003c
            if (r2 == r4) goto L_0x0038
            if (r2 != r3) goto L_0x0030
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x007f
        L_0x0030:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0038:
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0071
        L_0x003c:
            java.lang.Object r7 = r0.L$1
            io.ktor.utils.io.core.internal.ChunkBuffer r7 = (io.ktor.utils.io.core.internal.ChunkBuffer) r7
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0059
        L_0x0048:
            kotlin.ResultKt.throwOnFailure(r8)
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r5
            java.lang.Object r8 = r6.writeSuspend(r5, r0)
            if (r8 != r1) goto L_0x0058
            return r1
        L_0x0058:
            r2 = r6
        L_0x0059:
            io.ktor.utils.io.internal.JoiningState r8 = r2.joining
            r5 = 0
            if (r8 == 0) goto L_0x0072
            io.ktor.utils.io.ByteBufferChannel r8 = r2.resolveDelegation(r2, r8)
            if (r8 == 0) goto L_0x0072
            r0.L$0 = r5
            r0.L$1 = r5
            r0.label = r4
            java.lang.Object r8 = r8.writeAvailableSuspend((io.ktor.utils.io.core.internal.ChunkBuffer) r7, (kotlin.coroutines.Continuation<? super java.lang.Integer>) r0)
            if (r8 != r1) goto L_0x0071
            return r1
        L_0x0071:
            return r8
        L_0x0072:
            r0.L$0 = r5
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r8 = r2.writeAvailable((io.ktor.utils.io.core.internal.ChunkBuffer) r7, (kotlin.coroutines.Continuation<? super java.lang.Integer>) r0)
            if (r8 != r1) goto L_0x007f
            return r1
        L_0x007f:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeAvailableSuspend(io.ktor.utils.io.core.internal.ChunkBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object writeFully$suspendImpl(ByteBufferChannel byteBufferChannel, ByteBuffer byteBuffer, Continuation<? super Unit> continuation) {
        ByteBufferChannel resolveDelegation;
        JoiningState joiningState = byteBufferChannel.joining;
        if (joiningState == null || (resolveDelegation = byteBufferChannel.resolveDelegation(byteBufferChannel, joiningState)) == null) {
            byteBufferChannel.writeAsMuchAsPossible(byteBuffer);
            if (!byteBuffer.hasRemaining()) {
                return Unit.INSTANCE;
            }
            Object writeFullySuspend = byteBufferChannel.writeFullySuspend(byteBuffer, continuation);
            return writeFullySuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeFullySuspend : Unit.INSTANCE;
        }
        Object writeFully = resolveDelegation.writeFully(byteBuffer, continuation);
        return writeFully == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeFully : Unit.INSTANCE;
    }

    static /* synthetic */ Object writeFully$suspendImpl(ByteBufferChannel byteBufferChannel, Buffer buffer, Continuation<? super Unit> continuation) {
        byteBufferChannel.writeAsMuchAsPossible(buffer);
        if (buffer.getWritePosition() <= buffer.getReadPosition()) {
            return Unit.INSTANCE;
        }
        Object writeFullySuspend = byteBufferChannel.writeFullySuspend(buffer, continuation);
        return writeFullySuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeFullySuspend : Unit.INSTANCE;
    }

    /* renamed from: writeFully-JT6ljtQ$suspendImpl  reason: not valid java name */
    static /* synthetic */ Object m147writeFullyJT6ljtQ$suspendImpl(ByteBufferChannel byteBufferChannel, ByteBuffer byteBuffer, int i, int i2, Continuation<? super Unit> continuation) {
        Object writeFully = byteBufferChannel.writeFully(Memory.m1519slice87lwejk(byteBuffer, i, i2 - i), continuation);
        return writeFully == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeFully : Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object writeFullySuspend(java.nio.ByteBuffer r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$1
            r0.<init>(r5, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0041
            if (r2 == r4) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0070
        L_0x002d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0035:
            java.lang.Object r6 = r0.L$1
            java.nio.ByteBuffer r6 = (java.nio.ByteBuffer) r6
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0058
        L_0x0041:
            kotlin.ResultKt.throwOnFailure(r7)
            r2 = r5
        L_0x0045:
            boolean r7 = r6.hasRemaining()
            if (r7 == 0) goto L_0x0077
            r0.L$0 = r2
            r0.L$1 = r6
            r0.label = r4
            java.lang.Object r7 = r2.tryWriteSuspend$ktor_io(r4, r0)
            if (r7 != r1) goto L_0x0058
            return r1
        L_0x0058:
            io.ktor.utils.io.internal.JoiningState r7 = r2.joining
            if (r7 == 0) goto L_0x0073
            io.ktor.utils.io.ByteBufferChannel r7 = r2.resolveDelegation(r2, r7)
            if (r7 == 0) goto L_0x0073
            r2 = 0
            r0.L$0 = r2
            r0.L$1 = r2
            r0.label = r3
            java.lang.Object r6 = r7.writeFully((java.nio.ByteBuffer) r6, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r0)
            if (r6 != r1) goto L_0x0070
            return r1
        L_0x0070:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L_0x0073:
            r2.writeAsMuchAsPossible((java.nio.ByteBuffer) r6)
            goto L_0x0045
        L_0x0077:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeFullySuspend(java.nio.ByteBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object writeFullySuspend(io.ktor.utils.io.core.Buffer r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$3
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$3 r0 = (io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$3 r0 = new io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$3
            r0.<init>(r6, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0041
            if (r2 == r4) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0074
        L_0x002d:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0035:
            java.lang.Object r7 = r0.L$1
            io.ktor.utils.io.core.Buffer r7 = (io.ktor.utils.io.core.Buffer) r7
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x005c
        L_0x0041:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r6
        L_0x0045:
            int r8 = r7.getWritePosition()
            int r5 = r7.getReadPosition()
            if (r8 <= r5) goto L_0x007b
            r0.L$0 = r2
            r0.L$1 = r7
            r0.label = r4
            java.lang.Object r8 = r2.tryWriteSuspend$ktor_io(r4, r0)
            if (r8 != r1) goto L_0x005c
            return r1
        L_0x005c:
            io.ktor.utils.io.internal.JoiningState r8 = r2.joining
            if (r8 == 0) goto L_0x0077
            io.ktor.utils.io.ByteBufferChannel r8 = r2.resolveDelegation(r2, r8)
            if (r8 == 0) goto L_0x0077
            r2 = 0
            r0.L$0 = r2
            r0.L$1 = r2
            r0.label = r3
            java.lang.Object r7 = r8.writeFully((io.ktor.utils.io.core.Buffer) r7, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r0)
            if (r7 != r1) goto L_0x0074
            return r1
        L_0x0074:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L_0x0077:
            r2.writeAsMuchAsPossible((io.ktor.utils.io.core.Buffer) r7)
            goto L_0x0045
        L_0x007b:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeFullySuspend(io.ktor.utils.io.core.Buffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public final Object awaitClose(Continuation<? super Unit> continuation) {
        if (getClosed() != null) {
            return Unit.INSTANCE;
        }
        JoiningState joiningState = this.joining;
        if (joiningState != null) {
            Object awaitClose = joiningState.awaitClose(continuation);
            return awaitClose == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? awaitClose : Unit.INSTANCE;
        } else if (getClosed() != null) {
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("Only works for joined.".toString());
        }
    }

    public final Object joinFrom$ktor_io(ByteBufferChannel byteBufferChannel, boolean z, Continuation<? super Unit> continuation) {
        if (byteBufferChannel.getClosed() == null || byteBufferChannel.getState() != ReadWriteBufferState.Terminated.INSTANCE) {
            ClosedElement closed = getClosed();
            if (closed == null) {
                JoiningState joiningState = byteBufferChannel.setupDelegateTo(this, z);
                if (byteBufferChannel.tryCompleteJoining(joiningState)) {
                    Object awaitClose = byteBufferChannel.awaitClose(continuation);
                    if (awaitClose == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        return awaitClose;
                    }
                    return Unit.INSTANCE;
                }
                Object joinFromSuspend = joinFromSuspend(byteBufferChannel, z, joiningState, continuation);
                return joinFromSuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? joinFromSuspend : Unit.INSTANCE;
            } else if (byteBufferChannel.getClosed() != null) {
                return Unit.INSTANCE;
            } else {
                Void unused = ByteBufferChannelKt.rethrowClosed(closed.getSendException());
                throw new KotlinNothingValueException();
            }
        } else {
            if (z) {
                ClosedElement closed2 = byteBufferChannel.getClosed();
                Intrinsics.checkNotNull(closed2);
                close(closed2.getCause());
            }
            return Unit.INSTANCE;
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object joinFromSuspend(io.ktor.utils.io.ByteBufferChannel r10, boolean r11, io.ktor.utils.io.internal.JoiningState r12, kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            r9 = this;
            boolean r0 = r13 instanceof io.ktor.utils.io.ByteBufferChannel$joinFromSuspend$1
            if (r0 == 0) goto L_0x0014
            r0 = r13
            io.ktor.utils.io.ByteBufferChannel$joinFromSuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel$joinFromSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$joinFromSuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$joinFromSuspend$1
            r0.<init>(r9, r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r0.label
            r8 = 2
            r2 = 1
            if (r1 == 0) goto L_0x0043
            if (r1 == r2) goto L_0x0035
            if (r1 != r8) goto L_0x002d
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x0080
        L_0x002d:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0035:
            boolean r11 = r0.Z$0
            java.lang.Object r10 = r0.L$1
            io.ktor.utils.io.ByteBufferChannel r10 = (io.ktor.utils.io.ByteBufferChannel) r10
            java.lang.Object r12 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r12 = (io.ktor.utils.io.ByteBufferChannel) r12
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x005f
        L_0x0043:
            kotlin.ResultKt.throwOnFailure(r13)
            r0.L$0 = r9
            r0.L$1 = r10
            r0.Z$0 = r11
            r0.label = r2
            r3 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r1 = r9
            r2 = r10
            r5 = r12
            r6 = r0
            java.lang.Object r12 = r1.copyDirect$ktor_io(r2, r3, r5, r6)
            if (r12 != r7) goto L_0x005e
            return r7
        L_0x005e:
            r12 = r9
        L_0x005f:
            if (r11 == 0) goto L_0x006f
            boolean r11 = r10.isClosedForRead()
            if (r11 == 0) goto L_0x006f
            io.ktor.utils.io.ByteWriteChannel r12 = (io.ktor.utils.io.ByteWriteChannel) r12
            io.ktor.utils.io.ByteWriteChannelKt.close(r12)
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        L_0x006f:
            r12.flush()
            r11 = 0
            r0.L$0 = r11
            r0.L$1 = r11
            r0.label = r8
            java.lang.Object r10 = r10.awaitClose(r0)
            if (r10 != r7) goto L_0x0080
            return r7
        L_0x0080:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.joinFromSuspend(io.ktor.utils.io.ByteBufferChannel, boolean, io.ktor.utils.io.internal.JoiningState, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v55, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v12, resolved type: io.ktor.utils.io.ByteBufferChannel} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x025e A[Catch:{ all -> 0x02a3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x026d A[Catch:{ all -> 0x02a3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x02d9  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x02ff A[Catch:{ all -> 0x03c7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x031c A[SYNTHETIC, Splitter:B:154:0x031c] */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x0341 A[Catch:{ all -> 0x007c }] */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x037b A[ADDED_TO_REGION, Catch:{ all -> 0x007c }] */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x0384 A[Catch:{ all -> 0x007c }] */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x039d A[Catch:{ all -> 0x007c }] */
    /* JADX WARNING: Removed duplicated region for block: B:206:0x03f9 A[Catch:{ all -> 0x007c }] */
    /* JADX WARNING: Removed duplicated region for block: B:210:0x040f A[Catch:{ all -> 0x007c }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x011e A[Catch:{ all -> 0x007c }] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x015e A[Catch:{ all -> 0x03cb }] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x018d A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x018e  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x01bc  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x01e5 A[Catch:{ all -> 0x02c8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x01f4 A[Catch:{ all -> 0x02c8 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object copyDirect$ktor_io(io.ktor.utils.io.ByteBufferChannel r26, long r27, io.ktor.utils.io.internal.JoiningState r29, kotlin.coroutines.Continuation<? super java.lang.Long> r30) {
        /*
            r25 = this;
            r1 = r25
            r0 = r26
            r2 = r29
            r3 = r30
            boolean r4 = r3 instanceof io.ktor.utils.io.ByteBufferChannel$copyDirect$1
            if (r4 == 0) goto L_0x001c
            r4 = r3
            io.ktor.utils.io.ByteBufferChannel$copyDirect$1 r4 = (io.ktor.utils.io.ByteBufferChannel$copyDirect$1) r4
            int r5 = r4.label
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r5 & r6
            if (r5 == 0) goto L_0x001c
            int r3 = r4.label
            int r3 = r3 - r6
            r4.label = r3
            goto L_0x0021
        L_0x001c:
            io.ktor.utils.io.ByteBufferChannel$copyDirect$1 r4 = new io.ktor.utils.io.ByteBufferChannel$copyDirect$1
            r4.<init>(r1, r3)
        L_0x0021:
            java.lang.Object r3 = r4.result
            java.lang.Object r5 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r6 = r4.label
            r7 = 3
            r8 = 2
            r9 = 1
            if (r6 == 0) goto L_0x00c9
            if (r6 == r9) goto L_0x007f
            if (r6 == r8) goto L_0x005f
            if (r6 != r7) goto L_0x0057
            boolean r0 = r4.Z$0
            long r11 = r4.J$0
            java.lang.Object r2 = r4.L$3
            kotlin.jvm.internal.Ref$LongRef r2 = (kotlin.jvm.internal.Ref.LongRef) r2
            java.lang.Object r6 = r4.L$2
            io.ktor.utils.io.internal.JoiningState r6 = (io.ktor.utils.io.internal.JoiningState) r6
            java.lang.Object r13 = r4.L$1
            io.ktor.utils.io.ByteBufferChannel r13 = (io.ktor.utils.io.ByteBufferChannel) r13
            java.lang.Object r14 = r4.L$0
            io.ktor.utils.io.ByteBufferChannel r14 = (io.ktor.utils.io.ByteBufferChannel) r14
            kotlin.ResultKt.throwOnFailure(r3)     // Catch:{ all -> 0x007c }
            r8 = r2
            r7 = r4
            r4 = r6
            r2 = r11
            r1 = 0
            r9 = 3
            r10 = 1
            r6 = r0
            r0 = r5
            r5 = 2
            goto L_0x03c1
        L_0x0057:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x005f:
            boolean r0 = r4.Z$0
            long r11 = r4.J$0
            java.lang.Object r2 = r4.L$3
            kotlin.jvm.internal.Ref$LongRef r2 = (kotlin.jvm.internal.Ref.LongRef) r2
            java.lang.Object r6 = r4.L$2
            io.ktor.utils.io.internal.JoiningState r6 = (io.ktor.utils.io.internal.JoiningState) r6
            java.lang.Object r13 = r4.L$1
            io.ktor.utils.io.ByteBufferChannel r13 = (io.ktor.utils.io.ByteBufferChannel) r13
            java.lang.Object r14 = r4.L$0
            io.ktor.utils.io.ByteBufferChannel r14 = (io.ktor.utils.io.ByteBufferChannel) r14
            kotlin.ResultKt.throwOnFailure(r3)     // Catch:{ all -> 0x007c }
            r1 = r6
            r6 = r0
            r0 = r5
            r5 = 2
            goto L_0x0373
        L_0x007c:
            r0 = move-exception
            goto L_0x041c
        L_0x007f:
            long r11 = r4.J$1
            boolean r0 = r4.Z$0
            long r13 = r4.J$0
            java.lang.Object r2 = r4.L$9
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            java.lang.Object r6 = r4.L$8
            java.nio.ByteBuffer r6 = (java.nio.ByteBuffer) r6
            java.lang.Object r15 = r4.L$7
            io.ktor.utils.io.internal.RingBufferCapacity r15 = (io.ktor.utils.io.internal.RingBufferCapacity) r15
            java.lang.Object r7 = r4.L$6
            io.ktor.utils.io.internal.RingBufferCapacity r7 = (io.ktor.utils.io.internal.RingBufferCapacity) r7
            java.lang.Object r8 = r4.L$5
            io.ktor.utils.io.ByteBufferChannel r8 = (io.ktor.utils.io.ByteBufferChannel) r8
            java.lang.Object r10 = r4.L$4
            io.ktor.utils.io.ByteBufferChannel r10 = (io.ktor.utils.io.ByteBufferChannel) r10
            java.lang.Object r9 = r4.L$3
            kotlin.jvm.internal.Ref$LongRef r9 = (kotlin.jvm.internal.Ref.LongRef) r9
            r26 = r0
            java.lang.Object r0 = r4.L$2
            io.ktor.utils.io.internal.JoiningState r0 = (io.ktor.utils.io.internal.JoiningState) r0
            r27 = r0
            java.lang.Object r0 = r4.L$1
            io.ktor.utils.io.ByteBufferChannel r0 = (io.ktor.utils.io.ByteBufferChannel) r0
            r28 = r0
            java.lang.Object r0 = r4.L$0
            r16 = r0
            io.ktor.utils.io.ByteBufferChannel r16 = (io.ktor.utils.io.ByteBufferChannel) r16
            kotlin.ResultKt.throwOnFailure(r3)     // Catch:{ all -> 0x00c4 }
            r17 = r27
            r0 = r28
            r18 = r13
            r14 = r10
            r10 = r6
            r6 = r26
            goto L_0x019a
        L_0x00c4:
            r0 = move-exception
        L_0x00c5:
            r14 = r16
            goto L_0x03e8
        L_0x00c9:
            kotlin.ResultKt.throwOnFailure(r3)
            boolean r3 = r26.isClosedForRead()
            r6 = 0
            if (r3 == 0) goto L_0x00fb
            if (r2 == 0) goto L_0x00e9
            boolean r2 = r0.tryCompleteJoining(r2)
            if (r2 == 0) goto L_0x00dd
            goto L_0x00e9
        L_0x00dd:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "Check failed."
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        L_0x00e9:
            java.lang.Throwable r2 = r26.getClosedCause()
            if (r2 == 0) goto L_0x00f6
            java.lang.Throwable r0 = r26.getClosedCause()
            r1.close(r0)
        L_0x00f6:
            java.lang.Long r0 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r6)
            return r0
        L_0x00fb:
            if (r2 == 0) goto L_0x0108
            boolean r3 = r0.tryCompleteJoining(r2)
            if (r3 == 0) goto L_0x0108
            java.lang.Long r0 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r6)
            return r0
        L_0x0108:
            boolean r3 = r25.getAutoFlush()
            kotlin.jvm.internal.Ref$LongRef r6 = new kotlin.jvm.internal.Ref$LongRef     // Catch:{ all -> 0x0419 }
            r6.<init>()     // Catch:{ all -> 0x0419 }
            r14 = r1
            r7 = r4
            r8 = r6
            r4 = r2
            r6 = r3
            r2 = r27
        L_0x0118:
            long r9 = r8.element     // Catch:{ all -> 0x007c }
            int r11 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
            if (r11 >= 0) goto L_0x040d
            io.ktor.utils.io.internal.JoiningState r9 = r14.joining     // Catch:{ all -> 0x007c }
            if (r9 == 0) goto L_0x0128
            io.ktor.utils.io.ByteBufferChannel r9 = r14.resolveDelegation(r14, r9)     // Catch:{ all -> 0x007c }
            if (r9 != 0) goto L_0x0129
        L_0x0128:
            r9 = r14
        L_0x0129:
            java.nio.ByteBuffer r10 = r9.setupStateForWrite$ktor_io()     // Catch:{ all -> 0x007c }
            if (r10 != 0) goto L_0x0135
            r1 = r4
            r3 = r2
            r2 = r0
            r0 = r5
            goto L_0x031a
        L_0x0135:
            io.ktor.utils.io.internal.ReadWriteBufferState r11 = r9.getState()     // Catch:{ all -> 0x007c }
            io.ktor.utils.io.internal.RingBufferCapacity r11 = r11.capacity     // Catch:{ all -> 0x007c }
            long r12 = r9.getTotalBytesWritten()     // Catch:{ all -> 0x007c }
            io.ktor.utils.io.internal.ClosedElement r15 = r9.getClosed()     // Catch:{ all -> 0x03e3 }
            if (r15 != 0) goto L_0x03d6
            r15 = r9
            io.ktor.utils.io.ByteBufferChannel r15 = (io.ktor.utils.io.ByteBufferChannel) r15     // Catch:{ all -> 0x03e3 }
            r16 = r5
            r28 = r6
            r15 = r11
            r26 = r12
            r12 = r2
            r2 = r9
            r3 = r14
            r9 = r8
            r8 = r2
        L_0x0154:
            long r5 = r9.element     // Catch:{ all -> 0x03cb }
            int r17 = (r5 > r12 ? 1 : (r5 == r12 ? 0 : -1))
            if (r17 >= 0) goto L_0x02d9
            int r5 = r15._availableForWrite$internal     // Catch:{ all -> 0x03cb }
            if (r5 != 0) goto L_0x01bc
            r7.L$0 = r3     // Catch:{ all -> 0x03cb }
            r7.L$1 = r0     // Catch:{ all -> 0x03cb }
            r7.L$2 = r4     // Catch:{ all -> 0x03cb }
            r7.L$3 = r9     // Catch:{ all -> 0x03cb }
            r7.L$4 = r14     // Catch:{ all -> 0x03cb }
            r7.L$5 = r8     // Catch:{ all -> 0x03cb }
            r7.L$6 = r11     // Catch:{ all -> 0x03cb }
            r7.L$7 = r15     // Catch:{ all -> 0x03cb }
            r7.L$8 = r10     // Catch:{ all -> 0x03cb }
            r7.L$9 = r2     // Catch:{ all -> 0x03cb }
            r7.J$0 = r12     // Catch:{ all -> 0x03cb }
            r6 = r28
            r7.Z$0 = r6     // Catch:{ all -> 0x03cb }
            r28 = r3
            r17 = r4
            r3 = r26
            r7.J$1 = r3     // Catch:{ all -> 0x01b9 }
            r5 = 1
            r7.label = r5     // Catch:{ all -> 0x01b9 }
            r26 = r0
            java.lang.Object r0 = r2.tryWriteSuspend$ktor_io(r5, r7)     // Catch:{ all -> 0x01b9 }
            r5 = r16
            if (r0 != r5) goto L_0x018e
            return r5
        L_0x018e:
            r0 = r26
            r16 = r28
            r18 = r12
            r23 = r3
            r4 = r7
            r7 = r11
            r11 = r23
        L_0x019a:
            io.ktor.utils.io.internal.JoiningState r3 = r2.joining     // Catch:{ all -> 0x01b5 }
            if (r3 != 0) goto L_0x01ac
            int r3 = r15._availableForWrite$internal     // Catch:{ all -> 0x01b5 }
            r13 = r7
            r7 = r4
            r4 = r17
            r23 = r2
            r2 = r0
            r0 = r5
            r5 = r3
            r3 = r23
            goto L_0x01d3
        L_0x01ac:
            r2 = r0
            r0 = r5
            r12 = r11
            r11 = r7
            r7 = r4
        L_0x01b1:
            r4 = r17
            goto L_0x02ee
        L_0x01b5:
            r0 = move-exception
        L_0x01b6:
            r10 = r14
            goto L_0x00c5
        L_0x01b9:
            r0 = move-exception
            goto L_0x03d0
        L_0x01bc:
            r6 = r28
            r28 = r3
            r17 = r4
            r3 = r26
            r26 = r0
            r0 = r16
            r16 = r28
            r18 = r12
            r13 = r11
            r11 = r3
            r4 = r17
            r3 = r2
            r2 = r26
        L_0x01d3:
            int r1 = r3.writePosition     // Catch:{ all -> 0x02c8 }
            r3.prepareBuffer(r10, r1, r5)     // Catch:{ all -> 0x02c8 }
            kotlin.jvm.internal.Ref$IntRef r1 = new kotlin.jvm.internal.Ref$IntRef     // Catch:{ all -> 0x02c8 }
            r1.<init>()     // Catch:{ all -> 0x02c8 }
            r26 = r4
            java.nio.ByteBuffer r4 = r2.setupStateForRead()     // Catch:{ all -> 0x02c8 }
            if (r4 != 0) goto L_0x01f4
            r22 = r5
            r27 = r7
            r28 = r8
            r20 = r11
            r29 = r13
            r17 = r14
            r8 = r6
            goto L_0x025a
        L_0x01f4:
            r27 = r7
            io.ktor.utils.io.internal.ReadWriteBufferState r7 = r2.getState()     // Catch:{ all -> 0x02c8 }
            io.ktor.utils.io.internal.RingBufferCapacity r7 = r7.capacity     // Catch:{ all -> 0x02c8 }
            r28 = r8
            int r8 = r7._availableForRead$internal     // Catch:{ all -> 0x02ba }
            if (r8 != 0) goto L_0x0217
            r2.restoreStateAfterRead()     // Catch:{ all -> 0x0212 }
            r2.tryTerminate$ktor_io()     // Catch:{ all -> 0x0212 }
            r22 = r5
            r8 = r6
            r20 = r11
            r29 = r13
            r17 = r14
            goto L_0x025a
        L_0x0212:
            r0 = move-exception
            r8 = r28
            r7 = r13
            goto L_0x01b6
        L_0x0217:
            int r8 = r4.remaining()     // Catch:{ all -> 0x02ba }
            r20 = r11
            long r11 = (long) r8
            int r8 = r10.remaining()     // Catch:{ all -> 0x02b8 }
            r29 = r13
            r17 = r14
            long r13 = (long) r8
            r22 = r5
            r8 = r6
            long r5 = r9.element     // Catch:{ all -> 0x02b6 }
            long r5 = r18 - r5
            long r5 = java.lang.Math.min(r13, r5)     // Catch:{ all -> 0x02b6 }
            long r5 = java.lang.Math.min(r11, r5)     // Catch:{ all -> 0x02b6 }
            int r6 = (int) r5     // Catch:{ all -> 0x02b6 }
            int r5 = r15.tryWriteAtMost(r6)     // Catch:{ all -> 0x02b6 }
            if (r5 > 0) goto L_0x023e
            goto L_0x0254
        L_0x023e:
            boolean r6 = r7.tryReadExact(r5)     // Catch:{ all -> 0x02b6 }
            if (r6 == 0) goto L_0x02b0
            int r6 = r4.position()     // Catch:{ all -> 0x02b6 }
            int r6 = r6 + r5
            r4.limit(r6)     // Catch:{ all -> 0x02b6 }
            r10.put(r4)     // Catch:{ all -> 0x02b6 }
            r1.element = r5     // Catch:{ all -> 0x02b6 }
            r2.bytesRead(r4, r7, r5)     // Catch:{ all -> 0x02b6 }
        L_0x0254:
            r2.restoreStateAfterRead()     // Catch:{ all -> 0x02a3 }
            r2.tryTerminate$ktor_io()     // Catch:{ all -> 0x02a3 }
        L_0x025a:
            int r4 = r1.element     // Catch:{ all -> 0x02a3 }
            if (r4 > 0) goto L_0x026d
            r4 = r26
            r7 = r27
            r11 = r29
            r6 = r8
            r14 = r17
            r12 = r20
            r8 = r28
            goto L_0x02ee
        L_0x026d:
            int r4 = r1.element     // Catch:{ all -> 0x02a3 }
            r3.bytesWritten(r10, r15, r4)     // Catch:{ all -> 0x02a3 }
            long r4 = r9.element     // Catch:{ all -> 0x02a3 }
            int r6 = r1.element     // Catch:{ all -> 0x02a3 }
            long r6 = (long) r6     // Catch:{ all -> 0x02a3 }
            long r4 = r4 + r6
            r9.element = r4     // Catch:{ all -> 0x02a3 }
            int r1 = r1.element     // Catch:{ all -> 0x02a3 }
            int r5 = r22 - r1
            if (r5 == 0) goto L_0x0282
            if (r8 == 0) goto L_0x0285
        L_0x0282:
            r3.flush()     // Catch:{ all -> 0x02a3 }
        L_0x0285:
            r1 = r25
            r4 = r26
            r7 = r27
            r11 = r29
            r14 = r17
            r12 = r18
            r26 = r20
            r23 = r8
            r8 = r28
            r28 = r23
            r24 = r16
            r16 = r0
            r0 = r2
            r2 = r3
            r3 = r24
            goto L_0x0154
        L_0x02a3:
            r0 = move-exception
            r8 = r28
            r7 = r29
            r14 = r16
            r10 = r17
            r11 = r20
            goto L_0x03e8
        L_0x02b0:
            java.lang.AssertionError r0 = new java.lang.AssertionError     // Catch:{ all -> 0x02b6 }
            r0.<init>()     // Catch:{ all -> 0x02b6 }
            throw r0     // Catch:{ all -> 0x02b6 }
        L_0x02b6:
            r0 = move-exception
            goto L_0x02c1
        L_0x02b8:
            r0 = move-exception
            goto L_0x02bd
        L_0x02ba:
            r0 = move-exception
            r20 = r11
        L_0x02bd:
            r29 = r13
            r17 = r14
        L_0x02c1:
            r2.restoreStateAfterRead()     // Catch:{ all -> 0x02a3 }
            r2.tryTerminate$ktor_io()     // Catch:{ all -> 0x02a3 }
            throw r0     // Catch:{ all -> 0x02a3 }
        L_0x02c8:
            r0 = move-exception
            r28 = r8
            r20 = r11
            r29 = r13
            r17 = r14
            r7 = r29
            r14 = r16
            r10 = r17
            goto L_0x03e8
        L_0x02d9:
            r6 = r28
            r28 = r3
            r17 = r4
            r3 = r26
            r26 = r0
            r0 = r16
            r2 = r26
            r16 = r28
            r18 = r12
            r12 = r3
            goto L_0x01b1
        L_0x02ee:
            boolean r1 = r11.isFull()     // Catch:{ all -> 0x03c7 }
            if (r1 != 0) goto L_0x02fa
            boolean r1 = r8.getAutoFlush()     // Catch:{ all -> 0x03c7 }
            if (r1 == 0) goto L_0x02fd
        L_0x02fa:
            r8.flush()     // Catch:{ all -> 0x03c7 }
        L_0x02fd:
            if (r8 == r14) goto L_0x030e
            long r10 = r14.getTotalBytesWritten()     // Catch:{ all -> 0x03c7 }
            long r20 = r8.getTotalBytesWritten()     // Catch:{ all -> 0x03c7 }
            long r20 = r20 - r12
            long r10 = r10 + r20
            r14.setTotalBytesWritten$ktor_io(r10)     // Catch:{ all -> 0x03c7 }
        L_0x030e:
            r8.restoreStateAfterWrite$ktor_io()     // Catch:{ all -> 0x03c7 }
            r8.tryTerminate$ktor_io()     // Catch:{ all -> 0x03c7 }
            r1 = r4
            r8 = r9
            r14 = r16
            r3 = r18
        L_0x031a:
            if (r1 == 0) goto L_0x033b
            boolean r5 = r2.tryCompleteJoining(r1)     // Catch:{ all -> 0x007c }
            if (r5 == 0) goto L_0x0324
            goto L_0x040d
        L_0x0324:
            io.ktor.utils.io.internal.ReadWriteBufferState r5 = r2.getState()     // Catch:{ all -> 0x007c }
            io.ktor.utils.io.internal.RingBufferCapacity r5 = r5.capacity     // Catch:{ all -> 0x007c }
            boolean r5 = r5.flush()     // Catch:{ all -> 0x007c }
            if (r5 == 0) goto L_0x033b
            r2.resumeWriteOp()     // Catch:{ all -> 0x007c }
            r5 = r0
            r0 = r2
            r2 = r3
            r4 = r1
            r1 = r25
            goto L_0x0118
        L_0x033b:
            long r9 = r8.element     // Catch:{ all -> 0x007c }
            int r5 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r5 >= 0) goto L_0x040d
            r14.flush()     // Catch:{ all -> 0x007c }
            int r5 = r2.getAvailableForRead()     // Catch:{ all -> 0x007c }
            if (r5 != 0) goto L_0x0395
            r7.L$0 = r14     // Catch:{ all -> 0x007c }
            r7.L$1 = r2     // Catch:{ all -> 0x007c }
            r7.L$2 = r1     // Catch:{ all -> 0x007c }
            r7.L$3 = r8     // Catch:{ all -> 0x007c }
            r5 = 0
            r7.L$4 = r5     // Catch:{ all -> 0x007c }
            r7.L$5 = r5     // Catch:{ all -> 0x007c }
            r7.L$6 = r5     // Catch:{ all -> 0x007c }
            r7.L$7 = r5     // Catch:{ all -> 0x007c }
            r7.L$8 = r5     // Catch:{ all -> 0x007c }
            r7.L$9 = r5     // Catch:{ all -> 0x007c }
            r7.J$0 = r3     // Catch:{ all -> 0x007c }
            r7.Z$0 = r6     // Catch:{ all -> 0x007c }
            r5 = 2
            r7.label = r5     // Catch:{ all -> 0x007c }
            r9 = 1
            java.lang.Object r10 = r2.readSuspendImpl(r9, r7)     // Catch:{ all -> 0x007c }
            if (r10 != r0) goto L_0x036e
            return r0
        L_0x036e:
            r13 = r2
            r11 = r3
            r4 = r7
            r2 = r8
            r3 = r10
        L_0x0373:
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch:{ all -> 0x007c }
            boolean r3 = r3.booleanValue()     // Catch:{ all -> 0x007c }
            if (r3 == 0) goto L_0x0384
            if (r1 == 0) goto L_0x038d
            boolean r3 = r13.tryCompleteJoining(r1)     // Catch:{ all -> 0x007c }
            if (r3 == 0) goto L_0x038d
            goto L_0x0392
        L_0x0384:
            if (r1 == 0) goto L_0x0392
            boolean r3 = r13.tryCompleteJoining(r1)     // Catch:{ all -> 0x007c }
            if (r3 == 0) goto L_0x038d
            goto L_0x0392
        L_0x038d:
            r8 = r2
            r7 = r4
            r2 = r11
        L_0x0390:
            r4 = r1
            goto L_0x0399
        L_0x0392:
            r8 = r2
            goto L_0x040d
        L_0x0395:
            r5 = 2
            r13 = r2
            r2 = r3
            goto L_0x0390
        L_0x0399:
            io.ktor.utils.io.internal.JoiningState r1 = r14.joining     // Catch:{ all -> 0x007c }
            if (r1 == 0) goto L_0x03c1
            r7.L$0 = r14     // Catch:{ all -> 0x007c }
            r7.L$1 = r13     // Catch:{ all -> 0x007c }
            r7.L$2 = r4     // Catch:{ all -> 0x007c }
            r7.L$3 = r8     // Catch:{ all -> 0x007c }
            r1 = 0
            r7.L$4 = r1     // Catch:{ all -> 0x007c }
            r7.L$5 = r1     // Catch:{ all -> 0x007c }
            r7.L$6 = r1     // Catch:{ all -> 0x007c }
            r7.L$7 = r1     // Catch:{ all -> 0x007c }
            r7.L$8 = r1     // Catch:{ all -> 0x007c }
            r7.L$9 = r1     // Catch:{ all -> 0x007c }
            r7.J$0 = r2     // Catch:{ all -> 0x007c }
            r7.Z$0 = r6     // Catch:{ all -> 0x007c }
            r9 = 3
            r7.label = r9     // Catch:{ all -> 0x007c }
            r10 = 1
            java.lang.Object r11 = r14.tryWriteSuspend$ktor_io(r10, r7)     // Catch:{ all -> 0x007c }
            if (r11 != r0) goto L_0x03c1
            return r0
        L_0x03c1:
            r1 = r25
            r5 = r0
            r0 = r13
            goto L_0x0118
        L_0x03c7:
            r0 = move-exception
            r14 = r16
            goto L_0x041c
        L_0x03cb:
            r0 = move-exception
            r28 = r3
            r3 = r26
        L_0x03d0:
            r7 = r11
            r10 = r14
            r14 = r28
            r11 = r3
            goto L_0x03e8
        L_0x03d6:
            java.lang.Throwable r0 = r15.getSendException()     // Catch:{ all -> 0x03e3 }
            java.lang.Void unused = io.ktor.utils.io.ByteBufferChannelKt.rethrowClosed(r0)     // Catch:{ all -> 0x03e3 }
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException     // Catch:{ all -> 0x03e3 }
            r0.<init>()     // Catch:{ all -> 0x03e3 }
            throw r0     // Catch:{ all -> 0x03e3 }
        L_0x03e3:
            r0 = move-exception
            r8 = r9
            r7 = r11
            r11 = r12
            r10 = r14
        L_0x03e8:
            boolean r1 = r7.isFull()     // Catch:{ all -> 0x007c }
            if (r1 != 0) goto L_0x03f4
            boolean r1 = r8.getAutoFlush()     // Catch:{ all -> 0x007c }
            if (r1 == 0) goto L_0x03f7
        L_0x03f4:
            r8.flush()     // Catch:{ all -> 0x007c }
        L_0x03f7:
            if (r8 == r10) goto L_0x0406
            long r1 = r10.getTotalBytesWritten()     // Catch:{ all -> 0x007c }
            long r3 = r8.getTotalBytesWritten()     // Catch:{ all -> 0x007c }
            long r3 = r3 - r11
            long r1 = r1 + r3
            r10.setTotalBytesWritten$ktor_io(r1)     // Catch:{ all -> 0x007c }
        L_0x0406:
            r8.restoreStateAfterWrite$ktor_io()     // Catch:{ all -> 0x007c }
            r8.tryTerminate$ktor_io()     // Catch:{ all -> 0x007c }
            throw r0     // Catch:{ all -> 0x007c }
        L_0x040d:
            if (r6 == 0) goto L_0x0412
            r14.flush()     // Catch:{ all -> 0x007c }
        L_0x0412:
            long r0 = r8.element     // Catch:{ all -> 0x007c }
            java.lang.Long r0 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r0)     // Catch:{ all -> 0x007c }
            return r0
        L_0x0419:
            r0 = move-exception
            r14 = r25
        L_0x041c:
            r14.close(r0)
            goto L_0x0421
        L_0x0420:
            throw r0
        L_0x0421:
            goto L_0x0420
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.copyDirect$ktor_io(io.ktor.utils.io.ByteBufferChannel, long, io.ktor.utils.io.internal.JoiningState, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void ensureClosedJoined(JoiningState joiningState) {
        ClosedElement closed = getClosed();
        if (closed != null) {
            this.joining = null;
            if (!joiningState.getDelegateClose()) {
                joiningState.getDelegatedTo().flush();
                joiningState.complete();
                return;
            }
            ReadWriteBufferState state = joiningState.getDelegatedTo().getState();
            boolean z = (state instanceof ReadWriteBufferState.Writing) || (state instanceof ReadWriteBufferState.ReadingWriting);
            if (closed.getCause() != null || !z) {
                joiningState.getDelegatedTo().close(closed.getCause());
            } else {
                joiningState.getDelegatedTo().flush();
            }
            joiningState.complete();
        }
    }

    static /* synthetic */ Object writeFully$suspendImpl(ByteBufferChannel byteBufferChannel, byte[] bArr, int i, int i2, Continuation<? super Unit> continuation) {
        ByteBufferChannel resolveDelegation;
        JoiningState joiningState = byteBufferChannel.joining;
        if (joiningState == null || (resolveDelegation = byteBufferChannel.resolveDelegation(byteBufferChannel, joiningState)) == null) {
            while (i2 > 0) {
                int writeAsMuchAsPossible = byteBufferChannel.writeAsMuchAsPossible(bArr, i, i2);
                if (writeAsMuchAsPossible == 0) {
                    break;
                }
                i += writeAsMuchAsPossible;
                i2 -= writeAsMuchAsPossible;
            }
            if (i2 == 0) {
                return Unit.INSTANCE;
            }
            Object writeFullySuspend = byteBufferChannel.writeFullySuspend(bArr, i, i2, continuation);
            return writeFullySuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeFullySuspend : Unit.INSTANCE;
        }
        Object writeFully = resolveDelegation.writeFully(bArr, i, i2, continuation);
        return writeFully == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeFully : Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object writeFullySuspend(byte[] r6, int r7, int r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            r5 = this;
            boolean r0 = r9 instanceof io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$5
            if (r0 == 0) goto L_0x0014
            r0 = r9
            io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$5 r0 = (io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$5) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$5 r0 = new io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$5
            r0.<init>(r5, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003e
            if (r2 != r3) goto L_0x0036
            int r6 = r0.I$1
            int r7 = r0.I$0
            java.lang.Object r8 = r0.L$1
            byte[] r8 = (byte[]) r8
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0058
        L_0x0036:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x003e:
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = r5
        L_0x0042:
            if (r8 <= 0) goto L_0x0064
            r0.L$0 = r2
            r0.L$1 = r6
            r0.I$0 = r7
            r0.I$1 = r8
            r0.label = r3
            java.lang.Object r9 = r2.writeAvailable(r6, r7, r8, r0)
            if (r9 != r1) goto L_0x0055
            return r1
        L_0x0055:
            r4 = r8
            r8 = r6
            r6 = r4
        L_0x0058:
            java.lang.Number r9 = (java.lang.Number) r9
            int r9 = r9.intValue()
            int r7 = r7 + r9
            int r6 = r6 - r9
            r4 = r8
            r8 = r6
            r6 = r4
            goto L_0x0042
        L_0x0064:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeFullySuspend(byte[], int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object writeAvailable$suspendImpl(ByteBufferChannel byteBufferChannel, byte[] bArr, int i, int i2, Continuation<? super Integer> continuation) {
        ByteBufferChannel resolveDelegation;
        JoiningState joiningState = byteBufferChannel.joining;
        if (joiningState != null && (resolveDelegation = byteBufferChannel.resolveDelegation(byteBufferChannel, joiningState)) != null) {
            return resolveDelegation.writeAvailable(bArr, i, i2, continuation);
        }
        int writeAsMuchAsPossible = byteBufferChannel.writeAsMuchAsPossible(bArr, i, i2);
        if (writeAsMuchAsPossible > 0) {
            return Boxing.boxInt(writeAsMuchAsPossible);
        }
        return byteBufferChannel.writeSuspend(bArr, i, i2, continuation);
    }

    /* access modifiers changed from: private */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x005c A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x007c  */
    public final java.lang.Object writeSuspend(byte[] r7, int r8, int r9, kotlin.coroutines.Continuation<? super java.lang.Integer> r10) {
        /*
            r6 = this;
            boolean r0 = r10 instanceof io.ktor.utils.io.ByteBufferChannel$writeSuspend$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            io.ktor.utils.io.ByteBufferChannel$writeSuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel$writeSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$writeSuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$writeSuspend$1
            r0.<init>(r6, r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0048
            if (r2 == r4) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0075
        L_0x002d:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0035:
            int r7 = r0.I$1
            int r8 = r0.I$0
            java.lang.Object r9 = r0.L$1
            byte[] r9 = (byte[]) r9
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r10)
            r5 = r9
            r9 = r7
            r7 = r5
            goto L_0x005d
        L_0x0048:
            kotlin.ResultKt.throwOnFailure(r10)
            r2 = r6
        L_0x004c:
            r0.L$0 = r2
            r0.L$1 = r7
            r0.I$0 = r8
            r0.I$1 = r9
            r0.label = r4
            java.lang.Object r10 = r2.tryWriteSuspend$ktor_io(r4, r0)
            if (r10 != r1) goto L_0x005d
            return r1
        L_0x005d:
            io.ktor.utils.io.internal.JoiningState r10 = r2.joining
            if (r10 == 0) goto L_0x0076
            io.ktor.utils.io.ByteBufferChannel r10 = r2.resolveDelegation(r2, r10)
            if (r10 == 0) goto L_0x0076
            r2 = 0
            r0.L$0 = r2
            r0.L$1 = r2
            r0.label = r3
            java.lang.Object r10 = r10.writeSuspend(r7, r8, r9, r0)
            if (r10 != r1) goto L_0x0075
            return r1
        L_0x0075:
            return r10
        L_0x0076:
            int r10 = r2.writeAsMuchAsPossible(r7, r8, r9)
            if (r10 <= 0) goto L_0x004c
            java.lang.Integer r7 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r10)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeSuspend(byte[], int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ java.lang.Object write$suspendImpl(io.ktor.utils.io.ByteBufferChannel r5, int r6, kotlin.jvm.functions.Function1<? super java.nio.ByteBuffer, kotlin.Unit> r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.utils.io.ByteBufferChannel$write$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ktor.utils.io.ByteBufferChannel$write$1 r0 = (io.ktor.utils.io.ByteBufferChannel$write$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$write$1 r0 = new io.ktor.utils.io.ByteBufferChannel$write$1
            r0.<init>(r5, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0040
            if (r2 != r3) goto L_0x0038
            int r5 = r0.I$0
            java.lang.Object r6 = r0.L$1
            kotlin.jvm.functions.Function1 r6 = (kotlin.jvm.functions.Function1) r6
            java.lang.Object r7 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r7 = (io.ktor.utils.io.ByteBufferChannel) r7
            kotlin.ResultKt.throwOnFailure(r8)
            r4 = r6
            r6 = r5
            r5 = r7
            r7 = r4
            goto L_0x0049
        L_0x0038:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0040:
            kotlin.ResultKt.throwOnFailure(r8)
            if (r6 <= 0) goto L_0x007e
            r8 = 4088(0xff8, float:5.729E-42)
            if (r6 > r8) goto L_0x0061
        L_0x0049:
            int r8 = r5.writeAvailable((int) r6, (kotlin.jvm.functions.Function1<? super java.nio.ByteBuffer, kotlin.Unit>) r7)
            if (r8 < 0) goto L_0x0052
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L_0x0052:
            r0.L$0 = r5
            r0.L$1 = r7
            r0.I$0 = r6
            r0.label = r3
            java.lang.Object r8 = r5.awaitFreeSpaceOrDelegate(r6, r7, r0)
            if (r8 != r1) goto L_0x0049
            return r1
        L_0x0061:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r7 = "Min("
            r5.<init>(r7)
            r5.append(r6)
            java.lang.String r6 = ") should'nt be greater than (4088)"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r5 = r5.toString()
            r6.<init>(r5)
            throw r6
        L_0x007e:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "min should be positive"
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            goto L_0x008b
        L_0x008a:
            throw r5
        L_0x008b:
            goto L_0x008a
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.write$suspendImpl(io.ktor.utils.io.ByteBufferChannel, int, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object awaitFreeSpaceOrDelegate(int r6, kotlin.jvm.functions.Function1<? super java.nio.ByteBuffer, kotlin.Unit> r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof io.ktor.utils.io.ByteBufferChannel$awaitFreeSpaceOrDelegate$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ktor.utils.io.ByteBufferChannel$awaitFreeSpaceOrDelegate$1 r0 = (io.ktor.utils.io.ByteBufferChannel$awaitFreeSpaceOrDelegate$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$awaitFreeSpaceOrDelegate$1 r0 = new io.ktor.utils.io.ByteBufferChannel$awaitFreeSpaceOrDelegate$1
            r0.<init>(r5, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0043
            if (r2 == r4) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x006e
        L_0x002d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0035:
            int r6 = r0.I$0
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0056
        L_0x0043:
            kotlin.ResultKt.throwOnFailure(r8)
            r0.L$0 = r5
            r0.L$1 = r7
            r0.I$0 = r6
            r0.label = r4
            java.lang.Object r8 = r5.writeSuspend(r6, r0)
            if (r8 != r1) goto L_0x0055
            return r1
        L_0x0055:
            r2 = r5
        L_0x0056:
            io.ktor.utils.io.internal.JoiningState r8 = r2.joining
            if (r8 == 0) goto L_0x0071
            io.ktor.utils.io.ByteBufferChannel r8 = r2.resolveDelegation(r2, r8)
            if (r8 == 0) goto L_0x0071
            r2 = 0
            r0.L$0 = r2
            r0.L$1 = r2
            r0.label = r3
            java.lang.Object r6 = r8.write(r6, r7, r0)
            if (r6 != r1) goto L_0x006e
            return r1
        L_0x006e:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L_0x0071:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.awaitFreeSpaceOrDelegate(int, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object writeWhile$suspendImpl(ByteBufferChannel byteBufferChannel, Function1<? super ByteBuffer, Boolean> function1, Continuation<? super Unit> continuation) {
        if (!byteBufferChannel.writeWhileNoSuspend(function1)) {
            return Unit.INSTANCE;
        }
        ClosedElement closed = byteBufferChannel.getClosed();
        if (closed == null) {
            Object writeWhileSuspend = byteBufferChannel.writeWhileSuspend(function1, continuation);
            return writeWhileSuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeWhileSuspend : Unit.INSTANCE;
        }
        Void unused = ByteBufferChannelKt.rethrowClosed(closed.getSendException());
        throw new KotlinNothingValueException();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0077, code lost:
        r4 = resolveDelegation(r1, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00d7, code lost:
        if (r4.getClosed() != null) goto L_0x00d9;
     */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00c4 A[Catch:{ all -> 0x0066 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00c9 A[Catch:{ all -> 0x0066 }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0101  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0104  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x015e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0029  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object writeWhileSuspend(kotlin.jvm.functions.Function1<? super java.nio.ByteBuffer, java.lang.Boolean> r18, kotlin.coroutines.Continuation<? super kotlin.Unit> r19) {
        /*
            r17 = this;
            r1 = r17
            r0 = r19
            boolean r2 = r0 instanceof io.ktor.utils.io.ByteBufferChannel$writeWhileSuspend$1
            if (r2 == 0) goto L_0x0018
            r2 = r0
            io.ktor.utils.io.ByteBufferChannel$writeWhileSuspend$1 r2 = (io.ktor.utils.io.ByteBufferChannel$writeWhileSuspend$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r0 = r2.label
            int r0 = r0 - r4
            r2.label = r0
            goto L_0x001d
        L_0x0018:
            io.ktor.utils.io.ByteBufferChannel$writeWhileSuspend$1 r2 = new io.ktor.utils.io.ByteBufferChannel$writeWhileSuspend$1
            r2.<init>(r1, r0)
        L_0x001d:
            java.lang.Object r0 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 2
            r6 = 1
            if (r4 == 0) goto L_0x0069
            if (r4 == r6) goto L_0x003a
            if (r4 != r5) goto L_0x0032
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x012b
        L_0x0032:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x003a:
            long r7 = r2.J$0
            java.lang.Object r4 = r2.L$8
            io.ktor.utils.io.ByteBufferChannel r4 = (io.ktor.utils.io.ByteBufferChannel) r4
            java.lang.Object r9 = r2.L$7
            java.nio.ByteBuffer r9 = (java.nio.ByteBuffer) r9
            java.lang.Object r10 = r2.L$6
            io.ktor.utils.io.internal.RingBufferCapacity r10 = (io.ktor.utils.io.internal.RingBufferCapacity) r10
            java.lang.Object r11 = r2.L$5
            io.ktor.utils.io.internal.RingBufferCapacity r11 = (io.ktor.utils.io.internal.RingBufferCapacity) r11
            java.lang.Object r12 = r2.L$4
            io.ktor.utils.io.ByteBufferChannel r12 = (io.ktor.utils.io.ByteBufferChannel) r12
            java.lang.Object r13 = r2.L$3
            io.ktor.utils.io.ByteBufferChannel r13 = (io.ktor.utils.io.ByteBufferChannel) r13
            java.lang.Object r14 = r2.L$2
            kotlin.jvm.internal.Ref$BooleanRef r14 = (kotlin.jvm.internal.Ref.BooleanRef) r14
            java.lang.Object r15 = r2.L$1
            kotlin.jvm.functions.Function1 r15 = (kotlin.jvm.functions.Function1) r15
            java.lang.Object r5 = r2.L$0
            io.ktor.utils.io.ByteBufferChannel r5 = (io.ktor.utils.io.ByteBufferChannel) r5
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ all -> 0x0066 }
            r0 = r15
            goto L_0x00c5
        L_0x0066:
            r0 = move-exception
            goto L_0x014d
        L_0x0069:
            kotlin.ResultKt.throwOnFailure(r0)
            kotlin.jvm.internal.Ref$BooleanRef r0 = new kotlin.jvm.internal.Ref$BooleanRef
            r0.<init>()
            r0.element = r6
            io.ktor.utils.io.internal.JoiningState r4 = r1.joining
            if (r4 == 0) goto L_0x0080
            io.ktor.utils.io.ByteBufferChannel r4 = r1.resolveDelegation(r1, r4)
            if (r4 != 0) goto L_0x007e
            goto L_0x0080
        L_0x007e:
            r12 = r4
            goto L_0x0081
        L_0x0080:
            r12 = r1
        L_0x0081:
            java.nio.ByteBuffer r4 = r12.setupStateForWrite$ktor_io()
            if (r4 != 0) goto L_0x008d
            r14 = r0
            r5 = r1
            r0 = r18
            goto L_0x00fd
        L_0x008d:
            io.ktor.utils.io.internal.ReadWriteBufferState r5 = r12.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r11 = r5.capacity
            long r7 = r12.getTotalBytesWritten()
            io.ktor.utils.io.internal.ClosedElement r5 = r12.getClosed()     // Catch:{ all -> 0x014b }
            if (r5 != 0) goto L_0x013e
            r5 = r12
            io.ktor.utils.io.ByteBufferChannel r5 = (io.ktor.utils.io.ByteBufferChannel) r5     // Catch:{ all -> 0x014b }
            r14 = r0
            r5 = r1
            r13 = r5
            r9 = r4
            r10 = r11
            r4 = r12
            r0 = r18
        L_0x00a8:
            r2.L$0 = r5     // Catch:{ all -> 0x0066 }
            r2.L$1 = r0     // Catch:{ all -> 0x0066 }
            r2.L$2 = r14     // Catch:{ all -> 0x0066 }
            r2.L$3 = r13     // Catch:{ all -> 0x0066 }
            r2.L$4 = r12     // Catch:{ all -> 0x0066 }
            r2.L$5 = r11     // Catch:{ all -> 0x0066 }
            r2.L$6 = r10     // Catch:{ all -> 0x0066 }
            r2.L$7 = r9     // Catch:{ all -> 0x0066 }
            r2.L$8 = r4     // Catch:{ all -> 0x0066 }
            r2.J$0 = r7     // Catch:{ all -> 0x0066 }
            r2.label = r6     // Catch:{ all -> 0x0066 }
            java.lang.Object r15 = r4.writeSuspend(r6, r2)     // Catch:{ all -> 0x0066 }
            if (r15 != r3) goto L_0x00c5
            return r3
        L_0x00c5:
            io.ktor.utils.io.internal.JoiningState r15 = r4.joining     // Catch:{ all -> 0x0066 }
            if (r15 != 0) goto L_0x00d9
            boolean r15 = r4.writeWhileLoop(r9, r10, r0)     // Catch:{ all -> 0x0066 }
            if (r15 != 0) goto L_0x00d3
            r4 = 0
            r14.element = r4     // Catch:{ all -> 0x0066 }
            goto L_0x00d9
        L_0x00d3:
            io.ktor.utils.io.internal.ClosedElement r15 = r4.getClosed()     // Catch:{ all -> 0x0066 }
            if (r15 == 0) goto L_0x00a8
        L_0x00d9:
            boolean r4 = r11.isFull()
            if (r4 != 0) goto L_0x00e5
            boolean r4 = r12.getAutoFlush()
            if (r4 == 0) goto L_0x00e8
        L_0x00e5:
            r12.flush()
        L_0x00e8:
            if (r12 == r13) goto L_0x00f7
            long r9 = r13.getTotalBytesWritten()
            long r15 = r12.getTotalBytesWritten()
            long r15 = r15 - r7
            long r9 = r9 + r15
            r13.setTotalBytesWritten$ktor_io(r9)
        L_0x00f7:
            r12.restoreStateAfterWrite$ktor_io()
            r12.tryTerminate$ktor_io()
        L_0x00fd:
            boolean r4 = r14.element
            if (r4 != 0) goto L_0x0104
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0104:
            io.ktor.utils.io.internal.ClosedElement r4 = r5.getClosed()
            if (r4 != 0) goto L_0x0131
            io.ktor.utils.io.internal.JoiningState r4 = r5.joining
            if (r4 == 0) goto L_0x012e
            r4 = 0
            r2.L$0 = r4
            r2.L$1 = r4
            r2.L$2 = r4
            r2.L$3 = r4
            r2.L$4 = r4
            r2.L$5 = r4
            r2.L$6 = r4
            r2.L$7 = r4
            r2.L$8 = r4
            r4 = 2
            r2.label = r4
            java.lang.Object r0 = r5.writeWhile(r0, r2)
            if (r0 != r3) goto L_0x012b
            return r3
        L_0x012b:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x012e:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0131:
            java.lang.Throwable r0 = r4.getSendException()
            java.lang.Void unused = io.ktor.utils.io.ByteBufferChannelKt.rethrowClosed(r0)
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException
            r0.<init>()
            throw r0
        L_0x013e:
            java.lang.Throwable r0 = r5.getSendException()     // Catch:{ all -> 0x014b }
            java.lang.Void unused = io.ktor.utils.io.ByteBufferChannelKt.rethrowClosed(r0)     // Catch:{ all -> 0x014b }
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException     // Catch:{ all -> 0x014b }
            r0.<init>()     // Catch:{ all -> 0x014b }
            throw r0     // Catch:{ all -> 0x014b }
        L_0x014b:
            r0 = move-exception
            r13 = r1
        L_0x014d:
            boolean r2 = r11.isFull()
            if (r2 != 0) goto L_0x0159
            boolean r2 = r12.getAutoFlush()
            if (r2 == 0) goto L_0x015c
        L_0x0159:
            r12.flush()
        L_0x015c:
            if (r12 == r13) goto L_0x016b
            long r2 = r13.getTotalBytesWritten()
            long r4 = r12.getTotalBytesWritten()
            long r4 = r4 - r7
            long r2 = r2 + r4
            r13.setTotalBytesWritten$ktor_io(r2)
        L_0x016b:
            r12.restoreStateAfterWrite$ktor_io()
            r12.tryTerminate$ktor_io()
            goto L_0x0173
        L_0x0172:
            throw r0
        L_0x0173:
            goto L_0x0172
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeWhileSuspend(kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final boolean writeWhileLoop(ByteBuffer byteBuffer, RingBufferCapacity ringBufferCapacity, Function1<? super ByteBuffer, Boolean> function1) {
        int capacity = byteBuffer.capacity() - this.reservedSize;
        boolean z = true;
        while (z) {
            int tryWriteAtLeast = ringBufferCapacity.tryWriteAtLeast(1);
            if (tryWriteAtLeast == 0) {
                break;
            }
            int i = this.writePosition;
            int coerceAtMost = RangesKt.coerceAtMost(i + tryWriteAtLeast, capacity);
            byteBuffer.limit(coerceAtMost);
            byteBuffer.position(i);
            try {
                boolean booleanValue = function1.invoke(byteBuffer).booleanValue();
                if (byteBuffer.limit() == coerceAtMost) {
                    int position = byteBuffer.position() - i;
                    if (position >= 0) {
                        bytesWritten(byteBuffer, ringBufferCapacity, position);
                        if (position < tryWriteAtLeast) {
                            tryWriteAtLeast -= position;
                        }
                        z = booleanValue;
                    } else {
                        throw new IllegalStateException("Position has been moved backward: pushback is not supported.".toString());
                    }
                } else {
                    throw new IllegalStateException("Buffer limit modified.".toString());
                }
            } finally {
                ringBufferCapacity.completeRead(tryWriteAtLeast);
            }
        }
        return z;
    }

    public SuspendableReadSession startReadSession() {
        return this.readSession;
    }

    public void endReadSession() {
        this.readSession.completed();
        ReadWriteBufferState state = getState();
        if ((state instanceof ReadWriteBufferState.Reading) || (state instanceof ReadWriteBufferState.ReadingWriting)) {
            restoreStateAfterRead();
            tryTerminate$ktor_io();
        }
    }

    public WriterSuspendSession beginWriteSession() {
        WriteSessionImpl writeSessionImpl = this.writeSession;
        writeSessionImpl.begin();
        return writeSessionImpl;
    }

    public void endWriteSession(int i) {
        this.writeSession.written(i);
        this.writeSession.complete();
    }

    @Deprecated(message = "Use read { } instead.")
    public void readSession(Function1<? super ReadSession, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "consumer");
        lookAhead(new ByteBufferChannel$readSession$1(function1, this));
    }

    @Deprecated(message = "Use read { } instead.")
    static /* synthetic */ Object readSuspendableSession$suspendImpl(ByteBufferChannel byteBufferChannel, Function2<? super SuspendableReadSession, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        Object lookAheadSuspend = byteBufferChannel.lookAheadSuspend(new ByteBufferChannel$readSuspendableSession$2(function2, byteBufferChannel, (Continuation<? super ByteBufferChannel$readSuspendableSession$2>) null), continuation);
        return lookAheadSuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? lookAheadSuspend : Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0081, code lost:
        if (((java.lang.Boolean) r14).booleanValue() != false) goto L_0x0049;
     */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object discardSuspend(long r10, long r12, kotlin.coroutines.Continuation<? super java.lang.Long> r14) {
        /*
            r9 = this;
            boolean r0 = r14 instanceof io.ktor.utils.io.ByteBufferChannel$discardSuspend$1
            if (r0 == 0) goto L_0x0014
            r0 = r14
            io.ktor.utils.io.ByteBufferChannel$discardSuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel$discardSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$discardSuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$discardSuspend$1
            r0.<init>(r9, r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003c
            if (r2 != r3) goto L_0x0034
            long r10 = r0.J$0
            java.lang.Object r12 = r0.L$1
            kotlin.jvm.internal.Ref$LongRef r12 = (kotlin.jvm.internal.Ref.LongRef) r12
            java.lang.Object r13 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r13 = (io.ktor.utils.io.ByteBufferChannel) r13
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x007b
        L_0x0034:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x003c:
            kotlin.ResultKt.throwOnFailure(r14)
            kotlin.jvm.internal.Ref$LongRef r14 = new kotlin.jvm.internal.Ref$LongRef
            r14.<init>()
            r14.element = r10
            r10 = r12
            r12 = r14
            r13 = r9
        L_0x0049:
            long r4 = r12.element
            int r14 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r14 >= 0) goto L_0x00ac
            java.nio.ByteBuffer r14 = r13.setupStateForRead()
            if (r14 != 0) goto L_0x0056
            goto L_0x0066
        L_0x0056:
            io.ktor.utils.io.internal.ReadWriteBufferState r2 = r13.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r2 = r2.capacity
            int r4 = r2._availableForRead$internal     // Catch:{ all -> 0x00a4 }
            if (r4 != 0) goto L_0x0084
            r13.restoreStateAfterRead()
            r13.tryTerminate$ktor_io()
        L_0x0066:
            boolean r14 = r13.isClosedForRead()
            if (r14 != 0) goto L_0x00ac
            r0.L$0 = r13
            r0.L$1 = r12
            r0.J$0 = r10
            r0.label = r3
            java.lang.Object r14 = r13.readSuspend(r3, r0)
            if (r14 != r1) goto L_0x007b
            return r1
        L_0x007b:
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            boolean r14 = r14.booleanValue()
            if (r14 != 0) goto L_0x0049
            goto L_0x00ac
        L_0x0084:
            long r4 = r12.element     // Catch:{ all -> 0x00a4 }
            long r4 = r10 - r4
            r6 = 2147483647(0x7fffffff, double:1.060997895E-314)
            long r4 = java.lang.Math.min(r6, r4)     // Catch:{ all -> 0x00a4 }
            int r5 = (int) r4     // Catch:{ all -> 0x00a4 }
            int r4 = r2.tryReadAtMost(r5)     // Catch:{ all -> 0x00a4 }
            r13.bytesRead(r14, r2, r4)     // Catch:{ all -> 0x00a4 }
            long r5 = r12.element     // Catch:{ all -> 0x00a4 }
            long r7 = (long) r4     // Catch:{ all -> 0x00a4 }
            long r5 = r5 + r7
            r12.element = r5     // Catch:{ all -> 0x00a4 }
            r13.restoreStateAfterRead()
            r13.tryTerminate$ktor_io()
            goto L_0x0049
        L_0x00a4:
            r10 = move-exception
            r13.restoreStateAfterRead()
            r13.tryTerminate$ktor_io()
            throw r10
        L_0x00ac:
            long r10 = r12.element
            java.lang.Long r10 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r10)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.discardSuspend(long, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object readBlockSuspend(int r6, kotlin.jvm.functions.Function1<? super java.nio.ByteBuffer, kotlin.Unit> r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof io.ktor.utils.io.ByteBufferChannel$readBlockSuspend$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ktor.utils.io.ByteBufferChannel$readBlockSuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel$readBlockSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$readBlockSuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readBlockSuspend$1
            r0.<init>(r5, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0043
            if (r2 == r4) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x008e
        L_0x002d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0035:
            int r6 = r0.I$0
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x005a
        L_0x0043:
            kotlin.ResultKt.throwOnFailure(r8)
            int r8 = kotlin.ranges.RangesKt.coerceAtLeast((int) r6, (int) r4)
            r0.L$0 = r5
            r0.L$1 = r7
            r0.I$0 = r6
            r0.label = r4
            java.lang.Object r8 = r5.readSuspend(r8, r0)
            if (r8 != r1) goto L_0x0059
            return r1
        L_0x0059:
            r2 = r5
        L_0x005a:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r8 != 0) goto L_0x0080
            if (r6 > 0) goto L_0x0067
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L_0x0067:
            java.io.EOFException r7 = new java.io.EOFException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r0 = "Got EOF but at least "
            r8.<init>(r0)
            r8.append(r6)
            java.lang.String r6 = " bytes were expected"
            r8.append(r6)
            java.lang.String r6 = r8.toString()
            r7.<init>(r6)
            throw r7
        L_0x0080:
            r8 = 0
            r0.L$0 = r8
            r0.L$1 = r8
            r0.label = r3
            java.lang.Object r6 = r2.read(r6, r7, r0)
            if (r6 != r1) goto L_0x008e
            return r1
        L_0x008e:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readBlockSuspend(int, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object writePacket$suspendImpl(ByteBufferChannel byteBufferChannel, ByteReadPacket byteReadPacket, Continuation<? super Unit> continuation) {
        ByteBufferChannel resolveDelegation;
        ByteBufferChannel resolveDelegation2;
        JoiningState joiningState = byteBufferChannel.joining;
        if (joiningState == null || (resolveDelegation2 = byteBufferChannel.resolveDelegation(byteBufferChannel, joiningState)) == null) {
            do {
                try {
                    if (!(!byteReadPacket.getEndOfInput()) || byteBufferChannel.tryWritePacketPart(byteReadPacket) == 0) {
                    }
                    break;
                } catch (Throwable th) {
                    byteReadPacket.release();
                    throw th;
                }
            } while (byteBufferChannel.tryWritePacketPart(byteReadPacket) == 0);
            if (byteReadPacket.getRemaining() <= 0) {
                return Unit.INSTANCE;
            }
            JoiningState joiningState2 = byteBufferChannel.joining;
            if (joiningState2 == null || (resolveDelegation = byteBufferChannel.resolveDelegation(byteBufferChannel, joiningState2)) == null) {
                Object writePacketSuspend = byteBufferChannel.writePacketSuspend(byteReadPacket, continuation);
                return writePacketSuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writePacketSuspend : Unit.INSTANCE;
            }
            Object writePacket = resolveDelegation.writePacket(byteReadPacket, continuation);
            return writePacket == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writePacket : Unit.INSTANCE;
        }
        Object writePacket2 = resolveDelegation2.writePacket(byteReadPacket, continuation);
        return writePacket2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writePacket2 : Unit.INSTANCE;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0052 A[Catch:{ all -> 0x0045 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0069 A[Catch:{ all -> 0x0045 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x007d A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object writePacketSuspend(io.ktor.utils.io.core.ByteReadPacket r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteBufferChannel$writePacketSuspend$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            io.ktor.utils.io.ByteBufferChannel$writePacketSuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel$writePacketSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$writePacketSuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$writePacketSuspend$1
            r0.<init>(r5, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0047
            if (r2 == r4) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            java.lang.Object r6 = r0.L$0
            io.ktor.utils.io.core.ByteReadPacket r6 = (io.ktor.utils.io.core.ByteReadPacket) r6
            kotlin.ResultKt.throwOnFailure(r7)     // Catch:{ all -> 0x0045 }
            goto L_0x0077
        L_0x0031:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0039:
            java.lang.Object r6 = r0.L$1
            io.ktor.utils.io.core.ByteReadPacket r6 = (io.ktor.utils.io.core.ByteReadPacket) r6
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r7)     // Catch:{ all -> 0x0045 }
            goto L_0x005f
        L_0x0045:
            r7 = move-exception
            goto L_0x0087
        L_0x0047:
            kotlin.ResultKt.throwOnFailure(r7)
            r2 = r5
        L_0x004b:
            boolean r7 = r6.getEndOfInput()     // Catch:{ all -> 0x0045 }
            r7 = r7 ^ r4
            if (r7 == 0) goto L_0x0081
            r0.L$0 = r2     // Catch:{ all -> 0x0045 }
            r0.L$1 = r6     // Catch:{ all -> 0x0045 }
            r0.label = r4     // Catch:{ all -> 0x0045 }
            java.lang.Object r7 = r2.writeSuspend(r4, r0)     // Catch:{ all -> 0x0045 }
            if (r7 != r1) goto L_0x005f
            return r1
        L_0x005f:
            io.ktor.utils.io.internal.JoiningState r7 = r2.joining     // Catch:{ all -> 0x0045 }
            if (r7 == 0) goto L_0x007d
            io.ktor.utils.io.ByteBufferChannel r7 = r2.resolveDelegation(r2, r7)     // Catch:{ all -> 0x0045 }
            if (r7 == 0) goto L_0x007d
            r0.L$0 = r6     // Catch:{ all -> 0x0045 }
            r2 = 0
            r0.L$1 = r2     // Catch:{ all -> 0x0045 }
            r0.label = r3     // Catch:{ all -> 0x0045 }
            java.lang.Object r7 = r7.writePacket(r6, r0)     // Catch:{ all -> 0x0045 }
            if (r7 != r1) goto L_0x0077
            return r1
        L_0x0077:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0045 }
            r6.release()
            return r7
        L_0x007d:
            r2.tryWritePacketPart(r6)     // Catch:{ all -> 0x0045 }
            goto L_0x004b
        L_0x0081:
            r6.release()
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L_0x0087:
            r6.release()
            goto L_0x008c
        L_0x008b:
            throw r7
        L_0x008c:
            goto L_0x008b
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writePacketSuspend(io.ktor.utils.io.core.ByteReadPacket, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Deprecated(message = "Use read { } instead.")
    public <R> R lookAhead(Function1<? super LookAheadSession, ? extends R> function1) {
        Intrinsics.checkNotNullParameter(function1, "visitor");
        Throwable closedCause = getClosedCause();
        if (closedCause != null) {
            return function1.invoke(new FailedLookAhead(closedCause));
        }
        if (getState() == ReadWriteBufferState.Terminated.INSTANCE) {
            return function1.invoke(TerminatedLookAhead.INSTANCE);
        }
        boolean z = false;
        R r = null;
        if (setupStateForRead() != null) {
            try {
                if (getState().capacity._availableForRead$internal != 0) {
                    r = function1.invoke(this);
                    restoreStateAfterRead();
                    tryTerminate$ktor_io();
                    z = true;
                }
            } finally {
                restoreStateAfterRead();
                tryTerminate$ktor_io();
            }
        }
        if (!z) {
            Throwable closedCause2 = getClosedCause();
            if (closedCause2 != null) {
                return function1.invoke(new FailedLookAhead(closedCause2));
            }
            return function1.invoke(TerminatedLookAhead.INSTANCE);
        }
        Intrinsics.checkNotNull(r);
        return r;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0086, code lost:
        return r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x009b, code lost:
        return r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        r7.element = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00d7, code lost:
        r8.restoreStateAfterRead();
        r8.tryTerminate$ktor_io();
        r9 = r2;
        r8 = r4;
        r7 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00e0, code lost:
        if (r3 != false) goto L_0x017a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00e2, code lost:
        r2 = r7.getClosedCause();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00e7, code lost:
        if (r2 == null) goto L_0x0103;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00e9, code lost:
        r7 = new io.ktor.utils.io.internal.FailedLookAhead(r2);
        r0.L$0 = null;
        r0.L$1 = null;
        r0.L$2 = null;
        r0.L$3 = null;
        r0.L$4 = null;
        r0.label = 4;
        r9 = r8.invoke(r7, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00ff, code lost:
        if (r9 != r1) goto L_0x0102;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0101, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0102, code lost:
        return r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0109, code lost:
        if (r7.getState() != io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE) goto L_0x0122;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x010b, code lost:
        r7 = io.ktor.utils.io.internal.TerminatedLookAhead.INSTANCE;
        r0.L$0 = null;
        r0.L$1 = null;
        r0.L$2 = null;
        r0.L$3 = null;
        r0.L$4 = null;
        r0.label = 5;
        r9 = r8.invoke(r7, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x011e, code lost:
        if (r9 != r1) goto L_0x0121;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0120, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0121, code lost:
        return r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        r0.L$0 = r7;
        r0.L$1 = r9;
        r0.L$2 = r9;
        r0.L$3 = null;
        r0.L$4 = null;
        r0.label = 6;
        r8 = r8.invoke(r7, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0133, code lost:
        if (r8 != r1) goto L_0x0136;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0135, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0136, code lost:
        r0 = r7;
        r7 = r9;
        r9 = r8;
        r8 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:?, code lost:
        r7.element = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x013c, code lost:
        r7 = r0.getState();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0144, code lost:
        if (r7.getIdle() != false) goto L_0x0158;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0148, code lost:
        if (r7 == io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE) goto L_0x0158;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x014c, code lost:
        if ((r7 instanceof io.ktor.utils.io.internal.ReadWriteBufferState.Reading) != false) goto L_0x0152;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0150, code lost:
        if ((r7 instanceof io.ktor.utils.io.internal.ReadWriteBufferState.ReadingWriting) == false) goto L_0x0155;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0152, code lost:
        r0.restoreStateAfterRead();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0155, code lost:
        r0.tryTerminate$ktor_io();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0158, code lost:
        r9 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x015a, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x015b, code lost:
        r0 = r7;
        r7 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x015d, code lost:
        r8 = r0.getState();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0173, code lost:
        r0.restoreStateAfterRead();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0176, code lost:
        r0.tryTerminate$ktor_io();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0179, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x017c, code lost:
        return r9.element;
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x002d  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @kotlin.Deprecated(message = "Use read { } instead.")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ <R> java.lang.Object lookAheadSuspend$suspendImpl(io.ktor.utils.io.ByteBufferChannel r7, kotlin.jvm.functions.Function2<? super io.ktor.utils.io.LookAheadSuspendSession, ? super kotlin.coroutines.Continuation<? super R>, ? extends java.lang.Object> r8, kotlin.coroutines.Continuation<? super R> r9) {
        /*
            boolean r0 = r9 instanceof io.ktor.utils.io.ByteBufferChannel$lookAheadSuspend$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            io.ktor.utils.io.ByteBufferChannel$lookAheadSuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel$lookAheadSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$lookAheadSuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$lookAheadSuspend$1
            r0.<init>(r7, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            switch(r2) {
                case 0: goto L_0x006f;
                case 1: goto L_0x006b;
                case 2: goto L_0x0067;
                case 3: goto L_0x004b;
                case 4: goto L_0x0046;
                case 5: goto L_0x0041;
                case 6: goto L_0x002d;
                default: goto L_0x0025;
            }
        L_0x0025:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x002d:
            java.lang.Object r7 = r0.L$2
            kotlin.jvm.internal.Ref$ObjectRef r7 = (kotlin.jvm.internal.Ref.ObjectRef) r7
            java.lang.Object r8 = r0.L$1
            kotlin.jvm.internal.Ref$ObjectRef r8 = (kotlin.jvm.internal.Ref.ObjectRef) r8
            java.lang.Object r0 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r0 = (io.ktor.utils.io.ByteBufferChannel) r0
            kotlin.ResultKt.throwOnFailure(r9)     // Catch:{ all -> 0x003e }
            goto L_0x013a
        L_0x003e:
            r7 = move-exception
            goto L_0x015d
        L_0x0041:
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0121
        L_0x0046:
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0102
        L_0x004b:
            java.lang.Object r7 = r0.L$4
            kotlin.jvm.internal.Ref$ObjectRef r7 = (kotlin.jvm.internal.Ref.ObjectRef) r7
            java.lang.Object r8 = r0.L$3
            io.ktor.utils.io.ByteBufferChannel r8 = (io.ktor.utils.io.ByteBufferChannel) r8
            java.lang.Object r2 = r0.L$2
            kotlin.jvm.internal.Ref$ObjectRef r2 = (kotlin.jvm.internal.Ref.ObjectRef) r2
            java.lang.Object r4 = r0.L$1
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            java.lang.Object r5 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r5 = (io.ktor.utils.io.ByteBufferChannel) r5
            kotlin.ResultKt.throwOnFailure(r9)     // Catch:{ all -> 0x0064 }
            goto L_0x00d5
        L_0x0064:
            r7 = move-exception
            goto L_0x0181
        L_0x0067:
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x009b
        L_0x006b:
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0086
        L_0x006f:
            kotlin.ResultKt.throwOnFailure(r9)
            java.lang.Throwable r9 = r7.getClosedCause()
            if (r9 == 0) goto L_0x0087
            io.ktor.utils.io.internal.FailedLookAhead r7 = new io.ktor.utils.io.internal.FailedLookAhead
            r7.<init>(r9)
            r0.label = r3
            java.lang.Object r9 = r8.invoke(r7, r0)
            if (r9 != r1) goto L_0x0086
            return r1
        L_0x0086:
            return r9
        L_0x0087:
            io.ktor.utils.io.internal.ReadWriteBufferState r9 = r7.getState()
            io.ktor.utils.io.internal.ReadWriteBufferState$Terminated r2 = io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE
            if (r9 != r2) goto L_0x009c
            io.ktor.utils.io.internal.TerminatedLookAhead r7 = io.ktor.utils.io.internal.TerminatedLookAhead.INSTANCE
            r9 = 2
            r0.label = r9
            java.lang.Object r9 = r8.invoke(r7, r0)
            if (r9 != r1) goto L_0x009b
            return r1
        L_0x009b:
            return r9
        L_0x009c:
            kotlin.jvm.internal.Ref$ObjectRef r9 = new kotlin.jvm.internal.Ref$ObjectRef
            r9.<init>()
            java.nio.ByteBuffer r2 = r7.setupStateForRead()
            r4 = 0
            if (r2 != 0) goto L_0x00aa
        L_0x00a8:
            r3 = 0
            goto L_0x00e0
        L_0x00aa:
            io.ktor.utils.io.internal.ReadWriteBufferState r2 = r7.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r2 = r2.capacity
            int r2 = r2._availableForRead$internal     // Catch:{ all -> 0x017d }
            if (r2 != 0) goto L_0x00bb
            r7.restoreStateAfterRead()
            r7.tryTerminate$ktor_io()
            goto L_0x00a8
        L_0x00bb:
            r0.L$0 = r7     // Catch:{ all -> 0x017d }
            r0.L$1 = r8     // Catch:{ all -> 0x017d }
            r0.L$2 = r9     // Catch:{ all -> 0x017d }
            r0.L$3 = r7     // Catch:{ all -> 0x017d }
            r0.L$4 = r9     // Catch:{ all -> 0x017d }
            r2 = 3
            r0.label = r2     // Catch:{ all -> 0x017d }
            java.lang.Object r2 = r8.invoke(r7, r0)     // Catch:{ all -> 0x017d }
            if (r2 != r1) goto L_0x00cf
            return r1
        L_0x00cf:
            r5 = r7
            r4 = r8
            r8 = r5
            r7 = r9
            r9 = r2
            r2 = r7
        L_0x00d5:
            r7.element = r9     // Catch:{ all -> 0x0064 }
            r8.restoreStateAfterRead()
            r8.tryTerminate$ktor_io()
            r9 = r2
            r8 = r4
            r7 = r5
        L_0x00e0:
            if (r3 != 0) goto L_0x017a
            java.lang.Throwable r2 = r7.getClosedCause()
            r3 = 0
            if (r2 == 0) goto L_0x0103
            io.ktor.utils.io.internal.FailedLookAhead r7 = new io.ktor.utils.io.internal.FailedLookAhead
            r7.<init>(r2)
            r0.L$0 = r3
            r0.L$1 = r3
            r0.L$2 = r3
            r0.L$3 = r3
            r0.L$4 = r3
            r9 = 4
            r0.label = r9
            java.lang.Object r9 = r8.invoke(r7, r0)
            if (r9 != r1) goto L_0x0102
            return r1
        L_0x0102:
            return r9
        L_0x0103:
            io.ktor.utils.io.internal.ReadWriteBufferState r2 = r7.getState()
            io.ktor.utils.io.internal.ReadWriteBufferState$Terminated r4 = io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE
            if (r2 != r4) goto L_0x0122
            io.ktor.utils.io.internal.TerminatedLookAhead r7 = io.ktor.utils.io.internal.TerminatedLookAhead.INSTANCE
            r0.L$0 = r3
            r0.L$1 = r3
            r0.L$2 = r3
            r0.L$3 = r3
            r0.L$4 = r3
            r9 = 5
            r0.label = r9
            java.lang.Object r9 = r8.invoke(r7, r0)
            if (r9 != r1) goto L_0x0121
            return r1
        L_0x0121:
            return r9
        L_0x0122:
            r0.L$0 = r7     // Catch:{ all -> 0x015a }
            r0.L$1 = r9     // Catch:{ all -> 0x015a }
            r0.L$2 = r9     // Catch:{ all -> 0x015a }
            r0.L$3 = r3     // Catch:{ all -> 0x015a }
            r0.L$4 = r3     // Catch:{ all -> 0x015a }
            r2 = 6
            r0.label = r2     // Catch:{ all -> 0x015a }
            java.lang.Object r8 = r8.invoke(r7, r0)     // Catch:{ all -> 0x015a }
            if (r8 != r1) goto L_0x0136
            return r1
        L_0x0136:
            r0 = r7
            r7 = r9
            r9 = r8
            r8 = r7
        L_0x013a:
            r7.element = r9     // Catch:{ all -> 0x003e }
            io.ktor.utils.io.internal.ReadWriteBufferState r7 = r0.getState()
            boolean r9 = r7.getIdle()
            if (r9 != 0) goto L_0x0158
            io.ktor.utils.io.internal.ReadWriteBufferState$Terminated r9 = io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE
            if (r7 == r9) goto L_0x0158
            boolean r9 = r7 instanceof io.ktor.utils.io.internal.ReadWriteBufferState.Reading
            if (r9 != 0) goto L_0x0152
            boolean r7 = r7 instanceof io.ktor.utils.io.internal.ReadWriteBufferState.ReadingWriting
            if (r7 == 0) goto L_0x0155
        L_0x0152:
            r0.restoreStateAfterRead()
        L_0x0155:
            r0.tryTerminate$ktor_io()
        L_0x0158:
            r9 = r8
            goto L_0x017a
        L_0x015a:
            r8 = move-exception
            r0 = r7
            r7 = r8
        L_0x015d:
            io.ktor.utils.io.internal.ReadWriteBufferState r8 = r0.getState()
            boolean r9 = r8.getIdle()
            if (r9 != 0) goto L_0x0179
            io.ktor.utils.io.internal.ReadWriteBufferState$Terminated r9 = io.ktor.utils.io.internal.ReadWriteBufferState.Terminated.INSTANCE
            if (r8 == r9) goto L_0x0179
            boolean r9 = r8 instanceof io.ktor.utils.io.internal.ReadWriteBufferState.Reading
            if (r9 != 0) goto L_0x0173
            boolean r8 = r8 instanceof io.ktor.utils.io.internal.ReadWriteBufferState.ReadingWriting
            if (r8 == 0) goto L_0x0176
        L_0x0173:
            r0.restoreStateAfterRead()
        L_0x0176:
            r0.tryTerminate$ktor_io()
        L_0x0179:
            throw r7
        L_0x017a:
            T r7 = r9.element
            return r7
        L_0x017d:
            r8 = move-exception
            r6 = r8
            r8 = r7
            r7 = r6
        L_0x0181:
            r8.restoreStateAfterRead()
            r8.tryTerminate$ktor_io()
            goto L_0x0189
        L_0x0188:
            throw r7
        L_0x0189:
            goto L_0x0188
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.lookAheadSuspend$suspendImpl(io.ktor.utils.io.ByteBufferChannel, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @kotlin.Deprecated(message = "Use write { } instead.")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ java.lang.Object writeSuspendSession$suspendImpl(io.ktor.utils.io.ByteBufferChannel r4, kotlin.jvm.functions.Function2<? super io.ktor.utils.io.WriterSuspendSession, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.utils.io.ByteBufferChannel$writeSuspendSession$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            io.ktor.utils.io.ByteBufferChannel$writeSuspendSession$1 r0 = (io.ktor.utils.io.ByteBufferChannel$writeSuspendSession$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$writeSuspendSession$1 r0 = new io.ktor.utils.io.ByteBufferChannel$writeSuspendSession$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.internal.WriteSessionImpl r4 = (io.ktor.utils.io.internal.WriteSessionImpl) r4
            kotlin.ResultKt.throwOnFailure(r6)     // Catch:{ all -> 0x004f }
            goto L_0x0049
        L_0x002e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r6)
            io.ktor.utils.io.internal.WriteSessionImpl r4 = r4.writeSession
            r4.begin()
            r0.L$0 = r4     // Catch:{ all -> 0x004f }
            r0.label = r3     // Catch:{ all -> 0x004f }
            java.lang.Object r5 = r5.invoke(r4, r0)     // Catch:{ all -> 0x004f }
            if (r5 != r1) goto L_0x0049
            return r1
        L_0x0049:
            r4.complete()
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        L_0x004f:
            r5 = move-exception
            r4.complete()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeSuspendSession$suspendImpl(io.ktor.utils.io.ByteBufferChannel, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public void consumed(int i) {
        if (i >= 0) {
            ReadWriteBufferState state = getState();
            if (!state.capacity.tryReadExact(i)) {
                throw new IllegalStateException("Unable to consume " + i + " bytes: not enough available bytes");
            } else if (i > 0) {
                bytesRead(state.getReadBuffer(), state.capacity, i);
            }
        } else {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public final Object awaitAtLeast(int i, Continuation<? super Boolean> continuation) {
        if (i < 0) {
            throw new IllegalArgumentException(("atLeast parameter shouldn't be negative: " + i).toString());
        } else if (i > 4088) {
            throw new IllegalArgumentException(("atLeast parameter shouldn't be larger than max buffer size of 4088: " + i).toString());
        } else if (getState().capacity._availableForRead$internal >= i) {
            if (getState().getIdle() || (getState() instanceof ReadWriteBufferState.Writing)) {
                setupStateForRead();
            }
            return Boxing.boxBoolean(true);
        } else if (getState().getIdle() || (getState() instanceof ReadWriteBufferState.Writing)) {
            return awaitAtLeastSuspend(i, continuation);
        } else {
            if (i == 1) {
                return readSuspendImpl(1, continuation);
            }
            return readSuspend(i, continuation);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object awaitAtLeastSuspend(int r5, kotlin.coroutines.Continuation<? super java.lang.Boolean> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.utils.io.ByteBufferChannel$awaitAtLeastSuspend$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            io.ktor.utils.io.ByteBufferChannel$awaitAtLeastSuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel$awaitAtLeastSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$awaitAtLeastSuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$awaitAtLeastSuspend$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r5 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r5 = (io.ktor.utils.io.ByteBufferChannel) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0045
        L_0x002e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r6 = r4.readSuspend(r5, r0)
            if (r6 != r1) goto L_0x0044
            return r1
        L_0x0044:
            r5 = r4
        L_0x0045:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L_0x005a
            io.ktor.utils.io.internal.ReadWriteBufferState r0 = r5.getState()
            boolean r0 = r0.getIdle()
            if (r0 == 0) goto L_0x005a
            r5.setupStateForRead()
        L_0x005a:
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.awaitAtLeastSuspend(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public ByteBuffer request(int i, int i2) {
        ReadWriteBufferState state = getState();
        int i3 = state.capacity._availableForRead$internal;
        int i4 = this.readPosition;
        if (i3 < i2 + i) {
            return null;
        }
        if (!state.getIdle() && ((state instanceof ReadWriteBufferState.Reading) || (state instanceof ReadWriteBufferState.ReadingWriting))) {
            ByteBuffer readBuffer = state.getReadBuffer();
            prepareBuffer(readBuffer, carryIndex(readBuffer, i4 + i), i3 - i);
            if (readBuffer.remaining() >= i2) {
                return readBuffer;
            }
            return null;
        } else if (setupStateForRead() == null) {
            return null;
        } else {
            return request(i, i2);
        }
    }

    private final int afterBufferVisited(ByteBuffer byteBuffer, RingBufferCapacity ringBufferCapacity) {
        int position = byteBuffer.position() - this.readPosition;
        if (position > 0) {
            if (ringBufferCapacity.tryReadExact(position)) {
                bytesRead(byteBuffer, ringBufferCapacity, position);
                prepareBuffer(byteBuffer, this.readPosition, ringBufferCapacity._availableForRead$internal);
            } else {
                throw new IllegalStateException("Consumed more bytes than available");
            }
        }
        return position;
    }

    /* access modifiers changed from: private */
    public final Object readUTF8LineToAscii(Appendable appendable, int i, Continuation<? super Boolean> continuation) {
        if (getState() != ReadWriteBufferState.Terminated.INSTANCE) {
            return readUTF8LineToUtf8Suspend(appendable, i, continuation);
        }
        Throwable closedCause = getClosedCause();
        if (closedCause == null) {
            return Boxing.boxBoolean(false);
        }
        throw closedCause;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(15:(2:33|34)|35|36|37|38|39|40|41|42|43|(1:45)|51|54|23|(0)(0)) */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0117, code lost:
        r13 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0119, code lost:
        r14 = r25;
     */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00d2 A[SYNTHETIC, Splitter:B:33:0x00d2] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0123  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0182  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0184  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object readUTF8LineToUtf8Suspend(java.lang.Appendable r24, int r25, kotlin.coroutines.Continuation<? super java.lang.Boolean> r26) {
        /*
            r23 = this;
            r0 = r26
            boolean r1 = r0 instanceof io.ktor.utils.io.ByteBufferChannel$readUTF8LineToUtf8Suspend$1
            if (r1 == 0) goto L_0x0018
            r1 = r0
            io.ktor.utils.io.ByteBufferChannel$readUTF8LineToUtf8Suspend$1 r1 = (io.ktor.utils.io.ByteBufferChannel$readUTF8LineToUtf8Suspend$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0018
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            r2 = r23
            goto L_0x001f
        L_0x0018:
            io.ktor.utils.io.ByteBufferChannel$readUTF8LineToUtf8Suspend$1 r1 = new io.ktor.utils.io.ByteBufferChannel$readUTF8LineToUtf8Suspend$1
            r2 = r23
            r1.<init>(r2, r0)
        L_0x001f:
            java.lang.Object r0 = r1.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r1.label
            r5 = 2
            r6 = 1
            if (r4 == 0) goto L_0x0089
            if (r4 == r6) goto L_0x0051
            if (r4 != r5) goto L_0x0049
            java.lang.Object r3 = r1.L$3
            kotlin.jvm.internal.Ref$BooleanRef r3 = (kotlin.jvm.internal.Ref.BooleanRef) r3
            java.lang.Object r4 = r1.L$2
            kotlin.jvm.internal.Ref$BooleanRef r4 = (kotlin.jvm.internal.Ref.BooleanRef) r4
            java.lang.Object r5 = r1.L$1
            kotlin.jvm.internal.Ref$IntRef r5 = (kotlin.jvm.internal.Ref.IntRef) r5
            java.lang.Object r1 = r1.L$0
            io.ktor.utils.io.ByteBufferChannel r1 = (io.ktor.utils.io.ByteBufferChannel) r1
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ EOFException -> 0x0043 }
            goto L_0x0044
        L_0x0043:
        L_0x0044:
            r9 = r4
            r4 = r1
            r1 = 1
            goto L_0x016b
        L_0x0049:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0051:
            int r4 = r1.I$0
            java.lang.Object r7 = r1.L$8
            kotlin.jvm.internal.Ref$IntRef r7 = (kotlin.jvm.internal.Ref.IntRef) r7
            java.lang.Object r8 = r1.L$7
            kotlin.jvm.internal.Ref$ObjectRef r8 = (kotlin.jvm.internal.Ref.ObjectRef) r8
            java.lang.Object r9 = r1.L$6
            char[] r9 = (char[]) r9
            java.lang.Object r10 = r1.L$5
            kotlin.jvm.internal.Ref$BooleanRef r10 = (kotlin.jvm.internal.Ref.BooleanRef) r10
            java.lang.Object r11 = r1.L$4
            kotlin.jvm.internal.Ref$BooleanRef r11 = (kotlin.jvm.internal.Ref.BooleanRef) r11
            java.lang.Object r12 = r1.L$3
            kotlin.jvm.internal.Ref$IntRef r12 = (kotlin.jvm.internal.Ref.IntRef) r12
            java.lang.Object r13 = r1.L$2
            kotlin.jvm.internal.Ref$IntRef r13 = (kotlin.jvm.internal.Ref.IntRef) r13
            java.lang.Object r14 = r1.L$1
            java.lang.Appendable r14 = (java.lang.Appendable) r14
            java.lang.Object r15 = r1.L$0
            io.ktor.utils.io.ByteBufferChannel r15 = (io.ktor.utils.io.ByteBufferChannel) r15
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ EOFException -> 0x007b }
            goto L_0x007c
        L_0x007b:
        L_0x007c:
            r0 = r14
            r14 = r9
            r9 = r11
            r11 = r7
            r7 = r1
            r1 = r4
            r4 = r15
            r15 = r8
            r8 = r10
            r10 = r13
            r13 = r12
            goto L_0x0120
        L_0x0089:
            kotlin.ResultKt.throwOnFailure(r0)
            kotlin.jvm.internal.Ref$IntRef r0 = new kotlin.jvm.internal.Ref$IntRef
            r0.<init>()
            kotlin.jvm.internal.Ref$IntRef r4 = new kotlin.jvm.internal.Ref$IntRef
            r4.<init>()
            r4.element = r6
            kotlin.jvm.internal.Ref$BooleanRef r7 = new kotlin.jvm.internal.Ref$BooleanRef
            r7.<init>()
            kotlin.jvm.internal.Ref$BooleanRef r8 = new kotlin.jvm.internal.Ref$BooleanRef
            r8.<init>()
            r9 = 8192(0x2000, float:1.14794E-41)
            char[] r9 = new char[r9]
            kotlin.jvm.internal.Ref$ObjectRef r10 = new kotlin.jvm.internal.Ref$ObjectRef
            r10.<init>()
            kotlin.jvm.internal.Ref$IntRef r11 = new kotlin.jvm.internal.Ref$IntRef
            r11.<init>()
            r13 = r4
            r14 = r9
            r15 = r10
            r10 = r0
            r4 = r2
            r9 = r7
            r0 = r24
            r7 = r1
            r1 = r25
        L_0x00bb:
            boolean r12 = r4.isClosedForRead()
            if (r12 != 0) goto L_0x0123
            boolean r12 = r8.element
            if (r12 != 0) goto L_0x0123
            boolean r12 = r9.element
            if (r12 != 0) goto L_0x0123
            r12 = 2147483647(0x7fffffff, float:NaN)
            if (r1 == r12) goto L_0x00d2
            int r12 = r10.element
            if (r12 > r1) goto L_0x0123
        L_0x00d2:
            int r12 = r13.element     // Catch:{ EOFException -> 0x011e }
            io.ktor.utils.io.ByteBufferChannel$readUTF8LineToUtf8Suspend$2 r22 = new io.ktor.utils.io.ByteBufferChannel$readUTF8LineToUtf8Suspend$2     // Catch:{ EOFException -> 0x011e }
            r5 = r12
            r12 = r22
            r24 = r13
            r13 = r15
            r25 = r14
            r14 = r1
            r6 = r15
            r15 = r25
            r16 = r10
            r17 = r24
            r18 = r8
            r19 = r9
            r20 = r0
            r21 = r11
            r12.<init>(r13, r14, r15, r16, r17, r18, r19, r20, r21)     // Catch:{ EOFException -> 0x0117 }
            r12 = r22
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12     // Catch:{ EOFException -> 0x0117 }
            r7.L$0 = r4     // Catch:{ EOFException -> 0x0117 }
            r7.L$1 = r0     // Catch:{ EOFException -> 0x0117 }
            r7.L$2 = r10     // Catch:{ EOFException -> 0x0117 }
            r13 = r24
            r7.L$3 = r13     // Catch:{ EOFException -> 0x0119 }
            r7.L$4 = r9     // Catch:{ EOFException -> 0x0119 }
            r7.L$5 = r8     // Catch:{ EOFException -> 0x0119 }
            r14 = r25
            r7.L$6 = r14     // Catch:{ EOFException -> 0x011b }
            r7.L$7 = r6     // Catch:{ EOFException -> 0x011b }
            r7.L$8 = r11     // Catch:{ EOFException -> 0x011b }
            r7.I$0 = r1     // Catch:{ EOFException -> 0x011b }
            r15 = 1
            r7.label = r15     // Catch:{ EOFException -> 0x011b }
            java.lang.Object r5 = r4.read(r5, r12, r7)     // Catch:{ EOFException -> 0x011b }
            if (r5 != r3) goto L_0x011c
            return r3
        L_0x0117:
            r13 = r24
        L_0x0119:
            r14 = r25
        L_0x011b:
        L_0x011c:
            r15 = r6
            goto L_0x0120
        L_0x011e:
            r6 = r15
        L_0x0120:
            r5 = 2
            r6 = 1
            goto L_0x00bb
        L_0x0123:
            r6 = r15
            T r0 = r6.element
            if (r0 == 0) goto L_0x0134
            io.ktor.utils.io.pool.ObjectPool r0 = io.ktor.utils.io.internal.ObjectPoolKt.getBufferPool()
            T r1 = r6.element
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            r0.recycle(r1)
        L_0x0134:
            boolean r0 = r4.isClosedForRead()
            if (r0 != 0) goto L_0x016e
            boolean r0 = r9.element
            if (r0 == 0) goto L_0x016e
            boolean r0 = r8.element
            if (r0 != 0) goto L_0x016e
            io.ktor.utils.io.ByteBufferChannel$readUTF8LineToUtf8Suspend$3 r0 = new io.ktor.utils.io.ByteBufferChannel$readUTF8LineToUtf8Suspend$3     // Catch:{ EOFException -> 0x0167 }
            r0.<init>(r8)     // Catch:{ EOFException -> 0x0167 }
            kotlin.jvm.functions.Function1 r0 = (kotlin.jvm.functions.Function1) r0     // Catch:{ EOFException -> 0x0167 }
            r7.L$0 = r4     // Catch:{ EOFException -> 0x0167 }
            r7.L$1 = r10     // Catch:{ EOFException -> 0x0167 }
            r7.L$2 = r9     // Catch:{ EOFException -> 0x0167 }
            r7.L$3 = r8     // Catch:{ EOFException -> 0x0167 }
            r1 = 0
            r7.L$4 = r1     // Catch:{ EOFException -> 0x0167 }
            r7.L$5 = r1     // Catch:{ EOFException -> 0x0167 }
            r7.L$6 = r1     // Catch:{ EOFException -> 0x0167 }
            r7.L$7 = r1     // Catch:{ EOFException -> 0x0167 }
            r7.L$8 = r1     // Catch:{ EOFException -> 0x0167 }
            r1 = 2
            r7.label = r1     // Catch:{ EOFException -> 0x0167 }
            r1 = 1
            java.lang.Object r0 = r4.read(r1, r0, r7)     // Catch:{ EOFException -> 0x0168 }
            if (r0 != r3) goto L_0x0169
            return r3
        L_0x0167:
            r1 = 1
        L_0x0168:
        L_0x0169:
            r3 = r8
            r5 = r10
        L_0x016b:
            r8 = r3
            r10 = r5
            goto L_0x016f
        L_0x016e:
            r1 = 1
        L_0x016f:
            boolean r0 = r4.isClosedForRead()
            if (r0 == 0) goto L_0x0179
            int r0 = r10.element
            if (r0 > 0) goto L_0x0184
        L_0x0179:
            boolean r0 = r8.element
            if (r0 != 0) goto L_0x0184
            boolean r0 = r9.element
            if (r0 == 0) goto L_0x0182
            goto L_0x0184
        L_0x0182:
            r6 = 0
            goto L_0x0185
        L_0x0184:
            r6 = 1
        L_0x0185:
            java.lang.Boolean r0 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readUTF8LineToUtf8Suspend(java.lang.Appendable, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0057 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ java.lang.Object readUTF8Line$suspendImpl(io.ktor.utils.io.ByteBufferChannel r5, int r6, kotlin.coroutines.Continuation<? super java.lang.String> r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteBufferChannel$readUTF8Line$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            io.ktor.utils.io.ByteBufferChannel$readUTF8Line$1 r0 = (io.ktor.utils.io.ByteBufferChannel$readUTF8Line$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$readUTF8Line$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readUTF8Line$1
            r0.<init>(r5, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r5 = r0.L$0
            java.lang.StringBuilder r5 = (java.lang.StringBuilder) r5
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x004f
        L_0x002e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r7)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r2 = r7
            java.lang.Appendable r2 = (java.lang.Appendable) r2
            r0.L$0 = r7
            r0.label = r3
            java.lang.Object r5 = r5.readUTF8LineTo(r2, r6, r0)
            if (r5 != r1) goto L_0x004c
            return r1
        L_0x004c:
            r4 = r7
            r7 = r5
            r5 = r4
        L_0x004f:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r6 = r7.booleanValue()
            if (r6 != 0) goto L_0x0059
            r5 = 0
            return r5
        L_0x0059:
            java.lang.String r5 = r5.toString()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readUTF8Line$suspendImpl(io.ktor.utils.io.ByteBufferChannel, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object readRemaining$suspendImpl(ByteBufferChannel byteBufferChannel, long j, Continuation<? super ByteReadPacket> continuation) {
        if (!byteBufferChannel.isClosedForWrite()) {
            return byteBufferChannel.readRemainingSuspend(j, continuation);
        }
        Throwable closedCause = byteBufferChannel.getClosedCause();
        if (closedCause == null) {
            return byteBufferChannel.remainingPacket(j);
        }
        Void unused = ByteBufferChannelKt.rethrowClosed(closedCause);
        throw new KotlinNothingValueException();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x007a A[Catch:{ all -> 0x003f, all -> 0x00da }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00b0 A[Catch:{ all -> 0x003f, all -> 0x00da }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c2 A[Catch:{ all -> 0x003f, all -> 0x00da }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00c7 A[SYNTHETIC, Splitter:B:39:0x00c7] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00d0 A[Catch:{ all -> 0x003f, all -> 0x00da }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00d5 A[Catch:{ all -> 0x003f, all -> 0x00da }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object readRemainingSuspend(long r13, kotlin.coroutines.Continuation<? super io.ktor.utils.io.core.ByteReadPacket> r15) {
        /*
            r12 = this;
            boolean r0 = r15 instanceof io.ktor.utils.io.ByteBufferChannel$readRemainingSuspend$1
            if (r0 == 0) goto L_0x0014
            r0 = r15
            io.ktor.utils.io.ByteBufferChannel$readRemainingSuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel$readRemainingSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$readRemainingSuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readRemainingSuspend$1
            r0.<init>(r12, r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x004a
            if (r2 != r3) goto L_0x0042
            java.lang.Object r13 = r0.L$4
            io.ktor.utils.io.core.internal.ChunkBuffer r13 = (io.ktor.utils.io.core.internal.ChunkBuffer) r13
            java.lang.Object r14 = r0.L$3
            io.ktor.utils.io.core.Output r14 = (io.ktor.utils.io.core.Output) r14
            java.lang.Object r2 = r0.L$2
            kotlin.jvm.internal.Ref$LongRef r2 = (kotlin.jvm.internal.Ref.LongRef) r2
            java.lang.Object r4 = r0.L$1
            io.ktor.utils.io.core.BytePacketBuilder r4 = (io.ktor.utils.io.core.BytePacketBuilder) r4
            java.lang.Object r5 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r5 = (io.ktor.utils.io.ByteBufferChannel) r5
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ all -> 0x003f }
            goto L_0x00b3
        L_0x003f:
            r13 = move-exception
            goto L_0x00d6
        L_0x0042:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L_0x004a:
            kotlin.ResultKt.throwOnFailure(r15)
            io.ktor.utils.io.core.BytePacketBuilder r15 = new io.ktor.utils.io.core.BytePacketBuilder
            r2 = 0
            r15.<init>(r2, r3, r2)
            kotlin.jvm.internal.Ref$LongRef r4 = new kotlin.jvm.internal.Ref$LongRef     // Catch:{ all -> 0x00dd }
            r4.<init>()     // Catch:{ all -> 0x00dd }
            r4.element = r13     // Catch:{ all -> 0x00dd }
            r13 = r15
            io.ktor.utils.io.core.Output r13 = (io.ktor.utils.io.core.Output) r13     // Catch:{ all -> 0x00dd }
            io.ktor.utils.io.core.internal.ChunkBuffer r14 = io.ktor.utils.io.core.internal.UnsafeKt.prepareWriteHead(r13, r3, r2)     // Catch:{ all -> 0x00dd }
            r2 = r4
            r4 = r15
            r15 = r12
            r11 = r14
            r14 = r13
            r13 = r11
        L_0x0067:
            r6 = r13
            io.ktor.utils.io.core.Buffer r6 = (io.ktor.utils.io.core.Buffer) r6     // Catch:{ all -> 0x003f }
            int r5 = r6.getLimit()     // Catch:{ all -> 0x003f }
            int r7 = r6.getWritePosition()     // Catch:{ all -> 0x003f }
            int r5 = r5 - r7
            long r7 = (long) r5     // Catch:{ all -> 0x003f }
            long r9 = r2.element     // Catch:{ all -> 0x003f }
            int r5 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r5 <= 0) goto L_0x0080
            long r7 = r2.element     // Catch:{ all -> 0x003f }
            int r5 = (int) r7     // Catch:{ all -> 0x003f }
            r6.resetForWrite(r5)     // Catch:{ all -> 0x003f }
        L_0x0080:
            r9 = 6
            r10 = 0
            r7 = 0
            r8 = 0
            r5 = r15
            int r5 = readAsMuchAsPossible$default(r5, r6, r7, r8, r9, r10)     // Catch:{ all -> 0x003f }
            long r6 = r2.element     // Catch:{ all -> 0x003f }
            long r8 = (long) r5     // Catch:{ all -> 0x003f }
            long r6 = r6 - r8
            r2.element = r6     // Catch:{ all -> 0x003f }
            long r5 = r2.element     // Catch:{ all -> 0x003f }
            r7 = 0
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 <= 0) goto L_0x00bf
            boolean r5 = r15.isClosedForRead()     // Catch:{ all -> 0x003f }
            if (r5 != 0) goto L_0x00bf
            r0.L$0 = r15     // Catch:{ all -> 0x003f }
            r0.L$1 = r4     // Catch:{ all -> 0x003f }
            r0.L$2 = r2     // Catch:{ all -> 0x003f }
            r0.L$3 = r14     // Catch:{ all -> 0x003f }
            r0.L$4 = r13     // Catch:{ all -> 0x003f }
            r0.label = r3     // Catch:{ all -> 0x003f }
            java.lang.Object r5 = r15.readSuspend(r3, r0)     // Catch:{ all -> 0x003f }
            if (r5 != r1) goto L_0x00b0
            return r1
        L_0x00b0:
            r11 = r5
            r5 = r15
            r15 = r11
        L_0x00b3:
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch:{ all -> 0x003f }
            boolean r15 = r15.booleanValue()     // Catch:{ all -> 0x003f }
            if (r15 == 0) goto L_0x00be
            r15 = r5
            r5 = 1
            goto L_0x00c0
        L_0x00be:
            r15 = r5
        L_0x00bf:
            r5 = 0
        L_0x00c0:
            if (r5 == 0) goto L_0x00c7
            io.ktor.utils.io.core.internal.ChunkBuffer r13 = io.ktor.utils.io.core.internal.UnsafeKt.prepareWriteHead(r14, r3, r13)     // Catch:{ all -> 0x003f }
            goto L_0x0067
        L_0x00c7:
            r14.afterHeadWrite()     // Catch:{ all -> 0x00da }
            java.lang.Throwable r13 = r15.getClosedCause()     // Catch:{ all -> 0x00da }
            if (r13 != 0) goto L_0x00d5
            io.ktor.utils.io.core.ByteReadPacket r13 = r4.build()     // Catch:{ all -> 0x00da }
            return r13
        L_0x00d5:
            throw r13     // Catch:{ all -> 0x00da }
        L_0x00d6:
            r14.afterHeadWrite()     // Catch:{ all -> 0x00da }
            throw r13     // Catch:{ all -> 0x00da }
        L_0x00da:
            r13 = move-exception
            r15 = r4
            goto L_0x00de
        L_0x00dd:
            r13 = move-exception
        L_0x00de:
            r15.release()
            goto L_0x00e3
        L_0x00e2:
            throw r13
        L_0x00e3:
            goto L_0x00e2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readRemainingSuspend(long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public final void resumeReadOp() {
        Throwable th = null;
        Continuation continuation = (Continuation) _readOp$FU.getAndSet(this, (Object) null);
        if (continuation != null) {
            ClosedElement closed = getClosed();
            if (closed != null) {
                th = closed.getCause();
            }
            if (th != null) {
                Result.Companion companion = Result.Companion;
                continuation.resumeWith(Result.m1774constructorimpl(ResultKt.createFailure(th)));
                return;
            }
            Result.Companion companion2 = Result.Companion;
            continuation.resumeWith(Result.m1774constructorimpl(true));
        }
    }

    private final void resumeReadOp(Function0<? extends Throwable> function0) {
        Continuation continuation = (Continuation) _readOp$FU.getAndSet(this, (Object) null);
        if (continuation != null) {
            Result.Companion companion = Result.Companion;
            continuation.resumeWith(Result.m1774constructorimpl(ResultKt.createFailure((Throwable) function0.invoke())));
        }
    }

    private final void resumeWriteOp() {
        Continuation<Unit> writeOp;
        ClosedElement closed;
        Object obj;
        do {
            writeOp = getWriteOp();
            if (writeOp != null) {
                closed = getClosed();
                if (closed == null && this.joining != null) {
                    ReadWriteBufferState state = getState();
                    if (!(state instanceof ReadWriteBufferState.Writing) && !(state instanceof ReadWriteBufferState.ReadingWriting) && state != ReadWriteBufferState.Terminated.INSTANCE) {
                        return;
                    }
                }
            } else {
                return;
            }
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_writeOp$FU, this, writeOp, (Object) null));
        if (closed == null) {
            Result.Companion companion = Result.Companion;
            obj = Unit.INSTANCE;
        } else {
            Result.Companion companion2 = Result.Companion;
            obj = ResultKt.createFailure(closed.getSendException());
        }
        writeOp.resumeWith(Result.m1774constructorimpl(obj));
    }

    private final void resumeClosed(Throwable th) {
        Continuation continuation = (Continuation) _readOp$FU.getAndSet(this, (Object) null);
        if (continuation != null) {
            if (th != null) {
                Result.Companion companion = Result.Companion;
                continuation.resumeWith(Result.m1774constructorimpl(ResultKt.createFailure(th)));
            } else {
                Boolean valueOf = Boolean.valueOf(getState().capacity._availableForRead$internal > 0);
                Result.Companion companion2 = Result.Companion;
                continuation.resumeWith(Result.m1774constructorimpl(valueOf));
            }
        }
        Continuation continuation2 = (Continuation) _writeOp$FU.getAndSet(this, (Object) null);
        if (continuation2 != null) {
            Result.Companion companion3 = Result.Companion;
            if (th == null) {
                th = new ClosedWriteChannelException(ByteBufferChannelKt.DEFAULT_CLOSE_MESSAGE);
            }
            continuation2.resumeWith(Result.m1774constructorimpl(ResultKt.createFailure(th)));
        }
    }

    static /* synthetic */ Object awaitContent$suspendImpl(ByteBufferChannel byteBufferChannel, Continuation<? super Unit> continuation) {
        Object readSuspend = byteBufferChannel.readSuspend(1, continuation);
        return readSuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? readSuspend : Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public final Object readSuspend(int i, Continuation<? super Boolean> continuation) {
        boolean z = true;
        if (getState().capacity._availableForRead$internal >= i) {
            return Boxing.boxBoolean(true);
        }
        ClosedElement closed = getClosed();
        if (closed != null) {
            Throwable cause = closed.getCause();
            if (cause == null) {
                RingBufferCapacity ringBufferCapacity = getState().capacity;
                if (!ringBufferCapacity.flush() || ringBufferCapacity._availableForRead$internal < i) {
                    z = false;
                }
                if (getReadOp() == null) {
                    return Boxing.boxBoolean(z);
                }
                throw new IllegalStateException("Read operation is already in progress");
            }
            Void unused = ByteBufferChannelKt.rethrowClosed(cause);
            throw new KotlinNothingValueException();
        } else if (i == 1) {
            return readSuspendImpl(1, continuation);
        } else {
            return readSuspendLoop(i, continuation);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object readSuspendLoop(int r6, kotlin.coroutines.Continuation<? super java.lang.Boolean> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteBufferChannel$readSuspendLoop$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            io.ktor.utils.io.ByteBufferChannel$readSuspendLoop$1 r0 = (io.ktor.utils.io.ByteBufferChannel$readSuspendLoop$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$readSuspendLoop$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readSuspendLoop$1
            r0.<init>(r5, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x0039
            if (r2 != r4) goto L_0x0031
            int r6 = r0.I$0
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0096
        L_0x0031:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0039:
            kotlin.ResultKt.throwOnFailure(r7)
            r2 = r5
        L_0x003d:
            io.ktor.utils.io.internal.ReadWriteBufferState r7 = r2.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r7 = r7.capacity
            int r7 = r7._availableForRead$internal
            if (r7 < r6) goto L_0x004c
            java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            return r6
        L_0x004c:
            io.ktor.utils.io.internal.ClosedElement r7 = r2.getClosed()
            if (r7 == 0) goto L_0x0089
            java.lang.Throwable r0 = r7.getCause()
            if (r0 != 0) goto L_0x007c
            io.ktor.utils.io.internal.ReadWriteBufferState r7 = r2.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r7 = r7.capacity
            boolean r0 = r7.flush()
            if (r0 == 0) goto L_0x0069
            int r7 = r7._availableForRead$internal
            if (r7 < r6) goto L_0x0069
            r3 = 1
        L_0x0069:
            kotlin.coroutines.Continuation r6 = r2.getReadOp()
            if (r6 != 0) goto L_0x0074
            java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r6
        L_0x0074:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "Read operation is already in progress"
            r6.<init>(r7)
            throw r6
        L_0x007c:
            java.lang.Throwable r6 = r7.getCause()
            java.lang.Void unused = io.ktor.utils.io.ByteBufferChannelKt.rethrowClosed(r6)
            kotlin.KotlinNothingValueException r6 = new kotlin.KotlinNothingValueException
            r6.<init>()
            throw r6
        L_0x0089:
            r0.L$0 = r2
            r0.I$0 = r6
            r0.label = r4
            java.lang.Object r7 = r2.readSuspendImpl(r6, r0)
            if (r7 != r1) goto L_0x0096
            return r1
        L_0x0096:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 != 0) goto L_0x003d
            java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readSuspendLoop(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final boolean readSuspendPredicate(int i) {
        ReadWriteBufferState state = getState();
        return state.capacity._availableForRead$internal < i && (this.joining == null || getWriteOp() == null || (state != ReadWriteBufferState.IdleEmpty.INSTANCE && !(state instanceof ReadWriteBufferState.IdleNonEmpty)));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object readSuspendImpl(int r5, kotlin.coroutines.Continuation<? super java.lang.Boolean> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.utils.io.ByteBufferChannel$readSuspendImpl$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            io.ktor.utils.io.ByteBufferChannel$readSuspendImpl$1 r0 = (io.ktor.utils.io.ByteBufferChannel$readSuspendImpl$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$readSuspendImpl$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readSuspendImpl$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            int r5 = r0.I$0
            java.lang.Object r5 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r5 = (io.ktor.utils.io.ByteBufferChannel) r5
            kotlin.ResultKt.throwOnFailure(r6)     // Catch:{ all -> 0x0030 }
            goto L_0x0080
        L_0x0030:
            r6 = move-exception
            goto L_0x0083
        L_0x0032:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r6)
            io.ktor.utils.io.internal.ReadWriteBufferState r6 = r4.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r2 = r6.capacity
            int r2 = r2._availableForRead$internal
            if (r2 >= r5) goto L_0x0088
            io.ktor.utils.io.internal.JoiningState r2 = r4.joining
            if (r2 == 0) goto L_0x0059
            kotlin.coroutines.Continuation r2 = r4.getWriteOp()
            if (r2 == 0) goto L_0x0059
            io.ktor.utils.io.internal.ReadWriteBufferState$IdleEmpty r2 = io.ktor.utils.io.internal.ReadWriteBufferState.IdleEmpty.INSTANCE
            if (r6 == r2) goto L_0x0088
            boolean r6 = r6 instanceof io.ktor.utils.io.internal.ReadWriteBufferState.IdleNonEmpty
            if (r6 != 0) goto L_0x0088
        L_0x0059:
            r0.L$0 = r4     // Catch:{ all -> 0x0081 }
            r0.I$0 = r5     // Catch:{ all -> 0x0081 }
            r0.label = r3     // Catch:{ all -> 0x0081 }
            r6 = r0
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6     // Catch:{ all -> 0x0081 }
            io.ktor.utils.io.internal.CancellableReusableContinuation<java.lang.Boolean> r2 = r4.readSuspendContinuationCache     // Catch:{ all -> 0x0081 }
            r3 = r2
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3     // Catch:{ all -> 0x0081 }
            r4.suspensionForSize(r5, r3)     // Catch:{ all -> 0x0081 }
            kotlin.coroutines.Continuation r5 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r6)     // Catch:{ all -> 0x0081 }
            java.lang.Object r6 = r2.completeSuspendBlock(r5)     // Catch:{ all -> 0x0081 }
            java.lang.Object r5 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()     // Catch:{ all -> 0x0081 }
            if (r6 != r5) goto L_0x007d
            kotlin.coroutines.Continuation r0 = (kotlin.coroutines.Continuation) r0     // Catch:{ all -> 0x0081 }
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r0)     // Catch:{ all -> 0x0081 }
        L_0x007d:
            if (r6 != r1) goto L_0x0080
            return r1
        L_0x0080:
            return r6
        L_0x0081:
            r6 = move-exception
            r5 = r4
        L_0x0083:
            r0 = 0
            r5.setReadOp(r0)
            throw r6
        L_0x0088:
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readSuspendImpl(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public final boolean shouldResumeReadOp() {
        return this.joining != null && (getState() == ReadWriteBufferState.IdleEmpty.INSTANCE || (getState() instanceof ReadWriteBufferState.IdleNonEmpty));
    }

    /* access modifiers changed from: private */
    public final boolean writeSuspendPredicate(int i) {
        JoiningState joiningState = this.joining;
        ReadWriteBufferState state = getState();
        if (getClosed() != null) {
            return false;
        }
        if (joiningState == null) {
            if (state.capacity._availableForWrite$internal >= i || state == ReadWriteBufferState.IdleEmpty.INSTANCE) {
                return false;
            }
        } else if (state == ReadWriteBufferState.Terminated.INSTANCE || (state instanceof ReadWriteBufferState.Writing) || (state instanceof ReadWriteBufferState.ReadingWriting)) {
            return false;
        }
        return true;
    }

    public final Object tryWriteSuspend$ktor_io(int i, Continuation<? super Unit> continuation) {
        Throwable sendException;
        if (!writeSuspendPredicate(i)) {
            ClosedElement closed = getClosed();
            if (closed == null || (sendException = closed.getSendException()) == null) {
                return Unit.INSTANCE;
            }
            Void unused = ByteBufferChannelKt.rethrowClosed(sendException);
            throw new KotlinNothingValueException();
        }
        this.writeSuspensionSize = i;
        if (this.attachedJob != null) {
            Object invoke = this.writeSuspension.invoke(continuation);
            if (invoke == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return invoke == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke : Unit.INSTANCE;
        }
        CancellableReusableContinuation<Unit> cancellableReusableContinuation = this.writeSuspendContinuationCache;
        this.writeSuspension.invoke(cancellableReusableContinuation);
        Object completeSuspendBlock = cancellableReusableContinuation.completeSuspendBlock(IntrinsicsKt.intercepted(continuation));
        if (completeSuspendBlock == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return completeSuspendBlock == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? completeSuspendBlock : Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object writeSuspend(int r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof io.ktor.utils.io.ByteBufferChannel$writeSuspend$3
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ktor.utils.io.ByteBufferChannel$writeSuspend$3 r0 = (io.ktor.utils.io.ByteBufferChannel$writeSuspend$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.ByteBufferChannel$writeSuspend$3 r0 = new io.ktor.utils.io.ByteBufferChannel$writeSuspend$3
            r0.<init>(r6, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0038
            if (r2 != r3) goto L_0x0030
            int r7 = r0.I$0
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x003c
        L_0x0030:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0038:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r6
        L_0x003c:
            boolean r8 = r2.writeSuspendPredicate(r7)
            if (r8 == 0) goto L_0x006d
            r0.L$0 = r2
            r0.I$0 = r7
            r0.label = r3
            r8 = r0
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            kotlinx.coroutines.CancellableContinuationImpl r4 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r5 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r8)
            r4.<init>(r5, r3)
            r4.initCancellability()
            r5 = r4
            kotlinx.coroutines.CancellableContinuation r5 = (kotlinx.coroutines.CancellableContinuation) r5
            r2.writeSuspendBlock(r7, r5)
            java.lang.Object r4 = r4.getResult()
            java.lang.Object r5 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r4 != r5) goto L_0x006a
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r8)
        L_0x006a:
            if (r4 != r1) goto L_0x003c
            return r1
        L_0x006d:
            io.ktor.utils.io.internal.ClosedElement r7 = r2.getClosed()
            if (r7 == 0) goto L_0x0083
            java.lang.Throwable r7 = r7.getSendException()
            if (r7 != 0) goto L_0x007a
            goto L_0x0083
        L_0x007a:
            java.lang.Void unused = io.ktor.utils.io.ByteBufferChannelKt.rethrowClosed(r7)
            kotlin.KotlinNothingValueException r7 = new kotlin.KotlinNothingValueException
            r7.<init>()
            throw r7
        L_0x0083:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeSuspend(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public final void writeSuspendBlock(int i, CancellableContinuation<? super Unit> cancellableContinuation) {
        Throwable sendException;
        while (true) {
            ClosedElement closed = getClosed();
            if (closed != null && (sendException = closed.getSendException()) != null) {
                Void unused = ByteBufferChannelKt.rethrowClosed(sendException);
                throw new KotlinNothingValueException();
            } else if (!writeSuspendPredicate(i)) {
                Result.Companion companion = Result.Companion;
                cancellableContinuation.resumeWith(Result.m1774constructorimpl(Unit.INSTANCE));
                break;
            } else {
                while (getWriteOp() == null) {
                    if (writeSuspendPredicate(i)) {
                        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _writeOp$FU;
                        Continuation continuation = cancellableContinuation;
                        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, (Object) null, continuation)) {
                            if (writeSuspendPredicate(i) || !AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, continuation, (Object) null)) {
                                break;
                            }
                        }
                    }
                }
                throw new IllegalStateException("Operation is already in progress".toString());
            }
        }
        flushImpl(i);
        if (shouldResumeReadOp()) {
            resumeReadOp();
        }
    }

    private final ReadWriteBufferState.Initial newBuffer() {
        ReadWriteBufferState.Initial borrow = this.pool.borrow();
        borrow.capacity.resetForWrite();
        return borrow;
    }

    private final void releaseBuffer(ReadWriteBufferState.Initial initial) {
        this.pool.recycle(initial);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* renamed from: peekTo-lBXzO7A$suspendImpl  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ java.lang.Object m146peekTolBXzO7A$suspendImpl(io.ktor.utils.io.ByteBufferChannel r16, java.nio.ByteBuffer r17, long r18, long r20, long r22, long r24, kotlin.coroutines.Continuation<? super java.lang.Long> r26) {
        /*
            r0 = r16
            r1 = r26
            boolean r2 = r1 instanceof io.ktor.utils.io.ByteBufferChannel$peekTo$1
            if (r2 == 0) goto L_0x0018
            r2 = r1
            io.ktor.utils.io.ByteBufferChannel$peekTo$1 r2 = (io.ktor.utils.io.ByteBufferChannel$peekTo$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r1 = r2.label
            int r1 = r1 - r4
            r2.label = r1
            goto L_0x001d
        L_0x0018:
            io.ktor.utils.io.ByteBufferChannel$peekTo$1 r2 = new io.ktor.utils.io.ByteBufferChannel$peekTo$1
            r2.<init>(r0, r1)
        L_0x001d:
            java.lang.Object r1 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 1
            if (r4 == 0) goto L_0x003a
            if (r4 != r5) goto L_0x0032
            java.lang.Object r0 = r2.L$0
            kotlin.jvm.internal.Ref$IntRef r0 = (kotlin.jvm.internal.Ref.IntRef) r0
            kotlin.ResultKt.throwOnFailure(r1)     // Catch:{ EOFException -> 0x0068 }
            goto L_0x0068
        L_0x0032:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r1)
            kotlin.jvm.internal.Ref$IntRef r1 = new kotlin.jvm.internal.Ref$IntRef
            r1.<init>()
            long r6 = r22 + r20
            r8 = 4088(0xff8, double:2.0197E-320)
            long r6 = kotlin.ranges.RangesKt.coerceAtMost((long) r6, (long) r8)
            int r4 = (int) r6
            io.ktor.utils.io.ByteBufferChannel$peekTo$2 r15 = new io.ktor.utils.io.ByteBufferChannel$peekTo$2     // Catch:{ EOFException -> 0x0067 }
            r6 = r15
            r7 = r20
            r9 = r24
            r11 = r17
            r12 = r18
            r14 = r1
            r6.<init>(r7, r9, r11, r12, r14)     // Catch:{ EOFException -> 0x0067 }
            kotlin.jvm.functions.Function1 r15 = (kotlin.jvm.functions.Function1) r15     // Catch:{ EOFException -> 0x0067 }
            r2.L$0 = r1     // Catch:{ EOFException -> 0x0067 }
            r2.label = r5     // Catch:{ EOFException -> 0x0067 }
            java.lang.Object r0 = r0.read(r4, r15, r2)     // Catch:{ EOFException -> 0x0067 }
            if (r0 != r3) goto L_0x0067
            return r3
        L_0x0067:
            r0 = r1
        L_0x0068:
            int r0 = r0.element
            long r0 = (long) r0
            java.lang.Long r0 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.m146peekTolBXzO7A$suspendImpl(io.ktor.utils.io.ByteBufferChannel, java.nio.ByteBuffer, long, long, long, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public String toString() {
        return "ByteBufferChannel(" + hashCode() + ", " + getState() + ')';
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lio/ktor/utils/io/ByteBufferChannel$Companion;", "", "()V", "ReservedLongIndex", "", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ByteBufferChannel.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: CodeShrinkVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Don't wrap MOVE or CONST insns: 0x0029: MOVE  (r0v3 io.ktor.utils.io.internal.ReadWriteBufferState$IdleNonEmpty) = 
          (r0v2 io.ktor.utils.io.internal.ReadWriteBufferState$IdleNonEmpty)
        
        	at jadx.core.dex.instructions.args.InsnArg.wrapArg(InsnArg.java:164)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.assignInline(CodeShrinkVisitor.java:133)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.checkInline(CodeShrinkVisitor.java:118)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkBlock(CodeShrinkVisitor.java:65)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkMethod(CodeShrinkVisitor.java:43)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.visit(CodeShrinkVisitor.java:35)
        */
    public final void restoreStateAfterWrite$ktor_io() {
        /*
            r5 = this;
            r0 = 0
        L_0x0001:
            java.lang.Object r1 = r5._state
            r2 = r1
            io.ktor.utils.io.internal.ReadWriteBufferState r2 = (io.ktor.utils.io.internal.ReadWriteBufferState) r2
            io.ktor.utils.io.internal.ReadWriteBufferState r2 = r2.stopWriting$ktor_io()
            boolean r3 = r2 instanceof io.ktor.utils.io.internal.ReadWriteBufferState.IdleNonEmpty
            if (r3 == 0) goto L_0x001d
            io.ktor.utils.io.internal.RingBufferCapacity r3 = r2.capacity
            boolean r3 = r3.isEmpty()
            if (r3 == 0) goto L_0x001d
            io.ktor.utils.io.internal.ReadWriteBufferState$IdleEmpty r0 = io.ktor.utils.io.internal.ReadWriteBufferState.IdleEmpty.INSTANCE
            io.ktor.utils.io.internal.ReadWriteBufferState r0 = (io.ktor.utils.io.internal.ReadWriteBufferState) r0
            r4 = r2
            r2 = r0
            r0 = r4
        L_0x001d:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r3 = _state$FU
            boolean r1 = androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(r3, r5, r1, r2)
            if (r1 == 0) goto L_0x0001
            io.ktor.utils.io.internal.ReadWriteBufferState$IdleEmpty r1 = io.ktor.utils.io.internal.ReadWriteBufferState.IdleEmpty.INSTANCE
            if (r2 != r1) goto L_0x0034
            io.ktor.utils.io.internal.ReadWriteBufferState$IdleNonEmpty r0 = (io.ktor.utils.io.internal.ReadWriteBufferState.IdleNonEmpty) r0
            if (r0 == 0) goto L_0x0034
            io.ktor.utils.io.internal.ReadWriteBufferState$Initial r0 = r0.getInitial()
            r5.releaseBuffer(r0)
        L_0x0034:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.restoreStateAfterWrite$ktor_io():void");
    }

    private final ByteBuffer setupStateForRead() {
        Object obj;
        Throwable cause;
        ReadWriteBufferState startReading$ktor_io;
        Throwable cause2;
        do {
            obj = this._state;
            ReadWriteBufferState readWriteBufferState = (ReadWriteBufferState) obj;
            if (!Intrinsics.areEqual((Object) readWriteBufferState, (Object) ReadWriteBufferState.Terminated.INSTANCE) && !Intrinsics.areEqual((Object) readWriteBufferState, (Object) ReadWriteBufferState.IdleEmpty.INSTANCE)) {
                ClosedElement closed = getClosed();
                if (closed != null && (cause2 = closed.getCause()) != null) {
                    Void unused = ByteBufferChannelKt.rethrowClosed(cause2);
                    throw new KotlinNothingValueException();
                } else if (readWriteBufferState.capacity._availableForRead$internal == 0) {
                    return null;
                } else {
                    startReading$ktor_io = readWriteBufferState.startReading$ktor_io();
                }
            } else {
                ClosedElement closed2 = getClosed();
                if (closed2 == null || (cause = closed2.getCause()) == null) {
                    return null;
                }
                Void unused2 = ByteBufferChannelKt.rethrowClosed(cause);
                throw new KotlinNothingValueException();
            }
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, obj, startReading$ktor_io));
        ByteBuffer readBuffer = startReading$ktor_io.getReadBuffer();
        prepareBuffer(readBuffer, this.readPosition, startReading$ktor_io.capacity._availableForRead$internal);
        return readBuffer;
    }

    private final void restoreStateAfterRead() {
        Object obj;
        ReadWriteBufferState stopReading$ktor_io;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        ReadWriteBufferState.IdleNonEmpty idleNonEmpty = null;
        do {
            obj = this._state;
            ReadWriteBufferState readWriteBufferState = (ReadWriteBufferState) obj;
            ReadWriteBufferState.IdleNonEmpty idleNonEmpty2 = idleNonEmpty;
            if (idleNonEmpty2 != null) {
                idleNonEmpty2.capacity.resetForWrite();
                resumeWriteOp();
                idleNonEmpty = null;
            }
            stopReading$ktor_io = readWriteBufferState.stopReading$ktor_io();
            if ((stopReading$ktor_io instanceof ReadWriteBufferState.IdleNonEmpty) && getState() == readWriteBufferState && stopReading$ktor_io.capacity.tryLockForRelease()) {
                ReadWriteBufferState.IdleNonEmpty idleNonEmpty3 = stopReading$ktor_io;
                stopReading$ktor_io = ReadWriteBufferState.IdleEmpty.INSTANCE;
                idleNonEmpty = idleNonEmpty3;
            }
            atomicReferenceFieldUpdater = _state$FU;
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, obj, stopReading$ktor_io));
        if (stopReading$ktor_io == ReadWriteBufferState.IdleEmpty.INSTANCE) {
            ReadWriteBufferState.IdleNonEmpty idleNonEmpty4 = idleNonEmpty;
            if (idleNonEmpty4 != null) {
                releaseBuffer(idleNonEmpty4.getInitial());
            }
            resumeWriteOp();
        } else if ((stopReading$ktor_io instanceof ReadWriteBufferState.IdleNonEmpty) && stopReading$ktor_io.capacity.isEmpty() && stopReading$ktor_io.capacity.tryLockForRelease() && AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, stopReading$ktor_io, ReadWriteBufferState.IdleEmpty.INSTANCE)) {
            stopReading$ktor_io.capacity.resetForWrite();
            releaseBuffer(((ReadWriteBufferState.IdleNonEmpty) stopReading$ktor_io).getInitial());
            resumeWriteOp();
        }
    }

    private final boolean tryReleaseBuffer(boolean z) {
        Object obj;
        ReadWriteBufferState readWriteBufferState;
        ReadWriteBufferState.Initial initial = null;
        do {
            obj = this._state;
            ReadWriteBufferState readWriteBufferState2 = (ReadWriteBufferState) obj;
            ClosedElement closed = getClosed();
            if (initial != null) {
                if ((closed != null ? closed.getCause() : null) == null) {
                    initial.capacity.resetForWrite();
                }
                resumeWriteOp();
                initial = null;
            }
            if (readWriteBufferState2 == ReadWriteBufferState.Terminated.INSTANCE) {
                return true;
            }
            if (readWriteBufferState2 == ReadWriteBufferState.IdleEmpty.INSTANCE) {
                readWriteBufferState = ReadWriteBufferState.Terminated.INSTANCE;
            } else if (closed != null && (readWriteBufferState2 instanceof ReadWriteBufferState.IdleNonEmpty) && (readWriteBufferState2.capacity.tryLockForRelease() || closed.getCause() != null)) {
                if (closed.getCause() != null) {
                    readWriteBufferState2.capacity.forceLockForRelease();
                }
                initial = ((ReadWriteBufferState.IdleNonEmpty) readWriteBufferState2).getInitial();
                readWriteBufferState = ReadWriteBufferState.Terminated.INSTANCE;
            } else if (!z || !(readWriteBufferState2 instanceof ReadWriteBufferState.IdleNonEmpty) || !readWriteBufferState2.capacity.tryLockForRelease()) {
                return false;
            } else {
                initial = ((ReadWriteBufferState.IdleNonEmpty) readWriteBufferState2).getInitial();
                readWriteBufferState = ReadWriteBufferState.Terminated.INSTANCE;
            }
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, obj, readWriteBufferState));
        if (initial != null && getState() == ReadWriteBufferState.Terminated.INSTANCE) {
            releaseBuffer(initial);
        }
        return true;
    }

    private final int readAsMuchAsPossible(ByteBuffer byteBuffer) {
        ByteBuffer byteBuffer2 = setupStateForRead();
        int i = 0;
        if (byteBuffer2 != null) {
            RingBufferCapacity ringBufferCapacity = getState().capacity;
            try {
                if (ringBufferCapacity._availableForRead$internal != 0) {
                    int capacity = byteBuffer2.capacity() - this.reservedSize;
                    while (true) {
                        int remaining = byteBuffer.remaining();
                        if (remaining == 0) {
                            break;
                        }
                        int i2 = this.readPosition;
                        int tryReadAtMost = ringBufferCapacity.tryReadAtMost(Math.min(capacity - i2, remaining));
                        if (tryReadAtMost == 0) {
                            break;
                        }
                        byteBuffer2.limit(i2 + tryReadAtMost);
                        byteBuffer2.position(i2);
                        byteBuffer.put(byteBuffer2);
                        bytesRead(byteBuffer2, ringBufferCapacity, tryReadAtMost);
                        i += tryReadAtMost;
                    }
                }
            } finally {
                restoreStateAfterRead();
                tryTerminate$ktor_io();
            }
        }
        return i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK, PHI: r8 r9 
      PHI: (r8v1 int) = (r8v0 int), (r8v2 int) binds: [B:0:0x0000, B:23:0x0068] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r9v1 int) = (r9v0 int), (r9v2 int) binds: [B:0:0x0000, B:23:0x0068] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x006a A[EDGE_INSN: B:29:0x006a->B:24:0x006a ?: BREAK  
    EDGE_INSN: B:30:0x006a->B:24:0x006a ?: BREAK  , RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int readAsMuchAsPossible(io.ktor.utils.io.core.Buffer r7, int r8, int r9) {
        /*
            r6 = this;
        L_0x0000:
            java.nio.ByteBuffer r0 = r6.setupStateForRead()
            r1 = 0
            if (r0 != 0) goto L_0x0009
        L_0x0007:
            r4 = 0
            goto L_0x0052
        L_0x0009:
            io.ktor.utils.io.internal.ReadWriteBufferState r2 = r6.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r2 = r2.capacity
            int r3 = r2._availableForRead$internal     // Catch:{ all -> 0x006b }
            if (r3 != 0) goto L_0x001a
            r6.restoreStateAfterRead()
            r6.tryTerminate$ktor_io()
            goto L_0x0007
        L_0x001a:
            int r3 = r7.getLimit()     // Catch:{ all -> 0x006b }
            int r4 = r7.getWritePosition()     // Catch:{ all -> 0x006b }
            int r3 = r3 - r4
            int r4 = r0.remaining()     // Catch:{ all -> 0x006b }
            int r5 = java.lang.Math.min(r3, r9)     // Catch:{ all -> 0x006b }
            int r4 = java.lang.Math.min(r4, r5)     // Catch:{ all -> 0x006b }
            int r4 = r2.tryReadAtMost(r4)     // Catch:{ all -> 0x006b }
            if (r4 > 0) goto L_0x0036
            goto L_0x004c
        L_0x0036:
            int r1 = r0.remaining()     // Catch:{ all -> 0x006b }
            if (r3 >= r1) goto L_0x0044
            int r1 = r0.position()     // Catch:{ all -> 0x006b }
            int r1 = r1 + r3
            r0.limit(r1)     // Catch:{ all -> 0x006b }
        L_0x0044:
            io.ktor.utils.io.core.BufferPrimitivesJvmKt.writeFully(r7, r0)     // Catch:{ all -> 0x006b }
            r6.bytesRead(r0, r2, r4)     // Catch:{ all -> 0x006b }
            r0 = 1
            r1 = 1
        L_0x004c:
            r6.restoreStateAfterRead()
            r6.tryTerminate$ktor_io()
        L_0x0052:
            int r8 = r8 + r4
            int r9 = r9 - r4
            if (r1 == 0) goto L_0x006a
            int r0 = r7.getLimit()
            int r1 = r7.getWritePosition()
            if (r0 <= r1) goto L_0x006a
            io.ktor.utils.io.internal.ReadWriteBufferState r0 = r6.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r0 = r0.capacity
            int r0 = r0._availableForRead$internal
            if (r0 > 0) goto L_0x0000
        L_0x006a:
            return r8
        L_0x006b:
            r7 = move-exception
            r6.restoreStateAfterRead()
            r6.tryTerminate$ktor_io()
            goto L_0x0074
        L_0x0073:
            throw r7
        L_0x0074:
            goto L_0x0073
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readAsMuchAsPossible(io.ktor.utils.io.core.Buffer, int, int):int");
    }

    static /* synthetic */ int readAsMuchAsPossible$default(ByteBufferChannel byteBufferChannel, Buffer buffer, int i, int i2, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                i = 0;
            }
            if ((i3 & 4) != 0) {
                i2 = buffer.getLimit() - buffer.getWritePosition();
            }
            return byteBufferChannel.readAsMuchAsPossible(buffer, i, i2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: readAsMuchAsPossible");
    }

    private final int readAsMuchAsPossible(byte[] bArr, int i, int i2) {
        ByteBuffer byteBuffer = setupStateForRead();
        int i3 = 0;
        if (byteBuffer != null) {
            RingBufferCapacity ringBufferCapacity = getState().capacity;
            try {
                if (ringBufferCapacity._availableForRead$internal != 0) {
                    int capacity = byteBuffer.capacity() - this.reservedSize;
                    while (true) {
                        int i4 = i2 - i3;
                        if (i4 == 0) {
                            break;
                        }
                        int i5 = this.readPosition;
                        int tryReadAtMost = ringBufferCapacity.tryReadAtMost(Math.min(capacity - i5, i4));
                        if (tryReadAtMost == 0) {
                            break;
                        }
                        byteBuffer.limit(i5 + tryReadAtMost);
                        byteBuffer.position(i5);
                        byteBuffer.get(bArr, i + i3, tryReadAtMost);
                        bytesRead(byteBuffer, ringBufferCapacity, tryReadAtMost);
                        i3 += tryReadAtMost;
                    }
                }
            } finally {
                restoreStateAfterRead();
                tryTerminate$ktor_io();
            }
        }
        return i3;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0076 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0078 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int readAvailable(int r5, kotlin.jvm.functions.Function1<? super java.nio.ByteBuffer, kotlin.Unit> r6) {
        /*
            r4 = this;
            java.lang.String r0 = "block"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            if (r5 <= 0) goto L_0x009e
            r0 = 4088(0xff8, float:5.729E-42)
            if (r5 > r0) goto L_0x0081
            java.nio.ByteBuffer r0 = r4.setupStateForRead()
            r1 = 0
            if (r0 != 0) goto L_0x0014
        L_0x0012:
            r6 = 0
            goto L_0x0074
        L_0x0014:
            io.ktor.utils.io.internal.ReadWriteBufferState r2 = r4.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r2 = r2.capacity
            int r3 = r2._availableForRead$internal     // Catch:{ all -> 0x0079 }
            if (r3 != 0) goto L_0x0025
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
            goto L_0x0012
        L_0x0025:
            int r3 = r2.tryReadAtLeast(r5)     // Catch:{ all -> 0x0079 }
            if (r3 <= 0) goto L_0x006d
            if (r3 >= r5) goto L_0x002e
            goto L_0x006d
        L_0x002e:
            int r5 = r0.position()     // Catch:{ all -> 0x0079 }
            int r1 = r0.limit()     // Catch:{ all -> 0x0079 }
            r6.invoke(r0)     // Catch:{ all -> 0x0079 }
            int r6 = r0.limit()     // Catch:{ all -> 0x0079 }
            if (r1 != r6) goto L_0x0061
            int r6 = r0.position()     // Catch:{ all -> 0x0079 }
            int r6 = r6 - r5
            if (r6 < 0) goto L_0x0055
            r4.bytesRead(r0, r2, r6)     // Catch:{ all -> 0x0079 }
            if (r6 >= r3) goto L_0x0052
            int r3 = r3 - r6
            r2.completeWrite(r3)     // Catch:{ all -> 0x0079 }
            r2.flush()     // Catch:{ all -> 0x0079 }
        L_0x0052:
            r5 = 1
            r1 = 1
            goto L_0x006e
        L_0x0055:
            java.lang.String r5 = "Position shouldn't been moved backwards."
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0079 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0079 }
            r6.<init>(r5)     // Catch:{ all -> 0x0079 }
            throw r6     // Catch:{ all -> 0x0079 }
        L_0x0061:
            java.lang.String r5 = "Buffer limit shouldn't be modified."
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0079 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0079 }
            r6.<init>(r5)     // Catch:{ all -> 0x0079 }
            throw r6     // Catch:{ all -> 0x0079 }
        L_0x006d:
            r6 = 0
        L_0x006e:
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
        L_0x0074:
            if (r1 != 0) goto L_0x0078
            r5 = -1
            return r5
        L_0x0078:
            return r6
        L_0x0079:
            r5 = move-exception
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
            throw r5
        L_0x0081:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r0 = "Min("
            r6.<init>(r0)
            r6.append(r5)
            java.lang.String r5 = ") shouldn't be greater than 4088"
            r6.append(r5)
            java.lang.String r5 = r6.toString()
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r5 = r5.toString()
            r6.<init>(r5)
            throw r6
        L_0x009e:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "min should be positive"
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            goto L_0x00ab
        L_0x00aa:
            throw r5
        L_0x00ab:
            goto L_0x00aa
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readAvailable(int, kotlin.jvm.functions.Function1):int");
    }

    private final int writeAsMuchAsPossible(ByteBuffer byteBuffer) {
        ByteBufferChannel byteBufferChannel;
        int tryWriteAtMost;
        JoiningState joiningState = this.joining;
        if (joiningState == null || (byteBufferChannel = resolveDelegation(this, joiningState)) == null) {
            byteBufferChannel = this;
        }
        ByteBuffer byteBuffer2 = byteBufferChannel.setupStateForWrite$ktor_io();
        int i = 0;
        if (byteBuffer2 == null) {
            return 0;
        }
        RingBufferCapacity ringBufferCapacity = byteBufferChannel.getState().capacity;
        long totalBytesWritten2 = byteBufferChannel.getTotalBytesWritten();
        try {
            ClosedElement closed = byteBufferChannel.getClosed();
            if (closed == null) {
                ByteBufferChannel byteBufferChannel2 = byteBufferChannel;
                int limit = byteBuffer.limit();
                while (true) {
                    int position = limit - byteBuffer.position();
                    if (position == 0 || (tryWriteAtMost = ringBufferCapacity.tryWriteAtMost(Math.min(position, byteBuffer2.remaining()))) == 0) {
                        byteBuffer.limit(limit);
                        byteBufferChannel.bytesWritten(byteBuffer2, ringBufferCapacity, i);
                    } else if (tryWriteAtMost > 0) {
                        byteBuffer.limit(byteBuffer.position() + tryWriteAtMost);
                        byteBuffer2.put(byteBuffer);
                        i += tryWriteAtMost;
                        byteBufferChannel.prepareBuffer(byteBuffer2, byteBufferChannel.carryIndex(byteBuffer2, byteBufferChannel.writePosition + i), ringBufferCapacity._availableForWrite$internal);
                    } else {
                        throw new IllegalArgumentException("Failed requirement.".toString());
                    }
                }
                byteBuffer.limit(limit);
                byteBufferChannel.bytesWritten(byteBuffer2, ringBufferCapacity, i);
                return i;
            }
            Void unused = ByteBufferChannelKt.rethrowClosed(closed.getSendException());
            throw new KotlinNothingValueException();
        } finally {
            if (ringBufferCapacity.isFull() || byteBufferChannel.getAutoFlush()) {
                byteBufferChannel.flush();
            }
            if (byteBufferChannel != this) {
                setTotalBytesWritten$ktor_io(getTotalBytesWritten() + (byteBufferChannel.getTotalBytesWritten() - totalBytesWritten2));
            }
            byteBufferChannel.restoreStateAfterWrite$ktor_io();
            byteBufferChannel.tryTerminate$ktor_io();
        }
    }

    private final int writeAsMuchAsPossible(Buffer buffer) {
        ByteBufferChannel byteBufferChannel;
        JoiningState joiningState = this.joining;
        if (joiningState == null || (byteBufferChannel = resolveDelegation(this, joiningState)) == null) {
            byteBufferChannel = this;
        }
        ByteBuffer byteBuffer = byteBufferChannel.setupStateForWrite$ktor_io();
        int i = 0;
        if (byteBuffer == null) {
            return 0;
        }
        RingBufferCapacity ringBufferCapacity = byteBufferChannel.getState().capacity;
        long totalBytesWritten2 = byteBufferChannel.getTotalBytesWritten();
        try {
            ClosedElement closed = byteBufferChannel.getClosed();
            if (closed == null) {
                ByteBufferChannel byteBufferChannel2 = byteBufferChannel;
                while (true) {
                    int tryWriteAtMost = ringBufferCapacity.tryWriteAtMost(Math.min(buffer.getWritePosition() - buffer.getReadPosition(), byteBuffer.remaining()));
                    if (tryWriteAtMost == 0) {
                        break;
                    }
                    BufferUtilsJvmKt.readFully(buffer, byteBuffer, tryWriteAtMost);
                    i += tryWriteAtMost;
                    byteBufferChannel.prepareBuffer(byteBuffer, byteBufferChannel.carryIndex(byteBuffer, byteBufferChannel.writePosition + i), ringBufferCapacity._availableForWrite$internal);
                }
                byteBufferChannel.bytesWritten(byteBuffer, ringBufferCapacity, i);
                return i;
            }
            Void unused = ByteBufferChannelKt.rethrowClosed(closed.getSendException());
            throw new KotlinNothingValueException();
        } finally {
            if (ringBufferCapacity.isFull() || byteBufferChannel.getAutoFlush()) {
                byteBufferChannel.flush();
            }
            if (byteBufferChannel != this) {
                setTotalBytesWritten$ktor_io(getTotalBytesWritten() + (byteBufferChannel.getTotalBytesWritten() - totalBytesWritten2));
            }
            byteBufferChannel.restoreStateAfterWrite$ktor_io();
            byteBufferChannel.tryTerminate$ktor_io();
        }
    }

    private final int writeAsMuchAsPossible(byte[] bArr, int i, int i2) {
        ByteBufferChannel byteBufferChannel;
        JoiningState joiningState = this.joining;
        if (joiningState == null || (byteBufferChannel = resolveDelegation(this, joiningState)) == null) {
            byteBufferChannel = this;
        }
        ByteBuffer byteBuffer = byteBufferChannel.setupStateForWrite$ktor_io();
        int i3 = 0;
        if (byteBuffer == null) {
            return 0;
        }
        RingBufferCapacity ringBufferCapacity = byteBufferChannel.getState().capacity;
        long totalBytesWritten2 = byteBufferChannel.getTotalBytesWritten();
        try {
            ClosedElement closed = byteBufferChannel.getClosed();
            if (closed == null) {
                ByteBufferChannel byteBufferChannel2 = byteBufferChannel;
                while (true) {
                    int tryWriteAtMost = ringBufferCapacity.tryWriteAtMost(Math.min(i2 - i3, byteBuffer.remaining()));
                    if (tryWriteAtMost == 0) {
                        byteBufferChannel.bytesWritten(byteBuffer, ringBufferCapacity, i3);
                        return i3;
                    } else if (tryWriteAtMost > 0) {
                        byteBuffer.put(bArr, i + i3, tryWriteAtMost);
                        i3 += tryWriteAtMost;
                        byteBufferChannel.prepareBuffer(byteBuffer, byteBufferChannel.carryIndex(byteBuffer, byteBufferChannel.writePosition + i3), ringBufferCapacity._availableForWrite$internal);
                    } else {
                        throw new IllegalArgumentException("Failed requirement.".toString());
                    }
                }
            } else {
                Void unused = ByteBufferChannelKt.rethrowClosed(closed.getSendException());
                throw new KotlinNothingValueException();
            }
        } finally {
            if (ringBufferCapacity.isFull() || byteBufferChannel.getAutoFlush()) {
                byteBufferChannel.flush();
            }
            if (byteBufferChannel != this) {
                setTotalBytesWritten$ktor_io(getTotalBytesWritten() + (byteBufferChannel.getTotalBytesWritten() - totalBytesWritten2));
            }
            byteBufferChannel.restoreStateAfterWrite$ktor_io();
            byteBufferChannel.tryTerminate$ktor_io();
        }
    }

    /* JADX INFO: finally extract failed */
    public int writeAvailable(int i, Function1<? super ByteBuffer, Unit> function1) {
        ByteBufferChannel byteBufferChannel;
        int i2;
        int i3;
        Intrinsics.checkNotNullParameter(function1, "block");
        if (i <= 0) {
            throw new IllegalArgumentException("min should be positive".toString());
        } else if (i <= 4088) {
            JoiningState joiningState = this.joining;
            if (joiningState == null || (byteBufferChannel = resolveDelegation(this, joiningState)) == null) {
                byteBufferChannel = this;
            }
            ByteBuffer byteBuffer = byteBufferChannel.setupStateForWrite$ktor_io();
            int i4 = 0;
            if (byteBuffer == null) {
                i2 = 0;
            } else {
                RingBufferCapacity ringBufferCapacity = byteBufferChannel.getState().capacity;
                long totalBytesWritten2 = byteBufferChannel.getTotalBytesWritten();
                try {
                    ClosedElement closed = byteBufferChannel.getClosed();
                    if (closed == null) {
                        ByteBufferChannel byteBufferChannel2 = byteBufferChannel;
                        int tryWriteAtLeast = ringBufferCapacity.tryWriteAtLeast(i);
                        if (tryWriteAtLeast <= 0) {
                            i3 = 0;
                        } else {
                            byteBufferChannel.prepareBuffer(byteBuffer, byteBufferChannel.writePosition, tryWriteAtLeast);
                            int position = byteBuffer.position();
                            int limit = byteBuffer.limit();
                            function1.invoke(byteBuffer);
                            if (limit == byteBuffer.limit()) {
                                int position2 = byteBuffer.position() - position;
                                if (position2 < 0) {
                                    throw new IllegalStateException("Position has been moved backward: pushback is not supported".toString());
                                } else if (position2 >= 0) {
                                    byteBufferChannel.bytesWritten(byteBuffer, ringBufferCapacity, position2);
                                    if (position2 < tryWriteAtLeast) {
                                        ringBufferCapacity.completeRead(tryWriteAtLeast - position2);
                                    }
                                    i3 = 1;
                                    i4 = position2;
                                } else {
                                    throw new IllegalStateException();
                                }
                            } else {
                                throw new IllegalStateException("Buffer limit modified".toString());
                            }
                        }
                        if (ringBufferCapacity.isFull() || byteBufferChannel.getAutoFlush()) {
                            byteBufferChannel.flush();
                        }
                        if (byteBufferChannel != this) {
                            setTotalBytesWritten$ktor_io(getTotalBytesWritten() + (byteBufferChannel.getTotalBytesWritten() - totalBytesWritten2));
                        }
                        byteBufferChannel.restoreStateAfterWrite$ktor_io();
                        byteBufferChannel.tryTerminate$ktor_io();
                        int i5 = i4;
                        i4 = i3;
                        i2 = i5;
                    } else {
                        Void unused = ByteBufferChannelKt.rethrowClosed(closed.getSendException());
                        throw new KotlinNothingValueException();
                    }
                } catch (Throwable th) {
                    if (ringBufferCapacity.isFull() || byteBufferChannel.getAutoFlush()) {
                        byteBufferChannel.flush();
                    }
                    if (byteBufferChannel != this) {
                        setTotalBytesWritten$ktor_io(getTotalBytesWritten() + (byteBufferChannel.getTotalBytesWritten() - totalBytesWritten2));
                    }
                    byteBufferChannel.restoreStateAfterWrite$ktor_io();
                    byteBufferChannel.tryTerminate$ktor_io();
                    throw th;
                }
            }
            if (i4 == 0) {
                return -1;
            }
            return i2;
        } else {
            throw new IllegalArgumentException(("Min(" + i + ") shouldn't be greater than 4088").toString());
        }
    }

    private final boolean writeWhileNoSuspend(Function1<? super ByteBuffer, Boolean> function1) {
        ByteBufferChannel byteBufferChannel;
        JoiningState joiningState = this.joining;
        if (joiningState == null || (byteBufferChannel = resolveDelegation(this, joiningState)) == null) {
            byteBufferChannel = this;
        }
        ByteBuffer byteBuffer = byteBufferChannel.setupStateForWrite$ktor_io();
        if (byteBuffer == null) {
            return true;
        }
        RingBufferCapacity ringBufferCapacity = byteBufferChannel.getState().capacity;
        long totalBytesWritten2 = byteBufferChannel.getTotalBytesWritten();
        try {
            ClosedElement closed = byteBufferChannel.getClosed();
            if (closed == null) {
                ByteBufferChannel byteBufferChannel2 = byteBufferChannel;
                return byteBufferChannel.writeWhileLoop(byteBuffer, ringBufferCapacity, function1);
            }
            Void unused = ByteBufferChannelKt.rethrowClosed(closed.getSendException());
            throw new KotlinNothingValueException();
        } finally {
            if (ringBufferCapacity.isFull() || byteBufferChannel.getAutoFlush()) {
                byteBufferChannel.flush();
            }
            if (byteBufferChannel != this) {
                setTotalBytesWritten$ktor_io(getTotalBytesWritten() + (byteBufferChannel.getTotalBytesWritten() - totalBytesWritten2));
            }
            byteBufferChannel.restoreStateAfterWrite$ktor_io();
            byteBufferChannel.tryTerminate$ktor_io();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x00a1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ java.lang.Object read$suspendImpl(io.ktor.utils.io.ByteBufferChannel r5, int r6, kotlin.jvm.functions.Function1<? super java.nio.ByteBuffer, kotlin.Unit> r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            if (r6 < 0) goto L_0x00ac
            java.nio.ByteBuffer r0 = r5.setupStateForRead()
            if (r0 != 0) goto L_0x0009
            goto L_0x0071
        L_0x0009:
            io.ktor.utils.io.internal.ReadWriteBufferState r1 = r5.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r1 = r1.capacity
            int r2 = r1._availableForRead$internal     // Catch:{ all -> 0x00a4 }
            if (r2 != 0) goto L_0x001a
            r5.restoreStateAfterRead()
            r5.tryTerminate$ktor_io()
            goto L_0x0071
        L_0x001a:
            int r2 = r1._availableForRead$internal     // Catch:{ all -> 0x00a4 }
            if (r2 <= 0) goto L_0x0068
            if (r2 >= r6) goto L_0x0021
            goto L_0x0068
        L_0x0021:
            int r2 = r0.position()     // Catch:{ all -> 0x00a4 }
            int r3 = r0.limit()     // Catch:{ all -> 0x00a4 }
            r7.invoke(r0)     // Catch:{ all -> 0x00a4 }
            int r4 = r0.limit()     // Catch:{ all -> 0x00a4 }
            if (r3 != r4) goto L_0x005c
            int r3 = r0.position()     // Catch:{ all -> 0x00a4 }
            int r3 = r3 - r2
            if (r3 < 0) goto L_0x0050
            boolean r2 = r1.tryReadExact(r3)     // Catch:{ all -> 0x00a4 }
            if (r2 == 0) goto L_0x0044
            r5.bytesRead(r0, r1, r3)     // Catch:{ all -> 0x00a4 }
            r0 = 1
            goto L_0x0069
        L_0x0044:
            java.lang.String r6 = "Check failed."
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00a4 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x00a4 }
            r7.<init>(r6)     // Catch:{ all -> 0x00a4 }
            throw r7     // Catch:{ all -> 0x00a4 }
        L_0x0050:
            java.lang.String r6 = "Position has been moved backward: pushback is not supported."
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00a4 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x00a4 }
            r7.<init>(r6)     // Catch:{ all -> 0x00a4 }
            throw r7     // Catch:{ all -> 0x00a4 }
        L_0x005c:
            java.lang.String r6 = "Buffer limit modified."
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00a4 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x00a4 }
            r7.<init>(r6)     // Catch:{ all -> 0x00a4 }
            throw r7     // Catch:{ all -> 0x00a4 }
        L_0x0068:
            r0 = 0
        L_0x0069:
            r5.restoreStateAfterRead()
            r5.tryTerminate$ktor_io()
            if (r0 != 0) goto L_0x00a1
        L_0x0071:
            boolean r0 = r5.isClosedForRead()
            if (r0 == 0) goto L_0x0093
            if (r6 > 0) goto L_0x007a
            goto L_0x0093
        L_0x007a:
            java.io.EOFException r5 = new java.io.EOFException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "Got EOF but at least "
            r7.<init>(r8)
            r7.append(r6)
            java.lang.String r6 = " bytes were expected"
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            r5.<init>(r6)
            throw r5
        L_0x0093:
            java.lang.Object r5 = r5.readBlockSuspend(r6, r7, r8)
            java.lang.Object r6 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r5 != r6) goto L_0x009e
            return r5
        L_0x009e:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L_0x00a1:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L_0x00a4:
            r6 = move-exception
            r5.restoreStateAfterRead()
            r5.tryTerminate$ktor_io()
            throw r6
        L_0x00ac:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "min should be positive or zero"
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.read$suspendImpl(io.ktor.utils.io.ByteBufferChannel, int, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object discard$suspendImpl(ByteBufferChannel byteBufferChannel, long j, Continuation<? super Long> continuation) {
        long j2 = 0;
        if (j >= 0) {
            ByteBuffer byteBuffer = byteBufferChannel.setupStateForRead();
            if (byteBuffer != null) {
                RingBufferCapacity ringBufferCapacity = byteBufferChannel.getState().capacity;
                try {
                    if (ringBufferCapacity._availableForRead$internal != 0) {
                        int tryReadAtMost = ringBufferCapacity.tryReadAtMost((int) Math.min(2147483647L, j));
                        byteBufferChannel.bytesRead(byteBuffer, ringBufferCapacity, tryReadAtMost);
                        j2 = (long) tryReadAtMost;
                    }
                } finally {
                    byteBufferChannel.restoreStateAfterRead();
                    byteBufferChannel.tryTerminate$ktor_io();
                }
            }
            long j3 = j2;
            if (j3 == j || byteBufferChannel.isClosedForRead()) {
                return Boxing.boxLong(j3);
            }
            return byteBufferChannel.discardSuspend(j3, j, continuation);
        }
        throw new IllegalArgumentException(("max shouldn't be negative: " + j).toString());
    }

    /* JADX INFO: finally extract failed */
    private final int tryWritePacketPart(ByteReadPacket byteReadPacket) {
        ByteBufferChannel byteBufferChannel;
        JoiningState joiningState = this.joining;
        if (joiningState == null || (byteBufferChannel = resolveDelegation(this, joiningState)) == null) {
            byteBufferChannel = this;
        }
        ByteBuffer byteBuffer = byteBufferChannel.setupStateForWrite$ktor_io();
        if (byteBuffer == null) {
            return 0;
        }
        RingBufferCapacity ringBufferCapacity = byteBufferChannel.getState().capacity;
        long totalBytesWritten2 = byteBufferChannel.getTotalBytesWritten();
        try {
            ClosedElement closed = byteBufferChannel.getClosed();
            if (closed == null) {
                ByteBufferChannel byteBufferChannel2 = byteBufferChannel;
                int tryWriteAtMost = ringBufferCapacity.tryWriteAtMost((int) Math.min(byteReadPacket.getRemaining(), (long) byteBuffer.remaining()));
                if (tryWriteAtMost > 0) {
                    byteBuffer.limit(byteBuffer.position() + tryWriteAtMost);
                    ByteBuffersKt.readFully(byteReadPacket, byteBuffer);
                    byteBufferChannel.bytesWritten(byteBuffer, ringBufferCapacity, tryWriteAtMost);
                }
                if (ringBufferCapacity.isFull() || byteBufferChannel.getAutoFlush()) {
                    byteBufferChannel.flush();
                }
                if (byteBufferChannel != this) {
                    setTotalBytesWritten$ktor_io(getTotalBytesWritten() + (byteBufferChannel.getTotalBytesWritten() - totalBytesWritten2));
                }
                byteBufferChannel.restoreStateAfterWrite$ktor_io();
                byteBufferChannel.tryTerminate$ktor_io();
                return tryWriteAtMost;
            }
            Void unused = ByteBufferChannelKt.rethrowClosed(closed.getSendException());
            throw new KotlinNothingValueException();
        } catch (Throwable th) {
            if (ringBufferCapacity.isFull() || byteBufferChannel.getAutoFlush()) {
                byteBufferChannel.flush();
            }
            if (byteBufferChannel != this) {
                setTotalBytesWritten$ktor_io(getTotalBytesWritten() + (byteBufferChannel.getTotalBytesWritten() - totalBytesWritten2));
            }
            byteBufferChannel.restoreStateAfterWrite$ktor_io();
            byteBufferChannel.tryTerminate$ktor_io();
            throw th;
        }
    }

    private final boolean consumeEachBufferRangeFast(boolean z, Function2<? super ByteBuffer, ? super Boolean, Boolean> function2) {
        ByteBuffer byteBuffer = setupStateForRead();
        int i = true;
        if (byteBuffer != null) {
            RingBufferCapacity ringBufferCapacity = getState().capacity;
            if (ringBufferCapacity._availableForRead$internal == 0) {
                InlineMarker.finallyStart(i);
                restoreStateAfterRead();
                tryTerminate$ktor_io();
                InlineMarker.finallyEnd(i);
            } else {
                while (true) {
                    try {
                        if (!byteBuffer.hasRemaining() && !z) {
                            InlineMarker.finallyStart(i);
                            restoreStateAfterRead();
                            tryTerminate$ktor_io();
                            InlineMarker.finallyEnd(i);
                            break;
                        }
                        boolean booleanValue = function2.invoke(byteBuffer, Boolean.valueOf(z)).booleanValue();
                        afterBufferVisited(byteBuffer, ringBufferCapacity);
                        if (!booleanValue || (z && !byteBuffer.hasRemaining())) {
                            i = 2;
                        }
                    } finally {
                        InlineMarker.finallyStart(i ? 1 : 0);
                        restoreStateAfterRead();
                        tryTerminate$ktor_io();
                        InlineMarker.finallyEnd(i);
                    }
                }
                i = 2;
                return i;
            }
        }
        z = false;
        if (z || getClosed() == null) {
            return z;
        }
        function2.invoke(ReadWriteBufferStateKt.getEmptyByteBuffer(), Boolean.valueOf(i));
        return i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r3.afterHeadWrite();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0046, code lost:
        return r0.build();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final io.ktor.utils.io.core.ByteReadPacket remainingPacket(long r11) {
        /*
            r10 = this;
            io.ktor.utils.io.core.BytePacketBuilder r0 = new io.ktor.utils.io.core.BytePacketBuilder
            r1 = 0
            r2 = 1
            r0.<init>(r1, r2, r1)
            r3 = r0
            io.ktor.utils.io.core.Output r3 = (io.ktor.utils.io.core.Output) r3     // Catch:{ all -> 0x004c }
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareWriteHead(r3, r2, r1)     // Catch:{ all -> 0x004c }
        L_0x000e:
            r5 = r1
            io.ktor.utils.io.core.Buffer r5 = (io.ktor.utils.io.core.Buffer) r5     // Catch:{ all -> 0x0047 }
            int r4 = r5.getLimit()     // Catch:{ all -> 0x0047 }
            int r6 = r5.getWritePosition()     // Catch:{ all -> 0x0047 }
            int r4 = r4 - r6
            long r6 = (long) r4     // Catch:{ all -> 0x0047 }
            int r4 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
            if (r4 <= 0) goto L_0x0023
            int r4 = (int) r11     // Catch:{ all -> 0x0047 }
            r5.resetForWrite(r4)     // Catch:{ all -> 0x0047 }
        L_0x0023:
            r8 = 6
            r9 = 0
            r6 = 0
            r7 = 0
            r4 = r10
            int r4 = readAsMuchAsPossible$default(r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x0047 }
            long r4 = (long) r4     // Catch:{ all -> 0x0047 }
            long r11 = r11 - r4
            r4 = 0
            int r6 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x003f
            boolean r4 = r10.isClosedForRead()     // Catch:{ all -> 0x0047 }
            if (r4 != 0) goto L_0x003f
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareWriteHead(r3, r2, r1)     // Catch:{ all -> 0x0047 }
            goto L_0x000e
        L_0x003f:
            r3.afterHeadWrite()     // Catch:{ all -> 0x004c }
            io.ktor.utils.io.core.ByteReadPacket r11 = r0.build()     // Catch:{ all -> 0x004c }
            return r11
        L_0x0047:
            r11 = move-exception
            r3.afterHeadWrite()     // Catch:{ all -> 0x004c }
            throw r11     // Catch:{ all -> 0x004c }
        L_0x004c:
            r11 = move-exception
            r0.release()
            goto L_0x0052
        L_0x0051:
            throw r11
        L_0x0052:
            goto L_0x0051
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.remainingPacket(long):io.ktor.utils.io.core.ByteReadPacket");
    }

    private final Object suspensionForSize(int i, Continuation<? super Boolean> continuation) {
        while (true) {
            ReadWriteBufferState state = getState();
            boolean z = true;
            if (state.capacity._availableForRead$internal >= i || !(this.joining == null || getWriteOp() == null || (state != ReadWriteBufferState.IdleEmpty.INSTANCE && !(state instanceof ReadWriteBufferState.IdleNonEmpty)))) {
                Result.Companion companion = Result.Companion;
                continuation.resumeWith(Result.m1774constructorimpl(true));
            } else {
                ClosedElement closed = getClosed();
                if (closed == null) {
                    while (getReadOp() == null) {
                        if (getClosed() == null) {
                            ReadWriteBufferState state2 = getState();
                            if (state2.capacity._availableForRead$internal < i && (this.joining == null || getWriteOp() == null || (state2 != ReadWriteBufferState.IdleEmpty.INSTANCE && !(state2 instanceof ReadWriteBufferState.IdleNonEmpty)))) {
                                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _readOp$FU;
                                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, (Object) null, continuation)) {
                                    if (getClosed() == null) {
                                        ReadWriteBufferState state3 = getState();
                                        if (state3.capacity._availableForRead$internal < i) {
                                            if (this.joining != null) {
                                                if (getWriteOp() != null) {
                                                    if (state3 != ReadWriteBufferState.IdleEmpty.INSTANCE && !(state3 instanceof ReadWriteBufferState.IdleNonEmpty)) {
                                                        break;
                                                    }
                                                } else {
                                                    break;
                                                }
                                            } else {
                                                break;
                                            }
                                        }
                                    }
                                    if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, continuation, (Object) null)) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    throw new IllegalStateException("Operation is already in progress".toString());
                } else if (closed.getCause() != null) {
                    Result.Companion companion2 = Result.Companion;
                    continuation.resumeWith(Result.m1774constructorimpl(ResultKt.createFailure(closed.getCause())));
                    return IntrinsicsKt.getCOROUTINE_SUSPENDED();
                } else {
                    boolean flush = getState().capacity.flush();
                    boolean z2 = getState().capacity._availableForRead$internal >= i;
                    Result.Companion companion3 = Result.Companion;
                    if (!flush || !z2) {
                        z = false;
                    }
                    continuation.resumeWith(Result.m1774constructorimpl(Boolean.valueOf(z)));
                    return IntrinsicsKt.getCOROUTINE_SUSPENDED();
                }
            }
        }
        return IntrinsicsKt.getCOROUTINE_SUSPENDED();
    }
}
