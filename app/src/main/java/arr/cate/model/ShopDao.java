package arr.cate.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 1 on 2017/8/17.
 */
@Entity
public class ShopDao {
    @Id
    private Long id;
    private String name;
    private String imag;
    private int count;
    private Double price;
    public Double getPrice() {
        return this.price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public int getCount() {
        return this.count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public String getImag() {
        return this.imag;
    }
    public void setImag(String imag) {
        this.imag = imag;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 2037180871)
    public ShopDao(Long id, String name, String imag, int count, Double price) {
        this.id = id;
        this.name = name;
        this.imag = imag;
        this.count = count;
        this.price = price;
    }
    @Generated(hash = 300302291)
    public ShopDao() {
    }
}
