package com.jaikeerthick.feedsample.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jaikeerthick.feedsample.DetailActivity;
import com.jaikeerthick.feedsample.Model.FeedModel;
import com.jaikeerthick.feedsample.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {


    private List<String> commentsList;
    private Context context;

    public CommentsAdapter(Context mContext, List<String> mImagesList) {

        this.commentsList = mImagesList;
        this.context = mContext;

    }


    @NonNull
    @Override
    public CommentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommentsAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comments, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsAdapter.ViewHolder holder, final int position) {

        holder.comment.setText(commentsList.get(position));

    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView comment;

        public ViewHolder(@NonNull final View itemView) {

            super(itemView);

            comment = itemView.findViewById(R.id.comment_adapter);

        }
    }
}
