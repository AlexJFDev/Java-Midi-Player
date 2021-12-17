package dev.alexjf.java_midi_player;

import dev.alexjf.java_midi_player.frame.Screen;

public final class MidiMain {

    private MidiMain() {}
    
    public static void main(String[] args) {
        Screen screen = new Screen();
        screen.start();
    }
}
