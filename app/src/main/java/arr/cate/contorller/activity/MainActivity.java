package arr.cate.contorller.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import arr.cate.R;
import arr.cate.contorller.base.BaseActivity;
import arr.cate.contorller.fragment.HomeFragment;
import arr.cate.contorller.fragment.IndentFragment;
import arr.cate.contorller.fragment.MoreFragment;
import arr.cate.contorller.fragment.PersonalFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    private RadioButton homeText;
    private FrameLayout fragme;
    private RadioButton indent;
    private RadioButton personal;
    private RadioButton more;
    private HomeFragment homeFragment;
    private IndentFragment indentFragment;
    private PersonalFragment personalFragment;
    private MoreFragment moreFragment;
    private RadioButton home;
    private RadioButton personal1;

    @Override
    protected void initView() {
        homeText = (RadioButton) findViewById(R.id.home);
        fragme = (FrameLayout) findViewById(R.id.fragme);
        indent = (RadioButton) findViewById(R.id.indent);
        personal = (RadioButton) findViewById(R.id.personal);
        more = (RadioButton) findViewById(R.id.more);
        home = (RadioButton) findViewById(R.id.home);
        personal1 = (RadioButton) findViewById(R.id.personal);
    }

    @Override
    protected void initData() {

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
            homeFragment = new HomeFragment();
            transaction.add(R.id.fragme,homeFragment);
            transaction.show(homeFragment);
            transaction.commit();
    }

    @Override
    protected int initById() {
        return R.layout.activity_main;
    }

    @Override
    protected void initLinsenter() {
        indent.setOnClickListener(this);
        personal.setOnClickListener(this);
        more.setOnClickListener(this);
        homeText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        hideAll(transaction);
        switch (v.getId()){
            case R.id.home:
                if (homeFragment==null){
                homeFragment = new HomeFragment();
                    transaction.add(R.id.fragme,homeFragment);
                    transaction.show(homeFragment);
            }else {
                    transaction.show(homeFragment);
                }
            break;
            case R.id.indent:
                if (indentFragment==null){
                indentFragment = new IndentFragment();
                    transaction.add(R.id.fragme,indentFragment);
                    transaction.show(indentFragment);
            }else {
                    transaction.show(indentFragment);
                }
                break;
            case R.id.personal:
                if (personalFragment==null){
                personalFragment = new PersonalFragment();
                    transaction.add(R.id.fragme,personalFragment);
                    transaction.show(personalFragment);
            }else {
                    transaction.show(personalFragment);
                }
                break;
            case R.id.more:
                if ( moreFragment== null) {
                moreFragment = new MoreFragment();
                    transaction.add(R.id.fragme,moreFragment);
                    transaction.show(moreFragment);
                }else {
                    transaction.show(moreFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hideAll(FragmentTransaction transaction) {
        if (homeFragment!=null){
            transaction.hide(homeFragment);
        }
        if (indentFragment!=null){
            transaction.hide(indentFragment);
        }
        if (personalFragment!=null){
            transaction.hide(personalFragment);
        }
        if (moreFragment!=null){
            transaction.hide(moreFragment);
        }
    }
}
