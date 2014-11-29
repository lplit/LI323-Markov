SRC = ./src/Markov/*.java
BIN = ./bin/Markov/*.class
JAR = stat.Markov-LeGoff-Rudek.jar

run : all
	@echo "Running..."
	@java -cp ./bin Markov.Main

all : $(BIN)
	@echo "Compiling..."
	@javac -d ./bin $(SRC)

nano : $(BIN)
	@echo "Running..."
	@java -cp ./bin Markov.NanoWeb

$(BIN) : $(SRC)
	@echo "Recompilation required. Recompiling..."
	@javac -d ./bin $(SRC)

doc : $(SRC)
	javadoc -private -charset utf-8 -sourcepath src/ -d doc-private/ Markov

jar : $(SRC)
	jar cvf $(JAR) .

clean : 
	@echo "Cleaning..."
	@echo "Removing temporary (*~) files..."
	@rm -rf *~
	@rm -rf ./src/Markov/*~
	@echo "Removing .class files..."
	@rm -rf ./bin/Markov/*.class
	@rm -rf $(JAR)
	@echo "Removing JavaDoc..."
	@rm -rf ./doc-private
	@echo "Done!"

.PHONY : clean
