class Solution {
    public int calculate(String s) {
       Stack<Integer> stack = new Stack<>();
       int sign = 1;
       int res = 0;
      for (int i = 0; i < s.length(); i++){
         if (Character.isDigit(s.charAt(i))){
           int num = s.charAt(i) - '0';
           while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))){
             num = num * 10 + s.charAt(i + 1) -'0';
             i++;
           }
           res += sign*num;
         }else if (s.charAt(i) == '+'){
           sign = 1;
         }else if (s.charAt(i) == '-'){
           sign = -1;
         }else if (s.charAt(i) == '('){
           stack.push(res);
           stack.push(sign);
           res = 0;
           sign = 1;
         }else if (s.charAt(i) == ')') {
           res = res * stack.pop() + stack.pop();// nultiple sign and add the previous value
         }
      }
      return res;
    }
}

/**
 * Implement a basic calculator to evaluate a simple expression string.

 The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

 You may assume that the given expression is always valid.

 Some examples:
 "1 + 1" = 2
 " 2-1 + 2 " = 3
 "(1+(4+5+2)-3)+(6+8)" = 23
 Note: Do not use the eval built-in library function.
 */
public class solution2 {

	public int calculate(String s) {
		if (s == null || s.isEmpty()) {
			return 0;
		}

		s = s.replaceAll("\\s", "");
		char[] chars = s.toCharArray();
		List<String> filteredStr = new ArrayList();
		for (int i = 0; i < chars.length; ) {
			StringBuilder sb = new StringBuilder();
			while (i < chars.length && Character.isDigit(chars[i])) {
				sb.append(chars[i]);
				i++;
			}
			if (i == chars.length) {
				if (sb.toString().length() != 0) {
					filteredStr.add(sb.toString());
				}
			} else {
				if (sb.toString().length() != 0) {
					filteredStr.add(sb.toString());
				}
				if (chars[i] == '+' || chars[i] == '-' || chars[i] == '(' || chars[i] == ')') {
					filteredStr.add(String.valueOf(chars[i]));
				}
				i++;
			}
		}

		for (String str : filteredStr) {
			System.out.print(str);
		}

		Stack<String> stack1 = new Stack();
		Stack<String> stack2 = new Stack();
		for (int i = 0; i < filteredStr.size(); ) {
			while (i < filteredStr.size() && !filteredStr.get(i).equals(")")) {
				stack1.push(filteredStr.get(i));
				i++;
			}
			if (i != filteredStr.size()) {
				while (!stack1.isEmpty() && !stack1.peek().equals("(")) {
					stack2.push(stack1.pop());
				}
				stack1.pop();
				int exp = 0;
				while (!stack2.isEmpty()) {
					if (stack2.size() == 1) {
						stack1.push(stack2.pop());
						break;
					}
					int operand1 = Integer.parseInt(stack2.pop());
					String operator = stack2.pop();
					int operand2 = Integer.parseInt(stack2.pop());
					if (operator.equals("+")) {
						exp = operand1 + operand2;
					} else if (operator.equals("-")) {
						exp = operand1 - operand2;
					}
					stack2.push(String.valueOf(exp));
				}
				i++;
			}
		}

		if (stack1.size() == 1) {
			return Integer.parseInt(stack1.pop());
		}

		while (!stack1.isEmpty()) {
			stack2.push(stack1.pop());
		}
		while (!stack2.isEmpty()) {
			if (stack2.size() == 1) {
				stack1.push(stack2.pop());
				break;
			}
			int exp = 0;
			int operand1 = Integer.parseInt(stack2.pop());
			String operator = stack2.pop();
			int operand2 = Integer.parseInt(stack2.pop());
			if (operator.equals("+")) {
				exp = operand1 + operand2;
			} else if (operator.equals("-")) {
				exp = operand1 - operand2;
			}
			stack2.push(String.valueOf(exp));
		}
		return Integer.parseInt(stack1.pop());
	}

}
