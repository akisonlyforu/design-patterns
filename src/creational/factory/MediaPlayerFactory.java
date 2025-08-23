package creational.factory;

// Product interface - defines common behavior for all media players
interface MediaPlayer {
    void playSong();
}

// Concrete product - Windows implementation
class WindowsMediaPlayer implements MediaPlayer{
    public void playSong() {
        System.out.println("Playing your favourite video using Windows MediaPlayer");
    }
}

// Concrete product - VLC implementation
class VlcMediaPlayer implements MediaPlayer{
    public void playSong() {
        System.out.println("Playing your favourite video using VLC MediaPlayer");
    }
}

public class MediaPlayerFactory {
    // Constants for supported player types
    public static final String VLC_PLAYER = "VLC";
    public static final String WINDOWS_PLAYER = "WINDOWS";

    public MediaPlayer getMediaPlayer(String mediaPlayerType) {
        if (mediaPlayerType == null) {
            throw new IllegalArgumentException("Media player type cannot be null");
        }

        // Case-insensitive matching using switch
        switch (mediaPlayerType.toUpperCase()) {
            case VLC_PLAYER:
                return new VlcMediaPlayer();
            case WINDOWS_PLAYER:
                return new WindowsMediaPlayer();
            default:
                throw new IllegalArgumentException("Unknown media player type: " + mediaPlayerType);
        }
    }
}