package br.ce.weslley.page;

import br.ce.weslley.core.BasePage;
import br.ce.weslley.core.DSL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage extends BasePage {
    public void setNome(String nome) {
        dsl.escrever("elementosForm:nome", nome);
    }

    public void setSobrenome(String sobrenome) {
        dsl.escrever("elementosForm:sobrenome", sobrenome);
    }

    public void setSexoMasculino() {
        dsl.clicarRadio("elementosForm:sexo:0");
    }

    public void setSexoFeminino() {
        dsl.clicarRadio("elementosForm:sexo:1");
    }

    public void setCarne() {
        dsl.clicarCheck("elementosForm:comidaFavorita:0");
    }

    public void setFrango() {
        dsl.clicarCheck("elementosForm:comidaFavorita:1");
    }

    public void setPizza() {
        dsl.clicarCheck("elementosForm:comidaFavorita:2");
    }

    public void setVegetariano() {
        dsl.clicarCheck("elementosForm:comidaFavorita:3");
    }

    public void setEscolaridade(String escolaridade) {
        dsl.selecionarCombo("elementosForm:escolaridade", escolaridade);
    }

    public void setSugestao(String texto_sugestao) {
        dsl.escrever("elementosForm:sugestoes", texto_sugestao);
    }

    public void setEsporte(String esporte) {
        dsl.selecionarCombo("elementosForm:esportes", esporte);
    }

    public void setEsportes(String... esportes) {
        for (String esporte : esportes) {
            dsl.selecionarCombo("elementosForm:esportes", esporte);
        }
    }

    public void cadatrar() {
        dsl.clicarBotao("elementosForm:cadastrar");
    }

    public String obterResultadoDoNome() {
        return dsl.obterTexto(By.xpath("//*[@id='descNome']/span"));
    }

    public String obterResultadoDoSobrenome() {
        return dsl.obterTexto(By.xpath("//*[@id='descSobrenome']/span"));
        //*[@id='']/span
    }

    public String obterResultadoDoSexo() {
        return dsl.obterTexto(By.xpath("//*[@id='descSexo']/span"));
    }

    public String obterResultadoDaComida() {
        return dsl.obterTexto(By.xpath("//*[@id='descComida']/span"));
    }

    public String obterResuldadoDaEscolaridade() {
        return dsl.obterTexto(By.xpath("//*[@id='descEscolaridade']/span"));
    }

    public String obterResultadoDoEsporte() {
        return dsl.obterTexto(By.xpath("//*[@id='descEsportes']/span"));
    }

    public String obterResultadoSugestoes() {
        return dsl.obterTexto(By.xpath("//*[@id='descSugestoes']/span"));
    }

    public String obterResultadoCadastrado() {
        return dsl.obterTexto(By.xpath("//*[@id='resultado']/span"));
    }
}
