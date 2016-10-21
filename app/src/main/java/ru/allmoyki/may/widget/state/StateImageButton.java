package ru.allmoyki.may.widget.state;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.ImageButton;

import ru.allmoyki.may.R;
import ru.allmoyki.may.widget.utils.ViewUtil;

public class StateImageButton extends ImageButton {
    private int leftTop, rightTop, rightBottom, leftBottom;

    public StateImageButton(Context context) {
        super(context);
        init(null);
    }

    public StateImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public StateImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public StateImageButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
    public void setImageResource(int resId) {
        Drawable drawable = getResources().getDrawable(resId).mutate();
        setImageDrawable(drawable);
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(ViewUtil.createStateDrawable(getContext(), drawable, true));
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (enabled)
            setAlpha(1f);
        else setAlpha(0.5f);
    }
}
