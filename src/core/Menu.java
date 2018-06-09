package core;

/**
 *
 * @author yury_
 */
public class Menu {
    
    private Menu() {
    }
    
    public static Menu getInstance() {
        return MenuHolder.INSTANCE;
    }
    
    private static class MenuHolder {
        private static final Menu INSTANCE = new Menu();
    }
    
    public void showmenu(){
        System.out.println("new menu xd");
    }
}
