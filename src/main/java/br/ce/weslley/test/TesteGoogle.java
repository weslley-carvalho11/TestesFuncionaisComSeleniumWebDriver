package br.ce.weslley.test;

import br.ce.weslley.core.DriverFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteGoogle {

    @Before
    public void iniciar() {
        DriverFactory.getDriver().get("http://www.google.com");
    }

    @After
    public void finalizar() {
        DriverFactory.killDriver();
    }

    @Test
    public void teste() {
        System.out.println(DriverFactory.getDriver().getTitle());
        Assert.assertEquals("Google", DriverFactory.getDriver().getTitle());
    }
}
