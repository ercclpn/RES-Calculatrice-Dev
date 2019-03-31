package calculator;

import java.text.ParseException;

public class Calculator {
    public static String calculate(String[] line){

        try{
            if(line.length != 3){
                return "Wrong number of argument.";
            }
            int result = Integer.parseInt(line[0]);
            int b = Integer.parseInt((line[2]));
            switch(line[1]){
                case "+" :
                    result+=b;
                    break;

                case "-":
                    result-=b;
                    break;

                case "*":
                    result*=b;
                    break;

                case "/":
                    result/=b;
                    break;

                default:
                    return "Bad operation.";
            }
            return "Answer=" + String.valueOf(result);
        }catch(NumberFormatException e){
            return "Not a number.";
        }
    }
}
