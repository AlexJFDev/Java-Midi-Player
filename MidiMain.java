import javax.sound.midi.*;

import instrument.Instruments;
import note.Note;

public class MidiMain {
    static final int OCTAVE = 6;
    static final int INSTRUMENT = Instruments.BANJO;
    static final Note[] NOTE_ARRAY = {
        new Note().setNoteString("c").setInstrumentInt(INSTRUMENT).setOctaveInt(OCTAVE),
        new Note().setNoteString("c#").setInstrumentInt(INSTRUMENT).setOctaveInt(OCTAVE),
        new Note().setNoteString("d").setInstrumentInt(INSTRUMENT).setOctaveInt(OCTAVE),
        new Note().setNoteString("d#").setInstrumentInt(INSTRUMENT).setOctaveInt(OCTAVE),
        new Note().setNoteString("e").setInstrumentInt(INSTRUMENT).setOctaveInt(OCTAVE),
        new Note().setNoteString("f").setInstrumentInt(INSTRUMENT).setOctaveInt(OCTAVE),
        new Note().setNoteString("f#").setInstrumentInt(INSTRUMENT).setOctaveInt(OCTAVE),
        new Note().setNoteString("g").setInstrumentInt(INSTRUMENT).setOctaveInt(OCTAVE),
        new Note().setNoteString("g#").setInstrumentInt(INSTRUMENT).setOctaveInt(OCTAVE),
        new Note().setNoteString("a").setInstrumentInt(INSTRUMENT).setOctaveInt(OCTAVE),
        new Note().setNoteString("a#").setInstrumentInt(INSTRUMENT).setOctaveInt(OCTAVE),
        new Note().setNoteString("b").setInstrumentInt(INSTRUMENT).setOctaveInt(OCTAVE),
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
    
    public static void main(String[] args){
        try{
        Sequencer player = MidiSystem.getSequencer();
        player.open();
        Sequence seq = new Sequence(Sequence.PPQ, 4);
        Track track = seq.createTrack();

        /*long tickVal = 1;
        Note note = new Note(INSTRUMENT, OCTAVE, "c");

        for(int i = 0; i < 128; i++){
            note.setInstrumentInt(i);
            note.queueNote(tickVal, tickVal+1, track);
            tickVal += 2;
        }*/

        long tickVal = 0;
        
        for(Note note : NOTE_ARRAY){
            note.queueNote(track, tickVal, tickVal+3);
            tickVal = tickVal + 4;
        }
        
        player.setSequence(seq);
        player.start();

        }catch (Exception ex) {ex.printStackTrace();}
    }
}
