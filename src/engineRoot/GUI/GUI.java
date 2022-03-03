import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import java.util.LinkedList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_FRONT_AND_BACK;

public class GUI {
    private List<GUITexture> textureList;
    private static int polygonMode = GL_FILL;
    public GUI(){
        textureList = new LinkedList<>();
    }

    public List<GUITexture> getGUIList() {
        return textureList;
    }

    public void add(GUITexture texture){
        textureList.add(texture);
    }
    public void remove(GUITexture texture){
        textureList.remove(texture);
    }

    public static Vector2f getPosition(int x, int y, int width, int height){
        int screenWidth = GameDisplay.getWIDTH()[0]/2;
        int screenHeight = GameDisplay.getHEIGHT()[0]/2;
        if(x<screenWidth){
            if(y<=screenHeight){//LEFT UP COORDINATE PANE
                return new Vector2f((-(1-(((float)x+(float)width/2)/(float)screenWidth))),(1-(((float)x+(float)height/2)/(float)screenHeight)));
            }
            if(y>screenHeight){//LEFT DOWN COORDINATE PANE
                return new Vector2f((-(1-(((float)x+(float)width/2)/(float)screenWidth))),(-((((float)y+(float)height/2)/(float)screenHeight)-1)));
            }
        }
        else if(x>=screenWidth){
            if(y<=screenHeight){//RIGHT UP COORDINATE PANE
                return new Vector2f(((((float)x+(float)width/2)/(float)screenWidth)-1),(1-(((float)y+(float)height/2)/(float)screenHeight)));
            }
            if(y>screenHeight){//RIGHT DOWN COORDINATE PANE
                return new Vector2f(((((float)x+(float)width/2)/(float)screenWidth)-1),(-((((float)y+(float)height/2)/(float)screenHeight)-1)));
            }
        }
        return null;
    }

    public static Vector2f getScale(int width,int height){
        return new Vector2f((float)width/(float)(GameDisplay.getWIDTH()[0]),(float)height/(float)(GameDisplay.getHEIGHT()[0]));
    }

    public static void changePolyMode(){
        polygonMode = polygonMode == GL_FILL? GL_LINE: GL_FILL;
        glPolygonMode(GL_FRONT_AND_BACK, polygonMode);
    }

}
