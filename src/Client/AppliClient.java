package Client; /**
 * Created by user on 15/03/2016.
 */

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class AppliClient {

    public static void main(String[] args) {

        JTextField xField = new JTextField(10);
        JTextField yField = new JTextField(10);
        JTextField zField = new JTextField(10);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("ip:"));
        myPanel.add(xField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("port:"));
        myPanel.add(yField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("pseudo:"));
        myPanel.add(zField);

        int result = JOptionPane.showConfirmDialog(null, myPanel, "Connexion", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {

            String ip = xField.getText();
            int port = Integer.parseInt(yField.getText());
            String nom = zField.getText();
            Socket socket;

            try {
                socket = new Socket(ip, port);
                new Joueur(socket);

            } catch (UnknownHostException e) {

                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }

    }
}
