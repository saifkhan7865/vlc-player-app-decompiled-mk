package org.videolan.vlc.repository;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.vlc.mediadb.models.CustomDirectory;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.repository.DirectoryRepository$addCustomDirectory$1", f = "DirectoryRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: DirectoryRepository.kt */
final class DirectoryRepository$addCustomDirectory$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $path;
    int label;
    final /* synthetic */ DirectoryRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DirectoryRepository$addCustomDirectory$1(DirectoryRepository directoryRepository, String str, Continuation<? super DirectoryRepository$addCustomDirectory$1> continuation) {
        super(2, continuation);
        this.this$0 = directoryRepository;
        this.$path = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DirectoryRepository$addCustomDirectory$1(this.this$0, this.$path, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DirectoryRepository$addCustomDirectory$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.customDirectoryDao.insert(new CustomDirectory(this.$path));
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
