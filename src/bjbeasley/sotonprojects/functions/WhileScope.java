package bjbeasley.sotonprojects.functions;

import bjbeasley.sotonprojects.BooleanExpression;
import bjbeasley.sotonprojects.Interpreter;

public class WhileScope implements Scope
{
    
    Interpreter interpreter;
    int returnPosition;
    BooleanExpression condition;
    
    public WhileScope (int returnPosition, Interpreter interpreter, BooleanExpression condition)
    {
        this.returnPosition = returnPosition;
        this.interpreter = interpreter;
        this.condition = condition;
    }
    
    @Override
    public void executeCompletionBehaviour()
    {
        if(condition.evaluate())
        {
            System.out.println("Condition evaluated true looping back");
            interpreter.moveScannerToPosition(returnPosition);
        }
        else
        {
            System.out.println("Condition evaluated false, continuing");
        }
    }
    
}
