package arr.cate.contorller.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import java.util.List;

import arr.cate.R;
import arr.cate.model.entity.DataBean;

/**
 * Created by 1 on 2017/8/12.
 */
public class ShopAdapters extends RecyclerView.Adapter<ShopAdapters.ViewHolder>{
    private FragmentActivity activity;
    private List<DataBean.BodyBean> body;
    private ViewHolder viewHolder;

    public ShopAdapters(FragmentActivity activity, List<DataBean.BodyBean> body, int shopitem) {
        this.activity=activity;
        this.body=body;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==0){
            View view = View.inflate(activity, R.layout.shopitem, null);
            viewHolder = new ViewHolder(view);
        }
        if (viewType==1){
            View view=View.inflate(activity,R.layout.itemgrid,null);
            viewHolder=new ViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        if (body.get(position).getType()==0){
            return 0;
        }else {
            return 1;
        }

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        List<?> recommendInfos = body.get(9).getRecommendInfos();
        NameAdapter nameAdapter=new NameAdapter(activity,body,R.layout.item);
        if (holder.text!=null){
            holder.text.setText(body.get(position).getSeller().getName());
        }
        if (holder.gridView!=null){
           // holder.gridView.setAdapter(nameAdapter);

        }
    }

    @Override
    public int getItemCount() {
        return body.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        GridView gridView;
        public ViewHolder(View itemView) {
            super(itemView);
            text= (TextView) itemView.findViewById(R.id.shopname);
            gridView= (GridView) itemView.findViewById(R.id.gridv);
        }
    }
}
