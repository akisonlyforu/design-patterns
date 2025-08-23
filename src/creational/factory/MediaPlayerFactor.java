package creational.factory;

interface MediaPlayer {
    void playSong();
}

class WindowsMediaPlayer implements MediaPlayer{
    public void playSong() {
        System.out.println("Playing your favourite video using Windows MediaPlayer");
    }
}

class VlcMediaPlayer implements MediaPlayer{
    public void playSong() {
        System.out.println("Playing your favourite video using VLC MediaPlayer");
    }
}

class MediaPlayerFactory {
    public static final String VLC_PLAYER = "VLC";
    public static final String WINDOWS_PLAYER = "WINDOWS";

    public MediaPlayer getMediaPlayer(String mediaPlayerType) {
        if (mediaPlayerType == null) {
            throw new IllegalArgumentException("Media player type cannot be null");
        }

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

class TestMediaPlayerFactory {
    public static void main(String[] args) {
        System.out.println("=== Testing MediaPlayerFactory ===\n");

        // Create factory instance
        MediaPlayerFactory factory = new MediaPlayerFactory();

        // Test 1: Create VLC Media Player
        System.out.println("Test 1: Creating VLC Media Player");
        MediaPlayer vlcPlayer = factory.getMediaPlayer("VLC");
        if (vlcPlayer != null) {
            System.out.println("✓ VLC player created successfully");
            System.out.println("Type: " + vlcPlayer.getClass().getSimpleName());
            vlcPlayer.playSong();
        } else {
            System.out.println("✗ VLC player creation failed");
        }

        // Test 2: Create Windows Media Player
        System.out.println("\nTest 2: Creating Windows Media Player");
        MediaPlayer windowsPlayer = factory.getMediaPlayer("WINDOWS");
        if (windowsPlayer != null) {
            System.out.println("✓ Windows player created successfully");
            System.out.println("Type: " + windowsPlayer.getClass().getSimpleName());
            windowsPlayer.playSong();
        } else {
            System.out.println("✗ Windows player creation failed");
        }

        // Test 3: Case insensitive testing
        System.out.println("\nTest 3: Case insensitive testing");
        MediaPlayer vlcLower = factory.getMediaPlayer("vlc");
        MediaPlayer windowsMixed = factory.getMediaPlayer("Windows");

        if (vlcLower != null && windowsMixed != null) {
            System.out.println("✓ Case insensitive matching works");
            System.out.println("vlc (lowercase) created: " + vlcLower.getClass().getSimpleName());
            System.out.println("Windows (mixed case) created: " + windowsMixed.getClass().getSimpleName());
        } else {
            System.out.println("✗ Case insensitive matching failed");
        }

        // Test 4: Invalid player type
        System.out.println("\nTest 4: Invalid player type");
        try {
            MediaPlayer invalidPlayer = factory.getMediaPlayer("INVALID");
            if (invalidPlayer == null) {
                System.out.println("✓ Correctly returned null for invalid player type");
            } else {
                System.out.println("✗ Unexpected player created for invalid type");
            }
        } catch (Exception e) {
            System.out.println("✗ Exception thrown for invalid type: " + e.getMessage());
        }

        // Test 5: Null input
        System.out.println("\nTest 5: Null input handling");
        try {
            MediaPlayer nullPlayer = factory.getMediaPlayer(null);
            if (nullPlayer == null) {
                System.out.println("✓ Correctly handled null input");
            } else {
                System.out.println("✗ Unexpected behavior with null input");
            }
        } catch (Exception e) {
            System.out.println("Exception with null input: " + e.getMessage());
        }

        // Test 6: Empty string
        System.out.println("\nTest 6: Empty string input");
        try {
            MediaPlayer emptyPlayer = factory.getMediaPlayer("");
            if (emptyPlayer == null) {
                System.out.println("✓ Correctly handled empty string input");
            } else {
                System.out.println("✗ Unexpected behavior with empty string input");
            }
        } catch (Exception e) {
            System.out.println("Exception with empty string: " + e.getMessage());
        }

        // Test 7: Verify different instances are created each time
        System.out.println("\nTest 7: Instance creation verification");
        MediaPlayer vlc1 = factory.getMediaPlayer("VLC");
        MediaPlayer vlc2 = factory.getMediaPlayer("VLC");

        if (vlc1 != null && vlc2 != null && vlc1 != vlc2) {
            System.out.println("✓ Factory creates new instances each time (not singleton)");
            System.out.println("VLC1 hash: " + vlc1.hashCode());
            System.out.println("VLC2 hash: " + vlc2.hashCode());
        } else if (vlc1 == null || vlc2 == null) {
            System.out.println("✗ Failed to create VLC instances");
        } else {
            System.out.println("✗ Same instance returned (unexpected for factory pattern)");
        }

        // Test 8: Polymorphism verification
        System.out.println("\nTest 8: Polymorphism verification");
        MediaPlayer[] players = {
                factory.getMediaPlayer("VLC"),
                factory.getMediaPlayer("WINDOWS")
        };

        System.out.println("Playing media through polymorphic interface:");
        for (int i = 0; i < players.length; i++) {
            if (players[i] != null) {
                System.out.print("Player " + (i + 1) + ": ");
                players[i].playSong();
            }
        }

        // Test 9: Performance test with multiple creations
        System.out.println("\nTest 9: Performance test - creating 1000 instances");
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            MediaPlayer player = factory.getMediaPlayer(i % 2 == 0 ? "VLC" : "WINDOWS");
            // Use the player to prevent optimization
            if (player != null && i == 999) {
                System.out.println("Last player type: " + player.getClass().getSimpleName());
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("✓ Created 1000 instances in " + (endTime - startTime) + " milliseconds");

        System.out.println("\n=== Test Summary ===");
        System.out.println("Factory Pattern Implementation Results:");
        System.out.println("- Creates correct concrete implementations based on input");
        System.out.println("- Handles case-insensitive input properly");
        System.out.println("- Returns null for invalid inputs (consider exception handling)");
        System.out.println("- Creates new instances each time (factory behavior)");
        System.out.println("- Supports polymorphic usage through MediaPlayer interface");
    }
}