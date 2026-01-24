package chord.database.refactor;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.sound.sampled.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class SoundCreator {
    private int[] keyList;
    public SoundCreator(int[] keyList){
        this.keyList = keyList;
    }

    public void generateSound() throws IOException, UnsupportedAudioFileException {

        List<AudioInputStream> audioStreams = new ArrayList<>();
        AudioFormat format = null;
        long totalFrames = 0;

        // WAVs öffnen und prüfen
        for (int el : keyList) {
            Path file = Path.of("server/sound/" + el + ".wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(file.toFile());

            if (format == null) {
                format = ais.getFormat();
            } else {
                // Wichtig: Alle WAVs müssen gleiches Format haben (SampleRate, Channels, etc.)
                if (!format.matches(ais.getFormat())) {
                    ais.close();
                    throw new IllegalArgumentException("Unterschiedliches Audio-Format bei: " + file);
                }
            }

            totalFrames += ais.getFrameLength();
            audioStreams.add(ais);
        }

        // Streams hintereinander schalten
        Enumeration<AudioInputStream> en = Collections.enumeration(audioStreams);
        SequenceInputStream seq = new SequenceInputStream(new Enumeration<InputStream>() {
            @Override public boolean hasMoreElements() { return en.hasMoreElements(); }
            @Override public InputStream nextElement() { return en.nextElement(); }
        });

        // Gesamten Stream als AudioInputStream mit bekannter Länge bauen
        AudioInputStream appended = new AudioInputStream(seq, format, totalFrames);

        String outStr = Arrays.stream(keyList).mapToObj(String::valueOf)
                .collect(Collectors.joining("_"));

        outStr = "server/musicfiles/" + outStr + ".wav";

        Path out = Path.of(outStr);
        try {
            AudioSystem.write(appended, AudioFileFormat.Type.WAVE, out.toFile());
        } finally {
            // alles schließen
            appended.close();
            seq.close();
            for (AudioInputStream ais : audioStreams) {
                ais.close();
            }
        }
    }
}

/*
// Source - https://stackoverflow.com/a
// Posted by Sajan Parmar
// Retrieved 2026-01-24, License - CC BY-SA 3.0

String wavFile1 = "C:\\1.mp3";
String wavFile2 = "C:\\2.mp3";
FileInputStream fistream1 = new FileInputStream(wavFile1);  // first source file
FileInputStream fistream2 = new FileInputStream(wavFile2);//second source file
SequenceInputStream sistream = new SequenceInputStream(fistream1, fistream2);
FileOutputStream fostream = new FileOutputStream("D://merge1.mp3");//destinationfile

int temp;

while( ( temp = sistream.read() ) != -1)
{
    // System.out.print( (char) temp ); // to print at DOS prompt
    fostream.write(temp);   // to write to file
}
fostream.close();
sistream.close();
fistream1.close();
fistream2.close();


 */