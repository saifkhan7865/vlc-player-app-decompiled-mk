package org.videolan.vlc.gui.dialogs;

import android.app.Application;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.vlc.databinding.DialogUpdateBinding;
import org.videolan.vlc.util.AutoUpdate;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.dialogs.UpdateDialog$onCreateView$1$1", f = "UpdateDialog.kt", i = {}, l = {102}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: UpdateDialog.kt */
final class UpdateDialog$onCreateView$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ UpdateDialog this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    UpdateDialog$onCreateView$1$1(UpdateDialog updateDialog, Continuation<? super UpdateDialog$onCreateView$1$1> continuation) {
        super(2, continuation);
        this.this$0 = updateDialog;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new UpdateDialog$onCreateView$1$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UpdateDialog$onCreateView$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AutoUpdate autoUpdate = AutoUpdate.INSTANCE;
            Application application = this.this$0.requireActivity().getApplication();
            Intrinsics.checkNotNullExpressionValue(application, "getApplication(...)");
            String access$getUpdateURL$p = this.this$0.updateURL;
            if (access$getUpdateURL$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("updateURL");
                access$getUpdateURL$p = null;
            }
            final UpdateDialog updateDialog = this.this$0;
            this.label = 1;
            if (autoUpdate.downloadAndInstall(application, access$getUpdateURL$p, new Function1<Boolean, Unit>() {
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke(((Boolean) obj).booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    DialogUpdateBinding dialogUpdateBinding = null;
                    if (z) {
                        DialogUpdateBinding access$getBinding$p = updateDialog.binding;
                        if (access$getBinding$p == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            access$getBinding$p = null;
                        }
                        access$getBinding$p.downloadProgress.setVisibility(0);
                        DialogUpdateBinding access$getBinding$p2 = updateDialog.binding;
                        if (access$getBinding$p2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            dialogUpdateBinding = access$getBinding$p2;
                        }
                        dialogUpdateBinding.downloadProgress.setIndeterminate(true);
                        return;
                    }
                    DialogUpdateBinding access$getBinding$p3 = updateDialog.binding;
                    if (access$getBinding$p3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        dialogUpdateBinding = access$getBinding$p3;
                    }
                    dialogUpdateBinding.downloadProgress.setVisibility(8);
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
