package com.codeschool.candycoded;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private Candy[] candies;
    private CandyDBHelper candyDbHelper = new CandyDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView)this.findViewById(R.id.text_view_title);
        textView.setText(R.string.products_title);

        final ArrayList<String> candyList = new ArrayList<String>();

        candyList.add("Tropical Wave");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.list_item_candy,
                R.id.text_view_candy,
                candyList
        );

        ListView listView = (ListView)this.findViewById(R.id.list_view_candy);
        listView.setAdapter(adapter);

        Context context = this;
        String text = "Hello toast!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);
                detailIntent.putExtra("candy_name", candies[i].name);
                detailIntent.putExtra("candy_image", candies[i].image);
                detailIntent.putExtra("candy_price", candies[i].price);
                detailIntent.putExtra("candy_desc", candies[i].description);
                startActivity(detailIntent);
            }
        });
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://go.codeschool.com/CandyAPI", new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Log.e("AsyncHttpClient", "response = " + responseString);
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String response) {
                        Log.d("AsyncHttpClient", "response = " + response);
                        Gson gson = new GsonBuilder().create();
                        candies = gson.fromJson(response, Candy[].class);
                        adapter.clear();
                        for (Candy candy : candies) {
                            adapter.add(candy.name);
                        }
                        addCandiesToDatabase(candies);
                    }
                }
        );

    }
    public void addCandiesToDatabase(Candy[] candies){
        SQLiteDatabase db = candyDbHelper.getWritableDatabase();

        for(Candy candy : candies) {
            ContentValues values = new ContentValues();
            values.put(CandyContract.CandyEntry.COLUMN_NAME_NAME, candy.name);
            values.put(CandyContract.CandyEntry.COLUMN_NAME_PRICE, candy.price);
            values.put(CandyContract.CandyEntry.COLUMN_NAME_DESC, candy.description);
            values.put(CandyContract.CandyEntry.COLUMN_NAME_IMAGE, candy.image);

            db.insert(CandyContract.CandyEntry.TABLE_NAME, null, values);
        }
    }
}