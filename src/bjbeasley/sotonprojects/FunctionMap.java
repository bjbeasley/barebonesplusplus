package bjbeasley.sotonprojects;

import bjbeasley.sotonprojects.functions.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class FunctionMap
{
    private HashMap<String, Function> functionMap;
    private VariableMap variableMap;
    private Interpreter interpreter;
    
    public FunctionMap (Interpreter interpreter)
    {
        this.interpreter = interpreter;
        functionMap = new HashMap<>();
        variableMap = new VariableMap();
        registerFunctions();
    }
    
    public void registerFunctions ()
    {
        functionMap.put("clear", new ClearFunction(variableMap));
        functionMap.put("incr", new IncrementFunction(variableMap));
        functionMap.put("decr", new DecrementFunction(variableMap));
        functionMap.put("while", new WhileFunction(interpreter, this, variableMap));
        functionMap.put("end", new EndFunction(interpreter));
        functionMap.put("if", new IfFunction(interpreter, variableMap));
        functionMap.put("//", new CommentFunction());
    }
    
    public void executePreorderFunction (List<String> statement) throws FunctionNotFoundException, Exception
    {
        String function = statement.get(0);
        
        if(functionMap.containsKey(function))
        {
            LinkedList<String> args = new LinkedList<>(statement);
            args.removeFirst();
            
            functionMap.get(function).execute(args);
        }
        else
        {
            throw new FunctionNotFoundException();
        }
    }
}

class FunctionNotFoundException extends Exception
{

}