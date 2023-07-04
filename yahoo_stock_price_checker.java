import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class StockPriceFetcher {
    private static final String API_KEY = "99LZY9F61GXU1LAF";
    private static final String API_URL = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=";

    public static void main(String[] args) {
        String stockSymbol = "AAPL"; // Example: Apple Inc.

        try {
            // Creating the URL object with the desired stock symbol and API key
            URL url = new URL(API_URL + stockSymbol + "&apikey=" + API_KEY);

            // Opening the connection and setting the request method
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Reading the response from the API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // Parsing the response and extracting the current stock price
            String price = parseStockPrice(response.toString());
            System.out.println("The current price for " + stockSymbol + " is: $" + price);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String parseStockPrice(String json) {
        try {
            JSONObject data = new JSONObject(json);
            JSONObject globalQuote = data.getJSONObject("Global Quote");
            String price = globalQuote.getString("05. price");
            return price;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
