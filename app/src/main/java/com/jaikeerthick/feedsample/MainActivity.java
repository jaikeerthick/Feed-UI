package com.jaikeerthick.feedsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.jaikeerthick.feedsample.Adapter.PostAdapter;
import com.jaikeerthick.feedsample.Model.FeedModel;
import com.jaikeerthick.feedsample.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private ArrayList<FeedModel> mFeedList;
    private PostAdapter mAdapter;

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mFeedList = new ArrayList<>();
        binding.recyclerViewMain.setHasFixedSize(true);
        binding.recyclerViewMain.setLayoutManager(new LinearLayoutManager(this));
        addItemsFromJSON();
        mAdapter = new PostAdapter(this, mFeedList);
        binding.recyclerViewMain.setAdapter(mAdapter);

    }

    private void addItemsFromJSON() {
        try {

            String jsonDataString = readJSONDataFromFile();
            JSONObject object = new JSONObject(jsonDataString);
            JSONArray jsonArray  = object.getJSONArray("posts");

            for (int i=0; i<jsonArray.length(); ++i) {

                JSONObject itemObj = jsonArray.getJSONObject(i);

                String name = itemObj.getString("name");
                String imageUrl = itemObj.getString("imageUrl");
                String avatarUrl = itemObj.getString("avatarUrl");
                String time = itemObj.getString("time");
                String date = itemObj.getString("date");
                String timestamp = itemObj.getString("timestamp");
                String caption = itemObj.getString("caption");
                String comments = itemObj.getString("comments");
                String likes = itemObj.getString("likes");

                FeedModel model = new FeedModel(name, imageUrl, avatarUrl, time, date, timestamp, caption, comments, likes);
                mFeedList.add(model);


            }

        } catch (JSONException | IOException e) {
            Log.e(TAG, "Error: " + e.getMessage());
        }
    }

    private String readJSONDataFromFile() throws IOException{

        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {

            String jsonString = null;
            inputStream = getResources().openRawResource(R.raw.sample);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8"));

            while ((jsonString = bufferedReader.readLine()) != null) {
                builder.append(jsonString);
            }

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return new String(builder);
    }

}