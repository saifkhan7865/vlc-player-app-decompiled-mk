package j$.time.format;

import java.math.BigDecimal;
import java.math.BigInteger;

public abstract /* synthetic */ class DateTimeFormatterBuilder$FractionPrinterParser$$ExternalSyntheticBackportWithForwarding0 {
    public static /* synthetic */ BigDecimal m(BigDecimal bigDecimal) {
        return bigDecimal.signum() == 0 ? new BigDecimal(BigInteger.ZERO, 0) : bigDecimal.stripTrailingZeros();
    }
}
