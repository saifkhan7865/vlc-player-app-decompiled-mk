package io.ktor.server.http.content;

import io.ktor.http.ContentType;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.io.File;
import java.net.URL;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.text.Typography;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lio/ktor/http/ContentType;", "url", "Ljava/net/URL;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: PreCompressed.kt */
final class PreCompressedKt$bestCompressionFit$4$resolved$1 extends Lambda implements Function1<URL, ContentType> {
    final /* synthetic */ String $compressed;
    final /* synthetic */ Function1<URL, ContentType> $contentType;
    final /* synthetic */ String $resource;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreCompressedKt$bestCompressionFit$4$resolved$1(String str, String str2, Function1<? super URL, ContentType> function1) {
        super(1);
        this.$compressed = str;
        this.$resource = str2;
        this.$contentType = function1;
    }

    public final ContentType invoke(URL url) {
        Intrinsics.checkNotNullParameter(url, RtspHeaders.Values.URL);
        String path = url.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "url.path");
        StringBuilder sb = new StringBuilder();
        Regex.Companion companion = Regex.Companion;
        String str = this.$compressed;
        String str2 = File.separator;
        Intrinsics.checkNotNullExpressionValue(str2, "separator");
        sb.append(companion.escapeReplacement(StringsKt.substringAfterLast$default(str, str2, (String) null, 2, (Object) null)));
        sb.append(Typography.dollar);
        Regex regex = new Regex(sb.toString());
        String str3 = this.$resource;
        String str4 = File.separator;
        Intrinsics.checkNotNullExpressionValue(str4, "separator");
        return this.$contentType.invoke(new URL(url.getProtocol(), url.getHost(), url.getPort(), regex.replace((CharSequence) path, StringsKt.substringAfterLast$default(str3, str4, (String) null, 2, (Object) null))));
    }
}
