package androidx.core.os;

import android.os.LocaleList;
import androidx.car.app.CarContext$$ExternalSyntheticApiModelOutline0;
import java.util.Locale;

final class LocaleListPlatformWrapper implements LocaleListInterface {
    private final LocaleList mLocaleList;

    LocaleListPlatformWrapper(Object obj) {
        this.mLocaleList = LocaleListCompat$$ExternalSyntheticApiModelOutline0.m(obj);
    }

    public Object getLocaleList() {
        return this.mLocaleList;
    }

    public Locale get(int i) {
        return this.mLocaleList.get(i);
    }

    public boolean isEmpty() {
        return CarContext$$ExternalSyntheticApiModelOutline0.m(this.mLocaleList);
    }

    public int size() {
        return BundleKt$$ExternalSyntheticApiModelOutline0.m(this.mLocaleList);
    }

    public int indexOf(Locale locale) {
        return this.mLocaleList.indexOf(locale);
    }

    public boolean equals(Object obj) {
        return this.mLocaleList.equals(((LocaleListInterface) obj).getLocaleList());
    }

    public int hashCode() {
        return this.mLocaleList.hashCode();
    }

    public String toString() {
        return BundleKt$$ExternalSyntheticApiModelOutline0.m(this.mLocaleList);
    }

    public String toLanguageTags() {
        return CarContext$$ExternalSyntheticApiModelOutline0.m(this.mLocaleList);
    }

    public Locale getFirstMatch(String[] strArr) {
        return this.mLocaleList.getFirstMatch(strArr);
    }
}
