/**
 *  test case to ensure null zipcodes will not validate, handle gracefully
 */

var assert = chai.assert;

describe('Function', function() {
  it('should not validate null zipcode', function() {
    var expected = false;
    var zipCode = null;
    var actual = isValidZipCode(zipCode);
    assert.equal(expected, actual);
  });
});
