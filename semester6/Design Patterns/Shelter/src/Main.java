import Command.*;
import Entity.Animal;
import Factory.AnimalFactory;
import Factory.CatFactory;
import Factory.DogFactory;
import Observer.ConsoleObserver;
import Service.Shelter;
import Strategy.AdvancedCareStrategy;
import Strategy.BasicCareStrategy;

import java.util.Scanner;


    /* .USED DESIGN PATTERS:
    -----Singleton Pattern-----
    in Service.Shelter class.
    Implementation: Ensures only one instance of the Shelter class exists throughout the application,
    with a private constructor and a public static method (getInstance()) to access the instance.

    -----Factory Pattern-----
    in Factory.AnimalFactory, Factory.DogFactory, and Factory.CatFactory.
    Implementation: Provides an interface (AnimalFactory) for creating objects in a superclass but
    allows subclasses (DogFactory and CatFactory) to alter the type of objects that will be created.

    -----Observer Pattern-----
    in Observer.ShelterObserver and Observer.ConsoleObserver.
    Implementation: Defines a subscription mechanism (addObserver and removeObserver in Shelter) to notify multiple
    objects about any events that happen to the object they’re observing (Animal actions like add or remove).

    -----Strategy Pattern-----
    in Strategy.AnimalCareStrategy, Strategy.BasicCareStrategy, and Strategy.AdvancedCareStrategy.
    Implementation: Defines a family of algorithms (basic and advanced care strategies),
    encapsulates each one, and makes them interchangeable. The Shelter class uses these strategies
    to perform care actions, which can be altered dynamically.

    -----Command Pattern-----
    in Command.Command, Command.AddAnimalCommand, and Command.RemoveAnimalCommand.
    Implementation: Turns requests or simple operations like adding or removing animals into standalone objects
    (Command). This allows the parameterization of objects with operations and supports undoable and redouble operations.
    */
    public class Main {
        public static void main(String[] args) {
            Shelter shelter = Shelter.getInstance();
            shelter.addObserver(new ConsoleObserver());
            shelter.setCareStrategy(new BasicCareStrategy());

            Scanner scanner = new Scanner(System.in);
            CommandHistory history = new CommandHistory();

            while (true) {
                System.out.println("\nChoose an action:");
                System.out.println("1. Add Dog");
                System.out.println("2. Add Cat");
                System.out.println("3. Remove Animal");
                System.out.println("4. Display All Animals");
                System.out.println("5. Set Advanced Care Strategy");
                System.out.println("6. Perform Care For All Animals");
                System.out.println("7. Undo last action");
                System.out.println("8. Redo last action");
                System.out.println("9. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                    case 2:
                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter age: ");
                        int age = scanner.nextInt();
                        scanner.nextLine();

                        AnimalFactory factory = (choice == 1) ? new DogFactory() : new CatFactory();
                        Animal animal = factory.createAnimal(name, age);
                        Command command = new AddAnimalCommand(shelter, animal);
                        history.executeCommand(command);
                        break;
                    case 3:
                        System.out.print("Enter name of animal to remove: ");
                        String removeName = scanner.nextLine();
                        Animal toRemove = shelter.getAllAnimals().stream()
                                .filter(a -> a.getName().equalsIgnoreCase(removeName))
                                .findFirst()
                                .orElse(null);
                        if (toRemove != null) {
                            Command removeCommand = new RemoveAnimalCommand(shelter, toRemove);
                            history.executeCommand(removeCommand);
                        } else {
                            System.out.println("Animal not found!");
                        }
                        break;
                    case 4:
                        System.out.println("Animals in Shelter:");
                        shelter.getAllAnimals().forEach(System.out::println);
                        break;

                    case 5:
                        shelter.setCareStrategy(new AdvancedCareStrategy());
                        System.out.println("Advanced care strategy set.");
                        break;

                    case 6:
                        shelter.performCareForAll();
                        break;
                    case 7:
                        history.undo();
                        break;
                    case 8:
                        history.redo();
                        break;
                    case 9:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
        }
    }
