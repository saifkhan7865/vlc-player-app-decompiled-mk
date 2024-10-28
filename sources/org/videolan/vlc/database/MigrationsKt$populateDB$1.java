package org.videolan.vlc.database;

import android.content.Context;
import android.net.Uri;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.resources.AndroidDevices;
import org.videolan.vlc.mediadb.models.BrowserFav;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.database.MigrationsKt$populateDB$1", f = "Migrations.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: Migrations.kt */
final class MigrationsKt$populateDB$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MigrationsKt$populateDB$1(Context context, Continuation<? super MigrationsKt$populateDB$1> continuation) {
        super(2, continuation);
        this.$context = context;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MigrationsKt$populateDB$1(this.$context, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MigrationsKt$populateDB$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            List<Uri> listOf = CollectionsKt.listOf(AndroidDevices.MediaFolders.INSTANCE.getEXTERNAL_PUBLIC_MOVIES_DIRECTORY_URI(), AndroidDevices.MediaFolders.INSTANCE.getEXTERNAL_PUBLIC_MUSIC_DIRECTORY_URI(), AndroidDevices.MediaFolders.INSTANCE.getEXTERNAL_PUBLIC_PODCAST_DIRECTORY_URI(), AndroidDevices.MediaFolders.INSTANCE.getEXTERNAL_PUBLIC_DOWNLOAD_DIRECTORY_URI(), AndroidDevices.MediaFolders.INSTANCE.getWHATSAPP_VIDEOS_FILE_URI(), AndroidDevices.MediaFolders.INSTANCE.getWHATSAPP_VIDEOS_FILE_URI_A11());
            BrowserFavDao browserFavDao = ((MediaDatabase) MediaDatabase.Companion.getInstance(this.$context)).browserFavDao();
            for (Uri uri : listOf) {
                String lastPathSegment = uri.getLastPathSegment();
                if (lastPathSegment == null) {
                    lastPathSegment = "";
                }
                browserFavDao.insert(new BrowserFav(uri, 1, lastPathSegment, (String) null));
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
