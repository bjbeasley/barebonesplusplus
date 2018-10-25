package bjbeasley.sotonprojects.functions;

import bjbeasley.sotonprojects.BooleanExpression;
import bjbeasley.sotonprojects.Interpreter;
import bjbeasley.sotonprojects.VariableMap;

import java.util.List;

public class IfFunction implements Function
{
    Interpreter interpreter;
    VariableMap variableMap;
    
    public IfFunction (Interpreter interpreter, VariableMap variableMap)
    {
        this.interpreter = interpreter;
        this.variableMap = variableMap;
    }
    
    @Override
    public void execute(List<String> args) throws Exception
    {
        if(args.size() != 4)
        {
            throw new Exception("Unexpected number of arguments: Expected: 4 Got: " + args.size());
        }
        
        if(args.get(3).equals("then"))
        {
            args.remove(3);
        }
        else
        {
            throw new Exception ("If statement \"then\" not found");
        }
        
        BooleanExpression condition = BooleanExpression.argsToExpression(args, variableMap);
        
        if(!condition.evaluate())
        {
            interpreter.skipToNextMatch("end");
        }
        
    }
}
