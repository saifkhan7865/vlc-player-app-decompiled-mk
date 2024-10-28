package org.videolan.moviepedia.database;

import android.content.Context;
import androidx.room.Room;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"DB_NAME", "", "buildDatabase", "Lorg/videolan/moviepedia/database/MoviePediaDatabase;", "context", "Landroid/content/Context;", "moviepedia_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: MoviePediaDatabase.kt */
public final class MoviePediaDatabaseKt {
    private static final String DB_NAME = "moviepedia_database";

    /* access modifiers changed from: private */
    public static final MoviePediaDatabase buildDatabase(Context context) {
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        return Room.databaseBuilder(applicationContext, MoviePediaDatabase.class, DB_NAME).build();
    }
}
