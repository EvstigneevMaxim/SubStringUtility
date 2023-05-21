import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

//Для запуска утилиты нужно указать путь к каталогу и подстроку для поиска в аргументах командной строки.
//Например, чтобы найти подстроку "win" во всех файлах в каталоге "C:\Windows", нужно запустить команду:
//java SubstringSearchUtility C:\Windows win

public class SubstringSearchUtility {

    public static void main(String[] args) throws IOException {

        // Проверяем, указали ли путь, где искать
        if (args.length < 2) {
            System.out.println("Использование: java SubstringSearchUtility <каталог> <подстрока>");
            return;
        }

        String directory = args[0];
        String substring = args[1];

        // Создаем объект File для указанного каталога
        File dir = new File(directory);

        // Проверяем, существует ли указанный каталог и является ли он каталогом
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println(directory + " не является каталогом");
            return;
        }

        // Обходим все файлы в каталоге и его подкаталогах
        for (File file : dir.listFiles()) {
            // Если файл сам является каталогом, пропускаем его
            if (file.isDirectory()) {
                continue;
            }
            // Проходимся по файлу, читаем построчно
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                int lineNumber = 1;
                while ((line = br.readLine()) != null) {
                    // Если в строке найдена подстрока, выводим имя файла, номер строки и позицию подстроки
                    int index = line.indexOf(substring);
                    if (index >= 0) {
                        System.out.printf("%s:%d:%d%n", file.getAbsolutePath(), lineNumber, index + 1);
                    }
                    // Если длина строки превышает 4КБ, прекращаем чтение файла
                    if (line.length() > 4096) {
                        break;
                    }
                    lineNumber++;
                }
            }
        }
    }
}
