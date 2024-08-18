echo "Compilacion de JFlex"
java -jar /home/jorge/Apps/jlex-1.9.1/lib/jflex-full-1.9.1.jar -d ../Graficador/src/main/java/com/jbrod/graficador/analizadores Lexer.flex

echo " "
echo " "
echo " "
echo " "
echo " "
echo " "
echo " "

echo "Compilacion de Cup"
java -jar /home/jorge/Apps/java-cup-bin-11b-20160615/java-cup-11b.jar -parser Parser Parser.cup
mv Parser.java ../Graficador/src/main/java/com/jbrod/graficador/analizadores
mv sym.java ../Graficador/src/main/java/com/jbrod/graficador/analizadores