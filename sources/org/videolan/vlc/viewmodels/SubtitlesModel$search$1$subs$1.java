package org.videolan.vlc.viewmodels;

import java.io.File;
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
import kotlinx.coroutines.CoroutineScope;
import org.videolan.resources.opensubtitles.OpenSubtitle;
import org.videolan.tools.FileUtils;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Lorg/videolan/resources/opensubtitles/OpenSubtitle;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.viewmodels.SubtitlesModel$search$1$subs$1", f = "SubtitlesModel.kt", i = {0}, l = {154, 156, 158}, m = "invokeSuspend", n = {"videoFile"}, s = {"L$0"})
/* compiled from: SubtitlesModel.kt */
final class SubtitlesModel$search$1$subs$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends OpenSubtitle>>, Object> {
    Object L$0;
    int label;
    final /* synthetic */ SubtitlesModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SubtitlesModel$search$1$subs$1(SubtitlesModel subtitlesModel, Continuation<? super SubtitlesModel$search$1$subs$1> continuation) {
        super(2, continuation);
        this.this$0 = subtitlesModel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SubtitlesModel$search$1$subs$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<OpenSubtitle>> continuation) {
        return ((SubtitlesModel$search$1$subs$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        File file;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            file = new File(this.this$0.mediaUri.getPath());
            if (file.exists()) {
                String computeHash = FileUtils.INSTANCE.computeHash(file);
                long length = file.length();
                SubtitlesModel subtitlesModel = this.this$0;
                this.L$0 = file;
                this.label = 1;
                obj = subtitlesModel.getSubtitleByHash(length, computeHash, subtitlesModel.getObservableSearchLanguage().get(), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                SubtitlesModel subtitlesModel2 = this.this$0;
                String access$getName$p = subtitlesModel2.name;
                this.label = 3;
                obj = subtitlesModel2.getSubtitleByName(access$getName$p, (Integer) null, (Integer) null, this.this$0.getObservableSearchLanguage().get(), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return (List) obj;
            }
        } else if (i == 1) {
            file = (File) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            ResultKt.throwOnFailure(obj);
            return (List) obj;
        } else if (i == 3) {
            ResultKt.throwOnFailure(obj);
            return (List) obj;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        List list = (List) obj;
        if (!list.isEmpty()) {
            return list;
        }
        SubtitlesModel subtitlesModel3 = this.this$0;
        String name = file.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        this.L$0 = null;
        this.label = 2;
        obj = subtitlesModel3.getSubtitleByName(name, (Integer) null, (Integer) null, this.this$0.getObservableSearchLanguage().get(), this);
        if (obj == coroutine_suspended) {
            return coroutine_suspended;
        }
        return (List) obj;
    }
}
