package io.ktor.http.cio.internals;

import io.ktor.utils.io.pool.DefaultPool;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0019\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0002H\u0014¨\u0006\u0004"}, d2 = {"io/ktor/http/cio/internals/CharArrayPoolKt$CharArrayPool$2", "Lio/ktor/utils/io/pool/DefaultPool;", "", "produceInstance", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CharArrayPool.kt */
public final class CharArrayPoolKt$CharArrayPool$2 extends DefaultPool<char[]> {
    CharArrayPoolKt$CharArrayPool$2() {
        super(4096);
    }

    /* access modifiers changed from: protected */
    public char[] produceInstance() {
        return new char[2048];
    }
}
