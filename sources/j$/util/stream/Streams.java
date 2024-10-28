package j$.util.stream;

abstract class Streams {
    static Runnable composeWithExceptions(final Runnable runnable, final Runnable runnable2) {
        return new Runnable() {
            public void run() {
                try {
                    runnable.run();
                    runnable2.run();
                    return;
                } catch (Throwable th) {
                    try {
                        Collectors$$ExternalSyntheticBackport0.m(th, th);
                    } catch (Throwable unused) {
                    }
                }
                throw th;
            }
        };
    }
}
