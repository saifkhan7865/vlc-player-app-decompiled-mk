package org.videolan.resources.opensubtitles;

import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.squareup.moshi.Json;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b3\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b{\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BÕ\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\u0006\u0010\u0013\u001a\u00020\u0003\u0012\u0006\u0010\u0014\u001a\u00020\u0003\u0012\u0006\u0010\u0015\u001a\u00020\u0003\u0012\u0006\u0010\u0016\u001a\u00020\u0003\u0012\u0006\u0010\u0017\u001a\u00020\u0003\u0012\u0006\u0010\u0018\u001a\u00020\u0003\u0012\u0006\u0010\u0019\u001a\u00020\u0003\u0012\u0006\u0010\u001a\u001a\u00020\u0003\u0012\u0006\u0010\u001b\u001a\u00020\u0003\u0012\u0006\u0010\u001c\u001a\u00020\u0003\u0012\u0006\u0010\u001d\u001a\u00020\u0003\u0012\u0006\u0010\u001e\u001a\u00020\u0003\u0012\u0006\u0010\u001f\u001a\u00020\u0003\u0012\u0006\u0010 \u001a\u00020\u0003\u0012\u0006\u0010!\u001a\u00020\u0003\u0012\u0006\u0010\"\u001a\u00020\u0001\u0012\u0006\u0010#\u001a\u00020\u0003\u0012\u0006\u0010$\u001a\u00020\u0001\u0012\u0006\u0010%\u001a\u00020\u0003\u0012\u0006\u0010&\u001a\u00020\u0003\u0012\u0006\u0010'\u001a\u00020\u0003\u0012\u0006\u0010(\u001a\u00020\u0003\u0012\u0006\u0010)\u001a\u00020\u0003\u0012\u0006\u0010*\u001a\u00020\u0003\u0012\u0006\u0010+\u001a\u00020\u0003\u0012\u0006\u0010,\u001a\u00020\u0003\u0012\u0006\u0010-\u001a\u00020\u0003\u0012\u0006\u0010.\u001a\u00020\u0003\u0012\u0006\u0010/\u001a\u00020\u0003\u0012\u0006\u00100\u001a\u00020\u0003\u0012\u0006\u00101\u001a\u00020\u0003\u0012\u0006\u00102\u001a\u00020\u0003\u0012\u0006\u00103\u001a\u00020\u0003\u0012\u0006\u00104\u001a\u00020\u0003\u0012\u0006\u00105\u001a\u00020\u0003\u0012\u0006\u00106\u001a\u000207\u0012\u0006\u00108\u001a\u00020\u0003\u0012\u0006\u00109\u001a\u00020\u0003\u0012\u0006\u0010:\u001a\u00020\u0003\u0012\u0006\u0010;\u001a\u00020\u0003\u0012\u0006\u0010<\u001a\u00020\u0003\u0012\u0006\u0010=\u001a\u00020>¢\u0006\u0002\u0010?J\t\u0010~\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0001HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0001HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010 \u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010¡\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010¢\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010£\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010¤\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010¥\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010¦\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010§\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010¨\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010©\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010ª\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010«\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010¬\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010­\u0001\u001a\u000207HÆ\u0003J\n\u0010®\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010¯\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010°\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010±\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010²\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010³\u0001\u001a\u00020>HÆ\u0003J\n\u0010´\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010µ\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010¶\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010·\u0001\u001a\u00020\u0003HÆ\u0003JÎ\u0004\u0010¸\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\u00032\b\b\u0002\u0010\u0019\u001a\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u00032\b\b\u0002\u0010\u001b\u001a\u00020\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u00032\b\b\u0002\u0010\u001d\u001a\u00020\u00032\b\b\u0002\u0010\u001e\u001a\u00020\u00032\b\b\u0002\u0010\u001f\u001a\u00020\u00032\b\b\u0002\u0010 \u001a\u00020\u00032\b\b\u0002\u0010!\u001a\u00020\u00032\b\b\u0002\u0010\"\u001a\u00020\u00012\b\b\u0002\u0010#\u001a\u00020\u00032\b\b\u0002\u0010$\u001a\u00020\u00012\b\b\u0002\u0010%\u001a\u00020\u00032\b\b\u0002\u0010&\u001a\u00020\u00032\b\b\u0002\u0010'\u001a\u00020\u00032\b\b\u0002\u0010(\u001a\u00020\u00032\b\b\u0002\u0010)\u001a\u00020\u00032\b\b\u0002\u0010*\u001a\u00020\u00032\b\b\u0002\u0010+\u001a\u00020\u00032\b\b\u0002\u0010,\u001a\u00020\u00032\b\b\u0002\u0010-\u001a\u00020\u00032\b\b\u0002\u0010.\u001a\u00020\u00032\b\b\u0002\u0010/\u001a\u00020\u00032\b\b\u0002\u00100\u001a\u00020\u00032\b\b\u0002\u00101\u001a\u00020\u00032\b\b\u0002\u00102\u001a\u00020\u00032\b\b\u0002\u00103\u001a\u00020\u00032\b\b\u0002\u00104\u001a\u00020\u00032\b\b\u0002\u00105\u001a\u00020\u00032\b\b\u0002\u00106\u001a\u0002072\b\b\u0002\u00108\u001a\u00020\u00032\b\b\u0002\u00109\u001a\u00020\u00032\b\b\u0002\u0010:\u001a\u00020\u00032\b\b\u0002\u0010;\u001a\u00020\u00032\b\b\u0002\u0010<\u001a\u00020\u00032\b\b\u0002\u0010=\u001a\u00020>HÆ\u0001J\u0016\u0010¹\u0001\u001a\u00030º\u00012\t\u0010»\u0001\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u000b\u0010¼\u0001\u001a\u00030½\u0001HÖ\u0001J\n\u0010¾\u0001\u001a\u00020\u0003HÖ\u0001R\u0016\u0010(\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010AR\u0016\u0010\u001f\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bB\u0010AR\u0016\u0010 \u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bC\u0010AR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bD\u0010AR\u0016\u0010\u0012\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bE\u0010AR\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bF\u0010AR\u0016\u0010\u0010\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bG\u0010AR\u0016\u0010\u0011\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bH\u0010AR\u0016\u0010\u000f\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bI\u0010AR\u0016\u0010)\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010AR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bK\u0010AR\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bL\u0010AR\u0016\u0010\u001e\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bM\u0010AR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bN\u0010AR\u0016\u0010$\u001a\u00020\u00018\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bO\u0010PR\u0016\u0010/\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010AR\u0016\u0010!\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bR\u0010AR\u0016\u0010\"\u001a\u00020\u00018\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bS\u0010PR\u0016\u0010\u001d\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bT\u0010AR\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bU\u0010AR\u0016\u0010#\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bV\u0010AR\u0016\u0010<\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bW\u0010AR\u0016\u00106\u001a\u0002078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bX\u0010YR\u0016\u0010=\u001a\u00020>8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010[R\u0016\u0010.\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\\\u0010AR\u0016\u00101\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b]\u0010AR\u0016\u0010-\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b^\u0010AR\u0016\u0010\n\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b_\u0010AR\u0016\u0010\u0018\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b`\u0010AR\u0016\u0010\u0017\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\ba\u0010AR\u0016\u00103\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bb\u0010AR\u0016\u0010\u0019\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bc\u0010AR\u0016\u0010*\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bd\u0010AR\u0016\u00109\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\be\u0010AR\u0016\u0010\u001c\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bf\u0010AR\u0016\u00102\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bg\u0010AR\u0016\u0010%\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bh\u0010AR\u0016\u0010\t\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bi\u0010AR\u0016\u00104\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bj\u0010AR\u0016\u0010\u0015\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bk\u0010AR\u0016\u00105\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bl\u0010AR\u0016\u00100\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bm\u0010AR\u0016\u0010\f\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bn\u0010AR\u0016\u0010+\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bo\u0010AR\u0016\u0010\u0014\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bp\u0010AR\u0016\u0010\r\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bq\u0010AR\u0016\u0010\u001a\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\br\u0010AR\u0016\u0010\u000b\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bs\u0010AR\u0016\u0010\u0016\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bt\u0010AR\u0016\u0010\u001b\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bu\u0010AR\u0016\u0010\u000e\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bv\u0010AR\u0016\u00108\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bw\u0010AR\u0016\u0010'\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bx\u0010AR\u0016\u0010;\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\by\u0010AR\u0016\u0010\u0013\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bz\u0010AR\u0016\u0010&\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b{\u0010AR\u0016\u0010,\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b|\u0010AR\u0016\u0010:\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b}\u0010A¨\u0006¿\u0001"}, d2 = {"Lorg/videolan/resources/opensubtitles/OpenSubtitle;", "", "matchedBy", "", "idSubMovieFile", "movieHash", "movieByteSize", "movieTimeMS", "idSubtitleFile", "subFileName", "subActualCD", "subSize", "subHash", "subLastTS", "subTSGroup", "infoReleaseGroup", "infoFormat", "infoOther", "idSubtitle", "userID", "subLanguageID", "subFormat", "subSumCD", "subAuthorComment", "subAddDate", "subBad", "subRating", "subSumVotes", "subDownloadsCnt", "movieReleaseName", "movieFPS", "idMovie", "idMovieImdb", "movieName", "movieNameEng", "movieYear", "movieImdbRating", "subFeatured", "userNickName", "subTranslator", "iSO639", "languageName", "subComments", "subHearingImpaired", "userRank", "seriesSeason", "seriesEpisode", "movieKind", "subHD", "seriesIMDBParent", "subEncoding", "subAutoTranslation", "subForeignPartsOnly", "subFromTrusted", "queryParameters", "Lorg/videolan/resources/opensubtitles/QueryParameters;", "subTSGroupHash", "subDownloadLink", "zipDownloadLink", "subtitlesLink", "queryNumber", "score", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lorg/videolan/resources/opensubtitles/QueryParameters;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;D)V", "getISO639", "()Ljava/lang/String;", "getIdMovie", "getIdMovieImdb", "getIdSubMovieFile", "getIdSubtitle", "getIdSubtitleFile", "getInfoFormat", "getInfoOther", "getInfoReleaseGroup", "getLanguageName", "getMatchedBy", "getMovieByteSize", "getMovieFPS", "getMovieHash", "getMovieImdbRating", "()Ljava/lang/Object;", "getMovieKind", "getMovieName", "getMovieNameEng", "getMovieReleaseName", "getMovieTimeMS", "getMovieYear", "getQueryNumber", "getQueryParameters", "()Lorg/videolan/resources/opensubtitles/QueryParameters;", "getScore", "()D", "getSeriesEpisode", "getSeriesIMDBParent", "getSeriesSeason", "getSubActualCD", "getSubAddDate", "getSubAuthorComment", "getSubAutoTranslation", "getSubBad", "getSubComments", "getSubDownloadLink", "getSubDownloadsCnt", "getSubEncoding", "getSubFeatured", "getSubFileName", "getSubForeignPartsOnly", "getSubFormat", "getSubFromTrusted", "getSubHD", "getSubHash", "getSubHearingImpaired", "getSubLanguageID", "getSubLastTS", "getSubRating", "getSubSize", "getSubSumCD", "getSubSumVotes", "getSubTSGroup", "getSubTSGroupHash", "getSubTranslator", "getSubtitlesLink", "getUserID", "getUserNickName", "getUserRank", "getZipDownloadLink", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component31", "component32", "component33", "component34", "component35", "component36", "component37", "component38", "component39", "component4", "component40", "component41", "component42", "component43", "component44", "component45", "component46", "component47", "component48", "component49", "component5", "component50", "component51", "component52", "component53", "component54", "component55", "component56", "component57", "component58", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "resources_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Models.kt */
public final class OpenSubtitle {
    @Json(name = "ISO639")
    private final String iSO639;
    @Json(name = "IDMovie")
    private final String idMovie;
    @Json(name = "IDMovieImdb")
    private final String idMovieImdb;
    @Json(name = "IDSubMovieFile")
    private final String idSubMovieFile;
    @Json(name = "IDSubtitle")
    private final String idSubtitle;
    @Json(name = "IDSubtitleFile")
    private final String idSubtitleFile;
    @Json(name = "InfoFormat")
    private final String infoFormat;
    @Json(name = "InfoOther")
    private final String infoOther;
    @Json(name = "InfoReleaseGroup")
    private final String infoReleaseGroup;
    @Json(name = "LanguageName")
    private final String languageName;
    @Json(name = "MatchedBy")
    private final String matchedBy;
    @Json(name = "MovieByteSize")
    private final String movieByteSize;
    @Json(name = "MovieFPS")
    private final String movieFPS;
    @Json(name = "MovieHash")
    private final String movieHash;
    @Json(name = "MovieImdbRating")
    private final Object movieImdbRating;
    @Json(name = "MovieKind")
    private final String movieKind;
    @Json(name = "MovieName")
    private final String movieName;
    @Json(name = "MovieNameEng")
    private final Object movieNameEng;
    @Json(name = "MovieReleaseName")
    private final String movieReleaseName;
    @Json(name = "MovieTimeMS")
    private final String movieTimeMS;
    @Json(name = "MovieYear")
    private final String movieYear;
    @Json(name = "QueryNumber")
    private final String queryNumber;
    @Json(name = "QueryParameters")
    private final QueryParameters queryParameters;
    @Json(name = "Score")
    private final double score;
    @Json(name = "SeriesEpisode")
    private final String seriesEpisode;
    @Json(name = "SeriesIMDBParent")
    private final String seriesIMDBParent;
    @Json(name = "SeriesSeason")
    private final String seriesSeason;
    @Json(name = "SubActualCD")
    private final String subActualCD;
    @Json(name = "SubAddDate")
    private final String subAddDate;
    @Json(name = "SubAuthorComment")
    private final String subAuthorComment;
    @Json(name = "SubAutoTranslation")
    private final String subAutoTranslation;
    @Json(name = "SubBad")
    private final String subBad;
    @Json(name = "SubComments")
    private final String subComments;
    @Json(name = "SubDownloadLink")
    private final String subDownloadLink;
    @Json(name = "SubDownloadsCnt")
    private final String subDownloadsCnt;
    @Json(name = "SubEncoding")
    private final String subEncoding;
    @Json(name = "SubFeatured")
    private final String subFeatured;
    @Json(name = "SubFileName")
    private final String subFileName;
    @Json(name = "SubForeignPartsOnly")
    private final String subForeignPartsOnly;
    @Json(name = "SubFormat")
    private final String subFormat;
    @Json(name = "SubFromTrusted")
    private final String subFromTrusted;
    @Json(name = "SubHD")
    private final String subHD;
    @Json(name = "SubHash")
    private final String subHash;
    @Json(name = "SubHearingImpaired")
    private final String subHearingImpaired;
    @Json(name = "SubLanguageID")
    private final String subLanguageID;
    @Json(name = "SubLastTS")
    private final String subLastTS;
    @Json(name = "SubRating")
    private final String subRating;
    @Json(name = "SubSize")
    private final String subSize;
    @Json(name = "SubSumCD")
    private final String subSumCD;
    @Json(name = "SubSumVotes")
    private final String subSumVotes;
    @Json(name = "SubTSGroup")
    private final String subTSGroup;
    @Json(name = "SubTSGroupHash")
    private final String subTSGroupHash;
    @Json(name = "SubTranslator")
    private final String subTranslator;
    @Json(name = "SubtitlesLink")
    private final String subtitlesLink;
    @Json(name = "UserID")
    private final String userID;
    @Json(name = "UserNickName")
    private final String userNickName;
    @Json(name = "UserRank")
    private final String userRank;
    @Json(name = "ZipDownloadLink")
    private final String zipDownloadLink;

