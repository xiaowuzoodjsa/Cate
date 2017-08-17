package arr.cate.contorller.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import arr.cate.R;
import arr.cate.contorller.adapter.PageAdapter;
import arr.cate.contorller.base.BaseActivity;
import arr.cate.contorller.fragment.AssessFragment;
import arr.cate.contorller.fragment.CommodityFragment;
import arr.cate.contorller.fragment.ShopFragment;

/**
 * Created by 1 on 2017/8/12.
 */

public class MenuActivity extends BaseActivity {

    private TabLayout tab;
    private ViewPager viewp;
    List<String> list=new ArrayList<>();
    List<Fragment> listFrag=new ArrayList<>();

    @Override
    protected void initView() {
        tab = (TabLayout) findViewById(R.id.tab);
        viewp = (ViewPager) findViewById(R.id.viewp);
    }



    @Override
    protected void initData() {
            list.add("商品");
            list.add("评价");
            list.add("商家");
        listFrag.add(new CommodityFragment());
        listFrag.add(new AssessFragment());
        listFrag.add(new ShopFragment());
        PageAdapter adapter=new PageAdapter(getSupportFragmentManager(),list,listFrag);
        viewp.setAdapter(adapter);
        tab.setupWithViewPager(viewp);
    }

    @Override
    protected int initById() {
        return R.layout.activity_menu;
    }

    @Override
    protected void initLinsenter() {

    }
}
