package org.videolan.resources.opensubtitles;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lorg/videolan/resources/opensubtitles/OpenSubtitleRepository;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: OpenSubtitleRepository.kt */
final class OpenSubtitleRepository$Companion$instance$1 extends Lambda implements Function0<OpenSubtitleRepository> {
    public static final OpenSubtitleRepository$Companion$instance$1 INSTANCE = new OpenSubtitleRepository$Companion$instance$1();

    OpenSubtitleRepository$Companion$instance$1() {
        super(0);
    }

    public final OpenSubtitleRepository invoke() {
        return new OpenSubtitleRepository(OpenSubtitleClient.Companion.getInstance());
    }
}
