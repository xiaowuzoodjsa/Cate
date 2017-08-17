package arr.cate.model.http;

import android.os.Handler;

import java.io.File;
import java.io.IOException;

import arr.cate.model.Answer;
import arr.cate.model.IHttp;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 1 on 2017/8/11.
 */

public class OkHttpUtils implements IHttp{
    private static OkHttpUtils utils;
    private  OkHttpClient client;
    private final Handler handler;

    public static OkHttpUtils getInstance(){
        if (utils==null){
            synchronized (OkHttpUtils.class){
                utils=new OkHttpUtils();
            }
        }
        return utils;
    }

    private OkHttpUtils() {
        client = new OkHttpClient();
        handler = new Handler();
    }

    @Override
    public void doGet(String url, final Answer answer) {
        Request request=new Request.Builder().url(url).build();
        Call call=client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                answer.onError(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                answer.onSuccess(string);
            }
        });
    }

    public interface HttpResponseCallBack{
        void error(IOException str);
        void response(String str);
    }


    public  void doPostPicture(String url, File file,final HttpResponseCallBack httpResponseCallBack) {
        //OkHttpClient作为全局静态变量已经定义过了
        //2.创建一个请求体
        RequestBody body;
        //3.创建一个请求体建造器
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        builder.addFormDataPart("headPicture", "headPicture.jpg", RequestBody.create(MediaType.parse("image/jpg"), file));

        body = builder.build();

        //3.创建一个请求，利用构建器方式添加url和请求体。
        Request request = new Request.Builder().post(body).url(url).build();

        //4.定义一个call，利用okhttpclient的newcall方法来创建对象。因为Call是一个接口不能利用构造器实例化。
        Call call = client.newCall(request);

        //5.这是异步调度方法，上传和接受的工作都在子线程里面运作，如果要使用同步的方法就用call.excute(),此方法返回的就是Response
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                httpResponseCallBack.error(e);//错误发生时的处理
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                httpResponseCallBack.response(response.body().string());//把服务器发回来的数据response解析成string传入方法
            }
        });
    }

}
