package com.codeschool.candycoded;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.codeschool.candycoded.CandyContract.CandyEntry;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = DetailActivity.this.getIntent();

        if (intent != null && intent.hasExtra("position")) {
            int position = intent.getIntExtra("position", 0);
            CandyDBHelper dbHelper = new CandyDBHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM candy", null);
            cursor.moveToPosition(position);
            String candyName = cursor.getString(cursor.getColumnIndexOrThrow(CandyContract.CandyEntry.COLUMN_NAME_NAME));
            String candyPrice = cursor.getString(cursor.getColumnIndexOrThrow(CandyEntry.COLUMN_NAME_PRICE));
            String candyImage = cursor.getString(cursor.getColumnIndexOrThrow(CandyEntry.COLUMN_NAME_IMAGE));
            String candyDesc = cursor.getString(cursor.getColumnIndexOrThrow(CandyEntry.COLUMN_NAME_DESC));
            ((TextView) DetailActivity.this.findViewById(R.id.text_view_name)).setText(candyName);
            ((TextView) DetailActivity.this.findViewById(R.id.text_view_price)).setText(candyPrice);
            ((TextView) DetailActivity.this.findViewById(R.id.text_view_desc)).setText(candyDesc);
            Picasso.with(DetailActivity.this).load(candyImage).into((ImageView)DetailActivity.this.findViewById(R.id.image_view_candy));
        }
    }
}
