package com.yangyang.recycylerviewcardview;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by asus on 2016/3/8.
 */
public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
    private List<News> data;
    private LayoutInflater layoutInflater;
    private Context context;

    public MyAdapter(Context context, List<News> data) {
        this.context = context;
        this.data = data;
        layoutInflater=LayoutInflater.from(context);
    }
    //创建View
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.swip_item,parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }
    //绑定ViewHolder
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.im.setImageResource(R.mipmap.ic_launcher);//等一下再写异步加载图片
        holder.tvTitle.setText(data.get(position).getTitle());
        holder.tvContent.setText(data.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
class MyHolder extends RecyclerView.ViewHolder{
    ImageView im;
    TextView tvTitle,tvContent;

    public MyHolder(View itemView) {
        super(itemView);
        im= (ImageView) itemView.findViewById(R.id.im1);
        tvTitle= (TextView) itemView.findViewById(R.id.tvtitle);
        tvContent= (TextView) itemView.findViewById(R.id.tvcontent);
    }
}



