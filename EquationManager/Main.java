
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Equation {

    Term[] terms;  // Array of terms (e.g., 2x, y)
    String[] variables;  // Array of unique variables (e.g., "x", "y")

    Equation(int size) {
        terms = new Term[size];  // Initialize array with the size of the number of terms
    }

    // Displays the terms in the equation
    public void display() {
        for (Term term : terms) {
            System.out.print(term + " ");
        }
        System.out.println();
    }

    // Multiplies two equations
    public Equation multiply(String equationString) {
        Equation secondEquation = EquationManager.parseEquation(equationString);  // Parse the second equation
        Equation resultEquation = new Equation(this.terms.length * secondEquation.terms.length);

        int count = 0;
        // Multiply each term of the current equation with each term of the second equation
        for (Term firstTerm : this.terms) {
            for (Term secondTerm : secondEquation.terms) {
                resultEquation.terms[count++] = firstTerm.multiply(secondTerm);
            }
        }
        return resultEquation;  // Return the resulting equation after multiplication
    }

    // Evaluates the equation by substituting values for the variables
    public double evaluate(double[] values) {
        double result = 0.0;
        // Sum up the results of each term evaluation
        for (Term term : terms) {
            result += term.evaluate(variables, values);
        }
        return result;
    }

    @Override
    public String toString() {
        return "Equation [terms=" + Arrays.toString(terms) + ", variables=" + Arrays.toString(variables) + "]";
    }
}

class Term {

    int coefficient;  // Coefficient of the term (e.g., 2 in 2x)
    Variable[] variables;  // Variables in the term (e.g., x^2)

    Term(int size) {
        variables = new Variable[size];  // Initialize array of variables
    }

    // Multiplies two terms and returns the result as a new term
    public Term multiply(Term secondTerm) {
        int[] powerMap = new int[26];  // Maps each variable to its power
        int resultCoefficient = this.coefficient * secondTerm.coefficient;  // Multiply coefficients

        // Add the powers of variables for both terms
        loadVariablesToMap(powerMap, this.variables);
        loadVariablesToMap(powerMap, secondTerm.variables);

        // Create a new Term for the result with the combined variables and powers
        Term resultTerm = new Term(getNonZeroCount(powerMap));
        resultTerm.variables = mapToVariableArray(powerMap);
        resultTerm.coefficient = resultCoefficient;
        return resultTerm;
    }

    // Helper method to load variables into a power map (which stores the variable powers)
    private void loadVariablesToMap(int[] powerMap, Variable[] variables) {
        if (variables != null) {
            for (Variable variable : variables) {
                powerMap[variable.name - 'a'] += variable.power;
            }
        }
    }

    // Get the number of non-zero elements in the power map
    private int getNonZeroCount(int[] map) {
        int count = 0;
        for (int value : map) {
            if (value > 0) {
                count++;
            }
        }
        return count;
    }

    // Converts the power map back into an array of variables
    private Variable[] mapToVariableArray(int[] map) {
        Variable[] variables = new Variable[getNonZeroCount(map)];
        int index = 0;
        for (int i = 0; i < map.length; i++) {
            if (map[i] > 0) {
                variables[index++] = new Variable((char) (i + 'a'), map[i]);
            }
        }
        return variables;
    }

    // Parses a term string (like "2x" or "-3y") into a Term object
    public static Term parseTerm(String termInString) {
        termInString = termInString.trim();
        StringBuilder coefficientBuilder = new StringBuilder();
        StringBuilder variableBuilder = new StringBuilder();
        int index = 0;

        // Handle optional sign
        if (termInString.charAt(index) == '-') {
            coefficientBuilder.append(termInString.charAt(index));
            index++;
        } else if (termInString.charAt(index) == '+') {
            index++;
        }

        boolean foundVariable = false;
        for (; index < termInString.length(); index++) {
            char ch = termInString.charAt(index);
            if (Character.isDigit(ch)) {
                if (foundVariable) {
                    variableBuilder.append(ch);
                } else {
                    coefficientBuilder.append(ch);
                }
            } else {
                variableBuilder.append(ch);
                foundVariable = true;  // Mark that we've found a variable
            }
        }

        int coefficient = coefficientBuilder.length() > 0 ? Integer.parseInt(coefficientBuilder.toString()) : 1;
        Variable variable = variableBuilder.length() > 0 ? Variable.parseVariable(variableBuilder.toString()) : null;
        Term term = new Term(variable != null ? 1 : 0);
        term.coefficient = coefficient;
        if (variable != null) {
            term.variables[0] = variable;
        }

        return term;
    }

    // Evaluates the term by substituting the values of variables
    public double evaluate(String[] variableNames, double[] values) {
        double result = coefficient;
        if (variables != null) {
            for (Variable variable : variables) {
                for (int i = 0; i < variableNames.length; i++) {
                    if (variable.name == variableNames[i].charAt(0)) {
                        result *= Math.pow(values[i], variable.power);
                        break;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(coefficient);
        if (variables != null) {
            for (Variable variable : variables) {
                res.append(variable.name).append("^").append(variable.power);
            }
        }
        return res.toString();
    }
}

class Variable {

    char name;  // Variable name (e.g., 'x')
    int power;  // Power of the variable (e.g., 2 for x^2)

    Variable(char name, int power) {
        this.name = name;
        this.power = power;
    }

    // Parses a variable string (like "x2") into a Variable object
    public static Variable parseVariable(String varString) {
        char name = varString.charAt(0);  // Get the variable name (e.g., 'x')
        int power = 1;  // Default power is 1
        if (varString.length() > 1) {  // If there are more characters
            String powerString = varString.substring(1);  // Extract the power part
            if (!powerString.isEmpty()) {
                power = Integer.parseInt(powerString);  // Parse the power if present
            }
        }
        return new Variable(name, power);  // Return a new Variable object
    }

    @Override
    public String toString() {
        return name + "^" + power;
    }
}

class EquationManager {

    public static Equation parseEquation(String equationInString) {
        equationInString = equationInString.trim();
        String[] termsInPrimitive = splitTerms(equationInString);  // Split into terms

        int count = 0;
        Equation equation = new Equation(termsInPrimitive.length);
        for (String termInPrimitive : termsInPrimitive) {
            equation.terms[count++] = Term.parseTerm(termInPrimitive);
        }

        equation.variables = extractVariables(equationInString);
        return equation;
    }

    // Splits an equation string into terms
    private static String[] splitTerms(String equationInString) {
        return equationInString.split("(?=\\+)|(?=\\-)");
    }

    // Extracts variables from the equation string
    private static String[] extractVariables(String equationInString) {
        Set<String> variables = new HashSet<>();
        for (char ch : equationInString.toCharArray()) {
            if (Character.isLetter(ch)) {
                variables.add(String.valueOf(ch));
            }
        }
        return variables.toArray(new String[0]);
    }
}

public class Main {

    public static void main(String[] args) {
        String equation1 = "2x + y";
        String equation2 = "3y - 4x";

        // Parse the first equation
        Equation firstEquation = EquationManager.parseEquation(equation1);
        firstEquation.display();

        // Multiply the first equation with the second equation
        Equation result = firstEquation.multiply(equation2);
        result.display();

        // Evaluate the equation by substituting values for x and y
        double resultValue = result.evaluate(new double[]{2, 3});  // x = 2, y = 3
        System.out.println("Result: " + resultValue);
    }
}
