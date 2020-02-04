
import java.util.ArrayList;

public class hero {
    private String heroName;
    private String heroSuperPower;
    private String codeName;
    private boolean registered;
    private int id;
    private static ArrayList<hero> instances = new ArrayList<>();

    public hero(String heroName, String heroSuperPower, String codeName ) {
        this.heroName = heroName;
        this.heroSuperPower = heroSuperPower;
        this.codeName = codeName;
        this.registered = registered;
        instances.add(this);
        this.id = instances.size();
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getHeroSuperPower() {
        return heroSuperPower;
    }

    public void setHeroSuperPower(String heroSuperPower) {
        this.heroSuperPower = heroSuperPower;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public static ArrayList<hero> getInstances() {
        return instances;
    }

    public static void setInstances(ArrayList<hero> instances) {
        hero.instances = instances;
    }

    public boolean isRegistered() {
        return registered;
    }




//    public hero (String content){
//        this.heroName = content;
//        this.registered = false;
//
//    }


    public String getHeroName() {
        return heroName;
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
    public void update(String heroName) {
        this.heroName = heroName;
    }
    public void deletePost(){
        instances.remove(id-1);
    }
}
