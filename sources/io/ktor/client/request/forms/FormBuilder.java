package io.ktor.client.request.forms;

import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import io.ktor.http.Headers;
import io.ktor.util.InternalAPI;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.Input;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.HttpUrl;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0012\n\u0002\u0010\u0004\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0006\u001a\u00020\u0007\"\b\b\u0000\u0010\b*\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\u0005J1\u0010\u0006\u001a\u00020\u0007\"\b\b\u0000\u0010\b*\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u0002H\b2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0007¢\u0006\u0002\u0010\u000fJ \u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00102\b\b\u0002\u0010\r\u001a\u00020\u000eJ \u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00112\b\b\u0002\u0010\r\u001a\u00020\u000eJ \u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00122\b\b\u0002\u0010\r\u001a\u00020\u000eJ+\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00142\b\b\u0002\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u0015J \u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00162\b\b\u0002\u0010\r\u001a\u00020\u000eJ \u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00172\b\b\u0002\u0010\r\u001a\u00020\u000eJ \u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00182\b\b\u0002\u0010\r\u001a\u00020\u000eJ \u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000eJ&\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00192\b\b\u0002\u0010\r\u001a\u00020\u000eJ7\u0010\u001a\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e¢\u0006\u0002\u0010 J\u0017\u0010!\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\"H\u0000¢\u0006\u0002\b#R\u0018\u0010\u0003\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lio/ktor/client/request/forms/FormBuilder;", "", "()V", "parts", "", "Lio/ktor/client/request/forms/FormPart;", "append", "", "T", "part", "key", "", "value", "headers", "Lio/ktor/http/Headers;", "(Ljava/lang/String;Ljava/lang/Object;Lio/ktor/http/Headers;)V", "Lio/ktor/client/request/forms/ChannelProvider;", "Lio/ktor/client/request/forms/InputProvider;", "Lio/ktor/utils/io/core/ByteReadPacket;", "values", "", "(Ljava/lang/String;[Ljava/lang/String;Lio/ktor/http/Headers;)V", "", "", "", "", "appendInput", "size", "", "block", "Lkotlin/Function0;", "Lio/ktor/utils/io/core/Input;", "(Ljava/lang/String;Lio/ktor/http/Headers;Ljava/lang/Long;Lkotlin/jvm/functions/Function0;)V", "build", "", "build$ktor_client_core", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: formDsl.kt */
public final class FormBuilder {
    private final List<FormPart<?>> parts = new ArrayList();

    public static /* synthetic */ void append$default(FormBuilder formBuilder, String str, Object obj, Headers headers, int i, Object obj2) {
        if ((i & 4) != 0) {
            headers = Headers.Companion.getEmpty();
        }
        formBuilder.append(str, obj, headers);
    }

    @InternalAPI
    public final <T> void append(String str, T t, Headers headers) {
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(t, "value");
        Intrinsics.checkNotNullParameter(headers, "headers");
        this.parts.add(new FormPart(str, t, headers));
    }

    public static /* synthetic */ void append$default(FormBuilder formBuilder, String str, String str2, Headers headers, int i, Object obj) {
        if ((i & 4) != 0) {
            headers = Headers.Companion.getEmpty();
        }
        formBuilder.append(str, str2, headers);
    }

    public final void append(String str, String str2, Headers headers) {
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(str2, "value");
        Intrinsics.checkNotNullParameter(headers, "headers");
        this.parts.add(new FormPart(str, str2, headers));
    }

    public static /* synthetic */ void append$default(FormBuilder formBuilder, String str, Number number, Headers headers, int i, Object obj) {
        if ((i & 4) != 0) {
            headers = Headers.Companion.getEmpty();
        }
        formBuilder.append(str, number, headers);
    }

    public final void append(String str, Number number, Headers headers) {
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(number, "value");
        Intrinsics.checkNotNullParameter(headers, "headers");
        this.parts.add(new FormPart(str, number, headers));
    }

    public static /* synthetic */ void append$default(FormBuilder formBuilder, String str, boolean z, Headers headers, int i, Object obj) {
        if ((i & 4) != 0) {
            headers = Headers.Companion.getEmpty();
        }
        formBuilder.append(str, z, headers);
    }

    public final void append(String str, boolean z, Headers headers) {
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(headers, "headers");
        this.parts.add(new FormPart(str, Boolean.valueOf(z), headers));
    }

