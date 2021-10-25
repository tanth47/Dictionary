import java.net.SocketOption;

public class DictionaryApplication {
    public static void main(String[] args) {
        runApplication();
    }
    public static void runApplication() {
        DictionaryManagement a = new DictionaryManagement();
        System.out.println("Chao mung ban den voi tu dien xin xo!");
        System.out.println("Moi ban nhap so luong tu va cac tu cung dinh nghia!");
        a.insertFromCommandline();
    }
}