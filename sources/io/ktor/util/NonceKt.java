package io.ktor.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.NonCancellable;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\b\u0010\u0012\u001a\u00020\u0013H\u0000\u001a\u0016\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0007H\u0002\u001a\b\u0010\u0017\u001a\u00020\u0015H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000\"\u0014\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\r\"\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0018"}, d2 = {"INSECURE_NONCE_COUNT_FACTOR", "", "NonceGeneratorCoroutineName", "Lkotlinx/coroutines/CoroutineName;", "SECURE_NONCE_COUNT", "SECURE_RANDOM_PROVIDERS", "", "", "SECURE_RESEED_PERIOD", "SHA1PRNG", "nonceGeneratorJob", "Lkotlinx/coroutines/Job;", "getNonceGeneratorJob$annotations", "()V", "seedChannel", "Lkotlinx/coroutines/channels/Channel;", "getSeedChannel", "()Lkotlinx/coroutines/channels/Channel;", "ensureNonceGeneratorRunning", "", "getInstanceOrNull", "Ljava/security/SecureRandom;", "name", "lookupSecureRandom", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Nonce.kt */
public final class NonceKt {
    private static final int INSECURE_NONCE_COUNT_FACTOR = 4;
    private static final CoroutineName NonceGeneratorCoroutineName;
    private static final int SECURE_NONCE_COUNT = 8;
    private static final List<String> SECURE_RANDOM_PROVIDERS = CollectionsKt.listOf("NativePRNGNonBlocking", "WINDOWS-PRNG", "DRBG");
    private static final int SECURE_RESEED_PERIOD = 30000;
    private static final String SHA1PRNG = "SHA1PRNG";
    private static final Job nonceGeneratorJob;
    private static final Channel<String> seedChannel = ChannelKt.Channel$default(1024, (BufferOverflow) null, (Function1) null, 6, (Object) null);

    private static /* synthetic */ void getNonceGeneratorJob$annotations() {
    }

    static {
        CoroutineName coroutineName = new CoroutineName("nonce-generator");
        NonceGeneratorCoroutineName = coroutineName;
        nonceGeneratorJob = BuildersKt.launch(GlobalScope.INSTANCE, Dispatchers.getIO().plus(NonCancellable.INSTANCE).plus(coroutineName), CoroutineStart.LAZY, new NonceKt$nonceGeneratorJob$1((Continuation<? super NonceKt$nonceGeneratorJob$1>) null));
    }

    public static final Channel<String> getSeedChannel() {
        return seedChannel;
    }

    public static final void ensureNonceGeneratorRunning() {
        nonceGeneratorJob.start();
    }

    /* access modifiers changed from: private */
    public static final SecureRandom lookupSecureRandom() {
        SecureRandom instanceOrNull;
        String property = System.getProperty("io.ktor.random.secure.random.provider");
        if (property != null && (instanceOrNull = getInstanceOrNull(property)) != null) {
            return instanceOrNull;
        }
        for (String instanceOrNull2 : SECURE_RANDOM_PROVIDERS) {
            SecureRandom instanceOrNull3 = getInstanceOrNull(instanceOrNull2);
            if (instanceOrNull3 != null) {
                return instanceOrNull3;
            }
        }
        Logger logger = LoggerFactory.getLogger("io.ktor.util.random");
        logger.warn("None of the " + CollectionsKt.joinToString$default(SECURE_RANDOM_PROVIDERS, ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null) + " found, fallback to default");
        SecureRandom instanceOrNull$default = getInstanceOrNull$default((String) null, 1, (Object) null);
        if (instanceOrNull$default != null) {
            return instanceOrNull$default;
        }
        throw new IllegalStateException("No SecureRandom implementation found".toString());
    }

    static /* synthetic */ SecureRandom getInstanceOrNull$default(String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        return getInstanceOrNull(str);
    }

    private static final SecureRandom getInstanceOrNull(String str) {
        if (str == null) {
            return new SecureRandom();
        }
        try {
            return SecureRandom.getInstance(str);
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }
}
