package com.boardinglabs.taskficien;

import android.app.Activity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.boardinglabs.taskficien.utils.CalendarLayout;
import com.boardinglabs.taskficien.utils.HorizontalPager;

import java.util.ArrayList;
import java.util.Calendar;


public class HomeActivity extends Activity implements HorizontalPager.OnScreenSwitchListener{

//    private static final int TYPE_DAY_VIEW = 1;
//    private static final int TYPE_THREE_DAY_VIEW = 2;
//    private static final int TYPE_WEEK_VIEW = 3;
//    private int mWeekViewType = TYPE_THREE_DAY_VIEW;
//    private WeekView mWeekView;
    private TextView headerMonthNumberTV;
    private TextView headerMonthTextTV;
    private TextView headerDateNumberTV;
    private TextView headerDayTextTV;
    private HorizontalPager mPager;
    private int currentScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        headerMonthNumberTV = (TextView) findViewById(R.id.header_month_number_tv);
        headerMonthTextTV = (TextView) findViewById(R.id.header_month_text_tv);
        headerDateNumberTV = (TextView) findViewById(R.id.header_date_number_tv);
        headerDayTextTV = (TextView) findViewById(R.id.header_day_text_tv);

        headerDayTextTV.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Exo-Regular.otf"));
        headerDateNumberTV.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Exo-Bold.otf"));
        headerMonthTextTV.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Exo-Bold.otf"));
        headerMonthNumberTV.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Exo-Bold.otf"));

        mPager = (HorizontalPager) findViewById(R.id.home_horizontal_pager);
        mPager.setOnScreenSwitchListener(this);

        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);

        today.add(Calendar.DATE,-(today.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY));

        Calendar iters = (Calendar)today.clone();
        iters.add(Calendar.DATE,-14);

        for (int i = 0; i < 5; i++) {
            ArrayList<Calendar> calendars = new ArrayList<Calendar>();

            for (int j=0; j< 7; j++) {
                calendars.add((Calendar) iters.clone());
                iters.add(Calendar.DATE, 1);
            }

            CalendarLayout header = new CalendarLayout(this.getApplicationContext(),calendars);
            mPager.addView(header);
        }
        currentScreen = 2;
        mPager.setCurrentScreen(currentScreen,false);
    }

    protected void addRightWeek(Calendar startDate){

        mPager.removeViewAt(0);
        ArrayList<Calendar> calendars = new ArrayList<Calendar>();

        for (int j=0; j< 7; j++) {
            calendars.add((Calendar) startDate.clone());
            startDate.add(Calendar.DATE, 1);
        }

        CalendarLayout header = new CalendarLayout(this.getApplicationContext(),calendars);
        mPager.addView(header);
    }

    protected void addLeftWeek(Calendar startDate){
        mPager.removeViewAt(4);
        ArrayList<Calendar> calendars = new ArrayList<Calendar>();

        for (int j=0; j< 7; j++) {
            calendars.add((Calendar) startDate.clone());
            startDate.add(Calendar.DATE, 1);
        }

        CalendarLayout header = new CalendarLayout(this.getApplicationContext(),calendars);
        mPager.addView(header,0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onScreenSwitched(final int screen) {
        if(currentScreen != screen){
            Animation animFadeout = AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.fade_out_fast);
            animFadeout.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {

                    if(screen==1){
//            addLeftWeek();
                        CalendarLayout calendar = (CalendarLayout)mPager.getSubViews().get(0);
                        if(calendar.getState().equals("header")){
                            CalendarLayout.CalendarRowLayout headerRow = calendar.getHeader();
                            Calendar cal = (Calendar)headerRow.getWeeks().get(0).clone();
                            cal.add(Calendar.DATE,-7);
                            addLeftWeek(cal);
                            mPager.setCurrentScreen(screen+1,false);
                        }
                    }else if(screen==3){
                        CalendarLayout calendar = (CalendarLayout)mPager.getSubViews().get(4);
                        if(calendar.getState().equals("header")) {
                            CalendarLayout.CalendarRowLayout headerRow = calendar.getHeader();
                            Calendar cal = (Calendar) headerRow.getWeeks().get(0).clone();
                            cal.add(Calendar.DATE, 7);
                            addRightWeek(cal);
                            mPager.setCurrentScreen(screen - 1, false);
                        }
                    }
                    Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
                            R.anim.fade_in_fast);
                    animFadein.setAnimationListener(new Animation.AnimationListener() {

                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {



                        }
                    });
                    mPager.startAnimation(animFadein);
                }
            });
            mPager.startAnimation(animFadeout);
        }


    }

}
