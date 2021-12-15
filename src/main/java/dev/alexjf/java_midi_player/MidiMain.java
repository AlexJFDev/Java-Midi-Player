package dev.alexjf.java_midi_player;

import dev.alexjf.java_midi_player.json.JSong;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import javax.swing.JFrame;

public final class MidiMain {

    private MidiMain() {}
    
    public static void main(String[] args) {
        /*JFrame frame = new JFrame();
        frame.setSize(300, 300);
        frame.setVisible(true);*/
        String jSONLocationString = "";
        try{
            jSONLocationString = args[0];
        } catch(java.lang.ArrayIndexOutOfBoundsException ex) {
            System.out.println("Please pass an argument for the location of the song.json file. Do not include the file extension.");
            ex.printStackTrace();
            //System.exit(0);
            jSONLocationString = "songTest";
        }

        
        JSong jSong = new JSong(jSONLocationString+".json");
        try {
            Sequencer player = MidiSystem.getSequencer();
            player.open();
            player.setSequence(jSong.getJSongSequence());
            player.start();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
}
