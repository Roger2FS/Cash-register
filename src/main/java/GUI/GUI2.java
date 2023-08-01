package GUI;

import Bussiness.SimulationManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
public class GUI2 extends JFrame {
    private JPanel panel1;
    private JLabel tSim;
    private JLabel minAriv;
    private JTextField minArivTxt;
    private JLabel maxAriv;
    private JTextField maxArivTxt;
    private JLabel minService;
    private JLabel maxService;
    private JLabel clientiLabel;
    private JLabel coziLabel;
    private JLabel simulareLabel;
    private JTextField minServiceTxt;
    private JTextField maxSericeTxt;
    private JTextField clientiTxt;
    private JTextField coziTxt;
    private JTextField tSimTxt;
    private JButton startButton;
    private JTextArea rez;
    public int timeLimit;
    public int minArivTime;
    public int maxArivTime;
    public int minServiceTime;
    public int maxServiceTime;
    public int nrClienti;
    public int nrCozi;

    public GUI2() {
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tSim = tSimTxt.getText();
                timeLimit = Integer.parseInt(tSim);
                String minAriv = minArivTxt.getText();
                minArivTime = Integer.parseInt(minAriv);
                String maxAriv = maxArivTxt.getText();
                maxArivTime = Integer.parseInt(maxAriv);
                String minStime = minServiceTxt.getText();
                minServiceTime = Integer.parseInt(minStime);
                String maxStime = maxSericeTxt.getText();
                maxServiceTime = Integer.parseInt(maxStime);
                String clienti = clientiTxt.getText();
                nrClienti = Integer.parseInt(clienti);
                String cozi = coziTxt.getText();
                nrCozi = Integer.parseInt(cozi);

                SimulationManager simulationManager = new SimulationManager(timeLimit, minArivTime, maxArivTime, minServiceTime, maxServiceTime, nrClienti, nrCozi, rez);
                Thread t = new Thread(simulationManager);
                t.start();
            }
        });
    }

    public int[] getDate() {
        int[] array = {timeLimit, minArivTime, maxArivTime, minServiceTime, maxServiceTime, nrClienti, nrCozi};
        return array;
    }

    public static void main(String[] args) {
        GUI2 gui = new GUI2();
        gui.setContentPane(gui.panel1);

        gui.startButton.setBackground(Color.GREEN);
        gui.startButton.setForeground(Color.WHITE);
        Font font = new Font("Arial", Font.BOLD, 14);
        gui.startButton.setFont(font);

        gui.tSim.setForeground(Color.BLUE);
        gui.minAriv.setForeground(Color.BLUE);
        gui.maxAriv.setForeground(Color.BLUE);
        gui.minService.setForeground(Color.BLUE);
        gui.maxService.setForeground(Color.BLUE);
        gui.clientiLabel.setForeground(Color.BLUE);
        gui.coziLabel.setForeground(Color.BLUE);
        gui.simulareLabel.setForeground(Color.BLUE);

        String imagePath = "path/to/your/image.jpg";
        File imageFile = new File(imagePath);
        if (imageFile.exists()) {
            ImageIcon imageIcon = new ImageIcon(imagePath);
            JLabel backgroundLabel = new JLabel(imageIcon);
            backgroundLabel.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
            gui.panel1.add(backgroundLabel);
        }

        gui.setTitle("SimulatorCozi");
        gui.setSize(400, 400);
        gui.setVisible(true);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
