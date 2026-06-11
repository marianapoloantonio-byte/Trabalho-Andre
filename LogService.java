package Service;

import java.io.FileWriter;
import java.io.IOException;

public class LogService {

    public static void registrar(String mensagem) {

        try {

            FileWriter writer = new FileWriter("log.txt", true);

            writer.write(mensagem + "\n");

            writer.close();

        } catch (IOException e) {

            System.out.println("Erro ao gravar log.");
        }
    }


}
