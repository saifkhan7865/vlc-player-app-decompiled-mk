package io.ktor.utils.io.jvm.javaio;

import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import org.slf4j.Logger;

@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\n\u001a\u00020\u000b*\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u001a\u0016\u0010\u000f\u001a\u00020\u0010*\u00020\u00112\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\"#\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00018BX\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"ADAPTER_LOGGER", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "getADAPTER_LOGGER", "()Lorg/slf4j/Logger;", "ADAPTER_LOGGER$delegate", "Lkotlin/Lazy;", "CloseToken", "", "FlushToken", "toInputStream", "Ljava/io/InputStream;", "Lio/ktor/utils/io/ByteReadChannel;", "parent", "Lkotlinx/coroutines/Job;", "toOutputStream", "Ljava/io/OutputStream;", "Lio/ktor/utils/io/ByteWriteChannel;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Blocking.kt */
public final class BlockingKt {
    private static final Lazy ADAPTER_LOGGER$delegate = LazyKt.lazy(BlockingKt$ADAPTER_LOGGER$2.INSTANCE);
    /* access modifiers changed from: private */
    public static final Object CloseToken = new Object();
    /* access modifiers changed from: private */
    public static final Object FlushToken = new Object();

    /* access modifiers changed from: private */
    public static final Logger getADAPTER_LOGGER() {
        return (Logger) ADAPTER_LOGGER$delegate.getValue();
    }

    public static final InputStream toInputStream(ByteReadChannel byteReadChannel, Job job) {
        Intrinsics.checkNotNullParameter(byteReadChannel, "<this>");
        return new InputAdapter(job, byteReadChannel);
    }

    public static /* synthetic */ InputStream toInputStream$default(ByteReadChannel byteReadChannel, Job job, int i, Object obj) {
        if ((i & 1) != 0) {
            job = null;
        }
        return toInputStream(byteReadChannel, job);
    }

    public static final OutputStream toOutputStream(ByteWriteChannel byteWriteChannel, Job job) {
        Intrinsics.checkNotNullParameter(byteWriteChannel, "<this>");
        return new OutputAdapter(job, byteWriteChannel);
    }

    public static /* synthetic */ OutputStream toOutputStream$default(ByteWriteChannel byteWriteChannel, Job job, int i, Object obj) {
        if ((i & 1) != 0) {
            job = null;
        }
        return toOutputStream(byteWriteChannel, job);
    }
}
