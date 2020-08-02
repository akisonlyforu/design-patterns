package creational.factory;

public class MediaPlayerFactory {


  public MediaPlayer getMediaPlayer(String mediaPlayerType) {
    if ("VLC".equalsIgnoreCase(mediaPlayerType)) {
      return new VlcMediaPlayer();
    } else if ("WINDOWS".equalsIgnoreCase(mediaPlayerType)) {
      return new WindowsMediaPlayer();
    }
    return null;
  }
}