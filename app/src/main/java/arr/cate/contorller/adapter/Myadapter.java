package arr.cate.contorller.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import arr.cate.R;
import arr.cate.contorller.adapter.baselist.CommonAdapter;
import arr.cate.contorller.adapter.baselist.ViewHolder;
import arr.cate.model.entity.DataBean;

/**
 * Created by 1 on 2017/8/12.
 */
public class Myadapter extends CommonAdapter<DataBean.HeadBean.CategorieListBean>{

private Context context;
    public Myadapter(Context context, List<DataBean.HeadBean.CategorieListBean> data, int layoutId) {
        super(context, data, layoutId);
        this.context=context;
    }
    @Override
    public void setConverView(ViewHolder myViewHolder, DataBean.HeadBean.CategorieListBean categorieListBean) {

        Glide.with(context).load(categorieListBean.getPic()).into(((ImageView)myViewHolder.getView(R.id.imagev)));
        ((TextView)myViewHolder.getView(R.id.tv)).setText(categorieListBean.getName());
    }
}
