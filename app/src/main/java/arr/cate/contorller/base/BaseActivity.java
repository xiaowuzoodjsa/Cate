package arr.cate.contorller.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import arr.cate.contorller.app.MyApp;

/**
 * Created by 1 on 2017/8/11.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApp.baseActivity=this;
        setContentView(initById());
        initView();
        initData();
        initLinsenter();
    }
    protected abstract void initView();
    protected abstract void initData();
    protected abstract int initById();
    protected abstract void initLinsenter();
}
