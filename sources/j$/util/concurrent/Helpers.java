package j$.util.concurrent;

abstract class Helpers {
    static String mapEntryToString(Object obj, Object obj2) {
        String objectToString = objectToString(obj);
        int length = objectToString.length();
        String objectToString2 = objectToString(obj2);
        int length2 = objectToString2.length();
        char[] cArr = new char[(length + length2 + 1)];
        objectToString.getChars(0, length, cArr, 0);
        cArr[length] = '=';
        objectToString2.getChars(0, length2, cArr, length + 1);
        return new String(cArr);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
        r0 = r0.toString();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String objectToString(java.lang.Object r0) {
        /*
            if (r0 == 0) goto L_0x0008
            java.lang.String r0 = r0.toString()
            if (r0 != 0) goto L_0x000a
        L_0x0008:
            java.lang.String r0 = "null"
        L_0x000a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.util.concurrent.Helpers.objectToString(java.lang.Object):java.lang.String");
    }
}
