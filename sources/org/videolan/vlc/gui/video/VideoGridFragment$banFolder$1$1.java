package org.videolan.vlc.gui.video;

import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
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
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.helpers.MedialibraryUtils;
import org.videolan.vlc.gui.helpers.UiTools;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.video.VideoGridFragment$banFolder$1$1", f = "VideoGridFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: VideoGridFragment.kt */
final class VideoGridFragment$banFolder$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $path;
    int label;
    final /* synthetic */ VideoGridFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoGridFragment$banFolder$1$1(String str, VideoGridFragment videoGridFragment, Continuation<? super VideoGridFragment$banFolder$1$1> continuation) {
        super(2, continuation);
        this.$path = str;
        this.this$0 = videoGridFragment;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideoGridFragment$banFolder$1$1(this.$path, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoGridFragment$banFolder$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            String[] foldersList = Medialibrary.getInstance().getFoldersList();
            Intrinsics.checkNotNullExpressionValue(foldersList, "getFoldersList(...)");
            String str = this.$path;
            Intrinsics.checkNotNullExpressionValue(str, "$path");
            String removePrefix = StringsKt.removePrefix(str, (CharSequence) "file://");
            for (String str2 : foldersList) {
                if (Intrinsics.areEqual((Object) StringsKt.removePrefix(str2, (CharSequence) "file://"), (Object) removePrefix)) {
                    Log.w("VLC/VideoListFragment", "banFolder: trying to ban root: " + str2);
                    final VideoGridFragment videoGridFragment = this.this$0;
                    Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getMain(), (CoroutineStart) null, new AnonymousClass1((Continuation<? super AnonymousClass1>) null), 2, (Object) null);
                    return Unit.INSTANCE;
                }
            }
            MedialibraryUtils.INSTANCE.banDir(removePrefix);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.gui.video.VideoGridFragment$banFolder$1$1$1", f = "VideoGridFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.gui.video.VideoGridFragment$banFolder$1$1$1  reason: invalid class name */
    /* compiled from: VideoGridFragment.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(videoGridFragment, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                UiTools uiTools = UiTools.INSTANCE;
                FragmentActivity requireActivity = videoGridFragment.requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
                String string = videoGridFragment.getString(R.string.cant_ban_root);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                uiTools.snacker(requireActivity, string);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
