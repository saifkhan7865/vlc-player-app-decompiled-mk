package org.videolan.vlc.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RemoteViews;
import androidx.palette.graphics.Palette;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.WidgetMiniPlayerConfigureBinding;
import org.videolan.vlc.gui.helpers.BitmapUtilKt;
import org.videolan.vlc.mediadb.models.Widget;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.widget.MiniPlayerConfigureActivity$updatePreview$1$1", f = "MiniPlayerConfigureActivity.kt", i = {}, l = {135}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MiniPlayerConfigureActivity.kt */
final class MiniPlayerConfigureActivity$updatePreview$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Widget $widget;
    int label;
    final /* synthetic */ MiniPlayerConfigureActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MiniPlayerConfigureActivity$updatePreview$1$1(MiniPlayerConfigureActivity miniPlayerConfigureActivity, Widget widget, Continuation<? super MiniPlayerConfigureActivity$updatePreview$1$1> continuation) {
        super(2, continuation);
        this.this$0 = miniPlayerConfigureActivity;
        this.$widget = widget;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MiniPlayerConfigureActivity$updatePreview$1$1(this.this$0, this.$widget, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MiniPlayerConfigureActivity$updatePreview$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.widget.MiniPlayerConfigureActivity$updatePreview$1$1$1", f = "MiniPlayerConfigureActivity.kt", i = {0, 0}, l = {143, 152}, m = "invokeSuspend", n = {"width", "height"}, s = {"I$0", "I$1"})
    /* renamed from: org.videolan.vlc.widget.MiniPlayerConfigureActivity$updatePreview$1$1$1  reason: invalid class name */
    /* compiled from: MiniPlayerConfigureActivity.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int I$0;
        int I$1;
        Object L$0;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(miniPlayerConfigureActivity, widget, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            int i;
            MiniPlayerConfigureActivity miniPlayerConfigureActivity;
            int i2;
            Object obj2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                Bitmap decodeResource = BitmapFactory.decodeResource(miniPlayerConfigureActivity.getResources(), R.drawable.vlc_fake_cover);
                Palette generate = Palette.from(decodeResource).generate();
                Intrinsics.checkNotNullExpressionValue(generate, "generate(...)");
                MiniPlayerAppWidgetProvider miniPlayerAppWidgetProvider = new MiniPlayerAppWidgetProvider();
                Widget value = miniPlayerConfigureActivity.getModel$vlc_android_release().getWidget().getValue();
                if (value != null) {
                    int widgetId = value.getWidgetId();
                    Widget widget = widget;
                    MiniPlayerConfigureActivity miniPlayerConfigureActivity2 = miniPlayerConfigureActivity;
                    int width = (widget.getWidth() <= 0 || widget.getHeight() <= 0) ? MediaPlayer.Event.ESAdded : widget.getWidth();
                    i = (widget.getWidth() <= 0 || widget.getHeight() <= 0) ? 94 : widget.getHeight();
                    Context context = miniPlayerConfigureActivity2;
                    Intent intent = new Intent(MiniPlayerAppWidgetProvider.Companion.getACTION_WIDGET_INIT());
                    WidgetMiniPlayerConfigureBinding access$getBinding$p = miniPlayerConfigureActivity2.binding;
                    if (access$getBinding$p == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        access$getBinding$p = null;
                    }
                    boolean isChecked = access$getBinding$p.previewPlaying.isChecked();
                    this.L$0 = miniPlayerConfigureActivity2;
                    this.I$0 = width;
                    this.I$1 = i;
                    this.label = 1;
                    obj2 = miniPlayerAppWidgetProvider.layoutWidget(context, widgetId, intent, true, decodeResource, generate, isChecked, this);
                    if (obj2 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    miniPlayerConfigureActivity = miniPlayerConfigureActivity2;
                    i2 = width;
                }
                return Unit.INSTANCE;
            } else if (i3 == 1) {
                int i4 = this.I$1;
                i2 = this.I$0;
                miniPlayerConfigureActivity = (MiniPlayerConfigureActivity) this.L$0;
                ResultKt.throwOnFailure(obj);
                i = i4;
                obj2 = obj;
            } else if (i3 == 2) {
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            RemoteViews remoteViews = (RemoteViews) obj2;
            FrameLayout frameLayout = new FrameLayout(miniPlayerConfigureActivity);
            frameLayout.setLayoutParams(new FrameLayout.LayoutParams(i2, i));
            View apply = remoteViews != null ? remoteViews.apply(miniPlayerConfigureActivity.getApplicationContext(), frameLayout) : null;
            if (apply != null) {
                ViewGroup.LayoutParams layoutParams = apply.getLayoutParams();
                Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
                ((FrameLayout.LayoutParams) layoutParams).gravity = 17;
                frameLayout.addView(apply);
                Bitmap bitmapFromView = BitmapUtilKt.bitmapFromView(frameLayout, KotlinExtensionsKt.getDp(i2), KotlinExtensionsKt.getDp(i));
                this.L$0 = null;
                this.label = 2;
                if (BuildersKt.withContext(Dispatchers.getMain(), new MiniPlayerConfigureActivity$updatePreview$1$1$1$1$1$1(miniPlayerConfigureActivity, bitmapFromView, (Continuation<? super MiniPlayerConfigureActivity$updatePreview$1$1$1$1$1$1>) null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final MiniPlayerConfigureActivity miniPlayerConfigureActivity = this.this$0;
            final Widget widget = this.$widget;
            this.label = 1;
            if (BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass1((Continuation<? super AnonymousClass1>) null), this) == coroutine_suspended) {
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
