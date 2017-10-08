// =============================================================================
// Validation
//
// Either a value, or an error string.

sealed trait Validation[+A]

case class Value[A](value: A) extends Validation[A]
case class Error[A](error: String) extends Validation[A]


// =============================================================================
// isError
//
// Is this Validation an Error?

def isError[A](x: Validation[A]): Boolean =
  ???

expect("isError #1", isError(Error("hello")), true)
expect("isError #2", isError(Value(5)), false)


// =============================================================================
// isValue
//
// Is this Validation a Value?

def isValue[A](x: Validation[A]): Boolean =
  ???

expect("isValue #1", isValue(Error("hello")), false)
expect("isValue #2", isValue(Value(5)), true)


// =============================================================================
// pure
//
// Make a Validation from a value.

def pure[A](x: A): Validation[A] =
  ???

expect("pure #1", pure(5), Value(5))


// =============================================================================
// valueOr
//
// Get the value inside a Validation, or if it's an Error, return the given
// default value.

def valueOr[A](x: Validation[A])(y: A): A =
  ???

expect("valueOr #1", valueOr(Value(1))(2), 1)
expect("valueOr #2", valueOr(Error("hello"))(2), 2)


// =============================================================================
// errorOr
//
// Get the error inside a Validation, or if it's a Value, return the given
// default error.

def errorOr[A](x: Validation[A])(y: String): String =
  ???

expect("errorOr #1", errorOr(Value(1))("goodbye"), "goodbye")
expect("errorOr #2", errorOr(Error("hello"))("goodbye"), "hello")


// =============================================================================
// map
//
// Map a function over a Validation's value.

def map[A, B](f: A => B)(x: Validation[A]): Validation[B] =
  ???

expect("map #1", map(even)(Value(1)), Value(false))
expect("map #2", map(even)(Value(2)), Value(true))
expect("map #3", map(even)(Error("hello")), Error("hello"))


// =============================================================================
// apply
//
// Apply a function inside a Validation to a value inside a Validation. If both
// Validations contain errors, just return the first one.

def apply[A, B](f: Validation[A => B])(x: Validation[A]): Validation[B] =
  ???


expect("apply #1", apply(Value(even _))(Value(1)), Value(false))
expect("apply #2", apply(Value(even _))(Error("hi")), Error("hi"))
expect("apply #3", apply(Error("hi"))(Error("there")), Error("hi"))


// =============================================================================
// bind
//
// Bind together a Validation and a function that takes the value inside the
// Validation, returning a new Validation.

def bind[A, B](x: Validation[A])(f: A => Validation[B]): Validation[B] =
  ???

expect("bind #1", bind(Value(1))(n => Value(n+1)), Value(2))
expect("bind #2", bind(Error("hi"))((n: Int) => Value(n+1)), Error("hi"))


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
