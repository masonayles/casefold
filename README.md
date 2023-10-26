casefold is a command line program written in Java to standardize data files.

# Features
Convert text to:
* Lowercase
* Uppercase
* Sentence case
  - First of each sentence.
* Title case
  - First of each word.

## Support:
Standard input & plain text files in the same location as the program.

# Meeting 2
- input formatting (pipes?)

  * casefold [option] filename.txt

  * casefold [option] (scanner for system.in till ?)
    * cat filename.txt | casefold [option]
  
  String[] args
- [option] formatting
  
  -u / --upper 

  -l / --lower

  -s / --sentence

  -t / --title

- output formatting
  * print standard output plain text
  * hyperlink?
  * space/punctuation delimited?
- error handling
  * system.exit 1/2/3
  * record exit code meanings
  * no/wrong options
  * invalid file 
    * not exist
    * no input
    * not open
    * IO error
    * invalid type