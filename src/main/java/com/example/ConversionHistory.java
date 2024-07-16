package com.example;

import java.util.ArrayList;
import java.util.List;

public class ConversionHistory {
    private List<ConversionRecord> history = new ArrayList<>();

    public void addRecord(ConversionRecord record) {
        history.add(record);
    }

    public void printHistory() {
        for (ConversionRecord record : history) {
            System.out.println(record);
        }
    }
}
