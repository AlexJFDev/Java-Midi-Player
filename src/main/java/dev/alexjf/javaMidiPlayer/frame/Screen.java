package dev.alexjf.javaMidiPlayer.frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import dev.alexjf.javaMidiPlayer.json.JSong;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.io.File;
import java.awt.event.ActionEvent;

public class Screen extends JFrame implements ActionListener {
    private static JLabel label;
    private static JFileChooser fileChooser;
    public static AnimatedPanel animatedPanel;

    public Screen() {

    }

    public void start() {
        fileChooser = new JFileChooser();
        JFrame frame = new JFrame("Java Midi Player");
        frame.setDefaultCloseOperation(3);
        label = new JLabel("No file loaded");
        JButton fileChooserButton = new JButton("Select A File");
        Screen screen = new Screen();
        fileChooserButton.addActionListener(screen);
        JPanel contentPanel = new JPanel();
        JPanel panel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
        animatedPanel = new AnimatedPanel();
        animatedPanel.setBackground(Color.BLUE);
        animatedPanel.setBounds(0, 0, 300, 300);
        panel.add(fileChooserButton);
        panel.add(label);
        panel.setBackground(Color.GREEN);
        contentPanel.add(animatedPanel);
        contentPanel.add(panel);
        frame.add(contentPanel);
        frame.setSize(300, 300);
        frame.setContentPane(contentPanel);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        String eventString = event.getActionCommand();
        if ("Select A File".equals(eventString)) {
            int returnVal = fileChooser.showOpenDialog(Screen.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File jSongFile = fileChooser.getSelectedFile();
                JSong jSong = new JSong(jSongFile);
                try {
                    Sequencer player = MidiSystem.getSequencer();
                    player.open();
                    player.setSequence(jSong.getJSongSequence());
                    player.start();
                    label.setText(jSongFile.getAbsolutePath() + " Loaded");
                } catch (MidiUnavailableException e) {
                    e.printStackTrace();
                } catch (InvalidMidiDataException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public JLabel getLabel() {
        return label;
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }
}
