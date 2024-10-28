package org.videolan.resources.util;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.interfaces.Medialibrary;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 176)
/* compiled from: Extensions.kt */
public final class ExtensionsKt$waitForML$2$1$1 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ ExtensionsKt$waitForML$2$1$listener$1 $listener;
    final /* synthetic */ Medialibrary $ml;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExtensionsKt$waitForML$2$1$1(Medialibrary medialibrary, ExtensionsKt$waitForML$2$1$listener$1 extensionsKt$waitForML$2$1$listener$1) {
        super(1);
        this.$ml = medialibrary;
        this.$listener = extensionsKt$waitForML$2$1$listener$1;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Throwable th) {
        this.$ml.removeOnMedialibraryReadyListener(this.$listener);
    }
}
