## Bug 1
Brief description:  Constructor hardcodes memory to 16.
Failed unit test: testConstructorMemoryAssignment()

## Bug 2
Brief description:  Constructor hardcodes price to 0.
Failed unit test: testConstructorPriceAssignment()

## Bug 3
Brief description:  setOS() hardcodes OS to "None" regardless of input.
Failed unit test: testSetOSAssignment()

## Bug 4
Brief description:  Constructor adds a MacBook to inventory by default.
Failed unit test: testInitialInventoryEmpty()

## Bug 5
Brief description:  buy() overwrites input with a hardcoded MacBook object.
Failed unit test: testBuyAddsSpecificInstance()

## Bug 6
Brief description:  buy() lacks a duplicate check/exception.
Failed unit test: testBuyDuplicateThrowsException()

## Bug 7
Brief description:  sell() lacks a check for whether the item exists.
Failed unit test: testSellMissingThrowsException()

## Bug 8
Brief description:  printInventory() loop condition i <= size causes crash.
Failed unit test: testPrintInventoryLoopBounds()

## Bug 9
Brief description:  refurbish() compares OS strings using != instead of .equals().
Failed unit test: testRefurbishStringComparison()

## Bug 10
Brief description:  refurbish() assigns a typo price of $2500 to 2000-2011 models.
Failed unit test: testRefurbishPriceTypo()