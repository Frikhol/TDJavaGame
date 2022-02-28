public class Mesh {

    private RawModel rawModel;
    private MeshTexture texture;

    public Mesh(RawModel model, MeshTexture texture){
        this.rawModel = model;
        this.texture = texture;
    }

    public RawModel getRawModel() {
        return rawModel;
    }

    public MeshTexture getTexture() {
        return texture;
    }

    public void setRawModel(RawModel rawModel) {
        this.rawModel = rawModel;
    }

    public void setTexture(MeshTexture texture) {
        this.texture = texture;
    }
}
