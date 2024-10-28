package org.bouncycastle.tsp;

import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.tsp.Accuracy;
import org.videolan.resources.Constants;

public class GenTimeAccuracy {
    private Accuracy accuracy;

    public GenTimeAccuracy(Accuracy accuracy2) {
        this.accuracy = accuracy2;
    }

    private String format(int i) {
        StringBuilder sb;
        if (i < 10) {
            sb = new StringBuilder("00");
        } else if (i >= 100) {
            return Integer.toString(i);
        } else {
            sb = new StringBuilder(Constants.GROUP_VIDEOS_FOLDER);
        }
        sb.append(i);
        return sb.toString();
    }

    private int getTimeComponent(ASN1Integer aSN1Integer) {
        if (aSN1Integer != null) {
            return aSN1Integer.intValueExact();
        }
        return 0;
    }

    public int getMicros() {
        return getTimeComponent(this.accuracy.getMicros());
    }

    public int getMillis() {
        return getTimeComponent(this.accuracy.getMillis());
    }

    public int getSeconds() {
        return getTimeComponent(this.accuracy.getSeconds());
    }

    public String toString() {
        return getSeconds() + "." + format(getMillis()) + format(getMicros());
    }
}
