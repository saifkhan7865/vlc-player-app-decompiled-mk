package io.ktor.server.engine;

import io.ktor.util.NioPathKt$$ExternalSyntheticApiModelOutline0;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\n"}, d2 = {"io/ktor/server/engine/ApplicationEngineEnvironmentReloading$watchUrls$visitor$1", "Ljava/nio/file/SimpleFileVisitor;", "Ljava/nio/file/Path;", "preVisitDirectory", "Ljava/nio/file/FileVisitResult;", "dir", "attrs", "Ljava/nio/file/attribute/BasicFileAttributes;", "visitFile", "file", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationEngineEnvironmentReloading.kt */
public final class ApplicationEngineEnvironmentReloading$watchUrls$visitor$1 extends SimpleFileVisitor<Path> {
    final /* synthetic */ HashSet<Path> $paths;

    ApplicationEngineEnvironmentReloading$watchUrls$visitor$1(HashSet<Path> hashSet) {
        this.$paths = hashSet;
    }

    public /* bridge */ /* synthetic */ FileVisitResult preVisitDirectory(Object obj, BasicFileAttributes basicFileAttributes) {
        return preVisitDirectory(NioPathKt$$ExternalSyntheticApiModelOutline0.m(obj), basicFileAttributes);
    }

    public /* bridge */ /* synthetic */ FileVisitResult visitFile(Object obj, BasicFileAttributes basicFileAttributes) {
        return visitFile(NioPathKt$$ExternalSyntheticApiModelOutline0.m(obj), basicFileAttributes);
    }

    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes basicFileAttributes) {
        Intrinsics.checkNotNullParameter(path, "dir");
        Intrinsics.checkNotNullParameter(basicFileAttributes, "attrs");
        this.$paths.add(path);
        return NioPathKt$$ExternalSyntheticApiModelOutline0.m();
    }

    public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) {
        Intrinsics.checkNotNullParameter(path, "file");
        Intrinsics.checkNotNullParameter(basicFileAttributes, "attrs");
        Path m = NioPathKt$$ExternalSyntheticApiModelOutline0.m(path);
        if (m != null) {
            this.$paths.add(m);
        }
        return NioPathKt$$ExternalSyntheticApiModelOutline0.m();
    }
}
