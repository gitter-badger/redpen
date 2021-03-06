package cc.redpen.config;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for creating Configuration object with the Builder.
 */
public class ConfigurationBuilderTest {
    @Test
    public void testBuildSimpleConfiguration() {
        Configuration config = new Configuration.Builder()
                .addValidatorConfig(new ValidatorConfiguration("InvalidExpression"))
                .addValidatorConfig(new ValidatorConfiguration("SentenceLength"))
                .setSymbolTable("en").build();

        assertEquals(2, config.getValidatorConfigs().size());
        assertNotNull(config.getSymbolTable());
        assertEquals("InvalidExpression", config.getValidatorConfigs()
                .get(0).getConfigurationName());
        assertEquals("SentenceLength", config.getValidatorConfigs()
                .get(1).getConfigurationName());
    }

    @Test
    public void testBuildConfigurationWithoutSymbolTable() {
        Configuration config = new Configuration.Builder()
                .addValidatorConfig(new ValidatorConfiguration("InvalidExpression"))
                .addValidatorConfig(new ValidatorConfiguration("SentenceLength")).build();

        assertEquals(2, config.getValidatorConfigs().size());
        assertNotNull(config.getSymbolTable());
        assertEquals("InvalidExpression", config.getValidatorConfigs()
                .get(0).getConfigurationName());
        assertEquals("SentenceLength", config.getValidatorConfigs()
                .get(1).getConfigurationName());
    }

    @Test
    public void testBuildConfigurationAddingProperties() {
        Configuration config = new Configuration.Builder()
                .addValidatorConfig(new ValidatorConfiguration("InvalidExpression")
                        .addAttribute("dict", "./foobar.dict"))
                .addValidatorConfig(new ValidatorConfiguration("SentenceLength")
                        .addAttribute("max_length", "10")).build();

        assertEquals(2, config.getValidatorConfigs().size());
        assertNotNull(config.getSymbolTable());
        assertEquals("InvalidExpression", config.getValidatorConfigs()
                .get(0).getConfigurationName());
        assertEquals("./foobar.dict",
                config.getValidatorConfigs().get(0).getAttribute("dict"));
        assertEquals("SentenceLength", config.getValidatorConfigs()
                .get(1).getConfigurationName());
        assertEquals("10",
                config.getValidatorConfigs().get(1).getAttribute("max_length"));
    }

    @Test
    public void testBuildConfigurationSpecifyingLanguage() {
        Configuration config = new Configuration.Builder()
                .addValidatorConfig(new ValidatorConfiguration("InvalidExpression"))
                .setSymbolTable("ja")
                .build();

        assertNotNull(config.getSymbolTable());
        assertNotNull(config.getSymbolTable().getSymbol("FULL_STOP"));
        assertEquals("。", config.getSymbolTable().getSymbol("FULL_STOP").getValue());
    }

    @Test
    public void testBuildConfigurationOverrideSymbolSetting() {
        Configuration config = new Configuration.Builder()
                .addValidatorConfig(new ValidatorConfiguration("InvalidExpression"))
                .setSymbolTable("ja")
                .setSymbol("FULL_STOP", ".")
                .build();

        assertNotNull(config.getSymbolTable());
        assertNotNull(config.getSymbolTable().getSymbol("FULL_STOP"));
        assertEquals(".", config.getSymbolTable().getSymbol("FULL_STOP").getValue());
    }

    @Test
    public void testBuildConfigurationOverrideAddInvalidSymbolSetting() {
        Configuration config = new Configuration.Builder()
                .addValidatorConfig(new ValidatorConfiguration("InvalidExpression"))
                .setSymbolTable("ja")
                .addInvalidPattern("FULL_STOP", "●")
                .build();

        assertNotNull(config.getSymbolTable());
        assertNotNull(config.getSymbolTable().getSymbol("FULL_STOP"));
        assertEquals("。", config.getSymbolTable().getSymbol("FULL_STOP").getValue());
        assertTrue(config.getSymbolTable()
                .getSymbol("FULL_STOP").getInvalidSymbols().contains("●"));
    }

    @Test
    public void testBuildConfigurationAccessingSymbolByValue() {
        Configuration config = new Configuration.Builder()
                .addValidatorConfig(new ValidatorConfiguration("InvalidExpression"))
                .setSymbolTable("ja")
                .addInvalidPattern("FULL_STOP", "●")
                .build();

        assertNotNull(config.getSymbolTable());
        assertNotNull(config.getSymbolTable().getSymbol("FULL_STOP"));
        assertEquals("。", config.getSymbolTable().getSymbol("FULL_STOP").getValue());
        assertTrue(config.getSymbolTable()
                .getSymbol("FULL_STOP").getInvalidSymbols().contains("●"));
        assertTrue(config.getSymbolTable().containsSymbolByValue("。"));
        assertTrue(config.getSymbolTable()
                .getSymbolByValue("。").getInvalidSymbols().contains("●"));
    }
}
