import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public class Game {
    private static Game game = new Game();

    public static void main(String[] args){
        game.run();
    }

    public void run(){
        GameEngine.start(); //Necessary Game Engine prepare


        //Main game loop
        while(!glfwWindowShouldClose(GameDisplay.getID())){ //while not closed
            update(); // USE THIS FUNCTION FOR GAME LOGIC UPDATE TO
            GameEngine.loop(); //Necessary update frames
        }

        GameEngine.stop(); //Necessary game engine endings
        game.close();
    }

    public void update(){
        GameEngine.getEntity().increaseRotation(0,0.5f,0);
    }


    public void close(){

    }
}
