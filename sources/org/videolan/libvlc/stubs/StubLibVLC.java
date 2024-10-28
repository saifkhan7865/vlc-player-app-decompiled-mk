package org.videolan.libvlc.stubs;

import android.content.Context;
import java.util.List;
import org.videolan.libvlc.interfaces.ILibVLC;

public class StubLibVLC extends StubVLCObject<ILibVLC.Event> implements ILibVLC {
    private final Context mContext;

    public StubLibVLC(Context context, List<String> list) {
        this.mContext = context;
    }

    public StubLibVLC(Context context) {
        this(context, (List<String>) null);
    }

    public Context getAppContext() {
        return this.mContext;
    }
}
