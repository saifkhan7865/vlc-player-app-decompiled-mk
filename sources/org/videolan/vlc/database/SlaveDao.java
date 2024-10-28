package org.videolan.vlc.database;

import java.util.List;
import kotlin.Metadata;
import org.videolan.vlc.mediadb.models.Slave;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H'J\u001b\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\nH'¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u0004H'¨\u0006\r"}, d2 = {"Lorg/videolan/vlc/database/SlaveDao;", "", "get", "", "Lorg/videolan/vlc/mediadb/models/Slave;", "mrl", "", "insert", "", "slaves", "", "([Lorg/videolan/vlc/mediadb/models/Slave;)V", "slave", "mediadb_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SlaveDao.kt */
public interface SlaveDao {
    List<Slave> get(String str);

    void insert(Slave slave);

    void insert(Slave[] slaveArr);
}
