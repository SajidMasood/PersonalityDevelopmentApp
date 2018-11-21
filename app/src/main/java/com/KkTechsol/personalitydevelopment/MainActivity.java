package com.KkTechsol.personalitydevelopment;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    // TODO: 11/15/2018 variables....
    Toolbar toolbar;
    private RecyclerView mRecyclerView;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // TODO: 11/15/2018  Recycler View declaration...
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // TODO: 11/15/2018 Database References...
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Article");
        //mDatabase.keepSynced(true);
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Articles,ArticlesViewHolder> FBRA = new FirebaseRecyclerAdapter<Articles, ArticlesViewHolder>(
                Articles.class,
                R.layout.singleitemview,
                ArticlesViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(ArticlesViewHolder viewHolder, Articles model, int position) {
                viewHolder.setName(model.getTitle());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setImage(getApplicationContext(), model.getImage());

                final String art_key = getRef(position).getKey().toString(); //position
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent singleArtActivity = new Intent(MainActivity.this,SingleArticleActivity.class);
                        singleArtActivity.putExtra("ArticleId",art_key);
                        startActivity(singleArtActivity);
                    }
                });
            }
        };
        mRecyclerView.setAdapter(FBRA);
    }


    public static class ArticlesViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public ArticlesViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setName(String name){
            TextView title_name = (TextView) mView.findViewById(R.id.articleName);
            title_name.setText(name);
        }

        public void setDesc(String desc){
            TextView desc_name = (TextView) mView.findViewById(R.id.articleDesc);
            desc_name.setText(desc);
        }

        public void  setImage(final Context ctx, final String image){
            final ImageView article_image = (ImageView) mView.findViewById(R.id.articleImage);

            //Picasso.with(ctx).load(image).into(article_image);

            Picasso.with(ctx).load(image).networkPolicy(NetworkPolicy.OFFLINE).into(article_image, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {
                    Picasso.with(ctx).load(image).into(article_image);
                }
            });
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }





}
