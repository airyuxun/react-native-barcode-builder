package com.beecode.barcode.builder;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Base64;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by airyuxun on 2016/10/20.
 */

public class BarcodeBuilder {
    public byte[] buildCodeByteArray(String data,int size) throws WriterException {
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix bm = writer.encode(data, BarcodeFormat.QR_CODE, size, size);

        Bitmap mBitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                mBitmap.setPixel(i, j, bm.get(i, j) ? Color.BLACK: Color.WHITE);
            }
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    public String buildCodeBase64(String data,int size) throws WriterException {

            byte[] byteArray = buildCodeByteArray(data,size);
             return Base64.encodeToString(byteArray,Base64.DEFAULT);

    }

    public String buildFile(String path, String content, int size) throws WriterException, IOException {
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix bm = writer.encode(content, BarcodeFormat.QR_CODE, size, size);
        File f=new File(path);
        if(f.exists()&&f.canWrite()){
            MatrixToImageWriter.writeToFile(bm, Bitmap.CompressFormat.PNG,path);
        }else{
            f.createNewFile();
            MatrixToImageWriter.writeToFile(bm, Bitmap.CompressFormat.PNG,path);
        }

        return path;
    }
}
