/**
 *  test case to ensure bad long(9digit) zipcodes will not validate
 */

var assert = chai.assert;

describe('Function', function() {
  it('should not validate a bad long(9digit) zipcode', function() {
    var expected = false;
    var zipCode = "123XX-1234X";
    var actual = isValidZipCode(zipCode);
    assert.equal(expected, actual);
  });
});
