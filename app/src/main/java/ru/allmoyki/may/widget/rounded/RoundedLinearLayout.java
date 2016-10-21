package ru.allmoyki.may.widget.rounded;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import ru.allmoyki.may.R;


public class RoundedLinearLayout extends LinearLayout {
    protected int leftTop, rightTop, rightBottom, leftBottom;

    public RoundedLinearLayout(Context context) {
        super(context);
        init(null);
    }

    public RoundedLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public RoundedLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public RoundedLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    protected void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs,
                    R.styleable.StateImageButton, 0, 0);
            leftTop = a.getDimensionPixelSize(R.styleable.StateImageButton_leftTopRadius, 0);
            rightTop = a.getDimensionPixelSize(R.styleable.StateImageButton_rightTopRadius, 0);
            rightBottom = a.getDimensionPixelSize(R.styleable.StateImageButton_rightBottomRadius, 0);
            leftBottom = a.getDimensionPixelSize(R.styleable.StateImageButton_leftBottomRadius, 0);
            a.recycle();
        }

        Drawable bg = getBackground();
        if (bg != null && bg instanceof ColorDrawable) {
            setBgColor(((ColorDrawable) bg).getColor());
        }
    }

    public void setBgColor(int color) {
        GradientDrawable bg = new GradientDrawable(GradientDrawable.Orientation.TL_BR, new int[]{color, color});
        float[] floats = new float[]{leftTop, leftTop, rightTop, rightTop, rightBottom, rightBottom, leftBottom, leftBottom};
        bg.setCornerRadii(floats);

        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            setBackgroundDrawable(bg);
        } else {
            setBackground(bg);
        }
    }
}
