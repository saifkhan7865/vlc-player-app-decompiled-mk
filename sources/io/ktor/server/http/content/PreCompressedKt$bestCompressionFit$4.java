package io.ktor.server.http.content;

import io.ktor.http.ContentType;
import io.ktor.http.content.OutgoingContent;
import io.ktor.server.application.ApplicationCall;
import java.net.URL;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lio/ktor/server/http/content/CompressedResource;", "it", "Lio/ktor/server/http/content/CompressedFileType;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: PreCompressed.kt */
final class PreCompressedKt$bestCompressionFit$4 extends Lambda implements Function1<CompressedFileType, CompressedResource> {
    final /* synthetic */ ApplicationCall $call;
    final /* synthetic */ Function1<URL, ContentType> $contentType;
    final /* synthetic */ String $packageName;
    final /* synthetic */ String $resource;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreCompressedKt$bestCompressionFit$4(String str, ApplicationCall applicationCall, String str2, Function1<? super URL, ContentType> function1) {
        super(1);
        this.$resource = str;
        this.$call = applicationCall;
        this.$packageName = str2;
        this.$contentType = function1;
    }

    public final CompressedResource invoke(CompressedFileType compressedFileType) {
        Intrinsics.checkNotNullParameter(compressedFileType, "it");
        String str = this.$resource + '.' + compressedFileType.getExtension();
        Pair resolveResource$default = StaticContentResolutionKt.resolveResource$default(this.$call.getApplication(), str, this.$packageName, (ClassLoader) null, (Function1) new PreCompressedKt$bestCompressionFit$4$resolved$1(str, this.$resource, this.$contentType), 4, (Object) null);
        if (resolveResource$default == null) {
            return null;
        }
        return new CompressedResource((URL) resolveResource$default.getFirst(), (OutgoingContent.ReadChannelContent) resolveResource$default.getSecond(), compressedFileType);
    }
}
