package arr.cate.contorller.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import arr.cate.R;
import arr.cate.contorller.adapter.baseRecy.CommonRecyclerAdapter;
import arr.cate.contorller.adapter.baseRecy.View2Hodler;
import arr.cate.model.entity.DataBean;

/**
 * Created by 1 on 2017/8/12.
 */

public class NameAdapter extends CommonRecyclerAdapter<DataBean.BodyBean> {

    public NameAdapter(Context context, List<DataBean.BodyBean> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(View2Hodler holder, DataBean.BodyBean item) {
        ((TextView)holder.getView(R.id.shopname)).setText(item.getSeller().getName());
    }
}
