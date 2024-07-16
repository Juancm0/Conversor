package com.example;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {
    private static final String API_KEY = "f2389713fd579315a947e621";

    public Map<String, Double> getExchangeRates(String baseCurrency) throws Exception {
        String url_str = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + baseCurrency;
        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();

        JsonObject conversionRates = jsonobj.getAsJsonObject("conversion_rates");

        Map<String, Double> rates = new HashMap<>();
        for (Map.Entry<String, JsonElement> entry : conversionRates.entrySet()) {
            rates.put(entry.getKey(), entry.getValue().getAsDouble());
        }

        return rates;
    }

    public double convertCurrency(double amount, double rate) {
        return amount * rate;
    }
}
