package com.example.auto;

import com.example.auto.Configurable;

public class SportConfiguration implements Configurable {
    @Override
    public String makeConfiguration() {
        return "Sport";
    }
}