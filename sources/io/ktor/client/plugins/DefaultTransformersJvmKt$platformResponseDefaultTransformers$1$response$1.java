package io.ktor.client.plugins;

import io.ktor.client.call.HttpClientCall;
import io.ktor.client.statement.HttpResponseContainer;
import io.ktor.client.statement.HttpResponseKt;
import io.ktor.util.pipeline.PipelineContext;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J \u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003H\u0016¨\u0006\u000b"}, d2 = {"io/ktor/client/plugins/DefaultTransformersJvmKt$platformResponseDefaultTransformers$1$response$1", "Ljava/io/InputStream;", "available", "", "close", "", "read", "b", "", "off", "len", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DefaultTransformersJvm.kt */
public final class DefaultTransformersJvmKt$platformResponseDefaultTransformers$1$response$1 extends InputStream {
    final /* synthetic */ PipelineContext<HttpResponseContainer, HttpClientCall> $$this$intercept;
    final /* synthetic */ InputStream $stream;

    DefaultTransformersJvmKt$platformResponseDefaultTransformers$1$response$1(InputStream inputStream, PipelineContext<HttpResponseContainer, HttpClientCall> pipelineContext) {
        this.$stream = inputStream;
        this.$$this$intercept = pipelineContext;
    }

    public int read() {
        return this.$stream.read();
    }

    public int read(byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(bArr, "b");
        return this.$stream.read(bArr, i, i2);
    }

    public int available() {
        return this.$stream.available();
    }

    public void close() {
        super.close();
        this.$stream.close();
        HttpResponseKt.complete(this.$$this$intercept.getContext().getResponse());
    }
}
