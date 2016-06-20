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
public class SR_OPE extends SemanticRegister {
    
    private String _operator;
    
    public SR_OPE(Symbol pSymbol){
        super(pSymbol, "SR_OPE", pSymbol.value.toString());
        
        
    }

    public String getOperator() {
        return _operator;
    }

    public void setOperator(String _operator) {
        this._operator = _operator;
    }


    
    
    
    
}
