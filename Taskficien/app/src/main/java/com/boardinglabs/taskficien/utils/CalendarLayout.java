package com.boardinglabs.taskficien.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.boardinglabs.taskficien.R;
import com.boardinglabs.taskficien.bean.Event;
import com.boardinglabs.taskficien.adapter.HomeListAdapter;
import com.boardinglabs.taskficien.bean.Task;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by rizkygustinaldi on 11/28/14.
 */
public class CalendarLayout extends LinearLayout {


    private CalendarRowLayout header;
    private ArrayList<Calendar> dayWeeks;
    private LinearLayout content;
    private SlidingUpPanelLayout slidingUpPanel;
    private ListView listView;
    private ArrayList<ArrayList<Task>> tasks;
    private HomeListAdapter listAdapter;

    private int currentListViewShowIdx;
    private int totalHeight = 0;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String state;

    public CalendarLayout(Context context) {
        super(context);
    }

    public CalendarLayout(Context context, ArrayList<Calendar> vDaysOfWeeks) {
        super(context);
        this.dayWeeks = vDaysOfWeeks;
        this.state  = "header";
        init();

    }

    public CalendarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CalendarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        currentListViewShowIdx = 0;
        generateDummyData();

        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.TOP);
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 40));
        header = new CalendarRowLayout(getContext(),dayWeeks, this);

        addView(header,new LayoutParams(LayoutParams.MATCH_PARENT,header.height()));
        content =  (LinearLayout) inflate(getContext(), R.layout.activity_home_content, this);
        slidingUpPanel = (SlidingUpPanelLayout)content.findViewById(R.id.home_drksliding_layout);

        listView = (ListView) slidingUpPanel.findViewById(R.id.home_content_lv);

        listAdapter = new HomeListAdapter(getContext(), tasks.get(0));

        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new TaskListItemClickListener());
        listView.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });

        final int listHeight = ((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 55, getResources().getDisplayMetrics())) * tasks.size();

        totalHeight = ((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 485, getResources().getDisplayMetrics()));

