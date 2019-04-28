package com.dongjie.dongjiedemos.share_element;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;

public class ShareElement1Activity extends BaseActivity {
    RecyclerView recyclerView;
    LayoutInflater mInflater;
    int resource[] = {R.mipmap.header, R.mipmap.ic,R.mipmap.header, R.mipmap.ic,R.mipmap.header, R.mipmap.ic,R.mipmap.header, R.mipmap.ic,R.mipmap.header, R.mipmap.ic};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_element1);

        mInflater = LayoutInflater.from(this);
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ShareElementListAdapter adapter = new ShareElementListAdapter();
        recyclerView.setAdapter(adapter);
    }

    class ShareElementListAdapter extends RecyclerView.Adapter<ShareElementListAdapter.MyHolder>{

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new MyHolder(mInflater.inflate(R.layout.item_share_element_layout, null));
        }

        @Override
        public void onBindViewHolder(@NonNull final MyHolder myHolder, final int i) {
            myHolder.imageView.setImageResource(resource[i]);

            myHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ShareElement1Activity.this, ShareElement2Activity.class);
                    intent.putExtra("img_res", resource[i]);
                    Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(ShareElement1Activity.this,myHolder.imageView, "ele_img").toBundle();
                    startActivity(intent,bundle);
                }
            });
        }

        @Override
        public int getItemCount() {
            return resource.length;
        }

        class MyHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            public MyHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.image);
            }
        }
    }
}
