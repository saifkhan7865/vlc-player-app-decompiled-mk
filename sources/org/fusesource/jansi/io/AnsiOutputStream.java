package org.fusesource.jansi.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import org.fusesource.jansi.AnsiColors;
import org.fusesource.jansi.AnsiMode;
import org.fusesource.jansi.AnsiType;

public class AnsiOutputStream extends FilterOutputStream {
    private static final int BEL = 7;
    private static final int FIRST_ESC_CHAR = 27;
    private static final int LOOKING_FOR_CHARSET = 9;
    private static final int LOOKING_FOR_FIRST_ESC_CHAR = 0;
    private static final int LOOKING_FOR_INT_ARG_END = 4;
    private static final int LOOKING_FOR_NEXT_ARG = 2;
    private static final int LOOKING_FOR_OSC_COMMAND = 5;
    private static final int LOOKING_FOR_OSC_COMMAND_END = 6;
    private static final int LOOKING_FOR_OSC_PARAM = 7;
    private static final int LOOKING_FOR_SECOND_ESC_CHAR = 1;
    private static final int LOOKING_FOR_ST = 8;
    private static final int LOOKING_FOR_STR_ARG_END = 3;
    private static final int MAX_ESCAPE_SEQUENCE_LENGTH = 100;
    public static final byte[] RESET_CODE = "\u001b[0m".getBytes();
    private static final int SECOND_CHARSET0_CHAR = 40;
    private static final int SECOND_CHARSET1_CHAR = 41;
    private static final int SECOND_ESC_CHAR = 91;
    private static final int SECOND_OSC_CHAR = 93;
    private static final int SECOND_ST_CHAR = 92;
    private AnsiProcessor ap;
    private final byte[] buffer = new byte[100];
    private final AnsiColors colors;
    private final Charset cs;
    private final IoRunnable installer;
    private AnsiMode mode;
    private final ArrayList<Object> options = new ArrayList<>();
    private int pos = 0;
    private final AnsiProcessor processor;
    private boolean resetAtUninstall;
    private int startOfValue;
    private int state = 0;
    private final AnsiType type;
    private final IoRunnable uninstaller;
    private final WidthSupplier width;

    public interface IoRunnable {
        void run() throws IOException;
    }

    public interface WidthSupplier {
        int getTerminalWidth();
    }

    public static class ZeroWidthSupplier implements WidthSupplier {
        public int getTerminalWidth() {
            return 0;
        }
    }

    public AnsiOutputStream(OutputStream outputStream, WidthSupplier widthSupplier, AnsiMode ansiMode, AnsiProcessor ansiProcessor, AnsiType ansiType, AnsiColors ansiColors, Charset charset, IoRunnable ioRunnable, IoRunnable ioRunnable2, boolean z) {
        super(outputStream);
        this.width = widthSupplier;
        this.processor = ansiProcessor;
        this.type = ansiType;
        this.colors = ansiColors;
        this.installer = ioRunnable;
        this.uninstaller = ioRunnable2;
        this.resetAtUninstall = z;
        this.cs = charset;
        setMode(ansiMode);
    }

    public int getTerminalWidth() {
        return this.width.getTerminalWidth();
    }

    public AnsiType getType() {
        return this.type;
    }

    public AnsiColors getColors() {
        return this.colors;
    }

    public AnsiMode getMode() {
        return this.mode;
    }

    public void setMode(AnsiMode ansiMode) {
        AnsiProcessor ansiProcessor;
        if (ansiMode == AnsiMode.Strip) {
            ansiProcessor = new AnsiProcessor(this.out);
        } else if (ansiMode == AnsiMode.Force || (ansiProcessor = this.processor) == null) {
            ansiProcessor = new ColorsAnsiProcessor(this.out, this.colors);
        }
        this.ap = ansiProcessor;
        this.mode = ansiMode;
    }

    public boolean isResetAtUninstall() {
        return this.resetAtUninstall;
    }

    public void setResetAtUninstall(boolean z) {
        this.resetAtUninstall = z;
    }

