import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

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
        gbc.insets = new Insets(10,10,10,10);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String eq = eqDisplay.getText();

        postDisplay.setText(eq);

    }




    public static void main(String[] args) {
        new InToPost();
    }


}