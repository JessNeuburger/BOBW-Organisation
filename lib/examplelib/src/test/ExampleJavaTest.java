import static org.junit.Assert.*;

import examplejava.ExampleJava;
import org.junit.Test;

public class ExampleJavaTest {
    @Test
    public void TestExampleJava(){
        assertEquals(ExampleJava.getHelloWorld(),"Hello World!");
    }
}
