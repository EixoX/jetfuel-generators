package com.eixox.usecases.generators;

import java.util.LinkedHashMap;
import java.util.Map;

import com.eixox.usecases.UsecaseExecution;
import com.eixox.usecases.UsecaseImplementation;
import com.eixox.usecases.UsecaseResultType;

/**
 * A map of available cpf regions for number generation;
 * 
 * @author Rodrigo Portela
 *
 */
public class CpfsAvailable extends UsecaseImplementation<Void, Map<String, String>> {

	/**
	 * Executes the main flow of the cpfs available listing;
	 */
	@Override
	protected void mainFlow(UsecaseExecution<Void, Map<String, String>> execution) throws Exception {
		execution.result = LOCALIDADE_MAP;
		execution.result_type = UsecaseResultType.SUCCESS;
	}

	public static final String[] LOCALIDADES;
	public static final LinkedHashMap<String, String> LOCALIDADE_MAP;
	static {
		LOCALIDADES = new String[] {
				"Rio Grande do Sul",
				"Distrito Federal, Goiás, Mato Grosso, Mato Grosso do Sul e Tocantins",
				"Amazonas, Pará, Roraima, Amapá, Acre e Rondônia",
				"Ceará, Maranhão e Piauí",
				"Paraíba, Pernambuco, Alagoas e Rio Grande do Norte",
				"Bahia e Sergipe",
				"Minas Gerais",
				"Rio de Janeiro e Espírito Santo",
				"São Paulo",
				"Paraná e Santa Catarina"
		};
		LOCALIDADE_MAP = new LinkedHashMap<String, String>();
		for (int i = 0; i < LOCALIDADES.length; i++)
			LOCALIDADE_MAP.put(Integer.toString(i), LOCALIDADES[i]);
	}

}
