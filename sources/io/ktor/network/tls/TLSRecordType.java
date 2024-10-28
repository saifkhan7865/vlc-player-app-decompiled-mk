package io.ktor.network.tls;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0001\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000bB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\f"}, d2 = {"Lio/ktor/network/tls/TLSRecordType;", "", "code", "", "(Ljava/lang/String;II)V", "getCode", "()I", "ChangeCipherSpec", "Alert", "Handshake", "ApplicationData", "Companion", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: TLSRecordType.kt */
public enum TLSRecordType {
    ChangeCipherSpec(20),
    Alert(21),
    Handshake(22),
    ApplicationData(23);
    
    public static final Companion Companion = null;
    /* access modifiers changed from: private */
    public static final TLSRecordType[] byCode = null;
    private final int code;

    private TLSRecordType(int i) {
        this.code = i;
    }

    public final int getCode() {
        return this.code;
    }

    static {
        TLSRecordType tLSRecordType;
        Companion = new Companion((DefaultConstructorMarker) null);
        TLSRecordType[] tLSRecordTypeArr = new TLSRecordType[256];
        for (int i = 0; i < 256; i++) {
            TLSRecordType[] values = values();
            int length = values.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    tLSRecordType = null;
                    break;
                }
                tLSRecordType = values[i2];
                if (tLSRecordType.code == i) {
                    break;
                }
                i2++;
            }
            tLSRecordTypeArr[i] = tLSRecordType;
        }
        byCode = tLSRecordTypeArr;
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bR\u0018\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\t"}, d2 = {"Lio/ktor/network/tls/TLSRecordType$Companion;", "", "()V", "byCode", "", "Lio/ktor/network/tls/TLSRecordType;", "[Lio/ktor/network/tls/TLSRecordType;", "code", "", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: TLSRecordType.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TLSRecordType byCode(int i) {
            TLSRecordType tLSRecordType = (i < 0 || i >= 256) ? null : TLSRecordType.byCode[i];
            if (tLSRecordType != null) {
                return tLSRecordType;
            }
            throw new IllegalArgumentException("Invalid TLS record type code: " + i);
        }
    }
}
