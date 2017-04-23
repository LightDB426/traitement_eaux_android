package com.lebtssio.traitement_eaux2.metier;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;

import java.io.ByteArrayOutputStream;

public class SignatureCapture extends View {
    // variables nécessaires au dessin
    private Paint paint = new Paint();
    private Path path = new Path();// collection de l'ensemble des points sauvegardés lors des mouvements du doigt
    private Canvas canvas;
    private Bitmap bitmap;

    public SignatureCapture(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setBackgroundColor(Color.WHITE);
        paint.setAntiAlias(true); // empêche le scintillement gourmand en cpu et mémoire
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5f); //taille de la grosseur du trait en pixel
    }

    //gestion du dessin
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.FILL);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);
    }

    // gestion des événements du doigt
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(eventX, eventY);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(eventX, eventY);
                break;
            case MotionEvent.ACTION_UP:
                // nothing to do
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }
    // Conversion de la signature en chaine de caractères
    public String convertToString() {
        String vretour;
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(this.getWidth(), this.getHeight(),
                    Bitmap.Config.RGB_565);
        }
        try {
            canvas = new Canvas(bitmap);
            this.draw(canvas);
            ByteArrayOutputStream ByteStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ByteStream);
            byte[] b = ByteStream.toByteArray();
            vretour = Base64.encodeToString(b, Base64.DEFAULT);
        } catch (Exception e) {
            vretour = null;
        }
        return vretour;
    }

    public void reset() {
        path.reset();
        invalidate();
    }

}