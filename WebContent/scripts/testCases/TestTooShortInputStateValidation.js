/**
 *  test case to ensure too short names are not validated
 */

var assert = chai.assert;

describe('Function', function() {
  it('should not validate a too-short (malformed) state code: Y', function() {
    var expected = false;
    var stateCode = "Y";
    var actual = isValidStateCode(stateCode);
    assert.equal(expected, actual);
  });
});
