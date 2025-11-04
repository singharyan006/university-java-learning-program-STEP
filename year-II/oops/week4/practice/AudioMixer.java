public class AudioMixer {
    private String mixerModel;
    private int numberOfChannels;
    private boolean hasBluetoothConnectivity;
    private double maxVolumeDecibels;
    private String[] connectedDevices;
    private int deviceCount;

    // No-argument constructor using this() chaining
    public AudioMixer() {
        this("StandardMix-8", 8, false);
    }

    // Two-parameter constructor using this() chaining
    public AudioMixer(String mixerModel, int numberOfChannels) {
        this(mixerModel, numberOfChannels, false);
    }

    // Three-parameter constructor using this() chaining
    public AudioMixer(String mixerModel, int numberOfChannels, boolean hasBluetoothConnectivity) {
        this(mixerModel, numberOfChannels, hasBluetoothConnectivity, 120.0);
    }

    // Main constructor - all parameters
    public AudioMixer(String mixerModel, int numberOfChannels,
                      boolean hasBluetoothConnectivity, double maxVolumeDecibels) {
        this.mixerModel = mixerModel;
        this.numberOfChannels = numberOfChannels;
        this.hasBluetoothConnectivity = hasBluetoothConnectivity;
        this.maxVolumeDecibels = maxVolumeDecibels;
        this.connectedDevices = new String[numberOfChannels];
        this.deviceCount = 0;
        System.out.println("Constructor executed for: " + mixerModel);
    }

    public void connectDevice(String deviceName) {
        if (deviceCount < connectedDevices.length) {
            connectedDevices[deviceCount] = deviceName;
            deviceCount++;
            System.out.println("Connected: " + deviceName);
        } else {
            System.out.println("All channels occupied!");
        }
    }

    public void displayMixerStatus() {
        System.out.println("\n=== " + mixerModel + " STATUS ===");
        System.out.println("Channels: " + numberOfChannels);
        System.out.println("Bluetooth: " + (hasBluetoothConnectivity ? "Enabled" : "Disabled"));
        System.out.println("Max Volume: " + maxVolumeDecibels + " dB");
        System.out.println("Connected Devices: " + deviceCount + "/" + numberOfChannels);
        for (int i = 0; i < deviceCount; i++) {
            System.out.println("  Channel " + (i + 1) + ": " + connectedDevices[i]);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== MUSIC STUDIO SETUP ===");

        // Mixer using no-argument constructor
        AudioMixer mixer1 = new AudioMixer();

        // Mixer using two-parameter constructor
        AudioMixer mixer2 = new AudioMixer("BassBoost-4", 4);

        // Mixer using three-parameter constructor
        AudioMixer mixer3 = new AudioMixer("EchoMaster-12", 12, true);

        // Mixer using full constructor
        AudioMixer mixer4 = new AudioMixer("ProMix-X", 16, true, 135.0);

        // Connect devices
        mixer1.connectDevice("Mic A");
        mixer1.connectDevice("Keyboard");

        mixer2.connectDevice("Guitar");

        mixer3.connectDevice("Drum Pad");
        mixer3.connectDevice("Synth");

        mixer4.connectDevice("DJ Console");
        mixer4.connectDevice("Laptop");
        mixer4.connectDevice("Sampler");

        // Display status
        mixer1.displayMixerStatus();
        mixer2.displayMixerStatus();
        mixer3.displayMixerStatus();
        mixer4.displayMixerStatus();

        // Comment on constructor chaining
        System.out.println("\nConstructor chaining ensures consistent initialization across overloads.");
    }
}
