package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    //Ter um atributo da classe que seja WebDriver
    private final WebDriver navegador;

    //Ter um método construdor que pegue um navegador da classe e jogue para dentro do navegador
    public LoginPage(WebDriver navegador) {
        this.navegador = navegador;
    }

    //Se for se manter na mesma página, o nome será igual mas se for mudar de página, coloca o nome da próxima página
    public LoginPage informarOUsuario(String usuario) {
        //Fazer login
        navegador.findElement(By.cssSelector("label[for='usuario']")).click();
        navegador.findElement(By.id("usuario")).sendKeys(usuario);

        return this;

    }
    public LoginPage informarASenha(String senha){
        navegador.findElement(By.cssSelector("label[for='senha']")).click();
        navegador.findElement(By.id("senha")).sendKeys(senha);

        return this;
    }

    public ListaDeProdutosPage submeterFormularioDeLogin(){
        navegador.findElement(By.cssSelector("button[type='submit']")).click();

        return new ListaDeProdutosPage(navegador);
    }
}
