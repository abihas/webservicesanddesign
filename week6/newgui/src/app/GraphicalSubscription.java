package app;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.jms.JMSException;

public class GraphicalSubscription {

  private Frame mainFrame;
  private Label headerLabel;
  private Label statusLabel;
  private Panel controlPanel;

  public GraphicalSubscription() {
    prepareGUI();
  }

  public static void main(String[] args){
    GraphicalSubscription gui = new GraphicalSubscription();
    gui.showTextFieldDemo();
  }

  private void prepareGUI(){
    mainFrame = new Frame("Awesome aanmeldscherm");

    mainFrame.setSize(400,400);
    mainFrame.setLayout(new GridLayout(3,1));

    mainFrame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent windowEvent){
        System.exit(0);
      }
    });

    headerLabel = new Label();
    headerLabel.setAlignment(Label.CENTER);
    statusLabel = new Label();
    statusLabel.setAlignment(Label.CENTER);
    statusLabel.setSize(350,100);

    controlPanel = new Panel();
    controlPanel.setLayout(new GridLayout(3,2));

    mainFrame.add(headerLabel);
    mainFrame.add(controlPanel);
    mainFrame.add(statusLabel);
  }

  private void showTextFieldDemo(){
    headerLabel.setText("Aanmelden voor de Hanze chat service");

    Label namelabel= new Label("Gebruikersnaam: ", Label.LEFT);
    Label passwordLabel = new Label("Wachtwoord: ", Label.LEFT);

    final TextField userText = new TextField(12);
    final TextField passwordText = new TextField(12);

    passwordText.setEchoChar('*');
    Button loginButton = new Button("Login");

    loginButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        try {
          UserPublisher.sendPerson(userText.getText(), passwordText.getText());
          statusLabel.setText("De gegevens zijn in de wachtrij gezet.");
        } catch (JMSException ex) {
          statusLabel.setText("Er is iets fout gegaan. Check de console.");
          ex.printStackTrace();
        }
      }
    });

    controlPanel.add(namelabel);
    controlPanel.add(userText);
    controlPanel.add(passwordLabel);
    controlPanel.add(passwordText);
    controlPanel.add(loginButton);

    mainFrame.setVisible(true);
  }
}
