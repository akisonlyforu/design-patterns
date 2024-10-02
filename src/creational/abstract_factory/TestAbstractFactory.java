package creational.abstract_factory;

public class TestAbstractFactory {
    public static void main(String[] args) {
        System.out.println("=== Testing Abstract Factory Pattern ===\n");

        // Test 1: Windows Media Factory
        System.out.println("Test 1: Creating Windows Media Factory");
        try {
            MediaFactory windowsFactory = MediaFactoryProducer.getFactory("WINDOWS");
            System.out.println("✓ Windows factory created successfully");
            System.out.println("Factory type: " + windowsFactory.getClass().getSimpleName());

            // Create Windows family products
            AudioPlayer windowsAudio = windowsFactory.createAudioPlayer();
            VideoPlayer windowsVideo = windowsFactory.createVideoPlayer();

            System.out.println("Windows family products:");
            System.out.println("- Audio: " + windowsAudio.getClass().getSimpleName());
            System.out.println("- Video: " + windowsVideo.getClass().getSimpleName());

            // Test functionality
            windowsAudio.playSong();
            windowsVideo.playVideo();

        } catch (Exception e) {
            System.out.println("✗ Windows factory test failed: " + e.getMessage());
        }

        // Test 2: VLC Media Factory
        System.out.println("\nTest 2: Creating VLC Media Factory");
        try {
            MediaFactory vlcFactory = MediaFactoryProducer.getFactory("VLC");
            System.out.println("✓ VLC factory created successfully");
            System.out.println("Factory type: " + vlcFactory.getClass().getSimpleName());

            // Create VLC family products
            AudioPlayer vlcAudio = vlcFactory.createAudioPlayer();
            VideoPlayer vlcVideo = vlcFactory.createVideoPlayer();

            System.out.println("VLC family products:");
            System.out.println("- Audio: " + vlcAudio.getClass().getSimpleName());
            System.out.println("- Video: " + vlcVideo.getClass().getSimpleName());

            // Test functionality
            vlcAudio.playSong();
            vlcVideo.playVideo();

        } catch (Exception e) {
            System.out.println("✗ VLC factory test failed: " + e.getMessage());
        }

        // Test 3: Case insensitive factory creation
        System.out.println("\nTest 3: Case insensitive factory creation");
        try {
            MediaFactory windowsLower = MediaFactoryProducer.getFactory("windows");
            MediaFactory vlcMixed = MediaFactoryProducer.getFactory("Vlc");

            System.out.println("✓ Case insensitive matching works");
            System.out.println("'windows' created: " + windowsLower.getClass().getSimpleName());
            System.out.println("'Vlc' created: " + vlcMixed.getClass().getSimpleName());

        } catch (Exception e) {
            System.out.println("✗ Case insensitive test failed: " + e.getMessage());
        }

        // Test 4: Invalid factory type
        System.out.println("\nTest 4: Invalid factory type handling");
        try {
            MediaFactory invalidFactory = MediaFactoryProducer.getFactory("INVALID");
            System.out.println("✗ Should have thrown exception for invalid type");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Correctly handled invalid factory type");
            System.out.println("Exception: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ Unexpected exception: " + e.getMessage());
        }

        // Test 5: Null input handling
        System.out.println("\nTest 5: Null input handling");
        try {
            MediaFactory nullFactory = MediaFactoryProducer.getFactory(null);
            System.out.println("✗ Should have thrown exception for null input");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Correctly handled null input");
            System.out.println("Exception: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ Unexpected exception: " + e.getMessage());
        }

        // Test 6: Product family consistency
        System.out.println("\nTest 6: Product family consistency verification");
        testProductFamilyConsistency();

        // Test 7: Multiple factory instances
        System.out.println("\nTest 7: Multiple factory instances");
        MediaFactory factory1 = MediaFactoryProducer.getFactory("WINDOWS");
        MediaFactory factory2 = MediaFactoryProducer.getFactory("WINDOWS");

        if (factory1 != factory2) {
            System.out.println("✓ New factory instances created each time (not singleton)");
            System.out.println("Factory1 hash: " + factory1.hashCode());
            System.out.println("Factory2 hash: " + factory2.hashCode());
        } else {
            System.out.println("✗ Same factory instance returned");
        }

        // Test 8: Polymorphism through abstract factory
        System.out.println("\nTest 8: Polymorphism verification");
        testPolymorphism();

        System.out.println("\n=== Test Summary ===");
        System.out.println("Abstract Factory Pattern verified:");
        System.out.println("- Creates families of related products");
        System.out.println("- Ensures product compatibility within families");
        System.out.println("- Handles invalid input appropriately");
        System.out.println("- Supports polymorphic usage");
    }

    private static void testProductFamilyConsistency() {
        // Test Windows family consistency
        MediaFactory windowsFactory = MediaFactoryProducer.getFactory("WINDOWS");
        AudioPlayer windowsAudio = windowsFactory.createAudioPlayer();
        VideoPlayer windowsVideo = windowsFactory.createVideoPlayer();

        boolean windowsConsistency = windowsAudio.getClass().getSimpleName().startsWith("Windows") &&
                windowsVideo.getClass().getSimpleName().startsWith("Windows");

        // Test VLC family consistency
        MediaFactory vlcFactory = MediaFactoryProducer.getFactory("VLC");
        AudioPlayer vlcAudio = vlcFactory.createAudioPlayer();
        VideoPlayer vlcVideo = vlcFactory.createVideoPlayer();

        boolean vlcConsistency = vlcAudio.getClass().getSimpleName().startsWith("Vlc") &&
                vlcVideo.getClass().getSimpleName().startsWith("Vlc");

        if (windowsConsistency && vlcConsistency) {
            System.out.println("✓ Product family consistency maintained");
            System.out.println("Windows family: " + windowsAudio.getClass().getSimpleName() +
                    ", " + windowsVideo.getClass().getSimpleName());
            System.out.println("VLC family: " + vlcAudio.getClass().getSimpleName() +
                    ", " + vlcVideo.getClass().getSimpleName());
        } else {
            System.out.println("✗ Product family consistency violated");
        }
    }

    private static void testPolymorphism() {
        String[] factoryTypes = {"WINDOWS", "VLC"};

        for (String type : factoryTypes) {
            System.out.println("Testing " + type + " factory through polymorphism:");
            MediaFactory factory = MediaFactoryProducer.getFactory(type);

            // Use polymorphic references
            AudioPlayer audio = factory.createAudioPlayer();
            VideoPlayer video = factory.createVideoPlayer();

            System.out.print("  Audio: ");
            audio.playSong();
            System.out.print("  Video: ");
            video.playVideo();
        }
    }
}