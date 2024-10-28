package org.videolan.vlc.util;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.squareup.moshi.Json;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.Strings;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0013B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u000bJ\u000e\u0010\r\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bJ\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0005H\u0002J\u0015\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0011*\u00020\u000b¢\u0006\u0002\u0010\u0012R!\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048BX\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"Lorg/videolan/vlc/util/LocaleUtil;", "", "()V", "vlcLocaleList", "", "Lorg/videolan/vlc/util/LocaleUtil$VLCLocale;", "getVlcLocaleList", "()Ljava/util/List;", "vlcLocaleList$delegate", "Lkotlin/Lazy;", "getLocaleFromVLC", "", "from", "getLocaleName", "toTranslatedLanguage", "entry", "localeEquivalent", "", "(Ljava/lang/String;)[Ljava/lang/String;", "VLCLocale", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: LocaleUtil.kt */
public final class LocaleUtil {
    public static final LocaleUtil INSTANCE = new LocaleUtil();
    private static final Lazy vlcLocaleList$delegate = LazyKt.lazy(LocaleUtil$vlcLocaleList$2.INSTANCE);

    private LocaleUtil() {
    }

    public final String getLocaleName(String str) {
        Intrinsics.checkNotNullParameter(str, TypedValues.TransitionType.S_FROM);
        List<VLCLocale> vlcLocaleList = getVlcLocaleList();
        Intrinsics.checkNotNullExpressionValue(vlcLocaleList, "<get-vlcLocaleList>(...)");
        for (VLCLocale vLCLocale : vlcLocaleList) {
            if (Intrinsics.areEqual((Object) vLCLocale.getLanguage(), (Object) str)) {
                return INSTANCE.toTranslatedLanguage(vLCLocale);
            }
            Iterator it = vLCLocale.getValues().iterator();
            while (true) {
                if (it.hasNext()) {
                    if (Intrinsics.areEqual((Object) (String) it.next(), (Object) str)) {
                        return INSTANCE.toTranslatedLanguage(vLCLocale);
                    }
                }
            }
        }
        return str;
    }

    public final String getLocaleFromVLC(String str) {
        Intrinsics.checkNotNullParameter(str, TypedValues.TransitionType.S_FROM);
        List<VLCLocale> vlcLocaleList = getVlcLocaleList();
        Intrinsics.checkNotNullExpressionValue(vlcLocaleList, "<get-vlcLocaleList>(...)");
        for (VLCLocale vLCLocale : vlcLocaleList) {
            if (Intrinsics.areEqual((Object) vLCLocale.getLanguage(), (Object) str)) {
                return vLCLocale.getValues().get(0);
            }
            Iterator it = vLCLocale.getValues().iterator();
            while (true) {
                if (it.hasNext()) {
                    if (Intrinsics.areEqual((Object) (String) it.next(), (Object) str)) {
                        return vLCLocale.getValues().get(0);
                    }
                }
            }
        }
        return null;
    }

    private final String toTranslatedLanguage(VLCLocale vLCLocale) {
        String str = vLCLocale.getValues().get(0);
        String displayLanguage = new Locale(str).getDisplayLanguage();
        if (Intrinsics.areEqual((Object) displayLanguage, (Object) str)) {
            return vLCLocale.getLanguage();
        }
        Intrinsics.checkNotNull(displayLanguage);
        return Strings.firstLetterUppercase(displayLanguage);
    }

    private final List<VLCLocale> getVlcLocaleList() {
        return (List) vlcLocaleList$delegate.getValue();
    }

    public final String[] localeEquivalent(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        String lowerCase = str.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        if (Intrinsics.areEqual((Object) lowerCase, (Object) "in")) {
            return new String[]{"in", "id"};
        }
        return new String[]{str};
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J#\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lorg/videolan/vlc/util/LocaleUtil$VLCLocale;", "", "language", "", "values", "", "(Ljava/lang/String;Ljava/util/List;)V", "getLanguage", "()Ljava/lang/String;", "getValues", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: LocaleUtil.kt */
    public static final class VLCLocale {
        @Json(name = "language")
        private final String language;
        @Json(name = "values")
        private final List<String> values;

        public static /* synthetic */ VLCLocale copy$default(VLCLocale vLCLocale, String str, List<String> list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = vLCLocale.language;
            }
            if ((i & 2) != 0) {
                list = vLCLocale.values;
            }
            return vLCLocale.copy(str, list);
        }

        public final String component1() {
            return this.language;
        }

        public final List<String> component2() {
            return this.values;
        }

        public final VLCLocale copy(String str, List<String> list) {
            Intrinsics.checkNotNullParameter(str, "language");
            Intrinsics.checkNotNullParameter(list, "values");
            return new VLCLocale(str, list);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof VLCLocale)) {
                return false;
            }
            VLCLocale vLCLocale = (VLCLocale) obj;
            return Intrinsics.areEqual((Object) this.language, (Object) vLCLocale.language) && Intrinsics.areEqual((Object) this.values, (Object) vLCLocale.values);
        }

        public int hashCode() {
            return (this.language.hashCode() * 31) + this.values.hashCode();
        }

        public String toString() {
            return "VLCLocale(language=" + this.language + ", values=" + this.values + ')';
        }

        public VLCLocale(String str, List<String> list) {
            Intrinsics.checkNotNullParameter(str, "language");
            Intrinsics.checkNotNullParameter(list, "values");
            this.language = str;
            this.values = list;
        }

        public final String getLanguage() {
            return this.language;
        }

        public final List<String> getValues() {
            return this.values;
        }
    }
}
