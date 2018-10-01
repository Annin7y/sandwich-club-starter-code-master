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
      //  placeOfOrigin = (TextView) findViewById(R.id.origin_tv);
      //  description = (TextView) findViewById(R.id.description_tv);
        alsoKnownAs = (TextView) findViewById(R.id.also_known_tv);
      //  ingredients = (TextView) findViewById(R.id.ingredients_tv);

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

               //if the image can't be loaded the following error message/image will be displayed
               //error image added to the drawable folder
               .error(R.drawable.user_placeholder_error)
               .into(ingredientsIv);
   }

    private void closeOnError()
    {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich)
    {
       //placeOfOrigin.setText(sandwich.getPlaceOfOrigin());

       // if the strings are empty, the text will be set to "No data found"
       // The code structure below is based on the answers given in this thread:
       // https://stackoverflow.com/questions/46040636/check-if-textview-is-empty
     //  if(placeOfOrigin.getText().toString().equals(""))
   //    {
   //        placeOfOrigin.setText(R.string.no_data);
   //    }

   //     description.setText(sandwich.getDescription());

   //     if(description.getText().toString().equals(""))
    //    {
    //        description.setText(R.string.no_data);
     //   }

        // Set the ingredients & alsoKnownAs arrays into single TextViews using StringBuilder
        // The code structure below is based on the answer given in this thread:
        // https://stackoverflow.com/questions/17313495/how-to-display-multiline-from-array-list-in-single-textview
    //    StringBuilder builder = new StringBuilder();
    //    for (String ingredientString : sandwich.getIngredients())
    //    {
    //        builder.append(ingredientString + "\n");
     //   }

     //   ingredients.setText(builder.toString());

     //   if(ingredients.getText().toString().equals(""))
     //   {
     //       ingredients.setText(R.string.no_data);
     //   }

        StringBuilder builder2 = new StringBuilder();
        for(String alsoKnownString : sandwich.getAlsoKnownAs())
        {
        builder2.append(alsoKnownString + "\n");
        }
        
        alsoKnownAs.setText(builder2.toString());

        if(alsoKnownAs.getText().toString().equals(""))
        {
            alsoKnownAs.setText(R.string.no_data);
        }
}
}
