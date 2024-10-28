package org.videolan.tools;

import android.os.Build;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J!\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\n2\u0006\u0010\u000b\u001a\u00020\u0006¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/videolan/tools/LocaleUtils;", "", "()V", "getLocaleFromString", "Ljava/util/Locale;", "string", "", "getLocalesUsedInProject", "Lorg/videolan/tools/LocalePair;", "projectLocales", "", "defaultLocaleText", "([Ljava/lang/String;Ljava/lang/String;)Lorg/videolan/tools/LocalePair;", "tools_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: LocaleUtils.kt */
public final class LocaleUtils {
    public static final LocaleUtils INSTANCE = new LocaleUtils();

    private LocaleUtils() {
    }

    public final LocalePair getLocalesUsedInProject(String[] strArr, String str) {
        Intrinsics.checkNotNullParameter(strArr, "projectLocales");
        Intrinsics.checkNotNullParameter(str, "defaultLocaleText");
        String[] strArr2 = new String[strArr.length];
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            Locale localeFromString = getLocaleFromString(strArr[i]);
            String displayLanguage = localeFromString.getDisplayLanguage(localeFromString);
            String displayCountry = localeFromString.getDisplayCountry(localeFromString);
            Intrinsics.checkNotNull(displayCountry);
            if (displayCountry.length() == 0) {
                Intrinsics.checkNotNull(displayLanguage);
                strArr2[i] = Strings.firstLetterUppercase(displayLanguage);
            } else {
                StringBuilder sb = new StringBuilder();
                Intrinsics.checkNotNull(displayLanguage);
                sb.append(Strings.firstLetterUppercase(displayLanguage));
                sb.append(" - ");
                sb.append(Strings.firstLetterUppercase(displayCountry));
                strArr2[i] = sb.toString();
            }
        }
        TreeMap treeMap = new TreeMap();
        int length2 = strArr.length;
        for (int i2 = 0; i2 < length2; i2++) {
            String str2 = strArr2[i2];
            Intrinsics.checkNotNull(str2);
            treeMap.put(str2, strArr[i2]);
        }
        ArrayList arrayList = new ArrayList(treeMap.size() + 1);
        arrayList.add(0, str);
        ArrayList arrayList2 = new ArrayList(treeMap.size() + 1);
        arrayList2.add(0, "");
        int i3 = 1;
        for (Map.Entry entry : treeMap.entrySet()) {
            arrayList.add(i3, (String) entry.getKey());
            arrayList2.add(i3, (String) entry.getValue());
            i3++;
        }
        return new LocalePair((String[]) arrayList.toArray(new String[0]), (String[]) arrayList2.toArray(new String[0]));
    }

    public final Locale getLocaleFromString(String str) {
        List list;
        Intrinsics.checkNotNullParameter(str, TypedValues.Custom.S_STRING);
        if (Build.VERSION.SDK_INT >= 21) {
            Locale m = AppUtils$$ExternalSyntheticApiModelOutline0.m(str);
            Intrinsics.checkNotNullExpressionValue(m, "forLanguageTag(...)");
            return m;
        }
        String[] strArr = {"_", "-"};
        for (int i = 0; i < 2; i++) {
            String str2 = strArr[i];
            CharSequence charSequence = str;
            if (StringsKt.contains$default(charSequence, (CharSequence) str2, false, 2, (Object) null)) {
                List<String> split = new Regex(str2).split(charSequence, 0);
                if (!split.isEmpty()) {
                    ListIterator<String> listIterator = split.listIterator(split.size());
                    while (true) {
                        if (listIterator.hasPrevious()) {
                            if (listIterator.previous().length() != 0) {
                                list = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
                list = CollectionsKt.emptyList();
                String[] strArr2 = (String[]) list.toArray(new String[0]);
                if (strArr2.length == 2) {
                    return new Locale(strArr2[0], strArr2[1]);
                }
            }
        }
        return new Locale(str);
    }
}
