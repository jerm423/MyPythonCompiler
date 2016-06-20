/* --------------------------Codigo de Usuario----------------------- */
package pythoncompiler;

import java_cup.runtime.*;
import java.io.Reader;


%% //inicio de opciones

/* ------ Seccion de opciones y declaraciones de JFlex -------------- */


%class Lexer

/*
    Activar el contador de lineas, variable yyline
    Activar el contador de columna, variable yycolumn
*/
%line
%column
/*
   Activamos la compatibilidad con Java CUP para analizadores
   sintacticos(parser)
*/
%cup
//Agregamos soporte a unicode
%unicode
%eofval{
    return new Symbol(sym.EOF, new String("Fin de Archivo"));
%eofval}

/*
    Declaraciones

    El codigo entre %{  y %} sera copiado integramente en el
    analizador generado.
*/
%{
    /*  Generamos un java_cup.Symbol para guardar el tipo de token
        encontrado */
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }

    /* Generamos un Symbol para el tipo de token encontrado
       junto con su valor */
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}


/*----------------------------------------------INVALID TOKENS-------------------------------------*/
INVALID_CHARACTER       = "á"|"é"|"í"|"ó"|"ú"|"Á"|"É"|"Í"|"Ó"|"Ú"|"ñ"|"Ñ"|"¿"|"ä"|"ë"|"ï"|"ö"|"ü"|"à"|"è"|"ì"|"ò"|"ù"|\\

/*----------------------------------------------TYPES-------------------------------------*/
TYPE_INT                = "int"
TYPE_FLOAT              = "float"
TYPE_CHAR               = "char"
TYPE_STRING             = "string"
TYPE_BOOLEAN            = "boolean"
TYPE_LIST               = "list"
/*----------------------------------------------COMMENTS-------------------------------------*/
COMMENT                 =  {LINE_COMMENT}|{BLOCK_COMMENT}
LINE_COMMENT            = \#[^\n\r]*
BLOCK_COMMENT           = \"\"\"([^\"])* ~ \"\"\"

/*----------------------------------------------MICELLANEOUS-------------------------------------*/
SPACE                   = " "
LINE_FEED               = \n|\r|\r\n
WHITESPACE              = {SPACE}|{LINE_FEED}| [ \t\n]

/*----------------------------------------------IDENTIFIERS-------------------------------------*/
EXP_ALPHA               = [a-zA-Z]
IDENTIFIER              = ({EXP_ALPHA}|"_")({EXP_ALPHA}|{INT}|"_")*
INVALID_IDENTIFIER      = {IDENTIFIER}*{INVALID_CHARACTER}+({IDENTIFIER}|{INVALID_CHARACTER})*



/*----------------------------------------------OPERATORS-------------------------------------*/
ARITH_OP                = "+"|"-"|"*"|"/"|"%"|"**"|"//"
COMP_OP                 = "=="|"!="|"<>"|">"|"<"|">="|"<="
ASSIG_OP                = "="|"+="|"-="|"*="|"/="|"//="|"%="|"**="
BIT_OP                  = "&"|"|"|"^"|"~"|"<<"|">>"
LOGICAL_OP              = "AND"|"OR"|"NOT"|"and"|"or"|"not"
MEMBERSHIP_OP           = "in"|"not in"
IDENTITY_OP             = "is"|"is not"|"isn't"
DELIMITER_OP            = "@"|">>="|"<<="|"&="|"|="

L_PARENTHESIS_OP        = "("
R_PARENTHESIS_OP        = ")"
L_BRACKET_OP            = "["
R_BRACKET_OP            = "]"
L_CURLY_BRACKET_OP      = "{"
R_CURLY_BRACKET_OP      = "}"
COLON_OP                = ":"
COMMA                   = ","
PROPERTY_OP             = "."
SEMICOLON_OP            = ";"
TAB_OP                  = [\t]

