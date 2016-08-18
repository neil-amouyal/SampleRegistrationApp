/**
 *  test case to ensure good short(5digit) zipcodes will validate
 */

var assert = chai.assert;

describe('Function', function() {
  it('should validate a good short(5digit) zipcode', function() {
    var expected = true;
    var zipCode = "12345";
    var actual = isValidZipCode(zipCode);
    assert.equal(expected, actual);
  });
});
