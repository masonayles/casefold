# if OS is Windows, use a semicolon as path separator, else use colon
ifeq ($(OS), Windows_NT)
	SEP=;
else
	SEP=:
endif

JUnit = junit-4.13.2.jar
HamcrestAll = hamcrest-all-1.3.jar
CommonsCLI = commons-cli-1.6.0.jar
SystemRules = system-rules-1.19.0.jar
LibraryPath = lib
ClassPath = .$(SEP)$(LibraryPath)/$(CommonsCLI)$(SEP)$(LibraryPath)/$(JUnit)$(SEP)$(LibraryPath)/$(HamcrestAll)$(SEP)$(LibraryPath)/$(SystemRules)
JavaFlags = -Djava.security.manager=allow

CaseFold.class: CaseFold.java
		javac -cp "$(LibraryPath)/$(CommonsCLI)"  CaseFold.java

CaseFoldTest.class: CaseFold.java CaseFoldTest.java
		javac -cp "$(ClassPath)" CaseFoldTest.java

compile: CaseFold.class

compile-tests: CaseFoldTest.class

run: CaseFold.class
		java -cp ".$(SEP)commons-cli-1.6.0.jar" CaseFold

run-example: CaseFold.class
		java -cp ".$(SEP)lib:commons-cli-1.6.0.jar" CaseFold "-u" "test.txt"

run-example2: CaseFold.class
		java -cp ".$(SEP)lib:commons-cli-1.6.0.jar" CaseFold "-l" "test.txt"

run-example3: CaseFold.class
		java -cp ".$(SEP)lib:commons-cli-1.6.0.jar" CaseFold "-s" "test.txt"

run-example4: CaseFold.class
		java -cp ".$(SEP)lib:commons-cli-1.6.0.jar" CaseFold "-t" "test.txt"

run-tests: CaseFold.class CaseFoldTest.class
		java -cp $(ClassPath) $(JavaFlags) org.junit.runner.JUnitCore CaseFoldTest

# not sure if this is useful but it create a jar file and runs it through case fold. Might be useful for client but should ask and clarify.
jar: CaseFold.class
		jar cvfe CaseFold.jar CaseFold -C . CaseFold.class

clean:
		rm -f CaseFold.class CaseFoldTest.class