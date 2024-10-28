package org.videolan.vlc.providers;

import android.content.Context;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.libvlc.util.MediaBrowser;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.livedata.LiveDataset;
import org.videolan.vlc.util.FileUtilsKt;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0011H\u0016J\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u0015H@¢\u0006\u0002\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\nH\u0016J\b\u0010\u001a\u001a\u00020\u000fH\u0014J\u001d\u0010\u001b\u001a\u00020\u000f2\u000e\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0011H\u0010¢\u0006\u0002\b\u001dR\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lorg/videolan/vlc/providers/FilePickerProvider;", "Lorg/videolan/vlc/providers/FileBrowserProvider;", "context", "Landroid/content/Context;", "dataset", "Lorg/videolan/tools/livedata/LiveDataset;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "url", "", "showDummyCategory", "", "pickerType", "Lorg/videolan/vlc/providers/PickerType;", "(Landroid/content/Context;Lorg/videolan/tools/livedata/LiveDataset;Ljava/lang/String;ZLorg/videolan/vlc/providers/PickerType;)V", "computeHeaders", "", "value", "", "findMedia", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "media", "Lorg/videolan/libvlc/interfaces/IMedia;", "(Lorg/videolan/libvlc/interfaces/IMedia;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFlags", "", "interact", "initBrowser", "parseSubDirectories", "list", "parseSubDirectories$vlc_android_release", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FilePickerProvider.kt */
public final class FilePickerProvider extends FileBrowserProvider {
    private final PickerType pickerType;

    public void computeHeaders(List<? extends MediaLibraryItem> list) {
        Intrinsics.checkNotNullParameter(list, "value");
    }

    public int getFlags(boolean z) {
        return z ? 2 : 3;
    }

    public void parseSubDirectories$vlc_android_release(List<? extends MediaLibraryItem> list) {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FilePickerProvider(Context context, LiveDataset<MediaLibraryItem> liveDataset, String str, boolean z, PickerType pickerType2) {
        super(context, liveDataset, str, true, z, 10, false);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(liveDataset, "dataset");
        Intrinsics.checkNotNullParameter(pickerType2, "pickerType");
        this.pickerType = pickerType2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ FilePickerProvider(Context context, LiveDataset liveDataset, String str, boolean z, PickerType pickerType2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, liveDataset, str, (i & 8) != 0 ? false : z, (i & 16) != 0 ? PickerType.SUBTITLE : pickerType2);
    }

    /* access modifiers changed from: protected */
    public void initBrowser() {
        super.initBrowser();
        MediaBrowser mediabrowser = getMediabrowser();
        if (mediabrowser != null) {
            mediabrowser.setIgnoreFileTypes("db,nfo,ini,jpg,jpeg,ljpg,gif,png,pgm,pgmyuv,pbm,pam,tga,bmp,pnm,xpm,xcf,pcx,tif,tiff,lbm,sfv");
        }
    }

    /* access modifiers changed from: protected */
    public Object findMedia(IMedia iMedia, Continuation<? super MediaWrapper> continuation) {
        MediaWrapper abstractMediaWrapper = MLServiceLocator.getAbstractMediaWrapper(iMedia);
        if (abstractMediaWrapper != null) {
            if (abstractMediaWrapper.getType() == 3) {
                return abstractMediaWrapper;
            }
            if (this.pickerType == PickerType.SUBTITLE && abstractMediaWrapper.getType() == 4) {
                return abstractMediaWrapper;
            }
            if (this.pickerType == PickerType.SOUNDFONT && FileUtilsKt.isSoundFont(abstractMediaWrapper.getUri())) {
                return abstractMediaWrapper;
            }
            if (this.pickerType == PickerType.SETTINGS && FileUtilsKt.isSettings(abstractMediaWrapper.getUri())) {
                return abstractMediaWrapper;
            }
        }
        return null;
    }
}
