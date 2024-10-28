package org.videolan.vlc.media;

import android.content.SharedPreferences;
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
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.resources.Constants;
import org.videolan.tools.SettingsKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.PlaylistManager$saveMediaList$2", f = "PlaylistManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PlaylistManager.kt */
final class PlaylistManager$saveMediaList$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $isAudio;
    final /* synthetic */ StringBuilder $locations;
    int label;
    final /* synthetic */ PlaylistManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistManager$saveMediaList$2(StringBuilder sb, boolean z, PlaylistManager playlistManager, Continuation<? super PlaylistManager$saveMediaList$2> continuation) {
        super(2, continuation);
        this.$locations = sb;
        this.$isAudio = z;
        this.this$0 = playlistManager;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlaylistManager$saveMediaList$2(this.$locations, this.$isAudio, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlaylistManager$saveMediaList$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            List<MediaWrapper> copy = PlaylistManager.mediaList.getCopy();
            if (!(!copy.isEmpty())) {
                copy = null;
            }
            if (copy == null) {
                return Unit.INSTANCE;
            }
            for (MediaWrapper uri : copy) {
                StringBuilder sb = this.$locations;
                sb.append(AnsiRenderer.CODE_TEXT_SEPARATOR);
                sb.append(uri.getUri().toString());
            }
            if (this.$isAudio) {
                if (this.this$0.getSettings().getBoolean(SettingsKt.AUDIO_RESUME_PLAYBACK, true)) {
                    SharedPreferences access$getSettings = this.this$0.getSettings();
                    String sb2 = this.$locations.toString();
                    Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
                    SettingsKt.putSingle(access$getSettings, Constants.KEY_MEDIA_LAST_PLAYLIST_RESUME, StringsKt.trim((CharSequence) sb2).toString());
                    SharedPreferences access$getSettings2 = this.this$0.getSettings();
                    String sb3 = this.$locations.toString();
                    Intrinsics.checkNotNullExpressionValue(sb3, "toString(...)");
                    SettingsKt.putSingle(access$getSettings2, Constants.KEY_AUDIO_LAST_PLAYLIST, StringsKt.trim((CharSequence) sb3).toString());
                }
            } else if (this.this$0.getSettings().getBoolean(SettingsKt.VIDEO_RESUME_PLAYBACK, true)) {
                SharedPreferences access$getSettings3 = this.this$0.getSettings();
                String sb4 = this.$locations.toString();
                Intrinsics.checkNotNullExpressionValue(sb4, "toString(...)");
                SettingsKt.putSingle(access$getSettings3, Constants.KEY_MEDIA_LAST_PLAYLIST_RESUME, StringsKt.trim((CharSequence) sb4).toString());
                SharedPreferences access$getSettings4 = this.this$0.getSettings();
                String sb5 = this.$locations.toString();
                Intrinsics.checkNotNullExpressionValue(sb5, "toString(...)");
                SettingsKt.putSingle(access$getSettings4, "media_list", StringsKt.trim((CharSequence) sb5).toString());
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
