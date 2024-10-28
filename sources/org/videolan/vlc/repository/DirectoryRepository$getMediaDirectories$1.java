package org.videolan.vlc.repository;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.repository.DirectoryRepository", f = "DirectoryRepository.kt", i = {}, l = {43}, m = "getMediaDirectories", n = {}, s = {})
/* compiled from: DirectoryRepository.kt */
final class DirectoryRepository$getMediaDirectories$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DirectoryRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DirectoryRepository$getMediaDirectories$1(DirectoryRepository directoryRepository, Continuation<? super DirectoryRepository$getMediaDirectories$1> continuation) {
        super(continuation);
        this.this$0 = directoryRepository;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getMediaDirectories(this);
    }
}