    public void write(int i) throws IOException {
        switch (this.state) {
            case 0:
                if (i != 27) {
                    this.out.write(i);
                    break;
                } else {
                    byte[] bArr = this.buffer;
                    int i2 = this.pos;
                    this.pos = i2 + 1;
                    bArr[i2] = (byte) i;
                    this.state = 1;
                    break;
                }
            case 1:
                byte[] bArr2 = this.buffer;
                int i3 = this.pos;
                this.pos = i3 + 1;
                bArr2[i3] = (byte) i;
                if (i != 91) {
                    if (i != 93) {
                        if (i != 40) {
                            if (i != 41) {
                                reset(false);
                                break;
                            } else {
                                this.options.add(1);
                                this.state = 9;
                                break;
                            }
                        } else {
                            this.options.add(0);
                            this.state = 9;
                            break;
                        }
                    } else {
                        this.state = 5;
                        break;
                    }
                } else {
                    this.state = 2;
                    break;
                }
            case 2:
                byte[] bArr3 = this.buffer;
                int i4 = this.pos;
                this.pos = i4 + 1;
                bArr3[i4] = (byte) i;
                if (34 != i) {
                    if (48 > i || i > 57) {
                        if (59 != i) {
                            if (63 != i) {
                                if (61 != i) {
                                    processEscapeCommand(i);
                                    break;
                                } else {
                                    this.options.add('=');
                                    break;
                                }
                            } else {
                                this.options.add('?');
                                break;
                            }
                        } else {
                            this.options.add((Object) null);
                            break;
                        }
                    } else {
                        this.startOfValue = i4;
                        this.state = 4;
                        break;
                    }
                } else {
                    this.startOfValue = i4;
                    this.state = 3;
                    break;
                }
            case 3:
                byte[] bArr4 = this.buffer;
                int i5 = this.pos;
                this.pos = i5 + 1;
                bArr4[i5] = (byte) i;
                if (34 != i) {
                    int i6 = this.startOfValue;
                    this.options.add(new String(bArr4, i6, i5 - i6, this.cs));
                    if (i != 59) {
                        processEscapeCommand(i);
                        break;
                    } else {
                        this.state = 2;
                        break;
                    }
                }
                break;
            case 4:
                byte[] bArr5 = this.buffer;
                int i7 = this.pos;
                this.pos = i7 + 1;
                bArr5[i7] = (byte) i;
                if (48 > i || i > 57) {
                    int i8 = this.startOfValue;
                    this.options.add(Integer.valueOf(new String(bArr5, i8, i7 - i8)));
                    if (i != 59) {
                        processEscapeCommand(i);
                        break;
                    } else {
                        this.state = 2;
                        break;
                    }
                }
            case 5:
                byte[] bArr6 = this.buffer;
                int i9 = this.pos;
                this.pos = i9 + 1;
                bArr6[i9] = (byte) i;
                if (48 <= i && i <= 57) {
                    this.startOfValue = i9;
                    this.state = 6;
                    break;
                } else {
                    reset(false);
                    break;
                }
            case 6:
                byte[] bArr7 = this.buffer;
                int i10 = this.pos;
                this.pos = i10 + 1;
                bArr7[i10] = (byte) i;
                if (59 != i) {
                    if (48 > i || i > 57) {
                        reset(false);
                        break;
                    }
                } else {
                    int i11 = this.startOfValue;
                    this.options.add(Integer.valueOf(new String(bArr7, i11, i10 - i11)));
                    this.startOfValue = this.pos;
                    this.state = 7;
                    break;
                }
            case 7:
                byte[] bArr8 = this.buffer;
                int i12 = this.pos;
                this.pos = i12 + 1;
                bArr8[i12] = (byte) i;
                if (7 != i) {
                    if (27 == i) {
                        this.state = 8;
                        break;
                    }
                } else {
                    int i13 = this.startOfValue;
                    this.options.add(new String(bArr8, i13, i12 - i13, this.cs));
                    processOperatingSystemCommand();
                    break;
                }
                break;
            case 8:
                byte[] bArr9 = this.buffer;
                int i14 = this.pos;
                this.pos = i14 + 1;
                bArr9[i14] = (byte) i;
                if (92 != i) {
                    this.state = 7;
                    break;
                } else {
                    int i15 = this.startOfValue;
                    this.options.add(new String(bArr9, i15, (i14 - 1) - i15, this.cs));
                    processOperatingSystemCommand();
                    break;
                }
            case 9:
                this.options.add(Character.valueOf((char) i));
                processCharsetSelect();
                break;
        }
        if (this.pos >= this.buffer.length) {
            reset(false);
        }
    }

    private void processCharsetSelect() throws IOException {
        try {
            AnsiProcessor ansiProcessor = this.ap;
            reset(ansiProcessor != null && ansiProcessor.processCharsetSelect(this.options));
        } catch (RuntimeException e) {
            reset(true);
            throw e;
        }
    }

    private void processOperatingSystemCommand() throws IOException {
        try {
            AnsiProcessor ansiProcessor = this.ap;
            reset(ansiProcessor != null && ansiProcessor.processOperatingSystemCommand(this.options));
        } catch (RuntimeException e) {
            reset(true);
            throw e;
        }
    }

    private void processEscapeCommand(int i) throws IOException {
        try {
            AnsiProcessor ansiProcessor = this.ap;
            reset(ansiProcessor != null && ansiProcessor.processEscapeCommand(this.options, i));
        } catch (RuntimeException e) {
            reset(true);
            throw e;
        }
    }

    private void reset(boolean z) throws IOException {
        if (!z) {
            this.out.write(this.buffer, 0, this.pos);
        }
        this.pos = 0;
        this.startOfValue = 0;
        this.options.clear();
        this.state = 0;
    }

    public void install() throws IOException {
        IoRunnable ioRunnable = this.installer;
        if (ioRunnable != null) {
            ioRunnable.run();
        }
    }

    public void uninstall() throws IOException {
        if (!(!this.resetAtUninstall || this.type == AnsiType.Redirected || this.type == AnsiType.Unsupported)) {
            setMode(AnsiMode.Default);
            write(RESET_CODE);
            flush();
        }
        IoRunnable ioRunnable = this.uninstaller;
        if (ioRunnable != null) {
            ioRunnable.run();
        }
    }

    public void close() throws IOException {
        uninstall();
        super.close();
    }
}