OPERATOR               = {ARITH_OP}|{COMP_OP}|{ASSIG_OP}|{BIT_OP}|{LOGICAL_OP}|{MEMBERSHIP_OP}|
                        {IDENTITY_OP}|{DELIMITER_OP}|{L_PARENTHESIS_OP}|{R_PARENTHESIS_OP}|{L_BRACKET_OP}|{R_BRACKET_OP}
                        |{L_CURLY_BRACKET_OP}|{R_CURLY_BRACKET_OP}|{COLON_OP}|{COMMA}|{PROPERTY_OP}|{TAB_OP}|{SEMICOLON_OP}

/*----------------------------------------------KEYWORDS-------------------------------------*/
KEYWORD                 = "and"|"del"|"from"|"not"|"while"|"as"|"elif"|"global"|"or"|"with"|"assert"|"else"|"if"|"pass"|"yield"|"break"|"except"|"import"|"print"|"class"|"exec"|"in"|"raise"|"continue"|"finally"|"is"|"return"|"def"|"for"|"lambda"|"try"|"end"|"input"

/*----------------------------------------------LITERALS-------------------------------------*/
LONG_INT                = {INT}("l"|"L")

INT                     = {DECIMAL_INT}|{OCT_INT}|{HEX_INT}|{BIN_INT}
DECIMAL_INT             = {NON_CERO_DIGIT}{DIGIT}*|"0"
OCT_INT                 = "0"("o" | "O"){OCT_DIGIT}+ |  "0"{OCT_DIGIT}+
HEX_INT                 = "0"("x" | "X"){HEX_DIGIT}+
BIN_INT                 = "0"("b" | "B") {BIN_DIGIT}+
DIGIT                   = [0-9]
NON_CERO_DIGIT          = [1-9]
BIN_DIGIT               = "0"|"1"
OCT_DIGIT               = [0-7]
HEX_DIGIT               = {DIGIT}|[a-f]|[A-F]

FLOAT_NUM               = {POINT_FLOAT}|{EXP_FLOAT}
POINT_FLOAT             = {INT_PART}?{FRACTION_PART}|{INT_PART}"."
EXP_FLOAT               = ({INT_PART}|{POINT_FLOAT}){EXP}
INT_PART                = {DIGIT}+
FRACTION_PART           = "."{DIGIT}+
EXP                     = ("e" | "E")("+" | "-") {DIGIT}+
IMAG_NUM                = ({FLOAT_NUM}|{INT_PART})("j" | "J")

NUM_LIT                 = {LONG_INT}|{INT}|{FLOAT_NUM}|{IMAG_NUM}

STRING_PREFIX           = "r" | "u" | "ur" | "R" | "U" | "UR" | "Ur" | "uR"| "b" | "B" | "br" | "Br" | "bR" | "BR"
STRING_LIT              = \"[^\n\r\"]*\"
INVALID_STR_LIT         = \"+{STRING_LIT} | {STRING_LIT}\"+ | \"[^\n\r\"]*\
CHAR_LIT                = \'[^\']\'
INVALID_CHAR_LIT        = \'[^\']+\' | \'+\'[^\']+\' | \'[^\']+\'\'+
BOOLEAN_LIT             = "True"|"False"


LITERAL                 = {NUM_LIT}|{STRING_PREFIX}?({STRING_LIT}|{CHAR_LIT})|{BOOLEAN_LIT}
INVALID_LITERAL         = {INVALID_STR_LIT} | {INVALID_CHAR_LIT} |{NUM_LIT}({EXP_ALPHA} | {INVALID_CHARACTER} | "_"| (({EXP_ALPHA}|{INVALID_CHARACTER}|"_"){NUM_LIT}))+

%% //fin de opciones

