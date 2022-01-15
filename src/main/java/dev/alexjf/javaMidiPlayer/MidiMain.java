package dev.alexjf.javaMidiPlayer;

import dev.alexjf.javaMidiPlayer.frame.Screen;

public final class MidiMain {

    private MidiMain() {}

    public static void main(String[] args) {
        Screen screen = new Screen();
        screen.start();
    }
}
