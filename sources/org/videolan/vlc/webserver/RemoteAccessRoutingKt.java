package org.videolan.vlc.webserver;

import android.content.Context;
import android.content.SharedPreferences;
import io.ktor.server.auth.AuthenticationInterceptorsKt;
import io.ktor.server.auth.OAuth2RequestParameters;
import io.ktor.server.http.content.StaticContentKt;
import io.ktor.server.routing.Route;
import io.ktor.server.routing.RoutingBuilderKt;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import okio.Utf8;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.Artist;
import org.videolan.medialibrary.interfaces.media.Folder;
import org.videolan.medialibrary.interfaces.media.Genre;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.medialibrary.interfaces.media.VideoGroup;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.Settings;
import org.videolan.tools.livedata.LiveDataset;
import org.videolan.vlc.R;
import org.videolan.vlc.providers.BrowserProvider;
import org.videolan.vlc.webserver.RemoteAccessRoutingKt$format$2;
import org.videolan.vlc.webserver.RemoteAccessServer;

@Metadata(d1 = {"\u0000\u0001\n\u0000\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0001\u001a\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H@¢\u0006\u0002\u0010\t\u001a^\u0010\n\u001a<\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0007\u0012,\u0012*\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\u000b0\rj\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\u000b`\u00100\u000b2\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u0014H@¢\u0006\u0002\u0010\u0015\u001aD\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u00170\rj\b\u0012\u0004\u0012\u00020\u0017`\u00102\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u00142\u0006\u0010\u001a\u001a\u00020\u001bH@¢\u0006\u0002\u0010\u001c\u001a.\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u0014H\u0002\u001a\u001a\u0010!\u001a\u00020\u001e*\u00020\"2\u0006\u0010#\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020 \u001a\n\u0010$\u001a\u00020\u0017*\u00020%\u001a\u0012\u0010$\u001a\u00020\u0017*\u00020&2\u0006\u0010#\u001a\u00020\u0019\u001a\u0012\u0010$\u001a\u00020\u0017*\u00020'2\u0006\u0010\u0018\u001a\u00020\u0019\u001a\u0012\u0010$\u001a\u00020\u0017*\u00020(2\u0006\u0010#\u001a\u00020\u0019\u001a\u0014\u0010$\u001a\u00020\u0017*\u00020)2\b\b\u0002\u0010*\u001a\u00020\u000f\u001a\u0012\u0010$\u001a\u00020\u0017*\u00020+2\u0006\u0010#\u001a\u00020\u0019\u001a\u0012\u0010$\u001a\u00020\u0017*\u00020,2\u0006\u0010\u0018\u001a\u00020\u0019\"\u001b\u0010\u0000\u001a\u00020\u00018BX\u0002¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0002\u0010\u0003¨\u0006-"}, d2 = {"format", "org/videolan/vlc/webserver/RemoteAccessRoutingKt$format$2$1", "getFormat", "()Lorg/videolan/vlc/webserver/RemoteAccessRoutingKt$format$2$1;", "format$delegate", "Lkotlin/Lazy;", "getLogsFiles", "", "Lorg/videolan/vlc/webserver/LogFile;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMediaFromProvider", "Lkotlin/Pair;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "Ljava/util/ArrayList;", "", "", "Lkotlin/collections/ArrayList;", "provider", "Lorg/videolan/vlc/providers/BrowserProvider;", "dataset", "Lorg/videolan/tools/livedata/LiveDataset;", "(Lorg/videolan/vlc/providers/BrowserProvider;Lorg/videolan/tools/livedata/LiveDataset;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getProviderContent", "Lorg/videolan/vlc/webserver/RemoteAccessServer$PlayQueueItem;", "context", "Landroid/content/Context;", "idPrefix", "", "(Landroid/content/Context;Lorg/videolan/vlc/providers/BrowserProvider;Lorg/videolan/tools/livedata/LiveDataset;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getProviderDescriptions", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "setupRouting", "Lio/ktor/server/routing/Route;", "appContext", "toPlayQueueItem", "Lorg/videolan/medialibrary/interfaces/media/Album;", "Lorg/videolan/medialibrary/interfaces/media/Artist;", "Lorg/videolan/medialibrary/interfaces/media/Folder;", "Lorg/videolan/medialibrary/interfaces/media/Genre;", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "defaultArtist", "Lorg/videolan/medialibrary/interfaces/media/Playlist;", "Lorg/videolan/medialibrary/interfaces/media/VideoGroup;", "webserver_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: RemoteAccessRouting.kt */
public final class RemoteAccessRoutingKt {
    private static final Lazy format$delegate = LazyKt.lazy(RemoteAccessRoutingKt$format$2.INSTANCE);

    /* access modifiers changed from: private */
    public static final RemoteAccessRoutingKt$format$2.AnonymousClass1 getFormat() {
        return (RemoteAccessRoutingKt$format$2.AnonymousClass1) format$delegate.getValue();
    }

    public static final void setupRouting(Route route, Context context, CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(context, "appContext");
        Intrinsics.checkNotNullParameter(coroutineScope, OAuth2RequestParameters.Scope);
        SharedPreferences sharedPreferences = (SharedPreferences) Settings.INSTANCE.getInstance(context);
        StaticContentKt.staticFiles$default(route, "", new File(RemoteAccessServer.Companion.getServerFiles(context)), (String) null, (Function1) null, 12, (Object) null);
        RoutingBuilderKt.post(route, "/code", new RemoteAccessRoutingKt$setupRouting$1(context, coroutineScope, (Continuation<? super RemoteAccessRoutingKt$setupRouting$1>) null));
        RoutingBuilderKt.post(route, "/verify-code", new RemoteAccessRoutingKt$setupRouting$2(context, sharedPreferences, coroutineScope, (Continuation<? super RemoteAccessRoutingKt$setupRouting$2>) null));
        RoutingBuilderKt.get(route, "/", new RemoteAccessRoutingKt$setupRouting$3((Continuation<? super RemoteAccessRoutingKt$setupRouting$3>) null));
        RoutingBuilderKt.get(route, "/index.html", new RemoteAccessRoutingKt$setupRouting$4(context, (Continuation<? super RemoteAccessRoutingKt$setupRouting$4>) null));
        RoutingBuilderKt.post(route, "/upload-media", new RemoteAccessRoutingKt$setupRouting$5(sharedPreferences, (Continuation<? super RemoteAccessRoutingKt$setupRouting$5>) null));
        RoutingBuilderKt.get(route, "/download-logfile", new RemoteAccessRoutingKt$setupRouting$6((Continuation<? super RemoteAccessRoutingKt$setupRouting$6>) null));
        RoutingBuilderKt.get(route, "/logfile-list", new RemoteAccessRoutingKt$setupRouting$7(sharedPreferences, (Continuation<? super RemoteAccessRoutingKt$setupRouting$7>) null));
        RoutingBuilderKt.get(route, "/translation", new RemoteAccessRoutingKt$setupRouting$8(context, (Continuation<? super RemoteAccessRoutingKt$setupRouting$8>) null));
        RoutingBuilderKt.get(route, "/secure-url", new RemoteAccessRoutingKt$setupRouting$9(context, (Continuation<? super RemoteAccessRoutingKt$setupRouting$9>) null));
        RoutingBuilderKt.get(route, "/icon", new RemoteAccessRoutingKt$setupRouting$10(context, (Continuation<? super RemoteAccessRoutingKt$setupRouting$10>) null));
        RoutingBuilderKt.post(route, "/logs", new RemoteAccessRoutingKt$setupRouting$11(context, (Continuation<? super RemoteAccessRoutingKt$setupRouting$11>) null));
        AuthenticationInterceptorsKt.authenticate(route, new String[]{"user_session"}, RemoteAccessServer.Companion.getByPassAuth(), (Function1<? super Route, Unit>) new RemoteAccessRoutingKt$setupRouting$12(sharedPreferences, context, coroutineScope));
    }

    /* access modifiers changed from: private */
    public static final Object getLogsFiles(Continuation<? super List<LogFile>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new RemoteAccessRoutingKt$getLogsFiles$2((Continuation<? super RemoteAccessRoutingKt$getLogsFiles$2>) null), continuation);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v4, resolved type: org.videolan.tools.livedata.LiveDataset<org.videolan.medialibrary.media.MediaLibraryItem>} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x008c A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object getMediaFromProvider(org.videolan.vlc.providers.BrowserProvider r11, org.videolan.tools.livedata.LiveDataset<org.videolan.medialibrary.media.MediaLibraryItem> r12, kotlin.coroutines.Continuation<? super kotlin.Pair<? extends java.util.List<? extends org.videolan.medialibrary.media.MediaLibraryItem>, ? extends java.util.ArrayList<kotlin.Pair<java.lang.Integer, java.lang.String>>>> r13) {
        /*
            boolean r0 = r13 instanceof org.videolan.vlc.webserver.RemoteAccessRoutingKt$getMediaFromProvider$1
            if (r0 == 0) goto L_0x0014
            r0 = r13
            org.videolan.vlc.webserver.RemoteAccessRoutingKt$getMediaFromProvider$1 r0 = (org.videolan.vlc.webserver.RemoteAccessRoutingKt$getMediaFromProvider$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.webserver.RemoteAccessRoutingKt$getMediaFromProvider$1 r0 = new org.videolan.vlc.webserver.RemoteAccessRoutingKt$getMediaFromProvider$1
            r0.<init>(r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x004a
            if (r2 == r4) goto L_0x003d
            if (r2 != r3) goto L_0x0035
            java.lang.Object r11 = r0.L$1
            java.util.ArrayList r11 = (java.util.ArrayList) r11
            java.lang.Object r12 = r0.L$0
            org.videolan.tools.livedata.LiveDataset r12 = (org.videolan.tools.livedata.LiveDataset) r12
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x008e
        L_0x0035:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x003d:
            java.lang.Object r11 = r0.L$1
            r12 = r11
            org.videolan.tools.livedata.LiveDataset r12 = (org.videolan.tools.livedata.LiveDataset) r12
            java.lang.Object r11 = r0.L$0
            org.videolan.vlc.providers.BrowserProvider r11 = (org.videolan.vlc.providers.BrowserProvider) r11
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x005d
        L_0x004a:
            kotlin.ResultKt.throwOnFailure(r13)
            r13 = r12
            androidx.lifecycle.LiveData r13 = (androidx.lifecycle.LiveData) r13
            r0.L$0 = r11
            r0.L$1 = r12
            r0.label = r4
            java.lang.Object r13 = org.videolan.resources.util.ExtensionsKt.await(r13, r0)
            if (r13 != r1) goto L_0x005d
            return r1
        L_0x005d:
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
            androidx.lifecycle.MutableLiveData r11 = r11.getDescriptionUpdate()
            r7 = r11
            androidx.lifecycle.LiveData r7 = (androidx.lifecycle.LiveData) r7
            kotlinx.coroutines.MainCoroutineDispatcher r11 = kotlinx.coroutines.Dispatchers.getMain()
            kotlinx.coroutines.MainCoroutineDispatcher r11 = r11.getImmediate()
            kotlin.coroutines.CoroutineContext r11 = (kotlin.coroutines.CoroutineContext) r11
            org.videolan.vlc.webserver.RemoteAccessRoutingKt$getMediaFromProvider$$inlined$observeLiveDataUntil$1 r2 = new org.videolan.vlc.webserver.RemoteAccessRoutingKt$getMediaFromProvider$$inlined$observeLiveDataUntil$1
            r8 = 0
            r5 = 5000(0x1388, double:2.4703E-320)
            r4 = r2
            r9 = r13
            r10 = r12
            r4.<init>(r5, r7, r8, r9, r10)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.L$0 = r12
            r0.L$1 = r13
            r0.label = r3
            java.lang.Object r11 = kotlinx.coroutines.BuildersKt.withContext(r11, r2, r0)
            if (r11 != r1) goto L_0x008d
            return r1
        L_0x008d:
            r11 = r13
        L_0x008e:
            kotlin.Pair r13 = new kotlin.Pair
            java.util.List r12 = r12.getList()
            r13.<init>(r12, r11)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.webserver.RemoteAccessRoutingKt.getMediaFromProvider(org.videolan.vlc.providers.BrowserProvider, org.videolan.tools.livedata.LiveDataset, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static final void getProviderDescriptions(Context context, CoroutineScope coroutineScope, BrowserProvider browserProvider, LiveDataset<MediaLibraryItem> liveDataset) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(coroutineScope, Dispatchers.getIO(), (CoroutineStart) null, new RemoteAccessRoutingKt$getProviderDescriptions$1(browserProvider, liveDataset, new ArrayList(), context, coroutineScope, (Continuation<? super RemoteAccessRoutingKt$getProviderDescriptions$1>) null), 2, (Object) null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v36, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v9, resolved type: java.lang.String} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x0224  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0227  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0237  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x023a  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x01ca  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01d8  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object getProviderContent(android.content.Context r38, org.videolan.vlc.providers.BrowserProvider r39, org.videolan.tools.livedata.LiveDataset<org.videolan.medialibrary.media.MediaLibraryItem> r40, long r41, kotlin.coroutines.Continuation<? super java.util.ArrayList<org.videolan.vlc.webserver.RemoteAccessServer.PlayQueueItem>> r43) {
        /*
            r0 = r39
            r1 = r43
            java.lang.String r2 = ""
            boolean r3 = r1 instanceof org.videolan.vlc.webserver.RemoteAccessRoutingKt$getProviderContent$1
            if (r3 == 0) goto L_0x001a
            r3 = r1
            org.videolan.vlc.webserver.RemoteAccessRoutingKt$getProviderContent$1 r3 = (org.videolan.vlc.webserver.RemoteAccessRoutingKt$getProviderContent$1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L_0x001a
            int r1 = r3.label
            int r1 = r1 - r5
            r3.label = r1
            goto L_0x001f
        L_0x001a:
            org.videolan.vlc.webserver.RemoteAccessRoutingKt$getProviderContent$1 r3 = new org.videolan.vlc.webserver.RemoteAccessRoutingKt$getProviderContent$1
            r3.<init>(r1)
        L_0x001f:
            java.lang.Object r1 = r3.result
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r3.label
            r6 = 1
            if (r5 == 0) goto L_0x0048
            if (r5 != r6) goto L_0x0040
            long r4 = r3.J$0
            java.lang.Object r0 = r3.L$1
            org.videolan.vlc.providers.BrowserProvider r0 = (org.videolan.vlc.providers.BrowserProvider) r0
            java.lang.Object r3 = r3.L$0
            android.content.Context r3 = (android.content.Context) r3
            kotlin.ResultKt.throwOnFailure(r1)
            r37 = r3
            r3 = r0
            r0 = r1
            r1 = r37
            goto L_0x0066
        L_0x0040:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0048:
            kotlin.ResultKt.throwOnFailure(r1)
            r1 = r38
            r3.L$0 = r1
            r3.L$1 = r0
            r7 = r41
            r3.J$0 = r7
            r3.label = r6
            r5 = r40
            java.lang.Object r3 = getMediaFromProvider(r0, r5, r3)
            if (r3 != r4) goto L_0x0060
            return r4
        L_0x0060:
            r4 = r7
            r37 = r3
            r3 = r0
            r0 = r37
        L_0x0066:
            r7 = r0
            kotlin.Pair r7 = (kotlin.Pair) r7
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            java.lang.Object r0 = r7.getFirst()
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r9 = r0.iterator()
            r11 = 0
        L_0x0079:
            boolean r0 = r9.hasNext()
            if (r0 == 0) goto L_0x0268
            java.lang.Object r0 = r9.next()
            int r12 = r11 + 1
            if (r11 >= 0) goto L_0x008a
            kotlin.collections.CollectionsKt.throwIndexOverflow()
        L_0x008a:
            r13 = r0
            org.videolan.medialibrary.media.MediaLibraryItem r13 = (org.videolan.medialibrary.media.MediaLibraryItem) r13
            r14 = 3
            boolean r0 = r13 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper     // Catch:{ Exception -> 0x01b0 }
            if (r0 == 0) goto L_0x00da
            r0 = r13
            org.videolan.medialibrary.interfaces.media.MediaWrapper r0 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r0     // Catch:{ Exception -> 0x01b0 }
            int r0 = r0.getType()     // Catch:{ Exception -> 0x01b0 }
            if (r0 == r14) goto L_0x00da
            r0 = r13
            org.videolan.medialibrary.interfaces.media.MediaWrapper r0 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r0     // Catch:{ Exception -> 0x01b0 }
            android.net.Uri r0 = r0.getUri()     // Catch:{ Exception -> 0x01b0 }
            java.lang.String r0 = r0.getScheme()     // Catch:{ Exception -> 0x01b0 }
            boolean r0 = org.videolan.vlc.util.BrowserutilsKt.isSchemeFile(r0)     // Catch:{ Exception -> 0x01b0 }
            if (r0 == 0) goto L_0x00d1
            r0 = r13
            org.videolan.medialibrary.interfaces.media.MediaWrapper r0 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r0     // Catch:{ Exception -> 0x01b0 }
            android.net.Uri r0 = r0.getUri()     // Catch:{ Exception -> 0x01b0 }
            java.lang.String r0 = r0.getPath()     // Catch:{ Exception -> 0x01b0 }
            if (r0 == 0) goto L_0x00c7
            java.io.File r15 = new java.io.File     // Catch:{ Exception -> 0x01b0 }
            r15.<init>(r0)     // Catch:{ Exception -> 0x01b0 }
            long r14 = r15.length()     // Catch:{ Exception -> 0x01b0 }
            java.lang.String r15 = android.text.format.Formatter.formatFileSize(r1, r14)     // Catch:{ Exception -> 0x01b0 }
            goto L_0x00c8
        L_0x00c7:
            r15 = 0
        L_0x00c8:
            if (r15 != 0) goto L_0x00cb
            goto L_0x00d1
        L_0x00cb:
            r17 = r2
            r40 = r7
        L_0x00cf:
            r0 = r15
            goto L_0x00d6
        L_0x00d1:
            r0 = r2
            r17 = r0
            r40 = r7
        L_0x00d6:
            r10 = 1
            r15 = 0
            goto L_0x01ab
        L_0x00da:
            java.lang.Object r0 = r7.getSecond()     // Catch:{ Exception -> 0x01b0 }
            java.lang.Iterable r0 = (java.lang.Iterable) r0     // Catch:{ Exception -> 0x01b0 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ Exception -> 0x01b0 }
        L_0x00e4:
            boolean r14 = r0.hasNext()     // Catch:{ Exception -> 0x01b0 }
            if (r14 == 0) goto L_0x00ff
            java.lang.Object r14 = r0.next()     // Catch:{ Exception -> 0x01b0 }
            r16 = r14
            kotlin.Pair r16 = (kotlin.Pair) r16     // Catch:{ Exception -> 0x01b0 }
            java.lang.Object r16 = r16.getFirst()     // Catch:{ Exception -> 0x01b0 }
            java.lang.Number r16 = (java.lang.Number) r16     // Catch:{ Exception -> 0x01b0 }
            int r15 = r16.intValue()     // Catch:{ Exception -> 0x01b0 }
            if (r15 != r11) goto L_0x00e4
            goto L_0x0100
        L_0x00ff:
            r14 = 0
        L_0x0100:
            kotlin.Pair r14 = (kotlin.Pair) r14     // Catch:{ Exception -> 0x01b0 }
            if (r14 == 0) goto L_0x010c
            java.lang.Object r0 = r14.getSecond()     // Catch:{ Exception -> 0x01b0 }
            r15 = r0
            java.lang.String r15 = (java.lang.String) r15     // Catch:{ Exception -> 0x01b0 }
            goto L_0x010d
        L_0x010c:
            r15 = 0
        L_0x010d:
            r0 = r15
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0     // Catch:{ Exception -> 0x01b0 }
            int r0 = org.videolan.vlc.util.KextensionsKt.getFolderNumber(r0)     // Catch:{ Exception -> 0x01b0 }
            java.lang.CharSequence r15 = (java.lang.CharSequence) r15     // Catch:{ Exception -> 0x01b0 }
            int r14 = org.videolan.vlc.util.KextensionsKt.getFilesNumber(r15)     // Catch:{ Exception -> 0x01b0 }
            if (r0 <= 0) goto L_0x0160
            if (r14 <= 0) goto L_0x0160
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01b0 }
            r15.<init>()     // Catch:{ Exception -> 0x01b0 }
            android.content.res.Resources r10 = r1.getResources()     // Catch:{ Exception -> 0x01b0 }
            int r6 = org.videolan.vlc.R.plurals.subfolders_quantity     // Catch:{ Exception -> 0x01b0 }
            java.lang.Integer r16 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r0)     // Catch:{ Exception -> 0x01b0 }
            r17 = r2
            r40 = r7
            r2 = 1
            java.lang.Object[] r7 = new java.lang.Object[r2]     // Catch:{ Exception -> 0x017f }
            r2 = 0
            r7[r2] = r16     // Catch:{ Exception -> 0x017f }
            java.lang.String r0 = r10.getQuantityString(r6, r0, r7)     // Catch:{ Exception -> 0x017f }
            r15.append(r0)     // Catch:{ Exception -> 0x017f }
            java.lang.String r0 = " · "
            r15.append(r0)     // Catch:{ Exception -> 0x017f }
            android.content.res.Resources r0 = r1.getResources()     // Catch:{ Exception -> 0x017f }
            int r2 = org.videolan.vlc.R.plurals.mediafiles_quantity     // Catch:{ Exception -> 0x017f }
            java.lang.Integer r6 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r14)     // Catch:{ Exception -> 0x017f }
            r7 = 1
            java.lang.Object[] r10 = new java.lang.Object[r7]     // Catch:{ Exception -> 0x017f }
            r7 = 0
            r10[r7] = r6     // Catch:{ Exception -> 0x017f }
            java.lang.String r0 = r0.getQuantityString(r2, r14, r10)     // Catch:{ Exception -> 0x017f }
            r15.append(r0)     // Catch:{ Exception -> 0x017f }
            java.lang.String r15 = r15.toString()     // Catch:{ Exception -> 0x017f }
            goto L_0x00cf
        L_0x0160:
            r17 = r2
            r40 = r7
            if (r14 <= 0) goto L_0x0181
            android.content.res.Resources r0 = r1.getResources()     // Catch:{ Exception -> 0x017f }
            int r2 = org.videolan.vlc.R.plurals.mediafiles_quantity     // Catch:{ Exception -> 0x017f }
            java.lang.Integer r6 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r14)     // Catch:{ Exception -> 0x017f }
            r7 = 1
            java.lang.Object[] r10 = new java.lang.Object[r7]     // Catch:{ Exception -> 0x017f }
            r7 = 0
            r10[r7] = r6     // Catch:{ Exception -> 0x017f }
            java.lang.String r15 = r0.getQuantityString(r2, r14, r10)     // Catch:{ Exception -> 0x017f }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r15)     // Catch:{ Exception -> 0x017f }
            goto L_0x00cf
        L_0x017f:
            r0 = move-exception
            goto L_0x01b5
        L_0x0181:
            if (r0 <= 0) goto L_0x019d
            android.content.res.Resources r2 = r1.getResources()     // Catch:{ Exception -> 0x017f }
            int r6 = org.videolan.vlc.R.plurals.subfolders_quantity     // Catch:{ Exception -> 0x017f }
            java.lang.Integer r7 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r0)     // Catch:{ Exception -> 0x017f }
            r10 = 1
            java.lang.Object[] r14 = new java.lang.Object[r10]     // Catch:{ Exception -> 0x019b }
            r15 = 0
            r14[r15] = r7     // Catch:{ Exception -> 0x01ae }
            java.lang.String r0 = r2.getQuantityString(r6, r0, r14)     // Catch:{ Exception -> 0x01ae }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch:{ Exception -> 0x01ae }
            goto L_0x01ab
        L_0x019b:
            r0 = move-exception
            goto L_0x01b6
        L_0x019d:
            r10 = 1
            r15 = 0
            java.lang.String r0 = r13.getDescription()     // Catch:{ Exception -> 0x01ae }
            if (r0 != 0) goto L_0x01a8
            r0 = r17
            goto L_0x01ab
        L_0x01a8:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch:{ Exception -> 0x01ae }
        L_0x01ab:
            r22 = r0
            goto L_0x01c6
        L_0x01ae:
            r0 = move-exception
            goto L_0x01b7
        L_0x01b0:
            r0 = move-exception
            r17 = r2
            r40 = r7
        L_0x01b5:
            r10 = 1
        L_0x01b6:
            r15 = 0
        L_0x01b7:
            java.lang.Class<org.videolan.vlc.webserver.RemoteAccessServer> r2 = org.videolan.vlc.webserver.RemoteAccessServer.class
            java.lang.String r2 = r0.getMessage()
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            java.lang.String r6 = "RemoteAccessServer"
            android.util.Log.e(r6, r2, r0)
            r22 = r17
        L_0x01c6:
            boolean r0 = r13 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
            if (r0 == 0) goto L_0x01d8
            r2 = r13
            org.videolan.medialibrary.interfaces.media.MediaWrapper r2 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r2
            android.net.Uri r2 = r2.getUri()
            java.lang.String r2 = r2.toString()
        L_0x01d5:
            r28 = r2
            goto L_0x01e8
        L_0x01d8:
            boolean r2 = r13 instanceof org.videolan.medialibrary.media.Storage
            if (r2 == 0) goto L_0x0260
            r2 = r13
            org.videolan.medialibrary.media.Storage r2 = (org.videolan.medialibrary.media.Storage) r2
            android.net.Uri r2 = r2.getUri()
            java.lang.String r2 = r2.toString()
            goto L_0x01d5
        L_0x01e8:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r28)
            boolean r2 = r3 instanceof org.videolan.vlc.providers.FileBrowserProvider
            if (r2 == 0) goto L_0x0211
            java.lang.String r2 = r3.getUrl()
            if (r2 == 0) goto L_0x0207
            java.lang.String r2 = r3.getUrl()
            android.net.Uri r2 = android.net.Uri.parse(r2)
            java.lang.String r2 = r2.getScheme()
            boolean r2 = org.videolan.vlc.util.BrowserutilsKt.isSchemeFile(r2)
            if (r2 == 0) goto L_0x0211
        L_0x0207:
            if (r0 == 0) goto L_0x0211
            r2 = r13
            org.videolan.medialibrary.interfaces.media.MediaWrapper r2 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r2
            java.lang.String r2 = r2.getFileName()
            goto L_0x0215
        L_0x0211:
            java.lang.String r2 = r13.getTitle()
        L_0x0215:
            r21 = r2
            if (r0 == 0) goto L_0x0227
            r0 = r13
            org.videolan.medialibrary.interfaces.media.MediaWrapper r0 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r0
            int r0 = r0.getType()
            r2 = 3
            if (r0 != r2) goto L_0x0224
            goto L_0x0227
        L_0x0224:
            r29 = 0
            goto L_0x0229
        L_0x0227:
            r29 = 1
        L_0x0229:
            org.videolan.vlc.webserver.RemoteAccessServer$PlayQueueItem r0 = new org.videolan.vlc.webserver.RemoteAccessServer$PlayQueueItem
            long r6 = (long) r11
            long r19 = r4 + r6
            kotlin.jvm.internal.Intrinsics.checkNotNull(r21)
            java.lang.String r2 = r13.getArtworkMrl()
            if (r2 != 0) goto L_0x023a
            r25 = r17
            goto L_0x023c
        L_0x023a:
            r25 = r2
        L_0x023c:
            boolean r34 = r13.isFavorite()
            r35 = 3584(0xe00, float:5.022E-42)
            r36 = 0
            r23 = 0
            r26 = 0
            java.lang.String r27 = ""
            r30 = 0
            r32 = 0
            r33 = 0
            r18 = r0
            r18.<init>(r19, r21, r22, r23, r25, r26, r27, r28, r29, r30, r32, r33, r34, r35, r36)
            r8.add(r0)
            r7 = r40
            r11 = r12
            r2 = r17
            r6 = 1
            goto L_0x0079
        L_0x0260:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Unrecognised media type"
            r0.<init>(r1)
            throw r0
        L_0x0268:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.webserver.RemoteAccessRoutingKt.getProviderContent(android.content.Context, org.videolan.vlc.providers.BrowserProvider, org.videolan.tools.livedata.LiveDataset, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final RemoteAccessServer.PlayQueueItem toPlayQueueItem(Album album) {
        String str;
        Intrinsics.checkNotNullParameter(album, "<this>");
        long id = album.getId();
        String title = album.getTitle();
        Intrinsics.checkNotNullExpressionValue(title, "getTitle(...)");
        String albumArtist = album.getAlbumArtist();
        if (albumArtist == null) {
            albumArtist = "";
        }
        long duration = album.getDuration();
        String artworkMrl = album.getArtworkMrl();
        if (artworkMrl == null) {
            str = "";
        } else {
            str = artworkMrl;
        }
        return new RemoteAccessServer.PlayQueueItem(id, title, albumArtist, duration, str, false, "", (String) null, false, 0, false, (String) null, album.isFavorite(), Utf8.MASK_2BYTES, (DefaultConstructorMarker) null);
    }

    public static final RemoteAccessServer.PlayQueueItem toPlayQueueItem(Artist artist, Context context) {
        Intrinsics.checkNotNullParameter(artist, "<this>");
        Intrinsics.checkNotNullParameter(context, "appContext");
        long id = artist.getId();
        String title = artist.getTitle();
        Intrinsics.checkNotNullExpressionValue(title, "getTitle(...)");
        String quantityString = context.getResources().getQuantityString(R.plurals.albums_quantity, artist.getAlbumsCount(), new Object[]{Integer.valueOf(artist.getAlbumsCount())});
        Intrinsics.checkNotNullExpressionValue(quantityString, "getQuantityString(...)");
        String artworkMrl = artist.getArtworkMrl();
        if (artworkMrl == null) {
            artworkMrl = "";
        }
        String str = artworkMrl;
        return new RemoteAccessServer.PlayQueueItem(id, title, quantityString, 0, str, false, "", (String) null, false, 0, false, (String) null, artist.isFavorite(), Utf8.MASK_2BYTES, (DefaultConstructorMarker) null);
    }

    public static final RemoteAccessServer.PlayQueueItem toPlayQueueItem(Genre genre, Context context) {
        Intrinsics.checkNotNullParameter(genre, "<this>");
        Intrinsics.checkNotNullParameter(context, "appContext");
        long id = genre.getId();
        String title = genre.getTitle();
        Intrinsics.checkNotNullExpressionValue(title, "getTitle(...)");
        String quantityString = context.getResources().getQuantityString(R.plurals.track_quantity, genre.getTracksCount(), new Object[]{Integer.valueOf(genre.getTracksCount())});
        Intrinsics.checkNotNullExpressionValue(quantityString, "getQuantityString(...)");
        String artworkMrl = genre.getArtworkMrl();
        if (artworkMrl == null) {
            artworkMrl = "";
        }
        String str = artworkMrl;
        return new RemoteAccessServer.PlayQueueItem(id, title, quantityString, 0, str, false, "", (String) null, false, 0, false, (String) null, genre.isFavorite(), Utf8.MASK_2BYTES, (DefaultConstructorMarker) null);
    }

    public static final RemoteAccessServer.PlayQueueItem toPlayQueueItem(Playlist playlist, Context context) {
        Intrinsics.checkNotNullParameter(playlist, "<this>");
        Intrinsics.checkNotNullParameter(context, "appContext");
        long id = playlist.getId();
        String title = playlist.getTitle();
        Intrinsics.checkNotNullExpressionValue(title, "getTitle(...)");
        String quantityString = context.getResources().getQuantityString(R.plurals.track_quantity, playlist.getTracksCount(), new Object[]{Integer.valueOf(playlist.getTracksCount())});
        Intrinsics.checkNotNullExpressionValue(quantityString, "getQuantityString(...)");
        String artworkMrl = playlist.getArtworkMrl();
        if (artworkMrl == null) {
            artworkMrl = "";
        }
        String str = artworkMrl;
        return new RemoteAccessServer.PlayQueueItem(id, title, quantityString, 0, str, false, "", (String) null, false, 0, false, (String) null, playlist.isFavorite(), Utf8.MASK_2BYTES, (DefaultConstructorMarker) null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.lang.CharSequence} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final org.videolan.vlc.webserver.RemoteAccessServer.PlayQueueItem toPlayQueueItem(org.videolan.medialibrary.interfaces.media.MediaWrapper r22, java.lang.String r23) {
        /*
            java.lang.String r0 = "<this>"
            r1 = r22
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            java.lang.String r0 = "defaultArtist"
            r2 = r23
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            long r3 = r22.getId()
            java.lang.String r0 = r22.getTitle()
            java.lang.String r5 = "getTitle(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r5)
            java.lang.String r5 = r22.getArtist()
            if (r5 == 0) goto L_0x002d
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            int r6 = r5.length()
            if (r6 != 0) goto L_0x002a
            goto L_0x002b
        L_0x002a:
            r2 = r5
        L_0x002b:
            java.lang.String r2 = (java.lang.String) r2
        L_0x002d:
            r5 = r2
            long r6 = r22.getLength()
            java.lang.String r2 = r22.getArtworkMrl()
            java.lang.String r8 = ""
            if (r2 != 0) goto L_0x003c
            r10 = r8
            goto L_0x003d
        L_0x003c:
            r10 = r2
        L_0x003d:
            int r2 = r22.getWidth()
            int r9 = r22.getHeight()
            java.lang.String r2 = org.videolan.vlc.util.KextensionsKt.generateResolutionClass(r2, r9)
            if (r2 != 0) goto L_0x004d
            r13 = r8
            goto L_0x004e
        L_0x004d:
            r13 = r2
        L_0x004e:
            long r14 = r22.getTime()
            long r8 = r22.getSeen()
            r11 = 0
            int r2 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            if (r2 <= 0) goto L_0x0060
            r2 = 1
            r20 = 1
            goto L_0x0063
        L_0x0060:
            r2 = 0
            r20 = 0
        L_0x0063:
            boolean r17 = r22.isFavorite()
            org.videolan.vlc.webserver.RemoteAccessServer$PlayQueueItem r21 = new org.videolan.vlc.webserver.RemoteAccessServer$PlayQueueItem
            r1 = r21
            r9 = 0
            r11 = 0
            r12 = 0
            r16 = 0
            r18 = 2432(0x980, float:3.408E-42)
            r19 = 0
            r2 = r3
            r4 = r0
            r8 = r10
            r10 = r13
            r13 = r14
            r15 = r20
            r1.<init>(r2, r4, r5, r6, r8, r9, r10, r11, r12, r13, r15, r16, r17, r18, r19)
            return r21
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.webserver.RemoteAccessRoutingKt.toPlayQueueItem(org.videolan.medialibrary.interfaces.media.MediaWrapper, java.lang.String):org.videolan.vlc.webserver.RemoteAccessServer$PlayQueueItem");
    }

    public static /* synthetic */ RemoteAccessServer.PlayQueueItem toPlayQueueItem$default(MediaWrapper mediaWrapper, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        return toPlayQueueItem(mediaWrapper, str);
    }

    public static final RemoteAccessServer.PlayQueueItem toPlayQueueItem(Folder folder, Context context) {
        String str;
        Folder folder2 = folder;
        Intrinsics.checkNotNullParameter(folder2, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        long id = folder.getId();
        String title = folder.getTitle();
        Intrinsics.checkNotNullExpressionValue(title, "getTitle(...)");
        String quantityString = context.getResources().getQuantityString(R.plurals.videos_quantity, folder2.mediaCount(Folder.TYPE_FOLDER_VIDEO), new Object[]{Integer.valueOf(folder2.mediaCount(Folder.TYPE_FOLDER_VIDEO))});
        String str2 = quantityString == null ? "" : quantityString;
        String artworkMrl = folder.getArtworkMrl();
        if (artworkMrl == null) {
            str = "";
        } else {
            str = artworkMrl;
        }
        return new RemoteAccessServer.PlayQueueItem(id, title, str2, 0, str, false, "", (String) null, false, 0, false, "video-folder", folder.isFavorite(), 1920, (DefaultConstructorMarker) null);
    }

    public static final RemoteAccessServer.PlayQueueItem toPlayQueueItem(VideoGroup videoGroup, Context context) {
        Intrinsics.checkNotNullParameter(videoGroup, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        long id = videoGroup.getId();
        String title = videoGroup.getTitle();
        Intrinsics.checkNotNullExpressionValue(title, "getTitle(...)");
        String quantityString = videoGroup.mediaCount() > 1 ? context.getResources().getQuantityString(R.plurals.videos_quantity, videoGroup.mediaCount(), new Object[]{Integer.valueOf(videoGroup.mediaCount())}) : "length";
        Intrinsics.checkNotNull(quantityString);
        String artworkMrl = videoGroup.getArtworkMrl();
        if (artworkMrl == null) {
            artworkMrl = "";
        }
        return new RemoteAccessServer.PlayQueueItem(id, title, quantityString, 0, artworkMrl, false, "", (String) null, false, 0, videoGroup.getPresentSeen() == videoGroup.getPresentCount() && videoGroup.getPresentCount() != 0, "video-group", videoGroup.isFavorite(), 896, (DefaultConstructorMarker) null);
    }
}
