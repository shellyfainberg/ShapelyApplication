package com.example.shapelyapp.storage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shapelyapp.R;
import com.example.shapelyapp.model.Execrs;

import java.util.List;

public class ExAdapter extends RecyclerView.Adapter<ExAdapter.RecipeViewHolder> {

    private final List<Execrs> exList;
    private OnItemClickEvent onItemClickEvent;

      public interface OnItemClickEvent {
        void onItemClick(int position);
    }

    public void setOnItemClickEvent(OnItemClickEvent onItemClickEvent) {
        this.onItemClickEvent = onItemClickEvent;
    }

    public ExAdapter(List<Execrs> recipeList) {
        this.exList = recipeList;
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        TextView exName;
        TextView description;
        ImageView exImage;

        public RecipeViewHolder(@NonNull View itemView, final OnItemClickEvent onItemClickEvent) {
            super(itemView);

            exName = itemView.findViewById(R.id.exItem_TXV_name);
            description = itemView.findViewById(R.id.exItem_TXV_description);
            exImage = itemView.findViewById(R.id.exItem_TXV_imageFood);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickEvent != null) {
                        onItemClickEvent.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ex_item, parent, false);
        return new RecipeViewHolder(v,onItemClickEvent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        holder.exName.setText(exList.get(position).getName());
        holder.description.setText(exList.get(position).getDescription());
        Glide.with(holder.exImage.getContext()).load(exList.get(position).getImageRcs()).into(holder.exImage);
    }

    @Override
    public int getItemCount() {
          return exList.size();
    }

}
