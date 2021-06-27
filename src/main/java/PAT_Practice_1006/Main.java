package PAT_Practice_1006;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        String firstId, lastId;
        Date firstIn, lastOut;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

        firstId = lastId = sc.next();
        firstIn = simpleDateFormat.parse(sc.next());
        lastOut = simpleDateFormat.parse(sc.next());
        for (int i = 1; i < M; i++) {
            String id = sc.next();
            Date in = simpleDateFormat.parse(sc.next());
            Date out = simpleDateFormat.parse(sc.next());
            if( in.before(firstIn)){
                firstId = id;
                firstIn = in;
            }
            if( out.after(lastOut)){
                lastId = id;
                lastOut = out;
            }
        }
        System.out.println(firstId + " " + lastId);
    }
}
