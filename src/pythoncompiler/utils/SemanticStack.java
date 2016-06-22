/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pythoncompiler.utils;

import java.util.ArrayList;
import java.util.Collections;
import pythoncompiler.utils.registers.SemanticRegister;

/**
 *
 * @author Joseph
 */
public class SemanticStack {
    private static SemanticStack _instance = null;
    private static ArrayList<SemanticRegister> _Stack;
    private String _lastControlStructure = "";

    private SemanticStack(){
        
    }
    
    public static SemanticStack getInstance(){
        if(_instance== null){
            _instance = new SemanticStack();
            _Stack = new ArrayList<SemanticRegister>();
        }
        return _instance;
    }
    public void push(SemanticRegister pSemanticRegister){
        //System.out.println("#######Estoy insertando el token " + pSemanticRegister.getToken());
        _Stack.add(pSemanticRegister);

        listAllRegisters();
        //System.out.println("-------");
    }
    public SemanticRegister pop(){
        
        if(isStackEmpty()){
            System.out.println("La pila esta vacia");
            return null;
        }
        else{
            SemanticRegister register = _Stack.get(0);
            //System.out.println("Estoy sacando el registro " + register.getRegisterType());
            _Stack.remove(register);
            return register;
        }
        
    }
    
    public boolean isStackEmpty(){
        int stackSize = _Stack.size();
        if(stackSize == 0){
            
            return true;
        }
        else{
            return false;
        }
    }

    public void listAllRegisters(){
        /*
        ArrayList<SemanticRegister> stackCopy = _Stack;
        Collections.reverse(stackCopy);
        System.out.println("----Stack bottom---");
        for(int i = 0; i != stackCopy.size(); i++){
            System.out.println(stackCopy.get(i).getRegisterType() + " || " + stackCopy.get(i).getToken());
        }
        */
        
        System.out.println("----Stack bottom---");
        for(int i = 0; i != _Stack.size(); i++){
            System.out.println(_Stack.get(i).getRegisterType() + " || " + _Stack.get(i).getToken());
        }
        System.out.println("----Stack top---");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        

    }
    
    public String stackTopType(){        
        if(isStackEmpty()){
            System.out.println("La pila esta vacia");
            return null;
        }
        else{
            SemanticRegister register = _Stack.get(0);
            String registerType = register.getRegisterType();
            //System.out.println("-------------------------------------------------ULTIMO REGISTRO " + registerType);
            //System.out.println("Estoy sacando el registro " + register.getRegisterType());
            return registerType;
        }
    }
    
    public String findClosestSR_IDRegister(){
        
        ArrayList<SemanticRegister> stackCopy = _Stack;
        Collections.reverse(stackCopy);
        
        String registerType = "";

        for(int i = 0; i != stackCopy.size(); i++){
            if( (stackCopy.get(i).getRegisterType().equals("SR_TYPE")) ){
                    registerType = stackCopy.get(i).getToken();
                    break;
                }
        }
        
        return registerType;
    }

    public void clearStack(){
        _Stack.clear();
    }

    public ArrayList<SemanticRegister> getStack() {
        return _Stack;
    }
    
    public String getLastControlStructure() {
        return _lastControlStructure;
    }

    public void setLastControlStructure(String _lastControlStructure) {
        this._lastControlStructure = _lastControlStructure;
    }
    
    
}
