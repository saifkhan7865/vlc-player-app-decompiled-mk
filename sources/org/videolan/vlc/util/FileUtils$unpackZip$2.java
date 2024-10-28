package org.videolan.vlc.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "Ljava/util/ArrayList;", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.FileUtils$unpackZip$2", f = "FileUtils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: FileUtils.kt */
final class FileUtils$unpackZip$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<String>>, Object> {
    final /* synthetic */ String $path;
    final /* synthetic */ String $unzipDirectory;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FileUtils$unpackZip$2(String str, String str2, Continuation<? super FileUtils$unpackZip$2> continuation) {
        super(2, continuation);
        this.$unzipDirectory = str;
        this.$path = str2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FileUtils$unpackZip$2(this.$unzipDirectory, this.$path, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ArrayList<String>> continuation) {
        return ((FileUtils$unpackZip$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            ArrayList arrayList = new ArrayList();
            new File(this.$unzipDirectory).mkdirs();
            try {
                ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(this.$path)));
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                while (nextEntry != null) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bArr = new byte[1024];
                    String name = nextEntry.getName();
                    Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                    String replace$default = StringsKt.replace$default(name, '/', ' ', false, 4, (Object) null);
                    if (StringsKt.endsWith$default(replace$default, ".nfo", false, 2, (Object) null)) {
                        zipInputStream.closeEntry();
                        nextEntry = zipInputStream.getNextEntry();
                    } else {
                        File file = new File(this.$unzipDirectory, replace$default);
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        for (int read = zipInputStream.read(bArr); read != -1; read = zipInputStream.read(bArr)) {
                            byteArrayOutputStream.write(bArr, 0, read);
                            fileOutputStream.write(byteArrayOutputStream.toByteArray());
                            byteArrayOutputStream.reset();
                        }
                        arrayList.add(file.getAbsolutePath());
                        fileOutputStream.close();
                        zipInputStream.closeEntry();
                        nextEntry = zipInputStream.getNextEntry();
                    }
                }
                zipInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return arrayList;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
