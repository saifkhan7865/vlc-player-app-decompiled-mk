package com.google.zxing.client.result;

import com.google.zxing.Result;
import io.netty.util.internal.StringUtil;
import java.util.ArrayList;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public final class AddressBookAUResultParser extends ResultParser {
    public AddressBookParsedResult parse(Result result) {
        String massagedText = getMassagedText(result);
        String[] strArr = null;
        if (!massagedText.contains("MEMORY") || !massagedText.contains("\r\n")) {
            return null;
        }
        String matchSinglePrefixedField = matchSinglePrefixedField("NAME1:", massagedText, StringUtil.CARRIAGE_RETURN, true);
        String matchSinglePrefixedField2 = matchSinglePrefixedField("NAME2:", massagedText, StringUtil.CARRIAGE_RETURN, true);
        String[] matchMultipleValuePrefix = matchMultipleValuePrefix("TEL", 3, massagedText, true);
        String[] matchMultipleValuePrefix2 = matchMultipleValuePrefix("MAIL", 3, massagedText, true);
        String matchSinglePrefixedField3 = matchSinglePrefixedField("MEMORY:", massagedText, StringUtil.CARRIAGE_RETURN, false);
        String matchSinglePrefixedField4 = matchSinglePrefixedField("ADD:", massagedText, StringUtil.CARRIAGE_RETURN, true);
        if (matchSinglePrefixedField4 != null) {
            strArr = new String[]{matchSinglePrefixedField4};
        }
        return new AddressBookParsedResult(maybeWrap(matchSinglePrefixedField), (String[]) null, matchSinglePrefixedField2, matchMultipleValuePrefix, (String[]) null, matchMultipleValuePrefix2, (String[]) null, (String) null, matchSinglePrefixedField3, strArr, (String[]) null, (String) null, (String) null, (String) null, (String[]) null, (String[]) null);
    }

    private static String[] matchMultipleValuePrefix(String str, int i, String str2, boolean z) {
        ArrayList arrayList = null;
        for (int i2 = 1; i2 <= i; i2++) {
            String matchSinglePrefixedField = matchSinglePrefixedField(str + i2 + AbstractJsonLexerKt.COLON, str2, StringUtil.CARRIAGE_RETURN, z);
            if (matchSinglePrefixedField == null) {
                break;
            }
            if (arrayList == null) {
                arrayList = new ArrayList(i);
            }
            arrayList.add(matchSinglePrefixedField);
        }
        if (arrayList == null) {
            return null;
        }
        return (String[]) arrayList.toArray(EMPTY_STR_ARRAY);
    }
}
