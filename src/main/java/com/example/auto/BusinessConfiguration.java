package com.example.auto;

import com.example.auto.Configurable;

public class BusinessConfiguration implements Configurable {
    @Override
    public String makeConfiguration() {
        return "Business";
    }
}
