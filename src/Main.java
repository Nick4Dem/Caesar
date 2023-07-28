import java.util.Scanner;

public class Main {
    private static final String al_ua = "йцукенгшщзхїфівапролджєячсмитьбю,.':-!?ЙЦУКЕНГШЩЗХЇФІВАПРОЛДЖЄЯЧСМИТЬБЮ";
    private static final String al_en = "qwertyuiopasdfghjklzxcvbnm,.':-!?QWERTYUIOPASDFGHJKLZXCVBNM";
    public static void main(String[] args) {
        System.out.println("Оберіть з якою мовою бажаєте продовжувати? 1 - Українська чи 2 - Англійська");
        Scanner scanner = new Scanner(System.in);
        int var = scanner.nextInt();
        String alph;
        if (var==1) {
            alph=al_ua;
        } else {
            alph=al_en;
        }
        System.out.println("Укажіть шлях до файлу:");
        String path = scanner.next();

        System.out.println("Оберіть дію: 1 - Шифруємо файл, 2 - Дешифруємо");
        var = scanner.nextInt();
        if (var==1) {
            System.out.println("Укажіть ключ шифрування:");
            int key = scanner.nextInt();
            encrypt(alph, path, key);
        } else {
            System.out.println("Укажіть ключ дешифрування, якщо 0 то буде виконо метобом брут-форсу:");
            int key = scanner.nextInt();
            decrypt(alph, path, key);
        }
        System.out.println("Перевiряйте, зроблено!");
    }

    private static void encrypt(String alph, String path, int key){
        char[] m_al = alph.toCharArray();
        for (int i = 0; i < m_al.length; i++) {
            System.out.println(m_al[i]);
        }
    }
    private static void decrypt(String alph, String path, int key){

    }
}