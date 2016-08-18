/**
 *  test case to ensure good long(9digit) zipcodes will validate
 */

var assert = chai.assert;

describe('Function', function() {
  it('should validate a good long(9digit) zipcode', function() {
    var expected = true;
    var zipCode = "12345-1234";
    var actual = isValidZipCode(zipCode);
    assert.equal(expected, actual);
  });
});
