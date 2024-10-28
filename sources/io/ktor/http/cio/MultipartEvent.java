package io.ktor.http.cio;

import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.core.ByteReadPacket;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Deferred;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0005\u0006\u0007B\u0007\b\u0004¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&\u0001\u0003\b\t\n¨\u0006\u000b"}, d2 = {"Lio/ktor/http/cio/MultipartEvent;", "", "()V", "release", "", "Epilogue", "MultipartPart", "Preamble", "Lio/ktor/http/cio/MultipartEvent$Epilogue;", "Lio/ktor/http/cio/MultipartEvent$MultipartPart;", "Lio/ktor/http/cio/MultipartEvent$Preamble;", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Multipart.kt */
public abstract class MultipartEvent {
    public /* synthetic */ MultipartEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract void release();

    private MultipartEvent() {
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lio/ktor/http/cio/MultipartEvent$Preamble;", "Lio/ktor/http/cio/MultipartEvent;", "body", "Lio/ktor/utils/io/core/ByteReadPacket;", "(Lio/ktor/utils/io/core/ByteReadPacket;)V", "getBody", "()Lio/ktor/utils/io/core/ByteReadPacket;", "release", "", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Multipart.kt */
    public static final class Preamble extends MultipartEvent {
        private final ByteReadPacket body;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Preamble(ByteReadPacket byteReadPacket) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(byteReadPacket, "body");
            this.body = byteReadPacket;
        }

        public final ByteReadPacket getBody() {
            return this.body;
        }

        public void release() {
            this.body.release();
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\f\u001a\u00020\rH\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lio/ktor/http/cio/MultipartEvent$MultipartPart;", "Lio/ktor/http/cio/MultipartEvent;", "headers", "Lkotlinx/coroutines/Deferred;", "Lio/ktor/http/cio/HttpHeadersMap;", "body", "Lio/ktor/utils/io/ByteReadChannel;", "(Lkotlinx/coroutines/Deferred;Lio/ktor/utils/io/ByteReadChannel;)V", "getBody", "()Lio/ktor/utils/io/ByteReadChannel;", "getHeaders", "()Lkotlinx/coroutines/Deferred;", "release", "", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Multipart.kt */
    public static final class MultipartPart extends MultipartEvent {
        private final ByteReadChannel body;
        private final Deferred<HttpHeadersMap> headers;

        public final Deferred<HttpHeadersMap> getHeaders() {
            return this.headers;
        }

        public final ByteReadChannel getBody() {
            return this.body;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public MultipartPart(Deferred<HttpHeadersMap> deferred, ByteReadChannel byteReadChannel) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(deferred, "headers");
            Intrinsics.checkNotNullParameter(byteReadChannel, "body");
            this.headers = deferred;
            this.body = byteReadChannel;
        }

        public void release() {
            this.headers.invokeOnCompletion(new MultipartEvent$MultipartPart$release$1(this));
            Object unused = BuildersKt__BuildersKt.runBlocking$default((CoroutineContext) null, new MultipartEvent$MultipartPart$release$2(this, (Continuation<? super MultipartEvent$MultipartPart$release$2>) null), 1, (Object) null);
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lio/ktor/http/cio/MultipartEvent$Epilogue;", "Lio/ktor/http/cio/MultipartEvent;", "body", "Lio/ktor/utils/io/core/ByteReadPacket;", "(Lio/ktor/utils/io/core/ByteReadPacket;)V", "getBody", "()Lio/ktor/utils/io/core/ByteReadPacket;", "release", "", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Multipart.kt */
    public static final class Epilogue extends MultipartEvent {
        private final ByteReadPacket body;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Epilogue(ByteReadPacket byteReadPacket) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(byteReadPacket, "body");
            this.body = byteReadPacket;
        }

        public final ByteReadPacket getBody() {
            return this.body;
        }

        public void release() {
            this.body.release();
        }
    }
}
