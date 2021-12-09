import javax.sound.midi.*;

public class MidiMain {

    static final int OCTAVE = 6;
    static final Note[] NOTEARRAY = {
        new Note().setNote(OCTAVE, "c").setInstrument(1),
        new Note().setNote(OCTAVE, "c#").setInstrument(1),
        new Note().setNote(OCTAVE, "d").setInstrument(1),
        new Note().setNote(OCTAVE, "d#").setInstrument(1),
        new Note().setNote(OCTAVE, "e").setInstrument(1),
        new Note().setNote(OCTAVE, "f").setInstrument(1),
        new Note().setNote(OCTAVE, "f#").setInstrument(1),
        new Note().setNote(OCTAVE, "g").setInstrument(1),
        new Note().setNote(OCTAVE, "g#").setInstrument(1),
        new Note().setNote(OCTAVE, "a").setInstrument(1),
        new Note().setNote(OCTAVE, "a#").setInstrument(1),
        new Note().setNote(OCTAVE, "b").setInstrument(1),
    };
    
    public static void main(String[] args){
        

        try{
        Sequencer player = MidiSystem.getSequencer();
        player.open();
        Sequence seq = new Sequence(Sequence.PPQ, 4);
        Track track = seq.createTrack();
        
        long tickVal = 1;
        
        for(Note note : NOTEARRAY){
            note.queueNote(tickVal, tickVal+1, track);
            tickVal = tickVal + 2;
        }
        

        player.setSequence(seq);
        player.start();

        }catch (Exception ex) {ex.printStackTrace();}
    }
}
