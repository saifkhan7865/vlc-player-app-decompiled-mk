package org.videolan.vlc.gui.helpers;

import android.content.Context;
import androidx.appcompat.view.ActionMode;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.MultiSelectHelper;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.helpers.UiToolsKt", f = "UiTools.kt", i = {0, 0, 0, 0, 0}, l = {1034}, m = "fillActionMode", n = {"context", "mode", "realCount", "length", "ready"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
/* compiled from: UiTools.kt */
final class UiToolsKt$fillActionMode$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;

    UiToolsKt$fillActionMode$1(Continuation<? super UiToolsKt$fillActionMode$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return UiToolsKt.fillActionMode((Context) null, (ActionMode) null, (MultiSelectHelper<MediaLibraryItem>) null, this);
    }
}
