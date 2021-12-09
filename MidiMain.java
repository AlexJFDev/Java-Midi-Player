import javax.sound.midi.*;

public class MidiMain {

    static final int OCTAVE = 6;
    static final int INSTRUMENT = Instruments.ACOUSTIC_GRAND_PIANO;
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
    
    public static void main(String[] args){
        try{
        Sequencer player = MidiSystem.getSequencer();
        player.open();
        Sequence seq = new Sequence(Sequence.PPQ, 4);
        Track track = seq.createTrack();
        
        long tickVal = 1;
        
        for(Note note : NOTE_ARRAY){
            note.queueNote(tickVal, tickVal+1, track);
            tickVal = tickVal + 2;
        }
        
        player.setSequence(seq);
        player.start();

        }catch (Exception ex) {ex.printStackTrace();}
    }
}
