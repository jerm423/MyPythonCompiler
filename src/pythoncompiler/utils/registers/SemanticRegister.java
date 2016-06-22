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
public class SemanticRegister {
    private Symbol _SymbolObject;
    private String _RegisterType, _token;
    
    
    
    public SemanticRegister(Symbol pSymbol, String pRegisterType, String pToken) {
        _SymbolObject = pSymbol;
        _RegisterType = pRegisterType;
        _token = pToken;
    }

    public Symbol getSymbolObject() {
        return _SymbolObject;
    }

    public void setSymbolObject(Symbol _SymbolObject) {
        this._SymbolObject = _SymbolObject;
    }

    public String getRegisterType() {
        return _RegisterType;
    }

    public void setRegisterType(String _RegisterType) {
        this._RegisterType = _RegisterType;
    }

    public String getToken() {
        return _token;
    }

    public void setToken(String _token) {
        this._token = _token;
    }
    
    
    
}
