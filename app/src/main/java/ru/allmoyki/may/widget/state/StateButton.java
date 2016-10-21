package ru.allmoyki.may.widget.state;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.Button;

import ru.allmoyki.may.R;
import ru.allmoyki.may.widget.utils.ViewUtil;

public class StateButton extends Button {
    private int leftTop, rightTop, rightBottom, leftBottom;
    private boolean isInited = false;

    public StateButton(Context context) {
        super(context);
        init(null);
    }

    public StateButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public StateButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public StateButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
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
        isInited = true;
    }

    private void setBgColor(int color) {
        GradientDrawable bg = new GradientDrawable(GradientDrawable.Orientation.TL_BR, new int[]{color, color});
        float[] floats = new float[]{leftTop, leftTop, rightTop, rightTop, rightBottom, rightBottom, leftBottom, leftBottom};
        bg.setCornerRadii(floats);
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            setBackgroundDrawable(ViewUtil.createStateDrawable(getContext(), bg, true));
        } else {
            setBackground(ViewUtil.createStateDrawable(getContext(), bg, true));
        }
    }

    @Override
    public void setBackground(Drawable background) {
        if (background != null && background instanceof ColorDrawable) {
            if (isInited)
                setBgColor(((ColorDrawable) background).getColor());
            else super.setBackground(background);
        } else
            super.setBackground(ViewUtil.createStateDrawable(getContext(), background, true));
    }

    @Override
    public void setBackgroundDrawable(Drawable background) {
        if (background != null && background instanceof ColorDrawable) {
            if (isInited)
                setBgColor(((ColorDrawable) background).getColor());
            else super.setBackgroundDrawable(background);
        } else
            super.setBackgroundDrawable(ViewUtil.createStateDrawable(getContext(), background, true));
    }
}
