package org.videolan.vlc.util;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Ljava/util/Comparator;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ModelsHelper.kt */
final class ModelsHelperKt$descComp$2 extends Lambda implements Function0<Comparator<MediaLibraryItem>> {
    public static final ModelsHelperKt$descComp$2 INSTANCE = new ModelsHelperKt$descComp$2();

    ModelsHelperKt$descComp$2() {
        super(0);
    }

    public final Comparator<MediaLibraryItem> invoke() {
        return new ModelsHelperKt$descComp$2$$ExternalSyntheticLambda0();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005f, code lost:
        if (r4 == null) goto L_0x0061;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final int invoke$lambda$0(org.videolan.medialibrary.media.MediaLibraryItem r4, org.videolan.medialibrary.media.MediaLibraryItem r5) {
        /*
            r0 = -1
            if (r4 == 0) goto L_0x002d
            int r1 = r4.getItemType()
            r2 = 32
            if (r1 != r2) goto L_0x002d
            java.lang.String r1 = "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4, r1)
            r2 = r4
            org.videolan.medialibrary.interfaces.media.MediaWrapper r2 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r2
            int r2 = r2.getType()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5, r1)
            r1 = r5
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r1
            int r1 = r1.getType()
            r3 = 3
            if (r2 != r3) goto L_0x0027
            if (r1 == r3) goto L_0x0027
            return r0
        L_0x0027:
            if (r2 == r3) goto L_0x002d
            if (r1 != r3) goto L_0x002d
            r4 = 1
            return r4
        L_0x002d:
            if (r5 == 0) goto L_0x0067
            java.lang.String r5 = r5.getTitle()
            if (r5 == 0) goto L_0x0067
            java.util.Locale r1 = java.util.Locale.getDefault()
            java.lang.String r2 = "getDefault(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            java.lang.String r5 = r5.toLowerCase(r1)
            java.lang.String r1 = "toLowerCase(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r1)
            if (r5 == 0) goto L_0x0067
            if (r4 == 0) goto L_0x0061
            java.lang.String r4 = r4.getTitle()
            if (r4 == 0) goto L_0x0061
            java.util.Locale r0 = java.util.Locale.getDefault()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)
            java.lang.String r4 = r4.toLowerCase(r0)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r1)
            if (r4 != 0) goto L_0x0063
        L_0x0061:
            java.lang.String r4 = ""
        L_0x0063:
            int r0 = r5.compareTo(r4)
        L_0x0067:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.ModelsHelperKt$descComp$2.invoke$lambda$0(org.videolan.medialibrary.media.MediaLibraryItem, org.videolan.medialibrary.media.MediaLibraryItem):int");
    }
}
