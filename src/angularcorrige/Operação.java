/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angularcorrige;

import angularcorrige.chain.AbstractHandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author mfernandes
 */
public class Operação {

    File file;
    ArrayList<String> linhas = new ArrayList<>();
    ArrayList<Action> actions = new ArrayList<>();

    public Operação(String arquivo) {
        file = new File(arquivo);

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                linhas.add(sc.nextLine());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Operação.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void addOperação(String op) {
        ////[111, 27]: missing whitespace
        if (op.matches("\\[\\d+, \\d+\\]: .*")) {
            actions.add(new Action(op));
        }
    }

    void concertar(AbstractHandler first, JTextArea area) {
        if (this.actions.size() > 0) {
            try {
                area.append("processando: " + file.getAbsolutePath() + "\n");
                for (Action action : actions) {
                    this.linhas = first.handler(action.acao, linhas, action.linha, action.coluna);
                }
                Files.copy(file.toPath(), new File(file.toPath() + ".old").toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
                FileWriter fw = new FileWriter(file);
                fw.write(getFile());
                fw.close();
            } catch (Exception ex) {

            }
        } else {
            System.out.println("Arquivo sem alterações a fazer: " + file.getName());
        }
    }

    String getFile() {
        StringBuilder sb = new StringBuilder();
        for (String linha : linhas) {
            sb.append(linha).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "################################################\n"
                + file.getAbsolutePath() + "\n"
                + Arrays.toString(actions.toArray(new Action[]{})).replace(",", "\n");
    }

    class Action {

        int linha, coluna;
        String acao;

        public Action(String dado) {

            String[] dt = dado.split(":");
            String[] split = dt[0].split(",");

            this.linha = Integer.parseInt(split[0].replaceAll("\\D", ""));
            this.coluna = Integer.parseInt(split[1].replaceAll("\\D", ""));
            this.acao = dt[1];
        }

        @Override
        public String toString() {
            return "Action{" + "linha=" + linha + " coluna=" + coluna + " acao=" + acao + '}';
        }
    }

}
