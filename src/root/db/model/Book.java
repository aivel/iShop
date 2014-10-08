/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package root.db.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Max
 */

@Entity
@Table(name="Books")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    
    @Column
    private Long dateWritten;
    
    @Column
    private String title;
    
    @Column
    private String authorName;
    
    @Column
    private String coverUrl;

    @Column
    private Double price;

    @Column
    private String priceLocale;
    
    public Book() {
        dateWritten = 0L;
        title = "";
        authorName = null;
        coverUrl = "";
        price = 0D;
        priceLocale = "";
    }
    
    public Book(final Book book) {
        id = book.getId();
        dateWritten = book.getDateWritten();
        title = book.getTitle();
        authorName = book.getAuthorName();
        coverUrl = book.getCoverUrl();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @return the dateWritten
     */
    public Long getDateWritten() {
        return dateWritten;
    }

    /**
     * @param dateWritten the dateWritten to set
     */
    public void setDateWritten(final Long dateWritten) {
        this.dateWritten = dateWritten;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * @return the url
     */
    public String getCoverUrl() {
        return coverUrl;
    }

    /**
     * @param url the url to set
     */
    public void setCoverUrl(String url) {
        this.coverUrl = url;
    }

    /**
     * @return the authorName
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * @param authorName the authorName to set
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPriceLocale() {
        return priceLocale;
    }

    public void setPriceLocale(String priceLocale) {
        this.priceLocale = priceLocale;
    }
}
