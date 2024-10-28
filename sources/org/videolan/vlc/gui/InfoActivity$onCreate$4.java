package org.videolan.vlc.gui;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.LifecycleOwnerKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/graphics/Bitmap;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: InfoActivity.kt */
final class InfoActivity$onCreate$4 extends Lambda implements Function1<Bitmap, Unit> {
    final /* synthetic */ int $fabVisibility;
    final /* synthetic */ InfoActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    InfoActivity$onCreate$4(InfoActivity infoActivity, int i) {
        super(1);
        this.this$0 = infoActivity;
        this.$fabVisibility = i;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Bitmap) obj);
        return Unit.INSTANCE;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.gui.InfoActivity$onCreate$4$1", f = "InfoActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.gui.InfoActivity$onCreate$4$1  reason: invalid class name */
    /* compiled from: InfoActivity.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(infoActivity, i, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ViewCompat.setNestedScrollingEnabled(infoActivity.getBinding$vlc_android_release().container, true);
                infoActivity.getBinding$vlc_android_release().appbar.setExpanded(true, true);
                if (i != -1) {
                    infoActivity.getBinding$vlc_android_release().fab.setVisibility(i);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final void invoke(Bitmap bitmap) {
        if (bitmap != null) {
            this.this$0.getBinding$vlc_android_release().setCover(new BitmapDrawable(this.this$0.getResources(), bitmap));
            final InfoActivity infoActivity = this.this$0;
            final int i = this.$fabVisibility;
            Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.this$0), (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1((Continuation<? super AnonymousClass1>) null), 3, (Object) null);
            return;
        }
        this.this$0.noCoverFallback();
    }
}
