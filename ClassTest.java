import org.junit.*;
import org.junit.contrib.java.lang.system.Assertion;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemOutRule;
import static org.junit.Assert.*;

public class ClassTest
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
        String[] args = {"-help"};
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

    //TODO Finish Exit Codes

    // OUTPUT

    @Test
    public void test_lower_output()
    {
        exit.expectSystemExitWithStatus(0);
        exit.checkAssertionAfterwards(new Assertion() {
            @Override
            public void checkAssertion()
            {
                String expected = "hello world!\nwelcome!";
                String actual = stdout.getLog();
                String message = String.format("expected: '%s', actual: '%s'", expected, actual);
                assertEquals(message, expected, actual);
            }
        });

        String[] args = {"-l", "test.txt"};
        CaseFold.main(args);
    }
}
