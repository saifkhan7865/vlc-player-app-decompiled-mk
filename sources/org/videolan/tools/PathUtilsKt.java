package org.videolan.tools;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.vlc.ArtworkProvider;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\u001a\u001d\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00062\u0006\u0010\u0004\u001a\u00020\u0003\u001a\n\u0010\u0007\u001a\u00020\u0003*\u00020\u0003¨\u0006\b"}, d2 = {"containsPath", "", "", "", "path", "([Ljava/lang/String;Ljava/lang/String;)Z", "", "sanitizePath", "tools_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: PathUtils.kt */
public final class PathUtilsKt {
    public static final boolean containsPath(List<String> list, String str) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Iterable<String> iterable = list;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return false;
        }
        for (String sanitizePath : iterable) {
            if (Intrinsics.areEqual((Object) sanitizePath(sanitizePath), (Object) sanitizePath(str))) {
                return true;
            }
        }
        return false;
    }

    public static final String sanitizePath(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (StringsKt.endsWith$default((CharSequence) str, '/', false, 2, (Object) null)) {
            str = StringsKt.substringBeforeLast$default(str, "/", (String) null, 2, (Object) null);
        }
        return Strings.removeFileScheme(str);
    }

    public static final boolean containsPath(String[] strArr, String str) {
        Intrinsics.checkNotNullParameter(strArr, "<this>");
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        for (String sanitizePath : strArr) {
            if (Intrinsics.areEqual((Object) sanitizePath(sanitizePath), (Object) sanitizePath(str))) {
                return true;
            }
        }
        return false;
    }
}
