JUnit = junit-4.13.2.jar
HamcrestAll = hamcrest-all-1.3.jar
CommonsCLI = commons-cli-1.6.0.jar
SystemRules = system-rules-1.19.0.jar
LibraryPath = ".:lib"
ClassPath="$(LibraryPath)/$(CommonsCLI)/$(JUnit)/$(HamcrestAll)/$(SystemRules)"

JavaFlags = -Djava.security.manager=allow

CaseFold.class: CaseFold.java
		javac -cp "$(LibraryPath)/$(CommonsCLI)"  CaseFold.java

CaseFoldTest.class: CaseFold.java ClassTest.java
		javac -cp $(ClassPath) $<

compile: CaseFold.class

compile-tests: CaseFoldTest.class

run: CaseFold.class
		java -cp ".:commons-cli-1.6.0.jar" CaseFold

run-example: CaseFold.class
		java -cp ".lib:commons-cli-1.6.0.jar" CaseFold "-u" "test.txt"

run-tests: CaseFold.class CaseFoldTest.class
		java -cp $(ClassPath) $(JavaFlags) org.junit.runner.JUnitCore CaseFoldTest

clean:
		rm CaseFold.class CaseFoldTest.class