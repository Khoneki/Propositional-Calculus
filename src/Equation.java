import java.util.HashMap;
import java.util.Stack;

/**
 * Created by Khoneki on 2016-04-11.
 */
public class Equation {
    final static String ONLYIF = ">";
    final static String AND = "&";
    final static String OR = "+";
    final static String NOT = "~";
    final static String EQUAL = "=";
    final static String True = "T";
    final static String False = "F";

    public static String transFomula(String Fomula) {
        String[] fomula = Fomula.split("");
        String newFomula = "";

        Stack<String> fomulaStack = new Stack<>();
        Stack<String> connective = new Stack<>();

        for(int i = 0; i < fomula.length; i++) fomulaStack.push(fomula[i]);

        while(!fomulaStack.isEmpty()) {
            String temp = fomulaStack.pop();
            switch(temp) {
                case ONLYIF :
                    connective.push(temp);
                    break;
                case AND :
                    connective.push(temp);
                    break;
                case OR :
                    connective.push(temp);
                    break;
                case NOT :
                    connective.push(temp);
                    newFomula += connective.pop();
                    break;
                case EQUAL :
                    connective.push(temp);
                    break;
                case ")" :
                    int tp = 1;
                    String newf = "";
                    String tmp = "";
                    while(tp != 0) {
                        tmp = fomulaStack.pop();
                        if(tmp.equals(")")) tp++;
                        else if (tmp.equals("(")) tp--;
                        newf += tmp;
                    }
                    char[] newf2 = newf.toCharArray();
                    char[] newf3 = new char[newf2.length];
                    for(int i = 0; i < newf2.length; i++)
                        newf3[i] = newf2[newf2.length-i-1];
                    newFomula += "("+transFomula(new String(newf3))+")";
                    break;
                default :
                    if(temp.matches("^[A-Z]+$")) newFomula = newFomula + temp;
            }
        }
        while(!connective.isEmpty())
            newFomula = newFomula + connective.pop();
        return newFomula;
    }

    public static boolean compute(String fomula) {
        Fomula fml = new Fomula();
        String[] fm = fomula.split("");
        Stack<String> fmStack = new Stack<>();

        for(int i = 0; i < fm.length; i++) {

            fmStack.push(fm[i]);
            switch(fm[i]) {
                case AND :
                    fmStack.pop();
                    fmStack.push(fml.Conjunction(main.propos.get(fmStack.pop()), main.propos.get(fmStack.pop()))? True : False);
                    break;
                case OR :
                    fmStack.pop();
                    fmStack.push(fml.Disjunction(main.propos.get(fmStack.pop()), main.propos.get(fmStack.pop()))? True : False);
                    break;
                case ONLYIF :
                    fmStack.pop();
                    fmStack.push(fml.Implies(main.propos.get(fmStack.pop()), main.propos.get(fmStack.pop()))? True : False);
                    break;
                case EQUAL :
                    fmStack.pop();
                    fmStack.push(fml.Equivalent(main.propos.get(fmStack.pop()), main.propos.get(fmStack.pop()))? True : False);
                    break;
                case NOT :
                    fmStack.pop();
                    fmStack.push(fml.Negation(main.propos.get(fmStack.pop()))? True : False);
                    break;
                case ")" :
                    fmStack.pop();
                    int tp = 1;
                    String nwf = "";
                    String ftmp = "";
                    while(tp != 0) {
                        ftmp = fmStack.pop();
                        if(ftmp.equals(")")) {
                            tp++;
                            nwf += ftmp;
                        }
                        else if (ftmp.equals("(")) tp--;
                        else nwf += ftmp;
                    }
                    fmStack.push(compute(nwf) ? True : False);
                    break;

            }
        }
        return fmStack.pop().equals(True);
    }

}
