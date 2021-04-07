package com.jaikeerthick.feedsample;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jaikeerthick.feedsample.Adapter.CommentsAdapter;
import com.jaikeerthick.feedsample.Adapter.PostAdapter;
import com.jaikeerthick.feedsample.databinding.ActivityDetailBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    String name, avatarUrl, imageUrl, caption, dateAndTime, comments, likes, count;
    ArrayList<String> commentsList = new ArrayList<>();
    CommentsAdapter mAdapter;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = getIntent().getStringExtra("name");
        avatarUrl = getIntent().getStringExtra("avatarUrl");
        imageUrl = getIntent().getStringExtra("imageUrl");
        caption = getIntent().getStringExtra("caption");
        dateAndTime = getIntent().getStringExtra("timeAndDate");
        comments = getIntent().getStringExtra("comments");
        likes = getIntent().getStringExtra("likes");

        String[] array = comments.split(",");
        int i;
        for (i=0 ; i<array.length; i++){
            commentsList.add(array[i]);
        }
        count = String.valueOf(array.length);

        WorkerThread workerThread = new WorkerThread();
        new Thread(workerThread).start();

    }

    public class WorkerThread implements Runnable{

        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {


                    binding.recyclerViewDetail.setHasFixedSize(true);
                    binding.recyclerViewDetail.setLayoutManager(new LinearLayoutManager(DetailActivity.this));

                    binding.nameDetail.setText(name);
                    binding.captionDetail.setText(caption);
                    Picasso.get()
                            .load(avatarUrl)
                            .into(binding.avatarDetail);
                    Picasso.get()
                            .load(imageUrl)
                            .into(binding.imageDetail);
                    binding.dateAndTimeDetail.setText(dateAndTime);
                    binding.likeCommentDetail.setText(likes + " Likes, " + count + " Comments");

                    mAdapter = new CommentsAdapter(DetailActivity.this, commentsList);
                    binding.recyclerViewDetail.setAdapter(mAdapter);

                }
            });

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.samplemenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}