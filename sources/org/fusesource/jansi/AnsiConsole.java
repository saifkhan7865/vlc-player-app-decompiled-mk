package org.fusesource.jansi;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Locale;
import org.fusesource.jansi.internal.CLibrary;
import org.fusesource.jansi.internal.Kernel32;
import org.fusesource.jansi.io.AnsiOutputStream;
import org.fusesource.jansi.io.AnsiProcessor;
import org.fusesource.jansi.io.FastBufferedOutputStream;
import org.fusesource.jansi.io.WindowsAnsiProcessor;

public class AnsiConsole {
    static final int ENABLE_VIRTUAL_TERMINAL_PROCESSING = 4;
    static final boolean IS_CONEMU;
    static final boolean IS_CYGWIN;
    static final boolean IS_MSYSTEM;
    static final boolean IS_WINDOWS;
    public static final String JANSI_COLORS = "jansi.colors";
    public static final String JANSI_COLORS_16 = "16";
    public static final String JANSI_COLORS_256 = "256";
    public static final String JANSI_COLORS_TRUECOLOR = "truecolor";
    @Deprecated
    public static final String JANSI_EAGER = "jansi.eager";
    public static final String JANSI_ERR_COLORS = "jansi.err.colors";
    public static final String JANSI_ERR_MODE = "jansi.err.mode";
    @Deprecated
    public static final String JANSI_FORCE = "jansi.force";
    public static final String JANSI_GRACEFUL = "jansi.graceful";
    public static final String JANSI_MODE = "jansi.mode";
    public static final String JANSI_MODE_DEFAULT = "default";
    public static final String JANSI_MODE_FORCE = "force";
    public static final String JANSI_MODE_STRIP = "strip";
    public static final String JANSI_NORESET = "jansi.noreset";
    public static final String JANSI_OUT_COLORS = "jansi.out.colors";
    public static final String JANSI_OUT_MODE = "jansi.out.mode";
    @Deprecated
    public static final String JANSI_PASSTHROUGH = "jansi.passthrough";
    @Deprecated
    public static final String JANSI_STRIP = "jansi.strip";
    static int STDERR_FILENO = 2;
    static int STDOUT_FILENO = 1;
    @Deprecated
    public static PrintStream err;
    private static boolean initialized;
    private static int installed;
    @Deprecated
    public static PrintStream out;
    @Deprecated
    public static PrintStream system_err = System.err;
    @Deprecated
    public static PrintStream system_out = System.out;
    private static int virtualProcessing;

    static /* synthetic */ int access$006() {
        int i = virtualProcessing - 1;
        virtualProcessing = i;
        return i;
    }

    static /* synthetic */ int access$008() {
        int i = virtualProcessing;
        virtualProcessing = i + 1;
        return i;
    }

    static {
        boolean contains = System.getProperty("os.name").toLowerCase(Locale.ENGLISH).contains("win");
        IS_WINDOWS = contains;
        boolean z = false;
        IS_CYGWIN = contains && System.getenv("PWD") != null && System.getenv("PWD").startsWith("/");
        IS_MSYSTEM = contains && System.getenv("MSYSTEM") != null && (System.getenv("MSYSTEM").startsWith("MINGW") || System.getenv("MSYSTEM").equals("MSYS"));
        if (contains && System.getenv("ConEmuPID") != null) {
            z = true;
        }
        IS_CONEMU = z;
        if (getBoolean(JANSI_EAGER)) {
            initStreams();
        }
    }

    public static int getTerminalWidth() {
        int terminalWidth = out().getTerminalWidth();
        return terminalWidth <= 0 ? err().getTerminalWidth() : terminalWidth;
    }

    private AnsiConsole() {
    }

