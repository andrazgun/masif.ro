package com.masif.utils;

public class Tools {

    public static String replaceElements(String element, String valueToBeReplaced, String valueReplaceWith) {
        return element.replaceAll(valueToBeReplaced, valueReplaceWith);
    }
}