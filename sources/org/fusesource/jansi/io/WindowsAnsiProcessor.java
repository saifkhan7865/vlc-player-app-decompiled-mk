package org.fusesource.jansi.io;

import java.io.IOException;
import java.io.OutputStream;
import org.fusesource.jansi.WindowsSupport;
import org.fusesource.jansi.internal.Kernel32;

public final class WindowsAnsiProcessor extends AnsiProcessor {
    private static final short[] ANSI_BACKGROUND_COLOR_MAP;
    private static final short[] ANSI_FOREGROUND_COLOR_MAP;
    private static final short BACKGROUND_BLACK = 0;
    private static final short BACKGROUND_CYAN;
    private static final short BACKGROUND_MAGENTA;
    private static final short BACKGROUND_WHITE;
    private static final short BACKGROUND_YELLOW;
    private static final short FOREGROUND_BLACK = 0;
    private static final short FOREGROUND_CYAN;
    private static final short FOREGROUND_MAGENTA;
    private static final short FOREGROUND_WHITE;
    private static final short FOREGROUND_YELLOW;
    private final long console;
    private final Kernel32.CONSOLE_SCREEN_BUFFER_INFO info;
    private boolean negative;
    private final short originalColors;
    private short savedX;
    private short savedY;

    private short invertAttributeColors(short s) {
        return (short) ((s & 65280) | ((s & 15) << 4) | ((s & 240) >> 4));
    }

    static {
        short s = (short) (Kernel32.FOREGROUND_RED | Kernel32.FOREGROUND_GREEN);
        FOREGROUND_YELLOW = s;
        short s2 = (short) (Kernel32.FOREGROUND_BLUE | Kernel32.FOREGROUND_RED);
        FOREGROUND_MAGENTA = s2;
        short s3 = (short) (Kernel32.FOREGROUND_BLUE | Kernel32.FOREGROUND_GREEN);
        FOREGROUND_CYAN = s3;
        short s4 = (short) (Kernel32.FOREGROUND_RED | Kernel32.FOREGROUND_GREEN | Kernel32.FOREGROUND_BLUE);
        FOREGROUND_WHITE = s4;
        short s5 = (short) (Kernel32.BACKGROUND_RED | Kernel32.BACKGROUND_GREEN);
        BACKGROUND_YELLOW = s5;
        short s6 = (short) (Kernel32.BACKGROUND_BLUE | Kernel32.BACKGROUND_RED);
        BACKGROUND_MAGENTA = s6;
        short s7 = (short) (Kernel32.BACKGROUND_BLUE | Kernel32.BACKGROUND_GREEN);
        BACKGROUND_CYAN = s7;
        short s8 = (short) (Kernel32.BACKGROUND_RED | Kernel32.BACKGROUND_GREEN | Kernel32.BACKGROUND_BLUE);
        BACKGROUND_WHITE = s8;
        ANSI_FOREGROUND_COLOR_MAP = new short[]{0, Kernel32.FOREGROUND_RED, Kernel32.FOREGROUND_GREEN, s, Kernel32.FOREGROUND_BLUE, s2, s3, s4};
        ANSI_BACKGROUND_COLOR_MAP = new short[]{0, Kernel32.BACKGROUND_RED, Kernel32.BACKGROUND_GREEN, s5, Kernel32.BACKGROUND_BLUE, s6, s7, s8};
    }

