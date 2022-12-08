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
        String[] lines = response.toString().split("\n");
        for (String l : lines) {
            if (l.contains("regularMarketPrice")) {
                String[] split = l.split(":");
                String price = split[1].trim();
                price = price.substring(0, price.length() - 2);
                System.out.println("The current price for " + stockSymbol + " is: $" + price);
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}
