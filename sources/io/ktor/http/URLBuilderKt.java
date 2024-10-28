package io.ktor.http;

import androidx.core.app.FrameMetricsAggregator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.vlc.ArtworkProvider;

@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a#\u0010\u000e\u001a\u00020\u0004*\u00020\u00042\u0012\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0010\"\u00020\u0003¢\u0006\u0002\u0010\u0011\u001a\u0018\u0010\u000e\u001a\u00020\u0004*\u00020\u00042\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u0013\u001a \u0010\u0014\u001a\u00020\u0015*\u00060\u0016j\u0002`\u00172\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003H\u0002\u001a \u0010\u0019\u001a\u00020\u0015*\u00060\u0016j\u0002`\u00172\u0006\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0003H\u0002\u001a%\u0010\u001b\u001a\u00020\u0004*\u00020\u00042\u0012\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0010\"\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0011\u001a-\u0010\u001b\u001a\u00020\u0004*\u00020\u00042\u0012\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0010\"\u00020\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u001d¢\u0006\u0002\u0010\u001e\u001a\u001a\u0010\u001b\u001a\u00020\u0004*\u00020\u00042\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u0013H\u0007\u001a\"\u0010\u001b\u001a\u00020\u0004*\u00020\u00042\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u00132\b\b\u0002\u0010\u001c\u001a\u00020\u001d\u001a'\u0010\u001f\u001a\u0002H \"\f\b\u0000\u0010 *\u00060\u0016j\u0002`\u0017*\u00020\u00042\u0006\u0010!\u001a\u0002H H\u0002¢\u0006\u0002\u0010\"\u001a\n\u0010#\u001a\u00020\u0004*\u00020\u0004\u001a\u0012\u0010$\u001a\u00020\u0003*\b\u0012\u0004\u0012\u00020\u00030\u0013H\u0002\u001a#\u0010%\u001a\u00020\u0015*\u00020\u00042\u0012\u0010%\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0010\"\u00020\u0003¢\u0006\u0002\u0010&\u001a%\u0010'\u001a\u00020\u0004*\u00020\u00042\u0012\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0010\"\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0011\u001a\u001a\u0010'\u001a\u00020\u0004*\u00020\u00042\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0013H\u0007\u001aZ\u0010(\u001a\u00020\u0015*\u00020\u00042\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u00032\u0019\b\u0002\u0010+\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00150,¢\u0006\u0002\b-¢\u0006\u0002\u0010.\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0003*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\"(\u0010\b\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00038F@FX\u000e¢\u0006\f\u001a\u0004\b\t\u0010\u0006\"\u0004\b\n\u0010\u000b\"\u0018\u0010\f\u001a\u00020\u0003*\u00020\u00048@X\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u0006¨\u0006/"}, d2 = {"DEFAULT_PORT", "", "authority", "", "Lio/ktor/http/URLBuilder;", "getAuthority", "(Lio/ktor/http/URLBuilder;)Ljava/lang/String;", "value", "encodedPath", "getEncodedPath", "setEncodedPath", "(Lio/ktor/http/URLBuilder;Ljava/lang/String;)V", "encodedUserAndPassword", "getEncodedUserAndPassword", "appendEncodedPathSegments", "components", "", "(Lio/ktor/http/URLBuilder;[Ljava/lang/String;)Lio/ktor/http/URLBuilder;", "segments", "", "appendFile", "", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "host", "appendMailto", "encodedUser", "appendPathSegments", "encodeSlash", "", "(Lio/ktor/http/URLBuilder;[Ljava/lang/String;Z)Lio/ktor/http/URLBuilder;", "appendTo", "A", "out", "(Lio/ktor/http/URLBuilder;Ljava/lang/Appendable;)Ljava/lang/Appendable;", "clone", "joinPath", "path", "(Lio/ktor/http/URLBuilder;[Ljava/lang/String;)V", "pathComponents", "set", "scheme", "port", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Lio/ktor/http/URLBuilder;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: URLBuilder.kt */
public final class URLBuilderKt {
    public static final int DEFAULT_PORT = 0;

