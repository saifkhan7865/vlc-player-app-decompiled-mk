package org.videolan.vlc.database;

import android.content.SharedPreferences;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.resources.AppContextProvider;
import org.videolan.tools.Settings;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"org/videolan/vlc/database/MigrationsKt$migration_27_28$1", "Landroidx/room/migration/Migration;", "migrate", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "mediadb_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Migrations.kt */
public final class MigrationsKt$migration_27_28$1 extends Migration {
    MigrationsKt$migration_27_28$1() {
        super(27, 28);
    }

    public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
        List<String> list;
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "db");
        CharSequence string = ((SharedPreferences) Settings.INSTANCE.getInstance(AppContextProvider.INSTANCE.getAppContext())).getString("custom_paths", "");
        if (string == null || string.length() == 0) {
            list = null;
        } else {
            list = StringsKt.split$default(string, new String[]{":"}, false, 0, 6, (Object) null);
        }
        supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS CustomDirectory(path TEXT PRIMARY KEY NOT NULL);");
        if (list != null) {
            for (String str : list) {
                supportSQLiteDatabase.execSQL("INSERT INTO CustomDirectory(path) VALUES (\"" + str + "\")");
            }
        }
    }
}
