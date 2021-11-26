import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PFEvaluator {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("Calc1.stk");
        Scanner in = new Scanner(f);
        ArrayList<Token> tokenList = new ArrayList<Token>();
        String[] validOp = new String[]{"+", "-", "*", "/"};
        String invalidChar = "";
        while (in.hasNext()){
            String line = in.next();
            if (line.matches("\\d+")){
                Token token = new Token(TokenType.NUM, line);
                tokenList.add(token);
            }
            else if (Arrays.asList(validOp).contains(line)){
                Token token = null;
                if (line.equals("+")){
                    token = new Token(TokenType.PLUS, line);
                }
                else if (line.equals("-")){
                    token = new Token(TokenType.MINUS, line);
                }
                else if (line.equals("/")){
                    token = new Token(TokenType.SLASH, line);
                }
                else if (line.equals("*")){
                    token = new Token(TokenType.STAR, line);
                }
                tokenList.add(token);
            }
            else{
                Token token = new Token(TokenType.EOF, line);
                tokenList.add(token);
            }
        }
        Stack<Integer> opStack = new Stack<>();
        boolean failed = false;
        for (int i = 0; i < tokenList.size(); i++){
            Token token = tokenList.get(i);
            if (token.type.equals(TokenType.NUM)){
                opStack.push(Integer.parseInt(token.lexeme));
            }
            else if (token.type.equals(TokenType.EOF)){
                System.out.println("Unexpected character: " + token.lexeme + " on line " + (i + 1));
                failed = true;
                break;
            }
            else{
                Integer first, second;
                second = opStack.pop();
                first = opStack.pop();
                if (token.type.equals(TokenType.PLUS)){
                    opStack.push(first + second);
                }
                else if (token.type.equals(TokenType.MINUS)){
                    opStack.push(first - second);
                }
                else if (token.type.equals(TokenType.SLASH)){
                    opStack.push(first / second);
                }
                else if (token.type.equals(TokenType.STAR)){
                    opStack.push(first * second);
                }
            }
        }
        if (!failed){
            for (Token token : tokenList) {
                System.out.println("Token [type=" + token.type + ", lexeme=" + token.lexeme + "]");
            }
            System.out.println(opStack.pop());
        }
    }
}


