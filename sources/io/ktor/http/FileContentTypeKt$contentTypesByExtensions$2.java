package io.ktor.http;

import io.ktor.util.CollectionsKt;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0001H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "", "", "Lio/ktor/http/ContentType;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: FileContentType.kt */
final class FileContentTypeKt$contentTypesByExtensions$2 extends Lambda implements Function0<Map<String, List<? extends ContentType>>> {
    public static final FileContentTypeKt$contentTypesByExtensions$2 INSTANCE = new FileContentTypeKt$contentTypesByExtensions$2();

    FileContentTypeKt$contentTypesByExtensions$2() {
        super(0);
    }

    public final Map<String, List<ContentType>> invoke() {
        Map<String, List<ContentType>> caseInsensitiveMap = CollectionsKt.caseInsensitiveMap();
        caseInsensitiveMap.putAll(FileContentTypeKt.groupByPairs(kotlin.collections.CollectionsKt.asSequence(MimesKt.getMimes())));
        return caseInsensitiveMap;
    }
}
