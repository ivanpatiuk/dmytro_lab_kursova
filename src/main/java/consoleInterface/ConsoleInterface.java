package consoleInterface;

import java.util.Scanner;

public class ConsoleInterface {
    public static int changeSaladOption() {
        Scanner num = new Scanner(System.in);
        System.out.println("""
                ――――――――――――――――――――――――――――
                | Оберіть опцію:                           |
                | (1) Додати овочі                         |
                | (2) Показати список усіх овочів          |
                | (3) Змінити масу овочів                  |
                | (4) Видалити овочі з салату              |
                | (5) Показати список овочів в салаті      |
                | (0) Попереднє меню                       |
                ――――――――――――――――――――――――――――""");
        return num.nextInt();
    }
}
