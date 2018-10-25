package bjbeasley.sotonprojects.functions;

import bjbeasley.sotonprojects.IntVariable;
import bjbeasley.sotonprojects.VariableMap;

import java.util.List;

public class DecrementFunction implements Function
{
    VariableMap variableMap;
    
    public DecrementFunction (VariableMap variableMap)
    {
        this.variableMap = variableMap;
    }
    
    @Override
    public void execute(List<String> args) throws Exception
    {
        if(args.size() != 1)
        {
            throw new Exception("Unexpected number of arguments: Expected: 1 Got: " + args.size());
        }
        
        String arg = args.get(0);
        
        IntVariable var = variableMap.lookup(arg);
        var.decrement();
        
        System.out.println("Decrement function executed on " + arg);
    }
}
