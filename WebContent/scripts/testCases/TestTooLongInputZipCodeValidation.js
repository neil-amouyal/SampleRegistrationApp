/**
 *  test case to ensure too long zipcodes will not validate
 */

var assert = chai.assert;

describe('Function', function() {
  it('should not validate malformed(too long) zipcode', function() {
    var expected = false;
    var zipCode = "12345-987654321";
    var actual = isValidZipCode(zipCode);
    assert.equal(expected, actual);
  });
});
