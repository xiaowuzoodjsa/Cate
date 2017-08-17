package arr.cate.contorller.adapter.baselist;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 1 on 2017/8/11.
 */

public class ViewHolder {
    private SparseArray<View> mViews;
    private View convertView;

    public ViewHolder(Context context, ViewGroup parent, int layoutId, int position){
            this.mViews=new SparseArray<>();
        convertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        convertView.setTag(this);
    }
    //拿到一个ViewHolder对象
    public static ViewHolder get(Context context,View convertView,ViewGroup parent,int layoutId,int position){

        if (convertView==null){
            return new ViewHolder(context,parent,layoutId,position);
        }
        return (ViewHolder) convertView.getTag();
    }
    //通过控件的Id获取对于的控件，如果没有则加入views
    public <T extends View> T getView(int id){
        View views = mViews.get(id);
        if (views==null){
            views= convertView.findViewById(id);
            mViews.put(id,views);
        }
        return (T) views;
    }
    public View getConvertView()
    {
        return convertView;
    }

}
