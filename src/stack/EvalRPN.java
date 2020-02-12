package stack;

import java.util.Stack;

/*
反向波兰表示法
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are+,-,*,/. Each operand may be an integer or another expression.

Some examples:

  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6

 */
public class EvalRPN {

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack=new Stack<>();
        int operand1,operand2;
        for (String token : tokens){
            int type=analysis(token);
            if (type==0){
                stack.push(Integer.valueOf(token));
            }else{
                operand2=stack.pop();
                operand1=stack.pop();
                switch (type){
                    case 1:
                        stack.push(operand1+operand2);
                        break;
                    case 2:
                        stack.push(operand1-operand2);
                        break;
                    case 3:
                        stack.push(operand1*operand2);
                        break;
                    case 4:
                        stack.push(operand1/operand2);
                }
            }
        }
        if (!stack.isEmpty()){
            return stack.pop();
        }
        return 0;
    }

    public int analysis(String token){
        switch (token){
            case "+":
                return 1;
            case "-":
                return 2;
            case "*":
                return 3;
            case "/":
                return 4;
                default:
                    return 0;
        }
    }
}
