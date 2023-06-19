import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SubstringSearchUtility {
    private static final int MAX_BUFFER_SIZE = 4096; // Максимальный размер буфера в байтах

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Использование: java SubstringSearchUtility <каталог> <подстрока>");
            return;
        }

        String directoryPath = args[0];
        String substring = args[1];

        File dir = new File(directoryPath);

        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println(directoryPath + " не является каталогом");
            return;
        }

        try {
            File[] files = dir.listFiles();
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
            int position;

            while ((line = bufferedReader.readLine()) != null) {
                position = line.indexOf(searchStr);
                if (position != -1) {
                    System.out.println("Файл: " + file.getName());
                    System.out.println("Номер строки: " + lineNumber);
                    System.out.println("Позиция в строке: " + position);
                    System.out.println("Содержимое строки: " + line);
                    System.out.println();
                }
                lineNumber++;
            }
        }
    }
}