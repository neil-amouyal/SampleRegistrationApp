/**
 *  test case to ensure too short zipcodes will not validate
 */

var assert = chai.assert;

describe('Function', function() {
  it('should not validate malformed(too short) zipcode', function() {
    var expected = false;
    var zipCode = "1234";
    var actual = isValidZipCode(zipCode);
    assert.equal(expected, actual);
  });
});
