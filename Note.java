import javax.sound.midi.*;

public class Note {
    public static int instrument;
    public static int noteNum;
    public static String noteString;

    public Note setNote(int octave, String noteType){
        noteString = noteType;
        noteType = noteType.toLowerCase();
        octave = octave + 1;
        switch (noteType){
            case "c": noteNum = 0 + 12 * octave;
                      break;
            case "c#": noteNum = 1 + 12 * octave;
                       break;
            case "d": noteNum = 2 + 12 * octave;
                      break;
            case "d#": noteNum = 3 + 12 * octave;
                       break;
            case "e": noteNum = 4 + 12 * octave;
                      break;
            case "f": noteNum = 5 + 12 * octave;
                      break;
            case "f#": noteNum = 6 + 12 * octave;
                       break;
            case "g": noteNum = 7 + 12 * octave;
                      break;
            case "g#": noteNum = 8 + 12 * octave;
                       break;
            case "a": noteNum = 9 + 12 * octave;
                      break;
            case "a#": noteNum = 10 + 12 * octave;
                       break;
            case "b": noteNum = 11 + 12 * octave;
                      break;
            default: throw new IllegalArgumentException(noteType+" is not a valid note.");
        }
        return this;
    }

    public Note setInstrument(int instrumentNum){
        instrument = instrumentNum;
        return this;
    }

    public void queueNote(long tickVal, long tickVal2, Track track){
        try{
            ShortMessage first = new ShortMessage();
            first.setMessage(192, 1, instrument, 0);
            MidiEvent changeInstrument = new MidiEvent(first, tickVal);
            track.add(changeInstrument);

            ShortMessage a = new ShortMessage();
            a.setMessage(144, 1, noteNum, 100);
            MidiEvent noteOn = new MidiEvent(a, tickVal);
            track.add(noteOn);

            ShortMessage b = new ShortMessage();
            b.setMessage(128, 1, noteNum, 100);
            MidiEvent noteOff = new MidiEvent(b, tickVal2);
            track.add(noteOff);
        } catch (Exception ex) {ex.printStackTrace();}
    }
}
