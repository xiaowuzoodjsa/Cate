package arr.cate.contorller.activity;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import arr.cate.R;
import arr.cate.contorller.base.BaseActivity;
import arr.cate.model.http.DBManager;
import arr.cate.model.ShopDao;

/**
 * Created by 1 on 2017/8/16.
 */

public class ShopCarActivity extends BaseActivity{

    private ListView listv;
    private CheckBox checkBoxall;
    List<ShopDao> list=new ArrayList<>();

    @Override
    protected void initView() {
        listv = (ListView) findViewById(R.id.shopcarlist);
        checkBoxall = (CheckBox) findViewById(R.id.checkall);
    }

    @Override
    protected void initData() {
        list.clear();
        List<ShopDao> shopDaos = DBManager.getInstance(ShopCarActivity.this).queryUserList();
        list.addAll(shopDaos);
        final ShopCarAdapter adapter=new ShopCarAdapter(ShopCarActivity.this,list);
        listv.setAdapter(adapter);

    }


    @Override
    protected int initById() {
        return R.layout.shopcar;
    }

    @Override
    protected void initLinsenter() {
            checkBoxall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        for (int i = 0; i <list.size() ; i++) {

                        }
                    }
                }
            });
    }
}
