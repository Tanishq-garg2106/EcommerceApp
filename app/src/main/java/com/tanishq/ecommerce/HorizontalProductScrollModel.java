package com.tanishq.ecommerce;

public class HorizontalProductScrollModel {

    private int productImage;
    private String productTitle;
    private String productDescription;
    private String productRates;

    public HorizontalProductScrollModel(int productImage,String productTitle,String productDescription,String productRates) {
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.productDescription = productDescription;
        this.productRates = productRates;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductRates() {
        return productRates;
    }

    public void setProductRates(String productRates) {
        this.productRates = productRates;
    }
}

