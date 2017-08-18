package arr.cate.contorller.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import arr.cate.R;
import arr.cate.contorller.activity.ChangePassWordActivity;
import arr.cate.contorller.activity.SettingPasswordActivity;
import arr.cate.contorller.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends BaseFragment implements View.OnClickListener {
    private TextView settingpassword,changePassword;
    @Override
    protected int initById() {
        return R.layout.more;
    }

    @Override
    protected void initView(View view) {
        settingpassword = (TextView)view. findViewById(R.id.act_main_settingpassword);
        changePassword = (TextView) view.findViewById(R.id.act_main_updatapassword);
        settingpassword.setOnClickListener(this);
        changePassword.setOnClickListener(this);
    }
    @Override
    protected void initData() {

    }
    @Override
    protected void initLinsenter() {

    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.act_main_settingpassword:
                intent.setClass(getActivity(),SettingPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.act_main_updatapassword:
                intent.setClass(getActivity(),ChangePassWordActivity.class);
                startActivity(intent);
                break;
        }
    }
}
