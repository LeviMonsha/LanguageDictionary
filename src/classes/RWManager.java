package classes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RWManager {
    private final String fileName;

    public RWManager(String fn) {
        fileName = fn;
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("Dictionary is not exist");
        }
    }

    public List<String> Reader() {
        BufferedReader bufferedReader = null;
        String line;
        List<String> listLines = new ArrayList<>();
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            while((line = bufferedReader.readLine()) != null) {
                listLines.add(line);
            }

        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        return listLines;
    }
    public void Writer(String line) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(line);
            writer.close();

            System.out.println("Строка успешно записана в файл.");

        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
    public void DeleteLine(String line) {
        try {
            File inputFile = new File(fileName);
            File tempFile = new File("dict.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                System.out.println(currentLine + "  " + line);
                if (currentLine.equals(line)) {
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }

            writer.close();
            reader.close();

            if (!inputFile.delete()) {
                System.out.println("Не удалось удалить исходный файл");
                return;
            }
            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Не удалось переименовать временный файл в исходный");
            }

            System.out.println("Строка успешно удалена из файла.");

        } catch (IOException e) {
            System.out.println("Ошибка при обработке файла: " + e.getMessage());
        }
    }
}
