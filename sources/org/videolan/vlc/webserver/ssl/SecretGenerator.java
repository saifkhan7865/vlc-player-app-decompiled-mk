package org.videolan.vlc.webserver.ssl;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyGenParameterSpec;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import io.ktor.utils.io.charsets.CharsetJVMKt;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.UnrecoverableEntryException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.x500.X500Principal;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0004H\u0007J\u0018\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0004H\u0007J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0014\u001a\u00020\u0015H\u0003J\b\u0010\u001b\u001a\u00020\u001aH\u0003J\u0010\u0010\u001c\u001a\u00020\u00042\b\b\u0002\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010\u001f\u001a\u00020 J\u0006\u0010!\u001a\u00020\u0004J\u0010\u0010\"\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0012\u0010#\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010$\u001a\u00020\u001a2\u0006\u0010\u0014\u001a\u00020\u0015H\u0003J\u0010\u0010%\u001a\u00020\u001a2\u0006\u0010\u0014\u001a\u00020\u0015H\u0003J\u000e\u0010&\u001a\u00020\u001a2\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010&\u001a\u00020\u001a2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010'\u001a\u00020(J\u0010\u0010)\u001a\u00020\u001a2\u0006\u0010\u0014\u001a\u00020\u0015H\u0003J\u0010\u0010*\u001a\u00020 2\u0006\u0010+\u001a\u00020 H\u0002J\u0010\u0010,\u001a\u00020 2\u0006\u0010-\u001a\u00020 H\u0002J\u0010\u0010.\u001a\u00020\u001a2\u0006\u0010\u0014\u001a\u00020\u0015H\u0003R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \u000b*\u0004\u0018\u00010\u00040\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u00108BX\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006/"}, d2 = {"Lorg/videolan/vlc/webserver/ssl/SecretGenerator;", "", "()V", "AES_MODE_LESS_THAN_M", "", "AES_MODE_M_OR_GREATER", "ANDROID_KEY_STORE_NAME", "CIPHER_PROVIDER_NAME_ENCRYPTION_DECRYPTION_AES", "CIPHER_PROVIDER_NAME_ENCRYPTION_DECRYPTION_RSA", "KEY_ALIAS", "LOG_TAG", "kotlin.jvm.PlatformType", "RSA_ALGORITHM_NAME", "RSA_MODE", "s_keyInitLock", "secretKeyAPIMorGreater", "Ljava/security/Key;", "getSecretKeyAPIMorGreater", "()Ljava/security/Key;", "decryptData", "context", "Landroid/content/Context;", "encryptedData", "encryptData", "stringDataToEncrypt", "generateKeysForAPILessThanM", "", "generateKeysForAPIMOrGreater", "generateRandomAlphanumericString", "length", "", "generateRandomBytes", "", "generateRandomString", "getSecretKeyApiLessThanM", "getSecretKeyFromSharedPreferences", "initKeys", "initValidKeys", "removeKeys", "keyStore", "Ljava/security/KeyStore;", "removeSavedSharedPreferences", "rsaDecryptKey", "encrypted", "rsaEncryptKey", "secret", "saveEncryptedKey", "webserver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SecretGenerator.kt */
public final class SecretGenerator {
    private static final String AES_MODE_LESS_THAN_M = "AES/ECB/PKCS7Padding";
    private static final String AES_MODE_M_OR_GREATER = "AES/GCM/NoPadding";
    private static final String ANDROID_KEY_STORE_NAME = "AndroidKeyStore";
    private static final String CIPHER_PROVIDER_NAME_ENCRYPTION_DECRYPTION_AES = "BC";
    private static final String CIPHER_PROVIDER_NAME_ENCRYPTION_DECRYPTION_RSA = "AndroidOpenSSL";
    public static final SecretGenerator INSTANCE = new SecretGenerator();
    private static final String KEY_ALIAS = "vlc-android";
    private static final String LOG_TAG = SecretGenerator.class.getName();
    private static final String RSA_ALGORITHM_NAME = "RSA";
    private static final String RSA_MODE = "RSA/ECB/PKCS1Padding";
    private static final Object s_keyInitLock = new Object();

    private SecretGenerator() {
    }

    private final Key getSecretKeyAPIMorGreater() throws CertificateException, NoSuchAlgorithmException, IOException, KeyStoreException, UnrecoverableKeyException {
        KeyStore instance = KeyStore.getInstance(ANDROID_KEY_STORE_NAME);
        instance.load((KeyStore.LoadStoreParameter) null);
        Key key = instance.getKey(KEY_ALIAS, (char[]) null);
        Intrinsics.checkNotNullExpressionValue(key, "getKey(...)");
        return key;
    }

    private final void initKeys(Context context) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, NoSuchProviderException, InvalidAlgorithmParameterException, UnrecoverableEntryException, NoSuchPaddingException, InvalidKeyException {
        KeyStore instance = KeyStore.getInstance(ANDROID_KEY_STORE_NAME);
        instance.load((KeyStore.LoadStoreParameter) null);
        if (!instance.containsAlias(KEY_ALIAS)) {
            initValidKeys(context);
            return;
        }
        boolean z = false;
        try {
            KeyStore.Entry entry = instance.getEntry(KEY_ALIAS, (KeyStore.ProtectionParameter) null);
            if ((entry instanceof KeyStore.SecretKeyEntry) && Build.VERSION.SDK_INT >= 23) {
                z = true;
            }
            if ((entry instanceof KeyStore.PrivateKeyEntry) && Build.VERSION.SDK_INT < 23 && !TextUtils.isEmpty(getSecretKeyFromSharedPreferences(context))) {
                return;
            }
        } catch (NullPointerException e) {
            Log.e(LOG_TAG, "Failed to get key store entry", e);
        } catch (UnrecoverableKeyException e2) {
            Log.e(LOG_TAG, "Failed to get key store entry", e2);
        }
        if (!z) {
            synchronized (s_keyInitLock) {
                SecretGenerator secretGenerator = INSTANCE;
                Intrinsics.checkNotNull(instance);
                secretGenerator.removeKeys(context, instance);
                secretGenerator.initValidKeys(context);
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    public final void removeKeys(Context context, KeyStore keyStore) throws KeyStoreException {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(keyStore, "keyStore");
        keyStore.deleteEntry(KEY_ALIAS);
        removeSavedSharedPreferences(context);
    }

    private final void initValidKeys(Context context) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, CertificateException, UnrecoverableEntryException, NoSuchPaddingException, KeyStoreException, InvalidKeyException, IOException {
        synchronized (s_keyInitLock) {
            if (Build.VERSION.SDK_INT >= 23) {
                INSTANCE.generateKeysForAPIMOrGreater();
            } else {
                INSTANCE.generateKeysForAPILessThanM(context);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void removeSavedSharedPreferences(Context context) {
        boolean commit = ((SharedPreferences) Settings.INSTANCE.getInstance(context)).edit().remove(SettingsKt.ENCRYPTED_KEY_NAME).commit();
        String str = LOG_TAG;
        Log.d(str, "Cleared secret key shared preferences " + commit);
    }

    private final void generateKeysForAPILessThanM(Context context) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, CertificateException, UnrecoverableEntryException, NoSuchPaddingException, KeyStoreException, InvalidKeyException, IOException {
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance2.add(1, 30);
        KeyPairGeneratorSpec m = new KeyPairGeneratorSpec.Builder(context).setAlias(KEY_ALIAS).setSubject(new X500Principal("CN=vlc-android")).setSerialNumber(BigInteger.TEN).setStartDate(instance.getTime()).setEndDate(instance2.getTime()).build();
        Intrinsics.checkNotNullExpressionValue(m, "build(...)");
        KeyPairGenerator instance3 = KeyPairGenerator.getInstance(RSA_ALGORITHM_NAME, ANDROID_KEY_STORE_NAME);
        instance3.initialize(m);
        instance3.generateKeyPair();
        saveEncryptedKey(context);
    }

    private final void saveEncryptedKey(Context context) throws CertificateException, NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, KeyStoreException, NoSuchProviderException, UnrecoverableEntryException, IOException {
        if (((SharedPreferences) Settings.INSTANCE.getInstance(context)).getString(SettingsKt.ENCRYPTED_KEY_NAME, (String) null) == null) {
            byte[] bArr = new byte[16];
            new SecureRandom().nextBytes(bArr);
            String encodeToString = Base64.encodeToString(rsaEncryptKey(bArr), 0);
            SharedPreferences.Editor edit = ((SharedPreferences) Settings.INSTANCE.getInstance(context)).edit();
            Intrinsics.checkNotNullExpressionValue(edit, "edit(...)");
            edit.putString(SettingsKt.ENCRYPTED_KEY_NAME, encodeToString);
            if (!edit.commit()) {
                Log.e(LOG_TAG, "Saved keys unsuccessfully");
                throw new IOException("Could not save keys");
            }
        }
    }

    private final Key getSecretKeyApiLessThanM(Context context) {
        String secretKeyFromSharedPreferences = getSecretKeyFromSharedPreferences(context);
        if (!TextUtils.isEmpty(secretKeyFromSharedPreferences)) {
            byte[] decode = Base64.decode(secretKeyFromSharedPreferences, 0);
            Intrinsics.checkNotNull(decode);
            return new SecretKeySpec(rsaDecryptKey(decode), "AES");
        }
        throw new InvalidKeyException("Saved key missing from shared preferences");
    }

    private final String getSecretKeyFromSharedPreferences(Context context) {
        return ((SharedPreferences) Settings.INSTANCE.getInstance(context)).getString(SettingsKt.ENCRYPTED_KEY_NAME, (String) null);
    }

    private final void generateKeysForAPIMOrGreater() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {
        KeyGenerator instance = KeyGenerator.getInstance("AES", ANDROID_KEY_STORE_NAME);
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
        instance.init(new KeyGenParameterSpec.Builder(KEY_ALIAS, 3).setBlockModes(new String[]{"GCM"}).setEncryptionPaddings(new String[]{"NoPadding"}).setRandomizedEncryptionRequired(false).build());
        instance.generateKey();
    }

    public final String encryptData(Context context, String str) throws NoSuchPaddingException, NoSuchAlgorithmException, UnrecoverableEntryException, CertificateException, KeyStoreException, IOException, InvalidAlgorithmParameterException, InvalidKeyException, NoSuchProviderException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher;
        byte[] bArr;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, "stringDataToEncrypt");
        initKeys(context);
        if (Build.VERSION.SDK_INT >= 23) {
            cipher = Cipher.getInstance(AES_MODE_M_OR_GREATER);
            Intrinsics.checkNotNullExpressionValue(cipher, "getInstance(...)");
            byte[] generateRandomBytes = generateRandomBytes();
            String encodeToString = Base64.encodeToString(generateRandomBytes, 0);
            Intrinsics.checkNotNullExpressionValue(encodeToString, "encodeToString(...)");
            SettingsKt.putSingle((SharedPreferences) Settings.INSTANCE.getInstance(context), SettingsKt.KEYSTORE_PASSWORD_IV, encodeToString);
            cipher.init(1, getSecretKeyAPIMorGreater(), new GCMParameterSpec(128, generateRandomBytes));
        } else {
            cipher = Cipher.getInstance(AES_MODE_LESS_THAN_M, "BC");
            Intrinsics.checkNotNullExpressionValue(cipher, "getInstance(...)");
            try {
                cipher.init(1, getSecretKeyApiLessThanM(context));
            } catch (InvalidKeyException e) {
                removeKeys(context);
                throw e;
            } catch (IOException e2) {
                removeKeys(context);
                throw e2;
            } catch (IllegalArgumentException e3) {
                removeKeys(context);
                throw e3;
            }
        }
        Charset charset = Charsets.UTF_8;
        if (Intrinsics.areEqual((Object) charset, (Object) Charsets.UTF_8)) {
            bArr = StringsKt.encodeToByteArray(str);
        } else {
            CharsetEncoder newEncoder = charset.newEncoder();
            Intrinsics.checkNotNullExpressionValue(newEncoder, "charset.newEncoder()");
            bArr = CharsetJVMKt.encodeToByteArray(newEncoder, str, 0, str.length());
        }
        String encodeToString2 = Base64.encodeToString(cipher.doFinal(bArr), 0);
        Intrinsics.checkNotNullExpressionValue(encodeToString2, "encodeToString(...)");
        return encodeToString2;
    }

    public final String decryptData(Context context, String str) throws NoSuchPaddingException, NoSuchAlgorithmException, UnrecoverableEntryException, CertificateException, KeyStoreException, IOException, InvalidAlgorithmParameterException, InvalidKeyException, NoSuchProviderException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, "encryptedData");
        initKeys(context);
        byte[] decode = Base64.decode(str, 0);
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                cipher = Cipher.getInstance(AES_MODE_M_OR_GREATER);
                Intrinsics.checkNotNullExpressionValue(cipher, "getInstance(...)");
                cipher.init(2, getSecretKeyAPIMorGreater(), new GCMParameterSpec(128, Base64.decode(((SharedPreferences) Settings.INSTANCE.getInstance(context)).getString(SettingsKt.KEYSTORE_PASSWORD_IV, ""), 0)));
            } else {
                cipher = Cipher.getInstance(AES_MODE_LESS_THAN_M, "BC");
                Intrinsics.checkNotNullExpressionValue(cipher, "getInstance(...)");
                cipher.init(2, getSecretKeyApiLessThanM(context));
            }
            byte[] doFinal = cipher.doFinal(decode);
            Intrinsics.checkNotNull(doFinal);
            return new String(doFinal, Charsets.UTF_8);
        } catch (InvalidKeyException e) {
            removeKeys(context);
            throw e;
        } catch (IOException e2) {
            removeKeys(context);
            throw e2;
        }
    }

    public final String generateRandomString() {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        int nextInt = secureRandom.nextInt(16) + 32;
        for (int i = 0; i < nextInt; i++) {
            int nextInt2 = secureRandom.nextInt(95) + 33;
            if (nextInt2 < 0 || nextInt2 > 65535) {
                throw new IllegalArgumentException("Invalid Char code: " + nextInt2);
            }
            sb.append((char) nextInt2);
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    public static /* synthetic */ String generateRandomAlphanumericString$default(SecretGenerator secretGenerator, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = -1;
        }
        return secretGenerator.generateRandomAlphanumericString(i);
    }

    public final String generateRandomAlphanumericString(int i) {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        if (i == -1) {
            i = secureRandom.nextInt(16) + 32;
        }
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("abcdef0123456789".charAt(secureRandom.nextInt(16)));
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    public final byte[] generateRandomBytes() {
        byte[] bArr = new byte[12];
        new SecureRandom().nextBytes(bArr);
        return bArr;
    }

    private final byte[] rsaEncryptKey(byte[] bArr) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, NoSuchProviderException, NoSuchPaddingException, UnrecoverableEntryException, InvalidKeyException {
        KeyStore instance = KeyStore.getInstance(ANDROID_KEY_STORE_NAME);
        instance.load((KeyStore.LoadStoreParameter) null);
        KeyStore.Entry entry = instance.getEntry(KEY_ALIAS, (KeyStore.ProtectionParameter) null);
        Intrinsics.checkNotNull(entry, "null cannot be cast to non-null type java.security.KeyStore.PrivateKeyEntry");
        Cipher instance2 = Cipher.getInstance(RSA_MODE, CIPHER_PROVIDER_NAME_ENCRYPTION_DECRYPTION_RSA);
        instance2.init(1, ((KeyStore.PrivateKeyEntry) entry).getCertificate().getPublicKey());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        CipherOutputStream cipherOutputStream = new CipherOutputStream(byteArrayOutputStream, instance2);
        cipherOutputStream.write(bArr);
        cipherOutputStream.close();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        Intrinsics.checkNotNullExpressionValue(byteArray, "toByteArray(...)");
        return byteArray;
    }

    private final byte[] rsaDecryptKey(byte[] bArr) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, UnrecoverableEntryException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException {
        KeyStore instance = KeyStore.getInstance(ANDROID_KEY_STORE_NAME);
        instance.load((KeyStore.LoadStoreParameter) null);
        KeyStore.Entry entry = instance.getEntry(KEY_ALIAS, (KeyStore.ProtectionParameter) null);
        Intrinsics.checkNotNull(entry, "null cannot be cast to non-null type java.security.KeyStore.PrivateKeyEntry");
        Cipher instance2 = Cipher.getInstance(RSA_MODE, CIPHER_PROVIDER_NAME_ENCRYPTION_DECRYPTION_RSA);
        instance2.init(2, ((KeyStore.PrivateKeyEntry) entry).getPrivateKey());
        CipherInputStream cipherInputStream = new CipherInputStream(new ByteArrayInputStream(bArr), instance2);
        ArrayList arrayList = new ArrayList();
        while (true) {
            int read = cipherInputStream.read();
            if (read == -1) {
                break;
            }
            arrayList.add(Byte.valueOf((byte) read));
        }
        int size = arrayList.size();
        byte[] bArr2 = new byte[size];
        for (int i = 0; i < size; i++) {
            Object obj = arrayList.get(i);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            bArr2[i] = ((Number) obj).byteValue();
        }
        return bArr2;
    }

    public final void removeKeys(Context context) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {
        Intrinsics.checkNotNullParameter(context, "context");
        synchronized (s_keyInitLock) {
            KeyStore instance = KeyStore.getInstance(ANDROID_KEY_STORE_NAME);
            instance.load((KeyStore.LoadStoreParameter) null);
            SecretGenerator secretGenerator = INSTANCE;
            Intrinsics.checkNotNull(instance);
            secretGenerator.removeKeys(context, instance);
            Unit unit = Unit.INSTANCE;
        }
    }
}
