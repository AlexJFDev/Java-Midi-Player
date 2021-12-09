import javax.sound.midi.*;

public class MidiFromArray{
    
    public static void main(String[] args){
        int instrumentNum = 2;

        MidiFromArray mini = new MidiFromArray();
        Integer[][] notes = {
            {instrumentNum, getC(2), 1, 2}, 
            {instrumentNum, getC(3), 5, 6}, 
            {instrumentNum, getE(3), 7, 8}, 
            {instrumentNum, getF(3), 9, 10}, 
            {instrumentNum, getG(3), 11, 12}, 
            {instrumentNum, getF(3), 13, 14}, 
            {instrumentNum, getE(3), 15, 16}, 
            {instrumentNum, getC(3), 17, 18}, 
            {instrumentNum, getB(3), 23, 24}, 
            {instrumentNum, getD(3), 25, 26}, 
            {instrumentNum, getC(3), 27, 28}};
        for(Integer[] noteData : notes){
            int instrument = noteData[0];
            int start = noteData[2];
            int stop = noteData[3];
            int note = noteData[1];
            System.out.println("Playing "+Integer.toString(instrument)+", "+Integer.toString(note));
            mini.play(instrument, note, start, stop);
        }
    }

    public void play(int instrument, int note, int start, int stop){
        try {
            Sequencer player = MidiSystem.getSequencer();
            player.open();
            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();

            //MidiEvent event = null;

            ShortMessage first = new ShortMessage();
            first.setMessage(192, 1, instrument, 0);
            MidiEvent changeInstrument = new MidiEvent(first, start);
            track.add(changeInstrument);

            ShortMessage a = new ShortMessage();
            a.setMessage(144, 1, note, 100);
            MidiEvent noteOn = new MidiEvent(a, start);
            track.add(noteOn);

            ShortMessage b = new ShortMessage();
            b.setMessage(128, 1, note, 100);
            MidiEvent noteOff = new MidiEvent(b, stop);
            track.add(noteOff);
            player.setSequence(seq);
            player.start();

        } catch (Exception ex) {ex.printStackTrace();}
    }

    public static int getC(int octave){
        return 0 + ((octave+1)*12);
    }

    public static int getCSharp(int octave){
        return 1 + ((octave+1)*12);
    }

    public static int getD(int octave){
        return 2 + ((octave+1)*12);
    }

    public static int getDSharp(int octave){
        return 3 + ((octave+1)*12);
    }

    public static int getE(int octave){
        return 4 + ((octave+1)*12);
    }

    public static int getF(int octave){
        return 5 + ((octave+1)*12);
    }

    public static int getFSharp(int octave){
        return 6 + ((octave+1)*12);
    }

    public static int getG(int octave){
        return 7 + ((octave+1)*12);
    }

    public static int getGSharp(int octave){
        return 8 + ((octave+1)*12);
    }

    public static int getA(int octave){
        return 9 + ((octave+1)*12);
    }

    public static int getASharp(int octave){
        return 10 + ((octave+1)*12);
    }

    public static int getB(int octave){
        return 11 + ((octave+1)*12);
    }
}
