package org.videolan.moviepedia;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.Response;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lorg/videolan/moviepedia/UserAgentInterceptor;", "Lokhttp3/Interceptor;", "userAgent", "", "(Ljava/lang/String;)V", "getUserAgent", "()Ljava/lang/String;", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MoviepediaApiService.kt */
final class UserAgentInterceptor implements Interceptor {
    private final String userAgent;

    public UserAgentInterceptor(String str) {
        Intrinsics.checkNotNullParameter(str, "userAgent");
        this.userAgent = str;
    }

    public final String getUserAgent() {
        return this.userAgent;
    }

    public Response intercept(Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        return chain.proceed(chain.request().newBuilder().header("User-Agent", this.userAgent).header("Client", "vlc-android").header("Client-Version", "3050720").header("Client-Type", "release").build());
    }
}
