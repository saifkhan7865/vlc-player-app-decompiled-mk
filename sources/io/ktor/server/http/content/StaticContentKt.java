package io.ktor.server.http.content;

import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.CreatePluginUtilsKt;
import io.ktor.server.application.RouteScopedPlugin;
import io.ktor.server.routing.Route;
import io.ktor.server.routing.RoutingBuilderKt;
import io.ktor.util.AttributeKey;
import java.io.File;
import java.net.URL;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0016\u0010\u0017\u001a\u00020\b*\u0004\u0018\u00010\b2\u0006\u0010\u0018\u001a\u00020\bH\u0002\u001a\u001a\u0010\u0019\u001a\u0004\u0018\u00010\u0004*\u0004\u0018\u00010\u00042\b\u0010\u001a\u001a\u0004\u0018\u00010\u0004H\u0002\u001a\u0014\u0010\u001b\u001a\u00020\u0002*\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\bH\u0007\u001a\u0014\u0010\u001b\u001a\u00020\u0002*\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u0004H\u0007\u001a \u0010\u001d\u001a\u00020\u0002*\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u00042\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0004H\u0007\u001a\u001c\u0010\u0018\u001a\u00020\u0002*\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\bH\u0007\u001a\u001e\u0010\u0018\u001a\u00020\u0002*\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u00042\b\b\u0002\u0010\u001c\u001a\u00020\u0004H\u0007\u001a\u0014\u0010 \u001a\u00020\u0002*\u00020\u000b2\u0006\u0010!\u001a\u00020\bH\u0007\u001a\u0014\u0010 \u001a\u00020\u0002*\u00020\u000b2\u0006\u0010!\u001a\u00020\u0004H\u0007\u001a\n\u0010\"\u001a\u00020#*\u00020$\u001a>\u0010%\u001a\u00020\u0002*\u00020\u000b2\u0014\b\u0002\u0010&\u001a\n\u0012\u0006\b\u0001\u0012\u00020(0'\"\u00020(2\u0017\u0010)\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00020*¢\u0006\u0002\b+¢\u0006\u0002\u0010,\u001a*\u0010\u001e\u001a\u00020\u0002*\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u00042\b\b\u0002\u0010\u001e\u001a\u00020\u00042\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0004H\u0007\u001a\u0018\u0010-\u001a\u00020\u0002*\u00020\u000b2\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0004H\u0007\u001a»\u0001\u0010.\u001a\u00020\u0002*\u00020$2\b\u0010/\u001a\u0004\u0018\u00010\u00042\u0006\u00100\u001a\u00020\b2\u000e\u00101\u001a\n\u0012\u0004\u0012\u00020(\u0018\u0001022\u0012\u00103\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u0002040*2\u0018\u00105\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u000206020*2(\u00107\u001a$\b\u0001\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020$\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000209\u0012\u0006\u0012\u0004\u0018\u00010:082\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020#0*2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u0004022\b\u0010=\u001a\u0004\u0018\u00010\u0004H@ø\u0001\u0000¢\u0006\u0002\u0010>\u001a½\u0001\u0010?\u001a\u00020\u0002*\u00020$2\b\u0010/\u001a\u0004\u0018\u00010\u00042\b\u0010@\u001a\u0004\u0018\u00010\u00042\u000e\u00101\u001a\n\u0012\u0004\u0012\u00020(\u0018\u0001022\u0012\u00103\u001a\u000e\u0012\u0004\u0012\u00020A\u0012\u0004\u0012\u0002040*2\u0018\u00105\u001a\u0014\u0012\u0004\u0012\u00020A\u0012\n\u0012\b\u0012\u0004\u0012\u000206020*2(\u0010B\u001a$\b\u0001\u0012\u0004\u0012\u00020A\u0012\u0004\u0012\u00020$\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000209\u0012\u0006\u0012\u0004\u0018\u00010:082\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u00020A\u0012\u0004\u0012\u00020#0*2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u0004022\b\u0010=\u001a\u0004\u0018\u00010\u0004H@ø\u0001\u0000¢\u0006\u0002\u0010C\u001a%\u0010D\u001a\u00020\u000b*\u00020\u000b2\u0017\u0010)\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00020*¢\u0006\u0002\b+H\u0007\u001a-\u0010D\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u00042\u0017\u0010)\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00020*¢\u0006\u0002\b+H\u0007\u001aM\u0010E\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010F\u001a\u00020#2'\u0010G\u001a#\b\u0001\u0012\u0004\u0012\u00020$\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000209\u0012\u0006\u0012\u0004\u0018\u00010:0H¢\u0006\u0002\b+H\u0002ø\u0001\u0000¢\u0006\u0002\u0010I\u001aG\u0010J\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u00100\u001a\u00020\b2\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u00042\u001f\b\u0002\u0010K\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0L\u0012\u0004\u0012\u00020\u00020*¢\u0006\u0002\b+\u001aI\u0010M\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u00042\b\u0010@\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u00042\u001f\b\u0002\u0010K\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020A0L\u0012\u0004\u0012\u00020\u00020*¢\u0006\u0002\b+\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000\"\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0006X\u0004¢\u0006\u0002\n\u0000\"2\u0010\n\u001a\u0004\u0018\u00010\u0004*\u00020\u000b2\b\u0010\t\u001a\u0004\u0018\u00010\u00048F@FX\u000e¢\u0006\u0012\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011\",\u0010\u0012\u001a\u0004\u0018\u00010\b*\u00020\u000b2\b\u0010\t\u001a\u0004\u0018\u00010\b8F@FX\u000e¢\u0006\f\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016\u0002\u0004\n\u0002\b\u0019¨\u0006N"}, d2 = {"StaticContentAutoHead", "Lio/ktor/server/application/RouteScopedPlugin;", "", "pathParameterName", "", "staticBasePackageName", "Lio/ktor/util/AttributeKey;", "staticRootFolderKey", "Ljava/io/File;", "value", "staticBasePackage", "Lio/ktor/server/routing/Route;", "getStaticBasePackage$annotations", "(Lio/ktor/server/routing/Route;)V", "getStaticBasePackage", "(Lio/ktor/server/routing/Route;)Ljava/lang/String;", "setStaticBasePackage", "(Lio/ktor/server/routing/Route;Ljava/lang/String;)V", "staticRootFolder", "getStaticRootFolder", "(Lio/ktor/server/routing/Route;)Ljava/io/File;", "setStaticRootFolder", "(Lio/ktor/server/routing/Route;Ljava/io/File;)V", "combine", "file", "combinePackage", "resourcePackage", "default", "localPath", "defaultResource", "resource", "remotePath", "files", "folder", "isStaticContent", "", "Lio/ktor/server/application/ApplicationCall;", "preCompressed", "types", "", "Lio/ktor/server/http/content/CompressedFileType;", "configure", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Lio/ktor/server/routing/Route;[Lio/ktor/server/http/content/CompressedFileType;Lkotlin/jvm/functions/Function1;)V", "resources", "respondStaticFile", "index", "dir", "compressedTypes", "", "contentType", "Lio/ktor/http/ContentType;", "cacheControl", "Lio/ktor/http/CacheControl;", "modify", "Lkotlin/Function3;", "Lkotlin/coroutines/Continuation;", "", "exclude", "extensions", "defaultPath", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/String;Ljava/io/File;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function1;Ljava/util/List;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "respondStaticResource", "basePackage", "Ljava/net/URL;", "modifier", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function1;Ljava/util/List;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "static", "staticContentRoute", "autoHead", "handler", "Lkotlin/Function2;", "(Lio/ktor/server/routing/Route;Ljava/lang/String;ZLkotlin/jvm/functions/Function2;)Lio/ktor/server/routing/Route;", "staticFiles", "block", "Lio/ktor/server/http/content/StaticContentConfig;", "staticResources", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: StaticContent.kt */
public final class StaticContentKt {
    /* access modifiers changed from: private */
    public static final RouteScopedPlugin<Unit> StaticContentAutoHead = CreatePluginUtilsKt.createRouteScopedPlugin("StaticContentAutoHead", StaticContentKt$StaticContentAutoHead$1.INSTANCE);
    private static final String pathParameterName = "static-content-path-parameter";
    private static final AttributeKey<String> staticBasePackageName = new AttributeKey<>("BasePackage");
    private static final AttributeKey<File> staticRootFolderKey = new AttributeKey<>("BaseFolder");

    @Deprecated(message = "Please use `staticResources` instead")
    public static /* synthetic */ void getStaticBasePackage$annotations(Route route) {
    }

    public static /* synthetic */ Route staticFiles$default(Route route, String str, File file, String str2, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = "index.html";
        }
        if ((i & 8) != 0) {
            function1 = StaticContentKt$staticFiles$1.INSTANCE;
        }
        return staticFiles(route, str, file, str2, function1);
    }

    public static final Route staticFiles(Route route, String str, File file, String str2, Function1<? super StaticContentConfig<File>, Unit> function1) {
        Route route2 = route;
        String str3 = str;
        Function1<? super StaticContentConfig<File>, Unit> function12 = function1;
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(str3, "remotePath");
        File file2 = file;
        Intrinsics.checkNotNullParameter(file2, "dir");
        Intrinsics.checkNotNullParameter(function12, "block");
        StaticContentConfig staticContentConfig = new StaticContentConfig();
        function12.invoke(staticContentConfig);
        return staticContentRoute(route, str3, staticContentConfig.getAutoHeadResponse$ktor_server_core(), new StaticContentKt$staticFiles$2(str2, file2, staticContentConfig.getPreCompressedFileTypes$ktor_server_core(), staticContentConfig.getContentType$ktor_server_core(), staticContentConfig.getCacheControl$ktor_server_core(), staticContentConfig.getModifier$ktor_server_core(), staticContentConfig.getExclude$ktor_server_core(), staticContentConfig.getExtensions$ktor_server_core(), staticContentConfig.getDefaultPath$ktor_server_core(), (Continuation<? super StaticContentKt$staticFiles$2>) null));
    }

    public static /* synthetic */ Route staticResources$default(Route route, String str, String str2, String str3, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = "index.html";
        }
        if ((i & 8) != 0) {
            function1 = StaticContentKt$staticResources$1.INSTANCE;
        }
        return staticResources(route, str, str2, str3, function1);
    }

    public static final Route staticResources(Route route, String str, String str2, String str3, Function1<? super StaticContentConfig<URL>, Unit> function1) {
        Route route2 = route;
        String str4 = str;
        Function1<? super StaticContentConfig<URL>, Unit> function12 = function1;
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(str4, "remotePath");
        Intrinsics.checkNotNullParameter(function12, "block");
        StaticContentConfig staticContentConfig = new StaticContentConfig();
        function12.invoke(staticContentConfig);
        return staticContentRoute(route, str4, staticContentConfig.getAutoHeadResponse$ktor_server_core(), new StaticContentKt$staticResources$2(str3, str2, staticContentConfig.getPreCompressedFileTypes$ktor_server_core(), staticContentConfig.getContentType$ktor_server_core(), staticContentConfig.getCacheControl$ktor_server_core(), staticContentConfig.getModifier$ktor_server_core(), staticContentConfig.getExclude$ktor_server_core(), staticContentConfig.getExtensions$ktor_server_core(), staticContentConfig.getDefaultPath$ktor_server_core(), (Continuation<? super StaticContentKt$staticResources$2>) null));
    }

    public static /* synthetic */ void preCompressed$default(Route route, CompressedFileType[] compressedFileTypeArr, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            compressedFileTypeArr = CompressedFileType.values();
        }
        preCompressed(route, compressedFileTypeArr, function1);
    }

    public static final void preCompressed(Route route, CompressedFileType[] compressedFileTypeArr, Function1<? super Route, Unit> function1) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(compressedFileTypeArr, "types");
        Intrinsics.checkNotNullParameter(function1, "configure");
        List<CompressedFileType> staticContentEncodedTypes = PreCompressedKt.getStaticContentEncodedTypes(route);
        if (staticContentEncodedTypes == null) {
            staticContentEncodedTypes = CollectionsKt.emptyList();
        }
        route.getAttributes().put(PreCompressedKt.getCompressedKey(), CollectionsKt.distinct(CollectionsKt.plus(staticContentEncodedTypes, ArraysKt.asList((T[]) compressedFileTypeArr))));
        function1.invoke(route);
        route.getAttributes().remove(PreCompressedKt.getCompressedKey());
    }

    public static final File getStaticRootFolder(Route route) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        File file = (File) route.getAttributes().getOrNull(staticRootFolderKey);
        if (file != null) {
            return file;
        }
        Route parent = route.getParent();
        if (parent != null) {
            return getStaticRootFolder(parent);
        }
        return null;
    }

    public static final void setStaticRootFolder(Route route, File file) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        if (file != null) {
            route.getAttributes().put(staticRootFolderKey, file);
        } else {
            route.getAttributes().remove(staticRootFolderKey);
        }
    }

    private static final File combine(File file, File file2) {
        return file == null ? file2 : FilesKt.resolve(file, file2);
    }

    @Deprecated(message = "Please use `staticFiles` or `staticResources` instead")
    /* renamed from: static  reason: not valid java name */
    public static final Route m145static(Route route, Function1<? super Route, Unit> function1) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(function1, "configure");
        function1.invoke(route);
        return route;
    }

    @Deprecated(message = "Please use `staticFiles` or `staticResources` instead")
    /* renamed from: static  reason: not valid java name */
    public static final Route m144static(Route route, String str, Function1<? super Route, Unit> function1) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(str, "remotePath");
        Intrinsics.checkNotNullParameter(function1, "configure");
        return RoutingBuilderKt.route(route, str, function1);
    }

    @Deprecated(message = "Please use `staticFiles` instead")
    /* renamed from: default  reason: not valid java name */
    public static final void m143default(Route route, String str) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(str, "localPath");
        m142default(route, new File(str));
    }

    @Deprecated(message = "Please use `staticFiles` instead")
    /* renamed from: default  reason: not valid java name */
    public static final void m142default(Route route, File file) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(file, "localPath");
        RoutingBuilderKt.get(route, new StaticContentKt$default$1(combine(getStaticRootFolder(route), file), PreCompressedKt.getStaticContentEncodedTypes(route), (Continuation<? super StaticContentKt$default$1>) null));
    }

    @Deprecated(message = "Please use `staticFiles` instead")
    public static final void file(Route route, String str, String str2) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(str, "remotePath");
        Intrinsics.checkNotNullParameter(str2, "localPath");
        file(route, str, new File(str2));
    }

    public static /* synthetic */ void file$default(Route route, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = str;
        }
        file(route, str, str2);
    }

    @Deprecated(message = "Please use `staticFiles` instead")
    public static final void file(Route route, String str, File file) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(str, "remotePath");
        Intrinsics.checkNotNullParameter(file, "localPath");
        RoutingBuilderKt.get(route, str, new StaticContentKt$file$1(combine(getStaticRootFolder(route), file), PreCompressedKt.getStaticContentEncodedTypes(route), (Continuation<? super StaticContentKt$file$1>) null));
    }

    @Deprecated(message = "Please use `staticFiles` instead")
    public static final void files(Route route, String str) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(str, "folder");
        files(route, new File(str));
    }

    @Deprecated(message = "Please use `staticFiles` instead")
    public static final void files(Route route, File file) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(file, "folder");
        RoutingBuilderKt.get(route, "{static-content-path-parameter...}", new StaticContentKt$files$1(combine(getStaticRootFolder(route), file), PreCompressedKt.getStaticContentEncodedTypes(route), (Continuation<? super StaticContentKt$files$1>) null));
    }

    public static final String getStaticBasePackage(Route route) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        String str = (String) route.getAttributes().getOrNull(staticBasePackageName);
        if (str != null) {
            return str;
        }
        Route parent = route.getParent();
        if (parent != null) {
            return getStaticBasePackage(parent);
        }
        return null;
    }

    public static final void setStaticBasePackage(Route route, String str) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        if (str != null) {
            route.getAttributes().put(staticBasePackageName, str);
        } else {
            route.getAttributes().remove(staticBasePackageName);
        }
    }

    private static final String combinePackage(String str, String str2) {
        if (str == null) {
            return str2;
        }
        if (str2 == null) {
            return str;
        }
        return str + '.' + str2;
    }

    public static /* synthetic */ void resource$default(Route route, String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = str;
        }
        if ((i & 4) != 0) {
            str3 = null;
        }
        resource(route, str, str2, str3);
    }

    @Deprecated(message = "Please use `staticResources` instead")
    public static final void resource(Route route, String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(str, "remotePath");
        Intrinsics.checkNotNullParameter(str2, "resource");
        RoutingBuilderKt.get(route, str, new StaticContentKt$resource$1(str2, combinePackage(getStaticBasePackage(route), str3), PreCompressedKt.getStaticContentEncodedTypes(route), (Continuation<? super StaticContentKt$resource$1>) null));
    }

    public static /* synthetic */ void resources$default(Route route, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        resources(route, str);
    }

    @Deprecated(message = "Please use `staticResources` instead")
    public static final void resources(Route route, String str) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        RoutingBuilderKt.get(route, "{static-content-path-parameter...}", new StaticContentKt$resources$1(combinePackage(getStaticBasePackage(route), str), PreCompressedKt.getStaticContentEncodedTypes(route), (Continuation<? super StaticContentKt$resources$1>) null));
    }

    public static /* synthetic */ void defaultResource$default(Route route, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        defaultResource(route, str, str2);
    }

    @Deprecated(message = "Please use `staticResources` instead")
    public static final void defaultResource(Route route, String str, String str2) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(str, "resource");
        RoutingBuilderKt.get(route, new StaticContentKt$defaultResource$1(str, combinePackage(getStaticBasePackage(route), str2), PreCompressedKt.getStaticContentEncodedTypes(route), (Continuation<? super StaticContentKt$defaultResource$1>) null));
    }

    public static final boolean isStaticContent(ApplicationCall applicationCall) {
        Intrinsics.checkNotNullParameter(applicationCall, "<this>");
        return applicationCall.getParameters().contains(pathParameterName);
    }

    private static final Route staticContentRoute(Route route, String str, boolean z, Function2<? super ApplicationCall, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return RoutingBuilderKt.route(route, str, new StaticContentKt$staticContentRoute$1(z, function2));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0073, code lost:
        r25 = r3;
        r3 = r0;
        r0 = r1;
        r1 = r25;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x01b5, code lost:
        r9 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x01b6, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x01e2, code lost:
        if (((java.lang.Boolean) r3).booleanValue() == false) goto L_0x01e7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x01e6, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x01e7, code lost:
        r14.L$0 = r7;
        r14.L$1 = r8;
        r14.L$2 = r9;
        r14.L$3 = r10;
        r14.L$4 = r11;
        r14.L$5 = r5;
        r14.L$6 = r1;
        r14.L$7 = r0;
        r14.L$8 = r13;
        r14.L$9 = r2;
        r14.label = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0210, code lost:
        if (io.ktor.server.http.content.PreCompressedKt.respondStaticFile(r7, r2, r9, r10, r11, r5, r14) != r15) goto L_0x0213;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0212, code lost:
        return r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0213, code lost:
        r3 = r0;
        r4 = r1;
        r0 = r2;
        r1 = r13;
        r25 = r11;
        r11 = r7;
        r7 = r25;
        r26 = r10;
        r10 = r8;
        r8 = r26;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0225, code lost:
        if (io.ktor.server.application.ApplicationCallKt.isHandled(r11) == false) goto L_0x022a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0229, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x022a, code lost:
        r2 = r3.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0232, code lost:
        if (r2.hasNext() == false) goto L_0x02c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0234, code lost:
        r6 = new java.io.File(r0.getPath() + '.' + r2.next());
        r14.L$0 = r11;
        r14.L$1 = r10;
        r14.L$2 = r9;
        r14.L$3 = r8;
        r14.L$4 = r7;
        r14.L$5 = r5;
        r14.L$6 = r4;
        r14.L$7 = r1;
        r14.L$8 = r0;
        r14.L$9 = r2;
        r14.L$10 = r6;
        r14.label = 4;
        r3 = respondStaticFile$checkExclude(r4, r11, r6, r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0274, code lost:
        if (r3 != r15) goto L_0x0277;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0276, code lost:
        return r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0277, code lost:
        r25 = r1;
        r1 = r0;
        r0 = r2;
        r2 = r3;
        r3 = r25;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0284, code lost:
        if (((java.lang.Boolean) r2).booleanValue() == false) goto L_0x0289;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0288, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0289, code lost:
        r14.L$0 = r11;
        r14.L$1 = r10;
        r14.L$2 = r9;
        r14.L$3 = r8;
        r14.L$4 = r7;
        r14.L$5 = r5;
        r14.L$6 = r4;
        r14.L$7 = r3;
        r14.L$8 = r1;
        r14.L$9 = r0;
        r14.L$10 = null;
        r14.label = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x02b5, code lost:
        if (io.ktor.server.http.content.PreCompressedKt.respondStaticFile(r11, r6, r9, r8, r7, r5, r14) != r15) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x02b7, code lost:
        return r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x02bc, code lost:
        if (io.ktor.server.application.ApplicationCallKt.isHandled(r11) == false) goto L_0x02c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x02c0, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x02c1, code lost:
        r2 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x02c4, code lost:
        r2 = null;
        r13 = r1;
        r12 = r5;
        r25 = r11;
        r11 = r7;
        r7 = r25;
        r26 = r10;
        r10 = r8;
        r8 = r26;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x02d5, code lost:
        if (io.ktor.server.application.ApplicationCallKt.isHandled(r7) == false) goto L_0x02da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x02d9, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x02da, code lost:
        if (r13 == null) goto L_0x0310;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x02dc, code lost:
        r0 = new java.io.File(r8, r13);
        r14.L$0 = r2;
        r14.L$1 = r2;
        r14.L$2 = r2;
        r14.L$3 = r2;
        r14.L$4 = r2;
        r14.L$5 = r2;
        r14.L$6 = r2;
        r14.L$7 = r2;
        r14.L$8 = r2;
        r14.L$9 = r2;
        r14.label = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x030a, code lost:
        if (io.ktor.server.http.content.PreCompressedKt.respondStaticFile(r7, r0, r9, r10, r11, r12, r14) != r15) goto L_0x030d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x030c, code lost:
        return r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x030f, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0312, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0123  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x003a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object respondStaticFile(io.ktor.server.application.ApplicationCall r28, java.lang.String r29, java.io.File r30, java.util.List<? extends io.ktor.server.http.content.CompressedFileType> r31, kotlin.jvm.functions.Function1<? super java.io.File, io.ktor.http.ContentType> r32, kotlin.jvm.functions.Function1<? super java.io.File, ? extends java.util.List<? extends io.ktor.http.CacheControl>> r33, kotlin.jvm.functions.Function3<? super java.io.File, ? super io.ktor.server.application.ApplicationCall, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r34, kotlin.jvm.functions.Function1<? super java.io.File, java.lang.Boolean> r35, java.util.List<java.lang.String> r36, java.lang.String r37, kotlin.coroutines.Continuation<? super kotlin.Unit> r38) {
        /*
            r7 = r28
            r0 = r29
            r8 = r30
            r9 = r31
            r10 = r32
            r11 = r33
            r12 = r34
            r1 = r35
            r13 = r37
            r2 = r38
            boolean r3 = r2 instanceof io.ktor.server.http.content.StaticContentKt$respondStaticFile$1
            if (r3 == 0) goto L_0x0028
            r3 = r2
            io.ktor.server.http.content.StaticContentKt$respondStaticFile$1 r3 = (io.ktor.server.http.content.StaticContentKt$respondStaticFile$1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L_0x0028
            int r2 = r3.label
            int r2 = r2 - r5
            r3.label = r2
            goto L_0x002d
        L_0x0028:
            io.ktor.server.http.content.StaticContentKt$respondStaticFile$1 r3 = new io.ktor.server.http.content.StaticContentKt$respondStaticFile$1
            r3.<init>(r2)
        L_0x002d:
            r14 = r3
            java.lang.Object r2 = r14.result
            java.lang.Object r15 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r14.label
            r6 = 0
            switch(r3) {
                case 0: goto L_0x014c;
                case 1: goto L_0x0123;
                case 2: goto L_0x00e4;
                case 3: goto L_0x00b7;
                case 4: goto L_0x007b;
                case 5: goto L_0x0047;
                case 6: goto L_0x0042;
                default: goto L_0x003a;
            }
        L_0x003a:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0042:
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x030d
        L_0x0047:
            java.lang.Object r0 = r14.L$9
            java.util.Iterator r0 = (java.util.Iterator) r0
            java.lang.Object r1 = r14.L$8
            java.io.File r1 = (java.io.File) r1
            java.lang.Object r3 = r14.L$7
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r4 = r14.L$6
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4
            java.lang.Object r5 = r14.L$5
            kotlin.jvm.functions.Function3 r5 = (kotlin.jvm.functions.Function3) r5
            java.lang.Object r7 = r14.L$4
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            java.lang.Object r8 = r14.L$3
            kotlin.jvm.functions.Function1 r8 = (kotlin.jvm.functions.Function1) r8
            java.lang.Object r9 = r14.L$2
            java.util.List r9 = (java.util.List) r9
            java.lang.Object r10 = r14.L$1
            java.io.File r10 = (java.io.File) r10
            java.lang.Object r11 = r14.L$0
            io.ktor.server.application.ApplicationCall r11 = (io.ktor.server.application.ApplicationCall) r11
            kotlin.ResultKt.throwOnFailure(r2)
            r2 = r6
        L_0x0073:
            r25 = r3
            r3 = r0
            r0 = r1
            r1 = r25
            goto L_0x02b8
        L_0x007b:
            java.lang.Object r0 = r14.L$10
            java.io.File r0 = (java.io.File) r0
            java.lang.Object r1 = r14.L$9
            java.util.Iterator r1 = (java.util.Iterator) r1
            java.lang.Object r3 = r14.L$8
            java.io.File r3 = (java.io.File) r3
            java.lang.Object r4 = r14.L$7
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r5 = r14.L$6
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
            java.lang.Object r7 = r14.L$5
            kotlin.jvm.functions.Function3 r7 = (kotlin.jvm.functions.Function3) r7
            java.lang.Object r8 = r14.L$4
            kotlin.jvm.functions.Function1 r8 = (kotlin.jvm.functions.Function1) r8
            java.lang.Object r9 = r14.L$3
            kotlin.jvm.functions.Function1 r9 = (kotlin.jvm.functions.Function1) r9
            java.lang.Object r10 = r14.L$2
            java.util.List r10 = (java.util.List) r10
            java.lang.Object r11 = r14.L$1
            java.io.File r11 = (java.io.File) r11
            java.lang.Object r12 = r14.L$0
            io.ktor.server.application.ApplicationCall r12 = (io.ktor.server.application.ApplicationCall) r12
            kotlin.ResultKt.throwOnFailure(r2)
            r6 = r0
            r0 = r1
            r1 = r3
            r3 = r4
            r4 = r5
            r5 = r7
            r7 = r8
            r8 = r9
            r9 = r10
            r10 = r11
            r11 = r12
            goto L_0x027e
        L_0x00b7:
            java.lang.Object r0 = r14.L$9
            java.io.File r0 = (java.io.File) r0
            java.lang.Object r1 = r14.L$8
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r3 = r14.L$7
            java.util.List r3 = (java.util.List) r3
            java.lang.Object r4 = r14.L$6
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4
            java.lang.Object r5 = r14.L$5
            kotlin.jvm.functions.Function3 r5 = (kotlin.jvm.functions.Function3) r5
            java.lang.Object r7 = r14.L$4
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            java.lang.Object r8 = r14.L$3
            kotlin.jvm.functions.Function1 r8 = (kotlin.jvm.functions.Function1) r8
            java.lang.Object r9 = r14.L$2
            java.util.List r9 = (java.util.List) r9
            java.lang.Object r10 = r14.L$1
            java.io.File r10 = (java.io.File) r10
            java.lang.Object r11 = r14.L$0
            io.ktor.server.application.ApplicationCall r11 = (io.ktor.server.application.ApplicationCall) r11
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x0221
        L_0x00e4:
            java.lang.Object r0 = r14.L$9
            java.io.File r0 = (java.io.File) r0
            java.lang.Object r1 = r14.L$8
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r3 = r14.L$7
            java.util.List r3 = (java.util.List) r3
            java.lang.Object r4 = r14.L$6
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4
            java.lang.Object r5 = r14.L$5
            kotlin.jvm.functions.Function3 r5 = (kotlin.jvm.functions.Function3) r5
            java.lang.Object r7 = r14.L$4
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            java.lang.Object r8 = r14.L$3
            kotlin.jvm.functions.Function1 r8 = (kotlin.jvm.functions.Function1) r8
            java.lang.Object r9 = r14.L$2
            java.util.List r9 = (java.util.List) r9
            java.lang.Object r10 = r14.L$1
            java.io.File r10 = (java.io.File) r10
            java.lang.Object r11 = r14.L$0
            io.ktor.server.application.ApplicationCall r11 = (io.ktor.server.application.ApplicationCall) r11
            kotlin.ResultKt.throwOnFailure(r2)
            r13 = r1
            r1 = r4
            r25 = r2
            r2 = r0
            r0 = r3
            r3 = r25
            r26 = r11
            r11 = r7
            r7 = r26
            r27 = r10
            r10 = r8
            r8 = r27
            goto L_0x01dc
        L_0x0123:
            java.lang.Object r0 = r14.L$6
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r1 = r14.L$5
            kotlin.jvm.functions.Function3 r1 = (kotlin.jvm.functions.Function3) r1
            java.lang.Object r3 = r14.L$4
            kotlin.jvm.functions.Function1 r3 = (kotlin.jvm.functions.Function1) r3
            java.lang.Object r4 = r14.L$3
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4
            java.lang.Object r5 = r14.L$2
            java.util.List r5 = (java.util.List) r5
            java.lang.Object r7 = r14.L$1
            java.io.File r7 = (java.io.File) r7
            java.lang.Object r8 = r14.L$0
            io.ktor.server.application.ApplicationCall r8 = (io.ktor.server.application.ApplicationCall) r8
            kotlin.ResultKt.throwOnFailure(r2)
            r13 = r0
            r12 = r1
            r11 = r3
            r10 = r4
            r25 = r8
            r8 = r7
            r7 = r25
            goto L_0x01b5
        L_0x014c:
            kotlin.ResultKt.throwOnFailure(r2)
            io.ktor.http.Parameters r2 = r28.getParameters()
            java.lang.String r3 = "static-content-path-parameter"
            java.util.List r2 = r2.getAll(r3)
            if (r2 == 0) goto L_0x0313
            r16 = r2
            java.lang.Iterable r16 = (java.lang.Iterable) r16
            java.lang.String r2 = java.io.File.separator
            java.lang.String r3 = "separator"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            r17 = r2
            java.lang.CharSequence r17 = (java.lang.CharSequence) r17
            r23 = 62
            r24 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r22 = 0
            java.lang.String r2 = kotlin.collections.CollectionsKt.joinToString$default(r16, r17, r18, r19, r20, r21, r22, r23, r24)
            if (r2 != 0) goto L_0x0180
            goto L_0x0313
        L_0x0180:
            java.io.File r2 = io.ktor.util.PathKt.combineSafe((java.io.File) r8, (java.lang.String) r2)
            boolean r3 = r2.isDirectory()
            if (r0 == 0) goto L_0x01b9
            if (r3 == 0) goto L_0x01b9
            java.io.File r1 = new java.io.File
            r1.<init>(r2, r0)
            r14.L$0 = r7
            r14.L$1 = r8
            r14.L$2 = r9
            r14.L$3 = r10
            r14.L$4 = r11
            r14.L$5 = r12
            r14.L$6 = r13
            r0 = 1
            r14.label = r0
            r0 = r28
            r2 = r31
            r3 = r32
            r4 = r33
            r5 = r34
            r6 = r14
            java.lang.Object r0 = io.ktor.server.http.content.PreCompressedKt.respondStaticFile(r0, r1, r2, r3, r4, r5, r6)
            if (r0 != r15) goto L_0x01b4
            return r15
        L_0x01b4:
            r5 = r9
        L_0x01b5:
            r9 = r5
        L_0x01b6:
            r2 = 0
            goto L_0x02d1
        L_0x01b9:
            if (r3 != 0) goto L_0x01b6
            r14.L$0 = r7
            r14.L$1 = r8
            r14.L$2 = r9
            r14.L$3 = r10
            r14.L$4 = r11
            r14.L$5 = r12
            r14.L$6 = r1
            r0 = r36
            r14.L$7 = r0
            r14.L$8 = r13
            r14.L$9 = r2
            r3 = 2
            r14.label = r3
            java.lang.Object r3 = respondStaticFile$checkExclude(r1, r7, r2, r14)
            if (r3 != r15) goto L_0x01db
            return r15
        L_0x01db:
            r5 = r12
        L_0x01dc:
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
            if (r3 == 0) goto L_0x01e7
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x01e7:
            r14.L$0 = r7
            r14.L$1 = r8
            r14.L$2 = r9
            r14.L$3 = r10
            r14.L$4 = r11
            r14.L$5 = r5
            r14.L$6 = r1
            r14.L$7 = r0
            r14.L$8 = r13
            r14.L$9 = r2
            r3 = 3
            r14.label = r3
            r28 = r7
            r29 = r2
            r30 = r9
            r31 = r10
            r32 = r11
            r33 = r5
            r34 = r14
            java.lang.Object r3 = io.ktor.server.http.content.PreCompressedKt.respondStaticFile(r28, r29, r30, r31, r32, r33, r34)
            if (r3 != r15) goto L_0x0213
            return r15
        L_0x0213:
            r3 = r0
            r4 = r1
            r0 = r2
            r1 = r13
            r25 = r11
            r11 = r7
            r7 = r25
            r26 = r10
            r10 = r8
            r8 = r26
        L_0x0221:
            boolean r2 = io.ktor.server.application.ApplicationCallKt.isHandled(r11)
            if (r2 == 0) goto L_0x022a
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x022a:
            java.util.Iterator r2 = r3.iterator()
        L_0x022e:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x02c4
            java.lang.Object r3 = r2.next()
            java.lang.String r3 = (java.lang.String) r3
            java.io.File r6 = new java.io.File
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = r0.getPath()
            r12.append(r13)
            r13 = 46
            r12.append(r13)
            r12.append(r3)
            java.lang.String r3 = r12.toString()
            r6.<init>(r3)
            r14.L$0 = r11
            r14.L$1 = r10
            r14.L$2 = r9
            r14.L$3 = r8
            r14.L$4 = r7
            r14.L$5 = r5
            r14.L$6 = r4
            r14.L$7 = r1
            r14.L$8 = r0
            r14.L$9 = r2
            r14.L$10 = r6
            r3 = 4
            r14.label = r3
            java.lang.Object r3 = respondStaticFile$checkExclude(r4, r11, r6, r14)
            if (r3 != r15) goto L_0x0277
            return r15
        L_0x0277:
            r25 = r1
            r1 = r0
            r0 = r2
            r2 = r3
            r3 = r25
        L_0x027e:
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L_0x0289
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0289:
            r14.L$0 = r11
            r14.L$1 = r10
            r14.L$2 = r9
            r14.L$3 = r8
            r14.L$4 = r7
            r14.L$5 = r5
            r14.L$6 = r4
            r14.L$7 = r3
            r14.L$8 = r1
            r14.L$9 = r0
            r2 = 0
            r14.L$10 = r2
            r12 = 5
            r14.label = r12
            r28 = r11
            r29 = r6
            r30 = r9
            r31 = r8
            r32 = r7
            r33 = r5
            r34 = r14
            java.lang.Object r6 = io.ktor.server.http.content.PreCompressedKt.respondStaticFile(r28, r29, r30, r31, r32, r33, r34)
            if (r6 != r15) goto L_0x0073
            return r15
        L_0x02b8:
            boolean r6 = io.ktor.server.application.ApplicationCallKt.isHandled(r11)
            if (r6 == 0) goto L_0x02c1
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x02c1:
            r2 = r3
            goto L_0x022e
        L_0x02c4:
            r2 = 0
            r13 = r1
            r12 = r5
            r25 = r11
            r11 = r7
            r7 = r25
            r26 = r10
            r10 = r8
            r8 = r26
        L_0x02d1:
            boolean r0 = io.ktor.server.application.ApplicationCallKt.isHandled(r7)
            if (r0 == 0) goto L_0x02da
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x02da:
            if (r13 == 0) goto L_0x0310
            java.io.File r0 = new java.io.File
            r0.<init>(r8, r13)
            r14.L$0 = r2
            r14.L$1 = r2
            r14.L$2 = r2
            r14.L$3 = r2
            r14.L$4 = r2
            r14.L$5 = r2
            r14.L$6 = r2
            r14.L$7 = r2
            r14.L$8 = r2
            r14.L$9 = r2
            r1 = 6
            r14.label = r1
            r28 = r7
            r29 = r0
            r30 = r9
            r31 = r10
            r32 = r11
            r33 = r12
            r34 = r14
            java.lang.Object r0 = io.ktor.server.http.content.PreCompressedKt.respondStaticFile(r28, r29, r30, r31, r32, r33, r34)
            if (r0 != r15) goto L_0x030d
            return r15
        L_0x030d:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0310:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0313:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.http.content.StaticContentKt.respondStaticFile(io.ktor.server.application.ApplicationCall, java.lang.String, java.io.File, java.util.List, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function1, java.util.List, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object respondStaticFile$checkExclude(kotlin.jvm.functions.Function1<? super java.io.File, java.lang.Boolean> r5, io.ktor.server.application.ApplicationCall r6, java.io.File r7, kotlin.coroutines.Continuation<? super java.lang.Boolean> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.server.http.content.StaticContentKt$respondStaticFile$checkExclude$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ktor.server.http.content.StaticContentKt$respondStaticFile$checkExclude$1 r0 = (io.ktor.server.http.content.StaticContentKt$respondStaticFile$checkExclude$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ktor.server.http.content.StaticContentKt$respondStaticFile$checkExclude$1 r0 = new io.ktor.server.http.content.StaticContentKt$respondStaticFile$checkExclude$1
            r0.<init>(r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0088
        L_0x002a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r8)
            java.lang.Object r5 = r5.invoke(r7)
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 != 0) goto L_0x0047
            r5 = 0
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
            return r5
        L_0x0047:
            io.ktor.http.HttpStatusCode$Companion r5 = io.ktor.http.HttpStatusCode.Companion
            io.ktor.http.HttpStatusCode r5 = r5.getForbidden()
            boolean r7 = r5 instanceof io.ktor.http.content.OutgoingContent
            if (r7 != 0) goto L_0x0070
            boolean r7 = r5 instanceof byte[]
            if (r7 != 0) goto L_0x0070
            io.ktor.server.response.ApplicationResponse r7 = r6.getResponse()
            java.lang.Class<io.ktor.http.HttpStatusCode> r8 = io.ktor.http.HttpStatusCode.class
            kotlin.reflect.KType r8 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r8)
            java.lang.reflect.Type r2 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r8)
            java.lang.Class<io.ktor.http.HttpStatusCode> r4 = io.ktor.http.HttpStatusCode.class
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)
            io.ktor.util.reflect.TypeInfo r8 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r2, r4, r8)
            io.ktor.server.response.ResponseTypeKt.setResponseType(r7, r8)
        L_0x0070:
            io.ktor.server.response.ApplicationResponse r7 = r6.getResponse()
            io.ktor.server.response.ApplicationSendPipeline r7 = r7.getPipeline()
            java.lang.String r8 = "null cannot be cast to non-null type kotlin.Any"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5, r8)
            java.lang.Object r5 = (java.lang.Object) r5
            r0.label = r3
            java.lang.Object r5 = r7.execute(r6, r5, r0)
            if (r5 != r1) goto L_0x0088
            return r1
        L_0x0088:
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.http.content.StaticContentKt.respondStaticFile$checkExclude(kotlin.jvm.functions.Function1, io.ktor.server.application.ApplicationCall, java.io.File, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x00cc  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0158  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x015b  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x016f  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x01c1  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x01c6  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x021e  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x022e A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0029  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object respondStaticResource(io.ktor.server.application.ApplicationCall r29, java.lang.String r30, java.lang.String r31, java.util.List<? extends io.ktor.server.http.content.CompressedFileType> r32, kotlin.jvm.functions.Function1<? super java.net.URL, io.ktor.http.ContentType> r33, kotlin.jvm.functions.Function1<? super java.net.URL, ? extends java.util.List<? extends io.ktor.http.CacheControl>> r34, kotlin.jvm.functions.Function3<? super java.net.URL, ? super io.ktor.server.application.ApplicationCall, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r35, kotlin.jvm.functions.Function1<? super java.net.URL, java.lang.Boolean> r36, java.util.List<java.lang.String> r37, java.lang.String r38, kotlin.coroutines.Continuation<? super kotlin.Unit> r39) {
        /*
            r0 = r39
            boolean r1 = r0 instanceof io.ktor.server.http.content.StaticContentKt$respondStaticResource$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            io.ktor.server.http.content.StaticContentKt$respondStaticResource$1 r1 = (io.ktor.server.http.content.StaticContentKt$respondStaticResource$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            io.ktor.server.http.content.StaticContentKt$respondStaticResource$1 r1 = new io.ktor.server.http.content.StaticContentKt$respondStaticResource$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r11 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r1.label
            r12 = 4
            r13 = 3
            r14 = 2
            r3 = 1
            if (r2 == 0) goto L_0x00cc
            if (r2 == r3) goto L_0x0096
            if (r2 == r14) goto L_0x005f
            if (r2 == r13) goto L_0x003e
            if (r2 != r12) goto L_0x0036
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x025a
        L_0x0036:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x003e:
            java.lang.Object r2 = r1.L$6
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r3 = r1.L$5
            kotlin.jvm.functions.Function3 r3 = (kotlin.jvm.functions.Function3) r3
            java.lang.Object r4 = r1.L$4
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4
            java.lang.Object r5 = r1.L$3
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
            java.lang.Object r6 = r1.L$2
            java.util.List r6 = (java.util.List) r6
            java.lang.Object r7 = r1.L$1
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r8 = r1.L$0
            io.ktor.server.application.ApplicationCall r8 = (io.ktor.server.application.ApplicationCall) r8
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x0213
        L_0x005f:
            java.lang.Object r2 = r1.L$10
            java.util.Iterator r2 = (java.util.Iterator) r2
            java.lang.Object r3 = r1.L$9
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r4 = r1.L$8
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r5 = r1.L$7
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
            java.lang.Object r6 = r1.L$6
            kotlin.jvm.functions.Function3 r6 = (kotlin.jvm.functions.Function3) r6
            java.lang.Object r7 = r1.L$5
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            java.lang.Object r8 = r1.L$4
            kotlin.jvm.functions.Function1 r8 = (kotlin.jvm.functions.Function1) r8
            java.lang.Object r9 = r1.L$3
            java.util.List r9 = (java.util.List) r9
            java.lang.Object r10 = r1.L$2
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r12 = r1.L$1
            java.lang.String r12 = (java.lang.String) r12
            java.lang.Object r13 = r1.L$0
            io.ktor.server.application.ApplicationCall r13 = (io.ktor.server.application.ApplicationCall) r13
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r3
            r3 = r4
            r4 = r7
            r7 = r5
            r5 = r8
            r8 = r13
            goto L_0x01bb
        L_0x0096:
            java.lang.Object r2 = r1.L$10
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r3 = r1.L$9
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r4 = r1.L$8
            java.util.List r4 = (java.util.List) r4
            java.lang.Object r5 = r1.L$7
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
            java.lang.Object r6 = r1.L$6
            kotlin.jvm.functions.Function3 r6 = (kotlin.jvm.functions.Function3) r6
            java.lang.Object r7 = r1.L$5
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            java.lang.Object r8 = r1.L$4
            kotlin.jvm.functions.Function1 r8 = (kotlin.jvm.functions.Function1) r8
            java.lang.Object r9 = r1.L$3
            java.util.List r9 = (java.util.List) r9
            java.lang.Object r10 = r1.L$2
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r12 = r1.L$1
            java.lang.String r12 = (java.lang.String) r12
            java.lang.Object r13 = r1.L$0
            io.ktor.server.application.ApplicationCall r13 = (io.ktor.server.application.ApplicationCall) r13
            kotlin.ResultKt.throwOnFailure(r0)
            r28 = r13
            r13 = r12
            r12 = r28
            goto L_0x0152
        L_0x00cc:
            kotlin.ResultKt.throwOnFailure(r0)
            io.ktor.http.Parameters r0 = r29.getParameters()
            java.lang.String r2 = "static-content-path-parameter"
            java.util.List r0 = r0.getAll(r2)
            if (r0 == 0) goto L_0x0260
            r17 = r0
            java.lang.Iterable r17 = (java.lang.Iterable) r17
            java.lang.String r0 = java.io.File.separator
            java.lang.String r2 = "separator"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)
            r18 = r0
            java.lang.CharSequence r18 = (java.lang.CharSequence) r18
            r24 = 62
            r25 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            java.lang.String r0 = kotlin.collections.CollectionsKt.joinToString$default(r17, r18, r19, r20, r21, r22, r23, r24, r25)
            if (r0 != 0) goto L_0x0100
            goto L_0x0260
        L_0x0100:
            r12 = r29
            r1.L$0 = r12
            r13 = r30
            r1.L$1 = r13
            r10 = r31
            r1.L$2 = r10
            r9 = r32
            r1.L$3 = r9
            r8 = r33
            r1.L$4 = r8
            r7 = r34
            r1.L$5 = r7
            r6 = r35
            r1.L$6 = r6
            r5 = r36
            r1.L$7 = r5
            r4 = r37
            r1.L$8 = r4
            r2 = r38
            r1.L$9 = r2
            r1.L$10 = r0
            r1.label = r3
            r2 = r29
            r3 = r0
            r4 = r31
            r5 = r32
            r6 = r33
            r8 = r35
            r9 = r36
            r10 = r1
            java.lang.Object r2 = io.ktor.server.http.content.PreCompressedKt.respondStaticResource(r2, r3, r4, r5, r6, r7, r8, r9, r10)
            if (r2 != r11) goto L_0x0141
            return r11
        L_0x0141:
            r10 = r31
            r9 = r32
            r8 = r33
            r7 = r34
            r6 = r35
            r5 = r36
            r4 = r37
            r3 = r38
            r2 = r0
        L_0x0152:
            boolean r0 = io.ktor.server.application.ApplicationCallKt.isHandled(r12)
            if (r0 == 0) goto L_0x015b
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x015b:
            java.util.Iterator r0 = r4.iterator()
            r4 = r7
            r7 = r5
            r5 = r8
            r8 = r12
            r12 = r13
            r28 = r2
            r2 = r0
            r0 = r28
        L_0x0169:
            boolean r13 = r2.hasNext()
            if (r13 == 0) goto L_0x01c4
            java.lang.Object r13 = r2.next()
            java.lang.String r13 = (java.lang.String) r13
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            r15.append(r0)
            r14 = 46
            r15.append(r14)
            r15.append(r13)
            java.lang.String r13 = r15.toString()
            r1.L$0 = r8
            r1.L$1 = r12
            r1.L$2 = r10
            r1.L$3 = r9
            r1.L$4 = r5
            r1.L$5 = r4
            r1.L$6 = r6
            r1.L$7 = r7
            r1.L$8 = r3
            r1.L$9 = r0
            r1.L$10 = r2
            r14 = 2
            r1.label = r14
            r29 = r8
            r30 = r13
            r31 = r10
            r32 = r9
            r33 = r5
            r34 = r4
            r35 = r6
            r36 = r7
            r37 = r1
            java.lang.Object r13 = io.ktor.server.http.content.PreCompressedKt.respondStaticResource(r29, r30, r31, r32, r33, r34, r35, r36, r37)
            if (r13 != r11) goto L_0x01bb
            return r11
        L_0x01bb:
            boolean r13 = io.ktor.server.application.ApplicationCallKt.isHandled(r8)
            if (r13 == 0) goto L_0x0169
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x01c4:
            if (r12 == 0) goto L_0x021e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r0 = java.io.File.separator
            r2.append(r0)
            r2.append(r12)
            java.lang.String r18 = r2.toString()
            r1.L$0 = r8
            r1.L$1 = r10
            r1.L$2 = r9
            r1.L$3 = r5
            r1.L$4 = r4
            r1.L$5 = r6
            r1.L$6 = r3
            r0 = 0
            r1.L$7 = r0
            r1.L$8 = r0
            r1.L$9 = r0
            r1.L$10 = r0
            r0 = 3
            r1.label = r0
            r24 = 0
            r26 = 64
            r27 = 0
            r17 = r8
            r19 = r10
            r20 = r9
            r21 = r5
            r22 = r4
            r23 = r6
            r25 = r1
            java.lang.Object r0 = io.ktor.server.http.content.PreCompressedKt.respondStaticResource$default(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27)
            if (r0 != r11) goto L_0x020f
            return r11
        L_0x020f:
            r2 = r3
            r3 = r6
            r6 = r9
            r7 = r10
        L_0x0213:
            r13 = r2
            r18 = r3
            r17 = r4
            r16 = r5
            r15 = r6
            r14 = r7
            r12 = r8
            goto L_0x0228
        L_0x021e:
            r13 = r3
            r17 = r4
            r16 = r5
            r18 = r6
            r12 = r8
            r15 = r9
            r14 = r10
        L_0x0228:
            boolean r0 = io.ktor.server.application.ApplicationCallKt.isHandled(r12)
            if (r0 != 0) goto L_0x025d
            if (r13 != 0) goto L_0x0231
            goto L_0x025d
        L_0x0231:
            r0 = 0
            r1.L$0 = r0
            r1.L$1 = r0
            r1.L$2 = r0
            r1.L$3 = r0
            r1.L$4 = r0
            r1.L$5 = r0
            r1.L$6 = r0
            r1.L$7 = r0
            r1.L$8 = r0
            r1.L$9 = r0
            r1.L$10 = r0
            r0 = 4
            r1.label = r0
            r19 = 0
            r21 = 64
            r22 = 0
            r20 = r1
            java.lang.Object r0 = io.ktor.server.http.content.PreCompressedKt.respondStaticResource$default(r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)
            if (r0 != r11) goto L_0x025a
            return r11
        L_0x025a:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x025d:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0260:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.http.content.StaticContentKt.respondStaticResource(io.ktor.server.application.ApplicationCall, java.lang.String, java.lang.String, java.util.List, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function1, java.util.List, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
