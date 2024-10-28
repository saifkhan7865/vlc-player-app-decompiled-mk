package org.videolan.vlc.repository;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.resources.AndroidDevices;
import org.videolan.vlc.R;
import org.videolan.vlc.util.FileUtils;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"createDirectory", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "it", "", "context", "Landroid/content/Context;", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: DirectoryRepository.kt */
public final class DirectoryRepositoryKt {
    public static final MediaWrapper createDirectory(String str, Context context) {
        Intrinsics.checkNotNullParameter(str, "it");
        Intrinsics.checkNotNullParameter(context, "context");
        MediaWrapper abstractMediaWrapper = MLServiceLocator.getAbstractMediaWrapper(AndroidUtil.PathToUri(str));
        abstractMediaWrapper.setType(3);
        if (Intrinsics.areEqual((Object) AndroidDevices.INSTANCE.getEXTERNAL_PUBLIC_DIRECTORY(), (Object) str)) {
            abstractMediaWrapper.setDisplayTitle(context.getResources().getString(R.string.internal_memory));
        } else {
            FileUtils fileUtils = FileUtils.INSTANCE;
            String title = abstractMediaWrapper.getTitle();
            Intrinsics.checkNotNullExpressionValue(title, "getTitle(...)");
            String storageTag = fileUtils.getStorageTag(title);
            if (storageTag != null) {
                abstractMediaWrapper.setDisplayTitle(storageTag);
            }
        }
        Intrinsics.checkNotNull(abstractMediaWrapper);
        return abstractMediaWrapper;
    }
}
