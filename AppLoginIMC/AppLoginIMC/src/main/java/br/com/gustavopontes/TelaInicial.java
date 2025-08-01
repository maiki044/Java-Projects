package br.com.gustavopontes;

import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Edit;
import totalcross.ui.Grid;
import totalcross.ui.Label;
import totalcross.ui.ScrollContainer;
import totalcross.ui.Window;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.event.PenEvent;
import totalcross.ui.gfx.Color;

/**
 * Extende Window porque é uma tela
 * Não extende MainWindow porque não
 * é a primeira tela que o aplicativo abre
 */
public class TelaInicial extends Window {

    /*
     * Local para declarar os componentes
     * ex: Label labelAlgumacoisa;
     * ex: Edit editAlgumacoisa;
     * ex: Button buttonAlgumacoisa;
     */

    Button buttonSair;

    Edit editFiltroNome;
    Edit editFiltroTelefone;
    Button buttonPesquisar;

    Grid gridPessoas;

    /**
     * Construtor da classe TelaInicial
     */
    public TelaInicial() {
        // Chama o construtor da classe Window
        super();
        /*
         * Toda Window que não for a MainWindow precisa dessa linha
         * Caso contrário o initUI não é chamado e os componentes não aparecem
         */
        setRect(0, 0, FILL, FILL);
    }

    /**
     * Aqui são iniciados os componentes de qualquer tela do Totalcross
     * Esse método é chamado sempre que a tela é aberta
     */
    @Override
    public void initUI() {
        final Container menuContainer = new Container();
        menuContainer.setBackColor(Color.getRGB("00ced1"));
        add(menuContainer, LEFT, TOP, FILL, SCREENSIZE + 10);

        buttonSair = new Button("Sair");
        menuContainer.add(buttonSair, LEFT, TOP, PREFERRED, FILL);

        final Label labelTitulo = new Label("Cadastro de Pessoas");
        menuContainer.add(labelTitulo, AFTER, SAME, PREFERRED, FILL);

        final ScrollContainer scrollContainer = new ScrollContainer();
        add(scrollContainer, LEFT, AFTER, FILL, FILL);

        final Container containerFiltros = new Container();

        // containerFiltros.setBackColor(Color.BLACK);

        scrollContainer.add(containerFiltros, LEFT + 20, AFTER, FILL, FILL);

        Label labelFiltros = new Label("Filtros");
        containerFiltros.add(labelFiltros, LEFT, TOP, FILL, PREFERRED);

        editFiltroNome = new Edit();
        editFiltroNome.caption = "Nome";
        containerFiltros.add(editFiltroNome, LEFT + 20, AFTER + 10, FILL - 20, PREFERRED);

        editFiltroTelefone = new Edit();
        editFiltroTelefone.caption = "Telefone";
        containerFiltros.add(editFiltroTelefone, LEFT + 20, AFTER + 10, FILL - 20, PREFERRED);

        containerFiltros.resize();

        final Container containerGrid = new Container();

        scrollContainer.add(containerGrid, SAME, AFTER + 10, FILL, FILL);

        Label labelPessoas = new Label("Pessoas");
        containerGrid.add(labelPessoas, LEFT, TOP, FILL, PREFERRED);

        String[] gridPessoasCaptions = { "Id", "Nome", "Idade", "Telefone", };
        int gridPessoasWidths[] = { -10, -40, -10, -40 };
        int gridPessoasAligns[] = { CENTER, LEFT, CENTER, LEFT };

        gridPessoas = new Grid(gridPessoasCaptions, gridPessoasWidths,
        gridPessoasAligns, false);
        gridPessoas.setVisibleLines(10);

        containerGrid.add(gridPessoas, LEFT - 20, AFTER + 10, FILL - 20, PREFERRED);

        String[][] pessoas = {
        { "1", "Gustavo Pontes", "27", "(11)-1111-1111" },
        { "2", "William Pontes", "9", "(22)-2222-2222" }
        };

        gridPessoas.add(pessoas);

    }

    private void sair() {
        // Faz a tela atual desaparecer
        unpop();
    }

    /**
     * Adiciona Eventos aos componentes
     */
    public void onEvent(Event event) {
        switch (event.type) {
            // Caso a caneta do celular toque na tela (ex. Galaxy Note)
            case PenEvent.PEN_DOWN:
                onEventPressed(event);
                break;
            // Caso o dedo toque na tela
            case ControlEvent.PRESSED:
                onEventPressed(event);
                break;
            default:
                break;
        }
    }

    /**
     * Recebe os eventos de toque de caneta e dedo
     * 
     * @param event
     */
    private void onEventPressed(Event event) {
        // Testando se o evento é proveniente do botão Sair
        if (event.target == buttonSair) {
            sair();
        }
    }
}
