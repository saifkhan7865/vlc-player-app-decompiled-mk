package org.videolan.resources.opensubtitles;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lorg/videolan/resources/opensubtitles/IOpenSubtitleService;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: OpenSubtitleService.kt */
final class OpenSubtitleClient$Companion$instance$2 extends Lambda implements Function0<IOpenSubtitleService> {
    public static final OpenSubtitleClient$Companion$instance$2 INSTANCE = new OpenSubtitleClient$Companion$instance$2();

    OpenSubtitleClient$Companion$instance$2() {
        super(0);
    }

    public final IOpenSubtitleService invoke() {
        return OpenSubtitleServiceKt.buildClient();
    }
}
