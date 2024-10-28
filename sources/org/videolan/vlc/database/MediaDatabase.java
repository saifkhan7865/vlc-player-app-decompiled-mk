package org.videolan.vlc.database;

import android.content.Context;
import androidx.room.RoomDatabase;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.videolan.tools.SingletonHolder;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&¨\u0006\u000e"}, d2 = {"Lorg/videolan/vlc/database/MediaDatabase;", "Landroidx/room/RoomDatabase;", "()V", "browserFavDao", "Lorg/videolan/vlc/database/BrowserFavDao;", "customDirectoryDao", "Lorg/videolan/vlc/database/CustomDirectoryDao;", "externalSubDao", "Lorg/videolan/vlc/database/ExternalSubDao;", "slaveDao", "Lorg/videolan/vlc/database/SlaveDao;", "widgetDao", "Lorg/videolan/vlc/database/WidgetDao;", "Companion", "mediadb_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaDatabase.kt */
public abstract class MediaDatabase extends RoomDatabase {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    public abstract BrowserFavDao browserFavDao();

    public abstract CustomDirectoryDao customDirectoryDao();

    public abstract ExternalSubDao externalSubDao();

    public abstract SlaveDao slaveDao();

    public abstract WidgetDao widgetDao();

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/videolan/vlc/database/MediaDatabase$Companion;", "Lorg/videolan/tools/SingletonHolder;", "Lorg/videolan/vlc/database/MediaDatabase;", "Landroid/content/Context;", "()V", "mediadb_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaDatabase.kt */
    public static final class Companion extends SingletonHolder<MediaDatabase, Context> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
            super(AnonymousClass1.INSTANCE);
        }
    }
}
