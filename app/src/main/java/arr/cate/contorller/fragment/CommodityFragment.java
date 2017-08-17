package arr.cate.contorller.fragment;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.PointF;
import android.support.percent.PercentRelativeLayout;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import arr.cate.R;
import arr.cate.contorller.activity.ShopCarActivity;
import arr.cate.contorller.adapter.LeftAdapter;
import arr.cate.contorller.adapter.RightAdapter;
import arr.cate.contorller.app.MyApp;
import arr.cate.contorller.base.BaseFragment;
import arr.cate.model.ShopDao;
import arr.cate.model.entity.BezierTypeEvaluator;
import arr.cate.model.entity.CateBean;
import arr.cate.model.entity.CommodityBean;
import arr.cate.model.http.DBManager;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 1 on 2017/8/16.
 */
public class CommodityFragment extends BaseFragment {

    //左边的ListView
    private ListView lv_left;
    //左边ListView的Adapter
    private LeftAdapter leftAdapter;
    //左边的数据存储
    private List<String> listtitle = new ArrayList<String>();
    //左边数据的标志
    private List<Boolean> flagArray;
    //右边的ListView
    private HaveHeaderListView lv_right;
    //右边的ListView的Adapter
    private RightAdapter rightAdapter;
    //右边的数据存储
//    private List<List<String>> rightStr;
    //是否滑动标志位
    private Boolean isScroll = false;

    private List<List<CateBean.ListBean>> listss = new ArrayList<>();
    private PercentRelativeLayout viewById;
    private ImageView imagev;


    @Override
    protected int initById() {
        return R.layout.comm;
    }

