package angularcorrige.chain;
/////AUTO CRIADO

import java.util.ArrayList;

public class HandlerSHOULDBE extends AbstractHandler {

    @Override
    boolean accept(String acao) {
        return " \" should be '".equals(acao);
    }

    @Override
    ArrayList<String> handle(String acao, ArrayList<String> file, int linha, int coluna) {

        String line = file.get(linha - 1);
        String car = "" + line.charAt(coluna - 1);
        String pre = line.substring(0, coluna - 1);
        String pos = line.substring(coluna);

        file.remove(linha - 1);
        file.add(linha - 1, pre + "'" + pos.replace("\"", "'"));

        return file;

    }

}
