package org.videolan.television.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import androidx.leanback.app.BackgroundManager;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.TvUtilKt$updateBackground$2", f = "TvUtil.kt", i = {0}, l = {316}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
/* compiled from: TvUtil.kt */
final class TvUtilKt$updateBackground$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Activity $activity;
    final /* synthetic */ BackgroundManager $bm;
    final /* synthetic */ Object $item;
    final /* synthetic */ float $screenRatio;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TvUtilKt$updateBackground$2(Object obj, float f, BackgroundManager backgroundManager, Activity activity, Continuation<? super TvUtilKt$updateBackground$2> continuation) {
        super(2, continuation);
        this.$item = obj;
        this.$screenRatio = f;
        this.$bm = backgroundManager;
        this.$activity = activity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TvUtilKt$updateBackground$2 tvUtilKt$updateBackground$2 = new TvUtilKt$updateBackground$2(this.$item, this.$screenRatio, this.$bm, this.$activity, continuation);
        tvUtilKt$updateBackground$2.L$0 = obj;
        return tvUtilKt$updateBackground$2;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TvUtilKt$updateBackground$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
            Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new TvUtilKt$updateBackground$2$blurred$1(this.$item, this.$screenRatio, (Continuation<? super TvUtilKt$updateBackground$2$blurred$1>) null), this);
            if (withContext == coroutine_suspended) {
                return coroutine_suspended;
            }
            coroutineScope = coroutineScope2;
            obj = withContext;
        } else if (i == 1) {
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Bitmap bitmap = (Bitmap) obj;
        if (!CoroutineScopeKt.isActive(coroutineScope)) {
            return Unit.INSTANCE;
        }
        if (bitmap != null) {
            this.$bm.setDrawable(new BitmapDrawable(this.$activity.getResources(), bitmap));
        }
        return Unit.INSTANCE;
    }
}
