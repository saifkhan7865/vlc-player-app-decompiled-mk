package org.videolan.vlc.gui.audio;

import androidx.fragment.app.FragmentActivity;
import java.text.DateFormat;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.Tools;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.AudioPlayerBinding;
import org.videolan.vlc.gui.helpers.TalkbackUtil;
import org.videolan.vlc.media.PlaylistManager;
import org.videolan.vlc.viewmodels.PlaybackProgress;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.audio.AudioPlayer$updateProgress$1$text$1", f = "AudioPlayer.kt", i = {0}, l = {507}, m = "invokeSuspend", n = {"medias"}, s = {"L$0"})
/* compiled from: AudioPlayer.kt */
final class AudioPlayer$updateProgress$1$text$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Pair<? extends String, ? extends String>>, Object> {
    final /* synthetic */ PlaybackProgress $progress;
    Object L$0;
    int label;
    final /* synthetic */ AudioPlayer this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioPlayer$updateProgress$1$text$1(AudioPlayer audioPlayer, PlaybackProgress playbackProgress, Continuation<? super AudioPlayer$updateProgress$1$text$1> continuation) {
        super(2, continuation);
        this.this$0 = audioPlayer;
        this.$progress = playbackProgress;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AudioPlayer$updateProgress$1$text$1(this.this$0, this.$progress, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Pair<String, String>> continuation) {
        return ((AudioPlayer$updateProgress$1$text$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        List<MediaWrapper> list;
        int i;
        String str;
        String str2;
        int i2;
        char c;
        PlaylistManager playlistManager;
        PlaylistManager playlistManager2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            List<MediaWrapper> medias = this.this$0.getPlaylistModel().getMedias();
            if (medias == null) {
                return new Pair("", "");
            }
            final AudioPlayer audioPlayer = this.this$0;
            this.L$0 = medias;
            this.label = 1;
            if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass1((Continuation<? super AnonymousClass1>) null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            list = medias;
        } else if (i3 == 1) {
            list = (List) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (this.this$0.getPlaylistModel().getCurrentMediaPosition() == -1) {
            return new Pair("", "");
        }
        Long previousTotalTime = this.this$0.getPlaylistModel().getPreviousTotalTime();
        if (previousTotalTime == null) {
            return new Pair("", "");
        }
        long longValue = previousTotalTime.longValue() + this.$progress.getTime();
        long totalTime = this.this$0.getPlaylistModel().getTotalTime();
        String millisToString = Tools.millisToString((!this.this$0.showRemainingTime || totalTime <= 0) ? longValue : totalTime - longValue, false, true, false);
        String millisToString2 = Tools.millisToString(totalTime, false, false, false);
        TalkbackUtil talkbackUtil = TalkbackUtil.INSTANCE;
        FragmentActivity requireActivity = this.this$0.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        String millisToString3 = talkbackUtil.millisToString(requireActivity, totalTime);
        TalkbackUtil talkbackUtil2 = TalkbackUtil.INSTANCE;
        FragmentActivity requireActivity2 = this.this$0.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
        String millisToString4 = talkbackUtil2.millisToString(requireActivity2, (!this.this$0.showRemainingTime || totalTime <= 0) ? longValue : totalTime - longValue);
        CharSequence charSequence = millisToString;
        if (charSequence == null || charSequence.length() == 0) {
            millisToString = "0:00";
        } else {
            Intrinsics.checkNotNull(millisToString);
        }
        PlaybackService service = this.this$0.getPlaylistModel().getService();
        if (service == null || (playlistManager2 = service.getPlaylistManager()) == null || playlistManager2.getStopAfter() != -1) {
            PlaybackService service2 = this.this$0.getPlaylistModel().getService();
            i = ((service2 == null || (playlistManager = service2.getPlaylistManager()) == null) ? 0 : playlistManager.getStopAfter()) + 1;
        } else {
            i = list.size();
        }
        String string = this.this$0.getString(R.string.track_index, (this.this$0.getPlaylistModel().getCurrentMediaPosition() + 1) + " / " + i);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String str3 = string;
        String str4 = millisToString3;
        String string2 = this.this$0.getString(R.string.talkback_track_index, String.valueOf(this.this$0.getPlaylistModel().getCurrentMediaPosition() + 1), String.valueOf(i));
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        if (this.this$0.audioPlayProgressMode) {
            long currentTimeMillis = (System.currentTimeMillis() + totalTime) - longValue;
            if (Math.abs(this.this$0.lastEndsAt - currentTimeMillis) > 1) {
                this.this$0.lastEndsAt = currentTimeMillis;
            }
            str = this.this$0.getString(R.string.audio_queue_progress_finished, DateFormat.getTimeInstance(2).format(Boxing.boxLong(this.this$0.lastEndsAt)));
        } else if (!this.this$0.showRemainingTime || totalTime <= 0) {
            AudioPlayer audioPlayer2 = this.this$0;
            int i4 = R.string.audio_queue_progress;
            CharSequence charSequence2 = millisToString2;
            if (!(charSequence2 == null || charSequence2.length() == 0)) {
                millisToString = millisToString + " / " + millisToString2;
            }
            str = audioPlayer2.getString(i4, millisToString);
        } else {
            str = this.this$0.getString(R.string.audio_queue_progress_remaining, millisToString);
        }
        Intrinsics.checkNotNull(str);
        if (this.this$0.audioPlayProgressMode) {
            long currentTimeMillis2 = (System.currentTimeMillis() + totalTime) - longValue;
            if (Math.abs(this.this$0.lastEndsAt - currentTimeMillis2) > 1) {
                this.this$0.lastEndsAt = currentTimeMillis2;
            }
            str2 = this.this$0.getString(R.string.audio_queue_progress_finished, DateFormat.getTimeInstance(2).format(Boxing.boxLong(this.this$0.lastEndsAt)));
        } else if (!this.this$0.showRemainingTime || totalTime <= 0) {
            AudioPlayer audioPlayer3 = this.this$0;
            int i5 = R.string.audio_queue_progress;
            CharSequence charSequence3 = millisToString2;
            if (charSequence3 == null || charSequence3.length() == 0) {
                c = 0;
                i2 = 1;
            } else {
                c = 0;
                i2 = 1;
                millisToString4 = this.this$0.getString(R.string.talkback_out_of, millisToString4, str4);
                Intrinsics.checkNotNullExpressionValue(millisToString4, "getString(...)");
            }
            Object[] objArr = new Object[i2];
            objArr[c] = millisToString4;
            str2 = audioPlayer3.getString(i5, objArr);
        } else {
            str2 = this.this$0.getString(R.string.audio_queue_progress_remaining, millisToString4);
        }
        Intrinsics.checkNotNull(str2);
        return new Pair(str3 + "  ·  " + str, string2 + ". " + str2);
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.gui.audio.AudioPlayer$updateProgress$1$text$1$1", f = "AudioPlayer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.gui.audio.AudioPlayer$updateProgress$1$text$1$1  reason: invalid class name */
    /* compiled from: AudioPlayer.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(audioPlayer, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                AudioPlayerBinding audioPlayerBinding = null;
                if (!audioPlayer.shouldHidePlayProgress()) {
                    AudioPlayerBinding access$getBinding$p = audioPlayer.binding;
                    if (access$getBinding$p == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        audioPlayerBinding = access$getBinding$p;
                    }
                    KotlinExtensionsKt.setVisible(audioPlayerBinding.audioPlayProgress);
                } else {
                    AudioPlayerBinding access$getBinding$p2 = audioPlayer.binding;
                    if (access$getBinding$p2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        audioPlayerBinding = access$getBinding$p2;
                    }
                    KotlinExtensionsKt.setGone(audioPlayerBinding.audioPlayProgress);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
