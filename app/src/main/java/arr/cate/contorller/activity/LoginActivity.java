package arr.cate.contorller.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.json.JSONObject;

import java.util.Map;

import arr.cate.R;
import arr.cate.contorller.base.BaseActivity;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import static arr.cate.R.id.phone;

/**
 * Created by 1 on 2017/8/12.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private TextView qqLogin;
    private EventHandler eventHandler;
    private EditText edPhone;
    private Button getCode;
    private EditText yzm;
    private String trim;
    private String strPhoneNumber;
    private Button btu_login;
    private TextView weixinLogin;
    private TextView xinlangLogin;


    @Override
    protected void initView() {
        qqLogin = (TextView) findViewById(R.id.QQLogin);
        edPhone = (EditText) findViewById(phone);
        getCode = (Button) findViewById(R.id.btu_get);
        yzm = (EditText) findViewById(R.id.ed_yzm);
        btu_login = (Button) findViewById(R.id.btu_login);
        weixinLogin = (TextView) findViewById(R.id.weixinLogin);
        xinlangLogin = (TextView) findViewById(R.id.xinlangLogin);
    }

    @Override
    protected void initData() {
        // 如果希望在读取通信录的时候提示用户，可以添加下面的代码，并且必须在其他代码调用之前，否则不起作用；如果没这个需求，可以不加这行代码
        SMSSDK.setAskPermisionOnReadContact(true);

        // 创建EventHandler对象
        eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                Message message = myHandler.obtainMessage(0x00);
                message.arg1 = event;
                message.arg2 = result;
                message.obj = data;
                myHandler.sendMessage(message);
            }
        };

        // 注册监听器
        SMSSDK.registerEventHandler(eventHandler);
    }

    @Override
    protected int initById() {
        return R.layout.login;
    }

    @Override
    protected void initLinsenter() {
        qqLogin.setOnClickListener(this);
        edPhone.setOnClickListener(this);
        getCode.setOnClickListener(this);
        yzm.setOnClickListener(this);
        btu_login.setOnClickListener(this);
        weixinLogin.setOnClickListener(this);
        xinlangLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.QQLogin:
                mShareAPI = UMShareAPI.get(LoginActivity.this);
                SHARE_MEDIA platform = SHARE_MEDIA.QQ;
                mShareAPI.doOauthVerify(LoginActivity.this, platform, umAuthListener);
                break;
            case R.id.weixinLogin:
                mShareAPI = UMShareAPI.get(LoginActivity.this);
                SHARE_MEDIA platforms = SHARE_MEDIA.WEIXIN;
                UMShareAPI.get(this).getPlatformInfo(this, platforms, umAuthListener);
                break;
            case R.id.xinlangLogin:
                mShareAPI = UMShareAPI.get(LoginActivity.this);
                SHARE_MEDIA xinlang = SHARE_MEDIA.SINA;
                UMShareAPI.get(this).getPlatformInfo(this, xinlang, umAuthListener);
                break;
        }
                trim = yzm.getText().toString().trim();
                if (v.getId() == R.id.btu_login) {
                    String strCode = yzm.getText().toString();
                    if (null != strCode && strCode.length() == 4) {
                        // Log.d(TAG, code.getText().toString());
                        SMSSDK.submitVerificationCode("86", strPhoneNumber,strCode);
                    } else {
                        Toast.makeText(this, "密码长度不正确", Toast.LENGTH_SHORT).show();
                    }
                } else if (v.getId() == R.id.btu_get) {
                    strPhoneNumber = edPhone.getText().toString();
                    if (null == strPhoneNumber || "".equals(strPhoneNumber) || strPhoneNumber.length() != 11) {
                        Toast.makeText(this, "电话号码输入有误", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    SMSSDK.getVerificationCode("86", strPhoneNumber);
                    getCode.setClickable(false);
                    new Thread() {
                        @Override
                        public void run() {
                            int totalTime = 60;
                            for (int i = 0; i < totalTime; i++) {
                                Message message = myHandler.obtainMessage(0x01);
                                message.arg1 = totalTime - i;
                                myHandler.sendMessage(message);
                                try {
                                    sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            myHandler.sendEmptyMessage(0x02);
                        }
                    }.start();
        }
    }
    private String TAG = "RegistActivity.class";
    private UMShareAPI mShareAPI;
    Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x00:
                    int event = msg.arg1;
                    int result = msg.arg2;
                    Object data = msg.obj;
                    Log.e(TAG, "result : " + result + ", event: " + event + ", data : " + data);
                    if (result == SMSSDK.RESULT_COMPLETE) { //回调  当返回的结果是complete
                        if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) { //获取验证码
                            Toast.makeText(LoginActivity.this, "发送验证码成功", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "get verification code successful.");
                        } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE){ //提交验证码
                            Log.d(TAG, "submit code successful");
                            Toast.makeText(LoginActivity.this, "提交验证码成功", Toast.LENGTH_SHORT).show();
                            String trim = yzm.getText().toString().trim();
                            SharedPreferences mSharedPreferences = getSharedPreferences("loginUser", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = mSharedPreferences.edit();
                            editor.putString("user_id",strPhoneNumber);
                            editor.putString("user_psw",trim);
                            editor.commit();

                            Intent intent1 = new Intent();
                            intent1.setAction("aaa");
                            intent1.putExtra("username",strPhoneNumber);
                            sendBroadcast(intent1);
                            finish();

                        } else {
                            Log.d(TAG, data.toString());
                        }
                    } else { //进行操作出错，通过下面的信息区分析错误原因
                        try {
                            Throwable throwable = (Throwable) data;
                            throwable.printStackTrace();
                            JSONObject object = new JSONObject(throwable.getMessage());
                            String des = object.optString("detail");//错误描述
                            int status = object.optInt("status");//错误代码
                            //错误代码：  http://wiki.mob.com/android-api-%E9%94%99%E8%AF%AF%E7%A0%81%E5%8F%82%E8%80%83/
                            Log.e(TAG, "status: " + status + ", detail: " + des);
                            if (status > 0 && !TextUtils.isEmpty(des)) {
                                Toast.makeText(LoginActivity.this, des, Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 0x01:
                    getCode.setText("重新发送(" + msg.arg1 + ")");
                    break;
                case 0x02:
                    getCode.setText("获取验证码");
                    getCode.setClickable(true);
                    break;
            }
        }
    };
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText( getApplicationContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText( getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText( getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mShareAPI.onActivityResult(requestCode, resultCode, data);
    }
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }
}
