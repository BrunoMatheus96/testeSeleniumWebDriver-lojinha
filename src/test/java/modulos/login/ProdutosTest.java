package modulos.login;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.LoginPage;

import java.time.Duration;

@DisplayName("Testes Web Do Modulo de Produtos")
public class ProdutosTest {

    private WebDriver navegador;

    @BeforeEach
    public void beforeEach() {
        //Abrir o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver-win64\\chromedriver.exe");
        this.navegador = new ChromeDriver();

        //Maximizar a página Web
        this.navegador.manage().window().maximize();

        //Vou definir um tempo de espera padrão de 5 segundos
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Navegar para a página da Lojinha Web
        this.navegador.get("http://165.227.93.41/lojinha-web/v2/");
    }

    @Test
    @DisplayName("Nao e permitido resgistrar um produto com um valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoComValorIgualAZero() {
        //Fazer login
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                //Vou para a tela de registro de produto
                .acessarOFormularioDeAdicaoNovoProduto()
                //Vou preencher dados do produto e o valor será igual a zero
                .informarNomeDoProduto("MacbookPro")
                .informarValorDoProduto("000")
                .informarCoresDoProduto("preto, branco")
                //Vou submeter o formulário
                .submeterFormularioDeAdicaoComErro()
                //Vou validar que a mensagem de erro foi apresentada
                .capturaMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Nao e permitido resgistrar um produto com um valor acima de sete mil")
    public void testNaoEPermitidoRegistrarProdutoComValorAcimaDeSeteMil() {
        //Fazer login
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                //Vou para a tela de registro de produto
                .acessarOFormularioDeAdicaoNovoProduto()
                //Vou preencher dados do produto e o valor será igual a zero
                .informarNomeDoProduto("MacbookPro")
                .informarValorDoProduto("700001")
                .informarCoresDoProduto("preto, branco")
                //Vou submeter o formulário
                .submeterFormularioDeAdicaoComErro()
                //Vou validar que a mensagem de erro foi apresentada
                .capturaMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Posso adicionar produtos que estejam na faixa de 0,01 a 7000,00")
    public void testPossoAdicionarProdutoComValorDeUmCentavoASeteMilReais() {
        //Fazer login
        String mensagenApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                //Vou para a tela de registro de produto
                .acessarOFormularioDeAdicaoNovoProduto()
                //Vou preencher dados do produto e o valor será igual a zero
                .informarNomeDoProduto("Maçarico")
                .informarValorDoProduto("2500")
                .informarCoresDoProduto("preto, vermelho")
                //Vou submeter o formulário
                .submeterFormularioDeAdicaoComSucesso()
                .capturaMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagenApresentada);
    }

    @AfterEach
    public void afterEach() {
        //Vou fechar o navegador
        navegador.quit();
    }
}
