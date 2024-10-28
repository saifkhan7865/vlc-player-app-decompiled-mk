package org.videolan.resources.interfaces;

import android.content.Context;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.coroutines.Continuation;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J6\u0010\u0006\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007j\u0002`\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0003H¦@¢\u0006\u0002\u0010\u000fR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0010"}, d2 = {"Lorg/videolan/resources/interfaces/IMediaContentResolver;", "", "prefix", "", "getPrefix", "()Ljava/lang/String;", "getList", "Lkotlin/Pair;", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "", "Lorg/videolan/resources/interfaces/ResumableList;", "context", "Landroid/content/Context;", "id", "(Landroid/content/Context;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resources_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IMediaContentResolver.kt */
public interface IMediaContentResolver {
    Object getList(Context context, String str, Continuation<? super Pair<? extends List<? extends MediaWrapper>, Integer>> continuation);

    String getPrefix();
}
