package root.db.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Max on 10/16/2014.
 */
@Entity
@Table(name="Orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    private Long buyerId;

    @Column
    private Long whenOrdered;

    @Column
    private String deliveryAddress;

    @Column
    private Boolean courier;

    @Column
    private Long amount;

    @Column
    private Double cost;

    @Column
    private String costLocale;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Long getWhenOrdered() {
        return whenOrdered;
    }

    public void setWhenOrdered(Long whenOrdered) {
        this.whenOrdered = whenOrdered;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Boolean getCourier() {
        return courier;
    }

    public void setCourier(Boolean courier) {
        this.courier = courier;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getCostLocale() {
        return costLocale;
    }

    public void setCostLocale(String costLocale) {
        this.costLocale = costLocale;
    }
}
