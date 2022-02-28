public class MeshTexture {

    private int textureID;

    private float shineDamper = 30;
    private float reflectivity = 0.3f;

    public MeshTexture(int textureID){
        this.textureID = textureID;
    }

    public int getID(){
        return this.textureID;
    }

    public float getShineDamper() {
        return shineDamper;
    }

    public void setShineDamper(float shineDamper) {
        this.shineDamper = shineDamper;
    }

    public float getReflectivity() {
        return reflectivity;
    }

    public void setReflectivity(float reflectivity) {
        this.reflectivity = reflectivity;
    }
}
