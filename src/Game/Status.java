public class Status {
    GameObject object = null;
    int timer = 0;
    public Status(GameObject object, int time) {
        this.object = object;
        this.timer = time;
        StatusSystem.list.add(this);
    }


}
