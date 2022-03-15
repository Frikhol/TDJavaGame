import org.joml.Vector2f;

public class Enemy extends GameObject implements Effectible{
    private float health;
    private float resist;
    private float positionOnTrack;
    private Track track;
    private float speed;

    public Enemy(float health, float positionOnTrack, Track track, float speed, float resist) {
        this.health = health;
        this.positionOnTrack = positionOnTrack;
        this.track = track;
        this.speed = speed;
        this.resist = resist;

    }
    void tactFrame(){
        positionOnTrack+=getSpeed()*GameDisplay.deltaTime();
        takeDamage(effectMultiplayer("time_damage")*GameDisplay.deltaTime());
    }
    float getSpeed(){
        return speed*effectMultiplayer("move_speed");
    }

    public float getHealth() {
        return health*effectMultiplayer("stamina");
    }
    void takeDamage(float dmg){
        if(dmg<=0.0f)
            return;
        health-=(1.0f-getResist())*dmg/effectMultiplayer("stamina");
    }

    public float getResist() {
        return resist*percentEffectMultiplayer("resist");
    }


    Vector2f getPosition(){
        return track.getPosition(positionOnTrack);
    }

}
