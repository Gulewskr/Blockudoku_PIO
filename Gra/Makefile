JFLAGS = -g  
JC = javac
JVM = java  

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
  	Klocek.java \
	Movement.java \
	Plansza.java \
	Gameplay.java \
	WindowGame.java \
	Main.java 
MAIN = Main
default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

run: classes
	$(JVM) $(MAIN)
