package org.videolan.moviepedia.database;

import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.Collections;
import java.util.List;
import org.videolan.moviepedia.database.models.MediaImage;

public final class MediaImageDao_Impl implements MediaImageDao {
    /* access modifiers changed from: private */
    public final Converters __converters = new Converters();
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<MediaImage> __deletionAdapterOfMediaImage;
    private final EntityInsertionAdapter<MediaImage> __insertionAdapterOfMediaImage;

    public MediaImageDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfMediaImage = new EntityInsertionAdapter<MediaImage>(roomDatabase) {
            /* access modifiers changed from: protected */
            public String createQuery() {
                return "INSERT OR REPLACE INTO `media_metadata_image` (`url`,`media_id`,`image_type`,`image_language`) VALUES (?,?,?,?)";
            }

            /* access modifiers changed from: protected */
            public void bind(SupportSQLiteStatement supportSQLiteStatement, MediaImage mediaImage) {
                if (mediaImage.getUrl() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, mediaImage.getUrl());
                }
                if (mediaImage.getMediaId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, mediaImage.getMediaId());
                }
                supportSQLiteStatement.bindLong(3, (long) MediaImageDao_Impl.this.__converters.mediaImageTypeToKey(mediaImage.getImageType()));
                if (mediaImage.getLanguage() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, mediaImage.getLanguage());
                }
            }
        };
        this.__deletionAdapterOfMediaImage = new EntityDeletionOrUpdateAdapter<MediaImage>(roomDatabase) {
            /* access modifiers changed from: protected */
            public String createQuery() {
                return "DELETE FROM `media_metadata_image` WHERE `url` = ? AND `media_id` = ?";
            }

            /* access modifiers changed from: protected */
            public void bind(SupportSQLiteStatement supportSQLiteStatement, MediaImage mediaImage) {
                if (mediaImage.getUrl() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, mediaImage.getUrl());
                }
                if (mediaImage.getMediaId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, mediaImage.getMediaId());
                }
            }
        };
    }

    public void insert(MediaImage mediaImage) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfMediaImage.insert(mediaImage);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void insertAll(List<MediaImage> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfMediaImage.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void deleteAll(List<MediaImage> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfMediaImage.handleMultiple(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
