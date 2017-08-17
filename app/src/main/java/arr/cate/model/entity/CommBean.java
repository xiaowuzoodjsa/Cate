package arr.cate.model.entity;

/**
 * Created by 1 on 2017/8/16.
 */

public class CommBean {

    private String Title;
    private String name;
    private String Icon;
    private String Form;
    private double newPrice;

    public CommBean() {
        super();
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }

    public String getForm() {
        return Form;
    }

    public void setForm(String form) {
        Form = form;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public CommBean(String title, String name, String icon, String form, double newPrice) {

        Title = title;
        this.name = name;
        Icon = icon;
        Form = form;
        this.newPrice = newPrice;
    }
}