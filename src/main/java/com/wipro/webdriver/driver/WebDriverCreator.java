package com.wipro.webdriver.driver;

import org.openqa.selenium.WebDriver;

public abstract class WebDriverCreator {

    protected WebDriver driver = null;

    public abstract WebDriver factoryMethod();
}