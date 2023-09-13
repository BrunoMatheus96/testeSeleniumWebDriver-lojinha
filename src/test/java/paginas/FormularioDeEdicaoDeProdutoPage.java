package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioDeEdicaoDeProdutoPage {
    //Ter um atributo da classe que seja WebDriver
    private final WebDriver navegador;

    //Ter um método construdor que pegue um navegador da classe e jogue para dentro do navegador
    public FormularioDeEdicaoDeProdutoPage(WebDriver navegador) {
        this.navegador = navegador;
    }
    public String capturaMensagemApresentada(){
        return navegador.findElement(By.cssSelector(".toast.rounded")).getText();
    }
}
