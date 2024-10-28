package org.bouncycastle.jcajce.provider.drbg;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.SecureRandom;
import java.security.SecureRandomSpi;
import java.security.Security;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.prng.EntropySource;
import org.bouncycastle.crypto.prng.EntropySourceProvider;
import org.bouncycastle.crypto.prng.SP800SecureRandom;
import org.bouncycastle.crypto.prng.SP800SecureRandomBuilder;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.ClassUtil;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;
import org.bouncycastle.util.Properties;
import org.bouncycastle.util.Strings;

public class DRBG {
    /* access modifiers changed from: private */
    public static final String PREFIX = "org.bouncycastle.jcajce.provider.drbg.DRBG";
    /* access modifiers changed from: private */
    public static EntropyDaemon entropyDaemon;
    private static Thread entropyThread = null;
    private static final String[][] initialEntropySourceNames = {new String[]{"sun.security.provider.Sun", "sun.security.provider.SecureRandom"}, new String[]{"org.apache.harmony.security.provider.crypto.CryptoProvider", "org.apache.harmony.security.provider.crypto.SHA1PRNG_SecureRandomImpl"}, new String[]{"com.android.org.conscrypt.OpenSSLProvider", "com.android.org.conscrypt.OpenSSLRandom"}, new String[]{"org.conscrypt.OpenSSLProvider", "org.conscrypt.OpenSSLRandom"}};

    private static class CoreSecureRandom extends SecureRandom {
        CoreSecureRandom(Object[] objArr) {
            super(objArr[1], objArr[0]);
        }
    }

    public static class Default extends SecureRandomSpi {
        private static final SecureRandom random = DRBG.createBaseRandom(true);

        /* access modifiers changed from: protected */
        public byte[] engineGenerateSeed(int i) {
            return random.generateSeed(i);
        }

        /* access modifiers changed from: protected */
        public void engineNextBytes(byte[] bArr) {
            random.nextBytes(bArr);
        }

        /* access modifiers changed from: protected */
        public void engineSetSeed(byte[] bArr) {
            random.setSeed(bArr);
        }
    }

    private static class HybridEntropySource implements EntropySource {
        private final byte[] additionalInput = Pack.longToBigEndian(System.currentTimeMillis());
        private final int bytesRequired;
        private final SP800SecureRandom drbg;
        /* access modifiers changed from: private */
        public final SignallingEntropySource entropySource;
        private final AtomicInteger samples = new AtomicInteger(0);
        private final AtomicBoolean seedAvailable;

        private static class SignallingEntropySource implements IncrementalEntropySource {
            private final int byteLength;
            private final AtomicReference entropy = new AtomicReference();
            private final EntropyDaemon entropyDaemon;
            private final IncrementalEntropySource entropySource;
            private final AtomicBoolean scheduled = new AtomicBoolean(false);
            private final AtomicBoolean seedAvailable;

            SignallingEntropySource(EntropyDaemon entropyDaemon2, AtomicBoolean atomicBoolean, EntropySourceProvider entropySourceProvider, int i) {
                this.entropyDaemon = entropyDaemon2;
                this.seedAvailable = atomicBoolean;
                this.entropySource = (IncrementalEntropySource) entropySourceProvider.get(i);
                this.byteLength = (i + 7) / 8;
            }

            public int entropySize() {
                return this.byteLength * 8;
            }

            public byte[] getEntropy() {
                try {
                    return getEntropy(0);
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                    throw new IllegalStateException("initial entropy fetch interrupted");
                }
            }

            public byte[] getEntropy(long j) throws InterruptedException {
                byte[] bArr = (byte[]) this.entropy.getAndSet((Object) null);
                if (bArr == null || bArr.length != this.byteLength) {
                    return this.entropySource.getEntropy(j);
                }
                this.scheduled.set(false);
                return bArr;
            }

            public boolean isPredictionResistant() {
                return true;
            }

            /* access modifiers changed from: package-private */
            public void schedule() {
                if (!this.scheduled.getAndSet(true)) {
                    this.entropyDaemon.addTask(new EntropyGatherer(this.entropySource, this.seedAvailable, this.entropy));
                }
            }
        }

