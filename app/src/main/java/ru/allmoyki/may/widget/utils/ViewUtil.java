package ru.allmoyki.may.widget.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;

/**
 * Created by admin on 26.12.14.
 */
public class ViewUtil {

    public static Drawable createStateDrawable(Context context, Drawable bg, boolean needPress){
        if(bg != null) {
            if (!(bg instanceof StateListDrawable)) {
                StateListDrawable states = new StateListDrawable();

                if (needPress) {
                    Drawable dark = bg.getConstantState().newDrawable().mutate();
                    dark.setColorFilter(Color.parseColor("#cccccc"), PorterDuff.Mode.MULTIPLY);
                    states.addState(new int[]{android.R.attr.state_pressed}, dark);
                    states.addState(new int[]{android.R.attr.state_focused}, dark);
                }
                states.addState(new int[]{}, bg);

                return states;
            } else return bg;
        }else return null;
    }

    public static void fillTaskColor(Context context, View view, int i, int count, int startColor, int endColor, int opacity) {
        fillColor(context, view, getTaskColor(i, count, startColor, endColor, opacity));
    }

    public static int getTaskColor(int i, int count, int startColor, int endColor, int opacity)
    {
        if( count == 0 ) count = 1;
        int r = Color.red(startColor) - i * (Color.red(startColor) - Color.red(endColor))/count;
        int g = Color.green(startColor) - i * (Color.green(startColor) - Color.green(endColor))/count;
        int b = Color.blue(startColor) - i * (Color.blue(startColor) - Color.blue(endColor))/count;
        return Color.argb(opacity, r, g, b);
    }

    public static void fillColor(Context context, View view, int color){
        PorterDuff.Mode mMode = PorterDuff.Mode.MULTIPLY;
        Drawable d = view.getBackground();
        d.setColorFilter(color,mMode);

        int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable(d);
        } else {
            view.setBackground(d);
        }
    }

    public static void setViewBackgroundWithoutResettingPadding(final View v, final int backgroundResId) {
        final int paddingBottom = v.getPaddingBottom(), paddingLeft = v.getPaddingLeft();
        final int paddingRight = v.getPaddingRight(), paddingTop = v.getPaddingTop();
        v.setBackgroundResource(backgroundResId);
        v.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }
}
