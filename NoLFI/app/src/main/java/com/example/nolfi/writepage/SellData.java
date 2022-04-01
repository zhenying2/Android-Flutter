package com.example.nolfi.writepage;

/**
 * 사용자 계정 정보 모델 클래스
 */

public class SellData {
    private String productName;
    private String category;
    private String purchaseDate;
    private String expirationDate;
    private String productCost;
    private String sellingPrice;
    private String location;
    private String contact;

    public SellData() {}

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(String purchaseDate) { this.purchaseDate = purchaseDate; }

    public String getExpirationDate() { return expirationDate; }
    public void setExpirationDate(String expirationDate) { this.expirationDate = expirationDate; }

    public String getProductCost() { return productCost; }
    public void setProductCost(String productCost) { this.productCost = productCost; }

    public String getSellingPrice() { return sellingPrice; }
    public void setSellingPrice(String sellingPrice) { this.sellingPrice = sellingPrice; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
}