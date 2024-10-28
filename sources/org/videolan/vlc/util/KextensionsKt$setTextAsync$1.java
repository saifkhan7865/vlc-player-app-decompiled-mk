package org.videolan.vlc.util;

import android.text.Spannable;
import android.text.SpannableString;
import android.widget.TextView;
import androidx.core.text.PrecomputedTextCompat;
import java.lang.ref.WeakReference;
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

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.KextensionsKt$setTextAsync$1", f = "Kextensions.kt", i = {}, l = {241}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: Kextensions.kt */
final class KextensionsKt$setTextAsync$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ PrecomputedTextCompat.Params $params;
    final /* synthetic */ WeakReference<TextView> $ref;
    final /* synthetic */ CharSequence $text;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    KextensionsKt$setTextAsync$1(CharSequence charSequence, PrecomputedTextCompat.Params params, WeakReference<TextView> weakReference, Continuation<? super KextensionsKt$setTextAsync$1> continuation) {
        super(2, continuation);
        this.$text = charSequence;
        this.$params = params;
        this.$ref = weakReference;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new KextensionsKt$setTextAsync$1(this.$text, this.$params, this.$ref, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((KextensionsKt$setTextAsync$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PrecomputedTextCompat create = PrecomputedTextCompat.create(this.$text, this.$params);
            Intrinsics.checkNotNull(create);
            final Spannable valueOf = SpannableString.valueOf(create);
            final WeakReference<TextView> weakReference = this.$ref;
            this.label = 1;
            if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass1((Continuation<? super AnonymousClass1>) null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.util.KextensionsKt$setTextAsync$1$1", f = "Kextensions.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.util.KextensionsKt$setTextAsync$1$1  reason: invalid class name */
    /* compiled from: Kextensions.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(weakReference, valueOf, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                TextView textView = (TextView) weakReference.get();
                if (textView != null) {
                    textView.setText(valueOf);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
