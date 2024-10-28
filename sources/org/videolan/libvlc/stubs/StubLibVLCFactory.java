package org.videolan.libvlc.stubs;

import android.content.Context;
import java.util.List;
import org.videolan.libvlc.interfaces.ILibVLC;
import org.videolan.libvlc.interfaces.ILibVLCFactory;

public class StubLibVLCFactory implements ILibVLCFactory {
    public ILibVLC getFromOptions(Context context, List<String> list) {
        return new StubLibVLC(context, list);
    }

    public ILibVLC getFromContext(Context context) {
        return new StubLibVLC(context);
    }
}
