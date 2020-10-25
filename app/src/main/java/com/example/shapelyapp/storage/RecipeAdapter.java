package com.example.shapelyapp.storage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shapelyapp.R;
import com.example.shapelyapp.model.Recipe;

import java.util.List;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private final List<Recipe> recipeList;
    private OnItemClickEvent onItemClickEvent;

      public interface OnItemClickEvent {
        void onItemClick(int position);
    }

    public void setOnItemClickEvent(OnItemClickEvent onItemClickEvent) {
        this.onItemClickEvent = onItemClickEvent;
    }

    public RecipeAdapter(List<Recipe> recipeList) {
        this.recipeList = recipeList;

    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        TextView recipeName;

        public RecipeViewHolder(@NonNull View itemView, final OnItemClickEvent onItemClickEvent) {
            super(itemView);
            recipeName = itemView.findViewById(R.id.recipe_name);

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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false);
        return new RecipeViewHolder(v, onItemClickEvent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        holder.recipeName.setText(recipeList.get(position).getName());

    }

    @Override
    public int getItemCount() {
          return recipeList.size();
    }

}
