package de.portfolio
package basicExamples

import org.scalatest.funsuite.AnyFunSuite
import BasicExamples.{addDatePreffix, isSt, isNd, isRd, addPreffixtoDate}

class AddPreffixTest extends AnyFunSuite {
  // In this class we are going to implement our first test.


  // Function addDatePreffix and addPreffixtoDate will use the same test because they have the same functionality

  test("isSt should return st if it's 1st, 21st or 31st") {
    assert(isSt(21).get == "st")
    assert(isSt(22) == None)
  }

  test("isNd should return nd if it's 2nd or 22nd") {
    assert(isNd(22).get == "nd")
    assert(isNd(23) == None)
  }

  test("isRd should return rd if it's 3rd or 23rd") {
    assert(isRd(23).get == "rd")
    assert(isRd(22) == None)
  }

  // Function addDatePreffix and addPreffixtoDate will use the same test because they have the same functionality

  test("addPreffixtoDate should return the right preffix for each day") {
    assert(addPreffixtoDate(1) == "st")
    assert(addPreffixtoDate(22) == "nd")
    assert(addPreffixtoDate(23) == "rd")
    assert(addPreffixtoDate(4) == "th")
    assert(addPreffixtoDate(21) == "st")
    assert(addPreffixtoDate(11) == "th")
  }
}
