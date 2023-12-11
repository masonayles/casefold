import org.junit.*;
import org.junit.contrib.java.lang.system.Assertion;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemOutRule;
import static org.junit.Assert.*;

public class CaseFoldTest
{
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Rule
    public final SystemOutRule stdout = new SystemOutRule().enableLog().mute();

    // EXIT STATUSES

    @Test
    public void test_h_exit0()
    {
        exit.expectSystemExitWithStatus(0);

        // run program
        String[] args = {"-h"};
        CaseFold.main(args);
    }

    @Test
    public void test_help_exit0()
    {
        exit.expectSystemExitWithStatus(0);

        // run program
        String[] args = {"--help"};
        CaseFold.main(args);
    }

    @Test
    public void test_u_exit0()
    {
        exit.expectSystemExitWithStatus(0);

        // run program
        String[] args = {"-u", "test.txt"};
        CaseFold.main(args);
    }

    @Test
    public void test_upper_exit0()
    {
        exit.expectSystemExitWithStatus(0);

        // run program
        String[] args = {"--upper", "test.txt"};
        CaseFold.main(args);
    }

    @Test
    public void test_t_exit0()
    {
        exit.expectSystemExitWithStatus(0);

        // run program
        String[] args = {"-t", "test.txt"};
        CaseFold.main(args);
    }

    @Test
    public void test_title_exit0()
    {
        exit.expectSystemExitWithStatus(0);

        // run program
        String[] args = {"--title", "test.txt"};
        CaseFold.main(args);
    }

    @Test
    public void test_s_exit0()
    {
        exit.expectSystemExitWithStatus(0);

        // run program
        String[] args = {"-s", "test.txt"};
        CaseFold.main(args);
    }

    @Test
    public void test_sentence_exit0()
    {
        exit.expectSystemExitWithStatus(0);

        // run program
        String[] args = {"--sentence", "test.txt"};
        CaseFold.main(args);
    }

    @Test
    public void test_NoOptions_exit1()
    {
        exit.expectSystemExitWithStatus(1);

        // run program
        String[] args = {"test.txt"};
        CaseFold.main(args);
    }

    @Test
    public void test_NoInput_exit1()
    {
        exit.expectSystemExitWithStatus(1);

        // run program
        String[] args = {};
        CaseFold.main(args);
    }

    @Test
    public void test_ParseError_exit2()
    {
        exit.expectSystemExitWithStatus(2);

        // run program
        String[] args = {"-sen tence", "test.txt"};
        CaseFold.main(args);
    }

    @Test
    public void test_FileNotFound_exit4()
    {
        exit.expectSystemExitWithStatus(4);

        // run program
        String[] args = {"-u", "hello.txt"};
        CaseFold.main(args);
    }

    // OUTPUT

    @Test
    public void test_lower_output()
    {
        exit.expectSystemExitWithStatus(0);
        exit.checkAssertionAfterwards(new Assertion() {
            @Override
            public void checkAssertion()
            {
                String expected = "hello world!\nwelcome to this program!";
                String actual = stdout.getLog();
                String message = String.format("expected: '%s', actual: '%s'", expected, actual);
                assertEquals(message, expected, actual);
            }
        });

        String[] args = {"-l", "test.txt"};
        CaseFold.main(args);
    }

    @Test
    public void test_upper_output()
    {
        exit.expectSystemExitWithStatus(0);
        exit.checkAssertionAfterwards(new Assertion()
        {
            @Override
            public void checkAssertion()
            {
                String expected = "HELLO WORLD!\nWELCOME TO THIS PROGRAM!";
                String actual = stdout.getLog();
                String message = String.format("expected: '%s', actual: '%s'", expected, actual);
                assertEquals(message, expected, actual);
            }
        });

        String[] args = {"-u", "test.txt"};
        CaseFold.main(args);
    }

    @Test
    public void test_sentence_output()
    {
        exit.expectSystemExitWithStatus(0);
        exit.checkAssertionAfterwards(new Assertion() {
            @Override
            public void checkAssertion()
            {
                String expected = "Hello world!\nWelcome to this program!";
                String actual = stdout.getLog();
                String message = String.format("expected: '%s', actual: '%s'", expected, actual);
                assertEquals(message, expected, actual);
            }
        });

        String[] args = {"-s", "test.txt"};
        CaseFold.main(args);
    }

    @Test
    public void test_title_output()
    {
        exit.expectSystemExitWithStatus(0);
        exit.checkAssertionAfterwards(new Assertion() {
            @Override
            public void checkAssertion()
            {
                String expected = "Hello World!\nWelcome to This Program!";
                String actual = stdout.getLog();
                String message = String.format("expected: '%s', actual: '%s'", expected, actual);
                assertEquals(message, expected, actual);
            }
        });

        String[] args = {"-t", "test.txt"};
        CaseFold.main(args);
    }

    @Test
    public void test_invalidOption_exit1() {
        exit.expectSystemExitWithStatus(1);
        exit.checkAssertionAfterwards(new Assertion() {
            @Override
            public void checkAssertion() {
                String expectedErrorMessage = "Invalid/Missing options";
                String actualErrorMessage = stdout.getLog();
                String message = String.format("expected: '%s', actual: '%s'", expectedErrorMessage, actualErrorMessage);
                assertEquals(message, expectedErrorMessage, actualErrorMessage);
            }
        });

        // Run program with an invalid option
        String[] args = {"--invalidOption", "test.txt"};
        CaseFold.main(args);
    }

    @Test
    public void test_emptyFile_output() {
        exit.expectSystemExitWithStatus(0);
        exit.checkAssertionAfterwards(new Assertion() {
            @Override
            public void checkAssertion() {
                // Expecting empty output for an empty file
                String expected = "";
                String actual = stdout.getLog();
                String message = String.format("expected: '%s', actual: '%s'", expected, actual);
                assertEquals(message, expected, actual);
            }
        });

        // Assume the content of 'emptyTest.txt' is an empty file
        String[] args = {"-l", "emptyTest.txt"};
        CaseFold.main(args);
    }

    @Test
    public void test_noFile_output()
    {
        exit.expectSystemExitWithStatus(4);
        exit.checkAssertionAfterwards(new Assertion() {
            @Override
            public void checkAssertion() {
                // Expecting empty output for an empty file
                String expected = "";
                String actual = stdout.getLog();
                String message = String.format("expected: '%s', actual: '%s'", expected, actual);
                assertEquals(message, expected, actual);
            }
        });

        // Assume the content of 'emptyTest.txt' is an empty file
        String[] args = {"-l", ""};
        CaseFold.main(args);
    }
}
