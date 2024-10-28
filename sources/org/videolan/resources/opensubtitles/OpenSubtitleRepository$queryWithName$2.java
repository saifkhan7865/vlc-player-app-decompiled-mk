package org.videolan.resources.opensubtitles;

import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.resources.opensubtitles.OpenSubtitleRepository", f = "OpenSubtitleRepository.kt", i = {0, 0, 0, 0, 0}, l = {73}, m = "queryWithName", n = {"this", "name", "destination$iv$iv", "actualEpisode", "actualSeason"}, s = {"L$0", "L$1", "L$2", "I$0", "I$1"})
/* compiled from: OpenSubtitleRepository.kt */
final class OpenSubtitleRepository$queryWithName$2 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ OpenSubtitleRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OpenSubtitleRepository$queryWithName$2(OpenSubtitleRepository openSubtitleRepository, Continuation<? super OpenSubtitleRepository$queryWithName$2> continuation) {
        super(continuation);
        this.this$0 = openSubtitleRepository;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.queryWithName((String) null, (Integer) null, (Integer) null, (List<String>) null, (Continuation<? super List<OpenSubtitle>>) this);
    }
}
