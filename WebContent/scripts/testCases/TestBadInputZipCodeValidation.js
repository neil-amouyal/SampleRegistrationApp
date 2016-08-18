/**
 *  test case to ensure bad short(5digit) zipcodes will not validate
 */

var assert = chai.assert;

describe('Function', function() {
  it('should not validate a bad short(5digit) zipcode', function() {
    var expected = false;
    var zipCode = "123XX";
    var actual = isValidZipCode(zipCode);
    assert.equal(expected, actual);
  });
});
