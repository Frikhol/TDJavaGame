public class Model {

    private Mesh mesh;
    private MeshTexture texture;

    public Model() {
    }

    public Model(Mesh model, MeshTexture texture){
        this.mesh = model;
        this.texture = texture;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public MeshTexture getTexture() {
        return texture;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }

    public void setTexture(MeshTexture texture) {
        this.texture = texture;
    }


}
