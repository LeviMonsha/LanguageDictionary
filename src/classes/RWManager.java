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
    public void Writer() {

    }
}
