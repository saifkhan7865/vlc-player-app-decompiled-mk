package io.ktor.server.sessions;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH\u0002J\u0019\u0010\u000b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0002\u0010\fJ\u0019\u0010\r\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0002\u0010\fJ\u0010\u0010\u000e\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0002J\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\u00102\u0006\u0010\t\u001a\u00020\nH\u0002J!\u0010\u0011\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0002\u0010\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lio/ktor/server/sessions/DirectoryStorage;", "Lio/ktor/server/sessions/SessionStorage;", "Ljava/io/Closeable;", "dir", "Ljava/io/File;", "(Ljava/io/File;)V", "close", "", "fileOf", "id", "", "invalidate", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "read", "requireId", "split", "Lkotlin/sequences/Sequence;", "write", "value", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DirectoryStorage.kt */
public final class DirectoryStorage implements SessionStorage, Closeable {
    private final File dir;

    public void close() {
    }

    public DirectoryStorage(File file) {
        Intrinsics.checkNotNullParameter(file, "dir");
        this.dir = file;
        DirectoryStorageKt.mkdirsOrFail(file);
    }

    public Object write(String str, String str2, Continuation<? super Unit> continuation) {
        requireId(str);
        File fileOf = fileOf(str);
        File parentFile = fileOf.getParentFile();
        if (parentFile != null) {
            DirectoryStorageKt.mkdirsOrFail(parentFile);
        }
        FilesKt.writeText$default(fileOf, str2, (Charset) null, 2, (Object) null);
        return Unit.INSTANCE;
    }

    public Object read(String str, Continuation<? super String> continuation) {
        requireId(str);
        try {
            File fileOf = fileOf(str);
            File parentFile = fileOf.getParentFile();
            if (parentFile != null) {
                DirectoryStorageKt.mkdirsOrFail(parentFile);
            }
            String str2 = null;
            String readText$default = FilesKt.readText$default(fileOf, (Charset) null, 1, (Object) null);
            if (readText$default.length() > 0) {
                str2 = readText$default;
            }
            if (str2 != null) {
                return str2;
            }
            throw new IllegalStateException("Failed to read stored session from " + fileOf);
        } catch (FileNotFoundException unused) {
            throw new NoSuchElementException("No session data found for id " + str);
        }
    }

    public Object invalidate(String str, Continuation<? super Unit> continuation) {
        requireId(str);
        try {
            File fileOf = fileOf(str);
            fileOf.delete();
            File parentFile = fileOf.getParentFile();
            if (parentFile != null) {
                DirectoryStorageKt.deleteParentsWhileEmpty(parentFile, this.dir);
            }
            return Unit.INSTANCE;
        } catch (FileNotFoundException unused) {
            throw new NoSuchElementException("No session data found for id " + str);
        }
    }

    private final File fileOf(String str) {
        File file = this.dir;
        Sequence<String> split = split(str);
        String str2 = File.separator;
        Intrinsics.checkNotNullExpressionValue(str2, "separator");
        return new File(file, SequencesKt.joinToString$default(split, str2, (CharSequence) null, ".dat", 0, (CharSequence) null, (Function1) null, 58, (Object) null));
    }

    private final Sequence<String> split(String str) {
        return StringsKt.windowedSequence(str, 2, 2, true);
    }

    private final void requireId(String str) {
        CharSequence charSequence = str;
        if (charSequence.length() != 0) {
            if (StringsKt.indexOfAny$default(charSequence, (Collection) CollectionsKt.listOf("..", "/", "\\", "!", "?", ">", "<", "\u0000"), 0, false, 6, (Object) null) != -1) {
                throw new IllegalArgumentException("Bad session id " + str);
            }
            return;
        }
        throw new IllegalArgumentException("Session id is empty");
    }
}
