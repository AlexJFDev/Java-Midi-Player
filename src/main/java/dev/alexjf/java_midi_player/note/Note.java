package dev.alexjf.java_midi_player.note;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class Note {
    private int instrumentInt;
    private int octaveInt;
    private int noteInt;
    private long startTickLong;
    private long stopTickLong;

    private String noteString;

    private Track noteTrack;

    public Note(){
    }

    public Note(int instrumentInt2, int octaveInt2, String noteString2) {
        instrumentInt = instrumentInt2;
        octaveInt = octaveInt2;
        noteString = noteString2;

        this.setNoteInt(octaveInt, noteString);
    }

    public Note(int instrumentInt2, int octaveInt2, String noteString2, Track noteTrack2) {
        instrumentInt = instrumentInt2;
        octaveInt = octaveInt2;
        noteString = noteString2;
        noteTrack = noteTrack2;

        this.setNoteInt(octaveInt, noteString);
    }

    public Note(int instrumentInt2, int octaveInt2, String noteString2, long startTickLong2, long stopTickLong2) {
        instrumentInt = instrumentInt2;
        octaveInt = octaveInt2;
        noteString = noteString2;
        startTickLong = startTickLong2;
        stopTickLong = stopTickLong2;
        this.setNoteInt(octaveInt, noteString);
    }

    public Note(int instrumentInt2, int octaveInt2, String noteString2, Track noteTrack2, long startTickLong2, long stopTickLong2) {
        instrumentInt = instrumentInt2;
        octaveInt = octaveInt2;
        noteString = noteString2;
        noteTrack = noteTrack2;
        startTickLong = startTickLong2;
        stopTickLong = stopTickLong2;

        this.setNoteInt(octaveInt, noteString);
    }

    public Note setInstrumentInt(int instrumentNum) {
        instrumentInt = instrumentNum;
        return this;
    }

    public Note setOctaveInt(int octaveInt2) {
        octaveInt = octaveInt2;
        if (noteString != null) {
            setNoteInt(octaveInt, noteString);
        }
        return this;
    }

    public Note setNoteInt(int octaveInt2, String noteString2) {
        noteString2 = noteString2.toLowerCase();
        octaveInt2 = octaveInt2+1;
        switch (noteString2) {
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
            default: throw new IllegalArgumentException(noteString2 + " is not a valid note.");
        }
        return this;
    }

    public Note setNoteInt(int noteInt2) {
        noteInt = noteInt2;
        return this;
    }

    public Note setStartTickLong(long startTickLong2) {
        startTickLong = startTickLong2;
        return this;
    }

    public Note setStopTickLong(long stopTickLong2) {
        stopTickLong = stopTickLong2;
        return this;
    }

    public Note setNoteString(String noteString2) {
        noteString = noteString2;
        setNoteInt(octaveInt, noteString);
        return this;
    }

    public int getInstrumentInt() {
        return instrumentInt;
    }

    public int getOctaveInt() {
        return octaveInt;
    }

    public int getNoteInt() {
        return noteInt;
    }

    public long getStartTickLong() {
        return startTickLong;
    }

    public long getStopTickLong() {
        return stopTickLong;
    }

    public String getNoteString() {
        return noteString;
    }

    public void queueNote() {
        try {
            ShortMessage first = new ShortMessage();
            first.setMessage(192, 1, instrumentInt, 0);
            MidiEvent changeInstrument = new MidiEvent(first, startTickLong);
            noteTrack.add(changeInstrument);

            ShortMessage a = new ShortMessage();
            a.setMessage(144, 1, noteInt, 100);
            MidiEvent noteOn = new MidiEvent(a, startTickLong);
            noteTrack.add(noteOn);

            ShortMessage b = new ShortMessage();
            b.setMessage(128, 1, noteInt, 100);
            MidiEvent noteOff = new MidiEvent(b, stopTickLong);
            noteTrack.add(noteOff);
        } catch (InvalidMidiDataException ex) {
            ex.printStackTrace();
        }
    }

    public void queueNote(Track noteTrack2) {
        try {
            ShortMessage first = new ShortMessage();
            first.setMessage(192, 1, instrumentInt, 0);
            MidiEvent changeInstrument = new MidiEvent(first, startTickLong);
            noteTrack2.add(changeInstrument);

            ShortMessage a = new ShortMessage();
            a.setMessage(144, 1, noteInt, 100);
            MidiEvent noteOn = new MidiEvent(a, startTickLong);
            noteTrack2.add(noteOn);

            ShortMessage b = new ShortMessage();
            b.setMessage(128, 1, noteInt, 100);
            MidiEvent noteOff = new MidiEvent(b, stopTickLong);
            noteTrack2.add(noteOff);
        } catch (InvalidMidiDataException ex) {
            ex.printStackTrace();
        }
    }

    public void queueNote(long startTickLong2, long stopTickLong2) {
        try {
            ShortMessage first = new ShortMessage();
            first.setMessage(192, 1, instrumentInt, 0);
            MidiEvent changeInstrument = new MidiEvent(first, startTickLong2);
            noteTrack.add(changeInstrument);

            ShortMessage a = new ShortMessage();
            a.setMessage(144, 1, noteInt, 100);
            MidiEvent noteOn = new MidiEvent(a, startTickLong2);
            noteTrack.add(noteOn);

            ShortMessage b = new ShortMessage();
            b.setMessage(128, 1, noteInt, 100);
            MidiEvent noteOff = new MidiEvent(b, stopTickLong2);
            noteTrack.add(noteOff);
        } catch (InvalidMidiDataException ex) {
            ex.printStackTrace();
        }
    }

    public void queueNote(Track noteTrack2, long startTickLong2, long stopTickLong2) {
        try {
            ShortMessage first = new ShortMessage();
            first.setMessage(192, 1, instrumentInt, 0);
            MidiEvent changeInstrument = new MidiEvent(first, startTickLong2);
            noteTrack2.add(changeInstrument);

            ShortMessage a = new ShortMessage();
            a.setMessage(144, 1, noteInt, 100);
            MidiEvent noteOn = new MidiEvent(a, startTickLong2);
            noteTrack2.add(noteOn);

            ShortMessage b = new ShortMessage();
            b.setMessage(128, 1, noteInt, 100);
            MidiEvent noteOff = new MidiEvent(b, stopTickLong2);
            noteTrack2.add(noteOff);
        } catch (InvalidMidiDataException ex) {
            ex.printStackTrace();
        }
    }
}
