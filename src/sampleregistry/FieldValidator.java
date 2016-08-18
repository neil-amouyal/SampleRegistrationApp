package sampleregistry;

/**
 * Class FieldValidator is used to validate data fields that are going to be inserted into
 * the registrants table of the database.
 * 
 * A statecodes String[] is used to contain the different 2-letter state codes for all 50 states, and allows also for DC.
 * Potential state-code entries are validated against this structure.
 *
 */
public class FieldValidator {
	//Acceptable State Codes (Including District of Columbia)
	private static String[] statecodes = new String[] 
	{
		"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "DC", "FL",
		"GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME",
		"MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH",
		"NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI",
		"SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"
	};
	//Acceptable Country Codes
	private static String[] countrycodes = new String[]
	{
			"US"
	};
	
	
	/**
	 * validateNameFields was designed to be used for both first and last name validation.
	 * The limits for a valid name are between 2 and 512 characters.
	 * 
	 * Even though robots probably won't register at this website, names that include numbers are permitted.
	 * The spec didn't say otherwise so I won't discriminate against robots and robotic names.
	 * @param name name parameter to validate
	 * @return true if name field is valid
	 */
	public static boolean validateNameFields(String name)
	{
		boolean status = true;
		int maxNameLength = 512;
		int minNameLength = 2;
		try {
			if(name.length() > maxNameLength){
				status = false;
			}
			if(name.isEmpty()){
				status = false;
			}
			if(name.length() < minNameLength)
			{
				status = false;
			}
			
			return status;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	/**
	 * validateCountryField is a hard-core patriotic function that will make sure the country code entered
	 * is exactly 2 characters and is the Country Code for the good old USA. This is expandable to new countries
	 * by adding them in the countrycodes array. Length of the Country code is limited to 2 characters.
	 * 
	 * 
	 * @param country country code to validate
	 * @return true if the country code entered is valid
	 */
	public static boolean validateCountryField(String country)
	{
		boolean status = true;
		int countryCodeLength = 2;
		try{
			if(country.length() != countryCodeLength)
			{
				status = false;
			}
			else if(!validCountryCode(country))
			{
				status = false;
			}
			return status;
		}
		catch (Exception e)
		{
			status = false;
			return status;
		}
	}
	
	/**
	 * validaCountryCode is a helper method to test if a country code is valid. At this point, the input
	 * is guaranteed to be 2 characters in length, so not validation on the code is done in this function.
	 * Add more valid countries to the countrycodes array to include more regions.
	 * 
	 * 
	 * @param country this variable is guaranteed to be two characters long, will be validated for contents in this function
	 * @return returns true if the code entered is accepted
	 */
	private static boolean validCountryCode(String country) 
	{
		boolean status = false;
		String upcaseCountry = country.toUpperCase();
		for(String validCountry : countrycodes)
		{
			if (upcaseCountry.equals(validCountry))
			{
				status = true;
				break;
			}
		}
		return status;
	}
	
	/**
	 * validateZipField is function to test the validity of zipcode data that is passed in.
	 * The length must be either 5 characters or 10 characters. In the case of 10 characters, the character
	 * at the 5th position must be a hyphen and all other characters must be digits. Shorter 5-character zipcodes
	 * must be all digits. 
	 * 
	 * 
	 * @param zip zipcode to test for format and contents
	 * @return returns true if the input zipcode is 5 or 9 digits long (9 digits and a hyphen)
	 */
	public static boolean validateZipField(String zip)
	{
		boolean status = true;
		int hyphenIndex = 5;
		int minLength = 5;
		int maxLength = 10;
		char delimiter = '-';
		try{
			if(zip.length() != minLength && zip.length() != maxLength)
			{
				status = false;
			}
			else
			{
				for(int i=0;i<zip.length();i++)
				{
					char current = zip.charAt(i);
					if(i == hyphenIndex)
					{
						if(current!=delimiter)
						{
							status = false;
							break;
						}
					}
					else
					{
						if(!Character.isDigit(current))
						{
							status = false;
							break;
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			status = false;
		}
		return status;
	}

	/**
	 * validateState ensures that the state code passed in is exactly 2 characters, and appears in the 
	 * string[] array of acceptable state codes via a helper function.
	 * 
	 * 
	 * @param state parameter to validate, should be 2 characters long and is a valid state code
	 * @return returns true if the state parameter is two characters and map to a real state
	 */
	public static boolean validateState(String state) {
		boolean status = true;
		int stateCodeLength = 2;
		if (state==null)
		{
			status = false;
			return status;
		}
		String stateUpcase = state.toUpperCase();
		try{
			if(state.length() != stateCodeLength)
			{
				status = false;
			}
			if(!checkStateCodeMap(stateUpcase))
			{
				status = false;
			}
			return status;
		}catch(Exception e)
		{
			return false;
		}
	}

	/**
	 * checkStateCodeMap is a helper function to see if a state code is in the statecodes array.
	 * input is guaranteed to be 2 characters long and already upper-cased. 
	 * 
	 * 
	 * @param state two character long code to validate
	 * @return true if the input state code maps to an actual state
	 */
	private static boolean checkStateCodeMap(String state) {
		boolean isValidCode = false;
		for(String code : statecodes)
		{
			if (code.equals(state))
			{
				isValidCode = true;
				break;
			}
		}
		return isValidCode;
	}
}