    /* access modifiers changed from: private */
    public static final <A extends Appendable> A appendTo(URLBuilder uRLBuilder, A a) {
        a.append(uRLBuilder.getProtocol().getName());
        String name = uRLBuilder.getProtocol().getName();
        if (Intrinsics.areEqual((Object) name, (Object) "file")) {
            appendFile(a, uRLBuilder.getHost(), getEncodedPath(uRLBuilder));
            return a;
        } else if (Intrinsics.areEqual((Object) name, (Object) "mailto")) {
            appendMailto(a, getEncodedUserAndPassword(uRLBuilder), uRLBuilder.getHost());
            return a;
        } else {
            a.append("://");
            a.append(getAuthority(uRLBuilder));
            URLUtilsKt.appendUrlFullPath((Appendable) a, getEncodedPath(uRLBuilder), uRLBuilder.getEncodedParameters(), uRLBuilder.getTrailingQuery());
            if (uRLBuilder.getEncodedFragment().length() > 0) {
                a.append('#');
                a.append(uRLBuilder.getEncodedFragment());
            }
            return a;
        }
    }

    private static final void appendMailto(Appendable appendable, String str, String str2) {
        appendable.append(":");
        appendable.append(str);
        appendable.append(str2);
    }

    private static final void appendFile(Appendable appendable, String str, String str2) {
        appendable.append("://");
        appendable.append(str);
        CharSequence charSequence = str2;
        if (!StringsKt.startsWith$default(charSequence, '/', false, 2, (Object) null)) {
            appendable.append('/');
        }
        appendable.append(charSequence);
    }

    public static final URLBuilder clone(URLBuilder uRLBuilder) {
        Intrinsics.checkNotNullParameter(uRLBuilder, "<this>");
        return URLUtilsKt.takeFrom(new URLBuilder((URLProtocol) null, (String) null, 0, (String) null, (String) null, (List) null, (Parameters) null, (String) null, false, FrameMetricsAggregator.EVERY_DURATION, (DefaultConstructorMarker) null), uRLBuilder);
    }

