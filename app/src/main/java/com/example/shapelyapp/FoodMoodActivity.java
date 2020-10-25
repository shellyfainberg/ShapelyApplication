package com.example.shapelyapp;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shapelyapp.model.Recipe;
import com.example.shapelyapp.model.User;
import com.example.shapelyapp.storage.RecipeAdapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

public class FoodMoodActivity extends AppCompatActivity {

    private FloatingActionButton foodmood_FABTN_addRecipe;
    private AlertDialog dialog;
    private DatabaseReference mDatabase;


    private RecyclerView recyclerView;
    private RecipeAdapter recipeRecipeAdapter;
    private final ArrayList<Recipe> recipeList = new ArrayList<>();
    private User currUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodmood);
        initDatabase();
        initViews();
        configRecyclerView();
        currUser = getParsedUser();
        configFloatingButton();

        foodmood_FABTN_addRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewDialog();
            }
        });

        recipeRecipeAdapter.setOnItemClickEvent(new RecipeAdapter.OnItemClickEvent() {
            @Override
            public void onItemClick(int position) {
                displayItemDialog(recipeList.get(position));
            }
        });

    }

    private User getParsedUser(){
        return (User) getIntent().getSerializableExtra(getString(R.string.user_id));
    }
    private void configFloatingButton(){
        if(currUser != null && !currUser.getUserType().equals(getString(R.string.dietitian))){
            foodmood_FABTN_addRecipe.setVisibility(View.INVISIBLE);
        }
    }
    private void initDatabase() {

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.foodmood_RV_recycler_view);
        foodmood_FABTN_addRecipe = findViewById(R.id.foodmood_FABTN_addRecipe);
    }

    private void configRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recipeRecipeAdapter = new RecipeAdapter(recipeList);
        recyclerView.setAdapter(recipeRecipeAdapter);
        recipeRecipeAdapter.notifyDataSetChanged();
        setListener();
    }


    public void createNewDialog() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        final View contactPopupView = getLayoutInflater().inflate(R.layout.recipepopup, null);
        final EditText recipeName  = contactPopupView.findViewById(R.id.recipePopup_EDT_recipe_name);
        final EditText recipeIngr = contactPopupView.findViewById(R.id.recipePopup_EDT_ingredients);
        final EditText recipeDesc = contactPopupView.findViewById(R.id.recipePopup_EDT_description);
        Button recipePopup_BTN_ok = contactPopupView.findViewById(R.id.recipePopup_BTN_ok);

        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        recipePopup_BTN_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validData(recipeName.getText().toString(), recipeIngr.getText().toString(), recipeDesc.getText().toString())) {
                    writeToDatabase(new Recipe(recipeName.getText().toString(), recipeDesc.getText().toString(), recipeIngr.getText().toString()));
                    dialog.cancel();
                } else {

                }
            }
        });

    }

    private boolean validData(String recipeName, String recipeIngr, String recipeDesc) {
        return !recipeName.isEmpty() && !recipeIngr.isEmpty() && !recipeDesc.isEmpty();
    }

    private void writeToDatabase(Recipe recipe) {
        mDatabase.child("recipe").child(UUID.randomUUID().toString()).setValue(recipe);
    }

    private void setListener() {
        mDatabase.child("recipe").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                recipeList.clear();

                for (DataSnapshot data : snapshot.getChildren()) {
                    Recipe recipe = data.getValue(Recipe.class);
                    recipeList.add(recipe);
                }
                recipeRecipeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void displayItemDialog(Recipe recipe) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        final View contactPopupView = getLayoutInflater().inflate(R.layout.recipe_description_popup, null);
        TextView recipeName = contactPopupView.findViewById(R.id.recipe_description_TV_recipe_name);
        TextView recipeIngr = contactPopupView.findViewById(R.id.recipe_description_TV_recipe_ingredients);
        TextView recipeDesc = contactPopupView.findViewById(R.id.recipe_description_TV_recipe_description);
        recipeName.setText(recipe.getName());
        recipeIngr.setText(recipe.getIngredients());
        recipeDesc.setText(recipe.getDescription());

        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();
    }

}