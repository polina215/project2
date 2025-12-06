# project2
Polina Grivachenko
project
06.12.2025
калькулятор:
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Main extends JFrame {

    private double a = 0;
    private String c = "";
    private boolean n = true;

    Main() {
        super("Калькулятор");
        setBounds(500, 200, 1000, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JButton btn0 = new JButton("0");
        btn0.setBounds(150, 250, 50, 50);

        JButton btn1 = new JButton("1");
        btn1.setBounds(100, 100, 50, 50);

        JButton btn2 = new JButton("2");
        btn2.setBounds(150, 100, 50, 50);

        JButton btn3 = new JButton("3");
        btn3.setBounds(200, 100, 50, 50);

        JButton btn4 = new JButton("4");
        btn4.setBounds(100, 150, 50, 50);

        JButton btn5 = new JButton("5");
        btn5.setBounds(150, 150, 50, 50);

        JButton btn6 = new JButton("6");
        btn6.setBounds(200, 150, 50, 50);

        JButton btn7 = new JButton("7");
        btn7.setBounds(100, 200, 50, 50);

        JButton btn8 = new JButton("8");
        btn8.setBounds(150, 200, 50, 50);

        JButton btn9 = new JButton("9");
        btn9.setBounds(200, 200, 50, 50);


        JButton btnPlus = new JButton("+");
        btnPlus.setBounds(100, 250, 50, 50);

        JButton btnMinus = new JButton("-");
        btnMinus.setBounds(200, 250, 50, 50);

        JButton btnEquals = new JButton("=");
        btnEquals.setBounds(250, 200, 50, 100);

        JButton btnClear = new JButton("C");
        btnClear.setBounds(250, 100, 50, 100);


        JTextField displayField = new JTextField("0");
        displayField.setBounds(100, 50, 200, 50);


        btn0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (n) {
                    displayField.setText("0");
                    n = false;
                } else {
                    displayField.setText(displayField.getText() + "0");
                }
            }
        });
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (n) {
                    displayField.setText("1");
                    n = false;
                } else {
                    displayField.setText(displayField.getText() + "1");
                }
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (n) {
                    displayField.setText("2");
                    n = false;
                } else {
                    displayField.setText(displayField.getText() + "2");
                }
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (n) {
                    displayField.setText("3");
                    n = false;
                } else {
                    displayField.setText(displayField.getText() + "3");
                }
            }
        });
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (n) {
                    displayField.setText("4");
                    n = false;
                } else {
                    displayField.setText(displayField.getText() + "4");
                }
            }
        });
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (n) {
                    displayField.setText("5");
                    n = false;
                } else {
                    displayField.setText(displayField.getText() + "5");
                }
            }
        });
        btn6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (n) {
                    displayField.setText("6");
                    n = false;
                } else {
                    displayField.setText(displayField.getText() + "6");
                }
            }
        });
        btn7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (n) {
                    displayField.setText("7");
                    n = false;
                } else {
                    displayField.setText(displayField.getText() + "7");
                }
            }
        });
        btn8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (n) {
                    displayField.setText("8");
                    n = false;
                } else {
                    displayField.setText(displayField.getText() + "8");
                }
            }
        });
        btn9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (n) {
                    displayField.setText("9");
                    n = false;
                } else {
                    displayField.setText(displayField.getText() + "9");
                }
            }
        });



        btnPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!n) {
                    a = Double.parseDouble(displayField.getText());
                    c = "+";
                    n = true;
                }
            }
        });


        btnMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!n) {
                    a = Double.parseDouble(displayField.getText());
                    c = "-";


                            n = true;
                }
            }
        });


        btnEquals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!c.isEmpty() && !n) {
                    double b = Double.parseDouble(displayField.getText());
                    double result = 0;

                    if (c.equals("+")) {
                        result = a + b;
                    } else if (c.equals("-")) {
                        result = a - b;
                    }



                        displayField.setText(String.valueOf(result));
                    }

                    c = "";
                    n= true;
                }
            
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayField.setText("0");
                a = 0;
                c = "";
                n = true;
            }
        });

        add(displayField);
        add(btn0);
        add(btn1);
        add(btn2);
        add(btn3);
        add(btn4);
        add(btn5);
        add(btn6);
        add(btn7);
        add(btn8);
        add(btn9);
        add(btnPlus);
        add(btnMinus);
        add(btnEquals);
        add(btnClear);
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.setVisible(true);
    }
}