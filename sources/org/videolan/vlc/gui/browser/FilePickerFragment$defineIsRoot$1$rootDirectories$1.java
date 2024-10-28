package org.videolan.vlc.gui.browser;

import android.content.Context;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.vlc.repository.DirectoryRepository;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.browser.FilePickerFragment$defineIsRoot$1$rootDirectories$1", f = "FilePickerFragment.kt", i = {}, l = {134}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: FilePickerFragment.kt */
final class FilePickerFragment$defineIsRoot$1$rootDirectories$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<String>>, Object> {
    int label;
    final /* synthetic */ FilePickerFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FilePickerFragment$defineIsRoot$1$rootDirectories$1(FilePickerFragment filePickerFragment, Continuation<? super FilePickerFragment$defineIsRoot$1$rootDirectories$1> continuation) {
        super(2, continuation);
        this.this$0 = filePickerFragment;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FilePickerFragment$defineIsRoot$1$rootDirectories$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<String>> continuation) {
        return ((FilePickerFragment$defineIsRoot$1$rootDirectories$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            DirectoryRepository.Companion companion = DirectoryRepository.Companion;
            Context requireContext = this.this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
            this.label = 1;
            obj = ((DirectoryRepository) companion.getInstance(requireContext)).getMediaDirectories(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
