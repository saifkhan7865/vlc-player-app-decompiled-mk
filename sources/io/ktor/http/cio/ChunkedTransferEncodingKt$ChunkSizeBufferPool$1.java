package io.ktor.http.cio;

import io.ktor.utils.io.pool.DefaultPool;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001J\u0018\u0010\u0004\u001a\u00060\u0002j\u0002`\u00032\n\u0010\u0005\u001a\u00060\u0002j\u0002`\u0003H\u0014J\f\u0010\u0006\u001a\u00060\u0002j\u0002`\u0003H\u0014¨\u0006\u0007"}, d2 = {"io/ktor/http/cio/ChunkedTransferEncodingKt$ChunkSizeBufferPool$1", "Lio/ktor/utils/io/pool/DefaultPool;", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "clearInstance", "instance", "produceInstance", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ChunkedTransferEncoding.kt */
public final class ChunkedTransferEncodingKt$ChunkSizeBufferPool$1 extends DefaultPool<StringBuilder> {
    ChunkedTransferEncodingKt$ChunkSizeBufferPool$1() {
        super(2048);
    }

    /* access modifiers changed from: protected */
    public StringBuilder produceInstance() {
        return new StringBuilder(128);
    }

    /* access modifiers changed from: protected */
    public StringBuilder clearInstance(StringBuilder sb) {
        Intrinsics.checkNotNullParameter(sb, "instance");
        StringsKt.clear(sb);
        return sb;
    }
}
