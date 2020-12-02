package com.example.myapp.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;

public class ExampleItemAdapter extends RecyclerView.Adapter<ExampleItemAdapter.ExampleViewHolder> {
    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mCreatorName;
        public TextView mLikes;

        public ExampleViewHolder(View itemView)
        {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageViewItem);
             mCreatorName = itemView.findViewById(R.id.userTextView) ;
             mLikes = itemView.findViewById(R.id.likes);

        }

    }


}
