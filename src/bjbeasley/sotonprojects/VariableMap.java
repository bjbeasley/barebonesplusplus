package bjbeasley.sotonprojects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VariableMap
{
    public HashMap<String, IntVariable> variables;
    
    public VariableMap ()
    {
        variables = new HashMap<>();
    }
    
    public IntVariable lookup (String identifier)
    {
        return variables.get(identifier);
    }
    
    public IntVariable addVariable (String name)
    {
        IntVariable var = new IntVariable(0);
        
        if (variables.containsKey(name))
        {
            variables.remove(name);
        }
        
        variables.put(name, var);
        
        return var;
    }
    
    public void PrintVariableMap ()
    {
        List<String> names = new ArrayList(variables.keySet());
        List<IntVariable> vars = new ArrayList(variables.values());
    
        for (int i = 0; i < names.size(); i++)
        {
            System.out.print(names.get(i) + " = " + vars.get(i).value);
        }
    }
    
}
