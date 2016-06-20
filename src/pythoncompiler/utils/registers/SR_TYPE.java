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
public class SR_TYPE extends SemanticRegister {
    
    private String _type;
    
    public SR_TYPE(Symbol pSymbol){
        super(pSymbol, "SR_TYPE", pSymbol.value.toString());
        
        _type = "SR_TYPE";
    }

    public String getType() {
        return _type;
    }
    
    
    
}
