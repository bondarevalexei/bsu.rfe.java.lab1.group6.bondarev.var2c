import java.lang.invoke.WrongMethodTypeException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import  java.lang.reflect.*;

public class Main {

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    System.out.print("Введите сколько в завтраке сыра: ");
    int cheeseAmount = scan.nextInt();
    System.out.print("Введите сколько в завтраке яблок: ");
    int appleAmount = scan.nextInt();
    System.out.print("Введите сколько в завтраке коктейлей: ");
    int cocktailAMount = scan.nextInt();
    scan.nextLine();

    Food[] breakfast = new Food[cheeseAmount + appleAmount + cocktailAMount];

    for (int i = 0; i < cheeseAmount; i++) {
      breakfast[i] = new Cheese();
    }

    for (int i = cheeseAmount; i < cheeseAmount + appleAmount; i++) {
      System.out.print("Яблоко. Введите размер: ");
      breakfast[i] = new Apple(scan.nextLine());
    }

    for (int i = cheeseAmount + appleAmount; i < breakfast.length; i++) {
      Class myClass;
      try {
        myClass = Class.forName("Cocktail");
      } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
      }
      Constructor constructor;
      try {
        constructor = myClass.getConstructor(Scanner.class);
      } catch (NoSuchMethodException e) {
        throw new RuntimeException(e);
      }
      try {
        breakfast[i] = (Food)constructor.newInstance(scan);
      } catch (InstantiationException e) {
        throw new RuntimeException(e);
      } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
      } catch (InvocationTargetException e) {
        throw new RuntimeException(e);
      }
      scan.nextLine();
    }

    System.out.println(
      "Количество яблок = " +
      appleAmount +
      ", количество сыра = " +
      cheeseAmount +
      ", количество коктейлей = " +
      cocktailAMount
    );

    boolean action = true;
    do {
      System.out.println(
        "Введите действие (-exit - завершить программу, -calories, -sort): "
      );
      String input = scan.nextLine();

      if (input.trim().equals("-exit")) {
        action = false;
      } else if (input.trim().equals("-sort")) {
        Comparator<Food> comparator = Comparator.comparing(
          Food::calculateCalories
        );
        Arrays.sort(breakfast, comparator.reversed());
        for (Food food : breakfast) {
          System.out.println(food + " " + food.calculateCalories());
        }
      } else if (input.trim().equals("-calories")) {
        double result = 0.0;
        for (Food food : breakfast) {
          result += food.calculateCalories();
        }
        System.out.println("Калории = " + result);
      } else {
        throw new WrongMethodTypeException(input);
      }
    } while (action);

    for (Food food : breakfast) {
      food.consume();
    }

    scan.close();
  }
}
