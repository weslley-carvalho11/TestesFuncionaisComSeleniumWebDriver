package br.ce.weslley.test;

import br.ce.weslley.core.DSL;
import br.ce.weslley.core.DriverFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteAlert {

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
    public void deveInteragirComAlert() {
        dsl.clicarBotao("alert");
        String texto = dsl.alertaObterTextoEAceite();
        Assert.assertEquals("Alert Simples", texto);
        dsl.escrever("elementosForm:nome", texto);

    }

    @Test
    public void deveInteragirComAlertConfirm() {
        dsl.clicarBotao("confirm");
        Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoEAceite());
        Assert.assertEquals("Confirmado", dsl.alertaObterTextoEAceite());


        dsl.clicarBotao("confirm");
        Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoENega());
        Assert.assertEquals("Negado", dsl.alertaObterTextoEAceite());
    }

    @Test
    public void deveInteragirComAlertPrompt() {
        dsl.clicarBotao("prompt");
        Assert.assertEquals("Digite um numero", dsl.alertaObterTexto());
        dsl.alertaEscrever("12");
        Assert.assertEquals("Era 12?", dsl.alertaObterTextoEAceite());
        Assert.assertEquals(":D", dsl.alertaObterTextoEAceite());
    }
}
