package com.boardinglabs.taskficien.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.boardinglabs.taskficien.bean.Event;
import com.boardinglabs.taskficien.bean.Task;
import com.boardinglabs.taskficien.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by rizkygustinaldi on 11/29/14.
 */
public class HomeListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    private List<Task> tasks;
    private Context context;

    public HomeListAdapter(Context context, List<Task> items) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.tasks = items;
    }

    public int getCount() {
        return tasks.size();
    }

    public Task getItem(int position) {
        return tasks.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Task task = getItem(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.home_list_item, null);
            holder = new ViewHolder();
            TextView eventTime;
            TextView eventTimeAmount;
            TextView eventName;
            TextView eventType;
            TextView eventRemainingTask;
            TextView eventProgress;
            ImageView eventStatus;
            holder.eventName = (TextView) convertView.findViewById(R.id.hl_name_tv);
            holder.eventTime = (TextView) convertView.findViewById(R.id.hl_event_time_tv);
            holder.eventTimeAmount = (TextView) convertView.findViewById(R.id.hl_event_time_amount_tv);
            holder.eventType = (TextView) convertView.findViewById(R.id.hl_event_type_tv);
            holder.eventRemainingTask = (TextView) convertView.findViewById(R.id.hl_task_remaining_tv);
            holder.eventProgress = (ProgressBar) convertView.findViewById(R.id.hl_task_progress_pb);
            holder.eventStatus = (ImageView) convertView.findViewById(R.id.hl_task_status_image);

//            holder.icon3.setVisibility(View.INVISIBLE);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // ISI DATA
        holder.eventName.setText(task.getEvent().getName());
        holder.eventTime.setText(date12HourFormatString(task.getEvent().getStartTime()));
        holder.eventTimeAmount.setText(dateDifferenceText(task.getEvent().getStartTime(), task.getEvent().getEndTime(),3));
        holder.eventType.setText((task.getEvent().getType() == Event.EventType.SCHEDULE ? "SCHEDULE":"TASK"));
        holder.eventRemainingTask.setText("2/2");
        holder.eventProgress.setProgress(100);
        holder.eventName.setTypeface(Typeface.createFromAsset(this.context.getAssets(), "fonts/Exo-SemiBold.otf"));
        holder.eventTime.setTypeface(Typeface.createFromAsset(this.context.getAssets(), "fonts/Exo-Medium.otf"));
        holder.eventTimeAmount.setTypeface(Typeface.createFromAsset(this.context.getAssets(), "fonts/Exo-Medium.otf"));
        holder.eventType.setTypeface(Typeface.createFromAsset(this.context.getAssets(), "fonts/Exo-Medium.otf"));
        holder.eventRemainingTask.setTypeface(Typeface.createFromAsset(this.context.getAssets(), "fonts/Exo-Medium.otf"));
//        holder.eventStatus = (ImageView) convertView.findViewById(R.id.hl_task_status_image);

        return convertView;
    }

    private String date12HourFormatString(Date date){
        SimpleDateFormat dateFormatter = new SimpleDateFormat("hh:mm a");
        return dateFormatter.format(date);
    }

    private String dateDifferenceText(Date startDate, Date endDate, int type){
        long different = endDate.getTime() - startDate.getTime();
        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;
        String result = "";
        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;
        switch (type){
            case 1:
                result = String.format("%d", elapsedDays);
                break;
            case 2:
                result = String.format("%d", elapsedHours);
                break;
            case 3:
                result = String.format("%d", elapsedMinutes);
                break;
            case 4:
                result = String.format("%d", elapsedSeconds);
                break;
            default:
                result = String.format(
                        "%d days, %d hours, %d minutes, %d seconds%n",
                        elapsedDays,
                        elapsedHours, elapsedMinutes, elapsedSeconds);
                break;
        }
        return result;
    }

    static class ViewHolder {
        TextView eventTime;
        TextView eventTimeAmount;
        TextView eventName;
        TextView eventType;
        TextView eventRemainingTask;
        ProgressBar eventProgress;
        ImageView eventStatus;

    }
}
