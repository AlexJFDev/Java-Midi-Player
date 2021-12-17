package dev.alexjf.java_midi_player.frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

import dev.alexjf.java_midi_player.json.JSong;

import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class Screen extends JFrame implements ActionListener {
    static JLabel label;
    static JFileChooser fileChooser;

    public Screen(){
        
    }
    public void start(){
        fileChooser = new JFileChooser();
        JFrame frame = new JFrame("Java Midi Player");
        label = new JLabel("No file loaded");
        JButton fileChooserButton = new JButton("Select A File");
        Screen screen = new Screen();
        fileChooserButton.addActionListener(screen);
        JPanel panel = new JPanel();
        panel.add(fileChooserButton);
        panel.add(label);
        frame.add(panel);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent event)
    {
        String eventString = event.getActionCommand();
        if (eventString.equals("Select A File")){
            int returnVal = fileChooser.showOpenDialog(Screen.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File jSongFile = fileChooser.getSelectedFile();
                JSong jSong = new JSong(jSongFile);
                try {
                    Sequencer player = MidiSystem.getSequencer();
                    player.open();
                    player.setSequence(jSong.getJSongSequence());
                    player.start();
                    label.setText(jSongFile.getAbsolutePath()+" Loaded");
                } catch (MidiUnavailableException e) {
                    e.printStackTrace();
                } catch (InvalidMidiDataException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
