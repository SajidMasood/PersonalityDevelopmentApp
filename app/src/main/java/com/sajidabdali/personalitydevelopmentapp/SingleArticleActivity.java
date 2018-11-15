package com.sajidabdali.personalitydevelopmentapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class SingleArticleActivity extends AppCompatActivity {

    //
    Toolbar toolbar;
    private String art_key = null;
    private DatabaseReference userData,mref;
    private ImageView singleArticleImage;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_article);

        // TODO: 11/15/2018 Toolbar................
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        singleArticleImage = (ImageView) findViewById(R.id.image_id);

        art_key = getIntent().getExtras().getString("ArticleId");  //current food string value
        userData = FirebaseDatabase.getInstance().getReference().child(art_key);
        userData.keepSynced(true);

        mref=FirebaseDatabase.getInstance().getReference().child("Article").child(art_key);
        mref.keepSynced(true);
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final String art_image = (String)dataSnapshot.child("Image").getValue();
                Picasso.with(SingleArticleActivity.this).load(art_image).networkPolicy(NetworkPolicy.OFFLINE).into(singleArticleImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        //
                    }

                    @Override
                    public void onError() {
                        Picasso.with(SingleArticleActivity.this).load(art_image).into(singleArticleImage);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.desc_list_id);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Articles,ArticlesViewHolder> FBRA = new FirebaseRecyclerAdapter<Articles,ArticlesViewHolder>(
                Articles.class,
                R.layout.desclist,
                ArticlesViewHolder.class,
                userData
        ) {
            @Override
            protected void populateViewHolder(ArticlesViewHolder viewHolder, Articles model, int position) {
                viewHolder.setDesce(model.getDesce());
                viewHolder.setHead(model.getHead());

            }
        };
        recyclerView.setAdapter(FBRA);
    }

    public static class ArticlesViewHolder extends RecyclerView.ViewHolder{

        View mView;
        public ArticlesViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setHead(String head){
            TextView head_Text = (TextView) mView.findViewById(R.id.title_id);
            head_Text.setText(head);
        }
        public void setDesce(String name){
            TextView title_name = (TextView) mView.findViewById(R.id.desc_id);
            title_name.setText(name);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
