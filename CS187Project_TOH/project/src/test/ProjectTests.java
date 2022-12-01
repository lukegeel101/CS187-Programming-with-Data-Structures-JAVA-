package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algorithms.*;
import hanoi.*;
import structures.*;

// these are the same as public tests
public class ProjectTests {
  private RecursiveHanoiSolver recursiveHanoiSolver;
  private RecursiveMath recMathImpl;
  private HanoiBoard hanoiBoardImpl;
  private HanoiPeg[] peg;
  private HanoiPeg hanoiPegImpl;

  @Before
  public void before() {
    // Before each test.
    recMathImpl = new RecursiveMath();
    hanoiBoardImpl = new ArrayBasedHanoiBoard(5);
    hanoiPegImpl = new StackBasedHanoiPeg();
  }


   // These tests are public tests.
   @Before
   public void setup() {
     recMathImpl = new RecursiveMath();
     if (recMathImpl == null)
       fail("Could not allocate a RecursiveMath object.");
     recursiveHanoiSolver = new RecursiveHanoiSolver();
     peg = new HanoiPeg[3];
     peg[0] = new StackBasedHanoiPeg();
     peg[1] = new StackBasedHanoiPeg();
     peg[2] = new StackBasedHanoiPeg();
     if(peg[0] == peg[1] || peg[1] == peg[2] || peg[0] == peg[2])
       fail("The StackBasedHanoiPeg should return a new copy of HanoiPeg each time it is called.");
   }

   @Test(timeout = 5000)
   public void testIsEvenPublic() {
     assertTrue(recMathImpl.isEven(4));
     assertFalse(recMathImpl.isEven(1));
     assertTrue(recMathImpl.isEven(12));
     assertTrue(recMathImpl.isEven(-42));
     assertFalse(recMathImpl.isEven(-13));
   }

   @Test(timeout = 5000)
   public void testIsOddPublicPublic() {
     assertFalse(recMathImpl.isOdd(4));
     assertTrue(recMathImpl.isOdd(1));
     assertFalse(recMathImpl.isOdd(12));
     assertFalse(recMathImpl.isOdd(-42));
     assertTrue(recMathImpl.isOdd(-13));
   }

   @Test(timeout = 5000)
   public void testSumNPublic() {
     assertEquals(0, recMathImpl.sumN(0));
     assertEquals(1, recMathImpl.sumN(1));
     assertEquals(3, recMathImpl.sumN(2));
     assertEquals(6, recMathImpl.sumN(3));
     assertEquals(10, recMathImpl.sumN(4));
     assertEquals(15, recMathImpl.sumN(5));
     assertEquals(21, recMathImpl.sumN(6));
     assertEquals(5050, recMathImpl.sumN(100));
   }

   @Test(timeout = 5000, expected = IllegalArgumentException.class)
   public void testSumNExceptionPublic() {
     recMathImpl.sumN(-1);
   }

   @Test(timeout = 5000)
   public void testFactorialPublic() {
     assertEquals(1, recMathImpl.factorial(1));
     assertEquals(2, recMathImpl.factorial(2));
     assertEquals(6, recMathImpl.factorial(3));
     assertEquals(24, recMathImpl.factorial(4));
     assertEquals(3628800, recMathImpl.factorial(10));
   }

   @Test(timeout = 5000, expected = IllegalArgumentException.class)
   public void testFactorialExceptionPublic() {
     recMathImpl.factorial(-1);
   }

   @Test(timeout = 5000)
   public void testBiPowerPublic() {
     assertEquals(1, recMathImpl.biPower(0));
     assertEquals(2, recMathImpl.biPower(1));
     assertEquals(4, recMathImpl.biPower(2));
     assertEquals(0x1 << 20, recMathImpl.biPower(20));
   }

   @Test(timeout = 5000, expected = IllegalArgumentException.class)
   public void testBiPowerExceptionPublic() {
     recMathImpl.biPower(-5);
   }

   @Test(timeout = 5000, expected = IllegalArgumentException.class)
  public void testSetupExceptionPublic() {
  	hanoiBoardImpl = new ArrayBasedHanoiBoard(-1);
  }

