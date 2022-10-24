package view;

import controller.Controller;
import model.Cube;
import model.Cylinder;
import model.Entity;
import model.Sphere;
import repository.ArrayRepository;
import repository.IRepository;

//Se da o colectie de mai multe obiecte
//avand forme de cuburi, sfere si cilindrii.
//Sa se afiseze obiectele avand volumul mai mare
//decat 25cm3.
public class Main {
    public static void main(String[] args) {
        IRepository repo = new ArrayRepository();
        Controller controller = new Controller(repo);
        controller.addEntity(new Cube(100, "rosu"));
        controller.addEntity(new Cylinder(15, "small"));
        controller.addEntity(new Sphere(40, "ball"));

        Entity[]array=controller.removeEntity(2);

        //System.out.println("The shapes that have the volume more than 25cm3:" );
        //Entity[] array = controller.filter(25);
        //

        for (int i = 0; i < array.length; i++) {
            if (array[i] instanceof Cube)
                System.out.println((Cube) array[i]);
            if (array[i] instanceof Cylinder)
                System.out.println((Cylinder) array[i]);
            if (array[i] instanceof Sphere)
                System.out.println((Sphere) array[i]);

        }
    }
}