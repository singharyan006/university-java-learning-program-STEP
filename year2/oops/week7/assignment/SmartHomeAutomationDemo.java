// PROBLEM 6: Smart Home Automation
// Concept: Safe Downcasting with instanceof
// Demonstrates a home automation system with mixed smart devices
// and safe control using instanceof before downcasting.

public class SmartHomeAutomationDemo {
    public static void main(String[] args) {
        SmartDevice[] devices = new SmartDevice[] {
            new SmartTV("Living Room TV", 45, 12, "Netflix"),
            new SmartThermostat("Nest Thermostat", 22, 45, true),
            new SmartSecuritySystem("Front Door Security", 4, true, "1234"),
            new SmartKitchenAppliance("Oven", 180, 45, "Pizza")
        };

        for (SmartDevice device : devices) {
            System.out.println("Device: " + device.getName());
            device.basicStatus();

            // Safe downcasting with instanceof
            if (device instanceof SmartTV) {
                SmartTV tv = (SmartTV) device;
                tv.changeChannel(7);
                tv.adjustVolume(20);
                tv.launchStreamingApp("YouTube");
            } else if (device instanceof SmartThermostat) {
                SmartThermostat thermo = (SmartThermostat) device;
                thermo.setTemperature(24);
                thermo.setHumidity(50);
                thermo.toggleEnergySaving();
            } else if (device instanceof SmartSecuritySystem) {
                SmartSecuritySystem sec = (SmartSecuritySystem) device;
                sec.activateCamera();
                sec.triggerAlarm();
                sec.setAccessCode("5678");
            } else if (device instanceof SmartKitchenAppliance) {
                SmartKitchenAppliance kitchen = (SmartKitchenAppliance) device;
                kitchen.setCookingTime(30);
                kitchen.setCookingTemp(200);
                kitchen.selectRecipe("Cake");
            }
            System.out.println();
        }
    }
}

abstract class SmartDevice {
    protected String name;
    public SmartDevice(String name) {
        this.name = name;
    }
    public String getName() { return name; }
    public void basicStatus() {
        System.out.println("Status: " + name + " is online.");
    }
}

class SmartTV extends SmartDevice {
    private int channel;
    private int volume;
    private String streamingApp;
    public SmartTV(String name, int channel, int volume, String streamingApp) {
        super(name);
        this.channel = channel;
        this.volume = volume;
        this.streamingApp = streamingApp;
    }
    public void changeChannel(int ch) {
        channel = ch;
        System.out.println(name + " channel set to " + channel);
    }
    public void adjustVolume(int vol) {
        volume = vol;
        System.out.println(name + " volume set to " + volume);
    }
    public void launchStreamingApp(String app) {
        streamingApp = app;
        System.out.println(name + " streaming app launched: " + streamingApp);
    }
}

class SmartThermostat extends SmartDevice {
    private int temperature;
    private int humidity;
    private boolean energySaving;
    public SmartThermostat(String name, int temperature, int humidity, boolean energySaving) {
        super(name);
        this.temperature = temperature;
        this.humidity = humidity;
        this.energySaving = energySaving;
    }
    public void setTemperature(int temp) {
        temperature = temp;
        System.out.println(name + " temperature set to " + temperature + "°C");
    }
    public void setHumidity(int hum) {
        humidity = hum;
        System.out.println(name + " humidity set to " + humidity + "%");
    }
    public void toggleEnergySaving() {
        energySaving = !energySaving;
        System.out.println(name + " energy saving mode: " + (energySaving ? "ON" : "OFF"));
    }
}

class SmartSecuritySystem extends SmartDevice {
    private int cameras;
    private boolean alarmActive;
    private String accessCode;
    public SmartSecuritySystem(String name, int cameras, boolean alarmActive, String accessCode) {
        super(name);
        this.cameras = cameras;
        this.alarmActive = alarmActive;
        this.accessCode = accessCode;
    }
    public void activateCamera() {
        System.out.println(name + " cameras activated: " + cameras);
    }
    public void triggerAlarm() {
        alarmActive = true;
        System.out.println(name + " alarm triggered!");
    }
    public void setAccessCode(String code) {
        accessCode = code;
        System.out.println(name + " access code set to " + accessCode);
    }
}

class SmartKitchenAppliance extends SmartDevice {
    private int cookingTemp;
    private int cookingTime;
    private String recipe;
    public SmartKitchenAppliance(String name, int cookingTemp, int cookingTime, String recipe) {
        super(name);
        this.cookingTemp = cookingTemp;
        this.cookingTime = cookingTime;
        this.recipe = recipe;
    }
    public void setCookingTemp(int temp) {
        cookingTemp = temp;
        System.out.println(name + " cooking temperature set to " + cookingTemp + "°C");
    }
    public void setCookingTime(int time) {
        cookingTime = time;
        System.out.println(name + " cooking time set to " + cookingTime + " min");
    }
    public void selectRecipe(String r) {
        recipe = r;
        System.out.println(name + " recipe selected: " + recipe);
    }
}