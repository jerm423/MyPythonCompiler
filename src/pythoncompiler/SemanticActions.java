/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pythoncompiler;

import java_cup.runtime.Symbol;
import pythoncompiler.utils.Errors;
import pythoncompiler.utils.LabelGenerator;
import pythoncompiler.utils.registers.*;
import pythoncompiler.utils.SemanticStack;
import pythoncompiler.utils.SymbolTable;

/**
 *
 * @author Joseph
 */
public class SemanticActions {
    

    private boolean _showMessages = false;

    public void showMessages(boolean pShowMessages){
    	_showMessages = pShowMessages;
    }


    public static void testProduction(Symbol pSymbol){
    	System.out.println("------- This is a test of variable in line " + pSymbol.left + "and token " + pSymbol.value.toString()   );
    }
    
    public static void rememberType(Symbol pSymbol){
        
        SR_TYPE semanticRegister = new SR_TYPE(pSymbol);
        SemanticStack stack = SemanticStack.getInstance();

        stack.push( semanticRegister);
        
        

        //stack.listAllRegisters();
        //System.out.println("-------");
        
        

    }

    public static void rememberIdentifier(Symbol pSymbol){
        
        SR_ID semanticRegister = new SR_ID(pSymbol);
        SemanticStack stack = SemanticStack.getInstance();

        stack.push(semanticRegister);
        //stack.listAllRegisters();
        //System.out.println("-------");
    }
    
    public static void insertSymbols(){
       
        SemanticStack stack = SemanticStack.getInstance();
        SymbolTable symbolTable = SymbolTable.getInstance();
        
        SemanticRegister id, type;
        String lastSR_IDType;
        lastSR_IDType = stack.findClosestSR_IDRegister();
        
        String lastRegister = stack.stackTopType();
        
        
        
        
        while(lastRegister == "SR_ID"){
           
            id = stack.pop();
            //System.out.println("-------------------------------------------------ULTIMO REGISTRO " + lastRegister);
            //System.out.println("-------------------------------------------------INSERTANDO " + lastSR_IDType);
            if(symbolTable.getSymbolTable().containsKey(id.getToken())){
            
                Errors.getInstance().addSemanticError("Variable ya definida " + id.getToken() + " en la linea " +  (id.getSymbolObject().left+1));
            }
            
            else{
                symbolTable.getSymbolTable().put(id.getToken(), lastSR_IDType);
            }
            
            lastRegister = stack.stackTopType();
            //System.out.println("-------------------------------------------------AHORA EL ULTIMO REGISTRO ES " + lastRegister);
             
        }
         
        type = stack.pop();
        
  
    }
    
    
    public static void startFunction(Symbol pSymbol){
        
        SR_FUNC semanticRegister = new SR_FUNC(pSymbol);
        SemanticStack stack = SemanticStack.getInstance();

        stack.push(semanticRegister);
        //stack.listAllRegisters();
        //System.out.println("-------");
    }
    
    
    public static void startWhile(Symbol pSymbol){
        
        SR_WHILE semanticRegister = new SR_WHILE(pSymbol);
        semanticRegister.setWhile_label(LabelGenerator.getInstance().getWhileLabel());
        semanticRegister.setExit_label(LabelGenerator.getInstance().getExitLabel());
        SemanticStack stack = SemanticStack.getInstance();

        stack.push(semanticRegister);
        
        //escribir a ensamblador
        
        //stack.listAllRegisters();
        //System.out.println("-------");
    }
    
