package ru.allmoyki.may.utils;

import android.view.View;

/**
 * Created by Boichuk Dmitriy on 09.09.2015.
 */
public interface RecyclerItemClickListener {
    void onClick(View view, int position, boolean isLongClick);
}
