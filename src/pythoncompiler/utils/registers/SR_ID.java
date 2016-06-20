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
public class SR_ID extends SemanticRegister {
    
    private String _variable;
    
    public SR_ID(Symbol pSymbol){
        super(pSymbol, "SR_ID", pSymbol.value.toString());
        
        _variable = "SR_ID";
    }

    public String getType() {
        return _variable;
    }
    
    
    
}
