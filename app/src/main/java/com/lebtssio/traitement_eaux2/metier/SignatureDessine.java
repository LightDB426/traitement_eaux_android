package com.lebtssio.traitement_eaux2.metier;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.View;

public class SignatureDessine extends View {
    // variables nécessaire au dessin
    private Canvas canvas;
    private Bitmap bitmap;

    public SignatureDessine(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //gestion du dessin
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect desc = new Rect(0, 0, 200, 100);
        try {
            canvas.drawBitmap(bitmap, null, desc, null);
        } catch (Exception e) {

        }

    }

    public void dessine(String encodedString) {
        try {
            byte[] encodeByte = Base64
                    .decode(encodedString, Base64.DEFAULT);
            bitmap = BitmapFactory
                    .decodeByteArray(encodeByte, 0, encodeByte.length);
            bitmap = bitmap
                    .copy(bitmap.getConfig(), true);
            canvas = new Canvas(bitmap);
        } catch (Exception e) {
            bitmap = null;
        }
    }
}
