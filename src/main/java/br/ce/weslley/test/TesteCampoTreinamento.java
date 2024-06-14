package br.ce.weslley.test;

import br.ce.weslley.core.DSL;
import br.ce.weslley.core.DriverFactory;
import br.ce.weslley.page.CampoTreinamentoPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;

import java.util.Arrays;
import java.util.List;

public class TesteCampoTreinamento {

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
    public void preencherCampoNome() {
        page.setNome("Weslley");
        Assert.assertEquals("Weslley", dsl.obterValorCampo("elementosForm:nome"));
    }

    @Test
    public void testeTextField() {
        page.setSobrenome("Santos");
        Assert.assertEquals("Santos", dsl.obterValorCampo("elementosForm:sobrenome"));
    }

    @Test
    public void deveInteragirComTextArea() {
        dsl.escrever("elementosForm:sugestoes", "Texto" + "\n" + "Texto2");
        Assert.assertEquals("Texto" + "\n" + "Texto2", DriverFactory.getDriver().findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
    }

    @Test
    public void deveInteragirComRadioButton() {
        page.setSexoMasculino();
        Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
    }

    @Test
    public void deveInteragirComCheckbox() {
        page.setCarne();
        Assert.assertTrue(DriverFactory.getDriver().findElement(By.id("elementosForm:comidaFavorita:0")).isSelected());
    }

    @Test
    public void deveInteragirComCombo() {
        page.setEscolaridade("Mestrado");
        Assert.assertEquals("Mestrado", dsl.obterValorCombo("elementosForm:escolaridade"));
    }

    @Test
    public void deveVerificarValoresComboMitiplo() {
        dsl.selecionarCombo("elementosForm:esportes", "Natacao");
        dsl.selecionarCombo("elementosForm:esportes", "Corrida");
        dsl.selecionarCombo("elementosForm:esportes", "Karate");

        List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
        Assert.assertEquals(3, opcoesMarcadas.size());

        dsl.desselecionarCombo("elementosForm:esportes", "Natacao");
        opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
        Assert.assertEquals(2, opcoesMarcadas.size());
        opcoesMarcadas.containsAll(Arrays.asList("Corrida", "Karate"));
    }

    @Test
    public void deveInteragirComBotoes() {
        dsl.clicarBotao("buttonSimple");
        Assert.assertEquals("Obrigado!", dsl.obterValorCampo("buttonSimple"));
    }

    @Test
    public void deveInteragirComLinks() {
        dsl.clicarLink("Voltar");
        Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
    }

    @Test
    public void deveBuscarextoNaPagina() {
        Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
    }

    @Test
    public void testJavascript() {
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
        js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito com javascript'");
        js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");

        WebElement element = DriverFactory.getDriver().findElement(By.id("elementosForm:nome"));
        js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
    }
}
