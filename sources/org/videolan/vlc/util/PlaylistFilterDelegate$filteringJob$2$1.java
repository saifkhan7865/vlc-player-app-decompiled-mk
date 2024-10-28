package org.videolan.vlc.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.fusesource.jansi.AnsiRenderer;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.resources.AppContextProvider;
import org.videolan.vlc.media.MediaUtils;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.PlaylistFilterDelegate$filteringJob$2$1", f = "FilterDelegate.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: FilterDelegate.kt */
final class PlaylistFilterDelegate$filteringJob$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<MediaWrapper>>, Object> {
    final /* synthetic */ CharSequence $charSequence;
    final /* synthetic */ List<MediaWrapper> $list;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistFilterDelegate$filteringJob$2$1(CharSequence charSequence, List<? extends MediaWrapper> list, Continuation<? super PlaylistFilterDelegate$filteringJob$2$1> continuation) {
        super(2, continuation);
        this.$charSequence = charSequence;
        this.$list = list;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlaylistFilterDelegate$filteringJob$2$1(this.$charSequence, this.$list, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<MediaWrapper>> continuation) {
        return ((PlaylistFilterDelegate$filteringJob$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            List arrayList = new ArrayList();
            CharSequence charSequence = this.$charSequence;
            List<MediaWrapper> list = this.$list;
            List list2 = SequencesKt.toList(SequencesKt.map(SequencesKt.filter(CollectionsKt.asSequence(StringsKt.split$default((CharSequence) StringsKt.trim(charSequence).toString(), new String[]{AnsiRenderer.CODE_TEXT_SEPARATOR}, false, 0, 6, (Object) null)), PlaylistFilterDelegate$filteringJob$2$1$1$queryStrings$1.INSTANCE), PlaylistFilterDelegate$filteringJob$2$1$1$queryStrings$2.INSTANCE));
            for (MediaWrapper next : list) {
                String mediaTitle = MediaUtils.INSTANCE.getMediaTitle(next);
                Locale locale = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
                String lowerCase = mediaTitle.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                String location = next.getLocation();
                Intrinsics.checkNotNullExpressionValue(location, "getLocation(...)");
                Locale locale2 = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale2, "getDefault(...)");
                String lowerCase2 = location.toLowerCase(locale2);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                String mediaArtist = MediaUtils.INSTANCE.getMediaArtist(AppContextProvider.INSTANCE.getAppContext(), next);
                Locale locale3 = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale3, "getDefault(...)");
                String lowerCase3 = mediaArtist.toLowerCase(locale3);
                Intrinsics.checkNotNullExpressionValue(lowerCase3, "toLowerCase(...)");
                String mediaAlbumArtist = MediaUtils.INSTANCE.getMediaAlbumArtist(AppContextProvider.INSTANCE.getAppContext(), next);
                Locale locale4 = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale4, "getDefault(...)");
                String lowerCase4 = mediaAlbumArtist.toLowerCase(locale4);
                Intrinsics.checkNotNullExpressionValue(lowerCase4, "toLowerCase(...)");
                String mediaAlbum = MediaUtils.INSTANCE.getMediaAlbum(AppContextProvider.INSTANCE.getAppContext(), next);
                Locale locale5 = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale5, "getDefault(...)");
                String lowerCase5 = mediaAlbum.toLowerCase(locale5);
                Intrinsics.checkNotNullExpressionValue(lowerCase5, "toLowerCase(...)");
                String mediaGenre = MediaUtils.INSTANCE.getMediaGenre(AppContextProvider.INSTANCE.getAppContext(), next);
                Locale locale6 = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale6, "getDefault(...)");
                String lowerCase6 = mediaGenre.toLowerCase(locale6);
                Intrinsics.checkNotNullExpressionValue(lowerCase6, "toLowerCase(...)");
                Iterator it = list2.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    CharSequence charSequence2 = (String) it.next();
                    if (StringsKt.contains$default((CharSequence) lowerCase, charSequence2, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) lowerCase2, charSequence2, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) lowerCase3, charSequence2, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) lowerCase4, charSequence2, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) lowerCase5, charSequence2, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) lowerCase6, charSequence2, false, 2, (Object) null)) {
                        arrayList.add(next);
                    }
                }
            }
            return arrayList;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
