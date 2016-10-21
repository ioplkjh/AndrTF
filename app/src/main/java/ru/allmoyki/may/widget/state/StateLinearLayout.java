package ru.allmoyki.may.widget.state;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;

import ru.allmoyki.may.widget.rounded.RoundedLinearLayout;
import ru.allmoyki.may.widget.utils.ViewUtil;


public class StateLinearLayout extends RoundedLinearLayout {
    private boolean isInited = false;

    public StateLinearLayout(Context context) {
        super(context);
    }

    public StateLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StateLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public StateLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void init(AttributeSet attrs) {
        super.init(attrs);
        isInited = true;
    }

    @Override
    public void setBgColor(int color) {
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
