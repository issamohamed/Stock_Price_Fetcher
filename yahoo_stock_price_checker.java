import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class StockPriceFetcher {
    private static final String API_URL = "https://finance.yahoo.com/quote/";

    public static void main(String[] args) {
        String stockSymbol = "AAPL"; // example: Apple Inc.

        try {
            // Creating the URL object with the desired stock symbol
            URL url = new URL(API_URL + stockSymbol);

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
            String jsonString = response.toString();
            int startIndex = jsonString.indexOf("root.App.main = ") + 17;
            int endIndex = jsonString.indexOf(";\n}(this));");
            String json = jsonString.substring(startIndex, endIndex);
            JSONObject data = new JSONObject(json);
            JSONObject quote = data.getJSONObject("context").getJSONObject("dispatcher").getJSONObject("stores").getJSONObject("QuoteSummaryStore").getJSONArray("price").getJSONObject(0);
            String price = quote.getJSONObject("regularMarketPrice").getString("fmt");

            // Print the current stock price
            System.out.println("The current price for " + stockSymbol + " is: " + price);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
