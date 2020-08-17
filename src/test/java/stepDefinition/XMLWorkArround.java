package stepDefinition;

import org.w3c.dom.NodeList;
import io.cucumber.java.en.Given;
import utils.Util;

public class XMLWorkArround {

	NodeList nodes;	

	@Given("file are present in folder with {string} extention")
	public void file_are_present_in_folder_with_extention(final String string) {
		Util.checkFileFormat(string);
	}

	@Given("I read the xml files in iteration for {string} and {string} attribute values and {string}")
	public void i_read_the_xml_files_in_iteration_for_and_attribute_values_and(String string, String string2, String string3) {
		Util.iterateFileAndGetValues(string, string2, string3);
	}
	
	@Given("I read the xml files in iteration for {string} and {string} attribute values and {string} special characters values in excel")
	public void i_read_the_xml_files_in_iteration_for_and_attribute_values_and_special_characters_values_in_excel(String string, String string2, String string3) {
	    Util.iterateFileAndGetValues(string, string2, string3);
	}

}
