import javax.sound.midi.*;

public class Note {
    private int instrumentInt;
    private int octaveInt;
    private int noteInt;
    //public static int startTickInt;
    //public static int stopTickInt;

    private String noteString;

    public Note(int instrumentInt2, int octaveInt2, String noteString2) {
        instrumentInt = instrumentInt2;
        octaveInt = octaveInt2;
        noteString = noteString2;

        this.setNoteInt(octaveInt, noteString);
    }

    public Note setNoteInt(int octaveInt2, String noteString2){
        noteString2 = noteString2.toLowerCase();
        octaveInt2 = octaveInt2 ++;
        switch (noteString2){
            case "c": noteInt = 0 + 12 * octaveInt2;
                      break;
            case "c#": noteInt = 1 + 12 * octaveInt2;
                       break;
            case "d": noteInt = 2 + 12 * octaveInt2;
                      break;
            case "d#": noteInt = 3 + 12 * octaveInt2;
                       break;
            case "e": noteInt = 4 + 12 * octaveInt2;
                      break;
            case "f": noteInt = 5 + 12 * octaveInt2;
                      break;
            case "f#": noteInt = 6 + 12 * octaveInt2;
                       break;
            case "g": noteInt = 7 + 12 * octaveInt2;
                      break;
            case "g#": noteInt = 8 + 12 * octaveInt2;
                       break;
            case "a": noteInt = 9 + 12 * octaveInt2;
                      break;
            case "a#": noteInt = 10 + 12 * octaveInt2;
                       break;
            case "b": noteInt = 11 + 12 * octaveInt2;
                      break;
            default: throw new IllegalArgumentException(noteString2+" is not a valid note.");
        }
        return this;
    }

    public Note setInstrument(int instrumentNum){
        instrumentInt = instrumentNum;
        return this;
    }

    public void queueNote(long startTickLong, long stopTickLong, Track track){
        try{
            ShortMessage first = new ShortMessage();
            first.setMessage(192, 1, instrumentInt, 0);
            MidiEvent changeInstrument = new MidiEvent(first, startTickLong);
            track.add(changeInstrument);

            ShortMessage a = new ShortMessage();
            a.setMessage(144, 1, noteInt, 100);
            MidiEvent noteOn = new MidiEvent(a, startTickLong);
            track.add(noteOn);

            ShortMessage b = new ShortMessage();
            b.setMessage(128, 1, noteInt, 100);
            MidiEvent noteOff = new MidiEvent(b, stopTickLong);
            track.add(noteOff);
        } catch (Exception ex) {ex.printStackTrace();}
    }
}
