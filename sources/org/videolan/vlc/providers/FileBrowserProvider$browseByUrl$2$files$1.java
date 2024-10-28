package org.videolan.vlc.providers;

import android.content.Context;
import android.net.Uri;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.gui.helpers.hf.OtgAccessKt;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.providers.FileBrowserProvider$browseByUrl$2$files$1", f = "FileBrowserProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: FileBrowserProvider.kt */
final class FileBrowserProvider$browseByUrl$2$files$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<MediaLibraryItem>>, Object> {
    final /* synthetic */ String $url;
    int label;
    final /* synthetic */ FileBrowserProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FileBrowserProvider$browseByUrl$2$files$1(FileBrowserProvider fileBrowserProvider, String str, Continuation<? super FileBrowserProvider$browseByUrl$2$files$1> continuation) {
        super(2, continuation);
        this.this$0 = fileBrowserProvider;
        this.$url = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FileBrowserProvider$browseByUrl$2$files$1(this.this$0, this.$url, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<MediaLibraryItem>> continuation) {
        return ((FileBrowserProvider$browseByUrl$2$files$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        String str;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Context context = this.this$0.getContext();
            String path = Uri.parse(this.$url).getPath();
            List<MediaWrapper> list = null;
            if (path == null || (str = StringsKt.substringAfterLast$default(path, (char) AbstractJsonLexerKt.COLON, (String) null, 2, (Object) null)) == null) {
                str = "";
            }
            List<MediaWrapper> documentFiles = OtgAccessKt.getDocumentFiles(context, str);
            if (TypeIntrinsics.isMutableList(documentFiles)) {
                list = documentFiles;
            }
            return list == null ? new ArrayList() : list;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