    private static AnsiPrintStream ansiStream(boolean z) {
        boolean z2;
        boolean z3;
        AnonymousClass2 r11;
        AnonymousClass1 r10;
        AnsiType ansiType;
        AnsiOutputStream.WidthSupplier widthSupplier;
        AnsiMode ansiMode;
        AnsiColors ansiColors;
        AnonymousClass2 r12;
        AnonymousClass1 r112;
        AnsiType ansiType2;
        AnsiProcessor ansiProcessor;
        FastBufferedOutputStream fastBufferedOutputStream = new FastBufferedOutputStream(new FileOutputStream(z ? FileDescriptor.out : FileDescriptor.err));
        String property = System.getProperty(z ? "sun.stdout.encoding" : "sun.stderr.encoding");
        final int i = z ? STDOUT_FILENO : STDERR_FILENO;
        try {
            z3 = CLibrary.isatty(i) != 0;
            String str = System.getenv("TERM");
            String str2 = System.getenv("INSIDE_EMACS");
            if (z3 && "dumb".equals(str) && str2 != null && !str2.contains("comint")) {
                z3 = false;
            }
            z2 = false;
        } catch (Throwable unused) {
            z3 = false;
            z2 = true;
        }
        AnsiProcessor ansiProcessor2 = null;
        if (!z3) {
            AnsiType ansiType3 = z2 ? AnsiType.Unsupported : AnsiType.Redirected;
            widthSupplier = new AnsiOutputStream.ZeroWidthSupplier();
            ansiType = ansiType3;
            r10 = null;
            r11 = null;
        } else if (IS_WINDOWS) {
            final long GetStdHandle = Kernel32.GetStdHandle(z ? Kernel32.STD_OUTPUT_HANDLE : Kernel32.STD_ERROR_HANDLE);
            final int[] iArr = new int[1];
            boolean z4 = Kernel32.GetConsoleMode(GetStdHandle, iArr) != 0;
            if (z4 && Kernel32.SetConsoleMode(GetStdHandle, iArr[0] | 4) != 0) {
                Kernel32.SetConsoleMode(GetStdHandle, iArr[0]);
                ansiType2 = AnsiType.VirtualTerminal;
                r112 = new AnsiOutputStream.IoRunnable() {
                    public void run() throws IOException {
                        AnsiConsole.access$008();
                        Kernel32.SetConsoleMode(GetStdHandle, iArr[0] | 4);
                    }
                };
                r12 = new AnsiOutputStream.IoRunnable() {
                    public void run() throws IOException {
                        if (AnsiConsole.access$006() == 0) {
                            Kernel32.SetConsoleMode(GetStdHandle, iArr[0]);
                        }
                    }
                };
            } else if ((IS_CONEMU || IS_CYGWIN || IS_MSYSTEM) && !z4) {
                ansiType2 = AnsiType.Native;
                r112 = null;
                r12 = null;
            } else {
                try {
                    ansiProcessor = new WindowsAnsiProcessor((OutputStream) fastBufferedOutputStream, GetStdHandle);
                    ansiType2 = AnsiType.Emulation;
                } catch (Throwable unused2) {
                    ansiProcessor = new AnsiProcessor(fastBufferedOutputStream);
                    ansiType2 = AnsiType.Unsupported;
                }
                r112 = null;
                r12 = null;
                ansiProcessor2 = ansiProcessor;
            }
            AnonymousClass3 r2 = new AnsiOutputStream.WidthSupplier() {
                public int getTerminalWidth() {
                    Kernel32.CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info = new Kernel32.CONSOLE_SCREEN_BUFFER_INFO();
                    Kernel32.GetConsoleScreenBufferInfo(GetStdHandle, console_screen_buffer_info);
                    return console_screen_buffer_info.windowWidth();
                }
            };
            ansiType = ansiType2;
            r10 = r112;
            r11 = r12;
            widthSupplier = r2;
        } else {
            r10 = null;
            r11 = null;
            ansiType = AnsiType.Native;
            widthSupplier = new AnsiOutputStream.WidthSupplier() {
                public int getTerminalWidth() {
                    CLibrary.WinSize winSize = new CLibrary.WinSize();
                    CLibrary.ioctl(i, CLibrary.TIOCGWINSZ, winSize);
                    return winSize.ws_col;
                }
            };
        }
        String property2 = System.getProperty(z ? JANSI_OUT_MODE : JANSI_ERR_MODE, System.getProperty(JANSI_MODE));
        if (JANSI_MODE_FORCE.equals(property2)) {
            ansiMode = AnsiMode.Force;
        } else if (JANSI_MODE_STRIP.equals(property2)) {
            ansiMode = AnsiMode.Strip;
        } else if (property2 != null) {
            ansiMode = z3 ? AnsiMode.Default : AnsiMode.Strip;
        } else if (getBoolean(JANSI_PASSTHROUGH)) {
            ansiMode = AnsiMode.Force;
        } else if (getBoolean(JANSI_STRIP)) {
            ansiMode = AnsiMode.Strip;
        } else if (getBoolean(JANSI_FORCE)) {
            ansiMode = AnsiMode.Force;
        } else {
            ansiMode = z3 ? AnsiMode.Default : AnsiMode.Strip;
        }
        AnsiMode ansiMode2 = ansiMode;
        String property3 = System.getProperty(z ? JANSI_OUT_COLORS : JANSI_ERR_COLORS, System.getProperty(JANSI_COLORS));
        if (JANSI_COLORS_TRUECOLOR.equals(property3)) {
            ansiColors = AnsiColors.TrueColor;
        } else if (JANSI_COLORS_256.equals(property3)) {
            ansiColors = AnsiColors.Colors256;
        } else if (property3 != null) {
            ansiColors = AnsiColors.Colors16;
        } else {
            String str3 = System.getenv("COLORTERM");
            if (str3 == null || (!str3.contains(JANSI_COLORS_TRUECOLOR) && !str3.contains("24bit"))) {
                String str4 = System.getenv("TERM");
                if (str4 != null && str4.contains("-direct")) {
                    ansiColors = AnsiColors.TrueColor;
                } else if (str4 == null || !str4.contains("-256color")) {
                    ansiColors = AnsiColors.Colors16;
                } else {
                    ansiColors = AnsiColors.Colors256;
                }
            } else {
                ansiColors = AnsiColors.TrueColor;
            }
        }
        boolean z5 = ansiType != AnsiType.Unsupported && !getBoolean(JANSI_NORESET);
        Charset defaultCharset = Charset.defaultCharset();
        if (property != null) {
            try {
                defaultCharset = Charset.forName(property);
            } catch (UnsupportedCharsetException unused3) {
            }
        }
        return newPrintStream(new AnsiOutputStream(fastBufferedOutputStream, widthSupplier, ansiMode2, ansiProcessor2, ansiType, ansiColors, defaultCharset, r10, r11, z5), defaultCharset.name());
    }

