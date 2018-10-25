package bjbeasley.sotonprojects;

import bjbeasley.sotonprojects.functions.Scope;

import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Interpreter
{
    private Scanner scanner;
    private FunctionMap functionMap;
    
    private Stack<Scope> scopedFunctions;
    
    private int scannerPosition;
    private int currentStatementScannerStartPos;
    
    private Path programFilePath;
    
    public Interpreter (Path programFilePath)
    {
        functionMap = new FunctionMap(this);
        scopedFunctions = new Stack<>();
        scannerPosition = 0;
        currentStatementScannerStartPos = 0;
        this.programFilePath = programFilePath;
    
        initScanner();
    }
    
    private void initScanner ()
    {
        try
        {
            scanner = new Scanner(programFilePath);
            scanner.useDelimiter("\\s");
            System.out.println("File has been read.");
        }
        catch (IOException ioe)
        {
            System.out.println("Unable to read program file.\n" + ioe.getMessage());
        }
    }
    
    public void executeAllStatements () throws Exception
    {
        while(scanner.hasNext())
        {
            executeNextStatement();
        }
    }
    
    
    public void executeNextStatement () throws Exception
    {
        List<String> nextStatement = getNextStatement();
    
        System.out.println(String.join(", ", nextStatement));
    
        try
        {
            functionMap.executePreorderFunction(nextStatement);
        }
        catch (FunctionNotFoundException fnfe)
        {
            System.out.println("No preorder function found.");
            throw new Exception("Statement Invalid, no function found");
        }
    }
    
    public int getCurrentStatementScannerStartPos ()
    {
        return currentStatementScannerStartPos;
    }
    
    public void moveScannerToPosition (int newScannerPos)
    {
        initScanner();
        scannerPosition = 0;
        while(scannerPosition < newScannerPos)
        {
            scanner.next();
            scannerPosition ++;
        }
    }
    
    
    public List<String> getNextStatement() throws Exception
    {
        boolean semicolonFound = false;
        List<String> statement = new LinkedList<>();
    
        currentStatementScannerStartPos = scannerPosition;
        
        do
        {
            String next = scanner.next();
            scannerPosition++;
            
            if(next.contains(";"))
            {
                if((next.indexOf(";") != next.length()-1) || !next.endsWith(";"))
                {
                    throw new Exception ("Malformed expression, semicolon embedded without whitespace: \n" + next);
                }
                
                semicolonFound = true;
                next = next.replaceAll(";", "");
            }
            
            if(next.length() > 0)
            {
                statement.add(next);
            }
            
        } while (!semicolonFound);
        
        return statement;
    }
    
    public void registerScope (Scope scope)
    {
        scopedFunctions.push(scope);
    }
    
    public void completeTopScope ()
    {
        Scope scope = scopedFunctions.pop();
        
        scope.executeCompletionBehaviour();
    }
    
    public void skipToNextMatch(String match)
    {
        while(!scanner.hasNext("end"))
        {
            scanner.next();
        }
        System.out.println("Skipping to text: " + match);
    }
    
    
    
    
    
}
