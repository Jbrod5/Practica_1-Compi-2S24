/* - - - - - - - - - - - - - - - - - CODIGO DE USUARIO - - - - - - - - - - - - - - - - - */

package com.jbrod.graficador.analizadores;
import java_cup.runtime.*;
import com.jbrod.graficador.reportes.ReporteErrores;

%%


/*  - - - - - - - - - - - - - - - - - DECLARACIONES  - - - - - - - - - - - - - - - - - */
%public 
%class Lexer
%cup
%line
%column


//Numeros
entero = [0-9]+
decimal = [0-9]+"."[0-9]+

//Operaciones
mult = "*"
divi = "/"
suma = "+"
rest = "-"
para = "("
parc = ")"
coma = ","


// Palabras reservadas
//Colores
azul = "azul"
rojo = "rojo"
amarillo = "amarillo"
verde = "verde"
morado = "morado"
naranja = "naranja"
turquesa = "turquesa"
negro = "negro"
cafe = "cafe"


//Animacion
linea = "linea"
curva = "curva"
animar = "animar"
objeto = "objeto"
anterior = "anterior"
curva = "curva"

//Graficos
graficar = "graficar"
circulo = "circulo"
cuadrado = "cuadrado"
rectangulo = "rectangulo"
poligono = "poligono"

// Identificadores
identificador = [a-zA-Z_][a-zA-Z0-9_]*

LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]
WhiteSpaceOp ={WhiteSpace}*

%{

    // Manejo de tokens
    private Symbol symbol(int type){
        return new Symbol(type, yyline + 1, yycolumn + 1);
    }

    private Symbol symbol(int type, Object value){
        System.out.println("Token reconocido: " + yytext());
        return new Symbol(type, yyline + 1, yycolumn + 1, value);
    }

    private void error(String message){
        System.out.println("Error en la linea: " + (yyline + 1) + " columna: " + (yycolumn + 1) + " : " + message);
    }
    
    private ReporteErrores errores = new ReporteErrores();

    public ReporteErrores obtenerReporteErrores (){
        return errores; 
    }
    
%}


%%

/*  - - - - - - - - - - - - - - - - - REGLAS LEXICAS  - - - - - - - - - - - - - - - - - */

/* numeros */
{decimal} { return symbol(sym.NUMERO, Double.parseDouble(yytext())); }
{entero}  { return symbol(sym.NUMERO, Double.parseDouble(yytext() + ".0")); }


/* operaciones / simbolos */
{mult} {return symbol(sym.MULTIPLICACION); }
{divi} {return symbol(sym.DIVISION); }
{suma} {return symbol(sym.SUMA); }
{rest} {return symbol(sym.RESTA); }
{para} {return symbol(sym.PARA); }
{parc} {return symbol(sym.PARC); }
{coma} {return symbol(sym.COMA); }

/* PALABRAS RESERVADAS */
/* colores */
{azul}      {return symbol(sym.AZUL); }
{rojo}      {return symbol(sym.ROJO); }
{amarillo}  {return symbol(sym.AMARILLO); }
{verde}     {return symbol(sym.VERDE); }
{morado}    {return symbol(sym.MORADO); }
{naranja}   {return symbol(sym.NARANJA); }
{turquesa}  {return symbol(sym.TURQUESA); }
{negro}     {return symbol(sym.NEGRO); }
{cafe}      {return symbol(sym.CAFE); }

/* animación */
{linea}     {return symbol(sym.LINEA); }
{curva}     {return symbol(sym.CURVA); }
{animar}    {return symbol(sym.ANIMAR); }
{objeto}    {return symbol(sym.OBJETO); }
{anterior}  {return symbol(sym.ANTERIOR); }

/* gráficos */
{graficar}      {return symbol(sym.GRAFICAR); }
{circulo}       {return symbol(sym.CIRCULO); }
{cuadrado}      {return symbol(sym.CUADRADO); }
{rectangulo}    {return symbol(sym.RECTANGULO); }
{poligono}      {return symbol(sym.POLIGONO); }

/* identificadores */
{identificador} {return symbol(sym.IDENTIFICADOR, yytext());}

/* error fallback */
{WhiteSpace} { /* No hacer nada */ }

[^]            { System.out.println("No se reconocio el lexema " + yytext() + " como un token valido y se ignoro.");
                 errores.agregarError(yytext(), yyline +1, yycolumn + 1, "Lexico", "El simbolo no se encuentra definido en el alfabeto.");}
<<EOF>>        { return symbol(sym.EOF); }