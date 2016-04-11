import java.util.*;

public class main {
    final static String ONLYIF = ">";
    final static String AND = "&";
    final static String OR = "+";
    final static String NOT = "~";
    final static String EQUAL = "=";
    public static HashMap<String, Propositional> propos;

    public static void main(String[] args) {
        propos = new HashMap<>();
        Fomula f = new Fomula();
        Equation e = new Equation();
        Scanner sc = new Scanner(System.in);

        propos.put("T", new Propositional(true));
        propos.put("F", new Propositional(false));

        while(true) {
            String fomul = sc.nextLine();
            if(fomul.split("")[0].equals("!"))
                propos.put(fomul.split("")[1], new Propositional(fomul.split("")[2].equals("T")));
            else System.out.println(e.transFomula(fomul)+" "+e.compute(e.transFomula(fomul)));
        }
    }
}
