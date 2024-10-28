package org.videolan.resources.opensubtitles;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\bf\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Lorg/videolan/resources/opensubtitles/OpenSubtitleClient;", "", "Companion", "resources_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: OpenSubtitleService.kt */
public interface OpenSubtitleClient {
    public static final Companion Companion = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lorg/videolan/resources/opensubtitles/OpenSubtitleClient$Companion;", "", "()V", "instance", "Lorg/videolan/resources/opensubtitles/IOpenSubtitleService;", "getInstance", "()Lorg/videolan/resources/opensubtitles/IOpenSubtitleService;", "instance$delegate", "Lkotlin/Lazy;", "resources_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: OpenSubtitleService.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final Lazy<IOpenSubtitleService> instance$delegate = LazyKt.lazy(OpenSubtitleClient$Companion$instance$2.INSTANCE);

        private Companion() {
        }

        public final IOpenSubtitleService getInstance() {
            IOpenSubtitleService value = instance$delegate.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return value;
        }
    }
}
