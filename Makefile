SRC = ./src/Markov/*.java
BIN = ./bin/Markov/*.class
JAR = stat.Markov-LeGoff-Rudek.jar
GP = `which gnuplot`

run : all
	@echo "Running..."
	@mkdir -p ./bin
	@mkdir -p ./Results
	@java -cp ./bin Markov.Main
	@make -s plot

all : $(BIN)
	@echo "Compiling..."
	@javac -d ./bin $(SRC)

nano : $(BIN)
	@echo "Running..."
	@java -cp ./bin Markov.NanoWeb

$(BIN) : $(SRC)
	@clear
	@echo "Recompilation required. Recompiling..."
	@javac -d ./bin $(SRC)

doc : $(SRC)
	javadoc -private -charset utf-8 -sourcepath src/ -d doc-private/ Markov

plot : ./Results/*.txt
	@echo "Plotting nanoWeb1..."
	@$(GP) ./plotNano1.txt

	@echo "Plotting nanoWeb2..."
	@$(GP) ./plotNano2.txt

	@echo "Plotting nanoWeb3..."
	@$(GP) ./plotNano3.txt

	@echo "Plotting random SimpleWeb..."
	@$(GP) ./plotRandom.txt
	@echo "Done"

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
	@echo "Removing results..."
	@rm -rf ./Results/
	@echo "Done!"

.PHONY : clean
