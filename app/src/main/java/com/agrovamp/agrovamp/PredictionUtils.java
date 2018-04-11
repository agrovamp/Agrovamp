package com.agrovamp.agrovamp;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Nishat Sayyed on 11-04-2018.
 */

class PredictionUtils {
    private static List x = new ArrayList();
    private static List y = new ArrayList();
    private static List z = new ArrayList();

    public static final String TAG = PredictionUtils.class.getSimpleName();

    public static String getPredictionString() {
        int count = 0;
        int i;
        double sx = 0.0, sy = 0.0;
        double n = 0.0, d = 0.0;
        double w, ys, b;
        x.add(26.5D);
        x.add(26.5D);
        x.add(27.5D);
        x.add(27.5D);
        x.add(27.5D);
        x.add(28.0D);
        x.add(28.5D);
        x.add(28.0D);
        x.add(27.5D);
        x.add(27.0D);

        y.add(46D);
        y.add(48D);
        y.add(54D);
        y.add(63D);
        y.add(74D);
        y.add(53D);
        y.add(86D);
        y.add(75D);
        y.add(75D);
        y.add(57D);

        z.add(33D);
        z.add(34D);
        z.add(31D);
        z.add(35D);
        z.add(36D);
        z.add(34D);
        z.add(33D);
        z.add(33D);
        z.add(37D);
        z.add(43D);

        do {
//            double x[] = {26.5, 26.5, 27.5, 27.5, 27.5, 28, 28.5, 28, 27.5, 27};
//            double y[] = {46, 48, 54, 63, 74, 53, 86, 75, 75, 57};

            for (i = 0; i < x.size(); i++) {
                sx = sx + (double) x.get(i);
            }//closing for

            sx = sx / x.size();

            for (i = 0; i < y.size(); i++) {
                sy = sy + (double) y.get(i);
            }//closing for

            sy = sy / y.size();


            for (i = 0; i < x.size(); i++) {
                n = n + (((double)x.get(i) - sx) * ((double)y.get(i) - sy));

                d = d + ((double)x.get(i) - sx) * ((double)x.get(i) - sx);
            }

            w = n / d;

            b = sy - (w * sx);

            ys = b + w * 28;

            if (ys >= 70) {
                break;
            } else {
                count++;
                x.add(x.size() + 1, z.get(count));
                y.add(y.size() + 1, ys);
            }
        } while (ys <= 70);
        Log.d(TAG, "Count: " + count);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, count);
        Log.d(TAG, dateFormat.format(c.getTime()));
        return  dateFormat.format(c.getTime());
    }
}//closing class