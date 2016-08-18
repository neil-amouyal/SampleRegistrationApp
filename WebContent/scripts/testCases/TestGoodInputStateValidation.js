/**
 *  test case to ensure good state code validates okay //using colorado here
 */

var assert = chai.assert;

describe('Function', function() {
  it('should validate a good state code: CO', function() {
    var expected = true;
    var stateCode = "CO";
    var actual = isValidStateCode(stateCode);
    assert.equal(expected, actual);
  });
});
