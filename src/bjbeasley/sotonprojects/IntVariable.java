package bjbeasley.sotonprojects;

public class IntVariable
{
    public int value;
    
    public IntVariable (String valueString)
    {
        value = Integer.parseInt(valueString);
    }
    
    public IntVariable (int value)
    {
        this.value = value;
    }
    
    
    public void increment ()
    {
        value++;
    }
    
    public void decrement ()
    {
        value--;
    }
}
