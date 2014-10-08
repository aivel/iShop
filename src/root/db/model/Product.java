package root.db.model;

import java.io.Serializable;

/**
 * Created by Max on 9/28/2014.
 */
public class Product implements Serializable {
    private long id;
    private long amount;
    private String title;
    private String authorName;
    private String coverUrl;
    private double price;
    private String priceLocale;

    public Product() {}

    public Product(long id, long amount, String title, String authorName, String coverUrl, double price, String priceLocale) {
        this.id = id;
        this.amount = amount;
        this.title = title;
        this.authorName = authorName;
        this.coverUrl = coverUrl;
        this.price = price;
        this.priceLocale = priceLocale;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPriceLocale() {
        return priceLocale;
    }

    public void setPriceLocale(String priceLocale) {
        this.priceLocale = priceLocale;
    }
}
