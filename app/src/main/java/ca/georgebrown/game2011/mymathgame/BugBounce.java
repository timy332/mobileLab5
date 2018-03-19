package ca.georgebrown.game2011.mymathgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.provider.ContactsContract;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

/**
 * Created by Tech on 2018-03-19.
 */

public class BugBounce extends View {
    private int m_screenW;
    private int m_screenH;
    private int m_X;
    private int m_Y;
    private int m_initialY;
    private int m_bugW;
    private int m_bugH;
    private int m_scoreH;
    private int m_score;
    private Bitmap m_bug, m_bgr;
    private Paint m_paint;
    private long m_startTime, m_endTime;
    private double m_diff;

    public BugBounce (Context context,String PACKAGE_NAME) {
        super(context);
        m_startTime = System.nanoTime();
        m_bug = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier("bug","drawable",PACKAGE_NAME));
        m_bgr = BitmapFactory.decodeResource(getResources(), R.drawable.sky_bgr);
        m_bugW = m_bug.getWidth();
        m_bugH = m_bug.getHeight();

        m_initialY = 100;
        m_scoreH = 100;
        m_paint = new Paint();
        m_paint.setColor(Color.RED);
        m_paint.setTextSize(50);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        m_screenW = w;
        m_screenH = h;

        m_bgr = Bitmap.createScaledBitmap(m_bgr, w, h, true);
        m_X = (int) (m_screenW/2) - (m_bugW/2);
        m_Y = (int) (m_screenH/2) - (m_bugH/2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(m_bgr, 0, 0, null);

        canvas.drawText("Score: "+m_score, 50, 100, m_paint);

        m_endTime = System.nanoTime();
        m_diff = (m_endTime - m_startTime)/1e6;

        if (m_diff > 1000)
        {
            m_X = (int) (Math.random() * (m_screenW - m_bugW));
            m_Y = (int) (Math.random() * (m_screenH - m_bugH));
            m_startTime = m_endTime;
        }

        canvas.drawBitmap(m_bug, m_X, m_Y, null);

        invalidate();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        System.out.println( touchX + "(" + m_X + " " + (m_X+m_bugW) + ") "+
                            touchY + "(" + m_Y + " " + (m_Y+m_bugH) + ")");
        if ((touchX >= m_X && touchX <= m_X+m_bugW) && (touchY >= m_Y && touchY <= m_Y+m_bugH))
        {
            m_score++;
        }
        return super.onTouchEvent(event);
    }
}
