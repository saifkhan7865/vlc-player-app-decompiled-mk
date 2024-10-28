package j$.time;

public abstract /* synthetic */ class Duration$$ExternalSyntheticBackport1 {
    public static /* synthetic */ long m(long j, long j2) {
        int numberOfLeadingZeros = Long.numberOfLeadingZeros(j) + Long.numberOfLeadingZeros(j ^ -1) + Long.numberOfLeadingZeros(j2) + Long.numberOfLeadingZeros(-1 ^ j2);
        if (numberOfLeadingZeros > 65) {
            return j * j2;
        }
        if (numberOfLeadingZeros >= 64) {
            boolean z = false;
            boolean z2 = j >= 0;
            if (j2 != Long.MIN_VALUE) {
                z = true;
            }
            if (z || z2) {
                long j3 = j * j2;
                if (j == 0 || j3 / j == j2) {
                    return j3;
                }
            }
        }
        throw new ArithmeticException();
    }
}
