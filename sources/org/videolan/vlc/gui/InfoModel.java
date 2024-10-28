package org.videolan.vlc.gui;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.videolan.libvlc.FactoryManager;
import org.videolan.libvlc.interfaces.IComponentFactory;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.libvlc.interfaces.IMediaFactory;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH@¢\u0006\u0002\u0010\u001cJ\u0018\u0010\u0006\u001a\u00020\u00152\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020 J\u0016\u0010!\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u0016\u001a\u00020\u0017R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0007R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0007R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007¨\u0006$"}, d2 = {"Lorg/videolan/vlc/gui/InfoModel;", "Landroidx/lifecycle/ViewModel;", "()V", "cover", "Landroidx/lifecycle/MutableLiveData;", "Landroid/graphics/Bitmap;", "getCover", "()Landroidx/lifecycle/MutableLiveData;", "hasSubs", "", "getHasSubs", "mediaFactory", "Lorg/videolan/libvlc/interfaces/IMediaFactory;", "mediaTracks", "", "Lorg/videolan/libvlc/interfaces/IMedia$Track;", "getMediaTracks", "sizeText", "", "getSizeText", "checkFile", "Lkotlinx/coroutines/Job;", "mw", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "checkSubtitles", "", "itemFile", "Ljava/io/File;", "(Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "item", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "width", "", "parseTracks", "context", "Landroid/content/Context;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: InfoActivity.kt */
public final class InfoModel extends ViewModel {
    private final MutableLiveData<Bitmap> cover = new MutableLiveData<>();
    private final MutableLiveData<Boolean> hasSubs = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public final IMediaFactory mediaFactory;
    private final MutableLiveData<List<IMedia.Track>> mediaTracks = new MutableLiveData<>();
    private final MutableLiveData<Long> sizeText = new MutableLiveData<>();

    public InfoModel() {
        IComponentFactory factory = FactoryManager.getFactory(IMediaFactory.factoryId);
        Intrinsics.checkNotNull(factory, "null cannot be cast to non-null type org.videolan.libvlc.interfaces.IMediaFactory");
        this.mediaFactory = (IMediaFactory) factory;
    }

    public final MutableLiveData<Boolean> getHasSubs() {
        return this.hasSubs;
    }

    public final MutableLiveData<List<IMedia.Track>> getMediaTracks() {
        return this.mediaTracks;
    }

    public final MutableLiveData<Long> getSizeText() {
        return this.sizeText;
    }

    public final MutableLiveData<Bitmap> getCover() {
        return this.cover;
    }

    public final Job getCover(MediaLibraryItem mediaLibraryItem, int i) {
        return BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), (CoroutineContext) null, (CoroutineStart) null, new InfoModel$getCover$1(mediaLibraryItem, this, i, (Continuation<? super InfoModel$getCover$1>) null), 3, (Object) null);
    }

    public final Job parseTracks(Context context, MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mediaWrapper, "mw");
        return BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), (CoroutineContext) null, (CoroutineStart) null, new InfoModel$parseTracks$1(this, context, mediaWrapper, (Continuation<? super InfoModel$parseTracks$1>) null), 3, (Object) null);
    }

    public final Job checkFile(MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "mw");
        return BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), (CoroutineContext) null, (CoroutineStart) null, new InfoModel$checkFile$1(this, mediaWrapper, (Continuation<? super InfoModel$checkFile$1>) null), 3, (Object) null);
    }

    /* access modifiers changed from: private */
    public final Object checkSubtitles(File file, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new InfoModel$checkSubtitles$2(file, this, (Continuation<? super InfoModel$checkSubtitles$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }
}
