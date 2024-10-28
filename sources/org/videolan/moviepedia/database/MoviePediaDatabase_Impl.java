package org.videolan.moviepedia.database;

import android.os.Build;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import io.ktor.http.ContentDisposition;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class MoviePediaDatabase_Impl extends MoviePediaDatabase {
    private volatile MediaImageDao _mediaImageDao;
    private volatile MediaMetadataDao _mediaMetadataDao;
    private volatile MediaMetadataDataFullDao _mediaMetadataDataFullDao;
    private volatile MediaPersonJoinDao _mediaPersonJoinDao;
    private volatile PersonDao _personDao;

    /* access modifiers changed from: protected */
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new RoomOpenHelper.Delegate(1) {
            public void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            }

            public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `media_metadata` (`moviepedia_id` TEXT NOT NULL, `ml_id` INTEGER, `type` INTEGER NOT NULL, `title` TEXT NOT NULL, `summary` TEXT NOT NULL, `genres` TEXT NOT NULL, `releaseDate` INTEGER, `countries` TEXT NOT NULL, `season` INTEGER, `episode` INTEGER, `current_poster` TEXT NOT NULL, `current_backdrop` TEXT NOT NULL, `show_id` TEXT, `has_cast` INTEGER NOT NULL, `insertDate` INTEGER NOT NULL, PRIMARY KEY(`moviepedia_id`), FOREIGN KEY(`show_id`) REFERENCES `media_metadata`(`moviepedia_id`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `media_metadata_person` (`moviepedia_id` TEXT NOT NULL, `name` TEXT NOT NULL, `image` TEXT, PRIMARY KEY(`moviepedia_id`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `media_person_join` (`mediaId` TEXT NOT NULL, `personId` TEXT NOT NULL, `type` INTEGER NOT NULL, PRIMARY KEY(`mediaId`, `personId`, `type`), FOREIGN KEY(`mediaId`) REFERENCES `media_metadata`(`moviepedia_id`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`personId`) REFERENCES `media_metadata_person`(`moviepedia_id`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `media_metadata_image` (`url` TEXT NOT NULL, `media_id` TEXT NOT NULL, `image_type` INTEGER NOT NULL, `image_language` TEXT NOT NULL, PRIMARY KEY(`url`, `media_id`), FOREIGN KEY(`media_id`) REFERENCES `media_metadata`(`moviepedia_id`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
                supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '365a57b9404bfdf5a9d854f7c7639e8d')");
            }

            public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `media_metadata`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `media_metadata_person`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `media_person_join`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `media_metadata_image`");
                List<RoomDatabase.Callback> access$000 = MoviePediaDatabase_Impl.this.mCallbacks;
                if (access$000 != null) {
                    for (RoomDatabase.Callback onDestructiveMigration : access$000) {
                        onDestructiveMigration.onDestructiveMigration(supportSQLiteDatabase);
                    }
                }
            }

            public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
                List<RoomDatabase.Callback> access$100 = MoviePediaDatabase_Impl.this.mCallbacks;
                if (access$100 != null) {
                    for (RoomDatabase.Callback onCreate : access$100) {
                        onCreate.onCreate(supportSQLiteDatabase);
                    }
                }
            }

            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                SupportSQLiteDatabase unused = MoviePediaDatabase_Impl.this.mDatabase = supportSQLiteDatabase;
                supportSQLiteDatabase.execSQL("PRAGMA foreign_keys = ON");
                MoviePediaDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
                List<RoomDatabase.Callback> access$400 = MoviePediaDatabase_Impl.this.mCallbacks;
                if (access$400 != null) {
                    for (RoomDatabase.Callback onOpen : access$400) {
                        onOpen.onOpen(supportSQLiteDatabase);
                    }
                }
            }

            public void onPreMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
                DBUtil.dropFtsSyncTriggers(supportSQLiteDatabase);
            }

            public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase supportSQLiteDatabase) {
                SupportSQLiteDatabase supportSQLiteDatabase2 = supportSQLiteDatabase;
                HashMap hashMap = new HashMap(15);
                hashMap.put("moviepedia_id", new TableInfo.Column("moviepedia_id", "TEXT", true, 1, (String) null, 1));
                hashMap.put("ml_id", new TableInfo.Column("ml_id", "INTEGER", false, 0, (String) null, 1));
                hashMap.put("type", new TableInfo.Column("type", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("title", new TableInfo.Column("title", "TEXT", true, 0, (String) null, 1));
                hashMap.put("summary", new TableInfo.Column("summary", "TEXT", true, 0, (String) null, 1));
                hashMap.put("genres", new TableInfo.Column("genres", "TEXT", true, 0, (String) null, 1));
                hashMap.put("releaseDate", new TableInfo.Column("releaseDate", "INTEGER", false, 0, (String) null, 1));
                hashMap.put("countries", new TableInfo.Column("countries", "TEXT", true, 0, (String) null, 1));
                hashMap.put("season", new TableInfo.Column("season", "INTEGER", false, 0, (String) null, 1));
                hashMap.put("episode", new TableInfo.Column("episode", "INTEGER", false, 0, (String) null, 1));
                hashMap.put("current_poster", new TableInfo.Column("current_poster", "TEXT", true, 0, (String) null, 1));
                hashMap.put("current_backdrop", new TableInfo.Column("current_backdrop", "TEXT", true, 0, (String) null, 1));
                hashMap.put("show_id", new TableInfo.Column("show_id", "TEXT", false, 0, (String) null, 1));
                hashMap.put("has_cast", new TableInfo.Column("has_cast", "INTEGER", true, 0, (String) null, 1));
                hashMap.put("insertDate", new TableInfo.Column("insertDate", "INTEGER", true, 0, (String) null, 1));
                HashSet hashSet = new HashSet(1);
                hashSet.add(new TableInfo.ForeignKey("media_metadata", "NO ACTION", "NO ACTION", Arrays.asList(new String[]{"show_id"}), Arrays.asList(new String[]{"moviepedia_id"})));
                TableInfo tableInfo = new TableInfo("media_metadata", hashMap, hashSet, new HashSet(0));
                TableInfo read = TableInfo.read(supportSQLiteDatabase2, "media_metadata");
                if (!tableInfo.equals(read)) {
                    return new RoomOpenHelper.ValidationResult(false, "media_metadata(org.videolan.moviepedia.database.models.MediaMetadata).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
                }
                HashMap hashMap2 = new HashMap(3);
                hashMap2.put("moviepedia_id", new TableInfo.Column("moviepedia_id", "TEXT", true, 1, (String) null, 1));
                hashMap2.put(ContentDisposition.Parameters.Name, new TableInfo.Column(ContentDisposition.Parameters.Name, "TEXT", true, 0, (String) null, 1));
                hashMap2.put("image", new TableInfo.Column("image", "TEXT", false, 0, (String) null, 1));
                TableInfo tableInfo2 = new TableInfo("media_metadata_person", hashMap2, new HashSet(0), new HashSet(0));
                TableInfo read2 = TableInfo.read(supportSQLiteDatabase2, "media_metadata_person");
                if (!tableInfo2.equals(read2)) {
                    return new RoomOpenHelper.ValidationResult(false, "media_metadata_person(org.videolan.moviepedia.database.models.Person).\n Expected:\n" + tableInfo2 + "\n Found:\n" + read2);
                }
                HashMap hashMap3 = new HashMap(3);
                hashMap3.put("mediaId", new TableInfo.Column("mediaId", "TEXT", true, 1, (String) null, 1));
                hashMap3.put("personId", new TableInfo.Column("personId", "TEXT", true, 2, (String) null, 1));
                hashMap3.put("type", new TableInfo.Column("type", "INTEGER", true, 3, (String) null, 1));
                HashSet hashSet2 = new HashSet(2);
                hashSet2.add(new TableInfo.ForeignKey("media_metadata", "NO ACTION", "NO ACTION", Arrays.asList(new String[]{"mediaId"}), Arrays.asList(new String[]{"moviepedia_id"})));
                hashSet2.add(new TableInfo.ForeignKey("media_metadata_person", "NO ACTION", "NO ACTION", Arrays.asList(new String[]{"personId"}), Arrays.asList(new String[]{"moviepedia_id"})));
                TableInfo tableInfo3 = new TableInfo("media_person_join", hashMap3, hashSet2, new HashSet(0));
                TableInfo read3 = TableInfo.read(supportSQLiteDatabase2, "media_person_join");
                if (!tableInfo3.equals(read3)) {
                    return new RoomOpenHelper.ValidationResult(false, "media_person_join(org.videolan.moviepedia.database.models.MediaPersonJoin).\n Expected:\n" + tableInfo3 + "\n Found:\n" + read3);
                }
                HashMap hashMap4 = new HashMap(4);
                hashMap4.put(RtspHeaders.Values.URL, new TableInfo.Column(RtspHeaders.Values.URL, "TEXT", true, 1, (String) null, 1));
                hashMap4.put("media_id", new TableInfo.Column("media_id", "TEXT", true, 2, (String) null, 1));
                hashMap4.put("image_type", new TableInfo.Column("image_type", "INTEGER", true, 0, (String) null, 1));
                hashMap4.put("image_language", new TableInfo.Column("image_language", "TEXT", true, 0, (String) null, 1));
                HashSet hashSet3 = new HashSet(1);
                hashSet3.add(new TableInfo.ForeignKey("media_metadata", "NO ACTION", "NO ACTION", Arrays.asList(new String[]{"media_id"}), Arrays.asList(new String[]{"moviepedia_id"})));
                TableInfo tableInfo4 = new TableInfo("media_metadata_image", hashMap4, hashSet3, new HashSet(0));
                TableInfo read4 = TableInfo.read(supportSQLiteDatabase2, "media_metadata_image");
                if (tableInfo4.equals(read4)) {
                    return new RoomOpenHelper.ValidationResult(true, (String) null);
                }
                return new RoomOpenHelper.ValidationResult(false, "media_metadata_image(org.videolan.moviepedia.database.models.MediaImage).\n Expected:\n" + tableInfo4 + "\n Found:\n" + read4);
            }
        }, "365a57b9404bfdf5a9d854f7c7639e8d", "bca5384c7a2d285a8efab5e68fe21614")).build());
    }

    /* access modifiers changed from: protected */
    public InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "media_metadata", "media_metadata_person", "media_person_join", "media_metadata_image");
    }

    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        boolean z = Build.VERSION.SDK_INT >= 21;
        if (!z) {
            try {
                writableDatabase.execSQL("PRAGMA foreign_keys = FALSE");
            } catch (Throwable th) {
                super.endTransaction();
                if (!z) {
                    writableDatabase.execSQL("PRAGMA foreign_keys = TRUE");
                }
                writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
                if (!writableDatabase.inTransaction()) {
                    writableDatabase.execSQL("VACUUM");
                }
                throw th;
            }
        }
        super.beginTransaction();
        if (z) {
            writableDatabase.execSQL("PRAGMA defer_foreign_keys = TRUE");
        }
        writableDatabase.execSQL("DELETE FROM `media_person_join`");
        writableDatabase.execSQL("DELETE FROM `media_metadata_image`");
        writableDatabase.execSQL("DELETE FROM `media_metadata`");
        writableDatabase.execSQL("DELETE FROM `media_metadata_person`");
        super.setTransactionSuccessful();
        super.endTransaction();
        if (!z) {
            writableDatabase.execSQL("PRAGMA foreign_keys = TRUE");
        }
        writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
        if (!writableDatabase.inTransaction()) {
            writableDatabase.execSQL("VACUUM");
        }
    }

    /* access modifiers changed from: protected */
    public Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap hashMap = new HashMap();
        hashMap.put(MediaMetadataDao.class, MediaMetadataDao_Impl.getRequiredConverters());
        hashMap.put(PersonDao.class, PersonDao_Impl.getRequiredConverters());
        hashMap.put(MediaPersonJoinDao.class, MediaPersonJoinDao_Impl.getRequiredConverters());
        hashMap.put(MediaMetadataDataFullDao.class, MediaMetadataDataFullDao_Impl.getRequiredConverters());
        hashMap.put(MediaImageDao.class, MediaImageDao_Impl.getRequiredConverters());
        return hashMap;
    }

    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        return new HashSet();
    }

    public List<Migration> getAutoMigrations(Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> map) {
        return new ArrayList();
    }

    public MediaMetadataDao mediaMetadataDao() {
        MediaMetadataDao mediaMetadataDao;
        if (this._mediaMetadataDao != null) {
            return this._mediaMetadataDao;
        }
        synchronized (this) {
            if (this._mediaMetadataDao == null) {
                this._mediaMetadataDao = new MediaMetadataDao_Impl(this);
            }
            mediaMetadataDao = this._mediaMetadataDao;
        }
        return mediaMetadataDao;
    }

    public PersonDao personDao() {
        PersonDao personDao;
        if (this._personDao != null) {
            return this._personDao;
        }
        synchronized (this) {
            if (this._personDao == null) {
                this._personDao = new PersonDao_Impl(this);
            }
            personDao = this._personDao;
        }
        return personDao;
    }

    public MediaPersonJoinDao mediaPersonActorJoinDao() {
        MediaPersonJoinDao mediaPersonJoinDao;
        if (this._mediaPersonJoinDao != null) {
            return this._mediaPersonJoinDao;
        }
        synchronized (this) {
            if (this._mediaPersonJoinDao == null) {
                this._mediaPersonJoinDao = new MediaPersonJoinDao_Impl(this);
            }
            mediaPersonJoinDao = this._mediaPersonJoinDao;
        }
        return mediaPersonJoinDao;
    }

    public MediaMetadataDataFullDao mediaMedataDataFullDao() {
        MediaMetadataDataFullDao mediaMetadataDataFullDao;
        if (this._mediaMetadataDataFullDao != null) {
            return this._mediaMetadataDataFullDao;
        }
        synchronized (this) {
            if (this._mediaMetadataDataFullDao == null) {
                this._mediaMetadataDataFullDao = new MediaMetadataDataFullDao_Impl(this);
            }
            mediaMetadataDataFullDao = this._mediaMetadataDataFullDao;
        }
        return mediaMetadataDataFullDao;
    }

    public MediaImageDao mediaImageDao() {
        MediaImageDao mediaImageDao;
        if (this._mediaImageDao != null) {
            return this._mediaImageDao;
        }
        synchronized (this) {
            if (this._mediaImageDao == null) {
                this._mediaImageDao = new MediaImageDao_Impl(this);
            }
            mediaImageDao = this._mediaImageDao;
        }
        return mediaImageDao;
    }
}
