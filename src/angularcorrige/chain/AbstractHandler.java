/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angularcorrige.chain;

import java.util.ArrayList;

/**
 *
 * @author mfernandes
 */
public abstract class AbstractHandler {

    AbstractHandler next;

    public AbstractHandler setNext(AbstractHandler next) {
        if (this.next == null) {
            this.next = next;
        } else {
            this.next.setNext(next);
        }
        return this;
    }

    abstract boolean accept(String acao);

    abstract ArrayList<String> handle(String acao, ArrayList<String> file, int linha, int coluna);

    public ArrayList<String> handler(String acao, ArrayList<String> file, int linha, int coluna) {
        if (this.accept(acao)) {
            return this.handle(acao, file, linha, coluna);
        } else if (this.next != null) {
            return this.next.handler(acao, file, linha, coluna);
        }
        return file;
    }

}
