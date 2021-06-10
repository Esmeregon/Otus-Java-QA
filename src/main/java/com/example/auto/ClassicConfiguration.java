package com.example.auto;

import com.example.auto.Configurable;

public class ClassicConfiguration implements Configurable {
    @Override
    public String makeConfiguration() {
        return "Classic";
    }
}