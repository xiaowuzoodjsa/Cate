package arr.cate.contorller.adapter.baseRecy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;



public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter<View2Hodler> {

    protected Context mContext;
    protected LayoutInflater mInflater;
    //数据怎么办？利用泛型
    protected List<T> mDatas;
    // 布局怎么办？直接从构造里面传递
    private int mLayoutId;

    public CommonRecyclerAdapter(Context context, List<T> datas, int layoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = datas;
        this.mLayoutId = layoutId;
    }

    @Override
    public View2Hodler onCreateViewHolder(ViewGroup parent, int viewType) {
        // 先inflate数据
        View itemView = mInflater.inflate(mLayoutId, parent, false);
 //修改item的宽高的方法
        itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        // 返回ViewHolder
        View2Hodler holder = new View2Hodler(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(View2Hodler holder, int position) {
        // 绑定怎么办？回传出去
        convert(holder, mDatas.get(position));
    }

    /**
     * 利用抽象方法回传出去，每个不一样的Adapter去设置
     *
     * @param item 当前的数据
     */
    public abstract void convert(View2Hodler holder, T item);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
