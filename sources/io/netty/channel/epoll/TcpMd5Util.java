package io.netty.channel.epoll;

import androidx.tvprovider.media.tv.TvContractCompat;
import io.netty.util.internal.ObjectUtil;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

final class TcpMd5Util {
    static Collection<InetAddress> newTcpMd5Sigs(AbstractEpollChannel abstractEpollChannel, Collection<InetAddress> collection, Map<InetAddress, byte[]> map) throws IOException {
        ObjectUtil.checkNotNull(abstractEpollChannel, TvContractCompat.PARAM_CHANNEL);
        ObjectUtil.checkNotNull(collection, "current");
        ObjectUtil.checkNotNull(map, "newKeys");
        for (Map.Entry next : map.entrySet()) {
            byte[] bArr = (byte[]) next.getValue();
            ObjectUtil.checkNotNullWithIAE(next.getKey(), "e.getKey");
            ObjectUtil.checkNonEmpty(bArr, ((InetAddress) next.getKey()).toString());
            if (bArr.length > Native.TCP_MD5SIG_MAXKEYLEN) {
                throw new IllegalArgumentException("newKeys[" + next.getKey() + "] has a key with invalid length; should not exceed the maximum length (" + Native.TCP_MD5SIG_MAXKEYLEN + ')');
            }
        }
        for (InetAddress next2 : collection) {
            if (!map.containsKey(next2)) {
                abstractEpollChannel.socket.setTcpMd5Sig(next2, (byte[]) null);
            }
        }
        if (map.isEmpty()) {
            return Collections.emptySet();
        }
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry next3 : map.entrySet()) {
            abstractEpollChannel.socket.setTcpMd5Sig((InetAddress) next3.getKey(), (byte[]) next3.getValue());
            arrayList.add(next3.getKey());
        }
        return arrayList;
    }

    private TcpMd5Util() {
    }
}
