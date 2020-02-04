import java.util.ArrayList;

public class squad {
    private String content;
    private static ArrayList<squad> instances = new ArrayList<squad>();
    private boolean registered;
    private int id;
    public squad (String content){
        this.content = content;
        this.registered = false;
        instances.add(this);
        this.id = instances.size();
    }
    public String getContent() {
        return content;
    }
    public static ArrayList<squad> getAll(){
        return instances;
    }
    public static void clearAllSquads(){
        instances.clear();
    }
    public boolean getRegistered(){
        return this.registered;
    }
    public int getId() {
        return id;
    }
    public static squad findById(int id){
        return instances.get(id-1);
    }
    public void update(String content) {
        this.content = content;
    }
    public void deleteSquads(){
        instances.remove(id-1);
    }
}
