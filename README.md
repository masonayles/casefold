#---------------------- English ----------------------

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
  * a / an / the
  * as / at / by / for / in / of / off / on / per / to / up / via
  * and / but / if / nor / or / so / yet

# Output
  * print standard output plain text
  * hyperlink?
  * space/punctuation delimited?

# Exit Codes
* 0 - Success
* 1 - Option Error
* 2 - Parse Error
* 3 - IO Error
  * Error reading file
* 4 - File Not Found Error
* 404 - Unsupported Locale