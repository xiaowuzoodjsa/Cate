package arr.cate.contorller.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zzti.fengyongge.imagepicker.PhotoSelectorActivity;

import java.io.File;
import java.io.IOException;
import java.util.List;

import arr.cate.R;
import arr.cate.contorller.activity.LoginActivity;
import arr.cate.contorller.base.BaseFragment;
import arr.cate.model.http.OkHttpUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalFragment extends BaseFragment implements View.OnClickListener {


    private TextView tetxtv;
    private String name;
    private ImageView headImage;
    private TextView content;
    private TextView login;
    private Receiver receiver;
    protected static final int CHOOSE_PICTURE = 0;
    private File file;
    String Path="http://123.206.14.104:8080/FileUploadDemo/FileUploadServlet";
    private String nameQQ;
    private String QQurl;
    private String urls;

    @Override
    protected int initById() {
        return R.layout.personal;
    }

    @Override
    protected void initView(View view) {
        tetxtv = (TextView) view.findViewById(R.id.login);
        headImage = (ImageView) view.findViewById(R.id.headImage);
        content = (TextView) view.findViewById(R.id.content);
        login = (TextView) view.findViewById(R.id.login);
    }

    @Override
    protected void initData() {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction("aaa");
        Receiver receiver=new Receiver();
        //注册广播
        getActivity().registerReceiver(receiver, myIntentFilter);
    }

    @Override
    protected void initLinsenter() {
        tetxtv.setOnClickListener(this);
        headImage.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                if (name==null){
                startActivity(new Intent(getActivity(), LoginActivity.class));}else {
                    Toast.makeText(getActivity(),"信息",Toast.LENGTH_SHORT).show();
                    headImage.setImageResource(R.mipmap.ic_launcher);
                    content.setVisibility(View.GONE);
                    login.setText(PersonalFragment.this.name);
                }
                break;
            case R.id.headImage:
                Intent openAlbumIntent = new Intent(getActivity(), PhotoSelectorActivity.class);
                openAlbumIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                openAlbumIntent.setType("image/*");
                openAlbumIntent.putExtra("limit", 1 );
                startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取ImagePicker选中的图片的本地地址
            List<String> paths = (List<String>) data.getExtras().getSerializable("photos");
            for (int i = 0; i <paths.size() ; i++) {
                String s = paths.get(i);
                File file=new File(s);
           // System.out.println(paths.get(i)+"posi++++++++++++++++++");
            OkHttpUtils.getInstance().doPostPicture(Path, file, new OkHttpUtils.HttpResponseCallBack() {
                @Override
                public void error(IOException str) {
                    System.out.println("失败"+str);

                }

                @Override
                public void response(String str) {
                    System.out.println("成功"+str);
                    urls = "http://123.206.14.104:8080/FileUploadDemo/files/headPicture.jpg";
                    Glide.with(getActivity()).load(urls).error(R.mipmap.ic_launcher).into(headImage);
                }
            });
        }
    }
    public class Receiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            name = intent.getExtras().getString("username");
            nameQQ = intent.getExtras().getString("name");
            QQurl = intent.getExtras().getString("url");
            if (QQurl!=null&&nameQQ!=null){
            Glide.with(getActivity()).load(QQurl).into(headImage);
                login.setText(nameQQ);
                content.setVisibility(View.GONE);
            }


        }
    }

}
