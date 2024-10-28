package j$.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

class DesugarBufferedReaderLinesIterator implements Iterator {
    private final BufferedReader bufferedReader;
    String nextLine = null;

    DesugarBufferedReaderLinesIterator(BufferedReader bufferedReader2) {
        this.bufferedReader = bufferedReader2;
    }

    public boolean hasNext() {
        if (this.nextLine != null) {
            return true;
        }
        try {
            String readLine = this.bufferedReader.readLine();
            this.nextLine = readLine;
            return readLine != null;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public String next() {
        if (this.nextLine != null || hasNext()) {
            String str = this.nextLine;
            this.nextLine = null;
            return str;
        }
        throw new NoSuchElementException();
    }
}
