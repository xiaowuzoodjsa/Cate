package arr.cate.contorller.activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import arr.cate.R;
import arr.cate.model.ShopDao;

/**
 * Created by 1 on 2017/8/16.
 */
public class ShopCarAdapter extends BaseAdapter{

        private ShopCarActivity activity;
    private List<ShopDao> shopDaos;
    public ShopCarAdapter(ShopCarActivity shopCarActivity, List<ShopDao> shopDaos) {
        this.activity=shopCarActivity;
        this.shopDaos=shopDaos;
    }

    @Override
    public int getCount() {
        return shopDaos.size();
    }

    @Override
    public Object getItem(int position) {
        return shopDaos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView=View.inflate(activity, R.layout.shopcaritem,null);
            viewHolder=new ViewHolder();
            viewHolder.image= (ImageView) convertView.findViewById(R.id.shopcarimage);
            viewHolder.text= (TextView) convertView.findViewById(R.id.shopcarname);
            viewHolder.text1= (TextView) convertView.findViewById(R.id.shopcarprice);
            viewHolder.checkBox= (CheckBox) convertView.findViewById(R.id.checkboxs);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Glide.with(activity).load(shopDaos.get(position).getImag()).into(viewHolder.image);
        viewHolder.text.setText(shopDaos.get(position).getName());
        viewHolder.text1.setText(shopDaos.get(position).getPrice()+"");

        return convertView;
    }
    class ViewHolder{
        CheckBox checkBox;
        ImageView image;
        TextView text,text1;
    }
}
