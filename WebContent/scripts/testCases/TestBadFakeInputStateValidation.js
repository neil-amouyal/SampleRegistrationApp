/**
 *  test case to ensure bad state code does not validate 
 */

var assert = chai.assert;

describe('Function', function() {
  it('should not validate a bad state code: XX', function() {
    var expected = false;
    var stateCode = "XX";
    var actual = isValidStateCode(stateCode);
    assert.equal(expected, actual);
  });
});
