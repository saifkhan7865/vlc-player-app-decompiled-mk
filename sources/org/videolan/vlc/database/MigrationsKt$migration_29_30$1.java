package org.videolan.vlc.database;

import android.net.Uri;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.AndroidDevices;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"org/videolan/vlc/database/MigrationsKt$migration_29_30$1", "Landroidx/room/migration/Migration;", "migrate", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "mediadb_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Migrations.kt */
public final class MigrationsKt$migration_29_30$1 extends Migration {
    MigrationsKt$migration_29_30$1() {
        super(29, 30);
    }

    public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "db");
        Uri whatsapp_videos_file_uri_a11 = AndroidDevices.MediaFolders.INSTANCE.getWHATSAPP_VIDEOS_FILE_URI_A11();
        supportSQLiteDatabase.execSQL("INSERT INTO  fav_table(uri, type, title, icon_url) VALUES (\"" + whatsapp_videos_file_uri_a11 + "\", 1, \"" + whatsapp_videos_file_uri_a11.getLastPathSegment() + "\", null)");
    }
}
