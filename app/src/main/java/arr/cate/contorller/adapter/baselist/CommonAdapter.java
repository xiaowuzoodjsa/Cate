package arr.cate.contorller.adapter.baselist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;


/**
 * Created by 1 on 2017/8/11.
 */

public abstract class CommonAdapter<T> extends BaseAdapter {

    private Context context;
    private List<T> data;
    private int layoutId;
    protected LayoutInflater layoutInflater;

    public CommonAdapter(Context context, List<T> data, int layoutId) {
                this.context = context;
                this.layoutInflater = LayoutInflater.from(context);
                this.data = data;
                this.layoutId = layoutId;
            }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //获取ViewHolder对象
       ViewHolder viewHolder=ViewHolder.get(context,convertView,parent,layoutId,position);
        setConverView(viewHolder,data.get(position));
        return viewHolder.getConvertView();
    }
    //用户需要实现的方法
    public abstract void setConverView(ViewHolder myViewHolder, T t);
}
