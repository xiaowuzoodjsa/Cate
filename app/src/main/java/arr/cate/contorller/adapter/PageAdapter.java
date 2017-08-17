package arr.cate.contorller.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 1 on 2017/8/12.
 */
public class PageAdapter extends FragmentPagerAdapter{
    private  List<String> list;
    private List<Fragment> listFrag;;
    public PageAdapter(FragmentManager supportFragmentManager, List<String> list, List<Fragment> listFrag) {
        super(supportFragmentManager);
        this.list=list;
        this.listFrag=listFrag;
    }

    @Override
    public Fragment getItem(int position) {
        return listFrag.get(position);
    }

    @Override
    public int getCount() {
        return listFrag.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }
}
