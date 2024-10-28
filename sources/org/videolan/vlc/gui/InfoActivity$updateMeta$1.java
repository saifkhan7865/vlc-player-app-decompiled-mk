package org.videolan.vlc.gui;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.Artist;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.browser.PathAdapter;
import org.videolan.vlc.gui.view.VLCDividerItemDecoration;
import org.videolan.vlc.util.BrowserutilsKt;
import org.videolan.vlc.util.KextensionsKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.InfoActivity$updateMeta$1", f = "InfoActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: InfoActivity.kt */
final class InfoActivity$updateMeta$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ InfoActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    InfoActivity$updateMeta$1(InfoActivity infoActivity, Continuation<? super InfoActivity$updateMeta$1> continuation) {
        super(2, continuation);
        this.this$0 = infoActivity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new InfoActivity$updateMeta$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((InfoActivity$updateMeta$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        long j;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            MediaLibraryItem access$getItem$p = this.this$0.item;
            MediaLibraryItem mediaLibraryItem = null;
            if (access$getItem$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("item");
                access$getItem$p = null;
            }
            MediaWrapper[] tracks = access$getItem$p.getTracks();
            int i = 0;
            int length = tracks != null ? tracks.length : 0;
            if (length > 0) {
                Intrinsics.checkNotNull(tracks);
                j = 0;
                for (MediaWrapper length2 : tracks) {
                    j += length2.getLength();
                }
            } else {
                j = 0;
            }
            if (j > 0) {
                this.this$0.getBinding$vlc_android_release().setLength(Boxing.boxLong(j));
            }
            MediaLibraryItem access$getItem$p2 = this.this$0.item;
            if (access$getItem$p2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("item");
                access$getItem$p2 = null;
            }
            if (access$getItem$p2 instanceof MediaWrapper) {
                MediaLibraryItem access$getItem$p3 = this.this$0.item;
                if (access$getItem$p3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("item");
                    access$getItem$p3 = null;
                }
                MediaWrapper mediaWrapper = (MediaWrapper) access$getItem$p3;
                this.this$0.getBinding$vlc_android_release().setResolution(KextensionsKt.generateResolutionClass(mediaWrapper.getWidth(), mediaWrapper.getHeight()));
            }
            this.this$0.getBinding$vlc_android_release().setScanned(true);
            MediaLibraryItem access$getItem$p4 = this.this$0.item;
            if (access$getItem$p4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("item");
                access$getItem$p4 = null;
            }
            if (access$getItem$p4.getItemType() == 32) {
                MediaLibraryItem access$getItem$p5 = this.this$0.item;
                if (access$getItem$p5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("item");
                    access$getItem$p5 = null;
                }
                MediaWrapper mediaWrapper2 = (MediaWrapper) access$getItem$p5;
                this.this$0.getBinding$vlc_android_release().setProgress(mediaWrapper2.getLength() == 0 ? 0 : (int) ((mediaWrapper2.getTime() * 100) / j));
                this.this$0.getBinding$vlc_android_release().setSizeTitleText(this.this$0.getString(R.string.file_size));
                Uri uri = mediaWrapper2.getUri();
                if (BrowserutilsKt.isSchemeSupported(uri != null ? uri.getScheme() : null)) {
                    this.this$0.getBinding$vlc_android_release().ariane.setVisibility(0);
                    this.this$0.getBinding$vlc_android_release().ariane.setLayoutManager(new LinearLayoutManager(this.this$0, 0, false));
                    this.this$0.getBinding$vlc_android_release().ariane.setAdapter(new PathAdapter(this.this$0, mediaWrapper2));
                    if (this.this$0.getBinding$vlc_android_release().ariane.getItemDecorationCount() == 0) {
                        RecyclerView recyclerView = this.this$0.getBinding$vlc_android_release().ariane;
                        InfoActivity infoActivity = this.this$0;
                        Drawable drawable = ContextCompat.getDrawable(infoActivity, R.drawable.ic_divider);
                        Intrinsics.checkNotNull(drawable);
                        recyclerView.addItemDecoration(new VLCDividerItemDecoration(infoActivity, 0, drawable));
                    }
                    Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                    String[] foldersList = Medialibrary.getInstance().getFoldersList();
                    Intrinsics.checkNotNullExpressionValue(foldersList, "getFoldersList(...)");
                    for (Object obj2 : (Object[]) foldersList) {
                        String str = (String) obj2;
                        String uri2 = mediaWrapper2.getUri().toString();
                        Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
                        Intrinsics.checkNotNull(str);
                        String uri3 = Uri.parse(str).toString();
                        Intrinsics.checkNotNullExpressionValue(uri3, "toString(...)");
                        if (StringsKt.startsWith$default(uri2, uri3, false, 2, (Object) null)) {
                            booleanRef.element = true;
                        }
                    }
                    this.this$0.getBinding$vlc_android_release().setScanned(booleanRef.element);
                } else {
                    this.this$0.getBinding$vlc_android_release().ariane.setVisibility(8);
                }
            } else {
                MediaLibraryItem access$getItem$p6 = this.this$0.item;
                if (access$getItem$p6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("item");
                    access$getItem$p6 = null;
                }
                if (access$getItem$p6.getItemType() == 4) {
                    MediaLibraryItem access$getItem$p7 = this.this$0.item;
                    if (access$getItem$p7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("item");
                    } else {
                        mediaLibraryItem = access$getItem$p7;
                    }
                    Album[] albums = ((Artist) mediaLibraryItem).getAlbums();
                    if (albums != null) {
                        i = albums.length;
                    }
                    this.this$0.getBinding$vlc_android_release().setSizeTitleText(this.this$0.getString(R.string.albums));
                    this.this$0.getBinding$vlc_android_release().setSizeValueText(String.valueOf(i));
                    this.this$0.getBinding$vlc_android_release().sizeIcon.setImageDrawable(ContextCompat.getDrawable(this.this$0, R.drawable.ic_album));
                    this.this$0.getBinding$vlc_android_release().setExtraTitleText(this.this$0.getString(R.string.tracks));
                    this.this$0.getBinding$vlc_android_release().setExtraValueText(String.valueOf(length));
                    this.this$0.getBinding$vlc_android_release().extraIcon.setImageDrawable(ContextCompat.getDrawable(this.this$0, R.drawable.ic_song));
                } else {
                    this.this$0.getBinding$vlc_android_release().setSizeTitleText(this.this$0.getString(R.string.tracks));
                    this.this$0.getBinding$vlc_android_release().setSizeValueText(String.valueOf(length));
                    this.this$0.getBinding$vlc_android_release().sizeIcon.setImageDrawable(ContextCompat.getDrawable(this.this$0, R.drawable.ic_song));
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
