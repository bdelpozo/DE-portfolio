package de.portfolio
package datastructures

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.BeforeAndAfter

class FootballTeamTests extends AnyFunSuite with BeforeAndAfter {

  var barcelona: SpanishTeam = _
  var olympiqueLyon: FrenchTeam = _

  before {
    barcelona = new SpanishTeam("Barcelona F.C", "Més que un club", "Culers", 2002)
    olympiqueLyon = OlympiqueLyon
  }

  test("Spanish league play") {
    assert(barcelona.play(SpanishLeague) == "The team Barcelona F.C plays in LaLiga")
  }

  test("Champions league play for Spanish team") {
    assert(barcelona.play(ChampionsLeague) == "The team Barcelona F.C plays in the Champions League")
  }

  test("French league play") {
    assert(olympiqueLyon.play(FrenchLeague) == "The team Olympique Lionnais plays in Le Ligue 1")
  }

  test("Champions league play for French team") {
    assert(olympiqueLyon.play(ChampionsLeague) == "The team Olympique Lionnais plays in the Champions League")
  }
}