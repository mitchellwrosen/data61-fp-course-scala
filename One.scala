// =============================================================================
// One
//
// A value wrapped in a class.

sealed trait One[+A]

case class MkOne[A](value: A) extends One[A]


// =============================================================================
// pure
//
// Make a One from a value. This is just giving another name to the constructor.

def pure[A](x: A): One[A] =
  ???

expect("pure #1", pure(5), MkOne(5))


// =============================================================================
// getOne
//
// Get the value inside a One.

def getOne[A](x: One[A]): A =
  ???

expect("getOne #1", getOne(pure(1)), 1)


// =============================================================================
// map
//
// Map a function over the value inside a One.

def map[A, B](f: A => B)(x: One[A]): One[B] =
  ???

expect("map #1", map(even)(pure(1)), MkOne(false))
expect("map #2", map(even)(pure(2)), MkOne(true))


// =============================================================================
// apply
//
// Apply a function inside a One to a value inside a One.

def apply[A, B](f: One[A => B])(x: One[A]): One[B] =
  ???

expect("apply #1", apply(pure(even _))(pure(1)), MkOne(false))
expect("apply #2", apply(pure(even _))(pure(2)), MkOne(true))


// =============================================================================
// bind
//
// Bind together a One and a function that takes the raw value inside the One,
// returning a new One.

def bind[A, B](x: One[A])(f: A => One[B]): One[B] =
  ???

expect("bind #1", bind(pure(1))(n => pure(even(n))), MkOne(false))
expect("bind #2", bind(pure(2))(n => pure(even(n))), MkOne(true))


// =============================================================================
// All done!

def even(n: Int): Boolean =
  n % 2 == 0

def expect[A](name: String, actual: => A, expected: A): Unit =
  try {
    if (!actual.equals(expected))
      println(s"[$name] Expected $expected but got $actual")
  } catch {
    case _: NotImplementedError =>
      println(s"[$name] not implemented")
  }
