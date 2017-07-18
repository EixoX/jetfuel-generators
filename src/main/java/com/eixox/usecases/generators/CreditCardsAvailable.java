package com.eixox.usecases.generators;

import java.util.ArrayList;
import java.util.List;

import com.eixox.usecases.UsecaseExecution;
import com.eixox.usecases.UsecaseImplementation;
import com.eixox.usecases.UsecaseResultType;

/**
 * This usecase lists all the available credit card generators;
 * 
 * @author Rodrigo Portela
 *
 */
public class CreditCardsAvailable extends UsecaseImplementation<Void, List<CreditCardsAvailable.Description>> {

	@Override
	protected void mainFlow(UsecaseExecution<Void, List<CreditCardsAvailable.Description>> execution) throws Exception {

		execution.result = DESCRIPTIONS;
		execution.result_type = UsecaseResultType.SUCCESS;

	}

	/**
	 * A description for generating credit cards;
	 * 
	 * @author Rodrigo Portela
	 *
	 */
	public static class Description {

		/**
		 * The allowed card prefixes;
		 */
		public String[] prefixes;

		/**
		 * The card flag name;
		 */
		public String name;

		/**
		 * The card summary;
		 */
		public String summary;

		/**
		 * The link for further documentation;
		 */
		public String link;

		/**
		 * The number of digits in the card number;
		 */
		public int digits;

		/**
		 * Creates a new instance of the credit card generator description;
		 * 
		 * @param name
		 * @param summary
		 * @param link
		 * @param digits
		 * @param prefixes
		 */
		public Description(String name, String summary, String link, int digits, String... prefixes) {
			this.name = name;
			this.summary = summary;
			this.link = link;
			this.digits = digits;
			this.prefixes = prefixes;
		}
	}

	public static final ArrayList<Description> DESCRIPTIONS;
	static {
		DESCRIPTIONS = new ArrayList<CreditCardsAvailable.Description>();
		DESCRIPTIONS.add(new Description(
				"Amex",
				"Números de cartões de crédito válidos american express",
				"http://pt.wikipedia.org/wiki/American_Express",
				15,
				"37"));
		DESCRIPTIONS.add(new Description(
				"DinersClub",
				"Números de cartões de crédito válidos Diners Club International.",
				"http://pt.wikipedia.org/wiki/Diners_club",
				16,
				"3095"));
		DESCRIPTIONS.add(new Description(
				"Elo", "Números de cartões de créditos Elo válidos.",
				"https://gist.github.com/erikhenrique/5931368",
				16,
				"636368", "438935", "504175", "451416", "636297", "5067", "4576", "4011", "506699"));
		DESCRIPTIONS.add(new Description(
				"Maestro",
				"Números de cartões de crédito válidos Maestro.",
				"http://pt.wikipedia.org/wiki/MasterCard_Maestro",
				16,
				"5018", "5020", "5038", "5893", "6304", "6759", "6761", "6762", "6763", "0606"));
		DESCRIPTIONS.add(new Description(
				"Mastercard",
				"Númerso de cartões de crédito válidos Mastercard.",
				"http://pt.wikipedia.org/wiki/Mastercard",
				16,
				"548045"));
		DESCRIPTIONS.add(new Description(
				"Visa",
				"Números de cartões de crédito válidos Visa.",
				"http://pt.wikipedia.org/wiki/Visa",
				16,
				"40", "43", "47"));
	}

	public static final Description get(String name) {
		if (name != null && !name.isEmpty()) {
			for (int i = 0; i < DESCRIPTIONS.size(); i++)
				if (name.equalsIgnoreCase(DESCRIPTIONS.get(i).name))
					return DESCRIPTIONS.get(i);
		}
		return null;
	}

	public static final List<Description> find(String... names) {
		ArrayList<Description> list = new ArrayList<Description>(names.length);
		for (int i = 0; i < names.length; i++) {
			Description ccgd = get(names[i]);
			if (ccgd != null)
				list.add(ccgd);
		}
		return list;
	}

}