    public static void registerLiteral(Symbol pSymbol){
        
        SR_DO semanticRegister = new SR_DO(pSymbol);
        semanticRegister.setCategory("literal");
        semanticRegister.setType("literal");
        semanticRegister.setValue(pSymbol.value.toString());
        SemanticStack stack = SemanticStack.getInstance();

        //System.out.println("prueba literal " + pSymbol.value.toString());
        stack.push(semanticRegister);
        
        //System.out.println("------- estoy pasando por" + sym.terminalNames[pSymbol.sym] + "en la linea " + pSymbol.left + " bla " + pSymbol.value.toString());
        //escribir a ensamblador
        
        //stack.listAllRegisters();
        //System.out.println("-------");
    }
    
    
    public static void registerVariable(Symbol pSymbol){
        
        SR_DO semanticRegister = new SR_DO(pSymbol);
        //System.out.println("prueba variable " + pSymbol.value.toString());
        //System.out.println(Integer.toHexString(pSymbol.hashCode()));
        //System.out.println(pSymbol.value);
        semanticRegister.setCategory("variable");
        semanticRegister.setType(Integer.toHexString(pSymbol.hashCode()));
        semanticRegister.setValue(pSymbol.value.toString());
        SemanticStack stack = SemanticStack.getInstance();
        
        SymbolTable symbolTable = SymbolTable.getInstance();
        
        if(!symbolTable.getSymbolTable().containsKey(pSymbol.value.toString())){
            Errors.getInstance().addSemanticError("Variable no definida " + pSymbol.value.toString() + " en la linea " +  (pSymbol.left+1));
            symbolTable.getSymbolTable().put(pSymbol.value.toString(), "marca");
        }

        stack.push(semanticRegister);
        
    }
    
