package com.juaracoding;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProduckPage {
    WebDriver driver;

    @FindBy(css = ".inventory_item:nth-of-type(1) .btn_inventory")
    WebElement firstProduct;

    @FindBy(css = ".inventory_item:nth-of-type(2) .btn_inventory")
    WebElement secondProduct;

    @FindBy(className = "shopping_cart_link")
    WebElement cartIcon;

    public ProduckPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addFirstProductToCart() {
        firstProduct.click();
    }

    public void addSecondProductToCart() {
        secondProduct.click();
    }

    public void goToCart() {
        cartIcon.click();
    }
}