<YYINITIAL> {
    {COMMENT} {/*IGNORE*/}
    {INVALID_CHARACTER}    { return symbol(sym.ERROR_CHA); }

    {WHITESPACE} {/*IGNORE*/}

    {TAB_OP}                { return symbol(sym.TAB_OP, yytext()); }
    {SEMICOLON_OP}          { return symbol(sym.SEMICOLON_OP, yytext()); }
    {ARITH_OP}              { return symbol(sym.ARITH_OP, yytext()); }
    {L_PARENTHESIS_OP}      { return symbol(sym.L_PARENTHESIS_OP, yytext()); }
    {R_PARENTHESIS_OP}      { return symbol(sym.R_PARENTHESIS_OP, yytext()); }
    {L_BRACKET_OP}          { return symbol(sym.L_BRACKET_OP, yytext()); }
    {R_BRACKET_OP}          { return symbol(sym.R_BRACKET_OP, yytext()); }

    {CHAR_LIT}              { return symbol(sym.CHAR_LIT, new String(yytext())); }
    {NUM_LIT}               { return symbol(sym.NUM_LIT, new Integer(yytext())); }
    {STRING_LIT}            { return symbol(sym.STRING_LIT, new String(yytext())); }
    {BOOLEAN_LIT}           { return symbol(sym.BOOLEAN_LIT, new Boolean(yytext())); }
    "and"                   { return symbol(sym.AND, yytext()); }
    "break"                 { return symbol(sym.BREAK, yytext()); }
    "class"                 { return symbol(sym.CLASS, yytext()); }
    "continue"              { return symbol(sym.CONTINUE, yytext()); }
    "def"                   { return symbol(sym.DEF, yytext()); }
    "elif"                  { return symbol(sym.ELIF, yytext()); }
    "else"                  { return symbol(sym.ELSE, yytext()); }
    "except"                { return symbol(sym.EXCEPT, yytext()); }
    "finally"               { return symbol(sym.FINALLY, yytext()); }
    "for"                   { return symbol(sym.FOR, yytext()); }
    "if"                    { return symbol(sym.IF, yytext()); }
    "in"                    { return symbol(sym.IN, yytext()); }
    "input"                 { return symbol(sym.INPUT, yytext()); }
    "is not"                { return symbol(sym.IS_NOT, yytext()); }
    "print"                 { return symbol(sym.PRINT, yytext()); }
    "try"                   { return symbol(sym.TRY, yytext()); }
    "while"                 { return symbol(sym.WHILE, yytext()); }
    "range"                 { return symbol(sym.RANGE, yytext()); }
    "return"                { return symbol(sym.RETURN, yytext()); }
    {TYPE_LIST}             { return symbol(sym.TYPE_LIST, yytext()); }
    {TYPE_INT}              { return symbol(sym.TYPE_INT, yytext()); }
    {TYPE_FLOAT}            { return symbol(sym.TYPE_FLOAT, yytext()); }
    {TYPE_CHAR}             { return symbol(sym.TYPE_CHAR, yytext()); }
    {TYPE_STRING}           { return symbol(sym.TYPE_STRING, yytext()); }
    {TYPE_BOOLEAN}          { return symbol(sym.TYPE_BOOLEAN, yytext()); }
    {COMP_OP}               { return symbol(sym.COMP_OP, yytext()); }
    {ASSIG_OP}              { return symbol(sym.ASSIG_OP, yytext()); }
    {LOGICAL_OP}            { return symbol(sym.LOGICAL_OP, yytext()); }
    {PROPERTY_OP}           { return symbol(sym.PROPERTY_OP, yytext()); }
    {COLON_OP}              { return symbol(sym.COLON_OP, yytext()); }
    {IDENTIFIER}            { return symbol(sym.IDENTIFIER, yytext()); }
    {COMMA}                 { return symbol(sym.COMMA, yytext()); }
    {BIT_OP}                { return symbol(sym.BIT_OP, yytext()); }

    {INVALID_LITERAL}       { return symbol(sym.ERROR_LIT, yytext()); }
    {INVALID_IDENTIFIER}    { return symbol(sym.ERROR_ID, yytext()); }

}
[^]                         { return symbol(sym.ERROR_INV, yytext()); }


