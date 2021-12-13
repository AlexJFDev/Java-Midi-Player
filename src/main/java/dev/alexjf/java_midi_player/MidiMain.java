package dev.alexjf.java_midi_player;

import dev.alexjf.java_midi_player.instrument.Instruments;
import dev.alexjf.java_midi_player.note.Note;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Track;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public final class MidiMain {
    static final int OCTAVE = 6;
    static final int INSTRUMENT = Instruments.BANJO;
    static final Note[] NOTE_ARRAY = {
        new Note(INSTRUMENT, OCTAVE, "c"),
        new Note(INSTRUMENT, OCTAVE, "c#"),
        new Note(INSTRUMENT, OCTAVE, "d"),
        new Note(INSTRUMENT, OCTAVE, "d#"),
        new Note(INSTRUMENT, OCTAVE, "e"),
        new Note(INSTRUMENT, OCTAVE, "f"),
        new Note(INSTRUMENT, OCTAVE, "f#"),
        new Note(INSTRUMENT, OCTAVE, "g"),
        new Note(INSTRUMENT, OCTAVE, "g#"),
        new Note(INSTRUMENT, OCTAVE, "a"),
        new Note(INSTRUMENT, OCTAVE, "a#"),
        new Note(INSTRUMENT, OCTAVE, "b"),
    };

    private MidiMain() {}

    public static void main(String[] args) {
        String jSONLocationString = "";
        try{
            jSONLocationString = args[0];
        } catch(java.lang.ArrayIndexOutOfBoundsException ex) {
            System.out.println("Please pass an argument for the location of the song.json file.");
            ex.printStackTrace();
            System.exit(0);
        }
        try {
            InputStream jSONFileInputStream = new FileInputStream(jSONLocationString);
            JSONTokener jSONFileTokener = new JSONTokener(jSONFileInputStream);
            JSONObject jSONFileObject = new JSONObject(jSONFileTokener);
            System.out.println(jSONFileObject);
            System.out.println(Long.parseLong(jSONFileObject.getJSONArray("tracks").getJSONArray(0).getJSONArray(0).get(0).toString()) + 1);

            Sequencer player = MidiSystem.getSequencer();
            player.open();
            Sequence seq = new Sequence(Sequence.PPQ, 4);
            
            for(Object trackArray : jSONFileObject.getJSONArray("tracks")){

            }
            Track track = seq.createTrack();

            long tickVal = 0;
            for (Note note : NOTE_ARRAY) {
                note.queueNote(track, tickVal, tickVal + 3);
                tickVal = tickVal + 4;
            }

            /*long tickVal = 0;
            Note note = new Note(INSTRUMENT, OCTAVE, "c");
            for(int i = 0; i < 128; i++){
                note.setInstrumentInt(i);
                note.queueNote(track, tickVal, tickVal + 3);
                tickVal = tickVal + 4;
            }*/

            player.setSequence(seq);
            player.start();

        } catch (MidiUnavailableException ex) {
            ex.printStackTrace();
        } catch (InvalidMidiDataException ex) {
            ex.printStackTrace();
        } catch(FileNotFoundException ex){
            System.out.println("The file "+jSONLocationString+" was not found");
            ex.printStackTrace();
            System.exit(0);
        }
    }
}
