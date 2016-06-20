/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pythoncompiler;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import pythoncompiler.utils.Errors;
import pythoncompiler.utils.LabelGenerator;
import pythoncompiler.utils.SemanticStack;
import pythoncompiler.utils.SymbolTable;


/**
 *2
 * 
 * @author Cortes
 */
public class Main {
      public final static int GENERAR = 1;
      public final static int EJECUTAR = 2;
      public final static int IMPRIMIR_STACK = 3;
      public final static int POP = 4;
      public final static int LAST_REGISTER = 5;
      public final static int IMPRIMIR_SYMBOL_TABLE = 6;
      public final static int ERRORES = 7;
      public final static int TEST = 8;
      public final static int SALIR = 9;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        LabelGenerator l_gen = LabelGenerator.getInstance();
        
        java.util.Scanner in = new Scanner(System.in);
        int valor = 0;
        do {
            System.out.println("Elija una opcion: ");
            System.out.println("1) Generar");
            System.out.println("2) Ejecutar");
            System.out.println("3) Imprimir Pila");
            System.out.println("4) Pop");
            System.out.println("5) Ultimo registro");
            System.out.println("6) Imprimir Tabla Simbolos");
            System.out.println("7) Imprimir Errores");
            System.out.println("8) Test");
            System.out.println("9) Salir");
            System.out.print("Opcion: ");
            valor = in.nextInt();
            switch (valor) {
                /*  Generamos el analizador lexico y sintactico.
                 lcalc.flex contiene la definicion del analizador lexico
                 ycalc.cup contiene la definicion del analizador sintactico
                 */
                case GENERAR: {
                    System.out.println("\n*** Generando ***\n");
                    String lexerFile = "";
                    String parserFile = "";
                    if (args.length > 0) {
                        System.out.println("\n*** Procesando archivos custom ***\n");
                        lexerFile = args[0];
                        parserFile = args[1];
                    } else {
                        System.out.println("\n*** Procesando archivo default ***\n");
                        lexerFile = "Lexer.flex";
                        parserFile = "Parser.cup";
                    }
                    String[] Lexer = {lexerFile};
                    String[] Parser = {"-parser", "Parser", parserFile};
                    jflex.Main.main(Lexer);
                    try {
                        java_cup.Main.main(Parser);
                    } catch (Exception ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //movemos los archivos generados
                    boolean mvAL = moveFile("Lexer.java");
                    boolean mvAS = moveFile("Parser.java");
                    boolean mvSym= moveFile("sym.java");
                    if(mvAL && mvAS && mvSym){
                        System.exit(0);
                    }
                    System.out.println("Generado!");
                    break;
                }
                case IMPRIMIR_STACK: {
                    SemanticStack stack = SemanticStack.getInstance();
                    stack.listAllRegisters();
                    break;
                }
                case POP: {
                    SemanticStack stack = SemanticStack.getInstance();
                    stack.pop();
                    break;
                }
                case LAST_REGISTER: {
                    SemanticStack stack = SemanticStack.getInstance();
                    stack.stackTopType();
                    break;
                }
                case IMPRIMIR_SYMBOL_TABLE: {
                    SymbolTable symbolTable = SymbolTable.getInstance();
                    symbolTable.listSymbolTable();
                    break;
                }
                case ERRORES: {
                    Errors.getInstance().printAllErrors();
                    break;
                }
                case TEST: {
                    SemanticStack stack = SemanticStack.getInstance();
                    String test;
                    //test = stack.findClosestSR_IDRegister();
                    test = stack.stackTopType();
                    System.out.println(test);
                    break;
                }
                case EJECUTAR: {
                    /*  Ejecutamos el analizador lexico y sintactico
                     sobre un archivo de pruebas.
                     */
                    String[] archivoPrueba = {"input.py"};
                    Parser.main(archivoPrueba);
                    System.out.println("Ejecutado!");
                    break;
                }
                case SALIR: {
                    System.out.println("Adios!");
                    break;
                }
                default: {
                    System.out.println("Opcion no valida!");
                    break;
                }
            }
        } while (valor != 9);

    }

    public static boolean moveFile(String archNombre) {
        boolean efectuado = false;
        File arch = new File(archNombre);
        if (arch.exists()) {
            System.out.println("\n*** Moviendo " + arch + " \n***");
            Path currentRelativePath = Paths.get("");
            String nuevoDir = currentRelativePath.toAbsolutePath().toString()
                    + File.separator + "src" + File.separator
                    + "pythoncompiler" + File.separator + arch.getName();
            File archViejo = new File(nuevoDir);
            archViejo.delete();
            if (arch.renameTo(new File(nuevoDir))) {
                System.out.println("\n*** Generado " + archNombre + "***\n");
                efectuado = true;
            } else {
                System.out.println("\n*** No movido " + archNombre + " ***\n");
            }

        } else {
            System.out.println("\n*** Codigo no existente ***\n");
        }
        return efectuado;
    }
}
