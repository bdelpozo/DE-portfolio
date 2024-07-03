package de.portfolio

// Define a trait League with a method play
trait League {
  def play(teamName: String): String
}

// Define concrete Leagues
object SpanishLeague extends League {
  def play(teamName: String): String = s"The team $teamName plays in LaLiga"
}

object FrenchLeague extends League {
  def play(teamName: String): String = s"The team $teamName plays in Le Ligue 1"
}

object ChampionsLeague extends League {
  def play(teamName: String): String = s"The team $teamName plays in the Champions League"
}

abstract class FootballTeam(val name: String, val rallyingCry: String, val nickName: String, val foundationYear: Int) {
  // Here we used the play method from League
  def play(league: League): String = league.play(name)
}

// The teams extend FootballTeam directly and can play any League
class SpanishTeam(override val name: String, override val rallyingCry: String,
                       override val nickName: String,override val foundationYear: Int)
  extends FootballTeam(name, rallyingCry, nickName, foundationYear)

class FrenchTeam(override val name: String, override val rallyingCry: String,
                      override val nickName: String,override val foundationYear: Int)
  extends FootballTeam(name, rallyingCry, nickName, foundationYear)

case object OlympiqueLyon extends FrenchTeam("Olympique Lionnais", "Pour la France", "Les Fenottes", 2004)

object FootballTeamExamples extends App {
  val barcelona = new SpanishTeam("Barcelona F.C", "Més que un club", "Culers", 2002)
  println(barcelona.play(SpanishLeague))
  println(barcelona.play(ChampionsLeague))
  println(OlympiqueLyon.play(FrenchLeague))
  println(OlympiqueLyon.play(ChampionsLeague))
}