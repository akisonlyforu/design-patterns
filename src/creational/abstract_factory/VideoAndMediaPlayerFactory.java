package creational.abstract_factory;

// Abstract products - define interfaces for product families
interface AudioPlayer {
    void playSong();
}

interface VideoPlayer {
    void playVideo();
}

// Concrete products for Windows family
class WindowsAudioPlayer implements AudioPlayer {
    public void playSong() {
        System.out.println("Playing audio using Windows Media Player");
    }
}

class WindowsVideoPlayer implements VideoPlayer {
    public void playVideo() {
        System.out.println("Playing video using Windows Media Player");
    }
}

// Concrete products for VLC family
class VlcAudioPlayer implements AudioPlayer {
    public void playSong() {
        System.out.println("Playing audio using VLC Media Player");
    }
}

class VlcVideoPlayer implements VideoPlayer {
    public void playVideo() {
        System.out.println("Playing video using VLC Media Player");
    }
}

// Abstract factory - defines interface for creating product families
abstract class MediaFactory {
    public abstract AudioPlayer createAudioPlayer();
    public abstract VideoPlayer createVideoPlayer();
}

// Concrete factory for Windows family
class WindowsMediaFactory extends MediaFactory {
    public AudioPlayer createAudioPlayer() {
        return new WindowsAudioPlayer();
    }

    public VideoPlayer createVideoPlayer() {
        return new WindowsVideoPlayer();
    }
}

// Concrete factory for VLC family
class VlcMediaFactory extends MediaFactory {
    public AudioPlayer createAudioPlayer() {
        return new VlcAudioPlayer();
    }

    public VideoPlayer createVideoPlayer() {
        return new VlcVideoPlayer();
    }
}

// Factory producer - creates appropriate concrete factory
class MediaFactoryProducer {
    // Constants for supported factory types
    public static final String WINDOWS_FACTORY = "WINDOWS";
    public static final String VLC_FACTORY = "VLC";

    // Factory method - returns appropriate factory based on type
    public static MediaFactory getFactory(String factoryType) {
        if (factoryType == null) {
            throw new IllegalArgumentException("Factory type cannot be null");
        }

        // Case-insensitive matching
        switch (factoryType.toUpperCase()) {
            case WINDOWS_FACTORY:
                return new WindowsMediaFactory();
            case VLC_FACTORY:
                return new VlcMediaFactory();
            default:
                throw new IllegalArgumentException("Unknown factory type: " + factoryType);
        }
    }
}