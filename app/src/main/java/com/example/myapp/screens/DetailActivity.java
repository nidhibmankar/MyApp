package com.example.myapp.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapp.R;
import com.squareup.picasso.Picasso;

import static com.example.myapp.screens.ImageActivity.EXTRA_CREATOR;
import static com.example.myapp.screens.ImageActivity.EXTRA_LIKE;
import static com.example.myapp.screens.ImageActivity.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textViewnme, textViewlikes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String imgUrl = intent.getStringExtra(EXTRA_URL);
        String cName = intent.getStringExtra(EXTRA_CREATOR);
        int likes = intent.getIntExtra(EXTRA_LIKE,0);

        imageView = (ImageView)findViewById(R.id.detailImageview);
        textViewnme = (TextView)findViewById(R.id.detailCreatorName);
        textViewlikes = (TextView)findViewById(R.id.detailLikes);

        Picasso.get().load(imgUrl).fit().centerInside().into(imageView);
        textViewnme.setText(cName);
        textViewlikes.setText(likes);

    }
}