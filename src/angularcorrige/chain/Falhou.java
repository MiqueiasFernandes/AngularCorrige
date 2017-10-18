/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angularcorrige.chain;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class Falhou extends AbstractHandler {

    @Override
    boolean accept(String acao) {
        return true;
    }

    @Override
    ArrayList<String> handle(String acao, ArrayList<String> file, int linha, int coluna) {
        try {
            String nam = "/home/mfernandes/NetBeansProjects/AngularCorrige/src/angularcorrige/chain/"
                    + "Handler" + acao.replaceAll("\\W", "").toUpperCase() + ".java";

            if (new File(nam).exists()) {
                return file;
            }

            FileWriter fw
                    = new FileWriter(nam);
            fw.write("package angularcorrige.chain;\n"
                    + "/////AUTO CRIADO"
                    + "\n"
                    + "import java.util.ArrayList;\n"
                    + "\n"
                    + "public class Handler" + acao.replaceAll("\\W", "").toUpperCase() + " extends AbstractHandler {\n"
                    + "\n"
                    + "    @Override\n"
                    + "    boolean accept(String acao) {\n"
                    + "        return \"" + acao + "\".equals(acao);\n"
                    + "    }\n"
                    + "\n"
                    + "    @Override\n"
                    + "    ArrayList<String> handle(String acao, ArrayList<String> file, int linha, int coluna) {\n"
                    + "\n"
                    + "        String line = file.get(linha);\n"
                    + "        String car = \"\" + line.charAt(coluna - 1);\n"
                    + "        String pre = line.substring(0, coluna - 1);\n"
                    + "        String pos = line.substring(coluna);\n"
                    + "        file.remove(linha - 1);\n"
                    + "        file.add(linha - 1, pre + car +  pos);\n"
                    + "        return file;\n"
                    + "\n"
                    + "    }\n"
                    + "\n"
                    + "}");

            fw.close();

        } catch (Exception ex) {
            System.err.println("impossivel criar arquivo para " + acao);
        }

        return file;
    }

}
