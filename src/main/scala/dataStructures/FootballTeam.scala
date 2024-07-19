package de.portfolio
package dataStructures

// This pretends to be an example of the use of abstract class, case class, class, case object and traits

// We can start creating the class FootballTeam, with some attributes:
abstract class FootballTeam(val name: String) {
    val rallyingCry: String
    val nickName: String
    val foundationYear: Int
}
// Knowing that we are going to classify teams by their origin country, it makes sense to create traits for
//  the championships they will play having this into account.

// a trait for the Spanish League
trait SpanishLeague extends FootballTeam{
    def playSpanishLeague(): String = s"The team $name plays LaLiga"
}

// a trait for the French League
trait FrenchLeague extends FootballTeam{
    def playFrenchLeague(): String = s"The team $name plays Le Ligue 1"
}

// a trait for the Champions League that will have both Spanish and French teams
trait ChampionsLeague extends FootballTeam {
    def playChampionsLeague(): String = s"The team $name plays the Champions League"
}

// Now we create the case class SpanishTeam that extends FootballTeam with the traits SpanishLeague and ChampionsLeague
case class SpanishTeam(override val name: String, override val rallyingCry: String, override val nickName: String,
                        override val foundationYear: Int)
  extends FootballTeam(name) with SpanishLeague with ChampionsLeague

// And we create a class of FrenchTeam (not a case class because we want to declare a case object of this one)
class FrenchTeam(override val name: String, override val rallyingCry: String, override val nickName: String,
                       override val foundationYear: Int)
  extends FootballTeam(name) with FrenchLeague with ChampionsLeague

// case object to represent the french team Olympique Lionnais
case object OlympiqueLyon extends FrenchTeam("Olympique Lionnais", "Pour la France", "Les Fenottes", 2004)

// The next piece of code is commented because: case-to-case inheritance is prohibited and we would have to use extractors
//case object Barcelona extends SpanishTeam("Barcelona F.C.", "Més que un club", "Culers", 2002)

// Otherwise, in this example it's preferred to declare real teams in the form Barcelona is because, its class is SpanishTeam
//  meanwhile the class of the team Olympique Lionnais is OlympiqueLyon because og inheritance

object FootballTeamExamples extends App {
    val barcelona = SpanishTeam("Barcelona F.C.", "Més que un club", "Culers", 2002)
    println(barcelona.playSpanishLeague())
    println(barcelona.playChampionsLeague())

    println(OlympiqueLyon.playFrenchLeague())
    println(OlympiqueLyon.playChampionsLeague())

}

