package ru.allmoyki.may.widget.state;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

public class StateTextView extends TextView {
    public StateTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public StateTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public StateTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StateTextView(Context context) {
        super(context);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if(enabled)
            setAlpha(1f);
        else setAlpha(0.5f);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean res = super.onTouchEvent(event);
        invalidate();
        return res;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        try {
            final Bitmap b = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            final Canvas c = new Canvas(b);
            super.onDraw(c);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            if (isPressed())
                paint.setColorFilter(new PorterDuffColorFilter(Color.parseColor("#cccccc"), PorterDuff.Mode.MULTIPLY));
            canvas.drawBitmap(b, 0, 0, paint);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
