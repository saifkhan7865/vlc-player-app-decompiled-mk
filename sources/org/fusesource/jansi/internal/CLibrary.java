package org.fusesource.jansi.internal;

public class CLibrary {
    public static boolean HAVE_ISATTY;
    public static boolean HAVE_TTYNAME;
    public static final boolean LOADED;
    public static int STDERR_FILENO = 2;
    public static int STDOUT_FILENO = 1;
    public static int TCSADRAIN;
    public static int TCSAFLUSH;
    public static int TCSANOW;
    public static long TIOCGETA;
    public static long TIOCGETD;
    public static long TIOCGWINSZ;
    public static long TIOCSETA;
    public static long TIOCSETD;
    public static long TIOCSWINSZ;

    private static native void init();

    public static native int ioctl(int i, long j, WinSize winSize);

    public static native int ioctl(int i, long j, int[] iArr);

    public static native int isatty(int i);

    public static native int openpty(int[] iArr, int[] iArr2, byte[] bArr, Termios termios, WinSize winSize);

    public static native int tcgetattr(int i, Termios termios);

    public static native int tcsetattr(int i, int i2, Termios termios);

    public static native String ttyname(int i);

    static {
        boolean initialize = JansiLoader.initialize();
        LOADED = initialize;
        if (initialize) {
            init();
        }
    }

    public static class WinSize {
        public static int SIZEOF;
        public short ws_col;
        public short ws_row;
        public short ws_xpixel;
        public short ws_ypixel;

        private static native void init();

        static {
            JansiLoader.initialize();
            init();
        }

        public WinSize() {
        }

        public WinSize(short s, short s2) {
            this.ws_row = s;
            this.ws_col = s2;
        }
    }

    public static class Termios {
        public static int SIZEOF;
        public byte[] c_cc = new byte[32];
        public long c_cflag;
        public long c_iflag;
        public long c_ispeed;
        public long c_lflag;
        public long c_oflag;
        public long c_ospeed;

        private static native void init();

        static {
            JansiLoader.initialize();
            init();
        }
    }
}
