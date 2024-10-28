package io.netty.util;

public class IllegalReferenceCountException extends IllegalStateException {
    private static final long serialVersionUID = -2507492394288153468L;

    public IllegalReferenceCountException() {
    }

    public IllegalReferenceCountException(int i) {
        this("refCnt: " + i);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public IllegalReferenceCountException(int r3, int r4) {
        /*
            r2 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "refCnt: "
            r0.<init>(r1)
            r0.append(r3)
            java.lang.String r3 = ", "
            r0.append(r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            if (r4 <= 0) goto L_0x0019
            java.lang.String r1 = "increment: "
            r3.<init>(r1)
            goto L_0x001f
        L_0x0019:
            java.lang.String r1 = "decrement: "
            r3.<init>(r1)
            int r4 = -r4
        L_0x001f:
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            r2.<init>((java.lang.String) r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.util.IllegalReferenceCountException.<init>(int, int):void");
    }

    public IllegalReferenceCountException(String str) {
        super(str);
    }

    public IllegalReferenceCountException(String str, Throwable th) {
        super(str, th);
    }

    public IllegalReferenceCountException(Throwable th) {
        super(th);
    }
}
