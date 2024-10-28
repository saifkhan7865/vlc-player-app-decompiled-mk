package io.ktor.server.http.content;

import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/ktor/server/http/content/CompressedFileType;", "invoke", "(Lio/ktor/server/http/content/CompressedFileType;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: PreCompressed.kt */
final class PreCompressedKt$bestCompressionFit$3 extends Lambda implements Function1<CompressedFileType, Boolean> {
    final /* synthetic */ Set<String> $acceptedEncodings;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreCompressedKt$bestCompressionFit$3(Set<String> set) {
        super(1);
        this.$acceptedEncodings = set;
    }

    public final Boolean invoke(CompressedFileType compressedFileType) {
        Intrinsics.checkNotNullParameter(compressedFileType, "it");
        return Boolean.valueOf(this.$acceptedEncodings.contains(compressedFileType.getEncoding()));
    }
}
