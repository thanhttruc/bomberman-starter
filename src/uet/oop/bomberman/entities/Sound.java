package uet.oop.bomberman.entities;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;

import static uet.oop.bomberman.BombermanGame.bomber;

public class Sound extends JFrame{
    public static Clip bomb_explosion;
    public static Clip bomber_die;
    public static Clip set_bomb;
    public static Clip move_left;
    public static Clip move_right;
    public static Clip move_up;
    public static Clip move_down;
    public static Clip SoundBomb;
    public static Clip main_game;
    public static Clip next_level;
    public static boolean is_SoundBomberDie;

    public Sound(String name, String sound) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            URL url = this.getClass().getClassLoader().getResource(name);
            assert  url != null;
            AudioInputStream audio_input = AudioSystem.getAudioInputStream(url);

            if (sound.equals("die")) {
                bomber_die = AudioSystem.getClip();
                bomber_die.open(audio_input);
                FloatControl gainControl = (FloatControl) bomber_die.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(+6.0206f);
                bomber_die.start();
            }
            if (sound.equals("setBomb")) {
                set_bomb = AudioSystem.getClip();
                set_bomb.open(audio_input);
                FloatControl gainControl = (FloatControl) set_bomb.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(+6.0206f);
                set_bomb.start();
            }
            if (sound.equals("explosion")) {
                bomb_explosion = AudioSystem.getClip();
                bomb_explosion.open(audio_input);
                FloatControl gainControl = (FloatControl) bomb_explosion.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-8.0f);
                bomb_explosion.start();
            }
            if (sound.equals("receiveItem")) {
                SoundBomb = AudioSystem.getClip();
                SoundBomb.open(audio_input);
                FloatControl gainControl = (FloatControl) SoundBomb.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-8.0f);
                SoundBomb.start();
            }
            if (sound.equals("left")) {
                move_left = AudioSystem.getClip();
                move_left.open(audio_input);
                FloatControl gainControl = (FloatControl) move_left.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-8.0f);
                move_left.start();
            }
            if (sound.equals("right")) {
                move_right = AudioSystem.getClip();
                move_right.open(audio_input);
                FloatControl gainControl = (FloatControl) move_right.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-8.0f);
                move_right.start();
            }
            if (sound.equals("up")) {
                move_up = AudioSystem.getClip();
                move_up.open(audio_input);
                FloatControl gainControl = (FloatControl) move_up.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-8.0f);
                move_up.start();
            }
            if (sound.equals("down")) {
                move_down = AudioSystem.getClip();
                move_down.open(audio_input);
                FloatControl gainControl = (FloatControl) move_down.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-8.0f);
                move_down.start();
            }
            if (sound.equals("main_game")) {
                main_game = AudioSystem.getClip();
                main_game.open(audio_input);
                FloatControl gainControl = (FloatControl) main_game.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-8.0f);
                main_game.loop(24);
            }
            if (sound.equals("win")) {
                next_level = AudioSystem.getClip();
                next_level.open(audio_input);
                FloatControl gainControl = (FloatControl) next_level.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-8.0f);
            }

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void updateSound() {
        if (!bomber.isLife()) {
            if (!is_SoundBomberDie) {
                new Sound("levels/SoundBomberDie.wav", "die");
                main_game.close();
                is_SoundBomberDie = true;
            }
        }
    }
}
