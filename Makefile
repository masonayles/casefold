CaseFold.class: CaseFold.java
		javac -cp ".:lib/commons-cli-1.6.0.jar"  CaseFold.java
compile: CaseFold.class

run: CaseFold.class
		java -cp ".:commons-cli-1.6.0.jar" CaseFold
run-example: CaseFold.class
		java -cp ".lib:commons-cli-1.6.0.jar" CaseFold "-u" "test.txt"
