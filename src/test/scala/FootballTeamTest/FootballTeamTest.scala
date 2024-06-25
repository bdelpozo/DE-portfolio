package de.portfolio
package FootballTeamTest

import de.portfolio.DataStructures._
import org.scalatest.funsuite.AnyFunSuite

class FootballTeamTest extends AnyFunSuite {

  test("Spanish team should play in LaLiga") {
    val team1 = SpanishTeam("Real Madrid C.F.", "Hala Madrid", "Blancas", 2020)
    assert(team1.playSpanishLeague() == "The team Real Madrid C.F. plays LaLiga")
  }
  test("Spanish team should play in the Champions League") {
    val team1 = SpanishTeam("Real Madrid C.F.", "Hala Madrid", "Blancas", 2020)
    assert(team1.playChampionsLeague() == "The team Real Madrid C.F. plays the Champions League")
  }

  // In this case, as FrenchTeam is a class we have to test the case object OlympiqueLyon

  test("Olympique Lionnais should play in Le Ligue 1") {
    val team2 = OlympiqueLyon
    assert(team2.playFrenchLeague() == "The team Olympique Lionnais plays Le Ligue 1")
  }
  test("French team should play in the Champions League") {
    val team2 = OlympiqueLyon
    assert(team2.playChampionsLeague() == "The team Olympique Lionnais plays the Champions League")
  }

}