  @Test(timeout = 5000)
  public void testSetupAndIsLegalMovePublic() {
  	hanoiBoardImpl = new ArrayBasedHanoiBoard(5);

  	HanoiMove move = new HanoiMove(0, 1);
  	assertTrue(hanoiBoardImpl.isLegalMove(move));

  	move = new HanoiMove(0, 2);
  	assertTrue(hanoiBoardImpl.isLegalMove(move));

  	move = new HanoiMove(0, 0);
  	assertFalse("You can't move a ring on top of itself.",
  			hanoiBoardImpl.isLegalMove(move));

  	move = new HanoiMove(1, 0);
  	assertFalse("You cannot move from a ring that contains no rings.",
  			hanoiBoardImpl.isLegalMove(move));

  	move = new HanoiMove(2, 0);
  	assertFalse("You cannot move from a ring that contains no rings.",
  			hanoiBoardImpl.isLegalMove(move));

  }

  @Test(timeout = 5000)
  public void testSetupIsLegalMoveAndDoMovePublic() {
  	hanoiBoardImpl = new ArrayBasedHanoiBoard(5);

  	HanoiMove move = new HanoiMove(0, 1);
  	assertTrue(hanoiBoardImpl.isLegalMove(move));
  	hanoiBoardImpl.doMove(move);

  	move = new HanoiMove(0, 1);
  	assertFalse("Ring is too large.", hanoiBoardImpl.isLegalMove(move));

  	move = new HanoiMove(0, 2);
  	assertTrue(hanoiBoardImpl.isLegalMove(move));
  	hanoiBoardImpl.doMove(move);

  	move = new HanoiMove(1, 2);
  	assertTrue(hanoiBoardImpl.isLegalMove(move));
  	hanoiBoardImpl.doMove(move);

  	move = new HanoiMove(0, 2);
  	assertFalse("Ring is too large.", hanoiBoardImpl.isLegalMove(move));

  	move = new HanoiMove(0, 1);
  	hanoiBoardImpl.doMove(move);

  	move = new HanoiMove(0, 2);
  	assertFalse("Ring is too large.", hanoiBoardImpl.isLegalMove(move));

  	move = new HanoiMove(0, 1);
  	assertFalse("Ring is too large.", hanoiBoardImpl.isLegalMove(move));

  	move = new HanoiMove(2, 1);
  	assertTrue(hanoiBoardImpl.isLegalMove(move));
  	hanoiBoardImpl.doMove(move);

  	move = new HanoiMove(2, 0);
  	assertTrue(hanoiBoardImpl.isLegalMove(move));
  	hanoiBoardImpl.doMove(move);

  	move = new HanoiMove(1, 0);
  	assertTrue(hanoiBoardImpl.isLegalMove(move));
  	hanoiBoardImpl.doMove(move);

  	move = new HanoiMove(1, 2);
  	assertTrue(hanoiBoardImpl.isLegalMove(move));
  	hanoiBoardImpl.doMove(move);

  	move = new HanoiMove(0, 1);
  	assertTrue(hanoiBoardImpl.isLegalMove(move));
  	hanoiBoardImpl.doMove(move);

  	move = new HanoiMove(0, 2);
  	assertTrue(hanoiBoardImpl.isLegalMove(move));
  	hanoiBoardImpl.doMove(move);

  	move = new HanoiMove(1, 2);
  	assertTrue(hanoiBoardImpl.isLegalMove(move));
  	hanoiBoardImpl.doMove(move);

  	move = new HanoiMove(0, 2);
  	assertFalse("Ring is too large.", hanoiBoardImpl.isLegalMove(move));

  }

  @Test(timeout = 5000, expected = NullPointerException.class)
  public void testIsLegalMoveExceptionPublic() {
  	hanoiBoardImpl.isLegalMove(null);
  }

  @Test(timeout = 5000, expected = IllegalHanoiMoveException.class)
  public void testDoIllegalMove1Public() {
  	hanoiBoardImpl = new ArrayBasedHanoiBoard(0);
  	HanoiMove move = new HanoiMove(0, 1);
  	hanoiBoardImpl.doMove(move);
  }

