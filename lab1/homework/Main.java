public class Main {
    public static void main(String[] args) {
        if (args.length != 3) { //verificarea parametrilor introdusi la apelul programului
            System.out.println("Usage: java Main <a> <b> <k>");
            return;
        }

        int a, b, k;
        a = Integer.parseInt(args[0]);
        b = Integer.parseInt(args[1]);
        k = Integer.parseInt(args[2]);

        if (a > b) {
            System.out.println("'a' nu poate fi mai mare decat 'b', intervalul este [a,b]");
            return;
        }

        if (k <= 0) {
            System.out.println("'k' trebuie sa fie mai mare decat 0");
            return;
        }

        long startTime = System.nanoTime(); // start time
        StringBuilder result = new StringBuilder();
        for (int i = a; i <= b; i++) {
            if (isKReductible(i, k)) {
                result.append(i).append(" ");
            }
        }
        long endTime = System.nanoTime(); // end time
        long duration = (endTime - startTime) / 1000000; // millisecunde

        System.out.println("K-Reductible numbers in the interval [" + a + ", " + b + "] with k = " + k + ":");
        System.out.println(result.toString());
        System.out.println("Running time: " + duration + " milliseconds");
    }
    public static boolean isKReductible(int num, int k) {
        int sum = num;
        while (sum != k && sum <= num) {
             if (sum < 10) {
                 break; //e necesar pentru cazzul cand nu e k-reductibil si exista un alt k
             }
            int aux = sum;
            sum = 0;
            while (aux > 0) { //face suma dintre patratele cifrelor
                sum += (aux % 10) * (aux % 10);
                aux /= 10;
            }
        }
        return sum == k;
    }
}
