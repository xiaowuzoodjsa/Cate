package arr.cate.model.entity;

import java.io.Serializable;

/**
 * 保存手势密码相关设置
 */
public class Setting implements Serializable {
    public static final String SHOW_PATH = "1";
    public static final String HIDE_PATH = "0";

    private String gesture; // 手势密码
    private String showPath;// 是否显示轨迹（"0"隐藏/"1"显示）

    public Setting(String gesture, String showPath) {
        this.gesture = gesture;
        this.showPath = showPath;
    }

    public String getGesture() {
        return gesture;
    }

    public void setGesture(String gesture) {
        this.gesture = gesture;
    }

    public String getShowPath() {
        return showPath;
    }

    public void setShowPath(String showPath) {
        this.showPath = showPath;
    }
}