  @Test(timeout = 5000, expected = IllegalHanoiMoveException.class)
  public void testDoIllegalMove2Public() {
  	hanoiBoardImpl = new ArrayBasedHanoiBoard(2);
  	HanoiMove move = new HanoiMove(0, 1);
  	hanoiBoardImpl.doMove(move);
  	move = new HanoiMove(0, 1);
  	hanoiBoardImpl.doMove(move);
  }

  @Test(timeout = 5000, expected = NullPointerException.class)
     public void testDoIllegalMove3Public() {
  	hanoiBoardImpl = new ArrayBasedHanoiBoard(0);
  	hanoiBoardImpl.doMove(null);
  }

  @Test(timeout = 5000)
  public void testSetupDoMoveAndIsSolvedPublic() {
  	hanoiBoardImpl = new ArrayBasedHanoiBoard(0);
  	assertTrue(hanoiBoardImpl.isSolved());

  	hanoiBoardImpl = new ArrayBasedHanoiBoard(1);
  	assertFalse(hanoiBoardImpl.isSolved());
  	hanoiBoardImpl.doMove(new HanoiMove(0, 2));
  	assertTrue(hanoiBoardImpl.isSolved());

  	hanoiBoardImpl = new ArrayBasedHanoiBoard(2);
  	hanoiBoardImpl.doMove(new HanoiMove(0, 1));
  	assertFalse(hanoiBoardImpl.isSolved());
  	hanoiBoardImpl.doMove(new HanoiMove(0, 2));
  	assertFalse(hanoiBoardImpl.isSolved());
  	hanoiBoardImpl.doMove(new HanoiMove(1, 2));
  	assertTrue(hanoiBoardImpl.isSolved());
   }

   @Test(timeout = 5000)
  public void testPegPublic() {
  	assertFalse("Peg should be empty.", hanoiPegImpl.hasRings());

  	HanoiRing fiveRing = new HanoiRing(5);
  	hanoiPegImpl.addRing(fiveRing);
  	assertTrue("Peg should not be empty.", hanoiPegImpl.hasRings());
  	assertEquals("The top element we just put on top.", fiveRing,
     hanoiPegImpl.getTopRing());

  	HanoiRing fourRing = new HanoiRing(4);
  	hanoiPegImpl.addRing(fourRing);
  	assertEquals("The top element should be fourRing", fourRing,
     hanoiPegImpl.getTopRing());

  	HanoiRing top = hanoiPegImpl.remove();
  	assertEquals("The removed element should be fourRing", fourRing, top);
  	assertEquals("The top element should be fiveRing", fiveRing,
     hanoiPegImpl.getTopRing());
  	assertTrue("The peg should not be empty.", hanoiPegImpl.hasRings());

  	top = hanoiPegImpl.remove();
  	assertEquals("The removed element should be fiveRing", fiveRing, top);
  	assertFalse("The peg should be empty.", hanoiPegImpl.hasRings());
  }

  @Test(timeout = 5000, expected = IllegalHanoiMoveException.class)
  public void testIllegalRemovePublic() {
  	hanoiPegImpl.remove();
  }

  @Test(timeout = 5000, expected = IllegalHanoiMoveException.class)
  public void testIllegalAddPublic() {
  	hanoiPegImpl.addRing(new HanoiRing(5));
  	hanoiPegImpl.addRing(new HanoiRing(6));
  }

  @Test(timeout = 5000, expected = IllegalHanoiMoveException.class)
  public void testIllegalAdd2Public() {
  	hanoiPegImpl.addRing(new HanoiRing(5));
  	hanoiPegImpl.addRing(new HanoiRing(5));
   }

   @Test(timeout = 5000)
  public void testGetNumberOfRingsPublic() {
  	HanoiSolution solution = recursiveHanoiSolver.solve(0);
  	assertEquals(0, solution.getNumberOfRings());

  	solution = recursiveHanoiSolver.solve(1);
  	assertEquals(1, solution.getNumberOfRings());

  	solution = recursiveHanoiSolver.solve(2);
  	assertEquals(2, solution.getNumberOfRings());

  	solution = recursiveHanoiSolver.solve(3);
  	assertEquals(3, solution.getNumberOfRings());

  	solution = recursiveHanoiSolver.solve(10);
  	assertEquals(10, solution.getNumberOfRings());
  }

