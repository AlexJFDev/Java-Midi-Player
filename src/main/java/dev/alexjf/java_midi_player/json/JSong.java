package dev.alexjf.java_midi_player.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;

import org.json.JSONObject;
import org.json.JSONTokener;

import dev.alexjf.java_midi_player.note.Note;

public class JSong {

    private InputStream jSongInputStream;

    private Sequence jSongSequence;
    private String jSongDivisionTypeString;
    private float jSongDivisionTypeFloat;
    private int jSongResolutionInt;

    private int jSongTrackLengthInt;

    public JSong(String jSongLocationString) {
        try {
            jSongInputStream = new FileInputStream(jSongLocationString);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        loadJSong();
    }

    public JSong(File jSongFile) {
        try {
            jSongInputStream = new FileInputStream(jSongFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        loadJSong();
    }

    public Sequence getJSongSequence(){
        return jSongSequence;
    }

    private void loadJSong(){
        try {
            JSONTokener jSongTokener = new JSONTokener(jSongInputStream);
            JSONObject jSongJSONObject = new JSONObject(jSongTokener);

            jSongDivisionTypeString = jSongJSONObject.getJSONObject("meta").getString("timing type");
            switch (jSongDivisionTypeString){
            case "ppq": jSongDivisionTypeFloat = Sequence.PPQ;
                        break;
            case "smpte_24": jSongDivisionTypeFloat = Sequence.SMPTE_24;
                             break;
            case "smpte_25": jSongDivisionTypeFloat = Sequence.SMPTE_25;
                             break;
            case "smpte_30_drop": jSongDivisionTypeFloat = Sequence.SMPTE_30DROP;
                                  break;
            case "smpte_30": jSongDivisionTypeFloat = Sequence.SMPTE_30;
                             break;
            default: jSongDivisionTypeFloat = Sequence.PPQ; //this should throw an error though I'm not sure which one.
        }

        jSongResolutionInt = jSongJSONObject.getJSONObject("meta").getNumber("resolution").intValue();
        jSongSequence = new Sequence(jSongDivisionTypeFloat, jSongResolutionInt);

        jSongTrackLengthInt = jSongJSONObject.getJSONArray("tracks").length();
        for(int jSongTrackNumberInt = 0; jSongTrackNumberInt < jSongTrackLengthInt; jSongTrackNumberInt++){
            Track track = jSongSequence.createTrack();
            int trackLengthInt = jSongJSONObject.getJSONArray("tracks").getJSONArray(jSongTrackNumberInt).length() - 1;
            for(int trackNoteNumberInt = 0; trackNoteNumberInt < trackLengthInt; trackNoteNumberInt++){
                int trackNoteInstrumentInt = jSongJSONObject.getJSONArray("tracks").getJSONArray(jSongTrackNumberInt).getJSONArray(trackNoteNumberInt).getInt(0);
                int trackNoteOctaveInt = jSongJSONObject.getJSONArray("tracks").getJSONArray(jSongTrackNumberInt).getJSONArray(trackNoteNumberInt).getInt(1);
                String trackNoteNoteString = jSongJSONObject.getJSONArray("tracks").getJSONArray(jSongTrackNumberInt).getJSONArray(trackNoteNumberInt).getString(2); //This is the letter representation of the note eg A, A#, B, etc. I should rename the note class.
                Long trackNoteStartTickLong = jSongJSONObject.getJSONArray("tracks").getJSONArray(jSongTrackNumberInt).getJSONArray(trackNoteNumberInt).getLong(3);
                Long trackNoteStopTickLong = jSongJSONObject.getJSONArray("tracks").getJSONArray(jSongTrackNumberInt).getJSONArray(trackNoteNumberInt).getLong(4);

                Note trackNote = new Note(trackNoteInstrumentInt, trackNoteOctaveInt, trackNoteNoteString, track, trackNoteStartTickLong, trackNoteStopTickLong);
                trackNote.queueNote();
            }
        }
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
}