        HybridEntropySource(EntropyDaemon entropyDaemon, int i) {
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            this.seedAvailable = atomicBoolean;
            EntropySourceProvider access$700 = DRBG.createCoreEntropySourceProvider();
            this.bytesRequired = (i + 7) / 8;
            SignallingEntropySource signallingEntropySource = new SignallingEntropySource(entropyDaemon, atomicBoolean, access$700, 256);
            this.entropySource = signallingEntropySource;
            this.drbg = new SP800SecureRandomBuilder(new EntropySourceProvider() {
                public EntropySource get(int i) {
                    return HybridEntropySource.this.entropySource;
                }
            }).setPersonalizationString(Strings.toByteArray("Bouncy Castle Hybrid Entropy Source")).buildHMAC(new HMac(new SHA512Digest()), signallingEntropySource.getEntropy(), false);
        }

        public int entropySize() {
            return this.bytesRequired * 8;
        }

        public byte[] getEntropy() {
            byte[] bArr = new byte[this.bytesRequired];
            if (this.samples.getAndIncrement() > 128) {
                if (this.seedAvailable.getAndSet(false)) {
                    this.samples.set(0);
                    this.drbg.reseed(this.additionalInput);
                } else {
                    this.entropySource.schedule();
                }
            }
            this.drbg.nextBytes(bArr);
            return bArr;
        }

        public boolean isPredictionResistant() {
            return true;
        }
    }

