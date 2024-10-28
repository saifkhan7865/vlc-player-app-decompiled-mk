package io.ktor.http;

import io.ktor.http.ContentDisposition;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0007HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0017"}, d2 = {"Lio/ktor/http/HeaderValueParam;", "", "name", "", "value", "(Ljava/lang/String;Ljava/lang/String;)V", "escapeValue", "", "(Ljava/lang/String;Ljava/lang/String;Z)V", "getEscapeValue", "()Z", "getName", "()Ljava/lang/String;", "getValue", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: HttpHeaderValueParser.kt */
public final class HeaderValueParam {
    private final boolean escapeValue;
    private final String name;
    private final String value;

    public static /* synthetic */ HeaderValueParam copy$default(HeaderValueParam headerValueParam, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = headerValueParam.name;
        }
        if ((i & 2) != 0) {
            str2 = headerValueParam.value;
        }
        if ((i & 4) != 0) {
            z = headerValueParam.escapeValue;
        }
        return headerValueParam.copy(str, str2, z);
    }

    public final String component1() {
        return this.name;
    }

    public final String component2() {
        return this.value;
    }

    public final boolean component3() {
        return this.escapeValue;
    }

    public final HeaderValueParam copy(String str, String str2, boolean z) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(str2, "value");
        return new HeaderValueParam(str, str2, z);
    }

    public String toString() {
        return "HeaderValueParam(name=" + this.name + ", value=" + this.value + ", escapeValue=" + this.escapeValue + ')';
    }

    public HeaderValueParam(String str, String str2, boolean z) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(str2, "value");
        this.name = str;
        this.value = str2;
        this.escapeValue = z;
    }

    public final boolean getEscapeValue() {
        return this.escapeValue;
    }

    public final String getName() {
        return this.name;
    }

    public final String getValue() {
        return this.value;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public HeaderValueParam(String str, String str2) {
        this(str, str2, false);
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(str2, "value");
    }

    public boolean equals(Object obj) {
        if (obj instanceof HeaderValueParam) {
            HeaderValueParam headerValueParam = (HeaderValueParam) obj;
            if (!StringsKt.equals(headerValueParam.name, this.name, true) || !StringsKt.equals(headerValueParam.value, this.value, true)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        String lowerCase = this.name.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        int hashCode = lowerCase.hashCode();
        String lowerCase2 = this.value.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        return hashCode + (hashCode * 31) + lowerCase2.hashCode();
    }
}