  @Test(timeout = 5000, expected = IllegalArgumentException.class)
  public void testExceptionPublic() {
  	recursiveHanoiSolver.solve(-5);
  }

  private boolean isSolution(ListInterface<HanoiMove> moves) {
  	for (HanoiMove move : moves) {
  		int from = move.getFromPeg();
  		int to = move.getToPeg();
  		HanoiRing ring = peg[from].remove();
  		peg[to].addRing(ring);
  	}
  	return !(peg[0].hasRings()) && !(peg[1].hasRings());
  }

  @Test(timeout = 5000)
  public void testSolveSize0Public() {
  	// This test assumes your HanoiPeg implementation is correct.
  	HanoiSolution solution = recursiveHanoiSolver.solve(0);
  	ListInterface<HanoiMove> moves = solution.getMoves();
  	assertTrue(isSolution(moves));
  }

  @Test(timeout = 5000)
  public void testSolveSize1Public() {
  	// This test assumes your HanoiPeg implementation is correct.
  	peg[0].addRing(new HanoiRing(1));
  	HanoiSolution solution = recursiveHanoiSolver.solve(1);
  	ListInterface<HanoiMove> moves = solution.getMoves();
  	assertTrue(isSolution(moves));
  }

  @Test(timeout = 5000)
  public void testSolveSize2Public() {
  	// This test assumes your HanoiPeg implementation is correct.
  	peg[0].addRing(new HanoiRing(2));
  	peg[0].addRing(new HanoiRing(1));
  	HanoiSolution solution = recursiveHanoiSolver.solve(2);
  	ListInterface<HanoiMove> moves = solution.getMoves();
  	assertTrue(isSolution(moves));
  }

  @Test(timeout = 5000)
  public void testSolveSize3Public() {
  	// This test assumes your HanoiPeg implementation is correct.
  	peg[0].addRing(new HanoiRing(3));
  	peg[0].addRing(new HanoiRing(2));
  	peg[0].addRing(new HanoiRing(1));
  	HanoiSolution solution = recursiveHanoiSolver.solve(3);
  	ListInterface<HanoiMove> moves = solution.getMoves();
  	assertTrue(isSolution(moves));
  }

  @Test(timeout = 5000)
  public void testSolveSize4Public() {
  	// This test assumes your HanoiPeg implementation is correct.
  	peg[0].addRing(new HanoiRing(4));
  	peg[0].addRing(new HanoiRing(3));
  	peg[0].addRing(new HanoiRing(2));
  	peg[0].addRing(new HanoiRing(1));
  	HanoiSolution solution = recursiveHanoiSolver.solve(4);
  	ListInterface<HanoiMove> moves = solution.getMoves();
  	assertTrue(isSolution(moves));
  }

  @Test(timeout = 5000)
  public void testSolveSize15Public() {
  	// This test assumes your HanoiPeg implementation is correct.
  	peg[0].addRing(new HanoiRing(15));
  	peg[0].addRing(new HanoiRing(14));
  	peg[0].addRing(new HanoiRing(13));
  	peg[0].addRing(new HanoiRing(12));
  	peg[0].addRing(new HanoiRing(11));
  	peg[0].addRing(new HanoiRing(10));
  	peg[0].addRing(new HanoiRing(9));
  	peg[0].addRing(new HanoiRing(8));
  	peg[0].addRing(new HanoiRing(7));
  	peg[0].addRing(new HanoiRing(6));
  	peg[0].addRing(new HanoiRing(5));
  	peg[0].addRing(new HanoiRing(4));
  	peg[0].addRing(new HanoiRing(3));
  	peg[0].addRing(new HanoiRing(2));
  	peg[0].addRing(new HanoiRing(1));
  	HanoiSolution solution = recursiveHanoiSolver.solve(15);
  	ListInterface<HanoiMove> moves = solution.getMoves();
  	assertTrue(isSolution(moves));
  }

}
