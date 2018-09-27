package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity
{

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    ImageView ingredientsIv;
    TextView placeOfOrigin;
    TextView description;
    TextView alsoKnownAs;
    TextView ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ingredientsIv = findViewById(R.id.image_iv);

        placeOfOrigin = (TextView) findViewById(R.id.origin_tv);

        description = (TextView) findViewById(R.id.description_tv);

        alsoKnownAs = (TextView) findViewById(R.id.also_known_tv);

        ingredients = (TextView) findViewById(R.id.ingredients_tv);

        Intent intent = getIntent();
        if (intent == null)
        {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION)
        {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null)
        {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError()
    {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich)
    {
     //  placeOfOrigin.setText(sandwich.getPlaceOfOrigin());
     //  description.setText(sandwich.getDescription());

        /**
         * Set the ingredients array into a single TextView using StringBuilder
         * The code structure below is based on the answer given in this thread:
         * https://stackoverflow.com/questions/17313495/how-to-display-multiline-from-array-list-in-single-textview
         */
//        StringBuilder builder = new StringBuilder();
//        for (String ingredientString : sandwich.getIngredients()) {
//            builder.append(ingredientString + "\n");
//        }
//        ingredients.setText(builder.toString());
//
//
//        for(String alsoKnownString : sandwich.getAlsoKnownAs()) {
//        builder.append(alsoKnownString + "\n");
//    }
//        alsoKnownAs.setText(builder.toString());
}
}
