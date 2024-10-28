package org.videolan.vlc.repository;

import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.vlc.mediadb.models.CustomDirectory;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Lorg/videolan/vlc/mediadb/models/CustomDirectory;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.repository.DirectoryRepository$getCustomDirectories$2", f = "DirectoryRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: DirectoryRepository.kt */
final class DirectoryRepository$getCustomDirectories$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends CustomDirectory>>, Object> {
    int label;
    final /* synthetic */ DirectoryRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DirectoryRepository$getCustomDirectories$2(DirectoryRepository directoryRepository, Continuation<? super DirectoryRepository$getCustomDirectories$2> continuation) {
        super(2, continuation);
        this.this$0 = directoryRepository;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DirectoryRepository$getCustomDirectories$2(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<CustomDirectory>> continuation) {
        return ((DirectoryRepository$getCustomDirectories$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                return this.this$0.customDirectoryDao.getAll();
            } catch (Exception unused) {
                return CollectionsKt.emptyList();
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
