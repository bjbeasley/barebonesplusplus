package bjbeasley.sotonprojects.functions;

import bjbeasley.sotonprojects.Interpreter;

import java.util.List;

public class EndFunction implements Function
{
    Interpreter interpreter;
    
    public EndFunction (Interpreter interpreter)
    {
        this.interpreter = interpreter;
    }
    
    @Override
    public void execute(List<String> args) throws Exception
    {
        interpreter.completeTopScope();
    }
}
