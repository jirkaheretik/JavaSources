package cz.braza;

/*
"i need code that takes in a number and returns the opposite of that numbe so wirttan backwards"


Your Task

Review the LLM prompt and requirements and evaluate the Response1 and Response2 files for your programming language
Pick which response satisfies the prompt more accurately and review the code for that response against the Response Requirements
Input your final evaluation into Assessment.txt and explain why one response is better than the other
Only complete the tasks for 1 programming language / folder of your choosing
Prompt provided to the LLM

"i need code that takes in a number and returns the opposite of that numbe so wirttan backwards"

Response Requirements

The response must have code
The response must use the correct programming language
The response must implement a function
The function must receive an integer argument
The response must return the integer written in reverse
Hints and Tips

Review each Response and choose which one comes the closest to satisfying the prompt
If the prompt asks for code, the response must provide it
If a response generates code, you must ensure the code runs without errors
If there are errors in the code, use the comment feature to correct the code
The response must satisfy but not go beyond the Response Requirements listed above
The Response Requirements are more important than code functionality
Do not rewrite the function, only fix the errors
At the end, choose which Response is better, and justify your answer in the Assessment.txt file
Provide details describing why you chose that Response
Be mindful of your grammar
You may use Grammarly, but you may not use an LLM like ChatGPT


This is a tough question, because it is more about reviewing PROMPT than reviewing code.
Reason is that the prompt is ambiguous, it is not clear whether the intention is to get OPPOSITE of a number or its REVERSE.
Since getting the opposite value is too easy, my take is that the original intent was to get the reverse of a number, which means code Response1.java is better. On the other hand, it is the one code snippet that does not even work. It uses method reverse() which String class does not have. That could be easily solved by using the StringBuilder class, as suggested with a code comment.

The other issue with both code snippets (and the prompt as well) is that it does not work for some edge cases. Response2 will not work well for Integer.MIN_VALUE, while Response1 does not work for negative numbers and for many large enough positive integers too (1E9+ with high enough last digit). Again, this is more a problem of the input requirements.

Also, if speed is the issue, the suggested method is clear and concise, but SLOW, as it needs converting the integer to String and back, while the process can be easily done with numbers only.

Both code snippets are quite different from what human usually produce, well structured, using good naming and code convensions. These are the kind of mistakes AI doesn't make.
 */

public class Response1 {
    public static int reverse(int number) {

        String numString = Integer.toString(number);

        String reversedString = new StringBuilder(numString).reverse().toString();

        int reversedNumber = Integer.parseInt(reversedString);

        return reversedNumber;
    }

    public static int betterReverse(int number) {
        return Integer.parseInt(new StringBuilder(Integer.toString(number)).reverse().toString());
    }

    public static int bestReverse(int number) {
        int result = 0;
        while (number != 0) {
            result *= 10;
            result += number % 10;
            number /= 10;
        }
        return result;
    }

    public static void main(String[] args) {
        int number = -15;
        int reversedNumber = bestReverse(number);
        System.out.println("Original number: " + number);
        System.out.println("Reversed number: " + reversedNumber);
    }
}