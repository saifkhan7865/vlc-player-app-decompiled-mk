package io.ktor.network.tls;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0001\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\tB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\n"}, d2 = {"Lio/ktor/network/tls/TLSAlertLevel;", "", "code", "", "(Ljava/lang/String;II)V", "getCode", "()I", "WARNING", "FATAL", "Companion", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: TLSAlert.kt */
public enum TLSAlertLevel {
    WARNING(1),
    FATAL(2);
    
    public static final Companion Companion = null;
    /* access modifiers changed from: private */
    public static final TLSAlertLevel[] byCode = null;
    private final int code;

    private TLSAlertLevel(int i) {
        this.code = i;
    }

    public final int getCode() {
        return this.code;
    }

    static {
        TLSAlertLevel tLSAlertLevel;
        Companion = new Companion((DefaultConstructorMarker) null);
        TLSAlertLevel[] tLSAlertLevelArr = new TLSAlertLevel[256];
        for (int i = 0; i < 256; i++) {
            TLSAlertLevel[] values = values();
            int length = values.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    tLSAlertLevel = null;
                    break;
                }
                tLSAlertLevel = values[i2];
                if (tLSAlertLevel.code == i) {
                    break;
                }
                i2++;
            }
            tLSAlertLevelArr[i] = tLSAlertLevel;
        }
        byCode = tLSAlertLevelArr;
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bR\u0018\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\t"}, d2 = {"Lio/ktor/network/tls/TLSAlertLevel$Companion;", "", "()V", "byCode", "", "Lio/ktor/network/tls/TLSAlertLevel;", "[Lio/ktor/network/tls/TLSAlertLevel;", "code", "", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: TLSAlert.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TLSAlertLevel byCode(int i) {
            TLSAlertLevel tLSAlertLevel = (i < 0 || i >= 256) ? null : TLSAlertLevel.byCode[i];
            if (tLSAlertLevel != null) {
                return tLSAlertLevel;
            }
            throw new IllegalArgumentException("Invalid TLS record type code: " + i);
        }
    }
}
