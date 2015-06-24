package com.vgrigoriev.practice;

import java.util.Scanner;
import java.util.Stack;

/**
 * author vgrigoriev on 06.11.2014.
 */
public class Parentheses {

    public static final char LEFT_PAREN = '(';
    public static final char RIGHT_PAREN = ')';
    public static final char RIGHT_BRACKET = ']';
    public static final char LEFT_BRACKET = '[';
    public static final char LEFT_BRACE = '{';
    public static final char RIGHT_BRACE = '}';

    public static boolean isBalanced(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case LEFT_PAREN:
                    stack.push(LEFT_PAREN);
                    break;
                case LEFT_BRACKET:
                    stack.push(LEFT_BRACKET);
                    break;
                case LEFT_BRACE:
                    stack.push(LEFT_BRACE);
                    break;
                case RIGHT_PAREN:
                    if (!validateCorrectParen(stack)) {
                        return false;
                    }
                    break;
                case RIGHT_BRACKET:
                    if (!validateCorrectBracket(stack)) {
                        return false;
                    }
                    break;
                case RIGHT_BRACE:
                    if (!validateCorrectBrace(stack)) {
                        return false;
                    }
                    break;
            }
        }
        return stack.isEmpty();
    }

    private static boolean validateCorrectParen(Stack<Character> stack) {
        if (!stack.isEmpty()) {
            for (Character character : stack) {
                if (character.equals(LEFT_PAREN)) {
                    stack.remove(character);
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean validateCorrectBracket(Stack<Character> stack) {
        if (!stack.isEmpty()) {
            for (Character character : stack) {
                if (character.equals(LEFT_BRACKET)) {
                    stack.remove(character);
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean validateCorrectBrace(Stack<Character> stack) {
        if (!stack.isEmpty()) {
            for (Character character : stack) {
                if (character.equals(LEFT_BRACE)) {
                    stack.remove(character);
                    return true;
                }
            }
        }
        return false;
    }


    /*******************************************
     * Test main method
     *******************************************/

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String inputData = scan.nextLine();
        System.out.println(isBalanced(inputData));
    }
}
