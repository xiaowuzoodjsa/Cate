package arr.cate.contorller.fragment;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.GridView;
import android.widget.TextView;

import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import arr.cate.R;
import arr.cate.contorller.activity.MapActivity;
import arr.cate.contorller.activity.MenuActivity;
import arr.cate.contorller.adapter.Myadapter;
import arr.cate.contorller.adapter.NameAdapter;
import arr.cate.contorller.app.ImageLo;
import arr.cate.contorller.app.MyApp;
import arr.cate.contorller.base.BaseFragment;
import arr.cate.model.Answer;
import arr.cate.model.HttpFactroy;
import arr.cate.model.entity.DataBean;
import arr.cate.model.entity.HomeBean;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private Banner banner;
    private String url="http://123.206.14.104:8080/takeout/home?latitude=116.30142&longitude=40.05087";
    private GridView grid;
    private RecyclerView recy;
    private List<DataBean.BodyBean> body;
    private RecyclerAdapterWithHF myadapter;
    private TextView tv;
    private TextView tvmap;


    @Override
    protected int initById() {
        return R.layout.home;
    }

    @Override
    protected void initView(View view) {
        banner = (Banner) view.findViewById(R.id.banner);
        grid = (GridView) view.findViewById(R.id.grid);
        recy = (RecyclerView) view.findViewById(R.id.recy);
        tv = (TextView) view.findViewById(R.id.hometv);
    }

    List<DataBean.HeadBean.PromotionListBean> promotionList;
    @Override
    protected void initData() {
        View view = getActivity().getWindow().peekDecorView();
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);

        HttpFactroy.getHttp().doGet(url, new Answer() {
            @Override
            public void onSuccess(final String string) {
                MyApp.baseActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson=new Gson();
                        HomeBean homeBean = gson.fromJson(string, HomeBean.class);
                        String data = homeBean.getData();
                        DataBean dataBean = gson.fromJson(data, DataBean.class);
                         promotionList = dataBean.getHead().getPromotionList();
                        /**
                         * 遍历获取图片地址,标题
                         */

                        banner();


                        //GridView
                        List<DataBean.HeadBean.CategorieListBean> categorieList = dataBean.getHead().getCategorieList();
                        Myadapter adapter=new Myadapter(getActivity(),categorieList,R.layout.griditem);
                        grid.setAdapter(adapter);
                        body = dataBean.getBody();


                        //recycleview赋值
                        body.remove(9);
                        recy.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
                        NameAdapter adater= new NameAdapter(getActivity(),body,R.layout.shopitem);
                        myadapter = new RecyclerAdapterWithHF((RecyclerView.Adapter)adater);
                        recy.setAdapter(myadapter);


                        //RecycleView的点击事件
                        myadapter.setOnItemClickListener(new RecyclerAdapterWithHF.OnItemClickListener() {
                            @Override
                            public void onItemClick(RecyclerAdapterWithHF adapter, RecyclerView.ViewHolder vh, int position) {
                                startActivity(new Intent(getActivity(), MenuActivity.class));
                            }
                        });
                    }
                });
            }
            @Override
            public void onError(String string) {
                System.out.println("错误信息"+string);
            }
        });
    }

    @Override
    protected void initLinsenter() {
        tv.setOnClickListener(this);
    }



    private void banner() {
        List<String> list=new ArrayList<>();
         List<String> list2=new ArrayList<>();
        for (int i = 0; i <promotionList.size() ; i++) {
            list.add(promotionList.get(i).getPic());
            list2.add(promotionList.get(i).getInfo());
        }
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setImageLoader(new ImageLo())
                .setImages(list)
                .setBannerTitles(list2)
                .setDelayTime(2000)
                .isAutoPlay(true)
                .setIndicatorGravity(BannerConfig.CENTER)
                .start();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.hometv:
                Intent intent = new Intent(getActivity(), MapActivity.class);
                startActivityForResult(intent, 110);
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==110&&resultCode==1){
            String text = data.getStringExtra("text");
            tv.setText(text);
        }
    }
}
