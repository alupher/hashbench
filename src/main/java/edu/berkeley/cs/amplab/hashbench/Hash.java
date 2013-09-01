package edu.berkeley.cs.amplab.hashbench;

import com.clearspring.analytics.hash.MurmurHash;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import shark.util.MurmurHash3_x86_128;
import java.util.Random;

final class Hash {

	private static HashFunction guava_hf = Hashing.murmur3_128();
	private static Random rand = new Random();
	
	static int streamlib_murmur32(int inputLength) {
		// Use a dummy value to prevent the JIT from skipping the hashing
		int dummy = 0;
		byte[] input = new byte[inputLength];
		
		rand.nextBytes(input);
		dummy |= MurmurHash.hash(input);
		
		return dummy;
	}
	
	static int guava_murmur128(int inputLength) {
		int dummy = 0;
		byte[] input = new byte[inputLength];
		
		rand.nextBytes(input);
		dummy |= guava_hf.hashBytes(input).asInt();
		
		return dummy;
	}

	static int shark_murmur128(int inputLength) {
		int dummy = 0;
		byte[] input = new byte[inputLength];
		int[] result = new int[4];
		
		rand.nextBytes(input);
		MurmurHash3_x86_128.hash(input, 0, 4, result);
		dummy |= result[0]; 
		
		return dummy;
	}
}