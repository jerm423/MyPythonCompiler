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
public class SR_DO extends SemanticRegister {
    
    private String _type, _value;
    
    public SR_DO(Symbol pSymbol){
        super(pSymbol, "SR_DO", pSymbol.value.toString());
        
        
    }

    public String getType() {
        return _type;
    }

    public void setType(String _type) {
        this._type = _type;
    }

    public String getValue() {
        return _value;
    }

    public void setValue(String _value) {
        this._value = _value;
    }

    
    
    
    
}
