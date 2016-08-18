/**
 *  test case to ensure too long names are not validated
 */

var assert = chai.assert;

describe('Function', function() {
  it('should not validate a too-long (malformed) state code: ZZZ', function() {
    var expected = false;
    var stateCode = "ZZZ";
    var actual = isValidStateCode(stateCode);
    assert.equal(expected, actual);
  });
});
