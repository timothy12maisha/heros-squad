import java.util.ArrayList;

public class hero {
    private String content;
    private static ArrayList<hero> instances = new ArrayList<>();
    private boolean registered;
    private int id;
    public hero (String content){
        this.content = content;
        this.registered = false;
        instances.add(this);
        this.id = instances.size();
    }
    public String getContent() {
        return content;
    }
    public static ArrayList<hero> getAll(){
        return instances;
    }
    public static void clearAllHeros(){
        instances.clear();
    }
    public boolean getRegistered(){
        return this.registered;
    }
    public int getId() {
        return id;
    }
    public static hero findById(int id){
        return instances.get(id-1);
    }
    public void update(String content) {
        this.content = content;
    }
    public void deleteHeros(){
        instances.remove(id-1);
    }
}