    public static /* synthetic */ OpenSubtitle copy$default(OpenSubtitle openSubtitle, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, String str24, String str25, String str26, String str27, String str28, String str29, String str30, String str31, Object obj, String str32, Object obj2, String str33, String str34, String str35, String str36, String str37, String str38, String str39, String str40, String str41, String str42, String str43, String str44, String str45, String str46, String str47, String str48, String str49, QueryParameters queryParameters2, String str50, String str51, String str52, String str53, String str54, double d, int i, int i2, Object obj3) {
        OpenSubtitle openSubtitle2 = openSubtitle;
        int i3 = i;
        int i4 = i2;
        return openSubtitle.copy((i3 & 1) != 0 ? openSubtitle2.matchedBy : str, (i3 & 2) != 0 ? openSubtitle2.idSubMovieFile : str2, (i3 & 4) != 0 ? openSubtitle2.movieHash : str3, (i3 & 8) != 0 ? openSubtitle2.movieByteSize : str4, (i3 & 16) != 0 ? openSubtitle2.movieTimeMS : str5, (i3 & 32) != 0 ? openSubtitle2.idSubtitleFile : str6, (i3 & 64) != 0 ? openSubtitle2.subFileName : str7, (i3 & 128) != 0 ? openSubtitle2.subActualCD : str8, (i3 & 256) != 0 ? openSubtitle2.subSize : str9, (i3 & 512) != 0 ? openSubtitle2.subHash : str10, (i3 & 1024) != 0 ? openSubtitle2.subLastTS : str11, (i3 & 2048) != 0 ? openSubtitle2.subTSGroup : str12, (i3 & 4096) != 0 ? openSubtitle2.infoReleaseGroup : str13, (i3 & 8192) != 0 ? openSubtitle2.infoFormat : str14, (i3 & 16384) != 0 ? openSubtitle2.infoOther : str15, (i3 & 32768) != 0 ? openSubtitle2.idSubtitle : str16, (i3 & 65536) != 0 ? openSubtitle2.userID : str17, (i3 & 131072) != 0 ? openSubtitle2.subLanguageID : str18, (i3 & 262144) != 0 ? openSubtitle2.subFormat : str19, (i3 & 524288) != 0 ? openSubtitle2.subSumCD : str20, (i3 & 1048576) != 0 ? openSubtitle2.subAuthorComment : str21, (i3 & 2097152) != 0 ? openSubtitle2.subAddDate : str22, (i3 & 4194304) != 0 ? openSubtitle2.subBad : str23, (i3 & 8388608) != 0 ? openSubtitle2.subRating : str24, (i3 & 16777216) != 0 ? openSubtitle2.subSumVotes : str25, (i3 & 33554432) != 0 ? openSubtitle2.subDownloadsCnt : str26, (i3 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? openSubtitle2.movieReleaseName : str27, (i3 & 134217728) != 0 ? openSubtitle2.movieFPS : str28, (i3 & 268435456) != 0 ? openSubtitle2.idMovie : str29, (i3 & PKIFailureInfo.duplicateCertReq) != 0 ? openSubtitle2.idMovieImdb : str30, (i3 & 1073741824) != 0 ? openSubtitle2.movieName : str31, (i3 & Integer.MIN_VALUE) != 0 ? openSubtitle2.movieNameEng : obj, (i4 & 1) != 0 ? openSubtitle2.movieYear : str32, (i4 & 2) != 0 ? openSubtitle2.movieImdbRating : obj2, (i4 & 4) != 0 ? openSubtitle2.subFeatured : str33, (i4 & 8) != 0 ? openSubtitle2.userNickName : str34, (i4 & 16) != 0 ? openSubtitle2.subTranslator : str35, (i4 & 32) != 0 ? openSubtitle2.iSO639 : str36, (i4 & 64) != 0 ? openSubtitle2.languageName : str37, (i4 & 128) != 0 ? openSubtitle2.subComments : str38, (i4 & 256) != 0 ? openSubtitle2.subHearingImpaired : str39, (i4 & 512) != 0 ? openSubtitle2.userRank : str40, (i4 & 1024) != 0 ? openSubtitle2.seriesSeason : str41, (i4 & 2048) != 0 ? openSubtitle2.seriesEpisode : str42, (i4 & 4096) != 0 ? openSubtitle2.movieKind : str43, (i4 & 8192) != 0 ? openSubtitle2.subHD : str44, (i4 & 16384) != 0 ? openSubtitle2.seriesIMDBParent : str45, (i4 & 32768) != 0 ? openSubtitle2.subEncoding : str46, (i4 & 65536) != 0 ? openSubtitle2.subAutoTranslation : str47, (i4 & 131072) != 0 ? openSubtitle2.subForeignPartsOnly : str48, (i4 & 262144) != 0 ? openSubtitle2.subFromTrusted : str49, (i4 & 524288) != 0 ? openSubtitle2.queryParameters : queryParameters2, (i4 & 1048576) != 0 ? openSubtitle2.subTSGroupHash : str50, (i4 & 2097152) != 0 ? openSubtitle2.subDownloadLink : str51, (i4 & 4194304) != 0 ? openSubtitle2.zipDownloadLink : str52, (i4 & 8388608) != 0 ? openSubtitle2.subtitlesLink : str53, (i4 & 16777216) != 0 ? openSubtitle2.queryNumber : str54, (i4 & 33554432) != 0 ? openSubtitle2.score : d);
    }

    public final String component1() {
        return this.matchedBy;
    }

    public final String component10() {
        return this.subHash;
    }

    public final String component11() {
        return this.subLastTS;
    }

    public final String component12() {
        return this.subTSGroup;
    }

    public final String component13() {
        return this.infoReleaseGroup;
    }

    public final String component14() {
        return this.infoFormat;
    }

    public final String component15() {
        return this.infoOther;
    }

    public final String component16() {
        return this.idSubtitle;
    }

    public final String component17() {
        return this.userID;
    }

    public final String component18() {
        return this.subLanguageID;
    }

    public final String component19() {
        return this.subFormat;
    }

    public final String component2() {
        return this.idSubMovieFile;
    }

    public final String component20() {
        return this.subSumCD;
    }

    public final String component21() {
        return this.subAuthorComment;
    }

    public final String component22() {
        return this.subAddDate;
    }

    public final String component23() {
        return this.subBad;
    }

    public final String component24() {
        return this.subRating;
    }

    public final String component25() {
        return this.subSumVotes;
    }

    public final String component26() {
        return this.subDownloadsCnt;
    }

    public final String component27() {
        return this.movieReleaseName;
    }

    public final String component28() {
        return this.movieFPS;
    }

    public final String component29() {
        return this.idMovie;
    }

    public final String component3() {
        return this.movieHash;
    }

    public final String component30() {
        return this.idMovieImdb;
    }

    public final String component31() {
        return this.movieName;
    }

    public final Object component32() {
        return this.movieNameEng;
    }

    public final String component33() {
        return this.movieYear;
    }

    public final Object component34() {
        return this.movieImdbRating;
    }

    public final String component35() {
        return this.subFeatured;
    }

    public final String component36() {
        return this.userNickName;
    }

    public final String component37() {
        return this.subTranslator;
    }

    public final String component38() {
        return this.iSO639;
    }

    public final String component39() {
        return this.languageName;
    }

    public final String component4() {
        return this.movieByteSize;
    }

    public final String component40() {
        return this.subComments;
    }

    public final String component41() {
        return this.subHearingImpaired;
    }

    public final String component42() {
        return this.userRank;
    }

    public final String component43() {
        return this.seriesSeason;
    }

    public final String component44() {
        return this.seriesEpisode;
    }

    public final String component45() {
        return this.movieKind;
    }

    public final String component46() {
        return this.subHD;
    }

    public final String component47() {
        return this.seriesIMDBParent;
    }

    public final String component48() {
        return this.subEncoding;
    }

    public final String component49() {
        return this.subAutoTranslation;
    }

    public final String component5() {
        return this.movieTimeMS;
    }

    public final String component50() {
        return this.subForeignPartsOnly;
    }

    public final String component51() {
        return this.subFromTrusted;
    }

    public final QueryParameters component52() {
        return this.queryParameters;
    }

    public final String component53() {
        return this.subTSGroupHash;
    }

    public final String component54() {
        return this.subDownloadLink;
    }

    public final String component55() {
        return this.zipDownloadLink;
    }

    public final String component56() {
        return this.subtitlesLink;
    }

    public final String component57() {
        return this.queryNumber;
    }

    public final double component58() {
        return this.score;
    }

    public final String component6() {
        return this.idSubtitleFile;
    }

    public final String component7() {
        return this.subFileName;
    }

    public final String component8() {
        return this.subActualCD;
    }

    public final String component9() {
        return this.subSize;
    }

    public final OpenSubtitle copy(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, String str24, String str25, String str26, String str27, String str28, String str29, String str30, String str31, Object obj, String str32, Object obj2, String str33, String str34, String str35, String str36, String str37, String str38, String str39, String str40, String str41, String str42, String str43, String str44, String str45, String str46, String str47, String str48, String str49, QueryParameters queryParameters2, String str50, String str51, String str52, String str53, String str54, double d) {
        String str55 = str;
        Intrinsics.checkNotNullParameter(str55, "matchedBy");
        Intrinsics.checkNotNullParameter(str2, "idSubMovieFile");
        Intrinsics.checkNotNullParameter(str3, "movieHash");
        Intrinsics.checkNotNullParameter(str4, "movieByteSize");
        Intrinsics.checkNotNullParameter(str5, "movieTimeMS");
        Intrinsics.checkNotNullParameter(str6, "idSubtitleFile");
        Intrinsics.checkNotNullParameter(str7, "subFileName");
        Intrinsics.checkNotNullParameter(str8, "subActualCD");
        Intrinsics.checkNotNullParameter(str9, "subSize");
        Intrinsics.checkNotNullParameter(str10, "subHash");
        Intrinsics.checkNotNullParameter(str11, "subLastTS");
        Intrinsics.checkNotNullParameter(str12, "subTSGroup");
        Intrinsics.checkNotNullParameter(str13, "infoReleaseGroup");
        Intrinsics.checkNotNullParameter(str14, "infoFormat");
        Intrinsics.checkNotNullParameter(str15, "infoOther");
        Intrinsics.checkNotNullParameter(str16, "idSubtitle");
        Intrinsics.checkNotNullParameter(str17, "userID");
        Intrinsics.checkNotNullParameter(str18, "subLanguageID");
        Intrinsics.checkNotNullParameter(str19, "subFormat");
        Intrinsics.checkNotNullParameter(str20, "subSumCD");
        Intrinsics.checkNotNullParameter(str21, "subAuthorComment");
        Intrinsics.checkNotNullParameter(str22, "subAddDate");
        Intrinsics.checkNotNullParameter(str23, "subBad");
        Intrinsics.checkNotNullParameter(str24, "subRating");
        Intrinsics.checkNotNullParameter(str25, "subSumVotes");
        Intrinsics.checkNotNullParameter(str26, "subDownloadsCnt");
        Intrinsics.checkNotNullParameter(str27, "movieReleaseName");
        Intrinsics.checkNotNullParameter(str28, "movieFPS");
        Intrinsics.checkNotNullParameter(str29, "idMovie");
        Intrinsics.checkNotNullParameter(str30, "idMovieImdb");
        Intrinsics.checkNotNullParameter(str31, "movieName");
        Intrinsics.checkNotNullParameter(obj, "movieNameEng");
        Intrinsics.checkNotNullParameter(str32, "movieYear");
        Intrinsics.checkNotNullParameter(obj2, "movieImdbRating");
        Intrinsics.checkNotNullParameter(str33, "subFeatured");
        Intrinsics.checkNotNullParameter(str34, "userNickName");
        Intrinsics.checkNotNullParameter(str35, "subTranslator");
        Intrinsics.checkNotNullParameter(str36, "iSO639");
        Intrinsics.checkNotNullParameter(str37, "languageName");
        Intrinsics.checkNotNullParameter(str38, "subComments");
        Intrinsics.checkNotNullParameter(str39, "subHearingImpaired");
        Intrinsics.checkNotNullParameter(str40, "userRank");
        Intrinsics.checkNotNullParameter(str41, "seriesSeason");
        Intrinsics.checkNotNullParameter(str42, "seriesEpisode");
        Intrinsics.checkNotNullParameter(str43, "movieKind");
        Intrinsics.checkNotNullParameter(str44, "subHD");
        Intrinsics.checkNotNullParameter(str45, "seriesIMDBParent");
        Intrinsics.checkNotNullParameter(str46, "subEncoding");
        Intrinsics.checkNotNullParameter(str47, "subAutoTranslation");
        Intrinsics.checkNotNullParameter(str48, "subForeignPartsOnly");
        Intrinsics.checkNotNullParameter(str49, "subFromTrusted");
        Intrinsics.checkNotNullParameter(queryParameters2, "queryParameters");
        Intrinsics.checkNotNullParameter(str50, "subTSGroupHash");
        Intrinsics.checkNotNullParameter(str51, "subDownloadLink");
        Intrinsics.checkNotNullParameter(str52, "zipDownloadLink");
        Intrinsics.checkNotNullParameter(str53, "subtitlesLink");
        Intrinsics.checkNotNullParameter(str54, "queryNumber");
        return new OpenSubtitle(str55, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, str14, str15, str16, str17, str18, str19, str20, str21, str22, str23, str24, str25, str26, str27, str28, str29, str30, str31, obj, str32, obj2, str33, str34, str35, str36, str37, str38, str39, str40, str41, str42, str43, str44, str45, str46, str47, str48, str49, queryParameters2, str50, str51, str52, str53, str54, d);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OpenSubtitle)) {
            return false;
        }
        OpenSubtitle openSubtitle = (OpenSubtitle) obj;
        return Intrinsics.areEqual((Object) this.matchedBy, (Object) openSubtitle.matchedBy) && Intrinsics.areEqual((Object) this.idSubMovieFile, (Object) openSubtitle.idSubMovieFile) && Intrinsics.areEqual((Object) this.movieHash, (Object) openSubtitle.movieHash) && Intrinsics.areEqual((Object) this.movieByteSize, (Object) openSubtitle.movieByteSize) && Intrinsics.areEqual((Object) this.movieTimeMS, (Object) openSubtitle.movieTimeMS) && Intrinsics.areEqual((Object) this.idSubtitleFile, (Object) openSubtitle.idSubtitleFile) && Intrinsics.areEqual((Object) this.subFileName, (Object) openSubtitle.subFileName) && Intrinsics.areEqual((Object) this.subActualCD, (Object) openSubtitle.subActualCD) && Intrinsics.areEqual((Object) this.subSize, (Object) openSubtitle.subSize) && Intrinsics.areEqual((Object) this.subHash, (Object) openSubtitle.subHash) && Intrinsics.areEqual((Object) this.subLastTS, (Object) openSubtitle.subLastTS) && Intrinsics.areEqual((Object) this.subTSGroup, (Object) openSubtitle.subTSGroup) && Intrinsics.areEqual((Object) this.infoReleaseGroup, (Object) openSubtitle.infoReleaseGroup) && Intrinsics.areEqual((Object) this.infoFormat, (Object) openSubtitle.infoFormat) && Intrinsics.areEqual((Object) this.infoOther, (Object) openSubtitle.infoOther) && Intrinsics.areEqual((Object) this.idSubtitle, (Object) openSubtitle.idSubtitle) && Intrinsics.areEqual((Object) this.userID, (Object) openSubtitle.userID) && Intrinsics.areEqual((Object) this.subLanguageID, (Object) openSubtitle.subLanguageID) && Intrinsics.areEqual((Object) this.subFormat, (Object) openSubtitle.subFormat) && Intrinsics.areEqual((Object) this.subSumCD, (Object) openSubtitle.subSumCD) && Intrinsics.areEqual((Object) this.subAuthorComment, (Object) openSubtitle.subAuthorComment) && Intrinsics.areEqual((Object) this.subAddDate, (Object) openSubtitle.subAddDate) && Intrinsics.areEqual((Object) this.subBad, (Object) openSubtitle.subBad) && Intrinsics.areEqual((Object) this.subRating, (Object) openSubtitle.subRating) && Intrinsics.areEqual((Object) this.subSumVotes, (Object) openSubtitle.subSumVotes) && Intrinsics.areEqual((Object) this.subDownloadsCnt, (Object) openSubtitle.subDownloadsCnt) && Intrinsics.areEqual((Object) this.movieReleaseName, (Object) openSubtitle.movieReleaseName) && Intrinsics.areEqual((Object) this.movieFPS, (Object) openSubtitle.movieFPS) && Intrinsics.areEqual((Object) this.idMovie, (Object) openSubtitle.idMovie) && Intrinsics.areEqual((Object) this.idMovieImdb, (Object) openSubtitle.idMovieImdb) && Intrinsics.areEqual((Object) this.movieName, (Object) openSubtitle.movieName) && Intrinsics.areEqual(this.movieNameEng, openSubtitle.movieNameEng) && Intrinsics.areEqual((Object) this.movieYear, (Object) openSubtitle.movieYear) && Intrinsics.areEqual(this.movieImdbRating, openSubtitle.movieImdbRating) && Intrinsics.areEqual((Object) this.subFeatured, (Object) openSubtitle.subFeatured) && Intrinsics.areEqual((Object) this.userNickName, (Object) openSubtitle.userNickName) && Intrinsics.areEqual((Object) this.subTranslator, (Object) openSubtitle.subTranslator) && Intrinsics.areEqual((Object) this.iSO639, (Object) openSubtitle.iSO639) && Intrinsics.areEqual((Object) this.languageName, (Object) openSubtitle.languageName) && Intrinsics.areEqual((Object) this.subComments, (Object) openSubtitle.subComments) && Intrinsics.areEqual((Object) this.subHearingImpaired, (Object) openSubtitle.subHearingImpaired) && Intrinsics.areEqual((Object) this.userRank, (Object) openSubtitle.userRank) && Intrinsics.areEqual((Object) this.seriesSeason, (Object) openSubtitle.seriesSeason) && Intrinsics.areEqual((Object) this.seriesEpisode, (Object) openSubtitle.seriesEpisode) && Intrinsics.areEqual((Object) this.movieKind, (Object) openSubtitle.movieKind) && Intrinsics.areEqual((Object) this.subHD, (Object) openSubtitle.subHD) && Intrinsics.areEqual((Object) this.seriesIMDBParent, (Object) openSubtitle.seriesIMDBParent) && Intrinsics.areEqual((Object) this.subEncoding, (Object) openSubtitle.subEncoding) && Intrinsics.areEqual((Object) this.subAutoTranslation, (Object) openSubtitle.subAutoTranslation) && Intrinsics.areEqual((Object) this.subForeignPartsOnly, (Object) openSubtitle.subForeignPartsOnly) && Intrinsics.areEqual((Object) this.subFromTrusted, (Object) openSubtitle.subFromTrusted) && Intrinsics.areEqual((Object) this.queryParameters, (Object) openSubtitle.queryParameters) && Intrinsics.areEqual((Object) this.subTSGroupHash, (Object) openSubtitle.subTSGroupHash) && Intrinsics.areEqual((Object) this.subDownloadLink, (Object) openSubtitle.subDownloadLink) && Intrinsics.areEqual((Object) this.zipDownloadLink, (Object) openSubtitle.zipDownloadLink) && Intrinsics.areEqual((Object) this.subtitlesLink, (Object) openSubtitle.subtitlesLink) && Intrinsics.areEqual((Object) this.queryNumber, (Object) openSubtitle.queryNumber) && Double.compare(this.score, openSubtitle.score) == 0;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((this.matchedBy.hashCode() * 31) + this.idSubMovieFile.hashCode()) * 31) + this.movieHash.hashCode()) * 31) + this.movieByteSize.hashCode()) * 31) + this.movieTimeMS.hashCode()) * 31) + this.idSubtitleFile.hashCode()) * 31) + this.subFileName.hashCode()) * 31) + this.subActualCD.hashCode()) * 31) + this.subSize.hashCode()) * 31) + this.subHash.hashCode()) * 31) + this.subLastTS.hashCode()) * 31) + this.subTSGroup.hashCode()) * 31) + this.infoReleaseGroup.hashCode()) * 31) + this.infoFormat.hashCode()) * 31) + this.infoOther.hashCode()) * 31) + this.idSubtitle.hashCode()) * 31) + this.userID.hashCode()) * 31) + this.subLanguageID.hashCode()) * 31) + this.subFormat.hashCode()) * 31) + this.subSumCD.hashCode()) * 31) + this.subAuthorComment.hashCode()) * 31) + this.subAddDate.hashCode()) * 31) + this.subBad.hashCode()) * 31) + this.subRating.hashCode()) * 31) + this.subSumVotes.hashCode()) * 31) + this.subDownloadsCnt.hashCode()) * 31) + this.movieReleaseName.hashCode()) * 31) + this.movieFPS.hashCode()) * 31) + this.idMovie.hashCode()) * 31) + this.idMovieImdb.hashCode()) * 31) + this.movieName.hashCode()) * 31) + this.movieNameEng.hashCode()) * 31) + this.movieYear.hashCode()) * 31) + this.movieImdbRating.hashCode()) * 31) + this.subFeatured.hashCode()) * 31) + this.userNickName.hashCode()) * 31) + this.subTranslator.hashCode()) * 31) + this.iSO639.hashCode()) * 31) + this.languageName.hashCode()) * 31) + this.subComments.hashCode()) * 31) + this.subHearingImpaired.hashCode()) * 31) + this.userRank.hashCode()) * 31) + this.seriesSeason.hashCode()) * 31) + this.seriesEpisode.hashCode()) * 31) + this.movieKind.hashCode()) * 31) + this.subHD.hashCode()) * 31) + this.seriesIMDBParent.hashCode()) * 31) + this.subEncoding.hashCode()) * 31) + this.subAutoTranslation.hashCode()) * 31) + this.subForeignPartsOnly.hashCode()) * 31) + this.subFromTrusted.hashCode()) * 31) + this.queryParameters.hashCode()) * 31) + this.subTSGroupHash.hashCode()) * 31) + this.subDownloadLink.hashCode()) * 31) + this.zipDownloadLink.hashCode()) * 31) + this.subtitlesLink.hashCode()) * 31) + this.queryNumber.hashCode()) * 31) + Double.doubleToLongBits(this.score);
    }

    public String toString() {
        return "OpenSubtitle(matchedBy=" + this.matchedBy + ", idSubMovieFile=" + this.idSubMovieFile + ", movieHash=" + this.movieHash + ", movieByteSize=" + this.movieByteSize + ", movieTimeMS=" + this.movieTimeMS + ", idSubtitleFile=" + this.idSubtitleFile + ", subFileName=" + this.subFileName + ", subActualCD=" + this.subActualCD + ", subSize=" + this.subSize + ", subHash=" + this.subHash + ", subLastTS=" + this.subLastTS + ", subTSGroup=" + this.subTSGroup + ", infoReleaseGroup=" + this.infoReleaseGroup + ", infoFormat=" + this.infoFormat + ", infoOther=" + this.infoOther + ", idSubtitle=" + this.idSubtitle + ", userID=" + this.userID + ", subLanguageID=" + this.subLanguageID + ", subFormat=" + this.subFormat + ", subSumCD=" + this.subSumCD + ", subAuthorComment=" + this.subAuthorComment + ", subAddDate=" + this.subAddDate + ", subBad=" + this.subBad + ", subRating=" + this.subRating + ", subSumVotes=" + this.subSumVotes + ", subDownloadsCnt=" + this.subDownloadsCnt + ", movieReleaseName=" + this.movieReleaseName + ", movieFPS=" + this.movieFPS + ", idMovie=" + this.idMovie + ", idMovieImdb=" + this.idMovieImdb + ", movieName=" + this.movieName + ", movieNameEng=" + this.movieNameEng + ", movieYear=" + this.movieYear + ", movieImdbRating=" + this.movieImdbRating + ", subFeatured=" + this.subFeatured + ", userNickName=" + this.userNickName + ", subTranslator=" + this.subTranslator + ", iSO639=" + this.iSO639 + ", languageName=" + this.languageName + ", subComments=" + this.subComments + ", subHearingImpaired=" + this.subHearingImpaired + ", userRank=" + this.userRank + ", seriesSeason=" + this.seriesSeason + ", seriesEpisode=" + this.seriesEpisode + ", movieKind=" + this.movieKind + ", subHD=" + this.subHD + ", seriesIMDBParent=" + this.seriesIMDBParent + ", subEncoding=" + this.subEncoding + ", subAutoTranslation=" + this.subAutoTranslation + ", subForeignPartsOnly=" + this.subForeignPartsOnly + ", subFromTrusted=" + this.subFromTrusted + ", queryParameters=" + this.queryParameters + ", subTSGroupHash=" + this.subTSGroupHash + ", subDownloadLink=" + this.subDownloadLink + ", zipDownloadLink=" + this.zipDownloadLink + ", subtitlesLink=" + this.subtitlesLink + ", queryNumber=" + this.queryNumber + ", score=" + this.score + ')';
    }

    public OpenSubtitle(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, String str24, String str25, String str26, String str27, String str28, String str29, String str30, String str31, Object obj, String str32, Object obj2, String str33, String str34, String str35, String str36, String str37, String str38, String str39, String str40, String str41, String str42, String str43, String str44, String str45, String str46, String str47, String str48, String str49, QueryParameters queryParameters2, String str50, String str51, String str52, String str53, String str54, double d) {
        String str55 = str;
        String str56 = str2;
        String str57 = str3;
        String str58 = str4;
        String str59 = str5;
        String str60 = str6;
        String str61 = str7;
        String str62 = str8;
        String str63 = str9;
        String str64 = str10;
        String str65 = str11;
        String str66 = str12;
        String str67 = str13;
        String str68 = str14;
        String str69 = str16;
        Intrinsics.checkNotNullParameter(str55, "matchedBy");
        Intrinsics.checkNotNullParameter(str56, "idSubMovieFile");
        Intrinsics.checkNotNullParameter(str57, "movieHash");
        Intrinsics.checkNotNullParameter(str58, "movieByteSize");
        Intrinsics.checkNotNullParameter(str59, "movieTimeMS");
        Intrinsics.checkNotNullParameter(str60, "idSubtitleFile");
        Intrinsics.checkNotNullParameter(str61, "subFileName");
        Intrinsics.checkNotNullParameter(str62, "subActualCD");
        Intrinsics.checkNotNullParameter(str63, "subSize");
        Intrinsics.checkNotNullParameter(str64, "subHash");
        Intrinsics.checkNotNullParameter(str65, "subLastTS");
        Intrinsics.checkNotNullParameter(str66, "subTSGroup");
        Intrinsics.checkNotNullParameter(str67, "infoReleaseGroup");
        Intrinsics.checkNotNullParameter(str68, "infoFormat");
        Intrinsics.checkNotNullParameter(str15, "infoOther");
        Intrinsics.checkNotNullParameter(str16, "idSubtitle");
        Intrinsics.checkNotNullParameter(str17, "userID");
        Intrinsics.checkNotNullParameter(str18, "subLanguageID");
        Intrinsics.checkNotNullParameter(str19, "subFormat");
        Intrinsics.checkNotNullParameter(str20, "subSumCD");
        Intrinsics.checkNotNullParameter(str21, "subAuthorComment");
        Intrinsics.checkNotNullParameter(str22, "subAddDate");
        Intrinsics.checkNotNullParameter(str23, "subBad");
        Intrinsics.checkNotNullParameter(str24, "subRating");
        Intrinsics.checkNotNullParameter(str25, "subSumVotes");
        Intrinsics.checkNotNullParameter(str26, "subDownloadsCnt");
        Intrinsics.checkNotNullParameter(str27, "movieReleaseName");
        Intrinsics.checkNotNullParameter(str28, "movieFPS");
        Intrinsics.checkNotNullParameter(str29, "idMovie");
        Intrinsics.checkNotNullParameter(str30, "idMovieImdb");
        Intrinsics.checkNotNullParameter(str31, "movieName");
        Intrinsics.checkNotNullParameter(obj, "movieNameEng");
        Intrinsics.checkNotNullParameter(str32, "movieYear");
        Intrinsics.checkNotNullParameter(obj2, "movieImdbRating");
        Intrinsics.checkNotNullParameter(str33, "subFeatured");
        Intrinsics.checkNotNullParameter(str34, "userNickName");
        Intrinsics.checkNotNullParameter(str35, "subTranslator");
        Intrinsics.checkNotNullParameter(str36, "iSO639");
        Intrinsics.checkNotNullParameter(str37, "languageName");
        Intrinsics.checkNotNullParameter(str38, "subComments");
        Intrinsics.checkNotNullParameter(str39, "subHearingImpaired");
        Intrinsics.checkNotNullParameter(str40, "userRank");
        Intrinsics.checkNotNullParameter(str41, "seriesSeason");
        Intrinsics.checkNotNullParameter(str42, "seriesEpisode");
        Intrinsics.checkNotNullParameter(str43, "movieKind");
        Intrinsics.checkNotNullParameter(str44, "subHD");
        Intrinsics.checkNotNullParameter(str45, "seriesIMDBParent");
        Intrinsics.checkNotNullParameter(str46, "subEncoding");
        Intrinsics.checkNotNullParameter(str47, "subAutoTranslation");
        Intrinsics.checkNotNullParameter(str48, "subForeignPartsOnly");
        Intrinsics.checkNotNullParameter(str49, "subFromTrusted");
        Intrinsics.checkNotNullParameter(queryParameters2, "queryParameters");
        Intrinsics.checkNotNullParameter(str50, "subTSGroupHash");
        Intrinsics.checkNotNullParameter(str51, "subDownloadLink");
        Intrinsics.checkNotNullParameter(str52, "zipDownloadLink");
        Intrinsics.checkNotNullParameter(str53, "subtitlesLink");
        Intrinsics.checkNotNullParameter(str54, "queryNumber");
        this.matchedBy = str55;
        this.idSubMovieFile = str56;
        this.movieHash = str57;
        this.movieByteSize = str58;
        this.movieTimeMS = str59;
        this.idSubtitleFile = str60;
        this.subFileName = str61;
        this.subActualCD = str62;
        this.subSize = str63;
        this.subHash = str64;
        this.subLastTS = str65;
        this.subTSGroup = str66;
        this.infoReleaseGroup = str67;
        this.infoFormat = str68;
        this.infoOther = str15;
        this.idSubtitle = str16;
        this.userID = str17;
        this.subLanguageID = str18;
        this.subFormat = str19;
        this.subSumCD = str20;
        this.subAuthorComment = str21;
        this.subAddDate = str22;
        this.subBad = str23;
        this.subRating = str24;
        this.subSumVotes = str25;
        this.subDownloadsCnt = str26;
        this.movieReleaseName = str27;
        this.movieFPS = str28;
        this.idMovie = str29;
        this.idMovieImdb = str30;
        this.movieName = str31;
        this.movieNameEng = obj;
        this.movieYear = str32;
        this.movieImdbRating = obj2;
        this.subFeatured = str33;
        this.userNickName = str34;
        this.subTranslator = str35;
        this.iSO639 = str36;
        this.languageName = str37;
        this.subComments = str38;
        this.subHearingImpaired = str39;
        this.userRank = str40;
        this.seriesSeason = str41;
        this.seriesEpisode = str42;
        this.movieKind = str43;
        this.subHD = str44;
        this.seriesIMDBParent = str45;
        this.subEncoding = str46;
        this.subAutoTranslation = str47;
        this.subForeignPartsOnly = str48;
        this.subFromTrusted = str49;
        this.queryParameters = queryParameters2;
        this.subTSGroupHash = str50;
        this.subDownloadLink = str51;
        this.zipDownloadLink = str52;
        this.subtitlesLink = str53;
        this.queryNumber = str54;
        this.score = d;
    }

    public final String getMatchedBy() {
        return this.matchedBy;
    }

    public final String getIdSubMovieFile() {
        return this.idSubMovieFile;
    }

    public final String getMovieHash() {
        return this.movieHash;
    }

    public final String getMovieByteSize() {
        return this.movieByteSize;
    }

    public final String getMovieTimeMS() {
        return this.movieTimeMS;
    }

    public final String getIdSubtitleFile() {
        return this.idSubtitleFile;
    }

    public final String getSubFileName() {
        return this.subFileName;
    }

    public final String getSubActualCD() {
        return this.subActualCD;
    }

    public final String getSubSize() {
        return this.subSize;
    }

    public final String getSubHash() {
        return this.subHash;
    }

    public final String getSubLastTS() {
        return this.subLastTS;
    }

    public final String getSubTSGroup() {
        return this.subTSGroup;
    }

    public final String getInfoReleaseGroup() {
        return this.infoReleaseGroup;
    }

    public final String getInfoFormat() {
        return this.infoFormat;
    }

    public final String getInfoOther() {
        return this.infoOther;
    }

    public final String getIdSubtitle() {
        return this.idSubtitle;
    }

    public final String getUserID() {
        return this.userID;
    }

    public final String getSubLanguageID() {
        return this.subLanguageID;
    }

    public final String getSubFormat() {
        return this.subFormat;
    }

    public final String getSubSumCD() {
        return this.subSumCD;
    }

    public final String getSubAuthorComment() {
        return this.subAuthorComment;
    }

    public final String getSubAddDate() {
        return this.subAddDate;
    }

    public final String getSubBad() {
        return this.subBad;
    }

    public final String getSubRating() {
        return this.subRating;
    }

    public final String getSubSumVotes() {
        return this.subSumVotes;
    }

    public final String getSubDownloadsCnt() {
        return this.subDownloadsCnt;
    }

    public final String getMovieReleaseName() {
        return this.movieReleaseName;
    }

    public final String getMovieFPS() {
        return this.movieFPS;
    }

    public final String getIdMovie() {
        return this.idMovie;
    }

    public final String getIdMovieImdb() {
        return this.idMovieImdb;
    }

    public final String getMovieName() {
        return this.movieName;
    }

    public final Object getMovieNameEng() {
        return this.movieNameEng;
    }

    public final String getMovieYear() {
        return this.movieYear;
    }

    public final Object getMovieImdbRating() {
        return this.movieImdbRating;
    }

    public final String getSubFeatured() {
        return this.subFeatured;
    }

    public final String getUserNickName() {
        return this.userNickName;
    }

    public final String getSubTranslator() {
        return this.subTranslator;
    }

    public final String getISO639() {
        return this.iSO639;
    }

    public final String getLanguageName() {
        return this.languageName;
    }

    public final String getSubComments() {
        return this.subComments;
    }

    public final String getSubHearingImpaired() {
        return this.subHearingImpaired;
    }

    public final String getUserRank() {
        return this.userRank;
    }

    public final String getSeriesSeason() {
        return this.seriesSeason;
    }

    public final String getSeriesEpisode() {
        return this.seriesEpisode;
    }

    public final String getMovieKind() {
        return this.movieKind;
    }

    public final String getSubHD() {
        return this.subHD;
    }

    public final String getSeriesIMDBParent() {
        return this.seriesIMDBParent;
    }

    public final String getSubEncoding() {
        return this.subEncoding;
    }

    public final String getSubAutoTranslation() {
        return this.subAutoTranslation;
    }

    public final String getSubForeignPartsOnly() {
        return this.subForeignPartsOnly;
    }

    public final String getSubFromTrusted() {
        return this.subFromTrusted;
    }

    public final QueryParameters getQueryParameters() {
        return this.queryParameters;
    }

    public final String getSubTSGroupHash() {
        return this.subTSGroupHash;
    }

    public final String getSubDownloadLink() {
        return this.subDownloadLink;
    }

    public final String getZipDownloadLink() {
        return this.zipDownloadLink;
    }

    public final String getSubtitlesLink() {
        return this.subtitlesLink;
    }

    public final String getQueryNumber() {
        return this.queryNumber;
    }

    public final double getScore() {
        return this.score;
    }
}
