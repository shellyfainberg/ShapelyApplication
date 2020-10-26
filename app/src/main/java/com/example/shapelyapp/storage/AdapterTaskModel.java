package com.example.shapelyapp.storage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shapelyapp.R;
import com.example.shapelyapp.model.Event;

import java.util.ArrayList;

public class AdapterTaskModel extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Event> tasks;
    private OnItemClickListener mItemClickListener;


    public AdapterTaskModel(Context context, ArrayList<Event> tasks) {
        this.context = context;
        this.tasks = tasks;
    }

    public void updateList(ArrayList<Event> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_event, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            final Event task = getItem(position);
            final ViewHolder genericViewHolder = (ViewHolder) holder;

            String date = task.getDate();
            String [] s = date.split("/");
            String newDate = "";
            if(!task.getDate().isEmpty()){
                newDate = s[1] + "/" + s[2] + "/" + s[0];
            }else{
                newDate = "";
            }
            genericViewHolder.list_task_LBL_date.setText(newDate);
            genericViewHolder.list_task_LBL_time.setText(task.getTime());
            genericViewHolder.list_task_LBL_description.setText(task.getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    private Event getItem(int position) {
        return tasks.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView list_task_LBL_date, list_task_LBL_time, list_task_LBL_description;

        public ViewHolder(final View itemView) {
            super(itemView);
            this.list_task_LBL_date = itemView.findViewById(R.id.list_task_LBL_date);
            this.list_task_LBL_time = itemView.findViewById(R.id.list_task_LBL_time);
            this.list_task_LBL_description = itemView.findViewById(R.id.list_task_LBL_description);


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mItemClickListener.onItemLongClick(itemView, getAdapterPosition(), getItem(getAdapterPosition()));
                    return false;
                }
            });
        }
    }

    public void removeAt(int position) {
        tasks.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, tasks.size());
    }

    public void removeAll(){
        for(int i = 0; i < tasks.size(); i++){
            tasks.remove(i);
        }
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, Event task);
        void onItemLongClick(View view, int position, Event task);
    }
}