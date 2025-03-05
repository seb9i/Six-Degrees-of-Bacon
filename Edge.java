import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Edge {
    public String data;
    public boolean visited;
    public List<Edge> neighbors;
    public Edge come_from;
    public ArrayList<String> movies;
    public Edge(String data){
        this.data = data;
        this.visited = false;
        this.neighbors = new ArrayList<>();
        this.movies = new ArrayList<>();
        come_from = null;
    }

    public List<Edge> getNeighbors(){
        return neighbors;
    }
    public void addMovie(String movie){
        movies.add(movie);
    }
    public void setNeighbors(Edge neighbor){
        neighbors.add(neighbor);
    }
    public String toString(){
        return this.data;
    }
    public String getSimilarMovie(Edge i){
        for (String movie: this.movies){
            if (i.movies.contains(movie)){
                return movie;
            }
        }
        return "";
    }

}
