package org.videolan.vlc.gui.preferences.widgets;

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
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.vlc.mediadb.models.Widget;
import org.videolan.vlc.repository.WidgetRepository;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.preferences.widgets.PreferencesWidgets$updateWidgetEntity$1$1", f = "PreferencesWidgets.kt", i = {}, l = {248}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PreferencesWidgets.kt */
final class PreferencesWidgets$updateWidgetEntity$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Widget $widget;
    int label;
    final /* synthetic */ PreferencesWidgets this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreferencesWidgets$updateWidgetEntity$1$1(PreferencesWidgets preferencesWidgets, Widget widget, Continuation<? super PreferencesWidgets$updateWidgetEntity$1$1> continuation) {
        super(2, continuation);
        this.this$0 = preferencesWidgets;
        this.$widget = widget;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PreferencesWidgets$updateWidgetEntity$1$1(this.this$0, this.$widget, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PreferencesWidgets$updateWidgetEntity$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.gui.preferences.widgets.PreferencesWidgets$updateWidgetEntity$1$1$1", f = "PreferencesWidgets.kt", i = {}, l = {248}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.gui.preferences.widgets.PreferencesWidgets$updateWidgetEntity$1$1$1  reason: invalid class name */
    /* compiled from: PreferencesWidgets.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(preferencesWidgets, widget, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                WidgetRepository.Companion companion = WidgetRepository.Companion;
                FragmentActivity requireActivity = preferencesWidgets.requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
                Widget widget = widget;
                Intrinsics.checkNotNullExpressionValue(widget, "$widget");
                this.label = 1;
                if (WidgetRepository.updateWidget$default((WidgetRepository) companion.getInstance(requireActivity), widget, false, this, 2, (Object) null) == coroutine_suspended) {
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

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final PreferencesWidgets preferencesWidgets = this.this$0;
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
