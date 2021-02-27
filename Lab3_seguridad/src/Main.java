import controller.Controller;
import model.Model;
import view.View;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	  // Assemble all the pieces of the MVC
	  Model m = new Model();
	  View v = new View();
	  Controller c = new Controller(m, v);
	  //c.initController();
	}
	

}
