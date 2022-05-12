package edu.hitsz.application;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MusicThreadLoop extends MusicThread{

    public MusicThreadLoop(String filename) {
        super(filename);
    }

    @Override
    public void play(InputStream source) {
        int size = (int) (audioFormat.getFrameSize() * audioFormat.getSampleRate());
        byte[] buffer = new byte[size];
        //源数据行SoureDataLine是可以写入数据的数据行
        SourceDataLine dataLine = null;
        //获取受数据行支持的音频格式DataLine.info
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        try {
            dataLine = (SourceDataLine) AudioSystem.getLine(info);
            dataLine.open(audioFormat, size);
        } catch (LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

//            System.out.println(2);
        dataLine.start();
//            System.out.println(3);
        try {
            int numBytesRead = 0;

            while (!this.isStopped){
                while (numBytesRead != -1 && !this.isStopped){
//                    System.out.println(3.5);
                    //从音频流读取指定的最大数量的数据字节，并将其放入缓冲区中
                    numBytesRead =
                            source.read(buffer, 0, buffer.length);
                    //通过此源数据行将数据写入混频器
                    if (numBytesRead != -1) {
                        dataLine.write(buffer, 0, numBytesRead);
                    }
                }
                if(this.isStopped){
                    break;
                }
                source = new ByteArrayInputStream(samples);
                numBytesRead = 0;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println(4);

        dataLine.drain();
        dataLine.close();
    }

    @Override
    public void run() {
        super.run();
    }
}