    public static final String getEncodedUserAndPassword(URLBuilder uRLBuilder) {
        Intrinsics.checkNotNullParameter(uRLBuilder, "<this>");
        StringBuilder sb = new StringBuilder();
        URLUtilsKt.appendUserAndPassword(sb, uRLBuilder.getEncodedUser(), uRLBuilder.getEncodedPassword());
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    public static /* synthetic */ URLBuilder appendPathSegments$default(URLBuilder uRLBuilder, List list, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return appendPathSegments(uRLBuilder, (List<String>) list, z);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v6, resolved type: java.util.List<java.lang.String>} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final io.ktor.http.URLBuilder appendPathSegments(io.ktor.http.URLBuilder r7, java.util.List<java.lang.String> r8, boolean r9) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r0 = "segments"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            if (r9 != 0) goto L_0x0040
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.Collection r9 = (java.util.Collection) r9
            java.util.Iterator r8 = r8.iterator()
        L_0x0019:
            boolean r0 = r8.hasNext()
            if (r0 == 0) goto L_0x003d
            java.lang.Object r0 = r8.next()
            java.lang.String r0 = (java.lang.String) r0
            r1 = r0
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r0 = 1
            char[] r2 = new char[r0]
            r0 = 47
            r3 = 0
            r2[r3] = r0
            r5 = 6
            r6 = 0
            r4 = 0
            java.util.List r0 = kotlin.text.StringsKt.split$default((java.lang.CharSequence) r1, (char[]) r2, (boolean) r3, (int) r4, (int) r5, (java.lang.Object) r6)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            kotlin.collections.CollectionsKt.addAll(r9, r0)
            goto L_0x0019
        L_0x003d:
            r8 = r9
            java.util.List r8 = (java.util.List) r8
        L_0x0040:
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.ArrayList r9 = new java.util.ArrayList
            r0 = 10
            int r0 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r8, r0)
            r9.<init>(r0)
            java.util.Collection r9 = (java.util.Collection) r9
            java.util.Iterator r8 = r8.iterator()
        L_0x0053:
            boolean r0 = r8.hasNext()
            if (r0 == 0) goto L_0x0067
            java.lang.Object r0 = r8.next()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r0 = io.ktor.http.CodecsKt.encodeURLPathPart(r0)
            r9.add(r0)
            goto L_0x0053
        L_0x0067:
            java.util.List r9 = (java.util.List) r9
            appendEncodedPathSegments((io.ktor.http.URLBuilder) r7, (java.util.List<java.lang.String>) r9)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.URLBuilderKt.appendPathSegments(io.ktor.http.URLBuilder, java.util.List, boolean):io.ktor.http.URLBuilder");
    }

    public static /* synthetic */ URLBuilder appendPathSegments$default(URLBuilder uRLBuilder, String[] strArr, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return appendPathSegments(uRLBuilder, strArr, z);
    }

    public static final URLBuilder appendPathSegments(URLBuilder uRLBuilder, String[] strArr, boolean z) {
        Intrinsics.checkNotNullParameter(uRLBuilder, "<this>");
        Intrinsics.checkNotNullParameter(strArr, "components");
        return appendPathSegments(uRLBuilder, (List<String>) ArraysKt.toList((T[]) strArr), z);
    }

    public static final URLBuilder appendEncodedPathSegments(URLBuilder uRLBuilder, List<String> list) {
        List list2;
        Intrinsics.checkNotNullParameter(uRLBuilder, "<this>");
        Intrinsics.checkNotNullParameter(list, "segments");
        boolean z = false;
        boolean z2 = uRLBuilder.getEncodedPathSegments().size() > 1 && ((CharSequence) CollectionsKt.last(uRLBuilder.getEncodedPathSegments())).length() == 0 && (list.isEmpty() ^ true);
        if (list.size() > 1 && ((CharSequence) CollectionsKt.first(list)).length() == 0 && (!uRLBuilder.getEncodedPathSegments().isEmpty())) {
            z = true;
        }
        if (z2 && z) {
            list2 = CollectionsKt.plus(CollectionsKt.dropLast(uRLBuilder.getEncodedPathSegments(), 1), CollectionsKt.drop(list, 1));
        } else if (z2) {
            list2 = CollectionsKt.plus(CollectionsKt.dropLast(uRLBuilder.getEncodedPathSegments(), 1), list);
        } else if (z) {
            list2 = CollectionsKt.plus(uRLBuilder.getEncodedPathSegments(), CollectionsKt.drop(list, 1));
        } else {
            list2 = CollectionsKt.plus(uRLBuilder.getEncodedPathSegments(), list);
        }
        uRLBuilder.setEncodedPathSegments(list2);
        return uRLBuilder;
    }

    public static final URLBuilder appendEncodedPathSegments(URLBuilder uRLBuilder, String... strArr) {
        Intrinsics.checkNotNullParameter(uRLBuilder, "<this>");
        Intrinsics.checkNotNullParameter(strArr, "components");
        return appendEncodedPathSegments(uRLBuilder, (List<String>) ArraysKt.toList((T[]) strArr));
    }

    public static final String getAuthority(URLBuilder uRLBuilder) {
        Intrinsics.checkNotNullParameter(uRLBuilder, "<this>");
        StringBuilder sb = new StringBuilder();
        sb.append(getEncodedUserAndPassword(uRLBuilder));
        sb.append(uRLBuilder.getHost());
        if (!(uRLBuilder.getPort() == 0 || uRLBuilder.getPort() == uRLBuilder.getProtocol().getDefaultPort())) {
            sb.append(":");
            sb.append(String.valueOf(uRLBuilder.getPort()));
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    public static final String getEncodedPath(URLBuilder uRLBuilder) {
        Intrinsics.checkNotNullParameter(uRLBuilder, "<this>");
        return joinPath(uRLBuilder.getEncodedPathSegments());
    }

    public static final void setEncodedPath(URLBuilder uRLBuilder, String str) {
        List<String> list;
        Intrinsics.checkNotNullParameter(uRLBuilder, "<this>");
        Intrinsics.checkNotNullParameter(str, "value");
        CharSequence charSequence = str;
        if (StringsKt.isBlank(charSequence)) {
            list = CollectionsKt.emptyList();
        } else if (Intrinsics.areEqual((Object) str, (Object) "/")) {
            list = URLParserKt.getROOT_PATH();
        } else {
            list = CollectionsKt.toMutableList(StringsKt.split$default(charSequence, new char[]{'/'}, false, 0, 6, (Object) null));
        }
        uRLBuilder.setEncodedPathSegments(list);
    }

    private static final String joinPath(List<String> list) {
        if (list.isEmpty()) {
            return "";
        }
        if (list.size() != 1) {
            return CollectionsKt.joinToString$default(list, "/", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
        }
        if (((CharSequence) CollectionsKt.first(list)).length() == 0) {
            return "/";
        }
        return (String) CollectionsKt.first(list);
    }

    public static /* synthetic */ void set$default(URLBuilder uRLBuilder, String str, String str2, Integer num, String str3, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            num = null;
        }
        if ((i & 8) != 0) {
            str3 = null;
        }
        if ((i & 16) != 0) {
            function1 = URLBuilderKt$set$1.INSTANCE;
        }
        set(uRLBuilder, str, str2, num, str3, function1);
    }

    public static final void set(URLBuilder uRLBuilder, String str, String str2, Integer num, String str3, Function1<? super URLBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(uRLBuilder, "<this>");
        Intrinsics.checkNotNullParameter(function1, "block");
        if (str != null) {
            uRLBuilder.setProtocol(URLProtocol.Companion.createOrDefault(str));
        }
        if (str2 != null) {
            uRLBuilder.setHost(str2);
        }
        if (num != null) {
            uRLBuilder.setPort(num.intValue());
        }
        if (str3 != null) {
            setEncodedPath(uRLBuilder, str3);
        }
        function1.invoke(uRLBuilder);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Plesae use method with boolean parameter")
    public static final /* synthetic */ URLBuilder appendPathSegments(URLBuilder uRLBuilder, List list) {
        Intrinsics.checkNotNullParameter(uRLBuilder, "<this>");
        Intrinsics.checkNotNullParameter(list, "segments");
        return appendPathSegments(uRLBuilder, (List<String>) list, false);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Plesae use method with boolean parameter")
    public static final /* synthetic */ URLBuilder appendPathSegments(URLBuilder uRLBuilder, String... strArr) {
        Intrinsics.checkNotNullParameter(uRLBuilder, "<this>");
        Intrinsics.checkNotNullParameter(strArr, "components");
        return appendPathSegments(uRLBuilder, (List<String>) ArraysKt.toList((T[]) strArr), false);
    }

    @Deprecated(message = "Please use appendPathSegments method", replaceWith = @ReplaceWith(expression = "this.appendPathSegments(components", imports = {}))
    public static final URLBuilder pathComponents(URLBuilder uRLBuilder, String... strArr) {
        Intrinsics.checkNotNullParameter(uRLBuilder, "<this>");
        Intrinsics.checkNotNullParameter(strArr, "components");
        return appendPathSegments$default(uRLBuilder, ArraysKt.toList((T[]) strArr), false, 2, (Object) null);
    }

    @Deprecated(message = "Please use appendPathSegments method", replaceWith = @ReplaceWith(expression = "this.appendPathSegments(components", imports = {}))
    public static final URLBuilder pathComponents(URLBuilder uRLBuilder, List<String> list) {
        Intrinsics.checkNotNullParameter(uRLBuilder, "<this>");
        Intrinsics.checkNotNullParameter(list, "components");
        return appendPathSegments$default(uRLBuilder, (List) list, false, 2, (Object) null);
    }

    public static final void path(URLBuilder uRLBuilder, String... strArr) {
        Intrinsics.checkNotNullParameter(uRLBuilder, "<this>");
        Intrinsics.checkNotNullParameter(strArr, ArtworkProvider.PATH);
        Collection arrayList = new ArrayList(strArr.length);
        for (String encodeURLPath : strArr) {
            arrayList.add(CodecsKt.encodeURLPath(encodeURLPath));
        }
        uRLBuilder.setEncodedPathSegments((List) arrayList);
    }
}
