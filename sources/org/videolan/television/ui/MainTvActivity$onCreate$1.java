package org.videolan.television.ui;

import android.app.Application;
import android.content.SharedPreferences;
import androidx.core.os.BundleKt;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.util.Date;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.gui.dialogs.UpdateDialog;
import org.videolan.vlc.gui.dialogs.UpdateDialogKt;
import org.videolan.vlc.util.AutoUpdate;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.MainTvActivity$onCreate$1", f = "MainTvActivity.kt", i = {}, l = {83, 85}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MainTvActivity.kt */
final class MainTvActivity$onCreate$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ MainTvActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MainTvActivity$onCreate$1(MainTvActivity mainTvActivity, Continuation<? super MainTvActivity$onCreate$1> continuation) {
        super(2, continuation);
        this.this$0 = mainTvActivity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainTvActivity$onCreate$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainTvActivity$onCreate$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AutoUpdate autoUpdate = AutoUpdate.INSTANCE;
            Application application = this.this$0.getApplication();
            Intrinsics.checkNotNullExpressionValue(application, "getApplication(...)");
            this.label = 1;
            if (autoUpdate.clean(application, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (!((SharedPreferences) Settings.INSTANCE.getInstance(this.this$0)).getBoolean(SettingsKt.KEY_SHOW_UPDATE, true)) {
            return Unit.INSTANCE;
        }
        AutoUpdate autoUpdate2 = AutoUpdate.INSTANCE;
        Application application2 = this.this$0.getApplication();
        Intrinsics.checkNotNullExpressionValue(application2, "getApplication(...)");
        final MainTvActivity mainTvActivity = this.this$0;
        this.label = 2;
        if (AutoUpdate.checkUpdate$default(autoUpdate2, application2, false, new Function2<String, Date, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((String) obj, (Date) obj2);
                return Unit.INSTANCE;
            }

            public final void invoke(String str, Date date) {
                Intrinsics.checkNotNullParameter(str, RtspHeaders.Values.URL);
                Intrinsics.checkNotNullParameter(date, "date");
                UpdateDialog updateDialog = new UpdateDialog();
                updateDialog.setArguments(BundleKt.bundleOf(TuplesKt.to(UpdateDialogKt.UPDATE_URL, str), TuplesKt.to(UpdateDialogKt.UPDATE_DATE, Long.valueOf(date.getTime()))));
                updateDialog.show(mainTvActivity.getSupportFragmentManager(), "fragment_update");
            }
        }, this, 2, (Object) null) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
