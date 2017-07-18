package com.eixox.usecases.generators;

import java.util.Random;

import com.eixox.usecases.UsecaseExecution;
import com.eixox.usecases.UsecaseImplementation;
import com.eixox.usecases.UsecaseResultType;

public class Password extends UsecaseImplementation<Password.Parameters, String[]> {

	private static final Random RANDOM = new Random();
	private static final String ALPHABET = "ABCDEFGHJIJKLMNOPQRSTUVWXYZ123456890-_?;.,+=-!*abcdefghijklmnopqrstuvzxyz";

	public static class Parameters {
		public int length;
		public int count;
	}

	@Override
	protected void mainFlow(UsecaseExecution<Parameters, String[]> execution) throws Exception {

		int count = execution.params != null && execution.params.count > 0
				? execution.params.count
				: 1;

		int length = execution.params != null && execution.params.length > 4
				? execution.params.length
				: 8;

		execution.result = new String[count];
		for (int i = 0; i < count; i++) {
			char[] pwd = new char[length];
			for (int j = 0; j < length; j++)
				pwd[j] = ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length()));
			execution.result[i] = new String(pwd);
		}
		execution.result_type = UsecaseResultType.SUCCESS;
	}

}
