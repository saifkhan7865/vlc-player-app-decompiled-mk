package io.ktor.server.config;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\b\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a$\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\bH\u0002¨\u0006\n"}, d2 = {"combine", "", "root", "relative", "findListElements", "", "input", "listElements", "", "", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: MapApplicationConfig.kt */
public final class MapApplicationConfigKt {
    /* access modifiers changed from: private */
    public static final String combine(String str, String str2) {
        if (str.length() == 0) {
            return str2;
        }
        return str + '.' + str2;
    }

    /* access modifiers changed from: private */
    public static final void findListElements(String str, Map<String, Integer> map) {
        CharSequence charSequence = str;
        int indexOf$default = StringsKt.indexOf$default(charSequence, '.', 0, false, 6, (Object) null);
        while (true) {
            int i = indexOf$default;
            if (i != str.length()) {
                int i2 = i + 1;
                indexOf$default = StringsKt.indexOf$default(charSequence, '.', i2, false, 4, (Object) null);
                if (indexOf$default == -1) {
                    indexOf$default = str.length();
                }
                String substring = str.substring(i2, indexOf$default);
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                Integer intOrNull = StringsKt.toIntOrNull(substring);
                if (intOrNull != null) {
                    int intValue = intOrNull.intValue();
                    String substring2 = str.substring(0, i);
                    Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
                    int i3 = intValue + 1;
                    Integer num = map.get(substring2);
                    if (num != null) {
                        i3 = Math.max(num.intValue(), i3);
                    }
                    map.put(substring2, Integer.valueOf(i3));
                }
            } else {
                return;
            }
        }
    }
}
