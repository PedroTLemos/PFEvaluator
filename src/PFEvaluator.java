import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class PFEvaluator {
    public static void main(String args[]) throws FileNotFoundException {
        File f = new File("Calc1.stk");
        Scanner in = new Scanner(f);
        Stack<Integer> stack = new Stack<>();
        boolean failed = false;
        while (in.hasNext()){
            String line = in.next();
            System.out.println(line);
            if (line.matches("\\d+")){
                int number = Integer.parseInt(line);
                stack.push(number);
            }
            else{
                Integer first, second;
                second = stack.pop();
                first = stack.pop();
                int result;
                if (line.equals("+")){
                    result = first + second;
                }
                else if (line.equals("-")){
                    result = first - second;
                }
                else if (line.equals("*")){
                    result = first * second;
                }
                else if (line.equals("/")){
                    result = first / second;
                }
                else{
                    System.out.println("Operação inválida!");
                    failed = true;
                    break;
                }

                stack.push(result);
            }
        }
        if (!failed){
            System.out.println("Resultado: " + stack.pop());
        }
        else{
            System.out.println("Arquivo ilegível, por favor reescreva-o");
        }

    }
}
