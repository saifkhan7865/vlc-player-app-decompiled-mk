package org.videolan.vlc.gui.helpers;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u0016H\u0002J\u0016\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u001fJ\u0010\u0010 \u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u0016H\u0002J\u0010\u0010!\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u0016H\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0007R\u001b\u0010\u000b\u001a\u00020\f8BX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0011\u001a\u00020\f8BX\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0012\u0010\u000eR!\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00158BX\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u0010\u001a\u0004\b\u0017\u0010\u0018¨\u0006\""}, d2 = {"Lorg/videolan/vlc/gui/helpers/MediaComparators;", "", "()V", "ANDROID_AUTO", "Ljava/util/Comparator;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "getANDROID_AUTO", "()Ljava/util/Comparator;", "BY_TRACK_NUMBER", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "getBY_TRACK_NUMBER", "asciiAlphaNumeric", "Ljava/util/BitSet;", "getAsciiAlphaNumeric", "()Ljava/util/BitSet;", "asciiAlphaNumeric$delegate", "Lkotlin/Lazy;", "asciiPunctuation", "getAsciiPunctuation", "asciiPunctuation$delegate", "englishArticles", "", "", "getEnglishArticles", "()[Ljava/lang/String;", "englishArticles$delegate", "buildComparableTitle", "origTitle", "formatArticles", "title", "appendPrefix", "", "removeLeadingPunctuation", "removeNonAlphaNumeric", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaComparators.kt */
public final class MediaComparators {
    private static final Comparator<MediaLibraryItem> ANDROID_AUTO = new MediaComparators$$ExternalSyntheticLambda1();
    private static final Comparator<MediaWrapper> BY_TRACK_NUMBER = new MediaComparators$$ExternalSyntheticLambda0();
    public static final MediaComparators INSTANCE = new MediaComparators();
    private static final Lazy asciiAlphaNumeric$delegate = LazyKt.lazy(MediaComparators$asciiAlphaNumeric$2.INSTANCE);
    private static final Lazy asciiPunctuation$delegate = LazyKt.lazy(MediaComparators$asciiPunctuation$2.INSTANCE);
    private static final Lazy englishArticles$delegate = LazyKt.lazy(MediaComparators$englishArticles$2.INSTANCE);

    private MediaComparators() {
    }

    private final String[] getEnglishArticles() {
        return (String[]) englishArticles$delegate.getValue();
    }

    private final BitSet getAsciiAlphaNumeric() {
        return (BitSet) asciiAlphaNumeric$delegate.getValue();
    }

    private final BitSet getAsciiPunctuation() {
        return (BitSet) asciiPunctuation$delegate.getValue();
    }

    public final Comparator<MediaWrapper> getBY_TRACK_NUMBER() {
        return BY_TRACK_NUMBER;
    }

    /* access modifiers changed from: private */
    public static final int BY_TRACK_NUMBER$lambda$0(MediaWrapper mediaWrapper, MediaWrapper mediaWrapper2) {
        if (mediaWrapper.getDiscNumber() < mediaWrapper2.getDiscNumber()) {
            return -1;
        }
        if (mediaWrapper.getDiscNumber() > mediaWrapper2.getDiscNumber()) {
            return 1;
        }
        if (mediaWrapper.getTrackNumber() < mediaWrapper2.getTrackNumber()) {
            return -1;
        }
        if (mediaWrapper.getTrackNumber() > mediaWrapper2.getTrackNumber()) {
            return 1;
        }
        return 0;
    }

    public final Comparator<MediaLibraryItem> getANDROID_AUTO() {
        return ANDROID_AUTO;
    }

    /* access modifiers changed from: private */
    public static final int ANDROID_AUTO$lambda$1(MediaLibraryItem mediaLibraryItem, MediaLibraryItem mediaLibraryItem2) {
        MediaComparators mediaComparators = INSTANCE;
        String title = mediaLibraryItem.getTitle();
        Intrinsics.checkNotNullExpressionValue(title, "getTitle(...)");
        String buildComparableTitle = mediaComparators.buildComparableTitle(title);
        String title2 = mediaLibraryItem2.getTitle();
        Intrinsics.checkNotNullExpressionValue(title2, "getTitle(...)");
        return buildComparableTitle.compareTo(mediaComparators.buildComparableTitle(title2));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.CharSequence} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: java.lang.CharSequence} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v10, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.String buildComparableTitle(java.lang.String r5) {
        /*
            r4 = this;
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            java.lang.CharSequence r5 = kotlin.text.StringsKt.trim((java.lang.CharSequence) r5)
            java.lang.String r5 = r5.toString()
            r0 = r5
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            if (r0 != 0) goto L_0x0014
            return r5
        L_0x0014:
            java.lang.String r5 = r4.removeLeadingPunctuation(r5)
            java.util.Locale r0 = java.util.Locale.US
            java.lang.String r1 = "US"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.String r5 = r5.toLowerCase(r0)
            java.lang.String r0 = "toLowerCase(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r0)
            r0 = 0
            java.lang.String r1 = r4.formatArticles(r5, r0)
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            int r2 = r1.length()
            if (r2 != 0) goto L_0x0036
            goto L_0x0037
        L_0x0036:
            r5 = r1
        L_0x0037:
            java.lang.String r5 = (java.lang.String) r5
            char r1 = r5.charAt(r0)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            java.text.Normalizer$Form r2 = java.text.Normalizer.Form.NFD
            java.lang.String r1 = java.text.Normalizer.normalize(r1, r2)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            java.lang.String r2 = r4.removeNonAlphaNumeric(r1)
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            int r3 = r2.length()
            if (r3 != 0) goto L_0x0059
            goto L_0x005a
        L_0x0059:
            r1 = r2
        L_0x005a:
            java.lang.String r1 = (java.lang.String) r1
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            char r0 = r1.charAt(r0)
            r2.append(r0)
            r0 = 1
            java.lang.String r5 = r5.substring(r0)
            java.lang.String r0 = "substring(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r0)
            r2.append(r5)
            java.lang.String r5 = r2.toString()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.helpers.MediaComparators.buildComparableTitle(java.lang.String):java.lang.String");
    }

    public final String formatArticles(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "title");
        for (String str2 : getEnglishArticles()) {
            if (StringsKt.startsWith(str, str2, true)) {
                String substring = str.substring(str2.length());
                Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                String obj = StringsKt.trim((CharSequence) substring).toString();
                if (!z) {
                    return obj;
                }
                String substring2 = str.substring(0, str2.length());
                Intrinsics.checkNotNullExpressionValue(substring2, "substring(...)");
                Collection arrayList = new ArrayList();
                for (Object next : CollectionsKt.listOf(obj, StringsKt.trim((CharSequence) substring2).toString())) {
                    if (((String) next).length() > 0) {
                        arrayList.add(next);
                    }
                }
                return CollectionsKt.joinToString$default((List) arrayList, ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, MediaComparators$formatArticles$2.INSTANCE, 30, (Object) null);
            }
        }
        return str;
    }

    private final String removeNonAlphaNumeric(String str) {
        if (str.length() == 1 && getAsciiAlphaNumeric().get(str.charAt(0))) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        char[] charArray = str.toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "toCharArray(...)");
        for (char c : charArray) {
            if (INSTANCE.getAsciiAlphaNumeric().get(c)) {
                sb.append(c);
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    private final String removeLeadingPunctuation(String str) {
        CharSequence charSequence = str;
        int i = 0;
        int i2 = 0;
        while (i < charSequence.length()) {
            int i3 = i2 + 1;
            if (!INSTANCE.getAsciiPunctuation().get(charSequence.charAt(i))) {
                String substring = str.substring(i2);
                Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                return substring;
            }
            i++;
            i2 = i3;
        }
        return str;
    }
}
