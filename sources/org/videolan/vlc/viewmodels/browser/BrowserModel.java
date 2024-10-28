package org.videolan.vlc.viewmodels.browser;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.lifecycle.ViewModelProvider;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.Tools;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.Constants;
import org.videolan.tools.CoroutineContextProvider;
import org.videolan.tools.Settings;
import org.videolan.vlc.ArtworkProvider;
import org.videolan.vlc.providers.BrowserProvider;
import org.videolan.vlc.providers.PickerType;
import org.videolan.vlc.repository.DirectoryRepository;
import org.videolan.vlc.viewmodels.BaseModel;
import org.videolan.vlc.viewmodels.tv.TvBrowserModel;

@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001d\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u00032\u00020\u0004:\u0001UB;\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010¢\u0006\u0002\u0010\u0011J\u000e\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\bJ\u0019\u0010,\u001a\u00020-2\u0006\u0010+\u001a\u00020\b2\u0006\u0010.\u001a\u00020/H\u0001J\u0006\u00100\u001a\u00020\fJ\b\u00101\u001a\u00020\fH\u0016J\t\u00102\u001a\u00020-H\u0001J\u0016\u00103\u001a\u00020\f2\u0006\u0010+\u001a\u00020\bH@¢\u0006\u0002\u00104J\u000e\u00105\u001a\u00020*2\u0006\u0010+\u001a\u00020\bJ\u000b\u00106\u001a\u0004\u0018\u000107H\u0001J\u0018\u00108\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\b090\u0018J\u000b\u0010:\u001a\u0004\u0018\u00010\u0002H\u0001J\u000e\u0010;\u001a\u00020\f2\u0006\u0010<\u001a\u000207J\u0011\u0010=\u001a\u00020\b2\u0006\u0010+\u001a\u00020\bH\u0001J\b\u0010>\u001a\u00020-H\u0014J\u0006\u0010?\u001a\u00020-J\b\u0010@\u001a\u00020-H\u0016J\u0006\u0010A\u001a\u00020-J\u0016\u0010B\u001a\u00020-2\u0006\u0010<\u001a\u0002072\u0006\u0010C\u001a\u00020\nJ\u0011\u0010D\u001a\u00020\b2\u0006\u0010+\u001a\u00020\bH\u0001J\u0006\u0010E\u001a\u00020-J\u0011\u0010F\u001a\u00020\b2\u0006\u0010G\u001a\u00020\bH\u0001J\u0015\u0010H\u001a\u0004\u0018\u00010-2\u0006\u0010I\u001a\u000207¢\u0006\u0002\u0010JJ\u0006\u0010K\u001a\u00020-J\u0013\u0010L\u001a\u00020-2\b\u0010I\u001a\u0004\u0018\u000107H\u0001J\u0013\u0010M\u001a\u00020-2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002H\u0001J\u0010\u0010N\u001a\u00020-2\u0006\u0010N\u001a\u00020\u001cH\u0017J\u0006\u0010O\u001a\u00020-J\u0016\u0010P\u001a\u00020-2\u0006\u0010+\u001a\u00020\bH@¢\u0006\u0002\u00104J\u0016\u0010Q\u001a\u00020-2\u0006\u0010<\u001a\u000207H@¢\u0006\u0002\u0010RJ\u000e\u0010S\u001a\u00020-2\u0006\u0010T\u001a\u00020\fR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0002X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\f0\u0018X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\"X\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(¨\u0006V"}, d2 = {"Lorg/videolan/vlc/viewmodels/browser/BrowserModel;", "Lorg/videolan/vlc/viewmodels/BaseModel;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "Lorg/videolan/vlc/viewmodels/tv/TvBrowserModel;", "Lorg/videolan/vlc/viewmodels/browser/IPathOperationDelegate;", "context", "Landroid/content/Context;", "url", "", "type", "", "showDummyCategory", "", "pickerType", "Lorg/videolan/vlc/providers/PickerType;", "coroutineContextProvider", "Lorg/videolan/tools/CoroutineContextProvider;", "(Landroid/content/Context;Ljava/lang/String;JZLorg/videolan/vlc/providers/PickerType;Lorg/videolan/tools/CoroutineContextProvider;)V", "currentItem", "getCurrentItem", "()Lorg/videolan/medialibrary/media/MediaLibraryItem;", "setCurrentItem", "(Lorg/videolan/medialibrary/media/MediaLibraryItem;)V", "loading", "Landroidx/lifecycle/MutableLiveData;", "getLoading", "()Landroidx/lifecycle/MutableLiveData;", "nbColumns", "", "getNbColumns", "()I", "setNbColumns", "(I)V", "provider", "Lorg/videolan/vlc/providers/BrowserProvider;", "getProvider", "()Lorg/videolan/vlc/providers/BrowserProvider;", "getType", "()J", "getUrl", "()Ljava/lang/String;", "addCustomDirectory", "Lkotlinx/coroutines/Job;", "path", "appendPathToUri", "", "uri", "Landroid/net/Uri$Builder;", "browseRoot", "canSortByFileNameName", "consumeSource", "customDirectoryExists", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteCustomDirectory", "getAndRemoveDestination", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "getDescriptionUpdate", "Lkotlin/Pair;", "getSource", "isFolderEmpty", "mw", "makePathSafe", "onCleared", "reSort", "refresh", "refreshMW", "refreshMedia", "timeChanged", "replaceStoragePath", "resetSort", "retrieveSafePath", "encoded", "saveList", "media", "(Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;)Lkotlin/Unit;", "saveSort", "setDestination", "setSource", "sort", "stop", "toggleBanState", "updateMediaPlayed", "(Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateShowAllFiles", "value", "Factory", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BrowserModel.kt */
public class BrowserModel extends BaseModel<MediaLibraryItem> implements TvBrowserModel<MediaLibraryItem>, IPathOperationDelegate {
    private final /* synthetic */ PathOperationDelegate $$delegate_0;
    private MediaLibraryItem currentItem;
    private final MutableLiveData<Boolean> loading;
    private int nbColumns;
    private final BrowserProvider provider;
    private final boolean showDummyCategory;
    private final long type;
    private final String url;

    public void appendPathToUri(String str, Uri.Builder builder) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(builder, Constants.KEY_URI);
        this.$$delegate_0.appendPathToUri(str, builder);
    }

    public boolean canSortByFileNameName() {
        return true;
    }

    public void consumeSource() {
        this.$$delegate_0.consumeSource();
    }

    public MediaWrapper getAndRemoveDestination() {
        return this.$$delegate_0.getAndRemoveDestination();
    }

    public MediaLibraryItem getSource() {
        return this.$$delegate_0.getSource();
    }

    public String makePathSafe(String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        String makePathSafe = this.$$delegate_0.makePathSafe(str);
        Intrinsics.checkNotNullExpressionValue(makePathSafe, "makePathSafe(...)");
        return makePathSafe;
    }

    public String replaceStoragePath(String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        return this.$$delegate_0.replaceStoragePath(str);
    }

    public String retrieveSafePath(String str) {
        Intrinsics.checkNotNullParameter(str, "encoded");
        return this.$$delegate_0.retrieveSafePath(str);
    }

    public void setDestination(MediaWrapper mediaWrapper) {
        this.$$delegate_0.setDestination(mediaWrapper);
    }

    public void setSource(MediaLibraryItem mediaLibraryItem) {
        this.$$delegate_0.setSource(mediaLibraryItem);
    }

    public final String getUrl() {
        return this.url;
    }

    public final long getType() {
        return this.type;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BrowserModel(Context context, String str, long j, boolean z, PickerType pickerType, CoroutineContextProvider coroutineContextProvider, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, str, j, z, (i & 16) != 0 ? PickerType.SUBTITLE : pickerType, (i & 32) != 0 ? new CoroutineContextProvider() : coroutineContextProvider);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BrowserModel(android.content.Context r14, java.lang.String r15, long r16, boolean r18, org.videolan.vlc.providers.PickerType r19, org.videolan.tools.CoroutineContextProvider r20) {
        /*
            r13 = this;
            r0 = r13
            r2 = r14
            r4 = r15
            r5 = r16
            r1 = r20
            java.lang.String r3 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r3)
            java.lang.String r3 = "pickerType"
            r7 = r19
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r3)
            java.lang.String r3 = "coroutineContextProvider"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r3)
            r13.<init>(r14, r1)
            r0.url = r4
            r0.type = r5
            r8 = r18
            r0.showDummyCategory = r8
            org.videolan.vlc.viewmodels.browser.PathOperationDelegate r1 = new org.videolan.vlc.viewmodels.browser.PathOperationDelegate
            r1.<init>()
            r0.$$delegate_0 = r1
            r9 = 2
            int r1 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r1 != 0) goto L_0x0047
            org.videolan.vlc.providers.FilePickerProvider r9 = new org.videolan.vlc.providers.FilePickerProvider
            org.videolan.tools.livedata.LiveDataset r3 = r13.getDataset()
            r8 = 8
            r10 = 0
            r5 = 0
            r1 = r9
            r2 = r14
            r4 = r15
            r6 = r19
            r7 = r8
            r8 = r10
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)
            org.videolan.vlc.providers.BrowserProvider r9 = (org.videolan.vlc.providers.BrowserProvider) r9
            goto L_0x008d
        L_0x0047:
            r9 = 1
            int r1 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r1 != 0) goto L_0x005a
            org.videolan.vlc.providers.NetworkProvider r1 = new org.videolan.vlc.providers.NetworkProvider
            org.videolan.tools.livedata.LiveDataset r3 = r13.getDataset()
            r1.<init>(r14, r3, r15)
            r9 = r1
            org.videolan.vlc.providers.BrowserProvider r9 = (org.videolan.vlc.providers.BrowserProvider) r9
            goto L_0x008d
        L_0x005a:
            r9 = 3
            int r1 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r1 != 0) goto L_0x006d
            org.videolan.vlc.providers.StorageProvider r1 = new org.videolan.vlc.providers.StorageProvider
            org.videolan.tools.livedata.LiveDataset r3 = r13.getDataset()
            r1.<init>(r14, r3, r15)
            r9 = r1
            org.videolan.vlc.providers.BrowserProvider r9 = (org.videolan.vlc.providers.BrowserProvider) r9
            goto L_0x008d
        L_0x006d:
            org.videolan.vlc.providers.FileBrowserProvider r11 = new org.videolan.vlc.providers.FileBrowserProvider
            org.videolan.tools.livedata.LiveDataset r3 = r13.getDataset()
            int r7 = r13.getSort()
            boolean r9 = r13.getDesc()
            r10 = 8
            r12 = 0
            r5 = 0
            r1 = r11
            r2 = r14
            r4 = r15
            r6 = r18
            r8 = r9
            r9 = r10
            r10 = r12
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10)
            r9 = r11
            org.videolan.vlc.providers.BrowserProvider r9 = (org.videolan.vlc.providers.BrowserProvider) r9
        L_0x008d:
            r0.provider = r9
            org.videolan.vlc.providers.BrowserProvider r1 = r13.getProvider()
            androidx.lifecycle.MutableLiveData r1 = r1.getLoading()
            r0.loading = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.viewmodels.browser.BrowserModel.<init>(android.content.Context, java.lang.String, long, boolean, org.videolan.vlc.providers.PickerType, org.videolan.tools.CoroutineContextProvider):void");
    }

    public MediaLibraryItem getCurrentItem() {
        return this.currentItem;
    }

    public void setCurrentItem(MediaLibraryItem mediaLibraryItem) {
        this.currentItem = mediaLibraryItem;
    }

    public int getNbColumns() {
        return this.nbColumns;
    }

    public void setNbColumns(int i) {
        this.nbColumns = i;
    }

    public BrowserProvider getProvider() {
        return this.provider;
    }

    public MutableLiveData<Boolean> getLoading() {
        return this.loading;
    }

    public void refresh() {
        getProvider().refresh();
    }

    public final void refreshMW() {
        getProvider().refresh();
    }

    public final boolean browseRoot() {
        return getProvider().browseRoot();
    }

    public final void reSort() {
        Job unused = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), (CoroutineContext) null, (CoroutineStart) null, new BrowserModel$reSort$1(this, (Continuation<? super BrowserModel$reSort$1>) null), 3, (Object) null);
    }

    public final void resetSort() {
        setSort(getSettings().getInt(getSortKey(), 0));
        SharedPreferences settings = getSettings();
        setDesc(settings.getBoolean(getSortKey() + "_desc", false));
        getProvider().setDesc(getDesc());
        getProvider().setSort(getSort());
    }

    public void sort(int i) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), (CoroutineContext) null, (CoroutineStart) null, new BrowserModel$sort$1(this, i, (Continuation<? super BrowserModel$sort$1>) null), 3, (Object) null);
    }

    public final Unit saveList(MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "media");
        return getProvider().saveList(mediaWrapper);
    }

    public final boolean isFolderEmpty(MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "mw");
        return getProvider().isFolderEmpty(mediaWrapper);
    }

    public final MutableLiveData<Pair<Integer, String>> getDescriptionUpdate() {
        return getProvider().getDescriptionUpdate();
    }

    public final void stop() {
        getProvider().stop();
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        getProvider().release();
        super.onCleared();
    }

    public final void updateShowAllFiles(boolean z) {
        getProvider().updateShowAllFiles(z);
    }

    public final Job addCustomDirectory(String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        return ((DirectoryRepository) DirectoryRepository.Companion.getInstance(getContext())).addCustomDirectory(str);
    }

    public final Job deleteCustomDirectory(String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        return ((DirectoryRepository) DirectoryRepository.Companion.getInstance(getContext())).deleteCustomDirectory(str);
    }

    public final Object customDirectoryExists(String str, Continuation<? super Boolean> continuation) {
        return ((DirectoryRepository) DirectoryRepository.Companion.getInstance(getContext())).customDirectoryExists(str, continuation);
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ%\u0010\u0011\u001a\u0002H\u0012\"\b\b\u0000\u0010\u0012*\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0015H\u0016¢\u0006\u0002\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0017"}, d2 = {"Lorg/videolan/vlc/viewmodels/browser/BrowserModel$Factory;", "Landroidx/lifecycle/ViewModelProvider$NewInstanceFactory;", "context", "Landroid/content/Context;", "url", "", "type", "", "showDummyCategory", "", "pickerType", "Lorg/videolan/vlc/providers/PickerType;", "(Landroid/content/Context;Ljava/lang/String;JZLorg/videolan/vlc/providers/PickerType;)V", "getContext", "()Landroid/content/Context;", "getUrl", "()Ljava/lang/String;", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: BrowserModel.kt */
    public static final class Factory extends ViewModelProvider.NewInstanceFactory {
        private final Context context;
        private final PickerType pickerType;
        private final boolean showDummyCategory;
        private final long type;
        private final String url;

        public Factory(Context context2, String str, long j, boolean z, PickerType pickerType2) {
            Intrinsics.checkNotNullParameter(context2, "context");
            Intrinsics.checkNotNullParameter(pickerType2, "pickerType");
            this.context = context2;
            this.url = str;
            this.type = j;
            this.showDummyCategory = z;
            this.pickerType = pickerType2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Factory(Context context2, String str, long j, boolean z, PickerType pickerType2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(context2, str, j, (i & 8) != 0 ? true : z, (i & 16) != 0 ? PickerType.SUBTITLE : pickerType2);
        }

        public final Context getContext() {
            return this.context;
        }

        public final String getUrl() {
            return this.url;
        }

        public <T extends ViewModel> T create(Class<T> cls) {
            Intrinsics.checkNotNullParameter(cls, "modelClass");
            Context applicationContext = this.context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            return (ViewModel) new BrowserModel(applicationContext, this.url, this.type, this.showDummyCategory, this.pickerType, (CoroutineContextProvider) null, 32, (DefaultConstructorMarker) null);
        }
    }

    public final Object toggleBanState(String str, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new BrowserModel$toggleBanState$2(str, (Continuation<? super BrowserModel$toggleBanState$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    public final void refreshMedia(MediaWrapper mediaWrapper, long j) {
        MediaWrapper mediaWrapper2;
        Object obj;
        Intrinsics.checkNotNullParameter(mediaWrapper, "mw");
        Iterator it = getDataset().getList().iterator();
        while (true) {
            mediaWrapper2 = null;
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((MediaLibraryItem) obj).getId() == mediaWrapper.getId()) {
                break;
            }
        }
        if (obj instanceof MediaWrapper) {
            mediaWrapper2 = (MediaWrapper) obj;
        }
        if (mediaWrapper2 != null) {
            mediaWrapper2.setDisplayTime(j);
            mediaWrapper2.setTime(j);
            Tools.setMediaDescription(mediaWrapper2);
        }
    }

    public final Object updateMediaPlayed(MediaWrapper mediaWrapper, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new BrowserModel$updateMediaPlayed$2(this, mediaWrapper, (Continuation<? super BrowserModel$updateMediaPlayed$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    public final void saveSort() {
        SharedPreferences.Editor edit = ((SharedPreferences) Settings.INSTANCE.getInstance(getContext())).edit();
        edit.putInt(getSortKey(), getSort());
        edit.putBoolean(getSortKey() + "_desc", getDesc());
        edit.apply();
    }
}
