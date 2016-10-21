package ru.allmoyki.may.bus;

/**
 * Created by Boichuk Dmitriy on 22.09.2015.
 */
public class ServiceClick {
    private int position;
    private boolean state;

    public ServiceClick(int position, boolean state) {
        this.position = position;
        this.state = state;
    }

    public int getPosition() {
        return position;
    }

    public boolean isState() {
        return state;
    }
}
