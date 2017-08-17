package arr.cate.contorller.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 1 on 2017/8/11.
 */

public abstract class BaseFragment extends Fragment{



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(initById(), null);
            initView(view);
        initData();
        initLinsenter();
        return view;
    }
    protected abstract int initById();
    protected abstract void initView(View view);
    protected abstract void initData();
    protected abstract void initLinsenter();
}
