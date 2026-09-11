import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrimeGenerator {

    public List<BigInteger> getPrimes(int size) {

        System.out.println("About to find " + size + " primes.");
    
        List<BigInteger> primes = java.util.stream.IntStream.range(0, size)
            .parallel()
            .mapToObj(i -> new BigInteger(
                2000, 
                java.util.concurrent.ThreadLocalRandom.current()
            ).nextProbablePrime())
            .collect(java.util.stream.Collectors.toList());
        
        System.out.println("Found all " + primes.size() + " primes.");
        return primes;
    }
}