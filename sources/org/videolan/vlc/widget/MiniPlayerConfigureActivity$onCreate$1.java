package org.videolan.vlc.widget;

import android.content.SharedPreferences;
import androidx.lifecycle.LifecycleOwnerKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.mediadb.models.Widget;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "widget", "Lorg/videolan/vlc/mediadb/models/Widget;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MiniPlayerConfigureActivity.kt */
final class MiniPlayerConfigureActivity$onCreate$1 extends Lambda implements Function1<Widget, Unit> {
    final /* synthetic */ MiniPlayerConfigureActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MiniPlayerConfigureActivity$onCreate$1(MiniPlayerConfigureActivity miniPlayerConfigureActivity) {
        super(1);
        this.this$0 = miniPlayerConfigureActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Widget) obj);
        return Unit.INSTANCE;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.widget.MiniPlayerConfigureActivity$onCreate$1$1", f = "MiniPlayerConfigureActivity.kt", i = {}, l = {99}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.widget.MiniPlayerConfigureActivity$onCreate$1$1  reason: invalid class name */
    /* compiled from: MiniPlayerConfigureActivity.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(miniPlayerConfigureActivity, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                WidgetViewModel model$vlc_android_release = miniPlayerConfigureActivity.getModel$vlc_android_release();
                MiniPlayerConfigureActivity miniPlayerConfigureActivity = miniPlayerConfigureActivity;
                this.label = 1;
                if (model$vlc_android_release.create(miniPlayerConfigureActivity, miniPlayerConfigureActivity.getAppWidgetId(), this) == coroutine_suspended) {
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

    public final void invoke(Widget widget) {
        if (widget == null) {
            final MiniPlayerConfigureActivity miniPlayerConfigureActivity = this.this$0;
            Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getIO(), (CoroutineStart) null, new AnonymousClass1((Continuation<? super AnonymousClass1>) null), 2, (Object) null);
            return;
        }
        SharedPreferences sharedPreferences = (SharedPreferences) Settings.INSTANCE.getInstance(this.this$0);
        SettingsKt.putSingle(sharedPreferences, "widget_theme", String.valueOf(widget.getTheme()));
        SettingsKt.putSingle(sharedPreferences, "opacity", Integer.valueOf(widget.getOpacity()));
        SettingsKt.putSingle(sharedPreferences, "background_color", Integer.valueOf(widget.getBackgroundColor()));
        SettingsKt.putSingle(sharedPreferences, "foreground_color", Integer.valueOf(widget.getForegroundColor()));
        this.this$0.updatePreview();
    }
}
