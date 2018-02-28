package com.palusers.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.createUser.domain.UAAEmailData;
public class UAAEmailDataTest {

	@Test
    public void test() {		
				
		UAAEmailData uAAEmailData = new UAAEmailData();
		uAAEmailData.primary = true;
		uAAEmailData.value = "value";
		
        assertEquals(true,uAAEmailData.primary);
        assertEquals("value",uAAEmailData.value);
        
	}
}
