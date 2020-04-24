package p9;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Generates a custom input file with ASCII null characters, suitable for
 * binary run length encoding.
 *
 * @author Rajit Banerjee
 */

public class CustomInput {

    // Write 10 null character literals in custom_input.txt
    public static void main(String[] args) throws IOException {
        File file = new File("src/p9/custom_input.txt");
        FileWriter fw = new FileWriter(file);
        for (int i = 0; i < 10; i++) {
            fw.write('\0');
        }
        fw.close();
    }

}
