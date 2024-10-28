package org.videolan.vlc.database;

import java.util.List;
import kotlin.Metadata;
import kotlinx.coroutines.flow.Flow;
import org.videolan.vlc.mediadb.models.Widget;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u0005H'J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\nH'J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\f2\u0006\u0010\b\u001a\u00020\u0005H'J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0007H'J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0007H'¨\u0006\u0010"}, d2 = {"Lorg/videolan/vlc/database/WidgetDao;", "", "delete", "", "id", "", "get", "Lorg/videolan/vlc/mediadb/models/Widget;", "widgetId", "getAll", "", "getFlow", "Lkotlinx/coroutines/flow/Flow;", "insert", "widget", "update", "mediadb_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: WidgetDao.kt */
public interface WidgetDao {
    void delete(int i);

    Widget get(int i);

    List<Widget> getAll();

    Flow<Widget> getFlow(int i);

    void insert(Widget widget);

    void update(Widget widget);
}