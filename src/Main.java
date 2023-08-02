import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;


public class Main {
    private static final String [] corr_ua = {"кот","хат","привіт","дякую","будь ласка","так","ні","добре",
            "до побачення","як","справи","вас","звати","мене","звуть","також","дуже","велике","маленьке",
            "більше","менше","день","рік","місяць","тиждень","хліб","вода","сонце","місяць","зірка","небо",
            "дерево","квітка","мова","книга","школа","університет","місто","село","дорога","парк","музей",
            "ресторан","магазин","будинок","квартира","кімната","ліжко","стіл","стілець","телефон","комп'ютер",
            "батько","бігти","білизна","брат","брюки","будинок","будь ласка","вас","ведмідь","велике","вести",
            "взуття","великий","весна","вечір","вибачатися","вишня","вівця","відпочивати","вікно","він","вірити",
            "вісім","вітер","вищий","вчитися","вчитель","гарний","годинник","гора","гроші","грудень","дах",
            "двері","дерево","день","джерело","диван","дитина","дівчина","дім","дівчина","дідусь","дівчинка",
            "добре","добрий","довгий","дощ","друг","думати","дякую","дякувати","життя","жовтий","жінка","жити",
            "забути","закрити","засмучувати","захоплюватися","звір","звучати","земля","зима","зірка","зламати",
            "знати","зображувати","золотий","зоопарк","їжак","їжа","їсти","їхати","йти","кава","картка","кімната",
            "книга","кіт","кішка","кішечка","кладовище","клас","комп'ютер","конячий","коричневий","корова","короткий",
            "космос","країна","краса","красний","крижана","крісло","кролик","кружка","купатися","курити","ламати",
            "ледачий","ліжко","лікар","липень","лист","літо","літати","ліхтар","ліхтарик","лічильник","лічить",
            "лошадь","любити","лютий","магазин","мала","маленька","мати","мед","медаль","менше","мені","міст",
            "місто","місяць","місячний","мова","мовити","море","музей","мурашка","мурашник","мурашком","мурашці",
            "навчати","надія","намалювати","насолоджуватися","настрій","настільна","наступний","небо","невеликий",
            "невідомий","неділя","недовго","недорогий","незабаром","немає","необхідний","ніч","ночі","обіцяти",
            "обіцяю","обіцяють","обрати","оголошення","одяг","озеро","око","олівець","осінь","особливо","отець",
            "очі","палець","парк","парта","пенал","пензлик","передача","переклад","перекладач","перша","перший",
            "підлога","підлоги","підніматися","після","пісня","піцца","плащ","плітка","по-справжньому","погано",
            "погода","подорож","покоївка","поле","помідор","пора","поруч","поспішати","постачати","починаючи",
            "почуття","працювати","прекрасний","приймати","примара","примарно","принести","принц","програма",
            "проїхати","прочитати","птиця","пустеля","путівник","пучок","п'ятниця","ревнивий","робити","родич",
            "рослина","рука","рух","ручка","ручник","сад","садити","сам","самка","саме","самий","самі","самовідчуття",
            "сарай","свій","світло","секрет","сестра","сім","сім'я","сім'я","сидіти","сильний","син","сир","ситуація",
            "сказати","складно","склянка","слід","слухати","слухач","слухачі","слухачів","слідкувати","слово","сніг",
            "сніданок","собака","сон","сонце","сонцезахисний окуляри","сосна","спати","спати","спина","сподіватися",
            "спорт","справжній","справжня","справжнє","справа","справи","сприяння","ссати","стадо","стакан","старий",
            "стіл","стілець","сторона","стояти","студент","сукня","сумка","сумка","сумнів","сумний","сусід","сусідка",
            "сусідній","сутінки","сухий","сходити","схожий","сцена","сьогодні","сюжет","сюрприз","так","таксі",
            "там","танок","тата","тварина","телефон","темний","термін","тесляр","тетя","тиждень","тижня","тиран",
            "тобі","товариш","той"
            };
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
        String str_temp = str.toLowerCase();
        for (int i = 0; i < corr_ua.length; i++) {
            if (str_temp.indexOf(corr_ua[i])>=0){
                res=true;
                break;
            }
        }
        return res;
    }
}
//d:\Temp\orig.txt
//d:\Temp\orig_res.txt