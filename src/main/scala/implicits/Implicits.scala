package de.portfolio
package implicits

// This package is needed in order to use some Scala functionality

object Implicits {
  final case class Memory(value: MemorySize, unit: ITCapacityUnit) {
    override def toString: String = s"$value$unit"
  }

  type MemorySize     = Int
  type ITCapacityUnit = String

  implicit class IntWithMemorySize(value: MemorySize) {
    def Gb: Memory = Memory(value, "g")
    def Mb: Memory = Memory(value, "m")
    // ... add more memory units as needed
  }
}
