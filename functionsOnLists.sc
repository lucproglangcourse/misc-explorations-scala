/*
 * data List[A] = Nil | A :: List[A]
 *
 * In the context of the org chart example, we already saw uses of the
 * Seq.map and Seq.fold/sum functions.
 *
 * - map applies a function to each item in a list
 *
 * - foldLeft successively applies a function to the partial result
 *   accumulated so far and the next list item
 *
 * - There is also foldRight, which does the same thing but starting
 *   from the back of the list. This makes a difference unless the function
 *   is commutative (which also implies that both of its arguments are of
 *   the same type).
 *   When used with lists, foldRight usually does things in the right order
 *   but is not tail-recursive, so its recursive call stack might grow very
 *   large.
 *
 *  - filter keeps only those items that satisfy the given predicate
 *    (boolean function).
 */

assert { 1 :: 2 :: 3 :: Nil == List(1, 2, 3) }

def reverse[A]: List[A] => List[A] = {
  case Nil => Nil
  case h :: t => reverse(t) :+ h
}

/** DRY */
def test(rev: List[Int] => List[Int]) = {
  assert { rev(Nil) == Nil }
  assert { rev((1 to 5).toList) == (5 to 1 by -1).toList }
}

test { reverse }

def reverseAsFoldLeft[A](xs: List[A]) =
  xs.foldLeft(Nil: List[A]) {
    (r, x) => x :: r // same as +:
  }

test { reverseAsFoldLeft }

def reverseAsFoldRight[A](xs: List[A]) =
  xs.foldRight(Nil: List[A]) {
    (x, r) => r :+ x // mirror image of +:
  }

test { reverseAsFoldRight }

/** Because it is tail-recursive, this version of `reverse` runs in constant space. */
@scala.annotation.tailrec
def reverseAcc[A](curr: List[A], acc: List[A] = Nil): List[A] = curr match {
  case Nil => acc
  case h :: t => reverseAcc(t, h :: acc)
}

test { reverseAcc(_) }

def reverseWhile[A](xs: List[A]): List[A] = {
  var acc = List.empty[A]
  var curr = xs
  while (curr != Nil) {
    acc = curr.head :: acc // same as acc ::= curr.head
    curr = curr.tail
  }
  acc
}

test { reverseWhile }


// Up for a challenge? Try implementing these functions yourself and test
// if they behave like the predefined ones!

// TODO map
// TODO mapAsFold
// TODO foldLeft
// TODO append

println("■")
