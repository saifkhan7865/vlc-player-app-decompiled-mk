package org.bouncycastle.pqc.crypto.sphincsplus;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine;

public class SPHINCSPlusKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private SPHINCSPlusParameters parameters;
    private SecureRandom random;

    private byte[] sec_rand(int i) {
        byte[] bArr = new byte[i];
        this.random.nextBytes(bArr);
        return bArr;
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        byte[] bArr;
        SK sk;
        SPHINCSPlusEngine engine = this.parameters.getEngine();
        if (engine instanceof SPHINCSPlusEngine.HarakaSEngine) {
            byte[] sec_rand = sec_rand(engine.N * 3);
            byte[] bArr2 = new byte[engine.N];
            byte[] bArr3 = new byte[engine.N];
            bArr = new byte[engine.N];
            System.arraycopy(sec_rand, 0, bArr2, 0, engine.N);
            System.arraycopy(sec_rand, engine.N, bArr3, 0, engine.N);
            System.arraycopy(sec_rand, engine.N << 1, bArr, 0, engine.N);
            sk = new SK(bArr2, bArr3);
        } else {
            sk = new SK(sec_rand(engine.N), sec_rand(engine.N));
            bArr = sec_rand(engine.N);
        }
        engine.init(bArr);
        PK pk = new PK(bArr, new HT(engine, sk.seed, bArr).htPubKey);
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new SPHINCSPlusPublicKeyParameters(this.parameters, pk), (AsymmetricKeyParameter) new SPHINCSPlusPrivateKeyParameters(this.parameters, sk, pk));
    }

    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.random = keyGenerationParameters.getRandom();
        this.parameters = ((SPHINCSPlusKeyGenerationParameters) keyGenerationParameters).getParameters();
    }
}
