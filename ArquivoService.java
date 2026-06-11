package Service;

import java.io.*;

public class ArquivoService {

    public static void salvar(String nomeArquivo, Object dados) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(dados);
        } catch (IOException e) {
            LogService.registrar("Erro ao salvar o arquivo binário: " + nomeArquivo + " -> " + e.getMessage());
        }
    }

    public static Object carregar(String nomeArquivo) {
        File arquivo = new File(nomeArquivo);
        if (!arquivo.exists()) {
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return ois.readObject();
        } catch (Exception e) {
            LogService.registrar("Erro ao carregar o arquivo binário: " + nomeArquivo + " -> " + e.getMessage());
            return null;
        }
    }
}