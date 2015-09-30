package com.leolai.earthquake;

import android.location.Location;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 15-9-29.
 */
public class Quake {

    private Date date;
    private String detail;
    private Location location;
    private double magnitude;
    private String link;

    public Date getDate() {return date;}
    public String getDetail() {return detail;}
    public Location getLocation() {return location;}
    public double getMagnitude() {return  magnitude;}
    public String getLink() {return  link;}

    public Quake(Date _d, String _detail, Location _l, double _m, String _link) {
        date = _d;
        detail = _detail;
        location = _l;
        magnitude = _m;
        link = _link;
    }

    @Override
    public String toString() {
        SimpleDateFormat ff = new SimpleDateFormat("HH.mm");
        String mDateStr = ff.format(date);
        return mDateStr + " : " + magnitude + " " + detail;
    }


}
