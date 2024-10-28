package org.videolan.vlc.providers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import androidx.collection.SparseArrayCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.channels.ActorKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.libvlc.util.MediaBrowser;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.util.HeaderProvider;
import org.videolan.tools.CoroutineContextProvider;
import org.videolan.tools.DependencyProvider;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.tools.livedata.LiveDataset;
import org.videolan.vlc.R;
import org.videolan.vlc.util.KextensionsKt;
import org.videolan.vlc.util.ModelsHelper;
import org.videolan.vlc.util.ModelsHelperKt;
import org.videolan.vlc.util.TextUtils;

@Metadata(d1 = {"\u0000Ð\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\f\b&\u0018\u0000 \u00012\u00020\u00012\u00020\u0002:\u0002\u0001B5\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0010\u0010J\u001a\u00020\u001a2\u0006\u0010K\u001a\u00020\u0007H\u0016J\u0014\u0010L\u001a\u00020\u001a2\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u0014J\u001a\u0010M\u001a\u00020\u001a2\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH@¢\u0006\u0002\u0010NJ\u0006\u0010O\u001a\u00020\rJ\u000e\u0010P\u001a\u00020\u001aH¦@¢\u0006\u0002\u0010QJ\u001c\u0010R\u001a\b\u0012\u0004\u0012\u00020\u00070S2\u0006\u0010\b\u001a\u00020\tH@¢\u0006\u0002\u0010NJ\u001c\u0010T\u001a\b\u0012\u0004\u0012\u00020\u00070S2\u0006\u0010\b\u001a\u00020\tH@¢\u0006\u0002\u0010NJ\b\u0010U\u001a\u00020\rH\u0004J\u0016\u0010V\u001a\u00020\u001a2\f\u0010W\u001a\b\u0012\u0004\u0012\u00020\u00070SH\u0016J\b\u0010X\u001a\u00020\u001aH\u0016J*\u0010Y\u001a\b\u0012\u0004\u0012\u00020[0Z2\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\\\u001a\u00020\rH@¢\u0006\u0002\u0010]J\u0018\u0010^\u001a\u0004\u0018\u00010\u00072\u0006\u0010K\u001a\u00020[H@¢\u0006\u0002\u0010_J\"\u0010`\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0018\u00010aj\n\u0012\u0004\u0012\u00020\u0007\u0018\u0001`b2\u0006\u0010c\u001a\u00020\u000bJ\u0018\u0010d\u001a\u00020\t2\u0006\u0010e\u001a\u00020\u000b2\u0006\u0010f\u001a\u00020\u000bH\u0016J\u0010\u0010g\u001a\u00020\u000b2\u0006\u0010\\\u001a\u00020\rH\u0016J\u0018\u0010h\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u0001052\u0006\u0010\b\u001a\u00020\tH\u0004J\u000e\u0010i\u001a\u00020\r2\u0006\u0010K\u001a\u00020jJ\u000e\u0010k\u001a\u00020\r2\u0006\u0010K\u001a\u00020jJ\b\u0010l\u001a\u00020\u001aH\u0014J\u0006\u0010m\u001a\u00020\rJ\u000e\u0010n\u001a\u00020\r2\u0006\u0010o\u001a\u00020jJ\u0017\u0010p\u001a\u0004\u0018\u00010q2\u0006\u0010o\u001a\u00020jH\u0002¢\u0006\u0002\u0010rJ\u001f\u0010s\u001a\u00020\u001a2\u0010\b\u0002\u0010t\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010SH\u0010¢\u0006\u0002\buJ \u0010v\u001a\u00020\u001a2\u0010\b\u0002\u0010t\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010SH@¢\u0006\u0002\u0010wJ\b\u0010x\u001a\u00020\u001aH\u0016J\u000e\u0010y\u001a\u00020\u001aH@¢\u0006\u0002\u0010QJ\b\u0010z\u001a\u00020\u001aH\u0016J\u0018\u0010{\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u0001052\u0006\u0010\b\u001a\u00020\tH\u0004J*\u0010|\u001a\u0004\u0018\u00010\u001a2\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010}\u001a\u00020~2\u0006\u0010\\\u001a\u00020\rH¤@¢\u0006\u0002\u0010J\u0017\u0010\u0001\u001a\u0004\u0018\u00010\u001a2\u0006\u0010K\u001a\u00020j¢\u0006\u0003\u0010\u0001J\u0015\u0010\n\u001a\u00020\u001a2\r\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020\u000705J\t\u0010\u0001\u001a\u00020\u001aH\u0016J\u000f\u0010\u0001\u001a\u00020\u001a2\u0006\u0010W\u001a\u00020\rJ+\u0010\u0001\u001a\u00020\r\"\u0005\b\u0000\u0010\u0001*\t\u0012\u0005\u0012\u0003H\u00010\u00102\b\u0010\u0001\u001a\u0003H\u0001H\u0002¢\u0006\u0003\u0010\u0001R\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0012\u0010\u0013R/\u0010\u0014\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0016¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u001a0\u0015j\u0002`\u001bX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u001fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\"\u001a\u00020#¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u001a\u0010\f\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R#\u0010,\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\t0.0-¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0010\u00101\u001a\u0004\u0018\u000102X\u000e¢\u0006\u0002\n\u0000R \u00103\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070504X\u0004¢\u0006\u0002\n\u0000R\u0017\u00106\u001a\b\u0012\u0004\u0012\u00020\r0-¢\u0006\b\n\u0000\u001a\u0004\b7\u00100R\u001c\u00108\u001a\u0004\u0018\u000109X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u0014\u0010>\u001a\u00020?X\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010AR\u0010\u0010B\u001a\u0004\u0018\u000102X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010E\"\u0004\bF\u0010GR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\bH\u0010I¨\u0006\u0001²\u0006\u0012\u0010t\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u000105X\u0002"}, d2 = {"Lorg/videolan/vlc/providers/BrowserProvider;", "Lkotlinx/coroutines/CoroutineScope;", "Lorg/videolan/resources/util/HeaderProvider;", "context", "Landroid/content/Context;", "dataset", "Lorg/videolan/tools/livedata/LiveDataset;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "url", "", "sort", "", "desc", "", "(Landroid/content/Context;Lorg/videolan/tools/livedata/LiveDataset;Ljava/lang/String;IZ)V", "browserActor", "Lkotlinx/coroutines/channels/SendChannel;", "Lorg/videolan/vlc/providers/BrowserAction;", "getBrowserActor$annotations", "()V", "completionHandler", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "Lkotlinx/coroutines/CompletionHandler;", "getContext", "()Landroid/content/Context;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "coroutineContextProvider", "Lorg/videolan/tools/CoroutineContextProvider;", "getCoroutineContextProvider", "()Lorg/videolan/tools/CoroutineContextProvider;", "getDataset", "()Lorg/videolan/tools/livedata/LiveDataset;", "getDesc", "()Z", "setDesc", "(Z)V", "descriptionUpdate", "Landroidx/lifecycle/MutableLiveData;", "Lkotlin/Pair;", "getDescriptionUpdate", "()Landroidx/lifecycle/MutableLiveData;", "discoveryJob", "Lkotlinx/coroutines/Job;", "foldersContentMap", "Landroidx/collection/SimpleArrayMap;", "", "loading", "getLoading", "mediabrowser", "Lorg/videolan/libvlc/util/MediaBrowser;", "getMediabrowser", "()Lorg/videolan/libvlc/util/MediaBrowser;", "setMediabrowser", "(Lorg/videolan/libvlc/util/MediaBrowser;)V", "medialibrary", "Lorg/videolan/medialibrary/interfaces/Medialibrary;", "getMedialibrary$vlc_android_release", "()Lorg/videolan/medialibrary/interfaces/Medialibrary;", "parsingJob", "showOnlyMultimedia", "getSort", "()I", "setSort", "(I)V", "getUrl", "()Ljava/lang/String;", "addMedia", "media", "browse", "browseImpl", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "browseRoot", "browseRootImpl", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "browseUrl", "", "browseUrlImpl", "clearListener", "computeHeaders", "value", "fetch", "filesFlow", "Lkotlinx/coroutines/flow/Flow;", "Lorg/videolan/libvlc/interfaces/IMedia;", "interact", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findMedia", "(Lorg/videolan/libvlc/interfaces/IMedia;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getComparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "nbOfDigits", "getDescription", "folderCount", "mediaFileCount", "getFlags", "getList", "hasMedias", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "hasSubfolders", "initBrowser", "isComparatorAboutFilename", "isFolderEmpty", "mw", "parseMediaSize", "", "(Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;)Ljava/lang/Long;", "parseSubDirectories", "list", "parseSubDirectories$vlc_android_release", "parseSubDirectoriesImpl", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refresh", "refreshImpl", "release", "removeList", "requestBrowsing", "eventListener", "Lorg/videolan/libvlc/util/MediaBrowser$EventListener;", "(Ljava/lang/String;Lorg/videolan/libvlc/util/MediaBrowser$EventListener;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveList", "(Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;)Lkotlin/Unit;", "files", "stop", "updateShowAllFiles", "post", "E", "element", "(Lkotlinx/coroutines/channels/SendChannel;Ljava/lang/Object;)Z", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BrowserProvider.kt */
public abstract class BrowserProvider extends HeaderProvider implements CoroutineScope {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Lazy<Handler> browserHandler$delegate = LazyKt.lazy(BrowserProvider$Companion$browserHandler$2.INSTANCE);
    /* access modifiers changed from: private */
    public static final Map<String, List<MediaLibraryItem>> prefetchLists = new LinkedHashMap();
    private final SendChannel<BrowserAction> browserActor;
    private final Function1<Throwable, Unit> completionHandler;
    private final Context context;
    private final CoroutineContext coroutineContext = Dispatchers.getMain().getImmediate().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null));
    private final CoroutineContextProvider coroutineContextProvider;
    private final LiveDataset<MediaLibraryItem> dataset;
    private boolean desc;
    private final MutableLiveData<Pair<Integer, String>> descriptionUpdate;
    /* access modifiers changed from: private */
    public Job discoveryJob;
    /* access modifiers changed from: private */
    public final SimpleArrayMap<MediaLibraryItem, List<MediaLibraryItem>> foldersContentMap;
    private final MutableLiveData<Boolean> loading;
    private MediaBrowser mediabrowser;
    private final Medialibrary medialibrary;
    /* access modifiers changed from: private */
    public Job parsingJob;
    private boolean showOnlyMultimedia;
    private int sort;
    private final String url;

    private static /* synthetic */ void getBrowserActor$annotations() {
    }

    /* access modifiers changed from: protected */
    public Object browseImpl(String str, Continuation<? super Unit> continuation) {
        return browseImpl$suspendImpl(this, str, continuation);
    }

    public abstract Object browseRootImpl(Continuation<? super Unit> continuation);

    /* access modifiers changed from: protected */
    public Object findMedia(IMedia iMedia, Continuation<? super MediaLibraryItem> continuation) {
        return findMedia$suspendImpl(this, iMedia, continuation);
    }

    /* access modifiers changed from: protected */
    public Object refreshImpl(Continuation<? super Unit> continuation) {
        return refreshImpl$suspendImpl(this, continuation);
    }

    /* access modifiers changed from: protected */
    public abstract Object requestBrowsing(String str, MediaBrowser.EventListener eventListener, boolean z, Continuation<? super Unit> continuation);

    public BrowserProvider(Context context2, LiveDataset<MediaLibraryItem> liveDataset, String str, int i, boolean z) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(liveDataset, "dataset");
        this.context = context2;
        this.dataset = liveDataset;
        this.url = str;
        this.sort = i;
        this.desc = z;
        MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(false);
        this.loading = mutableLiveData;
        this.foldersContentMap = new SimpleArrayMap<>();
        this.showOnlyMultimedia = ((SharedPreferences) Settings.INSTANCE.getInstance(context2)).getBoolean(SettingsKt.BROWSER_SHOW_ONLY_MULTIMEDIA, false);
        this.descriptionUpdate = new MutableLiveData<>();
        Medialibrary instance = Medialibrary.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
        this.medialibrary = instance;
        Companion companion = Companion;
        DependencyProvider dependencyProvider = companion;
        Function1 function1 = AnonymousClass1.INSTANCE;
        String name = CoroutineContextProvider.class.getName();
        if (dependencyProvider.getOverrideCreator() || !dependencyProvider.getCreatorMap().containsKey(name)) {
            dependencyProvider.getCreatorMap().put(name, function1);
        }
        if (dependencyProvider.getObjectMap().containsKey(name) && dependencyProvider.getOverrideCreator()) {
            dependencyProvider.getObjectMap().remove(name);
        }
        DependencyProvider dependencyProvider2 = companion;
        String name2 = CoroutineContextProvider.class.getName();
        if (!dependencyProvider2.getObjectMap().containsKey(name2)) {
            Map objectMap = dependencyProvider2.getObjectMap();
            Function1 function12 = (Function1) dependencyProvider2.getCreatorMap().get(name2);
            objectMap.put(name2, function12 != null ? function12.invoke(this) : null);
        }
        Object obj = dependencyProvider2.getObjectMap().get(name2);
        if (obj != null) {
            this.coroutineContextProvider = (CoroutineContextProvider) obj;
            Function1<Throwable, Unit> browserProvider$completionHandler$1 = new BrowserProvider$completionHandler$1(this);
            this.completionHandler = browserProvider$completionHandler$1;
            this.browserActor = ActorKt.actor$default(this, (CoroutineContext) null, Integer.MAX_VALUE, (CoroutineStart) null, browserProvider$completionHandler$1, new BrowserProvider$browserActor$1(this, (Continuation<? super BrowserProvider$browserActor$1>) null), 5, (Object) null);
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type org.videolan.tools.CoroutineContextProvider");
    }

    public final Context getContext() {
        return this.context;
    }

    public final LiveDataset<MediaLibraryItem> getDataset() {
        return this.dataset;
    }

    public final boolean getDesc() {
        return this.desc;
    }

    public final int getSort() {
        return this.sort;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setDesc(boolean z) {
        this.desc = z;
    }

    public final void setSort(int i) {
        this.sort = i;
    }

    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    public final MutableLiveData<Boolean> getLoading() {
        return this.loading;
    }

    public final MediaBrowser getMediabrowser() {
        return this.mediabrowser;
    }

    public final void setMediabrowser(MediaBrowser mediaBrowser) {
        this.mediabrowser = mediaBrowser;
    }

    public final CoroutineContextProvider getCoroutineContextProvider() {
        return this.coroutineContextProvider;
    }

    public final MutableLiveData<Pair<Integer, String>> getDescriptionUpdate() {
        return this.descriptionUpdate;
    }

    public final Medialibrary getMedialibrary$vlc_android_release() {
        return this.medialibrary;
    }

    public final boolean isComparatorAboutFilename() {
        if (Settings.INSTANCE.getShowTvUi() && this.sort == 1 && this.desc) {
            return false;
        }
        if (Settings.INSTANCE.getShowTvUi() && this.sort == 1 && !this.desc) {
            return false;
        }
        int i = this.sort;
        if (i == 1 && this.desc) {
            return false;
        }
        if (i == 1 && !this.desc) {
            return false;
        }
        if (i == 10 || i == 0) {
            boolean z = this.desc;
        }
        return true;
    }

    public final Comparator<MediaLibraryItem> getComparator(int i) {
        if (Settings.INSTANCE.getShowTvUi()) {
            if (ArraysKt.contains((T[]) new Integer[]{1, 0}, Integer.valueOf(this.sort)) && this.desc) {
                return ModelsHelperKt.getTvDescComp(Settings.INSTANCE.getTvFoldersFirst());
            }
        }
        if (Settings.INSTANCE.getShowTvUi()) {
            if (ArraysKt.contains((T[]) new Integer[]{1, 0}, Integer.valueOf(this.sort)) && !this.desc) {
                return ModelsHelperKt.getTvAscComp(Settings.INSTANCE.getTvFoldersFirst());
            }
        }
        String str = this.url;
        if (str != null) {
            Uri parse = Uri.parse(str);
            if (Intrinsics.areEqual((Object) parse != null ? parse.getScheme() : null, (Object) "upnp")) {
                return null;
            }
        }
        int i2 = this.sort;
        if (i2 == 1 && this.desc) {
            return ModelsHelperKt.getDescComp();
        }
        if (i2 == 1 && !this.desc) {
            return ModelsHelperKt.getAscComp();
        }
        if ((i2 == 10 || i2 == 0) && this.desc) {
            return ModelsHelperKt.getFilenameDescComp(i);
        }
        return ModelsHelperKt.getFilenameAscComp(i);
    }

    /* access modifiers changed from: protected */
    public void initBrowser() {
        if (this.mediabrowser == null) {
            Companion companion = Companion;
            DependencyProvider dependencyProvider = companion;
            Function1 browserProvider$initBrowser$1 = new BrowserProvider$initBrowser$1(this);
            String name = MediaBrowser.class.getName();
            if (dependencyProvider.getOverrideCreator() || !dependencyProvider.getCreatorMap().containsKey(name)) {
                dependencyProvider.getCreatorMap().put(name, browserProvider$initBrowser$1);
            }
            if (dependencyProvider.getObjectMap().containsKey(name) && dependencyProvider.getOverrideCreator()) {
                dependencyProvider.getObjectMap().remove(name);
            }
            DependencyProvider dependencyProvider2 = companion;
            String name2 = MediaBrowser.class.getName();
            if (!dependencyProvider2.getObjectMap().containsKey(name2)) {
                Map objectMap = dependencyProvider2.getObjectMap();
                Function1 function1 = (Function1) dependencyProvider2.getCreatorMap().get(name2);
                objectMap.put(name2, function1 != null ? function1.invoke(this) : null);
            }
            Object obj = dependencyProvider2.getObjectMap().get(name2);
            if (obj != null) {
                MediaBrowser mediaBrowser = (MediaBrowser) obj;
                this.mediabrowser = mediaBrowser;
                if (!this.showOnlyMultimedia && mediaBrowser != null) {
                    mediaBrowser.setIgnoreFileTypes(".");
                    return;
                }
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type org.videolan.libvlc.util.MediaBrowser");
        }
    }

    private static final List<MediaLibraryItem> fetch$lambda$1(Lazy<? extends List<MediaLibraryItem>> lazy) {
        return (List) lazy.getValue();
    }

    public void fetch() {
        Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, new BrowserProvider$fetch$list$2(this));
        if (this.url == null) {
            browseRoot();
            parseSubDirectories$vlc_android_release$default(this, (List) null, 1, (Object) null);
            return;
        }
        Collection fetch$lambda$1 = fetch$lambda$1(lazy);
        if (fetch$lambda$1 == null || fetch$lambda$1.isEmpty()) {
            browse(this.url);
            return;
        }
        LiveDataset<MediaLibraryItem> liveDataset = this.dataset;
        List<MediaLibraryItem> fetch$lambda$12 = fetch$lambda$1(lazy);
        if (fetch$lambda$12 != null) {
            liveDataset.setValue(fetch$lambda$12);
            prefetchLists.remove(this.url);
            List<MediaLibraryItem> fetch$lambda$13 = fetch$lambda$1(lazy);
            Intrinsics.checkNotNull(fetch$lambda$13);
            computeHeaders(fetch$lambda$13);
            parseSubDirectories$vlc_android_release$default(this, (List) null, 1, (Object) null);
        }
    }

    public static /* synthetic */ void browse$default(BrowserProvider browserProvider, String str, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                str = null;
            }
            browserProvider.browse(str);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: browse");
    }

    /* access modifiers changed from: protected */
    public void browse(String str) {
        if (str != null) {
            this.loading.postValue(true);
        }
        post(this.browserActor, new Browse(str));
    }

    public static /* synthetic */ Object browseImpl$default(BrowserProvider browserProvider, String str, Continuation continuation, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                str = null;
            }
            return browserProvider.browseImpl(str, continuation);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: browseImpl");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v8, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v10, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x009c A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00b9  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ java.lang.Object browseImpl$suspendImpl(org.videolan.vlc.providers.BrowserProvider r12, java.lang.String r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            boolean r0 = r14 instanceof org.videolan.vlc.providers.BrowserProvider$browseImpl$1
            if (r0 == 0) goto L_0x0014
            r0 = r14
            org.videolan.vlc.providers.BrowserProvider$browseImpl$1 r0 = (org.videolan.vlc.providers.BrowserProvider$browseImpl$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.providers.BrowserProvider$browseImpl$1 r0 = new org.videolan.vlc.providers.BrowserProvider$browseImpl$1
            r0.<init>(r12, r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r0.label
            r8 = 3
            r2 = 2
            r9 = 0
            r10 = 1
            if (r1 == 0) goto L_0x005b
            if (r1 == r10) goto L_0x004e
            if (r1 == r2) goto L_0x0041
            if (r1 != r8) goto L_0x0039
            java.lang.Object r12 = r0.L$1
            java.lang.String r12 = (java.lang.String) r12
            java.lang.Object r13 = r0.L$0
            org.videolan.vlc.providers.BrowserProvider r13 = (org.videolan.vlc.providers.BrowserProvider) r13
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x00a0
        L_0x0039:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x0041:
            java.lang.Object r12 = r0.L$1
            r13 = r12
            java.lang.String r13 = (java.lang.String) r13
            java.lang.Object r12 = r0.L$0
            org.videolan.vlc.providers.BrowserProvider r12 = (org.videolan.vlc.providers.BrowserProvider) r12
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x0087
        L_0x004e:
            java.lang.Object r12 = r0.L$1
            r13 = r12
            java.lang.String r13 = (java.lang.String) r13
            java.lang.Object r12 = r0.L$0
            org.videolan.vlc.providers.BrowserProvider r12 = (org.videolan.vlc.providers.BrowserProvider) r12
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x00b7
        L_0x005b:
            kotlin.ResultKt.throwOnFailure(r14)
            if (r13 != 0) goto L_0x0074
            org.videolan.vlc.providers.BrowserProvider$browseImpl$2 r14 = new org.videolan.vlc.providers.BrowserProvider$browseImpl$2
            r14.<init>(r12, r13, r9)
            kotlin.jvm.functions.Function2 r14 = (kotlin.jvm.functions.Function2) r14
            r0.L$0 = r12
            r0.L$1 = r13
            r0.label = r10
            java.lang.Object r14 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r14, r0)
            if (r14 != r7) goto L_0x00b7
            return r7
        L_0x0074:
            r0.L$0 = r12
            r0.L$1 = r13
            r0.label = r2
            r3 = 0
            r5 = 2
            r6 = 0
            r1 = r12
            r2 = r13
            r4 = r0
            java.lang.Object r14 = filesFlow$default(r1, r2, r3, r4, r5, r6)
            if (r14 != r7) goto L_0x0087
            return r7
        L_0x0087:
            kotlinx.coroutines.flow.Flow r14 = (kotlinx.coroutines.flow.Flow) r14
            org.videolan.vlc.providers.BrowserProvider$browseImpl$suspendImpl$$inlined$mapNotNull$1 r1 = new org.videolan.vlc.providers.BrowserProvider$browseImpl$suspendImpl$$inlined$mapNotNull$1
            r1.<init>(r14, r12)
            kotlinx.coroutines.flow.Flow r1 = (kotlinx.coroutines.flow.Flow) r1
            r0.L$0 = r12
            r0.L$1 = r13
            r0.label = r8
            java.lang.Object r14 = kotlinx.coroutines.flow.FlowKt__CollectionKt.toList$default(r1, r9, r0, r10, r9)
            if (r14 != r7) goto L_0x009d
            return r7
        L_0x009d:
            r11 = r13
            r13 = r12
            r12 = r11
        L_0x00a0:
            java.util.Collection r14 = (java.util.Collection) r14
            java.util.List r14 = kotlin.collections.CollectionsKt.toMutableList(r14)
            r13.sort(r14)
            org.videolan.tools.livedata.LiveDataset<org.videolan.medialibrary.media.MediaLibraryItem> r0 = r13.dataset
            r0.setValue(r14)
            r13.computeHeaders(r14)
            r13.parseSubDirectories$vlc_android_release(r14)
            r11 = r13
            r13 = r12
            r12 = r11
        L_0x00b7:
            if (r13 == 0) goto L_0x00c3
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r12 = r12.loading
            r13 = 0
            java.lang.Boolean r13 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r13)
            r12.postValue(r13)
        L_0x00c3:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.providers.BrowserProvider.browseImpl$suspendImpl(org.videolan.vlc.providers.BrowserProvider, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void sort(List<MediaLibraryItem> list) {
        Intrinsics.checkNotNullParameter(list, "files");
        Comparator<MediaLibraryItem> comparator = getComparator(isComparatorAboutFilename() ? KextensionsKt.determineMaxNbOfDigits(list) : 0);
        if (comparator != null) {
            CollectionsKt.sortWith(list, comparator);
        } else if (this.desc) {
            CollectionsKt.reverse(list);
        }
    }

    public final Object browseUrl(String str, Continuation<? super List<? extends MediaLibraryItem>> continuation) {
        CompletableDeferred CompletableDeferred$default = CompletableDeferredKt.CompletableDeferred$default((Job) null, 1, (Object) null);
        post(this.browserActor, new BrowseUrl(str, CompletableDeferred$default));
        return CompletableDeferred$default.await(continuation);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x007b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00a1 A[LOOP:0: B:24:0x009b->B:26:0x00a1, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0111  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object browseUrlImpl(java.lang.String r12, kotlin.coroutines.Continuation<? super java.util.List<? extends org.videolan.medialibrary.media.MediaLibraryItem>> r13) {
        /*
            r11 = this;
            boolean r0 = r13 instanceof org.videolan.vlc.providers.BrowserProvider$browseUrlImpl$1
            if (r0 == 0) goto L_0x0014
            r0 = r13
            org.videolan.vlc.providers.BrowserProvider$browseUrlImpl$1 r0 = (org.videolan.vlc.providers.BrowserProvider$browseUrlImpl$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.providers.BrowserProvider$browseUrlImpl$1 r0 = new org.videolan.vlc.providers.BrowserProvider$browseUrlImpl$1
            r0.<init>(r11, r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r0.label
            r8 = 2
            r9 = 3
            r10 = 1
            if (r1 == 0) goto L_0x0059
            if (r1 == r10) goto L_0x0051
            if (r1 == r8) goto L_0x0049
            if (r1 != r9) goto L_0x0041
            java.lang.Object r12 = r0.L$3
            java.util.ArrayList r12 = (java.util.ArrayList) r12
            java.lang.Object r1 = r0.L$2
            java.util.Iterator r1 = (java.util.Iterator) r1
            java.lang.Object r2 = r0.L$1
            java.util.ArrayList r2 = (java.util.ArrayList) r2
            java.lang.Object r3 = r0.L$0
            org.videolan.vlc.providers.BrowserProvider r3 = (org.videolan.vlc.providers.BrowserProvider) r3
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x010a
        L_0x0041:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x0049:
            java.lang.Object r12 = r0.L$0
            org.videolan.vlc.providers.BrowserProvider r12 = (org.videolan.vlc.providers.BrowserProvider) r12
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x007c
        L_0x0051:
            java.lang.Object r12 = r0.L$0
            org.videolan.vlc.providers.BrowserProvider r12 = (org.videolan.vlc.providers.BrowserProvider) r12
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x006e
        L_0x0059:
            kotlin.ResultKt.throwOnFailure(r13)
            r0.L$0 = r11
            r0.label = r10
            r3 = 0
            r5 = 2
            r6 = 0
            r1 = r11
            r2 = r12
            r4 = r0
            java.lang.Object r13 = filesFlow$default(r1, r2, r3, r4, r5, r6)
            if (r13 != r7) goto L_0x006d
            return r7
        L_0x006d:
            r12 = r11
        L_0x006e:
            kotlinx.coroutines.flow.Flow r13 = (kotlinx.coroutines.flow.Flow) r13
            r0.L$0 = r12
            r0.label = r8
            r1 = 0
            java.lang.Object r13 = kotlinx.coroutines.flow.FlowKt__CollectionKt.toList$default(r13, r1, r0, r10, r1)
            if (r13 != r7) goto L_0x007c
            return r7
        L_0x007c:
            java.util.List r13 = (java.util.List) r13
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.lang.Iterable r13 = (java.lang.Iterable) r13
            java.util.ArrayList r3 = new java.util.ArrayList
            r4 = 10
            int r4 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r13, r4)
            r3.<init>(r4)
            java.util.Collection r3 = (java.util.Collection) r3
            java.util.Iterator r13 = r13.iterator()
        L_0x009b:
            boolean r4 = r13.hasNext()
            if (r4 == 0) goto L_0x00af
            java.lang.Object r4 = r13.next()
            org.videolan.libvlc.interfaces.IMedia r4 = (org.videolan.libvlc.interfaces.IMedia) r4
            org.videolan.medialibrary.interfaces.media.MediaWrapper r4 = org.videolan.medialibrary.MLServiceLocator.getAbstractMediaWrapper((org.videolan.libvlc.interfaces.IMedia) r4)
            r3.add(r4)
            goto L_0x009b
        L_0x00af:
            java.util.List r3 = (java.util.List) r3
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            java.util.Iterator r13 = r3.iterator()
        L_0x00b7:
            boolean r3 = r13.hasNext()
            if (r3 == 0) goto L_0x00d6
            java.lang.Object r3 = r13.next()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r3 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r3
            int r4 = r3.getType()
            if (r4 == 0) goto L_0x00d2
            if (r4 == r10) goto L_0x00d2
            if (r4 == r9) goto L_0x00ce
            goto L_0x00b7
        L_0x00ce:
            r2.add(r3)
            goto L_0x00b7
        L_0x00d2:
            r1.add(r3)
            goto L_0x00b7
        L_0x00d6:
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.Iterator r13 = r2.iterator()
            r3 = r12
            r12 = r1
            r1 = r13
        L_0x00df:
            boolean r13 = r1.hasNext()
            if (r13 == 0) goto L_0x0111
            java.lang.Object r13 = r1.next()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r13 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r13
            android.net.Uri r13 = r13.getUri()
            java.lang.String r13 = r13.toString()
            java.lang.String r2 = "toString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r2)
            r0.L$0 = r3
            r0.L$1 = r12
            r0.L$2 = r1
            r0.L$3 = r12
            r0.label = r9
            java.lang.Object r13 = r3.browseUrlImpl(r13, r0)
            if (r13 != r7) goto L_0x0109
            return r7
        L_0x0109:
            r2 = r12
        L_0x010a:
            java.util.Collection r13 = (java.util.Collection) r13
            r12.addAll(r13)
            r12 = r2
            goto L_0x00df
        L_0x0111:
            java.lang.Iterable r12 = (java.lang.Iterable) r12
            java.util.List r12 = kotlin.collections.CollectionsKt.toList(r12)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.providers.BrowserProvider.browseUrlImpl(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0069 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ java.lang.Object refreshImpl$suspendImpl(org.videolan.vlc.providers.BrowserProvider r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            boolean r0 = r11 instanceof org.videolan.vlc.providers.BrowserProvider$refreshImpl$1
            if (r0 == 0) goto L_0x0014
            r0 = r11
            org.videolan.vlc.providers.BrowserProvider$refreshImpl$1 r0 = (org.videolan.vlc.providers.BrowserProvider$refreshImpl$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.providers.BrowserProvider$refreshImpl$1 r0 = new org.videolan.vlc.providers.BrowserProvider$refreshImpl$1
            r0.<init>(r10, r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r0.label
            r8 = 2
            r9 = 1
            if (r1 == 0) goto L_0x0041
            if (r1 == r9) goto L_0x0039
            if (r1 != r8) goto L_0x0031
            java.lang.Object r10 = r0.L$0
            org.videolan.vlc.providers.BrowserProvider r10 = (org.videolan.vlc.providers.BrowserProvider) r10
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x006a
        L_0x0031:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0039:
            java.lang.Object r10 = r0.L$0
            org.videolan.vlc.providers.BrowserProvider r10 = (org.videolan.vlc.providers.BrowserProvider) r10
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0055
        L_0x0041:
            kotlin.ResultKt.throwOnFailure(r11)
            r0.L$0 = r10
            r0.label = r9
            r2 = 0
            r3 = 0
            r5 = 3
            r6 = 0
            r1 = r10
            r4 = r0
            java.lang.Object r11 = filesFlow$default(r1, r2, r3, r4, r5, r6)
            if (r11 != r7) goto L_0x0055
            return r7
        L_0x0055:
            kotlinx.coroutines.flow.Flow r11 = (kotlinx.coroutines.flow.Flow) r11
            org.videolan.vlc.providers.BrowserProvider$refreshImpl$suspendImpl$$inlined$mapNotNull$1 r1 = new org.videolan.vlc.providers.BrowserProvider$refreshImpl$suspendImpl$$inlined$mapNotNull$1
            r1.<init>(r11, r10)
            kotlinx.coroutines.flow.Flow r1 = (kotlinx.coroutines.flow.Flow) r1
            r0.L$0 = r10
            r0.label = r8
            r11 = 0
            java.lang.Object r11 = kotlinx.coroutines.flow.FlowKt__CollectionKt.toList$default(r1, r11, r0, r9, r11)
            if (r11 != r7) goto L_0x006a
            return r7
        L_0x006a:
            java.lang.String r0 = "null cannot be cast to non-null type kotlin.collections.MutableList<org.videolan.medialibrary.media.MediaLibraryItem>"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r11, r0)
            java.util.List r11 = kotlin.jvm.internal.TypeIntrinsics.asMutableList(r11)
            r10.sort(r11)
            org.videolan.tools.livedata.LiveDataset<org.videolan.medialibrary.media.MediaLibraryItem> r0 = r10.dataset
            r0.setValue(r11)
            r10.computeHeaders(r11)
            r10.parseSubDirectories$vlc_android_release(r11)
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r10 = r10.loading
            r11 = 0
            java.lang.Boolean r11 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r11)
            r10.postValue(r11)
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.providers.BrowserProvider.refreshImpl$suspendImpl(org.videolan.vlc.providers.BrowserProvider, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public final Object filesFlow(String str, boolean z, Continuation<? super Flow<? extends IMedia>> continuation) {
        return FlowKt__ContextKt.buffer$default(FlowKt.channelFlow(new BrowserProvider$filesFlow$2(this, str, z, (Continuation<? super BrowserProvider$filesFlow$2>) null)), Integer.MAX_VALUE, (BufferOverflow) null, 2, (Object) null);
    }

    static /* synthetic */ Object filesFlow$default(BrowserProvider browserProvider, String str, boolean z, Continuation continuation, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                str = browserProvider.url;
            }
            if ((i & 2) != 0) {
                z = true;
            }
            return browserProvider.filesFlow(str, z, continuation);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: filesFlow");
    }

    public void addMedia(MediaLibraryItem mediaLibraryItem) {
        Unit unit;
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "media");
        Comparator<MediaLibraryItem> comparator = getComparator(isComparatorAboutFilename() ? KextensionsKt.determineMaxNbOfDigits(this.dataset.getValue()) : 0);
        if (comparator != null) {
            this.dataset.add(mediaLibraryItem, comparator);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            this.dataset.add(mediaLibraryItem);
        }
    }

    public void refresh() {
        if (this.url != null) {
            Job job = this.parsingJob;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            this.parsingJob = null;
            this.loading.postValue(true);
            post(this.browserActor, Refresh.INSTANCE);
        }
    }

    public void computeHeaders(List<? extends MediaLibraryItem> list) {
        Intrinsics.checkNotNullParameter(list, "value");
        getPrivateHeaders().clear();
        int i = 0;
        for (MediaLibraryItem header : list) {
            int i2 = i + 1;
            String header2 = ModelsHelper.INSTANCE.getHeader(this.context, 1, header, i > 0 ? (MediaLibraryItem) list.get(i - 1) : null);
            if (header2 != null) {
                getPrivateHeaders().put(i, header2);
            }
            i = i2;
        }
        LiveData<SparseArrayCompat<String>> liveHeaders = getLiveHeaders();
        Intrinsics.checkNotNull(liveHeaders, "null cannot be cast to non-null type androidx.lifecycle.MutableLiveData<androidx.collection.SparseArrayCompat<kotlin.String>{ org.videolan.resources.util.HeaderProviderKt.HeadersIndex }>");
        ((MutableLiveData) liveHeaders).postValue(getPrivateHeaders().clone());
    }

    public static /* synthetic */ void parseSubDirectories$vlc_android_release$default(BrowserProvider browserProvider, List list, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                list = null;
            }
            browserProvider.parseSubDirectories$vlc_android_release(list);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: parseSubDirectories");
    }

    public void parseSubDirectories$vlc_android_release(List<? extends MediaLibraryItem> list) {
        post(this.browserActor, new ParseSubDirectories(list));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a3 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object parseSubDirectoriesImpl(java.util.List<? extends org.videolan.medialibrary.media.MediaLibraryItem> r12, kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            r11 = this;
            boolean r0 = r13 instanceof org.videolan.vlc.providers.BrowserProvider$parseSubDirectoriesImpl$1
            if (r0 == 0) goto L_0x0014
            r0 = r13
            org.videolan.vlc.providers.BrowserProvider$parseSubDirectoriesImpl$1 r0 = (org.videolan.vlc.providers.BrowserProvider$parseSubDirectoriesImpl$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.providers.BrowserProvider$parseSubDirectoriesImpl$1 r0 = new org.videolan.vlc.providers.BrowserProvider$parseSubDirectoriesImpl$1
            r0.<init>(r11, r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0043
            if (r2 == r5) goto L_0x003b
            if (r2 != r4) goto L_0x0033
            java.lang.Object r12 = r0.L$0
            org.videolan.vlc.providers.BrowserProvider r12 = (org.videolan.vlc.providers.BrowserProvider) r12
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x00a4
        L_0x0033:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x003b:
            java.lang.Object r12 = r0.L$0
            org.videolan.vlc.providers.BrowserProvider r12 = (org.videolan.vlc.providers.BrowserProvider) r12
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x0074
        L_0x0043:
            kotlin.ResultKt.throwOnFailure(r13)
            if (r12 != 0) goto L_0x0057
            org.videolan.tools.livedata.LiveDataset<org.videolan.medialibrary.media.MediaLibraryItem> r13 = r11.dataset
            java.util.List r13 = r13.getValue()
            boolean r13 = r13.isEmpty()
            if (r13 == 0) goto L_0x0057
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        L_0x0057:
            if (r12 != 0) goto L_0x0078
            org.videolan.tools.CoroutineContextProvider r12 = r11.coroutineContextProvider
            kotlinx.coroutines.CoroutineDispatcher r12 = r12.getMain()
            kotlin.coroutines.CoroutineContext r12 = (kotlin.coroutines.CoroutineContext) r12
            org.videolan.vlc.providers.BrowserProvider$parseSubDirectoriesImpl$currentMediaList$1 r13 = new org.videolan.vlc.providers.BrowserProvider$parseSubDirectoriesImpl$currentMediaList$1
            r13.<init>(r11, r3)
            kotlin.jvm.functions.Function2 r13 = (kotlin.jvm.functions.Function2) r13
            r0.L$0 = r11
            r0.label = r5
            java.lang.Object r13 = kotlinx.coroutines.BuildersKt.withContext(r12, r13, r0)
            if (r13 != r1) goto L_0x0073
            return r1
        L_0x0073:
            r12 = r11
        L_0x0074:
            java.util.List r13 = (java.util.List) r13
            r7 = r13
            goto L_0x007a
        L_0x0078:
            r7 = r12
            r12 = r11
        L_0x007a:
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
            r8 = r13
            java.util.List r8 = (java.util.List) r8
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
            r9 = r13
            java.util.List r9 = (java.util.List) r9
            androidx.collection.SimpleArrayMap<org.videolan.medialibrary.media.MediaLibraryItem, java.util.List<org.videolan.medialibrary.media.MediaLibraryItem>> r13 = r12.foldersContentMap
            r13.clear()
            org.videolan.vlc.providers.BrowserProvider$parseSubDirectoriesImpl$2 r13 = new org.videolan.vlc.providers.BrowserProvider$parseSubDirectoriesImpl$2
            r10 = 0
            r5 = r13
            r6 = r12
            r5.<init>(r6, r7, r8, r9, r10)
            kotlin.jvm.functions.Function2 r13 = (kotlin.jvm.functions.Function2) r13
            r0.L$0 = r12
            r0.label = r4
            java.lang.Object r13 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r13, r0)
            if (r13 != r1) goto L_0x00a4
            return r1
        L_0x00a4:
            r12.parsingJob = r3
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.providers.BrowserProvider.parseSubDirectoriesImpl(java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object parseSubDirectoriesImpl$default(BrowserProvider browserProvider, List list, Continuation continuation, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                list = null;
            }
            return browserProvider.parseSubDirectoriesImpl(list, continuation);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: parseSubDirectoriesImpl");
    }

    /* access modifiers changed from: private */
    public final Long parseMediaSize(MediaWrapper mediaWrapper) {
        String path;
        Uri uri = mediaWrapper.getUri();
        if (uri == null || (path = uri.getPath()) == null) {
            return null;
        }
        return Long.valueOf(new File(path).length());
    }

    public final boolean hasSubfolders(MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "media");
        List list = this.foldersContentMap.get(mediaWrapper);
        if (list != null) {
            Iterable<MediaLibraryItem> iterable = list;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (MediaLibraryItem mediaLibraryItem : iterable) {
                Intrinsics.checkNotNull(mediaLibraryItem, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper");
                arrayList.add((MediaWrapper) mediaLibraryItem);
            }
            Collection arrayList2 = new ArrayList();
            for (Object next : (List) arrayList) {
                if (((MediaWrapper) next).getType() == 3) {
                    arrayList2.add(next);
                }
            }
            if (((List) arrayList2).size() > 0) {
                return true;
            }
        }
        return false;
    }

    public final boolean hasMedias(MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "media");
        List list = this.foldersContentMap.get(mediaWrapper);
        if (list != null) {
            Iterable<MediaLibraryItem> iterable = list;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (MediaLibraryItem mediaLibraryItem : iterable) {
                Intrinsics.checkNotNull(mediaLibraryItem, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper");
                arrayList.add((MediaWrapper) mediaLibraryItem);
            }
            Collection arrayList2 = new ArrayList();
            for (Object next : (List) arrayList) {
                if (((MediaWrapper) next).getType() != 3) {
                    arrayList2.add(next);
                }
            }
            if (((List) arrayList2).size() > 0) {
                return true;
            }
        }
        return false;
    }

    public String getDescription(int i, int i2) {
        Resources resources = this.context.getResources();
        ArrayList arrayList = new ArrayList();
        if (i > 0) {
            arrayList.add(i + " §*§");
        }
        if (i2 > 0) {
            arrayList.add(i2 + " *§*");
        }
        if (arrayList.isEmpty()) {
            arrayList.add(resources.getString(R.string.empty_directory));
        }
        return TextUtils.INSTANCE.separatedString((String[]) arrayList.toArray(new String[0]));
    }

    static /* synthetic */ Object findMedia$suspendImpl(BrowserProvider browserProvider, IMedia iMedia, Continuation<? super MediaLibraryItem> continuation) {
        try {
            MediaWrapper abstractMediaWrapper = MLServiceLocator.getAbstractMediaWrapper(iMedia);
            Intrinsics.checkNotNull(abstractMediaWrapper);
            iMedia.release();
            if (!KextensionsKt.isMedia(abstractMediaWrapper)) {
                if (!browserProvider.showOnlyMultimedia || KextensionsKt.isBrowserMedia(abstractMediaWrapper) || KextensionsKt.isBrowserMedia(abstractMediaWrapper)) {
                    return abstractMediaWrapper;
                }
                if (browserProvider.showOnlyMultimedia) {
                    return null;
                }
            }
            return (abstractMediaWrapper.getType() == 1 || abstractMediaWrapper.getType() == 0) ? BuildersKt.withContext(browserProvider.coroutineContextProvider.getIO(), new BrowserProvider$findMedia$2(browserProvider, abstractMediaWrapper.getUri(), abstractMediaWrapper, (Continuation<? super BrowserProvider$findMedia$2>) null), continuation) : abstractMediaWrapper;
        } catch (Exception e) {
            Log.e(BrowserProviderKt.TAG, "Unable to generate the media wrapper. It usually happen when the IMedia fields have some encoding issues", e);
            return null;
        }
    }

    public final boolean browseRoot() {
        return post(this.browserActor, BrowseRoot.INSTANCE);
    }

    public int getFlags(boolean z) {
        return Settings.INSTANCE.getShowHiddenFiles() ? z | true ? 1 : 0 : z ? 1 : 0;
    }

    public void stop() {
        this.browserActor.m1139trySendJP2dKIU(Release.INSTANCE);
        Job job = this.discoveryJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.discoveryJob = null;
        Job job2 = this.parsingJob;
        if (job2 != null) {
            Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
        }
        this.parsingJob = null;
    }

    /* access modifiers changed from: protected */
    public final boolean clearListener() {
        return post(this.browserActor, ClearListener.INSTANCE);
    }

    public void release() {
        CoroutineScopeKt.cancel$default(this, (CancellationException) null, 1, (Object) null);
        if (this.url != null) {
            this.loading.postValue(false);
        }
    }

    public final void updateShowAllFiles(boolean z) {
        this.showOnlyMultimedia = z;
        refresh();
    }

    /* access modifiers changed from: protected */
    public final List<MediaLibraryItem> getList(String str) {
        Intrinsics.checkNotNullParameter(str, RtspHeaders.Values.URL);
        return prefetchLists.get(str);
    }

    /* access modifiers changed from: protected */
    public final List<MediaLibraryItem> removeList(String str) {
        Intrinsics.checkNotNullParameter(str, RtspHeaders.Values.URL);
        return prefetchLists.remove(str);
    }

    public final Unit saveList(MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "media");
        List list = this.foldersContentMap.get(mediaWrapper);
        if (list == null) {
            return null;
        }
        if (!list.isEmpty()) {
            Map<String, List<MediaLibraryItem>> map = prefetchLists;
            String location = mediaWrapper.getLocation();
            Intrinsics.checkNotNullExpressionValue(location, "getLocation(...)");
            map.put(location, list);
        }
        return Unit.INSTANCE;
    }

    public final boolean isFolderEmpty(MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "mw");
        List list = this.foldersContentMap.get(mediaWrapper);
        if (list != null) {
            return list.isEmpty();
        }
        return true;
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R \u0010\n\u001a\u0014\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u000bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/videolan/vlc/providers/BrowserProvider$Companion;", "Lorg/videolan/tools/DependencyProvider;", "Lorg/videolan/vlc/providers/BrowserProvider;", "()V", "browserHandler", "Landroid/os/Handler;", "getBrowserHandler", "()Landroid/os/Handler;", "browserHandler$delegate", "Lkotlin/Lazy;", "prefetchLists", "", "", "", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: BrowserProvider.kt */
    public static final class Companion extends DependencyProvider<BrowserProvider> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final Handler getBrowserHandler() {
            return (Handler) BrowserProvider.browserHandler$delegate.getValue();
        }
    }

    private final <E> boolean post(SendChannel<? super E> sendChannel, E e) {
        return CoroutineScopeKt.isActive(this) && !sendChannel.isClosedForSend() && ChannelResult.m2346isSuccessimpl(sendChannel.m1139trySendJP2dKIU(e));
    }
}
