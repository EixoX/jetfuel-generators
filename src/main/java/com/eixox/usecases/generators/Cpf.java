package com.eixox.usecases.generators;

import java.util.Random;

import com.eixox.usecases.UsecaseExecution;
import com.eixox.usecases.UsecaseImplementation;
import com.eixox.usecases.UsecaseResultType;

/**
 * Generates several valid CPF numbers for testing purposes;
 * 
 * @author Rodrigo Portela
 *
 */
public class Cpf extends UsecaseImplementation<Cpf.Parameters, Long[]> {

	/**
	 * The CPF generator parameters class;
	 * 
	 * @author Rodrigo Portela
	 *
	 */
	public static class Parameters {

		/**
		 * The number of cpfs to generate;
		 */
		public int count;

		/**
		 * The identifier of the region to generate; See CpfsAvailable.Regions
		 * to understand;
		 */
		public int region = -1;
	}

	/**
	 * Actually generates CPFs
	 */
	@Override
	protected void mainFlow(UsecaseExecution<Parameters, Long[]> execution) throws Exception {

		int count = 0;
		int region = -1;

		if (execution.params != null && execution.params.count > 0) {
			count = execution.params.count;
			region = execution.params.region;
		}

		if (count < 1 || count > 1000)
			count = 10;

		execution.result = new Long[count];
		for (int i = 0; i < count; i++)
			execution.result[i] = region < 0
					? generate(RANDOM.nextInt(10))
					: generate(region);

		execution.result_type = UsecaseResultType.SUCCESS;

	}

	/**
	 * A utility random number generator;
	 */
	private static final Random RANDOM = new Random();

	/**
	 * Generates a CPF for a specific region;
	 * 
	 * @param localidade
	 * @return
	 */
	public static final long generate(int localidade) {
		long a = RANDOM.nextInt(9) + 1;
		long b = RANDOM.nextInt(10);
		long c = RANDOM.nextInt(10);

		long d = RANDOM.nextInt(10);
		long e = RANDOM.nextInt(10);
		long f = RANDOM.nextInt(10);

		long g = RANDOM.nextInt(10);
		long h = RANDOM.nextInt(10);
		long i = localidade;

		if (a == b && b == c && c == d && d == e && e == f && f == g && g == h && h == i)
			return generate(localidade);

		// Note: compute 1st verification digit.
		long d1 = (a * 1 + b * 2 + c * 3 + d * 4 + e * 5 + f * 6 + g * 7 + h * 8 + i * 9) % 11;
		if (d1 == 10)
			d1 = 0;

		// d1 = (d1 >= 10 ? 0 : 11 - d1);

		// Note: compute 2nd verification digit.
		long d2 = (b * 1 + c * 2 + d * 3 + e * 4 + f * 5 + g * 6 + h * 7 + i * 8 + d1 * 9) % 11;
		if (d2 == 10)
			d2 = 0;

		return d2 + d1 * 10 + i * 100 + h * 1000 + g * 10000 + f * 100000 + e * 1000000 + d * 10000000 + c * 100000000
				+ b * 1000000000 + a * 10000000000L;
	}
}
