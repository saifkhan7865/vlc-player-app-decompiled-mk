package org.videolan.vlc.util;

import java.util.Comparator;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.medialibrary.media.Storage;
import org.videolan.tools.Settings;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0010\t\n\u0000\u001a\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f\u001a\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f\u001a\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0010\u001a\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0010\u001a\n\u0010\u0012\u001a\u00020\u0013*\u00020\u0014\"!\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00018FX\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004\"!\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u00018FX\u0002¢\u0006\f\n\u0004\b\t\u0010\u0006\u001a\u0004\b\b\u0010\u0004¨\u0006\u0015"}, d2 = {"ascComp", "Ljava/util/Comparator;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "getAscComp", "()Ljava/util/Comparator;", "ascComp$delegate", "Lkotlin/Lazy;", "descComp", "getDescComp", "descComp$delegate", "getFilenameAscComp", "nbOfDigits", "", "getFilenameDescComp", "getTvAscComp", "foldersFirst", "", "getTvDescComp", "lengthToCategory", "", "", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: ModelsHelper.kt */
public final class ModelsHelperKt {
    private static final Lazy ascComp$delegate = LazyKt.lazy(ModelsHelperKt$ascComp$2.INSTANCE);
    private static final Lazy descComp$delegate = LazyKt.lazy(ModelsHelperKt$descComp$2.INSTANCE);

    public static final String lengthToCategory(long j) {
        if (j == 0) {
            return "-";
        }
        if (j < 60000) {
            return "< 1 min";
        }
        if (j < 600000) {
            int floor = (int) Math.floor((double) (j / ((long) 60000)));
            return floor + " - " + (floor + 1) + " min";
        } else if (j < 3600000) {
            double d = (double) 10;
            double floor2 = Math.floor((double) (j / ((long) 600000)));
            Double.isNaN(d);
            int i = (int) (d * floor2);
            return i + " - " + (i + 10) + " min";
        } else {
            int floor3 = (int) Math.floor((double) (j / ((long) 3600000)));
            return floor3 + " - " + (floor3 + 1) + " h";
        }
    }

    public static final Comparator<MediaLibraryItem> getAscComp() {
        return (Comparator) ascComp$delegate.getValue();
    }

    public static final Comparator<MediaLibraryItem> getDescComp() {
        return (Comparator) descComp$delegate.getValue();
    }

    public static final Comparator<MediaLibraryItem> getTvAscComp(boolean z) {
        return new ModelsHelperKt$$ExternalSyntheticLambda0(z);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0086, code lost:
        if (r6 == null) goto L_0x0088;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final int getTvAscComp$lambda$0(boolean r4, org.videolan.medialibrary.media.MediaLibraryItem r5, org.videolan.medialibrary.media.MediaLibraryItem r6) {
        /*
            r0 = -1
            if (r4 == 0) goto L_0x0054
            boolean r4 = r5 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
            r1 = 0
            if (r4 == 0) goto L_0x000c
            r4 = r5
            org.videolan.medialibrary.interfaces.media.MediaWrapper r4 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r4
            goto L_0x000d
        L_0x000c:
            r4 = r1
        L_0x000d:
            if (r4 == 0) goto L_0x0018
            int r4 = r4.getType()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            goto L_0x0019
        L_0x0018:
            r4 = r1
        L_0x0019:
            boolean r2 = r6 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
            if (r2 == 0) goto L_0x0021
            r2 = r6
            org.videolan.medialibrary.interfaces.media.MediaWrapper r2 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r2
            goto L_0x0022
        L_0x0021:
            r2 = r1
        L_0x0022:
            if (r2 == 0) goto L_0x002c
            int r1 = r2.getType()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
        L_0x002c:
            r2 = 3
            if (r4 != 0) goto L_0x0030
            goto L_0x0040
        L_0x0030:
            int r3 = r4.intValue()
            if (r3 != r2) goto L_0x0040
            if (r1 != 0) goto L_0x0039
            goto L_0x003f
        L_0x0039:
            int r3 = r1.intValue()
            if (r3 == r2) goto L_0x0040
        L_0x003f:
            return r0
        L_0x0040:
            if (r4 != 0) goto L_0x0043
            goto L_0x0049
        L_0x0043:
            int r4 = r4.intValue()
            if (r4 == r2) goto L_0x0054
        L_0x0049:
            if (r1 != 0) goto L_0x004c
            goto L_0x0054
        L_0x004c:
            int r4 = r1.intValue()
            if (r4 != r2) goto L_0x0054
            r4 = 1
            return r4
        L_0x0054:
            if (r5 == 0) goto L_0x008e
            java.lang.String r4 = r5.getTitle()
            if (r4 == 0) goto L_0x008e
            java.util.Locale r5 = java.util.Locale.getDefault()
            java.lang.String r1 = "getDefault(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r1)
            java.lang.String r4 = r4.toLowerCase(r5)
            java.lang.String r5 = "toLowerCase(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)
            if (r4 == 0) goto L_0x008e
            if (r6 == 0) goto L_0x0088
            java.lang.String r6 = r6.getTitle()
            if (r6 == 0) goto L_0x0088
            java.util.Locale r0 = java.util.Locale.getDefault()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.String r6 = r6.toLowerCase(r0)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r5)
            if (r6 != 0) goto L_0x008a
        L_0x0088:
            java.lang.String r6 = ""
        L_0x008a:
            int r0 = r4.compareTo(r6)
        L_0x008e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.ModelsHelperKt.getTvAscComp$lambda$0(boolean, org.videolan.medialibrary.media.MediaLibraryItem, org.videolan.medialibrary.media.MediaLibraryItem):int");
    }

    public static final Comparator<MediaLibraryItem> getTvDescComp(boolean z) {
        return new ModelsHelperKt$$ExternalSyntheticLambda1(z);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0086, code lost:
        if (r5 == null) goto L_0x0088;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final int getTvDescComp$lambda$1(boolean r4, org.videolan.medialibrary.media.MediaLibraryItem r5, org.videolan.medialibrary.media.MediaLibraryItem r6) {
        /*
            r0 = -1
            if (r4 == 0) goto L_0x0054
            boolean r4 = r5 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
            r1 = 0
            if (r4 == 0) goto L_0x000c
            r4 = r5
            org.videolan.medialibrary.interfaces.media.MediaWrapper r4 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r4
            goto L_0x000d
        L_0x000c:
            r4 = r1
        L_0x000d:
            if (r4 == 0) goto L_0x0018
            int r4 = r4.getType()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            goto L_0x0019
        L_0x0018:
            r4 = r1
        L_0x0019:
            boolean r2 = r6 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
            if (r2 == 0) goto L_0x0021
            r2 = r6
            org.videolan.medialibrary.interfaces.media.MediaWrapper r2 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r2
            goto L_0x0022
        L_0x0021:
            r2 = r1
        L_0x0022:
            if (r2 == 0) goto L_0x002c
            int r1 = r2.getType()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
        L_0x002c:
            r2 = 3
            if (r4 != 0) goto L_0x0030
            goto L_0x0040
        L_0x0030:
            int r3 = r4.intValue()
            if (r3 != r2) goto L_0x0040
            if (r1 != 0) goto L_0x0039
            goto L_0x003f
        L_0x0039:
            int r3 = r1.intValue()
            if (r3 == r2) goto L_0x0040
        L_0x003f:
            return r0
        L_0x0040:
            if (r4 != 0) goto L_0x0043
            goto L_0x0049
        L_0x0043:
            int r4 = r4.intValue()
            if (r4 == r2) goto L_0x0054
        L_0x0049:
            if (r1 != 0) goto L_0x004c
            goto L_0x0054
        L_0x004c:
            int r4 = r1.intValue()
            if (r4 != r2) goto L_0x0054
            r4 = 1
            return r4
        L_0x0054:
            if (r6 == 0) goto L_0x008e
            java.lang.String r4 = r6.getTitle()
            if (r4 == 0) goto L_0x008e
            java.util.Locale r6 = java.util.Locale.getDefault()
            java.lang.String r1 = "getDefault(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r1)
            java.lang.String r4 = r4.toLowerCase(r6)
            java.lang.String r6 = "toLowerCase(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r6)
            if (r4 == 0) goto L_0x008e
            if (r5 == 0) goto L_0x0088
            java.lang.String r5 = r5.getTitle()
            if (r5 == 0) goto L_0x0088
            java.util.Locale r0 = java.util.Locale.getDefault()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.String r5 = r5.toLowerCase(r0)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            if (r5 != 0) goto L_0x008a
        L_0x0088:
            java.lang.String r5 = ""
        L_0x008a:
            int r0 = r4.compareTo(r5)
        L_0x008e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.ModelsHelperKt.getTvDescComp$lambda$1(boolean, org.videolan.medialibrary.media.MediaLibraryItem, org.videolan.medialibrary.media.MediaLibraryItem):int");
    }

    public static final Comparator<MediaLibraryItem> getFilenameAscComp(int i) {
        return new ModelsHelperKt$$ExternalSyntheticLambda2(i);
    }

    /* access modifiers changed from: private */
    public static final int getFilenameAscComp$lambda$2(int i, MediaLibraryItem mediaLibraryItem, MediaLibraryItem mediaLibraryItem2) {
        String str;
        String str2;
        String str3;
        String str4 = null;
        if (Settings.INSTANCE.getShowTvUi() && Settings.INSTANCE.getTvFoldersFirst()) {
            MediaWrapper mediaWrapper = mediaLibraryItem instanceof MediaWrapper ? (MediaWrapper) mediaLibraryItem : null;
            Integer valueOf = mediaWrapper != null ? Integer.valueOf(mediaWrapper.getType()) : null;
            MediaWrapper mediaWrapper2 = mediaLibraryItem2 instanceof MediaWrapper ? (MediaWrapper) mediaLibraryItem2 : null;
            Integer valueOf2 = mediaWrapper2 != null ? Integer.valueOf(mediaWrapper2.getType()) : null;
            if (valueOf != null && valueOf.intValue() == 3 && (valueOf2 == null || valueOf2.intValue() != 3)) {
                return -1;
            }
            if ((valueOf == null || valueOf.intValue() != 3) && valueOf2 != null && valueOf2.intValue() == 3) {
                return 1;
            }
        }
        boolean z = mediaLibraryItem instanceof MediaWrapper;
        MediaWrapper mediaWrapper3 = z ? (MediaWrapper) mediaLibraryItem : null;
        Integer valueOf3 = mediaWrapper3 != null ? Integer.valueOf(mediaWrapper3.getType()) : null;
        boolean z2 = mediaLibraryItem2 instanceof MediaWrapper;
        MediaWrapper mediaWrapper4 = z2 ? (MediaWrapper) mediaLibraryItem2 : null;
        Integer valueOf4 = mediaWrapper4 != null ? Integer.valueOf(mediaWrapper4.getType()) : null;
        if (valueOf3 != null && valueOf3.intValue() == 3 && (valueOf4 == null || valueOf4.intValue() != 3)) {
            return -1;
        }
        if ((valueOf3 == null || valueOf3.intValue() != 3) && valueOf4 != null && valueOf4.intValue() == 3) {
            return 1;
        }
        MediaWrapper mediaWrapper5 = z ? (MediaWrapper) mediaLibraryItem : null;
        if (mediaWrapper5 == null || (str = mediaWrapper5.getFileName()) == null) {
            Storage storage = mediaLibraryItem instanceof Storage ? (Storage) mediaLibraryItem : null;
            str = storage != null ? storage.getTitle() : null;
        }
        MediaWrapper mediaWrapper6 = z2 ? (MediaWrapper) mediaLibraryItem2 : null;
        if (mediaWrapper6 == null || (str2 = mediaWrapper6.getFileName()) == null) {
            Storage storage2 = mediaLibraryItem2 instanceof Storage ? (Storage) mediaLibraryItem2 : null;
            str2 = storage2 != null ? storage2.getTitle() : null;
        }
        if (str != null) {
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
            str3 = str.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(str3, "toLowerCase(...)");
        } else {
            str3 = null;
        }
        String sanitizeStringForAlphaCompare = KextensionsKt.sanitizeStringForAlphaCompare(str3, i);
        if (sanitizeStringForAlphaCompare == null) {
            return -1;
        }
        if (str2 != null) {
            Locale locale2 = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale2, "getDefault(...)");
            str4 = str2.toLowerCase(locale2);
            Intrinsics.checkNotNullExpressionValue(str4, "toLowerCase(...)");
        }
        String sanitizeStringForAlphaCompare2 = KextensionsKt.sanitizeStringForAlphaCompare(str4, i);
        if (sanitizeStringForAlphaCompare2 == null) {
            sanitizeStringForAlphaCompare2 = "";
        }
        return sanitizeStringForAlphaCompare.compareTo(sanitizeStringForAlphaCompare2);
    }

    public static final Comparator<MediaLibraryItem> getFilenameDescComp(int i) {
        return new ModelsHelperKt$$ExternalSyntheticLambda3(i);
    }

    /* access modifiers changed from: private */
    public static final int getFilenameDescComp$lambda$3(int i, MediaLibraryItem mediaLibraryItem, MediaLibraryItem mediaLibraryItem2) {
        String str;
        String str2;
        String str3;
        String str4 = null;
        if (Settings.INSTANCE.getShowTvUi() && Settings.INSTANCE.getTvFoldersFirst()) {
            MediaWrapper mediaWrapper = mediaLibraryItem instanceof MediaWrapper ? (MediaWrapper) mediaLibraryItem : null;
            Integer valueOf = mediaWrapper != null ? Integer.valueOf(mediaWrapper.getType()) : null;
            MediaWrapper mediaWrapper2 = mediaLibraryItem2 instanceof MediaWrapper ? (MediaWrapper) mediaLibraryItem2 : null;
            Integer valueOf2 = mediaWrapper2 != null ? Integer.valueOf(mediaWrapper2.getType()) : null;
            if (valueOf != null && valueOf.intValue() == 3 && (valueOf2 == null || valueOf2.intValue() != 3)) {
                return -1;
            }
            if ((valueOf == null || valueOf.intValue() != 3) && valueOf2 != null && valueOf2.intValue() == 3) {
                return 1;
            }
        }
        boolean z = mediaLibraryItem instanceof MediaWrapper;
        MediaWrapper mediaWrapper3 = z ? (MediaWrapper) mediaLibraryItem : null;
        Integer valueOf3 = mediaWrapper3 != null ? Integer.valueOf(mediaWrapper3.getType()) : null;
        boolean z2 = mediaLibraryItem2 instanceof MediaWrapper;
        MediaWrapper mediaWrapper4 = z2 ? (MediaWrapper) mediaLibraryItem2 : null;
        Integer valueOf4 = mediaWrapper4 != null ? Integer.valueOf(mediaWrapper4.getType()) : null;
        if (valueOf3 != null && valueOf3.intValue() == 3 && (valueOf4 == null || valueOf4.intValue() != 3)) {
            return -1;
        }
        if ((valueOf3 == null || valueOf3.intValue() != 3) && valueOf4 != null && valueOf4.intValue() == 3) {
            return 1;
        }
        MediaWrapper mediaWrapper5 = z ? (MediaWrapper) mediaLibraryItem : null;
        if (mediaWrapper5 == null || (str = mediaWrapper5.getFileName()) == null) {
            Storage storage = mediaLibraryItem instanceof Storage ? (Storage) mediaLibraryItem : null;
            str = storage != null ? storage.getTitle() : null;
        }
        MediaWrapper mediaWrapper6 = z2 ? (MediaWrapper) mediaLibraryItem2 : null;
        if (mediaWrapper6 == null || (str2 = mediaWrapper6.getFileName()) == null) {
            Storage storage2 = mediaLibraryItem2 instanceof Storage ? (Storage) mediaLibraryItem2 : null;
            str2 = storage2 != null ? storage2.getTitle() : null;
        }
        if (str2 != null) {
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
            str3 = str2.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(str3, "toLowerCase(...)");
        } else {
            str3 = null;
        }
        String sanitizeStringForAlphaCompare = KextensionsKt.sanitizeStringForAlphaCompare(str3, i);
        if (sanitizeStringForAlphaCompare == null) {
            return -1;
        }
        if (str != null) {
            Locale locale2 = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale2, "getDefault(...)");
            str4 = str.toLowerCase(locale2);
            Intrinsics.checkNotNullExpressionValue(str4, "toLowerCase(...)");
        }
        String sanitizeStringForAlphaCompare2 = KextensionsKt.sanitizeStringForAlphaCompare(str4, i);
        if (sanitizeStringForAlphaCompare2 == null) {
            sanitizeStringForAlphaCompare2 = "";
        }
        return sanitizeStringForAlphaCompare.compareTo(sanitizeStringForAlphaCompare2);
    }
}
