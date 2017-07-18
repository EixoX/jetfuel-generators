package com.eixox.usecases.generators;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.eixox.usecases.UsecaseExecution;
import com.eixox.usecases.UsecaseImplementation;
import com.eixox.usecases.UsecaseResultType;

public class CreditCard extends UsecaseImplementation<CreditCard.Parameters, Map<String, String[]>> {

	public static class Parameters {

		public String[] flags;

		public int count;
	}

	@Override
	protected void mainFlow(UsecaseExecution<Parameters, Map<String, String[]>> execution) throws Exception {

		// loads descriptions;
		List<CreditCardsAvailable.Description> descriptions = null;
		if (execution.params != null && execution.params.flags != null && execution.params.flags.length > 0)
			descriptions = CreditCardsAvailable.find(execution.params.flags);
		if (descriptions == null)
			descriptions = CreditCardsAvailable.DESCRIPTIONS;

		// initialize count;
		int count = execution.params == null || execution.params.count < 1
				? 10
				: execution.params.count;

		// generate the result;
		execution.result = new LinkedHashMap<String, String[]>();
		for (CreditCardsAvailable.Description description : descriptions) {
			String[] gens = new String[count];
			for (int i = 0; i < gens.length; i++)
				gens[i] = generate(description);
			execution.result.put(description.name, gens);
		}
		execution.result_type = UsecaseResultType.SUCCESS;
	}

	/**
	 * A shared random number generator;
	 */
	private static final Random RANDOM = new Random();

	/**
	 * Generates a new card number based on the provided description;
	 * 
	 * @param description
	 * @return
	 */
	private String generate(CreditCardsAvailable.Description description) {
		char[] chars = new char[description.digits];
		String prefix = description.prefixes[RANDOM.nextInt(description.prefixes.length)];
		int prefixlength = prefix.length();
		int calclength = chars.length - 1;

		// set prefix;
		for (int i = 0; i < prefixlength; i++)
			chars[i] = prefix.charAt(i);

		// set other random digits;
		for (int i = prefixlength; i < calclength; i++)
			chars[i] = (char) (48 + RANDOM.nextInt(10));

		// finds the luhn verifier;
		int sum = 0;
		int index;
		int n;
		for (int i = 0; i < calclength; i++) {
			index = calclength - i - 1;
			n = ((int) chars[index] - 48);
			if (i % 2 == 0) {
				n += n;
			}
			sum += (n / 10) + (n % 10);
		}
		sum *= 9;
		sum %= 10;
		chars[calclength] = (char) (48 + sum);
		return new String(chars);
	}

}
