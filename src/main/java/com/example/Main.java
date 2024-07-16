package com.example;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            CurrencyConverter converter = new CurrencyConverter();
            ConversionHistory history = new ConversionHistory();
            while (true) {
                // Mostrar el menú
                System.out.println("Seleccione una opción de conversión:");
                System.out.println("1. Dólar >> Peso Argentino");
                System.out.println("2. Peso Argentino >> Dólar");
                System.out.println("3. Dólar >> Real Brasileño");
                System.out.println("4. Real Brasileño >> Dólar");
                System.out.println("5. Dólar >> Peso Colombiano");
                System.out.println("6. Peso Colombiano >> Dólar");
                System.out.println("7. Salir");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (choice == 7) {
                    break;
                }

                String fromCurrency;
                String toCurrency;

                switch (choice) {
                    case 1:
                        fromCurrency = "USD";
                        toCurrency = "ARS";
                        break;
                    case 2:
                        fromCurrency = "ARS";
                        toCurrency = "USD";
                        break;
                    case 3:
                        fromCurrency = "USD";
                        toCurrency = "BRL";
                        break;
                    case 4:
                        fromCurrency = "BRL";
                        toCurrency = "USD";
                        break;
                    case 5:
                        fromCurrency = "USD";
                        toCurrency = "COP";
                        break;
                    case 6:
                        fromCurrency = "COP";
                        toCurrency = "USD";
                        break;
                    default:
                        System.out.println("Opción inválida, intente nuevamente.");
                        continue;
                }

                System.out.println("Ingrese la cantidad a convertir:");
                double amount = scanner.nextDouble();
                scanner.nextLine(); // Consume newline

                Map<String, Double> rates = converter.getExchangeRates(fromCurrency);
                if (!rates.containsKey(toCurrency)) {
                    System.out.println("No se puede convertir a la moneda deseada, intente nuevamente.");
                    continue;
                }

                double rate = rates.get(toCurrency);
                double convertedAmount = converter.convertCurrency(amount, rate);

                ConversionRecord record = new ConversionRecord(fromCurrency, toCurrency, amount, convertedAmount);
                history.addRecord(record);

                System.out.println(record);
            }

            System.out.println("Historial de conversiones:");
            history.printHistory();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
