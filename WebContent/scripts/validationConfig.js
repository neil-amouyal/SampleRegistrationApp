/**
 * This javascript module contains the validation for the form found on index.html
 * jquery is expected to be loaded already
 * 
 */


var states = [		"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "DC", "FL",
            		"GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME",
            		"MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH",
            		"NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI",
            		"SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY" ];


/**
 * isValidStateCode is a function to validate state codes against an array of supported regions.
 * Washington DC is also taken into consideration. This function was designed to be plugged into 
 * the jquery validate plugin.
 * 
 * 
 * @param inStateCode parameter input to validate
 * @returns {Boolean} true if the entered code is valid
 */
function isValidStateCode(inStateCode)
{
	var isvalid = false;
	if(inStateCode==null)
	{
		return isvalid;
	}
	var upcasedState = inStateCode.toUpperCase();
	for(var x=0; x<states.length; x+=1)
	{
		//convert to upper case
		if (upcasedState === states[x])
		{
			isvalid = true;
			break
		}
	}
	return isvalid
}

/**
 * isValidZipCode is a helper function to validate zipcodes to make sure they meet a certain format
 * XXXXX or XXXXX-XXXX where X=[0-9]
 * This function uses an 'isDigit' hack by relying on a range of characters.
 * 
 * 
 * @param inzip input zipcode for validation
 * @returns {Boolean}
 */
function isValidZipCode(inzip)
{
	if(inzip == null)
	{
		return false;
	}
	var valid_zip = true;
	var hyphenIndex = 5;
	var i = inzip.length;
	if(i !==5 && i!==10)
	{
		valid_zip = false;
		return valid_zip
	}
	for(var i=0;i<inzip.length;i+=1)
	{
		var x = inzip[i];
		if (i==hyphenIndex)
		{
			if (!(x==='-'))
			{
				valid_zip = false;
				break
			}
		}
		else
		{
			if(!((x >= '0' && x <= '9')))
			{
				valid_zip = false;
				break
			}
		}
	}
	
	return valid_zip
}


/**
 * Page Initialization, add the validation methods after helper functions loaded.
 */
$(document).ready(function () {
	if (typeof TESTMODE !== 'undefined')
	{
		return;
	}
	//add some validation functions
	jQuery.validator.addMethod("isValidState", function(value, element) {
    	return this.optional(element) || isValidStateCode(value) ;
	}, "* not a valid state code.");
	
	jQuery.validator.addMethod("isInUS", function(value, element) {
    	return this.optional(element) || (value.toUpperCase()==="US");
	}, "* not in US.");
	
	jQuery.validator.addMethod("isvalidZip", function(value, element) {
    	return this.optional(element) || isValidZipCode(value);
	}, "* not a valid zipcode.");
	
	
	/*
	 * Specify the rules, targets, and messages to be used in the validation
	 */
    $('#registry_form').validate({ // initialize the plugin
        rules: {
            firstname: {
                required: true,
                minlength: 2
            },
            lastname: {
                required: true,
                minlength: 2
            },
            adrs1: {
                required: true,
                minlength: 2
            },
            city: {
                required: true,
                minlength: 2
            },
            state: {
                required: true,
                minlength: 2,
                maxlength: 2,
                isValidState: true
            },
            country: {
            	required: true,
            	minlength: 2,
            	isInUS: true
            },
            zipcode: {
            	required: true,
            	minlength: 5,
            	maxlength: 10,
            	isvalidZip: true
            }
        },
        messages: {
        	firstname: " First Name is required. 2 Character minimum",
        	lastname: " Last Name is required. 2 Character minimum",
        	adrs1: " Primary address information is required.",
        	country: { 
        		required: " Two letter country code required.",
        		isInUS: " Sorry, only US citizens living in the US can apply at this time."
        	},
        	city: " City is required.",
        	state: {
        		required: " Please supply your 2-letter state code.",
        		isValidState: " Please enter a valid state code."
        	},
        	zipcode: {
        		required: " 5 or 9 digit Zipcode is required \[XXXXX or XXXXX-XXXX\]",
        		isvalidZip: " please enter a valid zip containing only digits and hyphens(-)"
        	}
        }
    });   
});//end init
