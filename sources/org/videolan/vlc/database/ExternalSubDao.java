package org.videolan.vlc.database;

import androidx.lifecycle.LiveData;
import java.util.List;
import kotlin.Metadata;
import org.videolan.vlc.mediadb.models.ExternalSub;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H'J\u001c\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\nH'¨\u0006\r"}, d2 = {"Lorg/videolan/vlc/database/ExternalSubDao;", "", "delete", "", "mediaPath", "", "idSubtitle", "get", "Landroidx/lifecycle/LiveData;", "", "Lorg/videolan/vlc/mediadb/models/ExternalSub;", "insert", "externalSub", "mediadb_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExternalSubDao.kt */
public interface ExternalSubDao {
    void delete(String str, String str2);

    LiveData<List<ExternalSub>> get(String str);

    void insert(ExternalSub externalSub);
}
