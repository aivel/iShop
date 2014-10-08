package root.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Max on 10/3/2014.
 */
public class CurrencyProvider {
     static final class LocalePair {
        public String from;
        public String to;

        public LocalePair(final String from, final String to) {
            this.from = from;
            this.to = to;
        }
    }

    private static Map<LocalePair, Double> cm = new HashMap<>();

    static {
        cm = new HashMap<>();
        cm.put(new LocalePair("en_US", "ru_RU"), 0.03);
        cm.put(new LocalePair("ru_RU", "en_US"), 39.58);

        cm.put(new LocalePair("en_US", "es_ES"), 1.26);
        cm.put(new LocalePair("es_ES", "en_US"), 0.79);

        cm.put(new LocalePair("ru_RU", "es_ES"), 50.09);
        cm.put(new LocalePair("es_ES", "ru_RU"), 0.02);
    }

    public static double convertCurrency(final String from, final String to, final double amount) {
        double rate = 1;

        for (Map.Entry<LocalePair, Double> entry: cm.entrySet()) {
            final LocalePair lp = entry.getKey();

            if (lp.from.contains(to) && lp.to.contains(from)) {
                rate = entry.getValue();
                break;
            }
        }

        return rate * amount;
    }
}
