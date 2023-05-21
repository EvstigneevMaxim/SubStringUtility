import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SubstringSearchUtility {
    private static final int MAX_BUFFER_SIZE = 4096; // Максимальный размер буфера в байтах

    public static void main(String[] args) {

        // Проверяем, указали ли путь, где искать
        if (args.length < 2) {
            System.out.println("Использование: java SubstringSearchUtility <каталог> <подстрока>");
            return;
        }

        String directoryPath = args[0];
        String substring = args[1];

        // Создаем объект File для указанного каталога
        File dir = new File(directoryPath);

        // Проверяем, существует ли указанный каталог и является ли он каталогом
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println(directoryPath + " не является каталогом");
            return;
        }

        try {
            // Получаем список файлов в указанном каталоге
            File directory = new File(directoryPath);
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    searchInFile(file, substring);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void searchInFile(File file, String searchStr) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file), MAX_BUFFER_SIZE)) {
            String line;
            int lineNumber = 1;
            int position = 0;
            int bufferOffset = 0;

            while ((line = bufferedReader.readLine()) != null) {
                while (position != -1) {
                    position = line.indexOf(searchStr, bufferOffset);
                    if (position != -1) {
                        System.out.println("Файл: " + file.getName());
                        System.out.println("Номер строки: " + lineNumber);
                        System.out.println("Позиция в строке: " + (position + bufferOffset));
                        System.out.println("Содержимое строки: " + line);
                        System.out.println();
                        bufferOffset += position + 1;
                    }
                }
                lineNumber++;
                bufferOffset = 0;
                position = 0;
            }
        }
    }
}
