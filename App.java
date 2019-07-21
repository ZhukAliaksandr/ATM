package by.zhuk.bankomat;

public class App {

    public static void main(String[] args) {
        try {
            Persistence persistence = new Persistence("accounts.txt");
            Recycling recycling = new Recycling(10_000);
            Service service = new Service(persistence, recycling);
            Controller controller = new Controller(service);

            controller.startConsole();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
