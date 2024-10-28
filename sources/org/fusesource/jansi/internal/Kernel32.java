package org.fusesource.jansi.internal;

import java.io.IOException;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public class Kernel32 {
    public static short BACKGROUND_BLUE;
    public static short BACKGROUND_GREEN;
    public static short BACKGROUND_INTENSITY;
    public static short BACKGROUND_RED;
    public static short COMMON_LVB_GRID_HORIZONTAL;
    public static short COMMON_LVB_GRID_LVERTICAL;
    public static short COMMON_LVB_GRID_RVERTICAL;
    public static short COMMON_LVB_LEADING_BYTE;
    public static short COMMON_LVB_REVERSE_VIDEO;
    public static short COMMON_LVB_TRAILING_BYTE;
    public static short COMMON_LVB_UNDERSCORE;
    public static short FOREGROUND_BLUE;
    public static short FOREGROUND_GREEN;
    public static short FOREGROUND_INTENSITY;
    public static short FOREGROUND_RED;
    public static int FORMAT_MESSAGE_FROM_SYSTEM;
    public static int INVALID_HANDLE_VALUE;
    public static int STD_ERROR_HANDLE;
    public static int STD_INPUT_HANDLE;
    public static int STD_OUTPUT_HANDLE;

    public static native int CloseHandle(long j);

    public static native int FillConsoleOutputAttribute(long j, short s, int i, COORD coord, int[] iArr);

    public static native int FillConsoleOutputCharacterW(long j, char c, int i, COORD coord, int[] iArr);

    public static native int FlushConsoleInputBuffer(long j);

    public static native int FormatMessageW(int i, long j, int i2, int i3, byte[] bArr, int i4, long[] jArr);

    public static native int GetConsoleMode(long j, int[] iArr);

    public static native int GetConsoleOutputCP();

    public static native int GetConsoleScreenBufferInfo(long j, CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info);

    public static native int GetLastError();

    public static native int GetNumberOfConsoleInputEvents(long j, int[] iArr);

    public static native long GetStdHandle(int i);

    private static native int PeekConsoleInputW(long j, long j2, int i, int[] iArr);

    private static native int ReadConsoleInputW(long j, long j2, int i, int[] iArr);

    public static native int ScrollConsoleScreenBuffer(long j, SMALL_RECT small_rect, SMALL_RECT small_rect2, COORD coord, CHAR_INFO char_info);

    public static native int SetConsoleCursorPosition(long j, COORD coord);

    public static native int SetConsoleMode(long j, int i);

    public static native int SetConsoleOutputCP(int i);

    public static native int SetConsoleTextAttribute(long j, short s);

    public static native int SetConsoleTitle(String str);

    public static native int WaitForSingleObject(long j, int i);

    public static native int WriteConsoleW(long j, char[] cArr, int i, int[] iArr, long j2);

    public static native int _getch();

    public static native void free(long j);

    private static native void init();

    public static native long malloc(long j);

    static {
        if (JansiLoader.initialize()) {
            init();
        }
    }

    public static class SMALL_RECT {
        public static int SIZEOF;
        public short bottom;
        public short left;
        public short right;
        public short top;

        private static native void init();

        static {
            JansiLoader.initialize();
            init();
        }

        public short width() {
            return (short) (this.right - this.left);
        }

        public short height() {
            return (short) (this.bottom - this.top);
        }

        public SMALL_RECT copy() {
            SMALL_RECT small_rect = new SMALL_RECT();
            small_rect.left = this.left;
            small_rect.top = this.top;
            small_rect.right = this.right;
            small_rect.bottom = this.bottom;
            return small_rect;
        }
    }

    public static class COORD {
        public static int SIZEOF;
        public short x;
        public short y;

        private static native void init();

        static {
            JansiLoader.initialize();
            init();
        }

        public COORD copy() {
            COORD coord = new COORD();
            coord.x = this.x;
            coord.y = this.y;
            return coord;
        }
    }

    public static class CONSOLE_SCREEN_BUFFER_INFO {
        public static int SIZEOF;
        public short attributes;
        public COORD cursorPosition = new COORD();
        public COORD maximumWindowSize = new COORD();
        public COORD size = new COORD();
        public SMALL_RECT window = new SMALL_RECT();

        private static native void init();

        static {
            JansiLoader.initialize();
            init();
        }

        public int windowWidth() {
            return this.window.width() + 1;
        }

        public int windowHeight() {
            return this.window.height() + 1;
        }
    }

    public static class CHAR_INFO {
        public static int SIZEOF;
        public short attributes;
        public char unicodeChar;

        private static native void init();

        static {
            JansiLoader.initialize();
            init();
        }
    }

    public static class KEY_EVENT_RECORD {
        public static int CAPSLOCK_ON;
        public static int ENHANCED_KEY;
        public static int LEFT_ALT_PRESSED;
        public static int LEFT_CTRL_PRESSED;
        public static int NUMLOCK_ON;
        public static int RIGHT_ALT_PRESSED;
        public static int RIGHT_CTRL_PRESSED;
        public static int SCROLLLOCK_ON;
        public static int SHIFT_PRESSED;
        public static int SIZEOF;
        public int controlKeyState;
        public short keyCode;
        public boolean keyDown;
        public short repeatCount;
        public short scanCode;
        public char uchar;

        private static native void init();

        static {
            JansiLoader.initialize();
            init();
        }

        public String toString() {
            return "KEY_EVENT_RECORD{keyDown=" + this.keyDown + ", repeatCount=" + this.repeatCount + ", keyCode=" + this.keyCode + ", scanCode=" + this.scanCode + ", uchar=" + this.uchar + ", controlKeyState=" + this.controlKeyState + AbstractJsonLexerKt.END_OBJ;
        }
    }

    public static class MOUSE_EVENT_RECORD {
        public static int CAPSLOCK_ON;
        public static int DOUBLE_CLICK;
        public static int ENHANCED_KEY;
        public static int FROM_LEFT_1ST_BUTTON_PRESSED;
        public static int FROM_LEFT_2ND_BUTTON_PRESSED;
        public static int FROM_LEFT_3RD_BUTTON_PRESSED;
        public static int FROM_LEFT_4TH_BUTTON_PRESSED;
        public static int LEFT_ALT_PRESSED;
        public static int LEFT_CTRL_PRESSED;
        public static int MOUSE_HWHEELED;
        public static int MOUSE_MOVED;
        public static int MOUSE_WHEELED;
        public static int NUMLOCK_ON;
        public static int RIGHTMOST_BUTTON_PRESSED;
        public static int RIGHT_ALT_PRESSED;
        public static int RIGHT_CTRL_PRESSED;
        public static int SCROLLLOCK_ON;
        public static int SHIFT_PRESSED;
        public static int SIZEOF;
        public int buttonState;
        public int controlKeyState;
        public int eventFlags;
        public COORD mousePosition = new COORD();

        private static native void init();

        static {
            JansiLoader.initialize();
            init();
        }

        public String toString() {
            return "MOUSE_EVENT_RECORD{mousePosition=" + this.mousePosition + ", buttonState=" + this.buttonState + ", controlKeyState=" + this.controlKeyState + ", eventFlags=" + this.eventFlags + AbstractJsonLexerKt.END_OBJ;
        }
    }

    public static class WINDOW_BUFFER_SIZE_RECORD {
        public static int SIZEOF;
        public COORD size = new COORD();

        private static native void init();

        static {
            JansiLoader.initialize();
            init();
        }

        public String toString() {
            return "WINDOW_BUFFER_SIZE_RECORD{size=" + this.size + AbstractJsonLexerKt.END_OBJ;
        }
    }

    public static class FOCUS_EVENT_RECORD {
        public static int SIZEOF;
        public boolean setFocus;

        private static native void init();

        static {
            JansiLoader.initialize();
            init();
        }
    }

    public static class MENU_EVENT_RECORD {
        public static int SIZEOF;
        public int commandId;

        private static native void init();

        static {
            JansiLoader.initialize();
            init();
        }
    }

    public static class INPUT_RECORD {
        public static short FOCUS_EVENT;
        public static short KEY_EVENT;
        public static short MENU_EVENT;
        public static short MOUSE_EVENT;
        public static int SIZEOF;
        public static short WINDOW_BUFFER_SIZE_EVENT;
        public short eventType;
        public FOCUS_EVENT_RECORD focusEvent = new FOCUS_EVENT_RECORD();
        public KEY_EVENT_RECORD keyEvent = new KEY_EVENT_RECORD();
        public MENU_EVENT_RECORD menuEvent = new MENU_EVENT_RECORD();
        public MOUSE_EVENT_RECORD mouseEvent = new MOUSE_EVENT_RECORD();
        public WINDOW_BUFFER_SIZE_RECORD windowBufferSizeEvent = new WINDOW_BUFFER_SIZE_RECORD();

        private static native void init();

        public static native void memmove(INPUT_RECORD input_record, long j, long j2);

        static {
            JansiLoader.initialize();
            init();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x0080  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.fusesource.jansi.internal.Kernel32.INPUT_RECORD[] readConsoleInputHelper(long r12, int r14, boolean r15) throws java.io.IOException {
        /*
            java.lang.String r0 = "ReadConsoleInputW failed: "
            r1 = 1
            int[] r1 = new int[r1]
            r8 = 0
            int r2 = org.fusesource.jansi.internal.Kernel32.INPUT_RECORD.SIZEOF     // Catch:{ all -> 0x007a }
            int r2 = r2 * r14
            long r2 = (long) r2     // Catch:{ all -> 0x007a }
            long r10 = malloc(r2)     // Catch:{ all -> 0x007a }
            int r2 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r2 == 0) goto L_0x0070
            if (r15 == 0) goto L_0x001f
            r2 = r12
            r4 = r10
            r6 = r14
            r7 = r1
            int r12 = PeekConsoleInputW(r2, r4, r6, r7)     // Catch:{ all -> 0x0078 }
            goto L_0x0027
        L_0x001f:
            r2 = r12
            r4 = r10
            r6 = r14
            r7 = r1
            int r12 = ReadConsoleInputW(r2, r4, r6, r7)     // Catch:{ all -> 0x0078 }
        L_0x0027:
            if (r12 == 0) goto L_0x005a
            r12 = 0
            r13 = r1[r12]     // Catch:{ all -> 0x0078 }
            if (r13 > 0) goto L_0x0038
            org.fusesource.jansi.internal.Kernel32$INPUT_RECORD[] r12 = new org.fusesource.jansi.internal.Kernel32.INPUT_RECORD[r12]     // Catch:{ all -> 0x0078 }
            int r13 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r13 == 0) goto L_0x0037
            free(r10)
        L_0x0037:
            return r12
        L_0x0038:
            org.fusesource.jansi.internal.Kernel32$INPUT_RECORD[] r14 = new org.fusesource.jansi.internal.Kernel32.INPUT_RECORD[r13]     // Catch:{ all -> 0x0078 }
        L_0x003a:
            if (r12 >= r13) goto L_0x0052
            org.fusesource.jansi.internal.Kernel32$INPUT_RECORD r15 = new org.fusesource.jansi.internal.Kernel32$INPUT_RECORD     // Catch:{ all -> 0x0078 }
            r15.<init>()     // Catch:{ all -> 0x0078 }
            r14[r12] = r15     // Catch:{ all -> 0x0078 }
            int r0 = org.fusesource.jansi.internal.Kernel32.INPUT_RECORD.SIZEOF     // Catch:{ all -> 0x0078 }
            int r0 = r0 * r12
            long r0 = (long) r0     // Catch:{ all -> 0x0078 }
            long r0 = r0 + r10
            int r2 = org.fusesource.jansi.internal.Kernel32.INPUT_RECORD.SIZEOF     // Catch:{ all -> 0x0078 }
            long r2 = (long) r2     // Catch:{ all -> 0x0078 }
            org.fusesource.jansi.internal.Kernel32.INPUT_RECORD.memmove(r15, r0, r2)     // Catch:{ all -> 0x0078 }
            int r12 = r12 + 1
            goto L_0x003a
        L_0x0052:
            int r12 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r12 == 0) goto L_0x0059
            free(r10)
        L_0x0059:
            return r14
        L_0x005a:
            java.io.IOException r12 = new java.io.IOException     // Catch:{ all -> 0x0078 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x0078 }
            r13.<init>(r0)     // Catch:{ all -> 0x0078 }
            java.lang.String r14 = org.fusesource.jansi.WindowsSupport.getLastErrorMessage()     // Catch:{ all -> 0x0078 }
            r13.append(r14)     // Catch:{ all -> 0x0078 }
            java.lang.String r13 = r13.toString()     // Catch:{ all -> 0x0078 }
            r12.<init>(r13)     // Catch:{ all -> 0x0078 }
            throw r12     // Catch:{ all -> 0x0078 }
        L_0x0070:
            java.io.IOException r12 = new java.io.IOException     // Catch:{ all -> 0x0078 }
            java.lang.String r13 = "cannot allocate memory with JNI"
            r12.<init>(r13)     // Catch:{ all -> 0x0078 }
            throw r12     // Catch:{ all -> 0x0078 }
        L_0x0078:
            r12 = move-exception
            goto L_0x007c
        L_0x007a:
            r12 = move-exception
            r10 = r8
        L_0x007c:
            int r13 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r13 == 0) goto L_0x0083
            free(r10)
        L_0x0083:
            goto L_0x0085
        L_0x0084:
            throw r12
        L_0x0085:
            goto L_0x0084
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fusesource.jansi.internal.Kernel32.readConsoleInputHelper(long, int, boolean):org.fusesource.jansi.internal.Kernel32$INPUT_RECORD[]");
    }

    public static INPUT_RECORD[] readConsoleKeyInput(long j, int i, boolean z) throws IOException {
        INPUT_RECORD[] readConsoleInputHelper;
        int i2;
        int i3;
        do {
            readConsoleInputHelper = readConsoleInputHelper(j, i, z);
            i3 = 0;
            for (INPUT_RECORD input_record : readConsoleInputHelper) {
                if (input_record.eventType == INPUT_RECORD.KEY_EVENT) {
                    i3++;
                }
            }
        } while (i3 <= 0);
        INPUT_RECORD[] input_recordArr = new INPUT_RECORD[i3];
        int i4 = 0;
        for (INPUT_RECORD input_record2 : readConsoleInputHelper) {
            if (input_record2.eventType == INPUT_RECORD.KEY_EVENT) {
                input_recordArr[i4] = input_record2;
                i4++;
            }
        }
        return input_recordArr;
    }
}
