import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// SignUp class for user registration
public class SignUp extends JFrame {

    // Private member variables
    private JLabel nameLabel, emailLabel, passwordLabel, questionLabel;
    private JTextField nameTextField, emailTextField;
    private JPasswordField passwordField;
    private JComboBox<String> questionComboBox;
    private JButton submitButton;

    // Variables to store sign-up details
    private String userName;
    private String userEmail;
    private String userPass;

    // Constructor to initialize the sign-up window
    public SignUp() {

        setTitle("Amrita Online Test");
        setSize(500, 500);
        setLayout(new GridLayout(5, 2));
        

    // Load the custom icon image
    //ImageIcon icon = new ImageIcon("C:\Users\abhinav\OneDrive\Pictures\Screenshots\amrita.png"); 
    //actual path to your icon image

    // Set the custom icon image
    //setIconImage(icon.getImage());

        // Initialize GUI components
        nameLabel = new JLabel("Name: ");
        emailLabel = new JLabel("Email: ");
        passwordLabel = new JLabel("Password: ");
        questionLabel = new JLabel("Select Number of Questions: ");
        nameTextField = new JTextField();
        emailTextField = new JTextField();
        passwordField = new JPasswordField();
        questionComboBox = new JComboBox<>(new String[]{"5 questions", "10 questions", "15 questions", "20 questions", "25 questions"});
        submitButton = new JButton("Sign Up");

        // Action listener for the submit button
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieve sign-up details and store them in variables
                setUserName(nameTextField.getText());
                setUserEmail(emailTextField.getText());
                setUserPass(new String(passwordField.getPassword()));
                // Validate email and password fields
                if (!isValidEmail(getUserEmail())) {
                    JOptionPane.showMessageDialog(SignUp.this, "Please enter a valid email address.");
                    return;
                }
                if (!isValidPassword(getUserPass())) {
                    JOptionPane.showMessageDialog(SignUp.this, "Password must be at least 8 characters long.");
                    return;
                }
                // Get the selected number of questions
                int numQuestions = 5; // Default value
                String selectedOption = (String) questionComboBox.getSelectedItem();
                if (selectedOption != null) {
                    String[] parts = selectedOption.split(" ");
                    numQuestions = Integer.parseInt(parts[0]);
                }

                // Display a message confirming sign-up
                JOptionPane.showMessageDialog(SignUp.this, "Sign Up Successful!\nName: " + getUserName() + "\nEmail: " + getUserEmail() + "\nPassword: " + getUserPass() + "\nNumber of Questions: " + numQuestions);
                dispose();

                // Start the online test
                //playSound("D:\mr_crabs_walking.wav"); 
                // Add the sound effect here
                new OnlineTest("Online Test Of Java", getUserName(), getUserEmail(), getUserPass(), numQuestions).setVisible(true);
            }
        });

        // Add GUI components to the frame
        add(nameLabel);
        //label name 

        add(nameTextField);
        add(emailLabel);
        add(emailTextField);//email text field
        add(passwordLabel);
        add(passwordField);
        //password feild
        add(questionLabel);
        add(questionComboBox);
        //questions box selector
        add(new JLabel());
        add(submitButton);
        //submit button

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Getter and Setter methods for userName, userEmail, and userPass
    public String getUserName() 
    {
        return userName;
    }

    public void setUserName(String userName)
     {
        this.userName = userName;
    }

    public String getUserEmail()
     {
        return userEmail;
    }

    public void setUserEmail(String userEmail)
     {
        this.userEmail = userEmail;
    }

    public String getUserPass()
     {
        return userPass;
    }

    public void setUserPass(String userPass)
     {
        this.userPass = userPass;
    }

    // Method to validate email format using regular expression
    private boolean isValidEmail(String email)
     {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Method to validate password length
    private boolean isValidPassword(String password) 
    {
        return password.length() >= 8;
    }

    // Main method to run the application
    public static void main(String[] args) 
    {
        EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new SignUp().setVisible(true);
            }
        });
    }

    // OnlineTest class for conducting the online test
    class OnlineTest extends JFrame implements ActionListener {

        // Private member variables
        private JLabel l, timerLabel;
        private JRadioButton jb[] = new JRadioButton[25]; // Updated to accommodate 25 questions
        private JToggleButton bookmarkButton;
        private JButton b1, b2, viewBookmarksButton;
        private ButtonGroup bg;
        private int count = 0, current = 0;
        private int m[] = new int[25]; // Updated for 25 questions
        private int minutes;
        private int seconds;
        private Timer timer;
        private ArrayList<Integer> bookmarkedQuestions = new ArrayList<>();

        // Variables to store sign-up details
        private String userName;
        private String userEmail;
        private String userPass;

        // Constructor to initialize the online test window
        public OnlineTest(String s, String name, String email, String password, int numQuestions) {
            super(s);
            this.userName = name;
            this.userEmail = email;
            this.userPass = password;

            l = new JLabel();
            add(l);
            bg = new ButtonGroup();
            this.count = 0;

            for (int i = 0; i < 25; i++) { // Updated loop for 25 questions
                jb[i] = new JRadioButton();
                add(jb[i]);
                bg.add(jb[i]);
            }
            //bookmarked question box            
            bookmarkButton = new JToggleButton("Bookmark");
            bookmarkButton.addActionListener(this);
            add(bookmarkButton);
            viewBookmarksButton = new JButton("View Bookmarks");
            viewBookmarksButton.addActionListener(this);
            add(viewBookmarksButton);
            b1 = new JButton("Next");
            b2 = new JButton("Submit");
            b1.addActionListener(this);
            b2.addActionListener(this);
            add(b1);
            add(b2);
            set();
            l.setBounds(30, 40, 450, 20);
            jb[0].setBounds(50, 80, 100, 20);
            jb[1].setBounds(50, 110, 100, 20);
            jb[2].setBounds(50, 140, 100, 20);
            jb[3].setBounds(50, 170, 100, 20);
            bookmarkButton.setBounds(400, 240, 100, 30);
            viewBookmarksButton.setBounds(150, 240, 200, 30);
            b1.setBounds(100, 290, 100, 30);
            b2.setBounds(270, 290, 100, 30);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(null);
            setLocation(250, 100);
            setVisible(true);
            setSize(600, 400);

            // Initialize timer based on the number of questions
            this.minutes = numQuestions;
            this.seconds = minutes * 60;
            timerLabel = new JLabel("Time Remaining: " + minutes + ":00");
            timerLabel.setBounds(450, 10, 150, 20);
            add(timerLabel);

            timer = new Timer(1000, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (seconds > 0) {
                        seconds--;
                        updateTimerLabel();
                    } else {
                        timer.stop();
                        JOptionPane.showMessageDialog(OnlineTest.this, "Time's up! Test submitted automatically.\nYour score: " + count + "\nSign Up Details:\nName: " + userName + "\nEmail: " + userEmail + "\nPassword: " + userPass);
                        dispose();
                    }
                }
            });
            timer.start();
        }

        // Method to update the timer label
        private void updateTimerLabel() {
            int mins = seconds / 60;
            int secs = seconds % 60;
            String time = String.format("%02d:%02d", mins, secs);
            timerLabel.setText("Time Remaining: " + time);
        }

        // ActionPerformed method to handle button clicks
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == b1) {
                if (check())
                    count = count + 1;
                current++;
                set();
                if (current == 12) {
                    b1.setEnabled(false);
                    b2.setText("Submit");
                }
            } else if (e.getSource() == b2) {
                JOptionPane.showMessageDialog(this, "Test submitted.\nYour score: " + count + "\nSign Up Details:\nName: " + userName + "\nEmail: " + userEmail + "\nPassword: " + userPass);
                dispose();
            } else if (e.getSource() == bookmarkButton) {
                if (bookmarkButton.isSelected()) {
                    bookmarkedQuestions.add(current);
                    JOptionPane.showMessageDialog(this, "Question bookmarked!");
                } else {
                    bookmarkedQuestions.remove((Integer) current);
                    JOptionPane.showMessageDialog(this, "Bookmark removed!");
                }
            } else if (e.getSource() == viewBookmarksButton) {
                StringBuilder bookmarks = new StringBuilder();
                bookmarks.append("Bookmarked Questions:\n");
                for (int q : bookmarkedQuestions) {
                    bookmarks.append("Question ").append(q + 1).append("\n");
                }
                JOptionPane.showMessageDialog(this, bookmarks.toString());
            }
        }

        // Method to set questions and options
        private void set() {
            jb[4].setSelected(true);
            if (current == 0) {
                l.setText("Que1: Which one among these is not a datatype");
                jb[0].setText("int");
                jb[1].setText("Float");
                jb[2].setText("boolean");
                jb[3].setText("char");
            }
            //que 2
             else if (current == 1)
              {
                l.setText("Que2: Which class is available to all the class automatically");
                jb[0].setText("Swing");
                jb[1].setText("Applet");
                jb[2].setText("Object");
                jb[3].setText("ActionEvent");
            }
            //que3
             else if (current == 2)
              {
                l.setText("Que3: Which package is directly available to our class without importing it");
                jb[0].setText("swing");
                jb[1].setText("applet");
                jb[2].setText("net");
                jb[3].setText("lang");
            }
            //que4
             else if (current == 3) 
             {
                l.setText("Que4: String class is defined in which package");
                jb[0].setText("lang");
                jb[1].setText("Swing");
                jb[2].setText("Applet");
                jb[3].setText("awt");
            }
            //que5
             else if (current == 4) 
             {
                l.setText("Que5: Which institute is best for java coaching");
                jb[0].setText("Utek");
                jb[1].setText("Aptech");
                jb[2].setText("SSS IT");
                jb[3].setText("jtek");
            }
            //que6
             else if (current == 5)
              {
                l.setText("Que6: Which one among these is not a keyword");
                jb[0].setText("class");
                jb[1].setText("int");
                jb[2].setText("get");
                jb[3].setText("if");
            } 
            //que7
            else if (current == 6) 
            {
                l.setText("Que7: Which one among these is not a class ");
                jb[0].setText("Swing");
                jb[1].setText("Actionperformed");
                jb[2].setText("ActionEvent");
                jb[3].setText("Button");
            } 
            //que8
            else if (current == 7)
             {
                l.setText("Que8: which one among these is not a function of Object class");
                jb[0].setText("toString");
                jb[1].setText("finalize");
                jb[2].setText("equals");
                jb[3].setText("getDocumentBase");
            } 
            //que9
            else if (current == 8) 
            {
                l.setText("Que9: which function is not present in Applet class");
                jb[0].setText("init");
                jb[1].setText("main");
                jb[2].setText("start");
                jb[3].setText("destroy");
            } 
            //que10
            else if (current == 9)
             {
                l.setText("Que10: Which one among these is not a valid component");
                jb[0].setText("JButton");
                jb[1].setText("JList");
                jb[2].setText("JButtonGroup");
                jb[3].setText("JTextArea");
            }
            //que11
             else if (current == 10)
              {
                l.setText("Que11: What does JVM stand for?");
                jb[0].setText("Java Very Much");
                jb[1].setText("Java Virtual Machine");
                jb[2].setText("Just Virtual Machine");
                jb[3].setText("Just Very Much");
            }
            //que12
             else if (current == 11)
              {
                l.setText("Que12: Which of the following is not a Java feature?");
                jb[0].setText("Dynamic");
                jb[1].setText("Architecture Neutral");
                jb[2].setText("Use of pointers");
                jb[3].setText("Object-oriented");
            }
             else if (current == 12) 
             {
                l.setText("Que13: _____ is used to find and fix bugs in the Java programs.");
                jb[0].setText("JVM");
                jb[1].setText("JRE");
                jb[2].setText("JDK");
                jb[3].setText("JDB");
            }
             else if (current == 13)
              {
                l.setText("Que14: Which of the following is a marker interface?");
                jb[0].setText("Runnable");
                jb[1].setText("Serializable");
                jb[2].setText("Cloneable");
                jb[3].setText("Comparable");
            }
             else if (current == 14)
              {
                l.setText("Que15: What is the return type of the hashCode() method in the Object class?");
                jb[0].setText("int");
                jb[1].setText("void");
                jb[2].setText("Object");
                jb[3].setText("long");
            }
             else if (current == 15)
              {
                l.setText("Que15: What is the return type of the hashCode() method in the Object class?");
                jb[0].setText("int");
                jb[1].setText("void");
                jb[2].setText("Object");
                jb[3].setText("long");            } else if (current == 16) {
                // Add the text for the seventeenth question and options
            }
             else if (current == 17)
              {
                l.setText("Que15: What is the return type of the hashCode() method in the Object class?");
                jb[0].setText("int");
                jb[1].setText("void");
                jb[2].setText("Object");
                jb[3].setText("long");            } else if (current == 18) {
                // Add the text for the nineteenth question and options
            } 
            else if (current == 19)
             {
                l.setText("Que15: What is the return type of the hashCode() method in the Object class?");
                jb[0].setText("int");
                jb[1].setText("void");
                jb[2].setText("Object");
                jb[3].setText("long");            
            } 
            else if (current == 20)
             {
                // Add the text for the twenty-first question and options
            } else if (current == 21) {
                l.setText("Que15: What is the return type of the hashCode() method in the Object class?");
                jb[0].setText("int");
                jb[1].setText("void");
                jb[2].setText("Object");
                jb[3].setText("long");          
              } 
                else if (current == 22) 
                {
                    l.setText("Que15: What is the return type of the hashCode() method in the Object class?");
                jb[0].setText("int");
                jb[1].setText("void");
                jb[2].setText("Object");
                jb[3].setText("long");        
                 } 
            else if (current == 23)
             {
                l.setText("Que15: What is the return type of the hashCode() method in the Object class?");
                jb[0].setText("int");
                jb[1].setText("void");
                jb[2].setText("Object");
                jb[3].setText("long");     
                }
                 //que num 24 is actual que num 25 
                 //it is indicated cause que num 1 is:
                else if (current == 24) 
                {
                    {
                        l.setText("Que15: What is the return type of the hashCode() method in the Object class?");
                        jb[0].setText("int");
                        jb[1].setText("void");
                        jb[2].setText("Object");
                        jb[3].setText("long");     
                        }            
                     }
                     //last question that is que no 25 
            // Set bookmarked questions
            if (bookmarkedQuestions.contains(current)) {
                bookmarkButton.setSelected(true);
            } else {
                bookmarkButton.setSelected(false);
            }
        }

        // Placeholder method for checking answers
        private boolean check() {
            return true;
        }
    }
}
