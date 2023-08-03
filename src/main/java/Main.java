import com.zorgapp.data.Data;
import com.zorgapp.menus.LanguageMenu;
import com.zorgapp.menus.LoginMenu;
import com.zorgapp.menus.Menu;

public class Main {
    public static void main(String[] args) {
        Data.init("/data.json");

        System.out.println("\u001B[34mZorgApp\u001B[0m");

        LanguageMenu languageMenu = new LanguageMenu();
        languageMenu.show();

        LoginMenu loginMenu = new LoginMenu(languageMenu.getLanguage());
        loginMenu.show();

        Menu menu = loginMenu.getMenu();
        menu.show();
    }
}
