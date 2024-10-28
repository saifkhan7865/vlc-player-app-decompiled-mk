package org.videolan.television.ui;

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
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.moviepedia.MediaScraper;
import org.videolan.moviepedia.models.resolver.ResolverMedia;
import org.videolan.television.util.HelpersKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.MediaScrapingTvFragment$defaultItemClickedListener$1$1", f = "MediaScrapingTvFragment.kt", i = {}, l = {84}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaScrapingTvFragment.kt */
final class MediaScrapingTvFragment$defaultItemClickedListener$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Object $item;
    int label;
    final /* synthetic */ MediaScrapingTvFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaScrapingTvFragment$defaultItemClickedListener$1$1(MediaScrapingTvFragment mediaScrapingTvFragment, Object obj, Continuation<? super MediaScrapingTvFragment$defaultItemClickedListener$1$1> continuation) {
        super(2, continuation);
        this.this$0 = mediaScrapingTvFragment;
        this.$item = obj;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaScrapingTvFragment$defaultItemClickedListener$1$1(this.this$0, this.$item, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MediaScrapingTvFragment$defaultItemClickedListener$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.television.ui.MediaScrapingTvFragment$defaultItemClickedListener$1$1$1", f = "MediaScrapingTvFragment.kt", i = {}, l = {86}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.television.ui.MediaScrapingTvFragment$defaultItemClickedListener$1$1$1  reason: invalid class name */
    /* compiled from: MediaScrapingTvFragment.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(mediaScrapingTvFragment, obj2, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                MediaScraper mediaScraper = MediaScraper.INSTANCE;
                FragmentActivity requireActivity = mediaScrapingTvFragment.requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
                MediaWrapper media = mediaScrapingTvFragment.getMedia();
                Object obj2 = obj2;
                Intrinsics.checkNotNullExpressionValue(obj2, "$item");
                this.label = 1;
                if (MediaScraper.saveMediaMetadata$default(mediaScraper, requireActivity, media, (ResolverMedia) obj2, false, false, this, 24, (Object) null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                try {
                    ResultKt.throwOnFailure(obj);
                } catch (Exception e) {
                    FragmentActivity requireActivity2 = mediaScrapingTvFragment.requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
                    HelpersKt.manageHttpException(requireActivity2, e);
                }
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
            final MediaScrapingTvFragment mediaScrapingTvFragment = this.this$0;
            final Object obj2 = this.$item;
            this.label = 1;
            if (BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass1((Continuation<? super AnonymousClass1>) null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.this$0.requireActivity().finish();
        return Unit.INSTANCE;
    }
}
