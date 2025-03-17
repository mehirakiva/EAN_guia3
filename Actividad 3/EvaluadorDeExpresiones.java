import java.util.Stack;
import java.util.Scanner;

public class EvaluadorDeExpresiones {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("UNIVERSIDAD EAN - Evaluador de Expresiones Aritméticas");
        System.out.println("Ingrese una expresión aritmética en notación infija:");

        String expresion = scanner.nextLine();

        if (verificarParentesisBalanceados(expresion)) {
            String postfija = convertirInfijaAPostfija(expresion);
            System.out.println("Expresión en notación postfija: " + postfija);
            double resultado = evaluarPostfija(postfija);
            System.out.println("Resultado de la evaluación: " + resultado);
        } else {
            System.out.println("La expresión tiene paréntesis no balanceados.");
        }
    }

    // 1. Verificación de paréntesis balanceados
    public static boolean verificarParentesisBalanceados(String expresion) {
        Stack<Character> pila = new Stack<>();

        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);

            if (c == '(') {
                pila.push(c);
            } else if (c == ')') {
                if (pila.isEmpty()) {
                    return false;  // Si no hay un paréntesis abierto correspondiente
                }
                pila.pop();
            }
        }

        return pila.isEmpty();  // Si la pila está vacía, los paréntesis están balanceados
    }

    // 2. Conversión de notación infija a postfija
    public static String convertirInfijaAPostfija(String expresion) {
        StringBuilder postfija = new StringBuilder();
        Stack<Character> pila = new Stack<>();

        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);

            if (Character.isDigit(c)) {
                // Si es un dígito, lo agregamos directamente a la expresión postfija
                postfija.append(c);
            } else if (c == '(') {
                pila.push(c);
            } else if (c == ')') {
                // Pop de la pila hasta encontrar el paréntesis de apertura
                while (!pila.isEmpty() && pila.peek() != '(') {
                    postfija.append(pila.pop());
                }
                pila.pop();  // Eliminamos el paréntesis de apertura '('
            } else if (esOperador(c)) {
                // Si es un operador, procesamos según la precedencia
                while (!pila.isEmpty() && tieneMayorPrecedencia(pila.peek(), c)) {
                    postfija.append(pila.pop());
                }
                pila.push(c);
            }
        }

        // Vaciar los operadores restantes en la pila
        while (!pila.isEmpty()) {
            postfija.append(pila.pop());
        }

        return postfija.toString();
    }

    // 3. Evaluación de la expresión postfija
    public static double evaluarPostfija(String postfija) {
        Stack<Double> pila = new Stack<>();

        for (int i = 0; i < postfija.length(); i++) {
            char c = postfija.charAt(i);

            if (Character.isDigit(c)) {
                pila.push((double) (c - '0'));  
            } else if (esOperador(c)) {
                double b = pila.pop();
                double a = pila.pop();
                double resultado = 0;

                switch (c) {
                    case '+':
                        resultado = a + b;
                        break;
                    case '-':
                        resultado = a - b;
                        break;
                    case '*':
                        resultado = a * b;
                        break;
                    case '/':
                        if (b != 0) {
                            resultado = a / b;
                        } else {
                            System.out.println("Error: División por cero.");
                            return Double.NaN;  // Devolver "No es un número"
                        }
                        break;
                }

                pila.push(resultado);
            }
        }

        return pila.pop();
    }

    // Verifica si un carácter es un operador
    public static boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    // Determina si un operador tiene mayor o igual precedencia que otro
    public static boolean tieneMayorPrecedencia(char operador1, char operador2) {
        if ((operador1 == '*' || operador1 == '/') && (operador2 == '+' || operador2 == '-')) {
            return true;
        }
        return (operador1 == operador2);
    }
}
