package arr.cate.contorller.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import arr.cate.R;
import arr.cate.model.entity.CateBean;

/**
 * Created by ASUS on 2017/8/13.
 */

public class RightAdapter extends CustomizeLVBaseAdapter {

    //上下文
    private Context mContext;
    //标题
    private Linsenter linsenter;
    private List<String> listtitle;
    //内容
    private List<List<CateBean.ListBean>> listss;
    private LayoutInflater inflater;

    public RightAdapter(Context mContext, List<String> listtitle, List<List<CateBean.ListBean>> listss) {
        this.mContext = mContext;
        this.listtitle = listtitle;
        this.listss = listss;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public Object getItem(int section, int position) {
        return listss.get(section).get(position);
    }

    @Override
    public long getItemId(int section, int position) {
        return position;
    }

    @Override
    public int getSectionCount() {
        return listtitle.size();
    }

    @Override
    public int getCountForSection(int section) {
        return listss.get(section).size();
    }

    @Override
    public View getItemView(final int section, final int position, View convertView, ViewGroup parent) {
        ChildViewHolder holder = null;
        if (convertView == null) {
            holder = new ChildViewHolder();
            //加载
            convertView = inflater.inflate(R.layout.lv_customize_item_right, parent, false);
            //绑定
            holder.lv_customize_item_image = (ImageView) convertView.findViewById(R.id.lv_customize_item_image);
            holder.lv_customize_item_title = (TextView) convertView.findViewById(R.id.lv_customize_item_title);
            holder.lv_customize_item_content = (TextView) convertView.findViewById(R.id.lv_customize_item_content);
            holder.lv_customize_item_sell = (TextView) convertView.findViewById(R.id.lv_customize_item_sell);
            holder.lv_customize_item_newprice = (TextView) convertView.findViewById(R.id.lv_customize_item_newprice);
            holder.lv_customize_item_oldprice = (TextView) convertView.findViewById(R.id.lv_customize_item_oldprice);
            holder.lv_customize_item_oldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            holder.image= (ImageView) convertView.findViewById(R.id.ssadd);
            convertView.setTag(holder);
        } else {
            holder = (ChildViewHolder) convertView.getTag();
        }
        //设置内容
        holder.lv_customize_item_title.setText(listss.get(section).get(position).getName());
        holder.lv_customize_item_content.setText(listss.get(section).get(position).getForm());
        holder.lv_customize_item_sell.setText("月销售:"+listss.get(section).get(position).getMonthSaleNum());
        holder.lv_customize_item_newprice.setText("￥："+(int) listss.get(section).get(position).getNewPrice()+"");
        holder.lv_customize_item_oldprice.setText("￥："+listss.get(section).get(position).getOldPrice()+"");
        Glide.with(mContext).load(listss.get(section).get(position).getIcon()).into(holder.lv_customize_item_image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linsenter.click(v,section,position);
            }
        });
        //点击事件
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, listss.get(section).get(position).getForm(), Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
        HeaderViewHolder holder = null;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            //加载
            convertView = inflater.inflate(R.layout.lv_customize_item_header, parent, false);
            //绑定
            holder.lv_customize_item_header_text = (TextView) convertView.findViewById(R.id.lv_customize_item_header_text);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        //不可点击
        convertView.setClickable(false);
        //设置标题
        holder.lv_customize_item_header_text.setText(listtitle.get(section));
        return convertView;
    }

    class ChildViewHolder {
        //Item图片
        private ImageView lv_customize_item_image;
        //Item内容
        private TextView lv_customize_item_title;
        private TextView lv_customize_item_content;
        private TextView lv_customize_item_sell;
        private TextView lv_customize_item_newprice;
        private TextView lv_customize_item_oldprice;
        private ImageView image;
    }

    class HeaderViewHolder {
        //标题
        private TextView lv_customize_item_header_text;
    }
    public void OnClick(Linsenter linsenter){
            this.linsenter=linsenter;
    }
    public interface Linsenter{
        void click(View view,int s,int position);
    }
}
