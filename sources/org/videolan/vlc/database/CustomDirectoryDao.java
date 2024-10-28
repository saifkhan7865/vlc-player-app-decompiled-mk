package org.videolan.vlc.database;

import java.util.List;
import kotlin.Metadata;
import org.videolan.vlc.mediadb.models.CustomDirectory;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00072\u0006\u0010\b\u001a\u00020\tH'J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H'J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'¨\u0006\f"}, d2 = {"Lorg/videolan/vlc/database/CustomDirectoryDao;", "", "delete", "", "customDirectory", "Lorg/videolan/vlc/mediadb/models/CustomDirectory;", "get", "", "path", "", "getAll", "insert", "mediadb_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CustomDirectoryDao.kt */
public interface CustomDirectoryDao {
    void delete(CustomDirectory customDirectory);

    List<CustomDirectory> get(String str);

    List<CustomDirectory> getAll();

    void insert(CustomDirectory customDirectory);
}
