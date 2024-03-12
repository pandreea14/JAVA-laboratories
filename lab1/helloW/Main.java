//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //ex 1
        System.out.println("Hello World!");

        //ex 2
        String languages[] = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};

        //ex 3
        int n = (int) (Math.random() * 1_000_000);
        System.out.println("n=" + n);

        //ex 4
        int result = n*3;
        System.out.println("result=" + result);
        int b = Integer.parseInt("10101",2);
        result += b;
        System.out.println("result=" + result);
        int c = Integer.parseInt("FF", 16);
        result += c;
        System.out.println("result=" + result);
        result *= 6;
        System.out.println("result=" + result);

        //ex 5
        int sum, copy;
        while(result > 9) {
            copy=result;
            sum=0;
            while (copy != 0) {
                sum += copy % 10;
                copy /= 10;
            }
            result=sum;
        }
        System.out.println("final result = " + result);

        //ex 6
        System.out.println("Willy-nilly, this semester I will learn " + languages[result]);
    }
}