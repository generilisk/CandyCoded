package com.codeschool.candycoded;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = DetailActivity.this.getIntent();

        String candy_name = "";
        if (intent.hasExtra("candy_name")) {
            candy_name = intent.getStringExtra("candy_name");
        }

        TextView textViewName = (TextView)this.findViewById(R.id.text_view_name);
        textViewName.setText(candy_name);

        String candy_image = "";
        if (intent.hasExtra("candy_image")) {
            candy_image = intent.getStringExtra("candy_image");
        }

        String candy_price = "";
        if (intent.hasExtra("candy_price")) {
            candy_price = intent.getStringExtra("candy_price");
        }

        TextView textViewPrice = (TextView)this.findViewById(R.id.text_view_price);
        textViewPrice.setText(candy_price);

        String candy_desc = "";
        if (intent.hasExtra("candy_desc")) {
            candy_desc = intent.getStringExtra("candy_desc");
        }

        TextView textViewDesc = (TextView)this.findViewById(R.id.text_view_desc);
        textViewDesc.setText(candy_desc);

        Log.d("DetailActivity", "Intent data: " + candy_image + ", " +
                candy_price + ", " + candy_desc);
    }
}
