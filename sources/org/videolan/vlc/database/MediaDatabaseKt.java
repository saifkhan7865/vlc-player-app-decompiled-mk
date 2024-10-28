package org.videolan.vlc.database;

import android.content.Context;
import androidx.room.Room;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"DB_NAME", "", "buildDatabase", "Lorg/videolan/vlc/database/MediaDatabase;", "context", "Landroid/content/Context;", "mediadb_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaDatabase.kt */
public final class MediaDatabaseKt {
    private static final String DB_NAME = "vlc_database";

    /* access modifiers changed from: private */
    public static final MediaDatabase buildDatabase(Context context) {
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        return Room.databaseBuilder(applicationContext, MediaDatabase.class, DB_NAME).addMigrations(MigrationsKt.getMigration_1_2(), MigrationsKt.getMigration_2_3(), MigrationsKt.getMigration_3_4(), MigrationsKt.getMigration_4_5(), MigrationsKt.getMigration_5_6(), MigrationsKt.getMigration_6_7(), MigrationsKt.getMigration_7_8(), MigrationsKt.getMigration_8_9(), MigrationsKt.getMigration_9_10(), MigrationsKt.getMigration_10_11(), MigrationsKt.getMigration_11_12(), MigrationsKt.getMigration_12_13(), MigrationsKt.getMigration_13_14(), MigrationsKt.getMigration_14_15(), MigrationsKt.getMigration_15_16(), MigrationsKt.getMigration_16_17(), MigrationsKt.getMigration_17_18(), MigrationsKt.getMigration_18_19(), MigrationsKt.getMigration_19_20(), MigrationsKt.getMigration_20_21(), MigrationsKt.getMigration_21_22(), MigrationsKt.getMigration_22_23(), MigrationsKt.getMigration_23_24(), MigrationsKt.getMigration_24_25(), MigrationsKt.getMigration_25_26(), MigrationsKt.getMigration_26_27(), MigrationsKt.getMigration_27_28(), MigrationsKt.getMigration_28_29(), MigrationsKt.getMigration_29_30(), MigrationsKt.getMigration_30_31(), MigrationsKt.getMigration_31_32(), MigrationsKt.getMigration_32_33(), MigrationsKt.getMigration_33_34(), MigrationsKt.getMigration_34_35()).addCallback(new MediaDatabaseKt$buildDatabase$1(context)).build();
    }
}
