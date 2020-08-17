Feature: Work around on XML test data folder

Scenario: to Read the Xml files from folder inside the project in iteration & fetch the id and Doc’ID attribute values
Given file are present in folder with ".xml" extention
And I read the xml files in iteration for "id" and "docid" attribute values and "print"


Scenario: to Read the Xml files from folder inside the project in iteration & check for special characters in id and Doc’ID attribute values
Given file are present in folder with ".xml" extention
And I read the xml files in iteration for "id" and "docid" attribute values and "insert" special characters values in excel