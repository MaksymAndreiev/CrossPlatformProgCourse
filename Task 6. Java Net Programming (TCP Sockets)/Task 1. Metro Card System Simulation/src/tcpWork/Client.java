package tcpWork;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Client {
    private int port = -1;
    private String server = null;
    private Socket socket = null;
    private ObjectInputStream is = null;
    private ObjectOutputStream os = null;

    private static int ser = 1;


    public Client(int port, String server) {
        this.port = port;
        this.server = server;
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(server, port), 1000);
            os = new ObjectOutputStream(socket.getOutputStream());
            is = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void finish(){
        try {
            os.writeObject(new StopOperation());
            os.flush();
            System.out.println(is.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void applyOperation(CardOperation op){
        try {
            if (os == null){
                System.out.println("da");
            }
            os.writeObject(op);
            os.flush();
            System.out.println(is.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private int choice(){
        System.out.println("1. Нова картка");
        System.out.println("2. Отримати інформацію");
        System.out.println("3. Поповнити рахунок");
        System.out.println("4. Сплатити проїзд");
        System.out.println("5. Залишок");
        System.out.println("6. Видалити картку");
        System.out.println("7. Вихід");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private void menu(){
        Client client = new Client(7891, "localhost");
        Scanner scanner = new Scanner(System.in);
        String serialNumber;
        CardOperation cardOperation = null;
        switch (choice()){
            case 1:
                System.out.print("І'мя: ");
                String name = scanner.next();
                System.out.print("Прізвище: ");
                String surname = scanner.next();
                System.out.print("По-батькові: ");
                String patronymic = scanner.next();
                System.out.print("Стать (ч-true, ж-false): ");
                boolean sex = scanner.nextBoolean();
                System.out.print("Дата народження: ");
                String birthday = scanner.next();
                User user = new User(name, surname, patronymic, sex, birthday);
                System.out.print("Навчальний заклад: ");
                String university = scanner.next();
                System.out.println("Серійний номер:");
                serialNumber = scanner.next();
                cardOperation = new AddMetroCardOperation();
                MetroCard metroCard = ((AddMetroCardOperation)cardOperation).getCard();
                metroCard.setSerialNumber(serialNumber);
                metroCard.setUser(user);
                metroCard.setUniversity(university);
                metroCard.setBalance(0);
                break;
            case 2:
                System.out.println("Серійний номер:");
                serialNumber = scanner.next();
                cardOperation = new GetCardInfoOperation(serialNumber);
                break;
            case 3:
                System.out.println("Серійний номер:");
                serialNumber = scanner.next();
                System.out.println("Сума:");
                double money = scanner.nextDouble();
                cardOperation = new AddMoneyOperation(serialNumber, money);
                break;
            case 4:
                System.out.println("Серійний номер:");
                serialNumber = scanner.next();
                System.out.println("Сума:");
                double fee = scanner.nextDouble();
                cardOperation = new PayMoneyOperation(serialNumber, fee);
                break;
            case 5:
                System.out.println("Серійний номер:");
                serialNumber = scanner.next();
                cardOperation = new ShowBalanceOperation(serialNumber);
                break;
            case 6:
                System.out.println("Серійний номер:");
                serialNumber = scanner.next();
                cardOperation = new RemoveCardOperation(serialNumber);
                break;
            case 7:
                System.exit(0);
            default:
                System.out.println("Виберіть варіант 1-7");
                break;
        }
        if (cardOperation != null){
            applyOperation(cardOperation);
        }
    }

    public static void main(String[] args){
        Client client = new Client(7891, "localhost");
        while (true){
            client.menu();
        }
    }
}
