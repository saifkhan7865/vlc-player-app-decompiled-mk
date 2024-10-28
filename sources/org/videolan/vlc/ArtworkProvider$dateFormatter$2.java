package org.videolan.vlc;

import java.text.SimpleDateFormat;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\t\n\u0000\n\u0002\b\u0003*\u0001\u0001\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "org/videolan/vlc/ArtworkProvider$dateFormatter$2$1", "invoke", "()Lorg/videolan/vlc/ArtworkProvider$dateFormatter$2$1;"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ArtworkProvider.kt */
final class ArtworkProvider$dateFormatter$2 extends Lambda implements Function0<AnonymousClass1> {
    public static final ArtworkProvider$dateFormatter$2 INSTANCE = new ArtworkProvider$dateFormatter$2();

    ArtworkProvider$dateFormatter$2() {
        super(0);
    }

    public final AnonymousClass1 invoke() {
        return new ThreadLocal<SimpleDateFormat>() {
            /* access modifiers changed from: protected */
            public SimpleDateFormat initialValue() {
                return new SimpleDateFormat("hhmmss.SSS", Locale.getDefault());
            }
        };
    }
}
