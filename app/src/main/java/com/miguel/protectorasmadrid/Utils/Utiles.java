package com.miguel.protectorasmadrid.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;


import java.io.ByteArrayOutputStream;

public class Utiles {

    public static final String URL = "http://dbprotectora.ddns.net:3000/";

    public static Bitmap base64ToBitmap(String base){
        String base64Image = base.split(",")[1];
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        return decodedByte;
    }

    public static String bitmapToBase64(Bitmap bitmap){


        final Bitmap scaledBitmap = bitmap.createScaledBitmap(bitmap, 700, 600, true);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        scaledBitmap.compress(Bitmap.CompressFormat.PNG, 30, outputStream);

        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }



}
