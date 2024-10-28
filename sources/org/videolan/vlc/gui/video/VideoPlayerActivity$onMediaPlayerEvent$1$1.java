package org.videolan.vlc.gui.video;

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
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.resources.Constants;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.gui.dialogs.adapters.VlcTrack;
import org.videolan.vlc.util.LocaleUtil;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.video.VideoPlayerActivity$onMediaPlayerEvent$1$1", f = "VideoPlayerActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: VideoPlayerActivity.kt */
final class VideoPlayerActivity$onMediaPlayerEvent$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaWrapper $mw;
    final /* synthetic */ PlaybackService $service;
    int label;
    final /* synthetic */ VideoPlayerActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoPlayerActivity$onMediaPlayerEvent$1$1(VideoPlayerActivity videoPlayerActivity, MediaWrapper mediaWrapper, PlaybackService playbackService, Continuation<? super VideoPlayerActivity$onMediaPlayerEvent$1$1> continuation) {
        super(2, continuation);
        this.this$0 = videoPlayerActivity;
        this.$mw = mediaWrapper;
        this.$service = playbackService;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideoPlayerActivity$onMediaPlayerEvent$1$1(this.this$0, this.$mw, this.$service, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoPlayerActivity$onMediaPlayerEvent$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Iterator it;
        String str;
        Object obj2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            MediaWrapper findMedia = this.this$0.getMedialibrary().findMedia(this.$mw);
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = Constants.GROUP_VIDEOS_FOLDER;
            String string = this.this$0.getSettings().getString(SettingsKt.AUDIO_PREFERRED_LANGUAGE, "");
            CharSequence charSequence = string;
            if (charSequence != null && charSequence.length() != 0) {
                List access$getCurrentMediaTracks = this.this$0.getCurrentMediaTracks();
                VlcTrack[] audioTracks = this.$service.getAudioTracks();
                if (audioTracks != null && (it = ArrayIteratorKt.iterator(audioTracks)) != null) {
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        VlcTrack vlcTrack = (VlcTrack) it.next();
                        Iterator it2 = access$getCurrentMediaTracks.iterator();
                        while (true) {
                            str = null;
                            if (!it2.hasNext()) {
                                obj2 = null;
                                break;
                            }
                            obj2 = it2.next();
                            if (Intrinsics.areEqual((Object) String.valueOf(((IMedia.Track) obj2).id), (Object) vlcTrack.getId())) {
                                break;
                            }
                        }
                        IMedia.Track track = (IMedia.Track) obj2;
                        LocaleUtil localeUtil = LocaleUtil.INSTANCE;
                        if (track != null) {
                            str = track.language;
                        }
                        if (str == null) {
                            str = "";
                        }
                        if (Intrinsics.areEqual((Object) localeUtil.getLocaleFromVLC(str), (Object) string)) {
                            objectRef.element = vlcTrack.getId();
                            break;
                        }
                    }
                }
            }
            String metaString = findMedia.getMetaString(150);
            if (metaString == null) {
                metaString = Constants.GROUP_VIDEOS_FOLDER;
            }
            if (Intrinsics.areEqual((Object) metaString, (Object) Constants.GROUP_VIDEOS_FOLDER)) {
                metaString = (String) objectRef.element;
            }
            if (!Intrinsics.areEqual((Object) metaString, (Object) Constants.GROUP_VIDEOS_FOLDER) || !Intrinsics.areEqual((Object) this.this$0.currentAudioTrack, (Object) "-2")) {
                this.$service.setAudioTrack(metaString.toString());
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
