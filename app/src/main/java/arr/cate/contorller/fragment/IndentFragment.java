package arr.cate.contorller.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;

import arr.cate.R;
import arr.cate.contorller.activity.ConcludeActivity;
import arr.cate.contorller.adapter.IndentAdapter;
import arr.cate.contorller.app.MyApp;
import arr.cate.contorller.base.BaseFragment;
import arr.cate.model.Answer;
import arr.cate.model.HttpFactroy;
import arr.cate.model.entity.IndentBean;
import arr.cate.model.entity.IndentDataBean;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndentFragment extends BaseFragment {


    private String url="http://123.206.14.104:8080/TakeoutService/order?userId=3626";
    private RecyclerView recy;
    private RecyclerAdapterWithHF myadapter;

    @Override
    protected int initById() {
        return R.layout.indent;
    }

    @Override
    protected void initView(View view) {
        recy = (RecyclerView) view.findViewById(R.id.recy);
    }

    @Override
    protected void initData() {
        HttpFactroy.getHttp().doGet(url, new Answer() {
            @Override
            public void onSuccess(final String string) {
                MyApp.baseActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson=new Gson();
                        IndentDataBean indentBean = gson.fromJson(string, IndentDataBean.class);
                        String data = indentBean.getData();
                        JsonArray jsonParser=new JsonParser().parse(data).getAsJsonArray();
                        ArrayList<IndentBean> list= new ArrayList<>();
                        for (JsonElement user:jsonParser) {
                            IndentBean indentBean1 = gson.fromJson(user, IndentBean.class);
                            list.add(indentBean1);
                        }
                        recy.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
                        IndentAdapter adapter=new IndentAdapter(getActivity(),list);
                        myadapter = new RecyclerAdapterWithHF((RecyclerView.Adapter)adapter);
                        recy.setAdapter(myadapter);
                        myadapter.setOnItemClickListener(new RecyclerAdapterWithHF.OnItemClickListener() {
                            @Override
                            public void onItemClick(RecyclerAdapterWithHF adapter, RecyclerView.ViewHolder vh, int position) {
                                startActivity(new Intent(getActivity(), ConcludeActivity.class));
                            }
                        });
                    }
                });
            }

            @Override
            public void onError(String string) {

            }
        });
    }

    @Override
    protected void initLinsenter() {

    }



}
