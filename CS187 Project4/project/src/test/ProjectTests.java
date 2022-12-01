package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import filesystem.LevelOrderIterator;
import sorting.MergeSorter;
import structures.Queue;

public class ProjectTests {
  //
  // LevelOrderIterator Tests
  //
  @Rule public Timeout timeout = new Timeout(10L, TimeUnit.SECONDS);

  File tempFile;
  LevelOrderIterator singleIterator;

  File tempDir;
  LevelOrderIterator nestedDirIterator;

  File emptyDir;
  LevelOrderIterator emptyDirIterator;

  File subDir;

  File leafDir;
  LevelOrderIterator leafDirIterator;

  /**
   * Before each test, this method sets up the following hierarchy in a temporary directory:
   *
   * <p>/ /a.txt /empty/ /subdir/ /subdir/subsubdir/ /subdir/subsubdir/bar.exe
   * /subdir/subsubdir/foo.txt /subdir/yahoo /z.exe
   *
   * <p>tempFile points at a single temporary file singleIterator creates a LevelOrderIterator
   * initialized with tempFile
   *
   * <p>tempDir points at the root of the temporary directory nestedFileIterator creates a
   * LevelOrderIterator initialized with tempDir
   *
   * <p>emptyDir points at /empty/ emptyDirIterator creates a LevelOrderIterator initialized with
   * emptyDir
   *
   * <p>subDir points at /subdir/ subDirIterator creates a LevelOrderIterator initialized with
   * subDir
   *
   * <p>leafDir points at /subdir/subsubdir/ leafDirIterator creates a LevelOrderIterator
   * initialized with leafDir
   *
   * @throws IOException
   */
  @Before
  public void beforeLevelOrderIteratorTests() throws IOException {
    tempFile = File.createTempFile("queues", "tmp");
    singleIterator = new LevelOrderIterator(tempFile);

    tempDir = Files.createTempDirectory("queues").toFile();
    for (String fileName : new String[] {"a.txt", "z.exe"}) {
      File f = new File(tempDir, fileName);
      f.createNewFile();
    }

    emptyDir = new File(tempDir, "empty");
    emptyDir.mkdir();
    emptyDirIterator = new LevelOrderIterator(emptyDir);

    subDir = new File(tempDir, "subdir");
    subDir.mkdir();
    File subDirFile = new File(subDir, "yahoo");
    subDirFile.createNewFile();
    leafDir = new File(subDir, "subsubdir");
    leafDir.mkdir();
    for (String fileName : new String[] {"foo.txt", "bar.exe"}) {
      File f = new File(leafDir, fileName);
      f.createNewFile();
    }
    leafDirIterator = new LevelOrderIterator(leafDir);

    nestedDirIterator = new LevelOrderIterator(tempDir);
  }

  @After
  public void after() {
    tempFile.delete();
    tempDir.delete();
  }

  @SuppressWarnings("unused")
  @Test(expected = FileNotFoundException.class)
  public void testFileNotFound() throws Exception {
    LevelOrderIterator i =
        new LevelOrderIterator(new File("probablyyoudon'thaveafilewiththisname"));
  }

  @Test
  public void testHasNextAtStartSingle() throws Exception {
    assertTrue(singleIterator.hasNext());
  }

  @Test
  public void testHasNextAtEndSingle() throws Exception {
    singleIterator.next();
    assertFalse(singleIterator.hasNext());
  }

  @Test(expected = NoSuchElementException.class)
  public void testExceptionAtEndSingle() throws Exception {
    singleIterator.next();
    singleIterator.next();
  }

  @Test
  public void testSingleFile() throws Exception {
    assertTrue(singleIterator.hasNext());
    File f = singleIterator.next();
    assertEquals(tempFile, f);
  }

  @Test
  public void testEmptyDirectory() throws Exception {
    assertTrue(emptyDirIterator.hasNext());
    File f = emptyDirIterator.next();
    assertEquals(emptyDir, f);
  }

  @Test(expected = NoSuchElementException.class)
  public void testEmptyDirectoryException() throws Exception {
    assertTrue(emptyDirIterator.hasNext());
    emptyDirIterator.next();
    emptyDirIterator.next();
  }

  @Test
  public void testLeafDirIterator() throws Exception {
    assertEquals(leafDir, leafDirIterator.next());
    assertEquals(new File(leafDir, "bar.exe"), leafDirIterator.next());
    assertEquals(new File(leafDir, "foo.txt"), leafDirIterator.next());
  }

  @Test
  public void testNestedDirIterator() throws Exception {
    assertEquals(tempDir, nestedDirIterator.next());
    assertEquals(new File(tempDir, "a.txt"), nestedDirIterator.next());
    assertEquals(emptyDir, nestedDirIterator.next());
    assertEquals(subDir, nestedDirIterator.next());
    assertEquals(new File(tempDir, "z.exe"), nestedDirIterator.next());
    assertEquals(leafDir, nestedDirIterator.next());
    assertEquals(new File(subDir, "yahoo"), nestedDirIterator.next());
    assertEquals(new File(leafDir, "bar.exe"), nestedDirIterator.next());
    assertEquals(new File(leafDir, "foo.txt"), nestedDirIterator.next());
    assertFalse(nestedDirIterator.hasNext());
  }

