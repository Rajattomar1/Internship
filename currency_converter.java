import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class currency_converter {
    // A map to hold exchange rates (base currency to target currency)
    private static final Map<String, Double> exchangeRates = new HashMap<>();

    static {
        // Initialize some exchange rates for demonstration purposes
        exchangeRates.put("USD_EUR", 0.85); // 1 USD to EUR
        exchangeRates.put("USD_JPY", 110.0); // 1 USD to JPY
        exchangeRates.put("EUR_USD", 1.18); // 1 EUR to USD
        exchangeRates.put("JPY_USD", 0.0091); // 1 JPY to USD
        // Add more rates as needed
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input base currency
        System.out.print("Enter base currency (e.g., USD, EUR, JPY): ");
        String baseCurrency = scanner.next().toUpperCase();

        // Input target currency
        System.out.print("Enter target currency (e.g., USD, EUR, JPY): ");
        String targetCurrency = scanner.next().toUpperCase();

        // Construct exchange rate key
        String rateKey = baseCurrency + "_" + targetCurrency;

        // Check if the exchange rate is available
        if (!exchangeRates.containsKey(rateKey)) {
            System.out.println("Exchange rate not available for the selected currencies.");
            scanner.close();
            return;
        }

        // Input amount to convert
        System.out.print("Enter amount to convert from " + baseCurrency + ": ");
        double amount = scanner.nextDouble();

        // Convert the amount
        double exchangeRate = exchangeRates.get(rateKey);
        double convertedAmount = amount * exchangeRate;

        // Display the result
        System.out.printf("Converted amount: %.2f %s\n", convertedAmount, targetCurrency);

        scanner.close();
    }
}