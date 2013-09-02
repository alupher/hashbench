package edu.berkeley.cs.amplab.hashbench;

import com.google.caliper.Benchmark;
import com.google.caliper.Param;

public final class HashBenchmark {
	@Param({
		"4",
		"8",
		"16",
		"32"
	}) int inputLength;
		
	@Benchmark int shark_murmur128(int reps) {
		int inputLength = this.inputLength;
		int dummy = 0;
		for (int i = 0; i < reps; i++) {
			dummy |= Hash.shark_murmur128(inputLength);
		}
		return dummy;
	}
	
	@Benchmark int guava_murmur128(int reps) {
		int inputLength = this.inputLength;
		int dummy = 0;
		for (int i = 0; i < reps; i++) {
			dummy |= Hash.guava_murmur128(inputLength);
		}
		return dummy;
	}

	@Benchmark int streamlib_murmur32(int reps) {
		int inputLength = this.inputLength;
		int dummy = 0;
		for (int i = 0; i < reps; i++) {
			dummy |= Hash.streamlib_murmur32(inputLength);
		}
		return dummy;
	}
	
	@Benchmark int streamlib_murmur64(int reps) {
		int inputLength = this.inputLength;
		int dummy = 0;
		for (int i = 0; i < reps; i++) {
			dummy |= Hash.streamlib_murmur64(inputLength);
		}
		return dummy;
	}
}