package creational.factory;

public class FactoryDesignPatternDemo {

  public static void main(String[] args){
    MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();

    MediaPlayer vlc = mediaPlayerFactory.getMediaPlayer("VLC");
    vlc.playSong();

    MediaPlayer windows = mediaPlayerFactory.getMediaPlayer("WINDOWS");
    windows.playSong();
  }

}