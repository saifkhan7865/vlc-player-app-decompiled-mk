package org.fusesource.jansi;

import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import org.fusesource.jansi.io.AnsiOutputStream;

public class AnsiPrintStream extends PrintStream {
    public AnsiPrintStream(AnsiOutputStream ansiOutputStream, boolean z) {
        super(ansiOutputStream, z);
    }

    public AnsiPrintStream(AnsiOutputStream ansiOutputStream, boolean z, String str) throws UnsupportedEncodingException {
        super(ansiOutputStream, z, str);
    }

    /* access modifiers changed from: protected */
    public AnsiOutputStream getOut() {
        return (AnsiOutputStream) this.out;
    }

    public AnsiType getType() {
        return getOut().getType();
    }

    public AnsiColors getColors() {
        return getOut().getColors();
    }

    public AnsiMode getMode() {
        return getOut().getMode();
    }

    public void setMode(AnsiMode ansiMode) {
        getOut().setMode(ansiMode);
    }

    public boolean isResetAtUninstall() {
        return getOut().isResetAtUninstall();
    }

    public void setResetAtUninstall(boolean z) {
        getOut().setResetAtUninstall(z);
    }

    public int getTerminalWidth() {
        return getOut().getTerminalWidth();
    }

    public void install() throws IOException {
        getOut().install();
    }

    public void uninstall() throws IOException {
        AnsiOutputStream out = getOut();
        if (out != null) {
            out.uninstall();
        }
    }

    public String toString() {
        return "AnsiPrintStream{type=" + getType() + ", colors=" + getColors() + ", mode=" + getMode() + ", resetAtUninstall=" + isResetAtUninstall() + "}";
    }
}
