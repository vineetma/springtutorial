package com.dakinegroup;
import org.junit.Before;
import org.junit.Test;
public class StoreItemTest extends junit.framework.TestCase {
   private StoreItem si;
   @Before 
   public void setUp() {
     si = new StoreItem(); 
   }

   @Test
   public void testSetDescription1() {
     System.out.println("Testing setDescription..");
     si.setDescription1("Item 1");
     assertEquals("Item 1",si.getDescription1());
   }
}
