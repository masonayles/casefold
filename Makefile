JUnit = junit-4.13.2.jar
HamcrestAll = hamcrest-all-1.3.jar
CommonsCLI = commons-cli-1.6.0.jar
LibraryPath = ".:lib"

CaseFold.class: CaseFold.java
		javac -cp "$(LibraryPath)/$(CommonsCLI)"  CaseFold.java

compile: CaseFold.class

run: CaseFold.class
		java -cp ".:commons-cli-1.6.0.jar" CaseFold

run-example: CaseFold.class
		java -cp ".lib:commons-cli-1.6.0.jar" CaseFold "-u" "test.txt"
