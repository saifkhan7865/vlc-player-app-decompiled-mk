package io.ktor.server.sessions;

import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u001a\u0015\u0010\u0006\u001a\u00020\u0007*\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003H\u0010\u001a\f\u0010\t\u001a\u00020\u0007*\u00020\u0003H\u0002¨\u0006\n"}, d2 = {"directorySessionStorage", "Lio/ktor/server/sessions/SessionStorage;", "rootDir", "Ljava/io/File;", "cached", "", "deleteParentsWhileEmpty", "", "mostTop", "mkdirsOrFail", "ktor-server-sessions"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: DirectoryStorage.kt */
public final class DirectoryStorageKt {
    public static /* synthetic */ SessionStorage directorySessionStorage$default(File file, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return directorySessionStorage(file, z);
    }

    public static final SessionStorage directorySessionStorage(File file, boolean z) {
        Intrinsics.checkNotNullParameter(file, "rootDir");
        if (z) {
            return new CacheStorage(new DirectoryStorage(file), 60000);
        }
        if (!z) {
            return new DirectoryStorage(file);
        }
        throw new NoWhenBranchMatchedException();
    }

    /* access modifiers changed from: private */
    public static final void mkdirsOrFail(File file) {
        if (!file.mkdirs() && !file.exists()) {
            throw new IOException("Couldn't create directory " + file);
        } else if (!file.isDirectory()) {
            throw new IOException("Path is not a directory: " + file);
        }
    }

    /* access modifiers changed from: private */
    public static final void deleteParentsWhileEmpty(File file, File file2) {
        while (!Intrinsics.areEqual((Object) file, (Object) file2) && file.isDirectory() && file.exists()) {
            String[] list = file.list();
            if (list != null && list.length != 0) {
                return;
            }
            if (file.delete() || !file.exists()) {
                file = file.getParentFile();
                Intrinsics.checkNotNullExpressionValue(file, "parentFile");
            } else {
                throw new IOException("Failed to delete dir " + file);
            }
        }
    }
}
