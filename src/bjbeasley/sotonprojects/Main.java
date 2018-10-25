package bjbeasley.sotonprojects;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main
{
    
    public static void main(String[] args)
    {
        if(args.length != 1)
        {
            System.out.println("Expected 1 argument (file path).");
            return;
        }
    
        String filePathString = args[0];
    
        Path path = Paths.get(filePathString);
    
        System.out.println("Attempting to load: " + path.toString());
    
        Interpreter interpreter = new Interpreter(path);
        
        try
        {
            interpreter.executeAllStatements();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
