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
        if (intent != null && intent.hasExtra("candy_name")) {
            candy_name = intent.getStringExtra("candy_name");
        }
        TextView textView = (TextView)this.findViewById(R.id.text_view_name);
        textView.setText(candy_name);

/*        String candyImage = "";
        if (intent != null && intent.hasExtra("candy_image")) {
            candyImage = intent.getStringExtra("candy_image");
        }
        String candyPrice = "";
        if (intent != null && intent.hasExtra("candy_price")) {
            candyPrice = intent.getStringExtra("candy_price");
        }
        String candyDesc = "";
        if (intent != null && intent.hasExtra("candy_desc")) {
            candyDesc = intent.getStringExtra("candy_desc");
        }
        Log.d("DetailActivity", "Intent Data: : + " + candyImage + ", " + candyPrice + ", " + candyDesc);*/
    }
}