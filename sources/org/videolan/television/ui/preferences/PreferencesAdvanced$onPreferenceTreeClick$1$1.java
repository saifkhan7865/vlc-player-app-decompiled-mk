package org.videolan.television.ui.preferences;

import android.app.Application;
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
import org.videolan.vlc.gui.dialogs.UpdateDialog;
import org.videolan.vlc.gui.dialogs.UpdateDialogKt;
import org.videolan.vlc.util.AutoUpdate;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.preferences.PreferencesAdvanced$onPreferenceTreeClick$1$1", f = "PreferencesAdvanced.kt", i = {}, l = {154}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PreferencesAdvanced.kt */
final class PreferencesAdvanced$onPreferenceTreeClick$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ PreferencesActivity $appCompatActivity;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreferencesAdvanced$onPreferenceTreeClick$1$1(PreferencesActivity preferencesActivity, Continuation<? super PreferencesAdvanced$onPreferenceTreeClick$1$1> continuation) {
        super(2, continuation);
        this.$appCompatActivity = preferencesActivity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PreferencesAdvanced$onPreferenceTreeClick$1$1(this.$appCompatActivity, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PreferencesAdvanced$onPreferenceTreeClick$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AutoUpdate autoUpdate = AutoUpdate.INSTANCE;
            Application application = this.$appCompatActivity.getApplication();
            Intrinsics.checkNotNullExpressionValue(application, "getApplication(...)");
            final PreferencesActivity preferencesActivity = this.$appCompatActivity;
            this.label = 1;
            if (autoUpdate.checkUpdate(application, true, new Function2<String, Date, Unit>() {
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((String) obj, (Date) obj2);
                    return Unit.INSTANCE;
                }

                public final void invoke(String str, Date date) {
                    Intrinsics.checkNotNullParameter(str, RtspHeaders.Values.URL);
                    Intrinsics.checkNotNullParameter(date, "date");
                    UpdateDialog updateDialog = new UpdateDialog();
                    updateDialog.setArguments(BundleKt.bundleOf(TuplesKt.to(UpdateDialogKt.UPDATE_URL, str), TuplesKt.to(UpdateDialogKt.UPDATE_DATE, Long.valueOf(date.getTime())), TuplesKt.to(UpdateDialogKt.NEW_INSTALL, true)));
                    updateDialog.show(preferencesActivity.getSupportFragmentManager(), "fragment_update");
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
