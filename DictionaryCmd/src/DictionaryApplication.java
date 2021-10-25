import java.util.Scanner;

public class DictionaryApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DictionaryManagement management = new DictionaryManagement();
        DictionaryCommandline commandline = new DictionaryCommandline();
        boolean exit = false;
        while (!exit) {
            intro();
            int type = sc.nextInt();
            while (type > 10 || type < 0) {
                System.out.println("Lua chon khong hop le!!!");
                System.out.println("Moi nhap lai!");
                type = sc.nextInt();
            }
            switch (type)
            {
                case 1:
                    management.dictionaryLookup();
                    break;
                case 2:
                    commandline.dictionarySearcher();
                    break;
                case 3:
                    commandline.showAllWords();
                    break;
                case 4:
                    management.fixData();
                    break;
                case 5:
                    management.exportToFile();
                    break;
                case 6:
                    management.insertFromFile();
                    break;
                case 7:
                    management.insertFromCommandline();
                    break;
                case 8:
                    exit = true;
                    break;
            }
        }
        management.exportToFile();
    }

    public static void intro() {
        System.out.println("Chao mung ban den voi tu dien!");
        System.out.println("Moih nap lua chon!");
        System.out.println("1.Look Up");
        System.out.println("2.Search all the relevant word");
        System.out.println("3.Show all words");
        System.out.println("4.Add/Delete/Edit a word");
        System.out.println("5.Export to file");
        System.out.println("6.Import data from file dictionaries.txt");
        System.out.println("7.Add a list of words");
        System.out.println("8.Exit");
    }
}