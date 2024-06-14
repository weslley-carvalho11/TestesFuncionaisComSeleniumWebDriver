package br.ce.weslley.test;

import br.ce.weslley.core.DSL;
import br.ce.weslley.core.DriverFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestePrime {

    private DSL dsl;

    @Before
    public void inicializa() {
        dsl = new DSL();
    }

    @After
    public void finalizar() {
        DriverFactory.killDriver();
    }

    @Test
    public void deveInteragirComRadioPrime() {
        DriverFactory.getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml?jfwid=f2a40");
        dsl.clicarRadio(By.xpath("//input[@id='j_idt247:line:0']/../..//span"));
        Assert.assertTrue(dsl.isRadioMarcado("j_idt247:line:0"));
    }
}
