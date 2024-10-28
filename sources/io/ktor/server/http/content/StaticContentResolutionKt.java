package io.ktor.server.http.content;

import io.ktor.http.CodecsKt;
import io.ktor.http.ContentType;
import io.ktor.http.content.OutgoingContent;
import io.ktor.server.application.Application;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.plugins.BadRequestException;
import io.ktor.server.util.PathsKt;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.videolan.vlc.ArtworkProvider;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u001a\u0010\u0004\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0006\u001a\u00020\u0003H\u0002\u001a.\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0002\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00032\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\f0\u000bH\u0007\u001a\f\u0010\r\u001a\u00020\u0003*\u00020\u0003H\u0000\u001aL\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\b\u0018\u00010\u000f*\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\f0\u000bH\u0000\u001a@\u0010\u000e\u001a\u0004\u0018\u00010\b*\u00020\u00132\u0006\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f0\u000b¨\u0006\u0014"}, d2 = {"findContainingJarFile", "Ljava/io/File;", "url", "", "normalisedPath", "resourcePackage", "path", "resourceClasspathResource", "Lio/ktor/http/content/OutgoingContent$ReadChannelContent;", "Ljava/net/URL;", "mimeResolve", "Lkotlin/Function1;", "Lio/ktor/http/ContentType;", "extension", "resolveResource", "Lkotlin/Pair;", "Lio/ktor/server/application/Application;", "classLoader", "Ljava/lang/ClassLoader;", "Lio/ktor/server/application/ApplicationCall;", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: StaticContentResolution.kt */
public final class StaticContentResolutionKt {
    public static /* synthetic */ OutgoingContent.ReadChannelContent resolveResource$default(ApplicationCall applicationCall, String str, String str2, ClassLoader classLoader, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            classLoader = applicationCall.getApplication().getEnvironment().getClassLoader();
        }
        if ((i & 8) != 0) {
            function1 = StaticContentResolutionKt$resolveResource$1.INSTANCE;
        }
        return resolveResource(applicationCall, str, str2, classLoader, (Function1<? super String, ContentType>) function1);
    }

    public static final OutgoingContent.ReadChannelContent resolveResource(ApplicationCall applicationCall, String str, String str2, ClassLoader classLoader, Function1<? super String, ContentType> function1) {
        Intrinsics.checkNotNullParameter(applicationCall, "<this>");
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(classLoader, "classLoader");
        Intrinsics.checkNotNullParameter(function1, "mimeResolve");
        if (!StringsKt.endsWith$default(str, "/", false, 2, (Object) null) && !StringsKt.endsWith$default(str, "\\", false, 2, (Object) null)) {
            String normalisedPath = normalisedPath(str2, str);
            Enumeration<URL> resources = classLoader.getResources(normalisedPath);
            Intrinsics.checkNotNullExpressionValue(resources, "classLoader.getResources(normalizedPath)");
            for (T t : SequencesKt.asSequence(CollectionsKt.iterator(resources))) {
                Intrinsics.checkNotNullExpressionValue(t, RtspHeaders.Values.URL);
                OutgoingContent.ReadChannelContent resourceClasspathResource = resourceClasspathResource(t, normalisedPath, new StaticContentResolutionKt$resolveResource$2(function1));
                if (resourceClasspathResource != null) {
                    return resourceClasspathResource;
                }
            }
        }
        return null;
    }

    public static /* synthetic */ Pair resolveResource$default(Application application, String str, String str2, ClassLoader classLoader, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            classLoader = application.getEnvironment().getClassLoader();
        }
        return resolveResource(application, str, str2, classLoader, (Function1<? super URL, ContentType>) function1);
    }

    public static final Pair<URL, OutgoingContent.ReadChannelContent> resolveResource(Application application, String str, String str2, ClassLoader classLoader, Function1<? super URL, ContentType> function1) {
        Intrinsics.checkNotNullParameter(application, "<this>");
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(classLoader, "classLoader");
        Intrinsics.checkNotNullParameter(function1, "mimeResolve");
        if (!StringsKt.endsWith$default(str, "/", false, 2, (Object) null) && !StringsKt.endsWith$default(str, "\\", false, 2, (Object) null)) {
            String normalisedPath = normalisedPath(str2, str);
            Enumeration<URL> resources = classLoader.getResources(normalisedPath);
            Intrinsics.checkNotNullExpressionValue(resources, "classLoader.getResources(normalizedPath)");
            for (T t : SequencesKt.asSequence(CollectionsKt.iterator(resources))) {
                Intrinsics.checkNotNullExpressionValue(t, RtspHeaders.Values.URL);
                OutgoingContent.ReadChannelContent resourceClasspathResource = resourceClasspathResource(t, normalisedPath, function1);
                if (resourceClasspathResource != null) {
                    return TuplesKt.to(t, resourceClasspathResource);
                }
            }
        }
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: io.ktor.server.http.content.LocalFileContent} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: io.ktor.server.http.content.LocalFileContent} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: io.ktor.server.http.content.LocalFileContent} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: io.ktor.server.http.content.LocalFileContent} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: io.ktor.server.http.content.LocalFileContent} */
    /* JADX WARNING: Multi-variable type inference failed */
    @io.ktor.util.InternalAPI
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final io.ktor.http.content.OutgoingContent.ReadChannelContent resourceClasspathResource(java.net.URL r8, java.lang.String r9, kotlin.jvm.functions.Function1<? super java.net.URL, io.ktor.http.ContentType> r10) {
        /*
            java.lang.String r0 = "url"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            java.lang.String r0 = "path"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            java.lang.String r0 = "mimeResolve"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            java.lang.String r0 = r8.getProtocol()
            r1 = 0
            if (r0 == 0) goto L_0x00aa
            int r2 = r0.hashCode()
            switch(r2) {
                case -341064690: goto L_0x0093;
                case 104987: goto L_0x005d;
                case 105516: goto L_0x0054;
                case 3143036: goto L_0x001f;
                default: goto L_0x001d;
            }
        L_0x001d:
            goto L_0x00aa
        L_0x001f:
            java.lang.String r9 = "file"
            boolean r9 = r0.equals(r9)
            if (r9 != 0) goto L_0x0029
            goto L_0x00aa
        L_0x0029:
            java.io.File r9 = new java.io.File
            java.lang.String r2 = r8.getPath()
            java.lang.String r0 = "url.path"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)
            r6 = 7
            r7 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            java.lang.String r0 = io.ktor.http.CodecsKt.decodeURLPart$default(r2, r3, r4, r5, r6, r7)
            r9.<init>(r0)
            boolean r0 = r9.isFile()
            if (r0 == 0) goto L_0x0051
            io.ktor.server.http.content.LocalFileContent r1 = new io.ktor.server.http.content.LocalFileContent
            java.lang.Object r8 = r10.invoke(r8)
            io.ktor.http.ContentType r8 = (io.ktor.http.ContentType) r8
            r1.<init>(r9, r8)
        L_0x0051:
            io.ktor.http.content.OutgoingContent$ReadChannelContent r1 = (io.ktor.http.content.OutgoingContent.ReadChannelContent) r1
            goto L_0x00aa
        L_0x0054:
            java.lang.String r9 = "jrt"
            boolean r9 = r0.equals(r9)
            if (r9 != 0) goto L_0x009c
            goto L_0x00aa
        L_0x005d:
            java.lang.String r2 = "jar"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0066
            goto L_0x00aa
        L_0x0066:
            r0 = 0
            r2 = 2
            java.lang.String r3 = "/"
            boolean r0 = kotlin.text.StringsKt.endsWith$default(r9, r3, r0, r2, r1)
            if (r0 == 0) goto L_0x0071
            goto L_0x0090
        L_0x0071:
            java.lang.String r0 = r8.toString()
            java.lang.String r2 = "url.toString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)
            java.io.File r0 = findContainingJarFile(r0)
            io.ktor.server.http.content.JarFileContent r2 = new io.ktor.server.http.content.JarFileContent
            java.lang.Object r8 = r10.invoke(r8)
            io.ktor.http.ContentType r8 = (io.ktor.http.ContentType) r8
            r2.<init>((java.io.File) r0, (java.lang.String) r9, (io.ktor.http.ContentType) r8)
            boolean r8 = r2.isFile()
            if (r8 == 0) goto L_0x0090
            r1 = r2
        L_0x0090:
            io.ktor.http.content.OutgoingContent$ReadChannelContent r1 = (io.ktor.http.content.OutgoingContent.ReadChannelContent) r1
            goto L_0x00aa
        L_0x0093:
            java.lang.String r9 = "resource"
            boolean r9 = r0.equals(r9)
            if (r9 != 0) goto L_0x009c
            goto L_0x00aa
        L_0x009c:
            io.ktor.http.content.URIFileContent r9 = new io.ktor.http.content.URIFileContent
            java.lang.Object r10 = r10.invoke(r8)
            io.ktor.http.ContentType r10 = (io.ktor.http.ContentType) r10
            r9.<init>(r8, r10)
            r1 = r9
            io.ktor.http.content.OutgoingContent$ReadChannelContent r1 = (io.ktor.http.content.OutgoingContent.ReadChannelContent) r1
        L_0x00aa:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.http.content.StaticContentResolutionKt.resourceClasspathResource(java.net.URL, java.lang.String, kotlin.jvm.functions.Function1):io.ktor.http.content.OutgoingContent$ReadChannelContent");
    }

    public static final File findContainingJarFile(String str) {
        Intrinsics.checkNotNullParameter(str, RtspHeaders.Values.URL);
        if (StringsKt.startsWith$default(str, "jar:file:", false, 2, (Object) null)) {
            int indexOf$default = StringsKt.indexOf$default((CharSequence) str, "!", 9, false, 4, (Object) null);
            if (indexOf$default != -1) {
                String substring = str.substring(9, indexOf$default);
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                return new File(CodecsKt.decodeURLPart$default(substring, 0, 0, (Charset) null, 7, (Object) null));
            }
            throw new IllegalArgumentException(("Jar path requires !/ separator but it is: " + str).toString());
        }
        throw new IllegalArgumentException("Only local jars are supported (jar:file:)");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0064 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.String extension(java.lang.String r9) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            r0 = r9
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r5 = 6
            r6 = 0
            r2 = 47
            r3 = 0
            r4 = 0
            r1 = r0
            int r1 = kotlin.text.StringsKt.lastIndexOf$default((java.lang.CharSequence) r1, (char) r2, (int) r3, (boolean) r4, (int) r5, (java.lang.Object) r6)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r2 = r1
            java.lang.Number r2 = (java.lang.Number) r2
            int r2 = r2.intValue()
            r7 = 0
            r8 = -1
            if (r2 == r8) goto L_0x0023
            goto L_0x0024
        L_0x0023:
            r1 = r7
        L_0x0024:
            if (r1 == 0) goto L_0x002c
            int r1 = r1.intValue()
        L_0x002a:
            r3 = r1
            goto L_0x004e
        L_0x002c:
            r5 = 6
            r6 = 0
            r2 = 92
            r3 = 0
            r4 = 0
            r1 = r0
            int r1 = kotlin.text.StringsKt.lastIndexOf$default((java.lang.CharSequence) r1, (char) r2, (int) r3, (boolean) r4, (int) r5, (java.lang.Object) r6)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r2 = r1
            java.lang.Number r2 = (java.lang.Number) r2
            int r2 = r2.intValue()
            if (r2 == r8) goto L_0x0045
            r7 = r1
        L_0x0045:
            if (r7 == 0) goto L_0x004c
            int r1 = r7.intValue()
            goto L_0x002a
        L_0x004c:
            r1 = 0
            r3 = 0
        L_0x004e:
            r5 = 4
            r6 = 0
            r2 = 46
            r4 = 0
            r1 = r0
            int r0 = kotlin.text.StringsKt.indexOf$default((java.lang.CharSequence) r1, (char) r2, (int) r3, (boolean) r4, (int) r5, (java.lang.Object) r6)
            if (r0 < 0) goto L_0x0064
            java.lang.String r9 = r9.substring(r0)
            java.lang.String r0 = "this as java.lang.String).substring(startIndex)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r0)
            goto L_0x0066
        L_0x0064:
            java.lang.String r9 = ""
        L_0x0066:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.http.content.StaticContentResolutionKt.extension(java.lang.String):java.lang.String");
    }

    private static final String normalisedPath(String str, String str2) {
        List split$default = StringsKt.split$default((CharSequence) str2, new char[]{'/', AbstractJsonLexerKt.STRING_ESC}, false, 0, 6, (Object) null);
        if (!split$default.contains("..")) {
            if (str == null) {
                str = "";
            }
            return CollectionsKt.joinToString$default(PathsKt.normalizePathComponents(CollectionsKt.plus(StringsKt.split$default((CharSequence) str, new char[]{'.', '/', AbstractJsonLexerKt.STRING_ESC}, false, 0, 6, (Object) null), split$default)), "/", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
        }
        throw new BadRequestException("Relative path should not contain path traversing characters: " + str2, (Throwable) null, 2, (DefaultConstructorMarker) null);
    }
}
