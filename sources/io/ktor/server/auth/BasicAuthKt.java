package io.ktor.server.auth;

import io.ktor.http.auth.AuthScheme;
import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.server.auth.BasicAuthenticationProvider;
import io.ktor.server.request.ApplicationRequest;
import io.ktor.util.Base64Kt;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a/\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\b¢\u0006\u0002\b\n\u001a\u001e\u0010\u000b\u001a\u0004\u0018\u00010\f*\u00020\r2\u0010\b\u0002\u0010\u000e\u001a\n\u0018\u00010\u000fj\u0004\u0018\u0001`\u0010\"\u000e\u0010\u0000\u001a\u00020\u0001XD¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"basicAuthenticationChallengeKey", "", "basic", "", "Lio/ktor/server/auth/AuthenticationConfig;", "name", "", "configure", "Lkotlin/Function1;", "Lio/ktor/server/auth/BasicAuthenticationProvider$Config;", "Lkotlin/ExtensionFunctionType;", "basicAuthenticationCredentials", "Lio/ktor/server/auth/UserPasswordCredential;", "Lio/ktor/server/request/ApplicationRequest;", "charset", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "ktor-server-auth"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: BasicAuth.kt */
public final class BasicAuthKt {
    /* access modifiers changed from: private */
    public static final Object basicAuthenticationChallengeKey = "BasicAuth";

    public static /* synthetic */ void basic$default(AuthenticationConfig authenticationConfig, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        basic(authenticationConfig, str, function1);
    }

    public static final void basic(AuthenticationConfig authenticationConfig, String str, Function1<? super BasicAuthenticationProvider.Config, Unit> function1) {
        Intrinsics.checkNotNullParameter(authenticationConfig, "<this>");
        Intrinsics.checkNotNullParameter(function1, "configure");
        BasicAuthenticationProvider.Config config = new BasicAuthenticationProvider.Config(str);
        function1.invoke(config);
        authenticationConfig.register(new BasicAuthenticationProvider(config));
    }

    public static /* synthetic */ UserPasswordCredential basicAuthenticationCredentials$default(ApplicationRequest applicationRequest, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = null;
        }
        return basicAuthenticationCredentials(applicationRequest, charset);
    }

    public static final UserPasswordCredential basicAuthenticationCredentials(ApplicationRequest applicationRequest, Charset charset) {
        Intrinsics.checkNotNullParameter(applicationRequest, "<this>");
        HttpAuthHeader parseAuthorizationHeader = HeadersKt.parseAuthorizationHeader(applicationRequest);
        if (!(parseAuthorizationHeader instanceof HttpAuthHeader.Single) || !StringsKt.equals(((HttpAuthHeader.Single) parseAuthorizationHeader).getAuthScheme(), AuthScheme.Basic, true)) {
            return null;
        }
        try {
            byte[] decodeBase64Bytes = Base64Kt.decodeBase64Bytes(((HttpAuthHeader.Single) parseAuthorizationHeader).getBlob());
            if (charset == null) {
                charset = Charsets.ISO_8859_1;
            }
            String str = new String(decodeBase64Bytes, 0, decodeBase64Bytes.length, charset);
            int indexOf$default = StringsKt.indexOf$default((CharSequence) str, (char) AbstractJsonLexerKt.COLON, 0, false, 6, (Object) null);
            if (indexOf$default == -1) {
                return null;
            }
            String substring = str.substring(0, indexOf$default);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            String substring2 = str.substring(indexOf$default + 1);
            Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String).substring(startIndex)");
            return new UserPasswordCredential(substring, substring2);
        } catch (Throwable unused) {
            return null;
        }
    }
}
