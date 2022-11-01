
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int row = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int column = scanner.nextInt();
        char[][] scheme = new char[row][column];
        for (int i = 0; i < scheme.length; i++) {
            for (int j = 0; j < scheme[i].length; j++) {
                scheme[i][j] = 'S';
            }
        }
        int choose;

        do {
            System.out.printf("1. Show the seats%n2. Buy a ticket%n3. Statistics%n0. Exit%n> ");
            choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    showTheSeats(scheme, row, column);
                    break;
                case 2:
                    buyTicket(scheme, row, column);
                    break;
                case 3:
                    statistics(scheme, row, column);
                case 0:
                    break;
                default:
                    System.out.println("Please choose 0, 1 or 2.");
                    break;
            }
        } while (choose != 0);
    }


    public static void showTheSeats(char[][] scheme2, int rows2, int seats2) {

        System.out.printf("Cinema:%n  ");
        for (int i = 1; i <= seats2; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < rows2; i++) {
            for (int j = 0; j <= seats2; j++) {
                if (j == 0) {
                    System.out.print(i + 1 + " ");
                } else {
                    System.out.print(scheme2[i][j - 1] + " ");
                }
            }
            System.out.println();
        }
    }


    public static void buyTicket(char[][] scheme, int rows, int seats) {
        Scanner scanner = new Scanner(System.in);
        int totalSeats = rows * seats;
        int rowNumber;
        int seatNumber;
        boolean seatTaken = false;
        while (!seatTaken) {

            do {
                System.out.printf("Enter a row number:%n> ");
                rowNumber = scanner.nextInt();
                if (rowNumber > rows) {
                    System.out.println("Wrong input!");
                }
            } while (rowNumber > rows);

            do {
                System.out.printf("Enter a seat number in that row:%n> ");
                seatNumber = scanner.nextInt();
                if (seatNumber > rows) {
                    System.out.println("Wrong input!");
                }

            } while (seatNumber > seats);


            if (scheme[rowNumber - 1][seatNumber - 1] == 'B') {
                System.out.println("That ticket has already been purchased!");
            } else {
                if (totalSeats <= 60) {
                    System.out.println("Ticket price: $10");
                } else {
                    if (rowNumber <= rows / 2) {
                        System.out.println("Ticket price: $10");
                    } else {
                        System.out.println("Ticket price: $8");
                    }
                }
                scheme[rowNumber - 1][seatNumber - 1] = 'B';
                seatTaken = true;
            }


        }

    }

    public static void statistics(char[][] scheme, int row, int column) {
        int b10 = 0;
        int b8 = 0;
        int totalSeats = row * column;
        int count = 0;
        int totalIncome = 0;

        for (char[] s : scheme) {
            for (char a : s) {
                if (totalSeats <= 60) {
                    totalIncome += 10;
                    if (a == 'B') {   // we must put in the condition for counting bought seats
                        b10++;
                    }
                } else {
                    count++;
                    if (row % 2 == 0) {
                        if (count <= totalSeats / 2) {
                            totalIncome += 10;
                            if (a == 'B') {
                                b10++;
                            }
                        } else {
                            totalIncome += 8;
                            if (a == 'B') {
                                b8++;
                            }
                        }
                    } else {
                        if (count <= ((row - 1) / 2) * column) {
                            totalIncome += 10;
                            if (a == 'B') {
                                b10++;
                            }
                        } else {
                            totalIncome += 8;
                            if (a == 'B') {
                                b8++;
                            }
                        }
                    }
                }
            }
        }
        double percentage =  (((double) b10 +(double) b8) * 100.0 / ((double) row * (double) column));
        System.out.println("Number of purchased tickets: " + (b10 + b8));
        System.out.printf("Percentage:%.2f%%", percentage);
        System.out.println();
        System.out.println("Current income: $" + (b10 * 10 + b8 * 8));
        System.out.println("Total income: $" + totalIncome);
    }
}