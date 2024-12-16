public class InfixPostfixEvaluator 
{
    
    // Splits the input infix expression into tokens based on whitespace
    public static String[] getTokens(String expression) 
    {
        return expression.split(" ");
    }

    // Converts an infix expression to postfix notation using stacks
    public static String infixToPostfix(String[] tokens) 
    {
        // Stack to hold the resulting postfix expression
        StackInterface<String> postfixStack = new ArrayBoundedStack<>(tokens.length);
        // Stack to hold operators during conversion
        StackInterface<String> operatorStack = new ArrayBoundedStack<>(tokens.length);
        String postfix = "";

        for (String token : tokens) 
        {
            // Check if the token is an operator
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) 
            {
                // While the stack is not empty and precedence of top is greater or equal to current token
                while (!operatorStack.isEmpty() && precedence(token) <= precedence(operatorStack.top())) 
                {
                    postfix += operatorStack.top() + " "; // Append operator to postfix
                    postfixStack.push(operatorStack.top());
                    operatorStack.pop(); // Pop operator from stack
                }
                operatorStack.push(token); // Push current operator to the stack
            } 
            else 
            {
                // If the token is an operand, add it directly to postfix
                postfix += token + " ";
                postfixStack.push(token);
            }
        }

        // Pop remaining operators from the stack to postfix
        while (!operatorStack.isEmpty()) 
        {
            postfix += operatorStack.top() + " ";
            postfixStack.push(operatorStack.top());
            operatorStack.pop();
        }

        return postfix; // Return the postfix expression as a string
    }

 // Evaluates a postfix expression and returns the result
    public static int evaluatePostfix(String postfix) 
    {
        // Stack to store operands during evaluation
        StackInterface<Integer> stack = new ArrayBoundedStack<>(postfix.length());
        String[] tokens = postfix.split(" ");

        for (String token : tokens) 
        {
            // Check if token is a number by verifying each character
            boolean isNumeric = true;
            for (char c : token.toCharArray()) 
            {
                if (!Character.isDigit(c)) 
                {
                    isNumeric = false;
                    break;
                }
            }

            // If token is a number, push it to the stack
            if (isNumeric) 
            {
                stack.push(Integer.parseInt(token));
            } 
            else 
            {
                // If token is an operator, pop two operands from stack
                int b = stack.top();
                stack.pop();
                int a = stack.top();
                stack.pop();
                
                // Perform operation based on the token and push the result
                switch (token) {
                    case "+" -> stack.push(a + b);
                    case "-" -> stack.push(a - b);
                    case "*" -> stack.push(a * b);
                    case "/" -> stack.push(a / b);
                }
            }
        }
        return stack.top(); // The final result is the top of the stack
    }

    // Determines the precedence of operators
    private static int precedence(String operator) 
    {
        return switch (operator) 
        {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            default -> -1;
        };
    }

    public static void main(String[] args) 
    {
        String[] expressions = 
        {
            "1 + 3 * 8",
            "8 - 3 - 4 * 6 + 3",
            "8 - 2 + 8 / 4 + 6 - 1 - 6 / 2"
        };

        for (String expression : expressions) 
        {
            // Tokenize, convert to postfix, evaluate, and display results
            String[] tokens = getTokens(expression);

            String postfix = infixToPostfix(tokens);
            System.out.println("Infix: " + expression);
            System.out.println("Postfix: " + postfix);

            int result = evaluatePostfix(postfix);
            System.out.println("Evaluation: " + result);
            System.out.println();
        }
    }
}