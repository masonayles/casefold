casefold is a command line program written in Java to standardize data files.

# Features

#### Convert text to:

* Lowercase
* Uppercase
* Sentence case
  - Capitalize first word of each sentence.
* Title case
  - Capitalize each word.

# File Support:
Standard input & plain text files in the same location as the program.

# Valid Input
casefold [option] filename.txt

casefold [option]
  * file name must be piped from other function

# Option List

### -u / --upper

* Capitalizes all characters

### -l / --lower

* Converts all characters to lowercase

### -s / --sentence

* Capitalizes the first character of each sentence
* Converts all other characters to lowercase

### -t / --title

* Capitalizes first character of all words, except:
  * a / an
  * the
  * at
  * to
  * by
  * and
  * but
  * for
  * or / nor

# Errors
//TODO

* system.exit 1/2/3
* record exit code meanings
* no/wrong options
* invalid file
  * not exist
  * no input
  * not open
  * IO error
  * invalid type

# Output
  * print standard output plain text
  * hyperlink?
  * space/punctuation delimited?

# Exit Codes
* 1 - Success
* 2 - Option error