    public static /* synthetic */ void append$default(FormBuilder formBuilder, String str, byte[] bArr, Headers headers, int i, Object obj) {
        if ((i & 4) != 0) {
            headers = Headers.Companion.getEmpty();
        }
        formBuilder.append(str, bArr, headers);
    }

    public final void append(String str, byte[] bArr, Headers headers) {
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(bArr, "value");
        Intrinsics.checkNotNullParameter(headers, "headers");
        this.parts.add(new FormPart(str, bArr, headers));
    }

    public static /* synthetic */ void append$default(FormBuilder formBuilder, String str, InputProvider inputProvider, Headers headers, int i, Object obj) {
        if ((i & 4) != 0) {
            headers = Headers.Companion.getEmpty();
        }
        formBuilder.append(str, inputProvider, headers);
    }

    public final void append(String str, InputProvider inputProvider, Headers headers) {
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(inputProvider, "value");
        Intrinsics.checkNotNullParameter(headers, "headers");
        this.parts.add(new FormPart(str, inputProvider, headers));
    }

    public static /* synthetic */ void appendInput$default(FormBuilder formBuilder, String str, Headers headers, Long l, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            headers = Headers.Companion.getEmpty();
        }
        if ((i & 4) != 0) {
            l = null;
        }
        formBuilder.appendInput(str, headers, l, function0);
    }

    public final void appendInput(String str, Headers headers, Long l, Function0<? extends Input> function0) {
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(function0, "block");
        this.parts.add(new FormPart(str, new InputProvider(l, function0), headers));
    }

    public static /* synthetic */ void append$default(FormBuilder formBuilder, String str, ByteReadPacket byteReadPacket, Headers headers, int i, Object obj) {
        if ((i & 4) != 0) {
            headers = Headers.Companion.getEmpty();
        }
        formBuilder.append(str, byteReadPacket, headers);
    }

    public final void append(String str, ByteReadPacket byteReadPacket, Headers headers) {
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(byteReadPacket, "value");
        Intrinsics.checkNotNullParameter(headers, "headers");
        this.parts.add(new FormPart(str, byteReadPacket, headers));
    }

    public static /* synthetic */ void append$default(FormBuilder formBuilder, String str, Iterable iterable, Headers headers, int i, Object obj) {
        if ((i & 4) != 0) {
            headers = Headers.Companion.getEmpty();
        }
        formBuilder.append(str, (Iterable<String>) iterable, headers);
    }

    public final void append(String str, Iterable<String> iterable, Headers headers) {
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(iterable, "values");
        Intrinsics.checkNotNullParameter(headers, "headers");
        if (StringsKt.endsWith$default(str, HttpUrl.PATH_SEGMENT_ENCODE_SET_URI, false, 2, (Object) null)) {
            for (String formPart : iterable) {
                this.parts.add(new FormPart(str, formPart, headers));
            }
            return;
        }
        throw new IllegalArgumentException(("Array parameter must be suffixed with square brackets ie `" + str + "[]`").toString());
    }

    public static /* synthetic */ void append$default(FormBuilder formBuilder, String str, String[] strArr, Headers headers, int i, Object obj) {
        if ((i & 4) != 0) {
            headers = Headers.Companion.getEmpty();
        }
        formBuilder.append(str, strArr, headers);
    }

    public final void append(String str, String[] strArr, Headers headers) {
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(strArr, "values");
        Intrinsics.checkNotNullParameter(headers, "headers");
        append(str, (Iterable<String>) ArraysKt.asIterable((T[]) strArr), headers);
    }

    public static /* synthetic */ void append$default(FormBuilder formBuilder, String str, ChannelProvider channelProvider, Headers headers, int i, Object obj) {
        if ((i & 4) != 0) {
            headers = Headers.Companion.getEmpty();
        }
        formBuilder.append(str, channelProvider, headers);
    }

    public final void append(String str, ChannelProvider channelProvider, Headers headers) {
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(channelProvider, "value");
        Intrinsics.checkNotNullParameter(headers, "headers");
        this.parts.add(new FormPart(str, channelProvider, headers));
    }

    public final <T> void append(FormPart<T> formPart) {
        Intrinsics.checkNotNullParameter(formPart, "part");
        this.parts.add(formPart);
    }

    public final List<FormPart<?>> build$ktor_client_core() {
        return this.parts;
    }
}
