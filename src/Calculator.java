import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Calculator {
    // NOTE: Sir this calculator can only work with two operands(0123456789, 0123456789) and one operator between them(e.g + - / X)
    // we use two arrays.
    // - 1st holds values to be solved
    // - 2nd holds values to be used as buttons

    // 1st array: this array will hold values you click on to be solved. e.g if you click on "3",  and "+", and "5" and then "=". This arraylist will contain [3, +, 5]
    ArrayList<String> solveArray = new ArrayList<>();

    // 2nd array: this array holds values to be used to create buttons
    String[] calcValuesArray = {
            "7", "8", "9",
            "4", "5", "6",
            "1", "2", "3",
            "+", "0", "-",
            "/", "X", "="};

    // Our app gui method
    void calculatorInterface () {

        // create java frame
        JFrame myFrame = new JFrame("Scientific Calculator");
        myFrame.setSize(400, 580);
        myFrame.setLayout(new GridLayout(5, 3));

        // loop through numbers array(the array in line 9) and create buttons for each of them
        for (int i = 0; i < calcValuesArray.length; i++) {
            String valueText = calcValuesArray[i];  // grab a value from array
            JButton button = new JButton(valueText);  // make button with the value
            button.setBackground(Color.BLACK);     // change background color of button(black)
            button.setForeground(Color.white);     // change text color of the button(white)
            button.setFont(new Font("Arial", Font.PLAIN, 40));     // set font of button and increase size
            myFrame.add(button);         // add button to frame

            // add action listener to button
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // if "=" was not clicked on. add the text of the button that was clicked on to our solveArray(in line 15)
                    if (valueText.equals("=") == false){
                        solveArray.add(valueText);

                    // if "=" was clicked on then its time to solve
                    } else if (valueText.equals("=") == true) {
                        solve();   // call solve method
                    }
                }
            });
        }
        myFrame.setVisible(true);
    }

    // our method for solving
    void solve() {
        String val1 = "";    // to hold value 1 e.g [1, 2, 0, +, 1, 0]. in this case val1 will be = 120
        String sign = "";    // to hold sign e.g [1, 2, 0, +, 1, 0]. in this case sign will be = +
        String val2 = "";    // to hold value 2 e.g [1, 2, 0, +, 1, 0]. in this case val2 will be = 20

        // loop through solveArray e.g [1, 2, 0, +, 1, 0] or whatever user clicked
        for (int i = 0; i < solveArray.toArray().length; i++) {

            // if solveArray.get(i): (is not equal to +) and (is not equal to -) and (is not equal to X) and (is not equal to /) and (sign is still empty) ->
            // add solveArray.get(i) to val1 variable
            // summary: so basically add all values you see in the arrayList to val1 variable as long as a sign hasn't been clicked on
            if ((solveArray.get(i) != "+") && (solveArray.get(i) != "-") && (solveArray.get(i) != "X") && (solveArray.get(i) != "/") && (sign == "")){
                val1 = val1 + solveArray.get(i); // add value to val1

            // if solveArray.get(i): (is equal to +) or (is equal to -) or (is equal to X) or (is equal to /)
            // add solveArray.get(i) to sign variable
            // summary: if a sign is clicked add it to the sign
            }else if ((solveArray.get(i) == "+") || (solveArray.get(i) == "-") || (solveArray.get(i) == "X") || (solveArray.get(i) == "/")){
                sign = solveArray.get(i);
            }
            // if both conditions are if and else if conditions are false -> add every value in solveArray to val2 variable
            else {
                val2 = val2 + solveArray.get(i);
            }
        }

        // once loop is done. print solveArray to console
        System.out.println(solveArray);

        // remember the values are still in string and in order to do mathematical operation with val1 and val2 variable, we need to convert it into a number(float preferably)
        float val1Number = Integer.parseInt(val1);    // val1 converted and stored in val1Number variable
        float val2Number = Integer.parseInt(val2);    // val2 converted and stored in val2Number variable
        float result = 0;   // this variable will later contain our result

        // if sign == +. then we add val1Number + val2Number
        if (sign == "+"){
            result = val1Number + val2Number;

        // if sign == -. then we sub val1Number - val2Number
        } else if (sign == "-") {
            result = val1Number - val2Number;

        // if sign == /. then we div val1Number / val2Number
        } else if (sign == "/") {
            result = val1Number / val2Number;

        // if sign == X. then we mul val1Number X val2Number
        } else if (sign == "X") {
            result = val1Number * val2Number;

        }

        // print out: val1Number + " " + sign + " " + val2Number  e.g 120 + 20
        System.out.print(val1Number + " " + sign + " " + val2Number);
        // print result variable
        System.out.println("\nRESULT: " + result);

        // clear arrayList
        solveArray.clear();
    }
}
