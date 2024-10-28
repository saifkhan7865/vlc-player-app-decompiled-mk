package org.videolan.moviepedia;

import com.squareup.moshi.Moshi;
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\b\u0010\u0002\u001a\u00020\u0003H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u0004"}, d2 = {"USER_AGENT", "", "buildClient", "Lorg/videolan/moviepedia/IMoviepediaApiService;", "moviepedia_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: MoviepediaApiService.kt */
public final class MoviepediaApiServiceKt {
    private static final String USER_AGENT = "VLC-Android";

    /* access modifiers changed from: private */
    public static final IMoviepediaApiService buildClient() {
        Retrofit.Builder baseUrl = new Retrofit.Builder().baseUrl(BuildConfig.MOVIEPEDIA_API_URL);
        OkHttpClient.Builder addInterceptor = new OkHttpClient.Builder().addInterceptor(new UserAgentInterceptor(USER_AGENT));
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor((HttpLoggingInterceptor.Logger) null, 1, (DefaultConstructorMarker) null);
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        Unit unit = Unit.INSTANCE;
        Object create = baseUrl.client(addInterceptor.addInterceptor(httpLoggingInterceptor).readTimeout(10, TimeUnit.SECONDS).connectTimeout(5, TimeUnit.SECONDS).build()).addConverterFactory(MoshiConverterFactory.create(new Moshi.Builder().add(Date.class, new Rfc3339DateJsonAdapter().nullSafe()).build())).build().create(IMoviepediaApiService.class);
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        return (IMoviepediaApiService) create;
    }
}
