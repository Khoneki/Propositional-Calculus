/**
 * Created by Khoneki on 2016-04-10.
 */
public class Fomula {
    public boolean Negation(Propositional P) {
        return !P.value;
    }

    public boolean Conjunction(Propositional P, Propositional Q) {
        return P.value & Q.value;
    }

    public boolean Disjunction(Propositional P, Propositional Q) {
        return P.value | Q.value;
    }

    public boolean Equivalent(Propositional P, Propositional Q) {
        return P.value == Q.value;
    }

    public boolean Implies(Propositional P, Propositional Q) {
        return !P.value | Q.value;
    }
}
