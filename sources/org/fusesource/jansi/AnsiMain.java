package org.fusesource.jansi;

import androidx.core.app.NotificationCompat;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Properties;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.internal.CLibrary;

public class AnsiMain {
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x00a2, code lost:
        if (r0 == null) goto L_0x00a8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x00a4, code lost:
        java.lang.System.setProperty(org.fusesource.jansi.AnsiConsole.JANSI_GRACEFUL, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x00a8, code lost:
        java.lang.System.clearProperty(org.fusesource.jansi.AnsiConsole.JANSI_GRACEFUL);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0099, code lost:
        if (r0 != null) goto L_0x00a4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void main(java.lang.String... r10) throws java.io.IOException {
        /*
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Jansi "
            r1.<init>(r2)
            java.lang.String r2 = getJansiVersion()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.println(r1)
            java.io.PrintStream r0 = java.lang.System.out
            r0.println()
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "library.jansi.path= "
            r1.<init>(r2)
            java.lang.String r2 = "library.jansi.path"
            java.lang.String r3 = ""
            java.lang.String r2 = java.lang.System.getProperty(r2, r3)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.println(r1)
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "library.jansi.version= "
            r1.<init>(r2)
            java.lang.String r2 = "library.jansi.version"
            java.lang.String r2 = java.lang.System.getProperty(r2, r3)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.println(r1)
            boolean r0 = org.fusesource.jansi.internal.JansiLoader.initialize()
            java.lang.String r1 = "jansi.graceful"
            if (r0 == 0) goto L_0x008d
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r4 = "Jansi native library loaded from "
            r2.<init>(r4)
            java.lang.String r4 = org.fusesource.jansi.internal.JansiLoader.getNativeLibraryPath()
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r0.println(r2)
            java.lang.String r0 = org.fusesource.jansi.internal.JansiLoader.getNativeLibrarySourceUrl()
            if (r0 == 0) goto L_0x00ab
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r4 = "   which was auto-extracted from "
            r2.<init>(r4)
            java.lang.String r4 = org.fusesource.jansi.internal.JansiLoader.getNativeLibrarySourceUrl()
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r0.println(r2)
            goto L_0x00ab
        L_0x008d:
            java.lang.String r0 = java.lang.System.getProperty(r1)
            java.lang.String r2 = "false"
            java.lang.System.setProperty(r1, r2)     // Catch:{ all -> 0x009c }
            org.fusesource.jansi.internal.JansiLoader.initialize()     // Catch:{ all -> 0x009c }
            if (r0 == 0) goto L_0x00a8
            goto L_0x00a4
        L_0x009c:
            r2 = move-exception
            java.io.PrintStream r4 = java.lang.System.out     // Catch:{ all -> 0x0489 }
            r2.printStackTrace(r4)     // Catch:{ all -> 0x0489 }
            if (r0 == 0) goto L_0x00a8
        L_0x00a4:
            java.lang.System.setProperty(r1, r0)
            goto L_0x00ab
        L_0x00a8:
            java.lang.System.clearProperty(r1)
        L_0x00ab:
            java.io.PrintStream r0 = java.lang.System.out
            r0.println()
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r4 = "os.name= "
            r2.<init>(r4)
            java.lang.String r4 = "os.name"
            java.lang.String r4 = java.lang.System.getProperty(r4)
            r2.append(r4)
            java.lang.String r4 = ", os.version= "
            r2.append(r4)
            java.lang.String r4 = "os.version"
            java.lang.String r4 = java.lang.System.getProperty(r4)
            r2.append(r4)
            java.lang.String r4 = ", os.arch= "
            r2.append(r4)
            java.lang.String r4 = "os.arch"
            java.lang.String r4 = java.lang.System.getProperty(r4)
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r0.println(r2)
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r4 = "file.encoding= "
            r2.<init>(r4)
            java.lang.String r4 = "file.encoding"
            java.lang.String r4 = java.lang.System.getProperty(r4)
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r0.println(r2)
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r4 = "java.version= "
            r2.<init>(r4)
            java.lang.String r4 = "java.version"
            java.lang.String r4 = java.lang.System.getProperty(r4)
            r2.append(r4)
            java.lang.String r4 = ", java.vendor= "
            r2.append(r4)
            java.lang.String r4 = "java.vendor"
            java.lang.String r4 = java.lang.System.getProperty(r4)
            r2.append(r4)
            java.lang.String r4 = ", java.home= "
            r2.append(r4)
            java.lang.String r4 = "java.home"
            java.lang.String r4 = java.lang.System.getProperty(r4)
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r0.println(r2)
            java.io.PrintStream r0 = java.lang.System.out
            r0.println()
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r4 = "jansi.graceful= "
            r2.<init>(r4)
            java.lang.String r1 = java.lang.System.getProperty(r1, r3)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            r0.println(r1)
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "jansi.mode= "
            r1.<init>(r2)
            java.lang.String r2 = "jansi.mode"
            java.lang.String r2 = java.lang.System.getProperty(r2, r3)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.println(r1)
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "jansi.out.mode= "
            r1.<init>(r2)
            java.lang.String r2 = "jansi.out.mode"
            java.lang.String r2 = java.lang.System.getProperty(r2, r3)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.println(r1)
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "jansi.err.mode= "
            r1.<init>(r2)
            java.lang.String r2 = "jansi.err.mode"
            java.lang.String r2 = java.lang.System.getProperty(r2, r3)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.println(r1)
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "jansi.colors= "
            r1.<init>(r2)
            java.lang.String r2 = "jansi.colors"
            java.lang.String r2 = java.lang.System.getProperty(r2, r3)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.println(r1)
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "jansi.out.colors= "
            r1.<init>(r2)
            java.lang.String r2 = "jansi.out.colors"
            java.lang.String r2 = java.lang.System.getProperty(r2, r3)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.println(r1)
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "jansi.err.colors= "
            r1.<init>(r2)
            java.lang.String r2 = "jansi.err.colors"
            java.lang.String r2 = java.lang.System.getProperty(r2, r3)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.println(r1)
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "jansi.passthrough= "
            r1.<init>(r2)
            java.lang.String r2 = "jansi.passthrough"
            boolean r2 = org.fusesource.jansi.AnsiConsole.getBoolean(r2)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.println(r1)
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "jansi.strip= "
            r1.<init>(r2)
            java.lang.String r2 = "jansi.strip"
            boolean r2 = org.fusesource.jansi.AnsiConsole.getBoolean(r2)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.println(r1)
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "jansi.force= "
            r1.<init>(r2)
            java.lang.String r2 = "jansi.force"
            boolean r2 = org.fusesource.jansi.AnsiConsole.getBoolean(r2)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.println(r1)
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "jansi.noreset= "
            r1.<init>(r2)
            java.lang.String r2 = "jansi.noreset"
            boolean r2 = org.fusesource.jansi.AnsiConsole.getBoolean(r2)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.println(r1)
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = org.fusesource.jansi.Ansi.DISABLE
            r1.append(r2)
            java.lang.String r2 = "= "
            r1.append(r2)
            java.lang.String r2 = org.fusesource.jansi.Ansi.DISABLE
            boolean r2 = org.fusesource.jansi.AnsiConsole.getBoolean(r2)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.println(r1)
            java.io.PrintStream r0 = java.lang.System.out
            r0.println()
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "IS_WINDOWS: "
            r1.<init>(r2)
            boolean r2 = org.fusesource.jansi.AnsiConsole.IS_WINDOWS
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.println(r1)
            boolean r0 = org.fusesource.jansi.AnsiConsole.IS_WINDOWS
            if (r0 == 0) goto L_0x02c7
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "IS_CONEMU: "
            r1.<init>(r2)
            boolean r2 = org.fusesource.jansi.AnsiConsole.IS_CONEMU
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.println(r1)
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "IS_CYGWIN: "
            r1.<init>(r2)
            boolean r2 = org.fusesource.jansi.AnsiConsole.IS_CYGWIN
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.println(r1)
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "IS_MSYSTEM: "
            r1.<init>(r2)
            boolean r2 = org.fusesource.jansi.AnsiConsole.IS_MSYSTEM
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.println(r1)
        L_0x02c7:
            java.io.PrintStream r0 = java.lang.System.out
            r0.println()
            r0 = 0
            diagnoseTty(r0)
            r1 = 1
            diagnoseTty(r1)
            org.fusesource.jansi.AnsiConsole.systemInstall()
            java.io.PrintStream r2 = java.lang.System.out
            r2.println()
            java.io.PrintStream r2 = java.lang.System.out
            java.lang.String r3 = "Resulting Jansi modes for stout/stderr streams:"
            r2.println(r3)
            java.io.PrintStream r2 = java.lang.System.out
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "  - System.out: "
            r3.<init>(r4)
            org.fusesource.jansi.AnsiPrintStream r4 = org.fusesource.jansi.AnsiConsole.out()
            java.lang.String r4 = r4.toString()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r2.println(r3)
            java.io.PrintStream r2 = java.lang.System.out
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "  - System.err: "
            r3.<init>(r4)
            org.fusesource.jansi.AnsiPrintStream r4 = org.fusesource.jansi.AnsiConsole.err()
            java.lang.String r4 = r4.toString()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r2.println(r3)
            java.io.PrintStream r2 = java.lang.System.out
            java.lang.String r3 = "Processor types description:"
            r2.println(r3)
            org.fusesource.jansi.AnsiType[] r2 = org.fusesource.jansi.AnsiType.values()
            int r3 = r2.length
            r4 = 0
        L_0x0326:
            java.lang.String r5 = "  - "
            java.lang.String r6 = ": "
            if (r4 >= r3) goto L_0x034c
            r7 = r2[r4]
            java.io.PrintStream r8 = java.lang.System.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>(r5)
            r9.append(r7)
            r9.append(r6)
            java.lang.String r5 = r7.getDescription()
            r9.append(r5)
            java.lang.String r5 = r9.toString()
            r8.println(r5)
            int r4 = r4 + 1
            goto L_0x0326
        L_0x034c:
            java.io.PrintStream r2 = java.lang.System.out
            java.lang.String r3 = "Colors support description:"
            r2.println(r3)
            org.fusesource.jansi.AnsiColors[] r2 = org.fusesource.jansi.AnsiColors.values()
            int r3 = r2.length
            r4 = 0
        L_0x0359:
            if (r4 >= r3) goto L_0x037b
            r7 = r2[r4]
            java.io.PrintStream r8 = java.lang.System.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>(r5)
            r9.append(r7)
            r9.append(r6)
            java.lang.String r7 = r7.getDescription()
            r9.append(r7)
            java.lang.String r7 = r9.toString()
            r8.println(r7)
            int r4 = r4 + 1
            goto L_0x0359
        L_0x037b:
            java.io.PrintStream r2 = java.lang.System.out
            java.lang.String r3 = "Modes description:"
            r2.println(r3)
            org.fusesource.jansi.AnsiMode[] r2 = org.fusesource.jansi.AnsiMode.values()
            int r3 = r2.length
            r4 = 0
        L_0x0388:
            if (r4 >= r3) goto L_0x03aa
            r7 = r2[r4]
            java.io.PrintStream r8 = java.lang.System.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>(r5)
            r9.append(r7)
            r9.append(r6)
            java.lang.String r7 = r7.getDescription()
            r9.append(r7)
            java.lang.String r7 = r9.toString()
            r8.println(r7)
            int r4 = r4 + 1
            goto L_0x0388
        L_0x03aa:
            java.io.PrintStream r2 = java.lang.System.out     // Catch:{ all -> 0x0484 }
            r2.println()     // Catch:{ all -> 0x0484 }
            testAnsi(r0)     // Catch:{ all -> 0x0484 }
            testAnsi(r1)     // Catch:{ all -> 0x0484 }
            int r2 = r10.length     // Catch:{ all -> 0x0484 }
            if (r2 != 0) goto L_0x03bf
            printJansiLogoDemo()     // Catch:{ all -> 0x0484 }
            org.fusesource.jansi.AnsiConsole.systemUninstall()
            return
        L_0x03bf:
            java.io.PrintStream r2 = java.lang.System.out     // Catch:{ all -> 0x0484 }
            r2.println()     // Catch:{ all -> 0x0484 }
            int r2 = r10.length     // Catch:{ all -> 0x0484 }
            if (r2 != r1) goto L_0x0408
            java.io.File r2 = new java.io.File     // Catch:{ all -> 0x0484 }
            r3 = r10[r0]     // Catch:{ all -> 0x0484 }
            r2.<init>(r3)     // Catch:{ all -> 0x0484 }
            boolean r3 = r2.exists()     // Catch:{ all -> 0x0484 }
            if (r3 == 0) goto L_0x0408
            java.io.PrintStream r1 = java.lang.System.out     // Catch:{ all -> 0x0484 }
            org.fusesource.jansi.Ansi r3 = org.fusesource.jansi.Ansi.ansi()     // Catch:{ all -> 0x0484 }
            org.fusesource.jansi.Ansi r3 = r3.bold()     // Catch:{ all -> 0x0484 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0484 }
            r4.<init>()     // Catch:{ all -> 0x0484 }
            java.lang.String r5 = "\""
            r4.append(r5)     // Catch:{ all -> 0x0484 }
            r10 = r10[r0]     // Catch:{ all -> 0x0484 }
            r4.append(r10)     // Catch:{ all -> 0x0484 }
            java.lang.String r10 = "\" content:"
            r4.append(r10)     // Catch:{ all -> 0x0484 }
            java.lang.String r10 = r4.toString()     // Catch:{ all -> 0x0484 }
            org.fusesource.jansi.Ansi r10 = r3.a((java.lang.String) r10)     // Catch:{ all -> 0x0484 }
            org.fusesource.jansi.Ansi r10 = r10.reset()     // Catch:{ all -> 0x0484 }
            r1.println(r10)     // Catch:{ all -> 0x0484 }
            writeFileContent(r2)     // Catch:{ all -> 0x0484 }
            org.fusesource.jansi.AnsiConsole.systemUninstall()
            return
        L_0x0408:
            java.io.PrintStream r2 = java.lang.System.out     // Catch:{ all -> 0x0484 }
            org.fusesource.jansi.Ansi r3 = org.fusesource.jansi.Ansi.ansi()     // Catch:{ all -> 0x0484 }
            org.fusesource.jansi.Ansi r3 = r3.bold()     // Catch:{ all -> 0x0484 }
            java.lang.String r4 = "original args:"
            org.fusesource.jansi.Ansi r3 = r3.a((java.lang.String) r4)     // Catch:{ all -> 0x0484 }
            org.fusesource.jansi.Ansi r3 = r3.reset()     // Catch:{ all -> 0x0484 }
            r2.println(r3)     // Catch:{ all -> 0x0484 }
            int r2 = r10.length     // Catch:{ all -> 0x0484 }
            r3 = 0
            r4 = 1
        L_0x0422:
            if (r3 >= r2) goto L_0x0445
            r5 = r10[r3]     // Catch:{ all -> 0x0484 }
            java.io.PrintStream r7 = org.fusesource.jansi.AnsiConsole.system_out     // Catch:{ all -> 0x0484 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x0484 }
            r8.<init>()     // Catch:{ all -> 0x0484 }
            int r9 = r4 + 1
            r8.append(r4)     // Catch:{ all -> 0x0484 }
            r8.append(r6)     // Catch:{ all -> 0x0484 }
            java.lang.String r4 = r8.toString()     // Catch:{ all -> 0x0484 }
            r7.print(r4)     // Catch:{ all -> 0x0484 }
            java.io.PrintStream r4 = org.fusesource.jansi.AnsiConsole.system_out     // Catch:{ all -> 0x0484 }
            r4.println(r5)     // Catch:{ all -> 0x0484 }
            int r3 = r3 + 1
            r4 = r9
            goto L_0x0422
        L_0x0445:
            java.io.PrintStream r2 = java.lang.System.out     // Catch:{ all -> 0x0484 }
            org.fusesource.jansi.Ansi r3 = org.fusesource.jansi.Ansi.ansi()     // Catch:{ all -> 0x0484 }
            org.fusesource.jansi.Ansi r3 = r3.bold()     // Catch:{ all -> 0x0484 }
            java.lang.String r4 = "Jansi filtered args:"
            org.fusesource.jansi.Ansi r3 = r3.a((java.lang.String) r4)     // Catch:{ all -> 0x0484 }
            org.fusesource.jansi.Ansi r3 = r3.reset()     // Catch:{ all -> 0x0484 }
            r2.println(r3)     // Catch:{ all -> 0x0484 }
            int r2 = r10.length     // Catch:{ all -> 0x0484 }
        L_0x045d:
            if (r0 >= r2) goto L_0x0480
            r3 = r10[r0]     // Catch:{ all -> 0x0484 }
            java.io.PrintStream r4 = java.lang.System.out     // Catch:{ all -> 0x0484 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0484 }
            r5.<init>()     // Catch:{ all -> 0x0484 }
            int r7 = r1 + 1
            r5.append(r1)     // Catch:{ all -> 0x0484 }
            r5.append(r6)     // Catch:{ all -> 0x0484 }
            java.lang.String r1 = r5.toString()     // Catch:{ all -> 0x0484 }
            r4.print(r1)     // Catch:{ all -> 0x0484 }
            java.io.PrintStream r1 = java.lang.System.out     // Catch:{ all -> 0x0484 }
            r1.println(r3)     // Catch:{ all -> 0x0484 }
            int r0 = r0 + 1
            r1 = r7
            goto L_0x045d
        L_0x0480:
            org.fusesource.jansi.AnsiConsole.systemUninstall()
            return
        L_0x0484:
            r10 = move-exception
            org.fusesource.jansi.AnsiConsole.systemUninstall()
            throw r10
        L_0x0489:
            r10 = move-exception
            if (r0 == 0) goto L_0x0490
            java.lang.System.setProperty(r1, r0)
            goto L_0x0493
        L_0x0490:
            java.lang.System.clearProperty(r1)
        L_0x0493:
            goto L_0x0495
        L_0x0494:
            throw r10
        L_0x0495:
            goto L_0x0494
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fusesource.jansi.AnsiMain.main(java.lang.String[]):void");
    }

    private static String getJansiVersion() {
        Package packageR = AnsiMain.class.getPackage();
        if (packageR == null) {
            return null;
        }
        return packageR.getImplementationVersion();
    }

    private static void diagnoseTty(boolean z) {
        int isatty = CLibrary.LOADED ? CLibrary.isatty(z ? CLibrary.STDERR_FILENO : CLibrary.STDOUT_FILENO) : 0;
        PrintStream printStream = System.out;
        StringBuilder sb = new StringBuilder("isatty(STD");
        sb.append(z ? "ERR" : "OUT");
        sb.append("_FILENO): ");
        sb.append(isatty);
        sb.append(", System.");
        sb.append(z ? NotificationCompat.CATEGORY_ERROR : "out");
        sb.append(AnsiRenderer.CODE_TEXT_SEPARATOR);
        sb.append(isatty == 0 ? "is *NOT*" : "is");
        sb.append(" a terminal");
        printStream.println(sb.toString());
    }

    private static void testAnsi(boolean z) {
        PrintStream printStream = z ? System.err : System.out;
        StringBuilder sb = new StringBuilder("test on System.");
        sb.append(z ? NotificationCompat.CATEGORY_ERROR : "out");
        sb.append(":");
        printStream.print(sb.toString());
        for (Ansi.Color color : Ansi.Color.values()) {
            printStream.print(AnsiRenderer.CODE_TEXT_SEPARATOR + Ansi.ansi().fg(color) + color + Ansi.ansi().reset());
        }
        printStream.println();
        printStream.print("            bright:");
        for (Ansi.Color color2 : Ansi.Color.values()) {
            printStream.print(AnsiRenderer.CODE_TEXT_SEPARATOR + Ansi.ansi().fgBright(color2) + color2 + Ansi.ansi().reset());
        }
        printStream.println();
        printStream.print("              bold:");
        for (Ansi.Color color3 : Ansi.Color.values()) {
            printStream.print(AnsiRenderer.CODE_TEXT_SEPARATOR + Ansi.ansi().bold().fg(color3) + color3 + Ansi.ansi().reset());
        }
        printStream.println();
        printStream.print("             faint:");
        for (Ansi.Color color4 : Ansi.Color.values()) {
            printStream.print(AnsiRenderer.CODE_TEXT_SEPARATOR + Ansi.ansi().a(Ansi.Attribute.INTENSITY_FAINT).fg(color4) + color4 + Ansi.ansi().reset());
        }
        printStream.println();
        printStream.print("        bold+faint:");
        for (Ansi.Color color5 : Ansi.Color.values()) {
            printStream.print(AnsiRenderer.CODE_TEXT_SEPARATOR + Ansi.ansi().bold().a(Ansi.Attribute.INTENSITY_FAINT).fg(color5) + color5 + Ansi.ansi().reset());
        }
        printStream.println();
        Ansi ansi = Ansi.ansi();
        ansi.a("        256 colors: ");
        for (int i = 0; i < 216; i++) {
            if (i > 0 && i % 36 == 0) {
                ansi.reset();
                ansi.newline();
                ansi.a("                    ");
            } else if (i > 0 && i % 6 == 0) {
                ansi.reset();
                ansi.a("  ");
            }
            ansi.bg((i % 6) + 16 + ((i / 36) * 6) + (((i / 6) % 6) * 36)).a(' ');
        }
        ansi.reset();
        printStream.println(ansi);
        Ansi ansi2 = Ansi.ansi();
        ansi2.a("         truecolor: ");
        for (int i2 = 0; i2 < 256; i2++) {
            if (i2 > 0 && i2 % 48 == 0) {
                ansi2.reset();
                ansi2.newline();
                ansi2.a("                    ");
            }
            int i3 = 255 - i2;
            int i4 = i2 * 2;
            if (i4 > 255) {
                i4 = 255 - i4;
            }
            ansi2.bgRgb(i3, i4, i2).fgRgb(255 - i3, 255 - i4, i3).a(i2 % 2 == 0 ? '/' : AbstractJsonLexerKt.STRING_ESC);
        }
        ansi2.reset();
        printStream.println(ansi2);
    }

    private static String getPomPropertiesVersion(String str) throws IOException {
        InputStream resourceAsStream = AnsiMain.class.getResourceAsStream("/META-INF/maven/" + str + "/pom.properties");
        if (resourceAsStream == null) {
            return null;
        }
        try {
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            return properties.getProperty("version");
        } finally {
            closeQuietly(resourceAsStream);
        }
    }

    private static void printJansiLogoDemo() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(AnsiMain.class.getResourceAsStream("jansi.txt"), "UTF-8"));
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    System.out.println(readLine);
                } else {
                    return;
                }
            } finally {
                closeQuietly(bufferedReader);
            }
        }
    }

    private static void writeFileContent(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read >= 0) {
                    System.out.write(bArr, 0, read);
                } else {
                    return;
                }
            }
        } finally {
            closeQuietly(fileInputStream);
        }
    }

    private static void closeQuietly(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }
}
