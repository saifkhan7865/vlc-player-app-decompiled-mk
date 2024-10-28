package org.videolan.mobile.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.videolan.libvlc.Dialog;
import org.videolan.libvlc.interfaces.ILibVLC;
import org.videolan.resources.AndroidDevices;
import org.videolan.resources.AppContextProvider;
import org.videolan.resources.VLCInstance;
import org.videolan.vlc.gui.SendCrashActivity;
import org.videolan.vlc.util.DialogDelegate;
import org.videolan.vlc.util.NetworkConnectionManager;
import org.videolan.vlc.util.VersionMigration;
import org.videolan.vlc.widget.MiniPlayerAppWidgetProvider;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.mobile.app.AppSetupDelegate$backgroundInit$1", f = "AppSetupDelegate.kt", i = {0}, l = {119}, m = "invokeSuspend", n = {"$this$outerLaunch"}, s = {"L$0"})
/* compiled from: AppSetupDelegate.kt */
final class AppSetupDelegate$backgroundInit$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $this_backgroundInit;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ AppSetupDelegate this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AppSetupDelegate$backgroundInit$1(Context context, AppSetupDelegate appSetupDelegate, Continuation<? super AppSetupDelegate$backgroundInit$1> continuation) {
        super(2, continuation);
        this.$this_backgroundInit = context;
        this.this$0 = appSetupDelegate;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AppSetupDelegate$backgroundInit$1 appSetupDelegate$backgroundInit$1 = new AppSetupDelegate$backgroundInit$1(this.$this_backgroundInit, this.this$0, continuation);
        appSetupDelegate$backgroundInit$1.L$0 = obj;
        return appSetupDelegate$backgroundInit$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AppSetupDelegate$backgroundInit$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            this.L$0 = coroutineScope2;
            this.label = 1;
            if (VersionMigration.INSTANCE.migrateVersion(this.$this_backgroundInit, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            coroutineScope = coroutineScope2;
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = (CoroutineScope) this.L$0;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        final Context context = this.$this_backgroundInit;
        Job unused = BuildersKt__Builders_commonKt.launch$default(coroutineScope, Dispatchers.getIO(), (CoroutineStart) null, new AnonymousClass1((Continuation<? super AnonymousClass1>) null), 2, (Object) null);
        this.$this_backgroundInit.getPackageManager().setComponentEnabledSetting(new ComponentName(this.$this_backgroundInit, SendCrashActivity.class), 1, 1);
        if (!AndroidDevices.INSTANCE.isAndroidTv()) {
            Context context2 = this.$this_backgroundInit;
            Intent intent = new Intent(MiniPlayerAppWidgetProvider.Companion.getACTION_WIDGET_INIT());
            intent.setComponent(new ComponentName(this.this$0.getAppContextProvider().getAppContext(), MiniPlayerAppWidgetProvider.class));
            context2.sendBroadcast(intent);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            NetworkConnectionManager.INSTANCE.start(AppContextProvider.INSTANCE.getAppContext());
        }
        return Unit.INSTANCE;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.mobile.app.AppSetupDelegate$backgroundInit$1$1", f = "AppSetupDelegate.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.mobile.app.AppSetupDelegate$backgroundInit$1$1  reason: invalid class name */
    /* compiled from: AppSetupDelegate.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(context, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (!VLCInstance.INSTANCE.testCompatibleCPU(AppContextProvider.INSTANCE.getAppContext())) {
                    return Unit.INSTANCE;
                }
                Dialog.setCallbacks((ILibVLC) VLCInstance.INSTANCE.getInstance(context), DialogDelegate.DialogsListener);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
