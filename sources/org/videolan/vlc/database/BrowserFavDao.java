package org.videolan.vlc.database;

import android.net.Uri;
import androidx.lifecycle.LiveData;
import java.util.List;
import kotlin.Metadata;
import kotlinx.coroutines.flow.Flow;
import org.videolan.vlc.mediadb.models.BrowserFav;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\nH'J\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\fH'J\u0014\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\nH'J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\bH'¨\u0006\u0010"}, d2 = {"Lorg/videolan/vlc/database/BrowserFavDao;", "", "delete", "", "uri", "Landroid/net/Uri;", "get", "", "Lorg/videolan/vlc/mediadb/models/BrowserFav;", "getAll", "Lkotlinx/coroutines/flow/Flow;", "getAllLocalFavs", "Landroidx/lifecycle/LiveData;", "getAllNetworkFavs", "insert", "browserFav", "mediadb_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BrowserFavDao.kt */
public interface BrowserFavDao {
    void delete(Uri uri);

    List<BrowserFav> get(Uri uri);

    Flow<List<BrowserFav>> getAll();

    LiveData<List<BrowserFav>> getAllLocalFavs();

    Flow<List<BrowserFav>> getAllNetworkFavs();

    void insert(BrowserFav browserFav);
}
