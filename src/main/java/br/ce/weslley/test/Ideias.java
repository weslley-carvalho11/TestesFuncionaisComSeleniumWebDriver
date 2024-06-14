package br.ce.weslley.test;

import br.ce.weslley.core.DSL;
import br.ce.weslley.core.DriverFactory;
import br.ce.weslley.page.CampoTreinamentoPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;

public class Ideias {

    private DSL dsl;
    private CampoTreinamentoPage page;

    @Before
    public void iniciar() {
        DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL();
        page = new CampoTreinamentoPage();
    }

    @After
    public void finalizar() {
        DriverFactory.killDriver();
    }

    @Test
    public void testMarcarTodosElementosEmVermelho() {
        dsl.executarJavaScript("arguments[0].style.border = arguments[1]", DriverFactory.getDriver().findElement(By.id("elementosForm:nome")), "solid 4px red");
        dsl.executarJavaScript("arguments[0].style.border = arguments[1]", DriverFactory.getDriver().findElement(By.id("elementosForm:sobrenome")), "solid 4px red");
        dsl.executarJavaScript("arguments[0].style.border = arguments[1]", DriverFactory.getDriver().findElement(By.id("elementosForm:sexo:0")), "solid 4px red");
        dsl.executarJavaScript("arguments[0].style.border = arguments[1]", DriverFactory.getDriver().findElement(By.id("elementosForm:comidaFavorita:0")), "solid 4px red");
        dsl.executarJavaScript("arguments[0].style.border = arguments[1]", DriverFactory.getDriver().findElement(By.id("elementosForm:escolaridade")), "solid 4px red");
        dsl.executarJavaScript("arguments[0].style.border = arguments[1]", DriverFactory.getDriver().findElement(By.id("elementosForm:esportes")), "solid 4px red");
        dsl.executarJavaScript("arguments[0].style.border = arguments[1]", DriverFactory.getDriver().findElement(By.id("elementosForm:sugestoes")), "solid 4px red");
        dsl.executarJavaScript("arguments[0].style.border = arguments[1]", DriverFactory.getDriver().findElement(By.id("elementosForm:cadastrar")), "solid 4px red");
    }

    @Test
    public void testPreencherFormularioComXPath() {
        DriverFactory.getDriver().findElement(By.xpath("//*[@id='elementosForm:nome']")).sendKeys("Osvaldo");
        Assert.assertEquals("Osvaldo", dsl.obterValorCampo("elementosForm:nome"));
    }
}
