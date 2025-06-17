
import java.util.*;

public class MathAlgos {

    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    private static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    private static int lcmOfArray(int[] arr) {
        int res = arr[0];
        for (int i = 1; i < arr.length; i++) {
            res = lcm(res, arr[i]);
        }
        return res;
    }
     //Sieve of Eratosthenes tc : o(n*log(logn))
    private static boolean[] primes(int n) {
        //true == not a prime && false == a prime 
        boolean[] isPrime = new boolean[n + 1];
        isPrime[0] = isPrime[1] = true;
        for (int i = 2; i * i <= n; i++) {
            if (!isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = true;
                }
            }
        }
        // for (int i = 2; i <= n; i++) {
        //     if (!isPrime[i]) {
        //         System.out.print(i + " ");
        //     }
        // }
        // System.out.println();
        return isPrime;
    }

    private static List<Long> segmentSeivePrimes(long l, long r) {
        boolean[] isPrime = primes((int) Math.sqrt(r) + 1);  // +1 to be safe
        List<Long> primes = new ArrayList<>();
        for (int i = 2; i < isPrime.length; i++) {
            if (!isPrime[i]) {
                primes.add((long) i);
            }
        }
        
        boolean[] isSegmentPrime = new boolean[(int) (r - l + 1)];
        
        for (long prime : primes) {
            long start = Math.max(prime * prime, ((l + prime - 1) / prime) * prime);
            for (long i = start; i <= r; i += prime) {
                isSegmentPrime[(int) (i - l)] = true;
            }
        }
        
        List<Long> res = new ArrayList<>();
        for (int i = 0; i < isSegmentPrime.length; i++) {
            if (!isSegmentPrime[i] && (l + i) > 1) {
                res.add(l + (long)i);
            }
        }
        
        return res;
    }

    // reference:- chatGPT Linear Sieve (Euler’s Sieve)
    //it is linear version of sieves of erathonesis it is much faster 
    private static List<Integer> linearSieve(int N) {
        boolean[] isPrime = new boolean[N + 1];
        int[] spf = new int[N + 1];
        List<Integer> primes = new ArrayList<>();
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                primes.add(i);
                spf[i] = i;
            }

            for (int p : primes) {
                if (p > spf[i] || i * p > N) {
                    break;
                }
                isPrime[i * p] = false;
                spf[i * p] = p;
            }
        }
        return primes;
    }

    private static List<Integer> factors(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                res.add(i);
                if (i != n / i) {
                    res.add(n / i);
                }
            }
        }
        return res;
    }

    private static int gcdOfArray(int[] arr) {
        int res = arr[0];
        for (int i = 1; i < arr.length; i++) {
            res = gcd(res, arr[i]);
            if (res == 1) {
                return 1;
            }
        }
        return res;
    }
    private static int fact(int n,int mod){
        if(n==0)
        return 1;
        return (n%mod*fact(n-1,mod)%mod)%mod;
    }
    private static int fact(int n){
        if(n==0)
        return 1;
        return n*fact(n-1);
    }
   

    private static int pow(int a, int b, int m) {
        if (b == 0) {
            return 1;
        }
        int res = pow(a, b / 2, m);
        return b % 2 == 0 ? ((res % m * res % m) % m) : (a % m * (res % m * res % m) % m) % m;
    }

    private static int pow(int a, int b) {
        if (b == 0) {
            return 1;
        }
        int res = pow(a, b / 2);
        return b % 2 == 0 ? res * res : res * res * a;
    }

    //modulo inverse = (a/b)%m = (a*(b^-1))%m = (a%m + (b^-1)%m)%m 
    //(b^-1)%m = pow(b,m-2,m);
    private static int moduloInverse(int b, int mod) {
        return pow(b, mod - 2, mod);
    }

    public static void main(String[] args) {
        // System.out.println(gcd(2,16));
        // System.out.println(lcm(2,16));
        // primes(100);
        // System.out.println(pow(3,4,10000));
        // System.out.println(pow(3,4));
        // System.out.println(lcmOfArray(new int[]{1,2,6}));
        // System.out.println(gcdOfArray(new int[]{12,2,6}));
        // System.out.println(linearSieve(100));
        System.out.println(segmentSeivePrimes(5000000000l,5000000100l));
    }
}
