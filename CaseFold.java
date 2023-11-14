import org.apache.commons.cli.*;
import java.io.*;

public class CaseFold
{
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
        System.out.format("input-type: %s%n", option);

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

        // Reading text from the input file. Did some research and still working it out.
        // TODO finish
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            StringBuilder text = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
            {
                text.append(line).append("\n");
            }
            reader.close();

            // Perform the selected case conversion
            // Upper case
            if (option.equals("u"))
            {
                System.out.println(text.toString().toUpperCase());
            }
            // Lower case
            else if (option.equals("l"))
            {
                System.out.println(text.toString().toLowerCase());
            }
            // Sentence case
            else if (option.equals("s"))
            {
                boolean isNewSentence = true;
                StringBuilder convertedString = new StringBuilder();

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

                System.out.println(convertedString);
            }
            // Title case
            else if (option.equals("t"))
            {
                boolean convertNextToUpper = true;
                StringBuilder convertedString = new StringBuilder();

                for (char ch : text.toString().toCharArray())
                {
                    if (ch == ' ')
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
                }

                System.out.println(convertedString);
            }
            // must also handle exceptions.
        }
        catch(FileNotFoundException err)
        {
            System.err.println("File not found");
            System.exit(4);
        }
        catch (IOException e) {
            // TODO IOException
            e.printStackTrace();
        }
    }
}
