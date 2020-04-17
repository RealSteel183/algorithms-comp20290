package p9;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CustomInput {
    public static void main(String[] args) throws IOException {
        File file = new File("src/p9/custom_input.txt");
        FileWriter fw = new FileWriter(file);
        for (int i = 0; i < 10; i++) {
            fw.write('\0');
        }
        fw.close();
    }

}
