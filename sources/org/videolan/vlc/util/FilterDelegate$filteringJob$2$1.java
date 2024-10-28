package org.videolan.vlc.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.fusesource.jansi.AnsiRenderer;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H@"}, d2 = {"<anonymous>", "", "T", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.FilterDelegate$filteringJob$2$1", f = "FilterDelegate.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: FilterDelegate.kt */
final class FilterDelegate$filteringJob$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<T>>, Object> {
    final /* synthetic */ CharSequence $charSequence;
    final /* synthetic */ List<T> $it;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FilterDelegate$filteringJob$2$1(CharSequence charSequence, List<? extends T> list, Continuation<? super FilterDelegate$filteringJob$2$1> continuation) {
        super(2, continuation);
        this.$charSequence = charSequence;
        this.$it = list;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FilterDelegate$filteringJob$2$1(this.$charSequence, this.$it, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<T>> continuation) {
        return ((FilterDelegate$filteringJob$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            List arrayList = new ArrayList();
            CharSequence charSequence = this.$charSequence;
            List<T> list = this.$it;
            List split$default = StringsKt.split$default((CharSequence) StringsKt.trim(charSequence).toString(), new String[]{AnsiRenderer.CODE_TEXT_SEPARATOR}, false, 0, 6, (Object) null);
            for (T t : list) {
                Iterator it = split$default.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    String title = t.getTitle();
                    Intrinsics.checkNotNullExpressionValue(title, "getTitle(...)");
                    if (StringsKt.contains((CharSequence) title, (CharSequence) (String) it.next(), true)) {
                        arrayList.add(t);
                        break;
                    }
                }
            }
            return arrayList;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
