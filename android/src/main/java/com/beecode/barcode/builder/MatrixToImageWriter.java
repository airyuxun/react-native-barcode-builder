package com.beecode.barcode.builder;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.google.zxing.common.BitMatrix;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by airyuxun on 2016/10/21.
 */

public final class MatrixToImageWriter {

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    private MatrixToImageWriter() {
    }

    public static Bitmap toBitmap(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();

        Bitmap mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                mBitmap.setPixel(i, j, matrix.get(i, j) ? Color.BLACK: Color.WHITE);
            }
        }

        return mBitmap;
    }

    public static void writeToFile(BitMatrix matrix, Bitmap.CompressFormat format, String filename)
            throws IOException {
        Bitmap mBitmap = toBitmap(matrix);
        saveToFile(filename,mBitmap,format);
    }
    public static void saveToFile(String filename,Bitmap bmp ,Bitmap.CompressFormat format) {
        try {
            FileOutputStream out = new FileOutputStream(filename);
            bmp.compress(format, 100, out);
            out.flush();
            out.close();
        } catch(Exception e) {}
    }

    public static void writeToStream(BitMatrix matrix,Bitmap.CompressFormat format,
                                     OutputStream stream) throws IOException {
        Bitmap mBitmap = toBitmap(matrix);
        mBitmap.compress(format, 100, stream);
    }

}