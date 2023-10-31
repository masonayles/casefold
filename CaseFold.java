import org.apache.commons.cli.*;

public class CaseFold
{
    public static void main(String[] args)
    {
        // configure command-line options
        Options options = new Options();
        options.addOption("u", "upper", false, "convert to UPPER CASE");
        options.addOption("l", "lower", false, "convert to lower case");
        options.addOption("s", "sentence", false, "convert to Sentence case");
        options.addOption("t", "title", false, "convert to Title Case");
        options.addOption("h", "help", false, "print usage");

        // parse args using the above options parser
        CommandLine input = null;
        try
        {
        CommandLineParser parser = new DefaultParser();
        input = parser.parse(options, args);
        }
        catch (ParseException err)
        {
            System.err.println("?");
            System.exit(1);
        }

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
            // get the actual input type value
            String inputType = input.getOptionValue("u");
            System.out.println();
        }
    }
}
