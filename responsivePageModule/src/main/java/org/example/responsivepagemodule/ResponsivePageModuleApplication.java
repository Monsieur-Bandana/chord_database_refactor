package org.example.responsivepagemodule;

import chord.database.remaster.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
public class ResponsivePageModuleApplication {
    LanguageHelper languageHelper;
    String language;
    private static final Logger log = LoggerFactory.getLogger(ResponsivePageModuleApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ResponsivePageModuleApplication.class, args);
	}

    private void setLanguage(String languagei){
        language = languagei;
        languageHelper = new LanguageHelper(language);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/training")
    public String language(@RequestParam(value = "language", defaultValue = "german") String languagei) throws Exception {

        setLanguage(languagei);
        log.info("language={} ladder={}", language, Arrays.toString(languageHelper.getBasicLadder()));
        Chord chord = generateRandomChord();
        return getPiano(chord);
    }

    private Chord generateRandomChord() throws Exception {
        // create Random basetone
        Random random = new Random();

        String[] scale = languageHelper.getBasicLadder();
        int basetoneIndex = random.nextInt(scale.length); // 0 bis 8
        String basetone = scale[basetoneIndex];
        // select Random chordtype
        int chordtype = random.nextInt(RuleSet.allHarmonies.length);
        Harmony harmony = RuleSet.allHarmonies[chordtype];
        return new ChordCreator(basetone, harmony, language).getChord();
    }

    private String getPiano(Chord chord) throws Exception {
        return new HTMLPiano(language, languageHelper.getLongLadder()).buildChordPiano(chord);
    }

    private String getTextualName(Chord chord){
        return chord.chordname;
    }


    private String getSound(Chord chord){
        try (AudioInputStream ais = new SoundCreator(chord.numericTones).generateSound()) {
            // lesen / senden / speichern
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "";
    }

}
