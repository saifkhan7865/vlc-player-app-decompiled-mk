package org.videolan.vlc.gui.dialogs;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.videolan.vlc.util.ContextOption;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\u001f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u0001\u0001\u000f¨\u0006\u0010"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/CtxMenuItem;", "", "id", "Lorg/videolan/vlc/util/ContextOption;", "title", "", "icon", "", "(Lorg/videolan/vlc/util/ContextOption;Ljava/lang/String;I)V", "getIcon", "()I", "getId", "()Lorg/videolan/vlc/util/ContextOption;", "getTitle", "()Ljava/lang/String;", "Lorg/videolan/vlc/gui/dialogs/Simple;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ContextSheet.kt */
public abstract class CtxMenuItem {
    private final int icon;
    private final ContextOption id;
    private final String title;

    public /* synthetic */ CtxMenuItem(ContextOption contextOption, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(contextOption, str, i);
    }

    private CtxMenuItem(ContextOption contextOption, String str, int i) {
        this.id = contextOption;
        this.title = str;
        this.icon = i;
    }

    public final int getIcon() {
        return this.icon;
    }

    public final ContextOption getId() {
        return this.id;
    }

    public final String getTitle() {
        return this.title;
    }
}
