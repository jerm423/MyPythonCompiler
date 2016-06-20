/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pythoncompiler.utils.registers;

import java_cup.runtime.Symbol;

/**
 *
 * @author Joseph
 */
public class SR_WHILE extends SemanticRegister {
    
    private String _while_label;
    private String _exit_label;
    
    public SR_WHILE(Symbol pSymbol){
        super(pSymbol, "SR_WHILE", pSymbol.value.toString());
    }
    
    public String getExit_label() {
        return _exit_label;
    }

    public void setExit_label(String _exit_label) {
        this._exit_label = _exit_label;
    }

    public String getWhile_label() {
        return _while_label;
    }

    public void setWhile_label(String _while_label) {
        this._while_label = _while_label;
    }

    
    
}
