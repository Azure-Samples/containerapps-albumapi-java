package examples.azure.containerappsalbumapijava;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AlbumController {

    @GetMapping("/")
    public String index() {
        return "<html>\n" +
                "\t<head>\n" +
                "\t\t<title>Album API</title>\n" +
                "\t</head>\n" +
                "\t<body>\n" +
                "\t\t<h1>Welcome to the Album API</h1>\n" +
                "\t\t<p>This API provides access to a collection of albums.</p>\n" +
                "\t\t<p>Use the following endpoints to access the data:</p>\n" +
                "\t\t<ul>\n" +
                "\t\t\t<li><a href='/albums?owner=Miller'><code>/albums?owner={owner}</code></a>: get all albums owned by the given owner</li>\n" +
                "\t\t\t<li><a href='/albums?lowPrice=10&highPrice=13'><code>/albums?lowPrice={lowPrice}&highPrice={highPrice}</code></a>: get all albums within the given price range</li>\n" +
                "\t\t\t<li><a href='/albums/2/owner'><code>/albums/{id}/owner</code></a>: get the owner of the album with the given id</li>\n" +
                "\t\t</ul>\n" +
                "\t</body>\n" +
                "</html>";
    }

    @GetMapping("/albums")
    List<Album> getAllAlbums(@RequestParam(required = false) final String owner,
                             @RequestParam(required = false) final Double lowPrice,
                             @RequestParam(required = false) final Double highPrice) {
        List<Album> filteredAlbums = loadAllAlbums();
        if (owner != null) {
            filteredAlbums = filterAlbumsByOwner(owner, filteredAlbums);
        }
        if (lowPrice != null && highPrice != null) {
            filteredAlbums = filterAlbumsByPrice(lowPrice, highPrice, filteredAlbums);
        }
        return filteredAlbums;
    }

    private List<Album> filterAlbumsByOwner(final String owner, final List<Album> albums) {
        final List<Album> filteredAlbums = new ArrayList<>();
        for (int i = 0; i < albums.size(); i++) {
            if (owner.equalsIgnoreCase(getAlbumOwner(albums.get(i).getId()))) {
                filteredAlbums.add(albums.get(i));
            }
        }
        return filteredAlbums;
    }

    private static List<Album> filterAlbumsByPrice(final Double lowPrice, final Double highPrice, final List<Album> albums) {
        final List<Album> filteredAlbums = new ArrayList<>();
        for (int i = 0; i < albums.size(); i++) {
            if (albums.get(i).getPrice() >= lowPrice && albums.get(i).getPrice() <= highPrice) {
                filteredAlbums.add(albums.get(i));
            }
        }
        return filteredAlbums;
    }

    @GetMapping("/albums/{id}/owner")
    String getAlbumOwner(@PathVariable final int id) {
        if (id == 1 || id == 2) {
            return "Miller";
        } else if (id == 3 || id == 4) {
            return "Mike";
        } else if (id == 5 || id == 6) {
            return "John";
        } else {
            return "Unknown";
        }
    }
    
    private static List<Album> loadAllAlbums() {
        final List<Album> albums = new ArrayList<>();
        albums.add(new Album(1, "You, Me and an App Id", "Daprize", 10.99, "https://aka.ms/albums-daprlogo"));
        albums.add(new Album(2, "Seven Revision Army", "The Blue-Green Stripes", 13.99, "https://aka.ms/albums-containerappslogo"));
        albums.add(new Album(3, "Scale It Up", "KEDA Club", 13.99, "https://aka.ms/albums-kedalogo"));
        albums.add(new Album(4, "Lost in Translation", "MegaDNS", 12.99, "https://aka.ms/albums-envoylogo"));
        albums.add(new Album(5, "Lock Down Your Love", "V is for VNET", 12.99, "https://aka.ms/albums-vnetlogo"));
        albums.add(new Album(6, "Sweet Container O' Mine", "Guns N Probeses", 14.99, "https://aka.ms/albums-containerappslogo"));
        return albums;
    }
}
