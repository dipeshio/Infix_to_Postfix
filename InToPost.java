import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;



public class InToPost implements ActionListener {
    int HEIGHT = 125;
    int WIDTH = 600;

    JFrame frame;
    JPanel solveButton;
    JTextField eqDisplay;
    JButton postButton;
    JTextField postDisplay;

    InToPost() {
        frame = new JFrame();
        frame.setTitle("In-Fix Notation to Post-Fix");
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);

        //creating a text field for infix eq.
        eqDisplay = new JTextField(" ");
        eqDisplay.setPreferredSize(new Dimension(225, 50));
        eqDisplay.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));

        // centering for the text field
        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        contentPanel.add(eqDisplay, gbc);

        //creating a button for postfix
        postButton = new JButton("Post!");
        postButton.setFont(new Font("Serif", Font.BOLD, 15));
        postButton.setPreferredSize(new Dimension(75, 50));
        postButton.addActionListener(this);
        contentPanel.add(postButton, gbc);

        //creating a text field for postfix eq.
        postDisplay = new JTextField(" ");
        postDisplay.setEditable(Boolean.FALSE);
        postDisplay.setPreferredSize(new Dimension(225, 50));
        postDisplay.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
        contentPanel.add(postDisplay, gbc);

        frame.add(contentPanel, BorderLayout.WEST);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public String Convert(String infix) {
        Stack<String> operators = new Stack<>();
        List<String> postfix = new ArrayList<>();

        // Loop through each character
        for (char current : infix.toCharArray()) {
            String currentStr = String.valueOf(current);
            if (isOperator(currentStr)) {
                while (!operators.empty() && hasLowerPrecedence(currentStr, operators.peek())) {
                    postfix.add(operators.pop());
                }
                operators.push(currentStr);
            } else if (Character.isLetterOrDigit(current)) { // Check if it's an operand
                postfix.add(currentStr);
            }
        }

        // Pop all remaining operators
        while (!operators.empty()) {
            postfix.add(operators.pop());
        }

        return String.join("", postfix);
    }


    // helper function to determine if character is an operator or not.
    public static boolean isOperator(String Operator) {
        return List.of("+", "-", "*", "/", "%").contains(Operator);
    }

    // helper function for determining operator precedence.
    public static boolean hasLowerPrecedence(String op1, String op2) {
        return precedence(op1) < precedence(op2);
    }

    // helper function for setting operator precedence.
    public static int precedence(String operator) {
        return switch (operator) {
            case "+", "-" -> 1;
            case "/", "*" -> 2;
            case "%" -> 3;
            default -> 4;
        };
    }

    public static int evaluateOperations(String postfix) {
        Stack<Integer> stack = new Stack<>();

        for (String component : postfix.split("")) {

            if (Character.isDigit(component.charAt(0))) {
                stack.push(Integer.parseInt(component));
            } else {
                int nbr1 = stack.pop();
                int nbr2 = stack.pop();
                switch (component) {
                    case "+":
                        stack.push(nbr1 + nbr2);
                    case "-":
                        stack.push(nbr1 - nbr2);
                    case "/":
                        stack.push(nbr1 / nbr2);
                    case "*":
                        stack.push(nbr1 * nbr2);
                    case "%":
                        stack.push(nbr1 % nbr2);
                }
            }
        }
        return stack.pop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String eq = eqDisplay.getText();
        postDisplay.setText(Convert(eq));
    }

    public static void main(String[] args) {
        new InToPost();
    }


}