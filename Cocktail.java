import java.util.Scanner;

public class Cocktail extends Food {
  final double drinkAv = 34;
  final double fruitAv = 56;
  private String _drink;
  private double _ml;
  private String _fruit;
  private double _gr;

  public Cocktail(Scanner scan) {
    super("Коктейль");
    setFruit(scan);
    scan.nextLine();
    SetDrink(scan);
    System.out.println("Коктейль был добавлен в завтрак.");
  }

  public boolean equals(Object argO) {
    if (!(argO instanceof Cocktail)) return false;
    if (name == null || ((Cocktail) argO).name == null) return false;
    return name.equals(((Cocktail) argO).name);
  }

  public void SetDrink(Scanner scan) {
    System.out.print("Введите название напитка: ");
    _drink = scan.nextLine();
    System.out.print("Введите мл: ");
    _ml = scan.nextInt();
  }

  public void setFruit(Scanner scan) {
    System.out.print("Введите название фрукта: ");
    _fruit = scan.nextLine();
    System.out.print("Введите гр: ");
    _gr = scan.nextInt();
  }

  public String toString() {
    return this.name + " с " + _drink + " и " + _fruit;
  }

  public String GetName() {
    return name;
  }

  public void SetName(String name) {
    this.name = name;
  }

  public double calculateCalories() {
    return _ml * drinkAv / 100 + _gr * fruitAv / 100;
  }

  public void consume() {
    System.out.println(this + " был выпит");
  }
}
