package com.yangyang.recycylerviewcardview;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2016/3/8.
 */
public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
    private List<News> data;
    private LayoutInflater layoutInflater;
    private Context context;
    //private List<Integer> mHeights;

    public MyAdapter(Context context, List<News> data) {
        this.context = context;
        this.data = data;
        layoutInflater=LayoutInflater.from(context);
//        mHeights=new ArrayList<>();
//        for(int i=0;i<data.size();i++){
//            mHeights.add((int) (100+Math.random()*300));//设置的高度自己要注意大概是多少
//        }
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
//        ViewGroup.LayoutParams params=holder.itemView.getLayoutParams();
//        params.height=mHeights.get(position);
//        holder.itemView.setLayoutParams(params);
        holder.im.setImageResource(R.mipmap.ic_launcher);//等一下再写异步加载图片
        holder.tvTitle.setText(data.get(position).getTitle());
        holder.tvContent.setText(data.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    //这两个方法在本应用其实没什么卵用，比较适合在本地获取数据的应用。
    public void add(int pos){
        News news=new News();
        news.setIconUrl("http://10.151.208.83:8080/JsonProject2/1.jpg");
        news.setContent("你好");
        news.setTitle("ADD Title");
        data.add(news);
        notifyItemInserted(pos);//不可以用notifydataset()，这样的话没有显示效果。
    }
    public void delete(int pos){
        data.remove(data.get(pos));
        notifyItemRemoved(pos);
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



