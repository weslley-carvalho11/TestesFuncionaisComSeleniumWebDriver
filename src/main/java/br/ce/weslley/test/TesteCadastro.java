package br.ce.weslley.test;

import br.ce.weslley.core.BaseTest;
import br.ce.weslley.core.DSL;
import br.ce.weslley.core.DriverFactory;
import br.ce.weslley.page.CampoTreinamentoPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TesteCadastro extends BaseTest {

    private DSL dsl;
    private CampoTreinamentoPage page;

    @Before
    public void iniciar() {
        DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL();
        page = new CampoTreinamentoPage();
    }

    @Test
    public void deveRealizarCadastroComSucesso() {
        page.setNome("Weslley");
        page.setSobrenome("Carvalho");
        page.setSexoMasculino();
        page.setCarne();
        page.setEscolaridade("Superior");
        page.setEsportes("Natacao");
        page.setSugestao("Informações de Teste");
        page.cadatrar();

        Assert.assertEquals("Cadastrado!", page.obterResultadoCadastrado());
        Assert.assertEquals("Weslley", page.obterResultadoDoNome());
        Assert.assertEquals("Carvalho", page.obterResultadoDoSobrenome());
        Assert.assertEquals("Masculino", page.obterResultadoDoSexo());
        Assert.assertEquals("Carne", page.obterResultadoDaComida());
        Assert.assertEquals("superior", page.obterResuldadoDaEscolaridade());
        Assert.assertEquals("Natacao", page.obterResultadoDoEsporte());
        Assert.assertEquals("Informações de Teste", page.obterResultadoSugestoes());
    }

    @Test
    public void deveValidarNomeObrigatorio() {
        page.cadatrar();
        Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceite());
    }

    @Test
    public void deveValidarSobrenomeObrigatorio() {
        page.setNome("Weslley");
        page.cadatrar();
        Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceite());
    }

    @Test
    public void validarSexo() {
        page.setNome("Weslley");
        page.setSobrenome("Carvalho");
        page.cadatrar();
        Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceite());
    }

    @Test
    public void validarComidaVegetariana() {
        page.setNome("Weslley");
        page.setSobrenome("Carvalho");
        page.setSexoMasculino();
        page.setCarne();
        page.setVegetariano();
        page.cadatrar();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceite());
    }

    @Test
    public void validarPraticaEsporteIndeciso() {
        page.setNome("Weslley");
        page.setSobrenome("Santos");
        page.setSexoMasculino();
        page.setCarne();

        page.setEsportes("Karate", "O que eh esporte?");
        page.cadatrar();
        Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceite());
    }

    @Test
    public void testJavascript() {
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
        //js.executeScript("alert('Testando JS via selenium')");
        js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via javascript'");
        js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");
        WebElement element = DriverFactory.getDriver().findElement(By.id("elementosForm:nome"));
        js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");

    }

    @Test
    public void deveClicarBotaoTabela() {
        dsl.clicarBotaoTabela("Nome", "Maria", "Botao", "elementosForm:tableUsuarios");
    }
}

