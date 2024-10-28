package org.videolan.vlc.gui.dialogs;

import android.net.Uri;
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
import org.videolan.vlc.repository.BrowserFavRepository;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.dialogs.NetworkServerDialog$saveServer$1", f = "NetworkServerDialog.kt", i = {}, l = {133, 134}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: NetworkServerDialog.kt */
final class NetworkServerDialog$saveServer$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $name;
    final /* synthetic */ Uri $uri;
    int label;
    final /* synthetic */ NetworkServerDialog this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NetworkServerDialog$saveServer$1(NetworkServerDialog networkServerDialog, Uri uri, String str, Continuation<? super NetworkServerDialog$saveServer$1> continuation) {
        super(2, continuation);
        this.this$0 = networkServerDialog;
        this.$uri = uri;
        this.$name = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new NetworkServerDialog$saveServer$1(this.this$0, this.$uri, this.$name, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((NetworkServerDialog$saveServer$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.this$0.networkUri != null) {
                BrowserFavRepository access$getBrowserFavRepository$p = this.this$0.browserFavRepository;
                if (access$getBrowserFavRepository$p == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("browserFavRepository");
                    access$getBrowserFavRepository$p = null;
                }
                Uri access$getNetworkUri$p = this.this$0.networkUri;
                if (access$getNetworkUri$p == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("networkUri");
                    access$getNetworkUri$p = null;
                }
                this.label = 1;
                if (access$getBrowserFavRepository$p.deleteBrowserFav(access$getNetworkUri$p, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            ResultKt.throwOnFailure(obj);
            this.this$0.dismiss();
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        BrowserFavRepository access$getBrowserFavRepository$p2 = this.this$0.browserFavRepository;
        if (access$getBrowserFavRepository$p2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("browserFavRepository");
            access$getBrowserFavRepository$p2 = null;
        }
        this.label = 2;
        if (access$getBrowserFavRepository$p2.addNetworkFavItem(this.$uri, this.$name, (String) null, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        this.this$0.dismiss();
        return Unit.INSTANCE;
    }
}
