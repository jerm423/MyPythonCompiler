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
public class SR_FUNC extends SemanticRegister {
    

    
    public SR_FUNC(Symbol pSymbol){
        super(pSymbol, "SR_FUNC", pSymbol.value.toString());
    }

    
    
}
