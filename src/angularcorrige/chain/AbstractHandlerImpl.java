/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angularcorrige.chain;

import java.util.ArrayList;

public class AbstractHandlerImpl extends AbstractHandler {

    @Override
    boolean accept(String acao) {
        return "".equals(acao);
    }

    @Override
    ArrayList<String> handle(String acao, ArrayList<String> file, int linha, int coluna) {

        String line = file.get(linha);
        String car = "" + line.charAt(coluna - 1);
        String pre = line.substring(0, coluna - 1);
        String pos = line.substring(coluna);
        
        file.remove(linha - 1);
        file.add(linha - 1, pre + car +  pos);
        
        return file;

    }

}
