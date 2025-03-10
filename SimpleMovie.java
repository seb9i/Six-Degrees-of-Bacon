import java.util.ArrayList;

public class SimpleMovie {
    public String title;
    private String actorsData;
    private ArrayList<String> actors;

    public SimpleMovie(String t, String a) {
        title = t;
        actorsData = a;
        actors = new ArrayList<String>();
        String[] tempActors = actorsData.split(":");
        for (int i = 0; i < tempActors.length; i++) {
            actors.add(tempActors[i]);
        }

    }

    public ArrayList<String> returnActors(){
        return actors;
    }
    public String toString() {
        return "Title: " + title + "\n" + "Actors: " + actors + "\n";
    }
}