    private static AnsiPrintStream newPrintStream(AnsiOutputStream ansiOutputStream, String str) {
        if (str != null) {
            try {
                return new AnsiPrintStream(ansiOutputStream, true, str);
            } catch (UnsupportedEncodingException unused) {
            }
        }
        return new AnsiPrintStream(ansiOutputStream, true);
    }

    static boolean getBoolean(String str) {
        try {
            String property = System.getProperty(str);
            if (property.isEmpty() || Boolean.parseBoolean(property)) {
                return true;
            }
            return false;
        } catch (IllegalArgumentException | NullPointerException unused) {
            return false;
        }
    }

    public static AnsiPrintStream out() {
        initStreams();
        return (AnsiPrintStream) out;
    }

    public static PrintStream sysOut() {
        return system_out;
    }

    public static AnsiPrintStream err() {
        initStreams();
        return (AnsiPrintStream) err;
    }

    public static PrintStream sysErr() {
        return system_err;
    }

    public static synchronized void systemInstall() {
        synchronized (AnsiConsole.class) {
            int i = installed + 1;
            installed = i;
            if (i == 1) {
                initStreams();
                try {
                    ((AnsiPrintStream) out).install();
                    ((AnsiPrintStream) err).install();
                    System.setOut(out);
                    System.setErr(err);
                } catch (IOException e) {
                    throw new IOError(e);
                }
            }
        }
    }

    public static synchronized boolean isInstalled() {
        boolean z;
        synchronized (AnsiConsole.class) {
            z = installed > 0;
        }
        return z;
    }

    public static synchronized void systemUninstall() {
        synchronized (AnsiConsole.class) {
            int i = installed - 1;
            installed = i;
            if (i == 0) {
                try {
                    ((AnsiPrintStream) out).uninstall();
                    ((AnsiPrintStream) err).uninstall();
                    initialized = false;
                    System.setOut(system_out);
                    System.setErr(system_err);
                } catch (IOException e) {
                    throw new IOError(e);
                }
            }
        }
    }

    static synchronized void initStreams() {
        synchronized (AnsiConsole.class) {
            if (!initialized) {
                out = ansiStream(true);
                err = ansiStream(false);
                initialized = true;
            }
        }
    }
}
