

need to compile with: javac -cp .:json-20210307.jar StockPriceFetcher.java







need to run with: java -cp .:json-20210307.jar StockPriceFetcher




descriptiom:

This Java program allows users to retrieve the current stock price of any company listed on the New York Stock Exchange (NYSE). It's a command-line application that interacts with the Alpha Vantage API to fetch real-time stock data.The program offers a user-friendly interface where users can enter the NYSE abbreviation of the desired stock. It validates the input, retrieves the stock price using the API, and displays it on the screen. Users can continue looking up stock prices for multiple stocks, with the option to exit the program at any time. During the development process, I faced a few challenges. One of the key challenges was integrating with the Alpha Vantage API and handling the JSON response returned by the API. I used the org.json library to parse the JSON data and extract the necessary information, such as the stock symbol and price. Another challenge was ensuring a smooth user experience and handling user input validation. I implemented error handling to handle cases where users input an invalid stock symbol or provide incorrect responses when prompted. Overall, the Stock Price Fetcher program provides a simple yet powerful tool for retrieving real-time stock prices.

