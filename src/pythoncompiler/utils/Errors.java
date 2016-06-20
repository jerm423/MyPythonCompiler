/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pythoncompiler.utils;

import java.util.ArrayList;

/**
 *
 * @author Joseph
 */
public class Errors {
    
    private static Errors instance = null;
    private ArrayList<String> lexicErrors = new ArrayList<String>();
    private ArrayList<String> syntacticErrors = new ArrayList<String>();
    private ArrayList<String> semanticErrors = new ArrayList<String>();
    
    private Errors(){
        
    }
    
    public static Errors getInstance(){
        if(instance == null){
            instance = new Errors();
        }
        return instance;
    }

    public ArrayList<String> getLexicErrors() {
        return lexicErrors;
    }

    public ArrayList<String> getSyntacticErrors() {
        return syntacticErrors;
    }

    public ArrayList<String> getSemanticErrors() {
        return semanticErrors;
    }
    
    
    public void addLexicError(String pError) {
        lexicErrors.add(pError);
    }
    
    public void addSyntacticError(String pError) {
        syntacticErrors.add(pError);
    }
    
    public void addSemanticError(String pError) {
        semanticErrors.add(pError);
    }
    
    public void printAllErrors(){
        
        System.out.println("-------Errores Lexicos-------");
        for(int i = 0; i != lexicErrors.size(); i++){
          System.out.println(lexicErrors.get(i));
        }
        
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("-------Errores Sintacticos-------");
        for(int i = 0; i != syntacticErrors.size(); i++){
          System.out.println(syntacticErrors.get(i));
        }
        
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("-------Errores Semanticos-------");
        for(int i = 0; i != semanticErrors.size(); i++){
          System.out.println(semanticErrors.get(i));
        }
    }
    
}
