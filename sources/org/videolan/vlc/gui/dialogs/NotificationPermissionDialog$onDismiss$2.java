package org.videolan.vlc.gui.dialogs;

import androidx.fragment.app.FragmentActivity;
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
import org.videolan.vlc.gui.helpers.hf.NotificationDelegate;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.dialogs.NotificationPermissionDialog$onDismiss$2", f = "NotificationPermissionDialog.kt", i = {}, l = {66}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: NotificationPermissionDialog.kt */
final class NotificationPermissionDialog$onDismiss$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ NotificationPermissionDialog this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NotificationPermissionDialog$onDismiss$2(NotificationPermissionDialog notificationPermissionDialog, Continuation<? super NotificationPermissionDialog$onDismiss$2> continuation) {
        super(2, continuation);
        this.this$0 = notificationPermissionDialog;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new NotificationPermissionDialog$onDismiss$2(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((NotificationPermissionDialog$onDismiss$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            NotificationDelegate.Companion companion = NotificationDelegate.Companion;
            FragmentActivity requireActivity = this.this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            this.label = 1;
            if (companion.getNotificationPermission(requireActivity, this) == coroutine_suspended) {
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
