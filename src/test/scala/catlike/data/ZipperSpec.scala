package catlike.data

import org.scalatest._

class ZipperSpec extends FlatSpec with Matchers {

  val initialZipper: Zipper[Int] = Zipper(Stream(4,3,2,1), 5, Stream(6,7,8))

  "catlike.instances.StreamZipper" should "have valid focus" in {
    initialZipper.focus shouldBe 5
  }
  it should "shift focus left" in {
    initialZipper.moveLeft shouldBe Zipper(Stream(3,2,1), 4, Stream(5,6,7,8))
  }
  it should "shift focus right" in {
    initialZipper.moveRight shouldBe Zipper(Stream(5,4,3,2,1), 6, Stream(7,8))
  }
  it should "pretty print the zipper intuitively" in {
    initialZipper.prettyPrint should include("1, 2, 3, 4 (5) 6, 7, 8")
  }
  it should "create a list from the zipper intuitively" in {
    initialZipper.toList shouldBe List(1,2,3,4,5,6,7,8)
  }
  it should "duplicateLeft" in {
    val smallerZipper: Zipper[Int] = Zipper(Stream(2,1), 3, Stream(4))

    smallerZipper.duplicateLeft(identity).toList shouldBe List(
      Zipper(Stream(1), 2, Stream(3,4)),
      Zipper(Stream(), 1, Stream(2,3,4))
    )
  }
  it should "duplicateRight" in {
    val smallerZipper: Zipper[Int] = Zipper(Stream(1), 2, Stream(3,4))
    smallerZipper.duplicateRight(identity).toList shouldBe List(
      Zipper(Stream(2,1), 3, Stream(4)),
      Zipper(Stream(3,2,1),4,Stream())
    )
  }
}