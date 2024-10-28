package io.ktor.network.tls.certificates;

import io.ktor.network.tls.OID;
import io.ktor.utils.io.charsets.CharsetJVMKt;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.Output;
import io.ktor.utils.io.core.OutputKt;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/core/BytePacketBuilder;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: Certificates.kt */
final class CertificatesKt$subjectAlternativeNames$1 extends Lambda implements Function1<BytePacketBuilder, Unit> {
    final /* synthetic */ List<String> $domains;
    final /* synthetic */ List<InetAddress> $ipAddresses;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CertificatesKt$subjectAlternativeNames$1(List<String> list, List<? extends InetAddress> list2) {
        super(1);
        this.$domains = list;
        this.$ipAddresses = list2;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((BytePacketBuilder) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(BytePacketBuilder bytePacketBuilder) {
        Intrinsics.checkNotNullParameter(bytePacketBuilder, "$this$writeDerSequence");
        CertificatesKt.writeDerObjectIdentifier(bytePacketBuilder, OID.Companion.getSubjectAltName());
        final List<String> list = this.$domains;
        final List<InetAddress> list2 = this.$ipAddresses;
        CertificatesKt.writeDerOctetString(bytePacketBuilder, new Function1<BytePacketBuilder, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((BytePacketBuilder) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(BytePacketBuilder bytePacketBuilder) {
                Intrinsics.checkNotNullParameter(bytePacketBuilder, "$this$writeDerOctetString");
                final List<String> list = list;
                final List<InetAddress> list2 = list2;
                CertificatesKt.writeDerSequence(bytePacketBuilder, new Function1<BytePacketBuilder, Unit>() {
                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        invoke((BytePacketBuilder) obj);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(BytePacketBuilder bytePacketBuilder) {
                        Intrinsics.checkNotNullParameter(bytePacketBuilder, "$this$writeDerSequence");
                        for (final String next : list) {
                            CertificatesKt.writeX509Extension(bytePacketBuilder, 2, new Function1<BytePacketBuilder, Unit>() {
                                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                    invoke((BytePacketBuilder) obj);
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(BytePacketBuilder bytePacketBuilder) {
                                    byte[] bArr;
                                    Intrinsics.checkNotNullParameter(bytePacketBuilder, "$this$writeX509Extension");
                                    Output output = bytePacketBuilder;
                                    String str = next;
                                    Charset charset = Charsets.UTF_8;
                                    if (Intrinsics.areEqual((Object) charset, (Object) Charsets.UTF_8)) {
                                        bArr = StringsKt.encodeToByteArray(str);
                                    } else {
                                        CharsetEncoder newEncoder = charset.newEncoder();
                                        Intrinsics.checkNotNullExpressionValue(newEncoder, "charset.newEncoder()");
                                        bArr = CharsetJVMKt.encodeToByteArray(newEncoder, str, 0, str.length());
                                    }
                                    OutputKt.writeFully$default(output, bArr, 0, 0, 6, (Object) null);
                                }
                            });
                        }
                        for (final InetAddress next2 : list2) {
                            CertificatesKt.writeX509Extension(bytePacketBuilder, 7, new Function1<BytePacketBuilder, Unit>() {
                                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                    invoke((BytePacketBuilder) obj);
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(BytePacketBuilder bytePacketBuilder) {
                                    Intrinsics.checkNotNullParameter(bytePacketBuilder, "$this$writeX509Extension");
                                    byte[] address = next2.getAddress();
                                    Intrinsics.checkNotNullExpressionValue(address, "ip.address");
                                    OutputKt.writeFully$default((Output) bytePacketBuilder, address, 0, 0, 6, (Object) null);
                                }
                            });
                        }
                    }
                });
            }
        });
    }
}
