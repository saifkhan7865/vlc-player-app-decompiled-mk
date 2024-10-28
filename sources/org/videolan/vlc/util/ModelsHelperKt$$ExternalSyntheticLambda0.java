package org.videolan.vlc.util;

import java.util.Comparator;
import org.videolan.medialibrary.media.MediaLibraryItem;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ModelsHelperKt$$ExternalSyntheticLambda0 implements Comparator {
    public final /* synthetic */ boolean f$0;

    public /* synthetic */ ModelsHelperKt$$ExternalSyntheticLambda0(boolean z) {
        this.f$0 = z;
    }

    public final int compare(Object obj, Object obj2) {
        return ModelsHelperKt.getTvAscComp$lambda$0(this.f$0, (MediaLibraryItem) obj, (MediaLibraryItem) obj2);
    }
}