  //
  // MergeSorter Tests
  //
  private MergeSorter<Integer> ms = new MergeSorter<Integer>();
  private Queue<Integer> empty;
  private Queue<Integer> one;
  private Queue<Integer> unsorted;
  private Queue<Integer> output1;
  private Queue<Integer> output2;

  @Before
  public void beforeMergeSorterTests() {
    empty = new Queue<Integer>();
    one = new Queue<Integer>();

    output1 = new Queue<Integer>();
    output2 = new Queue<Integer>();

    one.enqueue(1);
    unsorted = new Queue<Integer>();
    for (int i : new int[] {8, 4, 0, 3, 6, 1, 7, 9, 5, 2}) {
      unsorted.enqueue(i);
    }
  }

  @Test
  public void testDivideEmpty() throws Exception {
    ms.divide(empty, output1, output2);
    assertTrue(empty.isEmpty());
    assertTrue(output1.isEmpty());
    assertTrue(output2.isEmpty());
  }

  @Test
  public void testDivideOne() throws Exception {
    ms.divide(one, output1, output2);
    assertTrue(one.isEmpty());
    assertTrue(output1.isEmpty() || output2.isEmpty());
    assertFalse(output1.isEmpty() && output2.isEmpty());
  }

  @Test
  public void testDivideNSplitsEvenly() throws Exception {
    Queue<Integer> q = new Queue<Integer>();
    for (int i = 0; i < 11; i++) {
      q.enqueue(i);
    }
    ms.divide(q, output1, output2);
    assertTrue(
        ((output1.size() == 5) && (output2.size() == 6))
            || ((output1.size() == 6) && (output2.size() == 5)));
    ;
  }

  @Test
  public void testDivideNContainsAll() throws Exception {
    Queue<Integer> q = new Queue<Integer>();
    for (int i = 0; i < 11; i++) {
      q.enqueue(i);
    }
    ms.divide(q, output1, output2);
    int[] t = new int[11];
    int i = 0;
    while (!output1.isEmpty()) {
      t[i] = output1.dequeue();
      i++;
    }
    while (!output2.isEmpty()) {
      t[i] = output2.dequeue();
      i++;
    }
    Arrays.sort(t);
    for (int j = 0; j < 11; j++) {
      assertEquals(j, t[j]);
    }
  }

  @Test
  public void testMergeTwoEmpty() throws Exception {
    Queue<Integer> result = ms.merge(empty, empty);
    assertTrue(result.isEmpty());
  }

  @Test
  public void testMergeOneAndEmpty() throws Exception {
    Queue<Integer> result = ms.merge(one, empty);
    assertEquals(1, result.size());
    assertEquals(1, (int) result.dequeue());
  }

  @Test
  public void testMergeEmptyAndOne() throws Exception {
    Queue<Integer> result = ms.merge(empty, one);
    assertEquals(1, result.size());
    assertEquals(1, (int) result.dequeue());
  }

  @Test
  public void testMergeOneAndOne() throws Exception {
    Queue<Integer> two = new Queue<Integer>();
    two.enqueue(2);
    Queue<Integer> result = ms.merge(two, one);
    assertEquals(2, result.size());
    assertEquals(1, (int) result.dequeue());
    assertEquals(2, (int) result.dequeue());
  }

  @Test
  public void testMergeThreeAndOne() throws Exception {
    Queue<Integer> three = new Queue<Integer>();
    three.enqueue(-1);
    three.enqueue(1);
    three.enqueue(2);

    Queue<Integer> result = ms.merge(three, one);
    assertEquals(4, result.size());
    assertEquals(-1, (int) result.dequeue());
    assertEquals(1, (int) result.dequeue());
    assertEquals(1, (int) result.dequeue());
    assertEquals(2, (int) result.dequeue());
  }

  @Test
  public void testMergeOneAndThree() throws Exception {
    Queue<Integer> three = new Queue<Integer>();
    three.enqueue(-1);
    three.enqueue(1);
    three.enqueue(2);

    Queue<Integer> result = ms.merge(one, three);
    assertEquals(4, result.size());
    assertEquals(-1, (int) result.dequeue());
    assertEquals(1, (int) result.dequeue());
    assertEquals(1, (int) result.dequeue());
    assertEquals(2, (int) result.dequeue());
  }

  @Test
  public void testMergeSortEmpty() throws Exception {
    Queue<Integer> result = ms.mergeSort(empty);
    assertTrue(result.isEmpty());
  }

  @Test
  public void testMergeSortEmptyUnaliased() throws Exception {
    Queue<Integer> result = ms.mergeSort(empty);
    assertTrue(result.isEmpty());
    empty.enqueue(1);
    assertTrue(result.isEmpty());
  }

  @Test
  public void testMergeSort() throws Exception {
    Queue<Integer> sorted = ms.mergeSort(unsorted);
    assertEquals(10, sorted.size());
    for (int i = 0; i < 10; i++) {
      assertEquals(i, (int) sorted.dequeue());
    }
  }

  @Test
  public void testMergeSortUnaliased() throws Exception {
    Queue<Integer> sorted = ms.mergeSort(unsorted);
    sorted.dequeue();
    unsorted.enqueue(10);
    assertEquals(9, sorted.size());
    assertEquals(11, unsorted.size());
  }

}
