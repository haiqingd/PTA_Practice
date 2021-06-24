import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class PAT_Practice_1001 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int result = a + b;
        DecimalFormat df = new DecimalFormat();
        df.applyPattern(",###.##");
        String format = df.format(result);
        System.out.println(format);
    }
}
