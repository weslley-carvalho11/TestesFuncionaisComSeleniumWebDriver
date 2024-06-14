package br.ce.weslley.test;

import br.ce.weslley.core.DSL;
import br.ce.weslley.core.DriverFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class TesteFrameEJanelas {
    private DSL dsl;

    @Before
    public void iniciar() {
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.MICROSECONDS);
        DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL();
    }

    @After
    public void finalizar() {
        DriverFactory.killDriver();
    }

    @Test
    public void deveInteragirComFrames() {
        dsl.entrarFrame("Frame1");
        dsl.clicarBotao("frameButton");
        String msg = dsl.alertaObterTextoEAceite();
        Assert.assertEquals("Frame OK!", msg);
        dsl.sairFrame();
        dsl.escrever("elementosForm:nome", msg);
    }

    @Test
    public void deveInteragirComJanelas() {
        dsl.clicarBotao("buttonPopUpEasy");
        dsl.trocarJanela("Popup");
        dsl.escrever(By.tagName("textarea"), "Deu certo?");
        DriverFactory.getDriver().close();
        dsl.trocarJanela((String) DriverFactory.getDriver().getWindowHandles().toArray()[0]);
        dsl.escrever(By.tagName("textarea"), "e agora?");
    }

    @Test
    public void deveInteragirComJanelasSemTitulo() {
        dsl.clicarBotao("buttonPopUpHard");
        System.out.println(DriverFactory.getDriver().getWindowHandle());
        System.out.println(DriverFactory.getDriver().getWindowHandles());
        dsl.trocarJanela((String) DriverFactory.getDriver().getWindowHandles().toArray()[1]);
        dsl.escrever(By.tagName("textarea"), "Deu certo?");
        dsl.trocarJanela((String) DriverFactory.getDriver().getWindowHandles().toArray()[0]);
        dsl.escrever(By.tagName("textarea"), "e agora?");
    }

    @Test
    public void deveInteragirComFrameEscondido() {
        WebElement frame = DriverFactory.getDriver().findElement(By.id("frame2"));
        dsl.executarJavaScript("window.scrollBy(0, arguments[0])", frame.getLocation().y);
        dsl.entrarFrame("frame2");
        dsl.clicarBotao("frameButton");
        String msg = dsl.alertaObterTextoEAceite();
        Assert.assertEquals("Frame OK!", msg);
    }
}