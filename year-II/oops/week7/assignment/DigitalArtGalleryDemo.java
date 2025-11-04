// PROBLEM 5: Digital Art Gallery
// Concept: Downcasting
// Demonstrates a general ArtPiece system with specialized artwork types
// and curator access to specific features via downcasting.

public class DigitalArtGalleryDemo {
    public static void main(String[] args) {
        ArtPiece[] gallery = new ArtPiece[] {
            new Painting("Starry Night", "Van Gogh", "Impasto", "Vivid Blue-Yellow", "Ornate Gold"),
            new Sculpture("The Thinker", "Rodin", "Bronze", "2.5m x 1.2m x 1.5m", "Spotlight"),
            new DigitalArt("Neon Dreams", "A. Lee", "4K", "PNG, GIF", true),
            new Photography("Moonrise", "A. Adams", "f/8 ISO100", "HDR, Crop", "Matte 20x30cm")
        };

        // General exhibition info
        for (ArtPiece art : gallery) {
            art.displayInfo();
            System.out.println();
        }

        // Curator needs specific details for planning
        for (ArtPiece art : gallery) {
            if (art instanceof Painting) {
                Painting p = (Painting) art;
                System.out.println("Painting details:");
                p.showBrushTechnique();
                p.showColorPalette();
                p.showFrameSpec();
            } else if (art instanceof Sculpture) {
                Sculpture s = (Sculpture) art;
                System.out.println("Sculpture details:");
                s.showMaterial();
                s.showDimensions();
                s.showLighting();
            } else if (art instanceof DigitalArt) {
                DigitalArt d = (DigitalArt) art;
                System.out.println("Digital Art details:");
                d.showResolution();
                d.showFileFormats();
                d.showInteractive();
            } else if (art instanceof Photography) {
                Photography ph = (Photography) art;
                System.out.println("Photography details:");
                ph.showCameraSettings();
                ph.showEditingDetails();
                ph.showPrintSpec();
            }
            System.out.println();
        }
    }
}

class ArtPiece {
    protected String title;
    protected String artist;

    public ArtPiece(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public void displayInfo() {
        System.out.println("Title: " + title + " | Artist: " + artist);
    }
}

class Painting extends ArtPiece {
    private String brushTechnique;
    private String colorPalette;
    private String frameSpec;

    public Painting(String title, String artist, String brushTechnique, String colorPalette, String frameSpec) {
        super(title, artist);
        this.brushTechnique = brushTechnique;
        this.colorPalette = colorPalette;
        this.frameSpec = frameSpec;
    }
    public void showBrushTechnique() {
        System.out.println("Brush technique: " + brushTechnique);
    }
    public void showColorPalette() {
        System.out.println("Color palette: " + colorPalette);
    }
    public void showFrameSpec() {
        System.out.println("Frame: " + frameSpec);
    }
}

class Sculpture extends ArtPiece {
    private String material;
    private String dimensions;
    private String lighting;

    public Sculpture(String title, String artist, String material, String dimensions, String lighting) {
        super(title, artist);
        this.material = material;
        this.dimensions = dimensions;
        this.lighting = lighting;
    }
    public void showMaterial() {
        System.out.println("Material: " + material);
    }
    public void showDimensions() {
        System.out.println("Dimensions: " + dimensions);
    }
    public void showLighting() {
        System.out.println("Lighting: " + lighting);
    }
}

class DigitalArt extends ArtPiece {
    private String resolution;
    private String fileFormats;
    private boolean interactive;

    public DigitalArt(String title, String artist, String resolution, String fileFormats, boolean interactive) {
        super(title, artist);
        this.resolution = resolution;
        this.fileFormats = fileFormats;
        this.interactive = interactive;
    }
    public void showResolution() {
        System.out.println("Resolution: " + resolution);
    }
    public void showFileFormats() {
        System.out.println("File formats: " + fileFormats);
    }
    public void showInteractive() {
        System.out.println("Interactive elements: " + (interactive ? "Yes" : "No"));
    }
}

class Photography extends ArtPiece {
    private String cameraSettings;
    private String editingDetails;
    private String printSpec;

    public Photography(String title, String artist, String cameraSettings, String editingDetails, String printSpec) {
        super(title, artist);
        this.cameraSettings = cameraSettings;
        this.editingDetails = editingDetails;
        this.printSpec = printSpec;
    }
    public void showCameraSettings() {
        System.out.println("Camera settings: " + cameraSettings);
    }
    public void showEditingDetails() {
        System.out.println("Editing: " + editingDetails);
    }
    public void showPrintSpec() {
        System.out.println("Print: " + printSpec);
    }
}