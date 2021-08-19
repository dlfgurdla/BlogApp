package com.com.blog.view.post;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import com.com.blog.R;
import com.com.blog.controller.PostController;
import com.com.blog.controller.dto.CMRespDto;
import com.com.blog.controller.dto.PostUpdateDto;
import com.com.blog.model.Post;
import com.com.blog.view.CustomAppBarActivity;
import com.com.blog.view.InitActivity;
import com.com.blog.view.post.adapter.PostListAdapter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostWriteActivity extends CustomAppBarActivity implements InitActivity {
    private static final String TAG = "PostWriteActivity";
    private TextInputEditText tfTitle,tfContent;
    private MaterialButton btnWrite;
    private PostController postController;
    private Post post;
    private PostListAdapter postListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_write);

        init();
        initData();
        initLr();
        initSetting();

    }

    @Override
    public void init() {
        tfTitle = findViewById(R.id.tfWrContent);
        tfContent = findViewById(R.id.tfWrContent);
        btnWrite = findViewById(R.id.btnWrite);
    }

    @Override
    public void initLr() {
        btnWrite.setOnClickListener(v -> {


            PostUpdateDto postUpdateDto = new PostUpdateDto(
                    tfTitle.getText().toString(),
                    tfContent.getText().toString()
            );



//            postListAdapter.getItemCount();
            postController.insert(7,postUpdateDto).enqueue(new Callback<CMRespDto<Post>>() {
                @Override
                public void onResponse(Call<CMRespDto<Post>> call, Response<CMRespDto<Post>> response) {
                    CMRespDto<Post> cm = response.body();
                    postListAdapter.addItem(cm);
                }

                @Override
                public void onFailure(Call<CMRespDto<Post>> call, Throwable t) {

                }
            });
        });
    }

    @Override
    public void initSetting() {
        onAppBarSettings(true, "Write");
    }

    @Override
    public void initData() {
        Intent getIntent = getIntent();
        post = (Post) getIntent.getSerializableExtra("post");

//        Log.d(TAG, "initLr: "+last);
    }
}