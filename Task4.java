import java.util.HashMap;
import java.util.Map;

public class Task4 {

    public static void main(String[] args) {
        double[] prices = {13.3, 45, 23, 45, 545, 3453, 23, 78, 4431, 4554, 55, 909, 234, 84, 34};
        PriceViewer priceViewer = new PriceViewer(prices);
        Double price = priceViewer.getPrice("Prod. 2", "Apr");
        System.out.println("Price: " + price);
    }
}

class PriceViewer {
    private static final String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    Map<String, Map<String, Double>> table = new HashMap<>();

    PriceViewer(double[] prices) {
        if (prices.length == 0) throw new IllegalArgumentException("prices array length must be > 0");

        int productCount = prices.length / 12 + (prices.length % 12 > 0 ? 1 : 0);

        for (int i = 0; i < productCount; i++) {
            Map<String, Double> productRow = new HashMap<>();
            for (int j = 0; j < months.length; j++) {
                int priceIndex = i * 12 + j;
                double price = priceIndex < prices.length ? prices[priceIndex] : 0;
                productRow.put(months[j], price);
            }
            table.put("Prod. " + (i + 1), productRow);
        }

    }

    Double getPrice(String productName, String monthName) {
        Map<String, Double> productRow = table.get(productName);
        if(productRow == null) throw new IllegalArgumentException("no product with name " + productName);
        return productRow.get(monthName);
    }
}

