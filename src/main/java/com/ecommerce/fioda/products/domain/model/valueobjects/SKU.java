package com.ecommerce.fioda.products.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record SKU(
        String sku
) {
    public SKU() {
        this(null);
    }
    public SKU(String brand, String color, String name) {
        this(generateSKU(brand, color, name));
    }

    private static String generateSKU(String brand, String color, String name) {
        String brandPart = "";
        String colorPart = "";
        String namePart = "";

        if (brand.isEmpty()) brandPart = "XXXXX";
        if (color.isEmpty()) colorPart = "X";


        if(brand.length() >= 5) brandPart = brand.substring(0, 5).toUpperCase();
        else brandPart = brand.toUpperCase();

        if(colorPart.isEmpty()) colorPart = color.substring(0, 1).toUpperCase();

        if(name.length() >= 5) namePart = name.substring(0, 5).toUpperCase();
        else namePart = name.toUpperCase();

        return brandPart + "-" + colorPart + "-" + namePart;
    }
}
