package org.videolan.tools;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.os.Build;
import androidx.car.app.CarContext$$ExternalSyntheticApiModelOutline0;
import androidx.core.os.BundleKt$$ExternalSyntheticApiModelOutline0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u001a\u0010\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005*\u00020\u0001\u001a\f\u0010\u0006\u001a\u00020\u0007*\u00020\bH\u0007\u001a\n\u0010\t\u001a\u00020\u0007*\u00020\b\u001a\u0014\u0010\n\u001a\u00020\u000b*\u00020\b2\u0006\u0010\f\u001a\u00020\u0007H\u0007\u001a\u0012\u0010\r\u001a\u00020\u000b*\u00020\b2\u0006\u0010\f\u001a\u00020\u0007\u001a\u0012\u0010\u000e\u001a\u00020\b*\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0003¨\u0006\u0010"}, d2 = {"getContextWithLocale", "Landroid/content/Context;", "appLocale", "", "getLocaleLanguages", "", "getSystemLocale", "Ljava/util/Locale;", "Landroid/content/ContextWrapper;", "getSystemLocaleLegacy", "setSystemLocale", "", "locale", "setSystemLocaleLegacy", "wrap", "language", "tools_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: LocaleUtils.kt */
public final class LocaleUtilsKt {
    public static final ContextWrapper wrap(ContextWrapper contextWrapper, String str) {
        Locale locale;
        Intrinsics.checkNotNullParameter(contextWrapper, "<this>");
        Intrinsics.checkNotNullParameter(str, "language");
        Configuration configuration = contextWrapper.getBaseContext().getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= 24) {
            locale = getSystemLocale(contextWrapper);
        } else {
            locale = getSystemLocaleLegacy(contextWrapper);
        }
        CharSequence charSequence = str;
        if (charSequence.length() > 0 && !Intrinsics.areEqual((Object) locale.getLanguage(), (Object) str)) {
            Locale locale2 = StringsKt.contains$default(charSequence, (CharSequence) "-", false, 2, (Object) null) ? new Locale(StringsKt.substringBefore$default(str, "-", (String) null, 2, (Object) null), StringsKt.substringAfter$default(str, "-", (String) null, 2, (Object) null)) : new Locale(str);
            Locale.setDefault(locale2);
            if (Build.VERSION.SDK_INT >= 24) {
                setSystemLocale(contextWrapper, locale2);
            } else {
                setSystemLocaleLegacy(contextWrapper, locale2);
            }
        }
        return new ContextWrapper(contextWrapper.getBaseContext().createConfigurationContext(configuration));
    }

    public static final Locale getSystemLocaleLegacy(ContextWrapper contextWrapper) {
        Intrinsics.checkNotNullParameter(contextWrapper, "<this>");
        Locale locale = contextWrapper.getBaseContext().getResources().getConfiguration().locale;
        Intrinsics.checkNotNullExpressionValue(locale, "locale");
        return locale;
    }

    public static final Locale getSystemLocale(ContextWrapper contextWrapper) {
        Intrinsics.checkNotNullParameter(contextWrapper, "<this>");
        Locale m = CarContext$$ExternalSyntheticApiModelOutline0.m(contextWrapper.getBaseContext().getResources().getConfiguration()).get(0);
        Intrinsics.checkNotNullExpressionValue(m, "get(...)");
        return m;
    }

    public static final List<String> getLocaleLanguages(Context context) {
        ArrayList arrayList;
        Intrinsics.checkNotNullParameter(context, "<this>");
        if (Build.VERSION.SDK_INT >= 24) {
            arrayList = new ArrayList();
            int m = BundleKt$$ExternalSyntheticApiModelOutline0.m(CarContext$$ExternalSyntheticApiModelOutline0.m(context.getResources().getConfiguration()));
            for (int i = 0; i < m; i++) {
                arrayList.add(CarContext$$ExternalSyntheticApiModelOutline0.m(context.getResources().getConfiguration()).get(i));
            }
        } else {
            arrayList = CollectionsKt.arrayListOf(context.getResources().getConfiguration().locale);
        }
        Iterable<Locale> iterable = arrayList;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Locale language : iterable) {
            arrayList2.add(language.getLanguage());
        }
        return (List) arrayList2;
    }

    public static final void setSystemLocaleLegacy(ContextWrapper contextWrapper, Locale locale) {
        Intrinsics.checkNotNullParameter(contextWrapper, "<this>");
        Intrinsics.checkNotNullParameter(locale, "locale");
        contextWrapper.getBaseContext().getResources().getConfiguration().locale = locale;
    }

    public static final void setSystemLocale(ContextWrapper contextWrapper, Locale locale) {
        Intrinsics.checkNotNullParameter(contextWrapper, "<this>");
        Intrinsics.checkNotNullParameter(locale, "locale");
        contextWrapper.getBaseContext().getResources().getConfiguration().setLocale(locale);
    }

    public static final Context getContextWithLocale(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        CharSequence charSequence = str;
        if (!(!(charSequence == null || charSequence.length() == 0))) {
            str = null;
        }
        return str != null ? wrap(new ContextWrapper(context), str) : context;
    }
}
