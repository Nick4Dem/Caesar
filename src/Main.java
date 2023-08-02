import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;


public class Main {
    private static final String [] corr_ua = {"кот","хат"};
    private static final String al_ua = "йцукенгшщзхїфівапролджєячсмитьбю,.':-!?ЙЦУКЕНГШЩЗХЇФІВАПРОЛДЖЄЯЧСМИТЬБЮ";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int var;
        String alph = al_ua;
        String path;
        String pathOut;

        while (true) {
            System.out.println("Укажіть шлях до файлу:");
            path = scanner.next();

            Path pathFile = Path.of(path);
            if (!Files.isRegularFile(pathFile)) {
                System.out.println("Файл не існує, повтори введення");
            } else {

                String fileName = pathFile.getFileName().toString();
                String fileNameWithoutExtension = fileName.substring(0, (fileName.lastIndexOf('.')==-1) ? fileName.length() : fileName.lastIndexOf('.'));
                String fileNameExtension = fileName.substring((fileName.lastIndexOf('.')==-1) ? 0 :  fileName.lastIndexOf('.'));

                pathOut = pathFile.getParent().toString()+"\\"+fileNameWithoutExtension+"_res"+fileNameExtension;
                break;
            }
        }

        System.out.println("Оберіть дію: 1 - Шифруємо файл, 2 - Дешифруємо");
        var = scanner.nextInt();
        if (var==1) {
            System.out.println("Укажіть ключ шифрування:");
            int key = scanner.nextInt();
            encrypt(alph, path, pathOut, key);
        } else {
            System.out.println("Укажіть ключ дешифрування, якщо 0 то буде виконо метобом брут-форсу:");
            int key = scanner.nextInt();
            if (key==0){
                decrypt_BF(alph, path, pathOut);
            }else {
                decrypt(alph, path, pathOut, key);
            }
        }
        System.out.println("Перевiряйте, зроблено!");
    }
    private static void encrypt(String alph, String path, String pathOut, int key){
        char[] m_al = alph.toCharArray();
        int kvo = m_al.length-1;

        try (FileReader fileRead = new FileReader(path);
             FileWriter filewriter = new FileWriter(pathOut);
             BufferedReader reader = new BufferedReader(fileRead) ) {

            while (reader.ready()) {
                String  str = reader.readLine();
                String  newStr="";
                for(int i=0; i<str.length(); i++) {
                    char chr = str.charAt(i);
                    int index = searchInArray(m_al,chr);
                    if (index==-1){
                        newStr = newStr +chr;

                    }else {
                        int newIndex = index+key;
                        if (newIndex>kvo){
                            newIndex=newIndex-m_al.length;
                        }
                        newStr = newStr +m_al[newIndex];
                    }

                }
                filewriter.write(newStr);
            }
        } catch (Exception e) {
            System.out.println("(encrypt) Щось пішло не так: " + e);
        }
    }
    private static void decrypt(String alph, String path, String pathOut, int key){
        char[] m_al = alph.toCharArray();
        int kvo = m_al.length-1;

        try (FileReader fileRead = new FileReader(path);
             FileWriter filewriter = new FileWriter(pathOut);
             BufferedReader reader = new BufferedReader(fileRead) ) {

            while (reader.ready()) {
                String  str = reader.readLine();
                String  newStr="";
                for(int i=0; i<str.length(); i++) {
                    char chr = str.charAt(i);
                    int index = searchInArray(m_al,chr);
                    if (index==-1){
                        newStr = newStr +chr;

                    }else {
                        int newIndex = index-key;
                        if (newIndex<0){
                            newIndex=newIndex+m_al.length;
                        }
                        newStr = newStr +m_al[newIndex];
                    }
                }
                filewriter.write(newStr);
            }
        } catch (Exception e) {
            System.out.println("(decrypt) Щось пішло не так: " + e);
        }
    }
    private static void decrypt_BF(String alph, String path, String pathOut){
        char[] m_al = alph.toCharArray();
        int kvo = m_al.length-1;
        boolean ok = false;
        for (int key = 0; key < m_al.length; key++) {
            if (ok){
                break;
            }
            try (FileReader fileRead = new FileReader(path);
                 FileWriter filewriter = new FileWriter(pathOut);
                 BufferedReader reader = new BufferedReader(fileRead) ) {

                while (reader.ready()) {
                    String  str = reader.readLine();
                    String  newStr="";
                    for(int i=0; i<str.length(); i++) {
                        char chr = str.charAt(i);
                        int index = searchInArray(m_al,chr);
                        if (index==-1){
                            newStr = newStr +chr;

                        }else {
                            int newIndex = index+key;
                            if (newIndex>kvo){
                                newIndex=newIndex-m_al.length;
                            }
                            newStr = newStr +m_al[newIndex];
                        }

                    }
                    if (searchInArray(newStr)){
                        filewriter.write(newStr);
                        ok=true;
                    }
                }
            } catch (Exception e) {
                System.out.println("(decrypt_BF) Щось пішло не так: " + e);
            }
        }
    }
    private static int searchInArray(char[] mas, char chr ){
        int res=-1;
        for (int i = 0; i < mas.length; i++) {
            if (mas[i]==chr){
                res=i;
                break;
            }
        }
        return res;
    }
    private static boolean searchInArray(String str ){
        boolean res=false;
        for (int i = 0; i < corr_ua.length; i++) {
            if (str.indexOf(corr_ua[i])>=0){
                res=true;
                break;
            }
        }
        return res;
    }
}
//d:\Temp\orig.txt
//d:\Temp\orig_res.txt