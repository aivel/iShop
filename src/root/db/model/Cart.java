package root.db.model;

import root.utils.CurrencyProvider;
import root.utils.Utils;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Max on 9/28/2014.
 */
public class Cart implements Serializable {
    private Map<Long, Product> products;
    private double totalPrice;
    private String currentLocale;

    public Cart() {
        products = new HashMap<>();
        this.setCurrentLocale("");
    }

    public Cart(final String currentLocale) {
        products = new HashMap<>();
        this.setCurrentLocale(Utils.fulfillLocale(new Locale( currentLocale ) ).toString());
    }

    public void addProduct(final long id, final long amount, final String title,
                           final String authorName, final String coverUrl, final double price,
                           final String priceLocale) {
        if (amount <= 0 || id < 0)
            return;

        if(products.containsKey(id)) {
            final Product product = products.get(id);
            product.setAmount(product.getAmount() + amount);
        } else {
            final Product product = new Product(id, amount, title, authorName, coverUrl, price, priceLocale);

            products.put(id, product);
        }
    }

    public void remProduct(final long id, final long amount) {
        if (amount <= 0 || id < 0)
            return;

        if (!products.containsKey(id))
            return;

        final long oldAmount = products.get(id).getAmount();

        if (amount >= oldAmount)
            products.remove(id);
        else
            products.get(id).setAmount(oldAmount - amount);
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products.values());
    }

    public double getTotalPrice() {
        totalPrice = 0d;

        for (final Product product: products.values())
            totalPrice += CurrencyProvider.convertCurrency(product.getPriceLocale(),
                    getCurrentLocale(), product.getPrice() * product.getAmount());

        return Math.round(totalPrice * 100.0) / 100.0;
    }

    public void setCurrentLocale(String currentLocale) {
        this.currentLocale = currentLocale;
    }

    public boolean isNotEmpty() {
        return !products.isEmpty();
    }

    public String getCurrentLocale() {
        return currentLocale;
    }
}
