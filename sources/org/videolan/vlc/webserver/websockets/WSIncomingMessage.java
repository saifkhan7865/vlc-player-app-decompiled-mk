package org.videolan.vlc.webserver.websockets;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\fJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0013J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0016J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003JT\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010!J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020\u0005HÖ\u0001J\t\u0010&\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000eR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u000e¨\u0006'"}, d2 = {"Lorg/videolan/vlc/webserver/websockets/WSIncomingMessage;", "", "message", "", "id", "", "floatValue", "", "longValue", "", "stringValue", "authTicket", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Float;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;)V", "getAuthTicket", "()Ljava/lang/String;", "getFloatValue", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getLongValue", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getMessage", "getStringValue", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Float;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;)Lorg/videolan/vlc/webserver/websockets/WSIncomingMessage;", "equals", "", "other", "hashCode", "toString", "webserver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: WSIncomingMessage.kt */
public final class WSIncomingMessage {
    private final String authTicket;
    private final Float floatValue;
    private final Integer id;
    private final Long longValue;
    private final String message;
    private final String stringValue;

    public static /* synthetic */ WSIncomingMessage copy$default(WSIncomingMessage wSIncomingMessage, String str, Integer num, Float f, Long l, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = wSIncomingMessage.message;
        }
        if ((i & 2) != 0) {
            num = wSIncomingMessage.id;
        }
        Integer num2 = num;
        if ((i & 4) != 0) {
            f = wSIncomingMessage.floatValue;
        }
        Float f2 = f;
        if ((i & 8) != 0) {
            l = wSIncomingMessage.longValue;
        }
        Long l2 = l;
        if ((i & 16) != 0) {
            str2 = wSIncomingMessage.stringValue;
        }
        String str4 = str2;
        if ((i & 32) != 0) {
            str3 = wSIncomingMessage.authTicket;
        }
        return wSIncomingMessage.copy(str, num2, f2, l2, str4, str3);
    }

    public final String component1() {
        return this.message;
    }

    public final Integer component2() {
        return this.id;
    }

    public final Float component3() {
        return this.floatValue;
    }

    public final Long component4() {
        return this.longValue;
    }

    public final String component5() {
        return this.stringValue;
    }

    public final String component6() {
        return this.authTicket;
    }

    public final WSIncomingMessage copy(String str, Integer num, Float f, Long l, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "message");
        return new WSIncomingMessage(str, num, f, l, str2, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WSIncomingMessage)) {
            return false;
        }
        WSIncomingMessage wSIncomingMessage = (WSIncomingMessage) obj;
        return Intrinsics.areEqual((Object) this.message, (Object) wSIncomingMessage.message) && Intrinsics.areEqual((Object) this.id, (Object) wSIncomingMessage.id) && Intrinsics.areEqual((Object) this.floatValue, (Object) wSIncomingMessage.floatValue) && Intrinsics.areEqual((Object) this.longValue, (Object) wSIncomingMessage.longValue) && Intrinsics.areEqual((Object) this.stringValue, (Object) wSIncomingMessage.stringValue) && Intrinsics.areEqual((Object) this.authTicket, (Object) wSIncomingMessage.authTicket);
    }

    public int hashCode() {
        int hashCode = this.message.hashCode() * 31;
        Integer num = this.id;
        int i = 0;
        int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
        Float f = this.floatValue;
        int hashCode3 = (hashCode2 + (f == null ? 0 : f.hashCode())) * 31;
        Long l = this.longValue;
        int hashCode4 = (hashCode3 + (l == null ? 0 : l.hashCode())) * 31;
        String str = this.stringValue;
        int hashCode5 = (hashCode4 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.authTicket;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode5 + i;
    }

    public String toString() {
        return "WSIncomingMessage(message=" + this.message + ", id=" + this.id + ", floatValue=" + this.floatValue + ", longValue=" + this.longValue + ", stringValue=" + this.stringValue + ", authTicket=" + this.authTicket + ')';
    }

    public WSIncomingMessage(String str, Integer num, Float f, Long l, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "message");
        this.message = str;
        this.id = num;
        this.floatValue = f;
        this.longValue = l;
        this.stringValue = str2;
        this.authTicket = str3;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ WSIncomingMessage(String str, Integer num, Float f, Long l, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, num, (i & 4) != 0 ? null : f, (i & 8) != 0 ? null : l, (i & 16) != 0 ? null : str2, (i & 32) != 0 ? null : str3);
    }

    public final String getMessage() {
        return this.message;
    }

    public final Integer getId() {
        return this.id;
    }

    public final Float getFloatValue() {
        return this.floatValue;
    }

    public final Long getLongValue() {
        return this.longValue;
    }

    public final String getStringValue() {
        return this.stringValue;
    }

    public final String getAuthTicket() {
        return this.authTicket;
    }
}
