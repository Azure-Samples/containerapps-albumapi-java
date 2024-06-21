package examples.azure.containerappsalbumapijava;

public class Album {
    private final int id;
    private final String title;
    private final String artist;
    private final double price;
    private final String image_url;

    public Album(int id, String title, String artist, double price, String image_url) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.price = price;
        this.image_url = image_url;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public double getPrice() {
        return price;
    }

    public String getImage_url() {
        return image_url;
    }
}
