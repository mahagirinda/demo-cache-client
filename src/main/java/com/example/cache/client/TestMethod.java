package com.example.cache.client;

import java.util.Objects;

public class TestMethod {
    public static void main(String[] args) {

        String obj = null;

        String objStr = Objects.toString(obj, null);

        if (!Objects.isNull(objStr)) {
            System.out.println("isi : " + objStr);
        } else {
            System.out.println("kosong " + objStr);
        }

    }
}
