package j$.time;

public abstract /* synthetic */ class Clock$OffsetClock$$ExternalSyntheticBackport0 {
    public static /* synthetic */ long m(long j, long j2) {
        long j3 = j + j2;
        boolean z = false;
        boolean z2 = (j2 ^ j) < 0;
        if ((j ^ j3) >= 0) {
            z = true;
        }
        if (z2 || z) {
            return j3;
        }
        throw new ArithmeticException();
    }
}
