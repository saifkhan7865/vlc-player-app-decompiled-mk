package io.ktor.network.sockets;

import com.google.common.base.Ascii;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\b@\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0014\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005B\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0007J\u001a\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\u0014\u0010\nJ\u0010\u0010\u0015\u001a\u00020\u0016HÖ\u0001¢\u0006\u0004\b\u0017\u0010\u0018R\u0012\u0010\b\u001a\u00020\u00038Æ\u0002¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u001c\u0010\u0002\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\f\u0001\u0002ø\u0001\u0000\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001a"}, d2 = {"Lio/ktor/network/sockets/TypeOfService;", "", "value", "", "constructor-impl", "(I)B", "Lkotlin/UByte;", "(B)B", "intValue", "getIntValue-impl", "(B)I", "getValue-w2LRezQ", "()B", "B", "equals", "", "other", "equals-impl", "(BLjava/lang/Object;)Z", "hashCode", "hashCode-impl", "toString", "", "toString-impl", "(B)Ljava/lang/String;", "Companion", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
@JvmInline
/* compiled from: TypeOfService.kt */
public final class TypeOfService {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final byte IPTOS_LOWCOST = m1448constructorimpl((byte) 2);
    /* access modifiers changed from: private */
    public static final byte IPTOS_LOWDELAY = m1448constructorimpl((byte) Ascii.DLE);
    /* access modifiers changed from: private */
    public static final byte IPTOS_RELIABILITY = m1448constructorimpl((byte) 4);
    /* access modifiers changed from: private */
    public static final byte IPTOS_THROUGHPUT = m1448constructorimpl((byte) 8);
    /* access modifiers changed from: private */
    public static final byte UNDEFINED = m1448constructorimpl((byte) 0);
    private final byte value;

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ TypeOfService m1447boximpl(byte b) {
        return new TypeOfService(b);
    }

    /* renamed from: constructor-impl  reason: not valid java name */
    public static byte m1448constructorimpl(byte b) {
        return b;
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m1450equalsimpl(byte b, Object obj) {
        return (obj instanceof TypeOfService) && b == ((TypeOfService) obj).m1456unboximpl();
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m1451equalsimpl0(byte b, byte b2) {
        return UByte.m1799equalsimpl0(b, b2);
    }

    /* renamed from: getIntValue-impl  reason: not valid java name */
    public static final int m1452getIntValueimpl(byte b) {
        return b & 255;
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m1453hashCodeimpl(byte b) {
        return UByte.m1804hashCodeimpl(b);
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m1454toStringimpl(byte b) {
        return "TypeOfService(value=" + UByte.m1836toStringimpl(b) + ')';
    }

    public boolean equals(Object obj) {
        return m1450equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m1453hashCodeimpl(this.value);
    }

    public String toString() {
        return m1454toStringimpl(this.value);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ byte m1456unboximpl() {
        return this.value;
    }

    private /* synthetic */ TypeOfService(byte b) {
        this.value = b;
    }

    /* renamed from: getValue-w2LRezQ  reason: not valid java name */
    public final byte m1455getValuew2LRezQ() {
        return this.value;
    }

    /* renamed from: constructor-impl  reason: not valid java name */
    public static byte m1449constructorimpl(int i) {
        return m1448constructorimpl(UByte.m1792constructorimpl((byte) i));
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u001c\u0010\n\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000b\u0010\u0006R\u001c\u0010\f\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\r\u0010\u0006R\u001c\u0010\u000e\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000f\u0010\u0006\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0010"}, d2 = {"Lio/ktor/network/sockets/TypeOfService$Companion;", "", "()V", "IPTOS_LOWCOST", "Lio/ktor/network/sockets/TypeOfService;", "getIPTOS_LOWCOST-zieKYfw", "()B", "B", "IPTOS_LOWDELAY", "getIPTOS_LOWDELAY-zieKYfw", "IPTOS_RELIABILITY", "getIPTOS_RELIABILITY-zieKYfw", "IPTOS_THROUGHPUT", "getIPTOS_THROUGHPUT-zieKYfw", "UNDEFINED", "getUNDEFINED-zieKYfw", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: TypeOfService.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* renamed from: getUNDEFINED-zieKYfw  reason: not valid java name */
        public final byte m1461getUNDEFINEDzieKYfw() {
            return TypeOfService.UNDEFINED;
        }

        /* renamed from: getIPTOS_LOWCOST-zieKYfw  reason: not valid java name */
        public final byte m1457getIPTOS_LOWCOSTzieKYfw() {
            return TypeOfService.IPTOS_LOWCOST;
        }

        /* renamed from: getIPTOS_RELIABILITY-zieKYfw  reason: not valid java name */
        public final byte m1459getIPTOS_RELIABILITYzieKYfw() {
            return TypeOfService.IPTOS_RELIABILITY;
        }

        /* renamed from: getIPTOS_THROUGHPUT-zieKYfw  reason: not valid java name */
        public final byte m1460getIPTOS_THROUGHPUTzieKYfw() {
            return TypeOfService.IPTOS_THROUGHPUT;
        }

        /* renamed from: getIPTOS_LOWDELAY-zieKYfw  reason: not valid java name */
        public final byte m1458getIPTOS_LOWDELAYzieKYfw() {
            return TypeOfService.IPTOS_LOWDELAY;
        }
    }
}
