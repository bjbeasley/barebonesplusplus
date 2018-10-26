# barebonesplusplus

Language Spec:

Statement: <function name> <arg0> <arg1> ... ;
Expression: <variable/literal> <operator> <variable/literal>

Functions:
clear \<name\>;
 - Initialise a new variable with a given name (any non-whitespace character)
incr <var>;
 - Increment a given variable's value by 1
decr <var>;
 - Decrement a given variable's value by 1
while <expression> do;
 - Repeat the code between this and the corresponding "end;" until the expression evaluates to false
end;
 - Close a "scope" (i.e. if statement or while loop), calls the completion behaviour for the top scope on the stack
if <expression> then;
 - Run the code between this and the corresponding "end;" once if the expression evaluates to true
// <anything, even white space>;
 - Do nothing, yes this is an ugly implementation of commenting, but I thought it was pretty funny, and it literally took 30 seconds to make

Operators:
is
 - Returns true if the values on either side are equal, false otherwise
not
 - Returns true if the values on either side are not equal, false otherwise
greaterthan
 - Returns true if the value on lhs is greater than the value on rhs, false otherwise
lessthan
 - Returns true if the value on lhs is less than the value on rhs, false otherwise
