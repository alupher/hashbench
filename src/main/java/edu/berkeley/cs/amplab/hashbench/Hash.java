package edu.berkeley.cs.amplab.hashbench;
import com.clearspring.analytics.hash.MurmurHash;
import com.google.common.hash.Hashing;
import com.google.common.hash.HashFunction;
import shark.util.MurmurHash3_x86_128;
import java.util.Random;


final class Hash {
	static int streamlib_murmur32(int inputLength) {
		// Use a dummy value to prevent the JIT from skipping the hashing
		int dummy = 0;
		byte[] input = new byte[inputLength];
		Random rand = new Random();
		
		rand.nextBytes(input);
		dummy |= MurmurHash.hash(input);
		
		return dummy;
	}
	
	static int guava_murmur128(int inputLength) {
		int dummy = 0;
		byte[] input = new byte[inputLength];
		Random rand = new Random();

		HashFunction hf = Hashing.murmur3_128();
		rand.nextBytes(input);
		dummy |= hf.hashBytes(input).asInt();
		
		return dummy;
	}

	static int shark_murmur128(int inputLength) {
		int dummy = 0;
		byte[] input = new byte[inputLength];
		Random rand = new Random();
		
		int[] result = new int[4];
		rand.nextBytes(input);
		MurmurHash3_x86_128.hash(input, 0, 4, result);
		dummy |= result[0]; 
		
		return dummy;
	}
}