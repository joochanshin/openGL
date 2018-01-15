package engineTester;

import org.lwjgl.opengl.Display;

import renderEngine.*;
import renderEngine.Loader;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		
		// OpenGL expects vertices to be defined counter clockwise by default
		float[] vertices = {
				// Left bottom triangle
				-0.5f, 0.5f, 0f,
				-0.5f, -0.5f, 0f,
				0.5f, -0.5f, 0f,
				// Right top triangle
				0.5f, -0.5f, 0f,
				0.5f, 0.5f, 0f,
				-0.5f, 0.5f, 0f
		};
		int[] indices = {
				0, 1, 3,	// Top left triangle (VO, V1, V3)
				3, 1, 2 	// Bottom right triangle (V3, V1, V2)
		};
		
		RawModel model = loader.loadToVao(vertices, indices);
		
		while(!Display.isCloseRequested()){
			renderer.prepare();
			// game logic
			renderer.render(model);
			DisplayManager.updateDisplay();
		}
		
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
