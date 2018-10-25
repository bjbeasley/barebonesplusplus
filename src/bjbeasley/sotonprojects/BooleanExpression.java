package bjbeasley.sotonprojects;

import java.util.List;

public class BooleanExpression
{
    
    IntVariable lhs;
    IntVariable rhs;
    EqualityOperator equalityOperator;
    
    public BooleanExpression (IntVariable lhs, EqualityOperator equalityOperator, IntVariable rhs)
    {
        this.lhs = lhs;
        this.rhs = rhs;
        this.equalityOperator = equalityOperator;
    }
    
    public boolean evaluate ()
    {
        switch(equalityOperator)
        {
            case IS:
                return lhs.value == rhs.value;
            case NOT:
                return lhs.value != rhs.value;
            case LESSTHAN:
                return lhs.value < rhs.value;
            case GREATERTHAN:
                return lhs.value > rhs.value;
        }
        return false;
    }
    
    public static BooleanExpression argsToExpression (List<String> args, VariableMap variableMap) throws Exception
    {
        if(args.size() != 3)
        {
            throw new Exception("Unexpected number of arguments: Expected: 3 Got: " + args.size());
        }
        IntVariable lhs = variableMap.lookup(args.get(0));
    
        EqualityOperator equalityOperator = EqualityOperator.valueOf(args.get(1).toUpperCase());
    
        IntVariable rhs = variableMap.lookup(args.get(2));
        
        if(lhs == null)
        {
            lhs = new IntVariable(args.get(0));
        }
    
        if(rhs == null)
        {
            rhs = new IntVariable(args.get(2));
        }
        
        return new BooleanExpression(lhs, equalityOperator, rhs);
    }
    
}
