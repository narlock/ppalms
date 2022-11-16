package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import view.CodeInputStrategy;
import view.PpalmsGui;

class PpalmsGuiTest {

	@Test
	void testCodeInputStrategyAttributesNotNull() {
		CodeInputStrategy strategy = new CodeInputStrategy();
		assertNotNull(strategy.getCodeInputButton());
		assertNotNull(strategy.getCodeInputLabel());
	}

}
