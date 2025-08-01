package br.com.gustavopontes;

import totalcross.sys.Settings;
import totalcross.sys.SpecialKeys;
import totalcross.sys.Vm;
import totalcross.ui.Button;
import totalcross.ui.Edit;
import totalcross.ui.ImageControl;
import totalcross.ui.MainWindow;
import totalcross.ui.Toast;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.event.KeyEvent;
import totalcross.ui.event.PenEvent;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.util.UnitsConverter;

/**
 * Entrypoint (ponto de entrada do aplicativo)
 * Apenas a primeira tela a ser exibida irá extender MainWindow
 */
public class AppLogin extends MainWindow {

    /*
     * Local para declarar os componentes
     * ex: Label labelAlgumacoisa;
     * ex: Edit editAlgumacoisa;
     * ex: Button buttonAlgumacoisa;
     */

    Edit editLogin;
    Edit editSenha;
    Button buttonLogin;

    /**
     * Construtor da classe AppLogin
     */
    public AppLogin() {
        // Chama o construtor da classe MainWindow
        super();
        // Define o estilo da UI para Material
        setUIStyle(Settings.MATERIAL_UI);
    }

    static {
        Settings.screenDensity = 3.0;
    }

    /**
     * Aqui são iniciados os componentes de qualquer tela do Totalcross
     * Esse método é chamado sempre que a tela é aberta
     */
    @Override
    public void initUI() {
        setBackColor(Color.getRGB("00ced1"));

        try {
            ImageControl imageControlLogin = new ImageControl(new Image("images/6073874.png"));
            imageControlLogin.centerImage = true;
            imageControlLogin.scaleToFit = true;
            add(imageControlLogin, LEFT, TOP + UnitsConverter.toPixels(70), FILL, PARENTSIZE + 30);
        } catch (Exception exception) {
            Vm.debug(exception.getMessage());
        }

        editLogin = new Edit();
        editLogin.caption = "Usuário";
        add(editLogin, CENTER, AFTER + UnitsConverter.toPixels(70), PARENTSIZE + 60, PREFERRED);

        // Label labelSenha = new Label();
        // add(labelSenha, CENTER, AFTER + UnitsConverter.toPixels(50), PARENTSIZE + 60,
        // PREFERRED);

        editSenha = new Edit();
        editSenha.caption = "Senha";
        editSenha.setMode(Edit.PASSWORD_ALL);
        add(editSenha, CENTER, AFTER + UnitsConverter.toPixels(50), PARENTSIZE + 60, PREFERRED);

        buttonLogin = new Button("Fazer Login", Button.BORDER_ROUND);
        buttonLogin.roundBorderFactor = 7;
        add(buttonLogin, CENTER, AFTER + UnitsConverter.toPixels(50), PARENTSIZE + 60, PREFERRED);
    }

    private void login() {
        /*
         * Sempre que houver comparação de String,
         * o equals deve ser usado ao invés do ==
         */
        if (editLogin.getText().equals("") && "".equals(editSenha.getText())) {
            TelaInicial telaInicial = new TelaInicial();
            telaInicial.popup();
        } else {
            // Exibe uma mensagem preta,no rodapé da tela por 2000 milissegundos
            Toast.show("Usuário ou senha incorretos", 1000);
        }
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

        // Testando se o evento é proveniente do botão Login
        if (event.target == buttonLogin) {
            login();
        }
    }

}
