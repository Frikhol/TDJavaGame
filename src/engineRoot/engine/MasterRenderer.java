import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterRenderer {
    private  StaticShader shader = new StaticShader();
    private  Renderer renderer = new Renderer(shader);

    private Map<Model, List<GameObject>> entities = new HashMap<Model, List<GameObject>>();

    public void render(Light light,Camera camera){
        shader.start();
        shader.loadLight(light);
        shader.loadViewMatrix(camera);
        renderer.render(entities);
        shader.stop();
        entities.clear();
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public void processEntity(GameObject gameObject){
        Model entityModel = gameObject.getModel();
        List<GameObject> batch = entities.get(entityModel);
        if(batch!=null){
            batch.add(gameObject);
        } else {
            List<GameObject> newBatch = new ArrayList<GameObject>();
            newBatch.add(gameObject);
            entities.put(entityModel, newBatch);
        }
    }

    public void cleanUp(){
        shader.cleanUp();
    }
}
