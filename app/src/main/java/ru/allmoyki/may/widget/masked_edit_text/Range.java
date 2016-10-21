package ru.allmoyki.may.widget.masked_edit_text;

/**
 * Created by Boichuk Dmitriy on 15.09.2015.
 */

public class Range {
    private int start;
    private int end;

    Range() {
        start = -1;
        end = -1;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }


}