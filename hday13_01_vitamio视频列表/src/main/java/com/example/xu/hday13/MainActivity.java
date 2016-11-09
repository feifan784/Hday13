package com.example.xu.hday13;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;

public class MainActivity extends AppCompatActivity {


    private RecyclerView rcv;
    private List<String>list = new ArrayList<>();
    private VideoView lastvv;
    private MyAdapter adapter;
    private String[] strs={"http://bvideo.spriteapp.cn/video/2016/0610/575add617fc8b_wpd.mp4",
            "http://wvideo.spriteapp.cn/video/2016/0610/cdbba1a0-2ef5-11e6-ac8f-90b11c479401_wpd.mp4 "};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Vitamio.isInitialized(getApplicationContext());
        setContentView(R.layout.activity_main);

        initView();

        initData();

        initAdapter();


    }

    private void initAdapter() {
        rcv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter = new MyAdapter();
        rcv.setAdapter(adapter);


    }

    private void initData() {
        for (int i = 0; i <20 ; i++) {
            list.add(strs[i%strs.length]);
        }

    }

    private void initView() {
        rcv= (RecyclerView) findViewById(R.id.rcv);
    }
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>{

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.item,parent,false));
        }

        @Override
        public void onBindViewHolder(final MyHolder holder, final int position) {

            holder.tv.setText(position+"  "+list.get(position));
            holder.iv.setImageResource(R.mipmap.ic_launcher);

            holder.vv.setVisibility(View.GONE);
            if(holder.vv.isPlaying()){//可去掉holder.vv.isPlaying()
                holder.vv.stopPlayback();
            }

            holder.iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (lastvv!=null&&lastvv.isPlaying()){//可去掉  lastvv.isPlaying()
                        lastvv.setVisibility(View.GONE);
                        lastvv.stopPlayback();
                    }

                    holder.vv.setVisibility(View.VISIBLE);
                    holder.vv.setVideoPath(list.get(position));
                    holder.vv.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE,2);
                    lastvv = holder.vv;

                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyHolder extends RecyclerView.ViewHolder{
            TextView tv;
            ImageView iv;
            VideoView vv;

            public MyHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.tv);
                iv = (ImageView) itemView.findViewById(R.id.iv);
                vv = (VideoView) itemView.findViewById(R.id.vv);

            }
        }

    }
}
