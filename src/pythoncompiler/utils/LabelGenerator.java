/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pythoncompiler.utils;

/**
 *
 * @author Joseph
 */
public class LabelGenerator {
    private int _ElseLabelCounter = 0;
    private int _WhileLabelCounter = 0;
    private int _ForLabelCounter = 0;
    private int _ExitLabelCounter = 0;
    
    private static LabelGenerator _instance = null;
    
    private LabelGenerator(){
        
    }
    
    public static LabelGenerator getInstance(){
        if(_instance == null){
            _instance = new LabelGenerator();
        }
        return _instance;
    }
    
    public String getElseLabel(){
        String label = "else_label" + Integer.toString(_ElseLabelCounter);
        _ElseLabelCounter ++;
        return(label);
    }
    
    public String getWhileLabel(){
        String label = "while_label" + Integer.toString(_WhileLabelCounter);
        _WhileLabelCounter ++;
        return(label);
    }
    
    public String getForLabel(){
        String label = "for_label" + Integer.toString(_ForLabelCounter);
        _WhileLabelCounter ++;
        return(label);
    }
    
    public String getExitLabel(){
        String label = "exit_label" + Integer.toString(_ExitLabelCounter);
        _ExitLabelCounter ++;
        return(label);
    }
    
}
