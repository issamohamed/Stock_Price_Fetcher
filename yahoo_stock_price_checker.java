import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class StockPriceFetcher {
    private static final String API_KEY = "99LZY9F61GXU1LAF";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("What stock would you like to look at today?\nMake sure to type in your stock's NYSE abbreivation! (or 'exit' to quit) ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            String stockSymbol = input.toUpperCase();

            if (fetchAndDisplayStockPrice(stockSymbol)) {
                String choice;
                do {
                    System.out.print("Would you like to look at another stock? (yes/no) ");
                    choice = scanner.nextLine();
                    if (!choice.equalsIgnoreCase("yes") && !choice.equalsIgnoreCase("no")) {
                        System.out.println("Please type in 'yes' or 'no'!");
                    }
                } while (!choice.equalsIgnoreCase("yes") && !choice.equalsIgnoreCase("no"));

                if (choice.equalsIgnoreCase("no")) {
                    break;
                }
            } else {
                System.out.println("Please insert a valid stock!");
            }
        }
    }

    private static boolean fetchAndDisplayStockPrice(String symbol) {
        try {
            URL url = new URL("https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + symbol + "&apikey=" + API_KEY);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            JSONObject data = new JSONObject(response.toString());
            JSONObject globalQuote = data.getJSONObject("Global Quote");

            String fetchedSymbol = globalQuote.getString("01. symbol");
            String price = globalQuote.getString("05. price");

            System.out.println("The current price for " + fetchedSymbol + " is: $" + price);
            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
