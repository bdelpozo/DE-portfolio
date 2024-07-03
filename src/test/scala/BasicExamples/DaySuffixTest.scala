import de.portfolio.BasicExamples.BasicFunctions.suffixForDay
import org.scalatest.funsuite.AnyFunSuite

class DaySuffixTest extends AnyFunSuite {


  test("suffixForDay") {
    assert(suffixForDay(1) === "st")
    assert(suffixForDay(2) === "nd")
    assert(suffixForDay(3) === "rd")
    assert(suffixForDay(4) === "th")
    assert(suffixForDay(11) === "th")
    assert(suffixForDay(21) === "st")
    assert(suffixForDay(22) === "nd")
    assert(suffixForDay(31) === "st")

  }
  test("TODO: with no valid day numbers"){
    // TODO: Is this the expected behavior?
    assert(suffixForDay(41) === "th")
  }

  test("TODO: suffixForDay with negative numbers") {
    // TODO: Is this the expected behavior?
    assert(suffixForDay(-1) === "th")
    assert(suffixForDay(-2) === "th")
    assert(suffixForDay(-3) === "th")
    assert(suffixForDay(-4) === "th")
    assert(suffixForDay(-11) === "th")
    assert(suffixForDay(-21) === "th")
    assert(suffixForDay(-22) === "th")
    assert(suffixForDay(-31) === "th")
    assert(suffixForDay(-41) === "th")
  }
  test("TODO: suffixForDay with zero") {
    // TODO: Is this the expected behavior?
    assert(suffixForDay(0) === "th")
  }

  test("TODO: suffixForDay with big numbers") {
    // TODO: Is this the expected behavior?
    assert(suffixForDay(100) === "th")
    assert(suffixForDay(101) === "th")
    assert(suffixForDay(102) === "th")
    assert(suffixForDay(103) === "th")
    assert(suffixForDay(104) === "th")
    assert(suffixForDay(111) === "th")
    assert(suffixForDay(121) === "th")
    assert(suffixForDay(122) === "th")
    assert(suffixForDay(131) === "th")
    assert(suffixForDay(141) === "th")
  }
}