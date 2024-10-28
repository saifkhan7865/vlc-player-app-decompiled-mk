package io.ktor.util;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0012\u0010\u0005\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0002\u001a\u0012\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002\u001a\f\u0010\b\u001a\u00020\u0002*\u00020\u0002H\u0002\u001a\n\u0010\t\u001a\u00020\u0002*\u00020\u0002\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\n"}, d2 = {"extension", "", "Ljava/nio/file/Path;", "getExtension", "(Ljava/nio/file/Path;)Ljava/lang/String;", "combineSafe", "Ljava/io/File;", "relativePath", "dropLeadingTopDirs", "normalizeAndRelativize", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: NioPath.kt */
public final class NioPathKt {
    public static final String getExtension(Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return StringsKt.substringAfterLast$default(path.getFileName().toString(), ".", (String) null, 2, (Object) null);
    }

    public static final File combineSafe(Path path, Path path2) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(path2, "relativePath");
        Path normalizeAndRelativize = normalizeAndRelativize(path2);
        if (normalizeAndRelativize.startsWith("..")) {
            String obj = path2.toString();
            throw new InvalidPathException(obj, "Relative path " + path2 + " beginning with .. is invalid");
        } else if (!NioPathKt$$ExternalSyntheticApiModelOutline0.m(normalizeAndRelativize)) {
            File m = NioPathKt$$ExternalSyntheticApiModelOutline0.m(path.resolve(normalizeAndRelativize));
            Intrinsics.checkNotNullExpressionValue(m, "resolve(normalized).toFile()");
            return m;
        } else {
            throw new IllegalStateException(("Bad relative path " + path2).toString());
        }
    }

    public static final Path normalizeAndRelativize(Path path) {
        Path m;
        Path m$3;
        Path dropLeadingTopDirs;
        Intrinsics.checkNotNullParameter(path, "<this>");
        Path m$2 = path.getRoot();
        if (m$2 != null && (m = m$2.relativize(path)) != null && (m$3 = m.normalize()) != null && (dropLeadingTopDirs = dropLeadingTopDirs(m$3)) != null) {
            return dropLeadingTopDirs;
        }
        Path m$32 = path.normalize();
        Intrinsics.checkNotNullExpressionValue(m$32, "normalize()");
        return dropLeadingTopDirs(m$32);
    }

    private static final Path dropLeadingTopDirs(Path path) {
        Iterator it = path.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            Object next = it.next();
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            if (!Intrinsics.areEqual((Object) NioPathKt$$ExternalSyntheticApiModelOutline0.m(next).toString(), (Object) "..")) {
                break;
            }
            i++;
        }
        if (i == 0) {
            return path;
        }
        Path m = path.subpath(i, NioPathKt$$ExternalSyntheticApiModelOutline0.m(path));
        Intrinsics.checkNotNullExpressionValue(m, "subpath(startIndex, nameCount)");
        return m;
    }

    public static final File combineSafe(File file, Path path) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(path, "relativePath");
        Path normalizeAndRelativize = normalizeAndRelativize(path);
        if (normalizeAndRelativize.startsWith("..")) {
            String obj = path.toString();
            throw new InvalidPathException(obj, "Relative path " + path + " beginning with .. is invalid");
        } else if (!NioPathKt$$ExternalSyntheticApiModelOutline0.m(normalizeAndRelativize)) {
            return new File(file, normalizeAndRelativize.toString());
        } else {
            throw new IllegalStateException(("Bad relative path " + path).toString());
        }
    }
}
