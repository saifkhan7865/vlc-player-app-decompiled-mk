package io.ktor.client.plugins;

import io.ktor.client.HttpClientConfig;
import io.ktor.client.plugins.DefaultRequest;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;

@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a'\u0010\u0003\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\u00052\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00040\u0007¢\u0006\u0002\b\t\"\u0012\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"LOGGER", "Lorg/slf4j/Logger;", "Lio/ktor/util/logging/Logger;", "defaultRequest", "", "Lio/ktor/client/HttpClientConfig;", "block", "Lkotlin/Function1;", "Lio/ktor/client/plugins/DefaultRequest$DefaultRequestBuilder;", "Lkotlin/ExtensionFunctionType;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: DefaultRequest.kt */
public final class DefaultRequestKt {
    /* access modifiers changed from: private */
    public static final Logger LOGGER = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.DefaultRequest");

    public static final void defaultRequest(HttpClientConfig<?> httpClientConfig, Function1<? super DefaultRequest.DefaultRequestBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(httpClientConfig, "<this>");
        Intrinsics.checkNotNullParameter(function1, "block");
        httpClientConfig.install((HttpClientPlugin<? extends TBuilder, TPlugin>) DefaultRequest.Plugin, (Function1<? super TBuilder, Unit>) new DefaultRequestKt$defaultRequest$1(function1));
    }
}
