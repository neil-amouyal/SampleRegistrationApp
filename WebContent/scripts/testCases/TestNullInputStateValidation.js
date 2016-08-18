/**
 *  test case to ensure null state codes are not validated
 */

var assert = chai.assert;

describe('Function', function() {
  it('should not validate a null state code, handle null inputs gracefully', function() {
    var expected = false;
    var stateCode = null;
    var actual = isValidStateCode(stateCode);
    assert.equal(expected, actual);
  });
});