//        listView.post(new Runnable()
//        {
//            @Override
//            public void run() {
//                slidingUpPanel.setContentHeight(listHeight);
//            }
//        });

        if(listHeight > totalHeight - 300){
            slidingUpPanel.setPanelHeight(300);
            listView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, totalHeight - 300));
        }
        else{
            slidingUpPanel.setPanelHeight(totalHeight - listHeight);
        }




    }

    public void viewListForDayIndex(int index){
        listAdapter.setTasks(tasks.get(index));
        listAdapter.notifyDataSetChanged();
    }
    private class TaskListItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Task task = tasks.get(currentListViewShowIdx).get(position);

        }
    }

    private void generateDummyData(){
        tasks = new ArrayList<ArrayList<Task>>();
        for (int i =0; i< dayWeeks.size(); i++){
            ArrayList<Task> dtasks = new ArrayList<Task>();

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal2.add(Calendar.DATE, 1);

            Date now = cal.getTime();
            Date tomorrow = cal2.getTime();

            Task task1 = new Task();
            Event event1 = new Event();
            event1.setName("MAWA KARUNG - " + i);
            event1.setStartTime(now);
            event1.setEndTime(tomorrow);
            task1.setEvent(event1);
            task1.setName(event1.getName());
            task1.setDescription("Mawa karung jerona ucing");

            Task task2 = new Task();
            Event event2 = new Event();
            event2.setName("MAWA KARUNG");
            event2.setStartTime(now);
            event2.setEndTime(tomorrow);
            task2.setEvent(event2);
            task2.setName(event2.getName());
            task2.setDescription("Mawa karung jerona ucing");

            Task task3 = new Task();
            Event event3 = new Event();
            event3.setName("MAWA KARUNG");
            event3.setStartTime(now);
            event3.setEndTime(tomorrow);
            task3.setEvent(event3);
            task3.setName(event3.getName());
            task3.setDescription("Mawa karung jerona ucing");

            Task task4 = new Task();
            Event event4 = new Event();
            event4.setName("MAWA KARUNG");
            event4.setStartTime(now);
            event4.setEndTime(tomorrow);
            task4.setEvent(event4);
            task4.setName(event4.getName());
            task4.setDescription("Mawa karung jerona ucing");

            Task task8 = new Task();
            Event event8 = new Event();
            event8.setName("MAWA KARUNG");
            event8.setStartTime(now);
            event8.setEndTime(tomorrow);
            task8.setEvent(event8);
            task8.setName(event8.getName());
            task8.setDescription("Mawa karung jerona ucing");


            Task task5 = new Task();
            Event event5 = new Event();
            event5.setName("MAWA KARUNG");
            event5.setStartTime(now);
            event5.setEndTime(tomorrow);
            task5.setEvent(event5);
            task5.setName(event5.getName());
            task5.setDescription("Mawa karung jerona ucing");

            Task task6 = new Task();
            Event event6 = new Event();
            event6.setName("MAWA KARUNG");
            event6.setStartTime(now);
            event6.setEndTime(tomorrow);
            task6.setEvent(event6);
            task6.setName(event6.getName());
            task6.setDescription("Mawa karung jerona ucing");

            Task task7 = new Task();
            Event event7 = new Event();
            event7.setName("MAWA KARUNG");
            event7.setStartTime(now);
            event7.setEndTime(tomorrow);
            task7.setEvent(event7);
            task7.setName(event7.getName());
            task7.setDescription("Mawa karung jerona ucing");

            dtasks.add(task1);
            dtasks.add(task2);
            dtasks.add(task3);
            dtasks.add(task4);
            dtasks.add(task5);
            dtasks.add(task6);
            dtasks.add(task7);
            dtasks.add(task8);

            tasks.add(dtasks);
        }


    }


    public ArrayList<Calendar> getDayWeeks() {
        return dayWeeks;
    }

    public void setDayWeeks(ArrayList<Calendar> dayWeeks) {
        this.dayWeeks = dayWeeks;
    }

    public CalendarRowLayout getHeader() {
        return header;
    }

    public void setHeader(CalendarRowLayout header) {
        this.header = header;
    }

    public class CalendarRowLayout extends LinearLayout {
        private ArrayList<Calendar> weeks;
        private int numDay;
        private CalendarLayout parentLayout;
        public CalendarRowLayout(Context context) {
            super(context);
            init();
        }

        public CalendarRowLayout(Context context, ArrayList<Calendar> vcals) {
            super(context);
            this.weeks = vcals;
            numDay = this.weeks.size();
            init();
        }

        public CalendarRowLayout(Context context, ArrayList<Calendar> vcals, CalendarLayout vParent) {
            super(context);
            this.weeks = vcals;
            numDay = this.weeks.size();
            this.parentLayout = vParent;
            init();
        }

        public CalendarRowLayout(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public CalendarRowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }


        private void init() {
            setOrientation(LinearLayout.HORIZONTAL);
            setGravity(Gravity.CENTER);
            setPadding(
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()),
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()),
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()),
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics())
            );
            setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
//        layoutParams.width = LayoutParams.MATCH_PARENT;
//        layoutParams.height = LayoutParams.WRAP_CONTENT;

            columns = new ArrayList<HeaderCalendarColumnLayout>();
            float colWidth = ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth() / this.numDay - (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
            for (int i =0; i< this.weeks.size(); i++){
                HeaderCalendarColumnLayout col = new HeaderCalendarColumnLayout(getContext(),this.weeks.get(i));
                col.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        HeaderCalendarColumnLayout col = (HeaderCalendarColumnLayout) v;
                        parentLayout.viewListForDayIndex(columns.indexOf(col));
//                        Toast.
                    }
                });
                this.columns.add(col);
                ViewGroup.LayoutParams colLP = new ViewGroup.LayoutParams((int)colWidth, LayoutParams.MATCH_PARENT);
//            colLP.width = (int)colWidth;
                this.addView(col,colLP);
            }

        }

        public int height(){
            return this.columns.get(0).height();
        }

        public ArrayList<HeaderCalendarColumnLayout> getColumns() {
            return columns;
        }



        public void setColumns(ArrayList<HeaderCalendarColumnLayout> columns) {
            this.columns = columns;
        }

        private ArrayList<HeaderCalendarColumnLayout> columns;

        public ArrayList<Calendar> getWeeks() {
            return weeks;
        }

        public void setWeeks(ArrayList<Calendar> weeks) {
            this.weeks = weeks;
        }
    }
}
