import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;

public class Main {
    static ArrayList<SimpleMovie> movies = MovieDatabaseBuilder.getMovieDB("src/movie_data.txt");
    static Map<String, ArrayList<String>> cowork = new HashMap<>();
    static HashMap<String, Edge> nodes = new HashMap<>();

    public static void main(String[] args) {
        for (SimpleMovie i: movies){
            for (String x: i.returnActors()){
                if (!(cowork.containsKey(x))){
                    cowork.put(x, i.returnActors());
                    Edge current = new Edge(x);
                    nodes.put(x, current);
                    current.addMovie(i.title);

                }
                else {
                    ArrayList<String> combined = new ArrayList<>();
                    combined.addAll(i.returnActors());
                    combined.addAll(cowork.get(x));
                    cowork.put(x, combined);
                    Edge current = nodes.get(x);
                    current.addMovie(i.title);
                }

            }
        }

        for (Map.Entry<String, ArrayList<String>> i: cowork.entrySet()){
            Edge current = nodes.get(i.getKey());
            for (String x: i.getValue()){
                Edge curr = nodes.get(x);
                current.setNeighbors(curr);
            }
        }
        boolean wrong = true;
        while (wrong){
            Scanner x = new Scanner(System.in);
            System.out.printf("Which actor do you want to search for?: \n");
            String input = x.nextLine();
            if (input.equals("q")){
                wrong = false;
            }
            else {
                search(input);
            }

        }
    }
    public static void search(String actor) {
        Edge start = nodes.get("Kevin Bacon");
        Queue<Edge> queue = new LinkedList<>();
        queue.add(start);
        while (!(queue.isEmpty())) {
            Edge current = queue.poll();
            if (!(current == null)){
                for (Edge i: current.getNeighbors()){
                    if (i != null){
                        if (!(i.visited)){
                            queue.add(i);
                            i.visited = true;
                            i.come_from = current;
                        }
                    }
                }

            }

        }
        ArrayList<Edge> me = new ArrayList<>();
        if (nodes.get(actor).come_from == null){
            System.out.println("No link with Kevin Bacon");
        }
        else {
            Edge current = nodes.get(actor);
            while (!(current.come_from.data.equals("Kevin Bacon"))){
                me.add(current);
                current = current.come_from;
            }
            me.add(current);
            me.add(nodes.get("Kevin Bacon"));
            for (int i = 0; i < me.size() - 1; i++){
                System.out.printf("%s -> %s -> ", me.get(i), me.get(i).getSimilarMovie(me.get(i + 1)), me.get(i + 1));
            }
            System.out.print("Kevin Bacon");
            System.out.printf("\n%s has a bacon number of %d.\n", actor, me.size() - 1);
        }
    }

}