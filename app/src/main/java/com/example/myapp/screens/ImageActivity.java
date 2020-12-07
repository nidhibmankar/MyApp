package com.example.myapp.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapp.R;
import com.example.myapp.adapters.ExampleItemAdapter;
import com.example.myapp.classes.ExampleItemClass;

import java.util.ArrayList;
//Todo Create Java Class for Card View

public class ImageActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ExampleItemAdapter mExampleItemAdapter;
    private ArrayList<ExampleItemClass>

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
    }
}