Fix the defect and support ArrayIterator as the means to iterate over all elements.

You may note that ArrayIterator is declared as a 'package private' class which means
that only those in the package can construct objects from this class. Such a restriction
is not necessary but is just an example of the kind of visibility control you have
with classes.
