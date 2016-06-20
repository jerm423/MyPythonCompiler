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
        //semanticRegister.setType();
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
        semanticRegister.setType(Integer.toHexString(pSymbol.hashCode()));
        semanticRegister.setValue(pSymbol.value.toString());
        SemanticStack stack = SemanticStack.getInstance();
//
        stack.push(semanticRegister);
        
        //System.out.println("------- estoy pasando por" + sym.terminalNames[pSymbol.sym] + "en la linea " + pSymbol.left + " bla " + pSymbol.value.toString());
        //escribir a ensamblador
        
        //stack.listAllRegisters();
        //System.out.println("-------");
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
    
}
