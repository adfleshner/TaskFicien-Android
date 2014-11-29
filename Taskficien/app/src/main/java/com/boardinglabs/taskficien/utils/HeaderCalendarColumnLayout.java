package com.boardinglabs.taskficien.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by rizkygustinaldi on 11/28/14.
 */
public class HeaderCalendarColumnLayout extends LinearLayout {

    private TextView dayMonthTV;
    private VerticalTextView dayNameTV;
    private String dayNameText;
    private String dayMonthText;

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    private Calendar calendar;
    private int mDayNameLength = LENGTH_LONG;
    public static final int LENGTH_SHORT = 1;
    public static final int LENGTH_LONG = 2;

    public HeaderCalendarColumnLayout(Context context) {
        super(context);
        init();
    }

    public HeaderCalendarColumnLayout(Context context, String dayMontStr, String dayNameStr) {
        super(context);
        this.dayMonthText = dayMontStr;
        this.dayNameText = dayNameStr;
        init();
    }

    public HeaderCalendarColumnLayout(Context context, Calendar vcal) {
        super(context);
        this.calendar = (Calendar)vcal.clone();
        init();
    }

    public HeaderCalendarColumnLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HeaderCalendarColumnLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        Calendar tempCal = Calendar.getInstance();
        tempCal.set(Calendar.HOUR_OF_DAY, 0);
        tempCal.set(Calendar.MINUTE, 0);
        tempCal.set(Calendar.SECOND, 0);
        boolean isToday = isSameDay(this.calendar, tempCal);

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT));

        dayMonthTV = new TextView(getContext());
        ViewGroup.LayoutParams dayMontTVLP = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        dayMonthTV.setLayoutParams(dayMontTVLP);
        dayMonthTV.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, 7, getResources().getDisplayMetrics()));
        dayMonthTV.setPadding(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics())
        );
        dayMonthTV.setText(String.format("%d", this.calendar.get(Calendar.DAY_OF_MONTH)));
        dayMonthTV.setTextColor(isToday ? Color.parseColor("#2292e1") : Color.parseColor("#dee6eb"));
        dayMonthTV.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Exo-Medium.otf"));



        addView(dayMonthTV);
        dayNameTV = new VerticalTextView(getContext());
        LinearLayout.LayoutParams daynameTVLP = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        daynameTVLP.setMargins(0,-35,0,0);
        dayNameTV.setLayoutParams(daynameTVLP);
        dayNameTV.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 4, getResources().getDisplayMetrics()));
        dayNameTV.setPadding(
                (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics()),
                (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics()),
                (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics()),
                (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics())
        );

        dayNameTV.setGravity(Gravity.LEFT);
        dayNameTV.setText(getDayName(calendar));
        dayNameTV.setTextColor(isToday ? Color.parseColor("#2292e1") : Color.parseColor("#dee6eb"));
        dayNameTV.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Exo-Medium.otf"));
        addView(dayNameTV);

    }

    public int height(){
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, 7, getResources().getDisplayMetrics()) *2 + (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics())*2;
    }

    public TextView getDayMonthTV() {
        return dayMonthTV;
    }

    public void setDayMonthTV(TextView dayMonthTV) {
        this.dayMonthTV = dayMonthTV;
    }

    public VerticalTextView getDayNameTV() {
        return dayNameTV;
    }

    public void setDayNameTV(VerticalTextView dayNameTV) {
        this.dayNameTV = dayNameTV;
    }

    /////////////////////////////////////////////////////////////////
    //
    //      Helper methods.
    //
    /////////////////////////////////////////////////////////////////

    /**
     * Checks if an integer array contains a particular value.
     * @param list The haystack.
     * @param value The needle.
     * @return True if the array contains the value. Otherwise returns false.
     */
    private boolean containsValue(int[] list, int value) {
        for (int i = 0; i < list.length; i++){
            if (list[i] == value)
                return true;
        }
        return false;
    }


    /**
     * Converts an int (0-23) to time string (e.g. 12 PM).
     * @param hour The time. Limit: 0-23.
     * @return The string representation of the time.
     */
    private String getTimeString(int hour) {
        String amPm;
        if (hour >= 0 && hour < 12) amPm = "AM";
        else amPm = "PM";
        if (hour == 0) hour = 12;
        if (hour > 12) hour -= 12;
        return String.format("%02d %s", hour, amPm);
    }


    /**
     * Checks if two times are on the same day.
     * @param dayOne The first day.
     * @param dayTwo The second day.
     * @return Whether the times are on the same day.
     */
    private boolean isSameDay(Calendar dayOne, Calendar dayTwo) {
        return dayOne.get(Calendar.YEAR) == dayTwo.get(Calendar.YEAR) && dayOne.get(Calendar.DAY_OF_YEAR) == dayTwo.get(Calendar.DAY_OF_YEAR);
    }


    /**
     * Get the day name of a given date.
     * @param date The date.
     * @return The first the characters of the day name.
     */
    private String getDayName(Calendar date) {
        int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);
        if (Calendar.MONDAY == dayOfWeek) return (mDayNameLength == LENGTH_SHORT ? "M" : "MON");
        else if (Calendar.TUESDAY == dayOfWeek) return (mDayNameLength == LENGTH_SHORT ? "T" : "TUE");
        else if (Calendar.WEDNESDAY == dayOfWeek) return (mDayNameLength == LENGTH_SHORT ? "W" : "WED");
        else if (Calendar.THURSDAY == dayOfWeek) return (mDayNameLength == LENGTH_SHORT ? "T" : "THU");
        else if (Calendar.FRIDAY == dayOfWeek) return (mDayNameLength == LENGTH_SHORT ? "F" : "FRI");
        else if (Calendar.SATURDAY == dayOfWeek) return (mDayNameLength == LENGTH_SHORT ? "S" : "SAT");
        else if (Calendar.SUNDAY == dayOfWeek) return (mDayNameLength == LENGTH_SHORT ? "S" : "SUN");
        return "";
    }
}
