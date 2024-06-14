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

import java.util.concurrent.TimeUnit;

public class TesteSincronismo {

    private DSL dsl;

    @Before
    public void iniciar() {
        DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL();
    }

    @After
    public void finalizar() {
        DriverFactory.killDriver();
    }

    @Test
    public void deveUtilizarEsperaFixa() throws InterruptedException {
        dsl.clicarBotao("buttonDelay");
        Thread.sleep(5000);
        dsl.escrever("novoCampo", "Deu Certo?");
        Assert.assertEquals("Deu Certo?", dsl.obterValorCampo("novoCampo"));
    }
    //Espera fixa

    @Test
    public void deveUtilizarEsperaImplicita() {
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        dsl.clicarBotao("buttonDelay");
        dsl.escrever("novoCampo", "Deu Certo?");
        Assert.assertEquals("Deu Certo?", dsl.obterValorCampo("novoCampo"));
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }
    //Para que os outros testes não sejam afetados pela espera
    //A espera é aplicada a todo o código, o que muda para explita é que ele espera apenas um elemento.

    @Test
    public void deveUtilizarEsperaExplicita() {
        dsl.clicarBotao("buttonDelay");
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
        dsl.escrever("novoCampo", "Deu Certo?");
        Assert.assertEquals("Deu Certo?", dsl.obterValorCampo("novoCampo"));
    }
}
