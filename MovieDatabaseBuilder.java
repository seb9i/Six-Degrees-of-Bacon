import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.*;

public class MovieDatabaseBuilder {

    public static ArrayList<SimpleMovie> getMovieDB(String fileName) {
        ArrayList<SimpleMovie> movies = new ArrayList<SimpleMovie>();
        try {
            File movieData = new File(fileName);
            Scanner reader = new Scanner(movieData);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] data = line.split("---");

                if (data.length > 1) {
                    SimpleMovie s = new SimpleMovie(data[0], data[1]);
                    movies.add(s);
                }

            }
        }
        catch (FileNotFoundException noFile) {
            System.out.println("File not found!");
            return null;
        }
        return movies;
    }
    public static Map<String, ArrayList<String>> getMovieDBCo(String fileName) {
        Map<String, ArrayList<String>> cowork = new LinkedHashMap<>();
        try {
            File movieData = new File(fileName);
            Scanner reader = new Scanner(movieData);
            while (reader.hasNextLine()) {

                String line = reader.nextLine();
                String[] data = line.split("\\+");
                if (data.length > 1){
                    Edge current = new Edge(data[0]);
                    String[] coWor = data[1].split(", ");
                    cowork.put(data[0], new ArrayList<>(Arrays.asList(coWor)));;
                }

            }
        }
        catch (FileNotFoundException noFile) {
            System.out.println("File not found!");
            return null;
        }
        return cowork;
    }

}