    @Override
    protected void initView(View view) {

        lv_left = (ListView)view. findViewById(R.id.lv_left);
        flagArray = new ArrayList<>();
        leftAdapter = new LeftAdapter(getActivity(), listtitle, flagArray);
        lv_left.setAdapter(leftAdapter);

        lv_right = (HaveHeaderListView) view.findViewById(R.id.lv_right);
        rightAdapter = new RightAdapter(getActivity(), listtitle, listss);
        lv_right.setAdapter(rightAdapter);
        viewById = (PercentRelativeLayout) view.findViewById(R.id.per);
        imagev = (ImageView) view.findViewById(R.id.gouwuc);
    }
    @Override
    protected void initData() {
        flagArray.add(true);
        flagArray.add(false);
        flagArray.add(false);
        flagArray.add(false);
        flagArray.add(false);
        flagArray.add(false);
        flagArray.add(false);
        flagArray.add(false);
        flagArray.add(false);
        flagArray.add(false);
        flagArray.add(false);
        new Thread(new Runnable() {
            @Override
            public void run() {

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://123.206.14.104:8080/TakeoutService/goods?sellerId=101").build();

                try {
                    Response execute = client.newCall(request).execute();
//                    Log.e("TAG",execute.body().string());

                    Gson gson = new Gson();
                    CommodityBean commodityBean = gson.fromJson(execute.body().string(), CommodityBean.class);
                    String data1 = commodityBean.getData();

                    JsonParser parser = new JsonParser();
                    JsonArray asJsonArray = parser.parse(data1).getAsJsonArray();
                    for (JsonElement element : asJsonArray) {
                        CateBean commodityBean1 = gson.fromJson(element, CateBean.class);
                        List<CateBean.ListBean> list1 = commodityBean1.getList();

                        listtitle.add(commodityBean1.getName());
                        listss.add(list1);

                        MyApp.baseActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                leftAdapter.notifyDataSetChanged();
                                rightAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void initLinsenter() {
        imagev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ShopCarActivity.class));
            }
        });
        lv_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                isScroll = false;
                for (int i = 0; i < listtitle.size(); i++) {
                    if (i == position) {
                        flagArray.set(i, true);
                    } else {
                        flagArray.set(i, false);
                    }
                }
                //更新
                leftAdapter.notifyDataSetChanged();
                int rightSection = 0;
                for (int i = 0; i < position; i++) {
                    //查找
                    rightSection += rightAdapter.getCountForSection(i) + 1;
                }
                //显示到rightSection所代表的标题
                lv_right.setSelection(rightSection);
            }
        });
        lv_right.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    // 当不滚动时
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        // 判断滚动到底部
                        if (lv_right.getLastVisiblePosition() == (lv_right.getCount() - 1)) {
                            lv_left.setSelection(ListView.FOCUS_DOWN);
                        }
                        // 判断滚动到顶部
                        if (lv_right.getFirstVisiblePosition() == 0) {
                            lv_left.setSelection(0);
                        }
                        break;
                }

            }

            int y = 0;
            int x = 0;

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (isScroll) {
                    for (int i = 0; i < listss.size(); i++) {
                        if (i == rightAdapter.getSectionForPosition(lv_right.getFirstVisiblePosition())) {
                            flagArray.set(i, true);
                            //获取当前标题的标志位
                            x = i;
                        } else {
                            flagArray.set(i, false);
                        }
                    }
                    if (x != y) {
                        leftAdapter.notifyDataSetChanged();
                        //将之前的标志位赋值给y，下次判断
                        y = x;
                    }
                } else {
                    isScroll = true;
                }
            }
        });

        rightAdapter.OnClick(new RightAdapter.Linsenter() {
            @Override
            public void click(View view, int s,int position) {
                String name = listss.get(s).get(position).getName();
                String icon = listss.get(s).get(position).getIcon();
                double newPrice = listss.get(s).get(position).getNewPrice();
                //null,name,icon,0,newPrice
                ShopDao shopDao = new ShopDao(null, name, icon, 0, newPrice);
                DBManager.getInstance(getActivity()).insertUser(shopDao);
                System.out.println(name);
                //贝塞尔起始数据点
                int[] startPosition = new int[2];
                //贝塞尔结束数据点
                int[] endPosition = new int[2];
                //控制点
                int[] recyclerPosition = new int[2];

                view.getLocationInWindow(startPosition);
                //shop_car  购物车的view
                imagev.getLocationInWindow(endPosition);
                //recycler 你的recyclerview
                lv_right.getLocationInWindow(recyclerPosition);

                PointF startF = new PointF();
                PointF endF = new PointF();
                PointF controllF = new PointF();

                startF.x = startPosition[0];
                startF.y = startPosition[1] - recyclerPosition[1];
                endF.x = endPosition[0];
                endF.y = endPosition[1] - recyclerPosition[1];
                controllF.x = endF.x;
                controllF.y = startF.y;

                //Myapp.activity 你的上下文
                final ImageView imageView = new ImageView(getActivity());
                //goods_re 你最外层的view
                viewById.addView(imageView);
                //这里可以自己选择一张图片
                Glide.with(getActivity()).load(icon).into(imageView);
                imageView.getLayoutParams().width = 100;
                imageView.getLayoutParams().height = 100;
                imageView.setVisibility(View.VISIBLE);
                imageView.setX(startF.x);
                imageView.setY(startF.y);

                ValueAnimator valueAnimator = ValueAnimator.ofObject(new BezierTypeEvaluator(controllF), startF, endF);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        PointF pointF = (PointF) animation.getAnimatedValue();
                        imageView.setX(pointF.x);
                        imageView.setY(pointF.y);
                    }
                });


                //shoping_car 你的购物车view
                ObjectAnimator objectAnimatorX = new ObjectAnimator().ofFloat(imagev, "scaleX", 0.6f, 1.0f);
                ObjectAnimator objectAnimatorY = new ObjectAnimator().ofFloat(imagev, "scaleY", 0.6f, 1.0f);
                objectAnimatorX.setInterpolator(new AccelerateInterpolator());
                objectAnimatorY.setInterpolator(new AccelerateInterpolator());
                AnimatorSet set = new AnimatorSet();
                set.play(objectAnimatorX).with(objectAnimatorY).after(valueAnimator);
                set.setDuration(800);
                set.start();

                set.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                        //goods_re  你的最外层view
                        viewById.removeView(imageView);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
            }
        });

    }

}