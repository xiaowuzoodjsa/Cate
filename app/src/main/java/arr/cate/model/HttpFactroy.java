package arr.cate.model;

import arr.cate.model.http.OkHttpUtils;

/**
 * Created by 1 on 2017/8/11.
 */

public class HttpFactroy {
    private static final int OkHttp=1;
    private static int Type=OkHttp;
    private static OkHttpUtils instance;

    public static IHttp getHttp(){
        switch (Type){
            case OkHttp:
                instance = OkHttpUtils.getInstance();
                break;
        }
        return instance;
    }
}
