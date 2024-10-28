package org.videolan.vlc.gui.browser;

import java.io.File;
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
import org.videolan.vlc.viewmodels.browser.BrowserModel;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.browser.StorageBrowserFragment$showAddDirectoryDialog$2$2", f = "StorageBrowserFragment.kt", i = {}, l = {181}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: StorageBrowserFragment.kt */
final class StorageBrowserFragment$showAddDirectoryDialog$2$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ File $f;
    int label;
    final /* synthetic */ StorageBrowserFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StorageBrowserFragment$showAddDirectoryDialog$2$2(StorageBrowserFragment storageBrowserFragment, File file, Continuation<? super StorageBrowserFragment$showAddDirectoryDialog$2$2> continuation) {
        super(2, continuation);
        this.this$0 = storageBrowserFragment;
        this.$f = file;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new StorageBrowserFragment$showAddDirectoryDialog$2$2(this.this$0, this.$f, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((StorageBrowserFragment$showAddDirectoryDialog$2$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            String canonicalPath = this.$f.getCanonicalPath();
            Intrinsics.checkNotNullExpressionValue(canonicalPath, "getCanonicalPath(...)");
            this.label = 1;
            if (((BrowserModel) this.this$0.getViewModel()).addCustomDirectory(canonicalPath).join(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ((BrowserModel) this.this$0.getViewModel()).browseRoot();
        return Unit.INSTANCE;
    }
}
