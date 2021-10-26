import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary {

    public DictionaryManagement() {
        super();
    }

    /**
     * insert new words from commandline.
     *
     * @author Kyoraku
     */
    public void insertFromCommandline() {
        Scanner input = new Scanner(System.in); // input from commandline
        System.out.println("Moi nhap so luong tu can them");
        int numberOfWords = input.nextInt(); // number of words
        if (numberOfWords == 0) return;
        input.nextLine();
        for (int i = 0; i < numberOfWords; ++i) {
            System.out.print("Tu tieng anh:");
            String target = input.nextLine().toLowerCase(); // english word
            System.out.print("Tu tieng viet:");
            String definition = input.nextLine().toLowerCase(); // vietnamese word
            definition += '\n';
            Word newWord = new Word(target, definition); // this word
            if (explain.containsKey(target)) {
                System.out.println(target + " already exist");
                continue;
            }
            words.add(newWord);
            explain.put(target, definition);
            fixTrie(target, true);
        }
    }

    /**
     * insert new words from File.
     *
     * @author Taaan
     */
    public void insertFromFile() {
        File file = new File("./data/dictionaries.txt");
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String word = sc.nextLine();
                String target = "";
                String definition = "";
                boolean ok = false;
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == '\t') {
                        ok = true;
                        continue;
                    }
                    if (ok) {
                        definition += word.charAt(i);
                    } else {
                        target += word.charAt(i);
                    }
                }
                target = target.toLowerCase();
                definition = definition.toLowerCase();
                definition += '\n';
                if (explain.containsKey(target)) {
                    System.out.println(target + " already exist");
                    continue;
                }
                words.add(new Word(target, definition));
                explain.put(target, definition);
                fixTrie(target, true);
            }
        } catch (FileNotFoundException ex) {
        }

        int wordCount = 0; // number of words
        System.out.println("No\t| English\t\t| Vietnamese");
        for (Word i : words) {
            System.out.println(++wordCount + "\t| " + i.word_target + "\t\t| " + i.word_explain);
        }
    }

    /**
     * tra cuu tu.
     *
     * @author Taan
     */
    public void dictionaryLookup() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Moi nhap tu can tra cuu:");
        String target = sc.nextLine();
        target = target.toLowerCase();
        if (explain.containsKey(target)) {
            System.out.println(explain.get(target));
        } else System.out.println("Khong tim thay trong tu dien 🙁");
    }

    /**
     * export word data to file.
     *
     * @author Kyoraku
     */
    public void dictionaryExportToFile() {
        exportToFile();
    }

    /**
     * fix data.
     *
     * @author Kyoraku
     */
    public void fixData() {
        Scanner input = new Scanner(System.in); // input from commandline
        System.out.print("Fix du lieu:" + "\n" + "1. Them du lieu" + "\n");
        System.out.print("2. Xoa du lieu" + "\n" + "3. Sua du lieu" + "\n");
        System.out.print("4. Exit" + "\n");
        int type = input.nextInt(); // type of fixing data
        input.nextLine();
        switch (type) {
            case 1:
                System.out.print("Nhap tu can them:" + "\n");
                String target = input.nextLine().toLowerCase(); // english word

                System.out.print("Nhap nghia cua tu vua them: " + "\n");
                String definition = input.nextLine().toLowerCase(); // vietnamese word
                Word newWord = new Word(target, definition); // this word
                if (explain.containsKey(target)) {
                    System.out.println(target + " already exist");
                    break;
                }
                words.add(newWord);
                explain.put(target, definition);
                fixTrie(target, true);
            case 2:
                System.out.print("Nhap tu can xoa:" + "\n");
                String target1 = input.nextLine().toLowerCase(); // english word
                if (!explain.containsKey(target1)) {
                    System.out.println(target1 + " does not exist");
                    break;
                }
                words.removeIf((Word temp) -> {
                    boolean check = (temp.word_target == target1); // check word

                    return check;
                });
                explain.remove(target1);
                fixTrie(target1, false);
            case 3:
                System.out.print("Nhap tu can sua:" + "\n");
                String target2 = input.nextLine().toLowerCase(); // english word

                System.out.print("Nhap nghia cua tu vua sua: " + "\n");
                String definition1 = input.nextLine().toLowerCase(); // vietnamese word
                Word newWord1 = new Word(target2, definition1); // this word
                if (!explain.containsKey(target2)) {
                    System.out.println(target2 + " does not exist");
                    break;
                }
                words.removeIf((Word temp) -> {
                    boolean check = (temp.word_target == target2); // check word

                    return check;
                });
                explain.remove(target2);
                fixTrie(target2, false);
                words.add(newWord1);
                explain.put(target2, definition1);
                fixTrie(target2, true);
            case 4:
                return;
            default:
                System.out.println("Ban da dua ra lua chon loi!");
        }
    }
}