package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;
import org.videolan.resources.Constants;

public final class UPCAWriter implements Writer {
    private final EAN13Writer subWriter = new EAN13Writer();

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2) throws WriterException {
        return encode(str, barcodeFormat, i, i2, (Map<EncodeHintType, ?>) null);
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.UPC_A) {
            return this.subWriter.encode(Constants.GROUP_VIDEOS_FOLDER.concat(String.valueOf(str)), BarcodeFormat.EAN_13, i, i2, map);
        }
        throw new IllegalArgumentException("Can only encode UPC-A, but got ".concat(String.valueOf(barcodeFormat)));
    }
}
