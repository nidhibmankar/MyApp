package com.example.myapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;
import com.example.myapp.classes.ExampleItemClass;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExampleItemAdapter extends RecyclerView.Adapter<ExampleItemAdapter.ExampleViewHolder> {
    private Context mContext;
    private ArrayList<ExampleItemClass> mExampleList;

//    variable for interface
    private OnItemClickListener mListner;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public ExampleItemAdapter(Context context, ArrayList<ExampleItemClass> exampleList)
    {
        mContext = context;
        mExampleList = exampleList;
    }
    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(mContext).inflate(R.layout.example_items,parent,false);
        return new ExampleViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ExampleItemClass currentExampleItemClass = mExampleList.get(position);
        String imageurl = currentExampleItemClass.getMimageUrl();
        String creatorName = currentExampleItemClass.getMcreatorName();
        int likeCount = currentExampleItemClass.getMlikes();

        holder.mCreatorName.setText(creatorName);
        holder.mLikes.setText("Likes: " + likeCount);
        //Picasso.with(mContext).load(imageurl).fit().centerInside().into(holder.mImageView);
        Picasso.get().load(imageurl).fit().centerInside().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mCreatorName;
        public TextView mLikes;

        public ExampleViewHolder(final View itemView)
        {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageViewItem);
            mCreatorName = itemView.findViewById(R.id.userTextView);
            mLikes = itemView.findViewById(R.id.likes);

//            action on item view
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListner != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            mListner.onItemClick(position);
                        }
                    }
                }
            });

        }

    }


}
