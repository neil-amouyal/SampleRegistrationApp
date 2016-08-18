package testing;
import sampleregistry.*;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ServerSideValidationTesting {
	
	
	@Test
	public void testGoodNameValidation() 
	{
		String goodNameCandidate = "Neil";
		boolean expected = true;  //true because Neil should meet all criteria
		boolean result = FieldValidator.validateNameFields(goodNameCandidate);
		Assert.assertEquals(expected, result, "Test failed, couldn't validate a good name input.");
	}
	
	@Test
	public void testNullNameValidation() 
	{
		String nullNameCandidate = null;
		boolean expected = false;  
		boolean result = FieldValidator.validateNameFields(nullNameCandidate);
		Assert.assertEquals(expected, result, "Test failed, null name input didn't fail validation.");
	}
	
	
	@Test
	public void testTooLongNameValidation() 
	{
		String tooLongNameCandidate = "Harold";
		for(int i : new int[] {1,2,3,4,5,6,7,8,9,10,11,12})
			tooLongNameCandidate = tooLongNameCandidate + tooLongNameCandidate;
		boolean expected = false;  
		boolean result = FieldValidator.validateNameFields(tooLongNameCandidate);
		Assert.assertEquals(expected, result, "Test failed, name should be too long to validate.");
	}
	
	@Test
	public void testTooShortNameValidation() 
	{
		String shortNameCandidate = "Z";
		boolean expected = false;  
		boolean result = FieldValidator.validateNameFields(shortNameCandidate);
		Assert.assertEquals(expected, result, "Test failed, name should be too short to validate.");
	}
	
	@Test
	public void testGoodCountryCodeValidation() 
	{
		String countryCode = "US";
		boolean expected = true;  
		boolean result = FieldValidator.validateCountryField(countryCode);
		Assert.assertEquals(expected, result, "Test failed, country code US should validate fine.");
	}
	
	@Test
	public void testBadCountryCodeValidation() 
	{
		String countryCode = "JA";
		boolean expected = false;  
		boolean result = FieldValidator.validateCountryField(countryCode);
		Assert.assertEquals(expected, result, "Test failed, country code JA should not validate.");
	}
	
	@Test
	public void testTooLongCountryCodeValidation() 
	{
		String countryCode = "JAPAN";
		boolean expected = false;  
		boolean result = FieldValidator.validateCountryField(countryCode);
		Assert.assertEquals(expected, result, "Test failed, country code JAPAN is too long and should not validate.");
	}
	
	@Test
	public void testTooShortCountryCodeValidation() 
	{
		String countryCode = "U";
		boolean expected = false;  
		boolean result = FieldValidator.validateCountryField(countryCode);
		Assert.assertEquals(expected, result, "Test failed, country code U is too short to validate.");
	}
	
	@Test
	public void testNullCountryCodeValidation() 
	{
		String countryCode = null;
		boolean expected = false;  
		boolean result = FieldValidator.validateCountryField(countryCode);
		Assert.assertEquals(expected, result, "Test failed, null country code should not validate.");
	}
	
	//validateZipField
	
	@Test
	public void testGoodSimpleZipCodeValidation() 
	{
		String zip = "12345";
		boolean expected = true;  
		boolean result = FieldValidator.validateZipField(zip);
		Assert.assertEquals(expected, result, "Test failed, good zip code failed validation.");
	}
	
	@Test
	public void testGoodCompoundZipCodeValidation() 
	{
		String zip = "12345-1234";
		boolean expected = true;  
		boolean result = FieldValidator.validateZipField(zip);
		Assert.assertEquals(expected, result, "Test failed, good zip code failed validation.");
	}
	
	@Test
	public void testBadZipCodeValidation() 
	{
		String zip = "1XXX6";
		boolean expected = false;  
		boolean result = FieldValidator.validateZipField(zip);
		Assert.assertEquals(expected, result, "Test failed, malformed zipcode passed validation.");
	}
	
	@Test
	public void testShortBadZipCodeValidation() 
	{
		String zip = "12";
		boolean expected = false;  
		boolean result = FieldValidator.validateZipField(zip);
		Assert.assertEquals(expected, result, "Test failed, less than 5 character zipcode passed validation.");
	}
	
	@Test
	public void testShortLongZipCodeValidation() 
	{
		String zip = "123456789123456789";
		boolean expected = false;  
		boolean result = FieldValidator.validateZipField(zip);
		Assert.assertEquals(expected, result, "Test failed, zipcode of length 18 characters passed validation.");
	}
	
	@Test
	public void testNullZipCodeValidation() 
	{
		String zip = null;
		boolean expected = false;  
		boolean result = FieldValidator.validateZipField(zip);
		Assert.assertEquals(expected, result, "Test failed, null zipcode passed validation.");
	}
	
	
	@Test
	public void testGoodStateCodeValidation() 
	{
		String state = "MI";
		boolean expected = true;  
		boolean result = FieldValidator.validateState(state);
		Assert.assertEquals(expected, result, "Test failed, known good statecode failed validation.");
	}
	
	
	@Test
	public void testBadStateCodeValidation() 
	{
		String state = "XX";
		boolean expected = false;  
		boolean result = FieldValidator.validateState(state);
		Assert.assertEquals(expected, result, "Test failed, bad statecode passed validation.");
	}
	
	@Test
	public void testShortStateCodeValidation() 
	{
		String state = "X";
		boolean expected = false;  
		boolean result = FieldValidator.validateState(state);
		Assert.assertEquals(expected, result, "Test failed, malformed statecode passed validation.");
	}
	
	
	@Test
	public void testLongStateCodeValidation() 
	{
		String state = "ZZZ";
		boolean expected = false;  
		boolean result = FieldValidator.validateState(state);
		Assert.assertEquals(expected, result, "Test failed, malformed statecode passed validation.");
	}
	
	@Test
	public void testNullStateCodeValidation() 
	{
		String state = null;
		boolean expected = false;  
		boolean result = FieldValidator.validateState(state);
		Assert.assertEquals(expected, result, "Test failed, null statecode passed validation.");
	}
}