    public WindowsAnsiProcessor(OutputStream outputStream, long j) throws IOException {
        super(outputStream);
        Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info = new Kernel32.CONSOLE_SCREEN_BUFFER_INFO();
        this.info = console_screen_buffer_info;
        this.savedX = -1;
        this.savedY = -1;
        this.console = j;
        getConsoleInfo();
        this.originalColors = console_screen_buffer_info.attributes;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public WindowsAnsiProcessor(OutputStream outputStream, boolean z) throws IOException {
        this(outputStream, Kernel32.GetStdHandle(z ? Kernel32.STD_OUTPUT_HANDLE : Kernel32.STD_ERROR_HANDLE));
    }

    public WindowsAnsiProcessor(OutputStream outputStream) throws IOException {
        this(outputStream, true);
    }

    private void getConsoleInfo() throws IOException {
        this.os.flush();
        if (Kernel32.GetConsoleScreenBufferInfo(this.console, this.info) == 0) {
            throw new IOException("Could not get the screen info: " + WindowsSupport.getLastErrorMessage());
        } else if (this.negative) {
            Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info = this.info;
            console_screen_buffer_info.attributes = invertAttributeColors(console_screen_buffer_info.attributes);
        }
    }

    private void applyAttribute() throws IOException {
        this.os.flush();
        short s = this.info.attributes;
        if (this.negative) {
            s = invertAttributeColors(s);
        }
        if (Kernel32.SetConsoleTextAttribute(this.console, s) == 0) {
            throw new IOException(WindowsSupport.getLastErrorMessage());
        }
    }

    private void applyCursorPosition() throws IOException {
        if (Kernel32.SetConsoleCursorPosition(this.console, this.info.cursorPosition.copy()) == 0) {
            throw new IOException(WindowsSupport.getLastErrorMessage());
        }
    }

    /* access modifiers changed from: protected */
    public void processEraseScreen(int i) throws IOException {
        getConsoleInfo();
        int[] iArr = new int[1];
        if (i == 0) {
            int i2 = ((this.info.window.bottom - this.info.cursorPosition.y) * this.info.size.x) + (this.info.size.x - this.info.cursorPosition.x);
            int[] iArr2 = iArr;
            Kernel32.FillConsoleOutputAttribute(this.console, this.info.attributes, i2, this.info.cursorPosition.copy(), iArr2);
            Kernel32.FillConsoleOutputCharacterW(this.console, ' ', i2, this.info.cursorPosition.copy(), iArr2);
        } else if (i == 1) {
            Kernel32.COORD coord = new Kernel32.COORD();
            coord.x = 0;
            coord.y = this.info.window.top;
            int i3 = ((this.info.cursorPosition.y - this.info.window.top) * this.info.size.x) + this.info.cursorPosition.x;
            Kernel32.COORD coord2 = coord;
            int[] iArr3 = iArr;
            Kernel32.FillConsoleOutputAttribute(this.console, this.info.attributes, i3, coord2, iArr3);
            Kernel32.FillConsoleOutputCharacterW(this.console, ' ', i3, coord2, iArr3);
        } else if (i == 2) {
            Kernel32.COORD coord3 = new Kernel32.COORD();
            coord3.x = 0;
            coord3.y = this.info.window.top;
            int height = this.info.window.height() * this.info.size.x;
            Kernel32.COORD coord4 = coord3;
            int[] iArr4 = iArr;
            Kernel32.FillConsoleOutputAttribute(this.console, this.info.attributes, height, coord4, iArr4);
            Kernel32.FillConsoleOutputCharacterW(this.console, ' ', height, coord4, iArr4);
        }
    }

    /* access modifiers changed from: protected */
    public void processEraseLine(int i) throws IOException {
        getConsoleInfo();
        int[] iArr = new int[1];
        if (i == 0) {
            int i2 = this.info.size.x - this.info.cursorPosition.x;
            int[] iArr2 = iArr;
            Kernel32.FillConsoleOutputAttribute(this.console, this.info.attributes, i2, this.info.cursorPosition.copy(), iArr2);
            Kernel32.FillConsoleOutputCharacterW(this.console, ' ', i2, this.info.cursorPosition.copy(), iArr2);
        } else if (i == 1) {
            Kernel32.COORD copy = this.info.cursorPosition.copy();
            copy.x = 0;
            Kernel32.COORD coord = copy;
            int[] iArr3 = iArr;
            Kernel32.FillConsoleOutputAttribute(this.console, this.info.attributes, this.info.cursorPosition.x, coord, iArr3);
            Kernel32.FillConsoleOutputCharacterW(this.console, ' ', this.info.cursorPosition.x, coord, iArr3);
        } else if (i == 2) {
            Kernel32.COORD copy2 = this.info.cursorPosition.copy();
            copy2.x = 0;
            Kernel32.COORD coord2 = copy2;
            int[] iArr4 = iArr;
            Kernel32.FillConsoleOutputAttribute(this.console, this.info.attributes, this.info.size.x, coord2, iArr4);
            Kernel32.FillConsoleOutputCharacterW(this.console, ' ', this.info.size.x, coord2, iArr4);
        }
    }

    /* access modifiers changed from: protected */
    public void processCursorLeft(int i) throws IOException {
        getConsoleInfo();
        this.info.cursorPosition.x = (short) Math.max(0, this.info.cursorPosition.x - i);
        applyCursorPosition();
    }

    /* access modifiers changed from: protected */
    public void processCursorRight(int i) throws IOException {
        getConsoleInfo();
        this.info.cursorPosition.x = (short) Math.min(this.info.window.width(), this.info.cursorPosition.x + i);
        applyCursorPosition();
    }

    /* access modifiers changed from: protected */
    public void processCursorDown(int i) throws IOException {
        getConsoleInfo();
        this.info.cursorPosition.y = (short) Math.min(Math.max(0, this.info.size.y - 1), this.info.cursorPosition.y + i);
        applyCursorPosition();
    }

    /* access modifiers changed from: protected */
    public void processCursorUp(int i) throws IOException {
        getConsoleInfo();
        this.info.cursorPosition.y = (short) Math.max(this.info.window.top, this.info.cursorPosition.y - i);
        applyCursorPosition();
    }

    /* access modifiers changed from: protected */
    public void processCursorTo(int i, int i2) throws IOException {
        getConsoleInfo();
        this.info.cursorPosition.y = (short) Math.max(this.info.window.top, Math.min(this.info.size.y, (this.info.window.top + i) - 1));
        this.info.cursorPosition.x = (short) Math.max(0, Math.min(this.info.window.width(), i2 - 1));
        applyCursorPosition();
    }

    /* access modifiers changed from: protected */
    public void processCursorToColumn(int i) throws IOException {
        getConsoleInfo();
        this.info.cursorPosition.x = (short) Math.max(0, Math.min(this.info.window.width(), i - 1));
        applyCursorPosition();
    }

    /* access modifiers changed from: protected */
    public void processCursorUpLine(int i) throws IOException {
        getConsoleInfo();
        this.info.cursorPosition.x = 0;
        this.info.cursorPosition.y = (short) Math.max(this.info.window.top, this.info.cursorPosition.y - i);
        applyCursorPosition();
    }

    /* access modifiers changed from: protected */
    public void processCursorDownLine(int i) throws IOException {
        getConsoleInfo();
        this.info.cursorPosition.x = 0;
        this.info.cursorPosition.y = (short) Math.max(this.info.window.top, this.info.cursorPosition.y + i);
        applyCursorPosition();
    }

    /* access modifiers changed from: protected */
    public void processSetForegroundColor(int i, boolean z) throws IOException {
        Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info = this.info;
        console_screen_buffer_info.attributes = (short) (ANSI_FOREGROUND_COLOR_MAP[i] | (console_screen_buffer_info.attributes & -8));
        if (z) {
            Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info2 = this.info;
            console_screen_buffer_info2.attributes = (short) (console_screen_buffer_info2.attributes | Kernel32.FOREGROUND_INTENSITY);
        }
        applyAttribute();
    }

    /* access modifiers changed from: protected */
    public void processSetForegroundColorExt(int i) throws IOException {
        int roundColor = Colors.roundColor(i, 16);
        processSetForegroundColor(roundColor >= 8 ? roundColor - 8 : roundColor, roundColor >= 8);
    }

    /* access modifiers changed from: protected */
    public void processSetForegroundColorExt(int i, int i2, int i3) throws IOException {
        int roundRgbColor = Colors.roundRgbColor(i, i2, i3, 16);
        processSetForegroundColor(roundRgbColor >= 8 ? roundRgbColor - 8 : roundRgbColor, roundRgbColor >= 8);
    }

    /* access modifiers changed from: protected */
    public void processSetBackgroundColor(int i, boolean z) throws IOException {
        Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info = this.info;
        console_screen_buffer_info.attributes = (short) (ANSI_BACKGROUND_COLOR_MAP[i] | (console_screen_buffer_info.attributes & -113));
        if (z) {
            Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info2 = this.info;
            console_screen_buffer_info2.attributes = (short) (console_screen_buffer_info2.attributes | Kernel32.BACKGROUND_INTENSITY);
        }
        applyAttribute();
    }

    /* access modifiers changed from: protected */
    public void processSetBackgroundColorExt(int i) throws IOException {
        int roundColor = Colors.roundColor(i, 16);
        processSetBackgroundColor(roundColor >= 8 ? roundColor - 8 : roundColor, roundColor >= 8);
    }

    /* access modifiers changed from: protected */
    public void processSetBackgroundColorExt(int i, int i2, int i3) throws IOException {
        int roundRgbColor = Colors.roundRgbColor(i, i2, i3, 16);
        processSetBackgroundColor(roundRgbColor >= 8 ? roundRgbColor - 8 : roundRgbColor, roundRgbColor >= 8);
    }

    /* access modifiers changed from: protected */
    public void processDefaultTextColor() throws IOException {
        Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info = this.info;
        console_screen_buffer_info.attributes = (short) ((console_screen_buffer_info.attributes & -16) | (this.originalColors & 15));
        Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info2 = this.info;
        console_screen_buffer_info2.attributes = (short) (console_screen_buffer_info2.attributes & (Kernel32.FOREGROUND_INTENSITY ^ -1));
        applyAttribute();
    }

    /* access modifiers changed from: protected */
    public void processDefaultBackgroundColor() throws IOException {
        Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info = this.info;
        console_screen_buffer_info.attributes = (short) ((console_screen_buffer_info.attributes & -241) | (this.originalColors & 240));
        Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info2 = this.info;
        console_screen_buffer_info2.attributes = (short) (console_screen_buffer_info2.attributes & (Kernel32.BACKGROUND_INTENSITY ^ -1));
        applyAttribute();
    }

    /* access modifiers changed from: protected */
    public void processAttributeReset() throws IOException {
        Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info = this.info;
        console_screen_buffer_info.attributes = (short) ((console_screen_buffer_info.attributes & -256) | this.originalColors);
        this.negative = false;
        applyAttribute();
    }

    /* access modifiers changed from: protected */
    public void processSetAttribute(int i) throws IOException {
        if (i == 1) {
            Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info = this.info;
            console_screen_buffer_info.attributes = (short) (console_screen_buffer_info.attributes | Kernel32.FOREGROUND_INTENSITY);
            applyAttribute();
        } else if (i == 4) {
            Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info2 = this.info;
            console_screen_buffer_info2.attributes = (short) (console_screen_buffer_info2.attributes | Kernel32.BACKGROUND_INTENSITY);
            applyAttribute();
        } else if (i == 7) {
            this.negative = true;
            applyAttribute();
        } else if (i == 22) {
            Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info3 = this.info;
            console_screen_buffer_info3.attributes = (short) (console_screen_buffer_info3.attributes & (Kernel32.FOREGROUND_INTENSITY ^ -1));
            applyAttribute();
        } else if (i == 24) {
            Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info4 = this.info;
            console_screen_buffer_info4.attributes = (short) (console_screen_buffer_info4.attributes & (Kernel32.BACKGROUND_INTENSITY ^ -1));
            applyAttribute();
        } else if (i == 27) {
            this.negative = false;
            applyAttribute();
        }
    }

    /* access modifiers changed from: protected */
    public void processSaveCursorPosition() throws IOException {
        getConsoleInfo();
        this.savedX = this.info.cursorPosition.x;
        this.savedY = this.info.cursorPosition.y;
    }

    /* access modifiers changed from: protected */
    public void processRestoreCursorPosition() throws IOException {
        if (this.savedX != -1 && this.savedY != -1) {
            this.os.flush();
            this.info.cursorPosition.x = this.savedX;
            this.info.cursorPosition.y = this.savedY;
            applyCursorPosition();
        }
    }

    /* access modifiers changed from: protected */
    public void processInsertLine(int i) throws IOException {
        getConsoleInfo();
        Kernel32.SMALL_RECT copy = this.info.window.copy();
        copy.top = this.info.cursorPosition.y;
        Kernel32.COORD coord = new Kernel32.COORD();
        coord.x = 0;
        coord.y = (short) (this.info.cursorPosition.y + i);
        Kernel32.CHAR_INFO char_info = new Kernel32.CHAR_INFO();
        char_info.attributes = this.originalColors;
        char_info.unicodeChar = ' ';
        if (Kernel32.ScrollConsoleScreenBuffer(this.console, copy, copy, coord, char_info) == 0) {
            throw new IOException(WindowsSupport.getLastErrorMessage());
        }
    }

    /* access modifiers changed from: protected */
    public void processDeleteLine(int i) throws IOException {
        getConsoleInfo();
        Kernel32.SMALL_RECT copy = this.info.window.copy();
        copy.top = this.info.cursorPosition.y;
        Kernel32.COORD coord = new Kernel32.COORD();
        coord.x = 0;
        coord.y = (short) (this.info.cursorPosition.y - i);
        Kernel32.CHAR_INFO char_info = new Kernel32.CHAR_INFO();
        char_info.attributes = this.originalColors;
        char_info.unicodeChar = ' ';
        if (Kernel32.ScrollConsoleScreenBuffer(this.console, copy, copy, coord, char_info) == 0) {
            throw new IOException(WindowsSupport.getLastErrorMessage());
        }
    }

    /* access modifiers changed from: protected */
    public void processChangeWindowTitle(String str) {
        Kernel32.SetConsoleTitle(str);
    }
}
