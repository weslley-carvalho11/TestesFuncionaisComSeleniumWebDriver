package br.ce.weslley.core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class DSL {


    //TextFild e TestArea ----------------------------------------------------------------------------------------------
    public void escrever(By by, String texto) {
        DriverFactory.getDriver().findElement(by).clear();
        DriverFactory.getDriver().findElement(by).sendKeys(texto);
    }

    public void escrever(String id_campo, String texto) {
        DriverFactory.getDriver().findElement(By.id(id_campo)).clear();
        escrever(By.id(id_campo), texto);
    }

    public String obterValorCampo(String id_campo) {
        return DriverFactory.getDriver().findElement(By.id(id_campo)).getAttribute("value");
    }

    //Radio Button e Checkbox ------------------------------------------------------------------------------------------
    public void clicarRadio(By by) {
        DriverFactory.getDriver().findElement(by).click();
    }

    public void clicarRadio(String id) {
        clicarRadio(By.id(id));
    }

    public boolean isRadioMarcado(String id) {
        return DriverFactory.getDriver().findElement(By.id(id)).isSelected();
    }

    public void clicarCheck(String id) {
        DriverFactory.getDriver().findElement(By.id(id)).click();
    }

    public void isCheckMarcado(String id) {
        DriverFactory.getDriver().findElement(By.id("id")).isSelected();
    }

    //Combo-------------------------------------------------------------------------------------------------------------

    public void selecionarCombo(String id, String valor) {
        WebElement element = DriverFactory.getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        combo.selectByVisibleText(valor);
    }

    public void desselecionarCombo(String id, String valor) {
        WebElement element = DriverFactory.getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        combo.deselectByVisibleText(valor);
    }

    public String obterValorCombo(String id) {
        WebElement element = DriverFactory.getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        return combo.getFirstSelectedOption().getText();
    }

    public List<String> obterValoresCombo(String id) {
        WebElement element = DriverFactory.getDriver().findElement(By.id(id));
        Select select = new Select(element);
        List<WebElement> opcoes = select.getAllSelectedOptions();
        List<String> valores = new ArrayList<>();
        for (WebElement opcao : opcoes) {
            valores.add(opcao.getText());
        }
        return valores;
    }

    public int obterQuantidadeOpcoesCombo(String id) {
        WebElement element = DriverFactory.getDriver().findElement(By.id(id));
        Select select = new Select(element);
        List<WebElement> listaElementosCombo = select.getOptions();
        return listaElementosCombo.size();
    }

    public boolean verificarOpcaoCombo(String id, String valor) {
        WebElement element = DriverFactory.getDriver().findElement(By.id(id));
        Select select = new Select(element);
        List<WebElement> opcoes = select.getOptions();
        for (WebElement opcao : opcoes) {
            if (opcao.getText().equals(valor)) {
                return true;
            }
        }
        return false;
    }

    public void selecionarComboPrime(String radical, String valor) {
        clicarRadio(By.xpath("//*[@id=']" + radical + "_input]/../..//span"));
        //select[@id='j_idt246:option_input']/option[@value='Option1']
        clicarRadio(By.xpath("//*[@id='']" + radical + "_items]//li[.='" + valor + "']"));
    }

    //Botão-------------------------------------------------------------------------------------------------------------

    public void clicarBotao(String id) {
        DriverFactory.getDriver().findElement(By.id(id)).click();
    }

    public String obterValueElemento(String id) {
        return DriverFactory.getDriver().findElement(By.id(id)).getAttribute("value");
    }

    //Link

    public void clicarLink(String link) {
        DriverFactory.getDriver().findElement(By.linkText(link)).click();
    }

    //Texto-------------------------------------------------------------------------------------------------------------

    public String obterTexto(By by) {
        return DriverFactory.getDriver().findElement(by).getText();
    }

    public String obterTexto(String id) {
        return obterTexto(By.id(id));
    }

    //Alert
    public String alertaObterTexto() {
        Alert alert = DriverFactory.getDriver().switchTo().alert();
        return alert.getText();
    }

    public String alertaObterTextoEAceite() {
        Alert alert = DriverFactory.getDriver().switchTo().alert();
        String menssagem = alert.getText();
        alert.accept();
        return menssagem;
    }

    public String alertaObterTextoENega() {
        Alert alert = DriverFactory.getDriver().switchTo().alert();
        String menssagem = alert.getText();
        alert.dismiss();
        return menssagem;
    }

    public void alertaEscrever(String texto) {
        Alert alert = DriverFactory.getDriver().switchTo().alert();
        alert.sendKeys(texto);
        alert.accept();
    }

    //Frames-----------------------------------------------------------------------------------------------------------
    public void entrarFrame(String frame) {
        DriverFactory.getDriver().switchTo().frame(frame);
    }

    public void sairFrame() {
        DriverFactory.getDriver().switchTo().defaultContent();
    }

    public void trocarJanela(String id) {
        DriverFactory.getDriver().switchTo().window(id);
    }

    //Tabela-------------------------------------------------------------------------------------------------------------
    public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String idTabela) {
        WebElement tabela = DriverFactory.getDriver().findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']"));
        int idColuna = obterIndiceColuna(colunaBusca, tabela);
        int idLinha = obterInciceLinha(valor, tabela, idColuna);
        int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);
        WebElement celula = tabela.findElement(By.xpath(".//tr[" + idLinha + "]/td[" + idColunaBotao + "]"));
        celula.findElement(By.xpath("./input")).click();
        alertaObterTextoEAceite(); //Por algum motivo ele não tira print do alerta
    }

    protected int obterInciceLinha(String valor, WebElement tabela, int idColuna) {
        List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td[" + idColuna + "]"));
        int idLinha = -1;
        for (int i = 0; i < linhas.size(); i++) {
            if (linhas.get(i).getText().equals(valor)) {
                idLinha = i + 1;
                break;
            }
        }
        return idLinha;
    }

    protected int obterIndiceColuna(String coluna, WebElement tabela) {
        List<WebElement> colunas = DriverFactory.getDriver().findElements(By.xpath(".//th"));
        int idColuna = -1;
        for (int i = 0; i < colunas.size(); i++) {
            if (colunas.get(i).getText().equals(coluna)) {
                idColuna = i + 1;
                break;
            }
        }
        return idColuna;
    }

    public Object executarJavaScript(String comando, Object... parametro) {
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
        return js.executeScript(comando, parametro);
    }
}
