package hr.fer.oprpp1.custom.collections.demo;

import hr.fer.oprpp1.custom.collections.ObjectStack;
import org.apache.commons.lang3.math.NumberUtils;

public class StackDemo {
    public static void main(String[] args) { // args = "8 -2 / -1 *" "4 8 2 / + 1 -"
        String s = args[0];
        String[] characters = s.split(" ");
        ObjectStack stack = new ObjectStack();

        for (String character : characters) {
            if (NumberUtils.isParsable(character)) {
                stack.push(Integer.parseInt(character));
            } else {
                int firstOperand = (int) stack.pop();
                int secondOperand = (int) stack.pop();

                switch (character) {
                    case "+" -> stack.push(secondOperand + firstOperand);
                    case "-" -> stack.push(secondOperand - firstOperand);
                    case "/" -> {
                        if (firstOperand == 0) {
                            throw new ArithmeticException("It's not possible to divide by zero.");
                        } else {
                            stack.push(secondOperand / firstOperand);
                        }
                    }
                    case "*" -> stack.push(secondOperand * firstOperand);
                }
            }
        }
        if(stack.size() != 1) {
            System.out.println("error");
        }
        System.out.println("Expression evaluates to: " + stack.pop());
    }
}
