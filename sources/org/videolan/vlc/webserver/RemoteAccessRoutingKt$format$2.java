package org.videolan.vlc.webserver;

import java.text.DateFormat;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\t\n\u0000\n\u0002\b\u0003*\u0001\u0001\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "org/videolan/vlc/webserver/RemoteAccessRoutingKt$format$2$1", "invoke", "()Lorg/videolan/vlc/webserver/RemoteAccessRoutingKt$format$2$1;"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: RemoteAccessRouting.kt */
final class RemoteAccessRoutingKt$format$2 extends Lambda implements Function0<AnonymousClass1> {
    public static final RemoteAccessRoutingKt$format$2 INSTANCE = new RemoteAccessRoutingKt$format$2();

    RemoteAccessRoutingKt$format$2() {
        super(0);
    }

    public final AnonymousClass1 invoke() {
        return new ThreadLocal<DateFormat>() {
            /* access modifiers changed from: protected */
            public DateFormat initialValue() {
                return DateFormat.getDateTimeInstance(2, 2, Locale.getDefault());
            }
        };
    }
}
