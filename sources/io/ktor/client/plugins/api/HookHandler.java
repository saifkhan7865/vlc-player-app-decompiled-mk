package io.ktor.client.plugins.api;

import io.ktor.client.HttpClient;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001a\u00028\u0000¢\u0006\u0002\u0010\u0006J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u0010\u0010\u0005\u001a\u00028\u0000X\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lio/ktor/client/plugins/api/HookHandler;", "T", "", "hook", "Lio/ktor/client/plugins/api/ClientHook;", "handler", "(Lio/ktor/client/plugins/api/ClientHook;Ljava/lang/Object;)V", "Ljava/lang/Object;", "install", "", "client", "Lio/ktor/client/HttpClient;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ClientHook.kt */
public final class HookHandler<T> {
    private final T handler;
    private final ClientHook<T> hook;

    public HookHandler(ClientHook<T> clientHook, T t) {
        Intrinsics.checkNotNullParameter(clientHook, "hook");
        this.hook = clientHook;
        this.handler = t;
    }

    public final void install(HttpClient httpClient) {
        Intrinsics.checkNotNullParameter(httpClient, "client");
        this.hook.install(httpClient, this.handler);
    }
}
