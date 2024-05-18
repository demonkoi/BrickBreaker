package com.mycompany.brickbreaker;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sounds {
    public static synchronized void playSound(final String url) {
 new Thread(new Runnable() {
  public void run() {
   try {
    Clip clip = AudioSystem.getClip();
    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
        getClass().getClassLoader().getResourceAsStream(url));
    clip.open(inputStream);
    clip.start(); 
   } catch (Exception e) {
    System.err.println("Error playing sound.");
   }
  }
 }).start();
}
}
