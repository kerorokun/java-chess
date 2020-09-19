package btn.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import btn.utils.Vector2i;

public class Vector2iTests {

	@Test
	public void testConstructorZero() {
		Vector2i vec = new Vector2i(0, 0);

		assertEquals(vec.getX(), 0);
		assertEquals(vec.getY(), 0);
	}

	@Test
	public void testConstructorValid1() {
		Vector2i vec = new Vector2i(1, 7);

		assertEquals(vec.getX(), 1);
		assertEquals(vec.getY(), 7);
	}

	@Test
	public void testConstructorValid2() {
		Vector2i vec = new Vector2i(-3, -18);

		assertEquals(vec.getX(), -3);
		assertEquals(vec.getY(), -18);
	}

	@Test
	public void testAddZero() {
		Vector2i vec1 = new Vector2i(0, 0);
		Vector2i vec2 = new Vector2i(0, 0);
		Vector2i res = vec1.add(vec2);

		assertEquals(res.getX(), 0);
		assertEquals(res.getY(), 0);
	}

	@Test
	public void testAddNeg() {
		Vector2i vec1 = new Vector2i(0, 0);
		Vector2i vec2 = new Vector2i(-10, -10);
		Vector2i res = vec1.add(vec2);

		assertEquals(res.getX(), -10);
		assertEquals(res.getY(), -10);
	}

	@Test
	public void testAddSame() {
		Vector2i vec1 = new Vector2i(10, -2);
		Vector2i vec2 = new Vector2i(-10, 2);
		Vector2i res = vec1.add(vec2);

		assertEquals(res.getX(), 0);
		assertEquals(res.getY(), 0);
	}

	@Test
	public void testAddNormal1() {
		Vector2i vec1 = new Vector2i(10, -2);
		Vector2i vec2 = new Vector2i(8, 4);
		Vector2i res = vec1.add(vec2);

		assertEquals(res.getX(), 18);
		assertEquals(res.getY(), 2);
	}


	@Test
	public void testSubtractZero() {
		Vector2i vec1 = new Vector2i(0, 0);
		Vector2i vec2 = new Vector2i(0, 0);
		Vector2i res = vec1.subtract(vec2);

		assertEquals(res.getX(), 0);
		assertEquals(res.getY(), 0);
	}

	@Test
	public void testSubtractNeg() {
		Vector2i vec1 = new Vector2i(0, 0);
		Vector2i vec2 = new Vector2i(10, 10);
		Vector2i res = vec1.subtract(vec2);

		assertEquals(res.getX(), -10);
		assertEquals(res.getY(), -10);
	}

	@Test
	public void testSubtractSame() {
		Vector2i vec1 = new Vector2i(10, -2);
		Vector2i vec2 = new Vector2i(10, -2);
		Vector2i res = vec1.subtract(vec2);

		assertEquals(res.getX(), 0);
		assertEquals(res.getY(), 0);
	}

	@Test
	public void testSubtractNormal1() {
		Vector2i vec1 = new Vector2i(10, -2);
		Vector2i vec2 = new Vector2i(8, 4);
		Vector2i res = vec1.subtract(vec2);

		assertEquals(res.getX(), 2);
		assertEquals(res.getY(), -6);
	}

	@Test
	public void testEqualsSame() {
		Vector2i vec = new Vector2i(0, 0);
		assertTrue(vec.equals(vec));
	}

	@Test
	public void testEqualsNull() {
		Vector2i vec = new Vector2i(0, 0);
		assertFalse(vec.equals(null));
	}

	@Test
	public void testEqualsNonVector2i() {
		Vector2i vec = new Vector2i(0, 0);
		assertFalse(vec.equals("Hello"));
	}

	@Test
	public void testEqualsNormalTrue1() {
		Vector2i vec1 = new Vector2i(1, 5);
		Vector2i vec2 = new Vector2i(1, 5);
		assertTrue(vec1.equals(vec2));
	}

	@Test
	public void testEqualsNormalTrue2() {
		Vector2i vec1 = new Vector2i(0, 0);
		Vector2i vec2 = new Vector2i(0, 0);
		assertTrue(vec1.equals(vec2));
	}

	@Test
	public void testEqualsNormalFalse1() {
		Vector2i vec1 = new Vector2i(0, 0);
		Vector2i vec2 = new Vector2i(0, 1);
		assertFalse(vec1.equals(vec2));
	}

	@Test
	public void testEqualsNormalFalse2() {
		Vector2i vec1 = new Vector2i(-1, 7);
		Vector2i vec2 = new Vector2i(0, 1);
		assertFalse(vec1.equals(vec2));
	}
}
