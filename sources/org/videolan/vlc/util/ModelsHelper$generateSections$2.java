package org.videolan.vlc.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.media.DummyItem;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.ModelsHelper$generateSections$2", f = "ModelsHelper.kt", i = {}, l = {23}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ModelsHelper.kt */
final class ModelsHelper$generateSections$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<MediaLibraryItem>>, Object> {
    final /* synthetic */ List<MediaLibraryItem> $items;
    final /* synthetic */ int $sort;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ModelsHelper$generateSections$2(int i, List<? extends MediaLibraryItem> list, Continuation<? super ModelsHelper$generateSections$2> continuation) {
        super(2, continuation);
        this.$sort = i;
        this.$items = list;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ModelsHelper$generateSections$2(this.$sort, this.$items, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<MediaLibraryItem>> continuation) {
        return ((ModelsHelper$generateSections$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = ModelsHelper.INSTANCE.splitList$vlc_android_release(this.$sort, this.$items, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        List arrayList = new ArrayList();
        for (Map.Entry entry : ((Map) obj).entrySet()) {
            arrayList.add(new DummyItem((String) entry.getKey()));
            arrayList.addAll((List) entry.getValue());
        }
        return arrayList;
    }
}