    public static class Mappings extends AsymmetricAlgorithmProvider {
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("SecureRandom.DEFAULT", DRBG.PREFIX + "$Default");
            configurableProvider.addAlgorithm("SecureRandom.NONCEANDIV", DRBG.PREFIX + "$NonceAndIV");
        }
    }

    public static class NonceAndIV extends SecureRandomSpi {
        private static final SecureRandom random = DRBG.createBaseRandom(false);

        /* access modifiers changed from: protected */
        public byte[] engineGenerateSeed(int i) {
            return random.generateSeed(i);
        }

        /* access modifiers changed from: protected */
        public void engineNextBytes(byte[] bArr) {
            random.nextBytes(bArr);
        }

        /* access modifiers changed from: protected */
        public void engineSetSeed(byte[] bArr) {
            random.setSeed(bArr);
        }
    }

    private static class OneShotHybridEntropySource implements EntropySource {
        private final byte[] additionalInput = Pack.longToBigEndian(System.currentTimeMillis());
        private final int bytesRequired;
        private final SP800SecureRandom drbg;
        /* access modifiers changed from: private */
        public final OneShotSignallingEntropySource entropySource;
        private final AtomicInteger samples = new AtomicInteger(0);
        private final AtomicBoolean seedAvailable;

        private static class OneShotSignallingEntropySource implements IncrementalEntropySource {
            private final int byteLength;
            private final AtomicReference entropy = new AtomicReference();
            private final IncrementalEntropySource entropySource;
            private final AtomicBoolean scheduled = new AtomicBoolean(false);
            private final AtomicBoolean seedAvailable;

            OneShotSignallingEntropySource(AtomicBoolean atomicBoolean, EntropySourceProvider entropySourceProvider, int i) {
                this.seedAvailable = atomicBoolean;
                this.entropySource = (IncrementalEntropySource) entropySourceProvider.get(i);
                this.byteLength = (i + 7) / 8;
            }

            public int entropySize() {
                return this.byteLength * 8;
            }

            public byte[] getEntropy() {
                try {
                    return getEntropy(0);
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                    throw new IllegalStateException("initial entropy fetch interrupted");
                }
            }

            public byte[] getEntropy(long j) throws InterruptedException {
                byte[] bArr = (byte[]) this.entropy.getAndSet((Object) null);
                if (bArr == null || bArr.length != this.byteLength) {
                    return this.entropySource.getEntropy(j);
                }
                this.scheduled.set(false);
                return bArr;
            }

            public boolean isPredictionResistant() {
                return true;
            }

            /* access modifiers changed from: package-private */
            public void schedule() {
                if (!this.scheduled.getAndSet(true)) {
                    Thread thread = new Thread(new EntropyGatherer(this.entropySource, this.seedAvailable, this.entropy));
                    thread.setDaemon(true);
                    thread.start();
                }
            }
        }

        OneShotHybridEntropySource(int i) {
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            this.seedAvailable = atomicBoolean;
            EntropySourceProvider access$700 = DRBG.createCoreEntropySourceProvider();
            this.bytesRequired = (i + 7) / 8;
            OneShotSignallingEntropySource oneShotSignallingEntropySource = new OneShotSignallingEntropySource(atomicBoolean, access$700, 256);
            this.entropySource = oneShotSignallingEntropySource;
            this.drbg = new SP800SecureRandomBuilder(new EntropySourceProvider() {
                public EntropySource get(int i) {
                    return OneShotHybridEntropySource.this.entropySource;
                }
            }).setPersonalizationString(Strings.toByteArray("Bouncy Castle Hybrid Entropy Source")).buildHMAC(new HMac(new SHA512Digest()), oneShotSignallingEntropySource.getEntropy(), false);
        }

        public int entropySize() {
            return this.bytesRequired * 8;
        }

        public byte[] getEntropy() {
            byte[] bArr = new byte[this.bytesRequired];
            if (this.samples.getAndIncrement() > 1024) {
                if (this.seedAvailable.getAndSet(false)) {
                    this.samples.set(0);
                    this.drbg.reseed(this.additionalInput);
                } else {
                    this.entropySource.schedule();
                }
            }
            this.drbg.nextBytes(bArr);
            return bArr;
        }

        public boolean isPredictionResistant() {
            return true;
        }
    }

    private static class URLSeededEntropySourceProvider implements EntropySourceProvider {
        /* access modifiers changed from: private */
        public final InputStream seedStream;

        URLSeededEntropySourceProvider(final URL url) {
            this.seedStream = (InputStream) AccessController.doPrivileged(new PrivilegedAction<InputStream>() {
                public InputStream run() {
                    try {
                        return url.openStream();
                    } catch (IOException unused) {
                        throw new IllegalStateException("unable to open random source");
                    }
                }
            });
        }

        /* access modifiers changed from: private */
        public int privilegedRead(final byte[] bArr, final int i, final int i2) {
            return ((Integer) AccessController.doPrivileged(new PrivilegedAction<Integer>() {
                public Integer run() {
                    try {
                        return Integer.valueOf(URLSeededEntropySourceProvider.this.seedStream.read(bArr, i, i2));
                    } catch (IOException unused) {
                        throw new InternalError("unable to read random source");
                    }
                }
            })).intValue();
        }

        public EntropySource get(int i) {
            return new IncrementalEntropySource(i) {
                private final int numBytes;
                final /* synthetic */ int val$bitsRequired;

                {
                    this.val$bitsRequired = r2;
                    this.numBytes = (r2 + 7) / 8;
                }

                public int entropySize() {
                    return this.val$bitsRequired;
                }

                public byte[] getEntropy() {
                    try {
                        return getEntropy(0);
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                        throw new IllegalStateException("initial entropy fetch interrupted");
                    }
                }

                public byte[] getEntropy(long j) throws InterruptedException {
                    int i = this.numBytes;
                    byte[] bArr = new byte[i];
                    int i2 = 0;
                    while (i2 != i) {
                        int access$500 = URLSeededEntropySourceProvider.this.privilegedRead(bArr, i2, i - i2);
                        if (access$500 <= -1) {
                            break;
                        }
                        i2 += access$500;
                        DRBG.sleep(j);
                    }
                    if (i2 == i) {
                        return bArr;
                    }
                    throw new InternalError("unable to fully read random source");
                }

                public boolean isPredictionResistant() {
                    return true;
                }
            };
        }
    }

    static {
        entropyDaemon = null;
        entropyDaemon = new EntropyDaemon();
    }

    /* access modifiers changed from: private */
    public static SecureRandom createBaseRandom(boolean z) {
        if (Properties.getPropertyValue("org.bouncycastle.drbg.entropysource") != null) {
            EntropySourceProvider createEntropySource = createEntropySource();
            EntropySource entropySource = createEntropySource.get(128);
            byte[] entropy = entropySource.getEntropy();
            return new SP800SecureRandomBuilder(createEntropySource).setPersonalizationString(z ? generateDefaultPersonalizationString(entropy) : generateNonceIVPersonalizationString(entropy)).buildHash(new SHA512Digest(), entropySource.getEntropy(), z);
        } else if (Properties.isOverrideSet("org.bouncycastle.drbg.entropy_thread")) {
            synchronized (entropyDaemon) {
                if (entropyThread == null) {
                    Thread thread = new Thread(entropyDaemon, "BC Entropy Daemon");
                    entropyThread = thread;
                    thread.setDaemon(true);
                    entropyThread.start();
                }
            }
            HybridEntropySource hybridEntropySource = new HybridEntropySource(entropyDaemon, 256);
            byte[] entropy2 = hybridEntropySource.getEntropy();
            return new SP800SecureRandomBuilder(new EntropySourceProvider() {
                public EntropySource get(int i) {
                    return new HybridEntropySource(DRBG.entropyDaemon, i);
                }
            }).setPersonalizationString(z ? generateDefaultPersonalizationString(entropy2) : generateNonceIVPersonalizationString(entropy2)).buildHash(new SHA512Digest(), hybridEntropySource.getEntropy(), z);
        } else {
            OneShotHybridEntropySource oneShotHybridEntropySource = new OneShotHybridEntropySource(256);
            byte[] entropy3 = oneShotHybridEntropySource.getEntropy();
            return new SP800SecureRandomBuilder(new EntropySourceProvider() {
                public EntropySource get(int i) {
                    return new OneShotHybridEntropySource(i);
                }
            }).setPersonalizationString(z ? generateDefaultPersonalizationString(entropy3) : generateNonceIVPersonalizationString(entropy3)).buildHash(new SHA512Digest(), oneShotHybridEntropySource.getEntropy(), z);
        }
    }

    /* access modifiers changed from: private */
    public static EntropySourceProvider createCoreEntropySourceProvider() {
        String str = (String) AccessController.doPrivileged(new PrivilegedAction<String>() {
            public String run() {
                return Security.getProperty("securerandom.source");
            }
        });
        if (str == null) {
            return createInitialEntropySource();
        }
        try {
            return new URLSeededEntropySourceProvider(new URL(str));
        } catch (Exception unused) {
            return createInitialEntropySource();
        }
    }

    private static EntropySourceProvider createEntropySource() {
        final String propertyValue = Properties.getPropertyValue("org.bouncycastle.drbg.entropysource");
        return (EntropySourceProvider) AccessController.doPrivileged(new PrivilegedAction<EntropySourceProvider>() {
            public EntropySourceProvider run() {
                try {
                    return (EntropySourceProvider) ClassUtil.loadClass(DRBG.class, propertyValue).newInstance();
                } catch (Exception e) {
                    throw new IllegalStateException("entropy source " + propertyValue + " not created: " + e.getMessage(), e);
                }
            }
        });
    }

    private static EntropySourceProvider createInitialEntropySource() {
        return ((Boolean) AccessController.doPrivileged(new PrivilegedAction<Boolean>() {
            public Boolean run() {
                try {
                    return Boolean.valueOf(SecureRandom.class.getMethod("getInstanceStrong", (Class[]) null) != null);
                } catch (Exception unused) {
                    return false;
                }
            }
        })).booleanValue() ? new IncrementalEntropySourceProvider((SecureRandom) AccessController.doPrivileged(new PrivilegedAction<SecureRandom>() {
            public SecureRandom run() {
                try {
                    return (SecureRandom) SecureRandom.class.getMethod("getInstanceStrong", (Class[]) null).invoke((Object) null, (Object[]) null);
                } catch (Exception unused) {
                    return new CoreSecureRandom(DRBG.findSource());
                }
            }
        }), true) : new IncrementalEntropySourceProvider(new CoreSecureRandom(findSource()), true);
    }

    /* access modifiers changed from: private */
    public static final Object[] findSource() {
        int i = 0;
        while (true) {
            String[][] strArr = initialEntropySourceNames;
            if (i >= strArr.length) {
                return null;
            }
            String[] strArr2 = strArr[i];
            try {
                return new Object[]{Class.forName(strArr2[0]).newInstance(), Class.forName(strArr2[1]).newInstance()};
            } catch (Throwable unused) {
                i++;
            }
        }
    }

    private static byte[] generateDefaultPersonalizationString(byte[] bArr) {
        return Arrays.concatenate(Strings.toByteArray("Default"), bArr, Pack.longToBigEndian(Thread.currentThread().getId()), Pack.longToBigEndian(System.currentTimeMillis()));
    }

    private static byte[] generateNonceIVPersonalizationString(byte[] bArr) {
        return Arrays.concatenate(Strings.toByteArray("Nonce"), bArr, Pack.longToLittleEndian(Thread.currentThread().getId()), Pack.longToLittleEndian(System.currentTimeMillis()));
    }

    /* access modifiers changed from: private */
    public static void sleep(long j) throws InterruptedException {
        if (j != 0) {
            Thread.sleep(j);
        }
    }
}
