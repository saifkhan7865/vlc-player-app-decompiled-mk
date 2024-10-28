package org.fusesource.jansi;

import java.io.UnsupportedEncodingException;
import org.fusesource.jansi.internal.Kernel32;

public class WindowsSupport {
    public static String getLastErrorMessage() {
        return getErrorMessage(Kernel32.GetLastError());
    }

    public static String getErrorMessage(int i) {
        byte[] bArr = new byte[160];
        Kernel32.FormatMessageW(Kernel32.FORMAT_MESSAGE_FROM_SYSTEM, 0, i, 0, bArr, 160, (long[]) null);
        try {
            return new String(bArr, "UTF-16LE").trim();
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }
}
