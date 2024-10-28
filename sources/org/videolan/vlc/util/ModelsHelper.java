package org.videolan.vlc.util;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.util.HelpersKt;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u001e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J*\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tH@¢\u0006\u0002\u0010\nJ.\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\u000f\u001a\u0004\u0018\u00010\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005J8\u0010\u0011\u001a\u0014\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00122\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0013H@¢\u0006\u0004\b\u0014\u0010\u0015J\f\u0010\u0016\u001a\u0004\u0018\u00010\f*\u00020\u0005J\n\u0010\u0017\u001a\u00020\f*\u00020\u0005¨\u0006\u0018"}, d2 = {"Lorg/videolan/vlc/util/ModelsHelper;", "", "()V", "generateSections", "", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "sort", "", "items", "", "(ILjava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getHeader", "", "context", "Landroid/content/Context;", "item", "aboveItem", "splitList", "", "", "splitList$vlc_android_release", "(ILjava/util/Collection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDiscNumberString", "getFirstLetter", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ModelsHelper.kt */
public final class ModelsHelper {
    public static final ModelsHelper INSTANCE = new ModelsHelper();

    private ModelsHelper() {
    }

    public final Object generateSections(int i, List<? extends MediaLibraryItem> list, Continuation<? super List<MediaLibraryItem>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new ModelsHelper$generateSections$2(i, list, (Continuation<? super ModelsHelper$generateSections$2>) null), continuation);
    }

    public final Object splitList$vlc_android_release(int i, Collection<? extends MediaLibraryItem> collection, Continuation<? super Map<String, List<MediaLibraryItem>>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new ModelsHelper$splitList$2(i, collection, (Continuation<? super ModelsHelper$splitList$2>) null), continuation);
    }

    public final String getFirstLetter(MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "<this>");
        String title = mediaLibraryItem.getTitle();
        Intrinsics.checkNotNullExpressionValue(title, "getTitle(...)");
        if (title.length() == 0 || !Character.isLetter(mediaLibraryItem.getTitle().charAt(0)) || HelpersKt.isSpecialItem(mediaLibraryItem)) {
            return "#";
        }
        String title2 = mediaLibraryItem.getTitle();
        Intrinsics.checkNotNullExpressionValue(title2, "getTitle(...)");
        String substring = title2.substring(0, 1);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
        String upperCase = substring.toUpperCase(locale);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        return upperCase;
    }

    public final String getDiscNumberString(MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "<this>");
        if (mediaLibraryItem instanceof MediaWrapper) {
            MediaWrapper mediaWrapper = (MediaWrapper) mediaLibraryItem;
            if (mediaWrapper.getDiscNumber() != 0) {
                return "Disc " + mediaWrapper.getDiscNumber();
            }
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0148, code lost:
        if (r2 == null) goto L_0x014a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String getHeader(android.content.Context r17, int r18, org.videolan.medialibrary.media.MediaLibraryItem r19, org.videolan.medialibrary.media.MediaLibraryItem r20) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r19
            r3 = r20
            r4 = 0
            if (r1 == 0) goto L_0x0282
            if (r2 == 0) goto L_0x0282
            r5 = -1
            r7 = 0
            java.lang.String r9 = "toUpperCase(...)"
            java.lang.String r10 = "getDefault(...)"
            java.lang.String r11 = "substring(...)"
            java.lang.String r12 = "#"
            java.lang.String r13 = ""
            r14 = 0
            r15 = 1
            switch(r18) {
                case 0: goto L_0x01f0;
                case 1: goto L_0x01f0;
                case 2: goto L_0x01d3;
                case 3: goto L_0x019d;
                case 4: goto L_0x0167;
                case 5: goto L_0x0153;
                case 6: goto L_0x0020;
                case 7: goto L_0x00fb;
                case 8: goto L_0x0020;
                case 9: goto L_0x00da;
                case 10: goto L_0x0036;
                case 11: goto L_0x0020;
                case 12: goto L_0x0022;
                default: goto L_0x0020;
            }
        L_0x0020:
            goto L_0x0282
        L_0x0022:
            java.lang.String r1 = r0.getDiscNumberString(r2)
            if (r3 != 0) goto L_0x0029
            goto L_0x0034
        L_0x0029:
            java.lang.String r2 = r0.getDiscNumberString(r3)
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2)
            r2 = r2 ^ r15
            if (r2 == 0) goto L_0x0282
        L_0x0034:
            goto L_0x01ed
        L_0x0036:
            org.videolan.vlc.util.FileUtils r1 = org.videolan.vlc.util.FileUtils.INSTANCE
            boolean r5 = r2 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
            if (r5 == 0) goto L_0x0040
            r5 = r2
            org.videolan.medialibrary.interfaces.media.MediaWrapper r5 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r5
            goto L_0x0041
        L_0x0040:
            r5 = r4
        L_0x0041:
            if (r5 == 0) goto L_0x0048
            android.net.Uri r5 = r5.getUri()
            goto L_0x0049
        L_0x0048:
            r5 = r4
        L_0x0049:
            java.lang.String r5 = java.lang.String.valueOf(r5)
            java.lang.String r1 = r1.getFileNameFromPath(r5)
            org.videolan.vlc.util.FileUtils r5 = org.videolan.vlc.util.FileUtils.INSTANCE
            boolean r6 = r3 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
            if (r6 == 0) goto L_0x005b
            r6 = r3
            org.videolan.medialibrary.interfaces.media.MediaWrapper r6 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r6
            goto L_0x005c
        L_0x005b:
            r6 = r4
        L_0x005c:
            if (r6 == 0) goto L_0x0063
            android.net.Uri r6 = r6.getUri()
            goto L_0x0064
        L_0x0063:
            r6 = r4
        L_0x0064:
            java.lang.String r6 = java.lang.String.valueOf(r6)
            java.lang.String r5 = r5.getFileNameFromPath(r6)
            r6 = r1
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            int r6 = r6.length()
            if (r6 != 0) goto L_0x0076
            goto L_0x009d
        L_0x0076:
            char r6 = r1.charAt(r14)
            boolean r6 = java.lang.Character.isLetter(r6)
            if (r6 == 0) goto L_0x009d
            boolean r2 = org.videolan.resources.util.HelpersKt.isSpecialItem(r19)
            if (r2 == 0) goto L_0x0087
            goto L_0x009d
        L_0x0087:
            java.lang.String r1 = r1.substring(r14, r15)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r11)
            java.util.Locale r2 = java.util.Locale.getDefault()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r10)
            java.lang.String r1 = r1.toUpperCase(r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r9)
            goto L_0x009e
        L_0x009d:
            r1 = r12
        L_0x009e:
            if (r3 != 0) goto L_0x00a1
            goto L_0x00d8
        L_0x00a1:
            r2 = r5
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            int r2 = r2.length()
            if (r2 != 0) goto L_0x00ab
            goto L_0x00d1
        L_0x00ab:
            char r2 = r5.charAt(r14)
            boolean r2 = java.lang.Character.isLetter(r2)
            if (r2 == 0) goto L_0x00d1
            boolean r2 = org.videolan.resources.util.HelpersKt.isSpecialItem(r20)
            if (r2 == 0) goto L_0x00bc
            goto L_0x00d1
        L_0x00bc:
            java.lang.String r2 = r5.substring(r14, r15)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r11)
            java.util.Locale r3 = java.util.Locale.getDefault()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r10)
            java.lang.String r12 = r2.toUpperCase(r3)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r9)
        L_0x00d1:
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r12)
            r2 = r2 ^ r15
            if (r2 == 0) goto L_0x0282
        L_0x00d8:
            goto L_0x01ed
        L_0x00da:
            r1 = r2
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r1
            java.lang.String r1 = r1.getAlbum()
            if (r1 != 0) goto L_0x00e4
            r1 = r13
        L_0x00e4:
            if (r3 != 0) goto L_0x00e7
            goto L_0x00f9
        L_0x00e7:
            r2 = r3
            org.videolan.medialibrary.interfaces.media.MediaWrapper r2 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r2
            java.lang.String r2 = r2.getAlbum()
            if (r2 != 0) goto L_0x00f1
            goto L_0x00f2
        L_0x00f1:
            r13 = r2
        L_0x00f2:
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r13)
            r2 = r2 ^ r15
            if (r2 == 0) goto L_0x0282
        L_0x00f9:
            goto L_0x01ed
        L_0x00fb:
            boolean r1 = r2 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
            if (r1 == 0) goto L_0x0103
            r1 = r2
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r1
            goto L_0x0104
        L_0x0103:
            r1 = r4
        L_0x0104:
            if (r1 == 0) goto L_0x010c
            java.lang.String r1 = r1.getArtist()
            if (r1 != 0) goto L_0x0120
        L_0x010c:
            boolean r1 = r2 instanceof org.videolan.medialibrary.interfaces.media.Album
            if (r1 == 0) goto L_0x0114
            r1 = r2
            org.videolan.medialibrary.interfaces.media.Album r1 = (org.videolan.medialibrary.interfaces.media.Album) r1
            goto L_0x0115
        L_0x0114:
            r1 = r4
        L_0x0115:
            if (r1 == 0) goto L_0x011c
            java.lang.String r1 = r1.getAlbumArtist()
            goto L_0x011d
        L_0x011c:
            r1 = r4
        L_0x011d:
            if (r1 != 0) goto L_0x0120
            r1 = r13
        L_0x0120:
            if (r3 != 0) goto L_0x0123
            goto L_0x0151
        L_0x0123:
            boolean r2 = r3 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
            if (r2 == 0) goto L_0x012b
            r2 = r3
            org.videolan.medialibrary.interfaces.media.MediaWrapper r2 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r2
            goto L_0x012c
        L_0x012b:
            r2 = r4
        L_0x012c:
            if (r2 == 0) goto L_0x0137
            java.lang.String r2 = r2.getArtist()
            if (r2 != 0) goto L_0x0135
            goto L_0x0137
        L_0x0135:
            r13 = r2
            goto L_0x014a
        L_0x0137:
            boolean r2 = r3 instanceof org.videolan.medialibrary.interfaces.media.Album
            if (r2 == 0) goto L_0x013f
            r2 = r3
            org.videolan.medialibrary.interfaces.media.Album r2 = (org.videolan.medialibrary.interfaces.media.Album) r2
            goto L_0x0140
        L_0x013f:
            r2 = r4
        L_0x0140:
            if (r2 == 0) goto L_0x0147
            java.lang.String r2 = r2.getAlbumArtist()
            goto L_0x0148
        L_0x0147:
            r2 = r4
        L_0x0148:
            if (r2 != 0) goto L_0x0135
        L_0x014a:
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r13)
            r2 = r2 ^ r15
            if (r2 == 0) goto L_0x0282
        L_0x0151:
            goto L_0x01ed
        L_0x0153:
            java.lang.String r1 = org.videolan.resources.util.HelpersKt.getYear(r19)
            if (r3 != 0) goto L_0x015a
            goto L_0x0165
        L_0x015a:
            java.lang.String r2 = org.videolan.resources.util.HelpersKt.getYear(r20)
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2)
            r2 = r2 ^ r15
            if (r2 == 0) goto L_0x0282
        L_0x0165:
            goto L_0x01ed
        L_0x0167:
            boolean r9 = r2 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
            if (r9 == 0) goto L_0x0282
            if (r9 == 0) goto L_0x0170
            org.videolan.medialibrary.interfaces.media.MediaWrapper r2 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r2
            goto L_0x0171
        L_0x0170:
            r2 = r4
        L_0x0171:
            if (r2 == 0) goto L_0x0177
            long r7 = r2.getLastModified()
        L_0x0177:
            int r2 = org.videolan.resources.util.HelpersKt.getTimeCategory(r7)
            if (r3 != 0) goto L_0x0183
            java.lang.String r4 = org.videolan.resources.util.HelpersKt.getTimeCategoryString(r1, r2)
            goto L_0x0282
        L_0x0183:
            boolean r7 = r3 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
            if (r7 == 0) goto L_0x018a
            org.videolan.medialibrary.interfaces.media.MediaWrapper r3 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r3
            goto L_0x018b
        L_0x018a:
            r3 = r4
        L_0x018b:
            if (r3 == 0) goto L_0x0191
            long r5 = r3.getLastModified()
        L_0x0191:
            int r3 = org.videolan.resources.util.HelpersKt.getTimeCategory(r5)
            if (r3 == r2) goto L_0x0282
            java.lang.String r4 = org.videolan.resources.util.HelpersKt.getTimeCategoryString(r1, r2)
            goto L_0x0282
        L_0x019d:
            boolean r9 = r2 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
            if (r9 == 0) goto L_0x0282
            if (r9 == 0) goto L_0x01a6
            org.videolan.medialibrary.interfaces.media.MediaWrapper r2 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r2
            goto L_0x01a7
        L_0x01a6:
            r2 = r4
        L_0x01a7:
            if (r2 == 0) goto L_0x01ad
            long r7 = r2.getInsertionDate()
        L_0x01ad:
            int r2 = org.videolan.resources.util.HelpersKt.getTimeCategory(r7)
            if (r3 != 0) goto L_0x01b9
            java.lang.String r4 = org.videolan.resources.util.HelpersKt.getTimeCategoryString(r1, r2)
            goto L_0x0282
        L_0x01b9:
            boolean r7 = r3 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
            if (r7 == 0) goto L_0x01c0
            org.videolan.medialibrary.interfaces.media.MediaWrapper r3 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r3
            goto L_0x01c1
        L_0x01c0:
            r3 = r4
        L_0x01c1:
            if (r3 == 0) goto L_0x01c7
            long r5 = r3.getInsertionDate()
        L_0x01c7:
            int r3 = org.videolan.resources.util.HelpersKt.getTimeCategory(r5)
            if (r3 == r2) goto L_0x0282
            java.lang.String r4 = org.videolan.resources.util.HelpersKt.getTimeCategoryString(r1, r2)
            goto L_0x0282
        L_0x01d3:
            long r1 = org.videolan.resources.util.HelpersKt.getLength(r19)
            java.lang.String r1 = org.videolan.vlc.util.ModelsHelperKt.lengthToCategory(r1)
            if (r3 != 0) goto L_0x01de
            goto L_0x01ed
        L_0x01de:
            long r2 = org.videolan.resources.util.HelpersKt.getLength(r20)
            java.lang.String r2 = org.videolan.vlc.util.ModelsHelperKt.lengthToCategory(r2)
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2)
            r2 = r2 ^ r15
            if (r2 == 0) goto L_0x0282
        L_0x01ed:
            r4 = r1
            goto L_0x0282
        L_0x01f0:
            java.lang.String r1 = r19.getTitle()
            java.lang.String r5 = "getTitle(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r5)
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            int r1 = r1.length()
            if (r1 != 0) goto L_0x0202
            goto L_0x0234
        L_0x0202:
            java.lang.String r1 = r19.getTitle()
            char r1 = r1.charAt(r14)
            boolean r1 = java.lang.Character.isLetter(r1)
            if (r1 == 0) goto L_0x0234
            boolean r1 = org.videolan.resources.util.HelpersKt.isSpecialItem(r19)
            if (r1 == 0) goto L_0x0217
            goto L_0x0234
        L_0x0217:
            java.lang.String r1 = r19.getTitle()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r5)
            java.lang.String r1 = r1.substring(r14, r15)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r11)
            java.util.Locale r2 = java.util.Locale.getDefault()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r10)
            java.lang.String r1 = r1.toUpperCase(r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r9)
            goto L_0x0235
        L_0x0234:
            r1 = r12
        L_0x0235:
            if (r3 != 0) goto L_0x0238
            goto L_0x0280
        L_0x0238:
            java.lang.String r2 = r20.getTitle()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r5)
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            int r2 = r2.length()
            if (r2 != 0) goto L_0x0248
            goto L_0x0279
        L_0x0248:
            java.lang.String r2 = r20.getTitle()
            char r2 = r2.charAt(r14)
            boolean r2 = java.lang.Character.isLetter(r2)
            if (r2 == 0) goto L_0x0279
            boolean r2 = org.videolan.resources.util.HelpersKt.isSpecialItem(r20)
            if (r2 == 0) goto L_0x025d
            goto L_0x0279
        L_0x025d:
            java.lang.String r2 = r20.getTitle()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r5)
            java.lang.String r2 = r2.substring(r14, r15)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r11)
            java.util.Locale r3 = java.util.Locale.getDefault()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r10)
            java.lang.String r12 = r2.toUpperCase(r3)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r9)
        L_0x0279:
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r12)
            r2 = r2 ^ r15
            if (r2 == 0) goto L_0x0282
        L_0x0280:
            goto L_0x01ed
        L_0x0282:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.ModelsHelper.getHeader(android.content.Context, int, org.videolan.medialibrary.media.MediaLibraryItem, org.videolan.medialibrary.media.MediaLibraryItem):java.lang.String");
    }
}