    public static void registerOperator(Symbol pSymbol){
        
        SR_OPE semanticRegister = new SR_OPE(pSymbol);
        semanticRegister.setOperator(pSymbol.value.toString());
        SemanticStack stack = SemanticStack.getInstance();

        stack.push(semanticRegister);
        
        //System.out.println("------- estoy pasando por" + sym.terminalNames[pSymbol.sym] + "en la linea " + pSymbol.left + " bla " + pSymbol.value.toString());
        //escribir a ensamblador
        
        //stack.listAllRegisters();
        //System.out.println("-------");
    }
    
    
     public static void evalAssign(){
        
        SemanticStack stack = SemanticStack.getInstance();
        SymbolTable symbolTable = SymbolTable.getInstance();
        
        System.out.println("PRUEBA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        stack.listAllRegisters();
        //Pop 1 DO
        SR_DO do1 = (SR_DO) stack.pop();
        //Pop 2 OPE
        SR_OPE ope_sr = (SR_OPE) stack.pop();
        //Pop 3 DO
        SR_DO do2 = (SR_DO) stack.pop();
        //Chequeo de tipos

        if( (do1 != null) && (ope_sr != null) && (do2 != null) ){
            if(ope_sr.getToken().equals("=")){
                //System.out.println("todo bien hijueputa");
                if( (do1.getCategory().equals("variable")) && (do2.getCategory().equals("variable")) ){
                    //Si las dos son variables
                    System.out.println("son dos variables!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    if( (symbolTable.getSymbolTable().containsKey(do1.getToken())) && (symbolTable.getSymbolTable().containsKey(do2.getToken())) ){
                        //si las dos estan en tabla de simbolos

                        //Crear el do resulante
                        do2.setType("direccion");

                        //Escribir el mov al ensambador


                        stack.push(do2);
                        //symbolTable.getSymbolTable().put(id.getToken(), lastSR_IDType);
                    }
                    else{
                        //System.out.println("Variable no definida en la linea " +  ( do1.getSymbol().left+1));
                        Errors.getInstance().addSemanticError("Variable no definida en la linea " +  ( do1.getSymbol().left+1));
                    }
                }
                else if( (do1.getCategory().equals("variable")) && (do2.getCategory().equals("literal")) ){

                    //System.out.println("es una variable y un literal!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    String variableDataType = "";
                    if( symbolTable.getSymbolTable().containsKey(do1.getToken())){
                        variableDataType = symbolTable.getSymbolTable().get(do1.getValue());
                    }

                    if(variableDataType == ""){
                        Errors.getInstance().addSemanticError("Hubo un error adquiriendo el tipo de dato de la variable o la variable no ha sido definida " + do1.getToken() + " en la linea "  +  ( do1.getSymbol().left+1));
                    }
                    else if(variableDataType.equals("boolean")){
                        if( (do2.getToken() == "False") || (do2.getToken() == "False") ){
                            System.out.println("SI ES BOOL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                            //do1.setToken(do2.getToken());
                            stack.push(do1);
                        }
                        else{
                             Errors.getInstance().addSemanticError("No es posible asignar un tipo de dato no booleano a la variable booleana " + do1.getToken() + " en la linea "  +  ( do1.getSymbol().left+1));
                        }
                    }
                    else if(variableDataType.equals("int")){
                        if(isInteger(do2.getToken())){
                            System.out.println("SI ES ENTERO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                            //do1.setToken(do2.getToken());
                            stack.push(do1);
                        }
                        else{
                             Errors.getInstance().addSemanticError("No es posible asignar un tipo de dato no entero a la variable booleana " + do1.getToken() + " en la linea "  +  ( do1.getSymbol().left+1));
                        }
                    }
                    else if(variableDataType.equals("float")){
                        if(isFloat(do2.getToken())){
                            System.out.println("SI ES FLOAT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                            //do1.setToken(do2.getToken());
                            stack.push(do1);
                        }
                        else{
                             Errors.getInstance().addSemanticError("No es posible asignar un tipo de dato no entero a la variable booleana " + do1.getToken() + " en la linea "  +  ( do1.getSymbol().left+1));
                        }
                    }
                    else if(variableDataType.equals("string")){
                        //do1.setToken(do2.getToken());
                        stack.push(do1);
                    }
                    /*
                    else{
                        Errors.getInstance().addSemanticError("El tipo de dato de la variable " + do1.getToken() + " en la linea "  +  ( do1.getSymbol().left+1) + " no coincide con el literal que se intenta asignar"  ) ;
                    }
                    */




                }
            }
            else{
                Errors.getInstance().addSemanticError("La expresion anterior no es una asignacion en linea " + do1.getSymbolObject().left+1);
            }
        }
        
        //System.out.println("Petardo " + do1.getSymbolObject().value.toString());
        //System.out.println("Petardo " + ope_sr.getSymbolObject().value.toString());
        //System.out.println("Petardo " + do2.getSymbolObject().value.toString());
        
     }
    
    public static void evalBinary(){
        
        SemanticStack stack = SemanticStack.getInstance();
        
        //Pop 1 DO
        SemanticRegister do1 = stack.pop();
        //Pop 2 OPE
        SemanticRegister ope_sr = stack.pop();
        //Pop 3 DO
        SemanticRegister do2 = stack.pop();
        //Chequeo de tipos
        System.out.println("Petardo " + do1.getSymbolObject().value.toString());
        System.out.println("Petardo " + ope_sr.getSymbolObject().value.toString());
        System.out.println("Petardo " + do2.getSymbolObject().value.toString());
        
        
        /*
        switch (ope_sr.getToken()){
            
            
            case "+": {
                
                break;
            }
            case "-": {
                
                break;
            }
            case "*": {
                
                break;
            }
            case "/": {
                
                break;
            }
            case "%": {
                
                break;
            }
            case "**": {
                
                break;
            }
            case "//": {
                
                break;
            }
            case "/": {
                
                break;
            }
        }
        */
    }
    
    public static boolean isInteger(String s) {
        try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException e) { 
            return false; 
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
    
    public static boolean isFloat(String s) {
        try { 
            Float.parseFloat(s);
        } catch(NumberFormatException e) { 
            return false; 
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
    
    public static boolean isString(String s) {
        if( s instanceof String ) {
            return true;
        }
        else{
            return false;
        }
    }
    
    public static void checkAssignAndExpression(){
        System.out.println("Aqui va la prueba");
        SemanticStack.getInstance().listAllRegisters();
    }
    
    
}
