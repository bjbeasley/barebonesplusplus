package bjbeasley.sotonprojects.functions;

import bjbeasley.sotonprojects.*;

import java.util.List;

public class WhileFunction implements Function
{
    FunctionMap functionMap;
    VariableMap variableMap;
    Interpreter interpreter;
    
    public WhileFunction (Interpreter interpreter, FunctionMap functionMap, VariableMap variableMap)
    {
        this.interpreter = interpreter;
        this.functionMap = functionMap;
        this.variableMap = variableMap;
        
    }
    
    @Override
    public void execute(List<String> args) throws Exception
    {
        if(args.size() != 4)
        {
            throw new Exception("Unexpected number of arguments: Expected: 4 Got: " + args.size());
        }
        
        if(args.get(3).equals("do"))
        {
            args.remove(3);
        }
        else
        {
            throw new Exception ("While loop \"do\" not found");
        }
        
        BooleanExpression condition = BooleanExpression.argsToExpression(args, variableMap);
        
        if(!condition.evaluate())
        {
            interpreter.skipToNextMatch("end");
        }
        else
        {
            WhileScope scope = new WhileScope(interpreter.getCurrentStatementScannerStartPos(), interpreter, condition);
    
            interpreter.registerScope(scope);
        }

    }
}
