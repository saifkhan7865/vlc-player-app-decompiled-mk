package io.ktor.server.auth;

import io.ktor.http.HttpMethod;
import io.ktor.http.auth.AuthScheme;
import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.auth.DigestAuthenticationProvider;
import io.ktor.util.CryptoKt;
import io.ktor.util.TextKt;
import java.security.MessageDigest;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.videolan.resources.Constants;

@Metadata(d1 = {"\u0000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a/\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\b¢\u0006\u0002\b\n\u001a\f\u0010\u000b\u001a\u0004\u0018\u00010\f*\u00020\r\u001a\"\u0010\u000e\u001a\u00020\u000f*\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000f\u001a\n\u0010\u0015\u001a\u00020\f*\u00020\u0016\u001aQ\u0010\u0017\u001a\u00020\u0018*\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132*\u0010\u0014\u001a&\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0019H@ø\u0001\u0000¢\u0006\u0002\u0010\u001b\"\u000e\u0010\u0000\u001a\u00020\u0001XD¢\u0006\u0002\n\u0000*\u0001\u0010\u001c\"D\b\u0001\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u001d\u0012\b\b\u0005\u0012\u0004\b\b(\u001e\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u001d\u0012\b\b\u0005\u0012\u0004\b\b(\u001f\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00192D\b\u0001\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u001d\u0012\b\b\u0005\u0012\u0004\b\b(\u001e\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u001d\u0012\b\b\u0005\u0012\u0004\b\b(\u001f\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0019\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, d2 = {"digestAuthenticationChallengeKey", "", "digest", "", "Lio/ktor/server/auth/AuthenticationConfig;", "name", "", "configure", "Lkotlin/Function1;", "Lio/ktor/server/auth/DigestAuthenticationProvider$Config;", "Lkotlin/ExtensionFunctionType;", "digestAuthenticationCredentials", "Lio/ktor/server/auth/DigestCredential;", "Lio/ktor/server/application/ApplicationCall;", "expectedDigest", "", "method", "Lio/ktor/http/HttpMethod;", "digester", "Ljava/security/MessageDigest;", "userNameRealmPasswordDigest", "toDigestCredential", "Lio/ktor/http/auth/HttpAuthHeader$Parameterized;", "verifier", "", "Lkotlin/Function3;", "Lkotlin/coroutines/Continuation;", "(Lio/ktor/server/auth/DigestCredential;Lio/ktor/http/HttpMethod;Ljava/security/MessageDigest;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "DigestProviderFunction", "Lkotlin/ParameterName;", "userName", "realm", "ktor-server-auth"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: DigestAuth.kt */
public final class DigestAuthKt {
    /* access modifiers changed from: private */
    public static final Object digestAuthenticationChallengeKey = "DigestAuth";

    public static /* synthetic */ void digest$default(AuthenticationConfig authenticationConfig, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        digest(authenticationConfig, str, function1);
    }

    public static final void digest(AuthenticationConfig authenticationConfig, String str, Function1<? super DigestAuthenticationProvider.Config, Unit> function1) {
        Intrinsics.checkNotNullParameter(authenticationConfig, "<this>");
        Intrinsics.checkNotNullParameter(function1, "configure");
        DigestAuthenticationProvider.Config config = new DigestAuthenticationProvider.Config(str);
        function1.invoke(config);
        authenticationConfig.register(new DigestAuthenticationProvider(config));
    }

    public static final DigestCredential digestAuthenticationCredentials(ApplicationCall applicationCall) {
        Intrinsics.checkNotNullParameter(applicationCall, "<this>");
        HttpAuthHeader parseAuthorizationHeader = HeadersKt.parseAuthorizationHeader(applicationCall.getRequest());
        if (parseAuthorizationHeader == null || !Intrinsics.areEqual((Object) parseAuthorizationHeader.getAuthScheme(), (Object) AuthScheme.Digest) || !(parseAuthorizationHeader instanceof HttpAuthHeader.Parameterized)) {
            return null;
        }
        return toDigestCredential((HttpAuthHeader.Parameterized) parseAuthorizationHeader);
    }

    public static final DigestCredential toDigestCredential(HttpAuthHeader.Parameterized parameterized) {
        Intrinsics.checkNotNullParameter(parameterized, "<this>");
        String parameter = parameterized.parameter(HttpAuthHeader.Parameters.Realm);
        Intrinsics.checkNotNull(parameter);
        String parameter2 = parameterized.parameter(OAuth2RequestParameters.UserName);
        Intrinsics.checkNotNull(parameter2);
        String parameter3 = parameterized.parameter(Constants.KEY_URI);
        Intrinsics.checkNotNull(parameter3);
        String parameter4 = parameterized.parameter("nonce");
        Intrinsics.checkNotNull(parameter4);
        String parameter5 = parameterized.parameter("opaque");
        String parameter6 = parameterized.parameter("nc");
        String parameter7 = parameterized.parameter("algorithm");
        String parameter8 = parameterized.parameter("response");
        Intrinsics.checkNotNull(parameter8);
        return new DigestCredential(parameter, parameter2, parameter3, parameter4, parameter5, parameter6, parameter7, parameter8, parameterized.parameter("cnonce"), parameterized.parameter("qop"));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: java.security.MessageDigest} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: io.ktor.http.HttpMethod} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object verifier(io.ktor.server.auth.DigestCredential r4, io.ktor.http.HttpMethod r5, java.security.MessageDigest r6, kotlin.jvm.functions.Function3<? super java.lang.String, ? super java.lang.String, ? super kotlin.coroutines.Continuation<? super byte[]>, ? extends java.lang.Object> r7, kotlin.coroutines.Continuation<? super java.lang.Boolean> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.server.auth.DigestAuthKt$verifier$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ktor.server.auth.DigestAuthKt$verifier$1 r0 = (io.ktor.server.auth.DigestAuthKt$verifier$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ktor.server.auth.DigestAuthKt$verifier$1 r0 = new io.ktor.server.auth.DigestAuthKt$verifier$1
            r0.<init>(r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0040
            if (r2 != r3) goto L_0x0038
            java.lang.Object r4 = r0.L$2
            r6 = r4
            java.security.MessageDigest r6 = (java.security.MessageDigest) r6
            java.lang.Object r4 = r0.L$1
            r5 = r4
            io.ktor.http.HttpMethod r5 = (io.ktor.http.HttpMethod) r5
            java.lang.Object r4 = r0.L$0
            io.ktor.server.auth.DigestCredential r4 = (io.ktor.server.auth.DigestCredential) r4
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x005a
        L_0x0038:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0040:
            kotlin.ResultKt.throwOnFailure(r8)
            java.lang.String r8 = r4.getUserName()
            java.lang.String r2 = r4.getRealm()
            r0.L$0 = r4
            r0.L$1 = r5
            r0.L$2 = r6
            r0.label = r3
            java.lang.Object r8 = r7.invoke(r8, r2, r0)
            if (r8 != r1) goto L_0x005a
            return r1
        L_0x005a:
            byte[] r8 = (byte[]) r8
            r7 = 0
            if (r8 != 0) goto L_0x0062
            byte[] r0 = new byte[r7]
            goto L_0x0063
        L_0x0062:
            r0 = r8
        L_0x0063:
            byte[] r5 = expectedDigest(r4, r5, r6, r0)
            java.lang.String r4 = r4.getResponse()     // Catch:{ NumberFormatException -> 0x007e }
            byte[] r4 = io.ktor.util.CryptoKt.hex((java.lang.String) r4)     // Catch:{ NumberFormatException -> 0x007e }
            boolean r4 = java.security.MessageDigest.isEqual(r4, r5)
            if (r4 == 0) goto L_0x0078
            if (r8 == 0) goto L_0x0078
            goto L_0x0079
        L_0x0078:
            r3 = 0
        L_0x0079:
            java.lang.Boolean r4 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r4
        L_0x007e:
            java.lang.Boolean r4 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r7)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.DigestAuthKt.verifier(io.ktor.server.auth.DigestCredential, io.ktor.http.HttpMethod, java.security.MessageDigest, kotlin.jvm.functions.Function3, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final byte[] expectedDigest$digest(MessageDigest messageDigest, String str) {
        messageDigest.reset();
        byte[] bytes = str.getBytes(Charsets.ISO_8859_1);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        messageDigest.update(bytes);
        byte[] digest = messageDigest.digest();
        Intrinsics.checkNotNullExpressionValue(digest, "digester.digest()");
        return digest;
    }

    public static final byte[] expectedDigest(DigestCredential digestCredential, HttpMethod httpMethod, MessageDigest messageDigest, byte[] bArr) {
        List list;
        Intrinsics.checkNotNullParameter(digestCredential, "<this>");
        Intrinsics.checkNotNullParameter(httpMethod, "method");
        Intrinsics.checkNotNullParameter(messageDigest, "digester");
        Intrinsics.checkNotNullParameter(bArr, "userNameRealmPasswordDigest");
        String hex = CryptoKt.hex(bArr);
        String hex2 = CryptoKt.hex(expectedDigest$digest(messageDigest, TextKt.toUpperCasePreservingASCIIRules(httpMethod.getValue()) + AbstractJsonLexerKt.COLON + digestCredential.getDigestUri()));
        if (digestCredential.getQop() == null) {
            list = CollectionsKt.listOf(hex, digestCredential.getNonce(), hex2);
        } else {
            list = CollectionsKt.listOf(hex, digestCredential.getNonce(), digestCredential.getNonceCount(), digestCredential.getCnonce(), digestCredential.getQop(), hex2);
        }
        return expectedDigest$digest(messageDigest, CollectionsKt.joinToString$default(list, ":", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
    }
}
