package io.ktor.server.http.content;

import io.ktor.http.ContentType;
import io.ktor.http.content.OutgoingContent;
import io.ktor.http.content.VersionsKt;
import io.ktor.util.cio.ByteBufferPoolKt;
import io.ktor.util.cio.InputStreamAdaptersKt;
import io.ktor.utils.io.ByteReadChannel;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.attribute.FileTime;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.Job;
import org.bouncycastle.cms.CMSAttributeTableGenerator;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB\u001d\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\u000bJ\b\u0010&\u001a\u00020'H\u0016R\u0016\u0010\f\u001a\u0004\u0018\u00010\r8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0012\u001a\u00020\u00138FX\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0012\u0010\u0014R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u0016\u001a\u0004\b\u0019\u0010\u001aR\u001d\u0010\u001c\u001a\u0004\u0018\u00010\u001d8BX\u0002¢\u0006\f\n\u0004\b \u0010\u0016\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u000e\u0010#\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%¨\u0006("}, d2 = {"Lio/ktor/server/http/content/JarFileContent;", "Lio/ktor/http/content/OutgoingContent$ReadChannelContent;", "zipFilePath", "Ljava/nio/file/Path;", "resourcePath", "", "contentType", "Lio/ktor/http/ContentType;", "(Ljava/nio/file/Path;Ljava/lang/String;Lio/ktor/http/ContentType;)V", "jarFile", "Ljava/io/File;", "(Ljava/io/File;Ljava/lang/String;Lio/ktor/http/ContentType;)V", "contentLength", "", "getContentLength", "()Ljava/lang/Long;", "getContentType", "()Lio/ktor/http/ContentType;", "isFile", "", "()Z", "isFile$delegate", "Lkotlin/Lazy;", "jar", "Ljava/util/jar/JarFile;", "getJar", "()Ljava/util/jar/JarFile;", "jar$delegate", "jarEntry", "Ljava/util/jar/JarEntry;", "getJarEntry", "()Ljava/util/jar/JarEntry;", "jarEntry$delegate", "getJarFile", "()Ljava/io/File;", "normalized", "getResourcePath", "()Ljava/lang/String;", "readFrom", "Lio/ktor/utils/io/ByteReadChannel;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: JarFileContent.kt */
public final class JarFileContent extends OutgoingContent.ReadChannelContent {
    private final ContentType contentType;
    private final Lazy isFile$delegate;
    private final Lazy jar$delegate;
    private final Lazy jarEntry$delegate;
    private final File jarFile;
    private final String normalized;
    private final String resourcePath;

    public final File getJarFile() {
        return this.jarFile;
    }

    public final String getResourcePath() {
        return this.resourcePath;
    }

    public ContentType getContentType() {
        return this.contentType;
    }

    public JarFileContent(File file, String str, ContentType contentType2) {
        Intrinsics.checkNotNullParameter(file, "jarFile");
        Intrinsics.checkNotNullParameter(str, "resourcePath");
        Intrinsics.checkNotNullParameter(contentType2, CMSAttributeTableGenerator.CONTENT_TYPE);
        this.jarFile = file;
        this.resourcePath = str;
        this.contentType = contentType2;
        String file2 = FilesKt.normalize(new File(str)).toString();
        Intrinsics.checkNotNullExpressionValue(file2, "File(resourcePath).normalize().toString()");
        String replace$default = StringsKt.replace$default(file2, File.separatorChar, '/', false, 4, (Object) null);
        this.normalized = replace$default;
        this.jarEntry$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new JarFileContent$jarEntry$2(this));
        this.jar$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new JarFileContent$jar$2(this));
        this.isFile$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new JarFileContent$isFile$2(this));
        if (!StringsKt.startsWith$default(replace$default, "..", false, 2, (Object) null)) {
            JarEntry jarEntry = getJarEntry();
            if (jarEntry != null) {
                OutgoingContent outgoingContent = this;
                FileTime m = jarEntry.getLastModifiedTime();
                Intrinsics.checkNotNullExpressionValue(m, "it.lastModifiedTime");
                VersionsKt.setVersions(outgoingContent, CollectionsKt.plus(VersionsKt.getVersions(outgoingContent), LastModifiedJavaTimeKt.LastModifiedVersion(m)));
                return;
            }
            return;
        }
        throw new IllegalArgumentException(("Bad resource relative path " + str).toString());
    }

    /* access modifiers changed from: private */
    public final JarEntry getJarEntry() {
        return (JarEntry) this.jarEntry$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public final JarFile getJar() {
        return (JarFile) this.jar$delegate.getValue();
    }

    public final boolean isFile() {
        return ((Boolean) this.isFile$delegate.getValue()).booleanValue();
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public JarFileContent(java.nio.file.Path r2, java.lang.String r3, io.ktor.http.ContentType r4) {
        /*
            r1 = this;
            java.lang.String r0 = "zipFilePath"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "resourcePath"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "contentType"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.io.File r2 = io.ktor.util.NioPathKt$$ExternalSyntheticApiModelOutline0.m((java.nio.file.Path) r2)
            java.lang.String r0 = "zipFilePath.toFile()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)
            r1.<init>((java.io.File) r2, (java.lang.String) r3, (io.ktor.http.ContentType) r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.http.content.JarFileContent.<init>(java.nio.file.Path, java.lang.String, io.ktor.http.ContentType):void");
    }

    public Long getContentLength() {
        JarEntry jarEntry = getJarEntry();
        if (jarEntry != null) {
            return Long.valueOf(jarEntry.getSize());
        }
        return null;
    }

    public ByteReadChannel readFrom() {
        ByteReadChannel byteReadChannel$default;
        InputStream inputStream = getJar().getInputStream(getJarEntry());
        if (inputStream != null && (byteReadChannel$default = InputStreamAdaptersKt.toByteReadChannel$default(inputStream, ByteBufferPoolKt.getKtorDefaultPool(), (CoroutineContext) null, (Job) null, 6, (Object) null)) != null) {
            return byteReadChannel$default;
        }
        throw new IOException("Resource " + this.normalized + " not found");
    }
}
