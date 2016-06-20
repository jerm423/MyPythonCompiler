/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pythoncompiler.utils;

import java.util.Hashtable;
import java.util.Map.Entry;

/**
 *
 * @author Joseph
 */
public class SymbolTable {
    
    private static SymbolTable _instance = null;
    private Hashtable<String, String> _symbolTable = new Hashtable<String, String>();
    
    
    private SymbolTable(){
        
    }
    
    public static SymbolTable getInstance(){
        if(_instance== null){
            _instance = new SymbolTable();
        }
        return _instance;
    }

    public Hashtable<String, String> getSymbolTable() {
        return _symbolTable;
    }

    public void setSymbolTable(Hashtable<String, String> _symbolTable) {
        this._symbolTable = _symbolTable;
    }
    
    public void listSymbolTable(){
        for (Entry<String, String> entry : _symbolTable.entrySet()) {
            System.out.println(entry.getKey() + " || " + entry.getValue() );
          }
    }
    
    

    
    
        
    
}
