import java.util.*;
import java.io.*;
public class MovieScanner
{
    static ArrayList<LinkedList<String>> movies;
    static ArrayList<String> cast;
    String fileName ="movies.txt";
    static int numMovies = 0;
    int tableSize= 414703; 

    public MovieScanner() {
        movies = new ArrayList<LinkedList<String>>(tableSize);
        for (int i=0;i<tableSize;i++){
            movies.add(new LinkedList<String>());
        }
        numMovies=0;
    }

    public int hash(String s) {
        return (Math.abs(s.hashCode())%tableSize);
    }

    public static void main (String [] args){
        MovieScanner run = new MovieScanner();
        run.run();
        //run.moviesFeaturing("Vladimir Rogozhin");
        run.totalUniqueCastMembers();

    }

    public void run() {
        Scanner fileScanner;
        String line="";
        try {
            fileScanner = new Scanner (new File (fileName));
            while (fileScanner.hasNext()) {
                line = fileScanner.nextLine();
                String[] dummy = line.split("/");
                LinkedList dummy1 = new LinkedList();
                for(int i = 0; i<dummy.length; i++){
                    dummy1.add(dummy[i]);
                }
                movies.add(dummy1);
                numMovies++;
            }
        }

        catch (IOException e){
            System.out.println(e);
        }
    }

    public static void totalUniqueCastMembers(){
        Set<String> Cast = new HashSet<String>();
        for(int i=0; i<movies.size(); i++){
            Cast.addAll(movies.get(i));
        }

        System.out.println("Total Unique Cast Members : " + (Cast.size() - numMovies));
    }

    public ArrayList<String> moviesFeaturing (String castMember){
        ArrayList ret = new ArrayList();
        int counter = 0;
        boolean swtch = false;
        for(int i=0; i<movies.size(); i++){
            ret.add(movies.get(i).peek());
            for(int j=0; j<(movies.get(i)).size(); i++){
                String dummy = movies.get(i).remove();
                dummy = dummy.replace("/", " ");
                if(castMember.equals(dummy)){
                    swtch = true;
                }
            }
            if(!swtch){
                ret.remove(counter);
                counter--;
            }
            counter++;
        }
        for(int i=0; i<ret.size(); i++){
            System.out.print(ret.get(i));
        }
        return ret;
    }

    public ArrayList<String> moviesFeaturingTogether (String castMember, String castMember2){
        ArrayList cast1 = moviesFeaturing(castMember);
        ArrayList cast2 = moviesFeaturing(castMember2);
        ArrayList ret = new ArrayList();
        for(int i=0; i<cast1.size(); i++){
            if(cast2.contains(cast1.get(i))){
                ret.add(cast1.get(i));
            }
        }
        for(int i=0; i<ret.size(); i++){
            System.out.print(ret.get(i));
        }
        return ret;
    }

    public void maxFeatured (){
        ArrayList ret = new ArrayList();
        int max = -1;
        String name = "";
        Iterator<String> iter = cast.iterator();
        while(iter.hasNext()){
            name = iter.next();
            int counter = 0;
            boolean swtch = false;
            for(int i=0; i<movies.size(); i++){
                for(int j=0; j<(movies.get(i)).size(); i++){
                    String dummy = movies.get(i).remove();
                    dummy = dummy.replace("/", " ");
                    if(name.equals(dummy)){
                        counter++;
                    }
                }
            }
            if(counter==max){
                ret.add(name);
            }
            if(counter>max){
                ret.clear();
                ret.add(name);
            }
            for(int i=0; i<ret.size(); i++){
                System.out.print(ret.get(i));
            }
        }
    }
}
