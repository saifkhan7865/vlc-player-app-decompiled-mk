package org.videolan.resources.opensubtitles;

import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.resources.opensubtitles.OpenSubtitleRepository", f = "OpenSubtitleRepository.kt", i = {0, 0, 0, 0}, l = {61}, m = "queryWithHash", n = {"this", "movieHash", "destination$iv$iv", "movieByteSize"}, s = {"L$0", "L$1", "L$2", "J$0"})
/* compiled from: OpenSubtitleRepository.kt */
final class OpenSubtitleRepository$queryWithHash$2 extends ContinuationImpl {
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ OpenSubtitleRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OpenSubtitleRepository$queryWithHash$2(OpenSubtitleRepository openSubtitleRepository, Continuation<? super OpenSubtitleRepository$queryWithHash$2> continuation) {
        super(continuation);
        this.this$0 = openSubtitleRepository;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.queryWithHash(0, (String) null, (List<String>) null, (Continuation<? super List<OpenSubtitle>>) this);
    }
}
