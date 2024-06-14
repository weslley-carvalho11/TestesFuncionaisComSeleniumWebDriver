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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TesteAjax {
    private DSL dsl;

    @Before
    public void inicializa() {
        DriverFactory.getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml?jfwid=dc7cb");
        dsl = new DSL();
    }

    @After
    public void finalizar() {
        DriverFactory.killDriver();
    }

    @Test
    public void testAjax() {
        dsl.clicarBotao("j_idt246:j_idt250");
        dsl.escrever("j_idt246:name", "Osvaldo");
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 30);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("J_idt98")));
        Assert.assertEquals("Teste", dsl.obterTexto("j_idt246:display"));
    }
}
