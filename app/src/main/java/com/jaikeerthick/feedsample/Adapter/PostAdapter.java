package com.jaikeerthick.feedsample.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import com.jaikeerthick.feedsample.DetailActivity;
import com.jaikeerthick.feedsample.Model.FeedModel;
import com.jaikeerthick.feedsample.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import static androidx.core.app.ActivityOptionsCompat.makeSceneTransitionAnimation;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {


    private List<FeedModel> imagesList;
    private Context context;

    public PostAdapter(Context mContext, List<FeedModel> mImagesList) {

        this.imagesList = mImagesList;
        this.context = mContext;

    }


    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, final int position) {


            final FeedModel model = imagesList.get(position);
            Picasso.get()
                    .load(model.getAvatarUrl())
                    .into(holder.profileImage);
            holder.dateAndTime.setText(model.getTime() + "," + model.getDate());
            holder.name.setText(model.getName());
            holder.likes.setText(model.getLikes());
            holder.caption.setText(model.getCaption());
            String[] array = model.getComments().split(",");
            String count = String.valueOf(array.length);
            holder.comments.setText(count);
            Picasso.get()
                    .load(model.getImageUrl())
                    .into(holder.image);

            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("name", model.getName());
                    intent.putExtra("avatarUrl", model.getAvatarUrl());
                    intent.putExtra("imageUrl", model.getImageUrl());
                    intent.putExtra("timeAndDate", model.getTime() + "," + model.getDate());
                    intent.putExtra("caption", model.getCaption());
                    intent.putExtra("comments", model.getComments());
                    intent.putExtra("likes", model.getLikes());
                    Pair<View, String> p1 = Pair.create((View) holder.image, "ExampleTransition");
                    Pair<View, String> p2 = Pair.create((View) holder.cardView, "ExampleTransition1");
                    ActivityOptionsCompat optionsCompat = makeSceneTransitionAnimation((Activity) context, p1, p2);
                    context.startActivity(intent, optionsCompat.toBundle());


                }
            });



    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image, profileImage;
        TextView dateAndTime, name, caption, likes, comments;
        CardView cardView;


        public ViewHolder(@NonNull final View itemView) {

            super(itemView);

            image = itemView.findViewById(R.id.imageView_adapter);
            profileImage = itemView.findViewById(R.id.main_image_adapter);
            dateAndTime = itemView.findViewById(R.id.dateAndTime_adapter);
            name = itemView.findViewById(R.id.name_adapter);
            caption = itemView.findViewById(R.id.caption_adapter);
            likes = itemView.findViewById(R.id.count_likes);
            comments = itemView.findViewById(R.id.count_comments);
            cardView = itemView.findViewById(R.id.imageCard);


        }
    }
}
