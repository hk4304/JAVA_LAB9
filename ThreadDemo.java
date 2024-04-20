import java.util.Scanner;

class MyThread extends Thread {
    private String threadName;

    MyThread(String name) {
        this.threadName = name;
    }

    public void run() {
        System.out.println("Thread " + threadName + " is running.");
        try {
            // Simulating some task
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted.");
        }
        System.out.println("Thread " + threadName + " finished executing.");
    }
}

public class ThreadDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Create and start a thread");
            System.out.println("2. Check if a thread is alive");
            System.out.println("3. Get thread priority");
            System.out.println("4. Set thread priority");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter the name for the thread: ");
                    String name = scanner.nextLine();
                    MyThread thread = new MyThread(name);
                    thread.start();
                    break;
                case 2:
                    System.out.print("Enter the name of the thread to check if it's alive: ");
                    String threadName = scanner.nextLine();
                    Thread t = findThreadByName(threadName);
                    if (t != null && t.isAlive()) {
                        System.out.println("The thread " + threadName + " is alive.");
                    } else if (t != null) {
                        System.out.println("The thread " + threadName + " is not alive.");
                    } else {
                        System.out.println("Thread not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the name of the thread to get its priority: ");
                    String tName = scanner.nextLine();
                    Thread th = findThreadByName(tName);
                    if (th != null) {
                        System.out.println("Priority of thread " + tName + " is: " + th.getPriority());
                    } else {
                        System.out.println("Thread not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter the name of the thread to set its priority: ");
                    String thrName = scanner.nextLine();
                    Thread thr = findThreadByName(thrName);
                    if (thr != null) {
                        System.out.print("Enter the priority (1-10): ");
                        int priority;
                        try {
                            priority = Integer.parseInt(scanner.nextLine());
                            if (priority < Thread.MIN_PRIORITY || priority > Thread.MAX_PRIORITY) {
                                System.out.println("Invalid priority. Priority should be between " +
                                        Thread.MIN_PRIORITY + " and " + Thread.MAX_PRIORITY);
                                continue;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a number.");
                            continue;
                        }
                        thr.setPriority(priority);
                        System.out.println("Priority of thread " + thrName + " set to " + priority);
                    } else {
                        System.out.println("Thread not found.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static Thread findThreadByName(String name) {
        for (Thread t : Thread.getAllStackTraces().keySet()) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        return null;
    }
}
