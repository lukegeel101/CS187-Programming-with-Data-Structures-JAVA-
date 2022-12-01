package test;

import structures.*;
import tokenring.*;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class ProjectTests {
  // We are not giving you many tests for this project. Spend some
  // time writing your own tests. Make sure to focus on edge cases
  // and think about how you can make your code break. It is often
  // helpful to write your tests before you begin writing your
  // solution code.

  @Test(timeout = 100)
  public void testNewList() {
    DoublyCircularLinkedList<Integer> list = new DoublyCircularLinkedListImplementation<Integer>();
  }

  @Test(timeout = 100)
  public void testNewTokenRing() {
    NetworkInterface nic1 = new NetworkInterface();
    Workstation w1 = new WorkstationImplementation(nic1);
    NetworkInterface nic2 = new NetworkInterface();
    Workstation w2 = new WorkstationImplementation(nic2);
    Engine e = new Engine();
    w1.setEngine(e);
    w2.setEngine(e);
  }

}
