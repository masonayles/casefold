import org.apache.commons.cli.*;
import java.io.*;

public class CaseFold
{
    private static final String[] lowerWords = {"a", "an", "the", "as", "at", "by", "for", "in", "of", "off", "on",
            "per", "to", "up", "via", "and", "but", "if", "nor", "or", "so", "yet"};
    
    public static void main(String[] args)
    {
        // configure command-line options
        Options options = new Options();
        options.addOption("u", "upper", false,
                "Convert text from input file to UPPERCASE text");
        options.addOption("l", "lower", false,
                "Convert text from input file to lowercase text");
        options.addOption("s", "sentence", false,
                "Convert text from input file to Sentence case text");
        options.addOption("t", "title", false,
                "Convert text from input file to Title Case text");
        options.addOption("h", "help", false, "Prints usage information");

        // parse args using the above options parser
        CommandLine input = null;
        try
        {
            CommandLineParser parser = new DefaultParser();
            input = parser.parse(options, args);
        }
        catch (ParseException err)
        {
            System.err.println("Parsing error. Cause: " + err.getMessage());
            System.exit(2);
        }

        // check command option
        String option = null;
        if (input.hasOption("h"))
        {
            // automatically generate the help statement
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("CaseFold", "Converts the input text to a specified case", options,
                    "", true);
            System.exit(0);
        }
        else if (input.hasOption("u"))
        {
            // uppercase option
            option = "u";
        }
        else if (input.hasOption("l"))
        {
            // lowercase option
            option = "l";
        }
        else if (input.hasOption("s"))
        {
            // sentence case option
            option = "s";
        }
        else if (input.hasOption("t"))
        {
            // title case option
            option = "t";
        }
        else
        {
            System.err.println("Invalid/Missing options");
            System.exit(1);
        }

        // get remaining arguments
        String[] inputArray = input.getArgs();

        // get file name
        String fileName = null;
        // input #1
        if (inputArray.length == 1)
        {
            fileName = inputArray[0];
        }
        // input #2
        else
        {
            // create reader over input
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

            // get file name
            try
            {
                fileName = inputReader.readLine();
            }
            catch (IOException err)
            {
                System.err.println("Error reading file. Cause: " + err.getMessage());
                err.printStackTrace();
                System.exit(3);
            }
        }

        // Read text from the input file.
        StringBuilder text = new StringBuilder();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            text.append(line);
            while ((line = reader.readLine()) != null)
            {
                text.append("\n").append(line);
            }
            reader.close();
        }
        catch(FileNotFoundException err)
        {
            System.err.println("File not found");
            System.exit(4);
        }
        catch (IOException err) {
            System.err.println("Error reading file. Cause: " + err.getMessage());
            err.printStackTrace();
            System.exit(3);
        }

        // Perform the selected case conversion
        StringBuilder convertedString = new StringBuilder();

        switch (option)
        {
            // Upper case
            case "u" -> convertedString.append(text.toString().toUpperCase());

            // Lower case
            case "l" -> convertedString.append(text.toString().toLowerCase());

            // Title case
            case "t" ->
            {
                boolean convertNextToUpper = true;
                char[] charArray = text.toString().toCharArray();
                
                // loop across all characters
                for (int i = 0; i < charArray.length; i++)
                {
                    char ch = charArray[i];
                    if (ch == ' ')
                    {
                        boolean lowerWord = false;
                        // check if non-capitalize word
                        for (String word : lowerWords)
                        {
                            // check if lower word already found
                            if (lowerWord)
                            {
                                // end check
                                break;
                            }

                            // continue checking
                            lowerWord = true;
                            for (int x = 0; x < word.length(); x++)
                            {
                                char letter = Character.toLowerCase(charArray[(i + 1) + x]);
                                char wordLetter = word.charAt(x);
                                if (letter != wordLetter)
                                {
                                    lowerWord = false;
                                    break;
                                }
                            }
                        }

                        if (!lowerWord)
                        {
                            convertNextToUpper = true;
                        }
                    }
                    else if (ch == '\n')
                    {
                        convertNextToUpper = true;
                    }
                    else if (convertNextToUpper)
                    {
                        ch = Character.toUpperCase(ch);
                        convertNextToUpper = false;
                    }
                    else
                    {
                        ch = Character.toLowerCase(ch);
                    }
                    convertedString.append(ch);
                };
            }

            // Sentence case
            case "s" ->
            {
                boolean isNewSentence = true;
                for (char ch : text.toString().toCharArray())
                {
                    if (isNewSentence && Character.isLetter(ch))
                    {
                        ch = Character.toUpperCase(ch);
                        isNewSentence = false;
                    }
                    else
                    {
                        ch = Character.toLowerCase(ch);
                    }

                    if (ch == '.' || ch == '?' || ch == '!')
                    {
                        isNewSentence = true;
                    }

                    convertedString.append(ch);
                }
            }
        }

        // return converted text
        System.out.print(convertedString);
        System.exit(0);
    }
}
