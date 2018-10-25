package bjbeasley.sotonprojects.functions;

import bjbeasley.sotonprojects.VariableMap;

import java.util.List;

public class ClearFunction implements Function
{
    VariableMap variableMap;
    
    public ClearFunction (VariableMap variableMap)
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
        
        variableMap.addVariable(arg);
        
        System.out.println("Clear function executed on " + arg);
    }
}
