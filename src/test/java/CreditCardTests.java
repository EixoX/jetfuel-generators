import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Test;

import com.eixox.restrictions.CreditCardRestriction;
import com.eixox.usecases.UsecaseExecution;
import com.eixox.usecases.generators.CreditCard;
import com.eixox.usecases.generators.CreditCard.Parameters;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CreditCardTests {

	@Test
	public void testAvailableFlags() throws JsonProcessingException {

		UsecaseExecution<Parameters, Map<String, String[]>> execution = UsecaseExecution.create(CreditCard.class);
		execution.params = new Parameters();
		execution.params.count = 10;
		execution.params.flags = null;
		execution.run();
		System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(execution));
		Assert.assertTrue(execution.result != null);
		validate(execution.result);
	}

	@Test
	public void testCustomFlags() throws JsonProcessingException {

		UsecaseExecution<Parameters, Map<String, String[]>> execution = UsecaseExecution.create(CreditCard.class);
		execution.params = new Parameters();
		execution.params.count = 20;
		execution.params.flags = new String[] { "Visa", "Mastercard", "Elo", "Amex" };
		execution.run();
		System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(execution));
		Assert.assertTrue(execution.result != null);
		validate(execution.result);
	}

	private void validate(Map<String, String[]> result) {
		for (Entry<String, String[]> entry : result.entrySet()) {
			validate(entry.getValue());
		}
	}

	private void validate(String[] cards) {
		for (int i = 0; i < cards.length; i++)
			Assert.assertTrue(CreditCardRestriction.isValid(cards[i]));
	}
}
