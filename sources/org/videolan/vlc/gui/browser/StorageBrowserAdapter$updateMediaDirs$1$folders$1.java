package org.videolan.vlc.gui.browser;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.Medialibrary;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a(\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002 \u0003*\u0014\u0012\u000e\b\u0001\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0018\u00010\u00010\u0001*\u00020\u0004H@"}, d2 = {"<anonymous>", "", "", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.browser.StorageBrowserAdapter$updateMediaDirs$1$folders$1", f = "StorageBrowserAdapter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: StorageBrowserAdapter.kt */
final class StorageBrowserAdapter$updateMediaDirs$1$folders$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String[]>, Object> {
    int label;

    StorageBrowserAdapter$updateMediaDirs$1$folders$1(Continuation<? super StorageBrowserAdapter$updateMediaDirs$1$folders$1> continuation) {
        super(2, continuation);
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new StorageBrowserAdapter$updateMediaDirs$1$folders$1(continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String[]> continuation) {
        return ((StorageBrowserAdapter$updateMediaDirs$1$folders$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return Medialibrary.getInstance().getFoldersList();
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
