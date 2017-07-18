import java.util.LinkedHashMap;

import org.junit.Assert;
import org.junit.Test;

import com.eixox.restrictions.CpfRestriction;
import com.eixox.usecases.UsecaseExecution;
import com.eixox.usecases.generators.Cpf;
import com.eixox.usecases.generators.Cpf.Parameters;
import com.eixox.usecases.generators.CpfsAvailable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CpfTests {

	@Test
	public void testAnyRegion() throws JsonProcessingException {

		UsecaseExecution<Parameters, Long[]> execution = UsecaseExecution.create(Cpf.class);
		execution.params = new Parameters();
		execution.params.count = 10;
		execution.params.region = -1;
		execution.run();

		System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(execution));
		Assert.assertTrue(execution.result != null);

		LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
		for (int i = 0; i < execution.result.length; i++) {
			String rs = execution.result[i].toString();
			rs = rs.substring(8, 9);
			Integer counter = map.get(rs);
			map.put(rs, counter == null
					? 1
					: counter + 1);

			Assert.assertTrue(CpfRestriction.isValidObject(execution.result[i]));
		}

		Assert.assertTrue(map.keySet().size() > 5);

	}

	@Test
	public void testEveryRegion() throws JsonProcessingException {

		UsecaseExecution<Parameters, Long[]> execution = UsecaseExecution.create(Cpf.class);
		for (int i = 0; i < CpfsAvailable.LOCALIDADES.length; i++) {
			execution.params = new Parameters();
			execution.params.count = 10;
			execution.params.region = i;
			execution.run();
			System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(execution));
			Assert.assertTrue(execution.result != null);

			for (int j = 0; j < execution.result.length; j++) {
				Assert.assertTrue(CpfRestriction.isValidObject(execution.result[i]));
				String rs = execution.result[j].toString();
				rs = rs.substring(8, 9);
				Assert.assertTrue(rs.equals(Integer.toString(i)));
			}

		}

	}
}
