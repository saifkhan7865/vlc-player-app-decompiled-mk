package io.ktor.util.cio;

import io.ktor.utils.io.WriterScope;
import io.ktor.utils.io.core.CloseableJVMKt;
import java.io.Closeable;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/WriterScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.util.cio.FileChannelsKt$readChannel$1", f = "FileChannels.kt", i = {0, 0, 1, 1}, l = {44, 63}, m = "invokeSuspend", n = {"$this$use$iv", "closed$iv", "$this$use$iv", "closed$iv"}, s = {"L$0", "I$0", "L$0", "I$0"})
/* compiled from: FileChannels.kt */
final class FileChannelsKt$readChannel$1 extends SuspendLambda implements Function2<WriterScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $endInclusive;
    final /* synthetic */ long $fileLength;
    final /* synthetic */ long $start;
    final /* synthetic */ File $this_readChannel;
    int I$0;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FileChannelsKt$readChannel$1(long j, long j2, long j3, File file, Continuation<? super FileChannelsKt$readChannel$1> continuation) {
        super(2, continuation);
        this.$start = j;
        this.$endInclusive = j2;
        this.$fileLength = j3;
        this.$this_readChannel = file;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FileChannelsKt$readChannel$1 fileChannelsKt$readChannel$1 = new FileChannelsKt$readChannel$1(this.$start, this.$endInclusive, this.$fileLength, this.$this_readChannel, continuation);
        fileChannelsKt$readChannel$1.L$0 = obj;
        return fileChannelsKt$readChannel$1;
    }

    public final Object invoke(WriterScope writerScope, Continuation<? super Unit> continuation) {
        return ((FileChannelsKt$readChannel$1) create(writerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Closeable closeable;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i == 1) {
                closeable = (Closeable) this.L$0;
            } else if (i == 2) {
                closeable = (Closeable) this.L$0;
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th) {
                th = th;
            }
        } else {
            ResultKt.throwOnFailure(obj);
            WriterScope writerScope = (WriterScope) this.L$0;
            long j = this.$start;
            if (j >= 0) {
                long j2 = this.$endInclusive;
                long j3 = this.$fileLength;
                if (j2 <= j3 - 1) {
                    Closeable randomAccessFile = new RandomAccessFile(this.$this_readChannel, "r");
                    long j4 = this.$start;
                    long j5 = this.$endInclusive;
                    try {
                        FileChannel channel = ((RandomAccessFile) randomAccessFile).getChannel();
                        Intrinsics.checkNotNullExpressionValue(channel, "file.channel");
                        if (j4 > 0) {
                            channel.position(j4);
                        }
                        if (j5 == -1) {
                            this.L$0 = randomAccessFile;
                            this.I$0 = 0;
                            this.label = 1;
                            if (writerScope.getChannel().writeSuspendSession(new FileChannelsKt$readChannel$1$3$1(writerScope, channel, (Continuation<? super FileChannelsKt$readChannel$1$3$1>) null), this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            Ref.LongRef longRef = new Ref.LongRef();
                            longRef.element = j4;
                            this.L$0 = randomAccessFile;
                            this.I$0 = 0;
                            this.label = 2;
                            if (writerScope.getChannel().writeWhile(new FileChannelsKt$readChannel$1$3$2(j5, longRef, channel), this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                        closeable = randomAccessFile;
                    } catch (Throwable th2) {
                        th = th2;
                        closeable = randomAccessFile;
                        try {
                            closeable.close();
                        } catch (Throwable th3) {
                            CloseableJVMKt.addSuppressedInternal(th, th3);
                        }
                        throw th;
                    }
                } else {
                    throw new IllegalArgumentException(("endInclusive points to the position out of the file: file size = " + j3 + ", endInclusive = " + j2).toString());
                }
            } else {
                throw new IllegalArgumentException(("start position shouldn't be negative but it is " + j).toString());
            }
        }
        Unit unit = Unit.INSTANCE;
        closeable.close();
        return Unit.INSTANCE;
    }
}
