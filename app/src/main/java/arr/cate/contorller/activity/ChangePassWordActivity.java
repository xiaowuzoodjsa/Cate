package arr.cate.contorller.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import arr.cate.R;
import arr.cate.contorller.fragment.ChangePassFragment;

/** 修改密码
 * Created by zzj on 2016/11/19.
 */
public class ChangePassWordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_changepassword);
        initData();
    }

    private void initData() {
        ChangePassFragment lockFragment = new ChangePassFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.act_change_lockfrag,lockFragment).commit();
    }
}
