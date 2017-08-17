package arr.cate.contorller.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import arr.cate.R;
import arr.cate.model.entity.IndentBean;

/**
 * Created by 1 on 2017/8/14.
 */
public class IndentAdapter extends RecyclerView.Adapter<IndentAdapter.ViewHolder>{

    private FragmentActivity activity;
    private ArrayList<IndentBean> list;
    public IndentAdapter(FragmentActivity activity, ArrayList<IndentBean> list) {
        this.activity=activity;
        this.list=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(activity, R.layout.indentitem, null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        holder.text.setText(list.get(position).getSeller().getName());
        Glide.with(activity).load(list.get(position).getSeller().getPic()).into(holder.image);
        holder.text2.setText(list.get(position).getDetail().getTime());
        holder.text3.setText(list.get(position).getGoodsInfos().get(0).getName()+"等"+list.get(position).getGoodsInfos().size()+"件商品");

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text,text1,text2,text3,text4;

        public ViewHolder(View itemView) {
            super(itemView);
          image= (ImageView) itemView.findViewById(R.id.indentImage);
           text= (TextView) itemView.findViewById(R.id.indentName);
           text1= (TextView) itemView.findViewById(R.id.zhuangtai);
         text2= (TextView) itemView.findViewById(R.id.indenttime);
          text3= (TextView) itemView.findViewById(R.id.content);
            text4= (TextView) itemView.findViewById(R.id.indentprice);
        }
    }
}
