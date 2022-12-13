public class Cheese extends Food {

  public Cheese() {
    super("Сыр");
    System.out.println("Сыр был добавлен в завтрак.");
  }

  public void consume() {
    System.out.println(this + " съеден");
  }

  public double calculateCalories() {
    return 363.0;
  }
}
