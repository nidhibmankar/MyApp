package com.example.myapp.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapp.R;
import com.example.myapp.adapters.ExampleItemAdapter;
import com.example.myapp.apis.ApiClass;
import com.example.myapp.classes.ExampleItemClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
//Todo Create Java Class for Card View

public class ImageActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ExampleItemAdapter mExampleItemAdapter;
    private ArrayList<ExampleItemClass> exampleItemClassesList;
    private RequestQueue mRequestQue;
    private ApiClass apiClass;

//    variable to pass data
    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_CREATOR = "creatorName";
    public static final String EXTRA_LIKE = "like";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this ));

        exampleItemClassesList = new ArrayList<>();
        mRequestQue = Volley.newRequestQueue(this);

        parseJSON();

    }

    private void parseJSON() {
        //https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true
        String url = "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";
        // Crating JSON Request
        JsonObjectRequest mJSONRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("hits");
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject hitsHit = jsonArray.getJSONObject(i);
                                String objCreaterName = hitsHit.getString("user");
                                String objImageUrl = hitsHit.getString("webformatURL");
                                int objLikeCount = hitsHit.getInt("likes");
                                exampleItemClassesList.add(new ExampleItemClass(objImageUrl,objCreaterName,objLikeCount));

                            }
                            mExampleItemAdapter = new ExampleItemAdapter(ImageActivity.this, exampleItemClassesList);
                            mRecyclerView.setAdapter(mExampleItemAdapter);
                        }
                        catch (JSONException mException){
                            mException.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        }
        );
        mRequestQue.add(mJSONRequest);
    }

    public void onItemClick(int position){

        Intent detailsIntent = new Intent(ImageActivity.this,DetailActivity.class);
        ExampleItemClass clickedItem = exampleItemClassesList.get(position);

        detailsIntent.putExtra(EXTRA_URL, clickedItem.getMimageUrl());
        detailsIntent.putExtra(EXTRA_CREATOR, clickedItem.getMcreatorName() );
        detailsIntent.putExtra(EXTRA_LIKE, clickedItem.getMlikes());

        startActivity(detailsIntent);
    }

}

