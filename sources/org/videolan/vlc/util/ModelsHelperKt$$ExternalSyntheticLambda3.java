package org.videolan.vlc.util;

import java.util.Comparator;
import org.videolan.medialibrary.media.MediaLibraryItem;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ModelsHelperKt$$ExternalSyntheticLambda3 implements Comparator {
    public final /* synthetic */ int f$0;

    public /* synthetic */ ModelsHelperKt$$ExternalSyntheticLambda3(int i) {
        this.f$0 = i;
    }

    public final int compare(Object obj, Object obj2) {
        return ModelsHelperKt.getFilenameDescComp$lambda$3(this.f$0, (MediaLibraryItem) obj, (MediaLibraryItem) obj2);
    }
}
