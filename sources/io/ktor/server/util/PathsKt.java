package io.ktor.server.util;

import io.ktor.util.TextKt;
import io.ktor.util.date.GMTDateParser;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Typography;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.bouncycastle.pqc.legacy.math.linearalgebra.Matrix;

@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010\u0018\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010\u0019\n\u0000\u001a\u0015\u0010\u0006\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\b\u001a\u00020\tH\u0002\u001a \u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b*\b\u0012\u0004\u0012\u00020\u00050\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002\u001a\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b*\b\u0012\u0004\u0012\u00020\u00050\u000b\u001a\u001a\u0010\u000f\u001a\u00020\u0010*\b\u0012\u0004\u0012\u00020\u00050\u00112\u0006\u0010\u0012\u001a\u00020\u0005H\u0002\u001a\f\u0010\u0013\u001a\u00020\u0007*\u00020\u0005H\u0002\u001a\f\u0010\u0014\u001a\u00020\u0001*\u00020\u0015H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"FirstReservedLetters", "", "ReservedCharacters", "ReservedWords", "", "", "contains", "", "char", "", "filterComponentsImpl", "", "startIndex", "", "normalizePathComponents", "processAndReplaceComponent", "", "", "component", "shouldBeReplaced", "toASCIITable", "", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Paths.kt */
public final class PathsKt {
    private static final boolean[] FirstReservedLetters = toASCIITable(new char[]{'A', 'a', 'C', 'c', 'l', Matrix.MATRIX_TYPE_RANDOM_LT, 'P', 'p', 'n', 'N'});
    private static final boolean[] ReservedCharacters = toASCIITable(new char[]{AbstractJsonLexerKt.STRING_ESC, '/', AbstractJsonLexerKt.COLON, GMTDateParser.ANY, '?', '\"', Typography.less, Typography.greater, '|'});
    private static final Set<String> ReservedWords = SetsKt.setOf("CON", "PRN", "AUX", "NUL", "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "LPT1", "LPT2", "LPT3", "LPT4", "LPT5", "LPT6", "LPT7", "LPT8", "LPT9");

    public static final List<String> normalizePathComponents(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (shouldBeReplaced(list.get(i))) {
                return filterComponentsImpl(list, i);
            }
        }
        return list;
    }

    private static final List<String> filterComponentsImpl(List<String> list, int i) {
        ArrayList arrayList = new ArrayList(list.size());
        if (i > 0) {
            arrayList.addAll(list.subList(0, i));
        }
        List<String> list2 = arrayList;
        processAndReplaceComponent(list2, list.get(i));
        int size = list.size();
        for (int i2 = i + 1; i2 < size; i2++) {
            String str = list.get(i2);
            if (shouldBeReplaced(str)) {
                processAndReplaceComponent(list2, str);
            } else {
                arrayList.add(str);
            }
        }
        return list2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b0  */
    /* JADX WARNING: Removed duplicated region for block: B:48:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final void processAndReplaceComponent(java.util.List<java.lang.String> r6, java.lang.String r7) {
        /*
            r0 = r7
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r1 = r0.length()
            if (r1 != 0) goto L_0x000b
            goto L_0x00b3
        L_0x000b:
            java.lang.String r1 = "."
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r1)
            if (r1 != 0) goto L_0x00b3
            java.lang.String r1 = "~"
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r1)
            if (r1 != 0) goto L_0x00b3
            java.util.Set<java.lang.String> r1 = ReservedWords
            java.lang.String r2 = io.ktor.util.TextKt.toUpperCasePreservingASCIIRules(r7)
            boolean r1 = r1.contains(r2)
            if (r1 == 0) goto L_0x0029
            goto L_0x00b3
        L_0x0029:
            java.lang.String r1 = ".."
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r1)
            if (r7 == 0) goto L_0x0044
            r7 = r6
            java.util.Collection r7 = (java.util.Collection) r7
            boolean r7 = r7.isEmpty()
            r7 = r7 ^ 1
            if (r7 == 0) goto L_0x0043
            int r7 = kotlin.collections.CollectionsKt.getLastIndex(r6)
            r6.remove(r7)
        L_0x0043:
            return
        L_0x0044:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.Appendable r7 = (java.lang.Appendable) r7
            int r1 = r0.length()
            r2 = 0
            r3 = 0
        L_0x0051:
            r4 = 32
            if (r3 >= r1) goto L_0x006d
            char r5 = r0.charAt(r3)
            int r4 = kotlin.jvm.internal.Intrinsics.compare((int) r5, (int) r4)
            if (r4 < 0) goto L_0x006a
            boolean[] r4 = ReservedCharacters
            boolean r4 = contains(r4, r5)
            if (r4 != 0) goto L_0x006a
            r7.append(r5)
        L_0x006a:
            int r3 = r3 + 1
            goto L_0x0051
        L_0x006d:
            java.lang.StringBuilder r7 = (java.lang.StringBuilder) r7
            java.lang.String r7 = r7.toString()
            java.lang.String r0 = "filterTo(StringBuilder(), predicate).toString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r0)
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            int r0 = r7.length()
            int r0 = r0 + -1
            if (r0 < 0) goto L_0x009b
        L_0x0082:
            int r1 = r0 + -1
            char r3 = r7.charAt(r0)
            if (r3 == r4) goto L_0x0096
            r5 = 46
            if (r3 != r5) goto L_0x008f
            goto L_0x0096
        L_0x008f:
            int r0 = r0 + 1
            java.lang.CharSequence r7 = r7.subSequence(r2, r0)
            goto L_0x009f
        L_0x0096:
            if (r1 >= 0) goto L_0x0099
            goto L_0x009b
        L_0x0099:
            r0 = r1
            goto L_0x0082
        L_0x009b:
            java.lang.String r7 = ""
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
        L_0x009f:
            java.lang.String r7 = r7.toString()
            r0 = r7
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            if (r0 <= 0) goto L_0x00ad
            goto L_0x00ae
        L_0x00ad:
            r7 = 0
        L_0x00ae:
            if (r7 == 0) goto L_0x00b3
            r6.add(r7)
        L_0x00b3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.util.PathsKt.processAndReplaceComponent(java.util.List, java.lang.String):void");
    }

    private static final boolean shouldBeReplaced(String str) {
        int length = str.length();
        if (length == 0) {
            return true;
        }
        char charAt = str.charAt(0);
        if (charAt == '.' && (length == 1 || (length == 2 && str.charAt(1) == '.'))) {
            return true;
        }
        if (charAt == '~' && length == 1) {
            return true;
        }
        if (contains(FirstReservedLetters, charAt)) {
            Set<String> set = ReservedWords;
            if (set.contains(str) || set.contains(TextKt.toUpperCasePreservingASCIIRules(str))) {
                return true;
            }
        }
        char charAt2 = str.charAt(length - 1);
        if (charAt2 == ' ' || charAt2 == '.') {
            return true;
        }
        boolean[] zArr = ReservedCharacters;
        CharSequence charSequence = str;
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt3 = charSequence.charAt(i);
            if (Intrinsics.compare((int) charAt3, 32) < 0 || contains(zArr, charAt3)) {
                return true;
            }
        }
        return false;
    }

    private static final boolean[] toASCIITable(char[] cArr) {
        boolean[] zArr = new boolean[256];
        for (int i = 0; i < 256; i++) {
            zArr[i] = ArraysKt.contains(cArr, (char) i);
        }
        return zArr;
    }

    private static final boolean contains(boolean[] zArr, char c) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return c < zArr.length && zArr[c];
    }
}
