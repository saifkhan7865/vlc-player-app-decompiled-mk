package org.videolan.resources.opensubtitles;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import okhttp3.OkHttpClient;
import org.videolan.resources.AppContextProvider;
import org.videolan.resources.util.ConnectivityInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"BASE_URL", "", "USER_AGENT", "buildClient", "Lorg/videolan/resources/opensubtitles/IOpenSubtitleService;", "kotlin.jvm.PlatformType", "resources_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: OpenSubtitleService.kt */
public final class OpenSubtitleServiceKt {
    private static final String BASE_URL = "https://rest.opensubtitles.org/search/";
    private static final String USER_AGENT = "VLSub 0.9";

    /* access modifiers changed from: private */
    public static final IOpenSubtitleService buildClient() {
        return (IOpenSubtitleService) new Retrofit.Builder().baseUrl(BASE_URL).client(new OkHttpClient.Builder().addInterceptor(new UserAgentInterceptor(USER_AGENT)).addInterceptor(new ConnectivityInterceptor(AppContextProvider.INSTANCE.getAppContext())).readTimeout(10, TimeUnit.SECONDS).connectTimeout(5, TimeUnit.SECONDS).build()).addConverterFactory(MoshiConverterFactory.create()).build().create(IOpenSubtitleService.class);
    }
}
